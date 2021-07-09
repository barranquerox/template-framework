package pageobjects.base;

import java.time.Clock;
import org.openqa.selenium.WebElement;

/**
 * Abstract Component.
 */
public abstract class AbstractComponent extends AbstractPageObject {

  protected static final int TIMEOUT_TO_LOAD_COMPONENT = 10;
  private static final int SCROLL_TIMEOUT = 500;

  /**
   * Container of the component.
   */
  protected WebElement container;

  /**
   * Initializes the component with the container of the component.
   *
   * @param container the WebElement that contains the whole component in the web page.
   */
  public AbstractComponent(WebElement container) {
    super(Clock.systemDefaultZone(), TIMEOUT_TO_LOAD_COMPONENT);
    this.container = container;
  }

  /**
   * Wait for an implicit duration of time until the page scroll in calendar is finished.
   */
  protected void waitForScrollToEnd() {
    logger.debug("Wait for scroll to end");
    try {
      Thread.sleep(SCROLL_TIMEOUT);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Gets the container of the component.
   *
   * @return the WebElement container of the component
   */
  public WebElement getContainer() {
    return container;
  }
}
