package jumpstart.web.pages.together.onepagecrud;
import java.text.Format;
import java.text.SimpleDateFormat;

import jumpstart.entity.Person;
import jumpstart.entity.Regions;
import jumpstart.repository.PersonRepository;
import jumpstart.web.models.together.PersonPagedDataSource;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(stylesheet = "css/together/onepagecrud.css")
public class Persons {

    private String demoModeStr = System.getProperty("jumpstart.demo-mode");

    private enum Mode {
        CREATE, REVIEW, UPDATE;
    }

    // The activation context

    @Property
    private Mode editorMode;

    @Property
    private Long editorPersonId;

    // Screen fields

    @Property
    private GridDataSource listPersons;

    @Property
    private Person listPerson;

    @Property
    private Person editorPerson;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String deleteMessage;

    // Generally useful bits and pieces

    @Inject
    private PersonRepository personRepository;

    @InjectComponent
    private Form createForm;

    @InjectComponent
    private Form updateForm;

    @InjectComponent("firstName")
    private TextField firstNameField;

    @Inject
    private Messages messages;

    // The code

    void onActivate(EventContext ec) {

        if (ec.getCount() == 0) {
            editorMode = null;
            editorPersonId = null;
        }
        else if (ec.getCount() == 1) {
            editorMode = ec.get(Mode.class, 0);
            editorPersonId = null;
        }
        else {
            editorMode = ec.get(Mode.class, 0);
            editorPersonId = ec.get(Long.class, 1);
        }

    }

    Object[] onPassivate() {

        if (editorMode == null) {
            return null;
        }
        else if (editorMode == Mode.CREATE) {
            return new Object[] { editorMode };
        }
        else if (editorMode == Mode.REVIEW || editorMode == Mode.UPDATE) {
            return new Object[] { editorMode, editorPersonId };
        }
        else {
            throw new IllegalStateException(editorMode.toString());
        }

    }

    void setupRender() {
        listPersons = new PersonPagedDataSource(personRepository);

        if (editorMode == Mode.REVIEW) {
            if (editorPersonId == null) {
                editorPerson = null;
                // Handle null editorPerson in the template.
            }
            else {
                if (editorPerson == null) {
                    editorPerson = personRepository.findById(editorPersonId).orElse(null);
                    // Handle null editorPerson in the template.
                }
            }
        }

    }

    void onToCreate() {
        editorMode = Mode.CREATE;
        editorPersonId = null;
    }

    void onPersonSelected(Long personId) {
        editorMode = Mode.REVIEW;
        editorPersonId = personId;
    }

    // /////////////////////////////////////////////////////////////////////
    // CREATE
    // /////////////////////////////////////////////////////////////////////

    void onCancelCreate() {
        editorMode = null;
        editorPersonId = null;
    }

    void onPrepareForRenderFromCreateForm() throws Exception {

        // If fresh start, make sure there's a Person object available.

        if (createForm.isValid()) {
            editorMode = Mode.CREATE;
            editorPerson = new Person();
        }
    }

    void onPrepareForSubmitFromCreateForm() throws Exception {
        editorMode = Mode.CREATE;
        // Instantiate a Person for the form data to overlay.
        editorPerson = new Person();
    }

    void onValidateFromCreateForm() {

        if (editorPerson.getFirstName() != null && editorPerson.getFirstName().equals("Acme")) {
            createForm.recordError(firstNameField, firstNameField.getLabel() + " must not be Acme.");
        }

        if (demoModeStr != null && demoModeStr.equals("true")) {
            createForm.recordError("Sorry, but Create is not allowed in Demo mode.");
        }

        if (createForm.getHasErrors()) {
            return;
        }

        try {
            editorPerson = personRepository.save(editorPerson);
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            createForm.recordError(e.getMessage());
        }
    }

    void onSuccessFromCreateForm() {
        editorMode = Mode.REVIEW;
        editorPersonId = editorPerson.getId();
    }

    // /////////////////////////////////////////////////////////////////////
    // REVIEW
    // /////////////////////////////////////////////////////////////////////

    void onToUpdate(Long personId) {
        editorMode = Mode.UPDATE;
        editorPersonId = personId;
    }

    void onDelete(Long personId, Integer personVersion) {
        editorMode = Mode.REVIEW;
        editorPersonId = personId;

        if (demoModeStr != null && demoModeStr.equals("true")) {
            deleteMessage = "Sorry, but Delete is not allowed in Demo mode.";
            return;
        }

        try {
            personRepository.deleteById(personId);

            editorMode = null;
            editorPersonId = null;
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            deleteMessage = e.getMessage();
        }
    }

    // /////////////////////////////////////////////////////////////////////
    // UPDATE
    // /////////////////////////////////////////////////////////////////////

    void onCancelUpdate(Long personId) {
        editorMode = Mode.REVIEW;
        editorPersonId = personId;
    }

    void onPrepareForRenderFromUpdateForm() {

        // If fresh start, make sure there's a Person object available.

        if (updateForm.isValid()) {
            editorMode = Mode.UPDATE;
            editorPerson = personRepository.findById(editorPersonId).orElse(null);
            // Handle null editorPerson in the template.
        }
    }

    void onPrepareForSubmitFromUpdateForm() {
        editorMode = Mode.UPDATE;

        // Get objects for the form fields to overlay.
        editorPerson = personRepository.findById(editorPersonId).orElse(null);

        if (editorPerson == null) {
            updateForm.recordError("Person has been deleted by another process.");
            // Instantiate an empty person to avoid NPE in the Form.
            editorPerson = new Person();
        }
    }

    void onValidateFromUpdateForm() {

        if (editorPerson.getFirstName() != null && editorPerson.getFirstName().equals("Acme")) {
            updateForm.recordError(firstNameField, firstNameField.getLabel() + " must not be Acme.");
        }

        if (editorPersonId == 2 && !editorPerson.getFirstName().equals("Mary")) {
            updateForm.recordError(firstNameField, firstNameField.getLabel() + " for this person must be Mary.");
        }

        if (updateForm.getHasErrors()) {
            return;
        }

        try {
            personRepository.save(editorPerson);
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            updateForm.recordError(e.getMessage());
        }
    }

    void onSuccessFromUpdateForm() {
        editorMode = Mode.REVIEW;
        editorPersonId = editorPerson.getId();
    }

    // /////////////////////////////////////////////////////////////////////
    // GETTERS ETC.
    // /////////////////////////////////////////////////////////////////////

    public String getLinkCSSClass() {
        if (listPerson != null && listPerson.getId().equals(editorPersonId)) {
            return "active";
        }
        else {
            return "";
        }
    }

    public boolean isModeCreate() {
        return editorMode == Mode.CREATE;
    }

    public boolean isModeReview() {
        return editorMode == Mode.REVIEW;
    }

    public boolean isModeUpdate() {
        return editorMode == Mode.UPDATE;
    }

    public String getEditorPersonRegion() {
        return messages.get(Regions.class.getSimpleName() + "." + editorPerson.getRegion().name());
    }

    public String getDatePattern() {
        return "dd/MM/yyyy";
    }

    public Format getDateFormat() {
        return new SimpleDateFormat(getDatePattern());
    }
}