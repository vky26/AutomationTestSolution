package com.test.pageclass.mob;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.test.framework.AbstractPage;
import com.test.framework.ScenarioContext;

/**
* <h1>LoginPage</h1>
* The AndroidLoginPage class is has all Login Page Attributes and the related action methods
* Single point of place to change if any locator is updated in Login Page
* 
* @author  Vignesh Parameswari
* @since   2020-03-24
*/
public class AndroidLoginPage extends AbstractPage {

	@FindBy(xpath = "//a[@title='My Store']")
	private WebElement appLogo;
	
	
	private ScenarioContext sc;

	// constructor
	public AndroidLoginPage(ScenarioContext scenarioContext) {
		super(scenarioContext,"android");
		this.sc = scenarioContext;
		PageFactory.initElements(scenarioContext.getMobileDriver(), this);
	}

	//launch app
	public boolean launchApp() {
		System.out.println("App Launched");
		 try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return true;
	}
	
	
	public boolean close() {
		 sc.getMobileDriver().quit();
		 System.out.println("App Closed");
		 return true;
	}

	

}