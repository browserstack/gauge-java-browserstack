package com.browserstack.gauge.pages;

import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    private static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSpec
    public void setUp() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            // Capabilities from environment
            if(System.getenv("DEVICE") !=  null){
                caps.setCapability("browserName", System.getenv("BROWSERNAME"));
                caps.setCapability("platform", System.getenv("PLATFORM"));
                caps.setCapability("device", System.getenv("DEVICE"));
            }
            else {
                caps.setCapability("browser", System.getenv("BROWSER"));
                caps.setCapability("browser_version", System.getenv("BROWSER_VERSION"));
                
                caps.setCapability("os", System.getenv("OS"));
                caps.setCapability("os_version", System.getenv("OS_VERSION"));
            }

            // Hardcoded capabilities
            caps.setCapability("build", "FirstGaugeBuild");
            caps.setCapability("browserstack.debug", "true");

            URL remoteURL = new URL(URL);

            driver = new RemoteWebDriver(remoteURL, caps);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        } catch (MalformedURLException e) {

            System.out.println(e.toString());

        }
    }

    @AfterSpec
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
