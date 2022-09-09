package com.test.pageclass.web;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.framework.AbstractPage;
import com.test.framework.ScenarioContext;
/**
* <h1>PersonalInfoPage</h1>
* The PersonalInfoPage class is has all Personal Information Page Attributes and the related action methods
* Single point of place to change if any locator is updated in Personal Information Page in the Application
* 
* @author  Vignesh Parameswari
* @since   2020-03-24
*/
public class PersonalInfoPage extends AbstractPage {

	@FindBy(xpath = "//span[text()='My personal information']")
	private WebElement myPersonalInfoLink;
	
	@FindBy(xpath = "//input[@id='firstname']")
	private WebElement firstNamePath;
	
	@FindBy(xpath = "//input[@id='old_passwd']")
	private WebElement currentPassWord;
	
	@FindBy(xpath = "//input[@id='passwd']")
	private WebElement newPassWord;
	
	@FindBy(xpath = "//input[@id='confirmation']")
	private WebElement confirmPassword;
	
	@FindBy(xpath = "//button/span[text()='Save']")
	private WebElement saveButton;
	
	@FindBy(xpath = "(//i[@class='icon-chevron-left'])[1]")
	private WebElement backToAccounts;
	
	@FindBy(xpath = "//a[@title='View my customer account']/span")
	private WebElement nameInHeaderLink;
	
	
	private ScenarioContext sc;

	// constructor
	public PersonalInfoPage(ScenarioContext scenarioContext) {
		super(scenarioContext);
		this.sc = scenarioContext;
		PageFactory.initElements(scenarioContext.getDriver(), this);
	}


	public boolean changePersonalInfo() {
		webClickElement(myPersonalInfoLink);
		
		webClearField(firstNamePath);
		
		String name =  sc.readJsonData("addressDetails", "name");
		String[] firstNameArr = name.split(" ");
		String firstName = firstNameArr[0];
		sc.setFirstName(firstName);
		
		//updated firstName 
		firstName = firstName+sc.getRandomString();
		sc.setUpdatedFirstName(firstName);
		webSendKeys(firstNamePath, firstName);
		
		HashMap<String, Object> defaultDataMap = sc.readWholeJsonData("defaultproperties");
		webSendKeys(currentPassWord, sc.decodedString(defaultDataMap.get("passWord").toString()));
		webSendKeys(newPassWord, sc.decodedString(defaultDataMap.get("newPassWord").toString()));
		webSendKeys(confirmPassword, sc.decodedString(defaultDataMap.get("newPassWord").toString()));
		webClickElement(saveButton);
		
		return true;
	}

	//Verify Personal Info
	public boolean verifyUpdatedPersonalInfo() {
		
		//Verify Name Update in Header Link
		String updatedNameInHeaderLink = webGetText(nameInHeaderLink);
		String[] updatedFirstNameArr = updatedNameInHeaderLink.split(" ");
		String updatedFirstName = updatedFirstNameArr[0];
		
		if(updatedFirstName.equals(sc.getUpdatedFirstName())) {
			LOG.info("First Name has been updated successfully and its  verified in Header Section");
			Assert.assertTrue(true,"First Name has been updated successfully and its  verified in Header Section");
		}else {
			LOG.info("First Name has not been updated successfully and its  verified in in Header Section");
			Assert.assertFalse(true,"First Name has not been updated successfully and its  verified in in Header Section");
		}
		
		webClickElement(backToAccounts);
		webClickElement(myPersonalInfoLink);
		
		//Verify FirstName in My Personal Account Information
		if(webGetAttribute(firstNamePath, "value").equals(sc.getUpdatedFirstName())){
			LOG.info("First Name has been updated successfully and its  verified in Personal Information Screen");
			Assert.assertTrue(true,"First Name has been updated successfully and its  verified in Personal Information Screen");
		}else {
			LOG.info("First Name has not been updated successfully and its  verified in Personal Information Screen");
			Assert.assertFalse(true,"First Name has not been updated successfully and its  verified in Personal Information Screen");
		}
		
		sc.getScenario().attach("First Name has been updated successfully and its  verified in Header & Personal Information Screen", "text/plain", "First Name has been updated successfully and its  verified in Header & Personal Information Screen");
		takeScreenShot(sc, "First Name has been updated successfully and its  verified in Header & Personal Information Screen");
		 
		return true;
	
	}
	
	
	//Reset Personal Info
	public boolean resetPersonalInfo() {
		webClearField(firstNamePath);
		webSendKeys(firstNamePath, sc.getFirstName());
		
		HashMap<String, Object> defaultDataMap = sc.readWholeJsonData("defaultproperties");
		webSendKeys(currentPassWord, sc.decodedString(defaultDataMap.get("newPassWord").toString()));
		webSendKeys(newPassWord, sc.decodedString(defaultDataMap.get("passWord").toString()));
		webSendKeys(confirmPassword, sc.decodedString(defaultDataMap.get("passWord").toString()));
		webClickElement(saveButton);
		
		//Verify Name Update in Header Link after Reset
		String originalNameInHeaderLink = webGetText(nameInHeaderLink);
		String[] originalFirstNameArr = originalNameInHeaderLink.split(" ");
		String firstName = originalFirstNameArr[0];
		
		if(firstName.equals(sc.getFirstName())) {
			LOG.info("First Name has been reset back successfully and its  verified in Header Screen");
			Assert.assertTrue(true,"First Name has been reset back successfully and its  verified in Header Screen");
		}else {
			LOG.info("First Name has not been reset back successfully and its  verified in Header Screen");
			Assert.assertFalse(true,"First Name has not been reset back successfully and its  verified in Header Screen");
		}
		
		LOG.info("First Name has been reset back successfully and its  verified in Personal Information Screen");
		sc.getScenario().attach("First Name has been reset back successfully and its verified in Header & Personal Information Screen", "img/png", "First Name has been reset back successfully and its  verified in Header & Personal Information Screen");
		
		return true;
		
	}

}