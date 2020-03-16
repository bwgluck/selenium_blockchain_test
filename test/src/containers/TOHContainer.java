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
	
	@FindBy(how = How.CSS, using = "#cs-configuration-header")
	public WebElement SettingsHeader;
}
