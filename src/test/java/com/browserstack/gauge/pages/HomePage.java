package com.browserstack.gauge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private String selectedProductName;

    private final By firstProductName = By.xpath("//*[@id=\"1\"]/p");

    private final By firstProductAddToCartButton = By.xpath("//*[@id=\"1\"]/div[4]");

    private final By cartPane = By.cssSelector(".float\\-cart__content");

    private final By productCartText = By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]");

    public void selectFirstProductName() {
        String firstProduct = driver.findElement(firstProductName).getText();
        setSelectedProductName(firstProduct);
    }

    public void clickAddToCartButton() {
        driver.findElement(firstProductAddToCartButton).click();
    }

    public void waitForCartToOpen() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartPane));
    }

    public String getProductCartText(WebDriver driver) {
        return driver.findElement(productCartText).getText();
    }

    public void setSelectedProductName(String selectedProductName) {
        this.selectedProductName = selectedProductName;
    }

    public String getSelectedProductName() {
        return selectedProductName;
    }
}
