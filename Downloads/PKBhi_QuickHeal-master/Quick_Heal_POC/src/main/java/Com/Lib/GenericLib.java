package Com.Lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class GenericLib{	
	static Logger logger;
	public static String sCacheDataFilePath ="C:\\Users\\dashree\\Downloads\\PKBhi_QuickHeal-master\\Quick_Heal_POC\\src\\main\\resources\\data\\URLCATData.xlsx";
	public static String sServerDataFilePath = "C:\\Users\\dashree\\Downloads\\PKBhi_QuickHeal-master\\Quick_Heal_POC\\src\\main\\resources\\data\\URLCATData.xlsx";
	public static String sInputDataFilePath ="C:\\Users\\dashree\\Downloads\\PKBhi_QuickHeal-master\\Quick_Heal_POC\\src\\main\\resources\\data\\URLCATData.xlsx";
	public static String sInputSheetName ="Input";
	public static String sServerSheetName ="Server";
	public static String sCacheSheetName ="Cache";
	public static String sCacheCoulmnUrlName="Url";
	public static String sCacheCoulmnCategoryName ="Category";
	public static String sCacheCoulmnDomainFlagName ="DomainFlag";
	public static String sInputCoulmnUrlName="Url";
	public static String sServerCoulmnUrlName="Url";
	/*
	 * @author:Anil & Pawan
	 * 
	 * Description: To read the basic environment settings data from config file
	 * based on Property file value
	 */
	public static String getProprtyValue(String sFile, String sKey) {
		Properties prop = new Properties();
		String sValue = null;
		try {
			InputStream input = new FileInputStream(sFile);
			prop.load(input);
			sValue = prop.getProperty(sKey);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sValue;
	}

	/*
	 * @author:Anil & Pawan
	 * 
	 * 
	 */
	public static Properties getPropertyFile(String sFile) {
		Properties prop = new Properties();
		String sValue = null;
		try {
			InputStream input = new FileInputStream(sFile);
			prop.load(input);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/*
	 * @author:Anil & Pawan 
	 * 
	 * 
	 */
	public static void setPropertyValue(String sFile, String sKey, String sValue) {
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File(sFile));
			prop.load(fis);
			fis.close();
			FileOutputStream fos = new FileOutputStream(new File(sFile));
			prop.setProperty(sKey, sValue);
			prop.store(fos, "Updated  file with " + "Key " + sKey + " and Value " + sValue);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @author: Anil & Pawan 
	 * 
	 * Description:To read test data from excel sheet based on TestcaseID
	 */
	public static String[] readExcelData(String sFilepath, String sSheet, String urlID,int colNum) {
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilepath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			for (int i = 0; i <= iRowNum; i++) {
				if (sht.getRow(i).getCell(colNum).toString().equals(urlID)) {
					int iCellNum = sht.getRow(i).getPhysicalNumberOfCells();
					sData = new String[iCellNum];
					for (int j = 0; j < iCellNum; j++) {
						sData[j] = sht.getRow(i).getCell(j).getStringCellValue();
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}

	/*
	 * @author: Anil & Pawan 
	 * 
	 * Description: Method to read data based on row
	 * header
	 */

	public static int getColumnIndex(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.readExcelData(filepath, sSheet, "TEST_CASE_NO",0);
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}
	public static int getColumnIndexUrlId(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.readExcelData(filepath, sSheet, "UrlID",0);
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}

	public static int getColumnIndexCatogary(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.readExcelData(filepath, sSheet, colName,1);
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}
	
	public static int getColumnIndex(String filepath, String sSheet, String colName,int colNum) {
		String[] firstRow = GenericLib.readExcelData(filepath, sSheet,colName,colNum);
		int index = -1;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}
	
	

	/*
	 * @author: Anil & Pawan 
	 * 
	 * Description: Method to read data based on row
	 * header
	 */

	public static int getProdColumnIndex(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.readExcelData(filepath, sSheet, "Category",1);
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}

	
	/*
	 * @author: Anil & Pawan Description: Method to read data based on row
	 * header
	 */

	public static int getHeaderColumnIndex(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.readExcelData(filepath, sSheet, "SI No",0);
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}
	
	/*
	 * @author: Anil & Pawan 
	 * Description:Method is used to set data in excel sheet
	 */

	public static void setCellData(String filePath, String sSheet, String sTestCaseID, String columnName, String value)
			throws Exception {
		int columnNumber = getColumnIndex(filePath, sSheet, columnName);
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			// logger.info("----------Sheet " + sSheet);
			int lastRowNum = sht.getLastRowNum();
			for (int i = 0; i <= lastRowNum; i++) {
				if (sht.getRow(i).getCell(0).toString().equals(sTestCaseID)) {
					Row rowNum = sht.getRow(i);
					Cell cell = rowNum.getCell(columnNumber);
					if (cell == null) {
						cell = rowNum.createCell(columnNumber);
						cell.setCellValue(value);
						System.out.println("The Request is succusesfully added"+value);
					} else {
						cell.setCellValue(value);
					}
					break;
				}
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
	
	public static void setCellDataUrlId(String filePath, String sSheet, String sTestCaseID, String columnName, String value)
			throws Exception {
		int columnNumber = getColumnIndexUrlId(filePath, sSheet, columnName);
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			// logger.info("----------Sheet " + sSheet);
			int lastRowNum = sht.getLastRowNum();
			for (int i = 0; i <= lastRowNum; i++) {
				if (sht.getRow(i).getCell(0).toString().equals(sTestCaseID)) {
					Row rowNum = sht.getRow(i);
					Cell cell = rowNum.getCell(columnNumber);
					if (cell == null) {
						cell = rowNum.createCell(columnNumber);
						cell.setCellValue(value);
						System.out.println("The Request is succusesfully added"+value);
					} else {
						cell.setCellValue(value);
					}
					break;
				}
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
	
	public static void setCellDataCatogary(String filePath, String sSheet, String sTestCaseID, String columnName, String value)
			throws Exception {
		int columnNumber = getColumnIndexCatogary(filePath, sSheet, columnName);
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			// logger.info("----------Sheet " + sSheet);
			int lastRowNum = sht.getLastRowNum();
			for (int i = 0; i <= lastRowNum; i++) {
				if (sht.getRow(i).getCell(0).toString().equals(sTestCaseID)) {
					Row rowNum = sht.getRow(i);
					Cell cell = rowNum.getCell(columnNumber);
					if (cell == null) {
						cell = rowNum.createCell(columnNumber);
						cell.setCellValue(value);
						System.out.println("The Request is succusesfully added"+value);
					} else {
						cell.setCellValue(value);
					}
					break;
				}
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
	

	/*
	 * @author: 
	 * Description: To split and return the array
	 */
	public static String[] getSplittedArray(String str, String splitChar) {
		return str.split(splitChar);
	}
	/*
	 * @author:
	 * Description: Extract the string based on previous and next strings and occurrences
	 */
	public static String getString(String str, String startStr, int startOccurance, String endStr, int endOccurance) {
		return str.substring(str.indexOf(startStr, startOccurance) + startStr.length(),
				str.indexOf(endStr, endOccurance));
	}

	/*
	 * @author:
	 * Description: Extract the string based on previous and next strings
	 */
	public static String getString(String str, String startStr, String endStr) {
		return str.substring(str.indexOf(startStr) + startStr.length(), str.indexOf(endStr));
	}
	

		
	/* 
	 * description : read a particular column data  
	 * 
	 * 
	 * 
	 */
	public static String[] readExcelDataOfColumn(String sFilepath, String sSheet, String colName,int colNum) {
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilepath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			sData = new String[iRowNum];
			int iCellNum = getColumnIndexOfColumn(sFilepath, sSheet, colName,colNum);
			for (int i = 1; i <= iRowNum+2; i++) {
					//sData = new String[iRowNum];
					sData[i-1] = sht.getRow(i).getCell(iCellNum).toString();
				}
			}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}
	

	
	public static void validationClientCache(String url,int colNum) throws Exception
	{
		String sData[] = readExcelData(sCacheDataFilePath, "Sheet1",url,colNum);
		int urlInd = getColumnIndex(sCacheDataFilePath, "Sheet1", "Url",colNum);
		String expUrl = sData[urlInd];
		System.out.println(expUrl);
		if(expUrl.equalsIgnoreCase(url))
		{
			System.out.println("Url updated");
		}
		else {
			System.out.println("Url is not updated");
			Assert.fail();
		}
		
	}
	

	/*
	 * 
	 * Description:Method is used to set data in last row  of excel sheet
	 */

	public static void setLastCellDataUrl(String filePath, String sSheet,String columnName, String value,int colNum)
			throws Exception {
		int columnNumber = colNum;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			// logger.info("----------Sheet " + sSheet);
			int lastRowNum = sht.getLastRowNum()+1;
			System.out.println(lastRowNum);
			Row row = sht.createRow(lastRowNum);
			Row rowNum = sht.getRow(lastRowNum);
			Cell cell = rowNum.getCell(columnNumber);
			if (cell == null) {
				cell = rowNum.createCell(columnNumber);
				cell.setCellValue(value);
				System.out.println("The Request is succusesfully added "+value);
			} else {
					cell.setCellValue(value);
       				}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
	
		
		public static void setLastCellDataCatagory(String filePath, String sSheet,String columnName, String value,int colNum)
				throws Exception {
			int columnNumber = colNum;
			try {
				FileInputStream fis = new FileInputStream(filePath);
				Workbook wb = (Workbook) WorkbookFactory.create(fis);
				Sheet sht = wb.getSheet(sSheet);
				// logger.info("----------Sheet " + sSheet);
				int lastRowNum = sht.getLastRowNum();
				System.out.println(lastRowNum);
				
				Row rowNum = sht.getRow(lastRowNum);
				Cell cell = rowNum.getCell(columnNumber);
				if (cell == null) {
					cell = rowNum.createCell(columnNumber);
					cell.setCellValue(value);
					System.out.println("The Request is succusesfully added "+value);
				} else {
						cell.setCellValue(value);
	       				}
				FileOutputStream fileOut = new FileOutputStream(filePath);
				wb.write(fileOut);
				fileOut.flush();
				fileOut.close();
			} catch (Exception e) {
				throw (e);
			}
}
		public static void setServerCellData(String filePath, String sSheet,String columnName, String value,int colNum,int positionUrl)
				throws Exception {
			int columnNumber = colNum;
			try {
				FileInputStream fis = new FileInputStream(filePath);
				Workbook wb = (Workbook) WorkbookFactory.create(fis);
				Sheet sht = wb.getSheet(sSheet);
				// logger.info("----------Sheet " + sSheet);
				int lastRowNum = sht.getLastRowNum()-positionUrl;
				System.out.println(lastRowNum);
				
				Row rowNum = sht.getRow(lastRowNum);
				Cell cell = rowNum.getCell(columnNumber);
				if (cell == null) {
					cell = rowNum.createCell(columnNumber);
					cell.setCellValue(value);
					System.out.println("The Request is succusesfully added "+value);
				} else {
						cell.setCellValue(value);
	       				}
				FileOutputStream fileOut = new FileOutputStream(filePath);
				wb.write(fileOut);
				fileOut.flush();
				fileOut.close();
			} catch (Exception e) {
				throw (e);
			}
}
		public static void setLastCellDataDomain(String filePath, String sSheet,String columnName, String value,int colNum)
				throws Exception {
			int columnNumber = colNum;
			try {
				FileInputStream fis = new FileInputStream(filePath);
				Workbook wb = (Workbook) WorkbookFactory.create(fis);
				Sheet sht = wb.getSheet(sSheet);
				// logger.info("----------Sheet " + sSheet);
				int lastRowNum = sht.getLastRowNum();
				System.out.println(lastRowNum);
				
				Row rowNum = sht.getRow(lastRowNum);
				Cell cell = rowNum.getCell(columnNumber);
				if (cell == null) {
					cell = rowNum.createCell(columnNumber);
					cell.setCellValue(value);
					System.out.println("The Request is succusesfully added "+value);
				} else {
						cell.setCellValue(value);
	       				}
				FileOutputStream fileOut = new FileOutputStream(filePath);
				wb.write(fileOut);
				fileOut.flush();
				fileOut.close();
			} catch (Exception e) {
				throw (e);
			}
}
		
		/*extracting content of excle by column index
		 * 
		 * 
		 * */
		
	public 	ArrayList<String> extractExcelContentByColumnIndex(int columnIndex){
	        ArrayList<String> columndata = null;
	        try {
	            File f = new File("sample.xlsx");
	            FileInputStream ios = new FileInputStream(f);
	            XSSFWorkbook workbook = new XSSFWorkbook(ios);
	            XSSFSheet sheet = workbook.getSheetAt(0);
	            Iterator<Row> rowIterator = sheet.iterator();
	            columndata = new ArrayList<String>();

	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
	                Iterator<Cell> cellIterator = row.cellIterator();
	                while (cellIterator.hasNext()) {
	                    Cell cell = cellIterator.next();

	                    if(row.getRowNum() > 0){ //To filter column headings
	                        if(cell.getColumnIndex() == columnIndex){// To match column index
	                            switch (cell.getCellType()) {
	                            case Cell.CELL_TYPE_NUMERIC:
	                                columndata.add(cell.getNumericCellValue()+"");
	                                break;
	                            case Cell.CELL_TYPE_STRING:
	                                columndata.add(cell.getStringCellValue());
	                                break;
	                            }
	                        }
	                    }
	                }
	            }
	            ios.close();
	            System.out.println(columndata);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return columndata;
	    }
	
	public static void setCellData(String path,String sheetName, int colNumber, String value)
    {
		FileInputStream fis = null;
	    FileOutputStream fos = null;
	    XSSFWorkbook workbook = null;
	    XSSFSheet sheet = null;
	    XSSFRow row = null;
	    XSSFCell cell = null;
	    String xlFilePath;
	    int rowNum;
        try
        {
            sheet = workbook.getSheet(sheetName);
            rowNum = sheet.getLastRowNum()+1;
            row = sheet.getRow(rowNum);
            if(row==null)
                row = sheet.createRow(rowNum);
            for(int i=0;i<2;i++) {
            cell = row.getCell(colNumber+i);
            if(cell == null)
                cell = row.createCell(colNumber);
 
            cell.setCellValue(value);
            }
            fos = new FileOutputStream(path);
            workbook.write(fos);
            fos.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
           
        }
      
    }
	/*
	 * 
	 * 
	 * 
	 */
	
	public static void refresh(String url) throws Exception {
		
		setLastCellDataUrl(sCacheDataFilePath, "Sheet1", "Url", url, 0);
		setLastCellDataCatagory(sCacheDataFilePath, "Sheet1", "Category","13",1);
		setLastCellDataDomain(sCacheDataFilePath,"Sheet1","DomainFlag","M",2);
	}
	
	
	
	/*
	 * @author: Anil & Pawan 
	 * 
	 * Description:To read test data from excel sheet based on TestcaseID
	 */
	public static String[] readExcelDataOfParticulrColumn(String sFilepath, String sSheet, String colName,int colNum) {
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilepath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			for (int i = 0; i <= iRowNum; i++) {
				if (sht.getRow(i).getCell(colNum).toString().equals(colName)) {
					int iCellNum = sht.getRow(i).getPhysicalNumberOfCells();
					sData = new String[iCellNum];
					for (int j = 0; j < iCellNum; j++) {
						sData[j] = sht.getRow(i).getCell(j).getStringCellValue();
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}

	/*
	 * @author: Anil & Pawan 
	 * 
	 * Description: Method to read data based on row
	 * header
	 */

	public static int getColumnIndexOfColumn(String filepath, String sSheet, String colName, int colNum) {
		String[] firstRow = GenericLib.readExcelDataOfParticulrColumn(filepath, sSheet, colName,colNum);
		int index =1 ;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}
	
	public static String[] checkClientCache(String sFilepath,String urlRequest) throws EncryptedDocumentException, InvalidFormatException, IOException{
		String[] rowData = null ;
		boolean flag=true;
		String sdata[]=GenericLib.readExcelDataOfColumn(sFilepath,sCacheSheetName, sCacheCoulmnUrlName, 0);
		for(int i=0;i<sdata.length;i++){
			if(sdata[i].equals(urlRequest)){
				System.out.println("Requested Url Is Present In Client Cache"+" "+urlRequest);
				flag=false;
				break;
			}}
		if(flag==true){
			String[] urlData=GenericLib.serverResponse(urlRequest);
			rowData=new String[urlData.length];
			for (int j = 0; j < urlData.length; j++) {
				rowData[j] = urlData[j];
				}		}			
			return rowData;
	
			}
			
	
	public static String[] serverResponse(String requestedUrl) throws EncryptedDocumentException, InvalidFormatException, IOException{
		String[] rowData = null;
		String sdata[]=GenericLib.readExcelDataOfColumn(sServerDataFilePath,sServerSheetName,sServerCoulmnUrlName, 1);
		for(int i=0;i<sdata.length;i++){
			if(sdata[i].equals(requestedUrl)){
				System.out.println(i);
				FileInputStream fis = new FileInputStream(GenericLib.sServerDataFilePath);
				Workbook wb = (Workbook) WorkbookFactory.create(fis);
				Sheet sht = wb.getSheet(sServerSheetName);
				int iCellNum = sht.getRow(i).getPhysicalNumberOfCells();
				rowData=new String[iCellNum];
				for (int j = 0; j < iCellNum-1; j++) {
					rowData[j] = sht.getRow(i+1).getCell(j).getStringCellValue();
					System.out.println(rowData[j]);
					}
					break;
				}
				
			}
		return rowData;
			
		}
	
	public static void setCellData(String path,String sheetName, int colNumber, String value,int rowNum) throws Exception
    {try{
		FileInputStream fis = new FileInputStream(path);
	Workbook wb = (Workbook) WorkbookFactory.create(fis);
	Sheet sht = wb.getSheet(sheetName);
	// logger.info("----------Sheet " + sSheet);
	Row srowNum = sht.getRow(rowNum);
	Cell cell = srowNum.getCell(colNumber);
	if (cell == null) {
		cell = srowNum.createCell(colNumber);
		cell.setCellValue(value);
		System.out.println("The Request is succusesfully modified "+value);
	} else {
			cell.setCellValue(value);
				}
	FileOutputStream fileOut = new FileOutputStream(path);
	wb.write(fileOut);
	fileOut.flush();
	fileOut.close();
} catch (Exception e) {
	throw (e);
 }
      
    }
	
	public static void cacheRefreshRequest() throws Exception{
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./Reports/Client/cacheRefreshRequest_Test_Automation_02.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		ExtentTest test = extent.createTest("modifyUrlCategory", "Modification of Url Category");
		String rUrl[]=GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath, sCacheSheetName,sCacheCoulmnUrlName,0);
		String rDomainFlag[]=GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath,sCacheSheetName,sCacheCoulmnDomainFlagName,1);
		String sUrl[]=GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath,sServerSheetName,sServerCoulmnUrlName,1);
		String sDomainFlag[]=GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath, sServerSheetName, "DomainFlag",2);
		String sCategory[]=GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath,sServerSheetName, "Category",3);
		for(int i=0;i<rUrl.length;i++){
			for(int j=0;j<sUrl.length;j++){
				if(rUrl[i].equals(sUrl[j])){
					if(rDomainFlag[i].equals(sDomainFlag[j])){
						System.out.println("Category is not changed"+"-->"+rUrl[i]);
						test.log(Status.INFO, "Category is not changed"+"-->"+rUrl[i]);

						
						break;
					}	
					
					else{
						GenericLib.setCellData(GenericLib.sCacheDataFilePath,sCacheSheetName,1,sDomainFlag[j] ,i+1) ;
						GenericLib.setCellData(GenericLib.sCacheDataFilePath,sCacheSheetName,2,sCategory[j] ,i+1) ;
						System.out.println("Category is updated changed"+"-->"+sDomainFlag[j]);
						test.log(Status.INFO, "Category is updated changed"+"-->"+sDomainFlag[j]);
						break;
					}
				}
			}
		}
		test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		test.addScreenCaptureFromPath("screenshot.png");
		extent.flush();
	}
	
	
	
	public static void modifyUrlCategory(int urlUpdate,int categoryId) throws Exception {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./Reports/Client/modifyUrlCategory_Test_Automation_02.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		ExtentTest test = extent.createTest("modifyUrlCategory", "Modification of Url Category");
		String rUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sCacheDataFilePath, GenericLib.sCacheSheetName,GenericLib.sCacheCoulmnUrlName, 0);
		String sUrl[] = GenericLib.readExcelDataOfColumn(GenericLib.sServerDataFilePath,GenericLib.sServerSheetName,GenericLib.sCacheCoulmnUrlName, 1);
		int count=categoryId;
		for (int i = 0; i < urlUpdate; i++) {
			Random rnd=new  Random();
		    int number=rnd.nextInt(rUrl.length-1);
		    if(number<0){
		    	number=number+2;
		    }
			for (int j = 0; j < sUrl.length; j++) {
				if (rUrl[number].equals(sUrl[j])) {
					count++;
					GenericLib.setCellData(GenericLib.sServerDataFilePath,GenericLib.sServerSheetName, 2, "M", j + 1);
					test.log(Status.INFO, "Server Domain flag has been changed to ----> M");
					GenericLib.setCellData(GenericLib.sServerDataFilePath,GenericLib.sServerSheetName, 3, count+++"", j + 1);
					test.log(Status.INFO, "Server Category has been changed to ----> "+count++);
					GenericLib.setCellData(GenericLib.sServerDataFilePath, GenericLib.sServerSheetName, 5, TestUtil.generateTimeStamp(),j+1);
					test.log(Status.INFO, "Server Time Stamp has been changed to ----> "+TestUtil.generateTimeStamp());

				}
			}
		}
		test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		test.addScreenCaptureFromPath("screenshot.png");
		extent.flush();
	}
	 
}


