package com.Yatra.TestScript.Payment;

import java.util.ArrayList;
import java.util.Arrays;

//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Hotels Search test Cases would be designed in this class 
//Creator        :   Aspire Team
//Create         :   
//Modified on/By :   -
//-----------------------------------------------------------------------------------------------------------

import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.IAssert;

import com.Yatra.Pages.Bookings;
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
public class PaymentTest {

	EnvironmentPropertiesReader environmentPropertiesReader;
	String webSite;
	HomePage homePage;
	LoginPage loginPage;
	ReviewPage reviewPage;
	SearchResult searchResult;
	TravellerPage travellerPage;
	PaymentPage paymentPage;
	Bookings bookings;

	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(groups = { "desktop" }, description = "verify Ecash Redemption ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_001(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");

			// step: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);	
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			reviewPage.popUpAppear();
			/*reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7. Successfully Logged in Yatra account as 'Existing' User!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();
			Log.message(". Clicked on Cancel ecash.");

			String initialEcash1 = paymentPage.eCashAmount();
			int initialEcash = Integer.parseInt(initialEcash1);
			Log.message("Initial Cash:"+initialEcash);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify if user has ecash greater than 0.");
			Log.assertThat(initialEcash>0,
					"<b>Actual Result1:</b> Ecash amount is greater than 0.",
					"<b>Actual Result1:</b> Ecash amount is not greater than 0.",
					driver);

			paymentPage.scrollSliderOfEcashRedeem(-80);
			Log.message("10. Scroll the ecash Redeem Slider to adjust ecash amount.");

			String finalEcash1 = paymentPage.eCashAmount();
			int finalEcash = Integer.parseInt(finalEcash1);
			Log.message("Final Cash:"+finalEcash);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Scroll the slider and verify Ecash redemption.");
			Log.assertThat(initialEcash!=finalEcash,
					"<b>Actual Result2:</b> The user is able to scroll the slider and ecash redem is changed.",
					"<b>Actual Result2:</b> The user is not able to scroll the slider and ecash redem is not changed.",
					driver); 

			paymentPage.clickingOnRedeemNow();
			Log.message("11. Clicked on Redeem Now Button to add ecash.");


			Log.message("<br>");

			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("msgEcashRedeem"), paymentPage),
					"<b>Actual Result3:</b> Ecash is applied successfully and the message is displayed under Payment method."
							+ paymentPage.getMsgFromEcashRedeemSuccess(),
							"<b>Actual Result3:</b> Ecash is not applied and the message is not displayed under Payment method.",
							driver);

			paymentPage.clickingToCancelEcashRedem();
			Log.message("12. Clicked on Cancel Ecash Redeem link to remove ecash.");

			Log.message("<br>");
			Log.assertThat(	paymentPage.elementLayer.verifyPageElements(Arrays.asList("btnRedeemNow"), paymentPage)
					&& (!paymentPage.getTextFromPaymentDetailsModule().contains("eCash Redeemed")),
					"<b>Actual Result4:</b> Ecash Redeem is cancelled and is not displayed under Payment method.",
					"<b>Actual Result4:</b> Ecash is applied and the message is displayed under Payment method.",
					driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "verify CreditCard Transaction", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_002(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String paymentType = testData.get("PaymentType");
		String cardNumber = testData.get("CardNumber");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");


			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}


			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(2);	
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7. Successfully Logged in Yatra account as 'Existing' User!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();
			paymentPage.cancelCreditCardDetails();

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected "+paymentType+" as mode of Payment");

			paymentPage.enterCreditCardDetails(cardNumber);
			Log.message("11. Entered credit card details.");

			paymentPage.clickOnPayNow();
			Log.message("12. Clicked on 'PayNow' button on Payment Page.");

			Utils.waitForPageLoad(driver);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(driver.getCurrentUrl().contains("hdfcbank"),
					"<b>Actual Result:</b> Successfully navigated back on Bank Page.",
					"<b>Actual Result:</b> Unable to navigated back on Bank Page.", driver);

			paymentPage.cancelHdfcPayment();
			Log.message("13. Clicked on Cancel button.");

			paymentPage.returnFromCreditCardPage(browser,2);
			Log.message("14. Navigating back to 'Yatra' page.");

			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoYatra"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);



			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Verify Maestro Card With CVV", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_003(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String paymentType = testData.get("PaymentType");
		String cardNumber = testData.get("CardNumber");
		String cvv = testData.get("CVV");

		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");


			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}


			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(2);	
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7. Successfully Logged in Yatra account as 'Existing' User!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected '"+paymentType+"' as mode of Payment");

			paymentPage.enterDebitCardDetails(cardNumber,cvv);
			Log.message("11. Entered debit card details.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify if debit card transaction is working fine with cvv .");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("txtValidDBMsg"), paymentPage),
					"<b>Actual Result:</b> Debit Card is working fine with cvv.",
					"<b>Actual Result:</b> Debit Card is not working fine with cvv.", driver);

			paymentPage.clickOnPayNow();
			Log.message("12. Clicked on 'PayNow' button on Payment Page.");
			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User is successfully coming back to yatra page after failed transcaction.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoYatra"), paymentPage),
					"<b>Actual Result:</b> User successfully navigated back to yatra page after failed transaction with failed message:"+paymentPage.getTextFromFailedDebitCardTrans(),
					"<b>Actual Result:</b> User not coming back to yatra page after failed transaction.", driver);


			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "Verify Maestro Card Without CVV", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_004(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String paymentType = testData.get("PaymentType");
		String cardNumber = testData.get("CardNumber");

		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");


			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}


			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(2);	
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7. Successfully Logged in Yatra account as 'Existing' User!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected '"+paymentType+"' as mode of Payment");

			paymentPage.enterPartialDebitCardDetails(cardNumber);
         	Log.message("11. Entered debit card details without CVV.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify if debit card transaction is working fine with cvv .");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("txtValidDBMsg"), paymentPage),
					"<b>Actual Result:</b> Debit Card is working fine without cvv.",
					"<b>Actual Result:</b> Debit Card is not working fine without cvv.", driver);

			paymentPage.clickOnPayNow();
			Log.message("12. Clicked on 'PayNow' button on Payment Page.");


			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> User is successfully coming back to yatra page after failed transcaction.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoYatra"), paymentPage),
					"<b>Actual Result:</b> User successfully navigated back to yatra page after failed transaction with failed message:"+paymentPage.getTextFromFailedDebitCardTrans(),
					"<b>Actual Result:</b> User not coming back to yatra page after failed transaction.", driver);



			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "verify NetBanking Single Option ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_005(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String paymentType = testData.get("PaymentType");
		String bankName = testData.get("BankName");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		String URL= "https://merchant.onlinesbi.com";
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");


			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}


			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");*/

			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected '"+paymentType+"' as mode of Payment");

