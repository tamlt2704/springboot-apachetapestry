package jumpstart.web.pages.examples.infrastructure;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "css/examples/plain.css")
public class HandlingABadContext {

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
        // Get person - ask business service to find it (from the database)
        person = personRepository.findById(personId).orElse(null);
        // Handle null person in the template (with an If component).
    }
}
