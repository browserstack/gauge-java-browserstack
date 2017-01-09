package com.browserstack.gauge.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {

    @FindBy(how = How.NAME, using = "q")
    private WebElement searchBox;

    @FindBy(how = How.NAME, using = "btnK")
    private WebElement buttonSubmit;

    @FindBy(how = How.NAME, using = "btnI")
    private WebElement buttonLucky;

    public void searchFor(String term) {
        searchBox.sendKeys(term);
        searchBox.submit();
    }
}
