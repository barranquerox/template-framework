package environment;

import static com.google.common.io.Files.asByteSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.util.Strings;
import org.yaml.snakeyaml.Yaml;

/**
 * Environment Config.
 */
public final class EnvironmentConfig {

  /**
   * Logger.
   */
  private static final Logger logger = LogManager.getLogger(EnvironmentConfig.class);

  private static final String PREPROD = "preprod";
  private static final String PROD = "prod";

  private static Map<String, Object> configMap;

  private static String selectedDomain;
  private static String testingEnvironment;

  private static final String US_DOMAIN = "en_EN";
  private static final String DEFAULT_MESSAGE = "The domain {} doesn't exist, defaulting to {}";

  /**
   * Private constructor.
   */
  private EnvironmentConfig() {

  }

  /**
   * Initializes the environment selected for testing: PROD or PREPROD, domains, baseURL.
   */
  public static void initializeEnvironment() {

    // if no -Denvironment is passed, the default is preprod
    testingEnvironment = System.getProperty("environment", PREPROD).toLowerCase();

    // in case of an unknown environment (only prod or preprod supported for now)
    if (!testingEnvironment.equals(PROD) && !testingEnvironment.equals(PREPROD)) {
      logger.info("Testing environment '{}' not known, selecting PROD by default",
          testingEnvironment);
      testingEnvironment = PROD;
    }

    String environmentConfig = testingEnvironment + ".yaml";

    configMap = loadConfigFile(environmentConfig);

    // if a domain is not passed as argument -Ddomain, then defaults to en_EN
    selectedDomain = System.getProperty("domain", US_DOMAIN);
  }

  /**
   * Load the settings of the config file defined in /config.
   *
   * @param filename the name of the config file to be opened
   * @return The settings of the config file in a Key/Value Map
   */
  private static Map<String, Object> loadConfigFile(String filename) {
    String path = "config/" + filename;
    logger.debug("Config file: {}", path);

    File initialFile = new File(path);
    try (InputStream targetStream = asByteSource(initialFile).openStream()) {
      Yaml yaml = new Yaml();
      Map<String, Object> map = yaml.load(targetStream);
      logger.debug("Content of config file: {}", map);
      return map;
    } catch (IOException e) {
      logger.error("Problem when opening the config file", e);
      throw new IllegalArgumentException();
    }
  }

  /**
   * Gets the base url of the SUT.
   *
   * @return the base url of the SUT
   */
  public static String getBaseUrl() {

    // if a base URL is passed as argument -DbaseUrl it will take priority over domain
    // and environment
    String baseUrlFromCommandLine = System.getProperty("baseUrl");

    if (!Strings.isNullOrEmpty(baseUrlFromCommandLine)) {
      try {
        // validates that the WNG base url is well formatted
        URL baseUrl = new URL(baseUrlFromCommandLine);
        logger.info("Base url passed as argument: {}", baseUrl);
        return baseUrl.toString();
      } catch (MalformedURLException e) {
        logger.debug("The base URL is not correct.", e);
        logger.debug("Defaulting to the selected domain");
      }
    }

    // if the domain passed as an argument -Ddomain doesn't exist, default to US
    if (!configMap.containsKey(selectedDomain)) {
      logger.debug(DEFAULT_MESSAGE, selectedDomain, US_DOMAIN);
      selectedDomain = US_DOMAIN;
    }

    logger.info("Base url: {}", configMap.get(selectedDomain));
    return configMap.get(selectedDomain).toString();
  }

  /**
   * Gets the web next gen base url.
   *
   * @param domain the domain to get the base url
   * @return the web next gen base url to be used by the tests
   */
  public static String getBaseUrl(String domain) {
    logger.debug("Get base url on domain {}", domain);
    // if the domain passed as an argument doesn't exist, default to US
    if (!configMap.containsKey(domain)) {
      logger.debug(DEFAULT_MESSAGE, domain, US_DOMAIN);
      domain = US_DOMAIN;
    }

    logger.info("WebNextGen base url: {}", configMap.get(domain));
    return configMap.get(domain).toString();
  }

  public static String getGoogleUrl() {
    return configMap.get("google").toString();
  }

  public static String getBackMarketUrl() {
    return configMap.get("backmarket").toString();
  }

  public static String getTestingEnvironment() {
    return testingEnvironment;
  }
}
