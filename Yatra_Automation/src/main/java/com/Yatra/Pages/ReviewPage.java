package com.Yatra.Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class ReviewPage extends LoadableComponent<ReviewPage> {

	private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	public static String sPricingURL;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();


	/**********************************************************************************************
	 ********************************* WebElements of Yatra ReviewPage ***********************************
	 **********************************************************************************************/

	@FindBy(css = "button[class='button grey-btn rounded sleek-btn ng-binding']")
	private WebElement btnChangeFlight;

	@FindBy(css = "[id='checkoutBase']>div:nth-child(3)>main>div>aside>div[ng-controller='productFareDetailsController']")
	private WebElement moduleFareDetails;

	@FindBy(xpath = "//i[@class = 'arrow-down']")
	private WebElement drpPromoCode;

	@FindBy(xpath = "//ul[@class = 'promo-options']/li/label/span/span[@class='promo-key ng-binding']")
	private WebElement fldContentpromo;

	@FindBy(xpath = "//ul[@class = 'promo-options']/li[@class='ng-scope']")
	private WebElement lnkPromoCoupon;

	// @FindBy(xpath =
	// ".//*[@id='checkoutBase']/div[3]/main/div/div/form/div[3]/button")
	@FindBy(xpath = "//Button[@ng-disabled='isContinueBtnDisabled' and contains(text(),'Continue')]")
	private WebElement btnContinueReviewPage;

	@FindBy(xpath = ".//*[@id='travellerf0']")
	private WebElement userFirstName;

	@FindBy(xpath = ".//*[@id='travellerl0']")
	private WebElement userSecondName;

	@FindBy(css = ".col-md-1.col-xs-3.min-width70>span[class='ui-select']>select>option:nth-child(2)")
	private WebElement userTitle;

	@FindBy(xpath = ".//*[@id='paxNum0']/div[2]/span[1]")
	private WebElement drpuserTitle;

	@FindBy(xpath = ".//*[@id='traveller-dom']/div[1]/div[1]/div/article[2]/div[2]/input")
	private WebElement userEmail;

	@FindBy(css = "span[class='pull-left cursor-pointer ng-binding under-link']>a")
	private WebElement lnkFeeSurchrge;

	@FindBy(css = "span[class='pull-left w85perc ng-binding']")
	private WebElement msgPromoApplied;

	@FindBy(css = "span[ng-show='selectPromo && promoListEnable']")
	private WebElement lnkHavePromoCode;

	@FindBy(xpath = "//input[@id='promoListInput']")
	private WebElement txtPromoCode;

	@FindBy(css = "button[ng-show='!selectPromo']")
	private WebElement btnApply;

	@FindBy(css = "[ng-click='showFareRulesPopup()']")
	private WebElement lnkFareRules;

	@FindBy(css = "div[class='fareBox']>ul[class='list review-title']")
	private WebElement moduleFeeSurchrge;

	@FindBy(xpath = "//div[@ng-show='showLoginRegister']/form/div[@class='row']/button")
	private WebElement btnBookAsGuest;

	@FindBy(xpath = "//div[@ng-show='showLoginRegister']/form/div[@class='row']/input[@name = 'email']")
	private WebElement txtGuestEmail;

	@FindBy(xpath = "//div[@id='regUserDiv']/input[@type='password']")
	private WebElement txtGuestPassword;

	@FindBy(css = ".update-fare.pt10.ico-right")
	private WebElement PricePopUp;

	@FindBy(css = ".overlay-content>p[class='text-center']>button[class='button primary rounded']")
	private WebElement ContinueInPopUp;

	@FindBy(css = "[ng-show='priceChangeDiv']>div>div[class='overlay-content ']")
	private WebElement popupFareChange;

	@FindBy(css = "[ng-show='priceChangeDiv']>div>div[class='overlay-content ']>div[class='row mt10 btn-box text-center']>button")
	private WebElement ContinueInpopUpFareSlashed;

	@FindBy(css = "[ng-show='priceChangeDiv']>div>div[class='overlay-content ']>div[class='row mt10 btn-box']>button[ng-click='continueSameFlight()']")
	private WebElement ContinueInFareChangeAlertPopUp;

	@FindBy(css = " div[class='center-block text-center mt-1 mb-1 sticky-sm-bottom hide-under-overlay']>button[ng-disabled='isContinueBtnDisabled']")
	private WebElement ContinueInTravellerPage;

	@FindBy(css = "div[class='align-width']>input[name='mobile']")
	private WebElement txtGuestMobile;

	@FindBy(xpath = "//span[@class='account-msg ui-checkbox']")
	private WebElement chkExistingUser;

	@FindBy(css = "[id='checkoutBase']>div:not([class])>main>div>aside>div[class='box ng-scope']>div[class='box-content hide-under-overlay']>div>ul[class='list list-border']")
	private WebElement contentFareDetails;

	@FindBy(xpath = "//*[@ng-repeat='traveller in travellerDetails']")
	private List<WebElement> modTravellerDetails;

	@FindBy(css = "div[class='fareruleContainer overlay-holder']>div>div[class='overlay-content']")
	private WebElement moduleFareRules;

	@FindBy(css = "div[id='review-dom']>div>h3[class='box-title fs-md normal blank-label ng-binding']")
	private WebElement formReviewHeading;

	@FindBy(css = "div[class='overlay-content ']")
	private WebElement popUpFareAlert;

	@FindBy(css = "div[ng-controller='productFareDetailsController']>div[class='box-content hide-under-overlay']>div[ng-show='showFareDetails']>ul[class='list list-border']>li[class='ng-scope']>span[class='pull-right tr alignment']>span")
	private List<WebElement> lstPayAmount;

	@FindBy(css = "div[class='text-right alignment ng-scope']>span[class='block fs-xlg gray-dark u-pay ng-binding']:not([class='rs'])")
	private WebElement totalPayAmount;

	@FindBy(css = "input[id='promoListInput']")
	private WebElement fldContentPromoCode;

	@FindBy(css = "span[class*='pull-left w85perc ng-binding']")
	private WebElement PromoCodeErrorMessage;

	@FindBy(css = "button[class='button sleek-btn promo-btn ng-binding']")
	private WebElement btnApplyPromoCode;

	// @Harveer
	@FindBy(xpath = "(//div[@class='overlay modal-new'])[1]")
	private WebElement priceChangeDiv;

	@FindBy(css = "div[class='box hide-under-overlay ng-scope']>h3[class='box-title fs-md normal ng-binding']")
	private WebElement txtReviewYourBooking;

	@FindBy(css = "h3>span[class='pull-left w85perc ng-binding']")
	private WebElement txtPromoDiscountApplied;

	@FindBy(css = "div[class='text-right alignment ng-scope']>span[class='block fs-xlg gray-dark u-pay ng-binding']")
	private WebElement totalAmountInreviewPage;

	@FindBy(css = "ul[class='list list-border']>li:nth-child(5)>span[class='pull-right tr alignment']>a[class='remove-btn']")
	private WebElement btnRemove;

	@FindBy(css = "a[ng-click='cancelPromotionalEcash()']")
	private WebElement btnRemoveEcash;
	
	@FindBy(css = "div[class='col-sm-6 promo-select-ui width-increased']>a")
	private WebElement btnClosePromoBox;

	@FindBy(css = "ul[class='list list-border ng-scope']>li[ng-show='yatraExtra.eCash && yatraExtra.eCash.amount']")
	private WebElement txtTotalEcashEarned;

	@FindBy(css = "div[ng-show='showFareDetails']>ul[class='list list-border']>li:nth-child(4)")
	private WebElement txtFeeAndSurcharge;

	@FindBy(css = "div[ng-show='showFareDetails'] div[class='text-right alignment ng-scope']>span:nth-child(2)")
	private WebElement txtshowFlightFareDetails;
	
	@FindBy(css = "button.primary.rounded.pull-right")
	private WebElement btnFareChangeContinue; // remove later
	
	@FindBy(css = "div[ng-show='priceChangeDiv']")
	private WebElement altFareChange;
	
	@FindBy(css = "div[class='change-status bull-green']")
	private WebElement txtFareSlashed;
	
	@FindBy(css = "div[class='change-status bull-red']")
	private WebElement txtFareOops;
	
	@FindBy(css = "div[class='row mt10 btn-box text-center']>button")
	private WebElement btnFareSlashedContune;
	
	@FindBy(css = "button.primary.rounded.pull-right")
	private WebElement btnFareOopsContune;
	
	@FindBy(css = "table[class='int-fare-rules ng-scope']>tbody:nth-child(2)")
	private WebElement txtFareRules;

	@FindBy(css = "a[analytics='Flights - Review Itinerary - INT|Current Selection|View Fare Rules']")
	private WebElement BtnViewRules;
	
	@FindBy(css = "div[ng-show='priceChangeDiv'] div[class='mb10 ']")
	private WebElement txtWeAreSorryPopup;
	
	@FindBy(css = "div[class='row mt10 btn-box'] button[class='button rounded primary']")
	private WebElement txtSelectAnotherFlight;
	

	/**********************************
	 * WebElements of Yatra ReviewPage - Ends ****************************
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

	public ReviewPage(WebDriver driver, String url) {
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
	public ReviewPage(WebDriver driver) {
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

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, btnChangeFlight))) {
			Log.fail("ReviewPage didn't open up", driver);
		}
		timer.end();
		Log.message("Total time taken by #"+this.getClass().getTypeName()+" to load is:- "+timer.duration()+" "+TimeUnit.SECONDS, driver, true);
		//set value for pricing URL (if it require in case of test case fail, to send a mail with this URL)
		sPricingURL=driver.getCurrentUrl().trim();
		//new EmailSender(driver.getCurrentUrl().trim());
	}

	
	@Override
	protected void load() {
		timer.start();
    	isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}// load

	/**
	 * To click Book as Guest button on Review Page
	 * 
	 * @throws Exception
	 */

	public void clickOnBookAsGuestBtn() throws Exception {
		BrowserActions.scrollToView(btnBookAsGuest, driver);
		BrowserActions.clickOnElement(btnBookAsGuest, driver, "To click on Book as Guest button.");
	}

	/**
	 * Enter email id
	 * 
	 * @param emailid
	 *            as string
	 * @throws Exception
	 */
	public void enterEmailID(String emailid) throws Exception {
		Utils.waitForElement(driver, txtGuestEmail);
		BrowserActions.typeOnTextField(txtGuestEmail, emailid, driver, "Email id");
		Log.event("Entered the Email Id: " + emailid);
	}

	/**
	 * Enter mobile
	 * 
	 * @param mobile
	 *            as string
	 * @throws Exception
	 */
	public void enterMobile(String mobile) throws Exception {
		Utils.waitForElement(driver, txtGuestMobile);
		BrowserActions.typeOnTextField(txtGuestMobile, mobile, driver, "Mobile");
		Log.event("Entered the Mobile no: " + mobile);
	}

	/**
	 * Enter mobile
	 * 
	 * @param mobile
	 *            as string
	 * @throws Exception
	 */
	public void enterPassword(String password) throws Exception {
		Utils.waitForElement(driver, txtGuestPassword);
		BrowserActions.typeOnTextField(txtGuestPassword, password, driver, "Password");
		Log.event("Entered the Password : " + password);
	}

	public TravellerPage loginYatraGuestAccount(String emailId, String mobile) throws Exception {
		enterEmailID(emailId); // enter email address
		enterMobile(mobile); // enter mobile no
		clickOnBookAsGuestBtn(); // click Login button
		return new TravellerPage(driver).get();

	}

	public TravellerPage loginYatraGuestAccountExisting(String emailId, String password) throws Exception {
		enterEmailID(emailId); // enter email address
		enterPassword(password); // enter password
		clickOnBookAsGuestBtn(); // click Login button
		return new TravellerPage(driver).get();
	}

	/**
	 * verifying ExistingUse checkbox is checked on unchecked
	 * 
	 * @return
	 * @throws Exception
	 */

	public boolean verifyExistingUser() throws Exception {
		boolean status = BrowserActions.isRadioOrCheckBoxSelected(chkExistingUser);
		return status;
	}

	/**
	 * clicking on ExistingUser checkbox
	 * 
	 * @throws Exception
	 */
	public void clickOnExistingUser() throws Exception {
		BrowserActions.clickOnElement(chkExistingUser, driver, "Clicked on Existing User CheckBox.");
	}

	/**
	 * To click promo drop down on Review page
	 * 
	 * @throws Exception
	 */

	public void clickOnPromoDrpDwn() throws Exception {
		BrowserActions.scrollToView(drpPromoCode, driver);
		BrowserActions.clickOnElement(drpPromoCode, driver, "To click on Promo code Dropdown.");
	}

	public void selectPromoByIndex(int Index) throws Exception {
		Thread.sleep(1000);;
		driver.findElement(By.cssSelector(
				"div[class='col-sm-6 promo-select-ui width-increased']>ul>li:nth-child("+Index+")>label")).click();
		
	}

	/**
	 * To click promo drop down on Review page fetch coupon text from the
	 * dropdown submit the received coupon and click on Apply button on Review
	 * page
	 * 
	 * @throws Exception
	 */

	public boolean getPromoCode() throws Exception {
		BrowserActions.scrollToView(drpPromoCode, driver);
		BrowserActions.clickOnElement(drpPromoCode, driver, "To click on Promo code Dropdown.");
		String promoCode = BrowserActions.getText(driver, fldContentpromo, "Promo Code Content");
		BrowserActions.clickOnElement(lnkHavePromoCode, driver, "To click on Hava a Promo code link.");
		BrowserActions.typeOnTextField(txtPromoCode, promoCode, driver, "Entered Applicable Promo Code");
		BrowserActions.clickOnElement(btnApply, driver, "To click on Apply button.");
		String promoAppliedMessage = BrowserActions.getText(driver, msgPromoApplied,
				"Successful Promo Code applied message");
		promoAppliedMessage.startsWith("Congrats");
		return true;
	}

	/**
	 * To click promo drop down on Review page click on a coupon from the
	 * dropdown on Review page
	 * 
	 * @throws Exception
	 */

	public boolean clickOnPromoCoupon() throws Exception {
		BrowserActions.scrollToView(drpPromoCode, driver);
		BrowserActions.clickOnElement(drpPromoCode, driver, "To click on Promo coupon link.");
		BrowserActions.clickOnElement(lnkPromoCoupon, driver, "To click on Promo Coupon");
		String promoAppliedMessage = BrowserActions.getText(driver, msgPromoApplied,
				"Successful Promo Code applied message");
		promoAppliedMessage.startsWith("Congrats");
		return true;
	}

	/**
	 * To click Have a promo code link on Review page
	 * 
	 * @throws Exception
	 */

	public void clickOnHavePromoCode() throws Exception {
		BrowserActions.scrollToView(lnkHavePromoCode, driver);
		BrowserActions.clickOnElement(lnkHavePromoCode, driver, "To click on Hava a Promo code link.");

	}

	public TravellerPage clickOnContinue() throws Exception {		
		BrowserActions.nap(6);
		Utils.waitForElement(driver, btnContinueReviewPage);
		BrowserActions.scrollToView(btnContinueReviewPage, driver);
		BrowserActions.javascriptClick(btnContinueReviewPage, driver, "Continue Button");
		Utils.waitForPageLoad(driver);
		return new TravellerPage(driver).get();
	}

	/**
	 * to click on View fare rules link in fare details module
	 * 
	 * @throws Exception
	 */
	public void clickOnFareRulesLink() throws Exception {
		BrowserActions.javascriptClick(lnkFareRules, driver, "Clicked on View Fare Rules link.");
	}

	/**
	 * to click on Fee & surcharge link in fare detail module
	 * 
	 * @throws Exception
	 */

	public void clickOnFeeSurchrgeLink() throws Exception {
		BrowserActions.javascriptClick(lnkFeeSurchrge, driver, "Clicked on Fees & Surcharge link.");
	}

	@FindBy(css = "button[ng-click='continueSameFlight();']")
	WebElement ContinueInFarePopUp;

	/**
	 * Clicking Continue In Price Increase Pop Up
	 * 
	 * @return
	 * @throws Exception
	 */
	//TODO : Need to look on - Narayana
	/*public void popUpAppear() throws Exception {		
		if (PricePopUp.isDisplayed()) {			
			if(BrowserActions.isElementVisible(driver, btnFareChangeContinue)){
				BrowserActions.clickOnElement(btnFareChangeContinue, driver, "Clicked on continue in Popup");
			}else
			BrowserActions.clickOnElement(ContinueInFarePopUp, driver, "Clicked on continue in Popup");
		}
		else if (popupFareChange.isDisplayed())
			if (ContinueInFareChangeAlertPopUp.isDisplayed()) {
				BrowserActions.clickOnElement(ContinueInFareChangeAlertPopUp, driver,
						"Clicked on continue in Fare Change Alert Popup");
			} else if (ContinueInpopUpFareSlashed.isDisplayed()) {
				BrowserActions.clickOnElement(ContinueInpopUpFareSlashed, driver,
						"Clicked on continue in fare slashed popup");
			} else
				Log.event("No PopUp appear.");
	}*/
	
	// TODO : Need to look on - @Narayana
		public void popUpAppear() throws Exception {
			if (PricePopUp.isDisplayed()) {
				if (BrowserActions.isElementVisible(driver, btnFareChangeContinue)) {
					BrowserActions.clickOnElement(btnFareChangeContinue, driver, "Clicked on continue in Popup");
				} else
					BrowserActions.clickOnElement(ContinueInFarePopUp, driver, "Clicked on continue in Popup");
			} else if (popupFareChange.isDisplayed()) {
				if (BrowserActions.isElementVisible(driver, ContinueInFareChangeAlertPopUp)) {
					BrowserActions.clickOnElement(ContinueInFareChangeAlertPopUp, driver,"Clicked on continue in Fare Change Alert Popup");
				} else if (BrowserActions.isElementVisible(driver, ContinueInpopUpFareSlashed)) {
					BrowserActions.clickOnElement(ContinueInpopUpFareSlashed, driver,"Clicked on continue in fare slashed popup");
				} else
					Log.event("No PopUp appear.");
			}
		}

	/**
	 * Getting the text from the fare details panel
	 * 
	 * @return
	 * @throws Exception
	 */

	public String getTextFromFareDetails() throws Exception {
		String txtDetails = BrowserActions.getText(driver, contentFareDetails, "Getting text from the fare details.");
		return txtDetails;

	}

	/**
	 * getting the amount we need to pay step by step and then summing them
	 * 
	 * @return
	 * @throws Exception
	 */
	public int calculatingAmountToPay() throws Exception {

		int chrgedAmount = 0;

		for (int i = 0; i < lstPayAmount.size(); i++) {
			String amount = BrowserActions.getText(driver, lstPayAmount.get(i), "lstPayAmount").trim().replace(",", "");
			int amount1 = Integer.parseInt(amount);
			chrgedAmount = amount1 + chrgedAmount;
		}
		return chrgedAmount;

	}

	/**
	 * getting the text from the Total amount from the fare detail panel
	 * 
	 * @return
	 * @throws Exception
	 */
	public String gettingTotalPayAmount() throws Exception {
		String amount = BrowserActions.getText(driver, totalPayAmount, "Getting txt of total payment amount").trim()
				.replace(",", "");
		return amount;
	}

	public String getTextOfReviewHeading() throws Exception {
		String formReview = BrowserActions.getText(driver,
				driver.findElement(By.cssSelector(
						"div[id='review-dom']>div>h3[class='box-title fs-md normal blank-label ng-binding']")),
				"Getting text of Review Form Heading.");
		return formReview;
	}

	/**
	 * Enter Promo Code in review page and Click Apply
	 * 
	 * @param String
	 * @throws Exception
	 */
	public void enterPromo(String promo) throws Exception {
		BrowserActions.typeOnTextField(fldContentPromoCode, promo, driver, "Enter Promo");
		BrowserActions.clickOnElement(btnApplyPromoCode, driver, "Apply Button");
	}

	/**
	 * Getting the text of Promo Code Error Message
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String getTextFromPromoMessage() throws Exception {
		Utils.waitForPageLoad(driver);
		String ErrorMessage = BrowserActions.getText(driver, PromoCodeErrorMessage,
				"Getting Error text from the Promo Code");
		return ErrorMessage;

	}

	/**
	 * Description: to handle price change pop up, if pop appear <br>
	 * it will navigate to back to SRP Paage
	 */

	public void handlePriceChangeWindow() {
		// @harveer- i have hard coded these value because this pop up will be
		// available in some scenario
		if (driver.findElements(By.xpath("(//div[@class='overlay modal-new'])[1]")).size() > 0) {

			driver.findElement(By.cssSelector("button[class='button rounded primary']")).click();
		}
	}

	/**
	 * Getting the text of text Review Your Booking
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getTextReviewBooking() throws Exception {
		Utils.waitForPageLoad(driver);
		String Message = BrowserActions.getText(driver, txtReviewYourBooking, "Getting Error text Review Your Booking");
		return Message;
	}

	/**
	 * Getting the text of Promo Applied
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getTextPromotinalMessage() throws Exception {
		Utils.waitForPageLoad(driver);
		String Message = BrowserActions.getText(driver, txtPromoDiscountApplied, "Getting Text of Promo Applied");
		return Message;
	}

	/**
	 * Getting Text of Total Amount
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getTextTotalAmount() throws Exception {
		Utils.waitForPageLoad(driver);
		String Amount = BrowserActions.getText(driver, totalAmountInreviewPage, "Getting Text of Total Amount");
		return Amount;
	}

	/**
	 * To Click On Remove Button on Fare Details Section
	 * 
	 * @return String
	 * @throws Exception
	 */
	public void ClickOnRemoveButton() throws Exception {
		Utils.waitForPageLoad(driver);
		if(btnRemove.isDisplayed()){
			btnRemove.click();
		}
		else if(btnRemoveEcash.isDisplayed())
		{
		btnRemoveEcash.click();
		}
	}
	/**
	 * To Click On Close Button on Promo Box
	 * 
	 * @return String
	 * @throws Exception
	 */
	public void ClickOnCloseButtonInPromoBox() throws Exception {
		Utils.waitForPageLoad(driver);
		btnClosePromoBox.click();
	}

	/**
	 * Getting Text Ecash Earned
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getTextEcashFareDetails() throws Exception {
		Utils.waitForPageLoad(driver);
		String Ecash = BrowserActions.getText(driver, txtTotalEcashEarned, "Getting Text of Ecash Added In Account");
		return Ecash;
	}

	/**
	 * Getting Text Fee and Surcharge in Fare Details
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getTextFeeAndSurcharge() throws Exception {
		Utils.waitForPageLoad(driver);
		String Ecash = BrowserActions.getText(driver, txtFeeAndSurcharge,
				"Getting Text of Fee and Surcharge in Fare Details");
		return Ecash;
	}

	/**
	 * Getting the text from Flight Price in Review page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFlightPrice() throws Exception {
		Utils.waitForElement(driver, txtshowFlightFareDetails);
		String flightPriceGetTxt = BrowserActions.getText(driver, txtshowFlightFareDetails,
				"Flight Price should be displayed");
		return flightPriceGetTxt;
	}
	
	/**
	 * Getting the text from Flight Name On Review Page
	 * 
	 * @return
	 * @throws Exception
	 */

	public ArrayList<String> getTextAirlinename() throws Exception{
		List<WebElement> name = driver.findElements(By.cssSelector("article[class='row review-article ng-scope']>div[class='text-sm-center col-sm-2 text-xs-left sm-gutter-bottom']>p>span[class='ib ng-binding']"));
		ArrayList<String> airLineList = new ArrayList<String>();
		for (int i =0;i<name.size();i++){
		String airlineName = 	BrowserActions.getText(driver, name.get(i), "Getting name of Airlines In Pop Up");
		airLineList.add(airlineName);
		}
		return airLineList;	
	}
	/**
	 * To verify Fare Slashed & Fare Opps Alert Pop up is displayed
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean fareChangeAlertPopUpNotAppear() throws Exception {
		boolean status = false;
		if (!altFareChange.isDisplayed()) {
			status = true;
			Log.event("Flight fare change alert poupup is not displayed ");
		} else if (altFareChange.isDisplayed()) {
			Log.event("Flight fare change alert poupup is displayed ");
			status = false;
		}
		return status;
	}
	
	/**
	 * Clicking Continue in Fare Slashed & Fare Opps Alert Popup 
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean fareChangeAlertPopUpAppear() throws Exception {
		boolean status = false;		
		if (altFareChange.isDisplayed()){
			if (BrowserActions.isElementVisible(driver, txtFareSlashed)) {
				BrowserActions.clickOnElement(btnFareSlashedContune, driver, "Clicked on continue in Fare Slashed Alert Popup");
				status = true;
			} else if (BrowserActions.isElementVisible(driver, txtFareOops)) {
				BrowserActions.clickOnElement(btnFareOopsContune, driver,"Clicked on continue in Fare Oops Alert Popup");
				status = true;
			} else if (BrowserActions.isElementVisible(driver, altFareChange))
				BrowserActions.clickOnElement(ContinueInFarePopUp, driver, "Clicked on continue in Popup");
			status = true;
			Log.event("Flight fare change alert poupup is displayed ");
		}else{
			Log.event("Flight fare change alert poupup is not displayed ");
			status = false;
		}
    return status;
	}
	/**
	 * To click on Change Flight button on Review page
	 * 
	 * @throws Exception
	 */

	public void clickOnChangeFlight() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.scrollToView(btnChangeFlight, driver);
		BrowserActions.clickOnElement(btnChangeFlight, driver, "To click on Change Flight button on Review page");
	}
	
	/**
	 * To click on View Rules on Review page and also getting the text
	 * @return String
	 * @throws Exception
	 */

	
	public String getTextFareRules() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnElement(BtnViewRules, driver, "To click on View Rules on Review page");
		String abc = txtFareRules.getText();
		return abc;
	}
	/**
	 * to get pricing URL at run time
	 * @return: it will return pricing url 
	 */
	public static String getPricingURL(){
		return sPricingURL;
	}

	/**
	 * Clicking Continue in Fare Slashed & Fare Opps Alert Popup 
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public boolean fareChangeAlertPopUpAppear_InProgress() throws Exception {
		boolean status = false, selectflightagain = (Boolean) null;		
		if (altFareChange.isDisplayed()){
			if (BrowserActions.isElementVisible(driver, txtFareSlashed)) {
				BrowserActions.clickOnElement(btnFareSlashedContune, driver, "Clicked on continue in Fare Slashed Alert Popup");
				status = true;
			} else if (BrowserActions.isElementVisible(driver, txtFareOops)) {
				BrowserActions.clickOnElement(btnFareOopsContune, driver,"Clicked on continue in Fare Oops Alert Popup");
				status = true;
			} else if (BrowserActions.isElementVisible(driver, altFareChange)){
				BrowserActions.clickOnElement(ContinueInFarePopUp, driver, "Clicked on continue in Popup");
			status = true;
			}else if (BrowserActions.isElementVisible(driver, txtWeAreSorryPopup)) {
				BrowserActions.clickOnElement(txtSelectAnotherFlight, driver,"Clicked on Select Another Flight in Flight not confirmed alert popup");
				status = selectflightagain;
			}Log.event("Flight fare change alert poupup is displayed ");
		}else{
			Log.event("Flight fare change alert poupup is not displayed ");
			status = false;
		}
    return status;
	}
	
} // ReviewPage


