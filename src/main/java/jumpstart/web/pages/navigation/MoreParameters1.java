package jumpstart.web.pages.navigation;

import jumpstart.entity.Mode;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

import java.math.BigDecimal;

@Import(stylesheet = "css/examples/olive.css")
public class MoreParameters1 {
    @Property
    private int anInt;
    @Property
    private Long aLong;
    @Property
    private String aString;
    @Property
    private double aDouble;
    @Property
    private BigDecimal aBigDecimal;
    @Property
    private boolean aBoolean;

    @Property
    private Mode aMode;

    void setupRender() {
        anInt = 1;
        aLong = 3L;
        aString = "3";
        aBigDecimal = new BigDecimal("5.432");
        aBoolean = true;
        aMode = Mode.REVIEW;
    }
}
