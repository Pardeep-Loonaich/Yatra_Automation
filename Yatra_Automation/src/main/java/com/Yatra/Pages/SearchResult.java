package com.Yatra.Pages;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.BrowserType;
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

import bsh.util.Util;

public class SearchResult extends LoadableComponent<SearchResult> {

	private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Search Result Page
	 * ***********************************
	 **********************************************************************************************/
	// @Harveer- 1- make all element private
	// 2- remove all c commented code if it is not required.
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

	@FindBy(xpath = "//article[@class='full result-card animation-on item-0']//p[@class='new-blue-button .js-bookNow book-btn relative tc']") // div[class='fr
																																				// pr
																																				// partial-pay']
																																				// p:nth-child(1)
	private WebElement btnBookNowINT_New; //

	@FindBys({

			@FindBy(css = "div[ng-controller='productFareDetailsController']") })
	private List<WebElement> el;
	private List<WebElement> moduleFareDetails;

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
	
	@FindBy(css = "p[class='new-blue-button book-btn fd-btn']")
	private WebElement btnBookNowFlightDeatilPopUp_INTL;
	
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

	@FindBy(css = "a[title='My Bookings']")
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

	@FindBy(xpath = "//form[@id='modifySearch']/div[1]//label[1]//span/input") // "trip-type-label-holder.active
																				// span[class='radio']>input")
	private WebElement chkOneWay;

	@FindBy(xpath = "//form[@id='modifySearch']/div[1]//label[2]//span/input") // .trip-type-label-holder
																				// span[class='radio']")
	private WebElement chkRoundTrip;

	@FindBy(xpath = "//form[@id='modifySearch']/div[1]//label[3]//span/input") // .trip-type-label-holder.multicity-tab
																				// span[class='radio']")
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

	@FindBy(xpath = "(//div[@id='resultBox']//div[@class='js-flightRow js-flightItem'][1]//span[contains(.,'Book Now')])[1]")
	private WebElement flightBookNow;

	@FindBy(xpath = "/html/body//*[@class='close close-icon']//*[@class='wewidgeticon we_close']")
	private WebElement btnCloseIframeNotification;

	@FindBy(css = "label[id*='fare']")
	private WebElement fldContentFare;

	@FindBy(css = "div[ng-show='open_airline']>ul>li")
	private List<WebElement> selectAirlines;

	@FindBy(css = "i[class='ico ico-check']")
	private WebElement chkSelectAirline;

	/*
	 * @FindBy(xpath=
	 * "//iframe[@id='webklipper-publisher-widget-container-notification-frame']")
	 * private WebElement iFrameNotification;
	 */

	@FindBy(css = ".matrix-slide-list.tabs.matrix-ul")
	private WebElement lnkAirlineMatrixStrip;
	@FindBy(css = "ul[class='matrix-slide-list tabs matrix-ul']>li")
	private WebElement lnkAirline;

	@FindBy(css = "ul[class='matrix-slide-list tabs matrix-ul'] a[class='matrix-link tabs-link active'] p[class='matrix-label uprcse']")
	private WebElement txtselectedAirlineName;

	@FindBy(xpath = "//label[@class='filter-label nowrap']//span[@class='clip-overflow']")
	private List<WebElement> txtAirlineName_AirlineFilters;

	@FindBy(xpath = "//div[@ng-show='open_stop']//span[@class='full bxs txt-ac']")
	private List<WebElement> lnkSops_Filters;

	@FindBy(css = "div[id='resultList_0'] a[datep-obj='findLowesFare']")
	private WebElement lnkOnwardLFF;

	@FindBy(css = "div[id='resultList_1'] a[datep-obj='findLowesFare']")
	private WebElement lnkReturnLFF;

	@FindBy(css = "div[id='resultList_1'] p[class='full title-tagline']")
	private WebElement txtReturnLFF_TravelDetails;

	@FindBy(css = "div[id='resultList_0'] p[class='full title-tagline']")
	private WebElement txtOnwardLFF_TravelDetails;

	@FindBy(css = "div[id='resultList_1'] h6[class='full']")
	private WebElement txtCalender_ReturnLFF;

	@FindBy(css = "div[id='resultList_0'] h6[class='full']")
	private WebElement txtCalender_OnwardLFF;

	@FindBy(css = "i[class='ico ico-fare-cal-fixed']")
	private WebElement lnkFareAlert;

	@FindBy(css = "div[id='fareAlertPopup'] span[class='bold']")
	private WebElement txtFareAlertTitle;

	@FindBy(css = "input[class='new-blue-button search-btn']")
	private WebElement btnSetAlert_FareAlert;

	@FindBy(css = "form[id='fare-alert-form'] input[name='origin']")
	private WebElement txtOrigin_FareAlert;

	@FindBy(css = "form[id='fare-alert-form'] input[name='destination']")
	private WebElement txtDestination_FareAlert;

	@FindBy(css = "form[id='fare-alert-form'] li[class='search-limit'] a")
	private WebElement lnkExactDates_FareAlert;

	@FindBy(css = "form[id='fare-alert-form'] li[class='active']>a")
	private WebElement lnkDays_FareAlert;

	@FindBy(css = "form[id='fare-alert-form'] input[name='departDate']")
	private WebElement txtDepartureDate_FareAlert;

	@FindBy(css = "form[id='fare-alert-form'] input[name='trackPrice']")
	private WebElement txtMaxPrice_FareAlert;

	@FindBy(css = "form[id='fare-alert-form'] input[name='email']")
	private WebElement txtEmail_FareAlert;

	@FindBy(css = "form[id='fare-alert-form'] select[name='mobileISD']")
	private WebElement drpMobileSTD_FareAlert;

	@FindBy(css = "form[id='fare-alert-form'] input[name='mobile']")
	private WebElement txtMobile_FareAlert;

	@FindBy(css = "div[id='fareAlertPopup'] p[class='text-success tc']")
	private WebElement txtFareAlertMessage;

	@FindBy(css = "ul[class='tab fs-md']>li:not([class='active'])>a[yatratrackable='Flights|Search|flight_details|FareSummary']")
	private WebElement lnkFareAndSummaryFlightDetail;

	@FindBy(css = "ul[class='tab fs-md']>li:nth-child(3)>a")
	private WebElement lnkBaggageFlightDetail;

	@FindBy(css = "div[class='row baggage-summary']")
	private WebElement txtBaggageInfoFlightDetail;

	@FindBy(css = "label[title='Refine By Price']")
	private WebElement txtPriceFliter;

	@FindBy(css = "label[for='filter-depart-time']>span")
	private WebElement txtDepartTimeFilter;

	@FindBy(css = "label[title='Refine By Stops']")
	private WebElement txtStopsFilter;

	@FindBy(css = "label[title='Refine By Fare Type']")
	private WebElement txtFareTypeFilter;

	@FindBy(css = "label[title='Refine By Airlines']>span")
	private WebElement txtAirlinesFilter;

	@FindBy(css = "span[ng-checked='filter[getLegKey()].stops[0]']")
	private WebElement lnkNonStopFlights;

	@FindBy(css = "li[class='fl ml10 mobi-hide pad-hide']")
	private WebElement lnkShareItinerary;

	@FindBy(css = "div[class='full input-holder'] input[name='emails']")
	private WebElement txtEmail_ShareItinerary;

