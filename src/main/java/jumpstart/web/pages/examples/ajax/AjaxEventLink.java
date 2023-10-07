package jumpstart.web.pages.examples.ajax;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.http.services.Request;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import java.util.Date;

@Import(stylesheet = "css/examples/js.css")
public class AjaxEventLink {
    @Inject
    private Request request;

    @InjectComponent
    private Zone time2Zone;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    void onRefreshPage() {
        // nothing to do - as the page renders the template, it will call get serverTime1() and serverTime2()
    }

    void onRefreshZone() {
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(time2Zone);
        }
    }

    public Date getServerTime1() {
        return new Date();
    }
    public Date getServerTime2() {
        return new Date();
    }
}

