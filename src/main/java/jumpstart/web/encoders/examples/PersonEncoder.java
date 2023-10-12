package jumpstart.web.encoders.examples;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import org.apache.tapestry5.ValueEncoder;

public class PersonEncoder implements ValueEncoder<Person> {

    private PersonRepository personRepository;

    public PersonEncoder(PersonRepository personFinderService) {
        this.personRepository = personFinderService;
    }

    @Override
    public String toClient(Person value) {
        return String.valueOf(value.getId());
    }

    @Override
    public Person toValue(String id) {
        return personRepository.findById(Long.parseLong(id)).orElse(null);
    }
}