package com.browserstack.gauge.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.browserstack.local.Local;
import com.thoughtworks.gauge.*;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SearchSpec {
    HomePage homePage;
    private static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    private static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
    private WebDriver driver;
    private static Local local;

    @BeforeSuite
    public void beforeSuite() throws Exception {
        try {
            if (!(System.getenv("LOCAL").isEmpty()) && System.getenv("LOCAL").equalsIgnoreCase("true")) {
                local = new Local();
                Map<String, String> options = new HashMap<String, String>();
                options.put("key", AUTOMATE_KEY);
                local.start(options);
            }
        } catch (Exception e) {
            System.out.println("Error while start local - " + e);
        }
    }
    @BeforeSpec
    public void setUp() throws Exception {
        try {
            MutableCapabilities caps = new MutableCapabilities();
            HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();

            if (!(System.getenv("LOCAL").isEmpty()) && System.getenv("LOCAL").equalsIgnoreCase("true")) {
                if (local != null && local.isRunning()) {
                    browserstackOptions.put("local", "true");
                }
            }

            // Capabilities from environment
            if(System.getenv("DEVICE") !=  null){
                caps.setCapability("browserName", System.getenv("BROWSERNAME"));
                caps.setCapability("os", System.getenv("PLATFORM"));
                caps.setCapability("deviceName", System.getenv("DEVICE"));
            }
            else {
                caps.setCapability("browserName", System.getenv("BROWSERNAME"));
                caps.setCapability("browserVersion", System.getenv("BROWSER_VERSION"));

                browserstackOptions.put("os", System.getenv("OS"));
                browserstackOptions.put("osVersion", System.getenv("OS_VERSION"));
            }
            browserstackOptions.put("buildName", "browserstack-build-1");
            browserstackOptions.put("sessionName", "BStack Sample Gauge");
            browserstackOptions.put("source", "gauge:sample-master:v1.0");
            caps.setCapability("bstack:options", browserstackOptions);

            java.net.URL remoteURL = new URL(URL);
            driver = new RemoteWebDriver(remoteURL, caps);
            homePage = new HomePage(driver);

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Step("I am on the website <url>")
    public void I_am_on_the_website(String url) throws Throwable {
        driver.get(url);
        Thread.sleep(2000);
    }

    @Step("I select a product and click on 'Add to cart' button")
    public void I_select_a_product_and_add_to_cart() throws Throwable {
        homePage.selectFirstProductName();
        homePage.clickAddToCartButton();
        Thread.sleep(2000);
    }

    @Step("the product should be added to cart")
    public void product_should_be_added_to_cart() {
        homePage.waitForCartToOpen();
        assertEquals(homePage.getSelectedProductName(), homePage.getProductCartText(driver));
    }

    @Step("the page should contain <expectedTitle>")
    public void page_should_contain(String expectedTitle) {
        assertTrue(driver.getPageSource().contains(expectedTitle));
    }

    @AfterSpec
    public void tearDown() {
        driver.quit();
    }
    @AfterSuite
    public void afterSuite() throws Exception {
        if(local!= null && local.isRunning()){
            local.stop();
        }
    }
}
