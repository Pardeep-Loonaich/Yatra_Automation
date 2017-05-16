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
import com.Yatra.Pages.ProductDescriptionActivities;
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
	ProductDescriptionActivities productDescriptionActivities;
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

			activityDetailPage = searchResultActivites.ClickBookNow();
			Log.message("5. Clicked On Book Now Button!");
			String ActvityDetailsOnDeatilsPage = activityDetailPage.getTexActivityDetails();

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

			activityDetailPage = searchResultActivites.ClickBookNow();
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

			activityDetailPage = searchResultActivites.ClickBookNow();
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

			activityDetailPage = searchResultActivites.ClickBookNow();
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

			activityDetailPage = searchResultActivites.ClickBookNow();
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

			activityDetailPage = searchResultActivites.ClickBookNow();
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(3000);
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

			activityDetailPage = searchResultActivites.ClickBookNow();
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(3000);
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

			activityDetailPage = searchResultActivites.ClickBookNow();
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(3000);
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

			activityDetailPage = searchResultActivites.ClickBookNow();
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(3000);
			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");
			String ErrorMessage = activityDetailPage.getTextErrorMessageNoActivities();

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verify Activity selection option is not there that date should be grey");
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
			"desktop" }, description = "Verification after Clicking on Check Availability User Should redirect to product description page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			activityDetailPage = searchResultActivites.ClickBookNow();
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(3000);
			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("7. Clicked On Check Availability Button!");

			productDescriptionActivities = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("8. Clicked On Book Now After Check Availability Button!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verification after Clicking on Check Availability User Should redirect to product description page");
			Log.assertThat(
					productDescriptionActivities.elementLayer.verifyPageElements(Arrays.asList("btnContinue"),
							productDescriptionActivities),
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

	@Test(groups = {
			"desktop" }, description = "Verification after Clicking on Check Availability User Should redirect to product description page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			activityDetailPage = searchResultActivites.ClickBookNow();
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(3000);
			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("7. Clicked On Check Availability Button!");

			productDescriptionActivities = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("8. Clicked On Book Now After Check Availability Button!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verification after Clicking on Check Availability User Should redirect to product description page");
			Log.assertThat(
					productDescriptionActivities.elementLayer.verifyPageElements(Arrays.asList("btnContinue"),
							productDescriptionActivities),
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

	@Test(groups = {
			"desktop" }, description = "Verification after Clicking on Check Availability User Should redirect to product description page", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
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

			activityDetailPage = searchResultActivites.ClickBookNow();
			Log.message("5. Clicked On Book Now Button!");

			Thread.sleep(3000);
			activityDetailPage.clickOnBookNowButton();
			Log.message("6. Clicked On Book Now Button On Activity Detail Page!");

			activityDetailPage.clickOnCheckAvailability();
			Log.message("7. Clicked On Check Availability Button!");

			productDescriptionActivities = activityDetailPage.clickOnBookNowAfterCheckAvailability();
			Log.message("8. Clicked On Book Now After Check Availability Button!");

			Log.message("<br>");
			Log.message(
					"<b>Expected Result:</b> Verification after Clicking on Check Availability User Should redirect to product description page");
			Log.assertThat(
					productDescriptionActivities.elementLayer.verifyPageElements(Arrays.asList("btnContinue"),
							productDescriptionActivities),
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