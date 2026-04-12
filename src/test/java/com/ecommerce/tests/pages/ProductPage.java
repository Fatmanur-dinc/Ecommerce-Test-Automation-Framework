package com.ecommerce.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductPage {

    WebDriver driver;

    By phonesCategory = By.linkText("Phones");
    By laptopsCategory = By.linkText("Laptops");
    By productItems = By.className("card-title");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void selectCategory(String category) throws InterruptedException {
        if (category.equals("Phones")) {
            driver.findElement(phonesCategory).click();
        } else if (category.equals("Laptops")) {
            driver.findElement(laptopsCategory).click();
        }
        Thread.sleep(2000);
    }

    public int getProductCount() {
        List<WebElement> products = driver.findElements(productItems);
        return products.size();
    }

    public boolean isProductListVisible() {
        List<WebElement> products = driver.findElements(productItems);
        return products.size() > 0;
    }
}