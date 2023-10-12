package jumpstart.web.pages.together.totalcontrolcrud;

import java.text.DateFormat;
import java.text.Format;
import java.util.List;
import java.util.Locale;


import jumpstart.entity.Person;
import jumpstart.entity.Regions;
import jumpstart.repository.PersonRepository;
import jumpstart.web.commons.EvenOdd;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "css/examples/plain.css")
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

    @Property
    private EvenOdd evenOdd;

    // Generally useful bits and pieces

    @Inject
    private PersonRepository personRepository;

    @Inject
    private Messages messages;

    @Inject
    private Locale currentLocale;

    // The code

    void setupRender() {
        persons = personRepository.findPersons(MAX_RESULTS);
        evenOdd = new EvenOdd();
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

    public String getPersonRegion() {
        return messages.get(Regions.class.getSimpleName() + "." + person.getRegion().name());
    }

    public Format getDateFormat() {
        return DateFormat.getDateInstance(DateFormat.MEDIUM, currentLocale);
    }
}
