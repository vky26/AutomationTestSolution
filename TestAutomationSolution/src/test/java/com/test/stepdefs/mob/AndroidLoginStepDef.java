package com.test.stepdefs.mob;
import org.testng.Assert;

import com.test.framework.ScenarioContext;
import com.test.pageclass.mob.AndroidLoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
/**
* <h1>LoginAndroidStepDef</h1>
* The LoginStepDef class is has all the Step defs for Login Page which will be used commonly in all the feature files 
* 
* @author  Vignesh Parameswari
* @since   2020-03-24
*/

public class AndroidLoginStepDef {
    private ScenarioContext sc;
    private AndroidLoginPage loginPage;

    public AndroidLoginStepDef(ScenarioContext scenarioContext) {
        this.sc = scenarioContext;
        loginPage = new AndroidLoginPage(sc);
    }

    @Given("I login to the android app")
    public void loginToSite() {
       if(loginPage.launchApp()) {
    	   Assert.assertTrue(true, "Login to Shopping Site is successful");
       }else {
    	   Assert.assertFalse(true, "Login to Shopping Site is not successful");
       }
    }
    
    @And("I close the app")
    public void logoutOfShoppingSite() {
       if(loginPage.close()) {
    	   Assert.assertTrue(true, "Login out of Shopping Site is successful");
       }else {
    	   Assert.assertFalse(true, "Login out of Shopping Site is not successful");
       }
    }
    
}