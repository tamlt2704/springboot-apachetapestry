package jumpstart.web.pages;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

@Import(stylesheet = "css/examples/olive.css")
public class Methods {
    private String message;

    @Inject
    private Logger logger;

    void onActive() {
        message = "onActive() called...";
        logger.info("onActive() called...");
    }

    void setupRender() {
        message += "setupRender() called...";
        logger.info("setupRender() called...");
    }

    public String getMessage() {
        message += "getMessage() called...";
        logger.info("getMessage() called...");
        return message;
    }

    void cleanupRender() {
        message += "cleanupRender() called...";
        logger.info("cleanupRender() called...");
    }
}
