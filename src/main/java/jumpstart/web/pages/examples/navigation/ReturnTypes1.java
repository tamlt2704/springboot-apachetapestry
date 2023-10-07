package jumpstart.web.pages.examples.navigation;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.http.Link;
import org.apache.tapestry5.http.services.Response;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.HttpError;
import org.apache.tapestry5.services.PageRenderLinkSource;

@Import(stylesheet = "css/examples/plain.css")
public class ReturnTypes1 {

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String message;

    void onReturnVoid() {
        message = "You chose to return void.";
    }

    Object onReturnNull() {
        message = "You chose to return null.";
        return null;
    }

    boolean onReturnFalse() {
        message = "You chose to return false.";
        return false;
    }

    boolean onReturnTrue() {
        message = "You chose to return true.";
        return true;
    }

    Class<ReturnTypesClass> onReturnClass() {
        return ReturnTypesClass.class;
    }

    String onReturnString() {
        return "examples/navigation/ReturnTypesString";
    }

    @InjectPage
    private ReturnTypesPageInstance pageInstance;

    Object onReturnPageInstance() {
        pageInstance.set("Hello");
        return pageInstance;
    }

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    Link onReturnLink() {
        String parameters = "Howdy";
        Link link = pageRenderLinkSource.createPageRenderLinkWithContext(ReturnTypesLink.class, parameters);
        return link;
    }

    StreamResponse onReturnStreamResponse() {
        return new StreamResponse() {
            InputStream inputStream;

            @Override
            public void prepareResponse(Response response) {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                inputStream = classLoader.getResourceAsStream("jumpstart/web/text/examples/ReturnTypeText.txt");

                // Set content length to prevent chunking - see
                // http://tapestry-users.832.n2.nabble.com/Disable-Transfer-Encoding-chunked-from-StreamResponse-td5269662.html#a5269662

                try {
                    response.setHeader("Content-Length", "" + inputStream.available());
                } catch (IOException e) {
                    // Ignore the exception in this simple example.
                }
            }

            @Override
            public String getContentType() {
                return "text/plain";
            }

            @Override
            public InputStream getStream() throws IOException {
                return inputStream;
            }
        };
    }

    URL onReturnURL() throws MalformedURLException {
        return new URL("http://tapestry.apache.org");
    }

    @OnEvent(value = "returnhttperror")
    HttpError onReturnHttpError() {
        return new HttpError(404, "Page not found");
    }
}