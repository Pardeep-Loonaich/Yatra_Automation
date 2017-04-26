package com.Yatra.TestScript.Trains;

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
import com.Yatra.Pages.TrainSearchResult;
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
					"<b>Actual Result:</b> Successfully navigated to 'Train Search' page and the available seats for the selected train are:"+trainSearchResult.checkAvailableSeatsBySelectingTrainNClassByRow(2),
					"<b>Actual Result:</b> Unable to navigate to 'Train Search' page and the availabe seats are not visible.", driver);

			

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


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify that after selecting train,it should show availability of seats..");
        	Log.message("<br>");
        	/*Log.assertThat(trainSearchResult.elementLayer.verifyPageElements(Arrays.asList("btnFindTrain"), trainSearchResult),
					"<b>Actual Result:</b> Successfully navigated to 'Train Search' page and the available seats for the selected train are:"+trainSearchResult.checkAvailableSeatsBySelectingTrainNClassByRow(1),
					"<b>Actual Result:</b> Unable to navigate to 'Train Search' page and the availabe seats are not visible.", driver);
*/
			


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

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
}