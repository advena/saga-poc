package payment;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import payment.aggregate.PaymentAggregate;
import payment.command.GeneratePayments;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by advena on 25.09.16.
 */
public class PaymentGenerationSteps {
    private List<YearMonth> yearMonths;
    private EventsAssert events;
    private PaymentAggregate paymentsAggregate;

    @Before
    public void setUp() {
        events = new EventsAssert();
        paymentsAggregate = new PaymentAggregate(1L, events);
    }

    @Given("^list of \"([^\"]*)\"$")
    public void listOf(List<String> months) throws Throwable {
        yearMonths = months.stream().map(month -> YearMonth.parse(month)).collect(Collectors.toList());
    }

    @When("^payments are generated with \"([^\"]*)\" for (\\d+) game$")
    public void paymentsAreGeneratedWithForGame(String priceVal, long gameId) throws Throwable {
        BigDecimal price = BigDecimal.valueOf(Integer.valueOf(priceVal));
        paymentsAggregate.generatePayments(new GeneratePayments(gameId, yearMonths, price));

    }

    @Then("^should \"([^\"]*)\" of payments be generated$")
    public void shouldOfPaymentsBeGenerated(String number) throws Throwable {
        events.assertNumberOfPaymentsMatches(Integer.valueOf(number));
    }

    @Then("^each payment matches month when is generated$")
    public void eachPaymentMatchesMonthWhenIsGenerated() throws Throwable {
        events.assertPaymentsMatches(yearMonths);
    }

    @Then("^each payment matches \"([^\"]*)\"$")
    public void eachPaymentMatches(String price) throws Throwable {
        events.assertPaymentPriceMatches(BigDecimal.valueOf(Integer.valueOf(price)));
    }

}
