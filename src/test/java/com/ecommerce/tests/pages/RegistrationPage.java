package com.ecommerce.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {

    WebDriver driver;
    WebDriverWait wait;

    By signupButton = By.id("signin2");
    By usernameField = By.id("sign-username");
    By passwordField = By.id("sign-password");
    By registerButton = By.cssSelector("button[onclick='register()']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void clickSignup() throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(signupButton).click();
        Thread.sleep(1500);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegister() throws InterruptedException {
        driver.findElement(registerButton).click();
        Thread.sleep(2000);
    }

    public boolean isAlertPresent() {
        try {
            String alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            return alertText != null;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAlertText() {
        try {
            String alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            return alertText;
        } catch (Exception e) {
            return "";
        }
    }
}