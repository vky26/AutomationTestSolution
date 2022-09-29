package com.test.stepdefs.web;

import com.test.framework.ScenarioContext;
import com.test.pageclass.web.HotelBookingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class HotelBookingStepDef {
    private ScenarioContext sc;
    private HotelBookingPage bookingPage;

    public HotelBookingStepDef(ScenarioContext scenarioContext) {
        this.sc = scenarioContext;
        bookingPage = new HotelBookingPage(sc);
    }

    @Given("I check the availability of a deluxe room")
    public void checkAvailibility() {
        Assert.assertTrue(bookingPage.checkRoomAvailibility(),"Check Availibility of Deluxe Room");
    }

    @When("I book the room")
    public void bookADeluxeRoom() {
        Assert.assertTrue(bookingPage.bookARoom(),"Book a Deluxe Room");
    }

    @Then("I verify the booking")
    public void verifyBooking() {
        Assert.assertTrue(bookingPage.verifyRoomBooking(),"Verify the booking");
    }


}

