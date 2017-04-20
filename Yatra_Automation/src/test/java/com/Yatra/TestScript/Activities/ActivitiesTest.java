package com.Yatra.TestScript.Activities;

//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Activities test Cases would be designed in this class 
//Creator        :   Aspire Team
//Create         :   
//Modified on/By :   -
//-----------------------------------------------------------------------------------------------------------

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Yatra.Pages.ActivityDetailPage;
import com.Yatra.Pages.HomePage;
import com.Yatra.Pages.PaymentPageBus;
import com.Yatra.Pages.ReviewPageBus;
import com.Yatra.Pages.SearchResultActivites;
import com.Yatra.Pages.SearchResultBus;
import com.Yatra.Pages.TravellerPageBus;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class ActivitiesTest {

	EnvironmentPropertiesReader environmentPropertiesReader;
	String webSite;
	SearchResultActivites searchResultActivites;
	ActivityDetailPage activityDetailPage;
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(groups = {
			"desktop" }, description = "Verify Domestic Activities", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_001(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickActivities();
			Log.message("2. Clicked On Activities Link!");

			homePage.enterActivitiesOrigin(origin);
			Log.message("3. Entered Origin!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should see the auto suggestion");
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("listautoSuggestion"), homePage),
					"<b>Actual Result:</b> Auto Suggestion is visible",
					"<b>Actual Result:</b> Auto Suggestion is  not visible", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify International Activities", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_002(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickActivities();
			Log.message("2. Clicked On Activities Link!");

			homePage.enterActivitiesOrigin(origin);
			Log.message("3. Entered Origin!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should see the auto suggestion");
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("listautoSuggestion"), homePage),
					"<b>Actual Result:</b> Auto Suggestion is visible",
					"<b>Actual Result:</b> Auto Suggestion is  not visible", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify auto suggestion of search results", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_003(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickActivities();
			Log.message("2. Clicked On Activities Link!");

			homePage.enterActivitiesOrigin(origin);
			Log.message("3. Entered Origin!");
			String Activities = homePage.getTextAutoSuggestionCityName();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should see the auto suggestion and Place Name");
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("listautoSuggestion"), homePage),
					"<b>Actual Result:</b> Auto Suggestion is visible with Place name and activities are listed as : "
							+ Activities,
					"<b>Actual Result:</b> Auto Suggestion is  not visible", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify User should naviagte to SRP page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_004(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickActivities();
			Log.message("2. Clicked On Activities Link!");

			homePage.enterActivitiesOrigin(origin);
			Log.message("3. Entered Origin!");

			searchResultActivites = homePage.clickOnSearchActivites();
			Log.message("4. Clicked On Search Button!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should navigate to Search Result Page");
			Log.assertThat(
					searchResultActivites.elementLayer.verifyPageElements(Arrays.asList("btnModifySearch"),
							searchResultActivites),
					"<b>Actual Result:</b> User navigated to Search Result Page",
					"<b>Actual Result:</b> User is not navigated to Search Result Page", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
			"desktop" }, description = "Verify error message in case of no result", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_005(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickActivities();
			Log.message("2. Clicked On Activities Link!");

			homePage.enterActivitiesOrigin(origin);
			Log.message("3. Entered Origin!");

			searchResultActivites = homePage.clickOnSearchActivites();
			Log.message("4. Clicked On Search Button!");
			String Error = searchResultActivites.getTextErrorMessage();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should error message in case of no result");
			Log.assertThat(
					searchResultActivites.elementLayer.verifyPageElements(Arrays.asList("txtNoResultFound"),
							searchResultActivites),
					"<b>Actual Result:</b> User see an error message if Origin is mot correct and Messasge is Displayed as : "
							+ Error,
					"<b>Actual Result:</b> No Error Message is Visible on Search result page", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
}// ActivitiesTestCasesEnd