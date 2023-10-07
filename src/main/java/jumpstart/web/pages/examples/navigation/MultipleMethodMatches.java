package jumpstart.web.pages.examples.navigation;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet="css/examples/plain.css")
public class MultipleMethodMatches {
    @Property
    @Persist(PersistenceConstants.FLASH)
    private String message;

    void onActivate() {
        if (message == null) {
            message = "The event handlers invoked were...";
        }
    }

    void onDoSomething() {
        message += " onDoSomething()";
    }

    Object onDoSomething(String parameter) {
        message += " onDoSomething(parameter)";
        // To prevent onDoSomething() also being called, we'd return true or any other valid non-null value.
        return null;
    }
}
