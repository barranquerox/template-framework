package pageobjects.pages.backmarket;

import java.time.Duration;
import java.time.Instant;
import pageobjects.pages.backmarket.base.HeaderAndFooterPage;
import utils.TestReporter;

/**
 * The shopping cart page.
 */
public class ShoppingCartPage extends HeaderAndFooterPage {

  /**
   * Resell Page Constructor.
   */
  public ShoppingCartPage() {
    logger.debug("Initialize Shopping Cart Page");
  }

  @Override
  protected void isLoaded() {
    final Instant start = Instant.now();
    super.isLoaded();

    final Instant finish = Instant.now();
    final long timeElapsed = Duration.between(start, finish).toMillis();
    TestReporter.addScreenshotToReport("The Shopping Cart Page was loaded correctly in "
        + timeElapsed + " milliseconds.");
  }
}
