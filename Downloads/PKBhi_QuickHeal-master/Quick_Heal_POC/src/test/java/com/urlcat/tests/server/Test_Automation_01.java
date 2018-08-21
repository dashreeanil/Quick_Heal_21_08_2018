package com.urlcat.tests.server;

import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Com.Lib.GenericLib;
import Com.Lib.TestUtil;

public class Test_Automation_01 {

	@Test
	public void updateFromServer() throws Exception {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./Reports/Server/Test_Automation_01.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		ExtentTest test = extent.createTest("updateFromServer", "Test to validate Server response refresh");
		String sUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName,
				GenericLib.sServerCoulmnUrlName, 1);
		 int count=12;
		for (int i = 0; i < 5; i++) {
			Random rnd=new  Random();
		    int number=rnd.nextInt(sUrl.length-1);
		    if(number<0){
		    	number=number+2;
		    }
			GenericLib.setCellData(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName, 2, "M", number);
			test.log(Status.INFO, "Server Domain flag has been modified to ----> M ");
			GenericLib.setCellData(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName, 3, count+"", number);
			test.log(Status.INFO, "Server Category has been modified to ---->"+count);
			GenericLib.setCellData(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName, 5, TestUtil.generateTimeStamp(),number);
			test.log(Status.INFO, "Server Time Stamp has been modified to ---->"+TestUtil.generateTimeStamp());
			count++;
		}
		String rUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,
				GenericLib.sCacheCoulmnUrlName, 0);
		String rDomainFlag[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName, GenericLib.sCacheCoulmnDomainFlagName, 1);
		String sUrl1[] = GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName,
				"Url", 1);
		String sDomainFlag[] = GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath,
				GenericLib.sServerSheetName, "DomainFlag", 2);
		String sCategory[] = GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath,
				GenericLib.sServerSheetName, "Category", 3);
		System.out.println(sCategory[3]);
		String flag = "M";
		boolean status = true;
		for (int i = 0; i < sDomainFlag.length; i++) {
			if (sDomainFlag[i].equals(flag)) {
				for (int j = 0; j < rUrl.length; j++) {
					if (sUrl1[i].equals(rUrl[j])) {
						GenericLib.setCellData(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName, 1,
								sDomainFlag[i], j + 1);
						test.log(Status.INFO, "Cache Domain flag has been modified to ----> "+sDomainFlag[i]);

						GenericLib.setCellData(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName, 2,
								sCategory[i], j + 1);
						test.log(Status.INFO, "Cache Category has been modified to ---->"+sCategory[i]);
						
						status = false;
					}

				}

				if (status == true) {
					GenericLib.setLastCellDataUrl(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,
							GenericLib.sCacheCoulmnUrlName, sUrl1[i], 0);
					test.log(Status.INFO, "Cache Url has been modified to ---->"+sUrl1[i]);
					GenericLib.setLastCellDataCatagory(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,
							GenericLib.sCacheCoulmnCategoryName, sCategory[i], 2);
					test.log(Status.INFO, "Cache Category has been modified to ---->"+sCategory[i]);
					GenericLib.setLastCellDataDomain(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,
							GenericLib.sCacheCoulmnDomainFlagName, sDomainFlag[i], 1);
					test.log(Status.INFO, "Cache Domain flag has been modified to ----> "+sDomainFlag[i]);
				}
				status = true;
			}
		}
		test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		test.addScreenCaptureFromPath("screenshot.png");
		extent.flush();

	}

	
}

