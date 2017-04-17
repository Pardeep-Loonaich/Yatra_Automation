package com.Yatra.Pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.select.Evaluator.ContainsOwnText;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class ReviewPage extends LoadableComponent<ReviewPage> {

	private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra ReviewPage ***********************************
	 **********************************************************************************************/


	@FindBy(css = "button[class='button grey-btn rounded sleek-btn ng-binding']")
	private WebElement btnChngeFlight;

	@FindBy(css = "[id='checkoutBase']>div:nth-child(3)>main>div>aside>div[ng-controller='productFareDetailsController']")
	private WebElement moduleFareDetails;

	@FindBy(xpath = "//i[@class = 'arrow-down']")
	private WebElement drpPromoCode;

	@FindBy(xpath = "//ul[@class = 'promo-options']/li/label/span/span[@class='promo-key ng-binding']")
	private WebElement fldContentpromo;

	@FindBy(xpath = "//ul[@class = 'promo-options']/li[@class='ng-scope']")
	private WebElement lnkPromoCoupon;

	//@FindBy(xpath = ".//*[@id='checkoutBase']/div[3]/main/div/div/form/div[3]/button")
	@FindBy(xpath= "//Button[@ng-disabled='isContinueBtnDisabled' and contains(text(),'Continue')]")
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

	@FindBy(css="[ng-show='priceChangeDiv']>div>div[class='overlay-content ']")
	private WebElement popupFareChange;

	@FindBy(css="[ng-show='priceChangeDiv']>div>div[class='overlay-content ']>div[class='row mt10 btn-box text-center']>button")
	private WebElement ContinueInpopUpFareSlashed;

	@FindBy(css="[ng-show='priceChangeDiv']>div>div[class='overlay-content ']>div[class='row mt10 btn-box']>button[ng-click='continueSameFlight()']")
	private WebElement ContinueInFareChangeAlertPopUp;

	@FindBy(css = " div[class='center-block text-center mt-1 mb-1 sticky-sm-bottom hide-under-overlay']>button[ng-disabled='isContinueBtnDisabled']")
	private WebElement ContinueInTravellerPage;

	@FindBy(css = "div[class='align-width']>input[name='mobile']")
	private WebElement txtGuestMobile;

	@FindBy(xpath = "//span[@class='account-msg ui-checkbox']")
	private WebElement chkExistingUser;

	@FindBy(css = "[id='checkoutBase']>div:not([class])>main>div>aside>div[class='box ng-scope']>div[class='box-content hide-under-overlay']>div>ul[class='list list-border']")
	private WebElement contentFareDetails;

	@FindBy(xpath= "//*[@ng-repeat='traveller in travellerDetails']")
	private List<WebElement> modTravellerDetails ;


	@FindBy(css = "div[class='fareruleContainer overlay-holder']>div>div[class='overlay-content']")
	private WebElement moduleFareRules;

	@FindBy(css = "div[id='review-dom']>div>h3[class='box-title fs-md normal blank-label ng-binding']")
	private WebElement formReviewHeading;
	
	@FindBy(css="div[ng-controller='productFareDetailsController']>div[class='box-content hide-under-overlay']>div[ng-show='showFareDetails']>ul[class='list list-border']>li[class='ng-scope']>span[class='pull-right tr alignment']>span")
	private List <WebElement> lstPayAmount;

	@FindBy(css="div[class='text-right alignment ng-scope']>span[class='block fs-xlg gray-dark u-pay ng-binding']:not([class='rs'])")
	private WebElement totalPayAmount;


	
	/**********************************************************************************************
	 ********************************* WebElements of Yatra ReviewPage - Ends ****************************
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
		if (!isPageLoaded) {
			Assert.fail();
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, btnChngeFlight))) {
			Log.fail("ReviewPage didn't open up", driver);
		}
		// elementLayer = new ElementLayer(driver);
	}

	@Override
	protected void load() {
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


	@FindBy(css="button[ng-click='continueSameFlight();']")
	WebElement ContinueInFarePopUp;
	/**
	 * Clicking Continue In Price Increase Pop Up
	 * 
	 * @return
	 * @throws Exception
	 */

	public void popUpAppear() throws Exception {
		if (PricePopUp.isDisplayed()) {
			BrowserActions.clickOnElement(ContinueInFarePopUp, driver, "Clicked on continue in Popup");
		}

		else if (popupFareChange.isDisplayed()) 
			if(ContinueInFareChangeAlertPopUp.isDisplayed())
			{
				BrowserActions.clickOnElement(ContinueInFareChangeAlertPopUp, driver, "Clicked on continue in Fare Change Alert Popup");
			}
			else if(ContinueInpopUpFareSlashed.isDisplayed())
			{
				BrowserActions.clickOnElement(ContinueInpopUpFareSlashed, driver, "Clicked on continue in fare slashed popup");
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
	 * Getting the text of Review Form heading 
	 * 
	 * @return
	 * @throws Exception
	 */

	public String getTextOfReviewHeading() throws Exception {
		String formReview = BrowserActions.getText(driver, driver.findElement(By.cssSelector("div[id='review-dom']>div>h3[class='box-title fs-md normal blank-label ng-binding']")), "Getting text of Review Form Heading.");
		return formReview;

	}

	/**
	 * getting the amount we need to pay step by step and then summing them
	 * @return
	 * @throws Exception
	 */
	public int calculatingAmountToPay() throws Exception {

		int chrgedAmount=0;

		for (int i = 0; i < lstPayAmount.size(); i++) {
			String amount = BrowserActions.getText(driver,lstPayAmount.get(i), "lstPayAmount").trim().replace(",","");
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
		String amount =BrowserActions.getText(driver,totalPayAmount, "Getting txt of total payment amount").trim().replace(",","");
		return amount;
	}




} //ReviewPage
