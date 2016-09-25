package validity.period.change;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

/**
 * Created by advena on 25.09.16.
 */
public class ValidityPeriodChangeSteps {
    @Given("^Validity Period begins \"([^\"]*)\" and ends \"([^\"]*)\"$")
    public void validityPeriodBeginsAndEnds(String startMonth, String endMonth) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Validity Period start is changed to \"([^\"]*)\" and \"([^\"]*)\"$")
    public void validityPeriodStartIsChangedToAnd(String newStartMonth, String newEndMonth) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Validity Period should be available in \"([^\"]*)\"$")
    public void validityPeriodShouldBeAvailableIn(List<String> months) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
