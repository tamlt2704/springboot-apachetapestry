package jumpstart.web.pages.examples.navigation;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "css/examples/plain.css")
public class OnActivateAndOnPassivate {

    // The activation context

    private Long personId;

    // Screen fields

    @Property
    private Person person;

    // Generally useful bits and pieces

    @Inject
    PersonRepository personRepository;

    // The code

    // onActivate() is called by Tapestry to pass in the activation context from the URL.

    void onActivate(Long personId) {
        this.personId = personId;
    }

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.

    Long onPassivate() {
        return personId;
    }

    // setupRender() is called by tapestry at the start of rendering - it's good for things that are display only.

    void setupRender() throws Exception {
        // Get person - ask business service to find it (from the database)
        person = personRepository.findById(personId).orElse(null);

        if (person == null) {
            throw new Exception("Database data has not been set up!");
        }
    }
}