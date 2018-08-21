package com.urlcat.tests.client;

import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Com.Lib.GenericLib;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Test_Automation_01 {
	
	
	@Test(description="Test to validate the domain present in cache")
	public void checkForDomain() throws Exception
	{
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./Reports/Client/Test_Automation_01.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		ExtentTest test = extent.createTest("checkForDomain", "Test to validate the domain present in cache");
		String sdata[]=GenericLib.readExcelDataOfColumn(GenericLib.sInputDataFilePath, GenericLib.sInputSheetName, GenericLib.sInputCoulmnUrlName,0);
		for(int i=0;i<=sdata.length-1;i++){
			String[] urlInfo=GenericLib.checkClientCache(GenericLib.sCacheDataFilePath,sdata[i]);
			
			test.log(Status.INFO, "Input Url that needed to be checked is "+sdata[i]);
			if(urlInfo!=null){
				
			GenericLib.setLastCellDataUrl(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName , GenericLib.sCacheCoulmnUrlName, urlInfo[1], 0);
			test.log(Status.INFO, "Url has been updated to cache"+urlInfo[1]);
			GenericLib.setLastCellDataCatagory(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName, GenericLib.sCacheCoulmnCategoryName,urlInfo[3],2);
			test.log(Status.INFO, "Category has been updated to cache--->"+urlInfo[3]);
			GenericLib.setLastCellDataDomain(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName,GenericLib.sCacheCoulmnDomainFlagName,urlInfo[2],1);
			test.log(Status.INFO, "Domain Flag has been updated to cache--->"+urlInfo[2]);
			Assert.assertEquals(urlInfo[1], sdata[i]);
			}
		}
		test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		test.addScreenCaptureFromPath("screenshot.png");
		extent.flush();
	}
	
	
}
