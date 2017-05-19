package com.Yatra.TestScript.Flights;
//-----------------------------------------------------------------------------------------------------------

import java.util.ArrayList;

//Description    :   All the Flight Searching test Cases would be designed in this class 
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

import com.Yatra.Pages.HomePage;
import com.Yatra.Pages.LoginPage;
import com.Yatra.Pages.PaymentPage;
import com.Yatra.Pages.ReviewPage;
import com.Yatra.Pages.SearchResult;
import com.Yatra.Pages.TravellerPage;
import com.Yatra.TestScript.Common.BaseTest;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.Yatra.Utils.WebDriverFactory;

/**
 * @Description:<br>
 * this class have all test case related to search flight module 
 * @author harveer.singh
 *
 */
@Listeners(EmailReport.class)
public class FlightSearch extends BaseTest{

	EnvironmentPropertiesReader environmentPropertiesReader;
	HomePage homePage;
	LoginPage loginPage;
	SearchResult searchResult;
	ReviewPage reviewPage;
	TravellerPage travellerPage;
	PaymentPage paymentPage;
	String webSite;
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {		
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test( description = "Flight Search DOM - OW with  Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_001(HashMap<String, String> testData) throws Exception {		
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("6.Successfully specified Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page",
					"<b>Actual Result:</b> User should not navigated on SearchResult page", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Flight Search DOM - RT with Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_002(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip ' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Return date
			String returndate = homePage.selectReturnDate(returnDate);
			Log.message("6.Successfully selected the Return date: <b>" + returndate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("7.Successfully specified Passenger Info");

			// step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("8.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("9.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page",
					"<b>Actual Result:</b> User should not navigated on SearchResult page", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Flight Search INTL- OW with Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_003(HashMap<String, String> testData) throws Exception {
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
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("6.Successfully specified Passenger Info");

			// step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.successfully selectd Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page",
					"<b>Actual Result:</b> User should not navigated on SearchResult page", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(description = "Flight Search INTL- RT with Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_004(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Return date
			String returndate = homePage.selectReturnDate(returnDate);
			Log.message("6.Successfully selected the Return date: <b>" + returndate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("7.Successfully specified Passenger Info");

			// step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("8.successfully selectd Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("9.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page",
					"<b>Actual Result:</b> User should not navigated on SearchResult page", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(description = "Flight Search DOM-Multicity", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_005(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin1 = testData.get("Origin");
		String origin2 = testData.get("Origin_Multicity");
		String tripType = testData.get("TripType");
		String destination1 = testData.get("Destination");
		String destination2 = testData.get("Destination_Multicity");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'Multicity' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterMultiCityOrigin1(origin1);
			Log.message("3.Successfully entered Multicity Origin1 '<b>" + origin1 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination1(destination1);
			Log.message("4.Successfully entered Multicity Destination1 '<b>" + destination1 + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectMultiCityDateDeparture1(departureDate);
			Log.message("5.Successfully selected the Multicity Departure1 date: <b>" + departDate + "</b>(YY/MM/DD)");

			homePage.enterMultiCityOrigin2(origin2);
			Log.message("6.Successfully entered Multicity Origin2 '<b>" + origin2 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination2(destination2);
			Log.message("7.Successfully entered Multicity Destination1 '<b>" + destination2 + "</b>' in Yatra Homepage");

			// step: select Departure date
			String returndate = homePage.selectMultiCityDateDeparture2(returnDate);
			Log.message("8.Successfully selected the Multicity Departure2 date: <b>" + returndate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("9.Successfully specified Passenger Info");

			// step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("10.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("11.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page for DOM-Multicity ");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page for  DOM-Multicity",
					"<b>Actual Result:</b> User should not navigated on SearchResult page for DOM-Multicity",
					driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Flight Search INTL-Multicity", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_006(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin1 = testData.get("Origin");
		String origin2 = testData.get("Origin_Multicity");
		String tripType = testData.get("TripType");
		String destination1 = testData.get("Destination");
		String destination2 = testData.get("Destination_Multicity");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'Multicity' option in search Home Page ");

			// step: enter Origin1 place in Yatra Home page
			homePage.enterMultiCityOrigin1(origin1);
			Log.message("3.Successfully entered Multicity Origin1 '<b>" + origin1 + "</b>' in Yatra Homepage");

			// step: enter Destination1 in Yatra Home page
			homePage.enterMultiCityDestination1(destination1);

			Log.message("4.Successfully entered Multicity Destination1 '<b>" + destination1 + "</b>' in Yatra Homepage");

			// step: select Departure date1
			String departDate = homePage.selectMultiCityDateDeparture1(departureDate);
			Log.message("5.Successfully selected the Multicity Departure1 date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: enter Origin2 in Yatra Home page
			homePage.enterMultiCityOrigin2(origin2);
			Log.message("6.Successfully entered Multicity Origin2 '<b>" + origin2 + "</b>' in Yatra Homepage");

			// step: enter Destination2 in Yatra Home page
			homePage.enterMultiCityDestination2(destination2);
			Log.message("7.Successfully entered Multicity Destination1 '<b>" + destination2 + "</b>' in Yatra Homepage");

			// step: select Departure date2
			String returndate = homePage.selectMultiCityDateDeparture2(returnDate);
			Log.message("8.Successfully selected the Multicity Departure1 date: <b>" + returndate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("9.Successfully specified Passenger Info");

			// step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("10.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("11.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page for INTL-Multicity");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page for INTL-Multicity",
					"<b>Actual Result:</b> User should not navigated on SearchResult page for INTL-Multicity",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Validating the different modules of Search Result Page for OneWay(OW) search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_007(HashMap<String, String> testData) throws Exception {		
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
			
			//step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			//step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			//step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			//step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			//step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("6.Successfully specified Passenger Info");

			//step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.Successfully selected Passenger class and clicked Done button");

			//step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");				
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating the different modules of Search Result Page for OneWay(OW) search");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.verifySRPMenu(tripType), "<b>Actual Result:</b> SRP Page should appeared with <b>Modify Search button, Airline Matrix, Filter options, SRP Results (Tabular form), Set Fare Alerts, Share Itinerary and Footer </b>headers",
					"<b>Actual Result:</b> SRP Page should not appeared with <b> Modify Search button, Airline Matrix, Filter options, SRP Results (Tabular form), Set Fare Alerts, Share Itinerary and Footer </b>headers", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating the different modules of Search Result Page for Round Trip(RT) search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_008(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			//step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip ' option in search Home Page ");

			//step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			//step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			//step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			//step: select Return date
			String returndate = homePage.selectReturnDate(returnDate);
			Log.message("6.Successfully selected the Return date: <b>" + returndate + "</b>(YY/MM/DD)");

			//step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("7.Successfully specified Passenger Info");

			//step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("8.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("9.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating the different modules of Search Result Page for Round Trip(RT) search");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.verifySRPMenu(tripType),
					"<b>Actual Result:</b> SRP Page should appeared with <b> Modify Search button, Airline Matrix, Prev Day/Next Day search, LFF calendar, Filter options, SRP Results (Tabular form), Set Fare Alerts, Share Itinerary and Footer </b>headers",
					"<b>Actual Result:</b> SRP Page should not appeared with <b> Modify Search button, Airline Matrix, Prev Day/Next Day search, LFF calendar, Filter options, SRP Results (Tabular form), Set Fare Alerts, Share Itinerary and Footer headers </b>", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validating the different modules of Search Result Page for MultiCity(MC) search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_009(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin1 = testData.get("Origin");
		String origin2 = testData.get("Origin_Multicity");
		String tripType = testData.get("TripType");
		String destination1 = testData.get("Destination");
		String destination2 = testData.get("Destination_Multicity");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			//step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'Multicity' option in search Home Page ");

			//step: enter Origin place in Yatra Home page
			homePage.enterMultiCityOrigin1(origin1);
			Log.message("3.Successfully entered Multicity Origin1 '<b>" + origin1 + "</b>' in Yatra Homepage");

			//step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination1(destination1);
			Log.message("4.Successfully entered Multicity Destination1 '<b>" + destination1 + "</b>' in Yatra Homepage");

			//step: select Departure date
			String departDate = homePage.selectMultiCityDateDeparture1(departureDate);
			Log.message("5.Successfully selected the Multicity Departure1 date: <b>" + departDate + "</b>(YY/MM/DD)");

			homePage.enterMultiCityOrigin2(origin2);
			Log.message("6.Successfully entered Multicity Origin2 '<b>" + origin2 + "</b>' in Yatra Homepage");

			//step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination2(destination2);
			Log.message("7.Successfully entered Multicity Destination1 '<b>" + destination2 + "</b>' in Yatra Homepage");

			//step: select Departure date
			String returndate = homePage.selectMultiCityDateDeparture2(returnDate);
			Log.message("8.Successfully selected the Multicity Departure2 date: <b>" + returndate + "</b>(YY/MM/DD)");

			//step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("9.Successfully specified Passenger Info");

			//step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("10.Successfully selected Passenger class and clicked Done button");

			//step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("11.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating the different modules of Search Result Page for MultiCity(MC) search ");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.verifySRPMenu(tripType),
					"<b>Actual Result:</b> SRP Page should appeared with<b> Modify Search button, Airline Matrix, Prev Day/Next Day search, Filter options, SRP Results (Tabular form), Set Fare Alerts, Share Itinerary and Footer</b> headers",
					"<b>Actual Result:</b> SRP Page should not appeared with<b> Modify Search button, Airline Matrix, Prev Day/Next Day search, Filter options, SRP Results (Tabular form), Set Fare Alerts, Share Itinerary and Footer</b> headers", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating the SRP Header", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_010(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("6.Successfully selected Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");
			
			//getting text from headers
			String myAccountText = searchResult.getTextMyAccount();
			String supportText = searchResult.getTextSupport();
			String splDealsText = searchResult.getTextSplDeals();
			String recentSearchText = searchResult.getTextRecentSearch();	

			Log.message("<br>");
			Log.message("<b>Expected Result:</b>Validated the SRP Header");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon", "lnkYatraLogo"), searchResult),
					"<b>Actual Result:</b> Yatra logo and Headers are displayed, The Headers title names are My Account is displayed as: <b>: " + myAccountText +"</b>, Support is dispalyed as <b>: " + supportText +"</b>, Special Deals is dispalyed as <b>: " + splDealsText +"</b>, Recent Search is dispalyed as : <b>" + recentSearchText +"</b> ",
					"<b>Actual Result:</b> Yatra logo and Headers are not displayed", driver);
		
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Validating the action on clicking on Yatra logo", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_011(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("6.Successfully selected Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");			
			
			String yatraLogoTitle = searchResult.getTextFromYatraLogo();
			Log.assertThat(yatraLogoTitle.equals("yatra.com"),
					"<b>Actual Result:</b> Yatra Logo tool tip should appear 'yatra.com' in SRP",
					"<b>Actual Result:</b> Yatra Logo tool tip should not appear 'yatra.com' in SRP");
			
			
			// step: click 'Search' button in Yatra Home page
			homePage = searchResult.clickYatraLogo();
			Log.message("9.Successfully clicked 'Yatra' Logo in SRP ");
			Log.message("<br>");			
			Log.message("<b>Expected Result:</b> Validated the action on clicking on Yatra logo.");
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("btnSearch"), homePage),
					"<b>Actual Result:</b> Yatra.com tooltip should appeared and redirected to Yatra Homepage" ,
					"<b>Actual Result:</b> Yatra.com tooltip should not appeared and not redirected to Yatra Homepage",  driver);
		
			BrowserActions.nap(2);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating the SRP Header for logged in User", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_012(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2.Successfully Logged in Yatra account");
			
			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("4.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("5.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("6.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("7.Successfully selected Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("8.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("9.Successfully clicked 'Search' button in Yatra Homepage ");
			
			//getting text from headers
			String userAcctNametText = searchResult.getTextUserAcctName();
			String supportText = searchResult.getTextSupport();
			String splDealsText = searchResult.getTextSplDeals();
			String recentSearchText = searchResult.getTextRecentSearch();	

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated Yatra logo and logged in User menu items");
			BrowserActions.nap(3);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon", "lnkYatraLogo"), searchResult),
					"<b>Actual Result:</b> Yatra logo and logged in User menu items are displayed, UserName is <b>: " + userAcctNametText +"</b>, Support is dispalyed as <b>: " + supportText +"</b>, Special Deals is dispalyed as <b>: " + splDealsText +"</b>, Recent Search is dispalyed as : <b>" + recentSearchText +"</b> ",
					"<b>Actual Result:</b> Yatra logo and logged in User menu items are not dsiplayed", driver);
		
			BrowserActions.nap(2);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
	@Test( description = "Validating the action on clicking on My Account header Menu items", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_013(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");			
			
			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("6.Successfully selected Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");
			
			//getting text from headers
			searchResult.mouseOverMyAccount();
			String loginText = searchResult.getTextLogin();
			String signUpText = searchResult.getTextSignUp();
			String corporateLoginText = searchResult.getTextCorporateLogin();
			String agentLoginText = searchResult.getTextAgentLogin();	
			String myBookingText = searchResult.getMyBookings();	

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated the action on clicking on My Account header Menu items");
			BrowserActions.nap(3);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon", "lnkYatraLogo"), searchResult),
					"<b>Actual Result:</b> Successfully displayed My Account header menu items, and the Login is dispalyed as <b>: " + loginText +"</b>, SignUp is dispalyed as <b>: " + signUpText +"</b>, Corporate Login is dispalyed as <b>: " + corporateLoginText +"</b>, Agent Login is dispalyed as : <b>" + agentLoginText +"</b>, My Bookings is dispalyed as : <b>" + myBookingText +"</b> ",
					"<b>Actual Result:</b> Not displayed My Account header Menu items ", driver);
		
			BrowserActions.nap(2);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	 
	@Test( description = "Check total flight count", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_014(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("6.Successfully selected Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");			
			
			//getting text from headers			
			String totalFlightCountText[] = searchResult.getCountofTotalFlights().split(" ");
			String flightCount=totalFlightCountText[1];
			String flightSearchDurationText[] = searchResult.getFlightSearchDuration().split(" ");
			String duration=flightSearchDurationText[1];			
			String totalValues=searchResult.getCountofTotalFlightsAndDuration();	
			
			Log.assertThat(totalValues.equals("Found "+flightCount+ " flights IN "+duration+" SECONDS"),
					"<b>Actual Result:</b> Successfully verified Flight count and Flight search duration</b> ",
					"<b>Actual Result:</b> Not verified Flight count and Flight search duration</b> ", driver);
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verified Flight count and Flight search duration");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully verified Flight count and Search duration, and the total Flight Count: <b> " + flightCount +"</b>, Flight search durarion: <b> " + duration +"</b> ",
					"<b>Actual Result:</b> Not verified Flight count and Flight search duration", driver);
		
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
	@Test( description = "Validating the Matrix Result finder for OW search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_015(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("6.Successfully selected Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");			
			
			//getting text from OneWay Journey city and depart date				
			String sourceCityText = searchResult.getTextSourceCity_OW();
			String destCityText = searchResult.getTextDestinationCity_OW();
			String sourceDateText = searchResult.getTextSourceDate_OW();
		
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated the Matrix Result finder for OW search");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully validated Matrix result, Source city: <b> " + sourceCityText +"</b>, Source Date: <b> " + sourceDateText +"</b> and Destination city:<b> " + destCityText +"</b> ",
					"<b>Actual Result:</b> Not displayed Matrix results header with flight icons", driver);
				
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate that Duration should appear in 'hh mm' format across the SRP", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_016(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("6.Successfully selected Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");			
			
			//getting text from OneWay Flight Duration format inSRP	
			String flightDurationText = searchResult.getTextFlightDuration();
			searchResult.clickFlightDetails();
			String flightDetailsPouUpDurationText = searchResult.getTextFlightDetailsPouUpDuration();
			Log.assertThat(flightDurationText.equals(flightDetailsPouUpDurationText),
					"<b>Actual Result:</b> Flight duration format is dispalyed as like 'hh mm' format",
					"<b>Actual Result:</b> Flight duration format is not dispalyed as like 'hh mm' format");
			
			
			searchResult.closeFlightDetailsPouUp();
			String resultStripText = searchResult.getTextResultStrip();
			Log.assertThat(resultStripText.equals("VIEWED"),"<b>Actual Result:</b> Validated Result Strip",	"<b>Actual Result:</b> Not Validated Result Strip");
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated that Duration should appear in 'hh mm' format across the SRP ");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page, and the Flight duration format is dispalyed as like 'hh mm' <b>: " + flightDurationText +"</b>, Flight details popup duration format is dispalyed as like 'hh mm' <b>: " + flightDetailsPouUpDurationText +"</b>  ",
					"<b>Actual Result:</b> User should not navigated on SearchResult page, and the Flight duration format is not dispalyed as like 'hh mm' <b>: " + flightDurationText +"</b>, Flight details popup duration format is dispalyed as like 'hh mm' <b>: " + flightDetailsPouUpDurationText +"</b>", driver);
					
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
	@Test( description = "Validating that 'Modify Search' should display prefilled respective search made from Homepage for OW", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_017(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("6.Successfully selected Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' in Yatra Homepage!");

			// step: click 'Search' button in Yatra Home page
			searchResult.clickModifySearch(); 
			Log.message("9.Successfully clicked 'Modify Search' link in SRP ");				
			
			String originCityText = searchResult.getTextOrigin_ModifySearch();		
			String destCityText = searchResult.getTextDestination_ModifySearch();			
			Log.assertThat(searchResult.verifyTripTypeInModifySearch(tripType), "<b>Actual Result:</b> Successfully selected One Way Radio button",	"<b>Actual Result:</b> Not selected One Way Radio button");
            Log.assertThat(originCityText.contains(destination), "<b>Actual Result:</b> Successfully verified Modify Search Origin City, Orgin City:  <b> "+ destination+ "</b>", "<b>Actual Result:</b> Not verified Modify Searcch Origin City");
            Log.assertThat(destCityText.contains(origin), "<b>Actual Result:</b> Successfully verified Modify Search Destination City, Destination City:  <b> " + origin + "</b>","<b>Actual Result:</b> Not verified Modify Search Destination City");
		    
            //TODO: To change the logic for depart date verification (like 10, 20, 30 dates in moth level)
           /* String deprtDateText = searchResult.getTextDepartDate_ModifySearch();
			String[] depart = deprtDateText.split("/"); 
			Log.assertThat(departDate.equalsIgnoreCase(depart[2]+"_"+depart[1].replace("0", "")+"_"+depart[0].replace("0", "")),
					"<b>Actual Result:</b> Successfully verified selected Departure date with HP, Departure Date is: <b> "+ deprtDateText+"</b>",
					"<b>Actual Result:</b> Not verified selected Departure date with HP</b> ", driver);*/
						         
            String[] pax= passengerInfo.split("_");
			String adult =pax[0]; String child =pax[1]; String infant =pax[2]; 
			String adultText = searchResult.getTextAdult_ModifySearch();
			String childText = searchResult.getTextChild_ModifySearch();
			String infantText = searchResult.getTextInfant_ModifySearch();
			Log.assertThat(adultText.contains(adult),
					"<b>Actual Result:</b> Successfully verified Modify Search Adult passenger details, Selected Adult is:  <b> " + adult+ "</b>",
					"<b>Actual Result:</b> Not verified Modify Search Adult passenger details</b> ", driver);
			
			Log.assertThat(childText.contains(child),
					"<b>Actual Result:</b> Successfully verified Modify Search Child passenger details, Selected Child is:  <b>" +child+ "</b>",
					"<b>Actual Result:</b> Not verified Modify Search Child passenger details</b> ", driver);
			
			Log.assertThat(infantText.contains(infant),
					"<b>Actual Result:</b> Successfully verified Modify Search Infant passenger details, Selected Infant is: <b>+ "+ infant + "</b>",
					"<b>Actual Result:</b> Not verified Modify Search Infant passenger details</b> ", driver);
			
			
			String passengerClassText = searchResult.getTextPassengerClass_ModifySearch();			
			Log.assertThat(passengerClassText.contains(passengerClass),
					"<b>Actual Result:</b> Successfully verified Modify Search passenger class details, Selected Passenger Class: <b>"+ passengerClassText+ " </b> ",
					"<b>Actual Result:</b> Not verified Modify Search passenger class details</b> ", driver);
			
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating that 'Modify Search' should display prefilled respective search made from Homepage for RT", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_018(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Return date
			String returndate = homePage.selectReturnDate(returnDate);
			Log.message("6.Successfully selected the Return date: <b>" + returndate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("7.Successfully selected Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("8.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("9.Successfully clicked 'Search' in Yatra Homepage!");

			// step: click 'Search' button in Yatra Home page
			searchResult.clickModifySearch(); 
			Log.message("10.Successfully clicked 'Modify Search' link in SRP ");	
			
			String originCityText = searchResult.getTextOrigin_ModifySearch();		
			String destCityText = searchResult.getTextDestination_ModifySearch();			
			Log.assertThat(searchResult.verifyTripTypeInModifySearch(tripType), "<b>Actual Result:</b> Successfully selected Round Trip Radio button",	"<b>Actual Result:</b> Not selected Round Trip Radio button");
            Log.assertThat(originCityText.contains(destination), "<b>Actual Result:</b> Successfully verified Modify Search prefilled Origin City, Orgin City:  <b> "+ destination+ "</b>", "<b>Actual Result:</b> Not verified Modify Search Origin City");
            Log.assertThat(destCityText.contains(origin), "<b>Actual Result:</b> Successfully verified Modify Search prefilled Destination City, Destination City:  <b> "+ origin+ "</b>","<b>Actual Result:</b> Not verified Modify Search Destination City");
		    
           //TODO: To change the logic for depart date verification (like 10, 20, 30 dates in moth level)
            /*String deprtDateText = searchResult.getTextDepartDate_ModifySearch();
			String[] depart = deprtDateText.split("/"); 
			Log.assertThat(departDate.equalsIgnoreCase(depart[2]+"_"+depart[1].replace("0", "")+"_"+depart[0].replace("0", "")),
					"<b>Actual Result:</b> Successfully verified selected Departure date, Departure Date is: <b> "+ deprtDateText+"</b>",
					"<b>Actual Result:</b> Not verified selected Departure date</b> ", driver);
			
			//TODO: To change the logic for return date verification (like 10, 20, 30 dates in moth level)
			String returnDateText = searchResult.getTextReturnDate_ModifySearch();
			String[] arrayReturndate = returnDateText.split("/"); 
			Log.assertThat(returndate.equalsIgnoreCase(arrayReturndate[2]+"_"+arrayReturndate[1].replace("0", "")+"_"+arrayReturndate[0].replace("0", "")),
					"<b>Actual Result:</b> Successfully verified selected Return date, Return Date is: <b> "+ returnDateText+"</b>",
					"<b>Actual Result:</b> Not verified selected Return date</b> ", driver);*/
			
            String[] pax= passengerInfo.split("_");
			String adult =pax[0]; String child =pax[1]; String infant =pax[2]; 
			String adultText = searchResult.getTextAdult_ModifySearch();
			String childText = searchResult.getTextChild_ModifySearch();
			String infantText = searchResult.getTextInfant_ModifySearch();
			Log.assertThat(adultText.contains(adult),
					"<b>Actual Result:</b> Successfully verified Modify Search prefilled Adult passenger details, Selected Adult is:  <b> " + adult+ "</b>",
					"<b>Actual Result:</b> Not verified Modify Search prefilled Adult passenger details</b> ", driver);
			
			Log.assertThat(childText.contains(child),
					"<b>Actual Result:</b> Successfully verified Modify Search prefilled Child passenger details, Selected Child is:  <b> " + child+ "</b>",
					"<b>Actual Result:</b> Not verified Modify Search prefilled Child passenger details</b> ", driver);
			
			Log.assertThat(infantText.contains(infant),
					"<b>Actual Result:</b> Successfully verified Modify Search prefilled Infant passenger details, Selected Infant is:  <b> " + infant+ "</b>",
					"<b>Actual Result:</b> Not verified Modify Search prefilled Infant passenger details</b> ", driver);
					
			String passengerClassText = searchResult.getTextPassengerClass_ModifySearch();			
			Log.assertThat(passengerClass.contains(passengerClassText),
					"<b>Actual Result:</b> Successfully verified Modify Search prefilled passenger class details, Selected Passenger Class: <b>"+ passengerClassText+ " </b> ",
					"<b>Actual Result:</b> Not verified Modify Search prefilled passenger class details</b> ", driver);
					
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
	@Test( description = "Validating that 'Modify Search' should display prefilled respective search made from Homepage for MC", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_019(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin1 = testData.get("Origin");
		String origin2 = testData.get("Origin_Multicity");
		String tripType = testData.get("TripType");
		String destination1 = testData.get("Destination");
		String destination2 = testData.get("Destination_Multicity");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");	

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'Multicity' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterMultiCityOrigin1(origin1);
			Log.message("3.Successfully entered Multicity Origin1 '<b>" + origin1 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination1(destination1);
			Log.message("4.Successfully entered Multicity Destination1 '<b>" + destination1 + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectMultiCityDateDeparture1(departureDate);
			Log.message("5.Successfully selected the Multicity Departure1 date: <b>" + departDate + "</b>(YY/MM/DD)");

			homePage.enterMultiCityOrigin2(origin2);
			Log.message("6.Successfully entered Multicity Origin2 '<b>" + origin2 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination2(destination2);
			Log.message("7.Successfully entered Multicity Destination1 '<b>" + destination2 + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departdate1 = homePage.selectMultiCityDateDeparture2(returnDate);
			Log.message("8.Successfully selected the Multicity Departure2 date: <b>" + departdate1 + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("9.Successfully specified Passenger Info");

			// step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("10.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("11.Successfully clicked 'Search' button in Yatra Homepage ");

			// step: click 'Search' button in Yatra Home page
			searchResult.clickModifySearch(); 
			Log.message("12.Successfully clicked 'Modify Search' link in SRP ");	
			
			
			String originCityText = searchResult.getTextOrigin_ModifySearch();		
			String destCityText = searchResult.getTextDestination_ModifySearch();			
			Log.assertThat(searchResult.verifyTripTypeInModifySearch(tripType), "<b>Actual Result:</b> Successfully selected Multicity Radio button",	"<b>Actual Result:</b> Not selected Multicity Radio button");
            Log.assertThat(originCityText.contains(destination1), "<b>Actual Result:</b> Successfully verified Modify Search prefilled  Origin1 City ", "<b>Actual Result:</b> Not verified Modify Search prefilled  Origin1 City");
            Log.assertThat(destCityText.contains(origin1), "<b>Actual Result:</b> Successfully verified Modify Search prefilled Destination1  City ","<b>Actual Result:</b> Not verified Modify Search prefilled Destination1 City");
		 	 
			String originCityText_MC = searchResult.getTextOrigin1_ModifySearch();		
			String destCityText_MC = searchResult.getTextDestination1_ModifySearch();			
            Log.assertThat(originCityText_MC.contains(destination2), "<b>Actual Result:</b> Successfully verified Modify Search prefilled Origin2 City  ", "<b>Actual Result:</b> Not verified Modify Search prefilled Origin2 City");
            Log.assertThat(destCityText_MC.contains(origin2), "<b>Actual Result:</b> Successfully verified Modify Search prefilled Destination2 City name","<b>Actual Result:</b> Not verified Modify Search prefilled Destination2 City");
		               
            String[] pax= passengerInfo.split("_");
			String adult =pax[0]; String child =pax[1]; String infant =pax[2]; 
			String adultText = searchResult.getTextAdult_ModifySearch();
			String childText = searchResult.getTextChild_ModifySearch();
			String infantText = searchResult.getTextInfant_ModifySearch();
			Log.assertThat(adultText.contains(adult),
					"<b>Actual Result:</b> Successfully verified Modify Search prefilled Adult passenger details, Selected Adult is:  <b> " + adult+ "</b>",
					"<b>Actual Result:</b> Not verified Modify Search prefilled Adult passenger details</b> ", driver);
			
			Log.assertThat(childText.contains(child),
					"<b>Actual Result:</b> Successfully verified Modify Search prefilled Child passenger details, Selected Child is:  <b> " + child+ "</b>",
					"<b>Actual Result:</b> Not verified Modify Search prefilled Child passenger details</b> ", driver);
			
			Log.assertThat(infantText.contains(infant),
					"<b>Actual Result:</b> Successfully verified selectModify Search prefilleded Infant passenger details, Selected Infant is:  <b> " + infant+ "</b>",
					"<b>Actual Result:</b> Not verified Modify Search prefilled Infant passenger details</b> ", driver);
						
			String passengerClassText = searchResult.getTextPassengerClass_ModifySearch();			
			Log.assertThat(passengerClassText.contains(passengerClass),
					"<b>Actual Result:</b> Successfully verified Modify Search prefilled passenger class details, Selected Passenger Class: <b>"+ passengerClassText+ " </b> ",
					"<b>Actual Result:</b> Not verified Modify Search prefilled passenger class details</b> ", driver);
						
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(description = "Validating the Pax Details section on Modify Search pop up", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_023(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Modify Search' button
			searchResult.clickModifySearch();
			Log.message("5. Successfully clicked 'Modify Search'!");
			ArrayList<String> PaxDetails = searchResult.getTextPaxDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating the Pax Details section on Modify Search pop up");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("PaxDeatilInModify"), searchResult),
					"<b>Actual Result :</b> Pax Details In Modify Search is visible to the user and deatils are displayed as \n:"+ PaxDetails,
					"<b>Actual Result :</b> Pax Details In Modify Search is not visible to the user", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating that for DOM search Class dropdown would not contain First Class option", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_024(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for OneWay ");
					
			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			// step: click 'Search' button in Yatra Home page
			searchResult.clickModifySearch(); 
			Log.message("5.Successfully clicked 'Modify Search' link in SRP ");				
			
			List<String> passengerClassNames = searchResult.getPassengerClasssDetailsInMofifySearch();		
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated that for DOM search Class dropdown would not contain First Class option");
			BrowserActions.nap(2);
			Log.assertThat(!passengerClassNames.contains("First Class"),
					"<b>Actual Result:</b> DOM Flights Passenger Class dropdown sholud not contain First Class option</b>, Passenger Class List are :<b> "+ passengerClassNames +"<b>",
					"<b>Actual Result:</b> DOM Flights Passenger Class dropdown sholud contain First Class option</b> Passenger Class List are : <b> " + passengerClassNames  +"<b>", driver);
			
		   Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating that for INT search Class dropdown should contain First Class option", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_025(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for OneWay ");
					
			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			// step: click 'Search' button in Yatra Home page
			searchResult.clickModifySearch(); 
			Log.message("5.Successfully clicked 'Modify Search' link in SRP ");				
			
			List<String> passengerClassNames = searchResult.getPassengerClasssDetailsInMofifySearch();		
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated that for INT search Class dropdown contain First Class option");
			BrowserActions.nap(2);
			Log.assertThat(passengerClassNames.contains("First Class"),
					"<b>Actual Result:</b> INT Flights Passenger Class dropdown sholud contain First Class option</b>, Passenger Class List are :<b>  "+ passengerClassNames +"<b>",
					"<b>Actual Result:</b> INT Flights Passenger Class dropdown sholud not contain First Class option</b> Passenger Class List are :<b> " + passengerClassNames+"<b>" , driver);
			
		   Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating that Weekly fare Matrix will be available for OW search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_026(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");
	
			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for OneWay ");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated that Weekly fare Matrix will be available for OW search");
			BrowserActions.nap(3);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("weeklyStrip"), searchResult),
					"<b>Actual Result:</b> The Weekly fare Matrix displayed on the SRP page for OW Search",
					"<b>Actual Result:</b> The Weekly fare Matrix not displayed on the SRP page for OW Search",	driver);
			
			Log.assertThat(searchResult.verifyCurrentDateSelectionInWeeklyMatrix(), "<b>Actual Result:</b> Successfully selected Current date in weekly matrix", "<b>Actual Result:</b> Not selected Current date in weekly matrix");
			String currentdateFareText = searchResult.getTextCurrentDateFareInWeeklyMatrix();
			currentdateFareText = currentdateFareText.replace(" ", "").toString().trim();
			String lowesrFlightFareText = searchResult.getTextLowestFlightFareInAirlineMatrix(); 
			lowesrFlightFareText = lowesrFlightFareText.toString().trim();
			Log.assertThat(currentdateFareText.equalsIgnoreCase(lowesrFlightFareText),
					"<b>Actual Result:</b> Successfully shown lowest fare in matrix for that date, Fare: <b> "+ currentdateFareText +"</b>",
					"<b>Actual Result:</b> Not shown lowest fare in matrix for that date</b> ", driver);
						
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating that Weekly fare Matrix will not be available for RT search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_027(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");
						
			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated that Weekly fare Matrix will not be available for RT search");
			BrowserActions.nap(25);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("weeklyFlightsStrip"), searchResult),
					"<b>Actual Result:</b> The Weekly fare Matrix not displayed on the SRP page for RT Search",
					"<b>Actual Result:</b> The Weekly fare Matrix displayed on the SRP page for RT Search",
					driver);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating that Weekly fare Matrix will not be available for MC search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_028(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin1 = testData.get("Origin");
		String origin2 = testData.get("Origin_Multicity");
		String tripType = testData.get("TripType");
		String destination1 = testData.get("Destination");
		String destination2 = testData.get("Destination_Multicity");
		String departureDate1 = testData.get("DepartureDate");
		String departureDate2 = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'Multicity' option in search Home Page ");
	
			//step: selectMulticity Search fields in HomePage
			homePage.selectMultiCityFlightSearchFields(origin1, destination1, departureDate1, origin2, destination2, departureDate2, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'Multicity'");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating that Weekly fare Matrix will not be available for MC search");
			BrowserActions.nap(10);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("weeklyFlightsStrip"), searchResult),
					"<b>Actual Result:</b> The Weekly fare Matrix not displayed on the SRP page for MC Search",
					"<b>Actual Result:</b> The Weekly fare Matrix displayed on the SRP page for MC Search",
					driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
	@Test( description = "Validating the Airline Matrix", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_029(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");			
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated the Airline Matrix");
			Utils.waitForPageLoad(driver);
			Log.assertThat(searchResult.verifyAllAirlineMatrixSelection(), "<b>Actual Result:</b> 'All Airlines' option should appear with deafult selection ", "<b>Actual Result:</b> 'All Airlines' option should not appear with deafult selection", driver);
			List<String> airlineMatrixFareDetails = searchResult.getAirlineMatrixFareDetails();	
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("lnkAirlineMatrixStrip"), searchResult),
					"<b>Actual Result:</b> Airline Matrix flights arranged to increasing orderwise fare: <b>"+ airlineMatrixFareDetails + "</b>",
					"<b>Actual Result:</b> Airline Matrix flights increasing fare is not displayed on Search Result page", driver);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating the UI of Airline Matrix", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_030(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");			
			BrowserActions.nap(12);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should redirected to SRP and Validating the UI of Airline Matrix");
			Log.assertThat(searchResult.verifyAllAirlineMatrixSelection(), "<b>Actual Result:</b> 'All Airlines' option should appear with by deafult selection ", "<b>Actual Result:</b> 'All Airlines' option should not appear with by deafult selection", driver);
			Log.assertThat(searchResult.verifyAirlinelogoInMatrix(), "<b>Actual Result:</b> Successfully displayed Airline Logo's in Airline Matrix strip ", "<b>Actual Result:</b> Not displayed Airline Logo's in Airline Matrix strip", driver);
			List<String> airlineNames = searchResult.getAirlineNamesInMatrix();				
			List<String> airlineMatrixFareDetails = searchResult.getAirlineMatrixFareDetails();	
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("lnkAirlineMatrixStrip"), searchResult),
					"<b>Actual Result:</b> Airline Matrix flights arranged to increasing lowest fare orderwise, Airline Names: <b>"+ airlineNames + "</b>, and lowest fare of respective Airlines <b>"+ airlineMatrixFareDetails + "</b>",
					"<b>Actual Result:</b> Airline Matrix flights not arranged to increasing lowest fare orderwise", driver);
			
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating the action on clicking on Airline Matrix for any Airline", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_031(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");	
			BrowserActions.nap(12);
			
			searchResult.clickAirlineInAirlineMatrix();
			Log.message("5.Successfully clicked Airline on the Airline Matrix");	
			String airlineName = searchResult.getTextSelectedAirlineName();				
			searchResult.verifySelectedAirlineInAirlineFilters(airlineName);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated the action on clicking on Airline Matrix for any Airline");
			Log.assertThat(searchResult.verifySelectedAirlineInAirlineFilters(airlineName),
					"<b>Actual Result:</b> Successfully verified selected Airline in Filter Airline, Selected Airline Name: <b>"+ airlineName + "</b> ",
					"<b>Actual Result:</b> Not verified selected Airline in Filter Airline", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating that Prev day/Next day should not be availble for OW search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_032(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");				
			BrowserActions.nap(12);					
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Prev day/Next day search option should not be available on SRP for OW search");
			Log.assertThat(!(searchResult.verifyOnwardAndReturnLFF("PrevDay") && searchResult.verifyOnwardAndReturnLFF("NextDay")), "<b>Actual Result:</b> Successfully validated Prev day/Next day search option are not available on SRP", "<b>Actual Result:</b> Prev day/Next day search option are available on SRP", driver);
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating the Prev day/Next day for RT search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_033(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");	
			BrowserActions.nap(12);						
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Prev day/Next day search option should be available above Result Grid for both Onward and Return leg in SRP");
			Log.assertThat((searchResult.verifyOnwardLegLinks("PrevDay") && searchResult.verifyOnwardLegLinks("NextDay")), "<b>Actual Result:</b> Successfully validated Prev day/Next day search options on Onward leg in SRP", "<b>Actual Result:</b> Prev day/Next day search options are not available on Onwards leg in SRP", driver);
			Log.assertThat((searchResult.verifyReturnLegLinks("PrevDay") && searchResult.verifyReturnLegLinks("NextDay")), "<b>Actual Result:</b> Successfully validated Prev day/Next day searchs on Return leg in SRP", "<b>Actual Result:</b> Prev day/Next day search options are not available on Return leg in SRP", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating the functionality of Next Day for RT search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_034(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			
			Log.assertThat((searchResult.verifyOnwardLegLinks("PrevDay") && searchResult.verifyOnwardLegLinks("NextDay")), "<b>Actual Result:</b> Successfully validated Prev day/Next day search options on Onward leg in SRP", "<b>Actual Result:</b> Prev day/Next day search options are not available on Onwards leg in SRP", driver);
			Log.assertThat((searchResult.verifyReturnLegLinks("PrevDay") && searchResult.verifyReturnLegLinks("NextDay")), "<b>Actual Result:</b> Successfully validated Prev day/Next day searchs on Return leg in SRP", "<b>Actual Result:</b> Prev day/Next day search options are not available on Return leg in SRP", driver);
			
			String dapartureDateBefore = searchResult.getDepartureDate();
			// step: click Next Day button
			searchResult.clickOnwardNextDayButton();	
			Log.message("5.Successfully clicked Next Day button ");
			
			String dapartureDateAfter = searchResult.getDepartureDate();			
			BrowserActions.nap(2);						
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated the functionality of Next Day for RT search");
			Log.assertThat(!(dapartureDateBefore.equals(dapartureDateAfter)) , "<b>Actual Result:</b> Successfully updated Result set following changes", "<b>Actual Result:</b> Result set is not updated following changes", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
	@Test( description = "Validating the functionality of Prev Day for RT search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_035(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			
			Log.assertThat((searchResult.verifyOnwardLegLinks("PrevDay") && searchResult.verifyOnwardLegLinks("NextDay")), "<b>Actual Result:</b> Successfully validated Prev day/Next day search options on Onward leg in SRP", "<b>Actual Result:</b> Prev day/Next day search options are not available on Onwards leg in SRP", driver);
			Log.assertThat((searchResult.verifyReturnLegLinks("PrevDay") && searchResult.verifyReturnLegLinks("NextDay")), "<b>Actual Result:</b> Successfully validated Prev day/Next day searchs on Return leg in SRP", "<b>Actual Result:</b> Prev day/Next day search options are not available on Return leg in SRP", driver);
			
			String dapartureDateBefore = searchResult.getDepartureDate();			
			// step: click Prev Day button
			searchResult.clickOnwardPrevDayButton();	
			Log.message("5.Successfully clicked Prev Day button ");
			
			String dapartureDateAfter = searchResult.getDepartureDate();			
			BrowserActions.nap(2);						
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated the functionality of Prev Day for RT search");
			Log.assertThat(!(dapartureDateBefore.equals(dapartureDateAfter)) , "<b>Actual Result:</b> Successfully updated Result set following changes", "<b>Actual Result:</b> Result set is not updated following changes", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate the tool tips for Prev day/Next day search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_036(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");	
			BrowserActions.nap(12);									
			String onwardPrevDayText = searchResult.getTextOnwardPrevDaySearch();
			String onwardNextDayText = searchResult.getTextOnwardNextDaySearch();
			String nextPrevDayText = searchResult.getTextReturnPrevDaySearch();
			String nextNextDayText = searchResult.getTextReturnNextDaySearch();
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Prev day/Next day search button tool tips text should appear in SRP");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("lnkPrevDay_OnwardLeg", "lnkNextDay_OnwardLeg"), searchResult),
					"<b>Actual Result:</b> Successfully appeared tool tip of Prev/Next dates on Onwards Leg: <b>"+ onwardPrevDayText + "</b> / <b>"+onwardNextDayText+"</b> and Return leg: <b>"+ nextPrevDayText + "</b> / <b>"+nextNextDayText+"</b>",
					"<b>Actual Result:</b> Not Prev day/Next day search button tool tips text should appear in SRP", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate that LFF is not available for MC Search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_037(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin1 = testData.get("Origin");
		String origin2 = testData.get("Origin_Multicity");
		String tripType = testData.get("TripType");
		String destination1 = testData.get("Destination");
		String destination2 = testData.get("Destination_Multicity");
		String departureDate1 = testData.get("DepartureDate");
		String departureDate2 = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'Multicity' option in search Home Page ");
	
			//step: selectMulticity Search fields in HomePage
			homePage.selectMultiCityFlightSearchFields(origin1, destination1, departureDate1, origin2, destination2, departureDate2, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'Multicity'");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");			
			BrowserActions.nap(20);				
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated that LFF is not available for MC Search");
			Log.assertThat(!searchResult.verifyOnwardAndReturnLFF("OLFF"), "<b>Actual Result:</b> Successfully validated LFF is not displayed for MC Search", "<b>Actual Result:</b> LFF is displayed for MC Search", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate the LFF calendar for OW search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_038(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");				
			BrowserActions.nap(12);					
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated that LFF is not diplayed for OW Search");
			Log.assertThat(!searchResult.verifyOnwardAndReturnLFF("OLFF"), "<b>Actual Result:</b> Successfully validated LFF is not displayed for OW Search", "<b>Actual Result:</b> LFF is displayed for OW Search", driver);
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate the LFF calendar for RT search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_039(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");	
			BrowserActions.nap(12);				
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated that LFF is not available for MC Search");
			Log.assertThat(searchResult.verifyOnwardAndReturnLFF("OLFF"), "<b>Actual Result:</b> Successfully validated onward LFF is displayed for RT Search", "<b>Actual Result:</b> Onward LFF is not displayed for RT Search", driver);
			Log.assertThat(searchResult.verifyOnwardAndReturnLFF("RLFF"), "<b>Actual Result:</b> Successfully validated Return LFF is displayed for RT Search", "<b>Actual Result:</b> Return LFF is not displayed for RT Search", driver);
			
			searchResult.clickOnwardAndReturnLFF("OLFF");
			//String textTravelDetails = searchResult.getTextOnwardAndReturnLFF("OLFF");
			String textOnwardCalender = searchResult.getTextCalenderInOnwardAndReturnLFF("OLFF");			
			searchResult.clickOnwardAndReturnLFF("RLFF");
			String textReturnCalender = searchResult.getTextCalenderInOnwardAndReturnLFF("RLFF");				
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("lnkOnwardLFF", "lnkReturnLFF"), searchResult),
					"<b>Actual Result:</b> Successfully verified Onward Calender Name text: <b>"+ textOnwardCalender + "</b> and Return Calender Name text: <b>"+ textReturnCalender + "</b> ",
					"<b>Actual Result:</b> Not verified Onward and Return Calender Name text", driver);
			
			
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Validate the 'Fare Alerts' on SRP - OW Search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_040(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			//Step: select Trip type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			//Step: click Search button
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");	
			BrowserActions.nap(10);	
			
			//step: click Fare alert Popup
			searchResult.clickFareAlertPopup();
			Log.message("5.Successfully clicked 'Fare Alert' in SRP");	
			
			BrowserActions.nap(2);					
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate the 'Set A Fare Alert' text on  Fare Alert popup ");
			String fareAlertTitleText = searchResult.getTextFareAlertPopupTitle();			
			Log.assertThat(fareAlertTitleText.contains("Set A Fare Alert"),
					"<b>Actual Result:</b> Successfully verified Fare Alert popup title text:  <b>"+ fareAlertTitleText+"</b>",
					"<b>Actual Result:</b> Not verified Fare Alert popup title text</b> ", driver);
			
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate the functionality of 'Fare Alerts' - OW Search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_041(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String email = testData.get("EmailAddress");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");	
		String mobile = testData.get("Mobile");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			//Step: select Trip type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			//Step: click Search button
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");	
			
			//step: click Fare alert Popup
			searchResult.clickFareAlertPopup();
			Log.message("5.Successfully clicked 'Fare Alert' in SRP");	
			
			String fareAlertTitleText = searchResult.getTextFareAlertPopupTitle();			
			Log.assertThat(fareAlertTitleText.contains(Constants.C_FareAlert_Title),"<b>Actual Result:</b> Successfully verified Fare Alert popup title text:  <b>"+ fareAlertTitleText+"</b>", "<b>Actual Result:</b> Not verified Fare Alert popup title text</b> ", driver);
			
			String leavingfromText = searchResult.getTextLeavingFromInFareAlert();	
			String goingToText =  searchResult.getTextGoingToInFareAlert();	
			String ExactDatesText =  searchResult.getTextExactDatesInFareAlert();
			String daysText =  searchResult.getTextDaysInFareAlert();											
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("txtOrigin_FareAlert", "txtDestination_FareAlert"), searchResult),
					"<b>Actual Result:</b> Successfully appeared Leaving from and Going to prefilled text boxes with Depart and Destination city's are <b>"+ leavingfromText + "</b> , <b>"+goingToText+"</b> and Successfully appeared Exact Dates / +-3 Days option are : <b>"+ ExactDatesText + "</b> , <b>"+daysText+"</b>",
					"<b>Actual Result:</b> Not appeared Leaving from and Going to prefilled text boxes with Depart and Destination city's and  Exact Dates / +-3 Days option in Fare Alerts popup", driver);
			
			
			searchResult.enterMaxPriceInFareAlert("10000");	
			searchResult.enterEmailInFareAlert(email);	
			searchResult.selectMobileSTDInFareAlert("91");
			searchResult.enterMobileInFareAlert(mobile);				
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("txtMaxPrice_FareAlert", "txtEmail_FareAlert" , "txtMobile_FareAlert", "btnSetAlert_FareAlert"), searchResult),
					"<b>Actual Result:</b> Successfully appeared Max Price, Email, Mobile, Set Alert button options in Fare Alert popup</b>",
					"<b>Actual Result:</b> Not Max Price, Email, Mobile, Set Alert button options in Fare Alert popup", driver);
							
			//step: click Fare alert pop up
			searchResult.clickSetAlertButtonInFareAlertp();
			Log.message("6.Successfully clicked 'Set Alert' button on Fare Laert popup");	
			
			Log.message("<br>");				
			Log.message("<b>Expected Result:</b> Validated the functionality of 'Fare Alerts ");
			String successMessageText = searchResult.getTextFareAlertSuccessMesaage();			
			Log.assertThat(successMessageText.contains(Constants.C_FareAlert_Message),"<b>Actual Result:</b> Successfully verified Fare Alert Message text:  <b>"+ successMessageText+"</b>", "<b>Actual Result:</b> Not verified Fare Alert popup Message text</b> ", driver);
			
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate the functionality of share Itinerary", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_042(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");				
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String email = testData.get("EmailAddress");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			//Step: select Trip type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			//Step: click Search button
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked Search in Yatra Homepage!");				
			
			String shareItineraryText = searchResult.getTextShareItinerary();	
			Log.assertThat(shareItineraryText.contains("Share Itinerary"),"<b>Actual Result:</b> Successfully should appeared Share Itinerary tool tip ", "<b>Actual Result:</b> Not appeared Share Itinerary tool tip ");
							
			//step: click Share Itinerary
			searchResult.clickShareItinerary();
			Log.message("5.Successfully clicked Share Itinerary in SRP");
			
			//step: click Share Itinerary
			searchResult.clickFlightItineraryChkBoxInShareItinerary();
			Log.message("6.Successfully selected Flight checkbox in Share Itinerary");
			
			//step: click Share Itinerary
			searchResult.enterEmailInShareItinerary(email);
			Log.message("7.Successfully entered Email id in Share Itinerary, Mail id: <b>" + email +"</b>");
			
			//step: click Share Itinerary
			searchResult.enterMessageInShareItinerary("Text Message");
			Log.message("8.Successfully entered Message in Share Itinerary, Mail id: <b>Text Message</b>");
			
			//step: click Share Itinerary
			searchResult.clickShareInShareItinerary();
			Log.message("9.Successfully clicked Share button in Share Itinerary");
			
			Log.message("<br>");				
			Log.message("<b>Expected Result:</b> Validated the functionality of Share Itinerary ");
			String shareItineraryMessageText = searchResult.getTextShareItineraryPouUpMessage();			
			Log.assertThat(shareItineraryMessageText.contains(Constants.C_ShareItinerary_Message),"<b>Actual Result:</b> Successfully verified Share Itinerary functionality, Share Itinerary Message text:  <b>"+ shareItineraryMessageText+"</b>", "<b>Actual Result:</b> Not verified verified Share Itinerary functionality</b> ", driver);
																	
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate the various filter options present on SRP", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_043(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");				
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");	

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			//Step: select Trip type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			//Step: click Search button
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");				
			
			Log.message("<br>");				
			Log.message("<b>Expected Result:</b> Validated the various filter options present on SRP ");
			
			
			//verify Filter options in SRP result page
			String priceFilterText = searchResult.getTextPriceFilterTitle();			
			Log.assertThat(priceFilterText.toUpperCase().contains(Constants.C_Price),"<b>Actual Result:</b> Successfully verified Price filter option in SRP, Price Filter Title :  <b>"+ priceFilterText+"</b>", "<b>Actual Result:</b> Not verified Price filter option in SRP</b> ", driver);
							
			String deparTimeFilterText = searchResult.getTextDepartTimeFilterTitle();			
			Log.assertThat(deparTimeFilterText.toUpperCase().contains(Constants.C_DepartTime),"<b>Actual Result:</b> Successfully verified Depart Time filter option in SRP, Deaprt Time Filter Title :  <b>"+ deparTimeFilterText+"</b>", "<b>Actual Result:</b> Not verified Depart Time filter option in SRP</b> ", driver);
						
			String stopFilterText = searchResult.getTextStopsFilterTitle();			
			Log.assertThat(stopFilterText.toUpperCase().contains(Constants.C_Stops),"<b>Actual Result:</b> Successfully verified Stops option in SRP, Stops Filter Title :  <b>"+ stopFilterText+"</b>", "<b>Actual Result:</b> Not verified Stops filter option in SRP</b> ", driver);
							
			String fareTypeFilterText = searchResult.getTextFareTypeFilterTitle();			
			Log.assertThat(fareTypeFilterText.toUpperCase().contains(Constants.C_FareType),"<b>Actual Result:</b> Successfully verified Fare Type filter option in SRP, Fare Type Filter Title :  <b>"+ fareTypeFilterText+"</b>", "<b>Actual Result:</b> Not verified Fare Type filter option in SRP</b> ", driver);
			
			String airlinesFilterText = searchResult.getTextAirlinesFilterTitle();			
			Log.assertThat(airlinesFilterText.toUpperCase().contains(Constants.C_Airlines),"<b>Actual Result:</b> Successfully verified Airlines filter option in SRP, Airlines Filter Title :  <b>"+ airlinesFilterText+"</b>", "<b>Actual Result:</b> Not verified Airlines filter option in SRP</b> ", driver);
											
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate the Filter if user has selected Non stop Flights only from homepage", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_044(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");				
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");	

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			//Step: select Trip type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields");

			//Step: click Non Stop Flights Checkbox
		    homePage.clickNonStopFlightsCheckbox();
		    Log.message("4.Successfully cliecked Non Stop Flights checkbox");
		    
			//Step: click Search button
			searchResult = homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage!");				
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated the Filter if user has selected Non stop Flights only from homepage");
			Log.assertThat(searchResult.verifyNonStopFlightsSelectionInFilters(), "<b>Actual Result:</b> Successfully verified Non Stop Flights filter selection in SRP", "<b>Actual Result:</b> Not verified Non Stop Flights filter selection in SRP", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate the functionality of Filter options", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_045(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields");
			
			//step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");	
			
			//step: click Refundable check box in Fare Type filters
			searchResult.clickRefundableCheckbox();
			Log.message("5.Successfully clicked 'Refundable' option in SRP");	
			
			BrowserActions.nap(6);					
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Flight results have updated with Refundable option in SRP");
			Log.assertThat(searchResult.verifyRefundableFlight(), "<b>Actual Result:</b> Successfully Flight results have updated with Refundable option in SRP", "<b>Actual Result:</b> Not filtered Refundbale Flights in SRP", driver);
			
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate the functionality of Reset All link", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_046(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");	
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			//step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			//step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");	
			
			//step: select Airline in Airline Filters
			searchResult.selectAirlineInAirlineFilters(airlines);
			Log.message("5.Successfully clicked Airline in SRP");		
			BrowserActions.nap(2);	
			Log.assertThat(searchResult.verifyFlightNameTitlesInResultGrid(airlines), 
			"<b>Actual Result:</b> Successfully Flight results have updated with selected Airline option ",
			"<b>Actual Result:</b> Not filtered Flight Result grid in SRP", driver);
			BrowserActions.nap(6);			
			//step: select Rest All link in Filters
			searchResult.ClickOnScrollUpButton();
			searchResult.clickResetAll();
			Log.message("6.Successfully clicked Reset All option in SRP");	
			
			BrowserActions.nap(6);					
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate the functionality of Reset All link.");
			Log.assertThat(!searchResult.verifyFlightNameTitlesInResultGrid(airlines), "<b>Actual Result:</b> Successfully displayed complete result set after clicked Reset All ", "<b>Actual Result:</b> Not displayed complete result set after clicked Reset All", driver);
			
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(description = "Validate the functionality of Undo Last Filters", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_047(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			BrowserActions.nap(2);

			// step: click First 'Filter' Link
			searchResult.preferredFlightFirst();
			Log.message("5. Successfully Applied First Filter For Airline!");

			// step: click Second 'Filter' Link
			searchResult.scrollSliderOfFilterAmount(-200);
			Log.message("6. Successfully Applied Second Filter For Price!");
			BrowserActions.nap(2);
			String Error = searchResult.getTextErrorMessageAfterApplyingFilter();
			Log.message("<b> After Applying Filters error message is displayed as </b>:" + Error);

			// step: click On 'Undo' Button
			searchResult.ClickOnUndoButton();
			Log.message("7. Successfully Clicked On Undo Button!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate the functionality of Undo Last Filters");
			Log.assertThat(searchResult.elementLayer.verifyPageElementsDoNotExist(Arrays.asList("btnUndoLastFilter"),searchResult),
					"<b>Actual Result :</b> After Clicking On Undo Button Last Filter Got Removed",
					"<b>Actual Result :</b> After Clicking On Undo Button,All Filter Got Removed", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate the Fare Slider present on Filter Options", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_048(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			BrowserActions.nap(2);

			// step: click Second 'Filter' Link
			searchResult.scrollSliderOfFilterAmount(-100);
			Log.message("5. Successfully Applied Price Filter!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate the Fare Slider present on Filter Options");
			Log.assertThat(searchResult.verifyAirlinePriceAccToFilterApplied(),
					"<b>Actual Result :</b> After Clicking On Price Filter Button Some Flights are Visible",
					"<b>Actual Result :</b> After Clicking On Price Filter Button All Flights are Visible", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate the Current Selection box for RT/MC search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_049(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'Round Trip' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo,
					passengerClass);
			Log.message("3. Successfully filled the search details for Round Trip!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			BrowserActions.nap(2);
			String FlightDetails = searchResult.getTextSelectedFlight();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate the Current Selection box on SRP at Footer");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("selectionDivision"), searchResult),
					"<b>Actual Result :</b> Current Selection box on SRP at Footer is visible to the user with all detaila such as :\n"	+ FlightDetails,
					"<b>Actual Result :</b> Current Selection box on SRP at Footer is not visible to the user", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(description = "Validate the action on hovering mouse over Price", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_050(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'Round Trip' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo,
					passengerClass);
			Log.message("3. Successfully filled the search details for Round Trip!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			
			String FareDetails = searchResult.getTextFareSummarySelectionBox_DOM();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate the action on hovering mouse over Price");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("selectionDivision"), searchResult),
					"<b>Actual Result :</b> After Mouse Hover on Price User is able to see Fare Summary Pop Up and Details are Displayed as :\n"+ FareDetails,
					"<b>Actual Result :</b> After Mouse Hover on Price, NO Fare Summary Pop Up is visible", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate RT flights should appear with Checked icon on Result strip in Current Selection box ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_051(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'Round Trip' option in search Home Page ");

			// step: select RoundTrip Flight Search fields
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'Round Trip' trip.");

			//step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage");			
			List<String> flightFare = searchResult.getPriceFromSelectedFlightInCurrentSelectionBox();
							
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Selected flights should be marked on flight listing");
			Log.assertThat((searchResult.verifySelectedFlightInCurrentSelectionBox("Onward") && searchResult.verifySelectedFlightInCurrentSelectionBox("Return")), "<b>Actual Result:</b> Selected flights should be marked on flight listing, Selectd Flights fares : <b>"+flightFare+"</b>", 
					       "<b>Actual Result:</b> Selected flights should not be marked on flight listing", driver);
			
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate that Column Headers present in the Result Grid", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_052(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			//step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			//step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");				
			
			List<String> resultGridColumns = searchResult.getResultGridColumns();
			BrowserActions.nap(2);					
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that Column Headers present in the Result Grid");
			Log.assertThat(searchResult.verifyResultGridColumns(), "<b>Actual Result:</b> Successfully Validated Result Grid Column Headers, Column names are: <b>" + resultGridColumns +"</b>", "<b>Actual Result:</b> Not validated Result Grid Column Headers", driver);
			
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate that by default results will be sorted on basis of Price", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_053(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			//step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			//step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");				
			
			BrowserActions.nap(2);					
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that by default results will be sorted on basis of Price");
			Log.assertThat(searchResult.verifyPriceSorting("Upwards"), "<b>Actual Result:</b> Successfully displayed the Price sort arrow by default with Upwards", "<b>Actual Result:</b> Not displayed the Price sort arrow by default with Upwards", driver);
			
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validate that Result Grid can be sorted on the basis of column headers present", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_054(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			//step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			//step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage");				
			
			//step: click Price Sort Upwards Arrow
			searchResult.clickPriceSortArrow();
			Log.message("5.Successfully clicked Price Sort Arrow");	
			
			BrowserActions.nap(2);					
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that Result Grid can be sorted on the basis of column headers present");
			Log.assertThat(searchResult.verifyPriceSorting("Downwards"), "<b>Actual Result:</b> Result grid should sorted on the basis of selection made with Price Sort Arrow and should appeared highlighted heading downwards", 
					       "<b>Actual Result:</b> Result grid should not sorted on the basis of selection made with Price Sort Arrow and should not appeared highlighted heading downwards", driver);
			
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating the UI of Result strip for OW search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_055(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			//step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			//step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage");
			BrowserActions.nap(2);					
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating the UI of Result strip for OW search");
			Log.assertThat((searchResult.verifyResultGridColumHeaders("Upper") && searchResult.verifyResultGridColumHeaders("Lower")), "<b>Actual Result:</b> Result strip should appeared divided two parts in Upper and Lower column Headers", 
					       "<b>Actual Result:</b> Result strip should appeared divided two parts in Upper and Lower column Headers", driver);
			
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(description = "Validating the action on clicking on Flight Details link", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_056(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			BrowserActions.nap(2);

			// step: click 'Flight Details' Link
			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("5. Successfully clicked 'Flight Deatils' Link!");

			// step: click 'Fare and Rule' Link
			searchResult.clickOnlnkFareandRule();
			Log.message("6. Successfully clicked 'Fare and Rule' Link!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating the action on clicking on Flight Details link");
			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("txtFareSummaryInFlighDeatils", "txtFareRulesInFlighDeatils"), searchResult),
					"<b>Actual Result :</b> After Clicking On Fare Summary and Rule under Flight Deatils two div are visible to user",
					"<b>Actual Result :</b> After Clicking On Fare Summary and Rule under Flight Deatils two div are not visible to user",driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate that Flight Deatils section would appear with Fare type", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_057(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Flight Details' Link
			searchResult.clickOnFlightLinks();
			Log.message("5. Successfully clicked 'Flight Deatils' Link!");
			String FlightType = searchResult.getTextFareTypeInFligthDetail_DOM();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that Flight Deatils section would appear with Fare type");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("headerLogo"), searchResult),
					"<b>Actual Result :</b> On Search Result Page Fare Type is displayed as :" + FlightType,
					"<b>Actual Result :</b> On Search Result Page No Fare Type is displayed", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate the Fare Rules section", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_059(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click on Book Now in Flight Details
			BrowserActions.nap(3);
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5. Successfully clicked Book Now!");
			BrowserActions.nap(2);
			String FareRules = reviewPage.getTextFareRules();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate via flight check on review page");
			BrowserActions.nap(2);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtFareRules"), reviewPage),
					"<b>Actual Result :</b> In Review Page Fare Rules are displayed as : \n" + FareRules,
					"<b>Actual Result :</b> In Review Page Fare Rules is not displayed", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate that in case of Multiple pax Fare summary break up", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_060(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			BrowserActions.nap(5);
			
			// step: click 'Flight Details' button
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5. Successfully clicked Book Now'!");
			BrowserActions.nap(2);
			String FlightDetails = reviewPage.getTextFromFareDetails();
			String Totalfare = reviewPage.getTextTotalAmount();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that in case of Multiple pax Fare summary break up");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("totalAmountInreviewPage", "contentFareDetails"), reviewPage),
					"<b>Actual Result :</b> In Review Page,Flight Fare Details are Displayed as : \n" + FlightDetails + "\nand Total cost as : " + Totalfare,
					"<b>Actual Result :</b> In Review Page,Flight Fare Details not Displayed", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate via flight check on review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_061(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Stop Filter' Link
			searchResult.selectStopsFilter(1);
			Log.message("5. Successfully clicked 'Stop Filter'!");

			// step: click 'Flight Details' button
			BrowserActions.nap(3);
			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("6. Successfully clicked 'Flight Details'!");

			// step: click on Book Now in Flight Details
			BrowserActions.nap(3);
			reviewPage = searchResult.clickOnBookNowInFlightDetails_INTL();
			Log.message("7. Successfully clicked Book Now in Flight Details Pop Up!");
			ArrayList<String> FlightDetails = reviewPage.getTextAirlinename();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate flight details on review page");
			BrowserActions.nap(2);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnContinueReviewPage"), reviewPage),
					"<b>Actual Result :</b> In Review Page Flight Details are displayed as : \n" + FlightDetails,
					"<b>Actual Result :</b> In Review Page Via Flights are not Displayed", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate via flight check on review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_062(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Flight Details' button
			BrowserActions.nap(3);
			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("5. Successfully clicked 'Flight Details'!");

			// step: click on Book Now in Flight Details
			reviewPage = searchResult.clickOnBookNowInFlightDetails_INTL();
			Log.message("6. Successfully clicked Book Now in Flight Details Pop Up!");
			BrowserActions.nap(2);
			ArrayList<String> FlightDetails = reviewPage.getTextAirlinename();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate via flight check on review page");
			BrowserActions.nap(2);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnContinueReviewPage"), reviewPage),
					"<b>Actual Result :</b> In Review Page Flight Details are displayed as : \n" + FlightDetails,
					"<b>Actual Result :</b> In Review Page Via Flights are not Displayed", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate the action on clicking on 'Book Now' button from Flight Details section", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_063(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Flight Details' button
			BrowserActions.nap(3);
			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("5. Successfully clicked 'Flight Details'!");

			// step: click on Book Now in Flight Details
			searchResult.clickOnBookNowInFlightDetail_INTL();
			Log.message("6. Successfully clicked Book Now in Flight Details Pop Up!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate the action on clicking on 'Book Now' button from Flight Details section");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("conformingUrSeatPopUp"), searchResult),
					"<b>Actual Result :</b> In Flight Detail Pop Up No Book Now Button is visible to the user",
					"<b>Actual Result :</b> In Flight Detail Pop Up Book Now Button is visible to the user", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate that for RT search Book Now button should not be available on Flight Details section", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_064(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'Round Trip' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo,
					passengerClass);
			Log.message("3. Successfully filled the search details for Round Trip!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Flight Details' button
			BrowserActions.nap(5);
			searchResult.clickOnFlightDetailsInRT(5, 0);
			Log.message("5. Successfully clicked 'Flight Details'!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that for RT search Book Now button should not be available on Flight Details section");
			BrowserActions.nap(2);
			Log.assertThat(	searchResult.elementLayer.verifyPageElementsDoNotExist(Arrays.asList("btnBookNowFlightDeatilPopUp"),searchResult),
					"<b>Actual Result :</b> In Flight Detail Pop Up No Book Now Button is visible to the user",
					"<b>Actual Result :</b> In Flight Detail Pop Up Book Now Button is visible to the user", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(description = "Validate that on doing Change Flight from Review Page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_065(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String AirlinesName = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'Round Trip' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for One Way Trip!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: Applied Filter
			searchResult.selectAirlineInAirlineFilters(AirlinesName);
			Log.message("5. Successfully Applied Filter On SRP!");
			int NoOfResults = searchResult.getSizeofResult();
			
			// step: click 'Book Now' button
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Successfully clicked 'Book Now'!");
			
			// step: click 'Change Flight' Link
			reviewPage.clickOnChangeFlight();
			Log.message("7. Successfully clicked 'Change Flight' Link!");
			BrowserActions.nap(2);
			int NoOfResultsAfterChangeFlight = searchResult.getSizeofResult();
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that on doing Change Flight from review Page");
			BrowserActions.nap(2);
			Log.assertThat(NoOfResultsAfterChangeFlight != NoOfResults,
					"<b>Actual Result :</b> After Clicking On Change Flight Button On Review Page All Filter reset to default value",
					"<b>Actual Result :</b> After Clicking On Change Flight Button On Review Page All Filter are not reset to default value", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(description = "Validate that on doing Change Flight from review Page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_066(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'Round Trip' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for One Way Trip!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			String Date = searchResult.getTextDepatureDate();
			String originName = searchResult.getTextOfOrigiNameHeader();
			String DestinationName = searchResult.getTextOfDestinatioNameHeader();
			
			// step: click 'Book Now' button
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5. Successfully clicked 'Book Now'!");
			
			// step: click 'Change Flight' Link
			reviewPage.clickOnChangeFlight();
			Log.message("6. Successfully clicked 'Change Flight' Link!");
			BrowserActions.nap(2);
			String DateAfterFlightChange = searchResult.getTextDepatureDate();
			String originNameAfterFlightChange = searchResult.getTextOfOrigiNameHeader();
			String DestinationNameAfterFlightChange = searchResult.getTextOfDestinatioNameHeader();
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that on doing Change Flight from review Page");
			BrowserActions.nap(2);
			Log.assertThat(Date.equals(DateAfterFlightChange) && originName.equals(originNameAfterFlightChange) && DestinationName.equals(DestinationNameAfterFlightChange),
					"<b>Actual Result :</b> After Clicking On Change Flight Button On Review Page All the fields remain same Orgin,destination and date",
					"<b>Actual Result :</b> After Clicking On Change Flight Button On Review Page All the fields remain same Orgin,destination and date", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate that if user has selected any preferred airline from Airline matrix then on clicking on Change Flight SRP should render with 'All Airlines' selected", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_067(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String AirlinesName = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click one Option From 'Airline Filter'
			searchResult.selectAirlineInAirlineFilters(AirlinesName);
			Log.message("5. Successfully Selected one Option From Airline Filter!");
			ArrayList<String> resultBeforeChangeFlight = searchResult.verifyChkBoxAirlineFilter();

			// step: click on 'Book Now'
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Successfully Clicked Book Now!");

			// step: click on 'Change Flight'
			BrowserActions.nap(2);
			reviewPage.clickOnChangeFlight();
			Log.message("7. Successfully Clicked On Change Flight Button!");
			ArrayList<String> resultAfterChangeFlight = searchResult.verifyChkBoxAirlineFilter();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that if user has selected any preferred airline from Airline matrix then on clicking on Change Flight SRP should render with 'All Airlines' Selected");
			Log.assertThat(resultBeforeChangeFlight != (resultAfterChangeFlight),
					"<b>Actual Result :</b> After clicking on change flight on Review page,all airline option are visible",
					"<b>Actual Result :</b> After clicking on change flight on Review page, only last selected flight is visible", driver);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate that if there is no flight available for respective search-Error Message Should display", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_068(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			String ErrorMessage = searchResult.getTextOfNoFlightAvaliable();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Validate SRP if there is no flight available for respective search-Error Message Should Display");
			BrowserActions.nap(2);
			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("txterrorMessageNoFlights"),	searchResult),
					"<b>Actual Result :</b> Error Message is displayed when No Flight is visible for user and \n message is displayed as : " + ErrorMessage,
					"<b>Actual Result :</b> No  Error Message is displayed when No Flight is visible for user", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate SRP if there is no flight available for respective search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_069(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate SRP if there is no flight available for respective search");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(
					Arrays.asList("headerLogo", "btnModifySearchIcon", "txterrorMessageNoFlights"), searchResult),
					"<b>Actual Result :</b> All Fields are displayed on Search Result Page Header,ModifyButton and Error Message",
					"<b>Actual Result :</b> No Fields are visible to the user", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate that in case of INT search new SRP page should open", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_070(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			String originHeader = searchResult.getTextOfOrigiNameHeader();
			String departureHeader = searchResult.getTextOfDestinatioNameHeader();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that in case of INT search new SRP page should open");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("headerLogo"), searchResult),
					"<b>Actual Result :</b> User navigated to Search result page and origin is displayed as :"	+ originHeader + "  and Deapture is displayed as :" + departureHeader,
					"<b>Actual Result :</b> User didn't navigated to Search result page and", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate that for logged in User Offer strip should appear with user name", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_072(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Log in Yatra account
			loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Successfully Logged in Yatra account");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Successfully clicked 'Search'!");
			String name = searchResult.getTextUserNameFromHeader();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that for logged in User Offer strip should appear with user name");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("txtUserNameOnHeader"), searchResult),
					"<b>Actual Result :</b> If user is Logged in,User name is displayed as : " + name,
					"<b>Actual Result :</b> No user name is visible to the user", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Valiadte that if user is not logged in Offer strip should not contain user name It should appear like Congratulations Guest", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_073(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: Mouse Hover on 'My Account'
			searchResult.mouseOverMyAccount();
			String name = searchResult.getTextSignUp();
			Log.message("5. Successfully Mouse Hover On 'My Account' Link!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Valiadte that if user is not logged in Offer strip should not contain user name It should appear like Congratulations Guest");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("headerLogo"), searchResult),
					"<b>Actual Result :</b> If user is not Logged in 'Login Button' is dispalyed under My account section and text as : USER "	+ name,
					"<b>Actual Result :</b> If user is not Logged No 'Login Button' is dispalyed under My account",	driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate that for GDS flights there should appear Free Meals option", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_076(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			String message = searchResult.getTextFreeMeal_INTL();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that for GDS flights there should appear Free Meals option");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("logoFreeMeal"), searchResult),
					"<b>Actual Result :</b> Free Meal option is visible to the user and message is dispalyed as : "	+ message,
					"<b>Actual Result :</b> Free Meal option is not visible to the user", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate that for Domestic flights offering Paid Meals, Meal text should NOT appear on SRP", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_077(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that for Domestic flights offering Paid Meals, Meal  text should NOT appear on SRP");
			Log.assertThat(searchResult.verifyFreeMealOption(),
					"<b>Actual Result :</b> Free Meal text is not appear on SRP for SpiceJet, Indigo and GoAir Airlines",
					"<b>Actual Result :</b> Free Meal text is appear on SRP for All Flights", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate that there should appear Ecash earned amount in Result strip aligned to Flight Details link below Book Now button if configured from xdist for respective Airline", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_079(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String origin1 = testData.get("Origin_Multicity");
		String destination1 = testData.get("Destination_Multicity");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			String Ecash = searchResult.getTextEcashEarned();

			// step: click 'Modify Search' button
			searchResult.clickModifySearch();
			Log.message("5. Successfully clicked 'Modify Search'!");

			// step: Search New Flight
			searchResult.enterNewFlightDetails(origin1, destination1);
			Log.message("6. Successfully clicked 'Modify Search'!");
			String EcashNew = searchResult.getTextEcashEarned();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that there should appear Ecash earned amount in Result strip aligned to Flight Details link below Book Now button if configured from xdist for respective Airline");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("txtEcashEarned"), searchResult),
					"<b>Actual Result :</b> Ecash for different flights is displayed and amount is Rs." + Ecash	+ "\n and for other Flight amount is : " + EcashNew,
					"<b>Actual Result :</b> Ecash is not displayed to the user", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate that there should appear Seat Left message below Price in Result strip for OW/RT/MC search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_083(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			String seatLeft = searchResult.getTextSeatLeft();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Validate that there should appear Seat Left message below Price in Result strip for OW/RT/MC search");
			BrowserActions.nap(2);
			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("txtSeatLeftMessage"), searchResult),
					"<b>Actual Result :</b> Seat left message is Displayed on Search Result Page : " + seatLeft,
					"<b>Actual Result :</b> Yatra Page Footer is not visible on Search Result Page", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Validate that Flight Details pop should also appear with Seat left message", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_084(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Search' button
			searchResult.clickOnFlightLinks();
			Log.message("5. Successfully clicked 'Flight Details' Pop up!");
			String seatLeft = searchResult.getTextSeatLeftInFlightDetailPopUp();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verify that Flight Details pop should also appear with Seat left message");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("txtSeatLeftMessageInFlightDetails"),	searchResult),
					"<b>Actual Result :</b> Seat left message is Displayed on Flight Details Pop up as : " + seatLeft,
					"<b>Actual Result :</b> Seat left message is not Displayed on Flight Details", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify the Yatra page Footer", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_085(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify the Yatra page Footer");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("yatraFooterPanel"), searchResult),
					"<b>Actual Result :</b> Yatra Page Footer is visible on Search Result Page",
					"<b>Actual Result :</b> Yatra Page Footer is not visible on Search Result Page", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Weekly Strip verification on SRP for DOM flight - OW", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_093(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}

			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully entered OneWay Flight Search Fields ");
			BrowserActions.nap(2);
			
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> The Weekly strip should be displayed on the SRP page for domestic flights.");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("weeklyStrip"), searchResult),
					"<b>Actual Result:</b> The Weekly strip displayed on the SRP page for domestic flights.",
					"<b>Actual Result:</b> The Weekly strip not displayed on the SRP page for domestic flights.",
					driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Flight details link verification on SRP-DOM", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_094(HashMap<String, String> testData) throws Exception {
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
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			searchResult.clickOnFlightLink();
			Log.message("5.Clicked On Flight Detail Link!");
			String flightDeatils = searchResult.getTextFlightDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> The flight details pop up should be displayed on the SRP page after clicking on 'flight details' link and the following details should be displayed:Flight details,Book Now button and total amount!");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnBookNowFlightDeatilPopUp"),
							searchResult), "<b>Actual Result:</b> Book Now Button is Properly Displayed and Details are Displayed as :"
							+ flightDeatils, "<b>Actual Result:</b> Book Now Button and flight Details is not Properly Displayed", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);

		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Flight details link verification on SRP-DOM", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_095(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		// String emailId = testData.get("EmailAddress");
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
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			searchResult.clickOnFlightLink();
			Log.message("5.Clicked On Flight Detail Link!");

			searchResult.clickOnlnkFareandRule();
			Log.message("6.Clicked On Fare and Rule Link!");
			String Flightfare = searchResult.getTextFareDetailsandRuleInPopUp();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> The flight details pop up should be displayed on the SRP page after clicking on 'flight details' link.The following details should be displayed after clicking on fare & summary tab :Fare details section,Fare rules section!");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnBookNowFlightDeatilPopUp"),
							searchResult),	"<b>Actual Result:</b> After Clicking on Fare And Rule Details, Fare Deatils are properly displayed as:"
							+ Flightfare,	"<b>Actual Result:</b> After Clicking on Fare And Rule Details, Fare Deatils are not properly displayed",	driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);

		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(  description = "Flight details link verification on SRP-INTL", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_097(HashMap<String, String> testData) throws Exception {
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
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("5.Clicked On Flight Detail Link!");

			searchResult.clickOnlnkBaggage();
			Log.message("6.Clicked On Baggage Link!");
			String baggage = searchResult.getTextBaggageDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> The flight details pop up should be displayed on the SRP page after clicking on 'flight details' link.The baggage details should be displayed after clicking on baggage tab!");
			BrowserActions.nap(2);
			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnBookNowFlightDeatilPopUp"),
					searchResult),"<b>Actual Result:</b> After Clicking Baggage Tab,Baggage Details are as :" + baggage,
					"<b>Actual Result:</b> After Clicking Baggage Tab,Baggage Details are not displayed properly",	driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);

		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Flight details link verification on SRP-INTL", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_098(HashMap<String, String> testData) throws Exception {
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
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");
						
			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("5.Clicked On Flight Detail Link!");
			String Message = searchResult.getTextDisclamierMessage();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> The flight details pop up should be displayed on the SRP page after clicking on 'flight details' link.The Disclaimer line should be displayed on the pop-up.!");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnBookNowFlightDeatilPopUp"),
					searchResult), "<b>Actual Result:</b>  Disclaimer Message is properly displayed as :" + Message,
					"<b>Actual Result:</b>  Disclaimer Message is not properly displayed", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Add Meal on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_099(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		String domain = testData.get("Domain");	
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain, stops, airlines);	
			Log.message("5. Successfully clicked On Book Now Button with Preferred(<b>"+airlines+"</b>) Flight");
			reviewPage.popUpAppear();
			
			reviewPage.clickOnContinue();
			Log.message("6. Clicked On Continue Button on Review Page!");

			reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Successfully Logged in Yatra account!");

			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Enter User Details!");

			travellerPage.clickOnAddMeal();
			Log.message("9. Clicked On Add Meal!");

			travellerPage.selectMeal();
			Log.message("10. Selected Meal!");
			String mealCharges = travellerPage.getTextMealDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to see the Meal Charges inculded in the Fare Detail!");
			BrowserActions.nap(2);
			Log.assertThat(travellerPage.elementLayer.verifyPageElements(Arrays.asList("btnAddMeal"), travellerPage),
					"<b>Actual Result:</b> Meal Charges are included In Total Fare and Meal Charges is :" + mealCharges,
					"<b>Actual Result:</b> Meal Charges are not included In Total Fare", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Remove Meal on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_100(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		String domain = testData.get("Domain");		
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain, stops, airlines);	
			Log.message("5. Successfully clicked On Book Now Button with Preferred(<b>"+airlines+"</b>) Flight");			
			
			reviewPage.popUpAppear();

			reviewPage.clickOnContinue();
			Log.message("6. Clicked On Continue Button on Review Page!");

			reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Successfully Logged in Yatra account!");

			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Enter User Details!");

			travellerPage.clickOnAddMeal();
			Log.message("9. Clicked On Add Meal!");

			travellerPage.selectMeal();
			Log.message("10. Selected Meal!");
			String mealCharges = travellerPage.getTextMealDetails();
			BrowserActions.nap(2);

			travellerPage.clickOnRemoveMealButton();
			Log.message("11. Clicked On Remove Button In Review Page!");
			String mealChargesAfterRemovingMeal = travellerPage.getTextMealDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to Remove the Meal Charges On Review Page!");
			BrowserActions.nap(2);
			Log.assertThat(travellerPage.elementLayer.verifyPageElements(Arrays.asList("btnAddMeal"), travellerPage),
					"<b>Actual Result:</b> Meal Charges : " + mealCharges + "Meal Charges after Removing the Meal :"
							+ mealChargesAfterRemovingMeal,
					"<b>Actual Result:</b> Meal Charges Can Not Be Removed From Review Page", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Verify Add Baggage on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_101(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		String domain = testData.get("Domain");		
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain, stops, airlines);	
			Log.message("5. Successfully clicked On Book Now Button with Preferred(<b>"+airlines+"</b>) Flight");
			
			//reviewPage = searchResult.clickOnBookNowInOneWay(2);
			//Log.message("5. Clicked On Book Now Button!");
			reviewPage.popUpAppear();

			reviewPage.clickOnContinue();
			Log.message("6. Clicked On Continue Button on Review Page!");

			reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Successfully Logged in Yatra account!");

			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Enter User Details!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should See 'Add Baggage' Button on Review Page!");
			BrowserActions.nap(2);
			Log.assertThat(travellerPage.elementLayer.verifyPageElements(Arrays.asList("btnAddBaggage"), travellerPage),
					"<b>Actual Result:</b> 'Add Baggage' Button is visible to the User On Review Page",
					"<b>Actual Result:</b> 'Add Baggage' Button is not visible to the User On Review Page", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Add Baggage on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_102(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		String domain = testData.get("Domain");		
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain, stops, airlines);	
			Log.message("5. Successfully clicked On Book Now Button with Preferred(<b>"+airlines+"</b>) Flight");
			//reviewPage = searchResult.clickOnBookNowInOneWay(2);
			//Log.message("5. Clicked On Book Now Button!");
			reviewPage.popUpAppear();

			reviewPage.clickOnContinue();
			Log.message("6. Clicked On Continue Button on Review Page!");

			reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Successfully Logged in Yatra account!");

			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Enter User Details!");

			travellerPage.clickOnAddBaggage();
			Log.message("9. Clicked On Add Baggage!");

			travellerPage.selectBaggage();
			Log.message("10. Selected Baggage Type!");
			String BaggageFare = travellerPage.getTextBaggageDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to see the Baggage Charges inculded in the Fare Detail!");
			BrowserActions.nap(2);
			Log.assertThat(travellerPage.elementLayer.verifyPageElements(Arrays.asList("btnAddBaggage"), travellerPage),
					"<b>Actual Result:</b> Baggage Charges are included In Total Fare and Baggage Charges is :"
							+ BaggageFare,
					"<b>Actual Result:</b> Baggage Charges are not included In Total Fare", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Remove Baggage on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_103(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		String domain = testData.get("Domain");	
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain, stops, airlines);	
			Log.message("5. Successfully clicked On Book Now Button with Preferred(<b>"+airlines+"</b>) Flight");
			//reviewPage = searchResult.clickOnBookNowInOneWay(2);
			//Log.message("5. Clicked On Book Now Button!");
			reviewPage.popUpAppear();

			reviewPage.clickOnContinue();
			Log.message("6. Clicked On Continue Button on Review Page!");

			reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Successfully Logged in Yatra account!");

			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Enter User Details!");

			travellerPage.clickOnAddBaggage();
			Log.message("9. Clicked On Add Baggage!");

			travellerPage.selectBaggage();
			Log.message("10. Selected Baggage Type!");
			String BaggageFare = travellerPage.getTextBaggageDetails();
			BrowserActions.nap(2);

			travellerPage.clickOnRemoveBaggageButton();
			Log.message("11. Clicked On Remove Baggage Button!");
			String BaggageFareAfterRemoving = travellerPage.getTextBaggageDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to Remove the Baggage Charges On Review Page!");
			BrowserActions.nap(2);
			Log.assertThat(travellerPage.elementLayer.verifyPageElements(Arrays.asList("btnAddBaggage"), travellerPage),
					"<b>Actual Result:</b> Baggage Charges : " + BaggageFare
							+ "Baggage Charges after Removing the Baggage :" + BaggageFareAfterRemoving,
					"<b>Actual Result:</b> Baggage Charges can not be Removed from Review Page", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Failed payment flow with Credit card flight type, travel type, booking class of your choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_104(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String cardNumber = testData.get("CardNumber");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			reviewPage = searchResult.clickOnBookNowInOneWay(2);
			Log.message("5. Clicked On Book Now Button!");
			reviewPage.popUpAppear();

			reviewPage.clickOnContinue();
			Log.message("6. Clicked On Continue Button on Review Page!");

			reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Successfully Logged in Yatra account!");

			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Enter User Details!");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked On continue Button!");
			BrowserActions.nap(10);
			paymentPage.enterCreditCardDetails(cardNumber);
			Log.message("10. Fill Credit Card Details!");

			paymentPage.clickOnPayNow();
			Log.message("11.Click On Pay Now!");
			String ErrorMsg = paymentPage.getTextErrorMessage();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Failed payment flow with Credit card (flight type, travel type, booking class of your choice)");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("popUpInvalidCardNumber"), paymentPage),
					"<b>Actual Result:</b> Payment is failed and error Msg is Displayed as :" + ErrorMsg,
					"<b>Actual Result:</b> Payment is successfully On Payment Page", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Failed payment flow with Debit card flight type, travel type, booking class of your choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_105(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String cardNumber = testData.get("CardNumber");
    	String cvv = testData.get("CVV");

		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			ReviewPage reviewPage = searchResult.clickOnBookNowInOneWay(2);
			Log.message("5. Clicked On Book Now Button!");
			reviewPage.popUpAppear();

			reviewPage.clickOnContinue();
			Log.message("6. Clicked On Continue Button on Review Page!");

			reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Successfully Logged in Yatra account!");

			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Enter User Details!");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked On continue Button!");
			
			paymentPage.selectPaymentType("Debit Card");

			paymentPage.enterDebitCardDetails(cardNumber,cvv);
			Log.message("10. Fill invalid Debit Card Details!");

			paymentPage.clickOnPayNow();
			Log.message("11.Click On Pay Now!");
			String ErrorMsg = paymentPage.getTextErrorMessage();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Failed payment flow with Credit card (flight type, travel type, booking class of your choice)");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("popUpInvalidCardNumber"), paymentPage),
					"<b>Actual Result:</b> Payment is failed and error Msg is Displayed as :" + ErrorMsg,
					"<b>Actual Result:</b> Payment is successfully On Payment Page", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			 driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Guest flow - Verification of Book As Guest button ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_112(HashMap<String, String> testData) throws Exception {
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in search Home Page ");
			
			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4.Successfully filled the search details for 'ONE WAY' trip.");
			
			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");

			// step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");
			// BrowserActions.nap(5);

			reviewPage.clickOnContinue();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Book as Guest button.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnBookAsGuest"), reviewPage),
					"<b>Actual Result:</b> The Book as Guest button is displayed on Review Page.",
					"<b>Actual Result:</b> The Book as Guest button is not displayed on Review Page.", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Guest flow - Verification of Book As Guest button ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_113(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String mobile = testData.get("Mobile");
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in search Home Page ");
			
			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4.Successfully filled the search details for 'ONE WAY' trip.");
			BrowserActions.nap(2);

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");

			// step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");

			// step: Click on 'Continue' button in Yatra Review Matrix
			reviewPage.clickOnContinue();
			Log.message("7.Clicked on 'Continue' button in Review Page ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Book as Guest Email/Password fields.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtGuestEmail", "txtGuestMobile"), reviewPage),
					"<b>Actual Result:</b> The Book as button/Guest Email/Guest Mobile text field is displayed on Review Page.",
					"<b>Actual Result:</b> The Book as button/Guest Email/Guest Mobile text field is not displayed on Review Page.", driver);

			travellerPage = reviewPage.loginYatraGuestAccount(emailId, mobile);
			Log.message("7.Login as 'Guest User' ");

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Guest flow - Verification of Existing User checkbox", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_114(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
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
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in search Home Page ");
			
			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4.Successfully filled the search details for 'ONE WAY' trip.");
			BrowserActions.nap(2);

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");

			// step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");

			// step: Click on 'Continue' button in Yatra Review Matrix
			reviewPage.clickOnContinue();
			Log.message("7.Clicked on 'Continue' button in Review Page ");

			reviewPage.clickOnExistingUser();
			Log.message("8.Clicked on 'ExistingUser' checkbox in Review Page ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Book as Guest Email/Password.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtGuestEmail", "txtGuestPassword"), reviewPage),
					"<b>Actual Result:</b> The Book as Guest Email/Password is displayed on Review Page.",
					"<b>Actual Result:</b> The Book as Guest Email/Password is not displayed on Review Page.", driver);

			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Login as 'Existing User' ");

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Guest flow - Check Booking as a Existing User Fill Traveller form move to Payment page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_115(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in search Home Page ");
			// BrowserActions.nap(3);

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4.Successfully filled the search details for 'ONE WAY' trip.");
			BrowserActions.nap(2);

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");
			BrowserActions.nap(2);

			// step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowInOneWay(2);
			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");

			// step: Click on 'Continue' button in Yatra Review Matrix
			reviewPage.clickOnContinue();
			Log.message("7.Clicked on 'Continue' button in Review Page ");

			reviewPage.clickOnExistingUser();
			Log.message("8.Clicked on 'ExistingUser' checkbox in Review Page ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Book as Guest Email/Password.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtGuestEmail", "txtGuestPassword"), reviewPage),
					"<b>Actual Result:</b> The Book as Guest Email/Password is displayed on Review Page.",
					"<b>Actual Result:</b> The Book as Guest Email/Password is not displayed on Review Page.", driver);

			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Login as 'Existing User' ");

			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Fill 'Traveller Details' Form ");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9.Clicked on 'Continue' button in Traveller Page ");
			BrowserActions.nap(2);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating the Matrix Result finder for RT search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_116(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'Round Trip' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Return date
			String returndate = homePage.selectReturnDate(returnDate);
			Log.message("6.Successfully selected the Return date: <b>" + returndate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("7.Successfully selected Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("8.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("9.Successfully clicked 'Search' button in Yatra Homepage ");			
			
			//getting text from RoundTrip Journey city and depart date			
			String sourceCityText = searchResult.getTextSourceCity();
			String destCityText = searchResult.getTextDestinationCity();
			String sourceDateText = searchResult.getTextSourceDate();
			String destDateText = searchResult.getTextDestinationDate();
		
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated the Matrix Result finder for RT search");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully validated Matrix result, and the Source city is dispalyed as <b>: " + sourceCityText +"</b>, Source Date is dispalyed as <b>: " + sourceDateText +"</b>, Destination city is dispalyed as <b>: " + destCityText +"</b>, Destination date is dispalyed as <b>: " + destDateText +"</b>" ,
					"<b>Actual Result:</b> Not displayed Matrix results header with flight icons" , driver);
					
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating the Matrix Result finder for MC search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_117(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin1 = testData.get("Origin");
		String origin2 = testData.get("Origin_Multicity");
		String tripType = testData.get("TripType");
		String destination1 = testData.get("Destination");
		String destination2 = testData.get("Destination_Multicity");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'Multicity' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterMultiCityOrigin1(origin1);
			Log.message("3.Successfully entered Multicity Origin1 '<b>" + origin1 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination1(destination1);
			Log.message("4.Successfully entered Multicity Destination1 '<b>" + destination1 + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectMultiCityDateDeparture1(departureDate);
			Log.message("5.Successfully selected the Multicity Departure1 date: <b>" + departDate + "</b>(YY/MM/DD)");

			homePage.enterMultiCityOrigin2(origin2);
			Log.message("6.Successfully entered Multicity Origin2 '<b>" + origin2 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination2(destination2);
			Log.message("7.Successfully entered Multicity Destination1 '<b>" + destination2 + "</b>' in Yatra Homepage");

			// step: select Departure date
			String returndate = homePage.selectMultiCityDateDeparture2(returnDate);
			Log.message("8.Successfully selected the Multicity Departure2 date: <b>" + returndate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("9.Successfully specified Passenger Info");

			// step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("10.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("11.Successfully clicked 'Search' button in Yatra Homepage ");			
			
			//getting text from  starting Journey city and depart date	
			String startSourceCityText = searchResult.getTextStartSourceCity();			
			String startSourceDateText = searchResult.getTextStartSourceDate();
			String startDestCityText = searchResult.getTextStartDestCity();
			
			//getting text from  ending Journey city and depart date	
			String endSourceCityText = searchResult.getTextEndSourceCity();			
			String endSourceDateText = searchResult.getTextEndSourceDate();
			String endDestCityText = searchResult.getTextEndDestCity();
		
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated the Matrix Result finder for MC search");
			BrowserActions.nap(3);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully validated Matrix result, and the starting Source city: <b>: " + startSourceCityText +"</b>, Source Date:  <b> " + startSourceDateText +"</b>, Destination city: <b> " + startDestCityText +"</b>, and Ending Source city: <b> " + endSourceCityText +"</b>, Source date:<b> " + endSourceDateText +"</b>, Destination city: <b> " + endDestCityText +"</b>" ,
					"<b>Actual Result:</b> Not displayed Matrix results header with flight icons" , driver);
		    Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(description = "Airline Matrix Strip verification on SRP for DOM flight", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_118(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'Round Trip' option in search Home Page ");

			// step: select RoundTrip Flight Search fields
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'Round Trip' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			BrowserActions.nap(15);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Successfully verified Airline Matrix Strip on SRP for DOM fligh");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("lnkAirlineMatrixStrip"), searchResult),
					"<b>Actual Result:</b> Airline Matrix should be properly displayed on Search Result page",
					"<b>Actual Result:</b> Airline Matrix should not displayed properly on Search Result page", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);

		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "Airline Matrix Strip verification on SRP for INT flight", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_119(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'Round Trip' option in search Home Page ");

			// step: select Round Trip Flight Search fields
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'Round Trip' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			BrowserActions.nap(20);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Successfully verified Airline Matrix Strip on SRP for INT flight");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("lnkAirlineMatrixStrip"), searchResult),
					"<b>Actual Result:</b> irline Matrix should be properly displayed on Search Result page",
					"<b>Actual Result:</b> Airline Matrix should not displayed properly on Search Result page", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);

		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
	@Test( description = "Preferred Airline search (modify Search) INTL-OW", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_120(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");
		
			searchResult.clickModifySearch(); 
			Log.message("5.Successfully clicked 'Modify Search' link in SRP ");				
			
			String originCityText = searchResult.getTextOrigin_ModifySearch();		
			String destCityText = searchResult.getTextDestination_ModifySearch();			
			Log.assertThat(searchResult.verifyTripTypeInModifySearch(tripType), "<b>Actual Result:</b> Successfully selected One Way Radio button",	"<b>Actual Result:</b> Not selected One Way Radio button");
            Log.assertThat(originCityText.contains(destination), "<b>Actual Result:</b> Successfully verified Origin City ", "<b>Actual Result:</b> Not verified Origin City");
            Log.assertThat(destCityText.contains(origin), "<b>Actual Result:</b> Successfully verified Destination City P","<b>Actual Result:</b> Not verified Destination City ");
						         
            String[] pax= passengerInfo.split("_");
			String adult =pax[0]; String child =pax[1]; String infant =pax[2]; 
			String adultText = searchResult.getTextAdult_ModifySearch();
			String childText = searchResult.getTextChild_ModifySearch();
			String infantText = searchResult.getTextInfant_ModifySearch();
			Log.assertThat(adultText.contains(adult),
					"<b>Actual Result:</b> Successfully verified selected Adult passenger details </b> " + adultText,
					"<b>Actual Result:</b> Not verified selected Adult passenger details </b> ", driver);
			
			Log.assertThat(childText.contains(child),
					"<b>Actual Result:</b> Successfully verified selected Child passenger details  </b>" +childText,
					"<b>Actual Result:</b> Not verified selected Child passenger details </b> ", driver);
			
			Log.assertThat(infantText.contains(infant),
					"<b>Actual Result:</b> Successfully verified selected Infant passenger details </b>"+ infantText,
					"<b>Actual Result:</b> Not verified selected Infant passenger details </b> ", driver);
			
			
			String passengerClassText = searchResult.getTextPassengerClass_ModifySearch();			
			Log.assertThat(passengerClassText.contains(passengerClass),
					"<b>Actual Result:</b> Successfully verified selected passenger class details </b> ",
					"<b>Actual Result:</b> Not verified selected passenger class details </b> ", driver);
			
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
  	
	@Test( description = "Validating that 'Modify Search' should display prefilled respective search made from Homepage for RT-INTL", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_121(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");		
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Return date
			String returndate = homePage.selectReturnDate(returnDate);
			Log.message("6.Successfully selected the Return date: <b>" + returndate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("7.Successfully selected Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("8.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("9.Successfully clicked 'Search' in Yatra Homepage!");

			// step: click 'Search' button in Yatra Home page
			searchResult.clickModifySearch(); 
			Log.message("10.Successfully clicked 'Modify Search' link in SRP ");	
			
			String originCityText = searchResult.getTextOrigin_ModifySearch();		
			String destCityText = searchResult.getTextDestination_ModifySearch();			
			Log.assertThat(searchResult.verifyTripTypeInModifySearch(tripType), "<b>Actual Result:</b> Successfully selected Round Trip Radio button",	"<b>Actual Result:</b> Not selected Round Trip Radio button");
            Log.assertThat(originCityText.contains(destination), "<b>Actual Result:</b> Successfully verified Origin City, Origin: <b> " +destination +"</b>", "<b>Actual Result:</b> Not verified Origin City ");
            Log.assertThat(destCityText.contains(origin), "<b>Actual Result:</b> Successfully verified Destination City, Destination: <b> " +origin +"</b>","<b>Actual Result:</b> Not verified Destination City ");
		         
            String[] pax= passengerInfo.split("_");
			String adult =pax[0]; String child =pax[1]; String infant =pax[2]; 
			String adultText = searchResult.getTextAdult_ModifySearch();
			String childText = searchResult.getTextChild_ModifySearch();
			String infantText = searchResult.getTextInfant_ModifySearch();
			Log.assertThat(adultText.contains(adult),
					"<b>Actual Result:</b> Successfully verified selected Adult passenger details , Selected Adult is:  <b> " + adult+ "</b>",
					"<b>Actual Result:</b> Not verified selected Adult passenger details ", driver);
			
			Log.assertThat(childText.contains(child),
					"<b>Actual Result:</b> Successfully verified selected Child passenger details, Selected Child is:  <b> " + child+ "</b>",
					"<b>Actual Result:</b> Not verified selected Child passenger details  ", driver);
			
			Log.assertThat(infantText.contains(infant),
					"<b>Actual Result:</b> Successfully verified selected Infant passenger details , Selected Infant is:  <b> " + infant+ "</b>",
					"<b>Actual Result:</b> Not verified selected Infant passenger details", driver);
					
			String passengerClassText = searchResult.getTextPassengerClass_ModifySearch();			
			Log.assertThat(passengerClass.contains(passengerClassText),
					"<b>Actual Result:</b> Successfully verified selected passenger class details, Selected Passenger Class: <b>"+ passengerClassText+ " </b> ",
					"<b>Actual Result:</b> Not verified selected passenger class details ", driver);
					
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
	@Test( description = "Check to price calculation for DOM flight", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_125(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");

		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}

			// selected trip as one way and enter the search fields
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now buuton
			reviewPage = searchResult.clickOnBookNowInOneWay(2);
			Log.message("5.Clicked on 'Book Now' button in Search Result Page ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check to price calculation for DOM flight");
			BrowserActions.nap(5);

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("moduleFareDetails"), reviewPage),
					"<b>Actual Result:</b> The Fare details module is displayed on Review Page.",
					"<b>Actual Result:</b> The Fare details module is not displayed on Review Page.", driver);
			
			
			int totalAmt= reviewPage.calculatingAmountToPay();
			Log.message(" The total amount we get from the Fare module after adding:"+totalAmt);
			
			String Total = reviewPage.gettingTotalPayAmount().replace("Rs.","");
			int Total1 = Integer.parseInt(Total);
			Log.message(" The total amount:"+Total1);
			
			Log.assertThat(totalAmt==Total1,
					"<b>Actual Result:</b> The price is calculated properly in the Fare Detail module on Review Page.",
					"<b>Actual Result:</b> The price is not calculated properly in the Fare Detail module on Review Page.", driver);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Check to price calculation for INTL flight", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_126(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");

		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}

			// selected trip as one way and enter the search fields
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now buuton
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5.Clicked on 'Book Now' button in Search Result Page ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check to price calculation for INTL flight");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("moduleFareDetails"), reviewPage),
					"<b>Actual Result:</b> The Fare details module is displayed on Review Page.",
					"<b>Actual Result:</b> The Fare details module is not displayed on Review Page.", driver);
			
			
			int totalAmt= reviewPage.calculatingAmountToPay();
			Log.message(" The total amount we get from the Fare module after adding:"+totalAmt);
			
			String Total = reviewPage.gettingTotalPayAmount().replace("Rs.","");
			int Total1 = Integer.parseInt(Total);
			Log.message(" The total amount:"+Total1);
			
			Log.assertThat(totalAmt==Total1,
					"<b>Actual Result:</b> The price is calculated properly in the Fare Detail module on Review Page.",
					"<b>Actual Result:</b> The price is not calculated properly in the Fare Detail module on Review Page.", driver);
	
			Log.testCaseResult();
			
		} catch (Exception e) {
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Flight details link verification on SRP-DOM", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_127(HashMap<String, String> testData) throws Exception {
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
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			searchResult.clickOnFlightLink();
			Log.message("5.Clicked On Flight Detail Link!");
			String flightDeatils = searchResult.getTextFlightDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> The flight details pop up should be displayed on the SRP page after clicking on 'flight details' link and the following details should be displayed:Flight details,Book Now button and total amount!");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnBookNowFlightDeatilPopUp"),
							searchResult), "<b>Actual Result:</b> Book Now Button is Properly Displayed and Details are Displayed as :"
							+ flightDeatils, "<b>Actual Result:</b> Book Now Button and flight Details is not Properly Displayed", driver);


			searchResult.clickOnlnkFareandRule();
			Log.message("6.Clicked On Fare and Rule Link!");
			String Flightfare = searchResult.getTextFareDetailsandRuleInPopUp();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> The flight details pop up should be displayed on the SRP page after clicking on 'flight details' link.The following details should be displayed after clicking on fare & summary tab :Fare details section,Fare rules section!");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnBookNowFlightDeatilPopUp"),
							searchResult),	"<b>Actual Result:</b> After Clicking on Fare And Rule Details, Fare Deatils are properly displayed as:"
							+ Flightfare,	"<b>Actual Result:</b> After Clicking on Fare And Rule Details, Fare Deatils are not properly displayed",	driver);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e);

		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Flight details link verification on SRP-INTL", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_128(HashMap<String, String> testData) throws Exception {
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
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("5.Clicked On Flight Detail Link!");
			String Message = searchResult.getTextDisclamierMessage();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> The flight details pop up should be displayed on the SRP page after clicking on 'flight details' link.The Disclaimer line should be displayed on the pop-up.!");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnBookNowFlightDeatilPopUp"),
					searchResult), "<b>Actual Result:</b>  Disclaimer Message is properly displayed as :" + Message,
					"<b>Actual Result:</b>  Disclaimer Message is not properly displayed", driver);

			searchResult.clickOnlnkBaggage();
			Log.message("6.Clicked On Baggage Link!");
			String baggage = searchResult.getTextBaggageDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> The flight details pop up should be displayed on the SRP page after clicking on 'flight details' link.The baggage details should be displayed after clicking on baggage tab!");
			BrowserActions.nap(2);
			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnBookNowFlightDeatilPopUp"),
					searchResult),"<b>Actual Result:</b> After Clicking Baggage Tab,Baggage Details are as :" + baggage,
					"<b>Actual Result:</b> After Clicking Baggage Tab,Baggage Details are not displayed properly",	driver);

			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
	@Test(description = "Applying Promo Code on review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_129(HashMap<String, String> testData) throws Exception {
		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String promo[] =testData.get("Promo").split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage!");

			//reviewPage = searchResult.clickOnBookNowInOneWay(2);
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5.Clicked on 'Book Now' button in Search Result Page!");

			reviewPage.clickOnHavePromoCode();
			Log.message("6.Clicked on 'Have a Promo Code' link in Review Page!");
			
			reviewPage.enterPromo(promo[0]);
			Log.message("7.Entered invalid Promo Code!");
			String Error = reviewPage.getTextFromPromoMessage();
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Applying Promo Code on review page!");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("PromoCodeErrorMessage"), reviewPage),
					"<b>Actual Result:</b> If invalid Promo Code is Applied an error message is dispalyed as :" + Error,
					"<b>Actual Result:</b> If invalid Promo Code is Applied no error message is dispalyed",
					driver);
			
			reviewPage.enterPromo(promo[1]);
			Log.message("8.Entered valid Promo Code!");
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Applying Promo Code on review page!");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("PromoCodeErrorMessage"), reviewPage),
					"<b>Actual Result:</b> If valid Promo Code is Applied an message is dispalyed",
					"<b>Actual Result:</b> If valid Promo Code is Applied no message is dispalyed",	driver);
						
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Travel Assistance and Insurance verification on pax page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_130(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text!");
			}
			// selected trip as one way
			homePage.selectOneWayTrip();
			
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'ONE WAY' trip!");
			
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage!");

			reviewPage = searchResult.clickOnBookNowInOneWay(2);
			Log.message("5.Clicked on 'Book Now' button in Search Result Page!");

			reviewPage.clickOnContinue();
			Log.message("6. Clicked On Continue Button on Review Page!");

			reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Successfully Logged in Yatra account!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Travel Assistance and Insurance verification on pax page");
			Log.assertThat(travellerPage.getTextFromFareDetails().contains("Travel Assistance and Insurance"),
					"<b>Actual Result:</b> Travel Assistance and Insurance amount included in the Fare details",
					"<b>Actual Result:</b> Travel Assistance and Insurance amount not included in the Fare details",
					driver);
			
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(description = "Login flow - Check Booking as a Existing User Fill Traveller form move to Payment page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_131(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in search Home Page ");
			// BrowserActions.nap(3);

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4.Successfully filled the search details for 'ONE WAY' trip.");
			BrowserActions.nap(2);

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");
			BrowserActions.nap(2);

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowInOneWay(2);
			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");


			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChangeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// step: Click on 'Continue' button in Yatra Review Matrix
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7.Clicked on 'Continue' button in Review Page ");

			Log.assertThat(travellerPage.elementLayer.verifyPageElements(Arrays.asList("formTraveller"), travellerPage),
					"<b>Actual Result:</b> Successfully navigated on traveller Page.",
					"<b>Actual Result:</b> Unable to navigated on traveller Page.", driver);


			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8.Fill 'Traveller Details' Form ");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9.Clicked on 'Continue' button in Traveller Page ");
			BrowserActions.nap(2);
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Booking as a Existing User Fill Traveller form move to Payment page");
			BrowserActions.nap(2);
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("btnPayNow"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated on paymentPage.",
					"<b>Actual Result:</b> Unable to navigated on paymentPage.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
	@Test(description = "Verify validation of inputs in fare alert", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_132(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String Price = testData.get("Amount");
		String Email = testData.get("EmailAddress");
		String PhoneNumber = testData.get("Mobile");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Get Fare' Link
			searchResult.clickOnGetFareAlert();
			Log.message("5. Successfully clicked on 'Get Fare Alert' Link!");
			BrowserActions.nap(2);
			String ErrorMessagePrice = searchResult.enterGetFareDetailsPrice(Price);
			String ErrorMessageEmail = searchResult.enterGetFareDetailsEmail(Email);
			String ErrorMessagePhoneNumber = searchResult.enterGetFareDetailsPhoneNumber(PhoneNumber);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify validation of inputs in fare alert");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("headerLogo"), searchResult),
					"<b>Actual Result :</b> After Clicking On Get Fare Alert Link,\n validation message for Amount is Displayed as : "
							+ ErrorMessagePrice + "\n For Email is displayed as :" + ErrorMessageEmail	+ "\n and For Phone Number it is displayed as :" + ErrorMessagePhoneNumber,
					"<b>Actual Result :</b> No Error message is Displayed to User", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Fare Details viewed info", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_133(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Flight Details Link' button
			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("5. Successfully clicked on 'Flight Details Link'!");

			// step: click '[X]' button in Fare Details
			BrowserActions.nap(2);
			searchResult.clickOnCloseInFlightDetailPopUp();
			Log.message("6. Successfully clicked on 'Close' Button!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Fare Details viewed info");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnViewed"), searchResult),
					"<b>Actual Result :</b> After Closing Flight Details Pop Up, viewed button is visible",
					"<b>Actual Result :</b> After Closing Flight Details Pop Up, No viewed button is visible", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate Time Difference functionality between RT flights", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_134(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'Round Trip' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo,
					passengerClass);
			Log.message("3. Successfully filled the search details for Round Trip!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: Selecting Flight Having Time Less then 3 hrs
			searchResult.selectFlightLessThen3Hrs();
			Log.message("5. Successfully Selected Flight Having time Less then 3 Hrs!");
			String error = searchResult.getTextErrorMessageOnBookNowIfTimeIsLessThen3Hrs();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate Time Difference functionality between RT flights");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("yatraFooterPanel"), searchResult),
					"<b>Actual Result :</b> If Flight Difference is Less the 3 Hrs then Book Now should be disabled and Error Message is displayed as :" + error,
					"<b>Actual Result :</b>  If Flight Difference is Less the 3 Hrs then Book Now should not be disabled",	driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify Stops info in each flight card", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_135(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			BrowserActions.nap(2);
			String Stops = searchResult.getTextNoOfStops();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Stops info in each flight card");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("txtNoOfStops"), searchResult),
					"<b>Actual Result :</b> Stops are visible on all flight card and its shown as : " + Stops,
					"<b>Actual Result :</b> Stops on all Flight card are not visible", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify functionality of 'Go to TOP' button", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_136(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: scroll to Bottom of SRP
			searchResult.scrollToBottom();
			Log.message("5. Successfully Scrolled at Bottom Of the SRP Page!");

			// step: click 'Scroll Up' button
			searchResult.ClickOnScrollUpButton();
			Log.message("6. Successfully Clicked on Scroll To Top Button!");

			BrowserActions.nap(1);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify functionality of 'Go to TOP' button");
			Log.assertThat(	searchResult.elementLayer.verifyPageElementsDoNotExist(Arrays.asList("btnScrollUpSRP"),	searchResult),
					"<b>Actual Result :</b> User Have Scrolled to Top",
					"<b>Actual Result :</b>  User Have not Scrolled to Top", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify functionality of Airline Only filter", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_137(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: select 'Preferred' Flight
			String flightname = searchResult.preferredFlightFirst();
			Log.message("5. Successfully Selected 'Preferred Flight'!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate tool tip message on selection of flights");
			Log.assertThat(searchResult.verifySelectedFlightName(),
					"<b>Actual Result :</b> User see only  --> " + flightname + "  <-- Flights after applying Filter",
					"<b>Actual Result :</b> All Flights are dispalyed to user after applying filter", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validate tool tip message on selection of flights", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_138(HashMap<String, String> testData) throws Exception {
		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo,
					passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: filling user details
			loginPage = searchResult.navigateToSignIn();
			searchResult = loginPage.loginYatraAccountFromSearchResult(emailId, password);
			Log.message("5. Login 'Yatra' account successfully!");
			String message = searchResult.getTextContinueWithSearchMessage();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate tool tip message on selection of flights");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("headerLogo"), searchResult),
					"<b>Actual Result :</b> After Log in a tool tip message is diplayed as :" + message,
					"<b>Actual Result :</b> No Tool tip message is displayed to the user", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verify functionality of search button for saved searches", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_139(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");
			String originName = searchResult.getTextOfOrigiNameHeader();
			String DestinationName = searchResult.getTextOfDestinatioNameHeader();

			// step: filling user details
			loginPage = searchResult.navigateToSignIn();
			searchResult = loginPage.loginYatraAccountFromSearchResult(emailId, password);
			Log.message("6. Login 'Yatra' account successfully!");
			String originNameAfterLogin = searchResult.getTextOfOrigiNameHeader();
			String DestinationNameAfterLogin = searchResult.getTextOfDestinatioNameHeader();
			String message = searchResult.getTextRecentSearchPopUp();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verification Auto Save search result on login yatra account");
			Log.assertThat(	originName.equalsIgnoreCase(originNameAfterLogin)&& DestinationName.equalsIgnoreCase(DestinationNameAfterLogin),
					"<b>Actual Result :</b> After Login From SearchResult Page a pop up is appeared on Recent serach amd Message is displayed as :\n"+ message + " and city and destination is same",
					"<b>Actual Result :</b> No message is appeared on Search Result Page", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Delete recent Search Funtionality", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_141(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: Login Into Yatra Account
			loginPage = searchResult.navigateToSignIn();
			searchResult = loginPage.loginYatraAccountFromSearchResult(emailId, password);
			Log.message("5. Login 'Yatra' account successfully!");

			// step: click 'Recent Search' button
			searchResult.clickOnRecentSearch();
			Log.message("6. Successfully clicked 'Recent Search'!");

			// step: click '[X]' button
			searchResult.ClickOnCrossInRecentSearch();
			Log.message("7. Successfully clicked 'Cross[X]' Link in recent Search !");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto Save search result on login yatra account");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("headerLogo"), searchResult),
					"<b>Actual Result :</b> After Login From Search result Page a Pop Up is appeared with Details",
					"<b>Actual Result :</b> After Login From Search result Page No Pop Up is appeared with Details",
					driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Auto Save search result on login yatra account", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_142(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: Login Into Yatra Account
			loginPage = searchResult.navigateToSignIn();
			searchResult = loginPage.loginYatraAccountFromSearchResult(emailId, password);
			Log.message("5. Login 'Yatra' account successfully!");
			BrowserActions.nap(1);
			String Txt = searchResult.getTextRecentSearchPopUp();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto Save search result on login yatra account");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("headerLogo"), searchResult),
					"<b>Actual Result :</b> After Login From Search result Page a Pop Up is appeared with Details"	+ Txt,
					"<b>Actual Result :</b> After Login From Search result Page No Pop Up is appeared with Details", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verification Fare Break-up on review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_143(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Book Now' button
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5. Successfully clicked 'Search' !");
			BrowserActions.nap(2);
			String feeandSurcharge = reviewPage.getTextFeeAndSurcharge();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verification Fare Break-up on review page");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtFeeAndSurcharge"), reviewPage),
					"<b>Actual Result :</b> Fare Break-Up is displayed on Review Page as :\n" + feeandSurcharge,
					"<b>Actual Result :</b> No Fare Break-Up is displayed on Review Page", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
	@Test(description = "Validating the action on clicking on Flight Details link", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_151(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search' in Yatra Homepage!");

			// step: click 'Flight Details' button
			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("5. Successfully clicked 'Search' Button");

			// step: click 'Fare and Summary' Link
			searchResult.clickOnFareAndSummaryFlightDetail();
			Log.message("6. Successfully clicked 'Fare and Rule Detail Link' in Flight Detail Pop Up!");
			BrowserActions.nap(1);
			String fare = searchResult.getTextFareDetailsandRuleInPopUp();

			// step: click 'Baggage' Link
			searchResult.clickOnBaggageFlightDetail();
			Log.message("7. Successfully clicked 'Baggage Link' in Flight Detail Pop Up!");
			BrowserActions.nap(1);
			String Baggage = searchResult.getTextBaggageInfoFlightDetail();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating the action on clicking on Flight Details link");
			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("lnkBaggageFlightDetail", "lnkFareAndSummaryFlightDetail"), searchResult),
					"<b>Actual Result :</b> After Clicking on Flight Details Link a Pop Up Appear Fare and Summary Displayed as -->"
							+ fare + "\n and Baggage details as \n --> " + Baggage,
					"<b>Actual Result :</b> After Clicking On Flight Details Link, no pop up Appear", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validating book now button on Flight Details Link-INTL", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_152(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search' Button!");

			// step: click 'Book Now' button
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5. Successfully clicked 'Book Now' Button!");
			String txt = reviewPage.getTextReviewBooking();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating book now button on Flight Details Link-INTL");
			Log.assertThat(	reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtReviewYourBooking"), reviewPage),
					"<b>Actual Result :</b> User navigated to review Page and Text is Displayed as :" + txt,
					"<b>Actual Result :</b> User did not navigated to review Page", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "Validating the Prev day/Next day for MC search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_153(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin1 = testData.get("Origin");
		String origin2 = testData.get("Origin_Multicity");
		String tripType = testData.get("TripType");
		String destination1 = testData.get("Destination");
		String destination2 = testData.get("Destination_Multicity");
		String departureDate1 = testData.get("DepartureDate");
		String departureDate2 = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'Multicity' option in search Home Page ");
	
			//step: selectMulticity Search fields in HomePage
			homePage.selectMultiCityFlightSearchFields(origin1, destination1, departureDate1, origin2, destination2, departureDate2, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for 'Multicity'");
						
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");			
			BrowserActions.nap(12);						
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Prev day/Next day search option should be available above Result Grid for both Onward and Return leg in SRP");
			Log.assertThat((searchResult.verifyOnwardLegLinks("PrevDay") && searchResult.verifyOnwardLegLinks("NextDay")), "<b>Actual Result:</b> Successfully validated Prev day/Next day search options on Onward leg in SRP", "<b>Actual Result:</b> Prev day/Next day search options are not available on Onwards leg in SRP", driver);
			Log.assertThat((searchResult.verifyReturnLegLinks("PrevDay") && searchResult.verifyReturnLegLinks("NextDay")), "<b>Actual Result:</b> Successfully validated Prev day/Next day search options on Return leg in SRP", "<b>Actual Result:</b> Prev day/Next day search options are not available on Return leg in SRP", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
// ********************************End of Test cases ***************************************************************************************
} //FlightSearch
