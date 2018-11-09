package com.qa.hubspot.testcases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.pages.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.TestUtil;

public class ContactsPageTest {
	
	
	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	public ContactsPage contactsPage;

	@Parameters("browser") 
	@BeforeMethod
	public void setUp(String browser) {
		basePage = new BasePage();
		driver = basePage.init_driver(browser);
		prop = basePage.init_properties();
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.goToContactsPage();
	}
	
	
	@DataProvider(name = "getContactsTestData")
	public Object[][] getContactsTestData(){
		return TestUtil.getTestData("contacts");
	}
	
	@Test(dataProvider = "getContactsTestData")
	public void createContactTest(String emailId, String firstName, String lastName, String jobTitle){
		contactsPage.createNewContact(emailId, firstName,  lastName, jobTitle );
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
	
	
	
	
	
	
	

}
