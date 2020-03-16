package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverFactory {
	private static WebDriver driver = null;
	
	public synchronized static boolean isDriverCreated () {
		if (driver == null) {
			return false;
		}
		return true;
	}

	public synchronized static WebDriver getDriver() {
		if (driver == null) {
			int loop = 0;
			int retryCount = 3;
			while (loop <= retryCount) {
				try {
					return createDriver();
				} catch (Exception exception) {
					System.out.println ("The session was unable to be created. Trying again.");
					if (loop > retryCount) {
						throw exception;
					}
				}
				loop++;
			}
			return null;
		} else {
			return driver;
		}
	}

	private synchronized static WebDriver createDriver() {
		if (driver == null) {
			
			DesiredCapabilities capability;
			String browserName = "chrome";
			String platform = "LINUX";
			String version = "80.0.3987.106";

			LoggingPreferences loggingPrefs = new LoggingPreferences();
			loggingPrefs.enable(LogType.BROWSER, Level.ALL);
			
			switch (browserName) {
				default:
				case "firefox":
					capability = DesiredCapabilities.firefox();
					capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
					capability.setCapability(CapabilityType.LOGGING_PREFS, loggingPrefs);
					capability.setCapability(FirefoxDriver.MARIONETTE, true);
					
					FirefoxProfile profile = new FirefoxProfile();
					capability.setCapability(FirefoxDriver.PROFILE, profile);
					break;
				case "chrome":
					capability = DesiredCapabilities.chrome();
					capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
					capability.setCapability(CapabilityType.LOGGING_PREFS, loggingPrefs);
					capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
							UnexpectedAlertBehaviour.ACCEPT);
	
					
					
					ChromeOptions options = new ChromeOptions();
					capability.setCapability(ChromeOptions.CAPABILITY, options);
					break;
			}

			capability.setBrowserName(browserName);
			capability.setPlatform(getPlatform(platform));

			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
			} catch (MalformedURLException exception) {
				exception.printStackTrace();
			}
		}
		return driver;
	}

	public synchronized static void clearDriver() {
		try {
			driver.close();
			// This will likely run unless close really has issues
			driver.quit();
		} catch (Exception exception) {
			// Let's try once more to see if we can't get rid of the dangling
			// sessions issue.
			try {
				driver.quit();
			} catch (Exception innerException) {
				innerException.printStackTrace();
			}
			exception.printStackTrace();
		} finally {
			// No matter how this ends, it needs to go to null so the next
			// scenario(s) don't fail.
			driver = null;
		}
	}

	private static Platform getPlatform(String platformName) {
		switch (platformName) {
			// Linux/Unix choices
			case "LINUX":
				return Platform.LINUX;
			case "UNIX":
				return Platform.UNIX;
			// Any and default choice
			case "ANY":
			default:
				return Platform.ANY;
		}
	}
}