package com.Yatra.Pages;

import java.util.ArrayList;
import java.util.Collections;
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
import com.gargoylesoftware.htmlunit.javascript.host.Element;

public class SearchResultBus extends LoadableComponent<SearchResultBus> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	SearchResultBus searchResult;

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

	@FindBy(css = "section[class='col-3 result ng-scope']>div>div[class='wfull result-holder onwards ng-scope']>ul>li[class='totlal-fare box-sizing tar']>a")
	List<WebElement> btnSelectSeat;

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

	@FindBy(css = "div[class='wfull result-holder onwards ng-scope']>ul[class='wfull res-list mt20 sorting-list noListStyle']>li[class='depart box-sizing']>a")
	WebElement lnkDepartTime;

	@FindBy(css = "div[class='wfull result-holder onwards ng-scope']>ul:nth-child(3)>li[class='box-sizing ar-de-seats']>p[class='flL txtMedium w33P']:nth-child(1)>span")
	List<WebElement> departTime;

	@FindBy(css = "div[class='wfull result-holder onwards ng-scope']>ul:nth-child(3)>li[class='box-sizing ar-de-seats']>p[class='flL txtMedium w33P']:nth-child(2)>span")
	List<WebElement> arriveTime;

	@FindBy(css = "div[class='wfull result-holder onwards ng-scope']>ul:nth-child(3)>li[class='box-sizing ar-de-seats']>p[class='flL w33P ng-binding']")
	List<WebElement> duration;
	
	@FindBy(css = "#toater_21")
	WebElement txtErrorMsgEmptyCity;

	@FindBy(css = "div[class='wfull result-holder onwards ng-scope']>ul>li[class='totlal-fare box-sizing tar']>p>span[class='flL txtXXL ng-binding']")
	List<WebElement> price;
	
	@FindBy(css = "div[class='wfull']")
	WebElement selectSeatPopUp;
	
	@FindBy(css = "	div[class='wfull result-holder onwards ng-scope']>ul:not([class='wfull res-list mt20 sorting-list noListStyle'])>li[class='wfull box-sizing footer']>div[class='flL separator']>a")
	WebElement lnkbusDetail;
	
	@FindBy(css = "div[class='wfull bus-details-popup']")
	WebElement busDetailPopUp;
	
	@FindBy(css = "ul>li[data-avl='avl-Y']")
	List <WebElement> selectSeat;
	
	@FindBy(css = "div[id='msdrpdd20_child']>ul>li:nth-child(2)>span")
	WebElement lstBoardingPoint;
	
	@FindBy(css = "div[class='wfull total-fare']>span[class='select-seats-action-round']>a")
	WebElement selectBus_RT;
	
	@FindBy(css = "div[class='return floor-wrapper']>div[class='seat-floor flL mt10 lower-deck']")
	WebElement PopUp_RT;
	
	@FindBy(css = "div[class='flL bus-details-box']>div[class='wfull mt20']>div")
	WebElement ContinueButtonInPopUp;
	
	@FindBy(css = "div[class*='onwards floor-wrapper']>div[class*='seat-floor flL mt10 lower-deck']>ul>li[class*='flL type-SS avl-Y reserved-M']")
	WebElement emptyseatInPopUp;
	
	@FindBy(css="iframe[id='popoverWindow_iframe']")
	private WebElement iFrameOnSearchResultBus;
	
	
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
		BrowserActions.clickOnElement(lnkbusDetail, driver, "Select Seat");
		Utils.waitForPageLoad(driver);
	}
	/**
	 * To click Select Seat
	 * 
	 * @throws Exception
	 */

	public void clickBtnSelectSeat(int index) throws Exception {
		BrowserActions.clickOnElement(btnSelectSeat.get(index), driver, "Select Seat");
		Utils.waitForPageLoad(driver);
	}
	/**
	 * To click Select Seat RT
	 * 
	 * @throws Exception
	 */

	public void clickBtnSelectSeat_RT() throws Exception {
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
		String de = driver.findElement(By.cssSelector(("div[class*='flL bus-details-box']>div[class*='bus-info']>div[class*='wfull bdr-btm-grey padB10']>"))).getText();
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
		String txtDetails = driver.findElement(By.cssSelector("div[id='bordDropping-content']>div[class='wfull gray-scroll']>div>div>div[class='jspPane']")).getText();
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
		String txtDetails = driver.findElement(By.cssSelector("div[id='DroppingOnly-content']>div[class='wfull gray-scroll']>div>div>div")).getText();
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
							+ ")>li[class='box-sizing ar-de-seats']>p[class='flL txtMedium w33P']:nth-child(2)>span" + j
							+ ")>li[class='box-sizing ar-de-seats']>p[class='flL txtMedium w33P']:nth-child(1)>span"))
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
			arrTime.add(driver
					.findElement(By.cssSelector("div[class='wfull result-holder onwards ng-scope']>ul:nth-child("+j+")>li[class='box-sizing ar-de-seats']>p[class='flL w33P ng-binding']"))
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
	 * To Switch to Iframe
	 * 
	 * @throws Exception
	 */
	
	public void switchToIframe() throws Exception {
		//driver.manage().window().maximize(); 
		BrowserActions.switchToIframe(driver, iFrameOnSearchResultBus);
		//driver.switchTo().frame(driver.findElements(By.cssSelector("iframe[id='popoverWindow_iframe']")).get(0)); // switching the frame by ID
		BrowserActions.switchToDefault(driver);
	}	
	
	
	@FindBy(css="div[class*='onwards floor-wrapper']>div[class*='seat-floor flL mt10 lower-deck']>ul>li[class*='flL type-SS avl-Y reserved-M']")
	WebElement listSeat;
	/**
	 * To Select No Of Seat
	 * 
	 * @throws Exception
	 */

	public void selectSeat(int number) throws Exception{
		if(listSeat.isDisplayed()){
		for(int i = 0;i < number;i++){
		driver.findElement(By.cssSelector("div[class='wfull tab-content onwards seat-layout']>div[class='flL seat-map']>div[class='flL floor-layout lower']>div[class='onwards floor-wrapper']>div[class='seat-floor flL mt10 lower-deck']>ul>li[class='flL type-SS avl-Y reserved-M']")).click(); // Random Seat Selected
		BrowserActions.nap(2);
		}	
	}else{
		System.out.println("dfhejhgbjey");
	}
		
	}
	
	
	/**
	 * To Select Boarding Point
	 * 
	 * @throws Exception
	 */
	public String selectBoardingPoint() throws Exception {
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(("span[id='msdrpdd20_title']"))).click(); // Clicked On Drop Down
		Thread.sleep(4000);
		String txt = driver.findElement(By.cssSelector("div[id='msdrpdd20_child']>ul>li:nth-child(2)>span")).getText(); //Boarding Point
		driver.findElement(By.cssSelector("div[id='msdrpdd20_child']>ul>li:nth-child(2)>span")).click(); //Select One Boarding point from Drop Down
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
		for(int i = 0;i < number;i++)
		{
		driver.findElement(By.cssSelector("div[class*='return floor-wrapper']>div[class*='seat-floor flL mt10 lower-deck']>ul>li[class*='flL type-SS avl-Y reserved-M']")).click(); // Random Seat Selected
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
		String txt = driver.findElement(By.cssSelector("div[id='msdrpdd20_child']>ul>li:nth-child(2)>span")).getText(); //Boarding Point
		driver.findElement(By.cssSelector("div[id='msdrpdd20_child']>ul>li:nth-child(2)>span")).click(); //Select One Boarding point from Drop Down
		Utils.waitForPageLoad(driver);
		return txt;
	}
	

	
	public String getTextMaxNumber() throws Exception {
		BrowserActions.nap(2);
		String txtDetails = driver.findElement(By.cssSelector("p[class='wfull txt13 mt20 mr10 t-ac txtGrey']")).getText();
		return txtDetails;
	}
	
	public String getTextSeatType() throws Exception {
		BrowserActions.nap(2);
		String txtDetails = driver.findElement(By.cssSelector("ul>li[class='flL change-deck deck-btn left active']")).getText();
		return txtDetails;
	}
	
	public String getTextSeatTyp() throws Exception {
		BrowserActions.nap(2);
		String txtDetails = driver.findElement(By.cssSelector("ul>li[class='flL change-deck deck-btn right']")).getText();
		return txtDetails;
	
	}
	/**
	 * To click On Continue Button In PoP Up To Select Seat
	 * 
	 * @throws Exception
	 */

	public ReviewPageBus clickOnContinueInPopUp() throws Exception {
	BrowserActions.nap(2);
	driver.findElement(By.cssSelector(("div[class='flL bus-details-box']>div[class='wfull mt20']>div"))).click();
	Utils.waitForPageLoad(driver);
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
		String txtDetails = driver.findElement(By.cssSelector("p[class='wfull txtMedium latoBold mt2 onwards-selected-seats']")).getText();
		return txtDetails;
	}/**
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
		String txtDetails = driver.findElement(By.cssSelector(".boarding.tooltip")).getText();
		return txtDetails;
	
	}/**
	 * To click On Continue Button In PoP Up To Select Seat
	 * 
	 * @throws Exception
	 */

	public void clickOnContinue() throws Exception {
	BrowserActions.nap(2);
	driver.findElement(By.cssSelector(("div[class='flL bus-details-box']>div[class='wfull mt20']>div"))).click();
	
	}
}// SRPBUS

