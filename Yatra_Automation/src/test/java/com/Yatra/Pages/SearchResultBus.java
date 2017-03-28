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

	@FindBy(css = "div[class='flL padL5 w50P']")
	WebElement fldContentBusDetail_RT;

	@FindBy(css = "p[class='wfull text-error']>span[class='no-res-txt ng-binding']")
	WebElement txtNoResultFoundBus;

	@FindBy(css = "section[class='col-3 result ng-scope']>div>div[class='wfull result-holder onwards ng-scope']>ul:nth-child(3)>li[class='totlal-fare box-sizing tar']>a")
	WebElement btnSelectSeat;

	@FindBy(xpath = "//*[@id='onwards-content']/div[@class='flL bus-details-box']/div[@class='bus-info']/div/p[1]")
	WebElement fldContentBusInfoInPopUp;

	@FindBy(css = ".flL.button-nextPrev.anim.prev_day_search_link.eventTrackable")
	WebElement btnPrevDay;

	@FindBy(css = ".flL.button-nextPrev.ml5.anim.next_day_search_link.eventTrackable")
	WebElement btnNextDay;

	@FindBy(css = "#bus_depart_date")
	WebElement fldDepartDate;

	@FindBy(css = "#bus_return_date")
	WebElement fldReturnDate;

	@FindBy(css = "div[class='ac_results']>ul>li[ class='ac_over']")
	WebElement txtCityValidationErrorMsg;
	
	@FindBy(css = "#bus_origin_city")
	 WebElement txtOriginBus;

	@FindBy(css = "	#bordDropping-content>div[class='wfull gray-scroll']>div>div>div")
	 WebElement fldContentBoardingPoint;
	
	@FindBy(css = ".wfull.ng-scope>ul>li[id='bordDropping']")
	 WebElement lnkBoardingPoint;
	
	@FindBy(css = "div[class='wfull ng-scope']>ul>li:nth-child(3)>a")
	 WebElement lnkDroppingPoint;
	
	@FindBy(css = "div[class='jspContainer']>div")
	 WebElement fldContentDroppingPoint;
	
	@FindBy(css = ".wfull.pad-tb10.hide.bdr-top-grey.onwards.selected-seats")
	 WebElement txtSelectedSeat;
	
	@FindBy(css = ".wfull.pad-tb10.onwards.bdr-top-grey.hide.total-ammount")
	 WebElement txtTotalAmount;
	
	@FindBy(css = "")
	 WebElement fldConttBoardingPoint;
	
	@FindBy(css = "")
	 WebElement flontentBoarPoint;
	
	@FindBy(css = "#toater_21")
	WebElement txtErrorMsgEmptyCity;

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

	public SearchResultBus(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		elementLayer = new ElementLayer(driver);
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
		Utils.waitForPageLoad(driver);

	}

	/**
	 * Getting the text from the Bus Deatils
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextBusDetails() throws Exception {
		Utils.waitForElement(driver, fldContentBusDetail);
		String txtDetails = BrowserActions.getText(driver, fldContentBusDetail, "Getting text from the Bus Details");
		return txtDetails;
	}

	/**
	 * Getting the text from the SRP -No Bus Found
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextNoBusFound() throws Exception {
		Utils.waitForElement(driver, txtNoResultFoundBus);
		String txtDetails = BrowserActions.getText(driver, txtNoResultFoundBus, "Getting text from SRP No Bus Found");
		return txtDetails;
	}

	/**
	 * Getting the text from the SRP -No Bus Found
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextCityValidationErrorMsg() throws Exception {
		Utils.waitForElement(driver, txtCityValidationErrorMsg);
		String txtDetails = BrowserActions.getText(driver, txtCityValidationErrorMsg, "Getting Text From City Invalid Name");
		return txtDetails;
	}
	/**
	 * To click Select Seat
	 * 
	 * @throws Exception
	 */

	public void clickBtnSelectSeat() throws Exception {
		BrowserActions.clickOnElement(btnSelectSeat, driver, "Select Seat");
		Utils.waitForPageLoad(driver);
	}
	/**
	 * To Select Bus Origin
	 * 
	 * @throws Exception
	 */
	public void enterOriginBus(String origin) throws Exception {
		Utils.waitForElement(driver, txtOriginBus);
		BrowserActions.typeOnTextField(txtOriginBus, origin, driver, "Select Origin");
		Log.event("Entered the Origin: " + origin);
	}

	/**
	 * Getting the text from the Bus Info
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextBusInfo() throws Exception {
		Utils.waitForElement(driver, fldContentBusInfoInPopUp);
		String de= driver.switchTo().frame(fldContentBusInfoInPopUp).getWindowHandle();
//		String txtDetails = BrowserActions.getText(driver, fldContentBusInfoInPopUp,
//				"Getting text from the Bus Info From Pop Up After Clickimg Select Seat");
		return de;
	}
	/**
	 * Getting the text from the Bus Info
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextBusBoardingPoint() throws Exception {
		Utils.waitForElement(driver, fldContentBoardingPoint);
		String txtDetails = BrowserActions.getText(driver, fldContentBoardingPoint,
				"Getting text from the Bus Boarding Point");
		return txtDetails;
	}
	/**
	 * Getting the text from the Bus Info
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextBusDroppingPoint() throws Exception {
		Utils.waitForElement(driver, fldContentDroppingPoint);
		String txtDetails = BrowserActions.getText(driver, fldContentDroppingPoint,
				"Getting text from the Bus Dropping Point");
		return txtDetails;
	}
	

	/**
	 * To click On Boarding Point Link
	 * 
	 * @throws Exception
	 */

	public void clickOnBoardingPoint() throws Exception {
		Utils.waitForElement(driver, lnkBoardingPoint);
		BrowserActions.clickOnElement(lnkBoardingPoint, driver, "Boarding Point link");
		Utils.waitForPageLoad(driver);
	}
	/**
	 * To click On Dropping Point Link
	 * 
	 * @throws Exception
	 */

	public void clickOnDroppingPoint() throws Exception {
		BrowserActions.clickOnElement(lnkDroppingPoint, driver, "Dropping Point link");
		Utils.waitForPageLoad(driver);
	}
}