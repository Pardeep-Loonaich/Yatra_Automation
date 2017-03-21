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
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class FlightPricing {

	EnvironmentPropertiesReader environmentPropertiesReader;
	HomePage homePage;
	LoginPage loginPage;
	ReviewPage reviewPage;
	SearchResult searchResult;
	String webSite;
	//private String workbookName = "testdata\\data\\Flights.xls";
	//private String sheetName = "FlightPricing";
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {

		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}
	@Test(groups = { "desktop" }, description = "Check to price calculation for DOM flight-one way", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_FlightPricing_015(HashMap<String, String> testData) throws Exception {

		//HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
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

			//navigated to sign in page
			loginPage = homePage.navigateToSignIn();
			Log.message("3.Navigated to 'SignIn' Page.");			

			// login using valid credentials
			loginPage.loginYatraAccount(emailId, password);
			Log.message("4.Successfully Logged in Yatra account");	


			//selected trip as one way and enter the search fields
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);		
			Log.message("5.Successfully filled the search details for 'ONE WAY' trip.");			


			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("6.Successfully clicked 'Search' in Yatra Homepage ");

			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("BtnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.",driver);


			//clicked on book now buuton
			ReviewPage reviewPage = searchResult.clickOnBookNowInOneWay(10);
			Log.message("7.Clicked on 'Book Now' button in Search Result Page ");

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

	@Test(groups = { "desktop" }, description = "Check to price calculation for DOM flight-round trip", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_FlightPricing_016(HashMap<String, String> testData) throws Exception {

		//HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String browser=testData.get("browser");

		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
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

			// step: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}			

			//step: Navigate to Yatra Login
			loginPage = homePage.navigateToSignIn();
			Log.message("3.Navigated to 'SignIn' Page.");			

			loginPage.loginYatraAccount(emailId, password);
			Log.message("4.Successfully Logged in Yatra account");	

			//step: enter search details in Yatra Home page
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("5.Successfully filled the search details for 'ROUND' trip.");			

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("6.Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("BtnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.",driver);


			// clicked on book now
			ReviewPage reviewPage = searchResult.clickOnBookNowInRound(1,2,2,7);
			Log.message("7.Clicked on 'Book Now' button in Search Result Page.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.",driver);

			//clicked on fees & surcharge link
			reviewPage.clickOnFeeSurchrgeLink();
			Log.message("8.Clicked on 'Fees & Surcharge' details link in Review Page.");


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Fees & Surcharge details as Signed User should be displayed after clicking on Fees & Surcharge Link in Fare Details module.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("moduleFeeSurchrge"), reviewPage),
					"<b>Actual Result:</b> The Fare details module is displayed on Review Page.",
					"<b>Actual Result:</b> The Fare details module is not displayed on Review Page.",driver);


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


@Test(groups = { "desktop" }, description = "Insurance added on pax page ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_FlightPricing_028(HashMap<String, String> testData) throws Exception {

		//HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
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

			//step: Navigate to Yatra Login
			loginPage = homePage.navigateToSignIn();
			Log.message("3.Navigated to 'SignIn' Page.");			

			//enter the email and password details
			loginPage.loginYatraAccount(emailId, password);
			Log.message("4.Successfully Logged in Yatra account");	

			//selected trip as one way
			homePage.selectOneWayTrip();
			//entered the search details
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate,passengerInfo, passengerClass);
			Log.message("5.Successfully filled the search details for 'ONE WAY' trip.");			

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("6.Clicked on 'Search' in Yatra Homepage.");


			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.",driver);


			//clicked on book now button 
			ReviewPage reviewPage = searchResult.clickOnBookNowInOneWay(3);
			Log.message("7.Clicked on 'Book Now' button in Search Result Page.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.",driver);

			// clicked on continue button in review page
			reviewPage.clickOnContinue();
			Log.message("8.Clicked on Continue button in Review Page Step-1.");



			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Travel Assistance and Insurance amount should be inculded in the Fare Detail.");


			Log.assertThat(reviewPage.getTextFromFareDetails().contains("Travel Assistance and Insurance"),
					"<b>Actual Result:</b> Travel Assistance and Insurance amount included in the Fare details and is displayed as:",
					"<b>Actual Result:</b> Travel Assistance and Insurance amount not included in the Fare details",driver);


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Insurance verification on pax page removed", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_FlightPricing_029(HashMap<String, String> testData) throws Exception {

		//HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
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

			//step: Navigate to Yatra Login
			loginPage = homePage.navigateToSignIn();
			Log.message("3.Navigated to 'SignIn' Page.");			

			loginPage.loginYatraAccount(emailId, password);
			Log.message("4.Successfully Logged in Yatra account");	

			//selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
		    homePage.selectOneWayFlightSearchFields(origin, destination, departureDate,passengerInfo, passengerClass);
			Log.message("5.Successfully filled the search details for 'ONE WAY' trip.");			

			// step: click 'Search' button in Yatra Home page
			SearchResult searchResult = homePage.clickBtnSearch();
			Log.message("6.Clicked on 'Search' in Yatra Homepage.");

			Thread.sleep(6000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.",driver);


			// clicked on book now button in one way
			ReviewPage reviewPage = searchResult.clickOnBookNowInOneWay(3);
			Log.message("7.Clicked on 'Book Now' button in Search Result Page.");
		
			  Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
						"<b>Actual Result:</b> Successfully navigated on Review Page.",
						"<b>Actual Result:</b> Unable to navigated on Review Page.",driver);
			   
			  //clicke on continue button
			reviewPage.clickOnContinue();
			Log.message("8.Clicked on Continue button in Review Page Step-1.");

    
			Log.message("<br>");
			
			Log.message("<b>Expected Result:</b> Travel Assistance and Insurance amount should be inculded in the Fare Detail.");


			Log.assertThat(reviewPage.verifyInsuranceCheckbox()&&reviewPage.getTextFromFareDetails().contains("Travel Assistance and Insurance"),
							"<b>Actual Result:</b> Insurance checkbox is checked and Travel Assistance and Insurance amount is included in the Fare Details. ",
							"<b>Actual Result:</b> Insurance checkbox is not checked and the Travel Assistance and Insurance amount is not displayed in Fare Details.",driver);

			 
			//clicked on Insurance
			reviewPage.uncheckingInsuranceCheckbox();
			Log.message("9. Unchecking on Insurance checkbox. ");
			
			Log.assertThat(!reviewPage.verifyInsuranceCheckbox()&&(!reviewPage.getTextFromFareDetails().contains("Travel Assistance and Insurance")),
					"<b>Actual Result:</b> Insurance checkbox is unchecked and Travel Assistance and Insurance amount not included in the Fare Details. ",
					"<b>Actual Result:</b> Insurance checkbox is not unchecked and the Travel Assistance and Insurance amount is displayed in Fare Details.",driver);

			 
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}



	@Test(groups = { "desktop" }, description = "Change flight link verification on Review page - DOM", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_022(HashMap<String, String> testData) throws Exception {

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
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);		
			Thread.sleep(5000);

			// step: click 'Search' button in Yatra Home page
			searchResult =	homePage.clickBtnSearch();
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");	


			//step: Click on 'Book Now' button in Yatra Home page

			ReviewPage reviewPage = searchResult.clickOnBookNowInOneWay(1);

			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");
			Thread.sleep(5000);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Change Flight link.");	
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> The Change Flight link is displayed on Review page.",
					"<b>Actual Result:</b> The Change Flight link is not displayed on Review Page.",driver);		  

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Change flight link verification on Review page - INTL", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_023(HashMap<String, String> testData) throws Exception {

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
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);		
			Log.message("4.Successfully selected OneWay Flight Search Fields ");
			Thread.sleep(5000);

			// step: click 'Search' button in Yatra Home page
			searchResult =	homePage.clickBtnSearch();
			//Thread.sleep(5000);
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");							

			ReviewPage reviewPage = searchResult.clickOnBookNowINT();
			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");
			Thread.sleep(5000);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Change Flight link.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> The Change Flight link is displayed on Review page.",
					"<b>Actual Result:</b> The Change Flight link is not displayed on Review Page.",driver);		  

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Applying promo code on review page- Promo dropdown Validation", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_024(HashMap<String, String> testData) throws Exception {

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
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);		
			Log.message("4.Successfully selected OneWay Flight Search Fields ");
			Thread.sleep(5000);

			// step: click 'Search' button in Yatra Home page
			searchResult =	homePage.clickBtnSearch();			
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");	

			//step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");

			//step: Click on 'Promo Drop Down' in Review page
			reviewPage.clickOnPromoDrpDwn();			
			Log.message("7. Clicked  on 'Promo Drop Down' in Review page");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check Promo Code Dropdown.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("fldContentpromo"), reviewPage),
					"<b>Actual Result:</b> Promo Code Dropdown is displayed on Review page.",
					"<b>Actual Result:</b> Promo Code Dropdown is not displayed on Review Page.",driver);


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Applying promo code on review page- Promo Coupon Selection", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_025(HashMap<String, String> testData) throws Exception {

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
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);		
			Log.message("4.Successfully selected OneWay Flight Search Fields ");
			Thread.sleep(5000);

			// step: click 'Search' button in Yatra Home page
			searchResult =	homePage.clickBtnSearch();			
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");	

			//step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");	

			//step: Click on 'Promo Drop Down' in Review page and select Promo Coupon
			reviewPage.clickOnPromoCoupon();	
			Log.message("7. Clicked  on 'Promo Drop Down' in Review page and selected coupon");

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = { "desktop" }, description = "Applying promo code on review page- Have a Promo Code Validation", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_026(HashMap<String, String> testData) throws Exception {

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
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);		
			Log.message("4.Successfully selected OneWay Flight Search Fields ");
			Thread.sleep(15000);

			// step: click 'Search' button in Yatra Home page
			searchResult =	homePage.clickBtnSearch();
			Thread.sleep(5000);
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");							

			//step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("lnkHavePromoCode"), reviewPage),
					"<b>Actual Result:</b> Hava a Promo code link is displayed on Review page.",
					"<b>Actual Result:</b> Hava a Promo code link is not displayed on Review Page.",driver);

			//step: Click on 'Have a coupon' link in Review page 
			reviewPage.clickOnHavePromoCode();		

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = { "desktop" }, description = "Applying promo code on review page-  Have a Promo Code submission", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_027(HashMap<String, String> testData) throws Exception {

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
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);		
			Log.message("4.Successfully selected OneWay Flight Search Fields ");
			Thread.sleep(5000);

			// step: click 'Search' button in Yatra Home page
			searchResult =	homePage.clickBtnSearch();			
			Log.message("5.Successfully clicked 'Search' in Yatra Homepage ");							

			//step: Click on 'Book Now' button in Yatra Home page
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("6.Clicked on 'Book Now' button in Search Result Page ");

			/*
			 * Click on 'Promo Drop Down' in Review page and select Promo Coupon
			 * Click on 'Have a coupon' link and enter the selected promo coupon
			 * Click on 'Apply' button
			 */
			reviewPage.getPromoCode();		
			Log.message("7.Successfully Applied the Promo Code.");

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}	
	@Test(groups = {"desktop" }, description = "Check to price calculation for DOM flight-multicity", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_FlightPricing_017(HashMap <String,String> testData) throws Exception {

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

			
			Thread.sleep(2000);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> User should navigated on SearchResult page with DOM-Multicity flight result",
					"<b>Actual Result:</b> User not navigated on SearchResult page with DOM-Multicity flight result",
					driver);

			
			reviewPage = searchResult.clickOnBookNowInMulticity(1,1,2,4);
			Log.message("13.Clicked on 'Book Now' button in Search Result Page ");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.",driver);

			//clicked on fees & surcharge link
			reviewPage.clickOnFareRulesLink();
			Log.message("14.Clicked on 'Fare Rules' details link in Review Page.");


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Fare Rules popup as Signed User should be displayed after clicking on View Fare Rules Link in Fare Details module.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("moduleFareRules"), reviewPage),
					"<b>Actual Result:</b> The Fare Rules module is displayed on Review Page.",
					"<b>Actual Result:</b> The Fare Rules module is not displayed on Review Page.",driver);

			
		
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


}