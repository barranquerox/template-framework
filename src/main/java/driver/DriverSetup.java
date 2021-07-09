package driver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface DriverSetup {
  RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities);

  DesiredCapabilities getBrowserCapabilities(DesiredCapabilities capabilities);
}
