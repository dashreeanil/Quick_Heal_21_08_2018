package com.urlcat.reset;

import org.testng.annotations.Test;

import Com.Lib.GenericLib;

public class Test_Automation_ResetData {
	@Test
	public void updateUrlCategory() throws Exception {

		String sUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName,
				GenericLib.sServerCoulmnUrlName, 1);
		String rDomainFlag[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath,GenericLib.sCacheSheetName, GenericLib.sCacheCoulmnDomainFlagName, 1);
		String rUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,
				GenericLib.sCacheCoulmnUrlName, 0);
		String sCategory[] = GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath,
				GenericLib.sCacheSheetName, "Category", 2);
		String flag = "M";
		for (int i = 0; i < 5; i++) {
			GenericLib.setCellData(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName, 2, "A", i + 1);
			GenericLib.setCellData(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName, 3, "12", i + 1);
			
		}
		for (int i = 0; i < rDomainFlag.length; i++) {
			if (rDomainFlag[i].equals(flag)) {
				for (int j = 0; j < rUrl.length; j++) {
					if (sUrl[i].equals(rUrl[j])) {
						GenericLib.setCellData(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName, 1,
								"A", j + 1);
						GenericLib.setCellData(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName, 2,
								"14", j + 1);
					
					}

				}
		
	}
		}
	}
}
