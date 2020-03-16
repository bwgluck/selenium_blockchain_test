package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestUtils {

	public static final String PATH_SEPERATOR = "/";
	public static final String EASTERN_TIMEZONE = "US/Eastern";

	public static final int ONE_MINUTE_IN_SEC = 60;
	public static final int ONE_SECOND_IN_MS = 1000;
	public static final int HALF_SECOND_IN_MS = ONE_SECOND_IN_MS / 2;
	public static final int TYPE_AHEAD_SEARCH_DELAY_IN_MS = ONE_SECOND_IN_MS;  // TYPE_AHEAD_SEARCH_DELAY For Rule Filter Search tests, in milliseconds
	
	private static final int MAX_UI_TIMEOUT_IN_SEC = 20;
	
	//Dashboard UI # of successful logins
	private static int NUMBER_OF_SUCCESSFUL_LOGINS;

    public static int getSuccessfulLogins(){
        return NUMBER_OF_SUCCESSFUL_LOGINS;
    }
    public static void setSuccessfulLogins(int updatedSucessfulLogins){
    	NUMBER_OF_SUCCESSFUL_LOGINS = updatedSucessfulLogins;
    }
    //Dashboard UI # of successful logins
    
    //Dashboard UI # of failed logins
  	private static int NUMBER_OF_FAILED_LOGINS;

      public static int getFailedLogins(){
          return NUMBER_OF_FAILED_LOGINS;
      }
      public static void setFailedLogins(int updatedFailedLogins){
    	  NUMBER_OF_FAILED_LOGINS = updatedFailedLogins;
      }
      //Dashboard UI # of failed logins
      
      
	/**
	 * Gets the current active @WebDriver. Use this method to get the active driver from
	 * within this class.
	 * 
	 * @return The current active @WebDriver for the test case.
	 */
	private static WebDriver getDriver() {
		return WebDriverFactory.getDriver();
	}
	
	/**
	 * Get the current running mode of these tests. Will be true on 
	 * the develop environment, and false in Jenkins.
	 * @return True if in debug mode, false otherwise.
	 */
	public static boolean debugMode () {
		String debugProp = System.getProperty("debug");

		boolean debug = true;
		if (debugProp != null && debugProp.equals("false")) {
			debug = false;
		}
		return debug;
	}
	
	/**
	 * Get the maximum time we want to wait before we consider the load a performance
	 * failure.
	 * 
	 * @Return The maximum time out value for the UI.
	 */
	public static int getMaxTimeOutValue() {
		return MAX_UI_TIMEOUT_IN_SEC;
	}
	
	/**
	 * Returns the status if the element is present on the page.
	 * 
	 * @param by
	 *            The element to look for on the page.
	 * @return true if the element is present on the page, and false otherwise.
	 */
	public static boolean isElementPresent(By by) {
		try {
			getDriver().findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Assert the element has appeared on the page using the default maximum time for the UI from
	 * {@link #getMaxTimeOutValue() getMaxTimeOutValue}
	 * 
	 * @param element
	 *            The element to check for represented as a @WebElement
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	public static void assertElementAppears (WebElement element) throws Throwable {
		assertElementAppears (element, getMaxTimeOutValue());
	}
	
	/**
	 * Assert the element has appeared on the page within the specified wait time.
	 * 
	 * @param element
	 *            The element to check for represented as a @WebElement
	 * @param timeToWaitInSeconds
	 *            The time to wait for the element to appear in seconds.
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	public static void assertElementAppears(WebElement element, int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				assertTrue(element.isDisplayed());
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds) {
					fail("A timeout occured after " + timeToWaitInSeconds + " second(s): " + error.getMessage());
				} 
			} catch (Exception exception) {
				if (second >= timeToWaitInSeconds) {
					fail("A timeout occured after " + timeToWaitInSeconds + " second(s): " + exception.getMessage());
				}
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}	 
	
	/**
	 * Assert the expected text has been found in the element using the 
	 * default maximum time for the UI from @link #getMaxTimeOutValue() getMaxTimeOutValue}
	 * 
	 * @param webElement
	 *            The @WebElement to check.
	 * @param expectedText
	 *            The expected text to check for.
	 *            
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	public static void assertTextAppears(WebElement webElement,
			String expectedText) throws Throwable {
		assertTextAppears(webElement, expectedText, getMaxTimeOutValue());
	}
	
	/**
	 * Assert the expected text has been found in the element.
	 * 
	 * @param webElement
	 *            The @WebElement to check.
	 * @param expectedText
	 *            The expected text to check for.
	 * @param timeToWaitInSeconds
	 *            The time to wait for the element to appear in seconds.
	 *            
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	public static void assertTextAppears(WebElement webElement,
			String expectedText, int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				String browserName = TestSetup.getGridBrowserName();
					if (browserName.equals("MicrosoftEdge")){
						String TxtWithSpaces = webElement.getText();
						System.out.println("WebElement "+TxtWithSpaces+" = "+"["+ TxtWithSpaces+"]");
						String TxtWithNoSpaces = TxtWithSpaces.trim();
						System.out.println("WebElement "+TxtWithSpaces+" = "+"["+TxtWithNoSpaces+"]");
						assert expectedText.equals(TxtWithNoSpaces);
						break;
					}
				assertEquals(expectedText,
						webElement.getText());
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds)
					fail("A timeout occured after " + timeToWaitInSeconds + " second(s): " + error.getMessage());
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}

	/**
	 * Loop to continue checking for an element using it's ID using the 
	 * default maximum time for the UI from @link #getMaxTimeOutValue() getMaxTimeOutValue}
	 * 
	 * @param id
	 *            The id for identifying the element.
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	public static void assertElementAppears(String id) throws Throwable {
		assertElementAppears (id, getMaxTimeOutValue());
	}
	
	/**
	 * Loop to continue checking for an element using it's ID until the specified
	 * timeout occurs.
	 * 
	 * @param id
	 *            The id for identifying the element.
	 * @param timeToWaitInSeconds
	 *            The time to wait for the element to appear in seconds.
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	public static void assertElementAppears(String id,
			int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				assertTrue(isElementPresent(By.id(id)));
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds) {
					fail("A timeout occured after " + timeToWaitInSeconds + " second(s): " + error.getMessage());
				}
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}
	
	/**
	 * Assert the expected text has been found in the element using it's ID using the 
	 * default maximum time for the UI from @link #getMaxTimeOutValue() getMaxTimeOutValue}
	 * 
	 * @param id
	 *            The id for the element.
	 * @param expectedText
	 *            The expected text to check for.
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	public static void assertTextAppearsWithin(String id, String expectedText) throws Throwable {
		assertTextAppearsWithin (id, expectedText, getMaxTimeOutValue());
	}

	/**
	 * Assert the expected text has been found in the element using it's ID until the specified timeout occurs.
	 * 
	 * @param id
	 *            The id for the element.
	 * @param expectedText
	 *            The expected text to check for.
	 * @param timeToWaitInSeconds
	 *            The time to wait for the element to appear in seconds.
	 * 
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	public static void assertTextAppearsWithin(String id,
			String expectedText, int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				assertEquals(expectedText,
						getDriver().findElement(By.id(id))
								.getText());
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds)
					fail("timeout after " + second + " second(s): "
							+ error.getMessage());
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}
	
	/**
     * Loop to check for certain text appears within an element using
     * it's id within the specified time
	 * @param id
     *            The id for the element.
	 * @param containingText
     *            The expected text to check if it exists.
	 * @param timeToWaitInSeconds
     *            The time to wait for the element to appear in seconds.
     *
     * @throws Throwable
     *             Throws the error that the test case fails with.
     */
    public static void assertIdTextContains(String id,
            String containingText, int timeToWaitInSeconds) throws Throwable {
        for (int second = 0;; second++) {
        	try {
            	assertThat(getDriver().findElement(By.id(id))
                        .getText(), org.hamcrest.CoreMatchers.containsString(containingText));
            	        
                break;
            } catch (Error error) {
                if (second >= timeToWaitInSeconds)
                    fail("timeout after " + second + " second(s): "
                            + error.getMessage());
            }
            Thread.sleep(ONE_SECOND_IN_MS);
        }
    }
    
    
    public static void assertElementIdNotAppearsWithin(String id)
			throws Throwable {
		try {
			assertFalse(isElementPresent(By.id(id)));
		} catch (Error error) {
			fail("Element appeared on the screen" + error.getMessage());
		}
	}
    
	public static void assertPageURLIs(String expectedURL, int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				String actualURL = getDriver().getCurrentUrl();
				assertEquals(expectedURL, actualURL);
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds) {
					fail("timeout after " + second + " second(s): " + error.getMessage());
				}
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}
	
	public static void assertPageURLIs(String expectedURL) throws Throwable {
		assertPageURLIs(expectedURL, getMaxTimeOutValue());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Loop to continue checking for an element using xpath until the timeout occurs.
	 * 
	 * @param xPath
	 *            The xPath to the element.
	 * @param timeToWaitInSeconds
	 *            The time to wait for the element to appear in seconds.
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	@Deprecated
	public static void xPathElementAppearsWithin(String xPath,
			int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				assertTrue(isElementPresent(By.xpath(xPath)));
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds) {
					fail("timeout after " + second + " second(s): " + xPath
							+ error.getMessage());
				}
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}
	
	/**
	 * Loop to check for certain text appears within an element.
	 * 
	 * @param expectedText
	 *            The expected text to check for.
	 * @param xPath
	 *            The xPath to the element.
	 * @param timeToWaitInSeconds
	 *            The time to wait for the element to appear in seconds.
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	@Deprecated
	public static void xPathTextAppearsWithin(String expectedText,
			String xPath, int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				assertEquals(expectedText,
						getDriver().findElement(By.xpath(xPath)).getText());
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds)
					fail("timeout after " + second + " second(s): "
							+ error.getMessage());
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}
	
	@Deprecated
	public static void xPathValueAppearsWithin(String expectedValue,
			String xPath, int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				assertEquals(
						expectedValue,
						getDriver().findElement(By.xpath(xPath)).getAttribute(
								"value"));
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds)
					fail("timeout after " + second + " second(s): "
							+ error.getMessage());
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}
	
	@Deprecated
	public static boolean xPathStatAppearsWithin(String xPath,
			int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				String StatsString = getDriver().findElement(By.xpath(xPath))
						.getText();
				String StatNumber = StatsString.replaceAll("[^0-9]", "");
				System.out.println(StatNumber);
				Integer.parseInt(StatNumber);
				return true;
			} catch (NumberFormatException e) {

				if (second >= timeToWaitInSeconds) {
					fail("timeout after " + second + " second(s): ");
				}
			}
			Thread.sleep(1000);
		}
	}
	
	/**
	 * Check to make sure an element hasn't appeared on the page.
	 * 
	 * @param xPath
	 *            The xPath to the element.
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	@Deprecated
	public static void xPathElementNotPresent(String xPath) throws Throwable {
		try {
			assertFalse(isElementPresent(By.xpath(xPath)));
		} catch (Error error) {
			fail("Element appeared on the screen" + error.getMessage());
		}
	}

	/**
	 * Loop to continue checking to make sure an element is not present until
	 * the timeout occurs.
	 * 
	 * @param xPath
	 *            The xPath to the element.
	 * @param timeToWaitInSeconds
	 *            The time to wait for the element to not be present in seconds.
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	@Deprecated
	public static void xPathElementNotPresentWithin(String xPath,
			int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				assertFalse(isElementPresent(By.xpath(xPath)));
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds) {
					fail("timeout after " + second + " second(s): " + xPath
							+ error.getMessage());
				}
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}
	
	
	
	
	
	
	

	/**
	 * Loop to continue checking for the specified number of elements until the
	 * timeout occurs.
	 * 
	 * @param xPath
	 *            The xPath to the element.
	 * @param count
	 *            The number of elements.
	 * @param timeToWaitInSeconds
	 *            The time to wait for the element to appear in seconds.
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	public static void xPathElementsAppearWithin(String xPath, int count,
			int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				List<WebElement> elements = getDriver().findElements(
						By.xpath(xPath));
				if ((elements == null) && (count == 0)) {
					break;
				} else if (elements != null) {
					assertTrue(elements.size() == count);
				}
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds) {
					fail("timeout after " + second + " second(s): " + xPath
							+ error.getMessage());
				}
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}

	/**
	 * Loop to continue checking for an element using cssSelector until the
	 * timeout occurs.
	 * 
	 * @param cssSelector
	 *            The cssSelector for identifying the element.
	 * @param timeToWaitInSeconds
	 *            The time to wait for the element to appear in seconds.
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	@Deprecated
	public static void cssElementAppearsWithin(String cssSelector,
			int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				assertTrue(isElementPresent(By.cssSelector(cssSelector)));
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds) {
					fail("timeout after " + second + " second(s): "
							+ cssSelector + error.getMessage());
				}
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}
	

	
	/**
	 * Loop to check for certain text appears within an element using
	 * cssSelector.
	 * 
	 * @param expectedText
	 *            The expected text to check for.
	 * @param cssSelector
	 *            The cssSelector for the element.
	 * @param timeToWaitInSeconds
	 *            The time to wait for the element to appear in seconds.
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	public static void cssTextAppearsWithin(String expectedText,
			String cssSelector, int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {String browserName = TestSetup.getGridBrowserName();
			if (browserName.equals("MicrosoftEdge")){
				String TxtWithSpaces = getDriver().findElement(By.cssSelector(cssSelector)).getText();
				System.out.println("WebElement "+TxtWithSpaces+" = "+"["+ TxtWithSpaces+"]");
				String TxtWithNoSpaces = TxtWithSpaces.trim();
				System.out.println("WebElement "+TxtWithSpaces+" = "+"["+TxtWithNoSpaces+"]");
				assert expectedText.equals(TxtWithNoSpaces);
				break;
			}
				assertEquals(expectedText,
						getDriver().findElement(By.cssSelector(cssSelector))
								.getText());
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds)
					fail("timeout after " + second + " second(s): "
							+ error.getMessage());
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}
	

	
	
	
	
	
	

	@Deprecated
	public static void cssValueAppearsWithin(String expectedValue,
			String cssSelector, int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				assertEquals(expectedValue,
						getDriver().findElement(By.cssSelector(cssSelector))
								.getAttribute("value"));
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds)
					fail("timeout after " + second + " second(s): "
							+ error.getMessage());
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}
	
	public static void ValueAppearsWithin(String expectedValue,
			WebElement element, int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				assertEquals(expectedValue,
						element.getAttribute("value"));
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds)
					fail("timeout after " + second + " second(s): "
							+ error.getMessage());
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}

	@Deprecated
	public static void cssassertElementNotAppearsWithin(String cssSelector,
			int timeToWaitInSeconds) throws Throwable {
		for (int second = 0;; second++) {
			try {
				assertFalse(isElementPresent(By.cssSelector(cssSelector)));
				break;
			} catch (Error error) {
				if (second >= timeToWaitInSeconds) {
					fail("timeout after " + second
							+ " second(s). The css selector was: "
							+ cssSelector + " , the error message was: "
							+ error.getMessage()
							+ " , and the stack trace is: "
							+ error.getStackTrace());
				}
			}
			Thread.sleep(ONE_SECOND_IN_MS);
		}
	}

	/**
	 * Check using cssSelector to make sure an element hasn't appeared on the
	 * page.
	 * 
	 * @param xPath
	 *            The xPath to the element.
	 * @throws Throwable
	 *             Throws the error that the test case fails with.
	 */
	@Deprecated
	public static void assertCssElementNotPresent(String cssSelector)
			throws Throwable {
		try {
			assertFalse(isElementPresent(By.cssSelector(cssSelector)));
		} catch (Throwable error) {
			fail("Element appeared on the screen " + error.getMessage());
		}
	}

	/**
	 * Compare 2 Offense Severity Characters
	 * Compare source to against
	 * Natural ordering goes C > H > M > L (Critical > High > Medium > Low)
	 * If source is greater than against, return 1
	 * If source is the same as against, return 0
	 * If source is less than against, return -1
	 * @param source The first Offense Severity Character to compare
	 * @param against The second Offense Severity Character to compare
	 * @return 1 if source > against, 0 if source == against, -1 if source < against
	 */
	public static int offenseSeverityCompare(String source, String against) {
		ArrayList<String> sequence = new ArrayList<String>();
		sequence.add("L");
		sequence.add("M");
		sequence.add("H");
		sequence.add("C");

		source = source.trim().toUpperCase();
		against = against.trim().toUpperCase();
		
		int sourceIndex = sequence.indexOf(source);
		int againstIndex = sequence.indexOf(against);
		
		if (sourceIndex == -1) {
			throw new RuntimeException("\"source\" parameter is invalid.");
		}
		if (againstIndex == -1) {
			throw new RuntimeException("\"against\" parameter is invalid.");
		}
		if (sourceIndex == againstIndex) {
			return 0;
		} else if (sourceIndex < againstIndex) {
			return -1;
		} else {
			return 1;
		}
	}
	
	/**
	 * Determine if the list of dates given is sorted based on the sort order given.
	 * @param dates An array of @WebElement that hold all the dates
	 * @param order The sort order to check the elements against, should be ascend or descend
	 * @return true if the elements are sorted or too short to be sorted (one element or less), false otherwise
	 */
	public static boolean areDatesSorted(List <WebElement> dates , String order){
		String first, second = null;
		int count = dates.size();
  	  
		if(count == 1)
			return true;
  			   
		for (int i = 1; i < count; i++) {
			WebElement element1 = dates.get (i - 1);
			String id1 = element1.getAttribute("id");
			WebElement element2 = dates.get(i);
			String id2 = element2.getAttribute("id");
  			
			if (id2.endsWith("-date") && id1.endsWith("-date")) {
				first = element1.getText();
				second = element2.getText();

				DateFormat dateFormat = DateFormat
					.getDateInstance(DateFormat.LONG);
			
				try {
					Date date1 = dateFormat.parse(first);
					Date date2 = dateFormat.parse(second);
	        	
					//If the element is not visible, page down
					Actions actions = new Actions(getDriver());  
					if(!ExpectedConditions.visibilityOf(element1).equals(element1)){
						actions.sendKeys(Keys.PAGE_DOWN).perform();
					}
  		
					if (order.equalsIgnoreCase("descend")){
						if (date1.before(date2)) {
							return false;
						}
					} else if (order.equalsIgnoreCase("ascend")){	        		
						if (date1.after(date2)) {
							return false;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			} else
				return false;
		}
		return true;
	}

	/**
	 * Determine if the list of users given is sorted based on the sort order given.
	 * @param users An array of @WebElement that hold all the users
	 * @param order The sort order to check the elements against, should be ascend or descend
	 * @return true if the elements are sorted or too short to be sorted (one element or less), false otherwise
	 */
	public static boolean areUsersSorted(List <WebElement> users , String order){
		String first, second = null;
		WebElement element1, element2 = null;
		String id1, id2 = null;
  		
		int count =  users.size();
		if (count == 1)
			return true;

		Actions actions = new Actions(getDriver());
		for (int i=1; i < count; i++) {
			element2 = users.get(i);
			element1 = users.get(i-1);
			
			id2 = element2.getAttribute("id");
			id1 =  element1.getAttribute("id");
			
			if (id2.endsWith("-changed_by") && id1.endsWith("-changed_by")) {
				//If the element is not visible, page down
				if(!ExpectedConditions.visibilityOf(element1).equals(element1)){
					actions.sendKeys(Keys.PAGE_DOWN).perform();
				}
				
				first = element1.getText();
				second = element2.getText();
				
				if (order.equalsIgnoreCase("descend")) {
					if (first.compareTo(second) < 0) {
						return false;
					}
				} else if (order.equalsIgnoreCase("ascend")) {
					if (first.compareTo(second) > 0) {
						return false;
					}
				}
			} else
				return false;
			}
		return true;
	}
	
	/**
	 * Determine if the date provided is the current date, using the current timezone
	 * @param compareDate The date to be checked
	 * @return If the date is the current date
	 */
	public static boolean isTodaysDate (String compareDate) {
		DateFormat dateFormat = DateFormat
			.getDateInstance(DateFormat.LONG);

		try {
			String currentDate = dateFormat.format(new Date());
			return currentDate.compareTo(compareDate) == 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
  	
  /** 
   * Retrieve the unique identifier from the element ID for custom attributes
   * 
   * @param elementId The element ID to be parsed
   * @return The unique identifier
   */
	public static String getRowIdentifier (String elementId) {
		//rc-CUSTOM-01.75-title-text //rc-CUSTOM-01.75-statement  //CUSTOM-01.10-slider //CUSTOM-01.28-remove-button  //CUSTOM-01.29-edit-button
	  	  		
		int dot = elementId.indexOf("."); 
		String temp = elementId.substring(dot, elementId.length());
		int dash = temp.indexOf("-"); 
	  	  
		String unique  = temp.substring(0, dash);
		return unique;
	}

	/**
	 * This method will send the keys to the @WebElement, 
	 * and then verify they have been sent. If not, it will continue to 
	 * retry for the number of retries specified, with a default of three.
	 * @param element The element to do the sendkeys too.
	 * @param keys The keys to send.
	 * @throws Throwable The exception thrown if something goes wrong.
	 */
	public static void verifiedSendKeys(WebElement element, String keys) throws Throwable {
		int maxRetries = 3;
		verifiedSendKeys (element, keys, maxRetries);
		//Wait for debounce
		Thread.sleep(1000);
		
	}
	
	/**
	 * This method will send the keys to the @WebElement, 
	 * and then verify they have been sent. If not, it will continue to 
	 * retry for the number of retries specified, with a default of three.
	 * @param element The element to do the sendkeys too.
	 * @param keys The keys to send.
	 * @param retries The number of times to retry.
	 * @throws Throwable The exception thrown if something goes wrong.
	 */
	public static void verifiedSendKeys(WebElement element, String keys, int retries) throws Throwable {
		for (int tries = 0;; tries++) {
			try {
				element.clear();
				element.click();
				element.sendKeys(keys);
				String elementText = element.getAttribute("value");
		        assertTrue(elementText.equals(keys));
				break;
			}
			 catch (Throwable throwable) {
				if (tries > retries) {
					fail("A failure occured after " + tries + " tries: " + throwable.getMessage());
				} 
			}
			Thread.sleep(500);
		}
	}
	
	/**
	 * A method to check the disabled attribute of a @WebElement
	 * @param webElement The @WebElement to check.
	 * @param value The expected value of the disabled attribute.
	 */
	public static void buttonDisabled (WebElement webElement, String value) {
		String buttonDisabledValue = webElement.getAttribute("disabled");
		try {
			assertTrue (buttonDisabledValue.equals(value));
		} catch (Throwable t) {
			fail ("The element was having its disabled attribute checked and something went wrong."
				+ t.getMessage());
		}
	}
	
	/**
	 * This method wraps getting the current time from the 
	 * Eastern Time Zone formatted in the way the caller specifies.
	 * @param dateFormatting The formatting type from @SimpleDateFormat
	 * @return The time for the eastern time zone in the formatting requested.
	 */
	public static String getEasternTime (String dateFormatting) {
		DateFormat dateformat = new SimpleDateFormat(dateFormatting);
		dateformat.setTimeZone(TimeZone.getTimeZone(EASTERN_TIMEZONE));
		return dateformat.format(new Date());
	}
		
	/**
	 * This method opens a shadow DOM under a root element and returns
	 * the shadowRoot as a WebElement that can be used to access elements
	 * inside the shadow DOM
	 * @param rootElement The element the shadowRoot is attached to
	 * @return The opened shadowRoot with contents now accessible to WebDriver
	 */
	public static WebElement expandRootElement(WebElement rootElement) {
		WebElement shadowRoot = (WebElement) ((JavascriptExecutor) TestUtils.getDriver()).executeScript(
				"return arguments[0].shadowRoot", rootElement);
		return shadowRoot;
	}
}