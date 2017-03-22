package com.Yatra.Pages;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.select.Evaluator.ContainsOwnText;
import org.openqa.selenium.By;
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

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Search Page ***********************************
	 **********************************************************************************************/

	@FindBy(css = "button[class='button grey-btn rounded sleek-btn ng-binding']")
	public WebElement btnChngeFlight;

	@FindBy(css="div:nth-child(3)>main>div>aside[class='col-lg-3 col-md-3']>div>div[id='fareRulesPopup']>div")
	WebElement moduleFareRules;

	@FindBy(css = "[id='checkoutBase']>div:nth-child(3)>main>div>aside>div[ng-controller='productFareDetailsController']")

	WebElement moduleFareDetails;

	@FindBy(xpath = "//i[@class = 'arrow-down']")
	WebElement drpPromoCode;

	@FindBy(xpath ="//ul[@class = 'promo-options']/li/label/span/span[@class='promo-key ng-binding']")
	WebElement fldContentpromo;

	@FindBy(xpath = "//ul[@class = 'promo-options']/li[@class='ng-scope']")
	WebElement lnkPromoCoupon;

	@FindBy(xpath = ".//*[@id='checkoutBase']/div[3]/main/div/div/form/div[3]/button")
	WebElement btnContinueReviewPage;

	@FindBy(css = ".box-content.ssr-container.hide-under-overlay.ng-scope>div[class='button-group']>button:nth-child(1)")
	WebElement btnAddMeal;

	@FindBy(css = ".box-content.ssr-container.hide-under-overlay.ng-scope>div[class='button-group']>button:nth-child(2)")
	WebElement btnAddBaggage;


	//@FindBy(xpath = ".//*[@id='ssrContainer']/div[2]/div[3]/div[5]/div/div/ul/li[2]/span")
	@FindBy(css = "	div[id='traveller-dom']>div[id='ssrContainer']>div[ng-controller='productSSRController']>div:nth-child(3)>div[class='col-xs-8 col-md-4 ssr-trip ng-scope']>div>div>ul>li:nth-child(2)>span>select")
	WebElement drpSelectMeal;

	//@FindBy(xpath = ".//*[@id='ssrContainer']/div[2]/div[3]/div[5]/div/div/ul/li[2]/span/select/option[3]")
	@FindBy(css = "	div[id='traveller-dom']>div[id='ssrContainer']>div[ng-controller='productSSRController']>div:nth-child(3)>div[class='col-xs-8 col-md-4 ssr-trip ng-scope']>div>div>ul>li:nth-child(2)>span>select>option:nth-child(2)")
	WebElement selectMeal;

	@FindBy(xpath = ".//*[@id='ssrContainer']/div[2]/div[2]/div[5]/div/div/ul/li[2]/span/select")
	WebElement drpAddBaggage;

	@FindBy(xpath = ".//*[@id='ssrContainer']/div[2]/div[2]/div[5]/div/div/ul/li[2]/span/select/option[2]")
	WebElement selectBaggage;

	@FindBy(xpath = ".//*[@id='travellerf0']")
	WebElement userFirstName;

	@FindBy(xpath = ".//*[@id='travellerl0']")
	WebElement userSecondName;

	@FindBy(xpath = ".//*[@id='traveller-dom']/div[1]/div[1]/div/article[2]/div[2]/input")
	WebElement userEmail;

	@FindBy(css="span[class='pull-left cursor-pointer ng-binding under-link']>a")
	WebElement lnkFeeSurchrge;

	@FindBy(css ="span[class='pull-left w85perc ng-binding']")
	WebElement msgPromoApplied;

	@FindBy(css = "span[ng-show='selectPromo && promoListEnable']")
	WebElement lnkHavePromoCode;

	@FindBy(xpath="//input[@id='promoListInput']")
	WebElement txtPromoCode;

	@FindBy(css= "button[ng-show='!selectPromo']")
	WebElement btnApply;

	@FindBy(css="[ng-click='showFareRulesPopup()']")
	WebElement lnkFareRules;

	@FindBy(css="div[class='fareBox']>ul[class='list review-title']")
	WebElement moduleFeeSurchrge;

	@FindBy(xpath= "//div[@ng-show='showLoginRegister']/form/div[@class='row']/button")
	WebElement btnBookAsGuest;
	
	@FindBy(xpath= "//div[@ng-show='showLoginRegister']/form/div[@class='row']/input[@name = 'email']")
	WebElement txtGuestEmail;
	
	@FindBy(xpath ="//div[@id='regUserDiv']/input[@type='password']")
	WebElement txtGuestPassword;
	
	@FindBy(css = "div[class='align-width']>input[name='mobile']")
	WebElement txtGuestMobile;
	
	@FindBy(xpath ="//span[@class='account-msg ui-checkbox']")
	WebElement chkExistingUser;
	
	@FindBy(xpath=".//*[@id='traveller-dom']/div[3]/div/div/div[2]/p/label/span[1]/input")
	WebElement chkInsurance;

	@FindBy(xpath=".//*[@id='checkoutBase']/div[3]/main/div/aside/div[1]/div[1]/div/ul[1]")
	WebElement fldContentInsurance;

	@FindBy(xpath= "//*[@ng-repeat='traveller in travellerDetails']")
	List<WebElement> modTravellerDetails ;   

	@FindBy(css="[id='redeem-applied-id']>h4")
	WebElement msgEcashRedeemBalance;
	
	@FindBy(css="[id='redeem-applied-id']>h3")
	WebElement msgEcashRedeem;
	
	/**********************************************************************************************
	 ********************************* WebElements of Yatra Search Page - Ends ****************************
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
			Log.fail("Search Result page didn't open up", driver);
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
	 * @return
	 * @throws Exception
	 */
   
    public boolean verifyExistingUser() throws Exception{	
    	boolean status = BrowserActions.isRadioOrCheckBoxSelected(chkExistingUser);
    	return status;
    }
    
    /**
     * clicking on ExistingUser checkbox
     * @throws Exception
     */
    public void clickOnExistingUser() throws Exception{
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
	 * To click promo drop down on Review page
	 * 	 fetch coupon text from the dropdown 
	 *  	submit the received coupon and click on Apply button on Review page
	 * @throws Exception
	 */

	public boolean getPromoCode() throws Exception {
		BrowserActions.scrollToView(drpPromoCode, driver);
		BrowserActions.clickOnElement(drpPromoCode, driver, "To click on Promo code Dropdown.");
		String promoCode = BrowserActions.getText(driver, fldContentpromo, "Promo Code Content");
		BrowserActions.clickOnElement(lnkHavePromoCode, driver, "To click on Hava a Promo code link.");
		BrowserActions.typeOnTextField(txtPromoCode, promoCode, driver, "Entered Applicable Promo Code");
		BrowserActions.clickOnElement(btnApply, driver, "To click on Apply button.");
		String promoAppliedMessage = BrowserActions.getText(driver, msgPromoApplied, "Successful Promo Code applied message");
		promoAppliedMessage.startsWith("Congrats") ;
		return true;
	}

	/**
	 * To click promo drop down on Review page
	 * click on a coupon from the dropdown on Review page
	 * @throws Exception
	 */

	public boolean clickOnPromoCoupon() throws Exception {
		BrowserActions.scrollToView(drpPromoCode, driver);
		BrowserActions.clickOnElement(drpPromoCode, driver, "To click on Promo coupon link.");
		BrowserActions.clickOnElement(lnkPromoCoupon,driver,"To click on Promo Coupon");
		String promoAppliedMessage = BrowserActions.getText(driver, msgPromoApplied, "Successful Promo Code applied message");
		promoAppliedMessage.startsWith("Congrats") ;
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

	public void clickOnAddMeal() throws Exception {
		Utils.waitForElement(driver, btnAddMeal);
		BrowserActions.scrollToView(btnAddMeal, driver);
		BrowserActions.javascriptClick(btnAddMeal, driver, "Add Meal Button");
		Utils.waitForPageLoad(driver);
	}

	public void clickOnAddBaggage() throws Exception {
		Utils.waitForElement(driver, btnAddBaggage);
		BrowserActions.scrollToView(btnAddBaggage, driver);
		BrowserActions.javascriptClick(btnAddBaggage, driver, "Add Baggage Button");
		Utils.waitForPageLoad(driver);
	}



	public void selectMeal() throws Exception {
		Utils.waitForElement(driver, drpSelectMeal);
		BrowserActions.scrollToView(drpSelectMeal, driver);
		BrowserActions.javascriptClick(drpSelectMeal, driver, "Clicked On Drop Down Of Add meal");
		Utils.waitForElement(driver, selectMeal);
		BrowserActions.javascriptClick(selectMeal, driver, "Select Meal");
		Utils.waitForPageLoad(driver);
	}


	public void selectBaggage() throws Exception {
		Utils.waitForElement(driver, drpAddBaggage);
		BrowserActions.scrollToView(drpAddBaggage, driver);
		BrowserActions.javascriptClick(drpAddBaggage, driver, "Clicked On Drop Down Of Baggage");
		Utils.waitForElement(driver, selectBaggage);
		BrowserActions.javascriptClick(selectBaggage, driver, "Select Baggage");
		Utils.waitForPageLoad(driver);
	}

	public void enterUserDeatils() throws Exception {
		Utils.waitForElement(driver, userFirstName);		
		BrowserActions.nap(5);
		String randomFirstName = RandomStringUtils.randomAlphabetic(5)
				.toLowerCase();
		String randomLastName = RandomStringUtils.randomAlphabetic(5)
				.toLowerCase();
		BrowserActions.typeOnTextField(userFirstName, randomFirstName, driver, "First Name");
		BrowserActions.typeOnTextField(userSecondName, randomLastName, driver, "Second Name");

	}

	/**
	 * to click on View fare rules link in fare details module
	 * @throws Exception
	 */
	public void clickOnFareRulesLink() throws Exception{
		BrowserActions.javascriptClick(lnkFareRules, driver, "Clicked on View Fare Rules link.");
	}


	/**
	 *to click on Fee & surcharge link in fare detail module
	 * @throws Exception
	 */

	public void clickOnFeeSurchrgeLink() throws Exception{
		BrowserActions.javascriptClick(lnkFeeSurchrge, driver, "Clicked on Fees & Surcharge link.");
	}

	/**
	 * verifying insurance checbox is checked on unchecked
	 * @return
	 * @throws Exception
	 */
	public boolean verifyInsuranceCheckbox() throws Exception{	
		boolean status = BrowserActions.isRadioOrCheckBoxSelected(chkInsurance);
		return status;
	}

	/**
	 * clicking on insurance checkbox
	 * @throws Exception
	 */
	public void uncheckingInsuranceCheckbox() throws Exception{
		WebElement e =driver.findElement(By.xpath(".//*[@id='traveller-dom']/div[3]/div/div/div[2]/p/label"));
		BrowserActions.clickOnElement(e, driver, "Clicked on Insurance CheckBox.");
	}

	/**
	 * Getting the text from the fare details panel
	 * @return
	 * @throws Exception
	 */


	public String getTextFromFareDetails() throws Exception{
		String txtDetails = BrowserActions.getText(driver, fldContentInsurance, "Getting text from the fare details.");
		return txtDetails;

	}
	


	/**
	 * Getting the ecash redeem successful messages
	 * @return
	 * @throws Exception
	 */


	public String getMsgFromEcashRedeemSuccess() throws Exception{
		String txtRedeemMsg = BrowserActions.getText(driver, msgEcashRedeem, "Getting text for ecash Redeem message.").replace("Cancel Redemption", "");
		return txtRedeemMsg;

	}
	
	
	
	/**
	 * Getting the ecash redeem Balance successful messages
	 * @return
	 * @throws Exception
	 */


	public String getMsgFromEcashBalanceDeduction() throws Exception{
		String txtRedeemMsg = BrowserActions.getText(driver, msgEcashRedeemBalance, "Getting text for ecash Redeem Balance deduction message.");
		return txtRedeemMsg;

	}

}


