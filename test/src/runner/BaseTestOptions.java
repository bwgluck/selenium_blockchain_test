package runner;

import cucumber.api.CucumberOptions;

@CucumberOptions(
	monochrome = true,
	features = { "classpath:./" },
	glue = { "steps" })
public abstract class BaseTestOptions {

}