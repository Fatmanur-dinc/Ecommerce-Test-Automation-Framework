Feature: Order History

  Scenario: View order history after login
    Given user logs into demoblaze account
    Then user should see the home page after login

  Scenario: Verify navigation to cart from order history
    Given user logs into demoblaze account
    When user navigates to cart
    Then cart page should be displayed