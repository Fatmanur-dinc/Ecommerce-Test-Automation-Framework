# Ecommerce Test Automation Framework

This repository features a robust, scalable, and highly maintainable web test automation framework tailored for comprehensive e-commerce platforms. Built with **Java**, **Selenium WebDriver**, and **TestNG**, the framework implements the industry-standard **Page Object Model (POM)** architectural pattern to deliver clean, independent, and reusable test scripts.

---

## 🚀 Key Features

* **Page Object Model (POM):** Complete separation of UI locators and test logic to minimize code duplication and simplify maintenance.
* **Structured Test Suites:** Configured via custom XML suites (`testng.xml`) for organized test execution and prioritization.
* **Dynamic Locator Strategies:** Flexible and reliable element identification to withstand dynamic front-end structural changes.
* **Automated Error Capturing:** Includes robust assertions paired with an automated screenshot mechanism for failed test steps to facilitate quick debugging.
* **Clean Architecture:** Powered by **Maven** for efficient dependency management and standardized project layouts.

---

## 🛠️ Tech Stack & Dependencies

* **Language:** Java 17+
* **Automation Tool:** Selenium WebDriver (v4.x)
* **Testing Framework:** TestNG
* **Build Tool:** Maven

---

## 📁 Project Structure

```text
src/
├── main/java/
│   └── pages/               # Page classes holding elements and page-specific actions
└── test/java/
    └── tests/               # Test cases leveraging page components
testng.xml                   # Suite configuration file for test execution
pom.xml                      # Maven dependencies and build settings
