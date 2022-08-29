package com.browserstack.gauge.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SearchSpec {
    HomePage homePage;
    private static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    private static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    private WebDriver driver;

    @BeforeSpec
    public void setUp() {
        try {
            MutableCapabilities caps = new MutableCapabilities();
            HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();

            // Capabilities from environment
            if(System.getenv("DEVICE") !=  null){
                caps.setCapability("browserName", System.getenv("BROWSERNAME"));
                caps.setCapability("platform", System.getenv("PLATFORM"));
                caps.setCapability("deviceName", System.getenv("DEVICE"));
            }
            else {
                caps.setCapability("browserName", System.getenv("BROWSERNAME"));
                caps.setCapability("browserVersion", System.getenv("BROWSER_VERSION"));

                browserstackOptions.put("os", System.getenv("OS"));
                browserstackOptions.put("osVersion", System.getenv("OS_VERSION"));
            }
            browserstackOptions.put("buildName", "test-gauge");
            caps.setCapability("bstack:options", browserstackOptions);
            System.out.println("Inside Setup");

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
        assertTrue(driver.getTitle().equalsIgnoreCase(expectedTitle));
    }

    @AfterSpec
    public void tearDown() {
        driver.quit();
    }
}
