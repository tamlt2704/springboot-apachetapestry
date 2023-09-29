package jumpstart.web.pages;

import jumpstart.entity.Person;
import jumpstart.entity.Regions;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.text.Format;
import java.text.SimpleDateFormat;

@Import(stylesheet = "css/examples/totalcontroloutput.css")
public class TotalControlOutput {
    @Property
    private Person person;

    @Property
    private String regionName;

    @Property
    private Format startDateFormat;

    @Inject
    private PersonRepository personRepository;

    @Inject
    private Messages messages;

    void setupRender() {
        person = personRepository.findById(1L).orElse(null);

        if (person == null) {
            throw new IllegalStateException("Database does not contains person with id " + 1);
        }

        regionName = messages.get(Regions.class.getSimpleName() + "." + person.getRegion().name());

        startDateFormat = new SimpleDateFormat(" dd MMMM yyyy G");
    }
}
