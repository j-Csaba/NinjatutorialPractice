package com.tutorialsninja.qa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObj {
	
	WebDriver driver; 
	
//	PAGE SETUP/CONFIGURATION
	public HomePageObj(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}  
//	Expand My Account drawer 
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropdown;
	public void clickMyAccountDrawer() {
		
		myAccountDropdown.click();
		
	}
	
//	Login Button Action
	@FindBy(linkText="Login")
	private WebElement LoginButton;
	public LoginPageObj clickLoginButton() {
		LoginButton.click();
		return new LoginPageObj(driver);
	}
	
	public LoginPageObj navigatetoLoginPage() {
		
		myAccountDropdown.click();
		LoginButton.click();
		return new LoginPageObj(driver);
		
	}
	
//	Register button Action
	@FindBy(linkText="Register")
	private WebElement RegisterButton;
	public RegisterPageObj clickRegisterButton() {
		
		RegisterButton.click();
		return new RegisterPageObj(driver);
	}
	
// 	For Search Action
	@FindBy(name="search")
	private WebElement searchField;
	public void inputProductOnSearch(String Search) {
		searchField.sendKeys(Search);
	}
	
//	For Search Click action
	@FindBy(xpath="//*[@id=\"search\"]/span/button")
	private WebElement searchButton;
	public SearchPageObj clickSearchBtn() {
		searchButton.click();
		return new SearchPageObj(driver);
	}
	
	public SearchPageObj inputProductAndSearch(String providProduct) {
		searchField.sendKeys(providProduct);
		searchButton.click();
		return new SearchPageObj(driver);
	}
	
	
}
