package com.Yatra.TestScript.Flights;

import java.util.ArrayList;
import java.util.Arrays;

//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Flight Pricing test Cases would be designed in this class 
//Creator        :   Aspire Team
//Create         :   
//Modified on/By :   -
//-----------------------------------------------------------------------------------------------------------

import java.util.HashMap;

import javax.sql.rowset.serial.SerialArray;

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
import com.Yatra.Pages.TrainSearchResult;
import com.Yatra.Pages.TravellerPage;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class FlightNew {

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
			Log.message(
					"<b>Expected Result:</b> Validating the Pax Details section on Modify Search pop up");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("PaxDeatilInModify"), searchResult),
					"<b>Actual Result :</b> Pax Details In Modify Search is visible to the user and deatils are displayed as \n:" + PaxDetails,
					"<b>Actual Result :</b> Pax Details In Modify Search is not visible to the user", driver);

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
			Thread.sleep(2000);

			// step: click First 'Filter' Link
			searchResult.preferredFlightFirst();
			Log.message("5. Successfully Applied First Filter For Airline!");

			// step: click Second 'Filter' Link
			searchResult.scrollSliderOfFilterAmount(-200);
			Log.message("6. Successfully Applied Second Filter For Price!");
			Thread.sleep(2000);
			String Error = searchResult.getTextErrorMessageAfterApplyingFilter();
			Log.message("<b> After Applying Filters error message is displayed as </b>:" + Error);

			// step: click On 'Undo' Button
			searchResult.ClickOnUndoButton();
			Log.message("7. Successfully Clicked On Undo Button!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate the functionality of Undo Last Filters");
			Log.assertThat(
					searchResult.elementLayer.verifyPageElementsDoNotExist(Arrays.asList("btnUndoLastFilter"),
							searchResult),
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
			Thread.sleep(2000);

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
			Thread.sleep(2000);
			String FlightDetails = searchResult.getTextSelectedFlight();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate the Current Selection box on SRP at Footer");
			Thread.sleep(2000);
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(Arrays.asList("selectionDivision"), searchResult),
					"<b>Actual Result :</b> Current Selection box on SRP at Footer is visible to the user with all detaila such as :\n"
							+ FlightDetails,
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
			Thread.sleep(2000);
			String FareDetails = searchResult.getTextFareSummarySelectionBox_DOM();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate the action on hovering mouse over Price");
			Thread.sleep(2000);
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(Arrays.asList("selectionDivision"), searchResult),
					"<b>Actual Result :</b> After Mouse Hover on Price User is able to see Fare Summary Pop Up and Details are Displayed as :\n"
							+ FareDetails,
					"<b>Actual Result :</b> After Mouse Hover on Price, NO Fare Summary Pop Up is visible", driver);

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
			Thread.sleep(2000);

			// step: click 'Flight Details' Link
			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("5. Successfully clicked 'Flight Deatils' Link!");

			// step: click 'Fare and Rule' Link
			searchResult.clickOnlnkFareandRule();
			Log.message("6. Successfully clicked 'Fare and Rule' Link!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating the action on clicking on Flight Details link");
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(
							Arrays.asList("txtFareSummaryInFlighDeatils", "txtFareRulesInFlighDeatils"), searchResult),
					"<b>Actual Result :</b> After Clicking On Fare Summary and Rule under Flight Deatils two div are visible to user",
					"<b>Actual Result :</b> After Clicking On Fare Summary and Rule under Flight Deatils two div are not visible to user",
					driver);

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
			Thread.sleep(3000);
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5. Successfully clicked Book Now!");
			Thread.sleep(2000);
			String FareRules = reviewPage.getTextFareRules();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate via flight check on review page");
			Thread.sleep(2000);
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

			// step: click 'Flight Details' button
			Thread.sleep(3000);
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5. Successfully clicked Book Now'!");
			Thread.sleep(2000);
			String FlightDetails = reviewPage.getTextFromFareDetails();
			String Totalfare = reviewPage.getTextTotalAmount();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate that in case of Multiple pax Fare summary break up");
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(
							Arrays.asList("totalAmountInreviewPage", "contentFareDetails"), reviewPage),
					"<b>Actual Result :</b> In Review Page,Flight Fare Details are Displayed as : \n" + FlightDetails
							+ "\nand Total cost as : " + Totalfare,
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
			Thread.sleep(3000);
			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("6. Successfully clicked 'Flight Details'!");

			// step: click on Book Now in Flight Details
			Thread.sleep(3000);
			reviewPage = searchResult.clickOnBookNowInFlightDetails_INTL();
			Log.message("7. Successfully clicked Book Now in Flight Details Pop Up!");
			ArrayList<String> FlightDetails = reviewPage.getTextAirlinename();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate flight details on review page");
			Thread.sleep(2000);
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnContinueReviewPage"), reviewPage),
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
			Thread.sleep(3000);
			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("5. Successfully clicked 'Flight Details'!");

			// step: click on Book Now in Flight Details
			Thread.sleep(3000);
			reviewPage = searchResult.clickOnBookNowInFlightDetails_INTL();
			Log.message("6. Successfully clicked Book Now in Flight Details Pop Up!");
			Thread.sleep(2000);
			ArrayList<String> FlightDetails = reviewPage.getTextAirlinename();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate via flight check on review page");
			Thread.sleep(2000);
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnContinueReviewPage"), reviewPage),
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
			Thread.sleep(3000);
			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("5. Successfully clicked 'Flight Details'!");

			// step: click on Book Now in Flight Details
			searchResult.clickOnBookNowInFlightDetail_INTL();
			Log.message("6. Successfully clicked Book Now in Flight Details Pop Up!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Validate the action on clicking on 'Book Now' button from Flight Details section");
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(Arrays.asList("conformingUrSeatPopUp"), searchResult),
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

	@Test(description = "Validate that for RT and MC search Book Now button should not be available on Flight Details section", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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
			Thread.sleep(3000);
			searchResult.clickOnFlightDetailsInRT(5, 0);
			Log.message("5. Successfully clicked 'Flight Details'!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Validate that for RT and MC search Book Now button should not be available on Flight Details section");
			Thread.sleep(2000);
			Log.assertThat(
					searchResult.elementLayer.verifyPageElementsDoNotExist(Arrays.asList("btnBookNowFlightDeatilPopUp"),
							searchResult),
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
			boolean resultBeforeChangeFlight = searchResult.verifyChkBoxAirlineFilter();
			Log.message("" + resultBeforeChangeFlight);

			// step: click on 'Book Now'
			reviewPage = searchResult.clickOnBookNowInOneWay(15);
			Log.message("6. Successfully Clicked Book Now!");

			// step: click on 'Change Flight'
			Thread.sleep(2000);
			reviewPage.clickOnChangeFlight();
			Log.message("7. Successfully Clicked On Change Flight Button!");
			boolean resultAfterChangeFlight = searchResult.verifyChkBoxAirlineFilter();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Validate that if user has selected any preferred airline from Airline matrix then on clicking on Change Flight SRP should render with 'All Airlines' Selected");
			Thread.sleep(2000);
			Log.assertThat(resultBeforeChangeFlight != (resultAfterChangeFlight),
					"<b>Actual Result :</b> Error Message is displayed when No Flight is visible for user and \n message is displayed as : ",
					"<b>Actual Result :</b> No  Error Message is displayed when No Flight is visible for user", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			// driver.quit();
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
			Thread.sleep(2000);
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(Arrays.asList("txterrorMessageNoFlights"),
							searchResult),
					"<b>Actual Result :</b> Error Message is displayed when No Flight is visible for user and \n message is displayed as : "
							+ ErrorMessage,
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
			Thread.sleep(2000);
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
			Thread.sleep(2000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("headerLogo"), searchResult),
					"<b>Actual Result :</b> User navigated to Search result page and origin is displayed as :"
							+ originHeader + "  and Deapture is displayed as :" + departureHeader,
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
			Log.message(
					"<b>Expected Result:</b> Validate that for logged in User Offer strip should appear with user name");
			Thread.sleep(2000);
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(Arrays.asList("txtUserNameOnHeader"), searchResult),
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
			Thread.sleep(2000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("headerLogo"), searchResult),
					"<b>Actual Result :</b> If user is not Logged in 'Login Button' is dispalyed under My account section and text as : USER "
							+ name,
					"<b>Actual Result :</b> If user is not Logged No 'Login Button' is dispalyed under My account",
					driver);

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
					"<b>Actual Result :</b> Free Meal option is visible to the user and message is dispalyed as : "
							+ message,
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
			Log.message(
					"<b>Expected Result:</b> Validate that for Domestic flights offering Paid Meals, Meal  text should NOT appear on SRP");
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
			Log.message(
					"<b>Expected Result:</b> Validate that there should appear Ecash earned amount in Result strip aligned to Flight Details link below Book Now button if configured from xdist for respective Airline");
			Thread.sleep(2000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("txtEcashEarned"), searchResult),
					"<b>Actual Result :</b> Ecash for different flights is displayed and amount is Rs." + Ecash
							+ "\n and for other Flight amount is : " + EcashNew,
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
			Thread.sleep(2000);
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(Arrays.asList("txtSeatLeftMessage"), searchResult),
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

	@Test(groups = {
			"desktop" }, description = "Validate that Flight Details pop should also appear with Seat left message", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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
			Thread.sleep(2000);
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(Arrays.asList("txtSeatLeftMessage"), searchResult),
					"<b>Actual Result :</b> Seat left message is Displayed on Flight Details Pop up as : " + seatLeft,
					"<b>Actual Result :</b> Yatra Page Footer is not visible on Flight Details Pop up", driver);

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
			Thread.sleep(2000);
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(Arrays.asList("yatraFooterPanel"), searchResult),
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
			Thread.sleep(2000);
			String ErrorMessagePrice = searchResult.enterGetFareDetailsPrice(Price);
			String ErrorMessageEmail = searchResult.enterGetFareDetailsEmail(Email);
			String ErrorMessagePhoneNumber = searchResult.enterGetFareDetailsPhoneNumber(PhoneNumber);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify validation of inputs in fare alert");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("headerLogo"), searchResult),
					"<b>Actual Result :</b> After Clicking On Get Fare Alert Link,\n validation message for Amount is Displayed as : "
							+ ErrorMessagePrice + "\n For Email is displayed as :" + ErrorMessageEmail
							+ "\n and For Phone Number it is displayed as :" + ErrorMessagePhoneNumber,
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
			Thread.sleep(2000);
			searchResult.clickOnCloseInFlightDetailPopUp();
			Log.message("6. Successfully clicked on 'Close' Button!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Fare Details viewed info");
			Thread.sleep(2000);
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
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(Arrays.asList("btnBookNowBottom_RT"), searchResult),
					"<b>Actual Result :</b> If Flight Difference is Less the 3 Hrs then Book Now should be disabled and Error Message is displayed as :"
							+ error,
					"<b>Actual Result :</b>  If Flight Difference is Less the 3 Hrs then Book Now should not be disabled",
					driver);

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
			Thread.sleep(2000);
			String Stops = searchResult.getTextNoOfStops();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Stops info in each flight card");
			Thread.sleep(2000);
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

			Thread.sleep(1000);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify functionality of 'Go to TOP' button");
			Log.assertThat(
					searchResult.elementLayer.verifyPageElementsDoNotExist(Arrays.asList("btnScrollUpSRP"),
							searchResult),
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
			Log.assertThat(
					originName.equalsIgnoreCase(originNameAfterLogin)
							&& DestinationName.equalsIgnoreCase(DestinationNameAfterLogin),
					"<b>Actual Result :</b> After Login From SearchResult Page a pop up is appeared on Recent serach amd Message is displayed as :\n"
							+ message + " and city and destination is same",
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
			Thread.sleep(1000);
			String Txt = searchResult.getTextRecentSearchPopUp();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Auto Save search result on login yatra account");
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("headerLogo"), searchResult),
					"<b>Actual Result :</b> After Login From Search result Page a Pop Up is appeared with Details"
							+ Txt,
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
			Thread.sleep(2000);
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

	@Test(description = "Verification Promo functionality from drop down", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_144(HashMap<String, String> testData) throws Exception {

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

			// step: click on 'Promo' DropDown
			reviewPage.clickOnPromoDrpDwn();
			Log.message("6. Successfully clicked 'Promo' DropDown!");

			// step: selected 'Promotion' from the DropDown
			reviewPage.selectPromoByIndex(2);
			Log.message("7. Successfully Selected 'Promotion' from the DropDown!");
			Thread.sleep(3000);
			String Ecash = reviewPage.getTextEcashFareDetails();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verification Promo functionality from drop down");
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("totalAmountInreviewPage"), reviewPage),
					"<b>Actual Result :</b> When Promo Type is Ecash, after applying Promo Ecash Message is displayed as :\n"
							+ Ecash,
					"<b>Actual Result :</b> No Promotion is visible to the user", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Remove Promo Code Functionality from Fare Details", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_148(HashMap<String, String> testData) throws Exception {

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

			// step: click on 'Promo' DropDown
			reviewPage.clickOnPromoDrpDwn();
			Log.message("6. Successfully clicked 'Promo' DropDown!");

			// step: selected 'Promotion' from the DropDown
			reviewPage.selectPromoByIndex(1);
			Log.message("7. Successfully Selected 'Promotion' from the DropDown!");
			Thread.sleep(2000);
			String amount = reviewPage.getTextTotalAmount();

			// step: clicked On Remove Button
			reviewPage.ClickOnRemoveButton();
			Log.message("8. Successfully Clicked On 'Remove' Button!");
			String amountafterRemovingPromo = reviewPage.getTextTotalAmount();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verification Remove Promo Code Functionality from Promo Apply Box");
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("totalAmountInreviewPage"), reviewPage),
					"<b>Actual Result :</b> Amount When Promo Code is  applied is = :  " + amount
							+ " \n Amount after clicking Remove Button is displayed as = : " + amountafterRemovingPromo,
					"<b>Actual Result :</b> No Amount is changed after applying Promo and Unable to Click On Remove Button On FAre Details Section",
					driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Remove Promo Code Functionality from Promo Apply Box", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_149(HashMap<String, String> testData) throws Exception {

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

			// step: click on 'Promo' DropDown
			reviewPage.clickOnPromoDrpDwn();
			Log.message("6. Successfully clicked 'Promo' DropDown!");

			// step: selected 'Promotion' from the DropDown
			reviewPage.selectPromoByIndex(1);
			Log.message("7. Successfully Selected 'Promotion' from the DropDown!");
			Thread.sleep(2000);
			String amount = reviewPage.getTextTotalAmount();

			// step: clicked On Remove Button
			reviewPage.ClickOnCloseButtonInPromoBox();
			Log.message("8. Successfully Clicked On 'Remove' Button!");
			String amountafterRemovingPromo = reviewPage.getTextTotalAmount();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verification Remove Promo Code Functionality from Promo Apply Box");
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("totalAmountInreviewPage"), reviewPage),
					"<b>Actual Result :</b> Amount When Promo Code is  applied is = :  " + amount
							+ " \n Amount after Clicking Close Button is Displayed as  = : " + amountafterRemovingPromo,
					"<b>Actual Result :</b> Unable to click on Close Button in Promo Box and No Price is Changed",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verification Change Promo", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_150(HashMap<String, String> testData) throws Exception {

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

			// step: click on 'Promo' DropDown
			reviewPage.clickOnPromoDrpDwn();
			Log.message("6. Successfully clicked 'Promo' DropDown!");

			// step: selected First 'Promotion' from the DropDown
			reviewPage.selectPromoByIndex(1);
			Log.message("7. Successfully Selected First 'Promotion' from the DropDown!");
			Thread.sleep(2000);
			String firstPromoDiscount = reviewPage.getTextPromotinalMessage();

			// step: click on 'Promo' DropDown
			reviewPage.clickOnPromoDrpDwn();
			Log.message("8. Successfully clicked 'Promo' DropDown!");

			// step: selected Second 'Promotion' from the DropDown
			reviewPage.selectPromoByIndex(2);
			Log.message("9. Successfully Selected second 'Promotion' from the DropDown!");
			Thread.sleep(2000);
			String SecondPromoDiscount = reviewPage.getTextPromotinalMessage();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verification Change Promo");
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtPromoDiscountApplied"), reviewPage),
					"<b>Actual Result :</b> Promotional Discount For First promo code is :  " + firstPromoDiscount
							+ " \n Promotional Discount For second Promo is : " + SecondPromoDiscount,
					"<b>Actual Result :</b> User did not navigated to review Page", driver);

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
			Thread.sleep(1000);
			String fare = searchResult.getTextFareDetailsandRuleInPopUp();

			// step: click 'Baggage' Link
			searchResult.clickOnBaggageFlightDetail();
			Log.message("7. Successfully clicked 'Baggage Link' in Flight Detail Pop Up!");
			Thread.sleep(1000);
			String Baggage = searchResult.getTextBaggageInfoFlightDetail();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating the action on clicking on Flight Details link");
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(
							Arrays.asList("lnkBaggageFlightDetail", "lnkFareAndSummaryFlightDetail"), searchResult),
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
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtReviewYourBooking"), reviewPage),
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
}// FlightNewEnd
