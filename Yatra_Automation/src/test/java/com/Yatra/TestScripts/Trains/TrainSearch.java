package com.Yatra.TestScripts.Trains;

//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Bus Search test Cases would be designed in this class 
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
import com.Yatra.Pages.TrainSearchResult;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class TrainSearch {

	EnvironmentPropertiesReader environmentPropertiesReader;
	TrainSearchResult trainSearchResult;
	String webSite;	
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@SuppressWarnings("unused")
	@Test(groups = { "desktop" }, description = "Search Train ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_001(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String password = testData.get("Password");
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
			
			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@SuppressWarnings("unused")
	@Test(groups = { "desktop" }, description = "Search train", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_002(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String password = testData.get("Password");
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
			homePage.incorrectCity();
			Log.message("2.Selected Invalid city .");

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	

	@SuppressWarnings("unused")
	@Test(groups = { "desktop" }, description = "Search Train ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_003(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String password = testData.get("Password");
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
			
			
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	

	@SuppressWarnings("unused")
	@Test(groups = { "desktop" }, description = "Check for message in case of no result ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Train_004(HashMap<String, String> testData) throws Exception {

		String browser = testData.get("browser");
		String password = testData.get("Password");
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

			trainSearchResult.trainMessageBox();
			Log.message("4.Successfully verified the message box for 'Train Search' ");		
			
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
	


}