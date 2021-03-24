package com.test.stepdefs;
import org.testng.Assert;

import com.test.framework.ScenarioContext;
import com.test.pageclass.OrderTShirtPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
/**
* <h1>OrderTShirtStepDef</h1>
* The OrderTShirtStepDef class is has all the Step defs for Order T-Shirt Releated Feature
* 
* @author  Vignesh Parameswari
* @since   2020-03-24
*/

public class OrderTShirtStepDef {
    private ScenarioContext sc;
    private OrderTShirtPage orderPage;

    public OrderTShirtStepDef(ScenarioContext scenarioContext) {
        this.sc = scenarioContext;
        orderPage = new OrderTShirtPage(sc);
    }

    @When("^I order '(.+)' TShirt of quantity '(.+)'$")
    public void orderTShirt(String productName, int quantity) {
    	sc.setProductName(productName);
    	sc.setOrderQuantity(quantity);
    	
    	Assert.assertTrue(orderPage.orderTShirt(),"Order a product");
    }
    
    @Then("I verify the order history")
    public void verifyOrderHistory() {
    	Assert.assertTrue(orderPage.verifyOrderHistory(),"Verify Order History");
    }
    
}