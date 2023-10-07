package jumpstart.web.pages.examples.input;


import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet="css/examples/olive.css")
public class ActionLinks {

    // Screen fields

    @Property
    private int count;

    // The code

    // onActivate() is called by Tapestry to pass in the activation context from the URL.

    void onActivate(int count) {
        this.count = count;
    }

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.

    int onPassivate() {
        return count;
    }

    void onActionFromAddX(int amount) {
        count += amount;
    }

    void onActionFromAddY(int amount) {
        count += amount;
    }

    void onActionFromClear() {
        count = 0;
    }

}