import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by advena on 25.09.16.
 */
@RunWith(Cucumber.class)
@CucumberOptions(  monochrome = true,
        features = "src/test/features/",
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false,
        glue = "validity.period.change" )
public class ValidityPeriodChangeTest {

}