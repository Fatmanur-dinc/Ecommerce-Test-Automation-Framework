package com.ecommerce.tests.stepdefs;

import com.ecommerce.tests.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://the-internet.herokuapp.com/login");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                // username, password, shouldPass
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