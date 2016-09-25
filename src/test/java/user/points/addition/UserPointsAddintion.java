package validity.period.steps.user.points.addition;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by advena on 24.09.16.
 */
public class UserPointsAddintion {
    @Given("^User has \"([^\"]*)\" points at his account$")
    public void userHasPointsAtHisAccount(String p) throws Throwable {
        int points = Integer.valueOf(p);
    }

    @When("^User gets the \"([^\"]*)\" points added$")
    public void userGetsThePointsAdded(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^User has \"([^\"]*)\"$")
    public void userHas(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
