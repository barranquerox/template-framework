package pageobjects.base;

import driver.DriverBase;
import errors.PageLoadingError;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import utils.TestReporter;

/**
 * Abstract Page Object.
 */
public abstract class AbstractPageObject extends SlowLoadableComponent<AbstractPageObject> {

  /**
   * Logger.
   */
  protected static final Logger logger = LogManager.getLogger(AbstractPageObject.class);

  private static final int TRANSITION_TIMEOUT_MILLISECONDS = 500;

  protected AbstractPageObject(Clock clock, int timeOutInSeconds) {
    super(clock, timeOutInSeconds);
  }

  /**
   * Logs and throws the exception caught when loading the component.
   *
   * @param customMessage The custom message to write in the log.
   * @param e Exception caught when trying to load the component.
   */
  protected void throwNotLoadedException(String customMessage, @NotNull Exception e) {
    throw new PageLoadingError(customMessage + "\n" + e.getMessage(), e.getCause());
  }

  /**
   * Logs and throws the exception caught when loading the component.
   *
   * @param customMessage The custom message to write in the log.
   */
  protected void throwNotLoadedException(String customMessage) {
    throw new PageLoadingError(customMessage);
  }

  /**
   * Clicks on an element using actions.
   *
   * @param element to be clicked.
   */
  protected void elementClickWithActions(WebElement element) {
    logger.debug("Click on the element using Actions");
    Actions actions = new Actions(DriverBase.getDriver());
    actions.moveToElement(element).click().build().perform();
  }

  /**
   * Switch to a new opened tab (for example when a restaurant page is opened).
   */
  protected void switchToNewTab() {
    logger.debug("Switch to new tab");
    WebDriver driver = DriverBase.getDriver();

    // switch to the new tab with the restaurant page
    ArrayList<String> twoTabs = new ArrayList<>(driver.getWindowHandles());
    logger.debug("Window handles: {}", twoTabs);
    String newTabHandle = twoTabs.get(1);
    logger.debug("New tab handle: {}", newTabHandle);
    driver.switchTo().window(newTabHandle);
    TestReporter.addInfoToReport("Switched to new tab");
  }

  /**
   * Switch to the initial opened tab (for example when a home page is opened).
   */
  protected void switchToInitialTab() {
    logger.debug("Switch to initial tab");
    WebDriver driver = DriverBase.getDriver();

    // switch to the initial tab
    ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
    logger.debug("Window handles: {}", tab);
    String initialTabHandle = tab.get(0);
    logger.debug("Initial tab handle: {}", initialTabHandle);
    driver.switchTo().window(initialTabHandle);
    TestReporter.addInfoToReport("Switched to initial tab");
  }

  /**
   * Gets cookie value by given name.
   *
   * @param name String
   * @return String
   */
  public String getCookieValue(String name) {
    Cookie cookie = DriverBase.getDriver().manage().getCookieNamed(name);

    if (cookie != null) {
      logger.debug("{} cookie value is {}", name, cookie.getValue());
      return cookie.getValue();
    } else {
      return null;
    }
  }

  /**
   * Switch to a new opened window (for example when a link is opened on firefox).
   */
  protected void switchToNewWindow() {
    TestReporter.addInfoToReport("Switch to new window");
    WebDriver driver = DriverBase.getDriver();
    String parentWindow = driver.getWindowHandle();
    Set<String> handles = driver.getWindowHandles();
    for (String windowHandle : handles) {
      if (!windowHandle.equals(parentWindow)) {
        driver.switchTo().window(windowHandle);
        logger.debug("Switched to new window");
      }
    }
  }

  /**
   * Scroll to an element.
   *
   * @param element WebElement
   */
  protected void scrollToElement(WebElement element) {
    logger.debug("Scroll to element");
    Coordinates coordinates = ((Locatable) element).getCoordinates();
    coordinates.inViewPort();
    try {
      Thread.sleep(TRANSITION_TIMEOUT_MILLISECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  protected void load() {
    // empty method
  }

  @Override
  protected void isLoaded() throws Error {
    // empty method
  }
}
