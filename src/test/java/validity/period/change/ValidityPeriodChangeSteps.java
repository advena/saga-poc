package validity.period.change;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import validity.period.ValidityPeriod;
import validity.period.aggregate.ValidityPeriodAggregate;
import validity.period.command.ValidityPeriodChange;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by advena on 25.09.16.
 */
public class ValidityPeriodChangeSteps {

    private EventsAssert events;
    private ValidityPeriodAggregate validityPeriodAggregate;

    @Before
    public void setUp() {
        events = new EventsAssert();
    }
    @Given("^Validity Period begins \"([^\"]*)\" and ends \"([^\"]*)\"$")
    public void validityPeriodBeginsAndEnds(String start, String end) throws Throwable {
        YearMonth startMonth = YearMonth.parse(start);
        YearMonth endMonth = YearMonth.parse(end);
        validityPeriodAggregate = new ValidityPeriodAggregate(1, new ValidityPeriod(startMonth, endMonth), events);
    }

    @When("^Validity Period start is changed to \"([^\"]*)\" and \"([^\"]*)\"$")
    public void validityPeriodStartIsChangedToAnd(String newStart, String newEnd) throws Throwable {
        YearMonth newStartMonth = YearMonth.parse(newStart);
        YearMonth newEndMonth = YearMonth.parse(newEnd);
        validityPeriodAggregate.changePeriod(new ValidityPeriodChange(newStartMonth, newEndMonth));
    }

    @Then("^Validity Period should be available in \"([^\"]*)\"$")
    public void validityPeriodShouldBeAvailableIn(List<String> months) throws Throwable {
        List<YearMonth> expectedMonths = new ArrayList<>();
        months.stream().forEach(month -> expectedMonths.add(YearMonth.parse(month)));
        assertThat(events.getMonths()).isEqualTo(expectedMonths);
    }
}
