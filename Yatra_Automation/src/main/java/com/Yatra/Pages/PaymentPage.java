package com.Yatra.Pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
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
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class PaymentPage extends LoadableComponent<PaymentPage> {

	private String appURL;


	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();



	/**********************************************************************************************
	 ********************************* WebElements of Yatra PaymentPage ***********************************
	 **********************************************************************************************/

	@FindBy(xpath = "//input[@id= 'payNow']")
	private WebElement btnPayNow;

	@FindBy(css = "#cc_cno_id")
	private WebElement creditCardNumber;

	@FindBy(css = "#cc_cardholder_name_id")
	private WebElement creditCardName;

	@FindBy(css = "#cc_cvv_id")
	private WebElement creditCardCvv;

	@FindBy(css = "#dc")
	private WebElement lnkDebitCard;

	@FindBy(css = "#dc_cno_id")
	private WebElement debitCardNumber;

	@FindBy(css = "#dc_cardholder_name_id")
	private WebElement debitCardName;

	@FindBy(css = "#dc_cvv_id")
	private WebElement debitCardCvv;

	@FindBy(css = "#toater_20")
	private WebElement popUpInvalidCardNumber;

	@FindBy(css = "[id='redeem-applied-id']>h4")
	private WebElement msgEcashRedeemBalance;

	@FindBy(css = "[id='redeem-applied-id']>h3")
	private WebElement msgEcashRedeem;

	@FindBy(css="a[id='cancelRedemption']")
	private WebElement lnkCancelRedem;

	@FindBy(css="a[id='okgotitlink']")
	private WebElement lnkGotIt;

	@FindBy(css = "#totalAmountSpan")
	private WebElement totalAmount;

	@FindBy(css="input[id='redeem-ecash-button']")
	private WebElement btnRedeemNow;

	@FindBy(css="#paymentDetailsCont>div>ul[class='noListStyle']")
	private WebElement modulePaymentDetails;

	@FindBy(css ="div[id='cpmt_tabContainer']>ul>li")
	private List<WebElement> paymentType;

	@FindBy(css ="li[id='cpmt_otherPayOp']>ul>li")
	private List<WebElement> otherPaymentType;

	@FindBy(css ="div[class='net-banking-desk']>article[class='cpmt_net cpmt_lastInfo']>ul>li")
	private List<WebElement> selectNetBank;

	@FindBy(css="span[id='total-redeemable-ecash']")
	private WebElement appliedEcash;

	@FindBy(css="	div[id='login-cont']>p[class='back-merchant ng-scope']>a[ng-click='vm.backToMerchant()']")
	private WebElement lnkBckToYatraFrmFreechrge;

	@FindBy(css ="div[id='tab_mw']>article>ul>li")
	private List<WebElement> selectMobiWallet;

	@FindBy(css ="div[id='tab_atm']>article>ul>li")
	private List<WebElement> selectATMCard;

	@FindBy(css="input[class='BtnAlign cancelBtn Mbtn-cancel']")
	private WebElement btnCancelInHdfc;

	@FindBy(css ="select[id='emiBank_select']>option")
	private List<WebElement> selectCardInEMI;

	@FindBy(css ="form[name='modeform']>table>tbody>tr>td[align='right']>input")
	private List<WebElement> selectITZCard;

	@FindBy(css="span[class='sbi logo']")
	private WebElement logoSBI;

	@FindBy(css="input[name='Cancel']")
	private WebElement btnCancelInSBIATM;

	@FindBy(css="a[title='yatra.com']")
	private WebElement logoYatra;

	@FindBy(css ="#cpmt_tabContainer>ul")
	private WebElement lstPaymentMetod;

	@FindBy(css= "div[id='signinlyr']>h2")
	private WebElement logoMobiWikWallet;

	@FindBy(css= "a[title='Oxigen Wallet']")
	private WebElement logoOxyGenWallet;

	@FindBy(css= "div[class='header']>a[class='icon-payumoney']")
	private WebElement logoPayUWallet;

	@FindBy(css= ".img-responsive")
	private WebElement logoBuddyWallet;	

	@FindBy(css= ".logojio")
	private WebElement logoJioMoneyWallet;

	@FindBy(css= "#freechargeLogo")
	private WebElement logoFreechargeWallet;

	@FindBy(css= "#closeButton")
	private WebElement btnOlaMoneyWallet;

	@FindBy(css= "#registerButton")
	private WebElement btnPayZAppWallet;

	@FindBy(css= ".vf_logo")
	private WebElement logoVodafoneWallet;

	@FindBy(css= "form[id='pgWalletPay']")
	private WebElement formIdeaMoneyWallet;

	@FindBy(css= ".e-cash-pay")
	private WebElement modEcash;

	@FindBy(css= ".leg")
	private WebElement labelFlightDetails;

	@FindBy(css= "div[class='pax-detailed']")
	private WebElement labelTravellerDetails;

	@FindBy(css= "div[class='container-fluid breadcrumb']")
	private WebElement breadcrumbBookingProgress;

	@FindBy(css= "span[class='simple-tab eCashholder']")
	private WebElement labelEcash;

	@FindBy(css= "li[id='userLoginBlock']")
	private WebElement labelUserName;

	@FindBy(css= "span[class='simple-tab eCashholder']>span")
	private WebElement ecashAmount;

	@FindBy(xpath= ".//*[@id='qb_newCreditCard']")
	private WebElement tabSavedCC;

	@FindBy(css="i[class='PaymentSprite removeIcon']")
	private WebElement iconRemove;

	@FindBy(css="#cc_expm_id")
	private WebElement monthCC;

	@FindBy(css="#cc_expm_id>option")
	private List <WebElement> lstMonthsCC;

	@FindBy(css="#cc_expy_id")
	private WebElement yearCC;

	@FindBy(css="#cc_expy_id>option")
	private List <WebElement> lstYearsCC;

	@FindBy(css="#cc_SaveOptionDiv>label")
	private WebElement lblSaveCCInQB;

	@FindBy(css="#dc_expm_id")
	private WebElement monthDC;

	@FindBy(css="#dc_expm_id>option")
	private List <WebElement> lstMonthsDC;

	@FindBy(css="#dc_expy_id")
	private WebElement yearDC;

	@FindBy(css="#dc_expy_id>option")
	private List <WebElement> lstYearsDC;

	@FindBy(css="li[id='cpmt_others']>a")
	private WebElement lnkOtherPayment;

	@FindBy(css="li[id='selcted-bank']>label")
	private WebElement drpdownSelectBnk;

	@FindBy(css="div[class='cpmt_tnCstrip']>label")
	private WebElement lblTncChkbox;

	@FindBy(css=".slider-base")
	private WebElement scrollSlider;

	@FindBy(css = "#emi_cno_id")
	private WebElement creditCardNumberInEMI;

	@FindBy(css = "#emi_cardholder_name_id")
	private WebElement creditCardHolderNameInEMI;

	@FindBy(css="#emi_expm_id>option")
	private List<WebElement> lstMonthEMI;

	@FindBy(css="#emi_expy_id>option")
	private List<WebElement> lstYearEMI;

	@FindBy(css = "#emi_cvv_id")
	private WebElement creditCardCvvInEMI;

	@FindBy(css = "#emi_expm_id")
	private WebElement creditCardMonthInEMI;

	@FindBy(css = "#emi_expy_id")
	private WebElement creditCardYearInEMI;

	@FindBy(css="form[name='modeform']>table>tbody>tr>td[align='right']>input")
	private WebElement rdoITZCard;

	@FindBy(css="input[name='continue']")
	private WebElement btnContinueITZPortal;

	@FindBy(css="input[name='cancel']")
	private WebElement btnCancelOrderITZPortal;

	@FindBy(css=".box-title.normal.fs-base.clearfix")
	private WebElement fldHeadingYatraEarn;

	@FindBy(css="#spanEditStep1>a")
	private WebElement lnkEdit;

	@FindBy(css="li[id='earnEcashDiv']>span")
	private WebElement txtEcashAmount;

	@FindBy(css=".eCahseValue")
	private WebElement txtEcashValueFromHeader;

	@FindBy(css="div[class='leg']>span[class='ib fl']")
	private WebElement txtFlightDetails;

	@FindBy(css="#userShowName")
	private WebElement txtUserNme;

	@FindBy(css="div[class='pax-detailed']>h3")
	private WebElement txtHeadingTravelDtl;

	@FindBy(css="div[class='pax-names']")
	private WebElement txtTravelDtl;

	@FindBy(css="#tncLink")
	private WebElement lnkTnC;

	@FindBy(css="span[class='hudini']")
	private WebElement txtTnCHeader;

	@FindBy(css = "#rw_cno_id")
	private WebElement txtcreditCardNumberInRewards;

	@FindBy(css = "#rw_cardholder_name_id")
	private WebElement txtcreditCardHolderNameInRewards;

	@FindBy(css="#rw_expm_id>option")
	private List<WebElement> drplstMonthRewards;

	@FindBy(css="#rw_expy_id>option")
	private List<WebElement> drplstYearRewards;

	@FindBy(css = "#rw_cvv_id")
	private WebElement txtcreditCardCvvInRewards;

	@FindBy(css = "#rw_expm_id")
	private WebElement creditCardMonthInRewards;

	@FindBy(css = "#rw_expy_id")
	private WebElement creditCardYearInRewards;

	@FindBy(css = "#rw_reward_points_id")
	private WebElement txtRewardPointInRewards;

	@FindBy(css = "#next")
	private WebElement btnNextRewardPage;

	@FindBy(css = "#dc_cvvMsg_id")
	private WebElement txtValidDBMsg;

	@FindBy(css = "div[id='failed_payment_res']>div>span>span")
	private WebElement txtFailedDBTrans;

	@FindBy(css="#qb_newCreditCard")
	private WebElement lblNewCC;

	@FindBy(css="span[class='cpmt_qbCardNo']")
	private WebElement CreditCardNumber; 

	@FindBy(css="ul[class='js-quick noListStyle']>li")
	private List<WebElement> lstCreditCardNumbers; 

	@FindBy(css="div[id='paymentDetailsCont']>div[class='totalPayInf']>ul[class='noListStyle']>li>span")
	private List <WebElement> lstPayAmount;

	@FindBy(css="#totalAmountSpan")
	private WebElement totalPayAmount;
	
	@FindBy(css="td[class='NewReg']>a")
	private WebElement btnAppDownInAllahabad;
	
	@FindBy(css="div[class='login-btn']")
	private WebElement btnLoginAxis;
	
	@FindBy(css="input[id='submitciti']")
	private WebElement btnSubmitCiti;
	
	@FindBy(css="input[name='fldLoginUserId']")
	private WebElement btnContinueHDFC;
	
	@FindBy(css="[class='login_button']")
	private WebElement btnLoginICICI;
	
	@FindBy(css="input[type='submit']")
	private WebElement btnLoginIDBI;
	
	@FindBy(css="a[id='secure-login01']")
	private WebElement btnLoginKotak;
	
	@FindBy(css="input[class='loginpage_btn_loginshp']")
	private WebElement btnLoginPNB;
	
	@FindBy(css="input[name='CorporateSignonCorpId']")
	private WebElement fldUserIDAndhra;
	
	@FindBy(css= "#Button1")
	private WebElement btnBckToYatraOxygen;
	
	@FindBy(css= ".back_btn")
	private WebElement btnBckToYatraPAyU;
	
	@FindBy(css= "#backButton")
	private WebElement btnBckToYatraSBIBuddy;
	
	@FindBy(css= "#cancelBtn")
	private WebElement btnCancelJio;
	
	@FindBy(css= "#okButton")
	private WebElement btnOkJio;

	@FindBy(css= "button[class='btn btn-warning col-xs-4']")
	private WebElement btnPayZ;
	
	@FindBy(css= "#rejectPaymentReq")
	private WebElement btnCancelIdea;

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
	}// 

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
		timer.end();
		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, btnPayNow))) {
			Log.fail("PaymentPage didn't open up", driver);
		}
		Log.message("Total time taken by #"+this.getClass().getTypeName()+"to load is:- "+timer.duration()+" "+TimeUnit.SECONDS);

		// elementLayer = new ElementLayer(driver);
	}

	@Override
	protected void load() {
		timer.start();
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
		BrowserActions.clickOnElement(monthCC, driver, "Date");
		if (lstMonthsCC.size() != 0) {
			int rand = Utils.getRandom(1, lstMonthsCC.size());
			BrowserActions.clickOnElement(lstMonthsCC.get(rand), driver, "Month Selected");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);
		BrowserActions.clickOnElement(yearCC, driver, "Year");
		if (lstYearsCC.size() != 0) {
			int rand = Utils.getRandom(1, lstYearsCC.size());
			BrowserActions.clickOnElement(lstYearsCC.get(rand), driver, "Year Selected");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);
		BrowserActions.typeOnTextField(creditCardCvv, randomCvv, driver, "Credit card Cvv");
		BrowserActions.clickOnElement(lblSaveCCInQB,driver ,"Unchecking Save QB");
	}

	/**
	 * to save the credit card details in quick book
	 * @throws Exception
	 */

	public void saveCreditCardDetails() throws Exception{
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnElement(lblSaveCCInQB, driver, "Cshecking Save QB");
	}


	/**
	 * to get text from credit card number first on the list
	 * @return
	 * @throws Exception
	 */
	public /*ArrayList<String>*/ String getCrediCrdNum() throws Exception{
		/*ArrayList<String> cardNumList = new ArrayList<String>();

		for(WebElement e:lstCreditCardNumbers){
		String cc_No = BrowserActions.getText(driver,CreditCardNumber,"Getting the CreditCardNumber");
		cardNumList.add("cc_No");

		}
		return cardNumList;*/

		String cc_no = BrowserActions.getText(driver, CreditCardNumber, "Getting the CreditCardNumber").substring(15, 19);
		return cc_no;
	}


	/**
	 * Getting the text from the Error message after the failed transaction
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
	 * Clicking on debit Card Link
	 * @throws Exception
	 */
	public void clickOnDebitCardLink() throws Exception {
		BrowserActions.scrollToView(lnkDebitCard, driver);
		BrowserActions.javascriptClick(lnkDebitCard, driver, "Debit Card Link");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * Filling all debit card details
	 * @param cardNumber
	 * @param cardCVV
	 * @throws Exception
	 */
	public void enterDebitCardDetails(String cardNumber,String cardCVV) throws Exception {

		String randomName = RandomStringUtils.randomAlphabetic(7).toLowerCase();
		BrowserActions.typeOnTextField(debitCardNumber, cardNumber, driver, "Debit card Number");
		BrowserActions.typeOnTextField(debitCardName, randomName, driver, "Debit card Name");
		BrowserActions.clickOnElement(monthDC, driver, "Month");
		if (lstMonthsDC.size() != 0) {
			int rand = Utils.getRandom(1, lstMonthsDC.size());
			BrowserActions.clickOnElement(lstMonthsDC.get(rand), driver, "Month Selected");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);
		BrowserActions.clickOnElement(yearDC, driver, "Year");
		if (lstYearsDC.size() != 0) {
			int rand = Utils.getRandom(1, lstYearsDC.size());
			BrowserActions.clickOnElement(lstYearsDC.get(rand), driver, "Year Selected");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);
		BrowserActions.typeOnTextField(debitCardCvv, cardCVV, driver, "Debit card Cvv");


	}

	/**
	 * Filling Debit Card Details
	 * 
	 * @return
	 * @throws Exception
	 */
	public void enterPartialDebitCardDetails(String cardNumber) throws Exception {

		String randomName = RandomStringUtils.randomAlphabetic(7).toLowerCase();
		BrowserActions.typeOnTextField(debitCardNumber, cardNumber, driver, "Debit card Number");
		BrowserActions.typeOnTextField(debitCardName, randomName, driver, "Debit card Name");

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

     @FindBy(css="div[class='redeem-block']")
     private WebElement blckRedeem;
	/**
	 * Clicked on the cancel ecash redeem after verification
	 * @return
	 * @throws Exception
	 */
	public void verifyCancelEcash() throws Exception{
		if(blckRedeem.getAttribute("style").contains("margin-bottom: 10px; display: none;")){
			BrowserActions.clickOnElement(lnkCancelRedem, driver, "Clicked on Cancel ecash redeem.");
		}
		
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
	 * getting text of ecash under slider
	 * @return
	 * @throws Exception
	 */
	public String eCashAmount() throws Exception{
		if(scrollSlider.isDisplayed()){
		Utils.waitForElement(driver, appliedEcash);
		String ecash = appliedEcash.getText();
		return ecash;
		}
		else
			Log.event("No eCash at the moment...");
            return null;
	}



	/**
	 * scrolling the ecash slider
	 * @param value
	 */
	public void scrollSliderOfEcashRedeem(int value){
		Actions action = new Actions(driver);
		action.dragAndDropBy(scrollSlider, value, 0).build().perform();
	}

	/**
	 * to return ecashHeading  from ecash module
	 * @return
	 * @throws Exception
	 */
	public String getEcashHeading() throws Exception{
		String ecashHeading = BrowserActions.getText(driver, fldHeadingYatraEarn,"Heading Yatra's Earn");
		return ecashHeading;
	}



	/**
	/**
	 * to return ecashAmount  from ecash module
	 * @return
	 * @throws Exception
	 */
	public String getEcashAmount() throws Exception{
		String ecashAmount = BrowserActions.getText(driver, txtEcashAmount,"Ecash Amount");
		return ecashAmount;
	}



	/**
	 * to return ecashAmount  from ecash header
	 * @return
	 * @throws Exception
	 */
	public String getTextFromEcashLabel() throws Exception{
		String ecashAmount = BrowserActions.getText(driver, txtEcashValueFromHeader,"Ecash Amount");
		return ecashAmount;
	}

	/**
	 * Getting the Total amount from payment module
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFromTotalAmount() throws Exception {
		Utils.waitForPageLoad(driver);
		String txtTotalAmount = BrowserActions.getText(driver, totalAmount,
				"Getting text for Total Amount.").trim().replace(",","");
		return txtTotalAmount;

	}





	/**
	 * to select the payment type from left navigations panel
	 * @param PaymentType
	 * @throws Exception
	 */

	public void selectPaymentType(String PaymentType) throws Exception {
		List<WebElement> lstElement = paymentType;
		if(PaymentType.equals("ezeClick")||PaymentType.equals("Reward Points")){
			BrowserActions.scrollToViewElement(lnkOtherPayment, driver);
			BrowserActions.clickOnElement(lnkOtherPayment, driver, "list elements");

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
					//findElement is required here
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
				//findElement is required here
				BrowserActions.scrollToViewElement(e.findElement(By.cssSelector("label>label")), driver);
				BrowserActions.clickOnElement(e.findElement(By.cssSelector("label>label")), driver, "Selected Net bank");
				break;

			}
		}

	}
	
	@FindBy(css ="select[id='nprBank']>option")
	private List<WebElement> lstselectOtherNetBank;
	
	@FindBy(css ="select[id='nprBank']")
	private WebElement drpselectOtherNetBank;
	


	/**
	 * to select the net bank name
	 * @param BankName
	 * @throws Exception
	 */


	public void selectOtherNetBankName(String BankName) throws Exception {
		List<WebElement> lstElement = lstselectOtherNetBank;
		BrowserActions.clickOnElement(drpselectOtherNetBank, driver, "clicked on Select Other bank dropdown");

		
		for (WebElement e : lstElement) {
			if (e.getAttribute("value").contains(BankName)) {
				//findElement is required here
				BrowserActions.scrollToViewElement(e, driver);
				BrowserActions.clickOnElement(e, driver, "Selected Other Net bank");
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
		//findElement is required here
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
		//findElement is required here

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
		BrowserActions.clickOnElement(drpdownSelectBnk, driver, "Clicked on Card dropdown.");

		for (WebElement e : lstElement) {
			if (e.getAttribute("value").contains(cardName)) {
				BrowserActions.scrollToViewElement(e, driver);
				BrowserActions.clickOnElement(e, driver, "Selected Card");
				break;

			}
		}
		BrowserActions.clickOnElement(lblTncChkbox, driver, "Checked terms & condition checkbox.");

	}


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
		if (lstMonthEMI.size() != 0) {
			int rand = Utils.getRandom(1, lstMonthEMI.size());
			BrowserActions.clickOnElement(lstMonthEMI.get(rand), driver, "Month Selected from dropdown");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);

		BrowserActions.clickOnElement(creditCardYearInEMI, driver, "Clicked on Year dropdown.");
		if (lstYearEMI.size() != 0) {
			int rand = Utils.getRandom(1, lstYearEMI.size());
			BrowserActions.clickOnElement(lstYearEMI.get(rand), driver, "Year Selected from dropdown");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);

		BrowserActions.typeOnTextField(creditCardCvvInEMI, randomCvv, driver, "Credit card Cvv");

	}

	/**
	 * to navigate back to payment page browser from the bank portals in case of credit card
	 * @param browser
	 * @throws Exception
	 */

	public void returnFromCreditCardPage(String browser,int ran) throws Exception{
		if(browser.equalsIgnoreCase("firefox_windows")){
			for(int i=0;i<ran;i++){
				driver.navigate().back();
			}
		}
		else if(browser.equalsIgnoreCase("iexplorer_windows")){
		}
		driver.navigate().back();
		driver.navigate().refresh();

	}



	/**
	 * to navigate back to payment page from RewardPoint and Credit card in case of CITI bank portal
	 * @param browser
	 * @throws Exception
	 */
	public void returnFromCitiPortal(String browser) throws Exception{
		if(browser.equalsIgnoreCase("chrome_windows")){
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("window.history.go(-1)");
			driver.switchTo().alert().accept();
			driver.navigate().refresh();

		}	

	}

	/**
	 * to click on cancel transaction in HDFC bank portal and click on the validation that appear on the popup
	 * @throws Exception
	 */


	public void cancelHdfcPayment(String browser) throws Exception{
		if(browser.equalsIgnoreCase("chrome_windows")){
			BrowserActions.javascriptClick(btnCancelInHdfc, driver, "Clicked on cancel button");
			BrowserActions.javaScriptAlertPopUpHandler(driver, "cancel");
		}
		else if(browser.equalsIgnoreCase("iexplorer_windows")){
			BrowserActions.javascriptClick(btnCancelInHdfc, driver, "Clicked on cancel button");

			BrowserActions.javaScriptAlertPopUpHandler(driver, "ok");
		}
		else if(browser.equalsIgnoreCase("FireFox_windows")){
			BrowserActions.javaScriptAlertPopUpHandler(driver, "ok");
		}
	}

	public void cancelHdfcPaymentIe() throws Exception{
		
			
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.history.go(-1)");
		//driver.switchTo().alert().accept();
	}
	

	/**
	 * to click on cancel button in SBI payment portal in case of ATM to cancel transaction 
	 * @throws Exception
	 */
	public void cancelSBIATMPayment() throws Exception{
		BrowserActions.javascriptClick(btnCancelInSBIATM, driver, "Clicked on cancel button");
		//BrowserActions.javaScriptAlertPopUpHandler(driver, "cancel");
	}

	/**
	 * to navigate back to payment page from freecharge page by clicking on 'Back to yatra link' in case of MOBILE WALLET
	 * @throws Exception
	 */
	public void clickOnBackToYatraLinkFreechrge() throws Exception{
		BrowserActions.clickOnElement(lnkBckToYatraFrmFreechrge, driver, "Clicked on back to yatra link");
	}


	/**
	 * Selecting the 'itzName' from the given options in ITZ cashcard page 
	 * @param itzName is same as value of the locator 
	 * @throws Exception
	 */
	public void selectITZCashRan(String itzName) throws Exception {
		List<WebElement> lstElement =selectITZCard ;
		BrowserActions.clickOnElement(rdoITZCard, driver, "Selected ITZ option.");

		for (WebElement e : lstElement) {
			if (e.getAttribute("value").contains(itzName)) {
				BrowserActions.scrollToViewElement(e, driver);
				BrowserActions.clickOnElement(e, driver, "Selected ITZ Type");
				break;

			}
		}

	}



	/**
	 * to click on continue and after that cancelling the order for ITZCash in ITZCash portal
	 * @throws Exception
	 */
	public void cancelOrderForITZCash() throws Exception{
		BrowserActions.clickOnElement(btnContinueITZPortal, driver, "Clicked on continue in portal");
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnElement(btnCancelOrderITZPortal, driver, "Clicked on cancelorder in portal");

	}




	/**
	 * to return Flight Details from header section
	 * @return
	 * @throws Exception
	 */
	public String getFlightDetails() throws Exception{
		String flightDetails = BrowserActions.getText(driver, txtFlightDetails,"Getting Text From Flight Details.").trim().replace("?", "");
		return flightDetails;
	}


	/**
	 * to return User Name from header
	 * @return
	 * @throws Exception
	 */
	public String getUserName() throws Exception{
		String userName = BrowserActions.getText(driver, txtUserNme,"Getting Text from user name.");
		return userName;
	}



	/**
	 * to return Travellers Details and heading
	 * @return
	 * @throws Exception
	 */
	public String getTravellerDetails() throws Exception{
		String travellerHeading = BrowserActions.getText(driver, txtHeadingTravelDtl,"Getting Heading of Traveller Details.");
		String travellerDetails = BrowserActions.getText(driver, txtTravelDtl,"Getting Text From Traveller Details.");
		String travellerInfo = travellerHeading.concat(travellerDetails);
		return travellerInfo;
	}




	/**
	 * to click on Terms and condition link 
	 * @return
	 * @throws Exception
	 */
	public void clickedOnTnCLink() throws Exception{
		BrowserActions.scrollToView(lnkTnC,driver);
		BrowserActions.clickOnElement(lnkTnC,driver, "Clicked terms and condition link");
	}


	/**
	 * to verify Tnc page
	 * @return
	 * @throws Exception
	 */
	public boolean verifyTnCPage() throws Exception{
		return BrowserActions.getText(driver,txtTnCHeader, "Getting Text from span").contains("Terms and Conditions");
	}


	/**
	 * to click on Edit Link and returning Review Page
	 * @return
	 * @throws Exception
	 */
	public ReviewPage clickOnEditLink() throws Exception{
		BrowserActions.clickOnElement(lnkEdit, driver, "Clicked on edit Link under review bar");
		return new ReviewPage(driver).get();
	}


	/**
	 * to remove saved credit card details
	 * @throws Exception
	 */
	public void cancelCreditCardDetails() throws Exception{

		while(tabSavedCC.isDisplayed()){
			Utils.waitForElement(driver, iconRemove);
			BrowserActions.scrollToView(iconRemove, driver);
			BrowserActions.clickOnElement(iconRemove, driver, "Click on remove icon");

		}

	}


	public void selectNewCreditCard() throws Exception{
		BrowserActions.clickOnElement(lblNewCC, driver, "Selected the Use New CreditCard option.");

	}



	/**
	 * Filling Credit Card Details in the EMI option
	 * 
	 * @return
	 * @throws Exception
	 */
	public void enterCreditCardDetailsInRewards(String cardNumber) throws Exception {

		String randomName = RandomStringUtils.randomAlphabetic(7).toLowerCase();
		String randomCvv = RandomStringUtils.randomNumeric(3);
		String randomRewardPoint = RandomStringUtils.randomNumeric(2);

		BrowserActions.typeOnTextField(txtcreditCardNumberInRewards,cardNumber, driver, "Credit card Number in EMI");
		BrowserActions.typeOnTextField(txtcreditCardHolderNameInRewards, randomName, driver, "Credit card holder Name in EMI");


		BrowserActions.clickOnElement(creditCardMonthInRewards, driver, "Clicked on Month dropdown.");
		if (drplstMonthRewards.size() != 0) {
			int rand = Utils.getRandom(1, drplstMonthRewards.size());
			BrowserActions.clickOnElement(drplstMonthRewards.get(rand), driver, "Month Selected from dropdown");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);

		BrowserActions.clickOnElement(creditCardYearInRewards, driver, "Clicked on Year dropdown.");
		if (drplstYearRewards.size() != 0) {
			int rand = Utils.getRandom(1, drplstYearRewards.size());
			BrowserActions.clickOnElement(drplstYearRewards.get(rand), driver, "Year Selected from dropdown");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);

		BrowserActions.typeOnTextField(txtcreditCardCvvInRewards, randomCvv, driver, "Credit card Cvv");
		BrowserActions.typeOnTextField(txtRewardPointInRewards, randomRewardPoint, driver, "Enter random Reward point");


	}

	/**
	 * getting failed message from failed transaction after applying DebitCard
	 * @return
	 * @throws Exception
	 */
	public String getTextFromFailedDebitCardTrans() throws Exception{
		String msgFailed = BrowserActions.getText(driver, txtFailedDBTrans, "Getting txt of the failed debit card transaction.");
		return msgFailed;

	}



	/**
	 * getting the amount we need to pay step by step and then summing them
	 * @return
	 * @throws Exception
	 */
	public int calculatingAmountToPay() throws Exception {

		int chrgedAmount=0;

		for (int i = 0; i < lstPayAmount.size(); i++) {
			String amount = BrowserActions.getText(driver,lstPayAmount.get(i), "lstPayAmount").trim().replace(",","").replace("Rs.", "");
			int amount1 = Integer.parseInt(amount);
			chrgedAmount = amount1+chrgedAmount;
		}
		return chrgedAmount;

	}
	
	
	/**
	 * getting the text from the Total amount from the fare detail panel
	 * @return
	 * @throws Exception
	 */
	public String gettingTotalPayAmount()throws Exception {
		String amount =BrowserActions.getText(driver,totalPayAmount, "Getting txt of total payment amount.").trim().replace(",","");
		return amount;
	}


	public void navigateBackFromMobileWallet(String walletName,String browser) throws Exception{
		switch (walletName){
		case "mobikwik":
			driver.navigate().back();
			break;

		case "oxigen":
			if(browser.equalsIgnoreCase("chrome_windows")){
			BrowserActions.javascriptClick(btnBckToYatraOxygen, driver, "Clicked on 'Back to Yatra' button.");
			BrowserActions.javaScriptAlertPopUpHandler(driver, "ok");
			}
			if(browser.equalsIgnoreCase("iexplorer_windows")){
				JavascriptExecutor js = (JavascriptExecutor) driver; 
				js.executeScript("window.history.go(-2)");
				driver.navigate().refresh();
				driver.switchTo().alert().accept();
			}
			
			break;

		case "payu":
			BrowserActions.javascriptClick(btnBckToYatraPAyU, driver, "Clicked on 'Back to Yatra' button.");
			break;

		case "sbibuddy":
			BrowserActions.javascriptClick(btnBckToYatraSBIBuddy, driver, "Clicked on 'Back to Yatra' button.");
			break;

		case "reliancejio":
			BrowserActions.javascriptClick(btnCancelJio, driver, "Clicked on 'cancel' button.");
			BrowserActions.javascriptClick(btnOkJio, driver, "Clicked on 'ok' button in popup.");
			break;

		case "freecharge":
			clickOnBackToYatraLinkFreechrge();
			break;

		case "olamoney":
			BrowserActions.javascriptClick(btnOlaMoneyWallet, driver, "Clicked on 'Back to Yatra' button");
			break;

		case "payzapp":
			BrowserActions.javascriptClick(btnPayZ, driver, "Clicked on 'Back to Yatra' button");
			BrowserActions.javaScriptAlertPopUpHandler(driver, "ok");
			break;

		case "voda-mpesa":
			driver.navigate().back();
			break;

		case "idea-money":
			BrowserActions.javascriptClick(btnCancelIdea, driver, "Clicked on 'Back to Yatra' button");
			break;

		}
	}
	
	@FindBy(xpath= "//Button[@ng-disabled='isContinueBtnDisabled' and contains(text(),'Continue')]")
	private WebElement btnContinueReviewPage;
	

	public boolean verifyPage() throws Exception{
		boolean flag = false;
		if(btnContinueReviewPage.isDisplayed()!=flag){
			return !flag;

		}
		else
			return flag;	 
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

}
