package google;

import base.TestBase;
import org.testng.annotations.Test;
import pageobjects.pages.GoogleHomePage;

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

    GoogleHomePage googleHomePage = new GoogleHomePage();
    googleHomePage.get();

    googleHomePage.acceptCookies().search(query);
  }
}
