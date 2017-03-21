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
import com.Yatra.Pages.ReviewPage;
import com.Yatra.Pages.SearchResult;
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
	String webSite;
	//private String workbookName = "testdata\\data\\Flights.xls";
	//private String sheetName = "FlightSearch";
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(groups = { "desktop" }, description = "Flight Search DOM - OW with  Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_001(HashMap<String, String> testData) throws Exception {
		
		String browser=testData.get("browser");
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

			/// step: Select Trip Type
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
			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
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
	public void TC_Yatra_Flight_002(HashMap <String,String> testData) throws Exception {	
		
		String browser=testData.get("browser");
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

			loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2.Successfully Logged in Yatra account");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'RoundTrip ' option in search Home Page ");

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

	@Test(groups = { "desktop" }, description = "Flight Search DOM-Multicity", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_005(HashMap <String,String> testData) throws Exception {

		String browser=testData.get("browser");
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

			LoginPage loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2.Successfully Logged in Yatra account");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'Multicity' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterMultiCityOrigin1(origin1);
			Log.message("4.Successfully entered Multicity Origin1 '<b>" + origin1 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination1(destination1);
			Log.message("5.Successfully entered Multicity Destination1 '<b>" + destination1 + "</b>' in Yatra Homepage");

			String departDate = homePage.selectMultiCityDateDeparture1(departureDate);
			Log.message("6.Successfully selected the Multicity Departure1 date: <b>" + departDate + "</b>(YY/MM/DD)");

			homePage.enterMultiCityOrigin2(origin2);
			Log.message("7.Successfully entered Multicity Origin2 '<b>" + origin2 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination2(destination2);
			Log.message("8.Successfully entered Multicity Destination1 '<b>" + destination2 + "</b>' in Yatra Homepage");

			String returndate = homePage.selectMultiCityDateDeparture2(returnDate);
			Log.message("9.Successfully selected the Multicity Departure2 date: <b>" + returndate + "</b>(YY/MM/DD)");

			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("10.Passenger Info successfully specified");

			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("11.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("12.Successfully clicked 'Search' button in Yatra Homepage ");

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
	public void TC_Yatra_Flight_006(HashMap <String,String> testData) throws Exception {

		String browser=testData.get("browser");
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

			LoginPage loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2.Successfully Logged in Yatra account");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'Multicity' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterMultiCityOrigin1(origin1);
			Log.message("4.Successfully entered Multicity Origin1 '<b>" + origin1 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination1(destination1);
			Log.message("5.Successfully entered Multicity Destination1 '<b>" + destination1 + "</b>' in Yatra Homepage");

			String departDate = homePage.selectMultiCityDateDeparture1(departureDate);
			Log.message("6.Successfully selected the Multicity Departure1 date: <b>" + departDate + "</b>(YY/MM/DD)");

			homePage.enterMultiCityOrigin2(origin2);
			Log.message("7.Successfully entered Multicity Origin2 '<b>" + origin2 + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterMultiCityDestination2(destination2);
			Log.message("8.Successfully entered Multicity Destination1 '<b>" + destination2 + "</b>' in Yatra Homepage");

			String returndate = homePage.selectMultiCityDateDeparture2(returnDate);
			Log.message("9.Successfully selected the Multicity Departure1 date: <b>" + returndate + "</b>(YY/MM/DD)");

			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("10.Passenger Info successfully specified");

			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("11.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("12.Successfully clicked 'Search' button in Yatra Homepage ");

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
	public void TC_FlightSearch_011(HashMap <String,String> testData) throws Exception {

				
		String browser=testData.get("browser");
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
			// step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId , password);
			Log.message("2.Successfully Logged in Yatra account");	

			//step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'Two way' option in search Home Page ");

			//step:  select OneWay Flight Search fields
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);		
			Thread.sleep(5000);

			// step: click 'Search' button in Yatra Home page
			searchResult =	homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");	


			BrowserActions.nap(5);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Book as Guest button.");	
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("matrixStrip"), searchResult),
					"<b>Actual Result:</b> The Airline Matrix is displayed on Review Page.",
					"<b>Actual Result:</b> The Airline Matrix is not displayed on Review Page.",driver);  

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

	@Test(groups = {"desktop" }, description = "Airline Matrix Strip verification on SRP for INT flight", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_FlightSearch_012(HashMap<String, String> testData ) throws Exception {

		String browser=testData.get("browser");
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
			// step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId , password);
			Log.message("2.Successfully Logged in Yatra account");	

			//step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'Two way' option in search Home Page ");

			//step:  select OneWay Flight Search fields
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);		
			Thread.sleep(5000);

			// step: click 'Search' button in Yatra Home page
			searchResult =	homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");	


			BrowserActions.nap(5);
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Book as Guest button.");	
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("matrixStrip"), searchResult),
					"<b>Actual Result:</b> The Airline Matrix is displayed on Review Page.",
					"<b>Actual Result:</b> The Airline Matrix is not displayed on Review Page.",driver);  
			
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

	@Test(groups = { "desktop" }, description = "Weekly Strip verification on SRP for DOM flight - OW", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_FlightSearch_014(HashMap<String, String> testData) throws Exception {

		String browser=testData.get("browser");
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
				Log.message("2.Verified Yatra Title text");
			}			

			
			loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId , password);
			Log.message("3.Successfully Logged in Yatra account");	

			Thread.sleep(3000);		
            homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4.Successfully entered OneWay Flight Search Fields ");

			Thread.sleep(5000);			
			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");
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
	@Test(groups = {"desktop"}, description = "Preferred Airline search Domestic-One Way", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_007(HashMap<String, String> testData ) throws Exception {

		String browser=testData.get("browser");

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
	@Test(groups = {"desktop"}, description = "Preferred Airline search Domestic-Round Trip", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_008(HashMap<String, String> testData ) throws Exception {

		String browser=testData.get("browser");
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

	@Test(groups = {"desktop"}, description = "Preferred Airline search International-One Way", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_009(HashMap<String, String> testData) throws Exception {


		String browser=testData.get(" browser");
		
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
	@Test(groups = {"desktop"}, description = "Preferred Airline search International-Round Trip", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_010(HashMap<String, String> testData) throws Exception {


		String browser=testData.get("browser"); 
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
	"desktop" }, description = "Flight details link verification on SRP-DOM", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_017(HashMap<String, String> testData) throws Exception {

		String browser=testData.get("browser");
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
	"desktop" }, description = "Flight details link verification on SRP-INTL", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_018(HashMap<String, String> testData) throws Exception {

		String browser=testData.get("browser");
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
	"desktop" }, description = "Add Meal on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_031(HashMap<String, String> testData) throws Exception {

		String browser=testData.get("browser");
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
			Log.message("10. Selected Meal!");
			
			String mealCharges = reviewPage.getTextMealDetails();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to see the Meal Charges inculded in the Fare Detail!");
			Thread.sleep(5000);
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("mealDetails"), reviewPage),
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
	@Test(groups = {
	"desktop" }, description = "Remove Meal on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_032(HashMap<String, String> testData) throws Exception {

		String browser=testData.get("browser");
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

			String mealCharges = reviewPage.getTextMealDetails();
			
			Thread.sleep(3000);
			reviewPage.clickOnRemoveMealButton();
			Log.message("11. Clicked On Remove Button In Review Page!");
			String mealChargesAfterRemovingMeal = reviewPage.getTextMealDetails();
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to Remove the Meal Charges On Review Page!");
			Thread.sleep(5000);
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("mealDetails"), reviewPage),
					"<b>Actual Result:</b> Meal Charges : "+ mealCharges + "Meal Charges after Removing the Meal :" + mealChargesAfterRemovingMeal,
					"<b>Actual Result:</b> Meal Charges Can Not Be Removed From Review Page", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = {
	"desktop" }, description = "Add Baggage on pax page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_034(HashMap<String, String> testData) throws Exception {

		String browser=testData.get("browser");
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


			String BaggageFare = reviewPage.getTextBaggageDetails();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to see the Baggage Charges inculded in the Fare Detail!");
			Thread.sleep(5000);
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnAddBaggage"), reviewPage),
					"<b>Actual Result:</b> Baggage Charges are included In Total Fare and Baggage Charges is :" + BaggageFare,
					"<b>Actual Result:</b> Baggage Charges are not included In Total Fare", driver);


			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = {
	"desktop" }, description = "Remove Baggage on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_035(HashMap<String, String> testData) throws Exception {

		String browser=testData.get("browser");
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
			String BaggageFare = reviewPage.getTextBaggageDetails();
			
			Thread.sleep(3000);
			reviewPage.clickOnRemoveBaggageButton();
			Log.message("11. Clicked On Remove Baggage Button!");
			String BaggageFareAfterRemoving = reviewPage.getTextBaggageDetails();
			
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should be able to Remove the Meal Charges On Review Page!");
			Thread.sleep(5000);
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnRemoveBaggage"), reviewPage),
					"<b>Actual Result:</b> Baggage Charges : "+ BaggageFare + "Baggage Charges after Removing the Baggage :" + BaggageFareAfterRemoving,
					"<b>Actual Result:</b> Baggage Charges can not be Removed from Review Page", driver);

			
			
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verify Add Baggage on Pax/Review page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_033(HashMap<String, String> testData) throws Exception {

		String browser=testData.get("browser");
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

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should See 'Add Baggage' Button on Review Page!");
			Thread.sleep(5000);
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnAddBaggage"), reviewPage),
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

	@Test(groups = { "desktop" }, description = "Flight Search INTL- OW with Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_003(HashMap <String,String> testData) throws Exception {

		//HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String browser=testData.get("browser");
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
			Log.message("8.successfully selectd Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("9.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>"); 
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			Thread.sleep(6000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("BtnModifySearch"), searchResult),
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

	@Test(groups = { "desktop" }, description = "Flight Search INTL- RT with Booking Class Of Choice", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_004(HashMap<String, String> testData) throws Exception {

		String browser=testData.get("browser");
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
			Log.message("9.successfully selectd Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("10.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<br>"); 
			Log.message("<b>Expected Result:</b> User should navigated on SearchResult page");
			Thread.sleep(6000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("BtnModifySearch"), searchResult),
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

	
	@Test(groups = { "desktop" }, description = "Guest flow - Verification of Book As Guest button ", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_039(HashMap<String, String> testData) throws Exception {

		//HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String browser=testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate =  testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			loginPage = homePage.navigateToSignIn();
			loginPage.loginYatraAccount(emailId , password);
			Log.message("2.Successfully Logged in Yatra account");	

			//step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("3.Successfully clicked 'One Way' option in search Home Page ");

			//step:  select OneWay Flight Search fields
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);	
			Thread.sleep(5000);

			// step: click 'Search' button in Yatra Home page
			searchResult =	homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");	


			//step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();			
			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");
			Thread.sleep(5000);
			
			reviewPage.clickOnContinue();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Book as Guest button.");	
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnBookAsGuest"), reviewPage),
					"<b>Actual Result:</b> The Book as Guest button is displayed on Review Page.",
					"<b>Actual Result:</b> The Book as Guest button is not displayed on Review Page.",driver);  

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

}

