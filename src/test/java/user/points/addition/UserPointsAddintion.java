package user.points.addition;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import user.aggregate.UserAggregate;
import user.aggregate.command.AddPoints;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by advena on 24.09.16.
 */
public class UserPointsAddintion {

    private UserAggregate userAggregate;
    private EventsAssert events;

    @Before
    public void setUp() {
        events = new EventsAssert();
        userAggregate = new UserAggregate(1L, events);
    }

    @Given("^User has \"([^\"]*)\" points at his account$")
    public void userHasPointsAtHisAccount(String p) throws Throwable {
        int points = Integer.valueOf(p);
        userAggregate.addPoints(new AddPoints(points));

    }

    @When("^User gets the \"([^\"]*)\" points added$")
    public void userGetsThePointsAdded(String p) throws Throwable {
        int points = Integer.valueOf(p);
        userAggregate.addPoints(new AddPoints(points));
    }

    @Then("^User has \"([^\"]*)\"$")
    public void userHas(String p) throws Throwable {
        int expectedPoints = Integer.valueOf(p);
        assertThat(events.getPoints()).isEqualTo(expectedPoints);
    }

}
