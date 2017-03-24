package com.Yatra.Pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class SearchResultBus extends LoadableComponent<SearchResultBus> {

	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/

	@FindBy(css = "#BE_flight_flsearch_btn")
	WebElement btnFindBus;
	
	@FindBy(css = "div[class='wfull mt10 dest-details box-sizing']>div[class='flL w75P']")
	WebElement fldContentBusDetail;
	
	@FindBy(css = ".no-res-txt.ng-binding")
	WebElement txtNoResultFoundBus;

	
	/**********************************************************************************************
	 ********************************* WebElements of Home Page - Ends ****************************
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

	public SearchResultBus(WebDriver driver, String url) {
		appURL = url;
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}// SearchResultBus

	/**
	 * 
	 * @param driver
	 *              : webdriver
	 */
	public SearchResultBus(WebDriver driver) {
		appURL = "https://www.yatra.com/";
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}// SearchResultBus

	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, btnFindBus))) {
			Log.fail("SearchResultBus Page did not open up. Site might be down.", driver);
		}
	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}// load
	/**
	 * Getting the text from the Bus Deatils
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextBusDetails() throws Exception {
		String txtDetails = BrowserActions.getText(driver, fldContentBusDetail,"Getting text from the Bus Details");
		return txtDetails;
	}
	/**
	 * Getting the text from the SRP -No Bus Found
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextNoBusFound() throws Exception {
		String txtDetails = BrowserActions.getText(driver, txtNoResultFoundBus,"Getting text from SRP No Bus Found");
		return txtDetails;
	}
}