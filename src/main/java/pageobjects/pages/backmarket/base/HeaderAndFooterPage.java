package pageobjects.pages.backmarket.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import pageobjects.base.AbstractPage;
import pageobjects.components.backmarket.Footer;
import pageobjects.components.backmarket.Header;
import pageobjects.pages.backmarket.HomePage;
import utils.TestReporter;

public class HeaderAndFooterPage extends AbstractPage {
  // Selectors
  private final By headerContainerBy = By.tagName("header");
  private final By footerContainerBy = By.tagName("footer");

  // Page Components
  private Header header;
  private Footer footer;

  /**
   * Back Market Header and Footer Page Constructor.
   */
  public HeaderAndFooterPage() {
    logger.debug("Initialize Back Market Header And Footer Page");
  }

  public void openCookiesInformationPage() {
    TestReporter.addInfoToReport("Open cookies information page");
    footer.openCookiesLink();
  }

  public void signIn() {
    TestReporter.addInfoToReport("Sign in");
    header.signIn();
  }

  public void resell() {
    TestReporter.addInfoToReport("Resell");
    header.resell();
  }

  public void openQualityCenter() {
    TestReporter.addInfoToReport("Open quality center");
    header.openQualityCenter();
  }

  public void openShoppingCart() {
    TestReporter.addInfoToReport("Open shopping cart");
    header.openShoppingCart();
  }

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
      throwNotLoadedException("The Back Market page was not loaded correctly.", e);
    }

    header = new Header(driver.findElement(headerContainerBy));
    header.get();

    footer = new Footer(driver.findElement(footerContainerBy));
    footer.get();

    logger.debug("Header and footer base page is loaded correctly");
  }
}
