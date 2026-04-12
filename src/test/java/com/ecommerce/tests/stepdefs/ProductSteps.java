package com.ecommerce.tests.stepdefs;

import com.ecommerce.tests.pages.ProductPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class ProductSteps {

    WebDriver driver;
    ProductPage productPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        productPage = new ProductPage(driver);
    }

    @Given("user is on the home page")
    public void user_is_on_home_page() {
        productPage.navigateTo("https://www.demoblaze.com");
    }

    @When("user selects category {string}")
    public void user_selects_category(String category) throws InterruptedException {
        productPage.selectCategory(category);
    }

    @Then("product list should be visible")
    public void product_list_visible() {
        Assert.assertTrue(productPage.isProductListVisible());
    }

    @Then("at least one product should be listed")
    public void at_least_one_product_listed() {
        Assert.assertTrue(productPage.getProductCount() > 0);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}