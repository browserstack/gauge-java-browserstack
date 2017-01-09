package com.browserstack.gauge.pages;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.thoughtworks.gauge.Step;

public class SearchSpec {
    private final WebDriver driver;

    public SearchSpec() {
        this.driver = DriverFactory.getDriver();
    }

    @Step("On the homepage")
    public void navigateToHomePage() {
        driver.get(HomePage.Url);
    }

    @Step("Search for term <term>")
    public void searchFor(String term) {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.searchFor(term);
    }

    @Step("Make sure the first URL is <URL>")
    public void checkFirstURL(String URL) {
        ResultsPage resultsPage = PageFactory.initElements(driver, ResultsPage.class);
        assertEquals(URL, resultsPage.getFirstResultLink());
    }
}
