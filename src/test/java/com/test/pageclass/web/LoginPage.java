package com.test.pageclass.web;

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
		System.out.println("url: -->" + sc.decodedString(sc.readJsonData("defaultproperties", "url")));
		 sc.getDriver().get(sc.decodedString(sc.readJsonData("defaultproperties", "url")));
		 try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Assert.assertTrue(webIsElementVisible(appLogo),"App Launched Successfully");
		 
		 webClickElement(signInLink);
		 webSendKeys(emailId, sc.decodedString(sc.readJsonData("defaultproperties", "userName")));
		 webSendKeys(passWord, sc.decodedString(sc.readJsonData("defaultproperties", "passWord")));
		 webClickElement(signInButton);
		 
		 takeScreenShot(sc,"Login to application is successful");
		 return true;
	}
	
	public boolean loginToSite() {
		 sc.getDriver().get("https://google.com");
		 try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 takeScreenShot(sc,"Launched to application is successful");
		 return true;
	}
	
	public boolean close() {
		 sc.getDriver().close();
		 return true;
	}

	public boolean logoutOfShoppingSite() {
		webClickElement(signOutButton);
		return true;
	}
	

}