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
public class FrescoTest extends BaseTest {
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

	@Test(description = "Search Flights -- Verify Top Ten cities list on Booking Engine", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
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
			Log.endTestCase();
		}
	}

	@Test(description = "Search Flights -- Verify Auto suggestions against searched flights", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
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
			Log.endTestCase();
		}
	}

	@Test(description = "Search Hotels -- Verify Auto suggestions against searched hotels", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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
			// Re-trying if not displayed cities list Grid
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
			Log.endTestCase();
		}
	}

	@Test(description = "Search Flights +Hotels -- Verify Auto suggestions against searched flights + Hotels", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
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
			Log.endTestCase();
		}
	}

	@Test(description = "Search Homestays -- Verify Auto suggestions against searched Homestays", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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
			// Re-trying if not displayed cities list Grid
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
			Log.endTestCase();
		}
	}

	@Test(description = "Search Holidays -- Verify Auto suggestions against searched Holidays", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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
			// Re-trying if not displayed cities list Grid
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
			Log.endTestCase();
		}
	}

	@Test(description = "Search Activities -- Verify Auto suggestions against searched Activities", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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
			// Re-trying if not displayed cities list Grid
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
			Log.endTestCase();
		}
	}

	@Test(description = "Search Buses -- Verify Auto suggestions against searched Buses", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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
			// Re-trying if not displayed cities list Grid
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
			Log.endTestCase();
		}
	}

	@Test(description = "Search Trains -- Verify Auto suggestions against searched Trains", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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
			// Re-trying if not displayed cities list Grid
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
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport City name", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			}
			String cityNameText = fresco.getTextFlightAutoSuggestionSourceCityName();
			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> User should be able to have autosuggestions against the typed airport city name");
			Log.assertThat(cityNameText.contains(origin),
					"<b>Actual Result:</b> User should be able to have autosuggestions against the typed airport city name",
					"<b>Actual Result:</b> User should not able to have autosuggestions against the typed airport city name",
					driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport Name", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			}
			String cityNameText = fresco.getTextFlightAutoSuggestionSourceCityName();
			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> User should be able to have autosuggestions against the typed airport name");
			Log.assertThat(cityNameText.contains(origin),
					"<b>Actual Result:</b> User should be able to have autosuggestions against the typed airport name",
					"<b>Actual Result:</b> User should not able to have autosuggestions against the typed airport name",
					driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport Code", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			}
			String cityNameText = fresco.getTextFlightAutoSuggestionSourceCityName();
			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> User should be able to have autosuggestions against the typed airport code");
			Log.assertThat(cityNameText.contains(origin),
					"<b>Actual Result:</b> User should be able to have autosuggestions against the typed airport code",
					"<b>Actual Result:</b> User should not able to have autosuggestions against the typed airport code",
					driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "List of top cities should exist in db", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_015(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		// String origin = testData.get("Origin");

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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(" ");
			}

			List<String> cityNames = fresco.getSourceCitiesNamesInFlight();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified List of Top Cities");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed top cities list, Cities list are:  <b>" + cityNames
							+ "</b>",
					"<b>Actual Result:</b> Not displyaed top cities list", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid domestic Airport city for Source", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			}
			String cityName = fresco.getTextFlightAirportCityName();
			String countryName = fresco.getTextFlightAirportCountryName();
			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verified Domestic airport cities displays for Source with the country flag of India");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed Domestic airport cities for source with the country flag of India, airport city with country names: <b>"
							+ cityName + ", " + countryName + "</b>",
					"<b>Actual Result:</b> Not displyaed  Domestic airport cities for Source", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid domestic Airport city for Destination", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterDestination(destination);
			}
			String cityName = fresco.getTextFlightAirportCityName();
			String countryName = fresco.getTextFlightAirportCountryName();
			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verified Domestic airport cities displays for Destination with the country flag of India");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed Domestic airport cities for Destination with the country flag of India, airport city with country names: <b>"
							+ cityName + ", " + countryName + "</b>",
					"<b>Actual Result:</b> Not displyaed  Domestic airport cities for Destination", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid International Airport city for Source", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			}
			String cityName = fresco.getTextFlightAirportCityName();
			String countryName = fresco.getTextFlightAirportCountryName();
			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verified International airport cities displays for Source with the country flag of India");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed International airport cities for source with the country flag of India, airport city with country names: <b>"
							+ cityName + ", " + countryName + "</b>",
					"<b>Actual Result:</b> Not displyaed  International airport cities for Source", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid International Airport city for Destination", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");
			BrowserActions.nap(2);

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterDestination(destination);
			}
			String cityName = fresco.getTextFlightAirportCityName();
			String countryName = fresco.getTextFlightAirportCountryName();
			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verified International airport cities displays for Destination with the country flag of India");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed International airport cities for Destination with the country flag of India, airport city with country names: <b>"
							+ cityName + ", " + countryName + "</b>",
					"<b>Actual Result:</b> Not displyaed International airport cities for Destination", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport city for Source", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			}

			List<String> cityNames = fresco.getSourceCitiesNamesInFlight();
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed top cities have the most priority, Cities list are:  <b>"
							+ cityNames + "</b>",
					"<b>Actual Result:</b> Not displyaed top cities have the most priority", driver);

			String cityName = fresco.getTextFlightAirportCityName();
			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Validate top cities have the most priority for Source, then Domestic airports are priortise and then international airports.");
			Log.assertThat(cityName.contains(origin),
					"<b>Actual Result:</b> Successfully displayed top cities have the most priority for Source, then Domestic airports are priortise and then international airports.",
					"<b>Actual Result:</b> Not displayed top cities have the most priority for Source and Domestic airports are top priortise",
					driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport city for Destination", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterDestination(destination);
			}

			List<String> cityNames = fresco.getSourceCitiesNamesInFlight();
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displyaed top cities have the most priority, Cities list are:  <b>"
							+ cityNames + "</b>",
					"<b>Actual Result:</b> Not displyaed top cities have the most priority", driver);

			String cityName = fresco.getTextFlightAirportCityName();
			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Validate top cities have the most priority for Destination, then Domestic airports are priortise and then international airports.");
			Log.assertThat(cityName.contains(destination),
					"<b>Actual Result:</b> Successfully displayed top cities have the most priorityfor Destination, then Domestic airports are priortise and then international airports.",
					"<b>Actual Result:</b> Not displayed top cities have the most priority for Destination and Domestic airports are top priortise",
					driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport code for Source", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			}
			String cityCode = fresco.getTextFlightAirportCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate airport code and it should displayed on the first position");
			Log.assertThat(cityCode.contains(origin),
					"<b>Actual Result:</b> Successfully matched airport code and it should displayed on the first position",
					"<b>Actual Result:</b> Not matched airport code and it should not displayed on the first position",
					driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport code for Destination", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterDestination(destination);
			}
			String cityCode = fresco.getTextFlightAirportCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate airport code and it should displayed on the first position");
			Log.assertThat(cityCode.contains(destination),
					"<b>Actual Result:</b> Successfully matched airport code and it should displayed on the first position",
					"<b>Actual Result:</b> Not matched airport code and it should not displayed on the first position",
					driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Validate error destination message for Airport city ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// get the Error message from Destination
			String errorMessage = fresco.getErroeMessageInFlightDestination();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated error destination message for Airport city");
			Log.assertThat(errorMessage.contains(Constants.C_FlightDestination_ErrorMessage),
					"<b>Actual Result:</b> Successfully verified error destination message for Airport city, Error Message is: <b>"
							+ errorMessage + "</b>",
					"<b>Actual Result:</b> Not verified error destination message for Airport city", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Validate error destination message for Airport code ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// get the Error message from Destination
			String errorMessage = fresco.getErroeMessageInFlightDestination();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated error destination message for Airport code");
			Log.assertThat(errorMessage.contains(Constants.C_FlightDestination_ErrorMessage),
					"<b>Actual Result:</b> Successfully verified error destination message for Airport code, Error Message is: <b>"
							+ errorMessage + "</b>",
					"<b>Actual Result:</b> Not verified error destination message for Airport code", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Validate error destination message for Airport name ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			// get the Error message from Destination
			String errorMessage = fresco.getErroeMessageInFlightDestination();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated error destination message for Airport name");
			Log.assertThat(errorMessage.contains(Constants.C_FlightDestination_ErrorMessage),
					"<b>Actual Result:</b> Successfully verified error destination message for Airport name, Error Message is: <b>"
							+ errorMessage + "</b>",
					"<b>Actual Result:</b> Not verified error destination message for Airport name", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport name - Validate airport name in between letters of airports on Source", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_027(HashMap<String, String> testData) throws Exception {
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

			// step: enter Destination place in Yatra Home page
			fresco.enterOrigin(origin);
			Log.message("3.Successfully entered Destination '<b>" + origin + "</b>' in Homepage");
			BrowserActions.nap(2);

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			}
			String airportName = fresco.getTextFlightAutoSuggestionSourceCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated airport name in between letters of airports");
			Log.assertThat(airportName.contains(origin),
					"<b>Actual Result:</b> Successfully validated airport name in between letters of airports",
					"<b>Actual Result:</b> Not validated airport name in between letters of airports", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport name - Validate airport name in between letters of airports on Destination", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_028(HashMap<String, String> testData) throws Exception {
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterDestination(destination);
			}
			String airportName = fresco.getTextFlightAutoSuggestionSourceCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated airport name in between letters of airports on Destination");
			Log.assertThat(airportName.contains(destination),
					"<b>Actual Result:</b> Successfully validated airport name in between letters of airports on Destination",
					"<b>Actual Result:</b> Not validated airport name in between letters of airports on Destination",
					driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	// TODO: -InProgress
	@Test(description = "Validate airport city - validate resent saved Source details on Booking engine in HomePage ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_029(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

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

			// step: select OneWay Search fields in HomePage
			fresco.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = fresco.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Recent Search' button
			searchResult.clickOnRecentSearch();
			Log.message("5. Successfully clicked 'Recent Search'!");

			// step: get the text from Resent Search Popup
			/*
			 * String cityName = searchResult.getCityNameInRecentSearch();
			 * System.out.println(cityName);
			 */

			// step: get the text from Resent Search Popup
			String SourceCity = searchResult.getRecentSearchPopupDetails();
			System.out.println(SourceCity);

			// step: click 'Search' button in Yatra Home page
			fresco = searchResult.clickYatraLogo_Fresco();
			Log.message("6.Successfully clicked 'Yatra' Logo in SRP ");

			// TODO
			// get the Error message from Destination
			String sourceCity = fresco.getSourceCityName();
			System.out.println(sourceCity);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated resent saved Source details on Booking engine in HomePage");
			Log.assertThat(sourceCity.contains(origin),
					"<b>Actual Result:</b> Successfully verified resent saved Source details on Booking engine in HomePage",
					"<b>Actual Result:</b> Not verified resent saved Source details on Booking engine in HomePagee",
					driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	// TODO: -InProgress
	@Test(description = "Validate airport city - validated resent saved Destination details on Booking engine in HomePage", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_030(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

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

			// step: select OneWay Search fields in HomePage
			fresco.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = fresco.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Recent Search' button
			searchResult.clickOnRecentSearch();
			Log.message("5. Successfully clicked 'Recent Search'!");

			// step: get the text from Resent Search Popup
			/*
			 * String cityName = searchResult.getCityNameInRecentSearch();
			 * System.out.println(cityName);
			 */

			// step: get the text from Resent Search Popup
			String SourceCity = searchResult.getRecentSearchPopupDetails();
			System.out.println(SourceCity);

			// step: click 'Search' button in Yatra Home page
			fresco = searchResult.clickYatraLogo_Fresco();
			Log.message("6.Successfully clicked 'Yatra' Logo in SRP ");

			// TODO
			// get the Error message from Destination
			String destinationCity = fresco.getDestinationCityName();
			System.out.println(destinationCity);
			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Validated resent saved Destination details on Booking engine in HomePage");
			Log.assertThat(destinationCity.contains(destination),
					"<b>Actual Result:</b> Successfully verified resent saved Destination details on Booking engine in HomePage ",
					"<b>Actual Result:</b> Not verified resent saved Destination details on Booking engine in HomePage",
					driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport city/name/code - verify source flight in Explore tab", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_031(HashMap<String, String> testData) throws Exception {
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
			Log.message("3.Successfully clicked '<b>" + tripType + "</b>' option in Home Page ");
			BrowserActions.nap(6);

			fresco.clickFindNowInLowestFareFinder();
			Log.message("4.Successfully clicked Find Now button in LowestFareFinder");
			BrowserActions.nap(6);

			// step: enter Destination place in Yatra Home page
			fresco.enterOrigin_LowestFareFinder(origin);
			Log.message("5.Successfully entered Destination '<b>" + origin + "</b>' in Homepage");
			BrowserActions.nap(2);

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin_LowestFareFinder(origin);
			}
			String name = fresco.getTextFlightAutoSuggestionSourceCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified source flight in Explore tab");
			Log.assertThat(name.contains(origin),
					"<b>Actual Result:</b> Successfully verified source flight in Explore tab",
					"<b>Actual Result:</b> Not verified source flight in Explore tab", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport city/name/code - verify Destination flight in Explore tab", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_032(HashMap<String, String> testData) throws Exception {
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
			Log.message("3.Successfully clicked '<b>" + tripType + "</b>' option in Home Page ");
			BrowserActions.nap(6);

			fresco.clickFindNowInLowestFareFinder();
			Log.message("4.Successfully clicked Find Now button in LowestFareFinder");
			BrowserActions.nap(6);

			// step: enter Destination place in Yatra Home page
			fresco.enterDestination_LowestFareFinder(destination);
			Log.message("5.Successfully entered Destination '<b>" + destination + "</b>' in Homepage");
			BrowserActions.nap(2);

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterDestination_LowestFareFinder(destination);
			}
			String airportName = fresco.getTextFlightAutoSuggestionSourceCityName();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified Destination flight in Explore tab");
			Log.assertThat(airportName.contains(destination),
					"<b>Actual Result:</b> Successfully verified Destination flight in Explore tab",
					"<b>Actual Result:</b> Not verified Destination flight in Explore tab", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}
	
	@Test(description = "Valid Airport city/name/code - verify Destination fare finder flight details in Explore tab", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_033(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
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
			Log.message("3.Successfully clicked '<b>" + tripType + "</b>' option in Home Page ");
			BrowserActions.nap(6);

			fresco.clickFindNowInDestinationFinder();
			Log.message("4.Successfully clicked Find Now button in DestinationFinder");
			BrowserActions.nap(6);
			
			fresco.HandleWindowTabs();
			
			//fresco.clickOneWayInDestinationFinder();
			//Log.message("5.Successfully clicked OneWay option in DestinationFinder");
			
			// step: enter Source place in Home page
			fresco.enterOrigin_DestinationFinder(origin);
			Log.message("5.Successfully entered Source '<b>" + origin + "</b>' in Homepage");
			BrowserActions.nap(2);

			String originName = fresco.getTextFlightAutoSuggestionCityName_Destinationfinder();
			
			// step: enter Destination place in Home page
			fresco.enterDestination_DestinationFinder(destination);
			Log.message("6.Successfully entered Destination '<b>" + destination + "</b>' in Homepage");
			BrowserActions.nap(2);

			String destinationName = fresco.getTextFlightAutoSuggestionCityName_Destinationfinder();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified Destination flight in Explore tab");
			Log.assertThat((originName.contains(origin) && (destinationName.contains(destination))),
					"<b>Actual Result:</b> Successfully verified Destination fare finder flight details in Explore tab",
					"<b>Actual Result:</b> Not verified Destination fare finder flight details in Explore tab", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport city - Autosuggestion of travel with in budget", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_034(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		//String tripType = testData.get("TripType");
		String origin = testData.get("Origin");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			Fresco fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			// step: enter Origin place on Travel Within Budget in Home page
			fresco.enterOriginInTravelBudget(origin);
			Log.message("2.Successfully entered Origin '<b>" + origin + "</b>' in Travel Within Budget");
			BrowserActions.nap(2);

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOriginInTravelBudget(origin);
			}
			
			String cityName = fresco.getTextTravelBudgetCityName();			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified autosuggestion of 'travel with in budget' with indian cities according to the priority ");
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displayed autosuggestion of 'travel with in budget' with indian cities according to the priority, list of first city name is <b>"
							+ cityName + "</b>",
					"<b>Actual Result:</b> Not displayed autosuggestion of 'travel with in budget' with indian cities according to the priority", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Flight+Hotels -- verifiy Source autosuggestion of BYOP only indian cities according to the priority", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_035(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		// String origin = testData.get("Origin");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			Fresco fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			/// Step: click Flights+Hotels link in HomePage
			fresco.clickFlightsAndHotels();
			Log.message("2.Successfully clicked 'Flights+Hotels' tab  Home Page ");

			// step: Select Trip Type
			fresco.selectTripType_FlightsAndHotels(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");

			// step: enter Origin place in Yatra Home page
			fresco.enterOriginInFlightsAndHotels(" ");
			Log.message("4.Successfully clicked Origin");
			BrowserActions.nap(6);

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOriginInFlightsAndHotels(" ");
			}
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> verified Source autosuggestion of BYOP only indian cities according to the priority");
			List<String> citiesDetails = fresco.getCitiesFullDetailsInFlight();
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displayed Source autosuggestion of BYOP only indian cities according to the priority, Cities list are:  <b>"
							+ citiesDetails + "</b>",
					"<b>Actual Result:</b> Not ddisplayed Source autosuggestion of BYOP only indian cities according to the priority", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}
	
	@Test(description = "Flight+Hotels -- verifiy Destination autosuggestion of BYOP only indian cities according to the priority", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_036(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		// String destination = testData.get("Destination");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			Fresco fresco = new Fresco(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			/// Step: click Flights+Hotels link in HomePage
			fresco.clickFlightsAndHotels();
			Log.message("2.Successfully clicked 'Flights+Hotels' tab  Home Page ");

			// step: Select Trip Type
			fresco.selectTripType_FlightsAndHotels(tripType);
			Log.message("3.Successfully clicked 'One Way' option in Home Page ");

			// step: enter Destination place in Yatra Home page
			fresco.enterDestinationInFlightsAndHotels(" ");
			Log.message("4.Successfully clicked Destination");
			BrowserActions.nap(6);

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterDestinationInFlightsAndHotels(" ");
			}
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> verified Destination autosuggestion of BYOP only indian cities according to the priority");
			List<String> citiesDetails = fresco.getCitiesFullDetailsInFlight();
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displayed Destination autosuggestion of BYOP only indian cities according to the priority, Cities list are:  <b>" + citiesDetails + "</b>",
					"<b>Actual Result:</b> Not ddisplayed Destination autosuggestion of BYOP only indian cities according to the priority", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}
	
	@Test(description = "Valid Airport Source city -- Verify nearest airport with distance in range of 200km", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_037(HashMap<String, String> testData) throws Exception {
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterOrigin(origin);
			}
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> verified all nearest airport cities with distance in range of 200km");
			List<String> citiesDetails = fresco.getCitiesFullDetailsInFlight();
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displayed nearest airport cities with distance in range of 200km, Cities list are:  <b>" + citiesDetails + "</b>",
					"<b>Actual Result:</b> Not displayed nearest airport cities with distance in range of 200km", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "Valid Airport Destination city-- Verify nearest airport with distance in range of 200km", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_038(HashMap<String, String> testData) throws Exception {
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

			// Re-trying if not displayed cities list Grid
			if (fresco.getAutoSuggestionGrid() == false) {
				fresco.enterDestination(destination);
			}
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> verified all nearest airport cities with distance in range of 200km");
			List<String> citiesDetails = fresco.getCitiesFullDetailsInFlight();
			Log.assertThat(fresco.elementLayer.verifyPageElements(Arrays.asList("lnkAutoSuggestionsGrid"), fresco),
					"<b>Actual Result:</b> Successfully displayed nearest airport cities with distance in range of 200km, Cities list are:  <b>" + citiesDetails + "</b>",
					"<b>Actual Result:</b> Not displayed nearest airport cities with distance in range of 200km", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}
	
	@Test(description = "Verify Fresco paramaters in search URL for the respective source and destination both domestic", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_039(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");		
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String params = testData.get("Params");
		String originCountry = testData.get("OriginCountry");
		String destinationCountry = testData.get("DestinationCountry");

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

			// step: select OneWay Search fields in HomePage
			fresco.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = fresco.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: get the Parameter values from Search Result page URL
			List<String> listParamValues = searchResult.getParamValueFromCurrnetPageURL();	
			//In Java 8 we can use streams API	
			String[] paramValues = listParamValues.stream().toArray(String[]::new); 	
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified Fresco paramaters in search URL for the respective source and destination both domestic");
			Log.assertThat((paramValues[0].equals(params) && paramValues[1].equals(tripType.substring(0,1)) && paramValues[2].equals(origin) 
					      && paramValues[3].equals(originCountry) && paramValues[4].equals(destination) && paramValues[5].equals(destinationCountry)),
			"<b>Actual Result:</b> Successfully verified Fresco paramaters in search URL for the respective source and destination both domestic, Parameter values are: <b>" + listParamValues+ "</b>",
			"<b>Actual Result:</b> Not verified Fresco paramaters in search URL for the respective source and destination both domestic", driver);
						
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}
	
	@Test(description = "Verify Fresco paramaters in search URL for the respective source domestic and destination international", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_040(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");		
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String params = testData.get("Params");
		String originCountry = testData.get("OriginCountry");
		String destinationCountry = testData.get("DestinationCountry");

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

			// step: select OneWay Search fields in HomePage
			fresco.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = fresco.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: get the Parameter values from Search Result page URL
			List<String> listParamValues = searchResult.getParamValueFromCurrnetPageURL();	
			//In Java 8 we can use streams API	
			String[] paramValues = listParamValues.stream().toArray(String[]::new); 
			Log.message("<br>");
			BrowserActions.nap(8);
			Log.message("<b>Expected Result:</b> Verified Fresco paramaters in search URL for the respective source domestic and destination international");
			Log.assertThat((paramValues[0].equals(params) && paramValues[1].equals(tripType.substring(0,1)) && paramValues[2].equals(origin) 
					      && paramValues[3].equals(originCountry) && paramValues[4].equals(destination) && paramValues[5].equals(destinationCountry)),
			"<b>Actual Result:</b> Successfully verified Fresco paramaters in search URL for the respective source domestic and destination international, Parameter values are: <b>" + listParamValues+ "</b>",
			"<b>Actual Result:</b> Not verified Fresco paramaters in search URL for the respective source domestic and destination international", driver);
						
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}
	
	@Test(description = "Verify Fresco paramaters in search URL for the respective source international and destination domestic", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_041(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");		
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String params = testData.get("Params");
		String originCountry = testData.get("OriginCountry");
		String destinationCountry = testData.get("DestinationCountry");

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

			// step: select OneWay Search fields in HomePage
			fresco.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = fresco.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: get the Parameter values from Search Result page URL
			List<String> listParamValues = searchResult.getParamValueFromCurrnetPageURL();	
			String[] paramValues = listParamValues.stream().toArray(String[]::new); 	
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified Fresco paramaters in search URL for the respective source international and destination domestic");
			Log.assertThat((paramValues[0].equals(params) && paramValues[1].equals(tripType.substring(0,1)) && paramValues[2].equals(origin) 
					      && paramValues[3].equals(originCountry) && paramValues[4].equals(destination) && paramValues[5].equals(destinationCountry)),
			"<b>Actual Result:</b> Successfully verified Fresco paramaters in search URL for the respective source international and destination domestic, Parameter values are: <b>" + listParamValues+ "</b>",
			"<b>Actual Result:</b> Not verified Fresco paramaters in search URL for the respective source international and destination domestic", driver);
						
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {				
			Log.endTestCase();
		}
	}
	
	@Test(description = "Verify Fresco paramaters in search URL for the respective source and destination both domestic", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_042(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");		
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String params = testData.get("Params");
		String originCountry = testData.get("OriginCountry");
		String destinationCountry = testData.get("DestinationCountry");

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

			// step: select OneWay Search fields in HomePage
			fresco.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = fresco.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			BrowserActions.nap(5);
			
			// step: get the Parameter values from Search Result page URL
			List<String> listParamValues = searchResult.getParamValueFromCurrnetPageURL();	
			//In Java 8 we can use streams API		
			String[] paramValues = listParamValues.stream().toArray(String[]::new); 
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified Fresco paramaters in search URL for the respective source and destination both domestic");
			Log.assertThat((paramValues[0].equals(params) && paramValues[1].equals(tripType.substring(0,1)) && paramValues[2].equals(origin) 
					      && paramValues[3].equals(originCountry)&& paramValues[4].equals(destination) && paramValues[5].equals(destinationCountry)),
			"<b>Actual Result:</b> Successfully verified Fresco paramaters in search URL for the respective source and destination both international, Parameter values are: <b>" + listParamValues+ "</b>",
			"<b>Actual Result:</b> Not verified Fresco paramaters in search URL for the respective source and destination both international", driver);
						
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}
	
	@Test(description = "Verify Fresco paramaters in search URL for the respective source and destination both domestic - RT", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_043(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");		
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String returnDate = testData.get("ReturnDate");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String params = testData.get("Params");
		String originCountry = testData.get("OriginCountry");
		String destinationCountry = testData.get("DestinationCountry");

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

			//step: select Round Trip Search fields in HomePage
			fresco.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = fresco.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			BrowserActions.nap(5);
			
			// step: get the Parameter values from Search Result page URL
			List<String> listParamValues = searchResult.getParamValueFromCurrnetPageURL();	
			//In Java 8 we can use streams API			
			String[] paramValues = listParamValues.stream().toArray(String[]::new); 
						
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified paramaters in URL for the respective source and destination both domestic - RT");
			Log.assertThat((paramValues[0].equals(params) && paramValues[1].equals(tripType.substring(0,1)) && paramValues[2].equals(origin) 
					       && paramValues[3].equals(originCountry) && paramValues[4].equals(destination) && paramValues[5].equals(destinationCountry)),
			"<b>Actual Result:</b> Successfully verified paramaters in URL for the respective source and destination both domestic - RT, Parameter values are: <b>" + listParamValues+ "</b>",
			"<b>Actual Result:</b> Not verified paramaters in URL for the respective source and destination both domestic - RT", driver);
						
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}
	
	@Test(description = "Verify paramaters in URL for the respective source and destination both domestic - MC", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_044(HashMap<String, String> testData) throws Exception {
		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin0 = testData.get("Origin");
		String origin1 = testData.get("Origin_Multicity");
		String tripType = testData.get("TripType");
		String destination0 = testData.get("Destination");
		String destination1 = testData.get("Destination_Multicity");
		String departureDate0 = testData.get("DepartureDate");
		String departureDate1 = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String params = testData.get("Params");
		String originCountry0 = testData.get("OriginCountry");
		String destinationCountry0 = testData.get("DestinationCountry");
		String originCountry1 = testData.get("OriginCountry1");
		String destinationCountry1 = testData.get("DestinationCountry1");		

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

			// step: selectMulticity Search fields in HomePage
			fresco.selectMultiCityFlightSearchFields(origin0, destination0, departureDate0, origin1, destination1,	departureDate1, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'Multicity'");

			// Step: click Search button
			searchResult = fresco.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");
			BrowserActions.nap(5);
			
			// step: get the Parameter values from Search Result page URL
			List<String> listParamValues = searchResult.getParamValueFromCurrnetPageURL_MC();
			String[] paramValues = listParamValues.stream().toArray(String[]::new); 
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified paramaters in URL for the respective source and destination both domestic - MC");
			Log.assertThat(	(paramValues[0].equals(params) && paramValues[1].equals(tripType.substring(0, 1))&& paramValues[2].equals(origin0) && paramValues[3].equals(destination0) 
							&& paramValues[4].equals(originCountry0) && paramValues[5].equals(destinationCountry0)	&& paramValues[6].equals(origin1) && paramValues[7].equals(destination1) 
							&& paramValues[8].equals(originCountry1) && paramValues[9].equals(destinationCountry1)),
					"<b>Actual Result:</b> Successfully verified paramaters in search URL for the respective source and destination both domestic - MC, Parameter values are: <b>"	+ listParamValues + "</b>",
					"<b>Actual Result:</b> Not verified paramaters in URL for the respective source and destination both domestic - Mc",driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}
	
	@Test(description = "Verify Fresco paramaters in URL for the respective source and destination both domestic and select non stop flight option - RT", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_045(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");		
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String returnDate = testData.get("ReturnDate");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String params = testData.get("Params");
		String originCountry = testData.get("OriginCountry");
		String destinationCountry = testData.get("DestinationCountry");
		String nonstop = testData.get("NonStop");

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

			//step: select Round Trip Search fields in HomePage
			fresco.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");
						
			//step: click Non Stop Flights Checkbox
			fresco.clickNonStopFlightsCheckbox();
		    Log.message("4.Successfully cliecked Non Stop Flights checkbox");
		    
			//Step: click Search button
			searchResult = fresco.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage!");	
			BrowserActions.nap(5);
			
			// step: get the Parameter values from Search Result page URL
			List<String> listParamValues = searchResult.getParamValueFromCurrnetPageURL();	
			String[] paramValues = listParamValues.stream().toArray(String[]::new); 			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified paramaters in URL for the respective source and destination both domestic - RT");
			Log.assertThat((paramValues[0].equals(params) && paramValues[1].equals(tripType.substring(0,1)) && paramValues[2].equals(origin) && paramValues[3].equals(originCountry)
					&& paramValues[4].equals(destination) && paramValues[5].equals(destinationCountry) && paramValues[6].equals(nonstop)),
			"<b>Actual Result:</b> Successfully verified paramaters in URL for the respective source and destination both domestic-RT, Parameter values are: <b>" + listParamValues + "</b>",
			"<b>Actual Result:</b> Not verified paramaters in URL for the respective source and destination both domestic - RT", driver);
						
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}
	
	@Test(description = "Verify paramaters in URL for the respective source and destination both domestic and select non stop flight option - RT", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Fresco_046(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin0 = testData.get("Origin");
		String origin1 = testData.get("Origin_Multicity");
		String tripType = testData.get("TripType");
		String destination0 = testData.get("Destination");
		String destination1 = testData.get("Destination_Multicity");
		String departureDate0 = testData.get("DepartureDate");
		String departureDate1 = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");	
		String params = testData.get("Params");
		String originCountry0 = testData.get("OriginCountry");
		String destinationCountry0 = testData.get("DestinationCountry");
		String originCountry1 = testData.get("OriginCountry1");
		String destinationCountry1 = testData.get("DestinationCountry1");
		String nonstop = testData.get("NonStop");


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

			//step: selectMulticity Search fields in HomePage
			fresco.selectMultiCityFlightSearchFields(origin0, destination0, departureDate0, origin1, destination1, departureDate1, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'Multicity'");
						
			//step: click Non Stop Flights check box
			fresco.clickNonStopFlightsCheckbox();
		    Log.message("4.Successfully cliecked Non Stop Flights checkbox");
		    
			//Step: click Search button
			searchResult = fresco.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage!");	
			BrowserActions.nap(5);
			
			// step: get the Parameter values from Search Result page URL
			List<String> listParamValues = searchResult.getParamValueFromCurrnetPageURL_MC();	
			String[] paramValues = listParamValues.stream().toArray(String[]::new); 
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified paramaters in URL for the respective source and destination both domestic - MC");
			Log.assertThat((paramValues[0].equals(params) && paramValues[1].equals(tripType.substring(0,1)) && paramValues[2].equals(origin0) && paramValues[3].equals(destination0) && paramValues[4].equals(originCountry0)
					 && paramValues[5].equals(destinationCountry0) && paramValues[6].equals(origin1) && paramValues[7].equals(destination1) && paramValues[8].equals(originCountry1)
					 && paramValues[9].equals(destinationCountry1) && paramValues[10].equals(nonstop)),
			"<b>Actual Result:</b> Successfully verified paramaters in URL for the respective source and destination both domestic - MC, Parameter values are: <b>" + listParamValues+ "</b>",
			"<b>Actual Result:</b> Not verified paramaters in URL for the respective source and destination both domestic - MC", driver);
						
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}
	
	// ********************************End of Test cases************************************

} // FrescoTest
