package com.ecommerce.tests.stepdefs;

import com.ecommerce.tests.pages.CheckoutPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class CheckoutSteps {

    WebDriver driver;
    CheckoutPage checkoutPage;

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
        checkoutPage = new CheckoutPage(driver);
    }

    @Given("user has a product in cart")
    public void user_has_product_in_cart() throws InterruptedException {
        checkoutPage.navigateTo("https://www.demoblaze.com");
        checkoutPage.addProductToCart();
        checkoutPage.goToCart();
    }

    @When("user proceeds to checkout")
    public void user_proceeds_to_checkout() throws InterruptedException {
        checkoutPage.clickPlaceOrder();
    }

    @When("user fills name {string} country {string} city {string} card {string} month {string} year {string}")
    public void user_fills_order_form(String name, String country, String city,
                                      String card, String month, String year) {
        checkoutPage.fillOrderForm(name, country, city, card, month, year);
    }

    @When("user confirms the order")
    public void user_confirms_order() throws InterruptedException {
        checkoutPage.confirmPurchase();
    }

    @Then("order confirmation should be displayed")
    public void order_confirmation_displayed() {
        Assert.assertTrue(checkoutPage.isOrderConfirmed());
    }

    @When("user confirms the order without filling details")
    public void user_confirms_without_details() throws InterruptedException {
        checkoutPage.confirmPurchase();
    }

    @Then("order should not be placed")
    public void order_should_not_be_placed() {
        Assert.assertFalse(checkoutPage.isOrderConfirmed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}