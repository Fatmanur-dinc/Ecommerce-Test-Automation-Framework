package com.ecommerce.tests.stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PerformanceTest {

    WebDriver driver;

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
    }

    @Test
    public void testHomePageLoadTime() {
        long startTime = System.currentTimeMillis();
        driver.get("https://www.demoblaze.com");
        long endTime = System.currentTimeMillis();
        long loadTime = endTime - startTime;

        System.out.println("Home page load time: " + loadTime + " ms");
        Assert.assertTrue(loadTime < 10000, "Home page should load within 10 seconds. Actual: " + loadTime + " ms");
    }

    @Test
    public void testLoginPageLoadTime() {
        long startTime = System.currentTimeMillis();
        driver.get("https://the-internet.herokuapp.com/login");
        long endTime = System.currentTimeMillis();
        long loadTime = endTime - startTime;

        System.out.println("Login page load time: " + loadTime + " ms");
        Assert.assertTrue(loadTime < 10000, "Login page should load within 10 seconds. Actual: " + loadTime + " ms");
    }

    @Test
    public void testCartPageLoadTime() {
        driver.get("https://www.demoblaze.com");
        long startTime = System.currentTimeMillis();
        driver.get("https://www.demoblaze.com/cart.html");
        long endTime = System.currentTimeMillis();
        long loadTime = endTime - startTime;

        System.out.println("Cart page load time: " + loadTime + " ms");
        Assert.assertTrue(loadTime < 10000, "Cart page should load within 10 seconds. Actual: " + loadTime + " ms");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}