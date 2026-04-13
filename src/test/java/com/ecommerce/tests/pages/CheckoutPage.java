package com.ecommerce.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    By firstProduct = By.cssSelector(".card-title a");
    By addToCartButton = By.linkText("Add to cart");
    By cartLink = By.id("cartur");
    By placeOrderButton = By.cssSelector("button[data-target='#orderModal']");
    By nameField = By.id("name");
    By countryField = By.id("country");
    By cityField = By.id("city");
    By cardField = By.id("card");
    By monthField = By.id("month");
    By yearField = By.id("year");
    By purchaseButton = By.cssSelector("button[onclick='purchaseOrder()']");
    By confirmationText = By.cssSelector(".sweet-alert h2");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void addProductToCart() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        Thread.sleep(2000);
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {

        }
    }

    public void goToCart() throws InterruptedException {
        driver.findElement(cartLink).click();
        Thread.sleep(2000);
    }

    public void clickPlaceOrder() throws InterruptedException {
        driver.findElement(placeOrderButton).click();
        Thread.sleep(1500);
    }

    public void fillOrderForm(String name, String country, String city,
                              String card, String month, String year) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(countryField).sendKeys(country);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(cardField).sendKeys(card);
        driver.findElement(monthField).sendKeys(month);
        driver.findElement(yearField).sendKeys(year);
    }

    public void confirmPurchase() throws InterruptedException {
        driver.findElement(purchaseButton).click();
        Thread.sleep(2000);
    }

    public boolean isOrderConfirmed() {
        try {
            WebElement confirmation = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(confirmationText)
            );
            return confirmation.getText().contains("Thank you");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOrderFormVisible() {
        try {
            return driver.findElement(nameField).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}