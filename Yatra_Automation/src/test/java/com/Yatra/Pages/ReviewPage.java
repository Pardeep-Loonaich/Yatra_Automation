package com.Yatra.Pages;

import java.util.List;

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

	@FindBy(css ="span[class='pull-left w85perc ng-binding']")
	WebElement msgPromoApplied;
	
	@FindBy(css = "span[ng-show='selectPromo && promoListEnable']")
	WebElement lnkHavePromoCode;
	
	@FindBy(xpath="//input[@id='promoListInput']")
	WebElement txtPromoCode;
	
	@FindBy(css= "button[ng-show='!selectPromo']")
	WebElement btnApply;
	
	
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
			Log.fail("Review page didn't open up", driver);
		}
		// elementLayer = new ElementLayer(driver);
	}

	@Override
	protected void load() {
		isPageLoaded = true;

		Utils.waitForPageLoad(driver);
	}// load
	
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
}
