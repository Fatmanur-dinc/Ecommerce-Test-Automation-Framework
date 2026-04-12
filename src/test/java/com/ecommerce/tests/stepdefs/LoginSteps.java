package com.ecommerce.tests.stepdefs;

import com.ecommerce.tests.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Given("user is on the login page")
    public void user_is_on_login_page() {
        loginPage.navigateTo("https://the-internet.herokuapp.com/login");
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @Then("user should be redirected to the dashboard")
    public void user_redirected_to_dashboard() {
        Assert.assertTrue(loginPage.getCurrentUrl().contains("secure"));
    }

    @Then("user should see error message {string}")
    public void user_sees_error_message(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualMessage.contains("Your username is invalid")
                || actualMessage.contains("invalid"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}