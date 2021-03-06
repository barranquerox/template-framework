package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Test Account Provider.
 */
public final class TestAccountProvider {
  private static List<TestAccount> testAccountsList = new ArrayList<>();
  private static final SecureRandom random = new SecureRandom();
  private static final Logger logger = LogManager.getLogger(TestAccountProvider.class);

  private TestAccountProvider() {

  }

  static {
    try {
      // create object mapper instance
      ObjectMapper mapper = new ObjectMapper();

      // convert JSON array to list of accounts
      testAccountsList = Arrays
          .asList(mapper.readValue(Paths.get("test-data/testAccounts.json").toFile(),
              TestAccount[].class));

    } catch (Exception e) {
      logger.error(e);
    }
  }

  /**
   * Get a random TestAccount from the testAccountsList.
   *
   * @return TestAccount object.
   */
  public static TestAccount getRandomAccount() {
    logger.info("Selecting a random account from the list.");
    TestAccount account = testAccountsList.get(random.nextInt(testAccountsList.size()));
    String email = account.email();
    String password = account.password();
    String firstName = account.firstName();
    String lastName = account.lastName();
    logger.debug("Test account email: {}", email);
    logger.debug("Test account password: {}", password);
    logger.debug("Test account first name: {}", firstName);
    logger.debug("Test account last name: {}", lastName);
    return account;
  }
}
