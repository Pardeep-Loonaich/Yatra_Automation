package com.Yatra.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
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
import com.Yatra.Pages.ElementLayer;
import com.Yatra.Pages.SearchResultActivites;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class ActivityDetailPage  extends LoadableComponent<ActivityDetailPage> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	public SearchResultActivites searchResultActivites;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();

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
	
	@FindBy(css = "div[class='hotel-info ng-binding']")
	private WebElement txtDescrption;
	
	@FindBy(css = "div[id='viewAdditionalInfo']>div[class='hotel-info']")
	private WebElement txtAdditionalInfo;
	
	@FindBy(css = "div[class='wrapper_inner']")
	private WebElement fldSimilarActivities;
	
	@FindBy(css = "div[class='ng-isolate-scope ng-valid']")
	private WebElement dateCalendar;
	
	@FindBy(css = "div[class='select-wrapper fr ng-isolate-scope']>div[class='label-block fr ng-scope']")
	private WebElement PaxInfo;
	
	@FindBy(css = "span[class='price-sec ng-binding ng-scope']")
	private List<WebElement> AvaliableDatesForActivity;
	
	@FindBy(css = "span[class='price-sec ng-binding ng-scope']")
	private WebElement AvaliableDate;
	
	@FindBy(css = "span[class='ico cal-arr-right']")
	private WebElement nextMonth;
	
	@FindBy(css = "span[class='date-act ng-binding lowest-activity current-date']")
	private WebElement selectedDate;

	@FindBy(css = "span[class ='date-act ng-binding no-activity']")
	private WebElement noActivity;
	
	@FindBy(css = "span[class='no-activity-tip']")
	private WebElement ErrorMessageNoActivity;

	@FindBy(css = "button[data-trackaction='Check Availability']")
	private WebElement btnCheckAvailability;
	
	@FindBy(css = "div[class='col-left product-det']>button[data-trackaction='Book Now']")
	private WebElement btnBookNowAfterCheckAvailability;

	@FindBy(css = "a[class='fs-12 under-link']")
	private WebElement btnCancellationPolicy;
	
	@FindBy(css = "ul[class='cancelation-policy']")
	private WebElement txtCancellationPolicy;
	
	@FindBy(css = "p[class='view-details']>a[class='fs-12 under-link']>span[class='']")
	private WebElement btnCancellationPolicyHide;
	
	@FindBy(css = "[class*='edit-pax']")
	private WebElement lnkChngeDateOrPax;
	
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
		timer.end();
		if (!isPageLoaded) 
		{
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, btnBookNow))) 
		{
		Log.fail("Activity Detail Page did not open up. Site might be down.", driver);
		}
		Log.message("Total time taken by #"+this.getClass().getTypeName()+" to load is:- "+timer.duration()+" "+TimeUnit.SECONDS, driver, true);
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
		Utils.waitForElement(driver, btnBookNow);
		BrowserActions.clickOnElement(btnBookNow, driver, "Click On Book Now Button");
	}
	/**
	 * Click On to select Activities Date
	 * 
	 * @return
	 * @throws Exception
	 */
	public void clickOnSelectActivitiesDate() throws Exception {
		Utils.waitForPageLoad(driver);
		if(AvaliableDate.isDisplayed()){
		int rand = Utils.getRandom(0, AvaliableDatesForActivity.size());
		BrowserActions.clickOnElement(AvaliableDatesForActivity.get(rand), driver, "Click On Select Date");
		}
	else
	{
		BrowserActions.clickOnElement(nextMonth, driver, "Click On Next Month");
		int rand = Utils.getRandom(0, AvaliableDatesForActivity.size());
		BrowserActions.clickOnElement(AvaliableDatesForActivity.get(rand), driver, "Click On Select Date");
	
		}
}
	/**
	 * Verify Color Of the Selected Activity
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifySelectedDateColour() throws Exception {
	boolean status3 = false;
	String rgbvalue = "243, 71, 71";
	BrowserActions.getText(driver, selectedDate, "Getting Selected Date.");
	status3= Utils.verifyCssPropertyForElement(selectedDate,"background-color",rgbvalue);
	return status3;
	}
	/**
	 * To Get Error Message From NO Activity Date
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextErrorMessageNoActivities() throws Exception {
	String Message = null;
	if(noActivity.isDisplayed()){
	BrowserActions.mouseHover(driver, noActivity);	
	Message = BrowserActions.getText(driver, ErrorMessageNoActivity, "Error Messaeg No Activities");
	}
	return Message;
}	
	/**
	 * Click On Check Availability
	 * 
	 * @return
	 * @throws Exception
	 */
	public void clickOnCheckAvailability() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnElement(btnCheckAvailability, driver, "Click On Check Availability Button");
	}
	/**
	 * Click On Book Now After Check Availability
	 * 
	 * @return
	 * @throws Exception
	 */
	public ActivitiesReviewPage clickOnBookNowAfterCheckAvailability() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnElement(btnBookNowAfterCheckAvailability, driver, "Click On Book Now After Check Availability Button");
		return new ActivitiesReviewPage(driver).get();
	}
	/**
	 * Click On Cancellation Policy Button
	 * 
	 * @return
	 * @throws Exception
	 */
	public void clickOnCancellationPolicyButton() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnElement(btnCancellationPolicy, driver, "Click On Cancellation Policy Button");
	}
	/**
	 * Getting the text from the the Cancellation Policy
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextCancellationPolicy() throws Exception {
		Utils.waitForPageLoad(driver);
		String txtDetails = BrowserActions.getText(driver, txtCancellationPolicy,
				"Getting text from the Cancellation Policy");
		return txtDetails;
	}
	/**
	 * Click On Hide Cancellation Policy Button
	 * 
	 * @return
	 * @throws Exception
	 */
	public void clickOnHideCancellationPolicyButton() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnElement(btnCancellationPolicyHide, driver, "Click On Hide Cancellation Policy Button");
	}
}//ActivityDetailPageEnd