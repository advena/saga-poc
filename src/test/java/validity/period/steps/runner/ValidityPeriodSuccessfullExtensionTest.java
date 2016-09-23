package validity.period.steps.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 * Created by advena on 24.09.16.
 */
@RunWith(Cucumber.class)

@CucumberOptions(  monochrome = true,
        tags = "@tags",
        features = "src/test/features/",
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        dryRun = false,
        glue = "validity.period" )
public class ValidityPeriodSuccessfullExtensionTest {
}
