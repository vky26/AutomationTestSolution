package com.test.pageclass.web;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.test.framework.AbstractPage;
import com.test.framework.ScenarioContext;
 
/**
* <h1>OrderTShirtPage</h1>
* The OrderTShirtPage class is has all Order T-Shrit Page Attributes and the related action methods
* Single point of place to change if any locator is updated in Order T-Shirt Page in the Application
* 
* @author  Vignesh Parameswari
* @since   2020-03-24
*/
public class OrderTShirtPage extends AbstractPage {

	@FindBy(xpath = "(//ul/li/a[@title='T-shirts'])[last()]")
	private WebElement tShirtMenu;

	@FindBy(xpath = "(//div[@class='product-count'])[1]")
	private WebElement productCount;
	
	@FindBy(xpath = "//span[@id='our_price_display']")
	private WebElement productPrice;
	
	@FindBy(xpath = "//button[@type='submit']/span[text()='Add to cart']")
	private WebElement addToCart;
	
	@FindBy(xpath = "(//a[@title='Proceed to checkout'])[last()]")
	private WebElement proceedToCheckOut;
	
	@FindBy(xpath = "(//span[contains(text(),'Proceed to checkout')])[last()]")
	private WebElement proceedToPaymentCheckOut;
	
	@FindBy(xpath = "(//p[@class='product-name']/a[1])[last()]")
	private WebElement productNameSummaryScreen;
	
	@FindBy(xpath = "//span[@id='summary_products_quantity']")
	private WebElement productQuantitySummaryScreen;
	
	@FindBy(xpath = "//td[contains(@class,'cart_quantity')]")
	private WebElement productQuantityPaymentScreen;
	
	@FindBy(xpath = "//span[@id='total_price']")
	private WebElement productTotalAmountSummaryScreen;
	
	@FindBy(xpath = "//input[@id='addressesAreEquals']")
	private WebElement addressEqualCheckBox;
	
	@FindBy(xpath="//div[@class='checker']/span/input")
	private WebElement agreeCheckBox;
	
	@FindBy(xpath="//div[@class='delivery_option_price']")
	private WebElement deliveryFee;
	
	@FindBy(xpath="//span[@id='total_price']")
	private WebElement totalPayableAmount;
	
	@FindBy(xpath="//p[@class='payment_module']/a[@title='Pay by bank wire']")
	private WebElement payByBankWire;
	
	@FindBy(xpath="//h3[contains(text(),'Bank-wire payment.')]")
	private WebElement confirmPayByBankWireType;
	
	@FindBy(xpath="//span[text()='I confirm my order']")
	private WebElement confirmOrderButton;
	
	@FindBy(xpath="//p[@class='cheque-indent']/strong[text()='Your order on My Store is complete.']")
	private WebElement orderCompleteMessage;
	
	@FindBy(xpath="//span[@class='price']/strong")
	private WebElement priceInOrderSummary;
	
	@FindBy(xpath="//div[@class='box']")
	private WebElement contentBox;
	
	@FindBy(xpath="//a[@title='Back to orders']")
	private WebElement backToOrder;
	
	@FindBy(xpath="(//th[text()='Product']//following::tbody/tr[1])[1]/td[2]")
	private WebElement verifyProductNameinOrderHistoryPage;
	
	@FindBy(xpath="(//th[text()='Product']//following::tbody/tr[1])[1]/td[3]")
	private WebElement verifyProductQuantityinOrderHistoryPage;
	
	@FindBy(xpath="(//th[text()='Product']//following::tbody/tr[1])[1]/td[5]")
	private WebElement verifyProductPriceinOrderHistoryPage;
	
	@FindBy(xpath="(//span[@class='price-shipping'])[last()]")
	private WebElement verifyShippingPriceinOrderHistoryPage;
	
	@FindBy(xpath="(//span[@class='price'])[last()]")
	private WebElement verifyTotalPriceinOrderHistoryPage;
	
	@FindBy(xpath="//ul[@class='address alternate_item box']/li[2]")
	private WebElement verifyName;
	
	@FindBy(xpath="//ul[@class='address alternate_item box']/li[4]")
	private WebElement verifyAddLine1;
	
	@FindBy(xpath="//ul[@class='address alternate_item box']/li[5]")
	private WebElement verifyCity;

	@FindBy(xpath="//ul[@class='address alternate_item box']/li[6]")
	private WebElement verifyCountry;
	
