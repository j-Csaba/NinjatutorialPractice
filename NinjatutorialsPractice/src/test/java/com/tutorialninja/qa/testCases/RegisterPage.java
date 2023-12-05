/**
 * 
 */
package com.tutorialninja.qa.testCases;  
import org.openqa.selenium.WebDriver;  
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.totorialsninja.qa.baseClass.BaseClass;
import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.PageObjects.HomePageObj; 
import com.tutorialsninja.qa.PageObjects.RegisterPageObj;
import com.tutorialsninja.qa.PageObjects.RegisterSuccessObj;
 
/**
 * 
 */
public class RegisterPage extends BaseClass{
	RegisterSuccessObj registerSuccess;
	RegisterPageObj registerPage;
	public WebDriver driver;
	
	public RegisterPage() {
		super();
	}
	
	@BeforeMethod
	public void setupRegister() { 
		

		driver = initializedBrowserAndApplication(prop.getProperty("browser"));
		HomePageObj homePage = new HomePageObj(driver);
		homePage.clickMyAccountDrawer();
		registerPage = homePage.clickRegisterButton(); 

	}
	
	@AfterMethod
	public void teaDown() {
		driver.quit();
	}
	
	@DataProvider(name="RegisterDetails")
	public Object[][] registerProvider(){
		
		Object[][] data = Utilities.DataExcelProvider("RegisterPage"); 
		return data;
		
	}
	
//	Success Register and redirect to my account page
	@Test(priority=1, dataProvider="RegisterDetails")
	public void verifyRegisteringAnAcountWithMandatoryFields(String firstName, String lastName, String Email, String TelephoneNum, String Password, String confirmPassowrd) throws InterruptedException {
		   
		registerPage.registerAccount(firstName, lastName, Utilities.generateEmailTimeStamp(), TelephoneNum, Password, Password);
		registerPage.clickAgreeBtn();
		registerSuccess = registerPage.clickContinueBtn();
		
		
		String actualSuccessHeading = registerSuccess.regsSuccessSpiels();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("successRegistered"), "Account Success page is not displayed");
		registerSuccess.clickContinueButton(); 
//		Utilities.waitLoding();
	}
	
//	 Fill all fields not clicked privacy policy
	@Test(priority=2, dataProvider="RegisterDetails")
	public void verifyRegisteringAccountProvidingAllFields(String firstName, String lastName, String Email, String TelephoneNum, String Password, String confirmPassowrd) throws InterruptedException {
		 
		  
		registerPage.registerAccount(firstName, lastName, Utilities.generateEmailTimeStamp(), TelephoneNum, Password, Password);
		registerPage.newletterClickYes();
		registerPage.clickContinueBtn(); 
		
		String alertAgreeNoTicked = registerPage.privacyPolicywWarning();
		Assert.assertTrue(alertAgreeNoTicked.contains(dataProp.getProperty("warningPrivacyPolicy")), "Warning message for Privacu Policy not displayed"); 
//		Utilities.waitLoding();
	}
	
//	Warning Email Already Exist
	@Test(priority=3, dataProvider="RegisterDetails")
	public void verifyRegisteringAccountWithExistingEmailAddress(String firstName, String lastName, String Email, String TelephoneNum, String Password, String confirmPassowrd) throws InterruptedException {
		 
		registerPage.registerAccount(firstName, lastName, Email, TelephoneNum, Password, Password);
		registerPage.newletterClickYes();
		registerPage.clickAgreeBtn(); 
		registerPage.clickContinueBtn();
		
		String alertEmailExist = registerPage.emailExitSpiels();
		Assert.assertTrue(alertEmailExist.contains(dataProp.getProperty("warningEmailAlreacyExist")), "Warning for email exist not displayed"); 
//		Utilities.waitLoding();
	}
	
//	Mandatory field required
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() throws InterruptedException {  
		 
		
		registerPage.clickContinueBtn();
		 
		Assert.assertTrue(registerPage.warningRegistrationSpiels(dataProp.getProperty("warningPrivacyPolicy"), dataProp.getProperty("warningFnameRequired"), dataProp.getProperty("warningLnameRequired")
				, dataProp.getProperty("warningEmailRequired"), dataProp.getProperty("warningTelNumRequired"), dataProp.getProperty("warningPasswordRequired")), "Some Message was not displayed"); 
		
//		Utilities.waitLoding();
	 
	
	
	}
	
}
