package com.Yatra.TestScript;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Yatra.Pages.HomePage;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.TestDataExtractor;
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class SampleExtentReport {

	EnvironmentPropertiesReader environmentPropertiesReader;
	String webSite;
	private String workbookName = "testdata\\data\\Regression.xls";
	private String sheetName = "Extent";
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(groups = {
			"desktop" }, description = "To verify the Yatra Home Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_001(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String email = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step2: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}

			// step3: enter Origin place in Yatra Home page
			homePage.enterOrgion(origin);
			Log.message("3.Successfully entered Origin Place in Yatra Homepage: " + origin);

			// step4: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination Place in Yatra Homepage: " + destination);
			Thread.sleep(3000);

			// step5: click 'Search' button in Yatra Home page
			homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");

			Log.message("<b>Expected Result:</b> Successfully searched Flights");

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.endTestCase();
		}
	}

}
