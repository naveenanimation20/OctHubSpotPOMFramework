package com.qa.hubspot.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.TestUtil;

public class HomePage extends BasePage{
	
	
	@FindBy(xpath = "//h1[text()='Sales Dashboard']")
	WebElement homePageHeader;
	
	@FindBy(xpath = "//span[@class='account-name ']")
	WebElement accountName;
	
	@FindBy(id = "nav-primary-contacts-branch")
	WebElement contactsTab;
	
	@FindBy(id = "nav-secondary-contacts")
	WebElement contactsLink;
	
//	@FindBy(xpath = "//a[text()='Naveen']")
//	List<WebElement> name;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		 
	
	public String getHomePageTitle(){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleIs(Constants.HOME_PAGE_TITLE));
		return driver.getTitle().trim();
	}
	
	public String getHomePageHeader(){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(homePageHeader));
		return homePageHeader.getText();
	}
	
	public String getLoggedInAccountName(){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(accountName));
		return accountName.getText();
	}
	
	public ContactsPage goToContactsPage(){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(contactsTab));
		contactsTab.click();
		TestUtil.shortWait();
		contactsLink.click();
		
		return new ContactsPage(driver);
	}
	
	

}
