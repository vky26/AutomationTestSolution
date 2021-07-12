package com.test.framework;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
 

/**
* <h1>InitDriver</h1>
* The InitDriver class handles the driver initialization for web. 
* The drivers are added to the framework, and driver path is mentioned for mac OS. We need to change the driver according the OS and Chrome Version its running 
*
* @author  Vignesh Parameswari
* @since   2021-03-24
*/

public class InitDriver {
    protected static final Logger log = LoggerFactory.getLogger(InitDriver.class);

    public RemoteWebDriver makeDriver(String driverType)  {
        switch (driverType.toLowerCase()) {
            case "chrome":
              ChromeOptions options = new ChromeOptions();
              //System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver/chromedriver");
              WebDriverManager.chromedriver().driverVersion("91.0.4472.114").setup();
              WebDriverManager.chromedriver().setup();
              return new ChromeDriver(options);
            case "firefox":   
             System.setProperty("webdriver.gecko.driver","./src/test/resources/driver/geckodriver");
             return new FirefoxDriver();
            default:
                return null;
        }
    }

}