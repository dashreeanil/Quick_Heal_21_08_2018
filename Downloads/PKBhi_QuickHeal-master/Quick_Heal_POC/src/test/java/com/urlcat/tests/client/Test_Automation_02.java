package com.urlcat.tests.client;

import java.util.Random;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Com.Lib.GenericLib;
import Com.Lib.TestUtil;
import Com.ListnerUtil.ExtentReport;

public class Test_Automation_02 {

	@BeforeMethod
	public void modifyUrlCategory() throws Exception {
		GenericLib.modifyUrlCategory(4, 30, "./Reports/Client/Test_Automation_modifyUrlCategory.html",
				"modifyUrlCategory", "It will randomly modify the category in server");
	}

	@Test
	public void updatedCategoryReceived() throws Exception {
		ExtentReport.initExtentReport("./Reports/Client/Test_Automation_02_cacheRefreshRequestTimeStamp.html",
				"updatedTimeStamp", "It will update the Time Stamp in cache");
		int[] hour = GenericLib.time(GenericLib.sServerSheetName, 5);
		int currenthour = GenericLib.hrCalculater(TestUtil.generateTimeStamp());
		String rUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,
				GenericLib.sCacheCoulmnUrlName, 0);
		String sTimeStamp[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath,
				GenericLib.sServerSheetName, "TimeStamp", 5);
		for (int j = 0; j < sTimeStamp.length; j++) {
			int diff = currenthour - hour[j];
			if (diff > 3) {

				GenericLib.setCellData(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName, 5,
						TestUtil.generateTimeStamp(), j);
				System.out.println("Time Stamp has been updated to " + "--> " + TestUtil.generateTimeStamp());
				ExtentReport.test.log(Status.INFO,
						"Category is not updated for the URL " + "--> " + TestUtil.generateTimeStamp());
			}
		}

		GenericLib.mServerToCache("M", "./Reports/Test_Automation_02_ServerDomain_Updation.html", "mServerToCache",
				"Updating the domain urls in server");
		for (int i = 0; i < rUrl.length; i++) {
			GenericLib.cacheRefreshRequest("./Reports/Test_Automation_02_CacheDomain_Updation.html",
					"cacheRefreshRequest", "Updating the domain urls in cache");

		}
		GenericLib.verifyModifiedCategory("M", "./Reports/Client/Test_Automation_02_Validation.html",
				"verifyModifiedCategory", "Validation");
		GenericLib.validateDomain("M", "./Reports/Test_Automation_02_MasterServerDomain_Updation.html",
				"validateDomain", "Updating the domain urls in Master server");
	}

}
