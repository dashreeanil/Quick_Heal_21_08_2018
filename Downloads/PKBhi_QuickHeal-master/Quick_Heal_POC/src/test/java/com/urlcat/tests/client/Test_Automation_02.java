package com.urlcat.tests.client;

import java.util.Random;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Com.Lib.GenericLib;

public class Test_Automation_02 {

	@BeforeMethod
	public void modifyUrlCategory() throws Exception {
		GenericLib.modifyUrlCategory(5,200);
	}

	@Test
	public void updatedCategoryReceived() throws Exception {
		String rUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,GenericLib.sCacheCoulmnUrlName, 0);
		for (int i = 0; i < rUrl.length; i++) {
			GenericLib.cacheRefreshRequest();
		}
	}



}
