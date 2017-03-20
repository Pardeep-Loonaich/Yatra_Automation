package com.Yatra.Pages;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
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

	
	@FindBy(css = "div[ng-show='showFareDetails']")
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
	
	@FindBy(xpath = ".//*[@id='ssrContainer']/div[2]/div[3]/div[5]/div/div/ul/li[2]/span/select/option[2]")
	WebElement drpSelectMeal;
	
	@FindBy(xpath = ".//*[@id='ssrContainer']/div[2]/div[3]/div[5]/div/div/ul/li[2]/span/select/option[3]")
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


	public void clickOnContinue() throws Exception {
		BrowserActions.nap(6);
		Utils.waitForElement(driver, btnContinueReviewPage);
		BrowserActions.scrollToView(btnContinueReviewPage, driver);
		BrowserActions.javascriptClick(btnContinueReviewPage, driver, "Continue Button");
		Utils.waitForPageLoad(driver);
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


}
