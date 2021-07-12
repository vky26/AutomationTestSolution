package com.test.runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * <h1>DefaultRunner</h1>  The DefaultRunner class will get called first. This config is made in pom file. 
 * We specify tags to be run here & we can still override the tag from cli 
 *
 * @author Vignesh Parameswari
 * @since 2021-03-24
 */

@CucumberOptions(tags = "@demo", glue = { "com.test.stepdefs" }, plugin = {"json:target/cucumber.json"}, features = { "src/test/resources/features/" })
public class DefaultRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}


}
