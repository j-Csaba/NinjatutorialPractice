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
import com.tutorialsninja.qa.PageObjects.SearchPageObj;

/**
 * 
 */
public class SearchPageTest extends BaseClass{

	HomePageObj homeSearch;
	SearchPageObj searchProduct;
	
	public SearchPageTest() {
		super();
	}
	public WebDriver driver;
	
	@BeforeMethod
	public void setupSearch() {
		
		driver = initializedBrowserAndApplication(prop.getProperty("browser")); 
		homeSearch = new HomePageObj(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@DataProvider(name="SearchDataDetails")
	public Object[][] searchProder(){
		
		Object[][] data = Utilities.DataExcelProvider("SearchProduct");
		return data;
	}
	
//	With Existing Product
	@Test(priority=1, dataProvider="SearchDataDetails")
	public void SearchExistingProduct(String ExistingProducts, String NotExistingProduct) throws InterruptedException {
		  
		searchProduct = homeSearch.inputProductAndSearch(ExistingProducts); 
		Assert.assertTrue(searchProduct.searchResultItem());
//		Utilities.waitLoding();
	}
	
//	Wihtout any existing products
	@Test(priority=2, dataProvider="SearchDataDetails")
	public void SearchNotExitingProduct(String ExistingProducts, String NotExistingProduct) throws InterruptedException {
		
		searchProduct = homeSearch.inputProductAndSearch(NotExistingProduct);  
		Assert.assertEquals(searchProduct.spielsNotExistingProduct(),"abcd", "Not Existing Spiels not displayed"); 
//		Utilities.waitLoding();
	}
	
//	Without any input product
	@Test(priority=3, dependsOnMethods={"SearchExistingProduct","SearchNotExitingProduct"})
	public void SearchWithouthAnyProduct() throws InterruptedException {
		  
		searchProduct = homeSearch.clickSearchBtn(); 
		Assert.assertEquals(searchProduct.getPlaceHolder(), "", "Keywork field has text"); 
		Assert.assertEquals(searchProduct.spielsNotExistingProduct(),dataProp.getProperty("searchNotExitstingSpiels"), "Not Existing Spiels not displayed"); 
//		Utilities.waitLoding();
	}
}

