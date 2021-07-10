package pageobjects.pages.backmarket;

import environment.EnvironmentConfig;
import java.time.Duration;
import java.time.Instant;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import pageobjects.pages.backmarket.base.HeaderAndFooterPage;
import utils.TestReporter;

public class HomePage extends HeaderAndFooterPage {
  private final String baseUrl;

  // Selectors
  private final By acceptCookieButtonBy = By.cssSelector("[data-qa='accept-cta']");

  /**
   * Back Market Home Page Constructor.
   */
  public HomePage() {
    logger.debug("Initialize Back Market Home Page");
    baseUrl = EnvironmentConfig.getBackMarketUrl();
  }

  public HomePage closeCookiePopup() {
    TestReporter.addInfoToReport("Close cookie popup");
    driver.findElement(acceptCookieButtonBy).click();
    this.get();
    return this;
  }

  @Override
  protected void load() {
    TestReporter.addInfoToReport("Load Back Market Home Page: " + baseUrl);
    driver.get(baseUrl);
  }

  @Override
  protected void isLoaded() {
    final Instant start = Instant.now();
    super.isLoaded();


    final Instant finish = Instant.now();
    final long timeElapsed = Duration.between(start, finish).toMillis();
    TestReporter.addScreenshotToReport("The Back Market Home Page was loaded correctly in "
        + timeElapsed + " milliseconds.");
  }
}
