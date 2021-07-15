package pageobjects.pages.backmarket;

import environment.EnvironmentConfig;
import java.time.Duration;
import java.time.Instant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.pages.backmarket.base.HeaderAndFooterPage;
import utils.TestReporter;

/**
 * Back Market home page.
 */
public class HomePage extends HeaderAndFooterPage {
  private final String baseUrl;

  // Selectors
  private final By acceptCookieButtonBy = By.cssSelector("[data-qa='accept-cta']");

  /**
   * Home Page Constructor.
   */
  public HomePage() {
    logger.debug("Initialize Home Page");
    baseUrl = EnvironmentConfig.getBackMarketUrl();
  }

  /**
   * Closes the cookie popup.
   *
   * @return The same home page.
   */
  public HomePage closeCookiePopup() {
    TestReporter.addInfoToReport("Close cookie popup");

    WebElement cookieButton = driver.findElement(acceptCookieButtonBy);
    cookieButton.click();
    logger.debug("The accept cookie button was clicked");

    final int COOKIE_TIMEOUT = 2;

    WebDriverWait wait = new WebDriverWait(driver, COOKIE_TIMEOUT);
    wait.until(ExpectedConditions.invisibilityOf(cookieButton));
    logger.debug("The accept cookie button has disappeared");
    return this;
  }

  @Override
  protected void load() {
    TestReporter.addInfoToReport("Load Home Page: " + baseUrl);
    driver.get(baseUrl);
  }

  @Override
  protected void isLoaded() {
    final Instant start = Instant.now();
    super.isLoaded();

    final Instant finish = Instant.now();
    final long timeElapsed = Duration.between(start, finish).toMillis();
    TestReporter.addScreenshotToReport("The Home Page was loaded correctly in "
        + timeElapsed + " milliseconds.");
  }
}
