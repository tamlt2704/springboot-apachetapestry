package jumpstart.web.pages;

import org.apache.tapestry5.annotations.Import;

@Import(stylesheet = "css/examples/index.css")
public class Index {
    /** An ordinary getter. */
    public String getUsername() {
        return "world";
    }
}
