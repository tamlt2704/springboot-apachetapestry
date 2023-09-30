package jumpstart.web.pages.examples.tables;

import jumpstart.entity.Person;
import jumpstart.entity.Regions;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import java.util.Locale;

import java.text.DateFormat;
import java.text.Format;
import java.util.List;

@Import(stylesheet = "css/examples/styledloop.css")
public class StyledLoop {
    static private final int MAX_RESULTS = 10;

    @Property
    private List<Person> persons;

    @Property
    private Person person;

    @Inject
    private Messages messages;

    @Inject
    private Locale currentLocale;

    @Inject
    PersonRepository personRepository;

    void setupRender() {
        persons = personRepository.findPersons(MAX_RESULTS);
    }

    public String getPersonRegion() {
        return messages.get(Regions.class.getSimpleName() + "." + person.getRegion().name());
    }

    public Format getDateFormat() {
        return DateFormat.getDateInstance(DateFormat.MEDIUM, currentLocale);
    }
}
