package jumpstart.web.pages.examples.tables;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

@Import(stylesheet = "css/examples/plain.css")
public class LinkingLoop2 {
    @Property
    private String firstName;

    void onActivate(String firstName) {
        this.firstName = firstName;
    }

    String onPassivate() {
        return firstName;
    }
}
