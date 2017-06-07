package com.Yatra.TestScript.Fresco;
//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Fresco test Cases would be designed in this class 
//Creator        :   Aspire Team
//Create         :   
//Modified on/By :   -
//-----------------------------------------------------------------------------------------------------------

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Yatra.Pages.Fresco;
import com.Yatra.Pages.HomePage;
import com.Yatra.Pages.LoginPage;
import com.Yatra.Pages.SearchResult;
import com.Yatra.TestScript.Common.BaseTest;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.Yatra.Utils.WebDriverFactory;


@Listeners(EmailReport.class)
public class FrescoTest extends BaseTest{	
	EnvironmentPropertiesReader environmentPropertiesReader;
	LoginPage loginPage;
	SearchResult searchResult;
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
			if (fresco.getAutoSuggestionGrid() == false) {
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

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterOrigin(origin);
			Log.message("4.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			} 			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto suggestions against searched flights should be displayed");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
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
		String origin = testData.get("Origin");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickHotels();
			Log.message("2.Successfully clicked 'Hotels' tab  Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterHotelCity(origin);			
			Log.message("3.Successfully entered city '<b>" + origin + "</b>' in Yatra Homepage");
			
			BrowserActions.nap(6);
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterHotelCity(origin);
			} 
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto suggestions against searched hotels should be displayed");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
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
	
	
	
	@Test( description = "Search Flights +Hotels -- Verify Auto suggestions against searched flights + Hotels", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_004(HashMap<String, String> testData) throws Exception {		
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

			// Step: click Flights+Hotels link in HomePage
			fresco.clickFlightsAndHotels();
			Log.message("2.Successfully clicked 'Flights+Hotels' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType_FlightsAndHotels(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterOriginInFlightsAndHotels(origin);
			Log.message("4.Successfully entered Origin '<b>" + origin + "</b>' in Homepage");
			BrowserActions.nap(6);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOriginInFlightsAndHotels(origin);
			} 
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto suggestions against searched flights+Hotels should be displayed");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Auto suggestions against searched flights+Hotels should be displayed",
					"<b>Actual Result:</b> Auto suggestions against searched flights+Hotels not displayed", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Search Homestays -- Verify Auto suggestions against searched Homestays", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_005(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");	
		String origin = testData.get("Origin");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickHomeStays();
			Log.message("2.Successfully clicked 'Homestays' tab  Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterHomestaysCity(origin);			
			Log.message("3.Successfully entered city '<b>" + origin + "</b>' in Homepage");
			
			BrowserActions.nap(6);
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterHomestaysCity(origin);
			} 
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto suggestions against searched Homestays should be displayed");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Auto suggestions against searched Homestays should be displayed",
					"<b>Actual Result:</b> Auto suggestions against searched Homestays not displayed", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}		
	
	@Test( description = "Search Holidays -- Verify Auto suggestions against searched Holidays", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_006(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");	
		String origin = testData.get("Origin");
		
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickHolidays();
			Log.message("2.Successfully clicked 'Holidays' tab  Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterHolidaysCity(origin);			
			Log.message("3.Successfully entered city '<b>" + origin + "</b>' in Homepage");
			
			BrowserActions.nap(6);
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterHolidaysCity(origin);
			} 
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto suggestions against searched Holidays should be displayed");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Auto suggestions against searched Holidays should be displayed",
					"<b>Actual Result:</b> Auto suggestions against searched Holidays not displayed", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}		
	
	@Test( description = "Search Activities -- Verify Auto suggestions against searched Activities", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_007(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");	
		String origin = testData.get("Origin");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickActivities();
			Log.message("2.Successfully clicked 'Activities' tab  Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterActivitiesOrigin(origin);			
			Log.message("3.Successfully entered city '<b>" + origin + "</b>' in Homepage");
			
			BrowserActions.nap(6);
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterActivitiesOrigin(origin);
			} 
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto suggestions against searched Activities should be displayed");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Auto suggestions against searched Activities should be displayed",
					"<b>Actual Result:</b> Auto suggestions against searched Activities not displayed", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Search Buses -- Verify Auto suggestions against searched Buses", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_008(HashMap<String, String> testData) throws Exception {		
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

			// Step: click Hotels link in HomePage
			fresco.clickBuses();
			Log.message("2.Successfully clicked 'Buses' tab  Home Page ");

			fresco.selectTripTypeBus(tripType);
			Log.message("3. Trip Type Selected!");
			
			// step: enter Origin place in Yatra Home page
			fresco.enterOriginBus(origin); 		
			Log.message("4.Successfully entered city '<b>" + origin + "</b>' in Homepage");
			
			BrowserActions.nap(6);
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOriginBus(origin);
			} 
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto suggestions against searched Buses should be displayed");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Auto suggestions against searched Buses should be displayed",
					"<b>Actual Result:</b> Auto suggestions against searched Buses not displayed", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	@Test( description = "Search Trains -- Verify Auto suggestions against searched Trains", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_009(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");		
		String origin = testData.get("Origin");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickTrains();
			Log.message("2.Successfully clicked 'Trains' tab  Home Page ");
			
			// step: enter Origin place in Yatra Home page
			fresco.enterTrainOrigin(origin); 
			Log.message("3.Successfully entered city '<b>" + origin + "</b>' in Homepage");
			
			BrowserActions.nap(6);
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterTrainOrigin(origin);
			} 
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto suggestions against searched Trains should be displayed");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Auto suggestions against searched Trains should be displayed",
					"<b>Actual Result:</b> Auto suggestions against searched Trains not displayed", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Valid Airport City name", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_010(HashMap<String, String> testData) throws Exception {		
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

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterOrigin(origin);
			Log.message("4.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			} 
			String cityNameText = fresco.getTextFlightAutoSuggestionSourceCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to have autosuggestions against the typed airport city name");
			Log.assertThat(cityNameText.contains(origin),
					"<b>Actual Result:</b> User should be able to have autosuggestions against the typed airport city name",
					"<b>Actual Result:</b> User should not able to have autosuggestions against the typed airport city name", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Valid Airport Name", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_011(HashMap<String, String> testData) throws Exception {		
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

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterOrigin(origin);
			Log.message("4.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			} 
			String cityNameText = fresco.getTextFlightAutoSuggestionSourceCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to have autosuggestions against the typed airport name");
			Log.assertThat(cityNameText.contains(origin),
					"<b>Actual Result:</b> User should be able to have autosuggestions against the typed airport name",
					"<b>Actual Result:</b> User should not able to have autosuggestions against the typed airport name", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Valid Airport Code", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_012(HashMap<String, String> testData) throws Exception {		
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

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterOrigin(origin);
			Log.message("4.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			} 
			String cityNameText = fresco.getTextFlightAutoSuggestionSourceCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to have autosuggestions against the typed airport code");
			Log.assertThat(cityNameText.contains(origin),
					"<b>Actual Result:</b> User should be able to have autosuggestions against the typed airport code",
					"<b>Actual Result:</b> User should not able to have autosuggestions against the typed airport code", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}

	
	@Test( description = "List of top cities should exist in db", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_015(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		//String origin = testData.get("Origin");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {			
			// step: Navigate to Yatra Home Page
			Fresco fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");
			
			// step: enter Origin place in Yatra Home page
			fresco.enterOrigin(" ");
			Log.message("4.Successfully made a click on Flight booking engine");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(" ");
			} 
			
			List<String> cityNames = fresco.getSourceCitiesNamesInFlight();			
			Log.message("<br>");			
			Log.message("<b>Expected Result:</b> Verified List of Top Cities");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed top cities list, Cities list are:  <b>" + cityNames+"</b>",
					"<b>Actual Result:</b> Not displyaed top cities list", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Valid domestic Airport city for Source", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_016(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {			
			// step: Navigate to Yatra Home Page
			Fresco fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");
			
			// step: enter Origin place in Yatra Home page
			fresco.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			} 
			String cityName = fresco.getTextFlightAirportCityName();
			String countryName = fresco.getTextFlightAirportCountryName();
			Log.message("<br>");			
			Log.message("<b>Expected Result:</b> Verified Domestic airport cities displays for Source with the country flag of India");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed Domestic airport cities for source with the country flag of India, airport city with country names: <b>" + cityName+", "+ countryName +"</b>",
					"<b>Actual Result:</b> Not displyaed  Domestic airport cities for Source", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Valid domestic Airport city for Destination", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_017(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {			
			// step: Navigate to Yatra Home Page
			Fresco fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");
			
			// step: enter Origin place in Yatra Home page
			fresco.enterDestination(destination);
			Log.message("3.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterDestination(destination);
			} 
			String cityName = fresco.getTextFlightAirportCityName();
			String countryName = fresco.getTextFlightAirportCountryName();
			Log.message("<br>");			
			Log.message("<b>Expected Result:</b> Verified Domestic airport cities displays for Destination with the country flag of India");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed Domestic airport cities for Destination with the country flag of India, airport city with country names: <b>" + cityName+", "+ countryName +"</b>",
					"<b>Actual Result:</b> Not displyaed  Domestic airport cities for Destination", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Valid International Airport city for Source", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_018(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {			
			// step: Navigate to Yatra Home Page
			Fresco fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");
			
			// step: enter Origin place in Yatra Home page
			fresco.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			} 
			String cityName = fresco.getTextFlightAirportCityName();
			String countryName = fresco.getTextFlightAirportCountryName();
			Log.message("<br>");			
			Log.message("<b>Expected Result:</b> Verified International airport cities displays for Source with the country flag of India");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed International airport cities for source with the country flag of India, airport city with country names: <b>" + cityName+", "+ countryName +"</b>",
					"<b>Actual Result:</b> Not displyaed  International airport cities for Source", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Valid International Airport city for Destination", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_019(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {			
			// step: Navigate to Yatra Home Page
			Fresco fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");
			
			// step: enter Destination place in Yatra Home page
			fresco.enterDestination(destination);
			Log.message("3.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterDestination(destination);
			} 
			String cityName = fresco.getTextFlightAirportCityName();
			String countryName = fresco.getTextFlightAirportCountryName();
			Log.message("<br>");			
			Log.message("<b>Expected Result:</b> Verified International airport cities displays for Destination with the country flag of India");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed International airport cities for Destination with the country flag of India, airport city with country names: <b>" + cityName+", "+ countryName +"</b>",
					"<b>Actual Result:</b> Not displyaed International airport cities for Destination", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Valid Airport city for Source", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_020(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {			
			// step: Navigate to Yatra Home Page
			Fresco fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");
			
			// step: enter Origin place in Yatra Home page
			fresco.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			} 
			
			List<String> cityNames = fresco.getSourceCitiesNamesInFlight();			
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed top cities have the most priority, Cities list are:  <b>" + cityNames+"</b>",
					"<b>Actual Result:</b> Not displyaed top cities have the most priority", driver);
			
			String cityName = fresco.getTextFlightAirportCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate top cities have the most priority for Source, then Domestic airports are priortise and then international airports.");
			Log.assertThat(cityName.contains(origin),
					"<b>Actual Result:</b> Successfully displayed top cities have the most priority for Source, then Domestic airports are priortise and then international airports.",
					"<b>Actual Result:</b> Not displayed top cities have the most priority for Source and Domestic airports are top priortise", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Valid Airport city for Destination", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_021(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {			
			// step: Navigate to Yatra Home Page
			Fresco fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");			

			// step: enter Destination place in Yatra Home page
			fresco.enterDestination(destination);
			Log.message("3.Successfully entered Destination '<b>" + destination + "</b>' in Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterDestination(destination);
			} 
			
			List<String> cityNames = fresco.getSourceCitiesNamesInFlight();			
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed top cities have the most priority, Cities list are:  <b>" + cityNames+"</b>",
					"<b>Actual Result:</b> Not displyaed top cities have the most priority", driver);
			
			String cityName = fresco.getTextFlightAirportCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate top cities have the most priority for Destination, then Domestic airports are priortise and then international airports.");
			Log.assertThat(cityName.contains(destination),
					"<b>Actual Result:</b> Successfully displayed top cities have the most priorityfor Destination, then Domestic airports are priortise and then international airports.",
					"<b>Actual Result:</b> Not displayed top cities have the most priority for Destination and Domestic airports are top priortise", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	

	@Test( description = "Valid Airport code for Source", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_022(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {			
			// step: Navigate to Yatra Home Page
			Fresco fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");
			
			// step: enter Origin place in Yatra Home page
			fresco.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			} 			
			String cityCode = fresco.getTextFlightAirportCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate airport code and it should displayed on the first position");
			Log.assertThat(cityCode.contains(origin),
					"<b>Actual Result:</b> Successfully matched airport code and it should displayed on the first position",
					"<b>Actual Result:</b> Not matched airport code and it should not displayed on the first position", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Valid Airport code for Destination", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_023(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {			
			// step: Navigate to Yatra Home Page
			Fresco fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// Step: click Hotels link in HomePage
			fresco.clickFlights();
			Log.message("2.Successfully clicked 'Flights' tab  Home Page ");
			
			// step: Select Trip Type
			fresco.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");			

			// step: enter Destination place in Yatra Home page
			fresco.enterDestination(destination);
			Log.message("3.Successfully entered Destination '<b>" + destination + "</b>' in Homepage");
			BrowserActions.nap(2);
			
			//Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterDestination(destination);
			} 
			String cityCode = fresco.getTextFlightAirportCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate airport code and it should displayed on the first position");
			Log.assertThat(cityCode.contains(destination),
					"<b>Actual Result:</b> Successfully matched airport code and it should displayed on the first position",
					"<b>Actual Result:</b> Not matched airport code and it should not displayed on the first position", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate error destination message for Airport city ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_024(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		
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

			// step: enter Destination place in Yatra Home page
			fresco.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = fresco.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			fresco.clickSearchButton();
			Log.message("5.Successfully clicked Search button");	
			
			//get the Error message from Destination
			String errorMessage = fresco.getErroeMessageInFlightDestination();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated error destination message for Airport city");			
			Log.assertThat(errorMessage.contains(Constants.C_FlightDestination_ErrorMessage),
					"<b>Actual Result:</b> Successfully verified error destination message for Airport city, Error Message is: <b>"+ errorMessage +"</b>",
					"<b>Actual Result:</b> Not verified error destination message for Airport city", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Validate error destination message for Airport code ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_025(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		
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

			// step: enter Destination place in Yatra Home page
			fresco.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = fresco.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			fresco.clickSearchButton();
			Log.message("5.Successfully clicked Search button");	
			
			//get the Error message from Destination
			String errorMessage = fresco.getErroeMessageInFlightDestination();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated error destination message for Airport code");			
			Log.assertThat(errorMessage.contains(Constants.C_FlightDestination_ErrorMessage),
					"<b>Actual Result:</b> Successfully verified error destination message for Airport code, Error Message is: <b>"+ errorMessage +"</b>",
					"<b>Actual Result:</b> Not verified error destination message for Airport code", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Validate error destination message for Airport name ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_026(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		
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

			// step: enter Destination place in Yatra Home page
			fresco.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = fresco.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			fresco.clickSearchButton();
			Log.message("5.Successfully clicked Search button");	
			
			//get the Error message from Destination
			String errorMessage = fresco.getErroeMessageInFlightDestination();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated error destination message for Airport name");			
			Log.assertThat(errorMessage.contains(Constants.C_FlightDestination_ErrorMessage),
					"<b>Actual Result:</b> Successfully verified error destination message for Airport name, Error Message is: <b>"+ errorMessage +"</b>",
					"<b>Actual Result:</b> Not verified error destination message for Airport name", driver);

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

