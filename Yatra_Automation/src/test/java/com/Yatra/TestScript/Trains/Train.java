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
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Yatra.Pages.HomePage;
import com.Yatra.Pages.PaymentPage;

import com.Yatra.Pages.TrainReviewPage;
import com.Yatra.Pages.TrainSearchResult;
import com.Yatra.Pages.TrainTravellerPage;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;

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
			System.out.println(homePage);
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
	
	@Test(groups = { "desktop" }, description = "Verify Should reset all filters.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_019(HashMap<String, String> testData) throws Exception {

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

			boolean result = trainSearchResult.verifyQuotaByselectingTrainByIndex(1, quota);
			trainSearchResult.clickOnReset();
			Log.message("5.Clicked on 'Reset' filter button.");

			boolean result2 =trainSearchResult.verifyQuotaByselectingTrainByIndex(1, quota);
			

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify after clicking Should reset all filters .");
        	Log.assertThat(result!=result2,
					"<b>Actual Result:</b> Successfully reseted all filters.",
					"<b>Actual Result:</b> Unable to reset all filters.", driver);

			
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

			Thread.sleep(1000);
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

			trainTravellerPage.clickOnEditIrctcIDLink(irctcId);
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
	
	@Test(groups = { "desktop" }, description = "Should be able to change boarding point.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_027(HashMap<String, String> testData) throws Exception {

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
		
			Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindTrain"),trainSearchResult ),
					"<b>Actual Result:</b> Succesfully navigated to Train Search Result Page.",
					"<b>Actual Result:</b> Unable to navigated to Train Search Result Page.", driver);

		    trainSearchResult.selectTrainByIndexAndBook(1);
			Log.message("4.Successfully selected train and clicked on 'Book Now' button.");

			trainTravellerPage = trainSearchResult.clickOnContinue();
			Log.message("5.Clicked on 'Continue' button.");

			trainTravellerPage.selectBoardingPointFrmDrpdwn();
			Log.message("6.Selected Board Point from the dropdown.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user should be able to change boarding point.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("drpBoardingPnt"),trainTravellerPage ),
					"<b>Actual Result:</b> User is able to change the boarding point and the success message is:\n"+trainTravellerPage.getSuccessMsgAfterChngBoardPnt(),
					"<b>Actual Result:</b> User is not able to change the boarding point.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = { "desktop" }, description = "Verify if IRCTC id is linked with yatra ID then it should not ask to enter IRCTC Id on login page.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_028(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String password = testData.get("Password");

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
	

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify if IRCTC id is linked with yatra ID then it should not ask to enter IRCTC Id on login page.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("btnResetIrctcPsswrd"),trainTravellerPage ),
					"<b>Actual Result:</b> Yatra Id is linked with Irctc id.",
					"<b>Actual Result:</b> Yatra Id is not linked with Irctc id.", driver);

        
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Verify if  If IRCTC Id is not linked with Yatra Id it will ask user to enter IRCTC Id and it should link that id with yatra one on confirmed booking.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_029(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String password = testData.get("Password");

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
	

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify if IRCTC id is linked with yatra ID then it should not ask to enter IRCTC Id on login page.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("btnResetIrctcPsswrd"),trainTravellerPage ),
					"<b>Actual Result:</b> Yatra Id is linked with Irctc id.",
					"<b>Actual Result:</b> Yatra Id is not linked with Irctc id.", driver);

        
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = { "desktop" }, description = "Check for name, title & Age validations.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_030(HashMap<String, String> testData) throws Exception {

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
			
			trainTravellerPage.verifyPaxDetails();
			Log.message("8.Filled Invalid Pax Details.");

		
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify name, title & Age validations.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("toaderErrorMsg"),trainTravellerPage ),
					"<b>Actual Result:</b> Error message displayed after filling wrong details. \n MSG:"+ trainTravellerPage.getErrorTxtFromTheToader(),
					"<b>Actual Result:</b> Error message not displayed after filling wrong details.", driver);

        
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
			Log.message("8.Entered new mobile number.");
	

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify if Mobile Number is editable or not.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("popUpMobileNoUpdate"),trainTravellerPage ),
					"<b>Actual Result:</b> The Popup is appear when we edit Mobile Number and save it the success message is :."+trainTravellerPage.getSuccessMsgFromUpdateMobileNoPopUp(),
					"<b>Actual Result:</b> The Popup is not appear when we edit Mobile Number and save it.", driver);

        	 
        	
 			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = { "desktop" }, description = "Check for infant validations.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_033(HashMap<String, String> testData) throws Exception {

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

			trainTravellerPage.fillTravellerDetails();
			Log.message("8.Successfully filled traveller details.");

            trainTravellerPage.verifyInfantDetails();
			Log.message("9.Filled Invalid Infant Details.");

			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify for infant validations.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("toaderErrorMsg"),trainTravellerPage ),
        			"<b>Actual Result:</b> Error message displayed after filling wrong details. \n MSG:"+ trainTravellerPage.getErrorTxtFromTheToader(),
					"<b>Actual Result:</b> Error message not displayed after filling wrong details.", driver);

        	 
        	
 			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Check for reset password button.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_034(HashMap<String, String> testData) throws Exception {

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
			Log.message("6.SignIn as 'Guest' user.");

			Thread.sleep(2000);
	    	trainTravellerPage.enterIrctcId(irctcId);
			Log.message("7.Filling IRCTC ID if option is visible.");

	        trainTravellerPage.clickOnIRCTCResetPassword();
			Log.message("8.Clicked on 'Reset IRCTC password' button.");


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Check for reset password button.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("btnResetIrctcPswrd"),trainTravellerPage ),
					"<b>Actual Result:</b> After clicking on 'Reset' button,Reset password module display.",
					"<b>Actual Result:</b> After clicking on 'Reset' button,Reset password module not display.", driver);

        	 
        	
 			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = { "desktop" }, description = "User should be able to select either email or SMS option.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_035(HashMap<String, String> testData) throws Exception {

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
			Log.message("6.SignIn as 'Guest' user.");

			Thread.sleep(2000);
	    	trainTravellerPage.enterIrctcId(irctcId);
			Log.message("7.Filling IRCTC ID if option is visible.");

	        trainTravellerPage.clickOnIRCTCResetPassword();
			Log.message("8.Clicked on 'Reset IRCTC password' button.");
			
			trainTravellerPage.selectResetPasswordOption("Mobile");
			Log.message("9.Send Reset IRCTC password on mobile.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user should be able to select either email or SMS option.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("lblMobileResetPwd"),trainTravellerPage ),
					"<b>Actual Result:</b> Successfully selected SMS option to send Reset Password.",
					"<b>Actual Result:</b> Unable to select SMS option to send Reset Password.", driver);

        	
        	trainTravellerPage.selectResetPasswordOption("Email");
			Log.message("9.Send Reset IRCTC password on Email.");

			Log.message("<br>");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("lblEmailResetPwd"),trainTravellerPage ),
					"<b>Actual Result:</b> Successfully selected Email option to send Reset Password.",
					"<b>Actual Result:</b> Unable to select Email option to send Reset Password.", driver);

        	
 			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	
	@Test(groups = { "desktop" }, description = "Should show steps and links to create new IRCTC profile.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_037(HashMap<String, String> testData) throws Exception {

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
			Log.message("6.SignIn as 'Guest' user.");

			Thread.sleep(2000);
	    	trainTravellerPage.enterIrctcId(irctcId);
			Log.message("7.Filling IRCTC ID if option is visible.");

	        trainTravellerPage.clickOnIRCTCResetPassword();
			Log.message("8.Clicked on 'Reset IRCTC password' button.");
			
	        trainTravellerPage.clickOnCreateNewIrctcAccLnk();
	        Log.message("9.Clicked on 'Creat New' IRCTC Account Link.");
	    	
	        String msg= trainTravellerPage.getCreateNewIrctcAccSteps();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify should display steps and links to create new IRCTC profile,after clicking on Create New Link.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("lnkAsIndianRes","lnkAsNonIndianResStep1","lnkAsNonIndianResStep2","lnkAsNonIndianResStep3"),trainTravellerPage ),
					"<b>Actual Result:</b> Steps and Links are displayed to create new IRCTC profile as:"+msg,
					"<b>Actual Result:</b> Steps and Links are not displayed to create new IRCTC profile.", driver);

        	
        	
 			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Should show message 'Wrong userLoginId or mobile.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_038(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String password = testData.get("Password");
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

			trainTravellerPage.loginAsSignedUser(email, password);
			Log.message("6.SignIn as 'Guest' user.");

			Thread.sleep(2000);
	    	trainTravellerPage.enterIrctcId(irctcId);
			Log.message("7.Filling IRCTC ID if option is visible.");

	        trainTravellerPage.clickOnIRCTCResetPassword();
			Log.message("8.Clicked on 'Reset IRCTC password' button.");
			
			trainTravellerPage.selectResetPasswordOption("Mobile");
			Log.message("9.Send Reset IRCTC password on mobile.");
			
			trainTravellerPage.enterMobileNmberToRestIrctcPswd(mobile);
			Log.message("10.Entered Mobile Number to Reset IRCTC password on mobile.");
			String msg = trainTravellerPage.getErrorMsgForInvalidMbleNmbr();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify should show message 'Wrong userLoginId or mobile.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("lblMobileResetPwd"),trainTravellerPage ),
					"<b>Actual Result:</b> Proper validation message is displayed in case of wrong number as:"+msg,
					"<b>Actual Result:</b> Proper validation message is not displayed.", driver);

        	
        	
 			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "Check for modify itinerary.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_043(HashMap<String, String> testData) throws Exception {

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

			Thread.sleep(2000);

			trainTravellerPage.fillTravellerDetails();
			Log.message("8.Filling traveller details on PaxPage.");

			trainTravellerPage.clickToAcceptInsurance();
			Log.message("9.Clicking on 'Accept' Travel Insurance checkbox.");

			trainTravellerPage.checkBookingPolicy();
			Log.message("10.Clicking on 'Accept Booking Policy' checkbox.");

			trainReviewPage = trainTravellerPage.clickOnContinueInPaxPage();
			Log.message("11.Clicking on 'Continue' on PaxPage.");
			
			trainSearchResult = trainReviewPage.clickOnModifyIternary();
			Log.message("12.Clicking on 'Modify Iternary' on ReviewPage.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify after clicking on Modify Iternary button in Review Page,should redirect to TrainSearch page.");
        	Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindTrain"),trainSearchResult ),
					"<b>Actual Result:</b> Successfully navigated back to TrainSearch Page.",
					"<b>Actual Result:</b> Unable to navigated back to TrainSearch Page.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Verify Session strip should come on review page.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_044(HashMap<String, String> testData) throws Exception {

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
			Log.message("11.Clicking on 'Continue' on PaxPage.");
			
			String time = trainReviewPage.getTimeFromStrip();
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Strip should show session for 5 minutes.");
        	Log.assertThat(trainReviewPage.elementLayer.verifyPageElements(Arrays.asList("timeStrip"),trainReviewPage ),
					"<b>Actual Result:</b> Strip showing time session is displayed and the time displayed is '"+time+"' minutes.",
					"<b>Actual Result:</b> Strip showing time session is not displayed.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Check for eWallet div.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_047(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String mobile = testData.get("MobileNumber");
		String irctcId = testData.get("IRCTC_ID");
		String paymentType = testData.get("PaymentType");

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

			Thread.sleep(2000);
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
			Log.message("11.Clicking on 'Continue' on PaxPage.");
			
			paymentPage = trainReviewPage.continueInReviewIternary();
			Log.message("12.Clicking on 'Continue' on ReviewPage.");
			
			paymentPage.selectPaymentType(paymentType);
			Log.message("13.Selected '"+paymentType+"' as payment option on PaymentPage.");

			ArrayList<String> lstMW = paymentPage.getMobileWalletPaymentOptions();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify for eWallet div.");
        	Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("msgMW"),paymentPage ),
					"<b>Actual Result:</b> eWallet payment options are displayed on PaymentPage. \n eWallet Payment Options are:"+lstMW,
					"<b>Actual Result:</b> eWallet payment options are not displayed on PaymentPage.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	
	@Test(groups = { "desktop" }, description = "Should redirect with correct amount on PG.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_048(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String password = testData.get("Password");
		String irctcId = testData.get("IRCTC_ID");
		String paymentType = testData.get("PaymentType");
		String walletType = testData.get("BankName");

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
			Log.message("6.SignIn as 'Registred' user.");

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
			Log.message("11.Clicking on 'Continue' on PaxPage.");
			
			paymentPage = trainReviewPage.continueInReviewIternary();
			Log.message("12.Clicking on 'Continue' on ReviewPage.");
			
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("btnPayNow"),paymentPage ),
					"<b>Actual Result:</b> Succesfully navigated to Payment Page.",
					"<b>Actual Result:</b> Unable to navigated to Payment Page.", driver);

			
			paymentPage.scrollSliderOfEcashRedeem(-80);
            String amt = paymentPage.eCashAmount();
			
			paymentPage.clickingOnRedeemNow();
			Log.message("13.Applying '"+amt+"'eCash on ReviewPage.");

			paymentPage.selectPaymentType(paymentType);
			Log.message("14.Selected '"+paymentType+"' as payment option on PaymentPage.");
			
			paymentPage.selectMobileWallet(walletType);
			Log.message("15.Selected '"+walletType+"' from the payment option on PaymentPage.");
			
			//Getting total amount we are paying
			String totalAmount = paymentPage.gettingTotalPayAmount();
			int tAmt = Integer.parseInt(totalAmount);
            System.out.println(tAmt);
            
        	Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("btnPayNow"),paymentPage ),
					"<b>Actual Result:</b> Succesfully navigated to Payment Page.",
					"<b>Actual Result:</b> Unable to navigated to Payment Page.", driver);

			paymentPage.clickOnPayNow();
			Log.message("16.Clicked 'Pay Now' button on PaymentPage.");
			Log.message("<br>");
			
			//getting total amount on the Oxygen Payment page
			String oxyTotal = paymentPage.getTotalAmountFromOxygen();
			int tAmt1 = Integer.parseInt(oxyTotal);
            System.out.println(tAmt1);

			Log.message("<b>Expected Result:</b> Verify for eCash+ Cash Payment should redirect with correct amount on PG.");
     
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("txtTotalAmtOxygn"),paymentPage)&&tAmt==tAmt1,
					"<b>Actual Result:</b> Correct amount displayed on Payment Gateway.",
					"<b>Actual Result:</b> Correct amount not displayed on Payment Gateway.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Should redirect back to pax page,On session expires.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_051(HashMap<String, String> testData) throws Exception {

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
			Log.message("11.Clicking on 'Continue' on PaxPage.");
			
			paymentPage = trainReviewPage.continueInReviewIternary();
			Log.message("12.Clicking on 'Continue' on ReviewPage.");
		
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Should redirected back to pax page,On session expires.");
        	Log.assertThat(paymentPage.verifyExpireSessionInTrain(),
					"<b>Actual Result:</b> Successfully navigated to paxpage when session expires.",
					"<b>Actual Result:</b> Unable to navigated to paxpage when session expires.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Confirmation page will come only after login on IRCTC successfully.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_052(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String mobile = testData.get("MobileNumber");
		String irctcId = testData.get("IRCTC_ID");
		String paymentType = testData.get("PaymentType");
		String walletType = testData.get("BankName");
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
			Log.message("11.Clicking on 'Continue' on PaxPage.");
			
			paymentPage = trainReviewPage.continueInReviewIternary();
			Log.message("12.Clicking on 'Continue' on ReviewPage.");
		
			paymentPage.selectPaymentType(paymentType);
			Log.message("13.Selected '"+paymentType+"' as payment option on PaymentPage.");
			
			paymentPage.selectMobileWallet(walletType);
			Log.message("14.Selected '"+walletType+"' from the payment option on PaymentPage.");
			
			paymentPage.clickOnPayNow();
			Log.message("15.Clicked 'Pay Now' button on PaymentPage.");
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify confirmation page will come only after login on IRCTC successfully.");
        	Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoOxyGenWallet"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated to payment gateway page.",
					"<b>Actual Result:</b> Unable to navigated to payment gateway page.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "Should show selected train details,after clicking on booking summary drop down.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_053(HashMap<String, String> testData) throws Exception {

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
			Log.message("11.Clicking on 'Continue' on PaxPage.");
			
			paymentPage = trainReviewPage.continueInReviewIternary();
			Log.message("12.Clicking on 'Continue' on ReviewPage.");
			Thread.sleep(2000);
			String detail = paymentPage.gettingBookingSummary_Train();
			System.out.println(detail);
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify selected train details should display,after clicking on booking summary drop down.");
        	Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("modBookingSummry"),paymentPage ),
					"<b>Actual Result:</b> Selected train details displayed in booking summary module.",
					"<b>Actual Result:</b> Selected train details not displayed in booking summary module.", driver);

			
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
			
			Thread.sleep(2000);
			String winHandleBefore = driver.getWindowHandle();
			// Perform the click operation that opens new window
			trainSearchResult.clickingOnFindBusButton();
			Log.message("4.Clicked on 'FindBus' button.");
			

			Set<String> handles = driver.getWindowHandles(); 
			for(String winHandle : handles){
				if(!winHandle.equals(winHandleBefore)){
					driver.switchTo().window(winHandle);
					break;
				}
			}
			
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
	
	
	@Test(groups = { "desktop" },description = "Train Search Results should be sorted by arrival time in asc order", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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
			 
			Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindTrain"), trainSearchResult),
					"<b>Actual Result:</b> Successfully navigated to 'Train Search' page.",
					"<b>Actual Result:</b> Unable to navigate to 'Train Search' page.", driver);

			
	    	 trainSearchResult.verifyFindBusByselectingTrainByIndex();
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
	
	@Test(groups = { "desktop" }, description = "Check for Mobile Number validation.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_036(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String password = testData.get("Password");
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

			trainTravellerPage.loginAsSignedUser(email, password);
			Log.message("6.SignIn as 'Guest' user.");

			Thread.sleep(2000);
	    	trainTravellerPage.enterIrctcId(irctcId);
			Log.message("7.Filling IRCTC ID if option is visible.");

	        trainTravellerPage.clickOnIRCTCResetPassword();
			Log.message("8.Clicked on 'Reset IRCTC password' button.");
			
			trainTravellerPage.selectResetPasswordOption("Mobile");
			Log.message("9.Send Reset IRCTC password on mobile.");
			
			trainTravellerPage.enterMobileNmberToRestIrctcPswd(mobile);
			Log.message("10.Entered Mobile Number to Reset IRCTC password on mobile.");
			String msg = trainTravellerPage.getErrorMsgForInvalidMbleNmbr();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify should show proper validation messages.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("lblMobileResetPwd"),trainTravellerPage ),
					"<b>Actual Result:</b> Proper validation message is displayed in case of invalids number as:"+msg,
					"<b>Actual Result:</b> Proper validation message is not displayed.", driver);

        	
        	
 			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Verify user should be able to change ID and save.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_039(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String password = testData.get("Password");
		String irctcId[] = testData.get("IRCTC_ID").split(",");

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
			Log.message("6.SignIn as 'Guest' user.");

			Thread.sleep(2000);
	    	//trainTravellerPage.enterIrctcId(irctcId[0]);
			Log.message("7.Filling IRCTC ID if option is visible.");

			trainTravellerPage.clickOnEditIrctcIDLink(irctcId[1]);
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
	
	@Test(groups = { "desktop" }, description = "Check for coach Id validations and validation for accepting policy.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_040(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String mobile = testData.get("MobileNumber");
		String Id[] = testData.get("IRCTC_ID").split(",");

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
	    	trainTravellerPage.enterIrctcId(Id[0]);
			Log.message("7.Filling IRCTC ID if option is visible.");

			trainTravellerPage.fillTravellerDetails();
			Log.message("8.Filling traveller details on PaxPage.");
			
			trainTravellerPage.fillCoachId(Id[1]);
			Log.message("9.Entered invalid CoachId on PaxPage.");

		    trainTravellerPage.clickOnContinueBtnInPaxPage();
			Log.message("10.Clicking on 'Continue' on PaxPage.");
			
			String msg = trainTravellerPage.getErrorTxtFromTheToader();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify for coach Id validations and validation for accepting policy.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("btnContinue"),trainTravellerPage ),
					"<b>Actual Result:</b> Unable to navigate to Review Page in case of invalid coach ID and the invalid message is :"+msg,
					"<b>Actual Result:</b> Navigated to Review Page with an invalid coach ID.", driver);
        	
		    trainTravellerPage.deleteCoachId();
			Log.message("11.Deleted Entered CoachId on PaxPage.");

		    trainTravellerPage.clickOnContinueBtnInPaxPage();
			Log.message("12.Clicking on 'Continue' on PaxPage.");
			
			String msg1 = trainTravellerPage.getErrorTxtFromTheToader();

			Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("btnContinue"),trainTravellerPage ),
					"<b>Actual Result:</b> Unable to navigate to Review Page in case of we don't accept terms & conditions  and the invalid message is :"+msg1,
					"<b>Actual Result:</b> Navigated to Review Page without accepting terms & conditions.", driver);
        	
			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Check for modify traveller.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_041(HashMap<String, String> testData) throws Exception {

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
			Log.message("11.Clicking on 'Continue' on PaxPage.");
			
			trainTravellerPage = trainReviewPage.clickOnModifyTraveller();
			Log.message("12.Clicking on 'Modify Traveller' on ReviewPage.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify after clicking on Modify traveller button in Review Page,should redirect back to Pax page.");
        	Log.assertThat(trainTravellerPage.elementLayer.verifyPageElements(Arrays.asList("btnResetPwd"),trainTravellerPage ),
					"<b>Actual Result:</b> Successfully navigated back to Pax Page.",
					"<b>Actual Result:</b> Unable to navigated back to Pax Page.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = { "desktop" }, description = "Check details of Pax, selected train and travel time & date.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_042(HashMap<String, String> testData) throws Exception {

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
			Log.message("11.Clicking on 'Continue' on PaxPage.");
			
			String infoTrain = trainReviewPage.getTripInfo();
			String infoPax = trainReviewPage.getPaxInfo();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify details of Pax, selected train and travel time & date.");
        	Log.assertThat(trainReviewPage.elementLayer.verifyPageElements(Arrays.asList("btnModifyItinerary"),trainReviewPage ),
					"<b>Actual Result:</b> Successfully navigated to Review Page and the details are displayed as: \n TRAIN DETAILS: \n"+infoTrain+"\n\nPAX DETAILS:\n"+infoPax,
					"<b>Actual Result:</b> Unable to navigated back to Pax Page.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Check for all payment options.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_045(HashMap<String, String> testData) throws Exception {

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
			Log.message("11.Clicking on 'Continue' on PaxPage.");
			
			paymentPage = trainReviewPage.continueInReviewIternary();
			Log.message("12.Clicking on 'Continue' on ReviewPage.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify for all payment options.");
        	Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("lstPaymentMetod"),paymentPage ),
					"<b>Actual Result:</b> All the payment options are displayed on PaymentPage.",
					"<b>Actual Result:</b> All the payment options are not displayed on PaymentPage.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = { "desktop" }, description = "Should show proper message on payment page,after cancelling transaction.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_049(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String trainDepartureDate = testData.get("DepartureDate");
		String email = testData.get("EmailAddress");
		String mobile = testData.get("MobileNumber");
		String irctcId = testData.get("IRCTC_ID");
		String paymentType = testData.get("PaymentType");
		String walletType = testData.get("BankName");

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
			Log.message("11.Clicking on 'Continue' on PaxPage.");
			
			paymentPage = trainReviewPage.continueInReviewIternary();
			Log.message("12.Clicking on 'Continue' on ReviewPage.");
			
			paymentPage.selectPaymentType(paymentType);
			Log.message("13.Selected '"+paymentType+"' as payment option on PaymentPage.");
			
			paymentPage.selectMobileWallet(walletType);
			Log.message("14.Selected '"+walletType+"' from the payment option on PaymentPage.");
			
			paymentPage.clickOnPayNow();
			Log.message("15.Clicked 'Pay Now' button on PaymentPage.");

			paymentPage.navigateBackFromMobileWallet(walletType, browser);
			Log.message("16.Cancel transaction on the payment gateway.");

			String errorMsg = paymentPage.getTextFromTheFailedTransactionMsg();
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify after cancelling transaction should show proper message on payment page.");
        	Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("msgFailedTransctn"),paymentPage ),
					"<b>Actual Result:</b> Proper message is displayed on Paymentpage after the failed transaction.:\n ERROR MESSAGE:"+errorMsg,
					"<b>Actual Result:</b> Proper message is not displayed on Paymentpage after the failed transaction.", driver);

			
        	Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
}