	@FindBy(xpath="//ul[@class='address alternate_item box']/li[8]")
	private WebElement verifyPhoneNumber;
	
	private String nameInAddress = "//li[contains(@class,'address_firstname')]";
	private String addressLine1="//li[contains(@class,'address_address1')]";
	private String addressCity="//li[contains(@class,'address_city')]";
	private String addressCountry="//li[@class='address_country_name']";
	private String addressPhoneNumber="//li[@class='address_phone_mobile']";
	
	
	private ScenarioContext sc;

	// constructor
	public OrderTShirtPage(ScenarioContext scenarioContext) {
		super(scenarioContext);
		this.sc = scenarioContext;
		PageFactory.initElements(scenarioContext.getDriver(), this);
	}

	
	public boolean orderTShirt() {
		addProduct();//Add Product
		webClickElement(proceedToCheckOut);
		
		validateSummaryScreen();//Summary Screen
		webClickElement(proceedToCheckOut);
		
		validateAddressScreen();//Summary Screen
		webClickElement(proceedToPaymentCheckOut);
		
		validateShippingScreen();//Shipping Screen
		webClickElement(proceedToPaymentCheckOut);
		
		validatePaymentScreen();//Shipping Screen
		webClickElement(confirmOrderButton);
		
		validateOrderSummaryScreen();//order summary page
		
		sc.getScenario().attach("Test Data used is: Product name " + sc.getProductName() + " and the quantity is " + sc.getOrderQuantity(), "text/plain", "Test Data used for Order TShirt Scenario");
		takeWebScreenShot(sc, "Placed Order is successfully");
		return true;
	}


	private void addProduct() {
		webClickElement(tShirtMenu);
		
		//Check if there are any products in TShirt Menu
		String productCountText = webGetText(productCount);
		int index= productCountText.lastIndexOf(" ");
		int prodCount = Integer.parseInt(productCountText.substring(index-1,index+1).replaceAll(" ", ""));//remove white space
		if(prodCount>0) {
			sc.getScenario().attach("TShirts available to order", "text/plain", "TShirts available to order");
			Assert.assertTrue(true,"No TShirts available to order");
		}else {
			sc.getScenario().attach("No TShirts available to order", "text/plain", "No TShirts available to order");
			Assert.assertFalse(true,"No TShirts available to order");
		}
		
		String productPath = "(//div[@class='right-block'])[1]//a[@title='"+ sc.getProductName() +"']";
		webClickElement(productPath);
		
		sc.setProductPrice(webGetText(productPrice));
		
		webClickElement(addToCart);
		
		takeWebScreenShot(sc, "Product has been added to Cart Successfully");
	}

	// TODO Auto-generated method stub
	private void validateSummaryScreen() {
		
		//check if the product name is correct in summary screen
		if(webGetText(productNameSummaryScreen).equals(sc.getProductName())) {
			sc.getScenario().attach("Product Name is shown properly in Summary Screen", "text/plain", "Product Name is shown properly in Summary Screen");
			LOG.info("Product name is shown properly in summary screen");
			Assert.assertTrue(true,"Product name is shown properly in summary screen");
		}else {
			sc.getScenario().attach("Product Name is not shown properly in Summary Screen", "text/plain", "Product Name is not shown properly in Summary Screen");
			LOG.info("Product Name is not shown properly in summary screen");
			Assert.assertTrue(false,"Product Name is not shown properly in summary screen");
		}
		
		//check if the product quantity is correct in summary screen
		String productCountTextSummaryScreen = webGetText(productQuantitySummaryScreen);
		int index= productCountTextSummaryScreen.lastIndexOf(" ");
		int prodCountInSummaryScreen = Integer.parseInt(productCountTextSummaryScreen.substring(0, index).replaceAll(" ", ""));//remove white space
		
		if(prodCountInSummaryScreen == sc.getOrderQuantity()) {
			sc.getScenario().attach("Product Quanity is shown properly in Summary Screen", "text/plain", "Product Quanity is shown properly in Summary Screen");
			LOG.info("Product Quanity is shown properly in summary screen");
			Assert.assertTrue(true,"Product Quanity is shown properly in summary screen");
		}else {
			sc.getScenario().attach("Product Quanity is not shown properly in Summary Screen", "text/plain", "Product Quanity is not shown properly in Summary Screen");
			LOG.info("Product Quanity is not shown properly in summary screen");
			Assert.assertTrue(false,"Product Quanity is not shown properly in summary screen");
		}
		
		//Capture the price and store in context
		sc.setOrderPrice(webGetText(productTotalAmountSummaryScreen));
		LOG.info("Product Price has been set in context");
		
		takeWebScreenShot(sc, "Order Summary Screen Validation is successfully");
		
	}
	
