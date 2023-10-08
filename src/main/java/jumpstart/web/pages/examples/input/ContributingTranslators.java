package jumpstart.web.pages.examples.input;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

import java.math.BigDecimal;

@Import(stylesheet="css/examples/plain.css")
public class ContributingTranslators {
    // Screen fields

    @Property
    @Persist(PersistenceConstants.FLASH)
    private Boolean newToTapestry;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private BigDecimal price;
}
