package jumpstart.web.pages.examples.tables;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

@Import(stylesheet = "css/examples/plain.css")
public class LinkingGrid1 {
    public static final int MAX_RESULTS = 30;

    @Property
    private List<Person> persons;

    @Property
    private Person person;

    @Inject
    private PersonRepository personRepository;

    void setupRender() {
        persons = personRepository.findPersons(MAX_RESULTS);
    }
}
