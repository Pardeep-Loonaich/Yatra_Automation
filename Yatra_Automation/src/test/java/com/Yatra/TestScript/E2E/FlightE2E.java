package com.Yatra.TestScript.E2E;

//Description    :   All the Flight module End To End Flow test Cases would be designed in this class 
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
import com.Yatra.Pages.LoginPage;
import com.Yatra.Pages.PaymentPage;
import com.Yatra.Pages.ReviewPage;
import com.Yatra.Pages.SearchResult;
import com.Yatra.Pages.TravellerPage;
import com.Yatra.TestScript.Common.BaseTest;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.DataProviderUtils;
import com.Yatra.Utils.EmailReport;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.Yatra.Utils.WebDriverFactory;

/**
 * @Description:<br>
 * 	this class have all test case related to search flight module End To End Flow
 * 
 *
 */
@Listeners(EmailReport.class)
public class FlightE2E  extends BaseTest {

	EnvironmentPropertiesReader environmentPropertiesReader;
	HomePage homePage;
	LoginPage loginPage;
	SearchResult searchResult;
	ReviewPage reviewPage;
	TravellerPage travellerPage;
	PaymentPage paymentPage;
	String webSite;
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(description = "DOM_OW_Flow", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_E2E_001(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String airlines = testData.get("Airlines");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		String domain = testData.get("Domain");
		String emailId = testData.get("EmailAddress");
		String mobile = testData.get("Mobile");
		String cardNumber = testData.get("CardNumber");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			Log.message("<b>Expected Result:</b> Homepage should loaded in 10 sec and Booking Engine Should display");
			BrowserActions.nap(4);
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("dvSearchEngine"), homePage),
					"<b>Actual Result:</b> Homepage is loaded in 10 second and Booking Engine is display",
					"<b>Actual Result:</b> Homepage is not loaded in 10 second and Booking Engine is display", driver);

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("6.Successfully specified Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");
			
