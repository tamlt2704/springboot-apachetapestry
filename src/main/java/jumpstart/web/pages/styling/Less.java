package jumpstart.web.pages.styling;

import org.apache.tapestry5.annotations.Import;

@Import(stylesheet = "css/examples/olive.less")
public class Less {

    public String getUsername() {
        return "world";
    }
}
