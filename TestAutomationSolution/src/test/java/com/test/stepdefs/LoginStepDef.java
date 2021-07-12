package com.test.stepdefs;
import org.testng.Assert;

import com.test.framework.ScenarioContext;
import com.test.pageclass.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
/**
* <h1>LoginStepDef</h1>
* The LoginStepDef class is has all the Step defs for Login Page which will be used commonly in all the feature files 
* 
* @author  Vignesh Parameswari
* @since   2020-03-24
*/

public class LoginStepDef {
    private ScenarioContext sc;
    private LoginPage loginPage;

    public LoginStepDef(ScenarioContext scenarioContext) {
        this.sc = scenarioContext;
        loginPage = new LoginPage(sc);
    }

    @Given("I login to the shopping site")
    public void loginToSite() {
       if(loginPage.loginToShoppingSite()) {
    	   Assert.assertTrue(true, "Login to Shopping Site is successful");
       }else {
    	   Assert.assertFalse(true, "Login to Shopping Site is not successful");
       }
    }
    
    @And("I logout of the shopping site")
    public void logoutOfShoppingSite() {
       if(loginPage.logoutOfShoppingSite()) {
    	   Assert.assertTrue(true, "Login out of Shopping Site is successful");
       }else {
    	   Assert.assertFalse(true, "Login out of Shopping Site is not successful");
       }
    }
    
    @Given("I login to the google site")
    public void loginToGoogleSite() {
       if(loginPage.loginToSite()) {
    	   Assert.assertTrue(true, "Login to Shopping Site is successful");
       }else {
    	   Assert.assertFalse(true, "Login to Shopping Site is not successful");
       }
    }
    
    @Then("I close the browser")
    public void closeBrowser() {
       if(loginPage.close()) {
    	   Assert.assertTrue(true, "Closed Succesfully");
       }else {
    	   Assert.assertFalse(true, "Closed Succesfully");
       }
    }
    
    
}