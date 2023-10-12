package jumpstart.web.components;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.http.services.Request;
import org.apache.tapestry5.http.services.Session;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * This component is identical to the portion of Tapestry's default ExceptionReport page that handles
 * productionMode=false.
 */

public class ExceptionAndSessionDisplay {

    @Parameter(required = true)
    @Property
    private Throwable exception;

    @Inject
    @Property
    private Request request;

    @Property
    private String attributeName;

    public boolean getHasSession() {
        return request.getSession(false) != null;
    }

    public Session getSession() {
        return request.getSession(false);
    }

    public Object getAttributeValue() {
        return getSession().getAttribute(attributeName);
    }

}