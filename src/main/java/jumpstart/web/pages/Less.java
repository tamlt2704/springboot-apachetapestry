package jumpstart.web.pages;

import org.apache.tapestry5.annotations.Import;

@Import(stylesheet = "css/examples/olive.less")
public class Less {

    public String getUsername() {
        return "world";
    }
}
