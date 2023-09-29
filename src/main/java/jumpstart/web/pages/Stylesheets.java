package jumpstart.web.pages;

import org.apache.tapestry5.annotations.Import;

@Import(stylesheet = "css/examples/olive.css")
public class Stylesheets {
    public String getUserName() {
        return "world";
    }
}
