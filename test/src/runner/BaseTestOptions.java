package runner;

import cucumber.api.CucumberOptions;

@CucumberOptions(
	monochrome = true,
	features = { "classpath:./" },
	glue = { "com.ibm.security.ux.bdd.steps" })
public abstract class BaseTestOptions {

}