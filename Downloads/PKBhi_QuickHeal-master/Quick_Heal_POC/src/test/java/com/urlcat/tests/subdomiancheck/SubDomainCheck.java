package com.urlcat.tests.subdomiancheck;

import org.testng.annotations.Test;

import Com.Lib.GenericLib;

public class SubDomainCheck {

	@Test
	public void subDomainCheck() throws Exception
	{
		GenericLib.validateDomain("M", "./Reports/MasterServerDomain_Updation.html", "validateDomain", "Updating the domain urls in Master server");
		GenericLib.mServerToCache("M","./Reports/ServerDomain_Updation.html", "mServerToCache", "Updating the domain urls in server");
		String rUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,GenericLib.sCacheCoulmnUrlName, 0);
		for (int i = 0; i < rUrl.length; i++) {
			GenericLib.cacheRefreshRequest("./Reports/CacheDomain_Updation.html", "cacheRefreshRequest", "Updating the domain urls in cache");
			
		}
		
	}
}

