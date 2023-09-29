package jumpstart.web.pages.output;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "css/examples/olive.css")
public class EasyOutputEntityBean {
    @Property
    private Person person;

    @Inject
    private PersonRepository personRepository;

    void setupRender() {
        person = personRepository.findById(1L).orElse(null);

        if (person == null) {
            throw new IllegalStateException("Database does not contains person with id " + 1);
        }
    }
}
