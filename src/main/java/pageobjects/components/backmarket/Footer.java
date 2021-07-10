package pageobjects.components.backmarket;

import java.time.Instant;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pageobjects.base.AbstractComponent;

public class Footer extends AbstractComponent {

  private final By cookiesLinkBy = By.cssSelector("[name='Cookies']");

  /**
   * Initializes the component with the container of the component.
   *
   * @param container the WebElement that contains the whole component in the web page.
   */
  public Footer(WebElement container) {
    super(container);
  }

  public void openCookiesLink() {
    logger.debug("Open cookies link");
    container.findElement(cookiesLinkBy).click();
  }

  @Override
  protected void isLoaded() {
    logger.debug("Start loading the footer");

    try {
      container.findElement(cookiesLinkBy);
      logger.debug("Cookies link is displayed");
    } catch (NoSuchElementException e) {
      throwNotLoadedException("The Footer was not loaded correctly.", e);
    }

    logger.debug("The footer was loaded correctly");
  }
}
