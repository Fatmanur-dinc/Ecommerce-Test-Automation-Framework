Feature: User Registration

  Scenario: Successful registration with valid credentials
    Given user is on the demoblaze sign up page
    When user enters signup username "newuser99999" and password "test123"
    And user confirms signup
    Then registration should be successful

  Scenario: Registration with existing username
    Given user is on the demoblaze sign up page
    When user enters signup username "Florida42" and password "miami123"
    And user confirms signup
    Then user should see already registered message

  Scenario: Registration with empty fields
    Given user is on the demoblaze sign up page
    When user enters signup username "" and password ""
    And user confirms signup
    Then registration should not proceed