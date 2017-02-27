package com.Yatra.TestScript;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.TestDataExtractor;
@Listeners(EmailReport.class)
public class SampleExtentReport {
	
	EnvironmentPropertiesReader environmentPropertiesReader;
	String webSite;	
	private String workbookName = "testdata\\data\\Functional.xls";
	private String sheetName = "Extent";
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(groups = { "desktop" }, description = "To verify the Yatra Home Page and other Pages", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_001(String browser) throws Exception {
		try {
			
			HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
			/*String searchKey = testData.get("SearchKey");
			String email = testData.get("EmailAddress");
			String password = testData.get("Password");*/
			
			WebDriver driver = new FirefoxDriver(); //WebDriverFactory.get(browser);
			Log.testCaseInfo(testData);
			
			// Navigate to HomePage
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Navigate to Search Result Page
			Log.message("2. Navigated to Search Result Page for searchKey: ");

			// Navigate to Product description Page
			Log.message("3. Navigated to Flights description Page");

			// Click on add to bag
			Log.message("4.Click on add to cart");

			// Click on shopping bag
			Log.message("5.Click on Booking cart");

			//Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be on Home Page after clicking 'Continue Booking flights' link on Flights Bag Page");

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.endTestCase();
		}
	}

}
