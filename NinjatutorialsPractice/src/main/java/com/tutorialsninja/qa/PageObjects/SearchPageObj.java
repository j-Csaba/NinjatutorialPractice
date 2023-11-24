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
public class SearchPageObj {
	
	WebDriver driver;
	
	public SearchPageObj(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 


//	Search result
	@FindBy(linkText="iPhone")
	private WebElement searchResult;
	public boolean searchResultItem() {
		boolean resultSearch = searchResult.isDisplayed();
		return resultSearch;
	}
//	Not Existing Product Spiels
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement notExistingSpiels;
	public String spielsNotExistingProduct() {
		String NotExistingSpiels = notExistingSpiels.getText();
		return NotExistingSpiels;
	}
//	Search Criteria placeholder when no input search
	@FindBy(xpath="//*[@id=\"input-search\"]")
	private WebElement noInputProduct;
	public String getPlaceHolder() {
		String PlaceHolder = noInputProduct.getText();
		return PlaceHolder;
	}
	
}
