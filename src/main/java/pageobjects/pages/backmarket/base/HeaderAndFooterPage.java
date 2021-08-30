package pageobjects.pages.backmarket.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import pageobjects.base.AbstractPage;
import pageobjects.components.backmarket.Footer;
import pageobjects.components.backmarket.Header;
import pageobjects.pages.backmarket.HomePage;
import pageobjects.pages.backmarket.RegisterPage;
import pageobjects.pages.backmarket.ResellPage;
import pageobjects.pages.backmarket.ShoppingCartPage;
import utils.TestReporter;

/**
 * A backmarket page with header and footer.
 */
public class HeaderAndFooterPage extends AbstractPage {
  // Selectors
  private final By headerContainerBy = By.tagName("header");
  private final By footerContainerBy = By.tagName("footer");

  // Page Components
  private Header header;
  private Footer footer;

  /**
   * Header and Footer Page Constructor.
   */
  public HeaderAndFooterPage() {
    logger.debug("Initialize Header And Footer Page");
  }

  /**
   * Opens the cookie information page.
   */
  public void openCookiesInformationPage() {
    TestReporter.addInfoToReport("Open cookies information page");
    footer.openCookiesLink();
  }

  /**
   * Opens the sign in page.
   *
   * @return The sign in page.
   */
  public RegisterPage signIn() {
    TestReporter.addInfoToReport("Sign in");
    header.signIn();

    RegisterPage registerPage = new RegisterPage();
    registerPage.get();
    return registerPage;
  }

  /**
   * Opens the resell page.
   *
   * @return The resell page.
   */
  public ResellPage resell() {
    TestReporter.addInfoToReport("Resell");
    header.resell();

    ResellPage resellPage = new ResellPage();
    resellPage.get();
    return resellPage;
  }

  /**
   * Opens the shopping cart page.
   *
   * @return The shopping cart page.
   */
  public ShoppingCartPage openShoppingCart() {
    TestReporter.addInfoToReport("Open shopping cart");
    header.openShoppingCart();

    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    shoppingCartPage.get();
    return shoppingCartPage;
  }

  /**
   * Opens the back market home page.
   *
   * @return The home page.
   */
  public HomePage openHomePage() {
    TestReporter.addInfoToReport("Open home page");
    header.openHomePage();

    HomePage homePage = new HomePage();
    homePage.get();
    return homePage;
  }

  @Override
  protected void isLoaded() {
    logger.debug("Start loading header and footer base page");
    try {
      driver.findElement(headerContainerBy);
      logger.debug("The header container is displayed");
      driver.findElement(footerContainerBy);
      logger.debug("The footer container is displayed");
    } catch (NoSuchElementException e) {
      throwNotLoadedException("The Header and Footer page was not loaded correctly.", e);
    }

    header = new Header(driver.findElement(headerContainerBy));
    header.get();

    footer = new Footer(driver.findElement(footerContainerBy));
    footer.get();

    logger.debug("Header and footer base page is loaded correctly");
  }
}
