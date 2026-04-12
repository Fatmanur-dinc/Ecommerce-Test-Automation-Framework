package com.ecommerce.tests.stepdefs;

import com.ecommerce.tests.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenLoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://the-internet.herokuapp.com/login");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"tomsmith", "SuperSecretPassword!", true},
                {"tomsmith", "wrongpassword", false},
                {"wronguser", "SuperSecretPassword!", false},
                {"", "", false}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, boolean shouldPass) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        if (shouldPass) {
            Assert.assertTrue(
                    loginPage.getCurrentUrl().contains("secure"),
                    "Login başarılı olmalıydı ama olmadı: " + username
            );
        } else {
            Assert.assertFalse(
                    loginPage.getCurrentUrl().contains("secure"),
                    "Login başarısız olmalıydı ama geçti: " + username
            );
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}