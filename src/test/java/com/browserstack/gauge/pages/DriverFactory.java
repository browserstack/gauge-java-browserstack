package com.browserstack.gauge.pages;

import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    private static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    private static String envOr(String key, String fallback) {
        String value = System.getenv(key);
        return (value != null && !value.isEmpty()) ? value : fallback;
    }

    @BeforeSpec
    public void setUp() {
        try {
            // Selenium 4 enforces W3C. BrowserStack vendor capabilities must be
            // nested under the "bstack:options" map rather than set as flat keys.
            MutableCapabilities caps = new MutableCapabilities();
            Map<String, Object> bstackOptions = new HashMap<>();

            if (System.getenv("DEVICE") != null) {
                caps.setCapability("browserName", System.getenv("BROWSERNAME"));
                bstackOptions.put("deviceName", System.getenv("DEVICE"));
                bstackOptions.put("osVersion", System.getenv("PLATFORM"));
                bstackOptions.put("realMobile", "true");
            } else {
                caps.setCapability("browserName", envOr("BROWSER", "Chrome"));
                bstackOptions.put("browserVersion", envOr("BROWSER_VERSION", "latest"));
                bstackOptions.put("os", envOr("OS", "Windows"));
                bstackOptions.put("osVersion", envOr("OS_VERSION", "11"));
            }

            bstackOptions.put("buildName", envOr("BROWSERSTACK_BUILD_NAME", "browserstack build"));
            bstackOptions.put("sessionName", "BStack Sample Gauge");
            bstackOptions.put("debug", "true");
            caps.setCapability("bstack:options", bstackOptions);

            URL remoteURL = new URL(URL);

            driver = new RemoteWebDriver(remoteURL, caps);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        } catch (MalformedURLException e) {

            System.out.println(e.toString());

        }
    }

    @AfterSpec
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
