package com.test.stepdefs;
import org.testng.Assert;

import com.test.framework.ScenarioContext;
import com.test.pageclass.PersonalInfoPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
/**
* <h1>PersonalInfoStepDef</h1>
* The PersonalInfoStepDef class is has all the Step defs for UpdatePersonalInformation Releated Feature
* 
* @author  Vignesh Parameswari
* @since   2020-03-24
*/

public class PersonalInfoStepDef {
    private ScenarioContext sc;
    private PersonalInfoPage personalInfoPage;

    public PersonalInfoStepDef(ScenarioContext scenarioContext) {
        this.sc = scenarioContext;
        personalInfoPage = new PersonalInfoPage(sc);
    }

    @When("I change my personal information")
    public void orderTShirt() {
    	Assert.assertTrue(personalInfoPage.changePersonalInfo(),"Change the first name in Personal Information");
    }
    
    @Then("I verify my updated personal information")
    public void verifyPersonalInfo() {
    	Assert.assertTrue(personalInfoPage.verifyUpdatedPersonalInfo(),"Verification of my updated personal information");
    }
    
    @And("I reset back my personal information")
    public void resetPersonalInfo() {
    	Assert.assertTrue(personalInfoPage.resetPersonalInfo(),"Reset Personal Information");
    }
    
}