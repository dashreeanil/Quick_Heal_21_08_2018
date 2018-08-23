package com.urlcat.tests.client;

import java.util.Random;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Com.Lib.GenericLib;

public class Test_Automation_02 {

	@BeforeMethod
	public void modifyUrlCategory() throws Exception {
		GenericLib.modifyUrlCategory(5,18, "./Reports/Client/Test_Automation_modifyUrlCategory.html", "modifyUrlCategory", "It will randomly modify the category in server");
	}

	@Test
	public void updatedCategoryReceived() throws Exception {
		String rUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,GenericLib.sCacheCoulmnUrlName, 0);
		for (int i = 0; i < rUrl.length; i++) {
			GenericLib.cacheRefreshRequest("./Reports/Client/Test_Automation_cacheRefreshRequest.html", "updatedCategoryReceived", "It will update the category in cache");
			
		}
		
		 GenericLib.verifyModifiedCategory("M","./Reports/Client/Test_Automation_02_Validation.html", "verifyModifiedCategory", "Validation");
		
	}



}
	

