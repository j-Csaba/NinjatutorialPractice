package com.tutorialsninja.qa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPageObj {
	
	WebDriver driver;
	
//	PAGE SETUP/CONFIGURATION
	public AccountPageObj(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
//	Edit your account information actions
	@FindBy(linkText="Edit your account information")
	private WebElement linkText;
	
	public boolean locateLinkTxt() {
	
		boolean linkTxt = linkText.isDisplayed();
		return linkTxt;
		
	} 
	
	public void clickLinkTxt() {
		linkText.click();
		
	}
}
