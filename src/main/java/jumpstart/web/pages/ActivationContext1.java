package jumpstart.web.pages;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet = "css/examples/olive.css")
public class ActivationContext1 {
    @Property
    private Long personId;

    void setupRender() {
        personId = 1L;
    }
}
