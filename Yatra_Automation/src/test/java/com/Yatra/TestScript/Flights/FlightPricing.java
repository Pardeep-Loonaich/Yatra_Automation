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

import com.Yatra.Pages.HomePage;
import com.Yatra.Pages.LoginPage;
import com.Yatra.Pages.SearchResult;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.TestDataExtractor;
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class FlightPricing {

	EnvironmentPropertiesReader environmentPropertiesReader;
	String webSite;
	private String workbookName = "testdata\\data\\Flights.xls";
	private String sheetName = "FlightPricing";
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@SuppressWarnings("unused")
	@Test(groups = { "desktop" }, description = "Verify Flight Price", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_FlightPricing_001(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String email = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// TODO : Steps

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "Check to price calculation for DOM flight-one way", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_FlightPricing_015a(String browser) throws Exception {

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
			Log.message("11. Passenger Info successfully specified as class as 'Economic' class.");
			
			Thread.sleep(5000);			
			// step: click 'Search' button in Yatra Home page
		    SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("12.Successfully clicked 'Search' in Yatra Homepage ");
			
			searchResult.clickOnBookNow();
			Log.message("13.Clicked on 'Book Now' button in Search Result Page ");
			
						
		  
			

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Check to price calculation for DOM flight-round trip", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_FlightPricing_015b(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
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
			homePage.selectRoundTrip();
			Log.message("7.Successfully clicked 'Round trip ' option in search Home Page ");
			
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
			
			homePage.clickReturnDatePicker();
			homePage.selectReturnDateAfterTwoWeek();
			Log.message("11. Successfully selected the return date: <b>"+ returnDate +"</b>(YY/MM/DD)");

			//step: enter Passenger info in Yatra Home page
            homePage.specifyPassengerInfo(passengerInfo);
			Log.message("12. Passenger Info successfully specified as class selected as 'Business' class");
			
			Thread.sleep(5000);			
			// step: click 'Search' button in Yatra Home page
		    SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("13.Successfully clicked 'Search' in Yatra Homepage ");
			
	
		  
			

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
}
