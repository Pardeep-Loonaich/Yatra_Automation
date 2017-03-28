package com.Yatra.TestScript.Bus;

import java.util.Arrays;

//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Bus Search test Cases would be designed in this class 
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
import com.Yatra.Pages.SearchResultBus;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class BusSearchTest {

	EnvironmentPropertiesReader environmentPropertiesReader;
	String webSite;
	SearchResultBus searchResultBus;
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(groups = {
			"desktop" }, description = "Search Oneway bus for 1 pax", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void Web_Bus_001(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickBuses();
			Log.message("2. Clicked on Bus Link!");

			homePage.selectTripTypeBus(tripType);
			Log.message("3. Trip Type Selected!");

			homePage.selectOneWayBusSearchFields(origin, destination, departureDate, passengerInfo);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip!");

			searchResultBus = homePage.clickBtnSearchBus();
			Log.message("5. Clicked On Search Button!");
			String BusDetail = searchResultBus.getTextBusDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("fldContentBusDetail"),
							searchResultBus),
					"<b>Actual Result:</b> User Successfully navigated on SearchResult page and Bus Details are as : "
							+ BusDetail,
					"<b>Actual Result:</b> User is not navigated on SearchResult page", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Search Oneway bus for multiple pax", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void Web_Bus_002(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickBuses();
			Log.message("2. Clicked on Bus Link!");

			homePage.selectTripTypeBus(tripType);
			Log.message("3. Trip Type Selected!");

			homePage.selectOneWayBusSearchFields(origin, destination, departureDate, passengerInfo);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip!");

			searchResultBus = homePage.clickBtnSearchBus();
			Log.message("5. Clicked On Search Button!");
			String BusDetail = searchResultBus.getTextBusDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("fldContentBusDetail"),
							searchResultBus),
					"<b>Actual Result:</b> User Successfully navigated on SearchResult page and Bus Details are as : "
							+ BusDetail,
					"<b>Actual Result:</b> User is not navigated on SearchResult page", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Search RoundTrip bus for 1 pax", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void Web_Bus_003(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickBuses();
			Log.message("2. Clicked on Bus Link!");

			homePage.selectTripTypeBus(tripType);
			Log.message("3. Trip Type Selected!");

			homePage.selectRoundTripBusSearchFields(origin, destination, departureDate, returnDate, passengerInfo);
			Log.message("4. Successfully filled the search details for 'ROUND TRIP' trip!");

			searchResultBus = homePage.clickBtnSearchBus();
			Log.message("5. Clicked On Search Button!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("fldContentBusDetail_RT"),
							searchResultBus),
					"<b>Actual Result:</b> User Successfully navigated on SearchResult page",
					"<b>Actual Result:</b> User is not navigated on SearchResult page", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Search RoundTrip bus for multiple pax", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void Web_Bus_004(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickBuses();
			Log.message("2. Clicked on Bus Link!");

			homePage.selectTripTypeBus(tripType);
			Log.message("3. Trip Type Selected!");

			homePage.selectRoundTripBusSearchFields(origin, destination, departureDate, returnDate, passengerInfo);
			Log.message("4. Successfully filled the search details for 'ROUND TRIP' trip!");

			searchResultBus = homePage.clickBtnSearchBus();
			Log.message("5. Clicked On Search Button!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("fldContentBusDetail_RT"),
							searchResultBus),
					"<b>Actual Result:</b> User Successfully navigated on SearchResult page",
					"<b>Actual Result:</b> User is not navigated on SearchResult page", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Don't select any city for onward/return", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void Web_Bus_005(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickBuses();
			Log.message("2. Clicked on Bus Link!");

			homePage.selectTripTypeBus(tripType);
			Log.message("3. Trip Type Selected!");

			homePage.selectOneWayBusSearchFields(origin, destination, departureDate, passengerInfo);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip!");

			homePage.clickOnSearchBus();
			Log.message("5. Clicked On Search Button!");
			BrowserActions.nap(2);
			homePage.clickOnSearchBus();
			String ErrorMessage = homePage.getTextErrorMsgEmptyCity();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> User Should get a Error Message when,Don't select any city for onward/return");
			Thread.sleep(6000);
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("btnSearchBus"), homePage),
					"<b>Actual Result:</b> After Clicking Search Button, An Error Message is Displayed as :"
							+ ErrorMessage,
					"<b>Actual Result:</b> After Clicking Search Button, No Error Message is not Displayed", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Don't select any date to travel", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void Web_Bus_006(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickBuses();
			Log.message("2. Clicked on Bus Link!");

			homePage.selectTripTypeBus(tripType);
			Log.message("3. Trip Type Selected!");

			homePage.selectOneWayBusSearchFields(origin, destination, departureDate, passengerInfo);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip!");

			homePage.clickOnSearchBus();
			Log.message("5. Clicked On Search Button!");
			BrowserActions.nap(2);
			homePage.clickOnSearchBus();
			String ErrorMessage = homePage.getTextErrorMsgEmptyCity();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> User Should get a Error Message when,Don't select any city for onward/return");
			Thread.sleep(6000);
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("btnSearchBus"), homePage),
					"<b>Actual Result:</b> After Clicking Search Button, An Error Message is Displayed as :"
							+ ErrorMessage,
					"<b>Actual Result:</b> After Clicking Search Button, No Error Message is not Displayed", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

}