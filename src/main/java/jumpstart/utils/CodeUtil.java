package jumpstart.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.compress.utils.IOUtils;
import org.jasypt.util.text.StrongTextEncryptor;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*
Collection<File> filesToArchive = ...
try (ArchiveOutputStream o = ... create the stream for your format ...) {
    for (File f : filesToArchive) {
        // maybe skip directories for formats like AR that don't store directories
        ArchiveEntry entry = o.createArchiveEntry(f, entryName(f));
        // potentially add more flags to entry
        o.putArchiveEntry(entry);
        if (f.isFile()) {
            try (InputStream i = Files.newInputStream(f.toPath())) {
                IOUtils.copy(i, o);
            }
        }
        o.closeArchiveEntry();
    }
    o.finish();
}
 */

public class CodeUtil {
    @SneakyThrows
    public static void main(String[] args) throws IOException, WriterException {
        List<File> filesToArchive = new ArrayList<>();
        Files.walk(Paths.get(".")).forEach(path -> {
            File file = path.toFile();
            if (tobeIncluded(file)) {
//                System.out.println(file.getAbsolutePath());
                filesToArchive.add(file);
            }
        });

        /*byte[] buffer = new byte[1024];
        File out = new File("out.7z");
        SevenZOutputFile sevenZOutput = new SevenZOutputFile(out);
        for (File inputFile : filesToArchive) {
            SevenZArchiveEntry ze = sevenZOutput.createArchiveEntry(inputFile, inputFile.getCanonicalPath());
            try {
                FileInputStream in = new FileInputStream(inputFile);
                sevenZOutput.putArchiveEntry(ze);
                int len;
                while ((len = in.read(buffer)) > 0) {
                    sevenZOutput.write(buffer, 0, len);
                }
                sevenZOutput.closeArchiveEntry();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("added: " + inputFile.getAbsolutePath());
        }
        sevenZOutput.close();*/
        byte[] buf = new byte[2048];
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("out.tar.gz"));
        for (File file : filesToArchive) {
            out.putNextEntry(new ZipEntry(file.getCanonicalPath()));
            int len;
            FileInputStream in = new FileInputStream(file);
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.closeEntry();
            in.close();
            System.out.println("added " + file.getCanonicalPath());
        }
        out.close();
        System.out.println("Done.");


        Base64 base64 = new Base64();

        String encodedString = new String(base64.encode(Files.readAllBytes(Paths.get("out.tar.gz"))));
        System.out.println(encodedString);
        Files.write(Paths.get("base64raw.txt"), encodedString.getBytes());
        //String decodedString = new String(base64.decode(encodedString.getBytes()));

        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword("myEncryptionPassword");

        String encrypted = textEncryptor.encrypt(encodedString);
        Files.write(Paths.get("base64encrypted.txt"), encrypted.getBytes());

        String decrypted = textEncryptor.decrypt(encrypted);
        String decodedString = new String(base64.decode(decrypted.getBytes()));
        Files.write(Paths.get("out2.tar.gz"), decodedString.getBytes());

        System.out.println("before " + encodedString.substring(0,10));
        generateQRCodeImage(encodedString.substring(0,10), 50, 50, "test.png");
        BinaryBitmap binaryBitmap
                = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(
                        ImageIO.read(
                                new FileInputStream("test.png")))));

        Result result
                = new MultiFormatReader().decode(binaryBitmap);

        System.out.println(result.getText());
    }

    private static boolean tobeIncluded(File file) {
        boolean included = file.getName().equalsIgnoreCase("build.gradle");
//        included |= file.getPath().contains("src");
//        included &= file.isFile();
        return included;
    }

    public static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }


    public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig( 0xFF000002 , 0xFFFFC041 ) ;

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream,con);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }
}
