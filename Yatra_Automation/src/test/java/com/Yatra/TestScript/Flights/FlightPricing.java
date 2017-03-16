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
import com.Yatra.Pages.ReviewPage;
import com.Yatra.Pages.SearchResult;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.TestDataExtractor;
import com.Yatra.Utils.Utils;
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
	public void TC_FlightPricing_015(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("passengerClass");

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
		    loginPage.loginYatraAccount(emailId, password);
		    Log.message("4. Successfully login after entering the valid credentials.");
			
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo,passengerClass);		
			Log.message("5.Successfully filled the search details for 'ONE WAY' trip.");			

			
			// step: click 'Search' button in Yatra Home page
		    SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("7.Successfully clicked 'Search' in Yatra Homepage ");
			
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("BtnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.",driver);
		
			
			ReviewPage reviewPage = searchResult.clickOnBookNowInOneWay(10);
			Log.message("8.Clicked on 'Book Now' button in Search Result Page ");
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check to price calculation for DOM flight-one way.");
			Thread.sleep(5000);			

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("moduleFareDetails"), reviewPage),
					"<b>Actual Result:</b> The Fare details module is displayed on Review Page.",
					"<b>Actual Result:</b> The Fare details module is not displayed on Review Page.",driver);
		
			

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Check to price calculation for DOM flight-round trip", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_FlightPricing_016(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("passengerClass");

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
			Log.message("3.Navigated to 'Yatra' Login Page!");
			
			//click Login button in HomePage
			loginPage.clickBtnSignIn();

			// step: enter EmailId in Yatra Home page
		    loginPage.loginYatraAccount(emailId, password);
		    Log.message("4.Successfully login after entering the valid credentials.");
            Thread.sleep(1000);
		 
			//step: enter search details in Yatra Home page
		   homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo,passengerClass);
			Log.message("5.Successfully filled the search details for 'ROUND' trip.");			

			// step: click 'Search' button in Yatra Home page
		    SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("6.Clicked on 'Search' in Yatra Homepage.");
			
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("BtnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.",driver);
		
			
			ReviewPage reviewPage = searchResult.clickOnBookNowInRound(1,2,2,7);
			Log.message("7.Clicked on 'Book Now' button in Search Result Page.");
			
			reviewPage.clickOnFeeSurchrgeLink();
			Log.message("8.Clicked on 'Fees & Surcharge' details link in Review Page.");

			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Fees & Surcharge details as Signed User should be displayed after clicking on Fees & Surcharge Link in Fare Details module.");
               
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("moduleFeeSurchrge"), reviewPage),
					"<b>Actual Result:</b> The Fare details module is displayed on Review Page.",
					"<b>Actual Result:</b> The Fare details module is not displayed on Review Page.",driver);
		
		  
			reviewPage.clickOnFareRulesLink();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "Check to price calculation for DOM flight-multicity", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_FlightPricing_017(String browser) throws Exception {

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
		    loginPage.loginYatraAccount(emailId, password);
		    Log.message("4. Successfully login after entering the valid credentials.");
			
		   // homePage.selectTripType(tripType);
			Log.message("5.Successfully filled the search details for 'ONE WAY' trip.");			

			
			Thread.sleep(5000);			
			// step: click 'Search' button in Yatra Home page
		    SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("7.Successfully clicked 'Search' in Yatra Homepage ");
			
			ReviewPage reviewPage = searchResult.clickOnBookNowInOneWay(5);
			Log.message("8.Clicked on 'Book Now' button in Search Result Page ");
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check to price calculation for DOM flight-multicity.");
			Thread.sleep(5000);			

		
	
		  
			

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
}
