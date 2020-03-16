package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	format = { "pretty",
		"json:target/reports/json/BGDebugRunner.json",
		"html:target/reports/html/BGDebugRunner" },
	tags = { "@bgdebug" })
public class BGDebugRunner extends BaseTestOptions {

}
