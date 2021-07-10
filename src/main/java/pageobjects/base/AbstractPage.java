package pageobjects.base;

import driver.DriverBase;
import java.time.Clock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Base page.
 */
public abstract class AbstractPage extends AbstractPageObject {

  protected WebDriver driver;
  protected static final int TIMEOUT_TO_LOAD_PAGE = 20;

  public AbstractPage() {
    super(Clock.systemDefaultZone(), TIMEOUT_TO_LOAD_PAGE);
    driver = DriverBase.getDriver();
    PageFactory.initElements(driver, this);
  }
}
