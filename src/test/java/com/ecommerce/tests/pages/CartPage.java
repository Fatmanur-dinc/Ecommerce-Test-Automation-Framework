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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void clickFirstProduct() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(firstProduct).click();
    }

    public void addToCart() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(addToCartButton).click();
        Thread.sleep(2000);
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // alert gelmezse devam et
        }
    }

    public void goToCart() throws InterruptedException {
        driver.findElement(cartLink).click();
        Thread.sleep(2000);
    }

    public boolean isCartEmpty() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.isEmpty();
    }
}