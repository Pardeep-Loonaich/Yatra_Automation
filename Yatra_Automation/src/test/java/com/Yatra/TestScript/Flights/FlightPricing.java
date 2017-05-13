
package com.Yatra.TestScript.Flights;

import java.util.Arrays;

//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Flight Pricing test Cases would be designed in this class 
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

import test.abstractconfmethod.B;

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
public class FlightPricing extends BaseTest{

	EnvironmentPropertiesReader environmentPropertiesReader;
	HomePage homePage;
	LoginPage loginPage;
	ReviewPage reviewPage;
	SearchResult searchResult;
	TravellerPage travellerPage;
	PaymentPage paymentPage;
	String webSite;
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(description = "Check to price calculation for DOM flight-one way", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_087(HashMap<String, String> testData) throws Exception {
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
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button
			reviewPage = searchResult.clickOnBookNowInOneWay(10);
			Log.message("5.Clicked on 'Book Now' button in Search Result Page ");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check to price calculation for DOM flight-one way.");
			BrowserActions.nap(2);
			reviewPage.popUpAppear();
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("moduleFareDetails"), reviewPage),
					"<b>Actual Result:</b> The Fare details module is displayed on Review Page.",
					"<b>Actual Result:</b> The Fare details module is not displayed on Review Page.", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Check to price calculation for DOM flight-round trip", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_088(HashMap<String, String> testData) throws Exception {
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
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now
			reviewPage = searchResult.clickOnBookNowInRound(1, 2, 2, 7);
			Log.message("5.Clicked on 'Book Now' button in Search Result Page.");
			reviewPage.popUpAppear();

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChangeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicked on fees & surcharge link
			reviewPage.clickOnFeeSurchrgeLink();
			Log.message("6.Clicked on 'Fees & Surcharge' details link in Review Page.");			

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Fees & Surcharge details as Signed User should be displayed after clicking on Fees & Surcharge Link in Fare Details module.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("moduleFeeSurchrge"), reviewPage),
					"<b>Actual Result:</b> The Fare details module is displayed on Review Page.",
					"<b>Actual Result:</b> The Fare details module is not displayed on Review Page.", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Insurance added on pax page ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_089(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
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

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");
			
			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button
			reviewPage = searchResult.clickOnBookNowInOneWay(3);
			Log.message("5.Clicked on 'Book Now' button in Search Result Page.");
			reviewPage.popUpAppear();

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChangeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			reviewPage.clickOnContinue();
			Log.message("6. Clicked On Continue Button on Review Page!");

			reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Successfully Logged in Yatra account!");			

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Travel Assistance and Insurance amount should be inculded in the Fare Detail.");
			Log.assertThat(travellerPage.getTextFromFareDetails().contains("Travel Assistance and Insurance"),
					"<b>Actual Result:</b> Travel Assistance and Insurance amount included in the Fare details and is displayed as:",
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

	@Test(description = "Insurance verification on pax page removed", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_090(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
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

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(3);
			Log.message("5.Clicked on 'Book Now' button in Search Result Page.");
			reviewPage.popUpAppear();

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChangeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			reviewPage.clickOnContinue();
			Log.message("6.Clicked On Continue Button on Review Page!");

			reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Successfully Logged in Yatra account!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Travel Assistance and Insurance amount should be inculded in the Fare Detail.");

			Log.assertThat(travellerPage.verifyInsuranceCheckbox()	&& travellerPage.getTextFromFareDetails().contains("Travel Assistance and Insurance"),
					"<b>Actual Result:</b> Insurance checkbox is checked and Travel Assistance and Insurance amount is included in the Fare Details. ",
					"<b>Actual Result:</b> Insurance checkbox is not checked and the Travel Assistance and Insurance amount is not displayed in Fare Details.",	driver);

			// clicked on Insurance
			travellerPage.uncheckingInsuranceCheckbox();
			Log.message("8.Unchecking on Insurance checkbox. ");

			Log.assertThat(!travellerPage.verifyInsuranceCheckbox()	&& (!travellerPage.getTextFromFareDetails().contains("Travel Assistance and Insurance")),
					"<b>Actual Result:</b> Insurance checkbox is unchecked and Travel Assistance and Insurance amount not included in the Fare Details. ",
					"<b>Actual Result:</b> Insurance checkbox is not unchecked and the Travel Assistance and Insurance amount is displayed in Fare Details.", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "eCash redemption on payswift page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_091(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
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
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5.Clicked on 'Search' in Yatra Homepage.");

			BrowserActions.nap(2);
			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.", "<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(3);
			reviewPage.popUpAppear();
			Log.message("6.Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChangeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",	"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			reviewPage.clickOnContinue();
			Log.message("7.Clicked on Continue button in Review Page Step-1.");

			reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8.Successfully Logged in Yatra account!");

			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("9. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("10.Clicked on Continue button in Review Page Step-2.");

			paymentPage.clickingOnRedeemNow();
			Log.message("11.Clicked on Redeem Now Button to add ecash.");

			paymentPage.clickingOnGotIt();
			Log.message("12.Clicked on 'OK,Got It' link to confirm redeem ecash.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Ecash should be applied and balance amount should be deducted from the total payment under Payment method.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("msgEcashRedeem"), paymentPage),
					"<b>Actual Result1:</b> Ecash is applied successfully and the message is displayed under Payment method."
					+ paymentPage.getMsgFromEcashRedeemSuccess(), "<b>Actual Result1:</b> Ecash is not applied and the message is not displayed under Payment method.",	driver);

			Log.assertThat(	paymentPage.elementLayer.verifyPageElements(Arrays.asList("msgEcashRedeemBalance"), paymentPage),
					"<b>Actual Result2:</b> Balance is successfully deducted and the message is displayed under Payment method."
					+ paymentPage.getMsgFromEcashBalanceDeduction(), "<b>Actual Result2:</b> Balance is not deducted and the message is not displayed under Payment method.",	driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "eCash redemption on payswift page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_092(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
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
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			//step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.", "<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(3);
			Log.message("5.Clicked on 'Book Now' button in Search Result Page.");
			reviewPage.popUpAppear();
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChangeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",	"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			reviewPage.clickOnContinue();
			Log.message("6.Clicked on Continue button in Review Page Step-1.");

			reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7.Successfully Logged in Yatra account!");

			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9.Clicked on Continue button in Review Page Step-2.");

			paymentPage.clickingOnRedeemNow();
			Log.message("10.Clicked on Redeem Now Button to add ecash.");

			paymentPage.clickingToCancelEcashRedem();
			Log.message("11.Clicked on Cancel Ecash Redeem in Review Page Step-2.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Ecash should be applied and balance amount should be deducted from the total payment under Payment method.");
			Log.assertThat(	paymentPage.elementLayer.verifyPageElements(Arrays.asList("btnRedeemNow"), paymentPage)
							&& (!paymentPage.getTextFromPaymentDetailsModule().contains("eCash Redeemed")),
					"<b>Actual Result1:</b> Ecash Redeem is cancelled and is not displayed under Payment method.",
					"<b>Actual Result1:</b> Ecash is applied and the message is displayed under Payment method.", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(description = "Check to price calculation for DOM flight-multicity", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_096(HashMap<String, String> testData) throws Exception {
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

			String departDate = homePage.selectMultiCityDateDeparture1(departureDate);
			Log.message("5.Successfully selected the Multicity Departure1 date: <b>" + departDate + "</b>(YY/MM/DD)");

			homePage.enterMultiCityOrigin2(origin2);
			Log.message("6.Successfully entered Multicity Origin2 '<b>" + origin2 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination2(destination2);
			Log.message("7.Successfully entered Multicity Destination1 '<b>" + destination2 + "</b>' in Yatra Homepage");

			String returndate = homePage.selectMultiCityDateDeparture2(returnDate);
			Log.message("8.Successfully selected the Multicity Departure1 date: <b>" + returndate + "</b>(YY/MM/DD)");

			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("9.Passenger Info successfully specified");

			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("10.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("11.Successfully clicked 'Search' button in Yatra Homepage ");

			BrowserActions.nap(2);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page with DOM-Multicity flight result",
					"<b>Actual Result:</b> User not navigated on SearchResult page with DOM-Multicity flight result", driver);

			reviewPage = searchResult.clickOnBookNowInMulticity(1, 1, 2, 4);
			Log.message("12.Clicked on 'Book Now' button in Search Result Page ");
			reviewPage.popUpAppear();

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChangeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",	"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);
			
			// clicked on fees & surcharge link
			reviewPage.clickOnFareRulesLink();
			Log.message("13.Clicked on 'Fare Rules' details link in Review Page.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Fare Rules popup as Signed User should be displayed after clicking on View Fare Rules Link in Fare Details module.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("moduleFareRules"), reviewPage),
					"<b>Actual Result:</b> The Fare Rules module is displayed on Review Page.",
					"<b>Actual Result:</b> The Fare Rules module is not displayed on Review Page.", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			 driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(description = "Change flight link verification on Review page - DOM", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_106(HashMap<String, String> testData) throws Exception {
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

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			BrowserActions.nap(2);

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");

			// step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("4.Clicked on 'Book Now' button in Search Result Page ");
			reviewPage.popUpAppear();
			BrowserActions.nap(2);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Change Flight link.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChangeFlight"), reviewPage),
					"<b>Actual Result:</b> The Change Flight link is displayed on Review page.",
					"<b>Actual Result:</b> The Change Flight link is not displayed on Review Page.", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Change flight link verification on Review page - INTL", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_107(HashMap<String, String> testData) throws Exception {
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

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields ");
			BrowserActions.nap(2);

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();		
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");

			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5.Clicked on 'Book Now' button in Search Result Page ");
			reviewPage.popUpAppear();	

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Change Flight link.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChangeFlight"), reviewPage),
					"<b>Actual Result:</b> The Change Flight link is displayed on Review page.",
					"<b>Actual Result:</b> The Change Flight link is not displayed on Review Page.", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Applying promo code on review page- Promo dropdown Validation", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_108(HashMap<String, String> testData) throws Exception {
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

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields ");
			
			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");
			

			// step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5.Clicked on 'Book Now' button in Search Result Page ");
			reviewPage.popUpAppear();

			// step: Click on 'Promo Drop Down' in Review page
			reviewPage.clickOnPromoDrpDwn();
			Log.message("6. Clicked  on 'Promo Drop Down' in Review page");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Promo Code Dropdown");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("fldContentpromo"), reviewPage),
					"<b>Actual Result:</b> Promo Code Dropdown is displayed on Review page",
					"<b>Actual Result:</b> Promo Code Dropdown is not displayed on Review Page", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Applying promo code on review page- Promo Coupon Selection", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_109(HashMap<String, String> testData) throws Exception {
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

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields ");
			BrowserActions.nap(5);

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");

			// step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5.Clicked on 'Book Now' button in Search Result Page ");
			reviewPage.popUpAppear();

			// step: Click on 'Promo Drop Down' in Review page and select Promo Coupon
			reviewPage.clickOnPromoCoupon();
			Log.message("6. Clicked  on 'Promo Drop Down' in Review page and selected coupon");
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Applying promo code on review page- Have a Promo Code Validation", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_110(HashMap<String, String> testData) throws Exception {
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

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields ");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");
			BrowserActions.nap(4);

			// step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5.Clicked on 'Book Now' button in Search Result Page ");
			reviewPage.popUpAppear();

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("lnkHavePromoCode"), reviewPage),
					"<b>Actual Result:</b> Hava a Promo code link is displayed on Review page.",
					"<b>Actual Result:</b> Hava a Promo code link is not displayed on Review Page.", driver);

			// step: Click on 'Have a coupon' link in Review page
			reviewPage.clickOnHavePromoCode();
			Log.message("6.Clicked on 'Have a Promo Code' link in Review Page ");
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Applying promo code on review page-  Have a Promo Code submission", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_111(HashMap<String, String> testData) throws Exception {
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

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields ");
			BrowserActions.nap(2);

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' in Yatra Homepage ");

			// step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5.Clicked on 'Book Now' button in Search Result Page ");
			reviewPage.popUpAppear();

			/*
			 * Click on 'Promo Drop Down' in Review page and select Promo Coupon
			 * Click on 'Have a coupon' link and enter the selected promo coupon
			 * Click on 'Apply' button
			 */
			reviewPage.getPromoCode();
			Log.message("6.Successfully Applied the Promo Code.");
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
		
	@Test( description = "DOM flight pricing - OW ( Any airline)", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_122(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage.");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			//handle popup if displayed in Review page
			reviewPage.popUpAppear();	
			
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated DOM Flight pricing - OW ( Any airline)");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "DOM flight pricing - OW ( Preferred airline)", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_123(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage.");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain,stops, airlines);	
			Log.message("5. Successfully clicked On Book Now Button with Preferred(<b>"+airlines+"</b>) Flight");
			
			//handle popup if displayed in Review page
			reviewPage.popUpAppear();	
			
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated DOM Flight pricing - OW ( Preferred airline)");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);				
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
			Log.assertThat(	reviewPage.elementLayer.verifyPageElements(Arrays.asList("totalAmountInreviewPage"), reviewPage),
					"<b>Actual Result :</b> When Promo Type is Ecash, after applying Promo Ecash Message is displayed as :\n"
					+ Ecash, "<b>Actual Result :</b> No Promotion is visible to the user", driver);
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
			Log.assertThat(	reviewPage.elementLayer.verifyPageElements(Arrays.asList("totalAmountInreviewPage"), reviewPage),
					"<b>Actual Result :</b> Amount When Promo Code is  applied is = :  " + amount + " \n Amount after clicking Remove Button is displayed as = : " + amountafterRemovingPromo,
					"<b>Actual Result :</b> No Amount is changed after applying Promo and Unable to Click On Remove Button On FAre Details Section",	driver);
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
			Log.assertThat(	reviewPage.elementLayer.verifyPageElements(Arrays.asList("totalAmountInreviewPage"), reviewPage),
					"<b>Actual Result :</b> Amount When Promo Code is  applied is = :  " + amount	+ " \n Amount after Clicking Close Button is Displayed as  = : " + amountafterRemovingPromo,
					"<b>Actual Result :</b> Unable to click on Close Button in Promo Box and No Price is Changed",	driver);
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
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtPromoDiscountApplied"), reviewPage),
					"<b>Actual Result :</b> Promotional Discount For First promo code is :  " + firstPromoDiscount + " \n Promotional Discount For second Promo is : " + SecondPromoDiscount,
					"<b>Actual Result :</b> User did not navigated to review Page", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "DOM flight pricing - RT ( Any airline)", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_154(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInRT(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			//handle popup if displayed in Review page
			reviewPage.popUpAppear();	
			
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated DOM Flight pricing - RT ( Any airline)");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "DOM flight pricing - RT ( Preferred airline)", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_155(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInRT(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			//handle popup if displayed in Review page
			reviewPage.popUpAppear();	
			
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated DOM Flight pricing - RT ( Preferred airline)");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "INTL flight pricing - OW ( Any airline)", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_156(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage.");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			//handle popup if displayed in Review page
			reviewPage.popUpAppear();	
			
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated INTL Flight pricing - OW ( Any airline)");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "INTL flight pricing - OW ( Preferred airline)", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_157(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage.");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain, stops, airlines);	
			Log.message("5. Successfully clicked On Book Now Button with Preferred(<b>"+airlines+"</b>) Flight");
			
			//handle popup if displayed in Review page
			reviewPage.popUpAppear();	
			
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated INTL Flight pricing - OW ( Preferred airline)");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "INTL flight pricing - RT ( Any airline)", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_158(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInRT(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			//handle popup if displayed in Review page
			reviewPage.popUpAppear();	
			
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated INTL Flight pricing - RT ( Any airline)");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "INTL flight pricing - RT ( Preferred airline)", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_159(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInRT(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			//handle popup if displayed in Review page
			reviewPage.popUpAppear();	
			
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validated INTL Flight pricing - RT ( Preferred airline)");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "DOM-RT Flight pricing - Failure on fare change", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_162(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInRT(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
											
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify flight pricing and mark the test failed if flight fare change alert popup is appear for DOM-RT");
			Log.assertThat(reviewPage.fareChangeAlertPopUpNotAppear(), "<b>Actual Result:</b> Flight Fare Change Alert is not displayed ", "<b>Actual Result:</b> Flight Fare Change Alert is displayed", driver);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "DOM-OW Flight pricing - Failure on fare change", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_163(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage.");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify flight pricing and mark the test failed if flight fare change alert popup is appear for DOM-OW");
			Log.assertThat(reviewPage.fareChangeAlertPopUpNotAppear(), "<b>Actual Result:</b> Flight Fare Change Alert is not displayed ", "<b>Actual Result:</b> Flight Fare Change Alert is displayed", driver);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
				
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "INTL-RT Flight pricing - Failure on fare change", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_164(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInRT(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify flight pricing and mark the test failed if flight fare change alert popup is appear for INTL-RT");
			Log.assertThat(reviewPage.fareChangeAlertPopUpNotAppear(), "<b>Actual Result:</b> Flight Fare Change Alert is not displayed ", "<b>Actual Result:</b> Flight Fare Change Alert is displayed", driver);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
					
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "INTL-OW Flight pricing - Failure on fare change", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_165(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage.");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify flight pricing and mark the test failed if flight fare change alert popup is appear for INTL-OW");
			Log.assertThat(reviewPage.fareChangeAlertPopUpNotAppear(), "<b>Actual Result:</b> Flight Fare Change Alert is not displayed ", "<b>Actual Result:</b> Flight Fare Change Alert is displayed", driver);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
					
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test( description = "DOM-RT Flight pricing - Test Pass on fare change", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_166(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInRT(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			//handle popup if displayed in Review page	
			if (reviewPage.fareChangeAlertPopUpAppear() == true) {
				Log.message("6.Handled Flight fare change alert popup");
			} else {
				Log.message("6.Flight fare change alert poupup is not displayed");
			}		
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify flight pricing and mark the test pass even if Flight fare change alert popup is dsiplayed for DOM-RT");
			//Log.assertThat(reviewPage.fareChangeAlertPopUpAppear(), "<b>Actual Result:</b> Handled Flight Fare Change Alert poupup if displayed", "<b>Actual Result:</b> Not handled Flight Fare Change Alert poupup", driver);
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);		
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "DOM-OW Flight pricing - Test Pass on fare change", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_167(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage.");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			//handle popup if displayed in Review page	
			if (reviewPage.fareChangeAlertPopUpAppear() == true) {
				Log.message("6.Handled Flight fare change alert popup");
			} else {
				Log.message("6.Flight fare change alert poupup is not displayed");
			}
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify flight pricing and mark the test pass even if Flight fare change alert popup is dsiplayed for DOM-OW");
			//Log.assertThat(reviewPage.fareChangeAlertPopUpAppear(), "<b>Actual Result:</b> Handled Flight Fare Change Alert poupup if displayed", "<b>Actual Result:</b> Not handled Flight Fare Change Alert poupup", driver);
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "INTL-RT Flight pricing - Test Pass on fare change", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_168(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page ");
	
			//step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInRT(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			//handle popup if displayed in Review page	
			if (reviewPage.fareChangeAlertPopUpAppear() == true) {
				Log.message("6.Handled Flight fare change alert popup");
			} else {
				Log.message("6.Flight fare change alert poupup is not displayed");
			}
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify flight pricing and mark the test pass even if Flight fare change alert popup is dsiplayed for INTL-RT");
			//Log.assertThat(reviewPage.fareChangeAlertPopUpAppear(), "<b>Actual Result:</b> Handled Flight Fare Change Alert poupup if displayed", "<b>Actual Result:</b> Not handled Flight Fare Change Alert poupup", driver);
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test( description = "INTL-OW Flight pricing - Test Pass on fare change", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_169(HashMap<String, String> testData) throws Exception {		
		Utils.testCaseConditionalSkip(testData.get("RunMode"));		
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String stops = testData.get("Stops");
		String airlines = testData.get("Airlines");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage.");
			
			// step: select Airlines Book Now for One Way search
			reviewPage = searchResult.selectAirlineBookNowInOW(domain, stops, airlines);	
			Log.message("5. Successfully clicked Book Now Button with Random Flights");
			
			//handle popup if displayed in Review page	
			if (reviewPage.fareChangeAlertPopUpAppear() == true) {
				Log.message("6.Handled Flight fare change alert popup");
			} else {
				Log.message("6.Flight fare change alert poupup is not displayed");
			}
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify flight pricing and mark the test pass even if Flight fare change alert popup is dsiplayed fo INTL-OW");
			//Log.assertThat(reviewPage.fareChangeAlertPopUpAppear(), "<b>Actual Result:</b> Handled Flight Fare Change Alert poupup if displayed", "<b>Actual Result:</b> Not handled Flight Fare Change Alert poupup", driver);
			String flightPrice = reviewPage.getTextFlightPrice();
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtshowFlightFareDetails"), reviewPage),
					"<b>Actual Result:</b> Successfully displayed Flight Price in Review Page, Flight Price : "+ flightPrice,
					"<b>Actual Result:</b> Flight Prices is not displayed",	driver);			
			
			Log.testCaseResult();			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
} //Flight Pricing
