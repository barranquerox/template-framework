package pageobjects.pages.backmarket;

import java.time.Duration;
import java.time.Instant;
import pageobjects.pages.backmarket.base.HeaderAndFooterPage;
import utils.TestReporter;

/**
 * The quality center page.
 */
public class QualityCenterPage extends HeaderAndFooterPage {

  /**
   * Quality Center Page Constructor.
   */
  public QualityCenterPage() {
    logger.debug("Initialize Quality Center Page");
  }

  @Override
  protected void isLoaded() {
    final Instant start = Instant.now();
    super.isLoaded();

    final Instant finish = Instant.now();
    final long timeElapsed = Duration.between(start, finish).toMillis();
    TestReporter.addScreenshotToReport("The Quality Center Page was loaded correctly in "
        + timeElapsed + " milliseconds.");
  }
}
