package com.urlcat.tests.client;

import org.testng.annotations.Test;


import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Com.Lib.GenericLib;
import Com.ListnerUtil.ExtentReport;

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

		ExtentReport.initExtentReport("./Reports/Client/Test_Automation_01.html","checkForDomain", "Test to validate the domain present in cache");
		String sdata[]=GenericLib.readExcelDataOfColumn(GenericLib.sInputDataFilePath, GenericLib.sInputSheetName, GenericLib.sInputCoulmnUrlName,0);
		for(int i=0;i<=sdata.length-1;i++){
			String[] urlInfo=GenericLib.checkClientCache(GenericLib.sCacheDataFilePath,sdata[i]);
			
			ExtentReport.test.log(Status.INFO, "Input Url that needed to be checked is "+sdata[i]);
			if(urlInfo!=null){
				
			GenericLib.setLastCellDataUrl(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName , GenericLib.sCacheCoulmnUrlName, urlInfo[1], 0);
			ExtentReport.test.log(Status.INFO, "URL has been updated to cache "+urlInfo[1]);
			GenericLib.setLastCellDataCatagory(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName, GenericLib.sCacheCoulmnCategoryName,urlInfo[3],2);
			ExtentReport.test.log(Status.INFO, "Category has been updated to cache---> "+urlInfo[3]);
			GenericLib.setLastCellDataDomain(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName,GenericLib.sCacheCoulmnDomainFlagName,urlInfo[2],1);
			ExtentReport.test.log(Status.INFO, "Domain Flag has been updated to cache---> "+urlInfo[2]);
			GenericLib.setLastCellDataTimeStamp(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName,"TimeStamp",urlInfo[5],3);
			ExtentReport.test.log(Status.INFO, "TimeStamp has been updated to cache---> "+urlInfo[5]);
			Assert.assertEquals(urlInfo[1], sdata[i]);
			}
		}
		ExtentReport.test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		ExtentReport.test.addScreenCaptureFromPath("screenshot.png");
		ExtentReport.extent.flush();
	}
	
	
}
