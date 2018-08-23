package com.urlcat.tests.client;

import java.util.Random;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Com.Lib.GenericLib;
import Com.Lib.TestUtil;

public class Test_Automation_02 {

	@BeforeMethod
	public void modifyUrlCategory() throws Exception {
		GenericLib.modifyUrlCategory(5,18, "./Reports/Client/Test_Automation_modifyUrlCategory.html", "modifyUrlCategory", "It will randomly modify the category in server");
	}

	@Test
	public void updatedCategoryReceived() throws Exception {
		
		int[] hour = GenericLib.time(GenericLib.sServerSheetName, 5)	;
		int currenthour = GenericLib.hrCalculater(TestUtil.generateTimeStamp());
		String rUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,GenericLib.sCacheCoulmnUrlName, 0);
		String sTimeStamp[]=GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath,GenericLib.sServerSheetName,"TimeStamp",5);
		for(int j =0;j<rUrl.length;j++)
		{
			int diff = currenthour-hour[j+1];
			if(diff>3)
			{
				 GenericLib.setCellData(GenericLib.sServerDataFilePath,GenericLib.sServerSheetName,5,TestUtil.generateTimeStamp() ,j+1) ;
		         System.out.println("Time Stamp has been updated to "+"--> "+TestUtil.generateTimeStamp());
			}
		}
		
		for (int i = 0; i < rUrl.length; i++) {
			GenericLib.cacheRefreshRequest("./Reports/Client/Test_Automation_cacheRefreshRequest.html", "updatedCategoryReceived", "It will update the category in cache");

		}
		
		 GenericLib.verifyModifiedCategory("M","./Reports/Client/Test_Automation_02_Validation.html", "verifyModifiedCategory", "Validation");
		
	}



}
	

