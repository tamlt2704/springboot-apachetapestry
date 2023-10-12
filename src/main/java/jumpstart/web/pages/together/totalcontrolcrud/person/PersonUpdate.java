package jumpstart.web.pages.together.totalcontrolcrud.person;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import jumpstart.web.pages.together.easycrud.Persons;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "css/examples/plain.css")
public class PersonUpdate {

    // The activation context

    @Property
    private Long personId;

    // Screen fields

    @Property
    private Person person;

    // Other pages

    @InjectPage
    private Persons indexPage;

    // Generally useful bits and pieces

    @InjectComponent
    private BeanEditForm form;

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

        // We're doing this here instead of in onPrepareForRender() because person is used outside the form...

        // If fresh start, make sure there's a Person object available.

        if (form.isValid()) {
            person = personRepository.findById(personId).orElse(null);
            // Handle null person in the template.
        }

    }

    void onPrepareForSubmit() {

        // Get Person object for the form fields to overlay.
        person = personRepository.findById(personId).orElse(null);

        if (person == null) {
            form.recordError("Person has been deleted by another process.");
            // Instantiate an empty person to avoid NPE in the BeanEditForm.
            person = new Person();
        }
    }

    Object onCanceled() {
        return indexPage;
    }

    void onValidateFromForm() {

        if (person.getFirstName() != null && person.getFirstName().equals("Acme")) {
            form.recordError("First name must not be Acme.");
        }

        if (personId == 2 && !person.getFirstName().equals("Mary")) {
            form.recordError("First Name for this person must be Mary.");
        }

        if (form.getHasErrors()) {
            return;
        }

        try {
            personRepository.save(person);
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            form.recordError(e.getMessage());
        }
    }

    Object onSuccess() {
        return indexPage;
    }

}