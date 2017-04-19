package com.Yatra.TestScript.Bus;

import java.awt.geom.CubicCurve2D.Float;

//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Bus Search test Cases would be designed in this class 
//Creator        :   Aspire Team
//Create         :   
//Modified on/By :   -
//-----------------------------------------------------------------------------------------------------------

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.function.DoubleToIntFunction;

import org.eclipse.jetty.websocket.common.io.payload.PayloadProcessor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Yatra.Pages.HomePage;
import com.Yatra.Pages.PaymentPageBus;
import com.Yatra.Pages.ReviewPageBus;
import com.Yatra.Pages.SearchResultBus;
import com.Yatra.Pages.TravellerPageBus;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class BusSearchTest {

	EnvironmentPropertiesReader environmentPropertiesReader;
	String webSite;
	SearchResultBus searchResultBus;
	ReviewPageBus reviewPageBus;
	TravellerPageBus travellerPageBus;
	PaymentPageBus paymentPageBus;
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(groups = {
			"desktop" }, description = "Search Oneway bus for 1 pax", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_001(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Search Oneway bus for multiple pax", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_002(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Search RoundTrip bus for 1 pax", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_003(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Search RoundTrip bus for multiple pax", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_004(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Don't select any city for onward/return", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_005(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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
			String ErrorMessage = homePage.getTextErrorMsg();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> User Should get a Error Message when,Don't select any city for onward/return");
			Thread.sleep(6000);
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("btnSearchBus"), homePage),
					"<b>Actual Result:</b> After Clicking Search Button, An Error Message is Displayed as :"
							+ ErrorMessage,
					"<b>Actual Result:</b> After Clicking Search Button, No Error Message is not Displayed", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Don't select any date to travel", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_006(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
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

			homePage.enterOriginBus(origin);
			Log.message("4. Enter Origin!");

			homePage.enterDestinationBus(destination);
			Log.message("5. Enter Destination!");

			homePage.PassengerInfoBus(passengerInfo);
			Log.message("6. Passenger Added!");

			homePage.clickOnSearchBus();
			Log.message("7. Clicked On Search Button!");
			BrowserActions.nap(2);
			homePage.clickOnSearchBus();
			String ErrorMessage = homePage.getTextErrorMsg();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User Should get a Error Message when,Don't select any date to travel");
			Thread.sleep(6000);
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("btnSearchBus"), homePage),
					"<b>Actual Result:</b> After Clicking Search Button, An Error Message is Displayed as :"
							+ ErrorMessage,
					"<b>Actual Result:</b> After Clicking Search Button, No Error Message is not Displayed", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "On selecting same city for depart & Arrival", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_007(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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
			String ErrorMessage = homePage.getTextErrorMsg();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> User Should get a Error Message On selecting same city for depart & Arrival");
			Thread.sleep(6000);
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("btnSearchBus"), homePage),
					"<b>Actual Result:</b> After Clicking Search Button, An Error Message is Displayed as :"
							+ ErrorMessage,
					"<b>Actual Result:</b> After Clicking Search Button, No Error Message is not Displayed", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Enter incorrectCity Name for onward or return", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_008(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String InvalidDestination = testData.get("InvalidDestination");
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
			homePage.enterDestinationBus(InvalidDestination);
			String ErrorMessage = homePage.getTextErrorIncorrectCity();

			// homePage.clickOnSearchBus();
			// Log.message("5. Clicked On Search Button!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> User Should get a Error Message On Entering incorrectCity Name for onward or return");
			Thread.sleep(6000);
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("btnSearchBus"), homePage),
					"<b>Actual Result:</b> After Clicking Search Button, An Error Message is Displayed as :"
							+ ErrorMessage,
					"<b>Actual Result:</b> After Clicking Search Button, No Error Message is not Displayed", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Check for Message in Case Of No Result", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_009(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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
			String SRPDetail = searchResultBus.getTextNoBusFound();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> User should navigated on SearchResult page and No Bus Found Message Should Be Displayed");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("txtNoResultFoundBus"),
							searchResultBus),
					"<b>Actual Result:</b> User Successfully navigated on SearchResult page and On SRP details as  : "
							+ SRPDetail,
					"<b>Actual Result:</b> User navigated on SearchResult Page,NO Bus Found Message not displayed On SRP Page",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "User Should See the Price in Sorted Form in Ascending Order", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_010(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User Should see result sorted by price in asc order");
			if (!searchResultBus.getSortedPrice()) {
				Log.message("<b>Actual Result:</b> After Clicking Search Button, Bus price is not in ascending Order");
			}
			Log.message("<b>Actual Result:</b> After Clicking Search Button, price is displayed in sorted Form",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "User Should See the depart time in Sorted Form", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_011(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			searchResultBus.clickOnDepartTimeLink();
			Log.message("6. Clicked Depart Time Link!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User Should see the depart time in Sorted Form");
			if (!searchResultBus.getSortedDepartTime()) {
				Log.message(
						"<b>Actual Result:</b> After Clicking Search Button, Depart Time is not displayed in sorted Form");
			}
			Log.message(
					"<b>Actual Result:</b> After Clicking Search Button, Depart Time is not displayed in sorted Form",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "User Should See the Arrive time in Sorted Form", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_012(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			searchResultBus.clickOnDepartTimeLink();
			Log.message("6. Clicked Depart Time Link!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User Should see the Arrive time in Sorted Form");
			if (!searchResultBus.getSortedArriveTime()) {
				Log.message(
						"<b>Actual Result:</b> After Clicking Search Button, Depart Time is not displayed in sorted Form");
			}
			Log.message(
					"<b>Actual Result:</b> After Clicking Search Button, Depart Time is not displayed in sorted Form",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "User Should See the Duration in Sorted Form", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_013(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			searchResultBus.clickOnDepartTimeLink();
			Log.message("6. Clicked Depart Time Link!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User Should see the Duration in Sorted Form");
			if (!searchResultBus.getSortedDuration()) {
				Log.message(
						"<b>Actual Result:</b> After Clicking Search Button,Duration is not displayed in sorted Form");
			}
			Log.message("<b>Actual Result:</b> After Clicking Search Button,Duration is not displayed in sorted Form",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "On select seats should open seat-map page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_014(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			Thread.sleep(4000);

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> After Clicking on select seat User Should see a pop Up");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("selectSeatPopUp"), searchResultBus),
					"<b>Actual Result:</b> After Clicking on select seat the seat-map page appear",
					"<b>Actual Result:</b> After Clicking on select seat the seat-map page did not appear", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Click on bus details link", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_015(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			searchResultBus.clickOnLinkBusDetails();
			Log.message("6. Clicked On Bus Details Link!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> After Clicking on select seat User Should see a pop Up");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("busDetailPopUp"), searchResultBus),
					"<b>Actual Result:</b> After Clicking on select seat the seat-map page appear",
					"<b>Actual Result:</b> After Clicking on select seat the seat-map page did not appear", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify Prev/Next day tabs", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_016(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> On SearchResultPage Result Prev and Next Tab Should Be Shown");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("btnPrevDay", "btnNextDay"),
							searchResultBus),
					"<b>Actual Result:</b> On SearchResultPage Result Prev and Next Tab are Shown Properly",
					"<b>Actual Result:</b> On SearchResultPage Result Prev and Next Tab are not Shown Properly",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify RT /OW dates", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_017(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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
			Log.message("4. Successfully filled the search details for 'ROUND' trip!");

			searchResultBus = homePage.clickBtnSearchBus();
			Log.message("5. Clicked On Search Button!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> On SearchResultPage Result One Way and Round Trip Dates Should Be Shown Properly");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("fldDepartDate", "fldReturnDate"),
							searchResultBus),
					"<b>Actual Result:</b> On SearchResultPage Result 'One Way and Round Trip' Dates are Shown Properly",
					"<b>Actual Result:</b> On SearchResultPage Result 'One Way and Round Trip' Dates are not Shown Properly",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify validations in city names", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_018(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String invalidDestination = testData.get("InvalidDestination");
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

			searchResultBus.enterOriginBus(invalidDestination);
			Log.message("6. Filling Invalid Origin On Search Result Page!");
			String error = searchResultBus.getTextCityValidationErrorMsg();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> User Should Be navigated to Search Result Page and An Error Msg Should be Shown If City Name is Invalid");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("fldContentBusDetail"),
							searchResultBus),
					"<b>Actual Result:</b> User navigated to Search Result Page, and Error Message is Displayed as :"
							+ error,
					"<b>Actual Result:</b> User navigated to Search Result Page, But No Error Message is Displayed",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify Dropping points, Boarding point, Bus type, Amenities, Price, depart time, arrival time", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_019(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			String details = searchResultBus.getTextBusInfo();

			searchResultBus.clickOnBoardingPoint();
			Log.message("7. Clicked On Boarding Point!");
			String BoardingPoint = searchResultBus.getTextBusBoardingPoint();

			searchResultBus.clickOnDroppingPoint();
			Log.message("8. Clicked On Dropping Point!");
			String DroppingPoint = searchResultBus.getTextBusDroppingPoint();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> User Should See all details properly like Dropping points, Boarding point, Bus type, Amenities, Price, depart time, arrival time");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("selectSeatPopUp"), searchResultBus),
					"<b>Actual Result:</b> User navigated to Search Result Page,All details are properly seen as -->"
							+ details + "Boarding Point as --> " + BoardingPoint + "Dropping Point As -->"
							+ DroppingPoint,
					"<b>Actual Result:</b> ", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify correct seat number and type shown", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_020(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected!");
			String SeatNumber = searchResultBus.getTextSeatNumber();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User Should See Correct Seat Number And Type Shown");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("selectSeatPopUp"), searchResultBus),
					"<b>Actual Result:</b> Seat Number is Displayed as : " + SeatNumber,
					"<b>Actual Result:</b> Seat Number is not properly Displayed", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify boarding point drop down", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_021(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(6000);
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected!");

			String DropingPoint = searchResultBus.selectBoardingPoint();
			Log.message("8. Boarding Point Selected!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User Should See the boarding point drop down");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("selectSeatPopUp"), searchResultBus),
					"<b>Actual Result:</b> Boarding point Drop Down is Properly Displayed and Boarding Point as : "
							+ DropingPoint,
					"<b>Actual Result:</b> Seat Number is not properly Displayed", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify Max 6 seats can be selected and min 1", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_023(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			Thread.sleep(4000);
			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(6);
			Log.message("7. Seat Selected!");
			String Msg = searchResultBus.getTextMaxNumber();

			searchResultBus.selectBoardingPoint();
			Log.message("8. Boarding Point Selected!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User Should See the boarding point drop down");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("selectSeatPopUp"), searchResultBus),
					"<b>Actual Result:</b> User have selected seats and Message is displayed as : " + Msg,
					"<b>Actual Result:</b> User can select any number of Seat", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify Lower/Upper deck", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_024(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			Thread.sleep(4000);
			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			String bustype = searchResultBus.getTextSeatType();
			String bustyp = searchResultBus.getTextSeatTyp();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User Should Check for Lower/Upper deck");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("selectSeatPopUp"), searchResultBus),
					"<b>Actual Result:</b> User can Select " + bustype + " and " + bustyp + " seat type",
					"<b>Actual Result:</b> User can not Select lower/Upper Deck seats", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify RT search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_025(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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
			Log.message("4. Successfully filled the search details for 'ROUND' trip!");

			searchResultBus = homePage.clickBtnSearchBus();
			Log.message("5. Clicked On Search Button!");

			Thread.sleep(4000);
			searchResultBus.clickBtnSelectSeat_RT();
			Log.message("6. Clicked On Select Seat!");

			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Boarding Point Selected!");

			searchResultBus.clickOnSelectReturnSeat();
			Log.message("9. Clicked On Continue!");

			searchResultBus.selectReturnSeat(2);
			Log.message("10. Seat Selected Return Jounrey!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User Should See Select RT Seats");
			Thread.sleep(6000);
			Log.assertThat(searchResultBus.elementLayer.verifyPageElements(Arrays.asList("PopUp_RT"), searchResultBus),
					"<b>Actual Result:</b> User can See Both Seats",
					"<b>Actual Result:</b> User can not See Both Seats", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Check if seat is seletced but no boarding point is selected", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_026(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check if seat is seletced but no boarding point is selected");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("ContinueButtonInPopUp"),
							searchResultBus),
					"<b>Actual Result:</b> Error Message is displayed when no boarding Point is selected",
					"<b>Actual Result:</b> No Error Message is displayed", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify user should check if details are correct as of selected bus,seats and No. of pax", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_027(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected!");
			String details = searchResultBus.getTextBusInfo();
			String SeatNumber = searchResultBus.getTextSeatNumber();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verify user check if details are correct as of selected bus,seats and No. of pax");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("selectSeatPopUp"), searchResultBus),
					"<b>Actual Result:</b> All Deatils are Properly selected and Displayed as Seat No --> " + SeatNumber
							+ " and Bus Details as  --> " + details,
					"<b>Actual Result:</b> No Error Message is displayed", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify user should navigate to SRP after changing the bus", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_028(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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
			Log.message("4. Successfully filled the search details for 'One Way' trip!");

			searchResultBus = homePage.clickBtnSearchBus();
			Log.message("5. Clicked On Search Button!");

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(2);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			searchResultBus.switchToIframe();
			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			searchResultBus = reviewPageBus.clickOnChangeBus();
			Log.message("10. Clicked on continue to navigate to review page!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verify user should navigate to SRP after changing the bus on Review Page");
			Thread.sleep(6000);
			Log.assertThat(
					searchResultBus.elementLayer.verifyPageElements(Arrays.asList("btnFindBus"), searchResultBus),
					"<b>Actual Result:</b> Successfully navigated to Search Result Page",
					"<b>Actual Result:</b> Unable to navigate to Search Result Page", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify user click on continue Button and navigate to Review Page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_029(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user click on continue Button and navigate to Review Page");
			Thread.sleep(6000);
			Log.assertThat(driver.getCurrentUrl().contains("review"),
					"<b>Actual Result:</b> Successfully navigated to Review Page",
					"<b>Actual Result:</b> Unable to navigate to Review Page", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify the Guest Flow", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_030(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String phoneNumber = testData.get("PhoneNumber");
		String name = testData.get("CustomerName");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			searchResultBus.switchToIframe();
			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsGuest(email, phoneNumber);
			Log.message("10. Entered Email Address and Phone Number!");

			travellerPageBus.TravellerDetails(name);
			Log.message("11. Filled Guest Details!");

			travellerPageBus.clickOnContinueInTravellerPage();
			Log.message("12. Clicked on Continue!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify the guest Flow");
			Thread.sleep(6000);
			Log.assertThat(driver.getCurrentUrl().contains("payment"),
					"<b>Actual Result:</b> Guest Flow is working Fine",
					"<b>Actual Result:</b> Guest Flow is not working Fine", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify the Login User Flow", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_031(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String Password = testData.get("Password");
		String name = testData.get("CustomerName");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsLogin(email, Password);
			Log.message("10. Entered Email Address and Phone Number!");

			travellerPageBus.TravellerDetails(name);
			Log.message("11. Filled Guest Details and Clicked on Continue!");

			travellerPageBus.clickOnContinueInTravellerPage();
			Log.message("12. Clicked on Continue!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify the Login User Flow");
			Thread.sleep(6000);
			Log.assertThat(driver.getCurrentUrl().contains("payment"),
					"<b>Actual Result:</b> Login User Flow is working Fine",
					"<b>Actual Result:</b> Login User Flow is not working Fine", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify  mobile No. & ISD code valdation", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_032(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String phoneNumber = testData.get("PhoneNumber");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			reviewPageBus.fillUserDetailsAsGuest(email, phoneNumber);
			Log.message("10. Entered Email Address and Phone Number!");
			String Error = reviewPageBus.getTextErrorMsg();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify mobile No. & ISD code valdation");
			Thread.sleep(6000);
			Log.assertThat(reviewPageBus.elementLayer.verifyPageElements(Arrays.asList("BtnChangeBus"), reviewPageBus),
					"<b>Actual Result:</b> After Entering invalid Phone Number an error Message is Displayed as :"
							+ Error,
					"<b>Actual Result:</b> After Entering invalid Phone Number an error Message is not Displayed",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify the FaceBook Button", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_033(HashMap<String, String> testData) throws Exception {

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify the FaceBook Button");
			Thread.sleep(6000);
			Log.assertThat(reviewPageBus.elementLayer.verifyPageElements(Arrays.asList("btnFaceBook"), reviewPageBus),
					"<b>Actual Result:</b> FaceBook Button is properly Visible on Review Page",
					"<b>Actual Result:</b> FaceBook Button is not Visible on Review Page", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify name, title & Age validations", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_034(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String name = testData.get("CustomerName");
		String phoneNumber = testData.get("PhoneNumber");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsGuest(email, phoneNumber);
			Log.message("10. Entered Email Address and Phone Number!");

			travellerPageBus.TravellerDetails(name);
			Log.message("11. Filled Guest Details!");

			travellerPageBus.clickOnContinue();
			Log.message("12. Clicked on Continue!");
			String Error = travellerPageBus.getTextErrorMsg();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify name, title & Age validations");
			Thread.sleep(6000);
			Log.assertThat(
					travellerPageBus.elementLayer.verifyPageElements(Arrays.asList("btnContinue"), travellerPageBus),
					"<b>Actual Result:</b> If Name,title or Age field is empty an Error message is displayed as :"
							+ Error,
					"<b>Actual Result:</b> ", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify if Mobile Number is editable or not", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_035(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String[] phoneNumber = testData.get("PhoneNumber").split(",");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsGuest(email, phoneNumber[0]);
			Log.message("10. Entered Email Address and Phone Number!");
			String numberBeforeEdit = travellerPageBus.getTextMobileNo();

			travellerPageBus.clickEditMobileLink();
			Log.message("11. Clicked On Edit Mobile Number Link!");

			travellerPageBus.enterMobileNumber(phoneNumber[1]);
			Log.message("12. Enter Edited Mobile Number!");
			String numberAfterEdit = travellerPageBus.getTextMobileNo();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify if Mobile Number is editable or not");
			Thread.sleep(6000);
			Log.assertThat(
					travellerPageBus.elementLayer.verifyPageElements(Arrays.asList("btnContinue"), travellerPageBus),
					"<b>Actual Result:</b> User Mobile Number Before Edit is : " + numberBeforeEdit
							+ "User Mobile Number After Edit is : " + numberAfterEdit,
					"<b>Actual Result:</b> User mobile number is not editable", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify Validations of promocodes For Guest User", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_036(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String phoneNumber = testData.get("PhoneNumber");
		String promo = testData.get("PromoCode");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsGuest(email, phoneNumber);
			Log.message("10. Entered Email Address and Phone Number!");

			travellerPageBus.enterPromoCode(promo);
			Log.message("10. Entered Promo Code!");
			String Promo = travellerPageBus.getTextErrorMsgPromo();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Validations of promocodes For Guest User");
			Thread.sleep(6000);
			Log.assertThat(
					travellerPageBus.elementLayer.verifyPageElements(Arrays.asList("txtPromoMessage"),
							travellerPageBus),
					"<b>Actual Result:</b> If valid Promo is applied an Error message is displayed as : " + Promo,
					"<b>Actual Result:</b>  fail ", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify addons are selectable or not", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_037(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String phoneNumber = testData.get("PhoneNumber");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsGuest(email, phoneNumber);
			Log.message("10. Entered Email Address and Phone Number!");
			boolean resultFirst = travellerPageBus.checkBoxFirst();
			boolean resultSecond = travellerPageBus.checkBoxSecond();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Addons should have check boxes so user can select multiple options");
			Thread.sleep(6000);
			Log.assertThat(resultFirst && resultSecond,
					"<b>Actual Result:</b> Addons are check Boxes and User can select multiple options",
					"<b>Actual Result:</b> Addons are not check Boxes and user can not select multiple options",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify terms and conditions link", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_038(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String phoneNumber = testData.get("PhoneNumber");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsGuest(email, phoneNumber);
			Log.message("10. Entered Email Address and Phone Number!");

			Thread.sleep(3000);
			String winHandleBefore = driver.getWindowHandle();
			travellerPageBus.clickOnTermAndCondition();
			Log.message("11. Clicked On Term And Condition Link!");

			Set<String> handles = driver.getWindowHandles();
			for (String winHandle : handles) {
				if (!winHandle.equals(winHandleBefore)) {
					driver.switchTo().window(winHandle);
					break;
				}
			}
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify terms and conditions link");
			Log.assertThat(travellerPageBus.verifyTnCPage(),
					"<b>Actual Result:</b> Successfully navigated to Terms and condition Page.",
					"<b>Actual Result:</b> Unable to navigated to Term and condtion Page", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify for all payment option", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_039(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String name = testData.get("CustomerName");
		String phoneNumber = testData.get("PhoneNumber");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsGuest(email, phoneNumber);
			Log.message("10. Entered Email Address and Phone Number!");

			travellerPageBus.TravellerDetails(name);
			Log.message("11. Filled Guest Details!");

			paymentPageBus = travellerPageBus.clickOnContinueInTravellerPage();
			Log.message("12. Clicked on Continue!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify for all payment option");
			Thread.sleep(6000);
			Log.assertThat(
					paymentPageBus.elementLayer.verifyPageElements(Arrays.asList("fldContentPaymentMethods"),
							paymentPageBus),
					"<b>Actual Result:</b> All Payment Methods are visible",
					"<b>Actual Result:</b> No Payment Methods is visible", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify promocode is applied then amount should be shown correctly", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_040(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String name = testData.get("CustomerName");
		String phoneNumber = testData.get("PhoneNumber");
		String promo = testData.get("PromoCode");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsGuest(email, phoneNumber);
			Log.message("10. Entered Email Address and Phone Number!");

			travellerPageBus.enterPromoCode(promo);
			Log.message("11. Entered Promo Code!");

			travellerPageBus.TravellerDetails(name);
			Log.message("12. Filled Guest Details!");

			paymentPageBus = travellerPageBus.clickOnContinueInTravellerPage();
			Log.message("13. Clicked on Continue!");
			String PromoReward = paymentPageBus.getTextPromotionalRewards();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify promocode is applied then amount should be shown correctly");
			Thread.sleep(6000);
			Log.assertThat(
					paymentPageBus.elementLayer.verifyPageElements(Arrays.asList("PromoRewardPaymentPage"),
							paymentPageBus),
					"<b>Actual Result:</b> Promo is visible on payment page is :" + PromoReward,
					"<b>Actual Result:</b> No promo is visible on payment page", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify eWallet div", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_041(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String Password = testData.get("Password");
		String name = testData.get("CustomerName");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsLogin(email, Password);
			Log.message("10. Entered Email Address and Phone Number!");

			travellerPageBus.TravellerDetails(name);
			Log.message("11. Filled Guest Details and Clicked on Continue!");

			paymentPageBus = travellerPageBus.clickOnContinueInTravellerPage();
			Log.message("12. Clicked on Continue!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify eWallet div");
			Thread.sleep(6000);
			Log.assertThat(paymentPageBus.elementLayer.verifyPageElements(Arrays.asList("divEwallet"), paymentPageBus),
					"<b>Actual Result:</b> Ewallet is properly visible", "<b>Actual Result:</b> Ewallet is not visible",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify for ewallet amount which should be according to given % basis", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_042(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String Password = testData.get("Password");
		String name = testData.get("CustomerName");
		String Per = testData.get("Percentage");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsLogin(email, Password);
			Log.message("10. Entered Email Address and Phone Number!");

			travellerPageBus.TravellerDetails(name);
			Log.message("11. Filled Guest Details and Clicked on Continue!");

			paymentPageBus = travellerPageBus.clickOnContinueInTravellerPage();
			Log.message("12. Clicked on Continue!");
			
			Thread.sleep(2000);
			double TotalAmount = Double.parseDouble(paymentPageBus.getTextTotalAmountPaymentMethod());
			int Percentage = Integer.parseInt(Per);
			double TotalPercentage = TotalAmount*Percentage/100;
			double value = Math.round(TotalPercentage);
			String PercentageAfterCalcutaion = String.valueOf(value).replaceAll("[.]0", "");
			String EcashVisible = paymentPageBus.getTextMaxiumEcash();
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify for ewallet amount which should be according to given % basis");
			Thread.sleep(6000);
			Log.assertThat(PercentageAfterCalcutaion.equals(EcashVisible),
					"<b>Actual Result:</b> Correct ewallet amount is displayed in Payment Page",
					"<b>Actual Result:</b> Correct ewallet amount is not displayed in Payment Page", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verification continue PG and cancel transaction", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_043(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String phoneNumber = testData.get("PhoneNumber");
		String name = testData.get("CustomerName");
		String paymentType = testData.get("PaymentType");
		String cardNumber = testData.get("CardNumber");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			Thread.sleep(3000);
			travellerPageBus = reviewPageBus.fillUserDetailsAsGuest(email, phoneNumber);
			Log.message("10. Entered Email Address and Phone Number!");

			travellerPageBus.TravellerDetails(name);
			Log.message("11. Filled Guest Details and Clicked on Continue!");

			paymentPageBus = travellerPageBus.clickOnContinueInTravellerPage();
			Log.message("12. Clicked on Continue!");

			paymentPageBus.selectPaymentType(paymentType);
			Log.message("13. Payment Type Selected!");

			paymentPageBus.enterCreditCardDetails(cardNumber);
			Log.message("14. Credit Card Details SucessFully Filled!");

			paymentPageBus.cancelHdfcPayment(browser);
			Log.message("15. Clicked on Cancel Button On Payment GateWay!");

			Thread.sleep(1000);
			paymentPageBus.returnFromCreditCardPage(browser, 2);
			Log.message("16. Naviagted Back to Yatra Payment Page!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verification continue PG and cancel transaction");
			Thread.sleep(3000);
			Log.assertThat(driver.getCurrentUrl().contains("yatra"),
					"<b>Actual Result:</b> After Cancelling the transaction user naviagted back to Yatra Payment Page!",
					"<b>Actual Result:</b> After Cancelling the transaction user is not naviagted back to Yatra Payment Page",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify for eCash+ Cash Payment", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_044(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String Password = testData.get("Password");
		String name = testData.get("CustomerName");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			Thread.sleep(3000);
			travellerPageBus = reviewPageBus.fillUserDetailsAsLogin(email, Password);
			Log.message("10. Entered Email Address and Phone Number!");

			travellerPageBus.TravellerDetails(name);
			Log.message("11. Filled Guest Details and Clicked on Continue!");

			paymentPageBus = travellerPageBus.clickOnContinueInTravellerPage();
			Log.message("12. Clicked on Continue!");

			int amount = Integer.parseInt(paymentPageBus.getTextTotalAmountPaymentMethod());
			paymentPageBus.scrollSliderOfEcashRedeem(-80);
			paymentPageBus.clickOnRedeemNowButton();
			Log.message("13. Clicked on Redeem Now Button!");

			Thread.sleep(5000);
			int Ecash = Integer.parseInt(paymentPageBus.getTextEcashUsed());
			int ActualCost = amount - Ecash;
			int costVisible = Integer.parseInt(paymentPageBus.getTextTotalAmountPaymentMethod());

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify correct amount on Payment Page");
			Thread.sleep(6000);
			Log.assertThat(ActualCost == (costVisible),
					"<b>Actual Result:</b> Correct Cost is displayed in Payment Page",
					"<b>Actual Result:</b> Correct Cost is not displayed in Payment Page", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify booking summary drop down", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_045(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String phoneNumber = testData.get("PhoneNumber");
		String name = testData.get("CustomerName");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			Thread.sleep(4000);
			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsGuest(email, phoneNumber);
			Log.message("10. Entered Email Address and Phone Number!");

			travellerPageBus.TravellerDetails(name);
			Log.message("11. Filled Guest Details !");

			paymentPageBus = travellerPageBus.clickOnContinueInTravellerPage();
			Log.message("12. Clicked on Continue!");

			String details = paymentPageBus.clickOnBookingSummary();
			Log.message("13. Clicked On Booking Summary!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify booking summary drop down");
			Thread.sleep(6000);
			Log.assertThat(
					paymentPageBus.elementLayer.verifyPageElements(Arrays.asList("BookingSummaryDetail"),
							paymentPageBus),
					"<b>Actual Result:</b> Booking summary drop down is visble" + details,
					"<b>Actual Result:</b> Booking summary drop down is not visble", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify Validations of promocodes For Login User", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_046(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String email = testData.get("EmailAddress");
		String Password = testData.get("Password");
		String promo = testData.get("PromoCode");

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

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");

			searchResultBus.switchToIframe();
			searchResultBus.selectSeat(1);
			Log.message("7. Seat Selected from the popup!");

			searchResultBus.selectBoardingPoint();
			Log.message("8. Selected boarding point!");

			reviewPageBus = searchResultBus.clickOnContinueInPopUp();
			Log.message("9. Clicked on continue to navigate to review page!");

			travellerPageBus = reviewPageBus.fillUserDetailsAsLogin(email, Password);
			Log.message("10. Entered Email Address and Phone Number!");

			travellerPageBus.enterPromoCode(promo);
			Log.message("11. Entered Promo Code!");
			String Promo = travellerPageBus.getTextErrorMsgPromo();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Validations of promocodes For Guest User");
			Log.assertThat(
					travellerPageBus.elementLayer.verifyPageElements(Arrays.asList("txtPromoMessage"),
							travellerPageBus),
					"<b>Actual Result:</b> If Invalid Promo is applied an Error message is displayed as : " + Promo,
					"<b>Actual Result:</b> If Invalid Promo is applied no Error message is displayed", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

}// BusModule
