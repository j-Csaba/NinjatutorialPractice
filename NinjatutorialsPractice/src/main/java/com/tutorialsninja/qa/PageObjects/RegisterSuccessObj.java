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
public class RegisterSuccessObj {

	WebDriver driver;
	
	public RegisterSuccessObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	Success Registrion Config
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement registerSuccessSpiels;
	
	public String regsSuccessSpiels() {
		String SpielsMessage = registerSuccessSpiels.getText();
		return SpielsMessage;
	}

//	Continue Button Action
	@FindBy(xpath="//*[@id=\"content\"]/div/div/a")
	private WebElement continueButton;
	public void clickContinueButton() {
		continueButton.click();
	}
	
	
}
