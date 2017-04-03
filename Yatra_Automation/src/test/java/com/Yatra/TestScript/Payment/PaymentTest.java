package com.Yatra.TestScript.Payment;

import java.util.Arrays;

//-----------------------------------------------------------------------------------------------------------
//Description    :   All the Hotels Search test Cases would be designed in this class 
//Creator        :   Aspire Team
//Create         :   
//Modified on/By :   -
//-----------------------------------------------------------------------------------------------------------

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
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
	
	String BlueColor = "rgba(16, 114, 181, 1)";

	@BeforeTest(alwaysRun = true)
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite"));
	}

	@Test(groups = { "desktop" }, description = "verify Ecash Redemption ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_001(HashMap<String, String> testData) throws Exception {
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

			loginPage = homePage.navigateToSignIn();
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

			/*reviewPage.clickOnExistingUser();
			travellerPage = reviewPage.loginYatraGuestAccountExisting(emailId, password);
			Log.message("7. Successfully Logged in Yatra account as 'Existing' User!");
*/
			travellerPage.fillTravellerDetails_DOM(infantDOB);
			Log.message("8. Filled Traveller Details for domestic Flights.");

			paymentPage = travellerPage.clickOnContinue();
			Log.message("9. Clicked on Continue button on Travellers Page.");

			String initialEcash1 = paymentPage.eCashAmount();
			int initialEcash = Integer.parseInt(initialEcash1);
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify if user has ecash greater than 0.");
			Log.assertThat(initialEcash>0,
					"<b>Actual Result1:</b> Ecash amount is greater than 0.",
					"<b>Actual Result1:</b> Ecash amount is not greater than 0.",
					driver);
			
			paymentPage.scrollSliderOfEcashRedeem(20);
			Log.message("10. Scroll the ecash Redeem Slider to adjust ecash amount.");
			
			String finalEcash1 = paymentPage.eCashAmount();
			int finalEcash = Integer.parseInt(finalEcash1);

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


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			//driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "verify CreditCard Transaction", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_002(HashMap<String, String> testData) throws Exception {
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
		String cardNumber = testData.get("CreditCardNumber");
        String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			loginPage = homePage.navigateToSignIn();
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

		    paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected "+paymentType+" as mode of Payment");
			
			paymentPage.enterCreditCardDetails(cardNumber);
			Log.message("11. Entered credit card details.");

            paymentPage.clickOnPayNow();
			Log.message("12. Clicked on 'PayNow' button on Payment Page.");
  
			Thread.sleep(5000);
            Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(driver.getCurrentUrl().contains("hdfcbank"),
					"<b>Actual Result:</b> Successfully navigated back on Bank Page.",
					"<b>Actual Result:</b> Unable to navigated back on Bank Page.", driver);
			
			paymentPage.cancelHdfcPayment();
			Log.message("13. Clicked on Cancel button.");
			
			if(browser.equalsIgnoreCase("firefox_windows")){
				for(int i=0;i<2;i++){
			driver.navigate().back();
				}
			}
			driver.navigate().back();

			Log.message("14. Navigating back to 'Yatra' page.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(driver.getCurrentUrl().contains("yatra"),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);


		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
	
	@Test(groups = { "desktop" }, description = "verify NetBanking Single Option ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_005(HashMap<String, String> testData) throws Exception {
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

			loginPage = homePage.navigateToSignIn();
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

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected '"+paymentType+"' as mode of Payment");
			
			paymentPage.selectNetBankName(bankName);
			Log.message("11. Selected '"+bankName+"' for payment.");
			
			paymentPage.clickOnPayNow();
			Log.message("12. Clicked on 'Pay Now' for making payment.");
			
			
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(driver.getCurrentUrl().contains(URL),
					"<b>Actual Result:</b> Successfully navigated on "+bankName+" Bank Page.",
					"<b>Actual Result:</b> Unable to navigated on "+bankName+" Bank Page.", driver);
			if(browser.equalsIgnoreCase("firefox_windows")){
				for(int i=0;i<2;i++){
			driver.navigate().back();
				}
			}
			driver.navigate().back();

			Log.message("13. Navigating back to 'Yatra' page.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(driver.getCurrentUrl().contains("yatra"),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);
		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "Verify Emi Options ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_006(HashMap<String, String> testData) throws Exception {
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
		String cardNumber = testData.get("CreditCardNumber");

		String infant = testData.get("Infant");
		String[] infantDOB = infant.split(",");

		// Get the web driver instance
		final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		try {
			// step: Navigate to Yatra Home Page
			HomePage homePage = new HomePage(driver, webSite).get();
			Log.message("1. Navigated to 'Yatra' Home Page!");

			loginPage = homePage.navigateToSignIn();
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

			paymentPage.selectPaymentType(paymentType);
			Log.message("11. Selected '"+paymentType+"' as mode of Payment");
			
			paymentPage.selectCardNameInEMIDropdown(cardName);
			Log.message("12. Selected "+cardName+" for payment.");
				
			paymentPage.enterCreditCardDetailsInEMI(cardNumber);
			Log.message("13. Enter 'CreditCard' details for making payment.");
			
			paymentPage.clickOnPayNow();
			Log.message("14. Clicked on 'Pay Now' for making payment.");
				
			Thread.sleep(5000);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(driver.getCurrentUrl().contains("hdfcbank"),
					"<b>Actual Result:</b> Successfully navigated back on "+cardName+" Page.",
					"<b>Actual Result:</b> Unable to navigated back on "+cardName+" Page.", driver);
			
			paymentPage.cancelHdfcPayment();
			Log.message("15. Clicked on Cancel button.");
			
			if(browser.equalsIgnoreCase("firefox_windows")){
				for(int i=0;i<2;i++){
			driver.navigate().back();
				}
			}
			driver.navigate().back();

			Log.message("16. Navigating back to 'Yatra' page.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(driver.getCurrentUrl().contains("yatra"),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	@Test(groups = { "desktop" }, description = "Verify MobileWallet Single Option ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_007(HashMap<String, String> testData) throws Exception {
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

			loginPage = homePage.navigateToSignIn();
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

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected "+paymentType+" as mode of Payment");
				
		    paymentPage.selectMobileWallet(walletName);
			Log.message("11. Selected "+walletName+" for payment");

		    
			paymentPage.clickOnPayNow();
			Log.message("12. Clicked on 'Pay Now' for making payment.");
				
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected "+paymentType+" Page.");
			Log.assertThat(driver.getCurrentUrl().contains(walletName),
					"<b>Actual Result:</b> Successfully navigated back on "+walletName+" Page.",
					"<b>Actual Result:</b> Unable to navigated back on "+walletName+" Page.", driver);
			
			
			
			paymentPage.clickOnBackToYatraLinkFreechrge();
			Log.message("13. Navigating back to 'Yatra' page.");

			Thread.sleep(5000);
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(driver.getCurrentUrl().contains("yatra"),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
		//	driver.quit();
			Log.endTestCase();
		}
	}
	
	@Test(groups = { "desktop" }, description = "Verify Eze Click ", dataProviderClass = DataProviderUtils.class, dataProvider = "multipleExecutionData")
	public void TC_Yatra_Payment_010(HashMap<String, String> testData) throws Exception {
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

			loginPage = homePage.navigateToSignIn();
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

			paymentPage.selectPaymentType(paymentType);
			Log.message("10. Selected "+paymentType+" as mode of Payment");
				
		
			paymentPage.clickOnPayNow();
			Log.message("11. Clicked on 'Pay Now' for making payment.");
			
				
			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected "+paymentType+" Page.");
			Log.assertThat(driver.getCurrentUrl().contains(URL),
					"<b>Actual Result:</b> Successfully navigated back on "+paymentType+" Page.",
					"<b>Actual Result:</b> Unable to navigated back on "+paymentType+" Page.", driver);
			
			
			
			if(browser.equalsIgnoreCase("firefox_windows")){
				for(int i=0;i<2;i++){
			driver.navigate().back();
				}
			}
			driver.navigate().back();

			Log.message("12. Navigating back to 'Yatra' page.");

			Log.message("<br>");
			Log.message("<b>Expected Result:</b> Verify user is navigated to selected Bank Page.");
			Log.assertThat(driver.getCurrentUrl().contains("yatra"),
					"<b>Actual Result:</b> Successfully navigated back on Yatra Page.",
					"<b>Actual Result:</b> Unable to navigated back on Yatra Page.", driver);

		} catch (Exception e) {
			Log.exception(e);
		} finally {
			driver.quit();
			Log.endTestCase();
		}
	}
	
}