package com.ecommerce.tests.stepdefs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class FirefoxCompatibilityTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        if (System.getenv("CI") != null) {
            options.addArguments("--headless");
        }
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(groups = {"regression"})
    public void testHomePageLoadsOnFirefox() {
        driver.get("https://www.demoblaze.com");
        Assert.assertTrue(driver.getTitle().contains("STORE"),
                "Home page should load on Firefox");
    }

    @Test(groups = {"regression"})
    public void testLoginOnFirefox() {
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.urlContains("secure"));
        Assert.assertTrue(driver.getCurrentUrl().contains("secure"),
                "Login should work on Firefox");
    }

    @Test(groups = {"regression"})
    public void testProductPageLoadsOnFirefox() throws InterruptedException {
        driver.get("https://www.demoblaze.com");
        Thread.sleep(3000);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Phones"))).click();

        Thread.sleep(3000);
        Assert.assertTrue(
                !driver.findElements(By.className("card-title")).isEmpty(),
                "Product list should be visible on Firefox");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}