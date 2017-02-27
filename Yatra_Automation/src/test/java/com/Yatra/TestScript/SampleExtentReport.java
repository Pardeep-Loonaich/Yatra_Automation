package com.Yatra.TestScript;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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

	@Test(groups = { "desktop" }, description = "To verify the Yatra Home Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
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
			driver.get(webSite);
			Thread.sleep(6000);
		  
			// step1: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("1.Verified Yatra Title text");
			}

			// step2: enter Origin place in Yatra Home page
			WebElement selectOrgion = driver.findElement(By.id("BE_flight_origin_city"));
			selectOrgion.sendKeys(origin);
			Log.message("2.Successfully entered Origin Place in Yatra Homepage: " + origin);
			Thread.sleep(3000);
			
			// step3: enter Destination place in Yatra Home page
			WebElement selectDestination = driver.findElement(By.id("BE_flight_arrival_city"));
			selectDestination.sendKeys(destination);
			Log.message("3.Successfully entered Destination Place in Yatra Homepage: " + destination);
			Thread.sleep(3000);
			
			// step4: click 'Search' button in Yatra Home page
			WebElement searchBtn = driver.findElement(By.id("BE_flight_flsearch_btn"));
			searchBtn.click();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");

			Log.message("<b>Expected Result:</b> Successfully searched Flights");

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.endTestCase();
		}
	}

}
