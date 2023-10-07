package jumpstart.web.pages.examples.ajax;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.http.services.Request;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Import(stylesheet = "css/examples/js.css")
public class AjaxFormMultipleZoneUpdate {
    @Property
    @NotNull
    @Size(max = 10)
    private String firstName;

    @Property
    @NotNull
    @Size(max = 10)
    private String lastName;

    @Property
    @NotNull
    @Past
    private Date birthday;

    @Inject
    private Request request;
    @InjectComponent("ajaxForm")
    private Form form;

    @InjectComponent("firstName")
    private TextField firstNameField;

    @InjectComponent
    private Zone formZone;

    @InjectComponent
    private Zone outZone;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    void setupRender() {
        if (firstName == null && lastName == null && birthday == null) {
            firstName = "Humpty";
            lastName = "Dumpty";
            birthday = new Date(0);
        }
    }

    void onValidateFromAjaxForm() {
        if (firstName != null && firstName.equals("Acme")) {
            form.recordError(firstNameField, "First Name must not be Acme.");
        }
    }

    void onSuccess() {
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(formZone).addRender(outZone);
        }
    }

    void onFailure() {
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(formZone);
        }
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
