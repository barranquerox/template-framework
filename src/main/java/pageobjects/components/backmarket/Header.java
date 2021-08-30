package pageobjects.components.backmarket;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pageobjects.base.AbstractComponent;

/**
 * The header page component.
 */
public class Header extends AbstractComponent {

  // Selectors
  private final By homePageLinkBy = By.cssSelector("header a");
  private final By resellButtonBy = By.cssSelector("[data-test='topnav-buyback-link']");
  private final By faqButtonBy = By.cssSelector("[data-test='topnav-buyback-link'] + a");
  private final By signInButtonBy =
      By.cssSelector("[data-test='search-bar-container'] + div a[aria-label]");
  private final By shoppingCartBy = By.cssSelector("[data-test='search-bar-container'] + div + a");


  /**
   * Initializes the component with the container of the component.
   *
   * @param container the WebElement that contains the whole component in the web page.
   */
  public Header(WebElement container) {
    super(container);
  }

  public void signIn() {
    logger.debug("Click the sign in button");
    container.findElement(signInButtonBy).click();
  }

  public void resell() {
    logger.debug("Click the resell button");
    container.findElement(resellButtonBy).click();
  }

  public void openFaq() {
    logger.debug("Click the faq button");
    container.findElement(faqButtonBy).click();
  }

  public void openShoppingCart() {
    logger.debug("Click the shopping cart button");
    container.findElement(shoppingCartBy).click();
  }

  public void openHomePage() {
    logger.debug("Click the open home page link");
    container.findElement(homePageLinkBy).click();
  }

  @Override
  protected void isLoaded() {
    logger.debug("Start loading the header");
    try {
      container.findElement(resellButtonBy);
      logger.debug("The resell button is displayed");
      container.findElement(faqButtonBy);
      logger.debug("The faq button is displayed");
      container.findElement(signInButtonBy);
      logger.debug("The sign in button is displayed");
      container.findElement(shoppingCartBy);
      logger.debug("The shopping cart button is displayed");
      container.findElement(homePageLinkBy);
      logger.debug("The home page link is displayed");
    } catch (NoSuchElementException e) {
      throwNotLoadedException("The Header was not loaded correctly.", e);
    }

    logger.debug("The header was loaded correctly");
  }
}