	// TODO Auto-generated method stub
	private void validateAddressScreen() {
		
		//check if the delivery and billing address are same
		if(webIsElementSelected(addressEqualCheckBox)) {
			LOG.info("The delivery and billing address are same");
		}
		
		validateList(nameInAddress,sc.readJsonData("addressDetails", "name"));
		validateList(addressLine1,sc.readJsonData("addressDetails", "addLine1"));
		validateList(addressCity,sc.readJsonData("addressDetails", "addCity"));
		validateList(addressCountry,sc.readJsonData("addressDetails", "addCountry"));
		validateList(addressPhoneNumber,sc.readJsonData("addressDetails", "phoneNumber"));
		
		takeWebScreenShot(sc, "Validation of Address Screen is successfully");
		
	}
	
	private void validateList(String element, String expectedValue) {
		List<WebElement> elementList = webGetListOfElements(element);
		
		for(WebElement ele: elementList) {
			if((ele.getText()).equals(expectedValue)){
				LOG.info("Adress details match with test data in json. Expected "+ expectedValue + " found " + ele.getText());
			}else {
				LOG.error("Address mismatch with the test data in json. Expected " + expectedValue + " found " + ele.getText());
				Assert.assertFalse(true);
			}
		}
	}

	private void validateShippingScreen() {
		sc.setDeliveryFee(webGetText(deliveryFee));
		jsClickWithoutWait(agreeCheckBox);
		
		takeWebScreenShot(sc, "Validation of Shipping Screen is successfully");
	}
	
	private void validatePaymentScreen() {
		//check if the product name is correct in Payment screen
		if(webGetText(productNameSummaryScreen).equals(sc.getProductName())) {
			sc.getScenario().attach("Product Name is shown properly in Payment Screen", "text/plain", "Product Name is shown properly in Payment Screen");
			LOG.info("Product name is shown properly in Payment screen");
			Assert.assertTrue(true,"Product name is shown properly in Payment screen");
		}else {
			sc.getScenario().attach("Product Name is not shown properly in Payment Screen", "text/plain", "Product Name is not shown properly in Payment Screen");
			LOG.info("Product Name is not shown properly in Payment screen");
			Assert.assertTrue(false,"Product Name is not shown properly in Payment screen");
		}
		
		
		//check if the product quantity is correct in Payment screen
		String productCountTextSummaryScreen = webGetText(productQuantityPaymentScreen).replaceAll(" ", "");//remove white space
		int prodCountInPaymentScreen = Integer.parseInt(productCountTextSummaryScreen);
		
		if(prodCountInPaymentScreen == sc.getOrderQuantity()) {
			sc.getScenario().attach("Product Quanity is shown properly in Summary Screen", "text/plain", "Product Quanity is shown properly in Summary Screen");
			LOG.info("Product Quanity is shown properly in summary screen");
			Assert.assertTrue(true,"Product Quanity is shown properly in summary screen");
		}else {
			sc.getScenario().attach("Product Quanity is not shown properly in Summary Screen", "text/plain", "Product Quanity is not shown properly in Summary Screen");
			LOG.info("Product Quanity is not shown properly in summary screen");
			Assert.assertTrue(false,"Product Quanity is not shown properly in summary screen");
		}
		
		//validate Total in Payment Screen
		
		sc.setTotalPayableAmount(webGetText(totalPayableAmount));//set the total payable amount in context to verify later
		double totalPaymentAmount = Double.parseDouble(webGetText(totalPayableAmount).replace("$", ""));
		double productValue = Double.parseDouble(sc.getOrderPrice().replace("$", "")); 
		double shippingCharge = Double.parseDouble(sc.getDeliveryFee().replace("$", ""));  
		
		if(totalPaymentAmount ==(productValue + shippingCharge)) {
			LOG.info("Total Value of the product including shipping charge has been verified and equal to $" + totalPaymentAmount);
		}else {
			LOG.error("Total Value of the product including shipping charge is wrong.");
		}
		
		
		webClickElement("//p[@class='payment_module']/a[@title='"+ sc.readJsonData("defaultproperties", "paymentType") +"']");//Get Payment Type from Config file
		Assert.assertTrue(webIsElementVisible(confirmPayByBankWireType),"Bank Wire Payment Type has been selected");
		
		takeWebScreenShot(sc, "Validation of Payment Screen is successful and the total amount is " + totalPaymentAmount);
		
	}

	
	private void validateOrderSummaryScreen() {
		
		if(webIsElementVisible(orderCompleteMessage)) {
			LOG.info("Order Summary message has been verified successfully");
			Assert.assertTrue(true,"Order Summary message has been verified successfully");
		}else {
			LOG.info("Order Summary message has not been verified successfully");
			Assert.assertFalse(true,"Order Summary message has not been verified successfully");
		}
		
		if(webGetText(priceInOrderSummary).equals(sc.getTotalPayableAmount())) {
			Assert.assertTrue(true,"Price in Order Summary is verified");
		}else {
			Assert.assertFalse(true,"Price in Order Summary is wrong");
		}
		
		String content = webGetText(contentBox);
		Pattern pattern = Pattern.compile("reference (.*?) in"); // Extract the reference number from the order summary
		Matcher matcher = pattern.matcher(content); 
		while (matcher.find()) {
		 sc.setOrderRefNumber(matcher.group(1)); //Store the reference number in the context
		}
		
	}

