package jumpstart.web.pages.examples.input;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet="css/examples/olive.css")
public class EventLinks {

    @Property
    private int count;

    void onActivate(int count) {
        this.count = count;
    }

    int onPassivate() {
        return count;
    }

    void onAdd(int amount) {
        count += amount;
    }

    void onClear() {
        count = 0;
    }
}