			paymentPage.selectNetBankName(bankName);
			Log.message("11. Selected '"+bankName+"' for payment.");

			paymentPage.clickOnPayNow();
			Log.message("12. Clicked on 'Pay Now' for making payment.");

			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(driver.getCurrentUrl().contains(URL),
					"<b>Actual Result:</b> Successfully navigated on "+bankName+" Bank Page.",
					"<b>Actual Result:</b> Unable to navigated on "+bankName+" Bank Page.", driver);

			driver.navigate().back();
			Log.message("13. Navigating back to 'Yatra' page.");

			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoYatra"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "Verify Emi Options ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_006(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String paymentType = testData.get("PaymentType");
		String cardName = testData.get("BankName");
		String cardNumber = testData.get("CardNumber");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("9. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("10. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();

			paymentPage.selectPaymentType(paymentType);
			Log.message("11. Selected '"+paymentType+"' as mode of Payment");

			paymentPage.selectCardNameInEMIDropdown(cardName);
			Log.message("12. Selected "+cardName+" for payment.");

			paymentPage.enterCreditCardDetailsInEMI(cardNumber);
			Log.message("13. Enter 'CreditCard' details for making payment.");

			paymentPage.clickOnPayNow();
			Log.message("14. Clicked on 'Pay Now' for making payment.");

			Utils.waitForPageLoad(driver);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(driver.getCurrentUrl().contains("hdfcbank"),
					"<b>Actual Result:</b> Successfully navigated back on "+cardName+" Page.",
					"<b>Actual Result:</b> Unable to navigated back on "+cardName+" Page.", driver);

			paymentPage.cancelHdfcPayment();
			Log.message("15. Clicked on Cancel button.");

			driver.navigate().back();
			Log.message("16. Navigating back to 'Yatra' page.");
			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoYatra"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);
			Log.testCaseResult();


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "Verify MobileWallet Single Option ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_007(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String paymentType = testData.get("PaymentType");
		String walletName = testData.get("BankName");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected "+paymentType+" as mode of Payment");

			paymentPage.selectMobileWallet(walletName);
			Log.message("11. Selected "+walletName+" for payment");


			paymentPage.clickOnPayNow();
			Log.message("12. Clicked on 'Pay Now' for making payment.");
			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected "+paymentType+" Page.");
			Log.assertThat(driver.getCurrentUrl().contains(walletName),
					"<b>Actual Result:</b> Successfully navigated back on "+walletName+" Page.",
					"<b>Actual Result:</b> Unable to navigated back on "+walletName+" Page.", driver);



			paymentPage.clickOnBackToYatraLinkFreechrge();
			Log.message("13. Navigating back to 'Yatra' page.");
			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoYatra"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = { "desktop" }, description = "Verify Cash Card ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_008(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String paymentType = testData.get("PaymentType");
		String itzName = testData.get("BankName");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		String URL = "https://www.itzcash.com";
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected "+paymentType+" as mode of Payment");


			paymentPage.clickOnPayNow();
			Log.message("12. Clicked on 'Pay Now' for making payment.");
			Utils.waitForPageLoad(driver);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected "+paymentType+" Page.");
			Log.assertThat(driver.getCurrentUrl().contains(URL),
					"<b>Actual Result:</b> Successfully navigated back on "+paymentType+" Page.",
					"<b>Actual Result:</b> Unable to navigated back on "+paymentType+" Page.", driver);

			paymentPage.selectITZCashRan(itzName);
			Log.message("10. Selected "+itzName+" from ITZ options");


			paymentPage.cancelOrderForITZCash();
			Log.message("13. Navigating back to 'Yatra' page after cancelling ITZ Cash Payment.");
			Utils.waitForPageLoad(driver);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoYatra"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);
			Log.testCaseResult();


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Verify Atm All Options ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_009(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String paymentType = testData.get("PaymentType");
		String ATMCardName = testData.get("BankName");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected "+paymentType+" as mode of Payment");

			paymentPage.selectATMCardName(ATMCardName);
			Log.message("11. Selected "+ATMCardName+" for payment");


			paymentPage.clickOnPayNow();
			Log.message("12. Clicked on 'Pay Now' for making payment.");
			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected "+paymentType+" Page.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoSBI"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated back on "+ATMCardName+" Page.",
					"<b>Actual Result:</b> Unable to navigated back on "+ATMCardName+" Page.", driver);



			paymentPage.cancelSBIATMPayment();
			Log.message("13. Navigating back to 'Yatra' page after cancelling SBI Payment.");
			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoYatra"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Verify Eze Click ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_010(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String paymentType = testData.get("PaymentType");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		String URL = "https://payments.billdesk.com/amexezeclick";
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected "+paymentType+" as mode of Payment");


			paymentPage.clickOnPayNow();
			Log.message("11. Clicked on 'Pay Now' for making payment.");

			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected "+paymentType+" Page.");
			Log.assertThat(driver.getCurrentUrl().contains(URL),
					"<b>Actual Result:</b> Successfully navigated back on "+paymentType+" Page.",
					"<b>Actual Result:</b> Unable to navigated back on "+paymentType+" Page.", driver);


			driver.navigate().back();
			Log.message("12. Navigating back to 'Yatra' page.");
			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoYatra"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Verify Rewards", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_011(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String cardNumber = testData.get("CardNumber");
		String paymentType = testData.get("PaymentType");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();
			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected "+paymentType+" as mode of Payment");

			paymentPage.enterCreditCardDetailsInRewards(cardNumber);

			paymentPage.clickOnPayNow();
			Log.message("11. Clicked on 'Pay Now' for making payment.");

			Utils.waitForPageLoad(driver);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected "+paymentType+" Page.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("btnNextRewardPage"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated on "+paymentType+" Page.",
					"<b>Actual Result:</b> Unable to navigated on "+paymentType+" Page.", driver);

			paymentPage.returnFromRewardPage(browser);
			Log.message("12. Navigating back to 'Yatra' page.");


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoYatra"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Verify Stored cards", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_015(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String paymentType = testData.get("PaymentType");
		String cardNumber = testData.get("CardNumber");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}


			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);	
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7. Successfully Logged in Yatra account as 'Existing' User!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();
			paymentPage.cancelCreditCardDetails();

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected "+paymentType+" as mode of Payment");


			paymentPage.enterCreditCardDetails(cardNumber);
			Log.message("11. Entered credit card details.");

			paymentPage.saveCreditCardDetails();
			Log.message("12. Saving credit card details in quick book.");

			paymentPage.clickOnPayNow();
			Log.message("13. Clicked on 'PayNow' button on Payment Page.");

			Utils.waitForPageLoad(driver);
			Log.message("<br>");
			Log.assertThat(driver.getCurrentUrl().contains("hdfcbank"),
					"<b>Actual Result:</b> Successfully navigated back on Bank Page.",
					"<b>Actual Result:</b> Unable to navigated back on Bank Page.", driver);


			paymentPage.cancelHdfcPayment();
			Log.message("14. Clicked on Cancel button.");

			driver.navigate().back();
			Log.message("15. Navigating back to 'Yatra' page.");

			Utils.waitForPageLoad(driver);
			Log.message("<br>");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoYatra"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);

			String cc_number = paymentPage.getCrediCrdNum();
			Log.message("Credi Card details:"+cc_number);

			Utils.waitForPageLoad(driver);
			Log.message("<b>Expected Result:</b> Verify earlier stored cards are showing in credit card option.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("tabSavedCC"), paymentPage)&&(cardNumber.contains(cc_number)),
					"<b>Actual Result:</b> Saved credit card details are showing in credit card option.",
					"<b>Actual Result:</b> Saved credit card details are not showing in credit card option.", driver);


			Log.testCaseResult();


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = { "desktop" }, description = "Redeem eCash shouldnot display for guest user ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_016(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String mobile = testData.get("Mobile");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("2. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("3. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("4. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("5. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("6. Clicked on Continue button on Review Page.");

			travellerPage = reviewPage.loginYatraGuestAccount(emailId, mobile);
			Log.message("7. Successfully Logged in Yatra as 'Guest' user!");

			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify redeem ecash section should not display.");
			Log.assertThat(!paymentPage.getTextFromPaymentDetailsModule().contains("eCash"),
					"<b>Actual Result:</b> Redeem eCash not displayed in Payment detail module.",
					"<b>Actual Result:</b> Redeem eCash displayed in Payment detail module.", driver);
			Log.testCaseResult();


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "All payment methods displayed ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_017(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify all payment methods are showing in Payment Page.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("lstPaymentMetod"), paymentPage),
					"<b>Actual Result:</b> All Payment Methods are available on Payment Page.",
					"<b>Actual Result:</b> All Payment Methods are not available on Payment Page.", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = { "desktop" }, description = "Verify Ecash Redemption display properly", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_018(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");

			// step: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);	
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			reviewPage.popUpAppear();
			/*reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7. Successfully Logged in Yatra account as 'Existing' User!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			String initialTotalAmount1 = paymentPage.getTextFromTotalAmount();
			int initialTotalAmount = Integer.parseInt(initialTotalAmount1);
			Log.message("  Initial Total Amount:"+initialTotalAmount);

			paymentPage.verifyCancelEcash();

			String initialEcash1 = paymentPage.eCashAmount();
			int initialEcash = Integer.parseInt(initialEcash1);
			Log.message("  Initial eCash Amount in payment module before applying eCash:"+initialEcash);

			Log.message("<br>");
			Log.assertThat(initialEcash>0,
					"<b>Actual Result:</b> Ecash amount is greater than 0.",
					"<b>Actual Result:</b> Ecash amount is not greater than 0.",
					driver);

			paymentPage.scrollSliderOfEcashRedeem(-80);
			Log.message("10. Scroll the ecash Redeem Slider to adjust ecash amount.");

			String finalEcash1 = paymentPage.eCashAmount();
			int finalEcash = Integer.parseInt(finalEcash1);
			Log.message("  Final eCash Amount:"+finalEcash);



			paymentPage.clickingOnRedeemNow();
			Log.message("11. Clicked on Redeem Now Button to add eCash.");

			String finalTotalAmount1 = paymentPage.getTextFromTotalAmount();
			int finalTotalAmount = Integer.parseInt(finalTotalAmount1);
			Log.message("  Final Total Amount in payment module after applying eCash:"+finalTotalAmount);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify redeemed amount should display in payment details section.");
			Log.assertThat(paymentPage.getTravellerDetails().contains("ecash"),
					"<b>Actual Result:</b> eCash redeemed amount is displayed in payment details section.",
					"<b>Actual Result:</b> eCash redeemed amount is not displayed in payment details section.",
					driver); 


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify total amount is reduced now.");
			Log.softAssertThat(finalTotalAmount==(initialTotalAmount-finalEcash),
					"<b>Actual Result:</b> Total amount is reduced after deducting the eCash amount."+paymentPage.getMsgFromEcashBalanceDeduction(),
					"<b>Actual Result:</b> Total amount is not reduced after deducting the eCash amount.",
					driver);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify eCash redeemption details is showing in redeemption section.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("msgEcashRedeem"), paymentPage),
					"<b>Actual Result:</b> Ecash is applied successfully and the eCash redeemption details are displayed in redeemption section."
							+ paymentPage.getMsgFromEcashRedeemSuccess(),
							"<b>Actual Result:</b> Ecash is not applied successfully and the eCash redeemption details are not displayed in redeemption section.",
							driver);


			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}



	@Test(groups = { "desktop" }, description = "Verify redemption is removed and fare is correct now", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_019(HashMap<String, String> testData) throws Exception {

		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");

			// step: verify Yatra title bar text
			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);	
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			reviewPage.popUpAppear();
			/*reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7. Successfully Logged in Yatra account as 'Existing' User!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			String initialTotalAmount1 = paymentPage.getTextFromTotalAmount();
			int initialTotalAmount = Integer.parseInt(initialTotalAmount1);
			Log.message("  Initial Total Amount:"+initialTotalAmount);

			paymentPage.verifyCancelEcash();

			String initialEcash1 = paymentPage.eCashAmount();
			int initialEcash = Integer.parseInt(initialEcash1);
			Log.message("  Initial eCash Amount in payment module before applying eCash:"+initialEcash);

			Log.message("<br>");
			Log.assertThat(initialEcash>0,
					"<b>Actual Result:</b> Ecash amount is greater than 0.",
					"<b>Actual Result:</b> Ecash amount is not greater than 0.",
					driver);

			paymentPage.scrollSliderOfEcashRedeem(-80);
			Log.message("10. Scroll the ecash Redeem Slider to adjust ecash amount.");

			String finalEcash1 = paymentPage.eCashAmount();
			int finalEcash = Integer.parseInt(finalEcash1);
			Log.message("  Final eCash Amount:"+finalEcash);



			paymentPage.clickingOnRedeemNow();
			Log.message("11. Clicked on Redeem Now Button to add eCash.");

			String finalTotalAmount1 = paymentPage.getTextFromTotalAmount();
			int finalTotalAmount = Integer.parseInt(finalTotalAmount1);
			Log.message("  Total Amount in payment module after applying eCash:"+finalTotalAmount);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify redemption is happened.");
			Log.softAssertThat(finalTotalAmount==(initialTotalAmount-finalEcash),
					"<b>Actual Result:</b> Total amount is reduced after deducting the eCash amount."+paymentPage.getMsgFromEcashBalanceDeduction(),
					"<b>Actual Result:</b> Total amount is not reduced after deducting the eCash amount.",
					driver); 

			paymentPage.clickingToCancelEcashRedem();
			Log.message("12. Cancelling the Ecash Redemption.");

			String TotalAmount1 = paymentPage.getTextFromTotalAmount();
			int TotalAmount = Integer.parseInt(TotalAmount1);
			Log.message("  Total Amount in payment module after cancelling the eCash:"+TotalAmount);

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify redemption is removed and fare is correct now.");
			Log.assertThat(initialTotalAmount==finalTotalAmount, 
					"<b>Actual Result:</b> Total amount is same as before after removing ecash.",
					"<b>Actual Result:</b> Total amount is not same as before after removing ecash.",
					driver); 

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = { "desktop" }, description = "Earn eCash is showing", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_020(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify Earn Ecash amount is showing on Payment Page.");

			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("modEcash"), paymentPage),
					"<b>Actual Result:</b> Earn Ecash section is showing on Payment Page with heading '"+paymentPage.getEcashHeading()+"'and the amount is :"+paymentPage.getEcashAmount(),
					"<b>Actual Result:</b> Earn Ecash section is not showing on Payment Page.", driver);
			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Term and conditions link", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_022(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");


			Thread.sleep(2000);
			String winHandleBefore = driver.getWindowHandle();
			// Perform the click operation that opens new window
			paymentPage.clickedOnTnCLink();
			// Switch to new window opened
			Log.message("10. Clicked on TnC link on Payment Page.");


			Set<String> handles = driver.getWindowHandles(); 
			for(String winHandle : handles){
				if(!winHandle.equals(winHandleBefore)){
					driver.switchTo().window(winHandle);
					break;
				}
			}

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify TnC page is opening properly in separate window.");

			Log.assertThat(paymentPage.verifyTnCPage(),
					"<b>Actual Result:</b> Successfully navigated to Terms and condition Page.",
					"<b>Actual Result:</b> Unable to navigated to Terms and condition Page.", driver);

			Log.testCaseResult();


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Verify flight details is showing on top.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_023(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify flight details is showing on top.");

			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("labelTravellerDetails"), paymentPage),
					"<b>Actual Result:</b> Flight Details are displayed on top as :'"+paymentPage.getFlightDetails()+"'",
					"<b>Actual Result:</b> Flight Details are not displayed on top.", driver);

			Log.testCaseResult();


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Verify Traveller details is showing on top right.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_024(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify flight details is showing on top.");

			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("labelTravellerDetails"), paymentPage),
					"<b>Actual Result:</b> Flight Details are displayed on top as :'"+paymentPage.getTravellerDetails()+"'",
					"<b>Actual Result:</b> Flight Details are not displayed on top.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = { "desktop" }, description = "Verify booking progress bar is showing.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_025(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify booking progress bar should display on Paymentpage.");

			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("breadcrumbBookingProgress"), paymentPage),
					"<b>Actual Result:</b> Booking bar is displayed on Payment Page.",
					"<b>Actual Result:</b> Booking bar is not displayed on Payment Page.", driver);
			Log.testCaseResult();


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Verify that for a Logged in user eCash details are displayed in header on Payment page.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_026(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();


			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify that for a Logged in user eCash details are displayed in header on Payment page.");

			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("labelEcash"), paymentPage),
					"<b>Actual Result:</b> eCash details for logged in user is displayed on Payment Page as :'"+paymentPage.getTextFromEcashLabel()+"'",
					"<b>Actual Result:</b> eCash details for logged in user is not displayed on Payment Page.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Verify that user name is displayed for logged in user.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_027(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");


			paymentPage.verifyCancelEcash();

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify that user name is displayed for logged in user on Payment page.");

			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("labelUserName"), paymentPage),
					"<b>Actual Result:</b> User name is displayed for logged in user on Payment Page as :'"+paymentPage.getUserName()+"'",
					"<b>Actual Result:</b> User name is not displayed for logged in user on Payment Page.", driver);
			Log.testCaseResult();


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}


	@Test(groups = { "desktop" }, description = "Verify we are routing to review/pricing page seemlessly.", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_028(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");
		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");
			// step: verify Yatra title bar text

			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}

			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");
			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("8. Successfully Logged in Yatra account!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();

			Utils.waitForPageLoad(driver);
			reviewPage= paymentPage.clickOnEditLink();
			Log.message("10. Clicked on Edit Link under Review bookbar.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify we are routing to review/pricing page seemlessly.");

			Log.assertThat(reviewPage.getTextOfReviewHeading().contains("Review"),
					"<b>Actual Result:</b> Successfully navigated to reviewpage after clicking on 'Edit' link ",
					"<b>Actual Result:</b> Unable to navigate to reviewpage after clicking on 'Edit' link.", driver);

			Log.testCaseResult();

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	@Test(groups = { "desktop" }, description = "Check Delete stored card", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_029(HashMap<String, String> testData) throws Exception {
		Utils.testCaseConditionalSkip(testData.get("RunMode"));
		String browser = testData.get("browser");
		String emailId = testData.get("EmailAddress");
		String password = testData.get("Password");
		String origin = testData.get("Origin");
		String destination = testData.get("Destination");
		String departureDate = testData.get("DepartureDate");
		String passengerInfo = testData.get("PassengerInfo");
		String passengerClass = testData.get("Class");
		String infant = testData.get("Infant");
		String paymentType = testData.get("PaymentType");
		String cardNumber = testData.get("CardNumber");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			//loginPage = homePage.navigateToSignIn();
			loginPage = homePage.clickOnMainMenu(driver, "MyAccount", "Login");
			loginPage.loginYatraAccount(emailId, password);
			Log.message("2. Login 'Yatra' account successfully.");


			if (driver.getTitle().contains("Flight")) {
				Log.message("3. Verified Yatra Title text");
			}


			// selected trip as one way and enter the search details
			homePage.selectOneWayTrip();
			homePage.selectOneWayFlightSearchFields(origin, destination, departureDate, passengerInfo, passengerClass);
			Log.message("4. Successfully filled the search details for 'ONE WAY' trip.");

			// step: click 'Search' button in Yatra Home page
			searchResult = homePage.clickBtnSearch();
			Log.message("5. Clicked on 'Search' in Yatra Homepage.");

			Log.assertThat(	searchResult.elementLayer.verifyPageElements(Arrays.asList("btnModifySearchIcon"), searchResult),
					"<b>Actual Result:</b> Successfully navigated to SearchResult Page.",
					"<b>Actual Result:</b> Unable to navigated on SearchResult Page.", driver);

			// clicked on book now button in one way
			reviewPage = searchResult.clickOnBookNowInOneWay(1);	
			Log.message("6. Clicked on 'Book Now' button in Search Result Page.");

			Log.assertThat(reviewPage.elementLayer.verifyPageElements(Arrays.asList("btnChngeFlight"), reviewPage),
					"<b>Actual Result:</b> Successfully navigated on Review Page.",
					"<b>Actual Result:</b> Unable to navigated on Review Page.", driver);

			// clicke on continue button
			travellerPage = reviewPage.clickOnContinue();
			Log.message("7. Clicked on Continue button on Review Page.");

			/*	reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7. Successfully Logged in Yatra account as 'Existing' User!");
			 */
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			paymentPage.verifyCancelEcash();
			paymentPage.cancelCreditCardDetails();

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected "+paymentType+" as mode of Payment");


			paymentPage.enterCreditCardDetails(cardNumber);
			Log.message("11. Entered credit card details.");

			paymentPage.saveCreditCardDetails();
			Log.message("12. Saved credit card details in quick book.");


			paymentPage.clickOnPayNow();
			Log.message("13. Clicked on 'PayNow' button on Payment Page.");

			Utils.waitForPageLoad(driver);
			Log.assertThat(driver.getCurrentUrl().contains("hdfcbank"),
					"<b>Actual Result:</b> Successfully navigated back on Bank Page.",
					"<b>Actual Result:</b> Unable to navigated back on Bank Page.", driver);


			paymentPage.cancelHdfcPayment();
			Log.message("14. Clicked on Cancel button.");

			driver.navigate().back();
			Log.message("15. Navigating back to 'Yatra' page.");

			Utils.waitForPageLoad(driver);
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("logoYatra"), paymentPage),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);

			String cc_number = paymentPage.getCrediCrdNum();

			Utils.waitForPageLoad(driver);
			Log.assertThat(paymentPage.elementLayer.verifyPageElements(Arrays.asList("tabSavedCC"), paymentPage)&&(cardNumber.contains(cc_number)),
					"<b>Actual Result:</b> Saved credit card details are showing in credit card option.",
					"<b>Actual Result:</b> Saved credit card details are not showing in credit card option.", driver);

			Utils.waitForPageLoad(driver);

			paymentPage.cancelCreditCardDetails();
			Log.message("16. Deleted stored card details.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify stored card is getting deleted from list.");
			Log.assertThat(paymentPage.elementLayer.verifyPageElementsDoNotExist(Arrays.asList("tabSavedCC"), paymentPage),
					"<b>Actual Result:</b> Saved credit card details are deleted from the list.",
					"<b>Actual Result:</b> Saved credit card details are not deleted from the list.", driver);

			Log.testCaseResult();


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}

	

}//PaymentTest