	// Then part - Verification of Order History
	public boolean verifyOrderHistory() {
		webClickElement(backToOrder);
		
		validateOrderHistoryTable();//validate order history page
		
		webClickElement("//a[contains(text(),'"+ sc.getOrderRefNumber() +"')]");//click on reference number and validate further
		waitForPageLoad();
		verifyOrderInDetail();//Click on Order and verify the Product Details
		verifyDelvieryAddress();//Verify the Delivery Address Details
		
		return true;
	}


	private void validateOrderHistoryTable() {
		//check if order is present in order history page
		if(webIsElementVisible("//a[contains(text(),'"+ sc.getOrderRefNumber() +"')]")) {
			LOG.info("Order Reference number is present in Order History, and verified successfully");
			Assert.assertTrue(true,"Order Reference number is present in Order History, and verified successfully");
		}else {
			LOG.info("Order Reference number is not present in Order History, and verified successfully");
			Assert.assertFalse(true,"Order Reference number is not present in Order History, and verified successfully");
		}
		
		//check if the price of the order is correct in order history page
		if((webGetText("//td//a[contains(text(),'"+ sc.getOrderRefNumber() +"')]//following::td[2]/span").equals(sc.getTotalPayableAmount()))) {
			LOG.info("Order Price in Order History Screen has been verified successfully");
			Assert.assertTrue(true,"Order Price in Order History Screen has been verified successfully");
		}else {
			LOG.info("Order Price in Order History Screen has not been verified successfully");
			Assert.assertFalse(true,"Order Price in Order History Screen has not been verified successfully");
		}
		
		//verify the date of order in order history page
		if((webGetText("//td//a[contains(text(),'"+ sc.getOrderRefNumber() +"')]//following::td[1]").equals(sc.getCurrentDate()))) {
			LOG.info("Order Date in Order History Screen has been verified successfully");
			Assert.assertTrue(true,"Order Date in Order History Screen has been verified successfully");
		}else {
			LOG.info("Order Date in Order History Screen has not been verified successfully");
			Assert.assertFalse(true,"Order Date in Order History Screen has not been verified successfully");
		}
	}
	
