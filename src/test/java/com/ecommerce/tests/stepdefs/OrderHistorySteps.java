package com.ecommerce.tests.stepdefs;

import com.ecommerce.tests.pages.OrderHistoryPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class OrderHistorySteps {

    WebDriver driver;
    OrderHistoryPage orderHistoryPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        orderHistoryPage = new OrderHistoryPage(driver);
    }

    @Given("user logs into demoblaze account")
    public void user_logs_into_demoblaze() throws InterruptedException {
        orderHistoryPage.navigateTo("https://www.demoblaze.com");
        orderHistoryPage.login("Florida42", "miami123");
    }

    @Then("user should see the home page after login")
    public void user_sees_home_page_after_login() {
        Assert.assertTrue(orderHistoryPage.isLoggedIn());
    }

    @When("user navigates to cart")
    public void user_navigates_to_cart() throws InterruptedException {
        orderHistoryPage.goToCart();
    }

    @Then("cart page should be displayed")
    public void cart_page_should_be_displayed() {
        Assert.assertTrue(orderHistoryPage.isCartPageDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}