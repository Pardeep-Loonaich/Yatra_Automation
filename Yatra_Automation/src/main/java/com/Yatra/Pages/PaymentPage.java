
package com.Yatra.Pages;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class PaymentPage extends LoadableComponent<PaymentPage> {

	private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra PaymentPage ***********************************
	 **********************************************************************************************/
	@FindBy(xpath = "//input[@id= 'payNow']")
	public WebElement btnPayNow;

	@FindBy(css = "#cc_cno_id")
	WebElement creditCardNumber;

	@FindBy(css = "#cc_cardholder_name_id")
	WebElement creditCardName;

	@FindBy(css = "#cc_cvv_id")
	WebElement creditCardCvv;

	@FindBy(css = "#dc")
	WebElement lnkDebitCard;

	@FindBy(css = "#dc_cno_id")
	WebElement debitCardNumber;

	@FindBy(css = "#dc_cardholder_name_id")
	WebElement debitCardName;

	@FindBy(css = "#dc_cvv_id")
	WebElement debitCardCvv;

	@FindBy(css = "#toater_20")
	WebElement popUpInvalidCardNumber;

	@FindBy(css = "[id='redeem-applied-id']>h4")
	WebElement msgEcashRedeemBalance;

	@FindBy(css = "[id='redeem-applied-id']>h3")
	WebElement msgEcashRedeem;

	@FindBy(css="a[id='cancelRedemption']")
	WebElement lnkCancelRedem;

	@FindBy(css="a[id='okgotitlink']")
	WebElement lnkGotIt;

	@FindBy(css="input[id='redeem-ecash-button']")
	WebElement btnRedeemNow;

	@FindBy(css="#paymentDetailsCont>div>ul[class='noListStyle']")
	WebElement modulePaymentDetails;

	@FindBy(css ="div[id='cpmt_tabContainer']>ul>li")
	List<WebElement> paymentType;

	@FindBy(css ="li[id='cpmt_otherPayOp']>ul>li")
	List<WebElement> otherPaymentType;

	@FindBy(css ="div[class='net-banking-desk']>article[class='cpmt_net cpmt_lastInfo']>ul>li")
	List<WebElement> selectNetBank;

	@FindBy(css="span[id='total-redeemable-ecash']")
	WebElement appliedEcash;

	@FindBy(css="	div[id='login-cont']>p[class='back-merchant ng-scope']>a[ng-click='vm.backToMerchant()']")
	WebElement lnkBckToYatraFrmFreechrge;

	@FindBy(css ="div[id='tab_mw']>article>ul>li")
	List<WebElement> selectMobiWallet;

	@FindBy(css ="div[id='tab_atm']>article>ul>li")
	List<WebElement> selectATMCard;

	@FindBy(css="input[class='BtnAlign cancelBtn Mbtn-cancel']")
	WebElement btnCancelInHdfc;

	@FindBy(css ="select[id='emiBank_select']>option")
	List<WebElement> selectCardInEMI;

	@FindBy(css ="form[name='modeform']>table>tbody>tr>td[align='right']>input")
	List<WebElement> selectITZCard;

	@FindBy(css="span[class='sbi logo']")
	WebElement logoSBI;

	@FindBy(css="input[name='Cancel']")
	WebElement btnCancelInSBIATM;

	@FindBy(css="a[title='yatra.com']")
	WebElement logoYatra;
	
	@FindBy(css ="#cpmt_tabContainer>ul")
	WebElement lstPaymentMetod;
	
	@FindBy(css= "div[id='signinlyr']>h2")
	WebElement logoMobiWikWallet;
	
	@FindBy(css= "a[title='Oxigen Wallet']")
	WebElement logoOxyGenWallet;
	
	@FindBy(css= ".icon-payumoney")
	WebElement logoPayUWallet;
	
	@FindBy(css= ".img-responsive")
	WebElement logoBuddyWallet;	
	
	@FindBy(css= ".logojio")
	WebElement logoJioMoneyWallet;
	
	@FindBy(css= "#freechargeLogo")
	WebElement logoFreechargeWallet;

	@FindBy(css= "header>img")
	WebElement logoOlaMoneyWallet;

	@FindBy(css= ".container>div[style*='vertical-align']")
	WebElement logoPayZAppWallet;
	
	@FindBy(css= ".vf_logo")
	WebElement logoVodafoneWallet;
	
	@FindBy(css= "form[id='pgWalletPay']")
	WebElement formIdeaMoneyWallet;
	
	/**********************************************************************************************
	 ********************************* WebElements of Yatra PaymentPage - Ends ****************************
	 **********************************************************************************************/

	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : Webdriver
	 * 
	 * @param url
	 *            : UAT URL
	 */

	public PaymentPage(WebDriver driver, String url) {
		appURL = url;
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}// SearchPage

	/**
	 * 
	 * @param driver
	 *            : webdriver
	 */
	public PaymentPage(WebDriver driver) {
		Utils.waitForPageLoad(driver);
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		elementLayer = new ElementLayer(driver);
	}

	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, btnPayNow))) {
			Log.fail("PaymentPage didn't open up", driver);
		}
		// elementLayer = new ElementLayer(driver);
	}

	@Override
	protected void load() {
		isPageLoaded = true;

		Utils.waitForPageLoad(driver);
	}// load

	/**
	 * Filling Credit Card Details
	 * 
	 * @return
	 * @throws Exception
	 */
	public void enterCreditCardDetails(String cardNumber) throws Exception {
		String randomName = RandomStringUtils.randomAlphabetic(7).toLowerCase();
		String randomCvv = RandomStringUtils.randomNumeric(3);

		BrowserActions.typeOnTextField(creditCardNumber,cardNumber, driver, "Credit card Number");
		BrowserActions.typeOnTextField(creditCardName, randomName, driver, "Credit card Name");

		String css = "#cc_expm_id";
		WebElement month = driver.findElement(By.cssSelector(css));
		BrowserActions.clickOnElement(month, driver, "Date");
		List<WebElement> months = driver.findElements(By.cssSelector("#cc_expm_id>option"));
		if (months.size() != 0) {
			int rand = Utils.getRandom(1, months.size());
			BrowserActions.clickOnElement(months.get(rand), driver, "Month Selected");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);
		String css1 = "#cc_expy_id";
		WebElement year = driver.findElement(By.cssSelector(css1));
		BrowserActions.clickOnElement(year, driver, "Year");
		List<WebElement> year1 = driver.findElements(By.cssSelector("#cc_expy_id>option"));
		if (year1.size() != 0) {
			int rand = Utils.getRandom(1, year1.size());
			BrowserActions.clickOnElement(year1.get(rand), driver, "Year Selected");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);

		BrowserActions.typeOnTextField(creditCardCvv, randomCvv, driver, "Credit card Cvv");
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("#cc_SaveOptionDiv>label")), driver, "Unchecking Save QB");
	}

	/**
	 * Clicking on Pay Now In Payment Page
	 * 
	 * @throws Exception
	 */

	public void clickOnPayNow() throws Exception {
		BrowserActions.scrollToView(btnPayNow, driver);
		BrowserActions.javascriptClick(btnPayNow, driver, "Pay Now");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * Getting the text from the Error Pop Up In Credit card
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextErrorMessage() throws Exception {
		String txtDetails = BrowserActions.getText(driver, popUpInvalidCardNumber,
				"Getting text from the Credit card Error message");
		return txtDetails;

	}

	/**
	 * Clicking on Debit Card Link
	 * 
	 * @throws Exception
	 */

	public void clickOnDebitCardLink() throws Exception {
		BrowserActions.scrollToView(lnkDebitCard, driver);
		BrowserActions.javascriptClick(lnkDebitCard, driver, "Debit Card Link");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * Filling Debit Card Details
	 * 
	 * @return
	 * @throws Exception
	 */
	public void enterDebitCardDetails() throws Exception {
		clickOnDebitCardLink();
		String css = "#dc_expm_id";
		WebElement month = driver.findElement(By.cssSelector(css));
		BrowserActions.clickOnElement(month, driver, "Month");
		List<WebElement> months = driver.findElements(By.cssSelector("#dc_expm_id>option"));
		if (months.size() != 0) {
			int rand = Utils.getRandom(1, months.size());
			BrowserActions.clickOnElement(months.get(rand), driver, "Month Selected");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);
		String css1 = "#dc_expy_id";
		WebElement Year = driver.findElement(By.cssSelector(css1));
		BrowserActions.clickOnElement(Year, driver, "Year");
		List<WebElement> Year1 = driver.findElements(By.cssSelector("#dc_expy_id>option"));
		if (Year1.size() != 0) {
			int rand = Utils.getRandom(1, Year1.size());
			BrowserActions.clickOnElement(Year1.get(rand), driver, "Year Selected");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);
		String randomNumber = RandomStringUtils.randomNumeric(10);
		String randomName = RandomStringUtils.randomAlphabetic(7).toLowerCase();
		String randomCvv = RandomStringUtils.randomNumeric(3);
		BrowserActions.typeOnTextField(debitCardNumber, randomNumber, driver, "Debit card Number");
		BrowserActions.typeOnTextField(debitCardName, randomName, driver, "Debit card Name");
		BrowserActions.typeOnTextField(debitCardCvv, randomCvv, driver, "Debit card Cvv");

	}

	/**
	 * Getting the ecash redeem successful messages
	 * 
	 * @return
	 * @throws Exception
	 */

	public String getMsgFromEcashRedeemSuccess() throws Exception {
		String txtRedeemMsg = BrowserActions.getText(driver, msgEcashRedeem, "Getting text for ecash Redeem message.")
				.replace("Cancel Redemption", "");
		return txtRedeemMsg;

	}

	/**
	 * Getting the ecash redeem Balance successful messages
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getMsgFromEcashBalanceDeduction() throws Exception {
		String txtRedeemMsg = BrowserActions.getText(driver, msgEcashRedeemBalance,
				"Getting text for ecash Redeem Balance deduction message.");
		return txtRedeemMsg;

	}

	/**
	 * Clicked on the cancel ecash redeem
	 * @return
	 * @throws Exception
	 */
	public void clickingOnGotIt() throws Exception{
		BrowserActions.clickOnElement(lnkGotIt, driver, "Clicked on OK GOT IT ecash redeem.");

	}

	/**
	 * Clicked on the cancel ecash redeem
	 * @return
	 * @throws Exception
	 */
	public void clickingToCancelEcashRedem() throws Exception{
		BrowserActions.clickOnElement(lnkCancelRedem, driver, "Clicked on cancel ecash redeem.");

	}



	/**
	 * Clicked on the  ecash redeem
	 * @return
	 * @throws Exception
	 */
	public void clickingOnRedeemNow() throws Exception{
		BrowserActions.clickOnElement(btnRedeemNow, driver, "Clicked on Redeem Now.");

	}
	/**
	 * Getting the text from the Payment details panel
	 * @return
	 * @throws Exception
	 */
	public String getTextFromPaymentDetailsModule() throws Exception{
		String txtDetails = BrowserActions.getText(driver, modulePaymentDetails, "Getting text from the Payment details module.");
		return txtDetails;

	}

	/**
	 * to select the payment time from left panel
	 * @param PaymentType
	 * @throws Exception
	 */

	public void selectPaymentType(String PaymentType) throws Exception {
		List<WebElement> lstElement = paymentType;
		//List<WebElement> lstOtherElement = otherPaymentType;
		if(PaymentType.equals("ezeClick")||PaymentType.equals("Reward Points")){
			BrowserActions.scrollToViewElement(driver.findElement(By.cssSelector("li[id='cpmt_others']>a")), driver);
			BrowserActions.clickOnElement(driver.findElement(By.cssSelector("li[id='cpmt_others']>a")), driver, "list elements");

			for (WebElement ele : otherPaymentType) {
				if (ele.findElement(By.cssSelector("a")).getText().equals(PaymentType)) 
				{
					BrowserActions.scrollToViewElement(ele.findElement(By.cssSelector("a")), driver);
					BrowserActions.clickOnElement(ele.findElement(By.cssSelector("a")), driver, "list elements in others");
					break;
				}
			}
		}

		else{
			for (WebElement e : lstElement) {
				if (e.findElement(By.cssSelector("a")).getText().equals(PaymentType)) {

					BrowserActions.scrollToViewElement(e.findElement(By.cssSelector("a")), driver);
					BrowserActions.clickOnElement(e.findElement(By.cssSelector("a")), driver, "list elements");
					break;

				}
			}
		}
	}


	/**
	 * to select the net bank name
	 * @param BankName
	 * @throws Exception
	 */


	public void selectNetBankName(String BankName) throws Exception {
		List<WebElement> lstElement = selectNetBank;

		for (WebElement e : lstElement) {
			if (e.findElement(By.cssSelector("label>label")).getAttribute("class").contains(BankName)) {
				BrowserActions.scrollToViewElement(e.findElement(By.cssSelector("label>label")), driver);
				BrowserActions.clickOnElement(e.findElement(By.cssSelector("label>label")), driver, "Selected Net bank");
				break;

			}
		}

	}


	/**
	 * to select the mobile wallet name
	 * @param BankName
	 * @throws Exception
	 */


	public void selectMobileWallet(String WalletName) throws Exception {
		List<WebElement> lstElement = selectMobiWallet;

		for (WebElement e : lstElement) {
			if (e.findElement(By.cssSelector("label>label")).getAttribute("class").contains(WalletName)) {
				BrowserActions.scrollToViewElement(e.findElement(By.cssSelector("label>label")), driver);
				BrowserActions.clickOnElement(e.findElement(By.cssSelector("label>label")), driver, "selected Mobile Wallet");
				break;

			}
		}

	}

	/**
	 * to select the ATM Card Name
	 * @param BankName
	 * @throws Exception
	 */


	public void selectATMCardName(String ATMCardName) throws Exception {
		List<WebElement> lstElement = selectATMCard;

		for (WebElement e : lstElement) {
			if (e.findElement(By.cssSelector("label>label")).getAttribute("class").contains(ATMCardName)) {
				BrowserActions.scrollToViewElement(e.findElement(By.cssSelector("label>label")), driver);
				BrowserActions.clickOnElement(e.findElement(By.cssSelector("label>label")), driver, "seleted ATM.");
				break;

			}
		}

	}


	/**
	 * to select the card from dropdown in EMI
	 * @param BankName
	 * @throws Exception
	 */


	public void selectCardNameInEMIDropdown(String cardName) throws Exception {
		List<WebElement> lstElement = selectCardInEMI;
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("li[id='selcted-bank']>label")), driver, "Clicked on Card dropdown.");

		for (WebElement e : lstElement) {
			if (e.getAttribute("value").contains(cardName)) {
				BrowserActions.scrollToViewElement(e, driver);
				BrowserActions.clickOnElement(e, driver, "Selected Card");
				break;

			}
		}
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("div[class='cpmt_tnCstrip']>label")), driver, "Checked terms & condition checkbox.");

	}


	/**
	 * getting text of ecash under slider
	 * @return
	 * @throws Exception
	 */
	public String eCashAmount() throws Exception{
		String ecash = appliedEcash.getText();
		return ecash;

	}
	/**
	 * scrolling the ecash slider
	 * @param value
	 */
	public void scrollSliderOfEcashRedeem(int value){
		WebElement sliderA = driver.findElement(By.cssSelector(".slider-base"));
		Actions action = new Actions(driver);
		action.dragAndDropBy(sliderA, value, 0).build().perform();
	}


	@FindBy(css = "#emi_cno_id")
	WebElement creditCardNumberInEMI;

	@FindBy(css = "#emi_cardholder_name_id")
	WebElement creditCardHolderNameInEMI;

	@FindBy(css = "#emi_cvv_id")
	WebElement creditCardCvvInEMI;

	@FindBy(css = "#emi_expm_id")
	WebElement creditCardMonthInEMI;

	@FindBy(css = "#emi_expy_id")
	WebElement creditCardYearInEMI;

	/**
	 * Filling Credit Card Details in the EMI option
	 * 
	 * @return
	 * @throws Exception
	 */
	public void enterCreditCardDetailsInEMI(String cardNumber) throws Exception {

		String randomName = RandomStringUtils.randomAlphabetic(7).toLowerCase();
		String randomCvv = RandomStringUtils.randomNumeric(3);
		BrowserActions.typeOnTextField(creditCardNumberInEMI,cardNumber, driver, "Credit card Number in EMI");
		BrowserActions.typeOnTextField(creditCardHolderNameInEMI, randomName, driver, "Credit card holder Name in EMI");


		BrowserActions.clickOnElement(creditCardMonthInEMI, driver, "Clicked on Month dropdown.");
		List<WebElement> monthsList = driver.findElements(By.cssSelector("#emi_expm_id>option"));
		if (monthsList.size() != 0) {
			int rand = Utils.getRandom(1, monthsList.size());
			BrowserActions.clickOnElement(monthsList.get(rand), driver, "Month Selected from dropdown");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);

		BrowserActions.clickOnElement(creditCardYearInEMI, driver, "Clicked on Year dropdown.");
		List<WebElement> yearList = driver.findElements(By.cssSelector("#emi_expy_id>option"));
		if (yearList.size() != 0) {
			int rand = Utils.getRandom(1, yearList.size());
			BrowserActions.clickOnElement(yearList.get(rand), driver, "Year Selected from dropdown");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);

		BrowserActions.typeOnTextField(creditCardCvvInEMI, randomCvv, driver, "Credit card Cvv");

	}

	/**
	 * to navigate back to pages acc to browser
	 * @param browser
	 * @throws Exception
	 */

	public void navigateToBackPage(String browser) throws Exception{
		if(browser.equalsIgnoreCase("browser")){
			for(int i=0;i<2;i++){
				driver.navigate().back();
			}
		}
		else
			driver.navigate().back();

	}

	/**
	 * to click on Cancel in HDFC payment page
	 * @throws Exception
	 */
	public void cancelHdfcPayment() throws Exception{
		BrowserActions.javascriptClick(btnCancelInHdfc, driver, "Clicked on cancel button");
		BrowserActions.javaScriptAlertPopUpHandler(driver, "cancel");
	}

	/**
	 * to click on cancel in SBI ATM payment
	 * @throws Exception
	 */
	public void cancelSBIATMPayment() throws Exception{
		BrowserActions.javascriptClick(btnCancelInSBIATM, driver, "Clicked on cancel button");
		//BrowserActions.javaScriptAlertPopUpHandler(driver, "cancel");
	}

	/**
	 * to navigate back to yatra from freecharge page
	 * @throws Exception
	 */
	public void clickOnBackToYatraLinkFreechrge() throws Exception{
		BrowserActions.clickOnElement(lnkBckToYatraFrmFreechrge, driver, "Clicked on back to yatra link");
	}

	/**
	 * to select the card from dropdown in EMI
	 * @param BankName
	 * @throws Exception
	 */
	public void selectITZCashRan(String itzName) throws Exception {
		List<WebElement> lstElement =selectITZCard ;
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("form[name='modeform']>table>tbody>tr>td[align='right']>input")), driver, "Clicked on Card dropdown.");

		for (WebElement e : lstElement) {
			if (e.getAttribute("value").contains(itzName)) {
				BrowserActions.scrollToViewElement(e, driver);
				BrowserActions.clickOnElement(e, driver, "Selected ITZ Type");
				break;

			}
		}

	}
	/**
	 * to click on continue and cancelling the order for ITZCash
	 * @throws Exception
	 */
	public void cancelOrderForITZCash() throws Exception{
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("input[name='continue']")), driver, "Clicked on continue in portal");
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("input[name='cancel']")), driver, "Clicked on cancelorder in portal");

	}



	/*
	@FindBy(css ="div[id='submitciti']")
	WebElement btnSubmit;

	public void clickedOnSubmit() throws Exception {

		BrowserActions.scrollToViewElement(btnSubmit, driver);
		BrowserActions.clickOnElement(btnSubmit, driver, "Clicked on Submit button for city bank");

	}*/ 

	//enterCreditCardDetails


}

