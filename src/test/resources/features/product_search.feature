Feature: Product Catalog

  Scenario: Browse phones category
    Given user is on the home page
    When user selects category "Phones"
    Then product list should be visible

  Scenario: Browse laptops category
    Given user is on the home page
    When user selects category "Laptops"
    Then product list should be visible

  Scenario: Verify product count in phones
    Given user is on the home page
    When user selects category "Phones"
    Then at least one product should be listed