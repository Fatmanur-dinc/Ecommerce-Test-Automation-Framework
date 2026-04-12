Feature: Checkout and Payment

  Scenario: Successful checkout with valid details
    Given user has a product in cart
    When user proceeds to checkout
    And user fills name "John Doe" country "United States" city "New York" card "1234567890123456" month "12" year "2026"
    And user confirms the order
    Then order confirmation should be displayed

  Scenario: Checkout with empty form fields
    Given user has a product in cart
    When user proceeds to checkout
    And user confirms the order without filling details
    Then order should not be placed