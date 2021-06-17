#Author: Vignesh Parameswari
#Summary : Update the Personal Information First Name, Validate the My Account Page and reset it back to orignial

Feature: Update Personal Information

  @scenario2 @smoke
  Scenario: Update the Personal Information and Verify
    Given I login to the shopping site
		When I change my personal information
		Then I verify my updated personal information
		And I reset back my personal information