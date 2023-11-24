/**
 * 
 */
package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

/**
 * 
 */
public class Utilities {
	
	
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_WAIT_TIME=10;
	
//	To generate timestamp for email method
	public static String generateEmailTimeStamp() {
		Date date = new Date();
		String TimeStamp = date.toString().replace(" ","_").replace(":","_");
		return "testing"+TimeStamp+"@gmail.com";
	}
	
//	Wait to loading method
	public static void waitLoding() throws InterruptedException {
		Thread.sleep(5000);
	}
//	to fetch data from Excel method
	public static Object[][] DataExcelProvider(String dataSheet) {
		
		File excelFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialsnin\\qa\\testData\\TutorialsNinjaTestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
		
			FileInputStream fileExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fileExcel);
			
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet  = workbook.getSheet(dataSheet); 
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object [][] data = new Object[rows] [cols];
		for(int i=0; i<rows; i++) {
			XSSFRow row = sheet.getRow(i+1); 
			
			for(int j=0; j<cols;j++) {
				
				XSSFCell cell = row.getCell(j); 
			  CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING:
					data [i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data [i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data [i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
	}

	
//	to take the screenshot method	
	public static String captureScreenShot(WebDriver driver, String testName) {
		 
		 File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 String destinationScreenshotPath = System.getProperty("user.dir")+ "\\Screenshot\\" + testName +".png";
		 
		 try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) { 
			e.printStackTrace();
		}
		 return destinationScreenshotPath;
	}
}
