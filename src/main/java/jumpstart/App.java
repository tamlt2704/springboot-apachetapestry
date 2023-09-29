package jumpstart;

import jumpstart.entity.Person;
import jumpstart.entity.Regions;
import jumpstart.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EnumType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@Slf4j
public class App implements CommandLineRunner {
    @Autowired
    PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initFakeUser();
    }

    private void initFakeUser() {
        Faker faker = new Faker();
        int nbFakeUser = 50;
        Regions[] regionValues = Regions.values();
        Random random = new Random();

        for (int i = 0; i < nbFakeUser; i++) {
            Person person = Person.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .startDate(faker.date().birthday())
                    .region(regionValues[random.nextInt(regionValues.length)])
                    .build();

            personRepository.save(person);
        }

        for (Person person : personRepository.findAll()) {
            log.info(person.toString());
        }
    }
}
