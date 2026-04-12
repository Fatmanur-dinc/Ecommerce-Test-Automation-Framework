package com.ecommerce.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    By firstProduct = By.cssSelector(".card-title a");
    By addToCartButton = By.linkText("Add to cart");
    By cartLink = By.id("cartur");
    By cartItems = By.cssSelector("#tbodyid tr");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void clickFirstProduct() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }

    public void addToCart() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        Thread.sleep(3000);
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // alert gelmezse devam et
        }
    }

    public void goToCart() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
        Thread.sleep(3000);
    }

    public boolean isCartEmpty() {
        try {
            Thread.sleep(2000);
            List<WebElement> items = driver.findElements(cartItems);
            return items.isEmpty();
        } catch (Exception e) {
            return true;
        }
    }
}