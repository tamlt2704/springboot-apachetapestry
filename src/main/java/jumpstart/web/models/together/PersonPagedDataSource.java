package jumpstart.web.models.together;

import java.util.ArrayList;
import java.util.List;

import jumpstart.entity.Person;
import jumpstart.repository.PersonRepository;
import jumpstart.util.query.SortCriterion;
import jumpstart.util.query.SortDirection;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;

public class PersonPagedDataSource implements GridDataSource {
    private PersonRepository personFinderService;

    private int startIndex;
    private List<Person> preparedResults;

    public PersonPagedDataSource(PersonRepository personFinderService) {
        this.personFinderService = personFinderService;
    }

    @Override
    public int getAvailableRows() {
        return (int) personFinderService.count();
    }

    @Override
    public void prepare(final int startIndex, final int endIndex, final List<SortConstraint> sortConstraints) {
        List<SortCriterion> sortCriteria = new ArrayList<SortCriterion>();
        sortCriteria.add(new SortCriterion("firstName", SortDirection.ASCENDING));
        sortCriteria.add(new SortCriterion("lastName", SortDirection.ASCENDING));
        // todo sql query and sort
        //preparedResults = personFinderService.findPersons(startIndex, endIndex - startIndex + 1, sortCriteria);
        preparedResults = personFinderService.findPersons(10);

        this.startIndex = startIndex;
    }

    @Override
    public Object getRowValue(final int index) {
        return preparedResults.get(index - startIndex);
    }

    @Override
    public Class<Person> getRowType() {
        return Person.class;
    }

}