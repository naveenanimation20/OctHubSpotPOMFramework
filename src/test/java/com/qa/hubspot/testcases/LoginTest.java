package com.qa.hubspot.testcases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.pages.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;

public class LoginTest {
	
	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	
	@Parameters("browser") 
	@BeforeMethod
	public void setUp(String browser){
		basePage = new BasePage();
		driver = basePage.init_driver(browser);
		prop = basePage.init_properties();
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest(){
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is: "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyForgotPassowrdLinkTest(){
		Assert.assertTrue(loginPage.verifyForgotMyPasswordLink());
	}
	
	@Test(priority=3)
	public void verifySignUpLinkTest(){
		Assert.assertTrue(loginPage.verifySignUpLink());
	}
	
	@Test(priority=4)
	public void loginTest(){
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

	
	
	
	
	
}
