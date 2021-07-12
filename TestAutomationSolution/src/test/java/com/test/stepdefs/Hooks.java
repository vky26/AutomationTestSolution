package com.test.stepdefs;

import com.test.framework.ScenarioContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * <h1>Hooks</h1> The Hooks class implements an what needs to be done before and
 * after step . 
 * Currently setting default values in context before run and quits the session and browser after scenario execution
 *
 * @author Vignesh Parameswari
 * @since 2021-03-24
 */

public class Hooks {
	private String scenDesc;
	private ScenarioContext sc;
	private Scenario scenario;

	public Hooks(ScenarioContext scenarioContext) {
		this.sc = scenarioContext;
	}

	@Before
	public void before(Scenario scenario) {
		this.scenDesc = scenario.getName();
		sc.setEnvironment(System.getProperty("environment"));
		sc.setBrowser(System.getProperty("browser"));
		this.scenario = scenario;
		sc.setScenario(scenario);
	}

	@After
	public void after(Scenario scenario) {
		sc.quitDriver();
	}

}