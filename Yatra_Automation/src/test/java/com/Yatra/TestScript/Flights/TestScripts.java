package com.Yatra.TestScript.Flights;

import java.util.Arrays;
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
public class TestScripts {

	EnvironmentPropertiesReader environmentPropertiesReader;
	String webSite;
	private String workbookName = "testdata\\data\\Flights.xls";
	private String sheetName = "Flights";
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@SuppressWarnings("unused")
	@Test(groups = {"desktop" }, description = "To verify the Yatra Home Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_001(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step2: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}
			
			Log.message("<b>Expected Result:</b> Successfully verified Yatra HomePage");

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	
	@Test(groups = {"desktop" }, description = "Searching Fligts for One Way-Domestic", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_002(String browser) throws Exception {

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
			Log.message("4.Successfully entered '<b>"+ emailId+"</b>' Id in Emai Textbox ");

			// step: enter Destination place in Yatra Home page
			loginPage.enterPassword(password);
			Log.message("5.Successfully entered '<b>"+ password +"</b>' Password in Password textbox " );
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
						
			homePage.selectDepartureDate(departureDate);
			Log.message("10. Successfully selected the departure date: <b>"+ departureDate+"</b>(YY/MM/DD)");
					
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("11. Passenger Info successfully specified");
			
			Thread.sleep(5000);			
			// step: click 'Search' button in Yatra Home page
			homePage.clickBtnSearch();
			Log.message("12.Successfully clicked 'Search' in Yatra Homepage ");
			Log.message("<b>Expected Result:</b> Successfully searched Flights");						

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = {"desktop" }, description = "Searching Fligts for Round Trip-Domestic", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_003(String browser) throws Exception {

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
			Log.message("4.Successfully entered '<b>"+ emailId+"</b>' Id in Emai Textbox ");

			// step: enter Destination place in Yatra Home page
			loginPage.enterPassword(password);
			Log.message("5.Successfully entered '<b>"+ password +"</b>' Password in Password textbox " );
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
						
			homePage.selectDepartureDate(departureDate);
			Log.message("10. Successfully selected the departure date: <b>"+ departureDate+"</b>(YY/MM/DD)");
				
			homePage.selectReturnDate(returnDate);
			Log.message("11. Successfully selected the departure date: <b>"+ returnDate+"</b>(YY/MM/DD)");
					
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("12. Passenger Info successfully specified");
			
			Thread.sleep(5000);			
			// step5: click 'Search' button in Yatra Home page
			homePage.clickBtnSearch();
			Log.message("12.Successfully clicked 'Search' in Yatra Homepage ");
			Log.message("<b>Expected Result:</b> Successfully searched Flights");						

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}	
	@Test(groups = {"desktop" }, description = "Searching Fligts for One Way-International", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_004(String browser) throws Exception {

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
			homePage.clickBtnSearch();
			Log.message("11.Successfully clicked 'Search' in Yatra Homepage ");
			Log.message("<b>Expected Result:</b> Successfully searched Flights");						

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = {"desktop" }, description = "Searching Fligts for Round Trip-International", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_005(String browser) throws Exception {

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
			// step5: click 'Search' button in Yatra Home page
			homePage.clickBtnSearch();
			Log.message("12.Successfully clicked 'Search' in Yatra Homepage ");
			Log.message("<b>Expected Result:</b> Successfully searched Flights");						

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}	
	@Test(groups = {"desktop" }, description = "Searching Fligts for Round Trip-International with Chioce seat Booking", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_006(String browser) throws Exception {

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
			homePage.clickBtnSearch();
			Log.message("13.Successfully clicked 'Search' in Yatra Homepage ");
			
			Log.message("<b>Expected Result:</b> Successfully searched Flights");						

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
			
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}	
	@Test(groups = {"desktop" }, description = "Searching Fligts for Round Trip-Domestic with Chioce seat Booking", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_007(String browser) throws Exception {

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
			homePage.clickBtnSearch();
			Log.message("13.Successfully clicked 'Search' in Yatra Homepage ");
			
			Log.message("<b>Expected Result:</b> Successfully searched Flights");						

			Log.testCaseResult();
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
}
