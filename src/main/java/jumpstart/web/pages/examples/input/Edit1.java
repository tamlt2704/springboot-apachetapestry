package jumpstart.web.pages.examples.input;

import jumpstart.entity.Person;

import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "css/examples/plain.css")
public class Edit1 {

    // The activation context

    private Long personId;

    // Screen fields

    @Property
    private Person person;


    @Inject
    private PersonRepository personRepository;

    // Other pages

    @InjectPage
    private Edit2 page2;

    // Generally useful bits and pieces

    @InjectComponent("personForm")
    private BeanEditForm form;

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

    void onPrepareForRender() throws Exception {

        // If fresh start, make sure there's a Person object available.

        if (form.isValid()) {
            person = findPerson(personId);

            // We'd like to handle null person in the template, but can't because we're in BeanEditForm.
            // BeanEditForm doesn't handle null object well, so throw an exception to bypass it.

            if (person == null) {
                throw new Exception("Person " + personId + " does not exist.");
            }
        }

    }

    // Form bubbles up the PREPARE_FOR_SUBMIT event during form submission.

    void onPrepareForSubmit() {

        // Get Person object for the form fields to overlay.
        person = findPerson(personId);

        if (person == null) {
            form.recordError("Person has been deleted by another process.");
            // Instantiate an empty person to avoid NPE in the BeanEditForm.
            person = new Person();
        }
    }

    void onValidateFromPersonForm() {

        if (person.getFirstName() != null && person.getFirstName().equals("Acme")) {
            form.recordError("First Name must not be Acme.");
        }

        if (personId == 2 && !person.getFirstName().equals("Mary")) {
            form.recordError("First Name for this person must be Mary.");
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
        Person person = personRepository.findById(personId).orElse(null);

        if (person == null && personId < 4) {
            throw new IllegalStateException("Database data has not been set up!");
        }

        return person;
    }
}