	@FindBy(css = "div[class='results-wrapper'] div[class='results']>article:nth-child(1) span[class='checkbox']")
	private WebElement chkItinerary_ShareItinerary;

	@FindBy(xpath = "//div[@class='full input-holder']/textarea[@name='message']")
	private WebElement txtMessage_ShareItinerary;

	@FindBy(css = "li[class='fl ml10 mobi-hide pad-hide'] span")
	private WebElement txtShareItineraryTooltipText;

	@FindBy(css = "div[class='full mt20'] button[type='submit']")
	private WebElement txtShareButton_ShareItinerary;

	@FindBy(css = "div[class='overlay-content']>p[class='text-success tc']")
	private WebElement txtPopupMessage_ShareItinerary;

	@FindBy(css = "div[id='resultList_0'] a[class='prev-day']")
	private WebElement lnkPrevDay_OnwardLeg;

	@FindBy(css = "div[id='resultList_0'] a[class='next-day']")
	private WebElement lnkNextDay_OnwardLeg;

	@FindBy(css = "div[id='resultList_1'] a[class='prev-day']")
	private WebElement lnkPrevDay_ReturnLeg;

	@FindBy(css = "div[id='resultList_1'] a[class='next-day']")
	private WebElement lnkNextDay_ReturnLeg;

	@FindBy(css = "#signInBtn")
	private WebElement btnSignIn;

	@FindBy(css = "div[class='menu']>div>ul>li[id='userSignInStrip']>a")
	private WebElement btnMyAccount;

	@FindBy(css = "div[id='rc-notify']")
	private WebElement txtRecentSearchPopUp;

	@FindBy(css = "div[class='menu']>div>ul>li[id='recentSearch-wrapper']>a")
	private WebElement btnRecentSearch;

	@FindBy(css = "div[class='recent-iternary-price']>div>div[class='relative']>span")
	private WebElement lnkCloseInRecentSearch;

	@FindBy(css = "div[class='fare-cal-fixed']>i")
	private WebElement lnkFareCall;

	@FindBy(css = "input[name='trackPrice']")
	private WebElement txtFldMaxAmount;

	@FindBy(css = "input[name='email']")
	private WebElement txtFldEmail;

	@FindBy(css = ".mobile-input.ng-pristine.ng-untouched.ng-invalid.ng-invalid-required.ng-valid-pattern.ng-valid-minlength.ng-valid-maxlength")
	private WebElement txtFldPhoneNumber;

	@FindBy(css = "li>p[class='error-msg']")
	private WebElement errorMessageFromFareDetailPopUP;

	@FindBy(css = "li[class='mr22']>p[class='error-msg']")
	private WebElement errorMessageEmailFromFareDetailPopUP;

	@FindBy(css = "li[class='mobile-details']>p[class='error-msg']")
	private WebElement errorMessagePhoneNumberFromFareDetailPopUP;

	@FindBy(xpath = "//div[@class='overlay no-pad flight-details-overlay']/div[@class='overlay-header']/span[@class='ico ico-close overlay-close']")
	private WebElement closeInFlightDetailPopUp;

	@FindBy(css = "span[class='ml5 hidden-sm']")
	private WebElement btnViewed;

	@FindBy(css = "p[class='full fs-11 ltr-gray three-dot mt2 fly-stop']")
	private WebElement txtNoOfStops;

	@FindBy(css = "div[class='yatraSecure']")
	private WebElement yatraFooterPanel;

	@FindBy(css = "div[class='up-scroll ico ico-up-arrow-white']")
	private WebElement btnScrollUpSRP;

	@FindBy(css = "div[class='js-flightItem']>article>div[class='full result-card-content']>ul>li[class='result-grid-airline hidden-sm']>div>p[class='full airline-name']")
	private List<WebElement> lstflightNameSRP;

	@FindBy(xpath = "//div[@class='reviewSrch btnSearchBook js_review_search_btn'][1]")
	private WebElement flightDetailsInRecentSearch;

	@FindBy(css = "ul[class='full city-details tripO']>li:nth-child(1)>p[class*='city-name']")
	private WebElement txtOriginName;

	@FindBy(css = "ul[class='full city-details tripO']>li:nth-child(2)>p[class*='city-name']")
	private WebElement txtDestinationName;

	@FindBy(css = "p[class='full txt-ar text-error fs-xs line-hn mt5']")
	private WebElement txtSeatLeftMessage;

	@FindBy(css = "h3[class='trip-price fr']>span[class='fs-11 text-error block tr mb5']")
	private WebElement txtSeatLeftMessageInFlightDetails;

	@FindBy(css = "Span[class='fs-13 block mb20']")
	private WebElement txterrorMessageNoFlights;

	@FindBy(css = "span[class='ecash-amount']")
	private WebElement txtEcashEarned;

	@FindBy(css = "input[id='origin_0']")
	private WebElement txtOriginModifySearch;
	
	@FindBy(css = "input[id='destination_0']")
	private WebElement txtDestinationModifySearch;
	
	@FindBy(css = "form[id='modifySearch']>div>input")
	private WebElement btnSearchModifySearch;
	
	@FindBy(css = "li[class='simple-dropdown login-li clearfix']>span")
	private WebElement txtUserSignUp;

	@FindBy(css = "span[id='userShowName']")
	private WebElement txtUserNameOnHeader;
	
	@FindBy(css = "div[id='book-now-tooltip']>div")
	private WebElement txtContinueWithSearchMessage;
	
	@FindBy(css = "div[ng-show='open_ftype'] input[ yatratrackable='Flights|Search|filter - fare_type|refundable']")
	private WebElement chkRefundable;
	
