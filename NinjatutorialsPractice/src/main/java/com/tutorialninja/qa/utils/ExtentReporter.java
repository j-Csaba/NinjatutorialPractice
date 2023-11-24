/**
 * 
 */
package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream; 
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
/**
 * 
 */
public class ExtentReporter {

	public static ExtentReports generateExtentReport(){
		
		ExtentReports extentReport = new ExtentReports();
		File ExtentReportFile = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(ExtentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsuNinja Test Automation Report");
		sparkReporter.config().setDocumentTitle("TutorialsNinja Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configProp = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Configuration.properties");
		try {
		FileInputStream fisconfigProp = new FileInputStream(configPropFile);
		configProp.load(fisconfigProp);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browser"));
		extentReport.setSystemInfo("Operation System", System.getProperty("os.name"));
		extentReport.setSystemInfo("System Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		
		return extentReport;
	}
	
}
