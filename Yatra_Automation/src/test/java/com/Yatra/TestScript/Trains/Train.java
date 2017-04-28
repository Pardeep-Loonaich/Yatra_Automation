package com.Yatra.TestScript.Trains;

import java.util.ArrayList;

//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Train module test Cases would be designed in this class 
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
import com.Yatra.Pages.PaymentPage;
import com.Yatra.Pages.ReviewPage;
import com.Yatra.Pages.SearchResult;
import com.Yatra.Pages.SearchResultBus;
import com.Yatra.Pages.TrainReviewPage;
import com.Yatra.Pages.TrainSearchResult;
import com.Yatra.Pages.TrainTravellerPage;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class Train {

	EnvironmentPropertiesReader environmentPropertiesReader;
	TrainSearchResult trainSearchResult;
	TrainTravellerPage trainTravellerPage;
	TrainReviewPage trainReviewPage;
	PaymentPage paymentPage;
	String webSite;	
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(groups = { "desktop" }, description = "Search Train ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_001(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2. Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3. Successfully clicked 'Search' in Yatra Homepage ");
        	Log.message("<br>");

			Log.message("<b>Expected Result:</b> Verify trains results are showing.");
        	Log.message("<br>");
			Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindTrain"), trainSearchResult),
					"<b>Actual Result:</b> User successfully navigated to trains Search Result Page.",
					"<b>Actual Result:</b> User is not successfully navigated to trains Search Result Page.",
							driver);
			
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Search train", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_002(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.EnterOriginCity(origin);
			Log.message("2. Entered Invalid cityname.");
        	Log.message("<br>");

			Log.message("<b>Expected Result:</b> Verify proper message should be show after entering incorrect city name.");
        	Log.message("<br>");
			Log.assertThat(homePage.getErrorTextAfterInvalidTxt().contains("No match found for the search"),
					"<b>Actual Result:</b> Proper message is displayed after entering incorrect city name.",
					"<b>Actual Result:</b> Proper message is not displayed after entering incorrect city name.",
							driver);
			
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	

	@Test(groups = { "desktop" }, description = "Search Train with code", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_003(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.EnterOriginCity(origin);
			Log.message("2. Entered valid origin cityname.");
        	Log.message("<br>");

			Log.message("<b>Expected Result:</b> Verify list of cities shpuld display on basis of code.");
        	Log.message("<br>");
			Log.assertThat(homePage.getTextFrmSuggestionInOriginTrain().contains(origin),
					"<b>Actual Result:</b> Proper message is displayed after entering incorrect city name.",
					"<b>Actual Result:</b> Proper message is not displayed after entering incorrect city name.",
							driver);
			Log.testCaseResult();
			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	
	
	@Test(groups = { "desktop" }, description = "Check for message in case of no result ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_004(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			BrowserActions.nap(3);	
			
        	Log.message("<br>");

			Log.message("<b>Expected Result:</b> Verify for message in case of no result.");
        	Log.message("<br>");

			Log.assertThat(trainSearchResult.trainMessageBox(),
					"<b>Actual Result:</b> Error Message displayed on Train Search Page.",
					"<b>Actual Result:</b> Error Message not displayed on Train Search Page.", driver);
		
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	

	@Test(groups = { "desktop" }, description = "Train Search Results should be sorted by depart in asc order", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_005(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			BrowserActions.nap(3);

			Log.message("<br>");

			Log.message("<b>Expected Result:</b> Verify Results should be sorted by departure time in asc order.");
        	Log.message("<br>");
        	Log.assertThat(trainSearchResult.sortDepartDate(),
					"<b>Actual Result:</b> Successfully verified the deperature time for 'Train Search' in asc order.",
					"<b>Actual Result:</b> The departure time for 'Train Search' is not in asc order.", driver);

			
        	Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Train Search Results should be sorted by arrival time in asc order", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_006(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			BrowserActions.nap(3);

			Log.message("<br>");

			Log.message("<b>Expected Result:</b> Verify Results should be sorted by arrival time in asc order.");
        	Log.message("<br>");
        	Log.assertThat(trainSearchResult.sortArrivalDate(),
					"<b>Actual Result:</b> Successfully verified the arrival time for 'Train Search' in asc order.",
					"<b>Actual Result:</b> The arrival time for 'Train Search' is not in asc order.", driver);

			
        	Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	
	@Test(groups = { "desktop" }, description = "On selecting train,it should show availability of seats.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_008(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
        String availSeat = "AVAILABLE";

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			

			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify that after selecting train,it should show availability of seats..");
        	Log.message("<br>");
        	Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindTrain"), trainSearchResult),
					"<b>Actual Result:</b> Successfully navigated to 'Train Search' page and the available seats for the selected train are:"+trainSearchResult.checkDetailsBySelectingTrainNClassByRow(2,availSeat),
					"<b>Actual Result:</b> Unable to navigate to 'Train Search' page and the availabe seats are not visible.", driver);

        	Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "Check for Quota drop down of seats.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_009(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			 
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify that quota dropdown of train seats,it should show available quota..");
        	Log.message("<br>");
        	Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindTrain"), trainSearchResult),
					"<b>Actual Result:</b> Successfully navigated to 'Train Search' page and the available seats quota for the selected train are:"+trainSearchResult.selectQuota(),
					"<b>Actual Result:</b> Unable to navigate to 'Train Search' page and the available seats quota for the selected train are not visible.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Incase no seat is available, it should show an option to select bus.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_010(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			 
			Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindBus"), trainSearchResult),
					"<b>Actual Result:</b> Successfully navigated to 'Train Search' page.",
					"<b>Actual Result:</b> Unable to navigate to 'Train Search' page.", driver);

			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify that select bus option should display in case of no seat available..");
        	Log.message("<br>");
        	Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindBus"), trainSearchResult),
					"<b>Actual Result:</b> Select bus option is displayed in case of no seat available.",
					"<b>Actual Result:</b> Select bus option is not displayed in case of no seat available.", driver);

        	Log.testCaseResult();	

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = { "desktop" }, description = "Verify after selecting seat it should open pop-up for boarding point selection.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_011(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			

			trainSearchResult.selectTrainByIndexAndBook(1);
			Log.message("4.Successfully selected train and clicked on 'Book Now' button.");

			Thread.sleep(2000);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify after selecting seat it should open pop-up for boarding point selection.");
        	Log.message("<br>");
        	Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("modalArriveInfo"), trainSearchResult),
					"<b>Actual Result:</b> The Popup is displayed and the Boarding Points available are:"+trainSearchResult.gettingBoardingPoints(),
					"<b>Actual Result:</b> The Popup is not displayed", driver);


	
        	Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Check for Prev/Next day tabs.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_012(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			 
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify that Prev/Next tabs are displayed..");
        	Log.message("<br>");
        	Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("tabPrevDay","tabNextDay"), trainSearchResult),
					"<b>Actual Result:</b> Both Previous and Next Tab is displayed on 'Train Search Result' page.",
					"<b>Actual Result:</b> Both Previous and Next Tab is not displayed on 'Train Search Result' page.", driver);

        	Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Should show pop-up with other options of flight and bus.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_013(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			 
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify that Other Travelling options of flight and bus Popup should display  in case available..");
        	Log.message("<br>");
        	Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("popupOtherRoute"), trainSearchResult),
					"<b>Actual Result:</b> Other Travelling Options Popup displayed for the selected train are as:"+trainSearchResult.getTxtFromOtherTravelOption(),
					"<b>Actual Result:</b> Other Travelling Options Popup not displayed for the selected train.", driver);

        	trainSearchResult.closeOtherTravelOptionPopup();
			Log.message("4.Successfully close the Other Travelling OptionPopup.");

        	Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("popupOtherRouteInSideNav"), trainSearchResult),
					"<b>Actual Result:</b> Other Travelling Option displayed in Left Navigation Panel.",
					"<b>Actual Result:</b> Other Travelling Option not displayed", driver);

        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "Available class should be shown for selecting the train.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_014(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			 
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify that after selecting train,it should show availability of seats..");
        	Log.message("<br>");
        	Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindTrain"), trainSearchResult),
					"<b>Actual Result:</b> Successfully navigated to 'Train Search' page and the available seats for the selected train are:"+trainSearchResult.checkAvailableClassBySelectingTrainByRow(1),
					"<b>Actual Result:</b> Unable to navigate to 'Train Search' page and the availabe seats are not visible.", driver);

        	Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Should redirect to Bus SRP with same sectors after clicking on FindBus.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_015(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			
			trainSearchResult.clickingOnFindBusButton(1);
			Log.message("4.Clicked on 'FindBus' button.");

			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify after clicking on FindBus button page should redirect to Bus SRP with same sectors .");
        	Log.message("<br>");
        	Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindBuses"), trainSearchResult),
					"<b>Actual Result:</b> Successfully navigated to 'Bus Search' page after clicking on FindBus button.",
					"<b>Actual Result:</b> Unable to navigate to 'Bus Search' page after clicking on FindBus button.", driver);

        	Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	
	@Test(groups = { "desktop" }, description = "Verify pop-up with option to change boarding points.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_016(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			
			trainSearchResult.selectTrainByIndexAndBook(1);
			Log.message("4.Successfully selected train and clicked on 'Book Now' button.");
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify pop-up with option to change boarding points should display.");
        
			Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("modalArriveInfo"), trainSearchResult),
					"<b>Actual Result:</b> The Popup is displayed after booking a seat.",
					"<b>Actual Result:</b> The Popup is not displayed", driver);

			String initialBoardingPoint = trainSearchResult.verifyBoardingPointChange();
			Log.message("   The initial Boarding Point:"+initialBoardingPoint);
			
			trainSearchResult.changeBoardingPoint();
			Log.message("5.Changing the default Boarding Point.");

			String finalBoardingPoint = trainSearchResult.verifyBoardingPointChange();
			Log.message("   The Selected Boarding Point:"+finalBoardingPoint);
			
			Log.message("<br>");
        	Log.assertThat(!initialBoardingPoint.equals(finalBoardingPoint),
					"<b>Actual Result:</b> The user is able to select the other option of Boarding Point.",
					"<b>Actual Result:</b> The user is not able to select the other option of Boarding Point.", driver);

        	Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = { "desktop" }, description = "Verify pop-up should show correct boarding point options.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_017(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			
			
			trainSearchResult.selectTrainByIndexAndBook(1);
			Log.message("4.Successfully selected train and clicked on 'Book Now' button.");
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify pop-up should show correct boarding point options.");
        
			Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("modalArriveInfo"), trainSearchResult),
					"<b>Actual Result:</b> The Popup is displayed after booking a seat.",
					"<b>Actual Result:</b> The Popup is not displayed", driver);

		
			
			Log.message("<br>");
        	Log.assertThat(trainSearchResult.verifySelectedBoardingPoint(origin),
					"<b>Actual Result:</b> The Correct Boarding Point is displayed in the popup.",
					"<b>Actual Result:</b> The Correct Boarding Point is not displayed in the popup.", driver);

        	Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = { "desktop" }, description = "Verify Dropping point,Boarding point,depart time,arrival time in fliters.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_018(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");
			
			
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Dropping point,Boarding point,depart time,arrival time in fliters.");
        
			Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("lftNavSection"), trainSearchResult),
					"<b>Actual Result:</b> The Left Navigation is present and the sections are displayed with the headings.:"+trainSearchResult.getLeftNavHeading(),
					"<b>Actual Result:</b> The Left Navigation is not present", driver);
			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "Verify Book now button should be disabled in case of no seats available.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_020(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String quota = testData.get("Quota");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");	
			
			trainSearchResult.selectQuotaFrmDrpDown(quota);
			Log.message("4.Selected on 'Quota' as Ladies Quota.");

			
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Book now button should be disabled in case of no seats available.");
        	Log.assertThat(trainSearchResult.verifyBookNowByselectingTrainByIndex(1),
					"<b>Actual Result:</b> Successfully navigated to 'Bus Search' page after clicking on FindBus button.",
					"<b>Actual Result:</b> Unable to navigate to 'Bus Search' page after clicking on FindBus button.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Check if its showing current status of available seats and it should be according to selected quota.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_021(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String quota = testData.get("Quota");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");	
		
			trainSearchResult.selectQuotaFrmDrpDown(quota);
			Log.message("4.Selected on 'Quota' as Ladies Quota.");

			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify current status of available seats should be according to selected quota.");
        	Log.assertThat(trainSearchResult.verifyQuotaByselectingTrainByIndex(1,quota),
					"<b>Actual Result:</b> Current Status of available seat is according to selected quota."+trainSearchResult.checkDetailsBySelectingTrainNClassByRow(1,quota),
					"<b>Actual Result:</b> Unable to get current Status of available seat according to selected quota.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = { "desktop" }, description = "Verify Boarding points details should be correct.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_022(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String quota = testData.get("Quota");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
		
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");	
		
		   
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Boarding points details should be correct.");
        	Log.assertThat(trainSearchResult.verifyConfirmationPopUpDetails(1),
					"<b>Actual Result:</b> Current Status of available seat is according to selected quota.",
					"<b>Actual Result:</b> Unable to get current Status of available seat according to selected quota.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Verify for FB login also(Verifying FB Link only).", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_025(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String quota = testData.get("Quota");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
		
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");	
		
		    trainSearchResult.selectTrainByIndexAndBook(1);
			Log.message("4.Successfully selected train and clicked on 'Book Now' button.");

			trainTravellerPage = trainSearchResult.clickOnContinue();
			Log.message("5.Clicked on 'Continue' button.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify for FB login also(Verifying FB Link only).");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("lnkFB"),trainTravellerPage ),
					"<b>Actual Result:</b> When Login as Guest User the 'Login as Facebook User' link is displayed.",
					"<b>Actual Result:</b> When Login as Guest User the 'Login as Facebook User' link is not displayed.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Verify for guest user flow.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_023(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String mobile = testData.get("MobileNumber");
		String irctcId = testData.get("IRCTC_ID");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
		
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");	
		
			Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindTrain"),trainSearchResult ),
					"<b>Actual Result:</b> Succesfully navigated to Train Search Result Page.",
					"<b>Actual Result:</b> Unable to navigated to Train Search Result Page.", driver);

		    trainSearchResult.selectTrainByIndexAndBook(1);
			Log.message("4.Successfully selected train and clicked on 'Book Now' button.");

			trainTravellerPage = trainSearchResult.clickOnContinue();
			Log.message("5.Clicked on 'Continue' button.");

			trainTravellerPage.loginAsGuestUser(email, mobile);
			Log.message("6.SignIn as 'Guest' user.");

			Thread.sleep(2000);
	    	trainTravellerPage.enterIrctcId(irctcId);
			Log.message("7.Filling IRCTC ID if option is visible.");

			trainTravellerPage.fillTravellerDetails();
			Log.message("8.Filling traveller details on PaxPage.");

			trainTravellerPage.clickToAcceptInsurance();
			Log.message("9.Clicking on 'Accept' Travel Insurance checkbox.");

			trainTravellerPage.checkBookingPolicy();
			Log.message("10.Clicking on 'Accept Booking Policy' checkbox.");

			trainReviewPage = trainTravellerPage.clickOnContinueInPaxPage();
			Log.message("11.Clicking on 'Continue' in PaxPage.");

			Log.assertThat(trainReviewPage.elementLayer.verifyPageElements(Arrays.asList("btnModifyItinerary"),trainReviewPage ),
					"<b>Actual Result:</b> Succesfully navigated to Train Review Page.",
					"<b>Actual Result:</b> Unable to navigated to Train Review Page.", driver);
			paymentPage = trainReviewPage.continueInReviewIternary();
			Log.message("12.Clicking on 'Continue' in ReviewPage.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify for guest user flow.");
        	Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("btnPayNow"),paymentPage ),
					"<b>Actual Result:</b> Successfully navigated to Payment Page.",
					"<b>Actual Result:</b> Unable to navigated to Payment Page.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = { "desktop" }, description = "Verify for Signed User flow.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_024(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String password = testData.get("Password");
		String irctcId = testData.get("IRCTC_ID");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
		
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");	
		
			Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindTrain"),trainSearchResult ),
					"<b>Actual Result:</b> Succesfully navigated to Train Search Result Page.",
					"<b>Actual Result:</b> Unable to navigated to Train Search Result Page.", driver);

		    trainSearchResult.selectTrainByIndexAndBook(1);
			Log.message("4.Successfully selected train and clicked on 'Book Now' button.");

			trainTravellerPage = trainSearchResult.clickOnContinue();
			Log.message("5.Clicked on 'Continue' button.");

			trainTravellerPage.loginAsSignedUser(email, password);
			Log.message("6.SignIn as 'SignedIn' user.");

			Thread.sleep(2000);
	    	trainTravellerPage.enterIrctcId(irctcId);
			Log.message("7.Filling IRCTC ID if option is visible.");

			trainTravellerPage.fillTravellerDetails();
			Log.message("8.Filling traveller details on PaxPage.");

			trainTravellerPage.clickToAcceptInsurance();
			Log.message("9.Clicking on 'Accept' Travel Insurance checkbox.");

			trainTravellerPage.checkBookingPolicy();
			Log.message("10.Clicking on 'Accept Booking Policy' checkbox.");

			trainReviewPage = trainTravellerPage.clickOnContinueInPaxPage();
			Log.message("11.Clicking on 'Continue' in PaxPage.");

			Log.assertThat(trainReviewPage.elementLayer.verifyPageElements(Arrays.asList("btnModifyItinerary"),trainReviewPage ),
					"<b>Actual Result:</b> Succesfully navigated to Train Review Page.",
					"<b>Actual Result:</b> Unable to navigated to Train Review Page.", driver);
			paymentPage = trainReviewPage.continueInReviewIternary();
			Log.message("12.Clicking on 'Continue' in ReviewPage.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify for guest user flow.");
        	Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("btnPayNow"),paymentPage ),
					"<b>Actual Result:</b> Successfully navigated to Payment Page.",
					"<b>Actual Result:</b> Unable to navigated to Payment Page.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = { "desktop" }, description = "Verify for validations on IRCTC id.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_026(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String mobile = testData.get("MobileNumber");
		String irctcId = testData.get("IRCTC_ID");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
		
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");	
		
			Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindTrain"),trainSearchResult ),
					"<b>Actual Result:</b> Succesfully navigated to Train Search Result Page.",
					"<b>Actual Result:</b> Unable to navigated to Train Search Result Page.", driver);

		    trainSearchResult.selectTrainByIndexAndBook(1);
			Log.message("4.Successfully selected train and clicked on 'Book Now' button.");

			trainTravellerPage = trainSearchResult.clickOnContinue();
			Log.message("5.Clicked on 'Continue' button.");

			trainTravellerPage.loginAsGuestUser(email, mobile);
			Log.message("6.SignIn as 'Guest' user.");

			Thread.sleep(2000);
	    	trainTravellerPage.enterIrctcId(irctcId);
			Log.message("7.Filling IRCTC ID if option is visible.");

			trainTravellerPage.clickOnEditIrctcIDLink();
			Log.message("8.Clicked on 'Edit' Irctc ID Link.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify for validations on IRCTC id.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("popupInvalidIrctcId"),trainTravellerPage ),
					"<b>Actual Result:</b> The Popup is appear when we entered the invalid IRCTC Id and click on 'Submit' and the error message is :."+trainTravellerPage.getTextFromInvalidIrctcIdPopUp(),
					"<b>Actual Result:</b> The Popup is not appear when we entered the invalid IRCTC Id and click on 'Submit'.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Verify if Mobile Number is editable or not.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_031(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String mobile[] = testData.get("MobileNumber").split(",");
		String irctcId = testData.get("IRCTC_ID");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickTrainTab();
		
			// step: select Train Search fields
			homePage.selectTrainSearchFields(origin, destination, trainDepartureDate);
			Log.message("2.Successfully filled the search details for 'Train' trip.");

			// step: click 'Search' button in Yatra Home page
			trainSearchResult = homePage.clickTrainBtnSearch();
			Log.message("3.Successfully clicked 'Search' in Yatra Homepage ");	
		
			Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindTrain"),trainSearchResult ),
					"<b>Actual Result:</b> Succesfully navigated to Train Search Result Page.",
					"<b>Actual Result:</b> Unable to navigated to Train Search Result Page.", driver);

		    trainSearchResult.selectTrainByIndexAndBook(1);
			Log.message("4.Successfully selected train and clicked on 'Book Now' button.");

			trainTravellerPage = trainSearchResult.clickOnContinue();
			Log.message("5.Clicked on 'Continue' button.");

			trainTravellerPage.loginAsGuestUser(email, mobile[0]);
			Log.message("6.SignIn as 'Guest' user.");

			Thread.sleep(2000);
	    	trainTravellerPage.enterIrctcId(irctcId);
			Log.message("7.Filling IRCTC ID if option is visible.");

			Thread.sleep(2000);

			trainTravellerPage.editIrctcMobileNumber(mobile[1]);
			String mobNo = trainTravellerPage.getIRCTCMobileNo();
			Log.message("Mobile No"+mobNo);
			
			

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify if Mobile Number is editable or not.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("popUpMobileNoUpdate"),trainTravellerPage ),
					"<b>Actual Result:</b> The Popup is appear when we edit Mobile Number and save it the success message is :."+trainTravellerPage.getSuccessMsgFromUpdateMobileNoPopUp(),
					"<b>Actual Result:</b> The Popup is not appear when we edit Mobile Number and save it.", driver);

        	 
        	 String mobNo1 = trainTravellerPage.getIRCTCMobileNo();
 			Log.message("Mobile No"+mobNo1);
 			
 			Log.assertThat(mobNo!=mobNo1,
					"<b>Actual Result:</b> The Updated Mobile Number is same as the Entered Mobile no.",
					"<b>Actual Result:</b> The Popup is not appear when we edit Mobile Number and save it.", driver);

 			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	
}