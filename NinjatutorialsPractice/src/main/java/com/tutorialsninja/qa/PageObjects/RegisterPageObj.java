/**
 * 
 */
package com.tutorialsninja.qa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 */
public class RegisterPageObj {
	WebDriver driver;
//	PageFactory
	public RegisterPageObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	Firstname Config
	@FindBy(id="input-firstname")
	private WebElement firstnameField;
	
	public void inputFirstName(String firstnameDetail) {
		firstnameField.sendKeys(firstnameDetail);
	}
//	Lastname Config
	@FindBy(id="input-lastname")
	private WebElement lastnameField;
	
	public void inputLastName(String lastnameDetail) {
		lastnameField.sendKeys(lastnameDetail);
	}
	
//	Email Config
	@FindBy(id="input-email")
	private WebElement emailField;
	
	public void inputEmail(String emailDetail) {
		emailField.sendKeys(emailDetail);
	}

//	TelphoneNumber Config
	@FindBy(id="input-telephone")
	private WebElement telNumField;
	
	public void inputtelNum(String telNumDetail) {
		telNumField.sendKeys(telNumDetail);
	}
	
//	Password Config
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	public void inputPassword(String passwordDetail) {
		passwordField.sendKeys(passwordDetail);
	}

//	confirmPassword Config
	@FindBy(id="input-confirm")
	private WebElement confirmPassField;
	
	public void inputConfimPass(String confirmPassDetail) {
		confirmPassField.sendKeys(confirmPassDetail);
	}
	
//	Continue registration Config
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement ContinueBtn;
	
	public RegisterSuccessObj clickContinueBtn() {
		ContinueBtn.click();
		return new RegisterSuccessObj(driver);
	}
	
//	ONE LINE CODE FOR INPUT DETAILS ON REGISTRAION ACTION
	public RegisterSuccessObj registerAccount(String firstnameDetail,String lastnameDetail, String emailDetail, String telNumDetail, String passwordDetail, String confirmPassDetail) {
		firstnameField.sendKeys(firstnameDetail);
		lastnameField.sendKeys(lastnameDetail);
		emailField.sendKeys(emailDetail);
		telNumField.sendKeys(telNumDetail);
		passwordField.sendKeys(passwordDetail);
		confirmPassField.sendKeys(confirmPassDetail); 
		return new RegisterSuccessObj(driver);
	}
	
//	Agree Privacy Config
	@FindBy(name="agree")
	private WebElement AgreeButton;
	
	public void clickAgreeBtn() {
		AgreeButton.click();
	}

//	NewLetter radiobutton config
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement newsletterRadioYes;
	
	public void newletterClickYes() {
		newsletterRadioYes.click();
	}
	

	
//	Warning email already exit
	@FindBy(xpath="//div[contains(@class,'alert-danger')]")
	private WebElement emailAlreadyExit;
	
	public String emailExitSpiels() {
		String emailSpiels = emailAlreadyExit.getText();
		return emailSpiels;
	}
	
//	Warning for Privacy Policy required
	@FindBy(xpath="//div[contains(@class,'alert-danger')]")
	private WebElement privacyPolicywarningSpiels;
	
	public String privacyPolicywWarning() {
		String warningPrivacyPolicy = privacyPolicywarningSpiels.getText();
		return warningPrivacyPolicy;
	}
	
//	FirstName error spiels
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstnameErrorSpiels;
	
	public String fnameError() {
		String errorFname = firstnameErrorSpiels.getText();
		return errorFname;
	}
	
//	LastName error spiels
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastnameErrorSpiels;
	
	public String lnameError() {
		String errorLname = lastnameErrorSpiels.getText();
		return errorLname;
	}
	
//	Email error Spiels
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailerrorSpiels;
	
	public String emailerror() {
		String errorEmail = emailerrorSpiels.getText();
		return errorEmail;
	}
	
//	Telephone error spiels
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telNumErrorSpiels;
	
	public String telNumError() {
		String errortelNum = telNumErrorSpiels.getText();
		return errortelNum;
	}
	
//	Password error spiels
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordErrorSpiels;
	
	public String passwordError() {
		String errorPassword = passwordErrorSpiels.getText();
		return errorPassword;
	}
	
	public boolean warningRegistrationSpiels(String actualPrivacyWarning, String actualFNameWarning, String actualLNameWarning, String actualEmailWarning, String actualTelNumWarning, String actualPasswordWarning) {
		 
		boolean privacyWarning = privacyPolicywarningSpiels.getText().contains(actualPrivacyWarning);
		 
		boolean FNameWarning = firstnameErrorSpiels.getText().contains(actualFNameWarning);
		 
		boolean LNameWarning = lastnameErrorSpiels.getText().contains(actualLNameWarning);
		 
		boolean EmailWarning = emailerrorSpiels.getText().contains(actualEmailWarning);
		 
		boolean TelNumWarning = telNumErrorSpiels.getText().contains(actualTelNumWarning);
		 
		boolean PasswordWarning = passwordErrorSpiels.getText().contains(actualPasswordWarning);
		
		return privacyWarning && FNameWarning && LNameWarning && EmailWarning && TelNumWarning && PasswordWarning;
	}
	
	
	
	
	
}
