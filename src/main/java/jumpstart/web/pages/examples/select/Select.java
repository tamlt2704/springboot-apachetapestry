package jumpstart.web.pages.examples.select;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet = "css/examples/select.css")
public class Select {

    @Property
    private String color0;

    @Property
    private Color1 color1;

    public enum Color1 {
        RED, GREEN, BLUE
    }

    @Property
    private String color2;

    @Property
    private Color3 color3;

    public enum Color3 {
        YELLOW, CYAN, MAGENTA
    }

    void onActivate(String color0, Color1 color1, String color2, Color3 color3) {
        this.color0 = color0;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
    }

    Object[] onPassivate() {
        return new Object[] { color0, color1, color2, color3 };
    }

}