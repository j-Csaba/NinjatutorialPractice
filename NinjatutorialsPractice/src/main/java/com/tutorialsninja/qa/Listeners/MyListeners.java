/**
 * 
 */
package com.tutorialsninja.qa.Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utils.ExtentReporter;
import com.tutorialninja.qa.utils.Utilities;

/**
 * 
 */
public class MyListeners implements ITestListener{
	ExtentReports extentReport;
	ExtentTest extentTest; 
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of project test is Started");
		extentReport = ExtentReporter.generateExtentReport(); 
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		 
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName() + " Started Execution"); 
		
	}

	@Override
	public void onTestSuccess(ITestResult result) { 
		 extentTest.log(Status.PASS, result.getName()+ " Got Successfully Executed"); 
	}

	@Override
	public void onTestFailure(ITestResult result) { 
		 
		 System.out.println("Screenshot Taken");
		 
		 WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) { 
			e.printStackTrace();
		}
		
//		to call the method of screenshot from utilities
		 String destinationScreenshotPath = Utilities.captureScreenShot(driver, result.getName());
		 extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		 extentTest.log(Status.INFO, result.getThrowable());
		 extentTest.log(Status.FAIL, result.getName() + " Got Failed"); 
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		 
	 extentTest.log(Status.INFO, result.getThrowable());
	 extentTest.log(Status.SKIP, result.getName() + " Got Skipped");  
	}
	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush(); 
		
//		to auto launch the extentRpoert
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentrepot = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentrepot.toURI());
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
	} 
}
