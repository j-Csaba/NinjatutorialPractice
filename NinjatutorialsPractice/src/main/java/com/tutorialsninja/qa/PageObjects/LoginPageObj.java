package com.tutorialsninja.qa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObj {

	WebDriver driver;
	
//	PAGE SETUP/CONFIGURATION
	public LoginPageObj(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	FOR POSITIVE DETAILS
//	Email input Action
	@FindBy(id="input-email")
	private WebElement emailaddressField;
	public void inputEmailAddress(String EmailCreds) {
		emailaddressField.sendKeys(EmailCreds);
	}
	
//	Password input Action
	@FindBy(id="input-password")
	private WebElement passwordField;
	public void inputPassword(String PasswordCreds) {
		
		passwordField.sendKeys(PasswordCreds);
	}
	
//	Click Login button Action
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;	
	
	public AccountPageObj clickLoginbtn() {
		loginButton.click();
		return new AccountPageObj(driver);
	}
	
//	ONE LINE CODE FOR INPUT EMAIL/PASSWORD AND CLICK LOGIN ACTIONS
	public AccountPageObj LoginAccount(String EmailCreds, String PasswordCreds) {
		emailaddressField.sendKeys(EmailCreds);
		passwordField.sendKeys(PasswordCreds);
		loginButton.click();
		return new AccountPageObj(driver);
	}
	
	
//	FOR NEGATIVE DETAILS
//	Warning Message spiels
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement warningMessage;
	public String getWarningMessage() {
		String messageAlert = warningMessage.getText();
		return messageAlert;
	}
	
	
}
