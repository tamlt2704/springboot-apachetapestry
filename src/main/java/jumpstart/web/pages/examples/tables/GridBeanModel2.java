package jumpstart.web.pages.examples.tables;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beanmodel.BeanModel;
import org.apache.tapestry5.beanmodel.services.BeanModelSource;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet = "css/examples/plain.css")
public class GridBeanModel2 {

    // Activation context

    @Property
    private String firstName;

    // The code

    void onActivate(String firstName) {
        this.firstName = firstName;
    }

    String onPassivate() {
        return firstName;
    }
}