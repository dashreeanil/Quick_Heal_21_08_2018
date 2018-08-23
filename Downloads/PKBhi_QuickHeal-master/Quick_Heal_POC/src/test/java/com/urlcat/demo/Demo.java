package com.urlcat.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Com.Lib.GenericLib;
import Com.Lib.TestUtil;
import Com.ListnerUtil.ExtentReport;

public class Demo {

	public static int[] time(String sheetName,int colNum) throws Exception
	{
		 int[] newhr = null;
		 int hr;
		String sTimeStamp[]=GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath,sheetName,"TimeStamp",colNum);
		newhr = new int[sTimeStamp.length];
		 for(int i=0;i<sTimeStamp.length;i++)
		 {
			System.out.println(sTimeStamp[i]);
			 String [] arr = sTimeStamp[i].toString().split(" ");
			 System.out.println(arr[1]);
			 String [] serverTime= new String[arr.length];		 
			 String servertime = arr[1];
			 System.out.println(serverTime);
			 String [] timearr = servertime.split(":"); 
			 String hour = timearr[0];
			 System.out.println(hour);
			  hr = Integer.parseInt(hour);
			  newhr[i] = hr;

		 }
		 return newhr;
	}
	
	@Test
	public void demo() throws Exception {
int[] hour = time(GenericLib.sServerSheetName, 5)	;
int currenthour = GenericLib.hrCalculater(TestUtil.generateTimeStamp());
String sTimeStamp[]=GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath,GenericLib.sServerSheetName,"TimeStamp",5);
for(int i =0;i<sTimeStamp.length;i++)
{
	int diff = currenthour-hour[i];
	if(diff>3)
	{
		 GenericLib.setCellData(GenericLib.sServerDataFilePath,GenericLib.sServerSheetName,5,TestUtil.generateTimeStamp() ,i+1) ;
         System.out.println("Time Stamp has been updated to "+"--> "+TestUtil.generateTimeStamp());
	}
}



}
	
	
}
