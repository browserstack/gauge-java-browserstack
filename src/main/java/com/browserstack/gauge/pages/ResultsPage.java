package com.browserstack.gauge.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ResultsPage extends BasePage {

    @FindBy(how = How.CSS, using = "div.g:nth-of-type(1) h3.r a")
    private WebElement firstResult;

    public String getFirstResultLink() {
        return firstResult.getAttribute("href");
    }
}
