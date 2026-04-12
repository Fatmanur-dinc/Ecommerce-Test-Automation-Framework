package com.ecommerce.tests.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.ecommerce.tests.stepdefs",
        plugin = {"pretty", "html:target/cucumber-reports/report.html"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}