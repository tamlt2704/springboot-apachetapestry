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

@Import(stylesheet = "css/examples/plain.css")
public class GridBeanModel1 {
    public static final int MAX_RESULTS = 30;

    @Property
    private List<Person> persons;

    @Property
    private Person person;

    @Property
    private BeanModel<Person> myModel;

    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private Messages messages;

    @Inject
    private PersonRepository personRepository;

    void setupRender() {
        myModel = beanModelSource.createDisplayModel(Person.class, messages);
        myModel.add("action", null);
        myModel.include("id", "firstName", "lastName", "startDate", "action");
        myModel.get("firstName").sortable(false);
        myModel.get("lastName").label("Surname");

        persons = personRepository.findPersons(MAX_RESULTS);
    }
}
