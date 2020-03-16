package steps;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import containers.TOHContainer;
import utils.TestUtils;
import utils.WebDriverFactory;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TOHSteps {
	private WebDriver driver = WebDriverFactory.getDriver();
	
	@Given("^I navigate to the URL \"(.*?)\"$")
	public void navigate_to_URL(String url) throws Throwable {
		driver.get(url);
		Thread.sleep(10000);
	}
}
