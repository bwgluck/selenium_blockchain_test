package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	tags = { "@bgdebug" })
public class BGDebugRunner extends BaseTestOptions {

}
