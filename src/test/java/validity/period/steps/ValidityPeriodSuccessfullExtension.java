package validity.period.steps;

import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.api.CucumberOptions;
import game.aggregate.GameAggregate;
import game.data.GameDataDto;
import game.query.model.GameQueryModel;
import game.repository.GameRepository;
import payment.data.PaymementsDataDto;
import payment.query.model.PaymentQueryModel;
import user.aggregate.UserAggregate;
import user.data.UserDataDto;
import user.query.model.UserQueryModel;
import user.repository.UserRepository;
import validity.period.ValidityPeriod;
import validity.period.aggregate.ValidityPeriodAggregate;
import validity.period.command.ValidityPeriodExtensionCommand;
import validity.period.repository.ValidityPeriodRepository;
import saga.ValidityExtensionSaga;

import java.math.BigDecimal;
import java.time.YearMonth;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by advena on 23.09.16.
 */

@CucumberOptions(glue = {"test.java.validity.period"}, features = {
        "classpath:test/features/validity-period-change.feature"})
public class ValidityPeriodSuccessfullExtension {

    private UserAggregate user;
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

    @Given("^(\\d+) user has initially (\\d+) points$")
    public void userInitiallyHasPoints(long userId, int points) throws Throwable {
        user = new UserAggregate(userId);
        user.addPoints(points);
    }

    @And("^User owns (\\d+) Game$")
    public void userOwnsGame(long gameId) throws Throwable {
        user.addGame(gameId);
        userRepository.save(user);
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
        validityPeriodRepository.save(new ValidityPeriodAggregate(gameId, gameValidityPeriod));
    }

    @When("^(\\d+) User extends Validity Period for (\\d+) Game up to \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userExtendsValidityPeriodForGameUpToAnd(long userId, long gameId, String extnededStart, String extendedEnd) throws Throwable {
        validityExtensionSaga = ValidityExtensionSaga.generateSaga();
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
