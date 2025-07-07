Feature: Purchase the order from Ecommerce Website

  //pre-condition
  Background:
    Given I landed on Ecommerce Page

@Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And  Checkout <productName> and submit the order
    Then "Thank you for the order." message is displayed on ConfirmationPage
    Examples:
    |name                 |password   |productName|
    |gnidan12345@gmail.com|Qwerty12345|ZARA COAT 3|
