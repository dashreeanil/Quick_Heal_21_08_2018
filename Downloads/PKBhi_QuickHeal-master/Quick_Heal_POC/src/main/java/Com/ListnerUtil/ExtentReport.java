package Com.ListnerUtil;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport{
	
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public static void initExtentReport(String path,String methodName,String testCaseDescp)
	{
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
		 extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		 test = extent.createTest(testCaseDescp);
	}

}
