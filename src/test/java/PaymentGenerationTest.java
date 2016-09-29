import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by advena on 29.09.16.
 */
@RunWith(Cucumber.class)
@CucumberOptions(  monochrome = true,
        features = "src/test/features/payment-generation.feature",
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false,
        glue = "payment" )
public class PaymentGenerationTest {
}
