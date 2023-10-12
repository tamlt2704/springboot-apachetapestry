package jumpstart.web.pages.together.totalcontrolcrud.person;

import jumpstart.entity.Person;
import jumpstart.entity.Regions;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "css/together/totalcontrolcrudreview.css")
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

    @Inject
    private Messages messages;

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

    public String getPersonRegion() {
        return messages.get(Regions.class.getSimpleName() + "." + person.getRegion().name());
    }

    public Format getStartDateFormat() {
        final Format f = new SimpleDateFormat("dd MMMM yyyy G");
        return f;
    }
}