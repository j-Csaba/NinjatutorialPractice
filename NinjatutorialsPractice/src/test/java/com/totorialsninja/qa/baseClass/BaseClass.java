/**
 * 
 */
package com.totorialsninja.qa.baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialninja.qa.utils.Utilities;

/**
 * 
 */
public class BaseClass {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	
	public BaseClass() {
		
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Configuration.properties");
		
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsnin\\qa\\testData\\testdata.properties");
		
		try { 
		FileInputStream fils2 = new FileInputStream(dataPropFile);
		dataProp.load(fils2);
		}catch(Throwable e) {
			e.printStackTrace();

		}
		
		try {
		FileInputStream files = new FileInputStream(propFile);
		prop.load(files);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initializedBrowserAndApplication(String browserName) {
		 

		if(browserName.equalsIgnoreCase("chrome")) { 
			driver = new ChromeDriver();
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
			
		}else if(browserName.equalsIgnoreCase("safari")) {
			
			driver = new SafariDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	
}
