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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;

public class BoundaryValueTest {

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

    @DataProvider(name = "usernameBoundary")
    public Object[][] usernameBoundaryData() {
        return new Object[][] {
                {"", "SuperSecretPassword!", false},
                {"t", "SuperSecretPassword!", false},
                {"tomsmith", "SuperSecretPassword!", true},
                {"tomsmithtomsmithtomsmithtomsmithtomsmith", "SuperSecretPassword!", false}
        };
    }

    @Test(dataProvider = "usernameBoundary", groups = {"regression"})
    public void testUsernameBoundaryValues(String username, String password, boolean shouldPass) throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        if (shouldPass) {
            wait.until(ExpectedConditions.urlContains("secure"));
            Assert.assertTrue(driver.getCurrentUrl().contains("secure"),
                    "Login başarılı olmalıydı: " + username);
        } else {
            Assert.assertFalse(driver.getCurrentUrl().contains("secure"),
                    "Login başarısız olmalıydı: " + username);
        }
    }

    @DataProvider(name = "passwordBoundary")
    public Object[][] passwordBoundaryData() {
        return new Object[][] {
                {"tomsmith", "", false},
                {"tomsmith", "a", false},
                {"tomsmith", "SuperSecretPassword!", true},
                {"tomsmith", "wrongpassword", false}
        };
    }

    @Test(dataProvider = "passwordBoundary", groups = {"regression"})
    public void testPasswordBoundaryValues(String username, String password, boolean shouldPass) throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        if (shouldPass) {
            wait.until(ExpectedConditions.urlContains("secure"));
            Assert.assertTrue(driver.getCurrentUrl().contains("secure"),
                    "Login başarılı olmalıydı: " + password);
        } else {
            Assert.assertFalse(driver.getCurrentUrl().contains("secure"),
                    "Login başarısız olmalıydı: " + password);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}