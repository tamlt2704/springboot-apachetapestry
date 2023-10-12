package jumpstart.web.pages.together.easycrud.person;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "css/examples/plain.css")
public class PersonReview {

    // The activation context

    @Property
    private Long personId;

    // Screen fields

    @Property
    private Person person;

    // Generally useful bits and pieces

    @Inject
    private PersonRepository personRepository;

    // The code

    void onActivate(Long personId) {
        this.personId = personId;
    }

    Long onPassivate() {
        return personId;
    }

    void setupRender() {
        person = personRepository.findById(personId).orElse(null);
        // Handle null person in the template.
    }

}
