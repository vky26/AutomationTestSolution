package com.test.pageclass.web;

import com.test.framework.AbstractPage;
import com.test.framework.ScenarioContext;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* <h1>HotelBookingPage</h1>
* The HotelBookingPage class is has all Page Attributes and the related action methods
* Single point of place to change if any locator is updated in HotelBooking Scenario in the Application
* 
* @author  Vignesh Parameswari
* @since   2022-09-14
*/
public class HotelBookingPage extends AbstractPage {

	@FindBy(id = "date-start")
	private WebElement arrivalDate;

	@FindBy(id = "to-place")
	private WebElement numberOfNights;

	@FindBy(name = "wbe_product[adult_count]")
	private WebElement numberOfAdults;

	@FindBy(name = "wbe_product[children_count]")
	private WebElement numberOfChildren;

	@FindBy(name = "commit")
	private WebElement bookNowButton;

	@FindBy(xpath = "//h2[contains(text(),'Deluxe Appartment')]")
	private WebElement deluxeApartmentCategory;

	@FindBy(xpath = "(//h2[contains(text(),'Deluxe Appartment')]/following::table//tr[last()])//td[last()]/span")
	private WebElement deluxeApartmentRoom;

	@FindBy(xpath = "(//input[@type='number'])[1]")
	private WebElement addOn1;

	@FindBy(xpath = "(//input[@type='number'])[2]")
	private WebElement addOn2;

	@FindBy(xpath = "(//input[@type='submit'])[1]")
	private WebElement addSelectedAddOns;

	@FindBy(xpath = "//input[@title='E-mail']")
	private WebElement emailId;

	@FindBy(xpath = "//input[@title='Last name']")
	private WebElement lastName;

	@FindBy(xpath = "//input[@title='First name']")
	private WebElement firstName;

	@FindBy(xpath = "//input[@title='Phone']")
	private WebElement phoneNumber;

	@FindBy(xpath = "//input[@title='Credit Card']")
	private WebElement creditCardRadioButton;

	@FindBy(xpath = "//input[@title='I agree with the hotel and guarantee policy']")
	private WebElement agreePolicyRadioButton;

	@FindBy(xpath = "//input[@value='Create Booking']")
	private WebElement createBookingButton;

	@FindBy(id = "cardNumber")
	private WebElement cardNumber;

	@FindBy(id = "credit_card_collect_purchase_address")
	private WebElement billingAddressLine;

	@FindBy(id = "credit_card_collect_purchase_zip")
	private WebElement zip;

	@FindBy(id = "credit_card_collect_purchase_city")
	private WebElement city;

	@FindBy(id = "credit_card_collect_purchase_state")
	private WebElement state;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement payButton;

	@FindBy(xpath = "//h1[text()='Thank you for your booking!']")
	private WebElement successMessage;

	private ScenarioContext sc;

	// constructor
	public HotelBookingPage(ScenarioContext scenarioContext) {
		super(scenarioContext);
		this.sc = scenarioContext;
		PageFactory.initElements(scenarioContext.getDriver(), this);
	}

	public boolean checkRoomAvailibility() {
		sc.getDriver().get(sc.decodedString(sc.readJsonData("defaultproperties", "hotelBookingURL")));//launch app
		checkRoomAvailability();//check room availability
		takeScreenShot(sc, "Deluxe Room is available for booking");
		return true;
	}

	public boolean bookARoom(){
		selectAddOns();//select add-ons
		createBooking();//provide guest details and create booking
		enterCardDetails();//provide card details
		enterBillingDetails();//enter billing details
		takeScreenShot(sc, "Book a room");
		return true;
	}

	public boolean verifyRoomBooking() {
		verifyBooking();
		takeScreenShot(sc, "Deluxe Room is booked and verified successfully");
		return true;
	}

	private void checkRoomAvailability() {
		HashMap<String, Object> defaultDataMap = sc.readWholeJsonData("defaultproperties");//load test data
		//Create a date = date + 60-80

		String dateAfter = sc.getRandomDateString();
		webSendKeys(arrivalDate, dateAfter);
		webClearField(numberOfNights);
		webClickElement(numberOfNights);
		webSendKeys(numberOfNights, defaultDataMap.get("numberOfNights").toString());
		webClearField(numberOfAdults);
		webSendKeys(numberOfAdults, defaultDataMap.get("numberOfAdults").toString());
		webClearField(numberOfChildren);
		webSendKeys(numberOfChildren, defaultDataMap.get("numberOfChild").toString());
		webClickElement(bookNowButton);
		switchToIFrame("clock_pms_iframe_1"); //switch to frame
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1400)");//scroll down to the page

		if(webIsElementVisible(deluxeApartmentRoom)){
			webClickElement(deluxeApartmentRoom);
		}else{
			Assert.assertFalse(true,"No Deluxe Room Available for this date");
		}
	}

	private void selectAddOns() {
		webSendKeys(addOn1, "1");
		webSendKeys(addOn2, "1");
		jsClickWithoutWait(addSelectedAddOns);
	}

	private void createBooking() {

		HashMap<String, Object> defaultDataMap = sc.readWholeJsonData("defaultproperties");//load test data

		webSendKeys(emailId, defaultDataMap.get("emailID").toString());
		webSendKeys(lastName,defaultDataMap.get("lastName").toString());
		webSendKeys(firstName,defaultDataMap.get("firstName").toString());
		webSendKeys(phoneNumber,defaultDataMap.get("phoneNumber").toString());
		webClickElement(creditCardRadioButton);
		webClickElement(agreePolicyRadioButton);
		webClickElement(createBookingButton);
	}

	private void enterCardDetails() {
		String dropDownBrand = "credit_card_collect_purchase[brand]";
		String expiryMonth = "credit_card_collect_purchase[expire_month]";
		String expiryYear = "credit_card_collect_purchase[expire_year]";

		HashMap<String, Object> creditCardDetailsDataMap = sc.readWholeJsonData("creditCardDetails");//load test data

		webSendKeys(cardNumber, sc.decodedString(creditCardDetailsDataMap.get("number").toString()));
		selectDropDownByName(dropDownBrand,sc.decodedString(creditCardDetailsDataMap.get("brand").toString()));
		selectDropDownByName(expiryMonth,sc.decodedString(creditCardDetailsDataMap.get("expiryMonth").toString()));
		selectDropDownByName(expiryYear,sc.decodedString(creditCardDetailsDataMap.get("expiryYear").toString()));

	}

	private void enterBillingDetails() {
		String country = "credit_card_collect_purchase[country]";

		HashMap<String, Object> billingAddressDetailsDataMap = sc.readWholeJsonData("billingAddressDetails");//load test data

		webSendKeys(billingAddressLine,billingAddressDetailsDataMap.get("addLine").toString());
		webSendKeys(zip,billingAddressDetailsDataMap.get("zip").toString());
		webSendKeys(city,billingAddressDetailsDataMap.get("city").toString());
		webSendKeys(state,billingAddressDetailsDataMap.get("state").toString());
		selectDropDownByName(country, billingAddressDetailsDataMap.get("country").toString());
		webClickElement(payButton);

	}

	private void verifyBooking(){
		if(webIsElementVisible(successMessage)) {
			LOG.info("The booking message has been verified successfully");
			Assert.assertTrue(true,"The booking message has been verified successfully");
		}else {
			LOG.info("The booking hasn't been successfull");
			Assert.assertFalse(true,"The booking hasn't been successfull");
		}

	}

}