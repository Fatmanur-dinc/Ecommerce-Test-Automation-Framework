Feature: Shopping Cart

  Scenario: Add a product to cart
    Given user is on the demoblaze home page
    When user clicks on a product
    And user adds the product to cart
    Then cart should contain the product

  Scenario: Verify cart after adding product
    Given user is on the demoblaze home page
    When user clicks on a product
    And user adds the product to cart
    Then user navigates to cart page
    And cart should not be empty