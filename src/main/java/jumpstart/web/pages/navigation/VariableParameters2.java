package jumpstart.web.pages.navigation;

import jumpstart.entity.Mode;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet = "css/examples/olive.css")
public class VariableParameters2 {
    @Property
    private Long personId;

    @Property
    private Mode mode;

    @Property
    private String message;

    void onActivate(EventContext eventContext) {
        int parameterCount = eventContext.getCount();
        if (parameterCount == 1) {
            mode = eventContext.get(Mode.class, 0);
        } else if (parameterCount == 2) {
            mode = eventContext.get(Mode.class, 0);
            personId = eventContext.get(Long.class, 1);
        } else {
            message = "Wrong number of parameters received. Expected 1 or 2, found " + parameterCount + ".";
        }
    }
}
