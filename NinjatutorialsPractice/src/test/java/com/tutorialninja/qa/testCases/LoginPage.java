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
import com.tutorialsninja.qa.PageObjects.AccountPageObj;
import com.tutorialsninja.qa.PageObjects.HomePageObj;
import com.tutorialsninja.qa.PageObjects.LoginPageObj;
 


/**
 * 
 */
public class LoginPage extends BaseClass{
//	TO GLOBALIZED THE DATA FROM TESTDATA
	
	LoginPageObj loginPage;
	AccountPageObj accountPage;
	
	public LoginPage() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void Setup() { 
		
		driver = initializedBrowserAndApplication(prop.getProperty("browser"));
		HomePageObj homePage = new HomePageObj(driver); 
		loginPage = homePage.navigatetoLoginPage();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	} 
	
// 	Data Provider
	@DataProvider(name="dataProvidertest")
	public Object[][] supplyTestData() {
		
		Object [][] data = Utilities.DataExcelProvider("LoginPage"); 
		return data;
		
	}
	
//	Valid Creds both Email and Password
	@Test(priority=1, dataProvider="dataProvidertest")
	public void verifyLoginWithValidCred(String ValidEmail, String ValidPassword, String InvalidEmail, String InvalidPassword) throws InterruptedException {
 
		accountPage = loginPage.LoginAccount(ValidEmail, ValidPassword); 
		
		Assert.assertTrue(accountPage.locateLinkTxt(), "Edit your account information not displayed"); 
		accountPage.clickLinkTxt(); 
//		Utilities.waitLoding();
	}
	 
//	Creds Valid Email and Invalid Password
	@Test(priority=2, dataProvider="dataProvidertest")
	public void verifyLoginValidEmailInvalidPassword(String ValidEmail, String ValidPassword, String InvalidEmail, String InvalidPassword) throws InterruptedException{
	 
		loginPage.LoginAccount(ValidEmail, InvalidPassword);
		
		String actualMassage = loginPage.getWarningMessage();
		String expectedMessage = dataProp.getProperty("expectedMessageInvalid");
		Assert.assertTrue(actualMassage.contains(expectedMessage), "Warning: No match for E-Mail Address and/or Password." );
//		Utilities.waitLoding();
	}
	
//	Cred Invalid Email and Valid Password
	@Test(priority=3, dataProvider="dataProvidertest")
	public void verifyLoginInvalidEmailValidPassword(String ValidEmail, String ValidPassword, String InvalidEmail, String InvalidPassword)  throws InterruptedException{
		
		 loginPage.LoginAccount(Utilities.generateEmailTimeStamp(), ValidPassword);
		
		String actualMassage = loginPage.getWarningMessage();
		String expectedMessage = dataProp.getProperty("expectedMessageInvalid");
		Assert.assertTrue(actualMassage.contains(expectedMessage), "Warning: No match for E-Mail Address and/or Password." );
//		Utilities.waitLoding();
	}
	
//	Invalid creds both Email and Password
	@Test(priority=4, dataProvider="dataProvidertest")
	public void verifyLoginWithInvalidCred(String ValidEmail, String ValidPassword, String InvalidEmail, String InvalidPassword) throws InterruptedException {
		 
		loginPage.LoginAccount(Utilities.generateEmailTimeStamp(), InvalidPassword);
		String actualMessage = loginPage.getWarningMessage();
		String expectedMessage = dataProp.getProperty("expectedMessageInvalid"); 
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Warning: No match for E-Mail Address and/or Password.");
//		Utilities.waitLoding(); 
	}
	
//	No Any input for email and password
	@Test(priority=5)
	public void verifyLoginWithNoInputs() {
		
		loginPage.clickLoginbtn();
		String actualMessage = loginPage.getWarningMessage();
		String expectedMessage = dataProp.getProperty("expectedMessageInvalid"); 
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Warning: No match for E-Mail Address and/or Password.");
//		Utilities.waitLoding(); 
	}
}
