package com.Yatra.TestScript.Activities;

//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Activities test Cases would be designed in this class 
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

import com.Yatra.Pages.ActivitiesReviewPage;
import com.Yatra.Pages.ActivitiesTravellerPage;
import com.Yatra.Pages.ActivityDetailPage;
import com.Yatra.Pages.HomePage;
import com.Yatra.Pages.PaymentPage;
import com.Yatra.Pages.SearchResultActivites;
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
	ActivitiesReviewPage activitiesReviewPage;
	ActivitiesTravellerPage activitiesTravellerPage;
	PaymentPage paymentPage;

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
			System.out.println(homePage);
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

	@Test(groups = {
	"desktop" }, description = "Verify results should be sorted by price in asc order.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_006(HashMap<String, String> testData) throws Exception {

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
			Log.message("<b>Expected Result:</b> Verify results should be sorted by price in asc order.");
			Log.assertThat(searchResultActivites.getSortedPrice(),
					"<b>Actual Result:</b> Price is sorted in asc order. ",
					"<b>Actual Result:</b> Price is not sorted in asc order.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = {
	"desktop" }, description = "Verify results should be sorted by Category.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_007(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String categoryNme = testData.get("CategoryName");

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

			String categryNumb = searchResultActivites.selectCategory(categoryNme);
			Log.message("5. Selecting category as 'Classes & Workshops' and get searched category number.");

			String resultNum = searchResultActivites.gettingTxtFrmResultFoundStrip();
			Log.message("6. Getting selected result category number from the result strip.");
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify results should be sorted by Category.");
			Log.assertThat(resultNum.contains(categryNumb),
					"<b>Actual Result:</b> The result is sorted by Category. ",
					"<b>Actual Result:</b> The result is not sorted by Category.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Verify result should be sorted by TRANSFERS & TRANSPORT.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_008(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String categoryNme = testData.get("CategoryName");

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

			String categryNumb = searchResultActivites.selectCategory(categoryNme);
			Log.message("5. Selecting category as 'Transfers & Transport' and get searched category number.");

			String resultNum = searchResultActivites.gettingTxtFrmResultFoundStrip();
			Log.message("6. Getting selected result category number from the result strip.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify result should be sorted by TRANSFERS & TRANSPORT.");
			Log.assertThat(resultNum.contains(categryNumb),
					"<b>Actual Result:</b> The result is sorted by TRANSFERS & TRANSPORT category. ",
					"<b>Actual Result:</b> The result is not sorted by TRANSFERS & TRANSPORT category.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Verify result should be sorted by OUTDOOR FUN.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_009(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String categoryNme = testData.get("CategoryName");

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

			String categryNumb = searchResultActivites.selectCategory(categoryNme);
			Log.message("5. Selecting category as 'OUTDOOR FUN' and get searched category number.");

			String resultNum = searchResultActivites.gettingTxtFrmResultFoundStrip();
			Log.message("6. Getting selected result category number from the result strip.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify result should be sorted by OUTDOOR FUN.");
			Log.assertThat(resultNum.contains(categryNumb),
					"<b>Actual Result:</b> The result is sorted by OUTDOOR FUN category. ",
					"<b>Actual Result:</b> The result is not sorted by OUTDOOR FUN category.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = {"desktop" }, description = "Verify result should be sorted by Extreme Adventure.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_010(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String categoryNme = testData.get("CategoryName");

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

			String categryNumb = searchResultActivites.selectCategory(categoryNme);
			Log.message("5. Selecting category as 'Extreme Adventure' and get searched category number.");

			String resultNum = searchResultActivites.gettingTxtFrmResultFoundStrip();
			Log.message("6. Getting selected result category number from the result strip.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify result should be sorted by Extreme Adventure.");
			Log.assertThat(resultNum.contains(categryNumb),
					"<b>Actual Result:</b> The result is sorted by Extreme Adventure category. ",
					"<b>Actual Result:</b> The result is not sorted by Extreme Adventure category.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Verify result should be sorted by Events and Shows.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_011(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String categoryNme = testData.get("CategoryName");

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

			String categryNumb = searchResultActivites.selectCategory(categoryNme);
			Log.message("5. Selecting category as 'Events and Shows' and get searched category number.");

			String resultNum = searchResultActivites.gettingTxtFrmResultFoundStrip();
			Log.message("6. Getting selected result category number from the result strip.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify result should be sorted by Events and Shows.");
			Log.assertThat(resultNum.contains(categryNumb),
					"<b>Actual Result:</b> The result is sorted by Events and Shows category. ",
					"<b>Actual Result:</b> The result is not sorted by Events and Shows category.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Verify result should be sorted by Tours & Sightseeing.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_012(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String categoryNme = testData.get("CategoryName");

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

			String categryNumb = searchResultActivites.selectCategory(categoryNme);
			Log.message("5. Selecting category as 'Tours & Sightseeing' and get searched category number.");

			String resultNum = searchResultActivites.gettingTxtFrmResultFoundStrip();
			Log.message("6. Getting selected result category number from the result strip.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify result should be sorted by Tours & Sightseeing.");
			Log.assertThat(resultNum.contains(categryNumb),
					"<b>Actual Result:</b> The result is sorted by Tours & Sightseeing category. ",
					"<b>Actual Result:</b> The result is not sorted by Tours & Sightseeing category.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Verify result should be sorted by Food.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_013(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String categoryNme = testData.get("CategoryName");

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

			String categryNumb = searchResultActivites.selectCategory(categoryNme);
			Log.message("5. Selecting category as 'Food' and get searched category number.");

			String resultNum = searchResultActivites.gettingTxtFrmResultFoundStrip();
			Log.message("6. Getting selected result category number from the result strip.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify result should be sorted by Food.");
			Log.assertThat(resultNum.contains(categryNumb),
					"<b>Actual Result:</b> The result is sorted by Food category. ",
					"<b>Actual Result:</b> The result is not sorted by Food category.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Verify result should be sorted by Recommended.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_014(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String categoryNme = testData.get("CategoryName");

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
			Log.message("<b>Expected Result:</b> Verify result should be sorted by Recommended.");
			Log.assertThat(searchResultActivites.verifySortByGivenCategory(categoryNme),
					"<b>Actual Result:</b> The result is sorted by Recommended category. ",
					"<b>Actual Result:</b> The result is not sorted by Recommended category.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Verify result should be sorted by Popular.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_015(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String categoryNme = testData.get("CategoryName");

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
			Log.message("<b>Expected Result:</b> Verify result should be sorted by Popular.");
			Log.assertThat(searchResultActivites.verifySortByGivenCategory(categoryNme),
					"<b>Actual Result:</b> The result is sorted by Popular category. ",
					"<b>Actual Result:</b> The result is not sorted by Popular category.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verify Modify Search", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_016(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin[] = testData.get("Origin").split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step1: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			homePage.clickActivities();
			Log.message("2. Clicked On Activities Link!");

			homePage.enterActivitiesOrigin(origin[0]);
			Log.message("3. Entered Origin!");

			searchResultActivites = homePage.clickOnSearchActivites();
			Log.message("4. Clicked On Search Button!");

			searchResultActivites.clickOnModifySearchButton();
			Log.message("5. Clicked On Modify Search!");

			searchResultActivites.enterDestinationInModifySearch(origin[1]);
			Log.message("6. Entered new Origin in Modify Search!");
			String newCityDescription = searchResultActivites.getTextActivityDetailsByTileIndex(4);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should see new activity displayed");
			Log.assertThat(
					searchResultActivites.elementLayer.verifyPageElements(Arrays.asList("btnModifySearch"),
							searchResultActivites),
					"<b>Actual Result:</b> User see new Activity dispalyed and Description is Displayed as : "
							+ newCityDescription,
							"<b>Actual Result:</b> User did not see new activities and SRP", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verify Searched activity should be displayed", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_017(HashMap<String, String> testData) throws Exception {

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
			String ActvityDetailsOnSearchResult = searchResultActivites.getTextActivityDetailsByTileIndex(5);
			Log.message(ActvityDetailsOnSearchResult);

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(5);
			Log.message("5. Clicked On Book Now Button!");
			String ActvityDetailsOnDeatilsPage = activityDetailPage.getTexActivityDetails();
			Log.message(ActvityDetailsOnDeatilsPage);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should see same searched activity");
			Log.assertThat(ActvityDetailsOnSearchResult.equalsIgnoreCase(ActvityDetailsOnDeatilsPage),
					"<b>Actual Result:</b> User see the same activity dispalyed and First Activity Description is :"
							+ ActvityDetailsOnSearchResult + " Description on Activity Deatil Page is "
							+ ActvityDetailsOnDeatilsPage,
							"<b>Actual Result:</b> User did not see new activities", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verify Photos should be displayed", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_018(HashMap<String, String> testData) throws Exception {

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(5);
			Log.message("5. Clicked On Book Now Button!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should see Photos in Activity Details Page");
			Log.assertThat(
					activityDetailPage.elementLayer.verifyPageElements(Arrays.asList("activityPhotos"),
							activityDetailPage),
					"<b>Actual Result:</b> Photos are visible to the user",
					"<b>Actual Result:</b> Photos are not visible to the user", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verify Description should be displayed", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_019(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String activitydetails = testData.get("ActivityDetails");

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(5);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.ClickOnHeaderLinks(activitydetails);
			Log.message("6. Clicked On Description Tab!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should see Description in Activity Details Page");
			Log.assertThat(
					activityDetailPage.elementLayer.verifyPageElements(Arrays.asList("txtDescrption"),
							activityDetailPage),
					"<b>Actual Result:</b> Description is visible to the user",
					"<b>Actual Result:</b> Description is not visible to the user", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verify Additional Info should be displayed", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_020(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String activitydetails = testData.get("ActivityDetails");

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(5);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.ClickOnHeaderLinks(activitydetails);
			Log.message("6. Clicked On Additional info Tab!");
			String info = activityDetailPage.getTexActivityAdditionalInfo();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should see Additional Info in Activity Details Page");
			Log.assertThat(
					activityDetailPage.elementLayer.verifyPageElements(Arrays.asList("txtAdditionalInfo"),
							activityDetailPage),
					"<b>Actual Result:</b> Additional Info is visible to the user ans info is " + info + "/n",
					"<b>Actual Result:</b> Additional Info is not visible to the user", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verify Similar Activities should be displayed", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_021(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String activitydetails = testData.get("ActivityDetails");

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(5);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.ClickOnHeaderLinks(activitydetails);
			Log.message("6. Clicked On Similar Activities Tab!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should see Similar Activities in Activity Details Page");
			Log.assertThat(
					activityDetailPage.elementLayer.verifyPageElements(Arrays.asList("fldSimilarActivities"),
							activityDetailPage),
					"<b>Actual Result:</b> Similar Activities are visible to the user",
					"<b>Actual Result:</b> Similar Activities are not visible to the user", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verify after Clicking on Book Now date Pop Up should be displayed", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_022(HashMap<String, String> testData) throws Exception {

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(5);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User should see the date pop up after clicking on book Now Button");
			Thread.sleep(4000);
			Log.assertThat(
					activityDetailPage.elementLayer.verifyPageElements(Arrays.asList("dateCalendar"),
							activityDetailPage),
					"<b>Actual Result:</b> Date Pop Up is visible after clicking on Book Now Button",
					"<b>Actual Result:</b> Date Pop Up is not visible after clicking on Book Now Button", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Validate Traveller packs option is there", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_023(HashMap<String, String> testData) throws Exception {

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(5);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validate Traveller packs option is there");
			Thread.sleep(4000);
			Log.assertThat(
					activityDetailPage.elementLayer.verifyPageElements(Arrays.asList("PaxInfo"), activityDetailPage),
					"<b>Actual Result:</b> Traveller packs is visible after clicking on Book Now Button",
					"<b>Actual Result:</b> Traveller packs is not visible after clicking on Book Now Button", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verify after Clicking on Book Now date Pop Up should be displayed in red colour", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_024(HashMap<String, String> testData) throws Exception {

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(5);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnSelectActivitiesDate();
			Log.message("7. Selected Activity Date!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verify after Clicking on Book Now date Pop Up should be displayed in red colour");
			Log.assertThat(activityDetailPage.verifySelectedDateColour(),
					"<b>Actual Result:</b> Date Pop Up is visible after clicking on Book Now Button",
					"<b>Actual Result:</b> Date Pop Up is not visible after clicking on Book Now Button", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verify Activity selection option is not there that date should be grey", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_025(HashMap<String, String> testData) throws Exception {

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(2);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");
			String ErrorMessage = activityDetailPage.getTextErrorMessageNoActivities();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Activity selection option is not there that date should be grey");
			Log.assertThat(
					activityDetailPage.elementLayer.verifyPageElements(Arrays.asList("noActivity"), activityDetailPage),
					"<b>Actual Result:</b> After Clicking On Book Now dates are Grey and a error message is diplayed as: "
							+ ErrorMessage,
							"<b>Actual Result:</b> After Clicking On Book Now dates no dates are Grey", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	

	@Test(groups = {
	"desktop" }, description = "Verification after Clicking on show Cancellation policy link Cancellation policy should open", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_027(HashMap<String, String> testData) throws Exception {

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(2);
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(3000);
			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("7. Clicked On Check Availability Button!");

			activityDetailPage.clickOnCancellationPolicyButton();
			Log.message("8. Clicked On Cancellation Policy Button!");
			String Text = activityDetailPage.getTextCancellationPolicy();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b>Verification after Clicking on show Cancellation policy link Cancellation policy should open");
			Log.assertThat(
					activityDetailPage.elementLayer.verifyPageElements(Arrays.asList("btnCancellationPolicy"),
							activityDetailPage),
					"<b>Actual Result:</b> After Clicking On show Cancellation policy link Cancellation policy text is visible to the user with details :"
							+ Text,
							"<b>Actual Result:</b> After Clicking On show Cancellation policy link no text is visible to the user",
							driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verification after Clicking on hide Cancellation policy link Cancellation policy text should not appear", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_028(HashMap<String, String> testData) throws Exception {

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(2);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("7. Clicked On Check Availability Button!");

			activityDetailPage.clickOnCancellationPolicyButton();
			Log.message("8. Clicked On Show Cancellation Policy Button!");

			Thread.sleep(2000);
			activityDetailPage.clickOnHideCancellationPolicyButton();
			Log.message("9. Clicked On Hide Cancellation Policy Button!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verification after Clicking on hide Cancellation policy link Cancellation policy text should not appear");
			Log.assertThat(
					activityDetailPage.elementLayer.verifyPageElements(Arrays.asList("btnCancellationPolicy"),
							activityDetailPage),
					"<b>Actual Result:</b> After Clicking On hide Cancellation policy link Cancellation policy text is not visible to the user",
					"<b>Actual Result:</b> After Clicking On hide Cancellation policy link Cancellation policy text is visible to the user",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = {
	"desktop" }, description = "Verification pax Modifying option is there.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_029(HashMap<String, String> testData) throws Exception {

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(2);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("7. Clicked On Check Availability Button!");

			

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verification Pax Modifying option is there.");
			Log.assertThat(
					activityDetailPage.elementLayer.verifyPageElements(Arrays.asList("lnkChngeDateOrPax"),
							activityDetailPage),
					"<b>Actual Result:</b> Pax Modifying option is displayed on the Product Description page.",
					"<b>Actual Result:</b> Pax Modifying option is displayed on the Product Description page.",
					driver);
	
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = {
	"desktop" }, description = "Verification after Clicking on Continue in Review Page User Should redirect to Traveller page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_030(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String email = testData.get("EmailAddress");
		String phoneNumber = testData.get("MobileNumber");

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(5);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("7. Clicked On Check Availability Button!");

			activitiesReviewPage = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("8. Clicked On Book Now After Check Availability Button!");

			activitiesReviewPage.clickOnContinue();
			Log.message("9. Clicked On Continue!");

			activitiesTravellerPage = activitiesReviewPage.loginAsGuestUser(email, phoneNumber);
			Log.message("10. Filled Travellers Deatils!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verification after Clicking on Continue in Review Page User Should redirect to Traveller page");
			Log.assertThat(
					activitiesTravellerPage.elementLayer.verifyPageElements(Arrays.asList("btnContinueInTravellr"),
							activitiesTravellerPage),
					"<b>Actual Result:</b> After Clicking On Continue Button in Review Page user is redirected to Traveller page",
					"<b>Actual Result:</b> After Clicking On Continue Button in Review Page user is not redirected to Traveller page",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verification after Click on enter promocode", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_031(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String promoCode = testData.get("PromoCode");

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(4);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("7. Clicked On Check Availability Button!");

			activitiesReviewPage = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("8. Clicked On Book Now After Check Availability Button!");

			activitiesReviewPage.enterPromoCode(promoCode);
			Log.message("9. Entered Promo Code!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verification after Click on enter promocode");
			Log.assertThat(
					activitiesReviewPage.elementLayer.verifyPageElements(Arrays.asList("txtInvalidPromoCode"),
							activitiesReviewPage),
					"<b>Actual Result:</b> User is able to enter Promo code",
					"<b>Actual Result:</b> User is not able to enter Promo code", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {
	"desktop" }, description = "Verification after Entering Invalid promocode", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_033(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String promoCode = testData.get("PromoCode");

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(6);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("7. Clicked On Check Availability Button!");

			activitiesReviewPage = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("8. Clicked On Book Now After Check Availability Button!");

			activitiesReviewPage.enterPromoCode(promoCode);
			Log.message("9. Entered Promo Code!");
			String Error = activitiesReviewPage.getTextInvalidPromoCode();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verification after Entering Invalid promocode");
			Log.assertThat(
					activitiesReviewPage.elementLayer.verifyPageElements(Arrays.asList("btnContinueInReviewPage"),
							activitiesReviewPage),
					"<b>Actual Result:</b> If user input Invalid Promo Code and Error Message is displayed as :" + Error,
					"<b>Actual Result:</b> If user input Invalid Promo Code and No error Message is displayed", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Verify User will be directed to sign-in page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_035(HashMap<String, String> testData) throws Exception {

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

			Thread.sleep(2000);

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(2);
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(2000);

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnSelectActivitiesDate();
			Log.message("7. Selected Activity Date!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("8. Clicked on the check Availability for the selected Activity Date!");

			activitiesReviewPage = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("9. Clicked on 'Book Now' button in the Select product popup.");

			activitiesReviewPage.clickOnContinue();
			Log.message("10. Clicked on 'Continue' button in the ReviewPage .");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify User will be directed to sign-in page");
			Log.assertThat(activitiesReviewPage.elementLayer.verifyPageElements(Arrays.asList("divLoginContent"), activitiesReviewPage),
					"<b>Actual Result:</b> Successfully navigated to Sign-in Page.",
					"<b>Actual Result:</b> Unable to navigated to Sign-in Page.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = {"desktop" }, description = "Verify user should be able to enter email adress", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_036(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String email = testData.get("EmailAddress");

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

			Thread.sleep(2000);

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(2);
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(2000);

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnSelectActivitiesDate();
			Log.message("7. Selected Activity Date!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("8. Clicked on the check Availability for the selected Activity Date!");

			activitiesReviewPage = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("9. Clicked on 'Book Now' button in the Select product popup.");

			activitiesReviewPage.clickOnContinue();
			Log.message("10. Clicked on 'Continue' button in the ReviewPage .");

			activitiesReviewPage.enterEmailAddress(email);
			Log.message("11. Entered Email address on the SignIn Popup.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user should be able to enter email adress");
			Log.assertThat(activitiesReviewPage.elementLayer.verifyPageElements(Arrays.asList("txtEmailId"), activitiesReviewPage),
					"<b>Actual Result:</b> Successfully entered Email address on Sign-in Page and the error message after entering inavlid email address is:."+activitiesReviewPage.enterInvlidEmailAddressErrorMsg(),
					"<b>Actual Result:</b> Unable to enter Email address on Sign-in Page.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = {"desktop" }, description = "Verify if user have yatra account then password field is required.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_037(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String email = testData.get("EmailAddress");

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

			Thread.sleep(2000);
			activityDetailPage = searchResultActivites.ClickBookNowByIndex(2);
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(2000);
			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnSelectActivitiesDate();
			Log.message("7. Selected Activity Date!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("8. Clicked on the check Availability for the selected Activity Date!");

			activitiesReviewPage = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("9. Clicked on 'Book Now' button in the Select product popup.");

			activitiesReviewPage.clickOnContinue();
			Log.message("10. Clicked on 'Continue' button in the ReviewPage .");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify if user have yatra account then password field is required.");
			Log.assertThat(activitiesReviewPage.elementLayer.verifyPageElementsDoNotExist(Arrays.asList("txtPasswrd"), activitiesReviewPage),
					"<b>Actual Result:</b> Password field is not displayed on Sign-In Page.",
					"<b>Actual Result:</b> Password field is displayed on Sign-In Page.", driver);

			activitiesReviewPage.enterEmailAddress(email);
			Log.message("11. Entered Email address on the SignIn Popup.");

			activitiesReviewPage.clickOnYatraAccountChkbox();
			Log.message("12. Clicking on having 'Yatra Account' checkbox on the SignIn Popup.");

			Log.assertThat(activitiesReviewPage.elementLayer.verifyPageElements(Arrays.asList("txtPasswrd"), activitiesReviewPage),
					"<b>Actual Result:</b> Password field is displayed on Sign-In Page after clicking the checkbox.",
					"<b>Actual Result:</b> Password field is not displayed on Sign-In Page after clicking the checkbox.", driver);


			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = {"desktop" }, description = "Verify if user do not have yatra account then it should be able to enter the phone number.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_038(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String email = testData.get("EmailAddress");

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

			Thread.sleep(2000);

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(2);
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(2000);

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnSelectActivitiesDate();
			Log.message("7. Selected Activity Date!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("8. Clicked on the check Availability for the selected Activity Date!");

			activitiesReviewPage = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("9. Clicked on 'Book Now' button in the Select product popup.");

			activitiesReviewPage.clickOnContinue();
			Log.message("10. Clicked on 'Continue' button in the ReviewPage .");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify if user have yatra account then password field is required.");

			activitiesReviewPage.enterEmailAddress(email);
			Log.message("11. Entered Email address on the SignIn Popup.");

			boolean result = activitiesReviewPage.verifyYatraUserCheckboxInLogin();

			Log.assertThat((activitiesReviewPage.elementLayer.verifyPageElements(Arrays.asList("txtMobileNum"), activitiesReviewPage)&&result==false),
					"<b>Actual Result:</b> PhoneNumber field is displayed on Sign-In Page if user dont have Yatra Account.",
					"<b>Actual Result:</b> PhoneNumber field is not displayed on Sign-In Page if user have Yatra Account.", driver);


			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = {"desktop" }, description = "Verify user should be able to see the facebook.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_039(HashMap<String, String> testData) throws Exception {

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

			Thread.sleep(2000);

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(2);
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(2000);

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnSelectActivitiesDate();
			Log.message("7. Selected Activity Date!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("8. Clicked on the check Availability for the selected Activity Date!");

			activitiesReviewPage = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("9. Clicked on 'Book Now' button in the Select product popup.");

			activitiesReviewPage.clickOnContinue();
			Log.message("10. Clicked on 'Continue' button in the ReviewPage .");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user should be able to see the facebook.");
			Log.assertThat(activitiesReviewPage.elementLayer.verifyPageElements(Arrays.asList("logoFB"), activitiesReviewPage),
					"<b>Actual Result:</b> Facebook button is displayed on Sign-In Page.",
					"<b>Actual Result:</b> Facebook button is not displayed on Sign-In Page.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = {"desktop" }, description = "Verify user is redirected to payment page.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_040(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String email = testData.get("EmailAddress");
		String number = testData.get("MobileNumber");

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

			Thread.sleep(2000);
			activityDetailPage = searchResultActivites.ClickBookNowByIndex(2);
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(2000);
			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnSelectActivitiesDate();
			Log.message("7. Selected Activity Date!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("8. Clicked on the check Availability for the selected Activity Date!");
			Thread.sleep(2000);

			activitiesReviewPage = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("9. Clicked on 'Book Now' button in the Select product popup.");

			activitiesReviewPage.clickOnContinue();
			Log.message("10. Clicked on 'Continue' button in the ReviewPage.");

			activitiesTravellerPage = activitiesReviewPage.loginAsGuestUser(email, number);
			Log.message("11. Login as 'Guest' user.");

			activitiesTravellerPage.fillTravellerDetails();
			Log.message("12. Filled traveller details.");
			
			activitiesTravellerPage.clickOnHotelNotRequiredChkbox();
			paymentPage = activitiesTravellerPage.clickOnContinueInTravellerPage(); 
			Log.message("13. Clicked on 'Continue' button in the TravellerPage.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is redirected to payment page.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("btnPayNow"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated to PaymentPage.",
					"<b>Actual Result:</b> Unable to navigated to PaymentPage.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = {"desktop" }, description = "Verify if user is registerd with yatra then login option displayed.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_041(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String email = testData.get("EmailAddress");

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

			Thread.sleep(2000);

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(2);
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(2000);

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnSelectActivitiesDate();
			Log.message("7. Selected Activity Date!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("8. Clicked on the check Availability for the selected Activity Date!");
			Thread.sleep(2000);

			activitiesReviewPage = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("9. Clicked on 'Book Now' button in the Select product popup.");

			activitiesReviewPage.clickOnContinue();
			Log.message("10. Clicked on 'Continue' button in the ReviewPage.");

			activitiesReviewPage.enterEmailAddress(email);
			Log.message("11. Enter 'Email' address in SignIn Page.");

			activitiesReviewPage.clickOnYatraAccountChkbox();
			Log.message("12. Select 'Having Yatra's Account' checkbox in SignIn Page.");


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify if user is registerd with yatra then login option displayed.");
			Log.assertThat(activitiesReviewPage.elementLayer.verifyPageElements(Arrays.asList("btnLogin"), activitiesReviewPage),
					"<b>Actual Result:</b> Login option is displayed for the registered user.",
					"<b>Actual Result:</b> Login option is not displayed for the registered user.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = {"desktop" }, description = "Verify all payment options.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_042(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String email = testData.get("EmailAddress");
		String number = testData.get("MobileNumber");

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

			Thread.sleep(2000);

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(3);
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(2000);

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnSelectActivitiesDate();
			Log.message("7. Selected Activity Date!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("8. Clicked on the check Availability for the selected Activity Date!");
			Thread.sleep(2000);

			activitiesReviewPage = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("9. Clicked on 'Book Now' button in the Select product popup.");

			activitiesReviewPage.clickOnContinue();
			Log.message("10. Clicked on 'Continue' button in the ReviewPage.");

			activitiesTravellerPage = activitiesReviewPage.loginAsGuestUser(email, number);
			Log.message("11. Login as 'Guest' user.");

			activitiesTravellerPage.fillTravellerDetails();
			Log.message("12. Filled traveller details.");
			
			activitiesTravellerPage.clickOnHotelNotRequiredChkbox();

			paymentPage = activitiesTravellerPage.clickOnContinueInTravellerPage(); 
			Log.message("13. Clicked on 'Continue' button in the TravellerPage.");


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify all payment options.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("lstPaymentMetod"), paymentPage),
					"<b>Actual Result:</b> All the Payment options are displayed.",
					"<b>Actual Result:</b> All the Payment options are not displayed.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	
	@Test(groups = {
	"desktop" }, description = "Verify Domestic Activities", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Activities_026(HashMap<String, String> testData) throws Exception {

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

			activityDetailPage = searchResultActivites.ClickBookNowByIndex(2);
			Log.message("5. Clicked On Book Now Button!");

			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("7. Clicked On Check Availability Button!");

			activitiesReviewPage = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("8. Clicked On Book Now After Check Availability Button!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verification after Clicking on Check Availability User Should redirect to product description page");
			Log.assertThat(
					activitiesReviewPage.elementLayer.verifyPageElements(Arrays.asList("btnContinueInReviewPage"),
							activitiesReviewPage),
					"<b>Actual Result:</b> After Clicking On Check Availability Button user is redirected to Product Description Page",
					"<b>Actual Result:</b> After Clicking On Check Availability Button user is not redirected to Product Description Page",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}	
}// ActivitiesTestCasesEnd
