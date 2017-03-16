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

	@FindBy(css="[ng-click='showFareRulesPopup()']")
	WebElement lnkFareRules;


	@FindBy(css="div[class='fareBox']>ul[class='list review-title']")
	WebElement moduleFeeSurchrge;

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
