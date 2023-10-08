package jumpstart.web.pages.examples.state;

import jumpstart.web.pages.Index;

import jumpstart.web.state.examples.state.ShoppingBasket;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

@Import(stylesheet="css/examples/plain.css")
public class SharingAcrossMultiplePages1 {

    // Screen fields

    @SessionState
    @Property
    private ShoppingBasket myBasket;

    // The code

    public void setupRender() {
    }
    Object onSuccess() {
        return SharingAcrossMultiplePages2.class;
    }

    Object onGoHome() {
        // Delete the SSO from the session
        myBasket = null;
        return Index.class;
    }
}