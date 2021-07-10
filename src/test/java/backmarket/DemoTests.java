package backmarket;

import base.TestBase;
import org.testng.annotations.Test;
import pageobjects.pages.backmarket.HomePage;

@Test(groups = {"full-regression"})
public class DemoTests extends TestBase {

  @Test(
      description = "Example back market test",
      enabled = true,
      retryAnalyzer = TestBase.RetryAnalyzer.class
  )
  public void demoBackMarketTest() {
    HomePage backMarketHomePage = new HomePage();
    backMarketHomePage.get();

    backMarketHomePage.closeCookiePopup().signIn()
        .openHomePage()
        .signIn()
        .openShoppingCart();
  }
}
