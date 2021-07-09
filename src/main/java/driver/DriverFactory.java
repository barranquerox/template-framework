package driver;

import static driver.BrowserType.CHROME;

import com.google.common.base.Strings;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

  /**
   * The WebDriver used in the tests.
   */
  private RemoteWebDriver webDriver;

  /**
   * Stores the selected driver type to use on the tests. Can be CHROME or FIREFOX.
   */
  private final BrowserType selectedBrowserType;

  /**
   * The logger.
   */
  protected static final Logger logger = LogManager.getLogger(DriverFactory.class);

  /**
   * Stores the OS name.
   */
  private final String operatingSystem = System.getProperty("os.name").toUpperCase();

  /**
   * Stores the System Architecture. Most of the times is x86_64.
   */
  private final String systemArchitecture = System.getProperty("os.arch");

  /**
   * Java version.
   */
  private final String javaVersion = System.getProperty("java.version");

  /**
   * Stores value from the command line argument gridURL.
   */
  private final String seleniumGridUrl = System.getProperty("gridURL");

  /**
   * Constructor of the DriverFactory class. Reads the browser value passed
   * as an argument on the command line.
   */
  public DriverFactory() {
    // By default, the driver type is CHROME
    BrowserType browserType = CHROME;

    // reads the command line argument browser
    String browser = System.getProperty("browser", browserType.toString()).toUpperCase();
    try {
      browserType = BrowserType.valueOf(browser);
    } catch (IllegalArgumentException ignored) {
      logger.debug("An unknown driver specified, defaulting to '{}'", browserType, ignored);
    }
    selectedBrowserType = browserType;
  }

  /**
   * Gets an instantiated WebDriver of the type defined in the browser argument of the command
   * line. If there is a WebDriver already instantiated it returns that one.
   *
   * @return an instantiated WebDriver object
   */
  public RemoteWebDriver getDriver() {
    if (null == webDriver) {
      logger.trace("Starting WebDriver");
      instantiateWebDriver(selectedBrowserType);
    }
    return webDriver;
  }

  /**
   * Safely quits the WebDriver and sets its value as null.
   */
  public void quitDriver() {
    logger.trace("Closing Webdriver");
    if (null != webDriver) {
      webDriver.quit();
      webDriver = null;
      logger.trace("Webdriver is closed and set to null");
    }
  }

  /**
   * Gets the driver type. Can be CHROME or FIREFOX
   *
   * @return BrowserType
   */
  public BrowserType getBrowserType() {
    logger.trace("Getting browser type");
    return selectedBrowserType;
  }

  private void instantiateWebDriver(BrowserType browserType) {
    logger.info("Local Operating System: {}", operatingSystem);
    logger.info("Local Architecture: {}", systemArchitecture);
    logger.info("Java Version: {}", javaVersion);
    logger.info("Selected Browser: {}", selectedBrowserType);

    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    // set template of the screen recording filename
    desiredCapabilities.setCapability("testFileNameTemplate", "{testName}");

    // If using selenium grid
    if (!Strings.isNullOrEmpty(seleniumGridUrl)) {
      logger.info("Connecting to Selenium Grid: {}", seleniumGridUrl);
      setPlatformCapability(desiredCapabilities);
      setBrowserVersionCapability(desiredCapabilities);

      // validates that the selenium grid url is well formatted
      URL remoteWebDriverUrl = null;
      try {
        remoteWebDriverUrl = new URL(this.seleniumGridUrl);
      } catch (MalformedURLException e) {
        logger.error("The selenium grid URL is not correct.", e);
      }

      // to lower case because the driver type parameter was set to UPPERCASE to match with the
      // driver type enum, but when setting the browserName capability it has to be lowercase
      desiredCapabilities.setBrowserName(selectedBrowserType.toString().toLowerCase());

      // The WebDriver is initialized with the same ChromeOptions or FirefoxOptions either
      // locally or on the selenium grid
      webDriver = new RemoteWebDriver(remoteWebDriverUrl,
          selectedBrowserType.getBrowserCapabilities(desiredCapabilities));

    } else {
      // not using selenium grid, local browser
      webDriver = browserType.getWebDriverObject(desiredCapabilities);
    }
    
    logger.info("Browser version: {}", webDriver.getCapabilities()
        .getCapability("browserVersion"));

    String userAgent = (String) webDriver.executeScript("return navigator.userAgent;");

    logger.info("User agent: {}", userAgent);
  }

  private static void setBrowserVersionCapability(DesiredCapabilities desiredCapabilities) {
    // This capability is used when executing tests on the cloud, to choose the browser version
    // number
    String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
    if (!Strings.isNullOrEmpty(desiredBrowserVersion)) {
      logger.info("Desired browser version: {}", desiredBrowserVersion);
      desiredCapabilities.setVersion(desiredBrowserVersion);
    }
  }

  private static void setPlatformCapability(DesiredCapabilities desiredCapabilities) {
    // This capability is used when executing tests on the cloud, to choose the operative system
    String desiredPlatform = System.getProperty("desiredPlatform");
    if (!Strings.isNullOrEmpty(desiredPlatform)) {
      logger.info("Desired platform: {}", desiredPlatform);
      desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
    }
  }
}
