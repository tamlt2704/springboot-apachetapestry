package jumpstart.web.pages;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "css/examples/plain.css")
public class ActivationContext2 {

    @Inject
    private PersonRepository personRepository;

    @Property
    private Long personId;

    @Property
    private Person person;

    void onActivate(Long personId) {
        this.personId = personId;
    }

    void setupRender() throws Exception {
        person = personRepository.findById(personId).orElse(null);

        if (person == null) {
            throw new Exception("No person with id " + personId);
        }
    }
}
