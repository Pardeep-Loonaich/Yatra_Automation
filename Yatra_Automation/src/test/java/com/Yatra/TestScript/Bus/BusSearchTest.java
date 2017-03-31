package com.Yatra.TestScript.Bus;

import java.util.ArrayList;
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
	public void TC_Yatra_Bus_001(HashMap<String, String> testData) throws Exception {

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

			homePage.clickOnSearchBus();
			Log.message("5. Clicked On Search Button!");

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
			"desktop" }, description = "Check for Prev/Next day tabs", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_016(HashMap<String, String> testData) throws Exception {

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
			"desktop" }, description = "Check for RT /OW dates", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_017(HashMap<String, String> testData) throws Exception {

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
			"desktop" }, description = "Check for validations in city names", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_018(HashMap<String, String> testData) throws Exception {

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
	 			Log.message("4. Successfully filled the search details for 'ROUND' trip!");
	 
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
			"desktop" }, description = "Check for Dropping points, Boarding point, Bus type, Amenities, Price, depart time, arrival time", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Bus_019(HashMap<String, String> testData) throws Exception {

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
			Log.message("4. Successfully filled the search details for 'ROUND' trip!");

			searchResultBus = homePage.clickBtnSearchBus();
			Log.message("5. Clicked On Search Button!");
			Thread.sleep(6000);

			searchResultBus.clickBtnSelectSeat();
			Log.message("6. Clicked On Select Seat!");
			Thread.sleep(6000);
			String details = searchResultBus.getTextBusInfo();

			searchResultBus.clickOnBoardingPoint();
			Log.message("7. Clicked On Boarding Point!");
			Thread.sleep(6000);
			String BoardingPoint = searchResultBus.getTextBusBoardingPoint();

			searchResultBus.clickOnDroppingPoint();
			Log.message("8. Clicked On Dropping Point!");
			Thread.sleep(6000);
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

}