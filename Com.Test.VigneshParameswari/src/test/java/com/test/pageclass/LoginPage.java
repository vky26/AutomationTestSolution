package com.test.pageclass;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.test.framework.AbstractPage;
import com.test.framework.ScenarioContext;

/**
* <h1>LoginPage</h1>
* The LoginPage class is has all Login Page Attributes and the related action methods
* Single point of place to change if any locator is updated in Login Page
* 
* @author  Vignesh Parameswari
* @since   2020-03-24
*/
public class LoginPage extends AbstractPage {

	@FindBy(xpath = "//a[@title='My Store']")
	private WebElement appLogo;
	
	@FindBy(css = ".login")
	private WebElement signInLink;
	
	@FindBy(id = "email")
	private WebElement emailId;
	
	@FindBy(id = "passwd")
	private WebElement passWord;

	@FindBy(id ="SubmitLogin")
	private WebElement signInButton;
	
	@FindBy(css =".logout")
	private WebElement signOutButton;
	
	private ScenarioContext sc;

	// constructor
	public LoginPage(ScenarioContext scenarioContext) {
		super(scenarioContext);
		this.sc = scenarioContext;
		PageFactory.initElements(scenarioContext.getDriver(), this);
	}

	// Launch Shopping Web Site and Login
	public boolean loginToShoppingSite() {
		 sc.getDriver().get(sc.decodedString(sc.readJsonData("defaultproperties", "url")));
		 Assert.assertTrue(webIsElementVisible(appLogo),"App Launched Successfully");
		 
		 webClickElement(signInLink);
		 webSendKeys(emailId, sc.decodedString(sc.readJsonData("defaultproperties", "userName")));
		 webSendKeys(passWord, sc.decodedString(sc.readJsonData("defaultproperties", "passWord")));
		 webClickElement(signInButton);
		 
		 takeWebScreenShot(sc,"Login to application is successful");
		 return true;
	}

	public boolean logoutOfShoppingSite() {
		webClickElement(signOutButton);
		return true;
	}
	

}