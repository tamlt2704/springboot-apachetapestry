package jumpstart.web.pages.navigation;

import jumpstart.entity.Mode;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

import java.math.BigDecimal;

@Import(stylesheet = "css/examples/olive.css")
public class MoreParameters2 {
    @Property
    private int intParam;
    @Property
    private Long longParam;
    @Property
    private String stringParam;
    @Property
    private double doubleParam;
    @Property
    private BigDecimal bigDecimalParam;
    @Property
    private boolean booleanParam;
    @Property
    private Mode modeParam;
    @Property
    private Mode mode2Param;

    void onActivate(int intParam, Long longParam, String stringParam, double doubleParam, BigDecimal bigDecimalParam,
                    boolean booleanParam, Mode modeParam, Mode mode2Param) {
        this.intParam = intParam;
        this.longParam = longParam;
        this.doubleParam = doubleParam;
        this.stringParam = stringParam;
        this.bigDecimalParam = bigDecimalParam;
        this.booleanParam = booleanParam;
        this.modeParam = modeParam;
        this.mode2Param = mode2Param;
    }
}
