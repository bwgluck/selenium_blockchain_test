package containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverFactory;

/**
 * Defines container WebElements as an example
 */
public class TOHContainer {

	public TOHContainer() {
		PageFactory.initElements(WebDriverFactory.getDriver(), this);
	}
	
	// app.component.html elements
	@FindBy(how = How.CSS, using = "#page-title-header")
	public WebElement TOHPageTitleHeader;
	
	@FindBy(how = How.CSS, using = "#page-nav")
	public WebElement TOHNav;
	
	@FindBy(how = How.CSS, using = "#nav-dashboard-btn")
	public WebElement TOHNavDashboardBtn;
	
	@FindBy(how = How.CSS, using = "#nav-heroes-btn")
	public WebElement TOHNavHeroesBtn;
	
	// dashboard component elements
	@FindBy(how = How.CSS, using = "#top-heroes-header")
	public WebElement TopHeroesHeader;
	
	@FindBy(how = How.CSS, using = ".grid-pad")
	public WebElement GridPad;
	
	// hero details component elements
	@FindBy(how = How.CSS, using = "#hero-detail-component")
	public WebElement HeroDetailComponent;
	
	@FindBy(how = How.CSS, using = "#edit-name-text-input")
	public WebElement EditHeroNameTextInput;
	
	@FindBy(how = How.CSS, using = "#details-back-btn")
	public WebElement DetailsBackBtn;
	
	@FindBy(how = How.CSS, using = "#details-save-btn")
	public WebElement DetailsSaveBtn;
	
	// hero search component elements
	@FindBy(how = How.CSS, using = "#search-component")
	public WebElement SearchComponent;
	
	@FindBy(how = How.CSS, using = "#hero-search-header")
	public WebElement HeroSearchHeader;
	
	@FindBy(how = How.CSS, using = "#search-box")
	public WebElement SearchBox;
	
	@FindBy(how = How.CSS, using = ".search-result")
	public WebElement SearchResult;
	
	// my heroes component elements
	@FindBy(how = How.CSS, using = "#my-heroes-header")
	public WebElement MyHeroesHeader;
	
	@FindBy(how = How.CSS, using = "#add-hero-div")
	public WebElement AddHeroDiv;
	
	@FindBy(how = How.CSS, using = "#add-hero-label")
	public WebElement AddHeroLabel;
	
	@FindBy(how = How.CSS, using = "#add-hero-input")
	public WebElement AddHeroInput;
	
	@FindBy(how = How.CSS, using = "#add-hero-add-btn")
	public WebElement AddHeroAddBtn;
	
	@FindBy(how = How.CSS, using = "#my-heroes-list")
	public WebElement MyHeroesList;
	
	// message history component elements
	@FindBy(how = How.CSS, using = "#messages-div")
	public WebElement MessagesDiv;
	
	@FindBy(how = How.CSS, using = "#messages-header")
	public WebElement MessagesHeader;
	
	@FindBy(how = How.CSS, using = "#clear-messages-btn")
	public WebElement ClearMessagesBtn;
}
