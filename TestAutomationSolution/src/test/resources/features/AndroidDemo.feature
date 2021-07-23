#Author: Vignesh Parameswari
#Summary : Android sample scenario

Feature: Android Demo Scenario

  @scenario4 @demoAndroid
  Scenario: Launch App and Close the android app demo
    Given I login to the android app
    Then I close the app
    