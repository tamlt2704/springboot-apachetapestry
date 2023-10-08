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
public class Create1 {

    private String demoModeStr = System.getProperty("jumpstart.demo-mode");

    // Screen fields

    @Property
    private Person person;

    // Other pages

    @InjectPage
    private Create2 page2;

    // Generally useful bits and pieces

    @InjectComponent("personForm")
    private BeanEditForm personForm;

    @Inject
    private PersonRepository personRepository;
    // The code

    // PersonForm bubbles up the PREPARE_FOR_RENDER event when it is rendered

    void onPrepareForRender() throws Exception {

        // If fresh start, make sure there's a Person object available.

        if (personForm.isValid()) {
            person = new Person();
        }
    }

    // PersonForm bubbles up the PREPARE_FOR_SUBMIT event when it is submitted

    void onPrepareForSubmit() throws Exception {
        // Instantiate a Person for the form data to overlay.
        person = new Person();
    }

    void onValidateFromPersonForm() {

        if (person.getFirstName() != null && person.getFirstName().equals("Acme")) {
            personForm.recordError("First Name must not be Acme.");
        }

        if (demoModeStr != null && demoModeStr.equals("true")) {
            personForm.recordError("Sorry, but this function is not allowed in Demo mode.");
        }

        if (personForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
            personRepository.save(person);
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            personForm.recordError(e.getMessage());
        }
    }

    Object onSuccess() {
        page2.set(person.getId());
        return page2;
    }

    void onRefresh() {
        // By doing nothing the page will be displayed afresh.
    }
}