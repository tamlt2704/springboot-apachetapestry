package jumpstart.web.pages.examples.tables;

import jumpstart.entity.Person;
import jumpstart.entity.Regions;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.text.DateFormat;
import java.text.Format;
import java.util.List;
import java.util.Locale;

@Import(stylesheet = "css/examples/plain.css")
public class LinkingLoop1 {
    static private final int MAX_RESULTS = 10;

    @Property
    private List<Person> persons;

    @Property
    private Person person;

    @Inject
    PersonRepository personRepository;

    void setupRender() {
        persons = personRepository.findPersons(MAX_RESULTS);
    }
}
