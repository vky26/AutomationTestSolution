package com.test.framework;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.android.AndroidDriver;
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
    
    public RemoteWebDriver makeMobDriver(String deviceVersion)  {
    	RemoteWebDriver driver = null;
        
    	switch (deviceVersion.toLowerCase()) {
            case "android":
            	File appDir = new File("src/test/resources/apps");
                File app = new File(appDir, "ApiDemosdebug.apk"); //Android Dummy APK
            	DesiredCapabilities capabilities = new DesiredCapabilities();
            	capabilities.setCapability("DEVICE_NAME", "AndroidDevice");
            	capabilities.setCapability("VERSION", "10"); 
            	capabilities.setCapability("platformName","Android");
                capabilities.setCapability("app", app.getAbsolutePath());
                capabilities.setCapability("automationName","uiautomator2");
				try {
				    //start appium session manually and open the avd from android studio
					driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	break;
            case "ios":
            	break;
        }
		return driver;
        
    }

}