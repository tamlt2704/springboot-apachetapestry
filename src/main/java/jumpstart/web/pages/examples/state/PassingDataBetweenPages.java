package jumpstart.web.pages.examples.state;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.http.Link;

@Import(stylesheet="css/examples/plain.css")
public class PassingDataBetweenPages {

    @InjectPage
    private PassingByActivationContext byActivationContextPage;

    @InjectPage
    private PassingByQueryString byQueryStringPage;

    @InjectPage
    private PassingByPersist byPersistPage;

    Object onUseActivationContext() {
        byActivationContextPage.set(1L);
        return byActivationContextPage;
    }

    Object onUseQueryString() {
        Link link = byQueryStringPage.set("humpty");
        return link;
    }

    Object onUsePersist() {
        byPersistPage.set("Humpty", "Dumpty");
        return byPersistPage;
    }
}