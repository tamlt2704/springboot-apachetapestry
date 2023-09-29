package jumpstart.web.pages.output;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

@Import(stylesheet = "css/examples/olive.css")
public class Annotations {
    @Property
    private String message;

    @Inject
    private Logger logger;

    @OnEvent(value = EventConstants.ACTIVATE)
    void thisPageHasBeenRequested() {
        message = "thisPageHasBeenRequested() called...";
        logger.info("thisPageHasBeenRequested() called...");
    }

    @SetupRender
    void beforeAnyRendering() {
        message += "beforeAnyRendering() called...";
        logger.info("beforeAnyRendering() called...");
    }


    @CleanupRender
    void tidyUp() {
        message += "tidyUp() called...";
        logger.info("tidyUp() called...");
    }
}
