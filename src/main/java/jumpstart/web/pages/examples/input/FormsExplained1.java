package jumpstart.web.pages.examples.input;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Import(stylesheet = "css/examples/plain.css")
public class FormsExplained1 {

    @Property
    @NotNull
    @Size(max = 10)
    private String firstName;

    @Property
    @NotNull
    @Size(max = 10)
    private String lastName;

    @InjectPage
    private FormsExplained2 page2;

    @InjectComponent("names")
    private Form form;

    @InjectComponent("firstName")
    private TextField firstNameField;

    void onValidateFromNames() {
        if (firstName != null && firstName.equals("Acme")) {
            form.recordError(firstNameField, "First Name must not be Acme");
        }
    }

    Object onSuccess() {
        page2.set(firstName, lastName);
        return page2;
    }
}
