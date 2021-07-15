package google;

import base.TestBase;
import org.testng.annotations.Test;
import pageobjects.pages.google.HomePage;

/**
 * Example of a test with google.
 */
@Test(groups = {"full-regression"})
public class GoogleTests extends TestBase {

  @Test(
      description = "Example google test",
      enabled = true,
      retryAnalyzer = TestBase.RetryAnalyzer.class
  )
  public void exampleGoogle() {
    // Arrange
    final String query = "Cheese";

    HomePage googleHomePage = new HomePage();
    googleHomePage.get();

    googleHomePage.acceptCookies();
  }
}
