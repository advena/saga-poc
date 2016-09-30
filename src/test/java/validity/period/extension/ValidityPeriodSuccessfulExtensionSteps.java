package validity.period.extension;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import game.aggregate.GameAggregate;
import game.data.GameDataDto;
import game.query.model.GameQueryModel;
import game.repository.GameRepository;
import payment.aggregate.PaymentAggregate;
import payment.data.PaymementsDataDto;
import payment.query.model.PaymentQueryModel;
import saga.ValidityExtensionSaga;
import saga.command.ValidityPeriodExtensionCommand;
import user.aggregate.UserAggregate;
import user.aggregate.command.AddPoints;
import user.data.UserDataDto;
import user.query.model.UserQueryModel;
import user.repository.UserRepository;
import validity.period.ValidityPeriod;
import validity.period.aggregate.ValidityPeriodAggregate;
import validity.period.repository.ValidityPeriodRepository;

import java.math.BigDecimal;
import java.time.YearMonth;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by advena on 23.09.16.
 */

public class ValidityPeriodSuccessfulExtensionSteps {

    private UserAggregate userAggregate;
    private GameAggregate game;
    private ValidityPeriod gameValidityPeriod;
    private ValidityPeriod extendedValidityPeriod;
    private ValidityPeriodExtensionCommand extensionCommand;
    private ValidityExtensionSaga validityExtensionSaga;
    private UserRepository userRepository;
    private GameRepository gameRepository;
    private ValidityPeriodRepository validityPeriodRepository;
    private PaymentQueryModel paymentQueryModel;
    private UserQueryModel userQueryModel;
    private GameQueryModel gameQueryModel;
    private user.points.addition.EventsAssert userEventsHandler;
    private validity.period.change.EventsAssert validityPeriodEventsHandler;
    private ValidityPeriodAggregate validityPeriodAggregate;
    private payment.EventsAssert paymentsEvenetHandler;
    private PaymentAggregate paymentAggregate;

    @Before
    public void setUp() {
        userEventsHandler = new user.points.addition.EventsAssert();
        userAggregate = new UserAggregate(1L, userEventsHandler);
        validityPeriodEventsHandler = new validity.period.change.EventsAssert();
        paymentsEvenetHandler = new payment.EventsAssert();
        paymentAggregate = new PaymentAggregate(1L, paymentsEvenetHandler);
    }

    @Given("^(\\d+) user has initially (\\d+) points$")
    public void userInitiallyHasPoints(long userId, int points) throws Throwable {
        userAggregate.addPoints(new AddPoints(points));
    }

    @And("^User owns (\\d+) Game$")
    public void userOwnsGame(long gameId) throws Throwable {
        userAggregate.addGame(gameId);
    }

    @And("^(\\d+) Game cost for one month is (\\d+)$")
    public void gameCostForOneMonthIs(long gameId, int price) throws Throwable {
        game = new GameAggregate(gameId, new BigDecimal(price));
        gameRepository.save(game);
    }


    @And("^(\\d+) Game is available between \"([^\"]*)\" and \"([^\"]*)\"$")
    public void gameIsAvaliableBeetwenAnd(long gameId, String startMonth, String endMonth) throws Throwable {
        YearMonth start = YearMonth.parse(startMonth);
        YearMonth end = YearMonth.parse(endMonth);
        gameValidityPeriod = new ValidityPeriod(start, end);
        validityPeriodAggregate = new ValidityPeriodAggregate(gameId, gameValidityPeriod, validityPeriodEventsHandler);
    }

    @When("^(\\d+) User extends Validity Period for (\\d+) Game up to \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userExtendsValidityPeriodForGameUpToAnd(long userId, long gameId, String extnededStart, String extendedEnd) throws Throwable {
        validityExtensionSaga = ValidityExtensionSaga.generateSaga(userAggregate, validityPeriodAggregate, paymentAggregate);
        YearMonth start = YearMonth.parse(extnededStart);
        YearMonth end = YearMonth.parse(extendedEnd);
        extendedValidityPeriod = new ValidityPeriod(start, end);
        extensionCommand = new ValidityPeriodExtensionCommand(extendedValidityPeriod, userId, gameId);
        validityExtensionSaga.trigger(extensionCommand);
    }

    @Then("^Validity Period for (\\d+) Game stands for \"([^\"]*)\" and \"([^\"]*)\"$")
    public void validityPeriodForGameStandsForAnd(long gameId, String gameStart, String gameEnd) throws Throwable {
        YearMonth start = YearMonth.parse(gameStart);
        YearMonth end = YearMonth.parse(gameEnd);
        GameDataDto data = gameQueryModel.getDataFor(gameId);
        assertThat(data.getStartDate()).isEqualTo(start);
        assertThat(data.getEndDate()).isEqualTo(end);
    }

    @Then("^(\\d+) user has (\\d+) points$")
    public void userHasPoints(long userId, int points) throws Throwable {
        UserDataDto data = userQueryModel.getDataFor(userId);
        assertThat(data.getPoints()).isEqualTo(points);
    }

    @Then("^(\\d+) user has (\\d+) payments that are generated with price (\\d+) each$")
    public void paymentsAreGeneratedWithPriceEach(long userId, int paymentsNumber, int price) throws Throwable {
        PaymementsDataDto data = paymentQueryModel.getDataFor(userId);
        assertThat(data.getPayments().size()).isEqualTo(paymentsNumber);
        assertThat(data.getPayments().get(0)).isEqualTo(price);
        assertThat(data.getPayments().get(1)).isEqualTo(price);

    }
}
