package com.Yatra.TestScript.Hotels;

//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Hotels Search test Cases would be designed in this class 
//Creator        :   Aspire Team
//Create         :   
//Modified on/By :   -
//-----------------------------------------------------------------------------------------------------------

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
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class HotelSearchTest {

	EnvironmentPropertiesReader environmentPropertiesReader;
	String webSite;
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@SuppressWarnings("unused")
	@Test(groups = { "desktop" }, description = "Searching Hotels for Domestic & International Hotels", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_HotelSearch_001(HashMap<String, String> testData) throws Exception {

		String browser=testData.get("browser");
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

			// TODO : Steps

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	
	

}