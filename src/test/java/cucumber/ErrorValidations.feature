Feature: Error validation



@Errorvalidation
  Scenario Outline: Error message
    Given I landed on Ecommerce Page
    And Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed
    Examples:
      |name                 |password   |
      |gnidan12345@gmail.com|Qwerty123456|