			Log.message("<b>Expected Result:</b> Date strip should display above result");
			BrowserActions.nap(4);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("weeklyFlightsStrip"), searchResult),
					"<b>Actual Result:</b> Date strip is displayed above result",
					"<b>Actual Result:</b> Date strip is not displayed above result", driver);

			// step: Click On Book Now Button with specific airlines			
			String priceInSrp = searchResult.selectAirlineBookNowInOW_E2E(domain, airlines);
			Log.message("9.Clicked On Book Now Button with specific airlines!");
			
			Log.message("Price in Search Result page: <b> "+ priceInSrp + "</b>");
			ReviewPage reviewPage = new ReviewPage(driver);

			BrowserActions.nap(20);
			Log.message("<b>Expected Result:</b> Verify flight pricing should happen.Ignore price change and move forward");
			//handle popup if displayed in Review page	
			if (reviewPage.fareChangeAlertPopUpNotAppear() == false) {
				priceInSrp = reviewPage.fareChangeAlertPopUpAppear_E2E();
				Log.message("Review Page Flight updated fare: <b>" + priceInSrp + "<b>");
				Log.message("<b>Actual Result:</b> Flight pricing Pop Up appear and ignored price change and moved forward", driver);
			} else {
				Log.message("<b>Actual Result:</b> Flight pricing Pop Up does not appear", driver);
			}
						
			Log.message("<b>Expected Result:</b> Promo box should display");
			BrowserActions.nap(3);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtPromoCode"), reviewPage),
					"<b>Actual Result:</b> Promo box is display.", 
					"<b>Actual Result:</b> Promo box is not display.",driver);

			String priceInReviewPage = reviewPage.getTextTotalAmount();
			Log.message("Price in Review page: <b> "+ priceInReviewPage + "</b>");
			
			Log.message("<b>Expected Result:</b> Total fare should be same as was on search result page");
			Log.assertThat(priceInSrp.equalsIgnoreCase(priceInReviewPage),
					"<b>Actual Result:</b> Total fare is same as was on search result page",
					"<b>Actual Result:</b> Total fare is not same as was on search result page", driver);

			// step: Click On Continue Button
			reviewPage.clickOnContinue();
			Log.message("10.Clicked On Continue Button!");
			
			Log.message("<b>Expected Result:</b> Signin popup should display");
			BrowserActions.nap(3);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("signInPopUp"), reviewPage),
					"<b>Actual Result:</b> Signin popup is display.",
					"<b>Actual Result:</b> Signin popup is not display.", driver);

			// step: Sign In Yatra account as Guest
			travellerPage = reviewPage.loginYatraGuestAccount(emailId, mobile);
			Log.message("11.Signed In Yatra account as Guest!");

			travellerPage.uncheckingInsuranceCheckbox();
			BrowserActions.nap(3);
			String priceInTraveller = travellerPage.getTextTotalAmount();
			Log.message("Price in Traveller page: <b> "+ priceInReviewPage + "</b>");
			
			Log.message("<b>Expected Result:</b> Total amount should be same as was on review page");
			BrowserActions.nap(3);
			Log.assertThat(priceInReviewPage.equalsIgnoreCase(priceInTraveller),
					"<b>Actual Result:</b> Total amount is same as was on review page",
					"<b>Actual Result:</b> Total amount is not same as was on review page", driver);

			// step: Fill Traveller Details
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("12.Filled Traveller Details!");

			// step: Clicked on 'Continue' button in Traveller Page
			paymentPage = travellerPage.clickOnContinue();
			Log.message("13.Clicked on 'Continue' button in Traveller Page!");

			
			String priceInPaymentPage = paymentPage.getFlightPriceInPaymentPage();
			Log.message("Payment page without Convenience Fee Flight fare: <b>" + priceInPaymentPage + "<b>");
			Log.message("<b>Expected Result:</b> Total amount should be same as was on Traveller page");
			BrowserActions.nap(2);
			Log.assertThat(priceInPaymentPage.equalsIgnoreCase(priceInTraveller),
					"<b>Actual Result:</b> Total amount is same as was on Traveller page",
					"<b>Actual Result:</b> Total amount is not same as was on Traveller page", driver);
			
			// step: Entered Credit card Details
			paymentPage.enterCreditCardDetailsE2E(cardNumber);
			Log.message("14.Entered Credit card Details!");
			
			String priceWithConvienceFee = paymentPage.getTextFromTotalAmountE2E();
			Log.message("Price in Payment page with Convience Fee: <b> "+ priceWithConvienceFee+ "</b>");

			// step: Click On Pay Now
			paymentPage.clickOnPayNow();
			Log.message("15.Clicked On Pay Now!");

			BrowserActions.nap(3);
			
			//TODO: Commented code to take confirmation from Yatra team for Bank portal pages
			Log.message("Successfully reached Bank Portal page");
			
			/*String priceCitiPortal = paymentPage.getFlightPriceInBankPage();
			Log.message("Bank Portal page Flight fare: <b>" + priceCitiPortal + "</b>");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Move to bank page and verify total payable amount should be same as showing on payment page");
			BrowserActions.nap(3);
			Log.assertThat(priceCitiPortal.equalsIgnoreCase(priceWithConvienceFee),
					"<b>Actual Result:</b> Total amount is same as was on payment page",
					"<b>Actual Result:</b> Total amount is not same as was on payment page", driver);*/

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "DOM_RT_Flow", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_E2E_002(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String airlines = testData.get("Airlines");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		String emailId = testData.get("EmailAddress");
		String mobile = testData.get("Mobile");		
		String paymentType = testData.get("PaymentType");
		String bankName = testData.get("BankName");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			Log.message("<b>Expected Result:</b> Homepage should loaded in 10 sec and Booking Engine Should display");
			BrowserActions.nap(4);
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("dvSearchEngine"), homePage),
					"<b>Actual Result:</b> Homepage is loaded in 10 second and Booking Engine is display",
					"<b>Actual Result:</b> Homepage is not loaded in 10 second and Booking Engine is display", driver);

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page");

			// step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage");
			BrowserActions.nap(30);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("lnkAirlineMatrixStrip"), searchResult),
					"<b>Actual Result:</b> The Weekly fare Matrix displayed on the SRP page for RT Search",
					"<b>Actual Result:</b> The Weekly fare Matrix not displayed on the SRP page for RT Search", driver);

			// step: Click On Book Now Button with specific airlines
			String priceInSrp = searchResult.selectAirlineBookNowInRT_E2E(domain, airlines);
			Log.message("SRP page Flight fare: <b>" + priceInSrp + "<b>");
			Log.message("5.Clicked On Book Now Button with specific airlines!");

			ReviewPage reviewPage = new ReviewPage(driver);
			Log.message("<b>Expected Result:</b> Verify flight pricing should happen.Ignore price change and move forward");
			BrowserActions.nap(20);
			//handle popup if displayed in Review page	
			if (reviewPage.fareChangeAlertPopUpNotAppear() == false) {
				priceInSrp = reviewPage.fareChangeAlertPopUpAppear_E2E();
				Log.message("Review Page Flight updated fare : <b>" + priceInSrp + "<b>");
				Log.message("<b>Actual Result:</b> Flight pricing Pop Up appear and ignored price change and moved forward", driver);
			} else {
				Log.message("<b>Actual Result:</b> Flight pricing Pop Up does not appear", driver);
			}
			
			Log.message("<b>Expected Result:</b> Promo box should display");
			BrowserActions.nap(2);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtPromoCode"), reviewPage),
					"<b>Actual Result:</b> Promo box is display.", "<b>Actual Result:</b> Promo box is not display.", driver);

			String priceInReviewPage = reviewPage.getTextTotalAmount();
			Log.message("Review page Flight fare: <b>" + priceInReviewPage +"<b>");

			Log.message("<b>Expected Result:</b> Total fare should be same as was on search result page");
			Log.assertThat(priceInSrp.equalsIgnoreCase(priceInReviewPage),
					"<b>Actual Result:</b> Total fare is same as was on search result page",
					"<b>Actual Result:</b> Total fare is not same as was on search result page", driver);

			// step: Click On Continue Button
			reviewPage.clickOnContinue();
			Log.message("6.Clicked On Continue Button!");
			
			Log.message("<b>Expected Result:</b> Signin popup should display");
			BrowserActions.nap(2);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("signInPopUp"), reviewPage),
					"<b>Actual Result:</b> Signin popup is display.",
					"<b>Actual Result:</b> Signin popup is not display.", driver);

			// step: Sign In Yatra account as Guest
			travellerPage = reviewPage.loginYatraGuestAccount(emailId, mobile);
			Log.message("7.Signed In Yatra account as Guest!");

			travellerPage.uncheckingInsuranceCheckbox();
			BrowserActions.nap(2);
			String priceInTraveller = travellerPage.getTextTotalAmount();
			Log.message("Traveller page Flight fare: <b>" + priceInTraveller + "<b>");

			Log.message("<b>Expected Result:</b> Total amount should be same as was on review page");
			BrowserActions.nap(2);
			Log.assertThat(priceInReviewPage.equalsIgnoreCase(priceInTraveller),
					"<b>Actual Result:</b> Total amount is same as was on review page",
					"<b>Actual Result:</b> Total amount is not same as was on review page", driver);

			// step: Fill Traveller Details
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8.Filled Traveller Details!");

			// step: Clicked on 'Continue' button in Traveller Page
			paymentPage = travellerPage.clickOnContinue();
			Log.message("9.Clicked on 'Continue' button in Traveller Page!");

			String priceInPaymentPage = paymentPage.getFlightPriceInPaymentPage();
			Log.message("Payment page without Convenience Fee Flight fare: <b>" + priceInPaymentPage + "<b>");
			Log.message("<b>Expected Result:</b> Total amount should be same as was on Traveller page");
			BrowserActions.nap(2);
			Log.assertThat(priceInPaymentPage.equalsIgnoreCase(priceInTraveller),
					"<b>Actual Result:</b> Total amount is same as was on Traveller page",
					"<b>Actual Result:</b> Total amount is not same as was on Traveller page", driver);

			// step: Select Payment option
			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected <b>" + paymentType + "</b> as mode of Payment");

			// step: Select Internet bank name
			paymentPage.selectNetBankName(bankName);
			Log.message("11. Selected <b>" + bankName + "</b> for payment");

			String priceWithConvienceFee = paymentPage.getTextFromTotalAmountE2E();
			Log.message("Payment page Flight fare: <b>" + priceWithConvienceFee + "<b>");

			// step: Click On Pay Now
			paymentPage.clickOnPayNow();
			Log.message("12.Clicked On Pay Now!");

			BrowserActions.nap(3);
			
			//TODO: Commented code to take confirmation from Yatra team for Bank portal pages
			Log.message("Successfully reached Bank Portal page");
			
			/*//String priceCitiPortal = paymentPage.getFlightPriceInNetBankingPage();
			String priceCitiPortal = paymentPage.getFlightPriceInCitiNetBanke();
			Log.message("Bank Portal page Flight fare: <b>" + priceCitiPortal + "<b>");
			String pricePaymentPage = priceWithConvienceFee.replace(",", "");
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Move to bank page and verify total payable amount should be same as showing on payment page");
			BrowserActions.nap(2);
			Log.assertThat(priceCitiPortal.equalsIgnoreCase(pricePaymentPage),
					"<b>Actual Result:</b> Total amount is same as was on payment page",
					"<b>Actual Result:</b> Total amount is not same as was on payment page", driver);*/

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "INTL_OW_Flow_E2E", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_E2E_003(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String airlines = testData.get("Airlines");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		String[] adult = infant.split(",");
		String[] child = infant.split(",");
		String emailId = testData.get("EmailAddress");
		String mobile = testData.get("Mobile");
		String cardNumber = testData.get("CardNumber");
		String domain = testData.get("Domain");


		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			Log.message("<b>Expected Result:</b> Homepage should loaded in 10 sec and Booking Engine Should display");
			BrowserActions.nap(4);
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("dvSearchEngine"), homePage),
					"<b>Actual Result:</b> Homepage is loaded in 10 second and Booking Engine is display",
					"<b>Actual Result:</b> Homepage is not loaded in 10 second and Booking Engine is display", driver);

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			// step: enter Origin place in Yatra Home page
			homePage.enterOrigin(origin);
			Log.message("3.Successfully entered Origin '<b>" + origin + "</b>' in Yatra Homepage");

			// step: enter Destination place in Yatra Home page
			homePage.enterDestination(destination);
			Log.message("4.Successfully entered Destination '<b>" + destination + "</b>' in Yatra Homepage");

			// step: select Departure date
			String departDate = homePage.selectDepartureDate(departureDate);
			Log.message("5.Successfully selected the Departure date: <b>" + departDate + "</b>(YY/MM/DD)");

			// step: select Passengers info
			homePage.specifyPassengerInfo(passengerInfo);
			Log.message("6.Successfully specified Passenger Info");

			// step: select Passengers class
			homePage.selectPassengerClass(passengerClass);
			homePage.clickDoneButtonInPassengerBox();
			Log.message("7.Successfully selected Passenger class and clicked Done button");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("8.Successfully clicked 'Search' button in Yatra Homepage ");
			
			Log.message("<b>Expected Result:</b> Date strip should display above result");
			BrowserActions.nap(4);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("lnkAirlineMatrixStrip"), searchResult),
					"<b>Actual Result:</b> Date strip is displayed above result",
					"<b>Actual Result:</b> Date strip is not displayed above result", driver);

			// step: Click On Book Now Button with specific airlines
			String priceInSrp = searchResult.selectAirlineBookNowInOW_E2E(domain, airlines);
			Log.message("9.Clicked On Book Now Button with specific airlines!");

			Log.message("Price in Search Result page: <b> "+ priceInSrp + "<b>");
			
			ReviewPage reviewPage = new ReviewPage(driver).get();			
			Log.message("<b>Expected Result:</b> Verify flight pricing should happen.Ignore price change and move forward");
			
			//handle popup if displayed in Review page	
			if (reviewPage.fareChangeAlertPopUpNotAppear() == false) {
				priceInSrp = reviewPage.fareChangeAlertPopUpAppear_E2E();
				Log.message("Review Page Flight updated fare : <b>" + priceInSrp + "<b>");
				Log.message("<b>Actual Result:</b> Flight pricing Pop Up appear and ignored price change and moved forward", driver);
			} else {
				Log.message("<b>Actual Result:</b> Flight pricing Pop Up does not appear", driver);
			}			
			
			Log.message("<b>Expected Result:</b> Promo box should display");
			BrowserActions.nap(3);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtPromoCode"), reviewPage),
					"<b>Actual Result:</b> Promo box is display.", "<b>Actual Result:</b> Promo box is not display.", driver);

			String priceInReviewPage = reviewPage.getTextTotalAmount();
			Log.message("Price in Review page: <b> "+ priceInReviewPage + "<b>");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Total fare should be same as was on search result page");
			Log.assertThat(priceInSrp.equalsIgnoreCase(priceInReviewPage),
					"<b>Actual Result:</b> Total fare is same as was on search result page",
					"<b>Actual Result:</b> Total fare is not same as was on search result page", driver);

			// step: Click On Continue Button
			reviewPage.clickOnContinue();
			Log.message("10.Clicked On Continue Button!");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Signin popup should display");
			BrowserActions.nap(3);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("signInPopUp"), reviewPage),
					"<b>Actual Result:</b> Signin popup is display.",
					"<b>Actual Result:</b> Signin popup is not display.", driver);

			// step: Sign In Yatra account as Guest
			travellerPage = reviewPage.loginYatraGuestAccount(emailId, mobile);
			Log.message("11.Signed In Yatra account as Guest!");

			BrowserActions.nap(3);
			String priceInTraveller = travellerPage.getTextTotalAmount();
			Log.message("Price in Traveller page: <b> "+priceInTraveller + "<b>");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Total amount should be same as was on review page");
			BrowserActions.nap(3);
			Log.assertThat(priceInReviewPage.equalsIgnoreCase(priceInTraveller),
					"<b>Actual Result:</b> Total amount is same as was on review page",
					"<b>Actual Result:</b> Total amount is not same as was on review page", driver);

			// step: Fill Traveller Details
			travellerPage.fillTravellerDetails_INT(adult, child, infantDOB);
			Log.message("12.Filled Traveller Details!");

			// step: Clicked on 'Continue' button in Traveller Page
			paymentPage = travellerPage.clickOnContinue();
			Log.message("13.Clicked on 'Continue' button in Traveller Page!");

			String priceInPaymentPage = paymentPage.getFlightPriceInPaymentPage();
			Log.message("Payment page without Convenience Fee Flight fare: <b>" + priceInPaymentPage + "<b>");
			Log.message("<b>Expected Result:</b> Total amount should be same as was on Traveller page");
			BrowserActions.nap(2);
			Log.assertThat(priceInPaymentPage.equalsIgnoreCase(priceInTraveller),
					"<b>Actual Result:</b> Total amount is same as was on Traveller page",
					"<b>Actual Result:</b> Total amount is not same as was on Traveller page", driver);
			
			// step: Entered Credit card Details
			paymentPage.enterCreditCardDetailsE2E(cardNumber);
			Log.message("14.Entered Credit card Details!");
			
			String priceWithConvienceFee = paymentPage.getTextFromTotalAmountE2E();
			Log.message("Price in Payment page with Convience Fee: <b> "+ priceWithConvienceFee + "<b>");

			// step: Click On Pay Now
			paymentPage.clickOnPayNow();
			Log.message("15.Clicked On Pay Now!");

			//TODO: Commented code to take confirmation from Yatra team for Bank portal pages
			Log.message("Successfully reached Bank Portal page");
			
			/*String priceCitiPortal = paymentPage.getFlightPriceInBankPageE2E();
			Log.message("Flight Price in  bank Portal: <b> " + priceCitiPortal + "<b>");
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Move to bank page and verify total payable amount should be same as showing on payment page");
			BrowserActions.nap(3);
			Log.assertThat(priceCitiPortal.equalsIgnoreCase(priceWithConvienceFee),
					"<b>Actual Result:</b> Total amount is same as was on payment page",
					"<b>Actual Result:</b> Total amount is not same as was on payment page", driver);*/

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "INTL_RT_Flow", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_E2E_004(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String returnDate = testData.get("ReturnDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String domain = testData.get("Domain");
		String airlines = testData.get("Airlines");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		String emailId = testData.get("EmailAddress");
		String mobile = testData.get("Mobile");		
		String paymentType = testData.get("PaymentType");
		String bankName = testData.get("BankName");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");
			
			Log.message("<b>Expected Result:</b> Homepage should loaded in 10 sec and Booking Engine Should display");
			BrowserActions.nap(4);
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("dvSearchEngine"), homePage),
					"<b>Actual Result:</b> Homepage is loaded in 10 second and Booking Engine is display",
					"<b>Actual Result:</b> Homepage is not loaded in 10 second and Booking Engine is display", driver);

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'RoundTrip' option in search Home Page");

			// step: select Round Trip Search fields in HomePage
			homePage.selectRoundTripFlightSearchFields(origin, destination, departureDate, returnDate, passengerInfo, passengerClass);
			Log.message("3.Successfully filled the search details for Round Trip");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Clicked on 'Search' in Yatra Homepage");
			BrowserActions.nap(30);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("lnkAirlineMatrixStrip"), searchResult),
					"<b>Actual Result:</b> The Weekly fare Matrix displayed on the SRP page for RT Search",
					"<b>Actual Result:</b> The Weekly fare Matrix not displayed on the SRP page for RT Search", driver);

			// step: Click On Book Now Button with specific airlines
			String priceInSrp = searchResult.selectAirlineBookNowInRT_E2E(domain, airlines);
			Log.message("SRP page Flight fare: <b>" + priceInSrp + "<b>");
			Log.message("5.Clicked On Book Now Button with specific airlines!");

			ReviewPage reviewPage = new ReviewPage(driver);
			Log.message("<b>Expected Result:</b> Verify flight pricing should happen.Ignore price change and move forward");
			BrowserActions.nap(20);
			//handle popup if displayed in Review page	
			if (reviewPage.fareChangeAlertPopUpNotAppear() == false) {
				priceInSrp = reviewPage.fareChangeAlertPopUpAppear_E2E();
				Log.message("Review Page Flight updated fare : <b>" + priceInSrp + "<b>");
				Log.message("<b>Actual Result:</b> Flight pricing Pop Up appear and ignored price change and moved forward", driver);
			} else {
				Log.message("<b>Actual Result:</b> Flight pricing Pop Up does not appear", driver);
			}
			
			Log.message("<b>Expected Result:</b> Promo box should display");
			BrowserActions.nap(2);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtPromoCode"), reviewPage),
					"<b>Actual Result:</b> Promo box is display.", "<b>Actual Result:</b> Promo box is not display.", driver);

			String priceInReviewPage = reviewPage.getReviewPageFlightFare();
			Log.message("Review page Flight fare: <b>" + priceInReviewPage + "<b>");

			Log.message("<b>Expected Result:</b> Total fare should be same as was on search result page");
			Log.assertThat(priceInSrp.equalsIgnoreCase(priceInReviewPage),
					"<b>Actual Result:</b> Total fare is same as was on search result page",
					"<b>Actual Result:</b> Total fare is not same as was on search result page", driver);

			// step: Click On Continue Button
			reviewPage.clickOnContinue();
			Log.message("6.Clicked On Continue Button!");

			Log.message("<b>Expected Result:</b> Signin popup should display");
			BrowserActions.nap(2);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("signInPopUp"), reviewPage),
					"<b>Actual Result:</b> Signin popup is display.",
					"<b>Actual Result:</b> Signin popup is not display.", driver);

			// step: Sign In Yatra account as Guest
			travellerPage = reviewPage.loginYatraGuestAccount(emailId, mobile);
			Log.message("7.Signed In Yatra account as Guest!");

			travellerPage.uncheckingInsuranceCheckbox();
			BrowserActions.nap(3);
			String priceInTraveller = travellerPage.getTextTotalAmount();
			Log.message("Traveller page Flight fare: <b>" + priceInTraveller + "<b>");

			Log.message("<b>Expected Result:</b> Total amount should be same as was on review page");
			BrowserActions.nap(2);
			Log.assertThat(priceInReviewPage.equalsIgnoreCase(priceInTraveller),
					"<b>Actual Result:</b> Total amount is same as was on review page",
					"<b>Actual Result:</b> Total amount is not same as was on review page", driver);

			// step: Fill Traveller Details
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8.Filled Traveller Details!");

			// step: Clicked on 'Continue' button in Traveller Page
			paymentPage = travellerPage.clickOnContinue();
			Log.message("9.Clicked on 'Continue' button in Traveller Page!");

			String priceInPaymentPage = paymentPage.getFlightPriceInPaymentPage();
			Log.message("Payment page without Convenience Fee Flight fare: <b>" + priceInPaymentPage + "<b>");
			
			Log.message("<b>Expected Result:</b> Total amount should be same as was on Traveller page");
			BrowserActions.nap(4);
			Log.assertThat(priceInPaymentPage.equalsIgnoreCase(priceInTraveller),
					"<b>Actual Result:</b> Total amount is same as was on Traveller page",
					"<b>Actual Result:</b> Total amount is not same as was on Traveller page", driver);

			// step: Select Payment option
			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected <b>" + paymentType + "</b> as mode of Payment");

			// step: Select Internet bank name
			paymentPage.selectNetBankName(bankName);
			Log.message("11. Selected <b>" + bankName + "</b> for payment");

			String priceWithConvienceFee = paymentPage.getTextFromTotalAmountE2E();
			Log.message("Payment page Flight fare: <b>" + priceWithConvienceFee + "<b>");

			// step: Click On Pay Now
			paymentPage.clickOnPayNow();
			Log.message("12.Clicked On Pay Now!");
			BrowserActions.nap(3);
			
			//TODO: Commented code to take confirmation from Yatra team for Bank portal pages			
			Log.message("Successfully reached Bank Portal page");
			
			/*//String priceCitiPortal = paymentPage.getFlightPriceInNetBankingPage();
			String priceCitiPortal = paymentPage.getFlightPriceInCitiNetBanke();
			Log.message("Bank Portal page Flight fare: <b>" + priceCitiPortal + "<b>");
			String pricePaymentPage = priceWithConvienceFee.replace(",", "");
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Move to bank page and verify total payable amount should be same as showing on payment page");
			BrowserActions.nap(2);
			Log.assertThat(priceCitiPortal.equalsIgnoreCase(pricePaymentPage),
					"<b>Actual Result:</b> Total amount is same as was on payment page",
					"<b>Actual Result:</b> Total amount is not same as was on payment page", driver);*/

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}

	@Test(description = "DOM_OW_Pricing", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_E2E_005(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String origin = testData.get("Origin");
		String tripType = testData.get("TripType");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String airlines = testData.get("Airlines");
		String domain = testData.get("Domain");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Home Page
			homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			Log.message("<b>Expected Result:</b> Homepage should loaded in 10 sec and Booking Engine Should display");
			BrowserActions.nap(4);
			Log.assertThat(homePage.elementLayer.verifyPageElements(Arrays.asList("dvSearchEngine"), homePage),
					"<b>Actual Result:</b> Homepage is loaded in 10 second and Booking Engine is display",
					"<b>Actual Result:</b> Homepage is not loaded in 10 second and Booking Engine is display", driver);

			// step: Select Trip Type
			homePage.selectTripType(tripType);
			Log.message("2.Successfully clicked 'One Way' option in search Home Page ");

			/// step: select OneWay Search fields in HomePage
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3.Successfully selected OneWay Flight Search Fields!");

			// step: click 'Search' button in Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4.Successfully clicked 'Search' button in Yatra Homepage ");

			Log.message("<b>Expected Result:</b> Date strip should display above result");
			BrowserActions.nap(4);
			Log.assertThat(searchResult.elementLayer.verifyPageElements(Arrays.asList("weeklyFlightsStrip"), searchResult),
					"<b>Actual Result:</b> Date strip is displayed above result",
					"<b>Actual Result:</b> Date strip is not displayed above result", driver);

			// step: Click On Book Now Button with specific airlines			
			String priceInSrp = searchResult.selectAirlineBookNowInOW_E2E(domain, airlines);
			Log.message("SRP page Flight fare: <b>" + priceInSrp + "<b>");
			Log.message("5.Clicked On Book Now Button with specific airlines!");

			ReviewPage reviewPage = new ReviewPage(driver);
			Log.message("<b>Expected Result:</b> Verify flight pricing should happen.Ignore price change and move forward");
			BrowserActions.nap(20);
			//handle popup if displayed in Review page	
			if (reviewPage.fareChangeAlertPopUpNotAppear() == false) {
				priceInSrp = reviewPage.fareChangeAlertPopUpAppear_E2E();
				Log.message("Review Page Flight updated fare : <b>" + priceInSrp + "<b>");
				Log.message("<b>Actual Result:</b> Flight pricing Pop Up appear and ignored price change and moved forward", driver);
			} else {
				Log.message("<b>Actual Result:</b> Flight pricing Pop Up does not appear", driver);
			}			

			Log.message("<b>Expected Result:</b> Promo box should display");
			BrowserActions.nap(2);
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("txtPromoCode"), reviewPage),
					"<b>Actual Result:</b> Promo box is display.", "<b>Actual Result:</b> Promo box is not display.",	driver);

			String priceInReviewPage = reviewPage.getTextTotalAmount();
			Log.message("Review page Flight fare: <b>" + priceInReviewPage + "<b>");
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Total fare should be same as was on search result page");
			Log.assertThat(priceInSrp.equalsIgnoreCase(priceInReviewPage),
					"<b>Actual Result:</b> Total fare is same as was on search result page",
					"<b>Actual Result:</b> Total fare is not same as was on search result page", driver);

			Log.testCaseResult();
		} catch (Exception e) {
			Log.exception(e);
		} finally {			
			Log.endTestCase();
		}
	}
	
}// E2ETestCasesEnd
