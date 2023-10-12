package jumpstart.repository;

import jumpstart.entity.Person;
import jumpstart.util.query.SortCriterion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query(nativeQuery = true,
            value = "SELECT TOP :maxResults * FROM Person")
    List<Person> findPersons(@Param("maxResults") int maxResults);

    //TODO[]List<Person> findPersons(int startIndex, int total, List<SortCriterion> sortCriteria);
}
