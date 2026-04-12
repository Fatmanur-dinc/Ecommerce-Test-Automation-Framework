package com.ecommerce.tests.stepdefs;

import com.ecommerce.tests.pages.CartPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class CartSteps {

    WebDriver driver;
    CartPage cartPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        cartPage = new CartPage(driver);
    }

    @Given("user is on the demoblaze home page")
    public void user_is_on_demoblaze_home_page() {
        cartPage.navigateTo("https://www.demoblaze.com");
    }

    @When("user clicks on a product")
    public void user_clicks_on_product() throws InterruptedException {
        cartPage.clickFirstProduct();
    }

    @When("user adds the product to cart")
    public void user_adds_product_to_cart() throws InterruptedException {
        cartPage.addToCart();
    }

    @Then("cart should contain the product")
    public void cart_should_contain_product() throws InterruptedException {
        cartPage.goToCart();
        Assert.assertFalse(cartPage.isCartEmpty());
    }

    @Then("user navigates to cart page")
    public void user_navigates_to_cart_page() throws InterruptedException {
        cartPage.goToCart();
    }

    @Then("cart should not be empty")
    public void cart_should_not_be_empty() {
        Assert.assertFalse(cartPage.isCartEmpty());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}