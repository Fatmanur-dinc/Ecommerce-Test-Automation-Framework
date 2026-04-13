package com.ecommerce.tests.stepdefs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class SecurityValidationTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testLoginPageAccessible() {
        driver.get("https://the-internet.herokuapp.com/login");
        Assert.assertTrue(driver.getTitle().contains("The Internet"),
                "Login sayfası erişilebilir olmalı");
    }

    @Test
    public void testSecurePageRedirectsWithoutLogin() {
        driver.get("https://the-internet.herokuapp.com/secure");
        wait.until(ExpectedConditions.urlContains("login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"),
                "Giriş yapılmadan secure sayfasına erişim engellenmeli");
    }

    @Test
    public void testLoginWithValidCredentials() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(1000);
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("secure"));
        Assert.assertTrue(driver.getCurrentUrl().contains("secure"),
                "Geçerli credentials ile giriş başarılı olmalı");
    }

    @Test
    public void testLogoutRedirectsToLogin() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(1000);
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("secure"));

        driver.findElement(By.cssSelector("a.button")).click();
        wait.until(ExpectedConditions.urlContains("login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"),
                "Logout sonrası login sayfasına yönlendirilmeli");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}