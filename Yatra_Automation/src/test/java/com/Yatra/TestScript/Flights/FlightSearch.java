package com.Yatra.TestScript.Flights;

import java.util.Arrays;

//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Flight Searching test Cases would be designed in this class 
//Creator        :   Aspire Team
//Create         :   
//Modified on/By :   -
//-----------------------------------------------------------------------------------------------------------

import java.util.HashMap;

//import javax.naming.directory.SearchResult;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Yatra.Pages.HomePage;
import com.Yatra.Pages.LoginPage;
import com.Yatra.Pages.ReviewPage;
import com.Yatra.Pages.SearchResult;
import com.Yatra.Utils.BrowserActions;

import com.Yatra.Pages.SearchResult;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.TestDataExtractor;
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class FlightSearch {

	EnvironmentPropertiesReader environmentPropertiesReader;
	HomePage homePage;
	LoginPage loginPage;
	SearchResult searchResult;
	String webSite;
	private String workbookName = "testdata\\data\\Flights.xls";
	private String sheetName = "FlightSearch";
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}


	@Test(groups = { "desktop" }, description = "Flight Search DOM - OW with  Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_001(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("passengerClass");


		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
						
			LoginPage loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2.Successfully Logged in Yatra account");

			/// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in search Home Page ");

			
			//step:  select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo,passengerClass);		
			Log.message("4.Successfully selected OneWay Flight Search Fields ");


			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("4.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("5.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			String departDate= homePage.selectDepartureDate(departureDate);
			Log.message("6.Successfully selected the departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("7.Passenger Info successfully specified");

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

	@Test(groups = { "desktop" }, description = "Flight Search DOM - RT with Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_002(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");

		String passengerClass = testData.get("passengerClass");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
						
			loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId , password);
			Log.message("2.Successfully Logged in Yatra account");	
				
			//step: Select Trip Type

			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'RoundTrip ' option in search Home Page ");
			
			//step:  select OneWay Flight Search fields
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo,passengerClass);
			Log.message("4.Successfully selected RoundTrip Flight Search Fields ");
			
			// step: click 'Search' button in Yatra Home page
			searchResult =	homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");							

			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'RoundTrip' option in search Home Page ");


			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("4.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("5.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("6.Successfully selected the departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			String returndate = homePage.selectReturnDate(returnDate);
			Log.message("7.Successfully selected the departure date: <b>" + returndate + "</b>(YY/MM/DD)");

			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("8.Passenger Info successfully specified");

			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("9.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("10.Successfully clicked 'Search' button in Yatra Homepage ");

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
	
	
	
	@Test(groups = {"desktop" }, description = "Airline Matrix Strip verification on SRP for DOM flight", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_FlightSearch_011(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		// String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		// String departureDate = testData.get("DepartureDate");
		// String returnDate = testData.get("ReturnDate");
		String passangerInfo = testData.get("PassengerInfo");
		
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1.Navigated to 'Yatra' Home Page!");

			// step2: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {

				Log.message("2.Verified Yatra Title text");
			}

			LoginPage loginPage = new LoginPage(driver);
			Log.message("3. Navigated to 'Yatra' Login Page!");

			// click Login button in HomePage
			loginPage.clickBtnSignIn();

			// click Login button in HomePage
			loginPage.clickBtnSignIn();

			// step: enter Origin place in Yatra Home page
			loginPage.enterEmailID(emailId);
			Log.message("4.Successfully entered '"+ emailId+"' Id in Emai Textbox ");

			// step: enter Destination place in Yatra Home page
			loginPage.enterPassword(password);
			Log.message("5.Successfully entered '"+ password +"' Password in Password textbox " );
			Thread.sleep(3000);

			// step: click 'Search' button in Yatra Home page
			loginPage.clickBtnSignIn();

			Log.message("6.Successfully clicked 'SignIn' ");
			Log.message("Successfully Logged in Yatra acct");

			Log.message("6.Successfully clicked 'SignIn' ");
			Log.message("Successfully Logged in Yatra acct");

			homePage.selectRoundTrip();
			Log.message("7.Successfully clicked 'Round Trip' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("8.Successfully entered Origin '<b>"+ origin +"</b>' in Yatra Homepage" );

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '<b>"+ destination+"</b>' in Yatra Homepage" );
			Thread.sleep(3000);	
			
			homePage.clickDeptDatePicker();		
			homePage.selectDeptDateAfterOneWeek();
			Log.message("10. selected Depart Date");	
			
			homePage.clickReturnDatePicker();
			homePage.selectReturnDateAfterTwoWeek();

			Log.message("11. selected Return Date");
			Thread.sleep(20000);

			Log.message("11. selected Return Date");
			Thread.sleep(10000);

			homePage.specifyPassengerInfo(passangerInfo);
			Log.message("12. Select Choice Of booking Seat");	

			// step5: click 'Search' button in Yatra Home page
			searchResult =	homePage.clickBtnSearch();
			Thread.sleep(3000);
			Log.message("13.Successfully clicked 'Search' in Yatra Homepage ");
			
			Log.message("<b>Expected Result:</b> Successfully searched Flights");						
			Log.testCaseResult();
			Thread.sleep(10000);
			BrowserActions.nap(10);
			searchResult.clickAirlineMatrix();
			Log.message("Successfully clicked Airline Matrix");
			Thread.sleep(3000);
		} catch (Exception e) {
			Log.exception(e);
			
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}		
	
	@Test(groups = {"desktop" }, description = "Airline Matrix Strip verification on SRP for INT flight", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_FlightSearch_012(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String passangerInfo = testData.get("PassengerInfo");
		
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1.Navigated to 'Yatra' Home Page!");
			
			// step2: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}			

		    LoginPage loginPage = new LoginPage(driver);
			Log.message("3. Navigated to 'Yatra' Login Page!");
			
			//click Login button in HomePage
			loginPage.clickBtnSignIn();

			// step: enter Origin place in Yatra Home page
			loginPage.enterEmailID(emailId);
			Log.message("4.Successfully entered '"+ emailId+"' Id in Emai Textbox ");

			// step: enter Destination place in Yatra Home page
			loginPage.enterPassword(password);
			Log.message("5.Successfully entered '"+ password +"' Password in Password textbox " );
			Thread.sleep(3000);

			// step: click 'Search' button in Yatra Home page
			loginPage.clickBtnSignIn();
			Log.message("6.Successfully clicked 'SignIn' ");			
			Log.message("Successfully Logged in Yatra acct");			
			
			homePage.selectRoundTrip();
			Log.message("7.Successfully clicked 'Round Trip' option in search Home Page ");
			
			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("8.Successfully entered Origin '<b>"+ origin +"</b>' in Yatra Homepage" );

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '<b>"+ destination+"</b>' in Yatra Homepage" );
			Thread.sleep(3000);	
			
			homePage.clickDeptDatePicker();		
			homePage.selectDeptDateAfterOneWeek();
			Log.message("10. selected Depart Date");	
			
			homePage.clickReturnDatePicker();
			homePage.selectReturnDateAfterTwoWeek();
			Log.message("11. selected Return Date");	
			Thread.sleep(10000);	
			
			homePage.specifyPassengerInfo(passangerInfo);
			Log.message("12. Select Choice Of booking Seat");	

			// step5: click 'Search' button in Yatra Home page
			searchResult =	homePage.clickBtnSearch();
			Thread.sleep(3000);
			Log.message("13.Successfully clicked 'Search' in Yatra Homepage ");
			
			Log.message("<b>Expected Result:</b> Successfully searched Flights");						
			Log.testCaseResult();
			BrowserActions.nap(2);
			searchResult.clickAirlineMatrix();
			Log.message("Successfully clicked Airline Matrix");
			Thread.sleep(3000);
		} catch (Exception e) {
			Log.exception(e);
			
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}	
	
	@Test(groups = { "desktop" }, description = "Weekly Strip verification on SRP for DOM flight - OW", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_FlightSearch_014(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");

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

			//step: Navigate to Yatra Login
		    LoginPage loginPage = new LoginPage(driver);
			Log.message("3. Navigated to 'Yatra' Login Page!");
			
			//click Login button in HomePage
			loginPage.clickBtnSignIn();

			// step: enter EmailId in Yatra Home page
			loginPage.enterEmailID(emailId);
			Log.message("4.Successfully entered '<b>"+ emailId+"</b>' Id in Emai Textbox ");

			// step: enter password in Yatra Home page
			loginPage.enterPassword(password);
			Log.message("5.Successfully entered '<b>"+ password +"</b>' Password in Password textbox " );
			Thread.sleep(3000);

			// step: click 'Search' button in Yatra Home page
			loginPage.clickBtnSignIn();
			Log.message("6.Successfully clicked 'SignIn' ");			
			Log.message("Successfully Logged in Yatra acct");			
			
			//Step: Selected trip as one way trip.
			homePage.selectOneWayTrip();
			Log.message("7.Successfully clicked 'One Way ' option in search Home Page ");
			
			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("8.Successfully entered Origin '<b>"+ origin +"</b>' in Yatra Homepage" );

			//step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '<b>"+ destination+"</b>' in Yatra Homepage" );
					
			//step: enter Destination place in Yatra Home page
			
			homePage.clickDeptDatePicker();		
			homePage.selectDeptDateAfterOneWeek();
			Log.message("10. Successfully selected the departure date: <b>"+ departureDate+"</b>(YY/MM/DD)");
			
			//step: enter Passenger info in Yatra Home page
            homePage.specifyPassengerInfo(passengerInfo);
			Log.message("11. Passenger Info successfully specified");
			
			Thread.sleep(5000);			
			// step: click 'Search' button in Yatra Home page
		    SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("12.Successfully clicked 'Search' in Yatra Homepage ");
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> The Weekly strip should be displayed on the SRP page for domestic flights.");
			Thread.sleep(5000);			

			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("weeklyStrip"), searchResult),
					"<b>Actual Result:</b> The Weekly strip displayed on the SRP page for domestic flights.",
					"<b>Actual Result:</b> The Weekly strip not displayed on the SRP page for domestic flights.",driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = {"desktop"}, description = "Preferred Airline search Domestic-One Way", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_007(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1.Navigated to 'Yatra' Home Page!");

			// step2: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}			

		    LoginPage loginPage = new LoginPage(driver);
			Log.message("3. Navigated to 'Yatra' Login Page!");
			
			//click Login button in HomePage
			loginPage.clickBtnSignIn();

			// step: enter Origin place in Yatra Home page
			loginPage.enterEmailID(emailId);
			Log.message("4.Successfully entered '"+ emailId+"' Id in Emai Textbox ");

			// step: enter Destination place in Yatra Home page
			loginPage.enterPassword(password);
			Log.message("5.Successfully entered '"+ password +"' Password in Password textbox " );
			Thread.sleep(3000);

			// step: click 'Search' button in Yatra Home page
			loginPage.clickBtnSignIn();
			Log.message("6.Successfully clicked 'SignIn' ");			
			Log.message("Successfully Logged in Yatra acct");			
			
			homePage.selectOneWayTrip();
			Log.message("7.Successfully clicked 'One Way ' option in search Home Page ");
			
			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("8.Successfully entered Origin '<b>"+ origin +"</b>' in Yatra Homepage" );

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '<b>"+ destination+"</b>' in Yatra Homepage" );
			Thread.sleep(3000);	
			
			//step: enter depart date
			homePage.selectDepartureDate(departureDate);
			Log.message("10. selected Depart Date");			
			Thread.sleep(5000);	
			
			// step5: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("11.Successfully clicked 'Search' in Yatra Homepage ");
			
			String name = searchResult.preferredFlightFirst();
			Log.message("12. Selected Prefered Flight is : " + name);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = {"desktop"}, description = "Preferred Airline search Domestic-Round Trip", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_008(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String passangerInfo = testData.get("PassengerInfo");
		
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1.Navigated to 'Yatra' Home Page!");
			
			// step2: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}			

		    LoginPage loginPage = new LoginPage(driver);
			Log.message("3. Navigated to 'Yatra' Login Page!");
			
			//click Login button in HomePage
			loginPage.clickBtnSignIn();

			// step: enter Origin place in Yatra Home page
			loginPage.enterEmailID(emailId);
			Log.message("4.Successfully entered '"+ emailId+"' Id in Emai Textbox ");

			// step: enter Destination place in Yatra Home page
			loginPage.enterPassword(password);
			Log.message("5.Successfully entered '"+ password +"' Password in Password textbox " );
			Thread.sleep(3000);

			// step: click 'Search' button in Yatra Home page
			loginPage.clickBtnSignIn();
			Log.message("6.Successfully clicked 'SignIn' ");			
			Log.message("Successfully Logged in Yatra acct");			
			
			homePage.selectRoundTrip();
			Log.message("7.Successfully clicked 'Round Trip' option in search Home Page ");
			
			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("8.Successfully entered Origin '<b>"+ origin +"</b>' in Yatra Homepage" );

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '<b>"+ destination+"</b>' in Yatra Homepage" );
			Thread.sleep(3000);	
			
			homePage.clickDeptDatePicker();		
			homePage.selectDeptDateAfterOneWeek();
			Log.message("10. selected Depart Date");	
			
			homePage.clickReturnDatePicker();
			homePage.selectReturnDateAfterTwoWeek();
			Log.message("11. selected Return Date");	
			Thread.sleep(5000);	
			
			homePage.specifyPassengerInfo(passangerInfo);
			Log.message("12. Select Choice Of booking Seat");	

			// step5: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("13.Successfully clicked 'Search' in Yatra Homepage");
			
			String name = searchResult.preferredFlightFirst();
			Log.message("14. Selected Prefered Flight is : " + name);
			
			
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = {"desktop"}, description = "Preferred Airline search International-One Way", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_009(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1.Navigated to 'Yatra' Home Page!");

			// step2: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}			

		    LoginPage loginPage = new LoginPage(driver);
			Log.message("3. Navigated to 'Yatra' Login Page!");
			
			//click Login button in HomePage
			loginPage.clickBtnSignIn();

			// step: enter Origin place in Yatra Home page
			loginPage.enterEmailID(emailId);
			Log.message("4.Successfully entered '"+ emailId+"' Id in Emai Textbox ");

			// step: enter Destination place in Yatra Home page
			loginPage.enterPassword(password);
			Log.message("5.Successfully entered '"+ password +"' Password in Password textbox " );
			Thread.sleep(3000);

			// step: click 'Search' button in Yatra Home page
			loginPage.clickBtnSignIn();
			Log.message("6.Successfully clicked 'SignIn' ");			
			Log.message("Successfully Logged in Yatra acct");			
			
			homePage.selectOneWayTrip();
			Log.message("7.Successfully clicked 'One Way ' option in search Home Page ");
			
			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("8.Successfully entered Origin '<b>"+ origin +"</b>' in Yatra Homepage" );

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '<b>"+ destination+"</b>' in Yatra Homepage" );
			Thread.sleep(3000);	
			
			//step: enter depart date
			homePage.selectDepartureDate(departureDate);
			Log.message("10. selected Depart Date");			
			Thread.sleep(5000);	
			
			// step5: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("11.Successfully clicked 'Search' in Yatra Homepage ");
			
			String name = searchResult.preferredFlightFirst();
			Log.message("12. Selected Prefered Flight is : " + name);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = {"desktop"}, description = "Preferred Airline search International-Round Trip", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_010(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String passangerInfo = testData.get("PassengerInfo");
		
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1.Navigated to 'Yatra' Home Page!");
			
			// step2: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}			

		    LoginPage loginPage = new LoginPage(driver);
			Log.message("3. Navigated to 'Yatra' Login Page!");
			
			//click Login button in HomePage
			loginPage.clickBtnSignIn();

			// step: enter Origin place in Yatra Home page
			loginPage.enterEmailID(emailId);
			Log.message("4.Successfully entered '"+ emailId+"' Id in Emai Textbox ");

			// step: enter Destination place in Yatra Home page
			loginPage.enterPassword(password);
			Log.message("5.Successfully entered '"+ password +"' Password in Password textbox " );
			Thread.sleep(3000);

			// step: click 'Search' button in Yatra Home page
			loginPage.clickBtnSignIn();
			Log.message("6.Successfully clicked 'SignIn' ");			
			Log.message("Successfully Logged in Yatra acct");			
			
			homePage.selectRoundTrip();
			Log.message("7.Successfully clicked 'Round Trip' option in search Home Page ");
			
			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("8.Successfully entered Origin '<b>"+ origin +"</b>' in Yatra Homepage" );

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '<b>"+ destination+"</b>' in Yatra Homepage" );
			Thread.sleep(3000);	
			
			homePage.clickDeptDatePicker();		
			homePage.selectDeptDateAfterOneWeek();
			Log.message("10. selected Depart Date");	
			
			homePage.clickReturnDatePicker();
			homePage.selectReturnDateAfterTwoWeek();
			Log.message("11. selected Return Date");	
			Thread.sleep(5000);	
			
			homePage.specifyPassengerInfo(passangerInfo);
			Log.message("12. Select Choice Of booking Seat");	

			// step5: click 'Search' button in Yatra Home page
			SearchResult searchResult  =homePage.clickBtnSearch();
			Log.message("13. Successfully clicked 'Search' in Yatra Homepage ");
			
			String name = searchResult.preferredFlightFirst();
			Log.message("14. Selected Prefered Flight is : " + name);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
			
		} finally {
			driver.quit();
			Log.endTestCase();
		}

	}


	@Test(groups = {
			"desktop" }, description = "Flight details link verification on SRP-DOM", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_017(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1.Navigated to 'Yatra' Home Page!");

			// step2: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}

			LoginPage loginPage = new LoginPage(driver);
			Log.message("3. Navigated to 'Yatra' Login Page!");

			// click Login button in HomePage
			loginPage.clickBtnSignIn();

			// step: enter Origin place in Yatra Home page
			loginPage.enterEmailID(emailId);
			Log.message("4.Successfully entered '" + emailId + "' Id in Emai Textbox ");

			// step: enter Destination place in Yatra Home page
			loginPage.enterPassword(password);
			Log.message("5.Successfully entered '" + password + "' Password in Password textbox ");
			Thread.sleep(3000);

			// step: click 'Search' button in Yatra Home page
			loginPage.clickBtnSignIn();
			Log.message("6.Successfully clicked 'SignIn' ");
			Log.message("Successfully Logged in Yatra acct");

			homePage.selectRoundTrip();
			Log.message("7.Successfully clicked 'Round Trip' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("8.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");
			Thread.sleep(3000);

			homePage.clickDeptDatePicker();
			homePage.selectDeptDateAfterOneWeek();
			Log.message("10. selected Depart Date");

			homePage.clickReturnDatePicker();
			homePage.selectReturnDateAfterTwoWeek();
			Log.message("11. selected Return Date");
			Thread.sleep(5000);

			// step5: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("12. Successfully clicked 'Search' in Yatra Homepage ");
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Flight Detail Link Should be Properly visible to the User!");
			Thread.sleep(5000);
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(Arrays.asList("lnkFlightDetails_DOM"), searchResult),
					"<b>Actual Result:</b> Flight Detail Link is visible to the User",
					"<b>Actual Result:</b> Flight Detail Link is visible to the User", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);

		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Flight details link verification on SRP-INTL", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_018(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1.Navigated to 'Yatra' Home Page!");

			// step2: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}

			LoginPage loginPage = new LoginPage(driver);
			Log.message("3. Navigated to 'Yatra' Login Page!");

			// click Login button in HomePage
			loginPage.clickBtnSignIn();

			// step: enter Origin place in Yatra Home page
			loginPage.enterEmailID(emailId);
			Log.message("4.Successfully entered '" + emailId + "' Id in Emai Textbox ");

			// step: enter Destination place in Yatra Home page
			loginPage.enterPassword(password);
			Log.message("5.Successfully entered '" + password + "' Password in Password textbox ");
			Thread.sleep(3000);

			// step: click 'Search' button in Yatra Home page
			loginPage.clickBtnSignIn();
			Log.message("6.Successfully clicked 'SignIn' ");
			Log.message("Successfully Logged in Yatra acct");

			homePage.selectRoundTrip();
			Log.message("7.Successfully clicked 'Round Trip' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("8.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");
			Thread.sleep(3000);

			homePage.clickDeptDatePicker();
			homePage.selectDeptDateAfterOneWeek();
			Log.message("10. selected Depart Date");

			homePage.clickReturnDatePicker();
			homePage.selectReturnDateAfterTwoWeek();
			Log.message("11. selected Return Date");
			Thread.sleep(5000);

			// step5: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("12.Successfully clicked 'Search' in Yatra Homepage");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Flight Detail Link Should be Properly visible to the User!");
			Thread.sleep(5000);
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(Arrays.asList("lnkFlightDetails_INTL"), searchResult),
					"<b>Actual Result:</b> Flight Detail Link is visible to the User",
					"<b>Actual Result:</b> Flight Detail Link is visible to the User", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);

		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Add Meal on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_031(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
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
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			LoginPage loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2.Successfully Logged in Yatra account!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Flight Search fields
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4.Successfully filled the search details for 'ONE WAY' trip!");

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage!");
			
			ReviewPage reviewPage =  searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked On Book Now Button!");

			reviewPage.clickOnContinue();
			Log.message("7. Clicked On Continue Button on Review Page!");
			
			reviewPage.selectMeal();
			Log.message("8. Selected Meal!");
			
			
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = {
	"desktop" }, description = "Remove Meal on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
public void TC_Yatra_Flight_032(String browser) throws Exception {

HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
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
	// step1: Navigate to Yatra Home Page
	homePage = new HomePage(driver, webSite).get();
	Log.message("1. Navigated to 'Yatra' Home Page!");

	LoginPage loginPage = homePage.navigateToSignIn();
	loginPage.loginYatraAccount(emailId, password);
	Log.message("2.Successfully Logged in Yatra account!");

	// step: Select Trip Type
	homePage.selectTripType(tripType);
	Log.message("3.Successfully clicked 'One Way' option in search Home Page!");

	// step: select OneWay Flight Search fields
	homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
	Log.message("4.Successfully filled the search details for 'ONE WAY' trip!");

	// step: click 'Search' button in Yatra Home page
	SearchResult searchResult = homePage.clickBtnSearch();
	Log.message("5.Successfully clicked 'Search' in Yatra Homepage!");
	
	ReviewPage reviewPage =  searchResult.clickOnBookNowInOneWay(1);
	Log.message("6. Clicked On Book Now Button!");

	reviewPage.clickOnContinue();
	Log.message("7. Clicked On Continue Button on Review Page!");
	
	reviewPage.enterUserDeatils();
	Log.message("8. Enter User Details!");
	
	reviewPage.clickOnAddMeal();
	Log.message("9. Clicked On Add Baggage!");
	
	reviewPage.selectMeal();
	Log.message("10. Selected Meal!");
	
	
	Log.testCaseResult();
} catch (Exception e) {
	Log.exception(e);
} finally {
	//driver.quit();
	Log.endTestCase();
}
}
	@Test(groups = {
	"desktop" }, description = "Add Baggage on pax page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
public void TC_Yatra_Flight_034(String browser) throws Exception {

HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
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
	// step1: Navigate to Yatra Home Page
	homePage = new HomePage(driver, webSite).get();
	Log.message("1. Navigated to 'Yatra' Home Page!");

	LoginPage loginPage = homePage.navigateToSignIn();
	loginPage.loginYatraAccount(emailId, password);
	Log.message("2.Successfully Logged in Yatra account!");

	// step: Select Trip Type
	homePage.selectTripType(tripType);
	Log.message("3.Successfully clicked 'One Way' option in search Home Page!");

	// step: select OneWay Flight Search fields
	homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
	Log.message("4.Successfully filled the search details for 'ONE WAY' trip!");

	// step: click 'Search' button in Yatra Home page
	SearchResult searchResult = homePage.clickBtnSearch();
	Log.message("5.Successfully clicked 'Search' in Yatra Homepage!");
	
	ReviewPage reviewPage =  searchResult.clickOnBookNowInOneWay(1);
	Log.message("6. Clicked On Book Now Button!");

	reviewPage.clickOnContinue();
	Log.message("7. Clicked On Continue Button on Review Page!");
	
	reviewPage.enterUserDeatils();
	Log.message("8. Enter User Details!");
	
	reviewPage.clickOnAddBaggage();
	Log.message("9. Clicked On Add Baggage!");
	
	reviewPage.selectBaggage();
	Log.message("10. Selected Baggage Type!");
	
	
	Log.testCaseResult();
} catch (Exception e) {
	Log.exception(e);
} finally {
	//driver.quit();
	Log.endTestCase();
}
}

	@Test(groups = { "desktop" }, description = "Flight Search INTL- OW with Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_003(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
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
			// step1: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			LoginPage loginPage = homePage.navigateToSignIn();
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

			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("6.Successfully selected the departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("7.Passenger Info successfully specified");

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

	@Test(groups = { "desktop" }, description = "Flight Search INTL- RT with Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_004(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
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

			LoginPage loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2.Successfully Logged in Yatra account");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'RoundTrip' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("4.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("5.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("6.Successfully selected the departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			String returndate = homePage.selectReturnDate(returnDate);
			Log.message("7.Successfully selected the departure date: <b>" + returndate + "</b>(YY/MM/DD)");

			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("8.Passenger Info successfully specified");

			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("9.Successfully selected Passenger class and clicked Done button");
			
			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("10.Successfully clicked 'Search' button in Yatra Homepage ");

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

	@Test(groups = { "desktop" }, description = "Flight Search DOM-Multicity", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_005(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
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

			/*LoginPage loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2.Successfully Logged in Yatra account");*/

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'Multicity' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterMultiCityOrigin1(origin1);
			Log.message("4.Successfully entered Multicity Origin1 '<b>" + origin1 + "</b>' in Yatra Homepage");
			
			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination1(destination1);
			Log.message("5.Successfully entered Multicity Destination1 '<b>" + destination1 + "</b>' in Yatra Homepage");
			
			String departDate = homePage.selectMultiCityDepartureDate1(departureDate);
			Log.message("6.Successfully selected the Multicity Departure1 date: <b>" + departDate + "</b>(YY/MM/DD)");
			
			homePage.enterMultiCityOrigin2(origin2);
			Log.message("4.Successfully entered Multicity Origin2 '<b>" + origin2 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination2(destination2);
			Log.message("5.Successfully entered Multicity Destination1 '<b>" + destination2 + "</b>' in Yatra Homepage");

			String returndate = homePage.selectMultiCityDepartureDate2(returnDate);
			Log.message("7.Successfully selected the Multicity Departure2 date: <b>" + returndate + "</b>(YY/MM/DD)");

			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("8.Passenger Info successfully specified");

			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("9.Successfully selected Passenger class and clicked Done button");
			
			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("10.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>"); 
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page with DOM-Multicity flight result");
			Thread.sleep(6000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page with DOM-Multicity flight resulte",
					"<b>Actual Result:</b> User should navigated on SearchResult page with DOM-Multicity flight result", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Flight Search INTL-Multicity", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_006(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
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

			/*LoginPage loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2.Successfully Logged in Yatra account");*/

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'Multicity' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterMultiCityOrigin1(origin1);
			Log.message("4.Successfully entered Multicity Origin1 '<b>" + origin1 + "</b>' in Yatra Homepage");
			
			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination1(destination1);
			Log.message("5.Successfully entered Multicity Destination1 '<b>" + destination1 + "</b>' in Yatra Homepage");
			
			String departDate = homePage.selectMultiCityDepartureDate1(departureDate);
			Log.message("6.Successfully selected the Multicity Departure1 date: <b>" + departDate + "</b>(YY/MM/DD)");
			
			homePage.enterMultiCityOrigin2(origin2);
			Log.message("4.Successfully entered Multicity Origin2 '<b>" + origin2 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination2(destination2);
			Log.message("5.Successfully entered Multicity Destination1 '<b>" + destination2 + "</b>' in Yatra Homepage");

			String returndate = homePage.selectMultiCityDepartureDate2(returnDate);
			Log.message("7.Successfully selected the Multicity Departure1 date: <b>" + returndate + "</b>(YY/MM/DD)");

			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("8.Passenger Info successfully specified");

			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("9.Successfully selected Passenger class and clicked Done button");
			
			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("10.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>"); 
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page with INTL-Multicity flight result");
			Thread.sleep(6000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> ser should navigated on SearchResult page with INTL-Multicity flight result",
					"<b>Actual Result:</b> ser should navigated on SearchResult page with INTL-Multicity flight result", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
}
