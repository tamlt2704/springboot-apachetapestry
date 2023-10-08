package jumpstart.web.pages.examples.input;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;

// The @Import tells Tapestry to put a link to the file in the head of the page so that the browser will pull it in.
@Import(stylesheet = "css/examples/plain.css")
public class TotalControlEdit1 {

    // The activation context

    @Property
    private Long personId;

    // Screen fields

    @Property
    private Person person;

    // Other pages

    @InjectPage
    private TotalControlEdit2 page2;

    // Generally useful bits and pieces

    @InjectComponent("person")
    private Form form;

    @InjectComponent("firstName")
    private TextField firstNameField;

    @Inject
    private PersonRepository personRepository;

    // The code

    // onActivate() is called by Tapestry to pass in the activation context from the URL.

    void onActivate(Long personId) {
        this.personId = personId;
    }

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.

    Long onPassivate() {
        return personId;
    }

    // Form bubbles up the PREPARE_FOR_RENDER event during form render.

    void onPrepareForRender() {

        // If fresh start, make sure there's a Person object available.

        if (form.isValid()) {
            person = findPerson(personId);
            // Handle null person in the template (with an If component).
        }

    }

    // Form bubbles up the PREPARE_FOR_SUBMIT event during form submission.

    void onPrepareForSubmit() {

        // Get objects for the form fields to overlay.
        person = findPerson(personId);

        if (person == null) {
            form.recordError("Person has been deleted by another process.");
            // Instantiate an empty person to avoid NPE in the Form.
            person = new Person();
        }
    }

    void onValidateFromPerson() {

        if (person.getFirstName() != null && person.getFirstName().equals("Acme")) {
            form.recordError(firstNameField, firstNameField.getLabel() + " must not be Acme.");
        }

        if (personId == 2 && !person.getFirstName().equals("Mary")) {
            form.recordError(firstNameField, firstNameField.getLabel() + " for this person must be Mary.");
        }

        if (form.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
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
        page2.set(personId);
        return page2;
    }

    void onRefresh() {
        // By doing nothing the page will be displayed afresh.
    }

    private Person findPerson(Long personId) {
        // Ask business service to find Person
        Person person = personRepository.findById(personId).orElse(null);

        if (person == null && personId < 4) {
            throw new IllegalStateException("Database data has not been set up!");
        }

        // Handle null person in the template (with an If component).

        return person;
    }
}