	// TODO Auto-generated method stub
	private void verifyOrderInDetail() {
		//Verify Product Name
		if(webGetText(verifyProductNameinOrderHistoryPage).contains(sc.getProductName())) {
			LOG.info("Product in Order History Screen has been verified successfully");
			Assert.assertTrue(true,"Product in Order History Screen has been verified successfully");
		}else {
			LOG.info("Product in Order History Screen has not been verified successfully");
			Assert.assertFalse(true,"Product in Order History Screen has not been verified successfully");
		}
		
		//verify Product Quantity
		if(webGetText(verifyProductQuantityinOrderHistoryPage).equals(String.valueOf(sc.getOrderQuantity()))) {
			LOG.info("Product Quantity in Order History Screen has been verified successfully");
			Assert.assertTrue(true,"Product Quantity in Order History Screen has been verified successfully");
		}else {
			LOG.info("Product Quantity in Order History Screen has not been verified successfully");
			Assert.assertFalse(true,"Product Quantity in Order History Screen has not been verified successfully");
		}
		
		//Verify Product Price
		if(webGetText(verifyProductPriceinOrderHistoryPage).equals(String.valueOf(sc.getProductPrice()))) {
			LOG.info("Price of Product in Order History Screen has been verified successfully");
			Assert.assertTrue(true,"Price of Product in Order History Screen has been verified successfully");
		}else {
			LOG.info("Price of Product in Order History Screen has not been verified successfully");
			Assert.assertFalse(true,"Price of Product in Order History Screen has not been verified successfully");
		}
		
		//Verify Shipping Price
		if(webGetText(verifyShippingPriceinOrderHistoryPage).equals(String.valueOf(sc.getDeliveryFee()))) {
			LOG.info("Shipping Price of Product in Order History Screen has been verified successfully");
			Assert.assertTrue(true,"Shipping Price of Product in Order History Screen has been verified successfully");
		}else {
			LOG.info("Shipping Price of Product in Order History Screen has not been verified successfully");
			Assert.assertFalse(true,"Shipping Price of Product in Order History Screen has not been verified successfully");
		}
		
		
		//Verify Total Price
		if(webGetText(verifyTotalPriceinOrderHistoryPage).equals(String.valueOf(sc.getTotalPayableAmount()))) {
			LOG.info("Total Price of Product in Order History Screen has been verified successfully");
			Assert.assertTrue(true,"Total Price of Product in Order History Screen has been verified successfully");
		}else {
			LOG.info("Total Price of Product in Order History Screen has not been verified successfully");
			Assert.assertFalse(true,"Total Price of Product in Order History Screen has not been verified successfully");
		}
		
		
	}


	private void verifyDelvieryAddress() {
		HashMap<String, Object> addressDataMap = sc.readWholeJsonData("addressDetails");
		//verifyName
		if(webGetText(verifyName).equals(addressDataMap.get("name").toString())){
			LOG.info("Name in the Delivery Address from Order History Screen has been verified successfully");
			Assert.assertTrue(true,"Name in the Delivery Address from Order History Screen has been verified successfully");
		}else {
			LOG.info("Name in the Delivery Address from Order History Screen has not been verified successfully");
			Assert.assertFalse(true,"Name in the Delivery Address from Order History Screen has not been verified successfully");
		}
		
		//verifyAddLine1
		if(webGetText(verifyAddLine1).equals(addressDataMap.get("addLine1").toString())){
			LOG.info("Address Line 1 in the Delivery Address from Order History Screen has been verified successfully");
			Assert.assertTrue(true,"Address Line 1 in the Delivery Address from Order History Screen has been verified successfully");
		}else {
			Assert.assertFalse(true,"Address Line 1 in the Delivery Address from Order History Screen has not been verified successfully");
		}
		
		//verifyCity
		if(webGetText(verifyCity).equals(addressDataMap.get("addCity").toString())){
			LOG.info("City in the Delivery Address from Order History Screen has been verified successfully");
			Assert.assertTrue(true,"City in the Delivery Address from Order History Screen has been verified successfully");
		}else {
			Assert.assertFalse(true,"City in the Delivery Address from Order History Screen has not been verified successfully");
		}
		
		//verifyCountry
		if(webGetText(verifyCountry).equals(addressDataMap.get("addCountry").toString())){
			LOG.info("Country in the Delivery Address from Order History Screen has been verified successfully");
			Assert.assertTrue(true,"Country in the Delivery Address from Order History Screen has been verified successfully");
		}else {
			Assert.assertFalse(true,"Country in the Delivery Address from Order History Screen has not been verified successfully");
		}
		
		//verifyPhoneNumber
		if(webGetText(verifyPhoneNumber).equals(addressDataMap.get("phoneNumber").toString())){
			LOG.info("Phone Number in the Delivery Address from Order History Screen has been verified successfully");
			Assert.assertTrue(true,"Phone Number in the Delivery Address from Order History Screen has been verified successfully");
		}else {
			Assert.assertFalse(true,"Phone Number in the Delivery Address from Order History Screen has not been verified successfully");
		}
		
		LOG.info("Order History Details Page has verified successfully");
		takeWebScreenShot(sc, "Order History Details Page has verified successfully");
		
	}

}