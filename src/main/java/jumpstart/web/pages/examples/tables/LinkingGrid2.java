package jumpstart.web.pages.examples.tables;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.springframework.context.event.EventListener;

@Import(stylesheet = "css/examples/plain.css")
public class LinkingGrid2 {
    @Property
    private String firstName;

    void onActivate(String firstName) {
        this.firstName = firstName;
    }

    String onPassivate() {
        return firstName;
    }
}
