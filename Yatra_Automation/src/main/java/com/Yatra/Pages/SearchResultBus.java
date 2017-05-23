package com.Yatra.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class SearchResultBus extends LoadableComponent<SearchResultBus> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	SearchResultBus searchResult;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/

	@FindBy(css = "#BE_flight_flsearch_btn")
	private WebElement btnFindBus;

	@FindBy(css = "div[class='wfull mt10 dest-details box-sizing']>div[class='flL w75P']")
	private WebElement fldContentBusDetail;

	@FindBy(css = "div[class='flL padL5 w50P']")
	private	WebElement fldContentBusDetail_RT;

	@FindBy(css = "p[class='wfull text-error']>span[class='no-res-txt ng-binding']")
	private WebElement txtNoResultFoundBus;

	@FindBy(css = "section[class='col-3 result ng-scope']>div>div[class='wfull result-holder onwards ng-scope']>ul>li[class='totlal-fare box-sizing tar']>a")
	private List <WebElement> btnSelectSeat;

	@FindBy(xpath = "//*[@id='onwards-content']/div[@class='flL bus-details-box']/div[@class='bus-info']/div/p[1]")
	private WebElement fldContentBusInfoInPopUp;

	@FindBy(css = ".flL.button-nextPrev.anim.prev_day_search_link.eventTrackable")
	private WebElement btnPrevDay;

	@FindBy(css = ".flL.button-nextPrev.ml5.anim.next_day_search_link.eventTrackable")
	private WebElement btnNextDay;

	@FindBy(css = "#bus_depart_date")
	private WebElement fldDepartDate;

	@FindBy(css = "#bus_return_date")
	private WebElement fldReturnDate;

	@FindBy(css = "div[class='ac_results']>ul>li[ class='ac_over']")
	private WebElement txtCityValidationErrorMsg;

	@FindBy(css = "#bus_origin_city")
	private WebElement txtOriginBus;

	@FindBy(css = "	#bordDropping-content>div[class='wfull gray-scroll']>div>div>div")
	private WebElement fldContentBoardingPoint;

	@FindBy(css = ".wfull.ng-scope>ul>li[id='bordDropping']")
	private WebElement lnkBoardingPoint;

	@FindBy(css = "div[class='wfull ng-scope']>ul>li:nth-child(3)>a")
	private WebElement lnkDroppingPoint;

	@FindBy(css = "div[class='jspContainer']>div")
	private WebElement fldContentDroppingPoint;

	@FindBy(css = ".wfull.pad-tb10.hide.bdr-top-grey.onwards.selected-seats")
	private WebElement txtSelectedSeat;

	@FindBy(css = ".wfull.pad-tb10.onwards.bdr-top-grey.hide.total-ammount")
	private WebElement txtTotalAmount;

	@FindBy(css = "div[class='wfull result-holder onwards ng-scope']>ul[class='wfull res-list mt20 sorting-list noListStyle']>li[class='depart box-sizing']>a")
	private WebElement lnkDepartTime;

	@FindBy(css = "div[class='wfull result-holder onwards ng-scope']>ul:nth-child(3)>li[class='box-sizing ar-de-seats']>p[class='flL txtMedium w33P']:nth-child(1)>span")
	private List<WebElement> departTime;

	@FindBy(css = "div[class='wfull result-holder onwards ng-scope']>ul:nth-child(3)>li[class='box-sizing ar-de-seats']>p[class='flL txtMedium w33P']:nth-child(2)>span")
	private List<WebElement> arriveTime;

	@FindBy(css = "div[class='wfull result-holder onwards ng-scope']>ul:nth-child(3)>li[class='box-sizing ar-de-seats']>p[class='flL w33P ng-binding']")
	private List<WebElement> duration;

	@FindBy(css = "#toater_21")
	private WebElement txtErrorMsgEmptyCity;

	@FindBy(css = "div[class='wfull result-holder onwards ng-scope']>ul>li[class='totlal-fare box-sizing tar']>p>span[class='flL txtXXL ng-binding']")
	private List<WebElement> price;

	@FindBy(css = "div[class='wfull']")
	private WebElement selectSeatPopUp;

	@FindBy(css = "	div[class='wfull result-holder onwards ng-scope']>ul:not([class='wfull res-list mt20 sorting-list noListStyle'])>li[class='wfull box-sizing footer']>div[class='flL separator']>a")
	private WebElement lnkbusDetail;

	@FindBy(css = "div[class='wfull bus-details-popup']")
	private WebElement busDetailPopUp;

	@FindBy(css = "ul>li[data-avl='avl-Y']")
	private List<WebElement> selectSeat;

	@FindBy(css = "div[id='msdrpdd20_child']>ul>li:nth-child(2)>span")
	private	WebElement lstBoardingPoint;

	@FindBy(css = "div[class='wfull total-fare']>span[class='select-seats-action-round']>a")
	private WebElement selectBus_RT;

	@FindBy(css = "div[class='return floor-wrapper']>div[class='seat-floor flL mt10 lower-deck']")
	private WebElement PopUp_RT;

	@FindBy(css = "div[class='flL bus-details-box']>div[class='wfull mt20']>div")
	private	WebElement ContinueButtonInPopUp;

	@FindBy(css = "div[class*='onwards floor-wrapper']>div[class*='seat-floor flL mt10 lower-deck']>ul>li[class*='flL type-SS avl-Y reserved-M']")
	private WebElement emptyseatInPopUp;

	@FindBy(css="iframe[id='popoverWindow_iframe']")
	private WebElement iFrameOnSearchResultBus;

	@FindBy(xpath = "(//a[@data-trackvalue='Select Seats PopUp'][contains(.,'Continue')])[1]")
	WebElement ContinueInIframe;
	
	@FindBy(css = "div[id='bordDropping-content']>div[class='wfull gray-scroll']>div>div>div[class='jspPane']")
	WebElement detailsBoardingPoint;
	
	@FindBy(css = "div[id='DroppingOnly-content']>div[class='wfull gray-scroll']>div>div>div")
	WebElement detailsDroppingPoint;
	
	@FindBy(css = "div[id='onwards-content']>div[class='flL seat-map']>div[class='flL floor-layout lower']>p")
	WebElement txtMaxSeat;
	
	@FindBy(css = "ul>li[class='flL change-deck deck-btn left active']")
	WebElement txtSeatType;
	
	@FindBy(css = "ul>li[class='flL change-deck deck-btn right']")
	WebElement txtSeatTyp;
	
	@FindBy(css = "p[class='wfull txtMedium latoBold mt2 onwards-selected-seats']")
	WebElement txtSeatNumber;

	@FindBy(css = ".boarding.tooltip")
	WebElement errorMessage;
	
	@FindBy(css = "/div[class='wfull tab-content onwards seat-layout']>div[class='flL seat-map']>div[class='flL floor-layout lower']>div[class='onwards floor-wrapper']>div[class='seat-floor flL mt10 lower-deck']>ul>li[class='flL type-SS avl-Y reserved-M']")
	WebElement btnSelectseat;
	
	@FindBy(css = "div[class='wfull error-message'")
	WebElement errorMessageSorryNoSeat;
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
		timer.end();
		if (!isPageLoaded) {

			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, btnFindBus))) {
			Log.fail("SearchResultBus Page did not open up. Site might be down.", driver);
			Log.message("Total time taken by #"+this.getClass().getTypeName()+" to load is:- "+timer.duration()+" "+TimeUnit.SECONDS);
		}
	}// isLoaded

	@Override
	protected void load() {
		timer.start();
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}// load

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
		String txtDetails = BrowserActions.getText(driver, txtCityValidationErrorMsg,
				"Getting Text From City Invalid Name");
		return txtDetails;
	}

	/**
	 * To click Select Seat
	 * 
	 * @throws Exception
	 */

	public void clickOnLinkBusDetails() throws Exception {
		Utils.waitForElement(driver, lnkbusDetail);
		BrowserActions.clickOnElement(lnkbusDetail, driver, "Select Seat");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To click Select Seat
	 * 
	 * @throws Exception
	 */
	public void clickBtnSelectSeat() throws Exception {	
		BrowserActions.nap(3);
		Utils.waitForPageLoad(driver);
		int rand = Utils.getRandom(0, 5);
		BrowserActions.scrollToViewElement(btnSelectSeat.get(rand), driver);
		BrowserActions.clickOnElement(btnSelectSeat.get(rand), driver, "Select Seat");
		Utils.waitForPageLoad(driver);
	}
	/**
	 * To click Select Seat RT
	 * 
	 * @throws Exception
	 */

	public void clickBtnSelectSeat_RT() throws Exception {
		Utils.waitForElement(driver, selectBus_RT);
		BrowserActions.clickOnElement(selectBus_RT, driver, "Select Seat button RT");
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
		String de = driver
				.findElement(By.cssSelector(
						("div[id='onwards-content']>div[class='flL bus-details-box']>div[class='bus-info']>div[class='wfull bdr-btm-grey padB10']")))
				.getText();
		return de;
	}

	/**
	 * Getting the text from the Bus Info
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextBusBoardingPoint() throws Exception {
		BrowserActions.nap(2);
		String txtDetails = detailsBoardingPoint.getText();
		return txtDetails;
	}

	/**
	 * Getting the text from the Bus Info
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextBusDroppingPoint() throws Exception {
		BrowserActions.nap(2);
		String txtDetails = detailsDroppingPoint.getText();
		return txtDetails;
	}

	/**
	 * To click On Boarding Point Link
	 * 
	 * @throws Exception
	 */

	public void clickOnBoardingPoint() throws Exception {
		BrowserActions.nap(2);
		driver.findElement(By.xpath((".//*[@id='bordDropping']/a"))).click();
	}

	/**
	 * To click On Dropping Point Link
	 * 
	 * @throws Exception
	 */

	public void clickOnDroppingPoint() throws Exception {
		BrowserActions.nap(2);
		driver.findElement(By.xpath((".//*[@id='DroppingOnly']/a"))).click();
	}

	/**
	 * To click On Boarding Point Link
	 * 
	 * @throws Exception
	 */

	public void clickOnDepartTimeLink() throws Exception {
		Utils.waitForElement(driver, lnkDepartTime);
		BrowserActions.clickOnElement(lnkDepartTime, driver, "Depart Time Link");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * Need To be Tested once or Reviewed
	 */
	public boolean comapreAndSortList(ArrayList<String> prices) throws Exception {
		boolean Flag = true;
		Collections.sort(prices);
		for (int i = 1; i < prices.size(); i++) {
			if (prices.get(i - 1).compareTo(prices.get(i)) > 0)
				Flag = false;
		}
		return Flag;
	}

	/**
	 * To check Price in Sorted Form
	 * 
	 * @throws Exception
	 */
	public boolean getSortedPrice() throws Exception {
		boolean Flag = true;
		ArrayList<String> list = new ArrayList<String>();
		for (int j = 3; j < price.size(); j++) {
			list.add(driver
					.findElement(By.cssSelector("div[class='wfull result-holder onwards ng-scope']>ul:nth-child(" + j
							+ ")>li[class='totlal-fare box-sizing tar']>p>span[class='flL txtXXL ng-binding']"))
					.getText());
		}
		Collections.sort(list);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1).compareTo(list.get(i)) > 0)
				Flag = false;
		}
		return Flag;
	}

	/**
	 * To check Depart Time in Sorted Form
	 * 
	 * @throws Exception
	 */
	public boolean getSortedDepartTime() throws Exception {
		boolean Flag = true;
		ArrayList<String> time = new ArrayList<String>();
		for (int j = 3; j < departTime.size(); j++) {
			time.add(driver
					.findElement(By.cssSelector("div[class='wfull result-holder onwards ng-scope']>ul:nth-child(" + j
							+ ")>li[class='box-sizing ar-de-seats']>p[class='flL txtMedium w33P']:nth-child(1)>span"))
					.getText());
		}
		Collections.sort(time);
		for (int i = 1; i < time.size(); i++) {
			if (time.get(i - 1).compareTo(time.get(i)) > 0)
				Flag = false;
		}
		return Flag;
	}

	/**
	 * To check Arrive Time in Sorted Form
	 * 
	 * @throws Exception
	 */
	public boolean getSortedArriveTime() throws Exception {
		boolean Flag = true;
		ArrayList<String> arrTime = new ArrayList<String>();
		for (int j = 3; j < arriveTime.size(); j++) {
			arrTime.add(driver
					.findElement(By.cssSelector("div[class='wfull result-holder onwards ng-scope']>ul:nth-child(" + j
							+ ")>li[class='box-sizing ar-de-seats']>p[class='flL txtMedium w33P']:nth-child(2)>span"))
					.getText());
		}
		Collections.sort(arrTime);
		for (int i = 1; i < arrTime.size(); i++) {
			if (arrTime.get(i - 1).compareTo(arrTime.get(i)) > 0)
				Flag = false;
		}
		return Flag;
	}

	/**
	 * To check Duration in Sorted Form
	 * 
	 * @throws Exception
	 */
	public boolean getSortedDuration() throws Exception {
		boolean Flag = true;
		ArrayList<String> arrTime = new ArrayList<String>();
		for (int j = 3; j < duration.size(); j++) {
			arrTime.add(
					driver.findElement(By.cssSelector("div[class='wfull result-holder onwards ng-scope']>ul:nth-child("
							+ j + ")>li[class='box-sizing ar-de-seats']>p[class='flL w33P ng-binding']")).getText());
		}
		Collections.sort(arrTime);
		for (int i = 1; i < arrTime.size(); i++) {
			if (arrTime.get(i - 1).compareTo(arrTime.get(i)) > 0)
				Flag = false;
		}
		return Flag;
	}

	/**
	 * To Switch to Iframe
	 * 
	 * @throws Exception
	 */
	public void switchToIframe() throws Exception {
		BrowserActions.nap(2);
		BrowserActions.switchToIframe(driver, iFrameOnSearchResultBus);
		
	}	
	/**
	 * To Select No Of Seat
	 * 
	 * @throws Exception
	 */

	public void selectSeat(int number) throws Exception {
		Utils.waitForPageLoad(driver);
		if(txtSeatType.isDisplayed()){
		for (int i = 0; i < number; i++) {
			driver.findElement(By.cssSelector(
					"div[id='onwards-content']>div[class='flL seat-map']>div[class='flL floor-layout lower']>div[class='onwards floor-wrapper']>div[class='seat-floor flL mt10 lower-deck']>ul>li[class='flL type-SL avl-Y reserved-M']"))
					.click(); // Random Seat Selected
			BrowserActions.nap(2);
			}
		}else
			{
				for (int i = 0; i < number; i++) {
					driver.findElement(By.cssSelector(
							"div[id='onwards-content']>div[class='flL seat-map']>div[class='flL floor-layout lower']>div[class='onwards floor-wrapper']>div[class='seat-floor flL mt10 lower-deck']>ul>li[class='flL type-SS avl-Y reserved-M']"))
							.click(); // Random Seat Selected
					BrowserActions.nap(2);
			}
		}
	}
	/**
	 * To Select Boarding Point
	 * 
	 * @throws Exception
	 */
	public String selectBoardingPoint() throws Exception {
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(("span[id='msdrpdd20_title']"))).click(); // Clicked
																					// On
																					// Drop
																					// Down
		Thread.sleep(4000);
		String txt = driver.findElement(By.cssSelector("div[id='msdrpdd20_child']>ul>li:nth-child(2)>span")).getText(); // Boarding
																														// Point
		driver.findElement(By.cssSelector("div[id='msdrpdd20_child']>ul>li:nth-child(2)>span")).click(); //Select one Boadring point From Drop Down
																											
		Utils.waitForPageLoad(driver);
		return txt;
	}

	/**
	 * To Select Return Seat in Return Bus Ticket
	 * 
	 * @throws Exception
	 */

	public void selectReturnSeat(int number) throws Exception {
		Thread.sleep(5000);
		for (int i = 0; i < number; i++) {
			driver.findElement(By.cssSelector(
					"div[class*='return floor-wrapper']>div[class*='seat-floor flL mt10 lower-deck']>ul>li[class*='flL type-SS avl-Y reserved-M']"))
					.click(); // Random Seat Selected
		}
	}

	/**
	 * To Select Boarding Point In Return Ticket
	 * 
	 * @throws Exception
	 */
	public String selectBoardingPointReturn() throws Exception {
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(("span[id='msdrpdd20_title']"))).click(); // Clicked On Drop Down																
		String txt = driver.findElement(By.cssSelector("div[id='msdrpdd20_child']>ul>li:nth-child(2)>span")).getText();
		driver.findElement(By.cssSelector("div[id='msdrpdd20_child']>ul>li:nth-child(2)>span")).click(); // Select one Boarding point																								
		Utils.waitForPageLoad(driver);
		return txt;
	}

	/**
	 * To Get Text Total Seat Number Booked
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextMaxNumber() throws Exception {
		BrowserActions.nap(2);
		String txtDetails = txtMaxSeat.getText();
		return txtDetails;
	}
	/**
	 * To Get Text Seat Type
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSeatType() throws Exception {
		BrowserActions.nap(2);
		String txtDetails = txtSeatType.getText();
		return txtDetails;
	}
	/**
	 * To Get Text Seat Type
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSeatTyp() throws Exception {
		BrowserActions.nap(2);
		String txtDetails = txtSeatTyp.getText();
		return txtDetails;
	}

	/**
	 * To Click on Continue Button
	 * 
	 *  @return
	 * @throws Exception
	 */

	public ReviewPageBus clickOnContinueInPopUp() throws Exception {
		BrowserActions.nap(2);
		ContinueInIframe.click();
		BrowserActions.switchToDefault(driver);
		BrowserActions.nap(2);
		return new ReviewPageBus(driver).get();
	}

	/**
	 * Getting the text from the Bus Pop Up "Seat Number"
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSeatNumber() throws Exception {
		BrowserActions.nap(2);
		String txtDetails = txtSeatNumber.getText();
		return txtDetails;
	}

	/**
	 * To click On Continue Button In PoP Up To Select Seat
	 * 
	 * @throws Exception
	 */

	public void clickOnSelectReturnSeat() throws Exception {
		BrowserActions.nap(2);
		driver.findElement(By.cssSelector(("div[class='flL bus-details-box']>div[class='wfull mt20']>div"))).click();
	}

	/**
	 * Getting the text from the Bus Pop Up "Error Message"
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextErrorMessage() throws Exception {
		BrowserActions.nap(2);
		String txtDetails = errorMessage.getText();
		return txtDetails;
	}

	/**
	 * To click On Continue Button In PoP Up To Select Seat
	 * 
	 * @throws Exception
	 */
	public void clickOnContinue() throws Exception {
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("a[class*='flL ytBtn ytBtnBlue txtL LatoBold seat_map_continue tooltip']")).click(); 
	}
	
	
	
}// SRPBUS
