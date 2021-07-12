#Author: Vignesh Parameswari
#Summary : Order a TShirt, verify the order , select the payment and verify the order placed and the order history

Feature: Order T-Shirt

  @scenario1 @smoke
  Scenario Outline: Order T-Shirt and Verify in Order History
    Given I login to the shopping site
    When I order '<productName>' TShirt of quantity '<quantity>'
    Then I verify the order history
    And I logout of the shopping site
  Examples:
  |productName|quantity|
  |Faded Short Sleeve T-shirts|1|
  

  @scenario1 @demo
  Scenario: Launch and Close browser demo
    Given I login to the google site
    Then I close the browser
    