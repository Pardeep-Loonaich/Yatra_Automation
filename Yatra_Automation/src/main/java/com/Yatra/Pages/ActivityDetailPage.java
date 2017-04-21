package com.Yatra.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
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
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class ActivityDetailPage  extends LoadableComponent<ActivityDetailPage> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	public SearchResultActivites searchResultActivites;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/

	@FindBy(css = "div[class='price-block fr disp-table-cell']>ul>li[class='mt2']>button")
	private WebElement btnBookNow;
	
	@FindBy(css = "h3[class='fm-lb mb5 top-head ng-binding']")
	private WebElement txtDetailActivity;
	
	@FindBy(css = "div[id='carousel']>div>div[class='car-item ng-scope active']")
	private WebElement activityPhotos;
	
	@FindBy(css = "ul[class='row my-nav col hidden-md fs-12 uprcse tabs-active-bottom-in tabs']>li")
	private List<WebElement> headerDisplay;
	
	@FindBy(xpath = "div[class='hotel-info ng-binding']")
	private WebElement txtDescrption;
	
	@FindBy(css = "div[id='viewAdditionalInfo']>div[class='hotel-info']")
	private WebElement txtAdditionalInfo;
	
	@FindBy(css = "div[class='wrapper_inner']")
	private WebElement fldSimilarActivities;
	
	@FindBy(css = "div[class='ng-isolate-scope ng-valid']")
	private WebElement dateCalendar;
	
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

	public ActivityDetailPage(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		elementLayer = new ElementLayer(driver);
	}// ActivityDetailPage

	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, btnBookNow))) {
			Log.fail("Activity Detail Page did not open up. Site might be down.", driver);
		}
	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);

	}
	/**
	 * Getting the text from the the Activity Details
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTexActivityDetails() throws Exception {
		Utils.waitForPageLoad(driver);
		String txtDetails = BrowserActions.getText(driver, txtDetailActivity,
				"Getting text from the Activity Details");
		return txtDetails;
	}

	
	/**
	 * Clicking on header link om Activity deatils Page
	 * 
	 * @param String- photos,description
	 * @throws Exception
	 */
	public void ClickOnHeaderLinks(String header) throws Exception {
		Utils.waitForPageLoad(driver);
		for (WebElement ele : headerDisplay) {
			if (ele.findElement(By.cssSelector("a")).getText().equalsIgnoreCase(header)) 
			{
				BrowserActions.scrollToViewElement(ele.findElement(By.cssSelector("a")), driver);
				BrowserActions.clickOnElement(ele.findElement(By.cssSelector("a")), driver, "list elements in others");
				break;
			}
		}
	}
	
	/**
	 * Getting the text from the the Activity Description
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTexActivityDescription() throws Exception {
		Utils.waitForPageLoad(driver);
		String txtDetails = BrowserActions.getText(driver, txtDescrption,
				"Getting text from the Activity Description");
		return txtDetails;
	}
	/**
	 * Getting the text from the the Activity Additional Info
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTexActivityAdditionalInfo() throws Exception {
		Utils.waitForPageLoad(driver);
		String txtDetails = BrowserActions.getText(driver, txtAdditionalInfo,
				"Getting text from the Activity Additional Info");
		return txtDetails;
	}
	
	/**
	 * Click On Book Now
	 * 
	 * @return
	 * @throws Exception
	 */
	public void clickOnBookNowButton() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnElement(btnBookNow, driver, "Click On Book Now Button");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}//ActivityDetailPage