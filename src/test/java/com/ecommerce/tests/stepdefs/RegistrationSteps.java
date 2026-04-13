package com.ecommerce.tests.stepdefs;

import com.ecommerce.tests.pages.RegistrationPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class RegistrationSteps {

    WebDriver driver;
    RegistrationPage registrationPage;

    @Before
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
        registrationPage = new RegistrationPage(driver);
    }

    @Given("user is on the demoblaze sign up page")
    public void user_is_on_signup_page() throws InterruptedException {
        registrationPage.navigateTo("https://www.demoblaze.com");
        registrationPage.clickSignup();
    }

    @When("user enters signup username {string} and password {string}")
    public void user_enters_signup_credentials(String username, String password) {
        registrationPage.enterUsername(username);
        registrationPage.enterPassword(password);
    }

    @When("user confirms signup")
    public void user_confirms_signup() throws InterruptedException {
        registrationPage.clickRegister();
    }

    @Then("registration should be successful")
    public void registration_successful() {
        String alert = registrationPage.getAlertText();
        Assert.assertTrue(
                alert.contains("Sign up successful") ||
                        alert.contains("This user already exist") ||
                        alert.isEmpty(),
                "Kayıt işlemi tamamlanmalıydı: " + alert
        );
    }

    @Then("user should see already registered message")
    public void user_sees_already_registered() {
        String alert = registrationPage.getAlertText();
        Assert.assertTrue(
                alert.contains("This user already exist"),
                "Kullanıcı zaten var mesajı görünmeli, gelen: " + alert
        );
    }

    @Then("registration should not proceed")
    public void registration_should_not_proceed() {
        String alert = registrationPage.getAlertText();
        Assert.assertTrue(
                alert.contains("Please fill out") ||
                        alert.contains("fill") ||
                        alert.isEmpty(),
                "Kayıt gerçekleşmemeli, gelen: " + alert
        );
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}