package com.Yatra.Pages;

import java.util.ArrayList;
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
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class SearchResult extends LoadableComponent<SearchResult> {

	private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Search Result Page ***********************************
	 **********************************************************************************************/
	//@Harveer- 1- make all element private
	//2- remove all c commented code if it is not required.
	@FindBy(css = ".ico-newHeaderLogo")
	private WebElement headerLogo;

	@FindBy(css = ".filter-list.filter-list-with-clear:nth-child(1)>label>div>span")
	private WebElement chkChooseFlightFirst;

	@FindBy(css = ".filter-list.filter-list-with-clear:nth-child(1)>label>span:nth-child(2)")
	private WebElement preferredFlightName;

	@FindBy(css = "[class ='ico-newHeaderLogo']")
	private WebElement logoYatra;

	@FindBy(xpath = "//ul[@class='matrix-slide-list tabs matrix-ul']/li")
	private List<WebElement> lnkAirlineMatrix;

	@FindBy(css = "p[class='new-gray-button fl small link-button']") 
	private WebElement btnModifySearchIcon;

	@FindBy(css = "div[class='full']>div[class='matrix-wrapper day-matrix new-theme day-matrix-responsive']")
	private WebElement weeklyStrip;

	@FindBy(css = "[class='js-flightRow js-flightItem']")
	private List<WebElement> btnBookNow;

	@FindBy(css = "p[class='new-blue-button .js-bookNow book-btn relative tc']")
	private WebElement btnBookNowINT;

	@FindBy(css = "div[ng-controller='productFareDetailsController']")
	private WebElement moduleFareDetails;

	@FindBy(css = "div[class='show-result multi-1']>div>div[class='results']>div:nth-child(1)>article>div[class='my-res-info full']>ul>li>small:nth-child(2)")
	private WebElement firstAirlineName_OW_DOM;

	@FindBy(css = "div[class='js-flightItem']:nth-child(2)>article>div[class='full lob-inclusions bxs hidden-md']>div[class='inc-rgt']>ul>li>a[title='Flight Details']")
	private WebElement lnkFlightDetails_INTL;

	@FindBy(css = "#resultBoxSlider>div[id='resultList_0']>div[class='results']>div:nth-child(1)>article>footer>ul[class='res-footer-list fl uprcse']")
	private WebElement lnkFlightDetails_DOM;

	@FindBy(css = "div[class='new-green-button fr relative tc']")
	private WebElement btnBookNowRoundTrip;

	@FindBy(css = "div[class='show-result multi-1']>div>div[class='results']>div:nth-child(1)>article>footer>ul[class='res-footer-list fl uprcse']>li:nth-child(1)")
	private WebElement lnkFlightDetails;

	@FindBy(css = "div[class='short-details']>h3>p")
	private WebElement btnBookNowFlightDeatilPopUp;

	@FindBy(css = "div[class='short-details']>div")
	private WebElement fldContentFlightDetails;

	@FindBy(css = "div[class='my-fare-grid']>ul[class='tab fs-md']>li[ng-hide='isBYOPPage']")
	private WebElement lnkFareSummaryandRules;

	@FindBy(css = ".one-fourth")
	private WebElement fldContentFareDeatilAndRulesDetails;

	@FindBy(css = "div[class*='disclaimer']>span")
	private WebElement txtDisclaimer;

	@FindBy(css = "div[class='my-fare-grid']>ul[class='tab fs-md']>li:nth-child(3)")
	private WebElement lnkBaggage;

	@FindBy(css = "div[class='row baggage-summary']")
	private WebElement fldContentBaggageDetail;
	
	@FindBy(css = "#userSignInStrip a.dropdown-toggle")
	private WebElement txtMyAccount;
	
	@FindBy(css = "#cutomerSupportNav a.dropdown-toggle.eventTrackable")
	private WebElement txtSupport;
	
	@FindBy(css = "#discountHdrLink")
	private WebElement txtSplDeals;
	
	@FindBy(css = "#recentSearch-wrapper a.dropdown-toggle")
	private WebElement txtRecentSearch;
	
	@FindBy(css = "a.logo")
	private WebElement lnkYatraLogo;
	
	@FindBy(css = "#userShowName")
	private WebElement txtUserAcctName;
	
	@FindBy(css = ".simple-dropdown")
	private WebElement txtMyBookings;
	
	@FindBy(css = "#signInBtn")
	private WebElement txtLogin;
	
	@FindBy(css = ".simple-dropdown.login-li.clearfix>span>a")
	private WebElement txtSignUp;
	
	@FindBy(css = "a.simple-tab.eventTrackable.uniqueDate")
	private WebElement txtCorporateLogin;
	
	@FindBy(xpath = "//a[contains(text(),'Agent Login')]")
	private WebElement txtAgentLogin;
	
	@FindBy(css = "div[class='left fl']>p[class='fs-12']")  
	private WebElement txtTotalFlightSearch;

	@FindBy(css = ".left.fl p.fs-10.ltr-gray.uprcse.mt2")
	private WebElement txtFlightSearchDuration;
	
	@FindBy(css = "div[class='fl center']>ul[class='full city-details tripO']>li:nth-child(1)>p[class='city-name']")   
	private WebElement txtSourceCity_OW;
	
	@FindBy(css = "div[class='fl center']>ul[class='full city-details tripO']>li:nth-child(1)>p[class*='fs-10']")   
	private WebElement txtSourceDate_OW;
	
	@FindBy(css = "div[class='fl center']>ul[class='full city-details tripO']>li:nth-child(2)>p[class='city-name']")  
	private WebElement txtDestCity_OW;
	
	@FindBy(css = "ul[class='full city-details tripR']>li:nth-child(1)>p[class='city-name']")   
	private WebElement txtSourceCity;
	
	@FindBy(css = "ul[class='full city-details tripR']>li:nth-child(1)>p[class*='fs-10']")   
	private WebElement txtSourceDate;
	
	@FindBy(css = "ul[class='full city-details tripR']>li:nth-child(2)>p[class='city-name']")  
	private WebElement txtDestCity;
	
	@FindBy(css = "ul[class='full city-details tripR']>li:nth-child(2)>p[class*='fs-10']")   
	private WebElement txtDestDate;	
	
	@FindBy(css = "div[class*='center']>ul:nth-child(2)>li:nth-child(1)>p[class='city-name tl']")   
	private WebElement txtStartSourceCity;
	
	@FindBy(css = "div[class*='center']>ul:nth-child(2)>li:nth-child(1)>p[class*='fs-10']")   
	private WebElement txtStartSourceDate;
	
	@FindBy(css = "div[class*='center']>ul:nth-child(2)>li:nth-child(2)>p[class='fl city-name tl']")  
	private WebElement txtstartDestCity;
	
	@FindBy(css = "div[class*='center']>ul:nth-child(3)>li:nth-child(1)>p[class='city-name tl']")   
	private WebElement txtEndSourceCity;
	
	@FindBy(css = "div[class*='center']>ul:nth-child(3)>li:nth-child(1)>p[class*='fs-10']")   
	private WebElement txtEndSourceDate;
	
	@FindBy(css = "div[class*='center']>ul:nth-child(3)>li:nth-child(2)>p[class='fl city-name tl']")  
	private WebElement txtEndDestCity;
	
	@FindBy(css = "div[id='resultList_0']>div:nth-child(3)>div:nth-child(1) div[class='time']>span")   
	private WebElement txtFlightDuration;
	
	@FindBy(css = "div[id='resultList_0']>div:nth-child(3)>div:nth-child(1) a[class='under-link']")  
	private WebElement lnkFlightDetail;
	
	@FindBy(css = "div[class='row itinerary-details'] li[class='trip-type'] time")  
	private WebElement txtFlightDetailsPopupDuration;
	
	@FindBy(css = "span[class='ico ico-close overlay-close']")  
	private WebElement lnkcloseFlightDetailsPopUp;	
	
	@FindBy(css = "div[id='resultList_0']>div:nth-child(3)>div:nth-child(1) span[class='ml5 hidden-sm']")  
	private WebElement txtResultStripView;
	
	@FindBy(xpath = "//form[@id='modifySearch']/div[1]//label[1]//span/input") // "trip-type-label-holder.active span[class='radio']>input")  
	private WebElement chkOneWay;
	
	@FindBy(xpath = "//form[@id='modifySearch']/div[1]//label[2]//span/input") // .trip-type-label-holder span[class='radio']")  
	private WebElement chkRoundTrip;
	
	@FindBy(xpath = "//form[@id='modifySearch']/div[1]//label[3]//span/input") //.trip-type-label-holder.multicity-tab span[class='radio']")  
	private WebElement chkMultiCity;
	
	@FindBy(css = "#origin_0")  
	private WebElement txtOrigin_ModifySearch;
	
	@FindBy(css = "#destination_0")  
	private WebElement txtDestination_ModifySearch;
	
	@FindBy(css = ".paxx-details>div:nth-child(1)>span[class='spin-count']>strong")  
	private WebElement txtAdult_ModifySearch;
	
	@FindBy(css = ".paxx-details>div:nth-child(2)>span[class='spin-count']>strong")  
	private WebElement txtChild_ModifySearch;
	
	@FindBy(css = ".paxx-details>div:nth-child(3)>span[class='spin-count']>strong")  
	private WebElement txtInfant_ModifySearch;
		
	@FindBy(css = "#flight_depart_date_0")  
	private WebElement txtDepartDate_ModifySearch;
	
	@FindBy(css = "#arrivalDate_0")  
	private WebElement txtReturnDate_ModifySearch;
	
	@FindBy(xpath = "//input[@id='BE_flight_non_stop']") 
	private WebElement txtNonStopFlights_ModifySearch;
	
	@FindBy(css = ".select-box-wrapper.fl>select>option[selected='selected']")  
	private WebElement txtPassengerClass_ModifySearch;
	
	@FindBy(css = ".select-box-wrapper.fl>select[class='ng-valid ng-dirty ng-valid-parse ng-touched']>option[selected='selected']")  
	private WebElement txtPreferredAirline_ModifySearch;
	
	@FindBy(css = "#origin_1")  
	private WebElement txtOrigin1_ModifySearch;

	@FindBy(css = "#destination_1")  
	private WebElement txtDestination1_ModifySearch;
	
	@FindBy(css = "#flight_depart_date_1")  
	private WebElement txtDepartDate1_ModifySearch;

	@FindBy(css = "div[class='matrix-slide-wrapper has-next-prev matrix-small-screen']")
	private WebElement weeklyFlightsStrip;
	
	@FindBy(css = "ul[class='matrix-slide-list tabs day-ul']>li>a[class='matrix-link tabs-link active']")
	private WebElement lnkCurrentDate_WeeklyMatrix;
	
	@FindBy(css = "ul[class='matrix-slide-list tabs day-ul']>li>a[class='matrix-link tabs-link active']>p[class='matrix-label matrix-price  uprcse']")
	private WebElement txtCurrentDateFare_WeeklyMatrix;
	
	@FindBy(css = "div[id='resultList_0']>div:nth-child(3)>div:nth-child(1) li[class='price'] p[class='fr']>label")
	private WebElement txtLowestFlightFare_AirlineMatix;
	
	@FindBy(css = ".ng-pristine.ng-valid.ng-touched>option")
	private WebElement drpPassengerClass;	
	
	@FindBy(css = ".matrix-link.txt-ac.tabs-link.active")
	private WebElement lnkArirlineMatrix;
	
	@FindBy(css = "ul[class='matrix-slide-list tabs matrix-ul']>li:nth-child(2) p:nth-child(3)[class='matrix-label uprcse']")
	private WebElement lnkArirlineMatrixFare;
	
	@FindBy (xpath="(//div[@id='resultBox']//div[@class='js-flightRow js-flightItem'][1]//span[contains(.,'Book Now')])[1]")
	private WebElement flightBookNow;	

	@FindBy(xpath="/html/body//*[@class='close close-icon']//*[@class='wewidgeticon we_close']")
	private WebElement btnCloseIframeNotification;
	
	@FindBy(css = "label[id*='fare']")
	private WebElement  fldContentFare;
	
	@FindBy(css = "div[ng-show='open_airline']>ul>li")
	private List<WebElement> selectAirlines;
	
	@FindBy(css = "i[class='ico ico-check']")
	private WebElement  chkSelectAirline;
	
	@FindBy(xpath="//iframe[@id='webklipper-publisher-widget-container-notification-frame']")
	private WebElement iFrameNotification;
	
	@FindBy(css = ".matrix-slide-list.tabs.matrix-ul")
	private WebElement lnkAirlineMatrixStrip;
	
	@FindBy(css = "ul[class='matrix-slide-list tabs matrix-ul']>li")  
	private WebElement lnkAirline;	
	
	@FindBy(css = "ul[class='matrix-slide-list tabs matrix-ul'] a[class='matrix-link tabs-link active'] p[class='matrix-label uprcse']")  
	private WebElement txtselectedAirlineName;
	
	@FindBy(xpath = "//label[@class='filter-label nowrap']//span[@class='clip-overflow']")
	private List<WebElement> txtAirlineName_AirlineFilters;
	
	@FindBy(css = "div[id='resultList_0'] div[class='fr leg-fare-cal']")  
	private WebElement lnkOnwardLFF;
	
	@FindBy(css = "div[id='resultList_1'] div[class='fr leg-fare-cal']")  
	private WebElement lnkReturnLFF;
	
	@FindBy(css = "div[id='resultList_1'] p[class='full title-tagline']")  
	private WebElement txtReturnLFF_TravelDetails;
	
	@FindBy(css = "div[id='resultList_0'] p[class='full title-tagline']")  
	private WebElement txtOnwardLFF_TravelDetails;
	
	@FindBy(css = "div[id='resultList_1'] h6[class='full']")  
	private WebElement txtCalender_ReturnLFF;
	
	@FindBy(css = "div[id='resultList_0'] h6[class='full']")  
	private WebElement txtCalender_OnwardLFF;
	
	
	
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

	public SearchResult(WebDriver driver, String url) {
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
	public SearchResult(WebDriver driver) {
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

		if (isPageLoaded && !(Utils.waitForElement(driver, btnModifySearchIcon))) {
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
	 * to click on Book now button in Round Trip for Domestic flights
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public ReviewPage clickOnBookNowInRound(int list1, int index1, int list2, int index2) throws Exception {
		WebElement e1 = driver.findElement(By.cssSelector(" div[id='resultBoxSlider']>div:nth-child(" + list1
				+ ")>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child(" + index1
				+ ")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='price']>div[class='full']>div>p[class='new-blue-button fr book-button']:not([class='ng-hide']"));
		WebElement e2 = driver.findElement(By.cssSelector(" div[id='resultBoxSlider']>div:nth-child(" + list2
				+ ")>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child(" + index2
				+ ")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='price']>div[class='full']>div>p[class='new-blue-button fr book-button']:not([class='ng-hide']"));

		BrowserActions.scrollToView(e1, driver);
		BrowserActions.clickOnElement(e1, driver, "To select Flight from one list.");
		BrowserActions.scrollToView(e2, driver);
		BrowserActions.clickOnElement(e2, driver, "To select Flight from second list.");

		BrowserActions.clickOnElement(btnBookNowRoundTrip, driver, "Click on Book Now for RoundTrip.");
		return new ReviewPage(driver).get();
	}

	/**
	 * to click on Book now button in OneWay Trip for International flights
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public ReviewPage clickOnBookNowINT() throws Exception {
		BrowserActions.scrollToView(btnBookNowINT, driver);
		BrowserActions.clickOnElement(btnBookNowINT, driver, "To click on Book now button.");
		return new ReviewPage(driver).get();
	}

	/**
	 * to click on Book now button in OneWay Trip for Domestic flights
	 * 
	 * @param index: pass even value :) example to select first flight pass 2, and for second pass 4 and so on..
	 * @return
	 * @throws Exception
	 */
	public ReviewPage clickOnBookNowInOneWay(int index) throws Exception {
		
		/*WebElement e = driver.findElement(By.cssSelector(
		"div[id='resultBoxSlider']>div>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child("
					+ index
				+ ")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='book-now']>div>p[class='new-blue-button fr book-button js-bookNow relative tc']"));
		*/
		closeINotificationAtTopSRP();
		
		WebElement wBookNow=driver.findElement(By.xpath("(//ul[@class='table-listing my-res-table']//li[@class='book-now']//p[@analytics='Flight Option|Book Now'])["+index+"]"));
		BrowserActions.scrollToView(wBookNow, driver);
		BrowserActions.clickOnElement(wBookNow, driver, "To click on Book now button.");
		return new ReviewPage(driver).get();
	}

	/**
	 * Getting the text from the flight Name on Preferred Flight
	 * 
	 * @return
	 * @throws Exception
	 */
	public String preferredFlightFirst() throws Exception {
		Utils.waitForElement(driver, chkChooseFlightFirst);
		BrowserActions.scrollToView(chkChooseFlightFirst, driver);
		BrowserActions.javascriptClick(chkChooseFlightFirst, driver, "Choosed Preferred Flight");
		Utils.waitForPageLoad(driver);
		String name = BrowserActions.getText(driver, preferredFlightName, "Flight Name");
		return name;
	}

	/**
	 * To click on Airline Matrix Strip
	 * 
	 * @throws Exception
	 */
	/*public void clickAirlineMatrix() throws Exception {
		BrowserActions.clickOnElement(matrixStrip, driver, "Airline Matrix Strip");
		Utils.waitForPageLoad(driver);

	}*/

	/**
	 * To click on Flight Link
	 * 
	 * @throws Exception
	 */
	public void clickOnFlightLink() throws Exception {
		BrowserActions.clickOnElement(lnkFlightDetails, driver, "Flight Link");
		Utils.waitForPageLoad(driver);

	}

	/*
	 * to click on Book now button in Multicity Trip for Domestic flights
	 * 
	 * @param index
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	//@Harveer- update this method try to find the element with better approach it will fail in future
	// for any issue discuss with me 
	public ReviewPage clickOnBookNowInMulticity(int list1, int index1, int list2, int index2) throws Exception {
		WebElement e1 = driver.findElement(By.cssSelector(" div[id='resultBoxSlider']>div:nth-child(" + list1
				+ ")>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child(" + index1
				+ ")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='price']>div[class='full']>p"));
		WebElement e2 = driver.findElement(By.cssSelector(" div[id='resultBoxSlider']>div:nth-child(" + list2
				+ ")>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child(" + index2
				+ ")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='price']>div[class='full']>p"));

		BrowserActions.scrollToView(e1, driver);
		BrowserActions.clickOnElement(e1, driver, "To select Flight from one list.");
		BrowserActions.scrollToView(e2, driver);
		BrowserActions.clickOnElement(e2, driver, "To select Flight from second list.");

		BrowserActions.clickOnElement(btnBookNowRoundTrip, driver, "Click on Book Now for RoundTrip.");
		return new ReviewPage(driver).get();
	}

	/**
	 * Getting the text from the flight Details PopUp
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFlightDetails() throws Exception {
		String txtDetails = BrowserActions.getText(driver, fldContentFlightDetails,
				"Getting text from the Flight Deatil pop Up");
		return txtDetails;

	}
	
	/**
	 * To click on Book now button in OneWay Trip for International flights
	 * 
	 * @throws Exception
	 */
	public void clickOnlnkFareandRule() throws Exception {
		BrowserActions.scrollToView(lnkFareSummaryandRules, driver);
		BrowserActions.clickOnElement(lnkFareSummaryandRules, driver, "Link Fare And Rule In Flight Detail Pop Up");

	}

	/**
	 * Getting the text from the Flight Fare and Rules Deatil pop Up
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFareDetailsandRuleInPopUp() throws Exception {
		String txtDetails = BrowserActions.getText(driver, fldContentFareDeatilAndRulesDetails,
				"Getting text from the Flight Fare and Rules Deatil pop Up");
		return txtDetails;

	}

	/**
	 * Getting the text from the Disclaimer Message In Pop up
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDisclamierMessage() throws Exception {
		String txtDetails = BrowserActions.getText(driver, txtDisclaimer,
				"Getting text from the Disclaimer Message In Pop up");
		return txtDetails;
	}

	/**
	 * to click on Flight Details Link in OneWay Trip for International flights
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public void clickOnlnkFlightDetails_INTL() throws Exception {		
		closeINotificationAtTopSRP();
		BrowserActions.nap(2);
		BrowserActions.scrollToView(lnkFlightDetails_INTL, driver);
		BrowserActions.clickOnElement(lnkFlightDetails_INTL, driver, "Link Flight Details For International One Way");

	}
	

	/**
	 * to click on Baggage Details Link in OneWay Trip for International flight
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public void clickOnlnkBaggage() throws Exception {
		BrowserActions.scrollToView(lnkBaggage, driver);
		BrowserActions.clickOnElement(lnkBaggage, driver, "Link Baggage Details");

	}

	/**
	 * Getting the text from the flight fare and rules Detail PopUp
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextBaggageDetails() throws Exception {
		String txtDetails = BrowserActions.getText(driver, fldContentBaggageDetail,
				"Getting text from the Baggage Details In Pop up");
		return txtDetails;
	}

	
	
	/**
	 * Getting the text from My Account in SRP page 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextMyAccount() throws Exception {
		Utils.waitForElement(driver, txtMyAccount);
		String myAccountGetTxt = BrowserActions.getText(driver,	txtMyAccount, "My Account Text Should be displayed in SRP Page");
		return myAccountGetTxt;
	}
	
	/**
	 * Getting the text from Support in SRP page 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSupport() throws Exception {
		Utils.waitForElement(driver, txtSupport);
		String supportGetTxt = BrowserActions.getText(driver, txtSupport, "Support Text Should be displayed in SRP Page");
		return supportGetTxt;
	}
	
	/**
	 * Getting the text from Special Deals in SRP page 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSplDeals() throws Exception {
		Utils.waitForElement(driver, txtSplDeals);
		String splDealsGetTxt = BrowserActions.getText(driver, txtSplDeals, "Special Deals Text Should be displayed in SRP Page");
		return splDealsGetTxt;
	}
	
	/**
	 * Getting the text from Recent Search in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextRecentSearch() throws Exception {
		Utils.waitForElement(driver, txtRecentSearch);
		String recentSearchGetTxt = BrowserActions.getText(driver, txtRecentSearch, "Recent Search Text Should be displayed in SRP Page");
		return recentSearchGetTxt;
	}
	
	/**
	 * Getting the text from Yatra Logo in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFromYatraLogo() throws Exception {
		Utils.waitForElement(driver, lnkYatraLogo);
		BrowserActions.mouseHover(driver, lnkYatraLogo);
		return (BrowserActions.getTextFromAttribute(driver, lnkYatraLogo, "title", "Yatra Logo title"));
	}

	/**
	 * To click Yatra Logo on Search Result page
	 * 
	 * @throws Exception
	 */
	public HomePage clickYatraLogo() throws Exception {			
		BrowserActions.actionClick(lnkYatraLogo, driver, "Yatra Logo");
		Utils.waitForPageLoad(driver);
		Log.event("Successfully clicked Yatra Logo link in SRP");
		//BrowserActions.nap(10);
		return new HomePage(driver).get();
		
	}
	
	/**
	 * Getting the text from User Account name in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextUserAcctName() throws Exception {
		//BrowserActions.mouseHover(driver, txtUserAcctName);
		Utils.waitForElement(driver, txtUserAcctName);
		String userNameGetTxt = BrowserActions.getText(driver,	txtUserAcctName, "User Name Search Text Should be displayed in SRP Page");
		return userNameGetTxt;
	}
	
	
	/**
	 * Getting the text from Login button in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextLogin() throws Exception {
		Utils.waitForElement(driver, txtLogin);
		String textLogintGetTxt = BrowserActions.getText(driver, txtLogin, "Login button Text Should be displayed in SRP Page");
		return textLogintGetTxt;
	}
	
	
	/**
	 * Getting the text from SignUp in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSignUp() throws Exception {
		Utils.waitForElement(driver, txtSignUp);
		String signUpGetTxt = BrowserActions.getText(driver, txtSignUp, "SignUp Text Should be displayed in SRP Page");
		return signUpGetTxt;
	}
	
	/**
	 * Getting the text from Corporate Login in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextCorporateLogin() throws Exception {
		Utils.waitForElement(driver, txtCorporateLogin);
		String corporateLoginGetTxt = BrowserActions.getText(driver, txtCorporateLogin, "Corporate Login Text Should be displayed in SRP Page");
		return corporateLoginGetTxt;
	}
	
	/**
	 * Getting the text from Agent Login in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextAgentLogin() throws Exception {
		Utils.waitForElement(driver, txtAgentLogin);
		String agentLoginGetTxt = BrowserActions.getText(driver, txtAgentLogin, "Agent Login Text Should be displayed in SRP Page");
		return agentLoginGetTxt;
	}
	
	/**
	 * Getting the text from My Bookings in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getMyBookings() throws Exception {
		Utils.waitForElement(driver, txtMyBookings);
		String myBookingsGetTxt = BrowserActions.getText(driver, txtMyBookings, "My Booking Text Should be displayed in SRP Page");
		return myBookingsGetTxt;
	}
	
	/**
	 * To mouse hover to My Account
	 */
	public void mouseOverMyAccount() {
		BrowserActions.nap(4);
		BrowserActions.mouseHover(driver, txtMyAccount);
	}
	
	
	/**
	 * Getting the text from Count of Flights 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCountofTotalFlights() throws Exception {
		Utils.waitForElement(driver, txtTotalFlightSearch);
		String totalFlightsGetTxt = BrowserActions.getText(driver, txtTotalFlightSearch, "Total No of Flight Should be displayed in SRP Page");
		return totalFlightsGetTxt;
	}
	
	/**
	 * Getting the text from Count of Flights 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCountofTotalFlightsAndDuration() throws Exception {
		Utils.waitForElement(driver, txtTotalFlightSearch);
		String totalFlightsGetTxt = BrowserActions.getText(driver, txtTotalFlightSearch, "Total No of Flight Should be displayed in SRP Page");
		String flightSearchDurationTxt = BrowserActions.getText(driver,	txtFlightSearchDuration, "Flight Search duration Should be displayed in SRP Page");
		String flightCountAndDuration =  totalFlightsGetTxt + " " +flightSearchDurationTxt;
		return flightCountAndDuration;
	}
	
	/**
	 * Getting the text from Source city in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSourceCity() throws Exception {
		Utils.waitForElement(driver, txtSourceCity);
		String sourceCityGetTxt = BrowserActions.getText(driver, txtSourceCity, "Source City Should be displayed in SRP Page");
		return sourceCityGetTxt;
	}
		

	/**
	 * Getting the text from Source date in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSourceDate() throws Exception {
		Utils.waitForElement(driver, txtSourceDate);
		String sourceDateGetTxt = BrowserActions.getText(driver, txtSourceDate, "Source date Should be displayed in SRP Page");
		return sourceDateGetTxt;
	}
	
	/**
	 * Getting the text from Destination City in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDestinationCity() throws Exception {
		Utils.waitForElement(driver, txtDestCity);
		String destCityGetTxt = BrowserActions.getText(driver, txtDestCity, "Destination City Should be displayed in SRP Page");
		return destCityGetTxt;
	}
	
	

	/**
	 * Getting the text from Destination Date in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDestinationDate() throws Exception {
		Utils.waitForElement(driver, txtDestDate);
		String destDateGetTxt = BrowserActions.getText(driver, txtDestDate, "Destination date Should be displayed in SRP Page");
		return destDateGetTxt;
	}

	/**
	 * Getting the text from FlightSearch duration in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getFlightSearchDuration() throws Exception {
		Utils.waitForElement(driver, txtFlightSearchDuration);
		String flightSearchDurationTxt = BrowserActions.getText(driver,	txtFlightSearchDuration, "Flight Search duration Should be displayed in SRP Page");
		return flightSearchDurationTxt;
	}
	
	
	/**
	 * Getting the text from start source city in SRP for MC
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextStartSourceCity() throws Exception {
		Utils.waitForElement(driver, txtStartSourceCity);
		String startSourceCityGetTxt = BrowserActions.getText(driver, txtStartSourceCity, "Start Source City Should be displayed in SRP for MC");
		return startSourceCityGetTxt;
	}
	
	
	/**
	 * Getting the text from start source date in SRP for MC
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextStartSourceDate() throws Exception {
		Utils.waitForElement(driver, txtStartSourceDate);
		String startSourceDateGetTxt = BrowserActions.getText(driver, txtStartSourceDate, "Start Source date Should be displayed in SRP for MC");
		return startSourceDateGetTxt;
	}
	
	
	/**
	 * Getting the text from start destination city in SRP for MC
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextStartDestCity() throws Exception {
		Utils.waitForElement(driver, txtstartDestCity);
		String startDestCityGetTxt = BrowserActions.getText(driver, txtstartDestCity, "Start Destination City Should be displayed in SRP for MC");
		return startDestCityGetTxt;
	}
		
	
	/**
	 * Getting the text from end source city in SRP for MC
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextEndSourceCity() throws Exception {
		Utils.waitForElement(driver, txtEndSourceCity);
		String endSourceCityGetTxt = BrowserActions.getText(driver, txtEndSourceCity, "End Source City Should be displayed in SRP for MC");
		return endSourceCityGetTxt;
	}
	
	
	/**
	 * Getting the text from end source date in SRP for MC
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextEndSourceDate() throws Exception {
		Utils.waitForElement(driver, txtEndSourceDate);
		String endSourceDateGetTxt = BrowserActions.getText(driver, txtEndSourceDate, "End Source date Should be displayed in SRP for MC");
		return endSourceDateGetTxt;
	}
	
	
	/**
	 * Getting the text from end destination city in SRP for MC
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextEndDestCity() throws Exception {
		Utils.waitForElement(driver, txtEndDestCity);
		String endDestCityGetTxt = BrowserActions.getText(driver, txtEndDestCity, "End Destination City Should be displayed in SRP for MC");
		return endDestCityGetTxt;
	}
	
	
	/**
	 * Getting the text from Flight duration in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFlightDuration() throws Exception {
		Utils.waitForElement(driver, txtFlightDuration);
		String flightDurationGetTxt = BrowserActions.getText(driver, txtFlightDuration, "Flight duration format Should be displayed in SRP Page");
		return flightDurationGetTxt;
	}
	
		
	/**
	 * Getting the text from Flight details PouUp duration in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFlightDetailsPouUpDuration() throws Exception {
		Utils.waitForElement(driver, txtFlightDetailsPopupDuration);
		String flightDetailsPouUpDurationGetTxt = BrowserActions.getText(driver, txtFlightDetailsPopupDuration, "Flight details popup duration format Should be displayed in SRP Page");
		return flightDetailsPouUpDurationGetTxt;
	}
	
	
	/**
	 * To click Flight Details link in SRP
	 * 
	 * @throws Exception
	 */
	public void clickFlightDetails() throws Exception {
		Utils.waitForElement(driver, lnkFlightDetail);
		BrowserActions.clickOnElement(lnkFlightDetail, driver, "Click Flight Details");
		Utils.waitForPageLoad(driver);
		Log.event("Clicked Flight Details link in SRP");
	}
	
	
	/**
	 * To click Flight Details pouUp close link
	 * 
	 * @throws Exception
	 */
	public void closeFlightDetailsPouUp() throws Exception {
		Utils.waitForElement(driver, lnkcloseFlightDetailsPopUp);
		BrowserActions.clickOnElement(lnkcloseFlightDetailsPopUp, driver, "Click Flight Details PopUp close button");
		Utils.waitForPageLoad(driver);
		Log.event("Clicked Flight Details PopUp close button");
	}
	
	
	/**
	 * Getting the text from Flight details PouUp duration in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextResultStrip() throws Exception {
		Utils.waitForElement(driver, txtResultStripView);
		String resultStripGetTxt = BrowserActions.getText(driver, txtResultStripView, "Result Strip view should be displayed in SRP ");
		return resultStripGetTxt;
	}
	
	
	/**
	 * Getting the text from Source city in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSourceCity_OW() throws Exception {
		Utils.waitForElement(driver, txtSourceCity);
		String sourceCityGetTxt = BrowserActions.getText(driver, txtSourceCity_OW, "Source City Should be displayed in SRP Page");
		return sourceCityGetTxt;
	}
		

	/**
	 * Getting the text from Source date in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSourceDate_OW() throws Exception {
		Utils.waitForElement(driver, txtSourceDate);
		String sourceDateGetTxt = BrowserActions.getText(driver, txtSourceDate_OW, "Source date Should be displayed in SRP Page");
		return sourceDateGetTxt;
	}
	
	
	/**
	 * Getting the text from Destination City in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDestinationCity_OW() throws Exception {
		Utils.waitForElement(driver, txtDestCity);
		String destCityGetTxt = BrowserActions.getText(driver, txtDestCity_OW, "Destination City Should be displayed in SRP Page");
		return destCityGetTxt;
	}
	
	
	/**
	 * To click Modify Search link in SRP
	 * 
	 * @throws Exception
	 */
	public void clickModifySearch() throws Exception {
		BrowserActions.nap(20);
		Utils.waitForElement(driver, btnModifySearchIcon);	
		BrowserActions.clickOnElement(btnModifySearchIcon, driver, "Click Modify Search");
		BrowserActions.nap(1);
		Utils.waitForPageLoad(driver);
		Log.event("Clicked Modify Search link in SRP");
	}
	
	
	/**
	 * To verify Trip Type in ModifySearch
	 * 
	 * @param String : type of Trip
	 * @throws Exception
	 */
	public boolean verifyTripTypeInModifySearch(String tripType) throws Exception {
		boolean status = false;
		if (tripType.equals(Constants.C_ONEWAY)) {
			status = BrowserActions.isRadioOrCheckBoxSelected(chkOneWay);		
			Log.event("Successfully verified One Way button is selected");
		} else if (tripType.equals(Constants.C_ROUNDTRIP)) {
			status = BrowserActions.isRadioOrCheckBoxSelected(chkRoundTrip);
			Log.event("Successfully verified Round Trip button is selected");
		} else if (tripType.equals(Constants.C_MULTICITY)) {
			status = BrowserActions.isRadioOrCheckBoxSelected(chkMultiCity);
			Log.event("Successfully verified Multicity button is selected");
		}		
		return status;
	}
	
	
	/**
	 * To verify Trip Type in ModifySearch
	 * 
	 * @param String : type of Trip
	 * @throws Exception
	 */
	public boolean selectTripTypeInModifySearch(String tripType) throws Exception {
		boolean status = false;
		if (tripType.equals(Constants.C_ONEWAY)) {
			status = BrowserActions.isRadioOrCheckBoxSelected(chkOneWay);		
			Log.event("Successfully selected One Way button");
		} else if (tripType.equals(Constants.C_ROUNDTRIP)) {
			status = BrowserActions.isRadioOrCheckBoxSelected(chkRoundTrip);
			Log.event("Successfully selected Round Trip button");
		} else if (tripType.equals(Constants.C_MULTICITY)) {
			status = BrowserActions.isRadioOrCheckBoxSelected(chkMultiCity);
			Log.event("Successfully selected Multicity button");
		}		
		return status;
	}
	
	
	
	/**
	 * Getting the text from Origin in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextOrigin_ModifySearch() throws Exception {
		Utils.waitForElement(driver, txtOrigin_ModifySearch);
		String origin_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtOrigin_ModifySearch, "ng-msvalidate", "Origin in Modify Search");
		return origin_ModifySearchGetTxt;
	}
	
	/**
	 * Getting the text from Destination in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDestination_ModifySearch() throws Exception {
		Utils.waitForElement(driver, txtDestination_ModifySearch);
		String destination_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtDestination_ModifySearch, "ng-msvalidate",  "Destination in Modify Search");
		return destination_ModifySearchGetTxt;
	}
	

	/**
	 * To verify Non Stop Flights Only check box is checked on unchecked
	 * 
	 * @throws Exception
	 */
	public boolean verifyNonStopFlightsChkBox_ModifySearch() throws Exception {
		boolean status = BrowserActions.isRadioOrCheckBoxSelected(txtNonStopFlights_ModifySearch);	 
		return status;
	}
	
		
	/**
	 * Getting the text from Origin in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextAdult_ModifySearch() throws Exception {
		Utils.waitForElement(driver, txtAdult_ModifySearch);
		String adultGetTxt = BrowserActions.getText(driver, txtAdult_ModifySearch, "Adult Should be displayed in Modify Search panel");
		return adultGetTxt;
	}
	
	/**
	 * Getting the text from Origin in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextChild_ModifySearch() throws Exception {
		Utils.waitForElement(driver, txtChild_ModifySearch);
		String childGetTxt = BrowserActions.getText(driver, txtChild_ModifySearch, "Child Should be displayed in Modify Search panel");
		return childGetTxt;
	}
	
	/**
	 * Getting the text from Origin in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextInfant_ModifySearch() throws Exception {
		Utils.waitForElement(driver, txtInfant_ModifySearch);
		String InfantGetTxt = BrowserActions.getText(driver, txtInfant_ModifySearch, "Infant Should be displayed in Modify Search panel");
		return InfantGetTxt;
	}
	
	/**
	 * Getting the text from Destination in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDepartDate_ModifySearch() throws Exception {
		Utils.waitForElement(driver, txtDepartDate_ModifySearch);
		String departDate_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtDepartDate_ModifySearch, "ng-active-date",  "DepartDate in Modify Search");
		return departDate_ModifySearchGetTxt;
	}
	
	/**
	 * Getting the text from Destination in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextReturnDate_ModifySearch() throws Exception {
		Utils.waitForElement(driver, txtReturnDate_ModifySearch);
		String returnDate_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtReturnDate_ModifySearch, "ng-active-date",  "ReturnDate in Modify Search");
		return returnDate_ModifySearchGetTxt;
	}
	
	/**
	 * Getting the text from Destination in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextPassengerClass_ModifySearch() throws Exception {
		Utils.waitForElement(driver, txtPassengerClass_ModifySearch);
		String passengerClass_ModifySearchGetTxt = BrowserActions.getText(driver, txtPassengerClass_ModifySearch, "Passenger class should be displayed in Modify Search panel");
		return passengerClass_ModifySearchGetTxt;
	}
	
	/**
	 * Getting the text from Destination in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextPreferredAirline_ModifySearch() throws Exception {
		Utils.waitForElement(driver, txtPreferredAirline_ModifySearch);
		String preferredAirline_ModifySearchGetTxt = BrowserActions.getText(driver, txtPreferredAirline_ModifySearch, "Preferred Airlins should be displayed in Modify Search panel");
		return preferredAirline_ModifySearchGetTxt;
	}
	
	/**
	 * Getting the text from Origin in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextOrigin1_ModifySearch() throws Exception {
		Utils.waitForElement(driver, txtOrigin1_ModifySearch);
		String origin_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtOrigin1_ModifySearch, "ng-msvalidate", "Origin_MC in Modify Search");
		return origin_ModifySearchGetTxt;
	}
	
	/**
	 * Getting the text from Destination in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDestination1_ModifySearch() throws Exception {
		Utils.waitForElement(driver, txtDestination1_ModifySearch);
		String destination_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtDestination1_ModifySearch, "ng-msvalidate",  "Destination_MC in Modify Search");
		return destination_ModifySearchGetTxt;
	}
	
	
	/**
	 * Getting the text from Destination in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDepartDate1_ModifySearch() throws Exception {
		Utils.waitForElement(driver, txtDepartDate1_ModifySearch);
		String departDate_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtDepartDate1_ModifySearch, "ng-active-date",  "DepartDate_MC in Modify Search");
		return departDate_ModifySearchGetTxt;
	}	

	/**
	 * To verify current date selection in Weekly Matrix
	 * 
	 * @throws Exception
	 */
	public boolean verifyCurrentDateSelectionInWeeklyMatrix() throws Exception {
		boolean status = false;
		if (lnkCurrentDate_WeeklyMatrix.isDisplayed()) {
			status = true;
		} else if (lnkCurrentDate_WeeklyMatrix.isSelected()) {
			status = true;
		} else {
			status = false;
		}
		return status;

	}

	/**
	 * Getting the text from Current date Fare in Weekly Matrix
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextCurrentDateFareInWeeklyMatrix() throws Exception {
		Utils.waitForElement(driver, txtCurrentDateFare_WeeklyMatrix);
		String currentDateFare_WeeklyMatrixGetTxt = BrowserActions.getText(driver, txtCurrentDateFare_WeeklyMatrix,
				"Current Date should be displayed in Weekly Matrix");
		return currentDateFare_WeeklyMatrixGetTxt;
	}
	
	/**
	 * Getting the text from Lowest Flight Fare in Airline Matrix
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextLowestFlightFareInAirlineMatrix() throws Exception {
		Utils.waitForElement(driver, txtLowestFlightFare_AirlineMatix);
		String lowestFlightFareGetTxt = BrowserActions.getText(driver, txtLowestFlightFare_AirlineMatix," Lowest Flight fare should be displayed in Airline Matrix");
		return lowestFlightFareGetTxt;
	}	
	
	/**
	 * Getting the text from Passenger class Drop down in Modify Search panel
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getPassengerClasssDetailsInMofifySearch() throws Exception {		
		List<String> passengerclassList = new ArrayList<String>();		
		List<WebElement> passengerClassList1 = driver.findElements(By.xpath("//form[@id='modifySearch']//option[@ng-repeat='key in className']"));
		for (int i = 0; i < passengerClassList1.size(); i++) {
			String passengerClass = passengerClassList1.get(i).getText().toString().trim();
			passengerclassList.add(passengerClass);
		}
		Log.event("Modify Search Passenger Class drop down details list : "+ passengerclassList);
		return passengerclassList;
	}
	
	/**
	 * To verify Non Stop Flights Only check box is checked on unchecked
	 * 
	 * @throws Exception
	 */
	public boolean verifyAllAirlineMatrixSelection() throws Exception {
		boolean status = false;		
		if (lnkArirlineMatrix.isDisplayed()) {
			status = true;
		} else 
		if (lnkArirlineMatrix.isSelected()){
			status = true;
		}else {		
			status = false;
		}
		return status;			
	}

	/**
	 * Getting the text Airline Matrix fare details
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getAirlineMatrixFareDetails() throws Exception {		
		List<String> airlineMatrixFareDetailsList = new ArrayList<String>();		
		for (int i = 1; i < lnkAirlineMatrix.size(); i++) {
			WebElement airlineFareDetails = driver.findElement(By.cssSelector("ul[class='matrix-slide-list tabs matrix-ul'] li:nth-child("+i+") p:nth-child(3)[class='matrix-label uprcse']"));
			String airlineFare = airlineFareDetails.getText().toString().trim();
			airlineMatrixFareDetailsList.add(airlineFare);
		}
		Log.event("Airline Matrix fare details : "+ airlineMatrixFareDetailsList);
		return airlineMatrixFareDetailsList;
	}
	
	/**
	 * to select the Airlines by Name
	 * @param BankName
	 * @throws Exception
	 */
	public void selectAirline(String AirlinesName) throws Exception {
		for (WebElement e : selectAirlines) {
			if (e.findElement(By.cssSelector("label>span[class='clip-overflow']")).getText().equalsIgnoreCase(AirlinesName)) {
				BrowserActions.clickOnElement(e.findElement(By.cssSelector("label>span[class='clip-overflow']")), driver, "Selected Airline");
				break;
				}
			}
		}
	
	/**
	 * Description: to close notification which display randomly on SRP page
	 * @throws Exception 
	 */
	public void closeINotificationAtTopSRP() throws Exception {			
		boolean boolFrameNotification = BrowserActions.isElementPresent(driver, iFrameNotification);
		if (boolFrameNotification == true) {
			BrowserActions.nap(2);
			BrowserActions.switchToIframe(driver, iFrameNotification);
			BrowserActions.nap(2);
			BrowserActions.clickOnElement(btnCloseIframeNotification, driver, "Button to close Iframe Notification at top on SRP");
			BrowserActions.switchToDefault(driver);					
		} else {
			Log.event("Not displayed Iframe Notification at top on SRP ");				
		}		
	}
	
	/**
	 * Getting the text Airline Matrix fare details
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getAirlineNamesInMatrix() throws Exception {		
		List<String> airlineNamesInMatrix = new ArrayList<String>();		
		for (int i = 1; i < lnkAirlineMatrix.size(); i++) {
			WebElement airlineNamesDetails = driver.findElement(By.cssSelector("ul[class='matrix-slide-list tabs matrix-ul'] li:nth-child("+i+") p[class='matrix-label uprcse']"));
			String airlineNames = airlineNamesDetails.getText().toString().trim();
			airlineNamesInMatrix.add(airlineNames);
		}
		Log.event("Airline Matrix fare details : "+ airlineNamesInMatrix);
		return airlineNamesInMatrix;
	}
	
	/**
	 * Getting the text Airline Matrix fare details
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyAirlinelogoInMatrix() throws Exception {
		boolean status = false;
		for (int i = 1; i < lnkAirlineMatrix.size(); i++) {
			WebElement airlineNamesDetails = driver.findElement(By.cssSelector("ul[class='matrix-slide-list tabs matrix-ul'] li:nth-child(" + i	+ ") p[class='matrix-airline-logo']"));
			if (airlineNamesDetails.isDisplayed()) {
				status = true;
			} else {
				status = false;
				break;
			}
		}
		return status;
	}
	

	/**
	 * To click Flight Details pouUp close link
	 * 
	 * @throws Exception
	 */
	public void clickAirlineInAirlineMatrix() throws Exception {
		Utils.waitForElement(driver, lnkAirline);
		BrowserActions.clickOnElement(lnkAirline, driver, "Click Airline in Airline Matrix - RT");
		Utils.waitForPageLoad(driver);
		Log.event("Click Airline in Airline Matrix");
	}
	
	/**
	 * Getting the text from Corporate Login in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSelectedAirlineName() throws Exception {
		Utils.waitForElement(driver, txtselectedAirlineName);
		String airlineNameGetTxt = BrowserActions.getText(driver, txtselectedAirlineName, "Selected Airline Name Text Should be displayed on Airline Matrix in SRP Page");
		return airlineNameGetTxt;
	}
	
	/**
	 * To verify Non Stop Flights Only check box is checked on unchecked
	 * 
	 * @throws Exception
	 */
	public boolean verifySelectedAirlineInAirlineFilters1() throws Exception {
		boolean status = BrowserActions.isRadioOrCheckBoxSelected(txtNonStopFlights_ModifySearch);	 
		return status;
	}
	
	/**
	 * Getting the text Airline Matrix fare details
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifySelectedAirlineInAirlineFilters(String airlineName) throws Exception {
		boolean status = false;
		for (int i = 1; i < txtAirlineName_AirlineFilters.size(); i++) {
			WebElement airlineFareDetails = driver.findElement(By.cssSelector("div[ng-show='open_airline'] li:nth-child("+i+") span[class='clip-overflow']"));
			String airline = airlineFareDetails.getText().toString().trim();			
			if (airlineName.equalsIgnoreCase(airline)){				
				WebElement chkAirline = driver.findElement(By.cssSelector("div[ng-show='open_airline'] li:nth-child("+i+") span[class='checkbox']>input"));
				status = BrowserActions.isRadioOrCheckBoxSelected(chkAirline);
				break;
			}
		}
		return status;
	}
	
	/**
	 * To verify Non Stop Flights Only check box is checked on unchecked
	 * 
	 * @throws Exception
	 */
	public boolean verifyOnwardAndReturnLFF(String LFF) throws Exception {
		boolean status = false;
		if (LFF == "OLFF") {
			boolean boolOnwardLFF = BrowserActions.isElementPresent(driver, lnkOnwardLFF);
			if (boolOnwardLFF == true) {
				status = true;
			}
		} else if (LFF == "RLFF") {
			boolean boolReturnLFF = BrowserActions.isElementPresent(driver, lnkReturnLFF);
			if (boolReturnLFF == true) {
				status = true;
			}
		}
		return status;
	}
	
	/**
	 * To click Flight Details pouUp close link
	 * 
	 * @throws Exception
	 */
	public void clickOnwardAndReturnLFF(String LFF) throws Exception {
		if (LFF == "OLFF") {
			Utils.waitForElement(driver, lnkOnwardLFF);
			BrowserActions.clickOnElement(lnkOnwardLFF, driver, "Click onward LFF - RT");
			//Utils.waitForPageLoad(driver);
			Log.event("Click onward LFF");
		} else if (LFF == "RLFF") {
			Utils.waitForElement(driver, lnkReturnLFF);
			BrowserActions.clickOnElement(lnkReturnLFF, driver, "Click Return LFF - RT");
			//Utils.waitForPageLoad(driver);
			Log.event("Click Return LFF");
		}
	}
	
	/**
	 * To click Flight Details pouUp close link
	 * 
	 * @throws Exception
	 */
	public String getTextOnwardAndReturnLFF(String LFF) throws Exception {
		String textLFF = null;
		if (LFF == "OLFF") {
			Utils.waitForElement(driver, txtOnwardLFF_TravelDetails);
			textLFF = BrowserActions.getText(driver, txtOnwardLFF_TravelDetails, "Onward LFF Travel details text should be displayed");
		} else if (LFF == "RLFF") {
			Utils.waitForElement(driver, txtReturnLFF_TravelDetails);
			textLFF = BrowserActions.getText(driver, txtReturnLFF_TravelDetails, "Return LFF Travel details text should be displayed");
		}
		return textLFF;
	}
	
	/**
	 * To click Flight Details pouUp close link
	 * 
	 * @throws Exception
	 */
	public String getTextCalenderInOnwardAndReturnLFF(String LFF) throws Exception {
		String textLFF = null;
		if (LFF == "OLFF") {
			Utils.waitForElement(driver, txtOnwardLFF_TravelDetails);
			textLFF = BrowserActions.getText(driver, txtCalender_OnwardLFF, "Onward LFF Calender text should be displayed");
		} else if (LFF == "RLFF") {
			Utils.waitForElement(driver, txtReturnLFF_TravelDetails);
			textLFF = BrowserActions.getText(driver, txtCalender_ReturnLFF, "Return LFF Calender text should be displayed");
		}
		return textLFF;
	}

	//iv[id='resultList_0'] h6[class='full']

  //*******************************End of SRP Functions********************************************************************************************

} // SearchResult
