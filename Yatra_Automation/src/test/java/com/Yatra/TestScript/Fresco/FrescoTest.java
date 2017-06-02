package com.Yatra.TestScript.Fresco;
//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Fresco test Cases would be designed in this class 
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

import com.Yatra.Pages.Fresco;
import com.Yatra.Pages.HomePage;
import com.Yatra.Pages.LoginPage;
import com.Yatra.Pages.PaymentPage;
import com.Yatra.Pages.ReviewPage;
import com.Yatra.Pages.SearchResult;
import com.Yatra.Pages.TravellerPage;
import com.Yatra.TestScript.Common.BaseTest;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.Yatra.Utils.WebDriverFactory;


@Listeners(EmailReport.class)
public class FrescoTest extends BaseTest{

	EnvironmentPropertiesReader environmentPropertiesReader;
	HomePage homePage;
	LoginPage loginPage;
	SearchResult searchResult;
	ReviewPage reviewPage;
	TravellerPage travellerPage;
	PaymentPage paymentPage;
	String webSite;
	String BlueColor = "rgba(16, 114, 181, 1)";
	Fresco fresco;

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {		
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test( description = "Search Flights -- Verify Top Ten cities list on Booking Engine", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_001(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			
			// step: Navigate to Yatra Home Page
			fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);	
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getCitiesListGrid() == false) {
				fresco.enterOrigin(origin);
			} 			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Top Ten cities list displayed when clicked on booking engine");
			Log.assertThat(fresco.getCitiesListCount(),
					"<b>Actual Result:</b> Top Ten cities list displayed when clicked on booking engine",
					"<b>Actual Result:</b> Top Ten cities list not displayed when clicked on booking engine", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Search Flights -- Verify Auto suggestions against searched flights", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_002(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {			
			// step: Navigate to Yatra Home Page
			fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getCitiesListGrid() == false) {
				fresco.enterOrigin(origin);
			} 
			String cityNameText = fresco.getTextFlightAutoSuggestionSourceCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto suggestions against searched flights should be displayed");
			Log.assertThat(cityNameText.contains("DEL"),
					"<b>Actual Result:</b> Auto suggestions against searched flights should be displayed",
					"<b>Actual Result:</b> Auto suggestions against searched flights not displayed", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Search Hotels -- Verify Auto suggestions against searched hotels", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_003(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			fresco.clickHotels();
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterHotelCity(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");
			BrowserActions.nap(15);
			//Re-trying if not displayed cities list Grid
			if (fresco.getCitiesListGrid() == false) {
				fresco.enterOrigin(origin);
			} 
			String cityNameText = fresco.getTextHotelAutoSuggestionCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto suggestions against searched hotels should be displayed");
			Log.assertThat(cityNameText.contains("New Delhi"),
					"<b>Actual Result:</b> Auto suggestions against searched hotels should be displayed",
					"<b>Actual Result:</b> Auto suggestions against searched hotels not displayed", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}	
// ********************************End of Test cases ***************************************************************************************

} //FrescoTest

