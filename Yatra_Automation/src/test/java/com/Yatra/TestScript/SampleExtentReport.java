package com.Yatra.TestScript;

import java.security.PublicKey;
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
import com.Yatra.Pages.SearchResult;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.TestDataExtractor;
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class SampleExtentReport {


	EnvironmentPropertiesReader environmentPropertiesReader;
	String webSite;
	private String workbookName = "testdata\\data\\Regression.xls";
	private String sheetName = "SampleTest";
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(groups = {"desktop" }, description = "To verify the Yatra Home Page", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_001(String browser) throws Exception {

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

			// step2: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("2.Verified Yatra Title text");
			}

			// step3: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '"+ origin +"' in Yatra Homepage " );


			// step4: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '"+ destination+"' in Yatra Homepage" );
			Thread.sleep(3000);

			
			homePage.selectDepartureDate(testData.get("Date"));
			Log.message("5. Successfully cliked the departure date");
			
			homePage.specifyPassengerInfo(testData.get("PassengerInfo"));
			Log.message("6. Passenger Info successfully specified");
			
			homePage.clickBtnSearch();
			Log.message("7 .Successfully clicked 'Search' in Yatra Homepage ");

			

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
			Log.message("8.Successfully entered Origin '"+ origin +"' in Yatra Homepage " );

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '"+ destination+"' in Yatra Homepage" );
			Thread.sleep(3000);	
			

			homePage.clickDeptDatePicker();
			//homePage.selectDeptCurrentDate(); //TODO
			homePage.selectDeptDateAfterOneWeek();
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
	
	@Test(groups = {"desktop" }, description = "Searching Fligts for Round Trip-Domestic", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_003(String browser) throws Exception {

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
			Log.message("8.Successfully entered Origin '"+ origin +"' in Yatra Homepage " );

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '"+ destination+"' in Yatra Homepage" );
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
	
	@Test(groups = {"desktop" }, description = "Searching Fligts for One Way-International", dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
	public void TC_Yatra_Flight_004(String browser) throws Exception {

		HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String date = testData.get("Date");
		List<String> element = Arrays.asList("lnkAirLines");
		
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
			Log.message("8.Successfully entered Origin '"+ origin +"' in Yatra Homepage " );

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '"+ destination+"' in Yatra Homepage" );
			Thread.sleep(3000);	
			
			//step: enter depart date
			homePage.selectDepartureDate(date);
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
			Log.message("8.Successfully entered Origin '"+ origin +"' in Yatra Homepage " );

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '"+ destination+"' in Yatra Homepage" );
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
			Log.message("8.Successfully entered Origin '"+ origin +"' in Yatra Homepage " );

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '"+ destination+"' in Yatra Homepage" );
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
		List<String> element = Arrays.asList("returnDate");
		List<String> elements = Arrays.asList("BtnModifySearch");

		
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
			Log.message("8.Successfully entered Origin '"+ origin +"' in Yatra Homepage " );

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("9.Successfully entered Destination '"+ destination+"' in Yatra Homepage" );
			Thread.sleep(3000);	
			
			Log.message("<b>Expected Result:</b> Successfully searched Flights");
			Log.assertThat(homePage.elementLayer.verifyPageElements(element, homePage), "Pass", "Fail");
			
			homePage.clickDeptDatePicker();		
			homePage.selectDeptDateAfterOneWeek();
			Log.message("10. selected Depart Date");	
			
			homePage.clickReturnDatePicker();
			homePage.selectReturnDateAfterTwoWeek();
			Log.message("11. selected Return Date");	
			Thread.sleep(5000);	
			
			homePage.specifyPassengerInfo(passangerInfo);
			Log.message("12. Select Choice Of booking Seat",driver);	

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
}
