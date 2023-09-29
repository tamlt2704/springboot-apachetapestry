package jumpstart.web.pages.navigation;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.HashMap;
import java.util.Map;

@Import(stylesheet = "css/examples/olive.css")
public class ActivationRequestParameters1 {
    @Property
    private String partialName;
    @Inject
    private PersonRepository personRepository;

    void setupRender() {
        Person person = personRepository.findById(1L).orElse(null);
        partialName = person.getFirstName();
    }

    public Map<String, Object> getQueryParams() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("partialName", partialName);
        return queryParams;
    }
}
