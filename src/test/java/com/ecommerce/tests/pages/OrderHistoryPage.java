package com.ecommerce.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderHistoryPage {

    WebDriver driver;
    WebDriverWait wait;

    By loginNavButton = By.id("login2");
    By usernameField = By.id("loginusername");
    By passwordField = By.id("loginpassword");
    By loginSubmitButton = By.cssSelector("button[onclick='logIn()']");
    By welcomeUser = By.id("nameofuser");
    By cartLink = By.id("cartur");
    By cartTitle = By.cssSelector("#page-wrapper h2");

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void login(String username, String password) throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(loginNavButton).click();
        Thread.sleep(1500);
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginSubmitButton).click();
        Thread.sleep(2000);
    }

    public boolean isLoggedIn() {
        try {
            return wait.until(ExpectedConditions
                            .visibilityOfElementLocated(welcomeUser))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void goToCart() throws InterruptedException {
        driver.findElement(cartLink).click();
        Thread.sleep(2000);
    }

    public boolean isCartPageDisplayed() {
        try {
            return driver.findElement(cartTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}