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
import com.Yatra.Pages.PaymentPage;
import com.Yatra.Pages.ReviewPage;
import com.Yatra.Pages.SearchResult;
import com.Yatra.Pages.TravellerPage;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.Yatra.Utils.WebDriverFactory;

@Listeners(EmailReport.class)
public class FlightNew {

	EnvironmentPropertiesReader environmentPropertiesReader;
	HomePage homePage;
	LoginPage loginPage;
	ReviewPage reviewPage;
	SearchResult searchResult;
	TravellerPage travellerPage;
	PaymentPage paymentPage;
	String webSite;
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {

		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(description = "Verification Remove Promo Code Functionality from Promo Apply Box", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_149(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Book Now' button
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5. Successfully clicked 'Search' !");

			// step: click on 'Promo' DropDown
			reviewPage.clickOnPromoDrpDwn();
			Log.message("6. Successfully clicked 'Promo' DropDown!");

			// step: selected 'Promotion' from the DropDown
			reviewPage.selectPromoByIndex(1);
			Log.message("7. Successfully Selected 'Promotion' from the DropDown!");
			Thread.sleep(2000);
			String amount = reviewPage.getTextTotalAmount();
			
//			Log.message("<br>");
//			Log.message("<b>Expected Result:</b> Verification Change Promo");
//			Log.assertThat(
//					reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtPromoDiscountApplied"), reviewPage),
//					"<b>Actual Result:</b> Promotional Discount For First promo code is :  " + amount
//							+ " \n Promotional Discount For second Promo is : " + SecondPromoDiscount,
//					"<b>Actual Result:</b> User did not navigated to review Page", driver);
//			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Verification Change Promo", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_150(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search'!");

			// step: click 'Book Now' button
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5. Successfully clicked 'Search' !");

			// step: click on 'Promo' DropDown
			reviewPage.clickOnPromoDrpDwn();
			Log.message("6. Successfully clicked 'Promo' DropDown!");

			// step: selected First 'Promotion' from the DropDown
			reviewPage.selectPromoByIndex(1);
			Log.message("7. Successfully Selected First 'Promotion' from the DropDown!");
			Thread.sleep(2000);
			String firstPromoDiscount = reviewPage.getTextPromotinalMessage();

			// step: click on 'Promo' DropDown
			reviewPage.clickOnPromoDrpDwn();
			Log.message("8. Successfully clicked 'Promo' DropDown!");

			// step: selected Second 'Promotion' from the DropDown
			reviewPage.selectPromoByIndex(2);
			Log.message("9. Successfully Selected second 'Promotion' from the DropDown!");
			Thread.sleep(2000);
			String SecondPromoDiscount = reviewPage.getTextPromotinalMessage();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verification Change Promo");
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtPromoDiscountApplied"), reviewPage),
					"<b>Actual Result:</b> Promotional Discount For First promo code is :  " + firstPromoDiscount
							+ " \n Promotional Discount For second Promo is : " + SecondPromoDiscount,
					"<b>Actual Result:</b> User did not navigated to review Page", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validating the action on clicking on Flight Details link", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_151(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search' in Yatra Homepage!");

			// step: click 'Flight Details' button
			searchResult.clickOnlnkFlightDetails_INTL();
			Log.message("5. Successfully clicked 'Search' Button");

			// step: click 'Fare and Summary' Link
			searchResult.clickOnFareAndSummaryFlightDetail();
			Log.message("6. Successfully clicked 'Fare and Rule Detail Link' in Flight Detail Pop Up!");
			Thread.sleep(1000);
			String fare = searchResult.getTextFareDetailsandRuleInPopUp();

			// step: click 'Baggage' Link
			searchResult.clickOnBaggageFlightDetail();
			Log.message("7. Successfully clicked 'Baggage Link' in Flight Detail Pop Up!");
			Thread.sleep(1000);
			String Baggage = searchResult.getTextBaggageInfoFlightDetail();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating the action on clicking on Flight Details link");
			Log.assertThat(
					searchResult.elementLayer.verifyPageElements(
							Arrays.asList("lnkBaggageFlightDetail", "lnkFareAndSummaryFlightDetail"), searchResult),
					"<b>Actual Result:</b> After Clicking on Flight Details Link a Pop Up Appear Fare and Summary Displayed as -->"
							+ fare + "\n and Baggage details as \n --> " + Baggage,
					"<b>Actual Result:</b> After Clicking On Flight Details Link, no pop up Appear", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(description = "Validating book now button on Flight Details Link-INTL", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Flight_152(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String tripType = testData.get("TripType");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2. Successfully clicked 'One Way' option in search Home Page!");

			// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for OneWay!");

			// step: click 'Search' button
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Successfully clicked 'Search' Button!");

			// step: click 'Book Now' button
			reviewPage = searchResult.clickOnBookNowINT();
			Log.message("5. Successfully clicked 'Book Now' Button!");
			String txt = reviewPage.getTextReviewBooking();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Validating book now button on Flight Details Link-INTL");
			Log.assertThat(
					reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtReviewYourBooking"), reviewPage),
					"<b>Actual Result:</b> User navigated to review Page and Text is Displayed as :" + txt,
					"<b>Actual Result:</b> User did not navigated to review Page", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

}
