Feature: User Login

  Scenario: Valid login with correct credentials
    Given user is on the login page
    When user enters username "tomsmith" and password "SuperSecretPassword!"
    Then user should be redirected to the dashboard

  Scenario: Invalid login with wrong password
    Given user is on the login page
    When user enters username "tomsmith" and password "wrongpass"
    Then user should see error message "Your password is invalid!"

  Scenario: Login with empty fields
    Given user is on the login page
    When user enters username "" and password ""
    Then user should see error message "Your username is invalid!"