#Author: Vignesh Parameswari
#Summary : Book a deluxe room with 2 adds-on for 4 nights

Feature: Book a deluxe room

  @hotelBooking
  Scenario: Order T-Shirt and Verify in Order History
    Given I check the availability of a deluxe room
    When I book the room
    Then I verify the booking

