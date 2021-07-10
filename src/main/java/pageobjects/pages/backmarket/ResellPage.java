package pageobjects.pages.backmarket;

import java.time.Duration;
import java.time.Instant;
import pageobjects.pages.backmarket.base.HeaderAndFooterPage;
import utils.TestReporter;

public class ResellPage extends HeaderAndFooterPage {

  /**
   * Resell Page Constructor.
   */
  public ResellPage() {
    logger.debug("Initialize Resell Page");
  }

  @Override
  protected void isLoaded() {
    final Instant start = Instant.now();
    super.isLoaded();

    final Instant finish = Instant.now();
    final long timeElapsed = Duration.between(start, finish).toMillis();
    TestReporter.addScreenshotToReport("The Resell Page was loaded correctly in "
        + timeElapsed + " milliseconds.");
  }
}
