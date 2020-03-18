package steps;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.Robot;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
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
	private TOHContainer tohContainer = new TOHContainer();
	
	@Given("^I navigate to the URL \"(.*?)\"$")
	public void navigate_to_URL(String url) throws Throwable {
		driver.get(url);
		Thread.sleep(5000);
	}
	
	@Then("^I close the browser")
	public void close_the_browser() throws Throwable {
		Thread.sleep(3000);
		WebDriverFactory.clearDriver();
	}
	
	@Then("^I see the TOH page title header")
	public void I_see_page_title_header() throws Throwable {
		TestUtils.assertElementAppears(tohContainer.TOHPageTitleHeader);
	}
	
	@Then("^I see the TOH page navigation")
	public void I_see_page_nav() throws Throwable {
		TestUtils.assertElementAppears(tohContainer.TOHNav);
		TestUtils.assertElementAppears(tohContainer.TOHNavDashboardBtn);
		TestUtils.assertElementAppears(tohContainer.TOHNavHeroesBtn);
	}
	
	@Then("^I see the top heroes section")
	public void I_see_the_top_heroes_section() throws Throwable {
		TestUtils.assertElementAppears(tohContainer.TopHeroesHeader);
		TestUtils.assertElementAppears(tohContainer.GridPad);
	}
	
	@Then("^I see the hero search section")
	public void I_see_the_hero_search_section() throws Throwable {
		TestUtils.assertElementAppears(tohContainer.SearchComponent);
		TestUtils.assertElementAppears(tohContainer.HeroSearchHeader);
		TestUtils.assertElementAppears(tohContainer.SearchBox);
	}
	
	@Then("^I see the message history section")
	public void I_see_the_message_history_section() throws Throwable {
		TestUtils.assertElementAppears(tohContainer.MessagesDiv);
		TestUtils.assertElementAppears(tohContainer.MessagesHeader);
		TestUtils.assertElementAppears(tohContainer.ClearMessagesBtn);
	}
	
	@Then("^I click and view the details for top hero \"(.*?)\"$")
	public void I_click_and_view_top_hero_details(String hero) throws Throwable {		
		TestUtils.cssElementAppearsWithin("#" + hero + "-grid-tile", TestUtils.getMaxTimeOutValue());
		driver.findElement(By.cssSelector("#" + hero + "-grid-tile")).click();
		TestUtils.cssElementAppearsWithin("#" + hero + "-details-header", TestUtils.getMaxTimeOutValue());
		TestUtils.assertElementAppears(tohContainer.HeroDetailComponent);
		TestUtils.assertElementAppears(tohContainer.DetailsBackBtn);
		TestUtils.assertElementAppears(tohContainer.DetailsSaveBtn);
		Thread.sleep(3000);
	}
	
	@Then("^I go back to the dashboard from hero details page")
	public void I_go_back_to_dashboard_from_details() throws Throwable {
		TestUtils.assertElementAppears(tohContainer.DetailsBackBtn);
		tohContainer.DetailsBackBtn.click();
	}
	
	@Then("^I edit hero name and save as \"(.*?)\"$")
	public void I_edit_hero_name(String newHeroName) throws Throwable {		
		TestUtils.assertElementAppears(tohContainer.EditHeroNameTextInput);
		TestUtils.verifiedSendKeys(tohContainer.EditHeroNameTextInput, newHeroName);
		Thread.sleep(1000);
		TestUtils.assertElementAppears(tohContainer.DetailsSaveBtn);
		tohContainer.DetailsSaveBtn.click();
	}
	
	@Then("^I see this hero name in top heroes: \"(.*?)\"$")
	public void I_see_hero_name_in_top_heroes(String hero) throws Throwable {		
		TestUtils.cssElementAppearsWithin("#" + hero + "-grid-tile", TestUtils.getMaxTimeOutValue());
	}
	
	@Then("^I search for hero name \"(.*?)\"$")
	public void I_search_hero_name(String heroName) throws Throwable {		
		TestUtils.assertElementAppears(tohContainer.SearchBox);
		TestUtils.verifiedSendKeys(tohContainer.SearchBox, heroName);
		Thread.sleep(1000);
	}
	
	@Then("^I search for hero name \"(.*?)\" and click on the result$")
	public void I_search_hero_name_and_click(String heroName) throws Throwable {		
		TestUtils.assertElementAppears(tohContainer.SearchBox);
		TestUtils.verifiedSendKeys(tohContainer.SearchBox, heroName);
		Thread.sleep(1000);
		TestUtils.assertElementAppears(tohContainer.SearchResult);
		TestUtils.cssElementAppearsWithin("#" + heroName + "-search-link", TestUtils.getMaxTimeOutValue());
		driver.findElement(By.cssSelector("#" + heroName + "-search-link")).click();
	}
	
	@Then("^I view the details for \"(.*?)\"$")
	public void I_view_details(String hero) throws Throwable {		
		TestUtils.cssElementAppearsWithin("#" + hero + "-details-header", TestUtils.getMaxTimeOutValue());
		TestUtils.assertElementAppears(tohContainer.HeroDetailComponent);
		TestUtils.assertElementAppears(tohContainer.DetailsBackBtn);
		TestUtils.assertElementAppears(tohContainer.DetailsSaveBtn);
	}
	
	@Then("^I click on the my heroes navigation button")
	public void I_click_my_heroes_nav_btn() throws Throwable {
		TestUtils.assertElementAppears(tohContainer.TOHNavHeroesBtn);
		tohContainer.TOHNavHeroesBtn.click();
		Thread.sleep(1000);
	}
	
	@Then("^I see my heroes elements")
	public void I_see_my_heroes_elements() throws Throwable {
		TestUtils.assertElementAppears(tohContainer.MyHeroesHeader);
		TestUtils.assertElementAppears(tohContainer.AddHeroDiv);
		TestUtils.assertElementAppears(tohContainer.AddHeroLabel);
		TestUtils.assertElementAppears(tohContainer.AddHeroInput);
		TestUtils.assertElementAppears(tohContainer.AddHeroAddBtn);
		TestUtils.assertElementAppears(tohContainer.MyHeroesList);
	}
	
	@Then("^I add hero \"(.*?)\" to the heroes list$")
	public void I_add_hero(String heroName) throws Throwable {		
		TestUtils.assertElementAppears(tohContainer.AddHeroInput);
		TestUtils.verifiedSendKeys(tohContainer.AddHeroInput, heroName);
		Thread.sleep(1000);
		TestUtils.assertElementAppears(tohContainer.AddHeroAddBtn);
		tohContainer.AddHeroAddBtn.click();
		Thread.sleep(1000);
	}
	
	@Then("^I see \"(.*?)\" in the heroes list and click the entry$")
	public void I_see_hero_in_list_and_click(String heroName) throws Throwable {		
		TestUtils.cssElementAppearsWithin("#" + heroName + "-hero-list-item", TestUtils.getMaxTimeOutValue());
		TestUtils.cssElementAppearsWithin("#" + heroName + "-hero-link", TestUtils.getMaxTimeOutValue());
		driver.findElement(By.cssSelector("#" + heroName + "-hero-link")).click();
		Thread.sleep(1000);
	}
	
	@Then("^I go back to my heroes from hero details page")
	public void I_go_back_to_my_heroes_from_details() throws Throwable {
		TestUtils.assertElementAppears(tohContainer.DetailsBackBtn);
		tohContainer.DetailsBackBtn.click();
		Thread.sleep(1000);
	}
	
	@Then("^I delete \"(.*?)\" from the heroes list$")
	public void I_delete_hero(String heroName) throws Throwable {		
		TestUtils.cssElementAppearsWithin("#" + heroName + "-delete-btn", TestUtils.getMaxTimeOutValue());
		driver.findElement(By.cssSelector("#" + heroName + "-delete-btn")).click();
		Thread.sleep(1000);
	}
	
	@Then("^I do not see \"(.*?)\" in the heroes list$")
	public void I_do_not_see_hero(String heroName) throws Throwable {		
		TestUtils.cssassertElementNotAppearsWithin("#" + heroName + "-hero-list-item", TestUtils.getMaxTimeOutValue());
	}
	
	@Then("^I see message entry \"(.*?)\" with text \"(.*?)\"$")
	public void I_see_message_entry(String num,String message) throws Throwable {		
		TestUtils.cssTextAppearsWithin(message, "#message-entry-" + num,
				TestUtils.getMaxTimeOutValue());
	}
	
	@Then("^I clear the message history and verify it has been erased")
	public void clear_message_history() throws Throwable {
		TestUtils.assertElementAppears(tohContainer.ClearMessagesBtn);
		tohContainer.ClearMessagesBtn.click();
		Thread.sleep(1000);
		TestUtils.cssassertElementNotAppearsWithin("messages-div", TestUtils.getMaxTimeOutValue());
	}
}
