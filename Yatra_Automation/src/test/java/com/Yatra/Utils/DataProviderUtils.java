package com.Yatra.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataProviderUtils {
	/*private static String workbookName = "testdata\\data\\Flights.xls";
	private static String sheetName = "FlightPricing";*/
	private static boolean doFilePathMapping=true;

	private static EnvironmentPropertiesReader configProperty = EnvironmentPropertiesReader.getInstance();

	@DataProvider(parallel = true)
	public static Iterator<Object[]> parallelTestDataProvider(ITestContext context) throws IOException {

		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		List<String> browsers = null;
		List<String> platforms = null;
		List<String> browserVersions = null;
		List<String> deviceNames = null;
		String driverInitilizeInfo = null;
		String browser = null;
		String platform = null;
		String browserVersion = null;
		String deviceName = null;
		Iterator<String> browsersIt = null;
		Iterator<String> browserVersionsIt = null;
		Iterator<String> platformsIt = null;
		Iterator<String> deviceNameIt = null;

		// From local to sauce lab for browser test
		if (configProperty.hasProperty("runSauceLabFromLocal")
				&& configProperty.getProperty("runSauceLabFromLocal").equalsIgnoreCase("true")) {
			browser = configProperty.hasProperty("browserName") ? configProperty.getProperty("browserName") : null;
			browserVersion = configProperty.hasProperty("browserVersion") ? configProperty.getProperty("browserVersion")
					: null;
			platform = configProperty.hasProperty("platform") ? configProperty.getProperty("platform") : null;

			browsers = Arrays.asList(browser.split("\\|"));
			browserVersions = Arrays.asList(browserVersion.split("\\|"));
			platforms = Arrays.asList(platform.split("\\|"));

			browsersIt = browsers.iterator();
			browserVersionsIt = browserVersions.iterator();
			platformsIt = platforms.iterator();

			// From local to sauce lab for device test
			if (configProperty.hasProperty("runUserAgentDeviceTest")
					&& configProperty.getProperty("runUserAgentDeviceTest").equalsIgnoreCase("true")) {

				// handling parallel device test inputs
				deviceName = configProperty.hasProperty("deviceName") ? configProperty.getProperty("deviceName") : null;
				deviceNames = Arrays.asList(deviceName.split("\\|"));
				deviceNameIt = deviceNames.iterator();

				while (browsersIt.hasNext() && browserVersionsIt.hasNext() && platformsIt.hasNext()
						&& deviceNameIt.hasNext()) {
					browser = browsersIt.next();
					browserVersion = browserVersionsIt.next();
					platform = platformsIt.next();
					deviceName = deviceNameIt.next();
					driverInitilizeInfo = browser + "&" + browserVersion + "&" + platform + "&" + deviceName;
					dataToBeReturned.add(new Object[] { driverInitilizeInfo });
				}
			} else {
				// handling parallel browser test inputs
				while (browsersIt.hasNext() && browserVersionsIt.hasNext() && platformsIt.hasNext()) {
					browser = browsersIt.next();
					browserVersion = browserVersionsIt.next();
					platform = platformsIt.next();
					driverInitilizeInfo = browser + "&" + browserVersion + "&" + platform;
					dataToBeReturned.add(new Object[] { driverInitilizeInfo });
				}
			}

		} else {
			// local to local test execution and also handling parallel support
			browsers = Arrays.asList(context.getCurrentXmlTest().getParameter("browserName").split(","));
			for (String b : browsers) {
				dataToBeReturned.add(new Object[] { b });
			}
		}
		return dataToBeReturned.iterator();
	}

	@DataProvider(parallel = true)
	public static Iterator<Object[]> multiDataIterator(ITestContext context) throws IOException {

		File dir1 = new File(".");
		String strBasePath = null;
		String browserInputFile = null;
		String paymentTypeInputFile = null;
		strBasePath = dir1.getCanonicalPath();

		List<String> websites = Arrays.asList(context.getCurrentXmlTest().getParameter("webSite").split(","));
		browserInputFile = strBasePath + File.separator + "src" + File.separator + "main" + File.separator + "resources"
				+ File.separator + "DataProviders" + File.separator
				+ context.getCurrentXmlTest().getParameter("browserDataProvider");
		paymentTypeInputFile = strBasePath + File.separator + "src" + File.separator + "main" + File.separator
				+ "resources" + File.separator + "DataProviders" + File.separator
				+ context.getCurrentXmlTest().getParameter("paymentDataProvider");

		// Get a list of String file content (line items) from the test file.
		List<String> browserList = getFileContentList(browserInputFile);
		List<String> paymentTypes = getFileContentList(paymentTypeInputFile);

		// We will be returning an iterator of Object arrays so create that
		// first.
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();

		// Populate our List of Object arrays with the file content.
		for (String website : websites) {
			for (String browser : browserList) {
				for (String payment : paymentTypes)
					dataToBeReturned.add(new Object[] { browser, website, payment });
			}
		}

		// return the iterator - testng will initialize the test class and calls
		// the
		// test method with each of the content of this iterator.
		return dataToBeReturned.iterator();

	}// multiDataIterator

	/**
	 * Utility method to get the file content in UTF8
	 * 
	 * @param filenamePath
	 * @return
	 */
	private static List<String> getFileContentList(String filenamePath) {
		List<String> lines = new ArrayList<String>();
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filenamePath), "UTF8"));

			String strLine;
			while ((strLine = br.readLine()) != null) {
				lines.add(strLine);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}


	/**
	 * @author harveer.singh
	 * @return :Hashmap obj with all data
	 */

	@DataProvider
	public static Object[][] multipleExecutionData(Method method,ITestContext context)

	{   
		String sWorkBooName=context.getCurrentXmlTest().getParameter("workBookName");
		String sheetNameP=context.getCurrentXmlTest().getParameter("sheetName");
		String browserName=context.getCurrentXmlTest().getParameter("browserName");
		//String browserName=context.getCurrentXmlTest().getParameter("browserName").split("_")[0].trim();
		String operating_System_Name=context.getCurrentXmlTest().getParameter("browserName").split("_")[1].trim();
		
		
		String testCaseId = method.getName();
		//System.out.println("test case Name is :"+testCaseId);for debugging
		ExcelUtils readTestData=new ExcelUtils();
		// initializing excel connection 
		HSSFSheet sheet=readTestData.initiateExcelConnection(sheetNameP, sWorkBooName, doFilePathMapping);

		//HSSFSheet sheet=readTestData.initiateExcelConnection(sheetName, workbookName, doFilePathMapping);//don't remove this 
		HashMap<String, String> ObjHmap=new HashMap<String, String>();
		ObjHmap.put("os", operating_System_Name);
		ObjHmap.put("browser", browserName);
		
		ArrayList<Integer> ObjArrayOfTestCaseRow=readTestData.getRowNumbers(sheet,testCaseId);
		ArrayList<String> ObjArrayOf_Headers=readTestData.getHeaders(sheet);
		int executionCount=ObjArrayOfTestCaseRow.size();
		int totalData=ObjArrayOf_Headers.size();

		Object[][] obj=new Object[executionCount][1];

		for(int row=0;row<executionCount;row++)

		{
			for(int cell=0;cell<totalData;cell++)
			{		  
				String tempValue=sheet.getRow(ObjArrayOfTestCaseRow.get(row)).getCell(cell).getStringCellValue().trim();
				ObjHmap.put(ObjArrayOf_Headers.get(cell), tempValue);  
			}
			obj[row][0]=ObjHmap;  	  
		}
		return obj;	

	}//multipleExecutionData
}