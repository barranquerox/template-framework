package pageobjects.pages;

import driver.DriverBase;
import environment.EnvironmentConfig;
import java.time.Duration;
import java.time.Instant;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.base.AbstractPage;
import utils.TestReporter;

/**
 * Google Home Page.
 */
public class GoogleHomePage extends AbstractPage {
  private final String googleBaseUrl;

  @FindBy(name = "q")
  private WebElement searchBar;
  @FindBy(name = "btnK")
  private WebElement searchButton;
  @FindBy(name = "btnI")
  private WebElement feelingLuckyButton;
  @FindBy(css = "button[data-ved] + button")
  private WebElement acceptCookiesButton;
  @FindBy(css = "span > div")
  private WebElement cookiesPopup;

  /**
   * Google Home Page Constructor.
   */
  public GoogleHomePage() {
    logger.debug("Initialize Google Home Page");
    driver = DriverBase.getDriver();
    googleBaseUrl = EnvironmentConfig.getGoogleUrl();
    PageFactory.initElements(driver, this);
  }

  /**
   * Launches a search in the google home page.
   *
   * @param query string to be searched
   */
  public void search(String query) {
    TestReporter.addInfoToReport("Search query: " + query);
    searchBar.sendKeys(query);
    logger.debug("Entered {} in the search bar", query);
    searchButton.click();
    logger.debug("Clicked the search button");
  }

  /**
   * Accepts the cookies.
   *
   * @return the google home page
   */
  public GoogleHomePage acceptCookies() {
    TestReporter.addInfoToReport("Accept the cookies");
    acceptCookiesButton.click();
    logger.debug("The accept cookies button was clicked.");
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.invisibilityOf(cookiesPopup));
    return this;
  }

  @Override
  protected void load() {
    TestReporter.addInfoToReport("Load Google Home Page: " + googleBaseUrl);
    driver.get(googleBaseUrl);
  }

  @Override
  protected void isLoaded() {
    final Instant start = Instant.now();

    try {
      searchBar.isDisplayed();
      logger.debug("The search bar is displayed");
      searchButton.isDisplayed();
      logger.debug("The search button is displayed");
      feelingLuckyButton.isDisplayed();
      logger.debug("The feeling lucky button is displayed");
    } catch (NoSuchElementException e) {
      throwNotLoadedException("The Google home page was not loaded correctly.", e);
    }

    final Instant finish = Instant.now();
    final long timeElapsed = Duration.between(start, finish).toMillis();
    TestReporter.addScreenshotToReport("The Google Home Page was loaded correctly in "
        + timeElapsed + " milliseconds.");
  }

}