	@FindBy(xpath = "//div[@class='js-flightRow js-flightItem']//li[@ng-if='flt.ft==1']")
	private List<WebElement> lstRefundableFlights;
	
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
		closeINotificationAtTopSRP();
		BrowserActions.scrollToView(btnBookNowINT, driver);
		BrowserActions.clickOnElement(btnBookNowINT, driver, "To click on Book now button.");
		return new ReviewPage(driver).get();
	}

	/**
	 * to click on Book now button in OneWay Trip for Domestic flights
	 * 
	 * @param index:
	 *            pass even value :) example to select first flight pass 2, and
	 *            for second pass 4 and so on..
	 * @return
	 * @throws Exception
	 */
	public ReviewPage clickOnBookNowInOneWay(int index) throws Exception {
		closeINotificationAtTopSRP();
		WebElement wBookNow = driver.findElement(By.xpath("(//div[@data-gaeclist='Search Results Page'])[" + index
				+ "]//li[@class='book-now']//p[@yatratrackable='Flights|Search|Book Type|Book Now']"));
		BrowserActions.scrollToView(wBookNow, driver);
		BrowserActions.nap(2);
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
	/*
	 * public void clickAirlineMatrix() throws Exception {
	 * BrowserActions.clickOnElement(matrixStrip, driver, "Airline Matrix Strip"
	 * ); Utils.waitForPageLoad(driver); }
	 */

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
	// @Harveer- update this method try to find the element with better approach
	// it will fail in future
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
		String myAccountGetTxt = BrowserActions.getText(driver, txtMyAccount,
				"My Account Text Should be displayed in SRP Page");
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
		String supportGetTxt = BrowserActions.getText(driver, txtSupport,
				"Support Text Should be displayed in SRP Page");
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
		String splDealsGetTxt = BrowserActions.getText(driver, txtSplDeals,
				"Special Deals Text Should be displayed in SRP Page");
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
		String recentSearchGetTxt = BrowserActions.getText(driver, txtRecentSearch,
				"Recent Search Text Should be displayed in SRP Page");
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
		// BrowserActions.mouseHover(driver, lnkYatraLogo); //FF issue
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
		// BrowserActions.nap(10);
		return new HomePage(driver).get();

	}

	/**
	 * Getting the text from User Account name in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextUserAcctName() throws Exception {
		// BrowserActions.mouseHover(driver, txtUserAcctName);
		Utils.waitForElement(driver, txtUserAcctName);
		String userNameGetTxt = BrowserActions.getText(driver, txtUserAcctName,
				"User Name Search Text Should be displayed in SRP Page");
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
		String textLogintGetTxt = BrowserActions.getText(driver, txtLogin,
				"Login button Text Should be displayed in SRP Page");
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
		String corporateLoginGetTxt = BrowserActions.getText(driver, txtCorporateLogin,
				"Corporate Login Text Should be displayed in SRP Page");
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
		String agentLoginGetTxt = BrowserActions.getText(driver, txtAgentLogin,
				"Agent Login Text Should be displayed in SRP Page");
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
		String myBookingsGetTxt = BrowserActions.getText(driver, txtMyBookings,
				"My Booking Text Should be displayed in SRP Page");
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
		String totalFlightsGetTxt = BrowserActions.getText(driver, txtTotalFlightSearch,
				"Total No of Flight Should be displayed in SRP Page");
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
		String totalFlightsGetTxt = BrowserActions.getText(driver, txtTotalFlightSearch,
				"Total No of Flight Should be displayed in SRP Page");
		String flightSearchDurationTxt = BrowserActions.getText(driver, txtFlightSearchDuration,
				"Flight Search duration Should be displayed in SRP Page");
		String flightCountAndDuration = totalFlightsGetTxt + " " + flightSearchDurationTxt;
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
		String sourceCityGetTxt = BrowserActions.getText(driver, txtSourceCity,
				"Source City Should be displayed in SRP Page");
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
		String sourceDateGetTxt = BrowserActions.getText(driver, txtSourceDate,
				"Source date Should be displayed in SRP Page");
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
		String destCityGetTxt = BrowserActions.getText(driver, txtDestCity,
				"Destination City Should be displayed in SRP Page");
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
		String destDateGetTxt = BrowserActions.getText(driver, txtDestDate,
				"Destination date Should be displayed in SRP Page");
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
		String flightSearchDurationTxt = BrowserActions.getText(driver, txtFlightSearchDuration,
				"Flight Search duration Should be displayed in SRP Page");
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
		String startSourceCityGetTxt = BrowserActions.getText(driver, txtStartSourceCity,
				"Start Source City Should be displayed in SRP for MC");
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
		String startSourceDateGetTxt = BrowserActions.getText(driver, txtStartSourceDate,
				"Start Source date Should be displayed in SRP for MC");
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
		String startDestCityGetTxt = BrowserActions.getText(driver, txtstartDestCity,
				"Start Destination City Should be displayed in SRP for MC");
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
		String endSourceCityGetTxt = BrowserActions.getText(driver, txtEndSourceCity,
				"End Source City Should be displayed in SRP for MC");
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
		String endSourceDateGetTxt = BrowserActions.getText(driver, txtEndSourceDate,
				"End Source date Should be displayed in SRP for MC");
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
		String endDestCityGetTxt = BrowserActions.getText(driver, txtEndDestCity,
				"End Destination City Should be displayed in SRP for MC");
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
		String flightDurationGetTxt = BrowserActions.getText(driver, txtFlightDuration,
				"Flight duration format Should be displayed in SRP Page");
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
		String flightDetailsPouUpDurationGetTxt = BrowserActions.getText(driver, txtFlightDetailsPopupDuration,
				"Flight details popup duration format Should be displayed in SRP Page");
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
		String resultStripGetTxt = BrowserActions.getText(driver, txtResultStripView,
				"Result Strip view should be displayed in SRP ");
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
		BrowserActions.nap(10);
		Utils.waitForElement(driver, btnModifySearchIcon);	
		BrowserActions.clickOnElement(btnModifySearchIcon, driver, "Click Modify Search");
		BrowserActions.nap(3);
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
	 * @return
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
		//boolean boolFrameNotification = BrowserActions.isElementPresent(driver, iFrameNotification);
		if (driver.findElements(By.xpath("//iframe[@id='webklipper-publisher-widget-container-notification-frame']")).size()>0) 
		{
			WebElement iFrameNotification=driver.findElement(By.xpath("//iframe[@id='webklipper-publisher-widget-container-notification-frame']"));
			BrowserActions.switchToIframe(driver, iFrameNotification);
			BrowserActions.nap(2);
			BrowserActions.clickOnElement(btnCloseIframeNotification, driver, "Button to close Iframe Notification at top on SRP");
			BrowserActions.switchToDefault(driver);					
		} else {
			Log.event("Not displayed Iframe Notification at top on SRP ");				
		}		
	}
	
	/**
	 * Getting the text form Airline Names 
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
	 * To Verify Airline Logos in Airline Matrix
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
	 * To click Airline in Airline Matrix
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
	 * Getting the text from Selected Airline Name in Airline Matrix  
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
	 * To verify selection on particular Airline in Airline Filters 
	 * @param airlineName : Airline Name
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
	 * To verify Onward and Return LFF is displayed
	 * @param LFF : Lowest Fare Finder calendar
	 * @return
	 * @throws Exception
	 */
	public boolean verifyOnwardAndReturnLFF(String LFF) throws Exception {
		boolean status = false;
		if (LFF == "OLFF") {
			Utils.waitForElement(driver, lnkOnwardLFF);
			boolean boolOnwardLFF = BrowserActions.isElementPresent(driver, lnkOnwardLFF);
			if (boolOnwardLFF == true) {
				status = true;
			}
		} else if (LFF == "RLFF") {
			Utils.waitForElement(driver, lnkReturnLFF);
			boolean boolReturnLFF = BrowserActions.isElementPresent(driver, lnkReturnLFF);
			if (boolReturnLFF == true) {
				status = true;
			}
		}
		return status;
	}
	
	/**
	 * To click Onward and Return LFF calendar link
	 * @param LFF : Lowest Fare Finder calendar
	 * @throws Exception
	 */
	public void clickOnwardAndReturnLFF(String LFF) throws Exception {
		if (LFF == "OLFF") {
			Utils.waitForElement(driver, lnkOnwardLFF);
			BrowserActions.clickOnElement(lnkOnwardLFF, driver, "Click onward LFF - RT");			
			Log.event("Click onward LFF");
		} else if (LFF == "RLFF") {
			Utils.waitForElement(driver, lnkReturnLFF);
			BrowserActions.clickOnElement(lnkReturnLFF, driver, "Click Return LFF - RT");			
			Log.event("Click Return LFF");
		}
	}
	
	/**
	 * To Getting text from calendar Travel Details in LFF
	 * @param LFF : Lowest Fare Finder calendar
	 * @throws Exception
	 */
	public String getTextTravelDetailsInOnwardAndReturnLFF(String LFF) throws Exception {
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
	 * Getting text from calendar in LFF
	 * @param LFF : Lowest Fare Finder calendar
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

	
	/**
	 * To click Fare Alert pop up
	 * 
	 * @throws Exception
	 */
	public void clickFareAlertPopup() throws Exception {
		Utils.waitForElement(driver, lnkFareAlert);
		BrowserActions.clickOnElement(lnkFareAlert, driver, "Click Fare Alert");
		Utils.waitForPageLoad(driver);
		Log.event("Click Fare Alert");
	}
	
	/**
	 * Getting the text from Title in Fare Alert pop up  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFareAlertPopupTitle() throws Exception {
		Utils.waitForElement(driver, txtFareAlertTitle);
		String airlineNameGetTxt = BrowserActions.getText(driver, txtFareAlertTitle, "Fare Alert Title text should be displayed");
		return airlineNameGetTxt;
	}
	
	/**
	 * Getting the text from Leaving From text box in Fare Alert pop up  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextLeavingFromInFareAlert() throws Exception {
		Utils.waitForElement(driver, txtOrigin_FareAlert);
		String leavingFromGetTxt = BrowserActions.getTextFromAttribute(driver, txtOrigin_FareAlert, "ng-favalidate",  "Leaving From in Fare Alert");
		return leavingFromGetTxt;
	}
	
	
	/**
	 * Getting the text from Going To text box in Fare Alert pop up  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextGoingToInFareAlert() throws Exception {
		Utils.waitForElement(driver, txtDestination_FareAlert);
		String goingToGetTxt = BrowserActions.getTextFromAttribute(driver, txtDestination_FareAlert, "ng-favalidate",  "Going To in Fare Alert");
		return goingToGetTxt;
	}
	
	/**
	 * Getting the text from ExactDays in Fare Alert pop up  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextExactDatesInFareAlert() throws Exception {
		Utils.waitForElement(driver, lnkExactDates_FareAlert);
		String airlineNameGetTxt = BrowserActions.getText(driver, lnkExactDates_FareAlert, "Fare Alert Title text should be displayed");
		return airlineNameGetTxt;
	}
	
	
	/**
	 * Getting the text from Days in Fare Alert pop up  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDaysInFareAlert() throws Exception {
		Utils.waitForElement(driver, lnkDays_FareAlert);
		String airlineNameGetTxt = BrowserActions.getText(driver, lnkDays_FareAlert, "Fare Alert Title text should be displayed");
		return airlineNameGetTxt;
	}
	
	/**
	 * Getting the text from Departure field in Fare Alert pop up  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDepartureInFareAlert() throws Exception {
		Utils.waitForElement(driver, txtDepartureDate_FareAlert);
		String airlineNameGetTxt = BrowserActions.getText(driver, txtDepartureDate_FareAlert, "Fare Alert Title text should be displayed");
		return airlineNameGetTxt;
	}
	
	/**
	 * Enter Max Price in Fare Alert pop up
	 * 
	 * @param origin
	 *            as string
	 * @throws Exception
	 */
	public void enterMaxPriceInFareAlert(String price) throws Exception {
		Utils.waitForElement(driver, txtMaxPrice_FareAlert);		
		BrowserActions.typeOnTextField(txtMaxPrice_FareAlert, price, driver, "Enter Max Price");					
		Log.event("Entered the Max Price: " + price);		
	}
	
	/**
	 * Enter Email in Fare Alert pop up
	 * 
	 * @param email
	 *            as string
	 * @throws Exception
	 */
	public void enterEmailInFareAlert(String email) throws Exception {
		Utils.waitForElement(driver, txtEmail_FareAlert);		
		BrowserActions.typeOnTextField(txtEmail_FareAlert, email, driver, "Enter Email");				
		Log.event("Entered Email: " + email);		
	}
	
	/**
	 * Enter Mobile number in Fare Alert pop up
	 * 
	 * @param mobile
	 *            as string
	 * @throws Exception
	 */
	public void enterMobileInFareAlert(String mobile) throws Exception {
		Utils.waitForElement(driver, txtMobile_FareAlert);		
		BrowserActions.typeOnTextField(txtMobile_FareAlert, mobile, driver, "Enter Mobile");					
		Log.event("Entered the Mobile: " + mobile);		
	}
	
	
	/**
	 * To click Set Alert button in Fare Alert pop up
	 * 
	 * @throws Exception
	 */
	public void clickSetAlertButtonInFareAlertp() throws Exception {
		Utils.waitForElement(driver, btnSetAlert_FareAlert);
		BrowserActions.clickOnElement(btnSetAlert_FareAlert, driver, "Click Set Alert button in Fare Alert");
		Utils.waitForPageLoad(driver);
		Log.event("Click Set Alert button in Fare Alert");
	}
	
	/**
	 * Getting the text from Success message in Fare Alert pop up  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFareAlertSuccessMesaage() throws Exception {
		Utils.waitForElement(driver, txtFareAlertMessage);
		String messageGetTxt = BrowserActions.getText(driver, txtFareAlertMessage, "Fare Alert success message text should be displayed");
		return messageGetTxt;
	}
	
	/**
	 * Select Mobile STD in Fare Alert pop up
	 * 
	 * @param mobileSTD
	 *            as string
	 * @throws Exception
	 */
	public void selectMobileSTDInFareAlert(String mobileSTD) throws Exception {
		Utils.waitForElement(driver, txtMobile_FareAlert);		
		BrowserActions.selectDropDownValue(driver, drpMobileSTD_FareAlert, "Select Mobile STD code");					
		Log.event("Selected the Mobile STD code: " + mobileSTD);		
	}


	/**
	 * To click Fare And Summary in Flight Detail Pop Up
	 * 
	 * @throws Exception
	 */
	public void clickOnFareAndSummaryFlightDetail() throws Exception {
		Utils.waitForPageLoad(driver);	
		BrowserActions.clickOnElement(lnkFareAndSummaryFlightDetail, driver, "Click Fare And Summary Link");
	}
	

	/**
	 * To click Baggage in Flight Detail Pop Up
	 * 
	 * @throws Exception
	 */
	public void clickOnBaggageFlightDetail() throws Exception {
		Utils.waitForPageLoad(driver);	
		BrowserActions.clickOnElement(lnkBaggageFlightDetail, driver, "Click Baggage Link");
	}
	/**
	 * To get text Baggage Details In Flight Details pop Up
	 * @throws Exception
	 */
	
	public String getTextBaggageInfoFlightDetail() throws Exception {
		String Details = txtBaggageInfoFlightDetail.getText();
		return Details;
	}
	
	
	/**
	 * Getting the text from Price slider filter title in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextPriceFilterTitle() throws Exception {
		Utils.waitForElement(driver, txtPriceFliter);
		String priceGetTxt = BrowserActions.getText(driver, txtPriceFliter, "Price filter title text should be displayed");
		return priceGetTxt;
	}
	
	/**
	 * Getting the text from Depart Time filter title in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDepartTimeFilterTitle() throws Exception {
		Utils.waitForElement(driver, txtDepartTimeFilter);
		String departTimeGetTxt = BrowserActions.getText(driver, txtDepartTimeFilter, "Depart Time filter title text should be displayed");
		return departTimeGetTxt;
	}
	
	/**
	 * Getting the text from Stops filter title in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextStopsFilterTitle() throws Exception {
		Utils.waitForElement(driver, txtStopsFilter);
		String stopsGetTxt = BrowserActions.getText(driver, txtStopsFilter, "Stops filter title text should be displayed");
		return stopsGetTxt;
	}
	
	/**
	 * Getting the text from Fare Type filter title in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFareTypeFilterTitle() throws Exception {
		Utils.waitForElement(driver, txtFareTypeFilter);
		String fareTypeGetTxt = BrowserActions.getText(driver, txtFareTypeFilter, "Fare Type filter title text should be displayed");
		return fareTypeGetTxt;
	}
	
	/**
	 * Getting the text from Airlines filter title in SRP  
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextAirlinesFilterTitle() throws Exception {
		Utils.waitForElement(driver, txtAirlinesFilter);
		String airlinesGetTxt = BrowserActions.getText(driver, txtAirlinesFilter, "Airlines filter title text should be displayed");
		return airlinesGetTxt;
	}
		
	
	/**
	 * To verify Non Stop Flights Only check box is checked on Stop Filters in SRP
	 * 
	 * @throws Exception
	 */
	public boolean verifyNonStopFlightsSelectionInFilters() throws Exception {
		boolean status = false;
		if (BrowserActions.isElementSelected(driver, lnkNonStopFlights) == true) {
			status = true;
		}
		return status;
	}
	
	/**
	 * To click Share Itinerary
	 * 
	 * @throws Exception
	 */
	public void clickShareItinerary() throws Exception {
		BrowserActions.nap(30);
		Utils.waitForElement(driver, lnkShareItinerary);
		BrowserActions.clickOnElement(lnkShareItinerary, driver, "Click Share Itinerary");
		BrowserActions.nap(2);
		Log.event("Click Share Itinerary");
	}
	
	/**
	 * Enter Email in Share Itinerary
	 * 
	 * @param email
	 *            as string
	 * @throws Exception
	 */
	public void enterEmailInShareItinerary(String email) throws Exception {
		Utils.waitForElement(driver, txtEmail_ShareItinerary);		
		BrowserActions.typeOnTextField(txtEmail_ShareItinerary, email, driver, "Enter Email in Share Itinerary");				
		Log.event("Entered Email in Share Itinerary, Email Id : " + email);		
	}
	
	/**
	 * Getting the text from pop up message in Share Itinerary
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextShareItinerary() throws Exception {
		Utils.waitForElement(driver, txtShareItineraryTooltipText);
		BrowserActions.mouseHover(driver, txtShareItineraryTooltipText);  //FF issue
		String shareItineraryPouUpMessageGetTxt = BrowserActions.getText(driver, txtShareItineraryTooltipText, "Share Itinerary Tooltip Text should be displayed");
		return shareItineraryPouUpMessageGetTxt;
	}
	
	/**
	 * Enter Message in Share Itinerary
	 * 
	 * @param email
	 *            as string
	 * @throws Exception
	 */
	public void enterMessageInShareItinerary(String message) throws Exception {
		Utils.waitForElement(driver, txtMessage_ShareItinerary);		
		BrowserActions.typeOnTextField(txtMessage_ShareItinerary, message, driver, "Enter Message in Share Itinerary");				
		Log.event("Entered Message in Share Itinerary, Message: " + message);		
	}
	
	/**
	 * To select first flight check box for Itinerary  
	 * 
	 * @throws Exception
	 */
	public void clickFlightItineraryChkBoxInShareItinerary() throws Exception {
		Utils.waitForElement(driver, chkItinerary_ShareItinerary);	
		BrowserActions.clickOnElement(chkItinerary_ShareItinerary, driver, "Click Itinerary in in Share Itinerary");
		Log.event("Clicked Itinerary in in Share Itinerary");
	}
	
	/**
	 * To click Share button in Share Itinerary
	 * 
	 * @throws Exception
	 */
	public void clickShareInShareItinerary() throws Exception {
		Utils.waitForElement(driver, txtShareButton_ShareItinerary);	
		BrowserActions.clickOnElement(txtShareButton_ShareItinerary, driver, "Click Share button in Share Itinerary");
		Log.event("Clicked Share button in Share Itinerary");
	}
	
	/**
	 * Getting the text from pop up message in Share Itinerary
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextShareItineraryPouUpMessage() throws Exception {
		Utils.waitForElement(driver, txtPopupMessage_ShareItinerary);
		String shareItineraryPouUpMessageGetTxt = BrowserActions.getText(driver, txtPopupMessage_ShareItinerary, "Share Itinerary popup message should be displayed");
		return shareItineraryPouUpMessageGetTxt;
	}	
		
	/**
	 * To verify Onward Legs is displayed
	 * @param links : PrevDay and NextDay in Onward leg links
	 * @return
	 * @throws Exception
	 */
	public boolean verifyOnwardLegLinks(String links) throws Exception {
		boolean status = false;
		if (links == "PrevDay") {
			Utils.waitForElement(driver, lnkPrevDay_OnwardLeg);			
			if (BrowserActions.isElementPresent(driver, lnkPrevDay_OnwardLeg) == true) {
				status = true;
			}
		} else if (links == "NextDay") {
			Utils.waitForElement(driver, lnkNextDay_OnwardLeg);			
			if (BrowserActions.isElementPresent(driver, lnkNextDay_OnwardLeg) == true) {
				status = true;
			}
		}
		return status;
	}
	
	/**
	 * To verify Return leg links is displayed
	 * @param links : PrevDay and NextDay in Return leg links
	 * @return
	 * @throws Exception
	 */
	public boolean verifyReturnLegLinks(String links) throws Exception {
		boolean status = false;
		if (links == "PrevDay") {
			Utils.waitForElement(driver, lnkPrevDay_ReturnLeg);			
			if (BrowserActions.isElementPresent(driver, lnkPrevDay_ReturnLeg) == true) {
				status = true;
			}
		} else if (links == "NextDay") {
			Utils.waitForElement(driver, lnkNextDay_ReturnLeg);			
			if (BrowserActions.isElementPresent(driver, lnkNextDay_ReturnLeg) == true) {
				status = true;
			}
		}
		return status;
	}
	

	 /* To select Preferred Airline in Airline Filters 
	 * @param airlineName : Airline Name
	 * @return
	 * @throws Exception
	 */
	public void selectAirlineInAirlineFilters(String airlineName) throws Exception {		
		for (int i = 1; i < txtAirlineName_AirlineFilters.size(); i++) {
			WebElement airlineFareDetails = driver.findElement(By.cssSelector("div[ng-show='open_airline'] li:nth-child("+i+") span[class='clip-overflow']"));
			BrowserActions.scrollToView(airlineFareDetails, driver);
			String airline = airlineFareDetails.getText().toString().trim();			
			if (airlineName.equalsIgnoreCase(airline)){				
				WebElement chkAirline = driver.findElement(By.cssSelector("div[ng-show='open_airline'] li:nth-child("+i+") span[class='checkbox']"));
				BrowserActions.javascriptClick(chkAirline, driver, "Click Airline in Airlines Filters, Selected Airline is:" +airline);
				break;
			}else if(airline == null){
				Log.event("Preferred Airline("+airlineName+") is not available, so its going to click Book Now Button with Random Flights");	
			}
		}
		
	}
	
	/*
	 * To select Connecting flight or Direct flight in Stops filter
	 * 
	 * @param stops : stops
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void selectFlightStopsInFilters(String stops) throws Exception {
		for (int i = 1; i < lnkSops_Filters.size(); i++) {
			WebElement stop = driver.findElement(By.cssSelector("div[ng-show='open_stop'] li:nth-child(" + i + ") span[class='full bxs txt-ac']"));
			String stopText = stop.getText().toString().trim();
			if (stopText.equalsIgnoreCase(stops)) {
				// WebElement chkAirline =driver.findElement(By.cssSelector("div[ng-show='open_airline'] li:nth-child("+i+") span[class='checkbox']>input"));
				BrowserActions.clickOnElement(stop, driver, "Click Stop in Stops Filters, Selected Stop is:" + stop);
				break;
			} else if(stopText == null){
				Log.event("Flight Stop is not available so All flights(connecting and Random) visible by default");		
			}
		}

	}

	/**
	 * To click on Book now button in One Way for Domestic Any and Preferred flights based on filter values
	 * 
	 * @param domain : DOM or INTL
	 * @param stops : Stops
	 * @param airlines : Airline Name
	 * @return
	 * @throws Exception
	 */
	public ReviewPage selectAirlineBookNowInOW(String domain, String stops, String airlines) throws Exception {
		BrowserActions.nap(2);
		if (domain.equalsIgnoreCase("DOM")) {
			// Select Connecting flight or Direct flight in Stops filter
			if (stops.equalsIgnoreCase("All")) {
				Log.event("All flights visible by default");
			} else {
				selectFlightStopsInFilters(stops);
				Log.event("Successfully selected " + stops + " button in Stops Filter");
			}

			// click book now based on Any or Preferred airlines
			if (airlines.equalsIgnoreCase("Any")) {
				clickOnBookNowInOW(2); // select Book now
				Log.event("All flights details are visible by default and Clicked BookNow Random flight");
			} else {
				selectAirlineInAirlineFilters(airlines); // Select Preferred Airline in Airline Filters
				clickOnPrefferedFlightsBookNowInOW(1); // select Book Now 
				Log.event("Successfully selected " + airlines + " checkbx in Airlines Filter and Clicked BookNow");
			}
			Log.event("Successfully clicked Book Now for Round Trip");
		} else if (domain.equalsIgnoreCase("INTL")) {
			// Select Connecting flight or Direct flight in Stops filter
			if (stops.equalsIgnoreCase("All")) {
				Log.event("All flights visible by default");
			} else {
				selectFlightStopsInFilters(stops);
				Log.event("Successfully selected " + stops + " button in Stops Filter");
			}

			// click book now based on Any or Preferred airlines
			if (airlines.equalsIgnoreCase("Any")) {
				clickOnBookNowInDOM_INTL(1); // select Book now
				Log.event("All flights details are visible by default and Clicked BookNow Random flight");
			} else {
				selectAirlineInAirlineFilters(airlines); // Select Preferred Airline in Airline Filters
				clickOnBookNowInDOM_INTL(1); // select Book Now 
				Log.event("Successfully selected " + airlines + " checkbx in Airlines Filter and Clicked BookNow");
			}
			Log.event("Successfully clicked Book Now for Round Trip");
		}
		return new ReviewPage(driver).get();
	}
	
	/**
	 * to click on Book now button in One Way for Domestic Any flights
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public void clickOnBookNowInOW(int index) throws Exception {
		BrowserActions.nap(5);
		WebElement wBookNow=driver.findElement(By.xpath("(//div[@data-gaeclist='Search Results Page'])["+index+"]//li[@class='book-now']//p[@yatratrackable='Flights|Search|Book Type|Book Now']"));
		BrowserActions.scrollToView(wBookNow, driver);
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(wBookNow, driver, "To click on Book now button-DOM");		
	}
	
		
	/**
	 * to click on Book now button in One Way for INTL flights
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public void clickOnBookNowInDOM_INTL(int index) throws Exception {
		BrowserActions.nap(5);		
		WebElement wBookNow=driver.findElement(By.xpath("(//div[@ng-controller='scheduleController']//div[@class='js-flightItem'])["+index+"]//p[@class='new-blue-button .js-bookNow book-btn relative tc']"));
		BrowserActions.scrollToView(wBookNow, driver);
		BrowserActions.nap(2);		
		BrowserActions.clickOnElement(wBookNow, driver, "To click on Book now button - INTL");		
	}
	
	/**
	 * To get text Destination from header Section
	 * 
	 * @throws Exception
	 */

	public String getTextOfDestinatioNameHeader() throws Exception {
		String Destination = txtDestinationName.getText();
		return Destination;
	}

	/**
	 * To get text Seats Left in that Flight Section
	 * 
	 * @throws Exception
	 */

	public String getTextSeatLeft() throws Exception {
		String SeatLeft = txtSeatLeftMessage.getText();
		return SeatLeft;
	}

	/**
	 * To get text Seats Left in that Flight details Pop Up Section
	 * 
	 * @throws Exception
	 */

	public String getTextSeatLeftInFlightDetailPopUp() throws Exception {
		String SeatLeft = txtSeatLeftMessageInFlightDetails.getText();
		return SeatLeft;
	}

	public LoginPage navigateToSignIn() throws Exception {

		WebElement lnkMyAccount = driver.findElement(By.cssSelector("li[id='userSignInStrip']>a"));
		Point point = lnkMyAccount.getLocation();
		int xCord = point.getX();
		int yCord = point.getY();

		if (BrowserType.fromConfiguration("ie") == BrowserType.IE) {
			Robot robot = new Robot();
			robot.mouseMove(xCord, yCord);
		}
		btnMyAccount.click();
		BrowserActions.javascriptClick(btnSignIn, driver, "Sign In");
		Utils.waitForPageLoad(driver);
		return new LoginPage(driver).get();
	}

	/**
	 * To get text Text from Recent search pop Up
	 * 
	 * @throws Exception
	 */

	public String getTextRecentSearchPopUp() throws Exception {
		String Details = txtRecentSearchPopUp.getText();
		return Details;
	}

	/**
	 * To Click on Get Fare Alert Link
	 * 
	 * @throws Exception
	 */
	public void clickOnGetFareAlert() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnElement(lnkFareCall, driver, "Click on Get Fare Alert Link");
	}

	/**
	 * To Fill Price(Amount) in Fare Details In Pop Up
	 * 
	 * @throws Exception
	 */
	public String enterGetFareDetailsPrice(String Price) throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.typeOnTextField(txtFldMaxAmount, Price, driver, "To Fill Amount in fare Alert Pop Up");
		Thread.sleep(1000);
		String errorMessage = errorMessageFromFareDetailPopUP.getText();
		return errorMessage;
	}

	/**
	 * To Fill Email in Fare Details In Pop Up
	 * 
	 * @throws Exception
	 */

	public String enterGetFareDetailsEmail(String Email) throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.typeOnTextField(txtFldEmail, Email, driver, "To Fill Email in fare Alert Pop up");
		Thread.sleep(1000);
		String errorMessage = errorMessageEmailFromFareDetailPopUP.getText();
		return errorMessage;
	}

	/**
	 * To Fill PhoneNumber in Fare Details In Pop Up
	 * 
	 * @throws Exception
	 */
	public String enterGetFareDetailsPhoneNumber(String PhoneNumber) throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.typeOnTextField(txtFldPhoneNumber, PhoneNumber, driver,
				"To Fill Phone number in fare Alert Pop up");
		Thread.sleep(1000);
		String errorMessage = errorMessagePhoneNumberFromFareDetailPopUP.getText();
		return errorMessage;
	}

	/**
	 * To Click on [X] button in Flights Detail
	 * 
	 * @throws Exception
	 */
	public void clickOnCloseInFlightDetailPopUp() throws Exception {
		BrowserActions.javascriptClick(closeInFlightDetailPopUp, driver, "Click on [X] button in Flight Detail");
	}

	/**
	 * To get text from Number Of Stops
	 * 
	 * @throws Exception
	 */

	public String getTextNoOfStops() throws Exception {
		String Details = txtNoOfStops.getText();
		return Details;
	}

	/**
	 * To Scroll To Bottom of SRP
	 * 
	 * @throws Exception
	 */
	public void scrollToBottom() throws Exception {
		BrowserActions.scrollToView(yatraFooterPanel, driver);
	}

	/**
	 * To Scroll To Up of SRP
	 * 
	 * @throws Exception
	 */
	public void ClickOnScrollUpButton() throws Exception {
		BrowserActions.clickOnElement(btnScrollUpSRP, driver, "");
	}

	/**
	 * To get text from Number Of Stops
	 * 
	 * @throws Exception
	 */
	public boolean verifySelectedFlightName() throws Exception {
		String flightname = BrowserActions.getText(driver, preferredFlightName, "Flight Name");
		for (int i = 0; i < lstflightNameSRP.size(); i++) {
			String flightNameRow = BrowserActions.getText(driver, lstflightNameSRP.get(i),
					"Getting txt of the flight name by rows.");
			if (flightname.equalsIgnoreCase(flightNameRow)) {
				return true;
			}
		}
		return false;
	}

	public void clickOnRecentSearch() throws Exception {
		BrowserActions.javascriptClick(btnRecentSearch, driver, "Click on [X] button in Flight Detail");
	}

	/**
	 * To get text origin from header Section
	 * 
	 * @throws Exception
	 */

	public String getTextOfOrigiNameHeader() throws Exception {
		Utils.waitForPageLoad(driver);
		String Origin = txtOriginName.getText();
		return Origin;
	}
	
	/**
	 * To click on Flight Link
	 * 
	 * @throws Exception
	 */
	public void clickOnFlightLinks() throws Exception {
		List<WebElement> lstFlight = driver.findElements(By.cssSelector("div[class='js-flightRow js-flightItem']"));
		for(int i=0;i<lstFlight.size();i++)
		{
			WebElement srt = lstFlight.get(i).findElement(By.cssSelector("article>div[class='my-res-info full']>ul>li[class='book-now']>p[class*='full txt-ar text-error fs-xs line-hn mt5']"));
			if(srt.isDisplayed()){
			String seat = BrowserActions.getText(driver,srt , "Getting text");
			BrowserActions.javascriptClick( lstFlight.get(i).findElement(By.cssSelector("article>footer>ul[class='res-footer-list fl uprcse']>li:not([ng-class*='viewedData'])>a")), driver, "Clicked on flight details link.s");
			break;
			}
		}
	}
	/**
	 * To get text No Flight Visible
	 * 
	 * @throws Exception
	 */
	public String getTextOfNoFlightAvaliable() throws Exception {
		Utils.waitForPageLoad(driver);
		String Message = txterrorMessageNoFlights.getText();
		return Message;
	}

	@FindBy(css = "div[class='lob-logo']>i[class='ico ico-rc-plane']")
	private WebElement priceRecentSearch;
	
	public void scrollToRecentSearch() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.scrollToView(priceRecentSearch, driver);	
	}
	/**
	 * To get text Of Ecash Earned Below Book Now Button
	 * 
	 * @throws Exception
	 */

	public String getTextEcashEarned() throws Exception {
		Utils.waitForPageLoad(driver);
		String Ecash = txtEcashEarned.getText();
		return Ecash;
	}
	/**
	 * To Enter Details in Modify Search
	 * 
	 * @throws Exception
	 */
	public void enterNewFlightDetails(String Origin,String Destination) throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.typeOnTextField(txtOriginModifySearch, Origin, driver, "To Enter Origin In Modify Search");
		Thread.sleep(2000);
		BrowserActions.typeOnTextField(txtDestinationModifySearch, Destination, driver, "To Enter Destination In Modify Search");
		Thread.sleep(2000);
		BrowserActions.javascriptClick(btnSearchModifySearch, driver, "Click on Search Flight In Modify Search");
	}
	
	/**
	 * To get text Of User Name From Header
	 * 
	 * @throws Exception
	 */

	public String getTextUserNameFromHeader() throws Exception {
		Utils.waitForPageLoad(driver);
		String Name = txtUserNameOnHeader.getText();
		return Name;
	}
	/**
	 * To click on Book now button in Round Trip for Domestic Any and Preferred flights based on filter values
	 * 
	 * @param domain : DOM or INTL
	 * @param stops : Stops
	 * @param airlines : Airline Name
	 * @return
	 * @throws Exception
	 */
	public ReviewPage selectAirlineBookNowInRT(String domain, String stops, String airlines) throws Exception {
		BrowserActions.nap(2);
		if (domain.equalsIgnoreCase("DOM")) {
			// Select Connecting flight or Direct flight in Stops filter
			if (stops.equalsIgnoreCase("All")) {
				Log.event("All flights visible by default");
			} else {
				selectFlightStopsInFilters(stops);
				Log.event("Successfully selected " + stops + " button in Stops Filter");
			}

			// click book now based on Any or Preferred airlines
			if (airlines.equalsIgnoreCase("Any")) {
				BrowserActions.nap(5);
				clickOnBookNowInRT_DOM(1, 2, 2, 7); // select Book Now Airlines
				Log.event("All flights details are visible by default and clicked Book Now Random flight-DOM");
			} else {
				selectAirlineInAirlineFilters(airlines); // Select Preferred Airline in Airline Filters
				BrowserActions.nap(5);
				clickOnBookNowInRT(1, 1); // select Book Now Airlines
				Log.event("Successfully selected " + airlines + " checkbx in Airlines Filter and clicked Book Now - DOM");
			}
			Log.event("Successfully clicked Book Now for Round Trip");
		} else if (domain.equalsIgnoreCase("INTL")) {
			// Select Connecting flight or Direct flight in Stops filter
			if (stops.equalsIgnoreCase("All")) {
				Log.event("All flights visible by default");
			} else {
				selectFlightStopsInFilters(stops);
				Log.event("Successfully selected " + stops + " button in Stops Filter");
			}

			// click book now based on Any or Preferred airlines
			if (airlines.equalsIgnoreCase("Any")) {
				clickOnBookNowInDOM_INTL(1); // select Book now
				Log.event("All flights details are visible by default and clicked Book Now Random flight -RT");
			} else {
				selectAirlineInAirlineFilters(airlines); // Select Preferred Airline in Airline Filters
				clickOnBookNowInDOM_INTL(1); // select Book Now Airlines
				Log.event("Successfully selected " + airlines + " checkbx in Airlines Filter and clicked Book Now RT ");
			}
			Log.event("Successfully clicked Book Now for Round Trip");
		}
		return new ReviewPage(driver).get();
	}
	
	/**
	 * to click on Book now button in Round Trip for Domestic any flights
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public void clickOnBookNowInRT_DOM(int onwardList, int onwardFlight, int returnList, int returnFlight) throws Exception {
		BrowserActions.nap(5);
		WebElement onwardflight = driver.findElement(By.cssSelector(" div[id='resultBoxSlider']>div:nth-child(" + onwardList
				+ ")>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child(" + onwardFlight
				+ ")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='price']>div[class='full']>div>p[class='new-blue-button fr book-button']:not([class='ng-hide']"));
		WebElement returnflight = driver.findElement(By.cssSelector(" div[id='resultBoxSlider']>div:nth-child(" + returnList
				+ ")>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child(" + returnFlight
				+ ")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='price']>div[class='full']>div>p[class='new-blue-button fr book-button']:not([class='ng-hide']"));
		BrowserActions.scrollToView(onwardflight, driver);
		BrowserActions.clickOnElement(onwardflight, driver, "To select Flight from one list.");
		BrowserActions.scrollToView(returnflight, driver);
		BrowserActions.clickOnElement(returnflight, driver, "To select Flight from second list.");
		BrowserActions.clickOnElement(btnBookNowRoundTrip, driver, "Click on Book Now for RoundTrip for Domestic any flights");
		
	}
	
	/**
	 * to click on Book now button in Round Trip for Domestic Preferred flights
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public void clickOnBookNowInRT(int onwardFlight, int returnFlight) throws Exception {
		WebElement onwardflight = driver.findElement(By.xpath("//div[@id='resultList_0']//div[@class='js-flightRow js-flightItem']["+onwardFlight+"]//p[@class='new-blue-button fr book-button']"));
		WebElement returnflight = driver.findElement(By.xpath("//div[@id='resultList_1']//div[@class='js-flightRow js-flightItem']["+onwardFlight+"]//p[@class='new-blue-button fr book-button']"));
		BrowserActions.scrollToView(onwardflight, driver);
		BrowserActions.clickOnElement(onwardflight, driver, "To select Flight from one list.");
		BrowserActions.scrollToView(returnflight, driver);
		BrowserActions.clickOnElement(returnflight, driver, "To select Flight from second list.");
		BrowserActions.clickOnElement(btnBookNowRoundTrip, driver, "Click on Book Now for RoundTrip for Domestic Preferred flights");
		
	}
	/**
	 * to click on Book now button in Round Trip for Domestic Preferred flights
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public void clickOnPrefferedFlightsBookNowInOW(int index) throws Exception {
		BrowserActions.nap(3);
		WebElement prefferedFlightBookNow = driver.findElement(By.xpath("(//div[@id='resultList_0']//div[@class='js-flightRow js-flightItem']["+index+"])//p[@class='new-blue-button fr book-button js-bookNow relative tc']"));
		BrowserActions.scrollToView(prefferedFlightBookNow, driver);
		BrowserActions.nap(3);
		BrowserActions.clickOnElement(prefferedFlightBookNow, driver, "To click on Book now button-DOM");		
	}
	/**
	 * To get text 'Continue with ur search' Only in RT
	 * 
	 * @throws Exception
	 */

	public String getTextContinueWithSearchMessage() throws Exception {
		Utils.waitForPageLoad(driver);
		String Message = txtContinueWithSearchMessage.getText();
		return Message;
	}
	/**
	 * to click on Book Flight Details Link In Round Trip 
	 * Only 0 or 1 can be passed in Second argument as o is onward and 1 is return flight
	 * @param index and i
	 * @return
	 * @throws Exception
	 */
	public void clickOnFlightDetailsInRT(int index,int i) throws Exception {
		
		WebElement onwardflight = driver.findElement(By.cssSelector("div[id='resultBoxSlider']>div[id='resultList_"+i+"']>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child("+index+")>article>footer>ul[class='res-footer-list fl uprcse']>li:not([class='ng-hide'])>a"));
		BrowserActions.clickOnElement(onwardflight, driver, "To select Flight Details from one list");	
	}
	/**
	 * to click on Book Now In Flight Details 
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public ReviewPage clickOnBookNowInFlightDetails_INTL() throws Exception {
		btnBookNowFlightDeatilPopUp_INTL.click();
		return new ReviewPage(driver).get();
	}
	
	/**
	 * Getting the text from Prev Day buttons on Onward leg in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextOnwardPrevDaySearch() throws Exception {
		Utils.waitForElement(driver, lnkPrevDay_OnwardLeg);
		String prevDayGetTxt = BrowserActions.getTextFromAttribute(driver, lnkPrevDay_OnwardLeg, "title",  "Prev Day title");
		return prevDayGetTxt;
	}
	
	/**
	 * Getting the text from Next Day buttons on Onward leg in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextOnwardNextDaySearch() throws Exception {
		Utils.waitForElement(driver, lnkPrevDay_OnwardLeg);
		String nextDayGetTxt = BrowserActions.getTextFromAttribute(driver, lnkNextDay_OnwardLeg, "title",  "Next Day title");
		return nextDayGetTxt;
	}
	
	/**
	 * Getting the text from Prev Day buttons on Return leg in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextReturnPrevDaySearch() throws Exception {
		Utils.waitForElement(driver, lnkPrevDay_OnwardLeg);
		String prevDayGetTxt = BrowserActions.getTextFromAttribute(driver, lnkPrevDay_ReturnLeg, "title",  "Prev Day title");
		return prevDayGetTxt;
	}
	
	/**
	 * Getting the text from Next Day buttons on Return leg in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextReturnNextDaySearch() throws Exception {
		Utils.waitForElement(driver, lnkPrevDay_OnwardLeg);
		String nextDayGetTxt = BrowserActions.getTextFromAttribute(driver, lnkNextDay_ReturnLeg, "title",  "Next Day title");
		return nextDayGetTxt;
	}
	
		
	/**
	 * To click Refundable Checkbox in Fare Type filters
	 * 
	 * @throws Exception
	 */
	public void clickRefundableCheckbox() throws Exception {
		Utils.waitForElement(driver, chkRefundable);	
		BrowserActions.clickOnElement(chkRefundable, driver, "Click Refundable Checkbox");
		Log.event("Clicked Refundable option checkbox");
	}
	
		
	/**
	 * To verify the Refundable flights in Flight Grid
	 * 
	 * @throws Exception
	 */
	public boolean verifyRefundableFlights() throws Exception {	
		for (int i = 0; i < lstRefundableFlights.size(); i++) {
			WebElement refundableEle = driver.findElement(By.xpath("(//div[@class='js-flightRow js-flightItem']["+i+"])//li[@ng-if='flt.ft==1']"));
			String flightRow = BrowserActions.getText(driver, refundableEle, "Getting txt of the Refundable in flight rows.");
			if (flightRow.equalsIgnoreCase("Refundable")) {
				return true;
			}
		}
		return false;
	}
	
	// *******************************End of SRP Functions*************************************************************

} // SearchResult

