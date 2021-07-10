package pageobjects.pages.backmarket;

import java.time.Duration;
import java.time.Instant;
import pageobjects.pages.backmarket.base.HeaderAndFooterPage;
import utils.TestReporter;

public class RegisterPage extends HeaderAndFooterPage {

  /**
   * Register Page Constructor.
   */
  public RegisterPage() {
    logger.debug("Initialize Register Page");
  }

  @Override
  protected void isLoaded() {
    final Instant start = Instant.now();
    super.isLoaded();

    final Instant finish = Instant.now();
    final long timeElapsed = Duration.between(start, finish).toMillis();
    TestReporter.addScreenshotToReport("The Register Page was loaded correctly in "
        + timeElapsed + " milliseconds.");
  }
}
