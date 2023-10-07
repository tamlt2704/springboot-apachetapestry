package jumpstart.web.mixins;


import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class ClientTranslatorDisabler {

    // Generally useful bits and pieces

    @InjectContainer
    private ClientElement attachedTo;

    @Inject
    private JavaScriptSupport javaScriptSupport;

    // The code

    public void afterRender() {
        javaScriptSupport.require("client-translator-disabler").with(attachedTo.getClientId());
    }

}