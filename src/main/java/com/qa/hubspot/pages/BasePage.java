package com.qa.hubspot.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.hubspot.eventlistener.WebEventListener;
import com.qa.hubspot.util.Constants;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public WebEventListener webEventListener;
	public EventFiringWebDriver e_driver;

	public WebDriver init_driver(String browser) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/NaveenKhunteta/Downloads/chromedriver");
			driver = new ChromeDriver();
		}
		else if(browser.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", "/Users/NaveenKhunteta/Downloads/geckodriver");
			driver = new FirefoxDriver();
		}

		driver.manage().window().fullscreen();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		webEventListener = new WebEventListener();
		e_driver = new EventFiringWebDriver(driver);
		e_driver.register(webEventListener);
		driver = e_driver;

		return driver;
		
		// hello    world   test
	}

	public Properties init_properties() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("/Users/NaveenKhunteta/Documents/workspace/OctPOMSeries/"
					+ "src/main/java/com/qa/hubspot/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}
