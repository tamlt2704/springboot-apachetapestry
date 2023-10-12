package jumpstart.web.pages.together.easycrud;

import java.util.List;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "css/examples/js.css")
public class Persons {

    private String demoModeStr = System.getProperty("jumpstart.demo-mode");
    static private final int MAX_RESULTS = 30;

    // Screen fields

    @Property
    private List<Person> persons;

    @Property
    private Person person;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String errorMessage;

    // Generally useful bits and pieces

    @Inject
    private PersonRepository personRepository;

    // The code

    void setupRender() {
        persons = personRepository.findPersons(MAX_RESULTS);
    }

    void onDelete(Long id, Integer version) {

        if (demoModeStr != null && demoModeStr.equals("true")) {
            errorMessage = "Sorry, but this function is not allowed in Demo mode.";
            return;
        }

        try {
            personRepository.deleteById(id);
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            errorMessage = e.getMessage();
        }
    }
}
