package com.test.framework;

import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>AbstractPage</h1> The AbstractPage class implements all the reusable
 * methods that are needed for the Web App Automation for our scenarios. 
 * All the page class extends this Abstract Page to use its methods
 *
 * @author Vignesh Parameswari
 * @since 2021-03-24
 */

public abstract class AbstractPage {
	protected static final Logger LOG = LoggerFactory.getLogger(AbstractPage.class);
	protected static final String LOG_CONTEXT = "context";
	private int driverWaitTime = 30;
	protected RemoteWebDriver driver;

	public AbstractPage(ScenarioContext scenarioContext) {
		if (System.getProperty("driverWaitTime") != null) {
			driverWaitTime = Integer.valueOf(System.getProperty("driverWaitTime"));
		}
		driver = scenarioContext.getDriver();
	}
	
	//Driver Initialisation for Mobile,  param to differentiate 
	public AbstractPage(ScenarioContext scenarioContext, String mobileVersion) {
		if (System.getProperty("driverWaitTime") != null) {
			driverWaitTime = Integer.valueOf(System.getProperty("driverWaitTime"));
		}
		driver = scenarioContext.getMobileDriver();
	}

	/**
	 * <h1>webClickElement</h1>
	 * This webClickElement - Clicks on the web element if present
	 * Accepts WebElement  
	 * Script Fails if element is not found
	 */
	public boolean webClickElement(WebElement ele) {
		Wait<WebDriver> wait = new WebDriverWait(driver, driverWaitTime);
		wait.until(ExpectedConditions.visibilityOf(ele));
		ele.click();
		return true;
	}

	/**
	 * <h1>webClickElement</h1>
	 * This webClickElement - Clicks on the web element if present
	 * Accepts String web element locator 
	 * Script Fails if element is not found
	 */
	public boolean webClickElement(String ele) {
		Wait<WebDriver> wait = new WebDriverWait(driver, driverWaitTime);
		wait.until(ExpectedConditions.elementToBeClickable((By.xpath(ele)))).click();
		return true;
	}

	/**
	 * <h1>webSendKeys</h1>
	 * This webSendKeys - enters the user provided input in the webelement 
	 * Accepts WebElement and the input string
	 * Script Fails if element is not found
	 */
	public boolean webSendKeys(WebElement ele, String input) {
		Wait<WebDriver> wait = new WebDriverWait(driver, driverWaitTime);
		wait.until(ExpectedConditions.visibilityOf(ele));
		ele.sendKeys(input);
		return true;
	}


	/**
	 * <h1>webClearField</h1>
	 * This webClearField - clears the WebElement field
	 * Accepts WebElement  
	 * Script Fails if element is not found
	 */
	public boolean webClearField(WebElement ele) {
		ele.clear();
		return true;
	}

	/**
	 * <h1>webGetAttribute</h1>
	 * This webGetAttribute - returns text value for the webElement element if present
	 * Accepts String element path  
	 * Script Fails if element is not found
	 */
	public String webGetText(String ele) {
		Wait<WebDriver> wait = new WebDriverWait(driver, driverWaitTime);
		wait.until(ExpectedConditions.elementToBeClickable((By.xpath(ele))));
		return driver.findElement(By.xpath(ele)).getText();

	}

	/**
	 * <h1>webGetAttribute</h1>
	 * This webGetAttribute - returns text value for the webElement element if present
	 * Accepts WebElement  
	 * Script Fails if element is not found
	 */
	public String webGetText(WebElement ele) {
		Wait<WebDriver> wait = new WebDriverWait(driver, driverWaitTime);
		wait.until(ExpectedConditions.visibilityOf(ele));
		return ele.getText();
	}
	
	/**
	 * <h1>webGetAttribute</h1>
	 * This webGetAttribute - returns attribute value for the webElement element if present
	 * Accepts WebElement and the attribute Name 
	 * Script Fails if element is not found
	 */
	public String webGetAttribute(WebElement ele, String attributeName) {
		Wait<WebDriver> wait = new WebDriverWait(driver, driverWaitTime);
		wait.until(ExpectedConditions.visibilityOf(ele));
		return ele.getAttribute(attributeName);
	}

	/**
	 * <h1>webIsElementVisible</h1>
	 * This webIsElementVisible - returns boolean if element is present, false otherwise
	 * 
	 * Accepts WebElement 
	 */
	public boolean webIsElementVisible(WebElement ele) {
		return ele.isDisplayed();
	}
	
	/**
	 * <h1>webIsElementVisible</h1>
	 * This webIsElementVisible - returns boolean if element is present, false otherwise
	 * 
	 * Accepts String locator
	 */
	public boolean webIsElementVisible(String ele) {
		return driver.findElement(By.xpath(ele)).isDisplayed();
	}
	

	/**
	 * <h1>jsClickWithoutWait</h1>
	 * This jsClickWithoutWait - Java script click on the element passed
	 */
	public void jsClickWithoutWait(WebElement ele) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	/**
	 * <h1>webGetListOfElements</h1>
	 * This webGetListOfElements -returns the list of available webelements for the provided locator element
	 */
	public List<WebElement> webGetListOfElements(String ele) {
		Wait<WebDriver> wait = new WebDriverWait(driver, driverWaitTime);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(ele))));
		return driver.findElements(By.xpath(ele));

	}

	/**
	 * <h1>webIsElementSelected</h1>
	 * This webIsElementSelected - Returns true if checkbox / radiobutton is selected
	 */
	public boolean webIsElementSelected(WebElement ele) {
		return ele.isSelected();
	}

	/**
	 * <h1>waitForPageLoad</h1>
	 * This waitForPageLoad - waits for the browser to load completely
	 */
	public void waitForPageLoad() {
		Wait<WebDriver> wait = new WebDriverWait(driver, driverWaitTime);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	/**
	 * <h1>pressTab</h1>
	 * This pressTab - presses Keyboard Tab Key
	 * Returns true boolean if the operation is successful
	 */
	public boolean pressTab() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();
		return true;
	}
	
	/**
	 * <h1>pressEnter</h1>
	 * This pressEnter - presses Keyboard Enter Key
	 * Returns true boolean if the operation is successful
	 */
	public boolean pressEnter() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();
		return true;
	}
	
	/**
	 * <h1>takeWebScreenShot</h1>
	 * This takeWebScreenShot method Take screenshot for web app
	 */
	public boolean takeWebScreenShot(ScenarioContext sc, String content) {
		final byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
		sc.getScenario().attach(screenshot, "image/png", content);
		return true;
	}

}
