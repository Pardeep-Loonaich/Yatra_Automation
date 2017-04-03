package com.Yatra.TestScript.Flights;
//-----------------------------------------------------------------------------------------------------------

//Description    :   All the Flight Searching test Cases would be designed in this class 
//Creator        :   Aspire Team
//Create         :   
//Modified on/By :   -
//-----------------------------------------------------------------------------------------------------------

import java.util.Arrays;
import java.util.HashMap;

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
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class FlightSearch {

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

	@Test(groups = {"desktop" }, description = "Flight Search DOM - OW with  Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_001(HashMap<String, String> testData) throws Exception {

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
			Log.message("6.Passenger Info successfully specified");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			Thread.sleep(6000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page",
					"<b>Actual Result:</b> User should navigated on SearchResult page", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Flight Search DOM - RT with Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_002(HashMap<String, String> testData) throws Exception {

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
			Log.message("7.Passenger Info successfully specified");

			// step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("8.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("9.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			Thread.sleep(6000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page",
					"<b>Actual Result:</b> User should navigated on SearchResult page", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Flight Search DOM-Multicity", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_005(HashMap<String, String> testData) throws Exception {

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
			Log.message("9.Passenger Info successfully specified");

			// step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("10.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("11.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page with DOM-Multicity flight result");
			Thread.sleep(6000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page with DOM-Multicity flight resulte",
					"<b>Actual Result:</b> User should navigated on SearchResult page with DOM-Multicity flight result",
					driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Flight Search INTL-Multicity", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_006(HashMap<String, String> testData) throws Exception {

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
			Log.message("9.Passenger Info successfully specified");

			// step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("10.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("11.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page with INTL-Multicity flight result");
			Thread.sleep(6000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> ser should navigated on SearchResult page with INTL-Multicity flight result",
					"<b>Actual Result:</b> ser should navigated on SearchResult page with INTL-Multicity flight result",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = {"desktop" }, description = "Airline Matrix Strip verification on SRP for DOM flight", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_118(HashMap<String, String> testData) throws Exception {
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
			Log.message("2.Successfully clicked 'Two way' option in search Home Page ");

			// step: select OneWay Flight Search fields
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo,
					passengerClass);
			Log.message("3.Successfully filled the search details for 'TWO WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			BrowserActions.nap(5);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Book as Guest button.");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("matrixStrip"), searchResult),
					"<b>Actual Result:</b> The Airline Matrix is displayed on Review Page.",
					"<b>Actual Result:</b> The Airline Matrix is not displayed on Review Page.", driver);

			searchResult.clickAirlineMatrix();
			Log.message("Successfully clicked Airline Matrix");
			Thread.sleep(3000);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);

		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Airline Matrix Strip verification on SRP for INT flight", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_119(HashMap<String, String> testData) throws Exception {

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
			Log.message("2.Successfully clicked 'Two way' option in search Home Page ");

			// step: select OneWay Flight Search fields
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo,
					passengerClass);
			Log.message("3.Successfully filled the search details for 'TWO WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			BrowserActions.nap(5);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Book as Guest button.");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("matrixStrip"), searchResult),
					"<b>Actual Result:</b> The Airline Matrix is displayed on Review Page.",
					"<b>Actual Result:</b> The Airline Matrix is not displayed on Review Page.", driver);

			searchResult.clickAirlineMatrix();
			Log.message("Successfully clicked Airline Matrix");
			Thread.sleep(3000);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);

		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Weekly Strip verification on SRP for DOM flight - OW", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_093(HashMap<String, String> testData) throws Exception {

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
			Thread.sleep(5000);
			
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> The Weekly strip should be displayed on the SRP page for domestic flights.");
			Thread.sleep(5000);
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

	@Test(groups = {"desktop" }, description = "Add Meal on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_099(HashMap<String, String> testData) throws Exception {

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

			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("5. Clicked On Book Now Button!");

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
			Thread.sleep(5000);
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

	@Test(groups = {"desktop" }, description = "Remove Meal on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_100(HashMap<String, String> testData) throws Exception {

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

			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("5. Clicked On Book Now Button!");
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
			Thread.sleep(3000);

			travellerPage.clickOnRemoveMealButton();
			Log.message("11. Clicked On Remove Button In Review Page!");
			String mealChargesAfterRemovingMeal = travellerPage.getTextMealDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to Remove the Meal Charges On Review Page!");
			Thread.sleep(5000);
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

	@Test(groups = { "desktop" }, description = "Add Baggage on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_102(HashMap<String, String> testData) throws Exception {

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

			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("5. Clicked On Book Now Button!");
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
			Thread.sleep(5000);
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

	@Test(groups = { "desktop" }, description = "Remove Baggage on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_103(HashMap<String, String> testData) throws Exception {

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

			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("5. Clicked On Book Now Button!");
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
			Thread.sleep(3000);

			travellerPage.clickOnRemoveBaggageButton();
			Log.message("11. Clicked On Remove Baggage Button!");
			String BaggageFareAfterRemoving = travellerPage.getTextBaggageDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to Remove the Baggage Charges On Review Page!");
			Thread.sleep(5000);
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

	@Test(groups = { "desktop",	"mobile" }, description = "Verify Add Baggage on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_101(HashMap<String, String> testData) throws Exception {

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

			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("5. Clicked On Book Now Button!");
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
			Thread.sleep(5000);
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

	@Test(groups = {"desktop" }, description = "Flight Search INTL- OW with Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_003(HashMap<String, String> testData) throws Exception {
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
			Log.message("6.Passenger Info successfully specified");

			// step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.successfully selectd Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			Thread.sleep(6000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page",
					"<b>Actual Result:</b> User should navigated on SearchResult page", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Flight Search INTL- RT with Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_004(HashMap<String, String> testData) throws Exception {

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
			Log.message("7.Passenger Info successfully specified");

			// step: select Passenger class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("8.successfully selectd Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("9.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			Thread.sleep(6000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page",
					"<b>Actual Result:</b> User should navigated on SearchResult page", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Guest flow - Verification of Book As Guest button ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_112(HashMap<String, String> testData) throws Exception {

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
			Thread.sleep(3000);

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4.Successfully filled the search details for 'ONE WAY' trip.");
			Thread.sleep(3000);

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");

			// step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");
			// Thread.sleep(5000);

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

	@Test(groups = { "desktop" }, description = "Failed payment flow with Credit card flight type, travel type, booking class of your choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_104(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String cardNumber = testData.get("CreditCardNumber");

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

			reviewPage = searchResult.clickOnBookNowInOneWay(1);
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


	@Test(groups = { "desktop" }, description = "Failed payment flow with Debit card flight type, travel type, booking class of your choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_105(HashMap<String, String> testData) throws Exception {

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

			ReviewPage reviewPage = searchResult.clickOnBookNowInOneWay(1);
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

			paymentPage.enterDebitCardDetails();
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

	@Test(groups = { "desktop" }, description = "Flight details link verification on SRP-DOM", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_094(HashMap<String, String> testData) throws Exception {

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
			Thread.sleep(5000);
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

	@Test(groups = { "desktop" }, description = "Flight details link verification on SRP-DOM", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_095(HashMap<String, String> testData) throws Exception {

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
			Thread.sleep(5000);
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

	@Test(groups = { "desktop" }, description = "Flight details link verification on SRP-INTL", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_098(HashMap<String, String> testData) throws Exception {

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
			Thread.sleep(5000);
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

	@Test(groups = { "desktop" }, description = "Flight details link verification on SRP-INTL", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_097(HashMap<String, String> testData) throws Exception {

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
			Thread.sleep(5000);
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

	@Test(groups = { "desktop" }, description = "Guest flow - Verification of Book As Guest button ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_113(HashMap<String, String> testData) throws Exception {

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
			Thread.sleep(3000);

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4.Successfully filled the search details for 'ONE WAY' trip.");
			Thread.sleep(3000);

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

	@Test(groups = { "desktop" }, description = "Guest flow - Verification of Existing User checkbox", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_114(HashMap<String, String> testData) throws Exception {

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
			// Thread.sleep(3000);

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4.Successfully filled the search details for 'ONE WAY' trip.");
			Thread.sleep(3000);

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

	@Test(groups = {"desktop" }, description = "Guest flow - Check Booking as a Existing User Fill Traveller form move to Payment page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_115(HashMap<String, String> testData) throws Exception {

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
			// Thread.sleep(3000);

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4.Successfully filled the search details for 'ONE WAY' trip.");
			Thread.sleep(3000);

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");
			Thread.sleep(2000);

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
			Thread.sleep(2000);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = {"desktop" }, description = "Validating the SRP Header", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_010(HashMap<String, String> testData) throws Exception {

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
			Log.message("<b>Expected Result:</b>User should navigated on SRP, validated the Yatra-Logo and Headers");
			BrowserActions.nap(6);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon", "lnkYatraLogo"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page, and the Yatra logo is displayed,  My Account is dispalyed as <b>: " + myAccountText +"</b>, Support is dispalyed as <b>: " + supportText +"</b>, Special Deals is dispalyed as <b>: " + splDealsText +"</b>, Recent Search is dispalyed as : <b>" + recentSearchText +"</b> ",
					"<b>Actual Result:</b> User should not navigated on SearchResult page, and the Yatra logo is displayed, My Account is not dispalyed as <b>: " + myAccountText +"</b>, Support is not dispalyed as <b>: " + supportText +"</b>, Special Deals is not dispalyed as <b>: " + splDealsText +"</b>, Recent Search is not dispalyed as : <b>" + recentSearchText +"</b> ", driver);
		
			BrowserActions.nap(2);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Validating the action on clicking on Yatra logo", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_011(HashMap<String, String> testData) throws Exception {

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
			
			String yatraLogoTitle = searchResult.getTextFromAYatraLogo();
			Log.assertThat(yatraLogoTitle.equals("yatra.com"),
					"<b>Actual Result:</b> Yatra Logo tool tip should appear 'yatra.com' in SRP",
					"<b>Actual Result:</b> Yatra Logo tool tip should not appear 'yatra.com' in SRP");
			
			
			// step: click 'Search' button in Yatra Home page
			homePage = searchResult.clickYatraLogo();
			Log.message("9.Successfully clicked 'Yatra' Logo in SRP ");
			Log.message("<br>");
			//BrowserActions.nap(10);
			Log.message("<b>Expected Result:</b> Validated the action on clicking on Yatra logo.");
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("btnSearch"), homePage),
					"<b>Actual Result:</b> Validated the action on clicking on Yatra logo and User should redirected to yatra Homepage" ,
					"<b>Actual Result:</b> Not validated the action on clicking on Yatra logo and User should not redirected to Yatra Homepage",  driver);
		
			BrowserActions.nap(2);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = {"desktop" }, description = "Validating the SRP Header for logged in User", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_012(HashMap<String, String> testData) throws Exception {

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
			Log.message("<b>Expected Result:</b> Validated the SRP Header for logged in User");
			BrowserActions.nap(3);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon", "lnkYatraLogo"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page, and the Yatra logo is displayed,  UserName is dispalyed as <b>: " + userAcctNametText +"</b>, Support is dispalyed as <b>: " + supportText +"</b>, Special Deals is dispalyed as <b>: " + splDealsText +"</b>, Recent Search is dispalyed as : <b>" + recentSearchText +"</b> ",
					"<b>Actual Result:</b> User should not navigated on SearchResult page, and the Yatra logo is not displayed, UserName is not dispalyed as <b>: " + userAcctNametText +"</b>, Support is not dispalyed as <b>: " + supportText +"</b>, Special Deals is not dispalyed as <b>: " + splDealsText +"</b>, Recent Search is not dispalyed as : <b>" + recentSearchText +"</b> ", driver);
		
			BrowserActions.nap(2);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = {"desktop" }, description = "Validating the action on clicking on My Account header Menu items", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_013(HashMap<String, String> testData) throws Exception {

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
					"<b>Actual Result:</b> User should navigated on SearchResult page, and the Login is dispalyed as <b>: " + loginText +"</b>, SignUp is dispalyed as <b>: " + signUpText +"</b>, Corporate Login is dispalyed as <b>: " + corporateLoginText +"</b>, Agent Login is dispalyed as : <b>" + agentLoginText +"</b>, My Bookings is dispalyed as : <b>" + myBookingText +"</b> ",
					"<b>Actual Result:</b> User should not navigated on SearchResult page, and the Login is not dispalyed as <b>: " + loginText +"</b>, SignUp is not dispalyed as <b>: " + signUpText +"</b>, Corporate Login is not dispalyed as <b>: " + corporateLoginText +"</b>, Agent Login is not dispalyed as : <b>" + agentLoginText +"</b>, My Bookings is not dispalyed as : <b>" + myBookingText +"</b> ", driver);
		
			BrowserActions.nap(2);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
 
	@Test(groups = {"desktop" }, description = "Check total flight count", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_014(HashMap<String, String> testData) throws Exception {

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
					"<b>Actual Result:</b> Successfully verified Flight Count and Flight duration</b> ",
					"<b>Actual Result:</b> Not verified Flight Count and Flight search duration</b> ", driver);
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Checked total flight count");
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page, and the total flight count is dispalyed as <b>: " + totalFlightCountText +"</b>, Flight Search durarion is dispalyed as <b>: " + flightSearchDurationText +"</b> ",
					"<b>Actual Result:</b> User should not navigated on SearchResult page, and the total flight count is not dispalyed as <b>: " + totalFlightCountText +"</b>, Flight Search durarion is not dispalyed as <b>: " + flightSearchDurationText +"</b> ", driver);
		
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = {"desktop" }, description = "Validating the Matrix Result finder for OW search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_015(HashMap<String, String> testData) throws Exception {

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
			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page, and the Source city is dispalyed as <b>: " + sourceCityText +"</b>, Source Date is dispalyed as <b>: " + sourceDateText +"</b> Destination city is dispalyed as <b>: " + destCityText +"</b> ",
					"<b>Actual Result:</b> User should not navigated on SearchResult page, and the Source city is not dispalyed as <b>: " + sourceCityText +"</b>, Source Date is not dispalyed as <b>: " + sourceDateText +"</b> , Destination city is not dispalyed as <b>: " + destCityText +"</b>", driver);
		
			BrowserActions.nap(2);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Validating the Matrix Result finder for RT search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_116(HashMap<String, String> testData) throws Exception {

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
					"<b>Actual Result:</b> User should navigated on SearchResult page, and the Source city is dispalyed as <b>: " + sourceCityText +"</b>, Source Date is dispalyed as <b>: " + sourceDateText +"</b>, Destination city is dispalyed as <b>: " + destCityText +"</b>, Destination date is dispalyed as <b>: " + destDateText +"</b>" ,
					"<b>Actual Result:</b> User should not navigated on SearchResult page, and the Source city is not dispalyed as <b>: " + sourceCityText +"</b>, Source Date is not dispalyed as <b>: " + sourceDateText +"</b> , Destination city is not dispalyed as <b>: " + destCityText +"</b>, Destination date is not dispalyed as <b>: " + destDateText +"</b>" , driver);
		
			BrowserActions.nap(2);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	
	@Test(groups = {"desktop" }, description = "Validating the Matrix Result finder for MC search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_117(HashMap<String, String> testData) throws Exception {

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
			Log.message("9.Passenger Info successfully specified");

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
					"<b>Actual Result:</b> User should navigated on SearchResult page, and the starting Source city is dispalyed as <b>: " + startSourceCityText +"</b>, starting Source Date is dispalyed as <b>: " + startSourceDateText +"</b>, starting Destination city is dispalyed as <b>: " + startDestCityText +"</b>, ending Source city is dispalyed as <b>: " + endSourceCityText +"</b>,  ending Source date is dispalyed as <b>: " + endSourceDateText +"</b>, ending Destination city is dispalyed as <b>: " + endDestCityText +"</b>" ,
					"<b>Actual Result:</b> User should not navigated on SearchResult page, and the starting Source city is not dispalyed as <b>: " + startSourceCityText +"</b>, starting Source Date is not dispalyed as <b>: " + startSourceDateText +"</b> , starting Destination city is not dispalyed as <b>: " + startDestCityText +"</b>, ending Source city is not dispalyed as <b>: " + endSourceCityText +"</b>, ending Source date is dispalyed as <b>: " + endSourceDateText +"</b>, ending Destination city is dispalyed as <b>: " + endDestCityText +"</b>" , driver);
		
			BrowserActions.nap(2);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = {"desktop" }, description = "Validate that Duration should appear in 'hh mm' format across the SRP", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_016(HashMap<String, String> testData) throws Exception {

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
			
			//getting text from OneWay Flight Durationn format inSRP	
			String flightDurationText = searchResult.getTextFlightDuration();
			searchResult.clickFlightDetails();
			String flightDetailsPouUpDurationText = searchResult.getTextFlightDetailsPouUpDuration();
			Log.assertThat(flightDurationText.equals(flightDetailsPouUpDurationText),
					"<b>Actual Result:</b> Validated that flight duration and flight details popup duration should appear in 'hh mm' format",
					"<b>Actual Result:</b> Validated that flight duration and flight details popup duration should not appear in 'hh mm' format");
			
			
			searchResult.closeFlightDetailsPouUp();
			String resultStripText = searchResult.getTextResultStrip();
			Log.assertThat(resultStripText.equals("VIEWED"),
					"<b>Actual Result:</b> Validated Result Strip",
					"<b>Actual Result:</b> Not Validated Result Strip");
			
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated that Duration should appear in 'hh mm' format across the SRP ");
			BrowserActions.nap(6);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page, and the Flight duration format is dispalyed as like 'hh mm' <b>: " + flightDurationText +"</b>, Flight details popup duration format is dispalyed as like 'hh mm' <b>: " + flightDetailsPouUpDurationText +"</b>  ",
					"<b>Actual Result:</b> User should not navigated on SearchResult page, and the Flight duration format is not dispalyed as like 'hh mm' <b>: " + flightDurationText +"</b>, Flight details popup duration format is dispalyed as like 'hh mm' <b>: " + flightDetailsPouUpDurationText +"</b>", driver);
		
			BrowserActions.nap(2);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
  // ********************************End of Testcases ***************************************************************************************
} //FlightSearch
