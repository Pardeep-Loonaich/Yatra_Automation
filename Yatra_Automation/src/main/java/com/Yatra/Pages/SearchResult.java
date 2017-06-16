package com.Yatra.Pages;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.Yatra.Pages.ElementLayer;
import com.Yatra.Pages.HomePage;
import com.Yatra.Pages.LoginPage;
import com.Yatra.Pages.ReviewPage;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.BrowserType;
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.EnvironmentPropertiesReader;

import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

@SuppressWarnings("unused")
public class SearchResult extends LoadableComponent<SearchResult> {

	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	ExecutionTimer timer = new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader = EnvironmentPropertiesReader.getInstance();

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Search Result Page
	 * ***********************************
	 **********************************************************************************************/

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

	@FindBy(xpath = "//article[@class='full result-card animation-on item-0']//p[@class='new-blue-button .js-bookNow book-btn relative tc']")
	private WebElement btnBookNowINT_New;

	@FindBys({ @FindBy(css = "div[ng-controller='productFareDetailsController']") })
	private List<WebElement> el;
	// private List<WebElement> moduleFareDetails;

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

	@FindBy(css = "div[class='overlay-content']>div[class='my-fare-grid']>ul[class='tab fs-md']>li[analytics='Fare Details Pop up|Fare Rules']")
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

	@FindBy(css = "ul[class='full city-details tripO']>li:nth-child(1)>p[class='city-name']")
	private WebElement txtSourceCity_OW;

	@FindBy(css = "ul[class='full city-details tripO']>li:nth-child(1)>p[class='fs-10 ltr-gray uprcse mt2 tl']")
	private WebElement txtSourceDate_OW;

	@FindBy(css = "ul[class='full city-details tripO']>li:nth-child(2)>p[class='city-name']")
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

	@FindBy(css = "div[id='resultList_0']>div>div[data-gaeclist='Search Results Page']>article>div[class='my-res-info full']>ul[class='table-listing my-res-table']>li>div[class='time']>span")
	private WebElement txtFlightDuration;

	@FindBy(css = "div[id='resultList_0']>div:nth-child(3)>div:nth-child(1) a[class='under-link']")
	private WebElement lnkFlightDetail;

	@FindBy(css = "div[class='row itinerary-details'] li[class='trip-type'] time")
	private WebElement txtFlightDetailsPopupDuration;

	@FindBy(css = "span[class='ytfi-cancel']")
	private WebElement lnkcloseFlightDetailsPopUp;

	@FindBy(css = "div[id='resultList_0']>div:nth-child(3)>div:nth-child(1) span[class='ml5 hidden-sm']")
	private WebElement txtResultStripView;

	@FindBy(xpath = "//form[@id='modifySearch']/div[1]//label[1]//span/input")
	private WebElement chkOneWay; // TODO

	@FindBy(xpath = "//form[@id='modifySearch']/div[1]//label[2]//span/input")
	private WebElement chkRoundTrip; // TODO

	@FindBy(xpath = "//form[@id='modifySearch']/div[1]//label[3]//span/input")
	private WebElement chkMultiCity; // TODO

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

	@FindBy(css = "div[class*='matrix-slide-wrapper has-next-prev matrix-small-screen day-matrix-wrapper']")
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

	@FindBy(css = "i[class='ytfi-bell ico-fare-cal-fixed']")
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

	@FindBy(xpath = "//div[@class='overlay no-pad flight-details-overlay']/div[@class='overlay-header']/span[@class='ytfi-cancel']")
	private WebElement closeInFlightDetailPopUp;

	@FindBy(css = "span[class='ml5 hidden-sm']")
	private WebElement btnViewed;

	@FindBy(css = "p[class='full fs-11 ltr-gray three-dot mt2 fly-stop']")
	private WebElement txtNoOfStops;

	@FindBy(css = "div[class='yatraSecure']")
	private WebElement yatraFooterPanel;

	@FindBy(css = "div[class='up-scroll ytfi-up-open']")
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

	@FindBy(css = "small[class='fs-xs block mt5 lt-gray full']")
	private WebElement conformingUrSeatPopUp;

	@FindBy(css = "span[class='checkbox']>input[analytics*='Filter Option Click|Airline']")
	private WebElement chkBoxAirline;

	@FindBy(css = "footer[class='row my-res-footer full']>ul[class='res-footer-list fr']>li[ng-if*='flt.ft']")
	private WebElement lnkRefundableAndNonRefundable_DOM;

	@FindBy(css = "span[class='ref-ui']")
	private WebElement txtRefundableAndNonRefundableInFlightDetails;

	@FindBy(css = "div[class='one-fourth']")
	private WebElement txtFareSummaryInFlighDeatils;

	@FindBy(css = "div[class='one-sixth']")
	private WebElement txtFareRulesInFlighDeatils;

	@FindBy(css = "div[ng-show='open_ftype'] li:nth-child(1) span[class='checkbox']")
	private WebElement chkRefundable;

	@FindBy(xpath = "//div[@class='js-flightRow js-flightItem']//li[@ng-if='flt.ft==1']")
	private List<WebElement> lstRefundableFlights;

	@FindBy(css = "span[class='rz-pointer']:nth-child(4)")
	private WebElement AmountSlider;

	@FindBy(css = "button[class='new-blue-button mr10']")
	private WebElement btnUndoLastFilter;

	@FindBy(css = "span[class='rz-bubble set-middle']")
	private WebElement amountSliderAfterChange;

	@FindBy(xpath = "//div[@class='js-flightRow js-flightItem']//small[@class='fs-sm gray fl ml5 name carrier-name']")
	private List<WebElement> lstFlightResultGrid;

	@FindBy(css = "a[class='under-link fr']")
	private WebElement lnkResetAll;

	@FindBy(css = "div[class='header-container']")
	private WebElement lnkHeaders;

	@FindBy(css = "div[class='full filter-wrapper accordion-wrappper']")
	private WebElement lnkFilters;

	@FindBy(css = "div[class='matrix-wrapper day-matrix new-theme day-matrix-responsive']")
	private WebElement lnkAirlinematrix_OW;

	@FindBy(css = "div[class='matrix-slide-wrapper has-next-prev matrix-small-screen']")
	private WebElement lnkAirlinematrix;

	@FindBy(css = "div[id='resultBoxSlider']")
	private WebElement lnkResultGrid;

	@FindBy(css = "div[class='fare-cal-fixed']")
	private WebElement lnkSetFareAlerts;

	@FindBy(css = "div[class='footer-beetle-new dom']")
	private WebElement lnkFooter;

	@FindBy(css = "div[id='resultBoxSlider'] a[title= 'Sort By Airline']")
	private WebElement lnkAirlineColumn;

	@FindBy(css = "div[id='resultBoxSlider'] a[title= 'Sort By Depart']")
	private WebElement lnkDepartColumn;

	@FindBy(css = "div[id='resultBoxSlider'] a[title= 'Sort By Arrive']")
	private WebElement lnkArriveColumn;

	@FindBy(css = "div[id='resultBoxSlider'] a[title= 'Sort By Duration']")
	private WebElement lnkDurationColumn;

	@FindBy(css = "div[id='resultBoxSlider'] a[title= 'Sort By Price']")
	private WebElement lnkPriceColumn;

	@FindBy(css = "div[class='new-gray-button fr']")
	private WebElement btnBookNowBottom_RT;

	@FindBy(css = "div[ng-show='selSumBeforeTime']")
	private WebElement txtErrorMessageFlightDifference;

	@FindBy(css = "div[ng-if='flt.freemeal']>p")
	private WebElement txtFreeMeal;

	@FindBy(css = "i[class='ytfi-free-meal']")
	private WebElement logoFreeMeal;

	@FindBy(css = "li[ng-if='flt.freemeal']")
	private WebElement txtFreeMeal_DOM;

	@FindBy(css = "div[class='full smart-search bxs']")
	private WebElement smartBox;

	@FindBy(css = "div[class='selection-box new-theme seg-2']")
	private WebElement selectionDivision;

	@FindBy(css = "p[class='mb10']")
	private WebElement errorMessageAfterApplyingFilter;

	@FindBy(css = "h2[class='fs-lg fl hand-cursor']")
	private WebElement priceSelectionBox;

	@FindBy(css = "div[id='fareSummaryPopup'")
	private WebElement fareSummaryPopUp;

	@FindBy(css = "span[class='full bxs txt-ac']")
	private List<WebElement> stopsFilter;

	@FindBy(css = "div[class='reviewSrch btnSearchBook js_review_search_btn']")
	private WebElement fareRecentSearch;

	@FindBy(css = "div[class='spinner']>span[class='spin-count']")
	private List<WebElement> txtPaxDeatilInModify;

	@FindBy(css = "div[class='spinner']>span[class='spin-count']")
	private WebElement PaxDeatilInModify;

	@FindBy(css = "a[class='under-link js-sortlink active']")
	private WebElement lnkPriceUpwards;

	@FindBy(css = "a[class='under-link js-sortlink active down']")
	private WebElement lnkPriceDownwards;

	@FindBy(css = "div[id='resultList_0'] p[class='new-blue-button fr book-button ytfi-ok-circled']")
	private WebElement lnkOnwardFlightSelection;

	@FindBy(css = "div[id='resultList_1'] p[class='new-blue-button fr book-button ytfi-ok-circled']")
	private WebElement lnkReturnFlightSelection;

	@FindBy(css = "div[id='resultList_1'] p[class='new-blue-button fr book-button ytfi-ok-circled'] span[class='mobl']")
	private WebElement lnkReturnSelectedFlightFare;

	@FindBy(css = "div[id='resultList_0'] p[class='new-blue-button fr book-button ytfi-ok-circled'] span[class='mobl']")
	private WebElement txtOnwardsSelectedFlightFare;

	@FindBy(css = "p[class='fs-10 ltr-gray uprcse mt2 tl']")
	private WebElement txtDepartureDate;

	@FindBy(css = "div[id='resultList_0'] div[class='js-flightRow js-flightItem']:nth-child(1) div[ class='my-res-info full']")
	private WebElement upperPartResultGridHeader;

	@FindBy(css = "div[id='resultList_0'] div[class='js-flightRow js-flightItem']:nth-child(1) footer[class='row my-res-footer full']")
	private WebElement lowerPartResultGridHeader;

	@FindBy(xpath = "//iframe[@id='webklipper-publisher-widget-container-notification-frame']")
	private WebElement IframeNotification;

	@FindBy(css = "a[id='webklipper-publisher-widget-container-notification-close-div']")
	private WebElement btnCloseIframeNotification_Double;

	@FindBy(css = "button.primary.rounded.pull-right")
	private WebElement btnFareChangeContinue; // remove later

	@FindBy(css = "button[ng-click='continueSameFlight();']")
	WebElement ContinueInFarePopUp;

	@FindBy(css = "[ng-show='priceChangeDiv']>div>div[class='overlay-content ']>div[class='row mt10 btn-box']>button[ng-click='continueSameFlight()']")
	private WebElement ContinueInFareChangeAlertPopUp;

	@FindBy(css = ".update-fare.pt10.ico-right")
	private WebElement PricePopUp;

	@FindBy(css = "[ng-show='priceChangeDiv']>div>div[class='overlay-content ']")
	private WebElement popupFareChange;

	@FindBy(css = "[ng-show='priceChangeDiv']>div>div[class='overlay-content ']>div[class='row mt10 btn-box text-center']>button")
	private WebElement ContinueInpopUpFareSlashed;

	@FindBy(css = "div[class='lob-logo']>i[class='ico ico-rc-plane']")
	private WebElement priceRecentSearch;

	@FindBy(css = "div[class='recent-iternary-detail'] div[class='place jsHideonSmallTab withFlightPlace']")
	private WebElement txtResentSearchDetails;

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
		timer.end();
		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, btnModifySearchIcon))) {
			Log.fail("Search Result page didn't open up", driver);
		}
		Log.message("Total time taken by #" + this.getClass().getTypeName() + " to load is:- " + timer.duration() + " "
				+ TimeUnit.MILLISECONDS);
		Constants.performanceData.add(timer.duration());
		elementLayer = new ElementLayer(driver);
	}

	@Override
	protected void load() {
		timer.start();
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
		closeINotificationAtTopSRP();
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
		popUpAppear();
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
		BrowserActions.nap(5);
		closeINotificationAtTopSRP();
		BrowserActions.scrollToView(btnBookNowINT, driver);
		BrowserActions.clickOnElement(btnBookNowINT, driver, "To click on Book now button.");
		popUpAppear();
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
		popUpAppear();
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
	 * To click on Flight Link
	 * 
	 * @throws Exception
	 */
	public void clickOnFlightLink() throws Exception {
		BrowserActions.clickOnElement(lnkFlightDetails, driver, "Flight Link");
		Utils.waitForPageLoad(driver);

	}

	/*
	 * To click on Book now button in MultiCity Trip for Domestic flights
	 * 
	 * @param index
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public ReviewPage clickOnBookNowInMulticity(int list1, int index1, int list2, int index2) throws Exception {
		closeINotificationAtTopSRP();
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
		String myAccountGetTxt = BrowserActions.getText(driver, txtMyAccount, "My Account");
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
		String supportGetTxt = BrowserActions.getText(driver, txtSupport, "Support");
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
		String splDealsGetTxt = BrowserActions.getText(driver, txtSplDeals, "Special Deals");
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
		String recentSearchGetTxt = BrowserActions.getText(driver, txtRecentSearch, "Recent Search");
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
		String userNameGetTxt = BrowserActions.getText(driver, txtUserAcctName, "User Acctount Name");
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
		String textLogintGetTxt = BrowserActions.getText(driver, txtLogin, "Login button ");
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
		String signUpGetTxt = BrowserActions.getText(driver, txtSignUp, "SignUp");
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
		String corporateLoginGetTxt = BrowserActions.getText(driver, txtCorporateLogin, "Corporate Login");
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
		String agentLoginGetTxt = BrowserActions.getText(driver, txtAgentLogin, "Agent Login");
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
		String myBookingsGetTxt = BrowserActions.getText(driver, txtMyBookings, "My Booking");
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
		String totalFlightsGetTxt = BrowserActions.getText(driver, txtTotalFlightSearch, "Total No of Flight");
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
		String totalFlightsGetTxt = BrowserActions.getText(driver, txtTotalFlightSearch, "Total No of Flight");
		String flightSearchDurationTxt = BrowserActions.getText(driver, txtFlightSearchDuration,
				"Flight Search duration");
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
		String sourceCityGetTxt = BrowserActions.getText(driver, txtSourceCity, "Source City");
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
		String sourceDateGetTxt = BrowserActions.getText(driver, txtSourceDate, "Source Date");
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
		String destCityGetTxt = BrowserActions.getText(driver, txtDestCity, "Destination City");
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
		String destDateGetTxt = BrowserActions.getText(driver, txtDestDate, "Destination Date");
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
				"Flight Search duration ");
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
		String startSourceCityGetTxt = BrowserActions.getText(driver, txtStartSourceCity, "Start MC_Source City");
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
		String startSourceDateGetTxt = BrowserActions.getText(driver, txtStartSourceDate, "Start MC_Source Date");
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
		String startDestCityGetTxt = BrowserActions.getText(driver, txtstartDestCity, "Start MC_Destination City");
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
		String endSourceCityGetTxt = BrowserActions.getText(driver, txtEndSourceCity, "End MC_Source City");
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
		String endSourceDateGetTxt = BrowserActions.getText(driver, txtEndSourceDate, "End MC_Source Date");
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
		String endDestCityGetTxt = BrowserActions.getText(driver, txtEndDestCity, "End MC_Destination City");
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
				"Flight duration 'hh mm' format");
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
				"Flight details popup duration 'hh mm' format");
		return flightDetailsPouUpDurationGetTxt;
	}

	/**
	 * To click Flight Details link in SRP
	 * 
	 * @throws Exception
	 */
	public void clickFlightDetails() throws Exception {
		Utils.waitForElement(driver, lnkFlightDetail);
		BrowserActions.clickOnElement(lnkFlightDetail, driver, "Flight Details link");
		Utils.waitForPageLoad(driver);
		Log.event("Clicked Flight Details link in SRP");
	}

	/**
	 * To click Flight Details pouUp close link
	 * 
	 * @throws Exception
	 */
	public void closeFlightDetailsPouUp() throws Exception {
		Utils.waitForPageLoad(driver);
		if (lnkcloseFlightDetailsPopUp.isDisplayed()) {
			BrowserActions.clickOnElement(lnkcloseFlightDetailsPopUp, driver,
					"Click Flight Details PopUp close button");
			Utils.waitForPageLoad(driver);
		}
		Log.event("Clicked Flight Details PopUp close button");
	}

	/**
	 * Getting the text from Flight details PouUp duration in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextResultStrip() throws Exception {
		Utils.waitForPageLoad(driver);
		String resultStripGetTxt = BrowserActions.getText(driver, txtResultStripView, "Result Strip view");
		return resultStripGetTxt;
	}

	/**
	 * Getting the text from Source city in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSourceCity_OW() throws Exception {
		Utils.waitForPageLoad(driver);
		String sourceCityGetTxt = BrowserActions.getText(driver, txtSourceCity_OW, "Source City ");
		return sourceCityGetTxt;
	}

	/**
	 * Getting the text from Source date in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSourceDate_OW() throws Exception {
		Utils.waitForPageLoad(driver);
		String sourceDateGetTxt = BrowserActions.getText(driver, txtSourceDate_OW, "Source Date ");
		return sourceDateGetTxt;
	}

	/**
	 * Getting the text from Destination City in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDestinationCity_OW() throws Exception {
		Utils.waitForPageLoad(driver);
		String destCityGetTxt = BrowserActions.getText(driver, txtDestCity_OW, "Destination City");
		return destCityGetTxt;
	}

	/**
	 * To click Modify Search link in SRP
	 * 
	 * @throws Exception
	 */
	public boolean clickModifySearch() throws Exception {
		boolean status = false;
		BrowserActions.nap(20);
		closeINotificationAtTopSRP();
		BrowserActions.nap(20);
		Utils.waitForElement(driver, btnModifySearchIcon);
		if (BrowserActions.isElementPresent(driver, btnModifySearchIcon) == true) {
			BrowserActions.clickOnElement(btnModifySearchIcon, driver, "Modify Search button");
			Log.event("Clicked Modify Search link in SRP");
			status = true;
		} else {
			Log.event("Not clicked Modify Search link in SRP");
			status = true;
		}
		BrowserActions.nap(5);
		Utils.waitForPageLoad(driver);
		return status;
	}

	/**
	 * To verify Trip Type in ModifySearch
	 * 
	 * @param String
	 *            : type of Trip
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
	 * @param String
	 *            : type of Trip
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
		String origin_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtOrigin_ModifySearch,
				"ng-msvalidate", "Modify Search Origin");
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
		String destination_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtDestination_ModifySearch,
				"ng-msvalidate", "Modify Search Destination");
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
		String adultGetTxt = BrowserActions.getText(driver, txtAdult_ModifySearch, "Modify Search Adult");
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
		String childGetTxt = BrowserActions.getText(driver, txtChild_ModifySearch, "Modify Search Child");
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
		String InfantGetTxt = BrowserActions.getText(driver, txtInfant_ModifySearch, "Modify Search Infant Text field");
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
		String departDate_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtDepartDate_ModifySearch,
				"ng-active-date", "Modify Search DepartDate");
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
		String returnDate_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtReturnDate_ModifySearch,
				"ng-active-date", "Modify Search ReturnDate");
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
		String passengerClass_ModifySearchGetTxt = BrowserActions.getText(driver, txtPassengerClass_ModifySearch,
				"Modify Search Passenger class");
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
		String preferredAirline_ModifySearchGetTxt = BrowserActions.getText(driver, txtPreferredAirline_ModifySearch,
				"Modify Search Preferred Airlines");
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
		String origin_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtOrigin1_ModifySearch,
				"ng-msvalidate", "Modify Search Origin_MC");
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
		String destination_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver,
				txtDestination1_ModifySearch, "ng-msvalidate", "Modify Search Destination_MC");
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
		String departDate_ModifySearchGetTxt = BrowserActions.getTextFromAttribute(driver, txtDepartDate1_ModifySearch,
				"ng-active-date", "Modify Search DepartDate_MC");
		return departDate_ModifySearchGetTxt;
	}

	/**
	 * To verify current date selection in Weekly Matrix
	 * 
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
				"Weekly Matrix Current Date");
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
		String lowestFlightFareGetTxt = BrowserActions.getText(driver, txtLowestFlightFare_AirlineMatix,
				"Airline Matrix Lowest Flight fare");
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
		List<WebElement> passengerClassList1 = driver
				.findElements(By.xpath("//form[@id='modifySearch']//option[@ng-repeat='key in className']"));
		for (int i = 0; i < passengerClassList1.size(); i++) {
			String passengerClass = passengerClassList1.get(i).getText().toString().trim();
			passengerclassList.add(passengerClass);
		}
		Log.event("Modify Search Passenger Class drop down details list : " + passengerclassList);
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
		} else if (lnkArirlineMatrix.isSelected()) {
			status = true;
		} else {
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
			WebElement airlineFareDetails = driver
					.findElement(By.cssSelector("ul[class='matrix-slide-list tabs matrix-ul'] li:nth-child(" + i
							+ ") p:nth-child(3)[class='matrix-label uprcse']"));
			String airlineFare = airlineFareDetails.getText().toString().trim().replace("RS.", "");
			;
			airlineMatrixFareDetailsList.add(airlineFare);
		}
		Log.event("Airline Matrix fare details : " + airlineMatrixFareDetailsList);
		return airlineMatrixFareDetailsList;
	}

	/**
	 * to select the Airlines by Name
	 * 
	 * @param BankName
	 * @throws Exception
	 */
	public void selectAirline(String AirlinesName) throws Exception {
		for (WebElement e : selectAirlines) {
			if (e.findElement(By.cssSelector("label>span[class='clip-overflow']")).getText()
					.equalsIgnoreCase(AirlinesName)) {
				BrowserActions.clickOnElement(e.findElement(By.cssSelector("label>span[class='clip-overflow']")),
						driver, "Selected Airline");
				break;
			}
		}
	}

	/**
	 * Description: to close notification which display randomly on SRP page
	 * 
	 * @throws Exception
	 */

	public void closeINotificationAtTopSRP() throws Exception {
		BrowserActions.nap(4);
		if (BrowserActions.isElementPresent(driver, IframeNotification) == true) {
			// if(IframeNotification.isDisplayed()){
			BrowserActions.switchToIframe(driver, IframeNotification);
			if (BrowserActions.isElementPresent(driver, btnCloseIframeNotification) == true) {
				BrowserActions.clickOnElement(btnCloseIframeNotification, driver,
						"Button to close Iframe Notification at top on SRP");
			} else if (BrowserActions.isElementPresent(driver, btnCloseIframeNotification_Double) == true) {
				BrowserActions.clickOnElement(btnCloseIframeNotification_Double, driver,
						"Iframe Left side bottom close button Notification");
			}
			BrowserActions.switchToDefault(driver);
			// BrowserActions.nap(6);
		} else {
			Log.event("Not displayed Iframe Notification at Top and Bottom on SRP ");
		}
		BrowserActions.nap(6);
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
			WebElement airlineNamesDetails = driver
					.findElement(By.cssSelector("ul[class='matrix-slide-list tabs matrix-ul'] li:nth-child(" + i
							+ ") p[class='matrix-label uprcse']"));
			String airlineNames = airlineNamesDetails.getText().toString().trim();
			airlineNamesInMatrix.add(airlineNames);
		}
		Log.event("Airline Matrix fare details : " + airlineNamesInMatrix);
		return airlineNamesInMatrix;
	}

	/**
	 * To Verify Airline Logo's in Airline Matrix
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyAirlinelogoInMatrix() throws Exception {
		boolean status = false;
		for (int i = 1; i < lnkAirlineMatrix.size(); i++) {
			WebElement airlineNamesDetails = driver
					.findElement(By.cssSelector("ul[class='matrix-slide-list tabs matrix-ul'] li:nth-child(" + i
							+ ") p[class='matrix-airline-logo']"));
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
		String airlineNameGetTxt = BrowserActions.getText(driver, txtselectedAirlineName, "Selected Airline Name Text");
		return airlineNameGetTxt;
	}

	/**
	 * To verify selection on particular Airline in Airline Filters
	 * 
	 * @param airlineName
	 *            : Airline Name
	 * @return
	 * @throws Exception
	 */
	public boolean verifySelectedAirlineInAirlineFilters(String airlineName) throws Exception {
		boolean status = false;
		for (int i = 1; i < txtAirlineName_AirlineFilters.size(); i++) {
			WebElement airlineFareDetails = driver.findElement(
					By.cssSelector("div[ng-show='open_airline'] li:nth-child(" + i + ") span[class='clip-overflow']"));
			String airline = airlineFareDetails.getText().toString().trim();
			if (airlineName.equalsIgnoreCase(airline)) {
				WebElement chkAirline = driver.findElement(By.cssSelector(
						"div[ng-show='open_airline'] li:nth-child(" + i + ") span[class='checkbox']>input"));
				status = BrowserActions.isRadioOrCheckBoxSelected(chkAirline);
				break;
			}
		}
		return status;
	}

	/**
	 * To verify Onward and Return LFF is displayed
	 * 
	 * @param LFF
	 *            : Lowest Fare Finder calendar
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
	 * 
	 * @param LFF
	 *            : Lowest Fare Finder calendar
	 * @throws Exception
	 */
	public void clickOnwardAndReturnLFF(String LFF) throws Exception {
		if (LFF == "OLFF") {
			Utils.waitForElement(driver, lnkOnwardLFF);
			BrowserActions.clickOnElement(lnkOnwardLFF, driver, "Click onward LFF - RT");
			Log.event("Clicked onward LFF");
		} else if (LFF == "RLFF") {
			Utils.waitForElement(driver, lnkReturnLFF);
			BrowserActions.clickOnElement(lnkReturnLFF, driver, "Click Return LFF - RT");
			Log.event("Clicked Return LFF");
		}
	}

	/**
	 * To Getting text from calendar Travel Details in LFF
	 * 
	 * @param LFF
	 *            : Lowest Fare Finder calendar
	 * @throws Exception
	 */
	public String getTextTravelDetailsInOnwardAndReturnLFF(String LFF) throws Exception {
		String textLFF = null;
		if (LFF == "OLFF") {
			Utils.waitForElement(driver, txtOnwardLFF_TravelDetails);
			textLFF = BrowserActions.getText(driver, txtOnwardLFF_TravelDetails, "Onward LFF Travel details text");
		} else if (LFF == "RLFF") {
			Utils.waitForElement(driver, txtReturnLFF_TravelDetails);
			textLFF = BrowserActions.getText(driver, txtReturnLFF_TravelDetails, "Return LFF Travel details text");
		}
		return textLFF;
	}

	/**
	 * Getting text from calendar in LFF
	 * 
	 * @param LFF
	 *            : Lowest Fare Finder calendar
	 * @throws Exception
	 */
	public String getTextCalenderInOnwardAndReturnLFF(String LFF) throws Exception {
		String textLFF = null;
		if (LFF == "OLFF") {
			Utils.waitForElement(driver, txtOnwardLFF_TravelDetails);
			textLFF = BrowserActions.getText(driver, txtCalender_OnwardLFF, "Onward LFF Calender text");
		} else if (LFF == "RLFF") {
			Utils.waitForElement(driver, txtReturnLFF_TravelDetails);
			textLFF = BrowserActions.getText(driver, txtCalender_ReturnLFF, "Return LFF Calender text");
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
		BrowserActions.clickOnElement(lnkFareAlert, driver, "Fare Alert popup");
		Utils.waitForPageLoad(driver);
		Log.event("Clicked Fare Alert");
	}

	/**
	 * Getting the text from Title in Fare Alert pop up
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFareAlertPopupTitle() throws Exception {
		Utils.waitForElement(driver, txtFareAlertTitle);
		String airlineNameGetTxt = BrowserActions.getText(driver, txtFareAlertTitle, "Fare Alert Title text");
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
		String leavingFromTxt = BrowserActions.getTextFromAttribute(driver, txtOrigin_FareAlert, "ng-favalidate",
				"Fare Alert Leaving From text ");
		return leavingFromTxt;
	}

	/**
	 * Getting the text from Going To text box in Fare Alert pop up
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextGoingToInFareAlert() throws Exception {
		Utils.waitForElement(driver, txtDestination_FareAlert);
		String goingToTxt = BrowserActions.getTextFromAttribute(driver, txtDestination_FareAlert, "ng-favalidate",
				"Fare Alert Going To text ");
		return goingToTxt;
	}

	/**
	 * Getting the text from ExactDays in Fare Alert pop up
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextExactDatesInFareAlert() throws Exception {
		Utils.waitForElement(driver, lnkExactDates_FareAlert);
		String exactDatesTxt = BrowserActions.getText(driver, lnkExactDates_FareAlert, "Fare Alert ExactDates text");
		return exactDatesTxt;
	}

	/**
	 * Getting the text from Days in Fare Alert pop up
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDaysInFareAlert() throws Exception {
		Utils.waitForElement(driver, lnkDays_FareAlert);
		String daysTxt = BrowserActions.getText(driver, lnkDays_FareAlert, "Fare Alert Days text");
		return daysTxt;
	}

	/**
	 * Getting the text from Departure field in Fare Alert pop up
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDepartureInFareAlert() throws Exception {
		Utils.waitForElement(driver, txtDepartureDate_FareAlert);
		String departureDateTxt = BrowserActions.getText(driver, txtDepartureDate_FareAlert,
				"Fare Alert Departure Date text");
		return departureDateTxt;
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
		BrowserActions.clickOnElement(btnSetAlert_FareAlert, driver, "Fare Alert Set Alert button");
		Utils.waitForPageLoad(driver);
		Log.event("Clicked Set Alert button in Fare Alert");
	}

	/**
	 * Getting the text from Success message in Fare Alert pop up
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFareAlertSuccessMesaage() throws Exception {
		Utils.waitForElement(driver, txtFareAlertMessage);
		String messageGetTxt = BrowserActions.getText(driver, txtFareAlertMessage, "Fare Alert success message text");
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
		BrowserActions.selectDropDownValue(driver, drpMobileSTD_FareAlert, "Fare Alert Mobile STD code dropdown");
		Log.event("Selected the Mobile STD code: " + mobileSTD);
	}

	/**
	 * To click Fare And Summary in Flight Detail Pop Up
	 * 
	 * @throws Exception
	 */
	public void clickOnFareAndSummaryFlightDetail() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnElement(lnkFareAndSummaryFlightDetail, driver, "Fare And Summary Link");
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
	 * 
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
		String priceTxt = BrowserActions.getText(driver, txtPriceFliter, "Price filter option");
		return priceTxt;
	}

	/**
	 * Getting the text from Depart Time filter title in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextDepartTimeFilterTitle() throws Exception {
		Utils.waitForElement(driver, txtDepartTimeFilter);
		String departTimeTxt = BrowserActions.getText(driver, txtDepartTimeFilter, "Depart Time filter option");
		return departTimeTxt;
	}

	/**
	 * Getting the text from Stops filter title in SRP
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextStopsFilterTitle() throws Exception {
		Utils.waitForElement(driver, txtStopsFilter);
		String stopsGetTxt = BrowserActions.getText(driver, txtStopsFilter, "Stops filter option");
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
		String fareTypeGetTxt = BrowserActions.getText(driver, txtFareTypeFilter, "Fare Type filter option");
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
		String airlinesGetTxt = BrowserActions.getText(driver, txtAirlinesFilter, "Airlines filter option");
		return airlinesGetTxt;
	}

	/**
	 * To verify Non Stop Flights Only check box is checked on Stop Filters in
	 * SRP
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
		Utils.waitForElement(driver, lnkShareItinerary);
		BrowserActions.clickOnElement(lnkShareItinerary, driver, "Share Itinerary");
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
		BrowserActions.nap(30);
		Utils.waitForElement(driver, txtShareItineraryTooltipText);
		BrowserActions.mouseHover(driver, txtShareItineraryTooltipText); // FF
																			// issue
		String shareItineraryPouUpMessageGetTxt = BrowserActions.getText(driver, txtShareItineraryTooltipText,
				"Share Itinerary Tooltip ");
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
		BrowserActions.typeOnTextField(txtMessage_ShareItinerary, message, driver, "Share Itinerary Message textbox");
		Log.event("Entered Message in Share Itinerary, Message: " + message);
	}

	/**
	 * To select first flight check box for Itinerary
	 * 
	 * @throws Exception
	 */
	public void clickFlightItineraryChkBoxInShareItinerary() throws Exception {
		Utils.waitForElement(driver, chkItinerary_ShareItinerary);
		BrowserActions.clickOnElement(chkItinerary_ShareItinerary, driver, "Click Itinerary checkbox");
		Log.event("Clicked Itinerary in in Share Itinerary");
	}

	/**
	 * To click Share button in Share Itinerary
	 * 
	 * @throws Exception
	 */
	public void clickShareInShareItinerary() throws Exception {
		Utils.waitForElement(driver, txtShareButton_ShareItinerary);
		BrowserActions.clickOnElement(txtShareButton_ShareItinerary, driver, "Share Itinerary Share button");
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
		String shareItineraryPouUpMessageGetTxt = BrowserActions.getText(driver, txtPopupMessage_ShareItinerary,
				"Share Itinerary popup message");
		return shareItineraryPouUpMessageGetTxt;
	}

	/**
	 * To verify Onward Legs is displayed
	 * 
	 * @param links
	 *            : PrevDay and NextDay in Onward leg links
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
	 * 
	 * @param links
	 *            : PrevDay and NextDay in Return leg links
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

	/*
	 * To select Preferred Airline in Airline Filters
	 * 
	 * @param airlineName : Airline Name
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public void selectAirlineInAirlineFilters(String airlineName) throws Exception {
		for (int i = 1; i < txtAirlineName_AirlineFilters.size(); i++) {
			WebElement airlineFareDetails = driver.findElement(
					By.cssSelector("div[ng-show='open_airline'] li:nth-child(" + i + ") span[class='clip-overflow']"));
			BrowserActions.scrollToView(airlineFareDetails, driver);
			String airline = airlineFareDetails.getText().toString().trim();
			if (airlineName.equalsIgnoreCase(airline)) {
				WebElement chkAirline = driver.findElement(
						By.cssSelector("div[ng-show='open_airline'] li:nth-child(" + i + ") span[class='checkbox']"));
				BrowserActions.javascriptClick(chkAirline, driver,
						"Click Airline in Airlines Filters, Selected Airline is:" + airline);
				break;
			} else if (airline == null) {
				Log.event("Preferred Airline(" + airlineName
						+ ") is not available, so its going to click Book Now Button with Random Flights");
			}
		}

	}

	/*
	 * To select Connecting flight or Direct flight in Stops filter
	 * 
	 * @param stops : stops
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */

	public void selectFlightStopsInFilters(String stops) throws Exception {
		for (int i = 1; i < lnkSops_Filters.size(); i++) {
			WebElement stop = driver.findElement(
					By.cssSelector("div[ng-show='open_stop'] li:nth-child(" + i + ") span[class='full bxs txt-ac']"));
			String stopText = stop.getText().toString().trim();
			if (stopText.equalsIgnoreCase(stops)) {
				BrowserActions.clickOnElement(stop, driver, "Click Stop in Stops Filters, Selected Stop is:" + stop);
				break;
			} else if (stopText == null) {
				Log.event("Flight Stop is not available so All flights(connecting and Random) visible by default");
			}
		}

	}

	/**
	 * To click on Book now button in One Way for Domestic Any and Preferred
	 * flights based on filter values
	 * 
	 * @param domain
	 *            : DOM or INTL
	 * @param stops
	 *            : Stops
	 * @param airlines
	 *            : Airline Name
	 * @return
	 * @throws Exception
	 */
	public ReviewPage selectAirlineBookNowInOW(String domain, String stops, String airlines) throws Exception {
		closeINotificationAtTopSRP();
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
				selectAirlineInAirlineFilters(airlines); // Select Preferred
															// Airline in
															// Airline Filters
				clickOnPrefferedFlightsBookNowInOW(1); // select Book Now
				Log.event("Successfully selected " + airlines + " checkbx in Airlines Filter and Clicked BookNow");
			}
			Log.event("Successfully clicked Book Now in SRP");
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
				selectAirlineInAirlineFilters(airlines); // Select Preferred
															// Airline in
															// Airline Filters
				clickOnBookNowInDOM_INTL(1); // select Book Now
				Log.event("Successfully selected " + airlines + " checkbx in Airlines Filter and Clicked BookNow");
			}
			Log.event("Successfully clicked Book Now in SRP");
		}
		popUpAppear();
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
		WebElement wBookNow = driver.findElement(By.xpath("(//div[@data-gaeclist='Search Results Page'])[" + index
				+ "]//li[@class='book-now']//p[@yatratrackable='Flights|Search|Book Type|Book Now']"));
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
		WebElement wBookNow = driver
				.findElement(By.xpath("(//div[@ng-controller='scheduleController']//div[@class='js-flightItem'])["
						+ index + "]//p[@class='new-blue-button .js-bookNow book-btn relative tc']"));
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
		BrowserActions.clickOnElement(lnkFareCall, driver, "Get Fare Alert Link");
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
		BrowserActions.javascriptClick(btnRecentSearch, driver, "Click on Recent Search Button");
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
		for (int i = 0; i < lstFlight.size(); i++) {
			WebElement srt = lstFlight.get(i).findElement(By.cssSelector(
					"article>div[class='my-res-info full']>ul>li[class='book-now']>p[class*='full txt-ar text-error fs-xs line-hn mt5']"));
			if (srt.isDisplayed()) {
				String seat = BrowserActions.getText(driver, srt, "Getting text");
				BrowserActions.javascriptClick(
						lstFlight.get(i)
								.findElement(By.cssSelector(
										"article>footer>ul[class='res-footer-list fl uprcse']>li:not([ng-class*='viewedData'])>a")),
						driver, "Clicked on flight details links");
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
	public void enterNewFlightDetails(String Origin, String Destination) throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.typeOnTextField(txtOriginModifySearch, Origin, driver, "To Enter Origin In Modify Search");
		Thread.sleep(2000);
		BrowserActions.typeOnTextField(txtDestinationModifySearch, Destination, driver,
				"To Enter Destination In Modify Search");
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
	 * To click on Book now button in Round Trip for Domestic Any and Preferred
	 * flights based on filter values
	 * 
	 * @param domain
	 *            : DOM or INTL
	 * @param stops
	 *            : Stops
	 * @param airlines
	 *            : Airline Name
	 * @return
	 * @throws Exception
	 */
	public ReviewPage selectAirlineBookNowInRT(String domain, String stops, String airlines) throws Exception {
		closeINotificationAtTopSRP();
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
				selectAirlineInAirlineFilters(airlines); // Select Preferred
															// Airline in
															// Airline Filters
				BrowserActions.nap(5);
				clickOnBookNowInRT(1, 1); // select Book Now Airlines
				Log.event(
						"Successfully selected " + airlines + " checkbx in Airlines Filter and clicked Book Now - DOM");
			}
			Log.event("Successfully clicked Book Now for Round Trip");

		} else if (domain.equalsIgnoreCase("INTL")) {
			// Select Connecting flight or Direct flight in Stops filter
			if (stops.equalsIgnoreCase("All")) {
				BrowserActions.nap(5);
				Log.event("All flights visible by default");
			} else {
				selectFlightStopsInFilters(stops);
				Log.event("Successfully selected " + stops + " button in Stops Filter");
			}

			// click book now based on Any or Preferred airlines
			if (airlines.equalsIgnoreCase("Any")) {
				BrowserActions.nap(5);
				clickOnBookNowInDOM_INTL(1); // select Book now
				Log.event("All flights details are visible by default and clicked Book Now Random flight -RT");
			} else {
				selectAirlineInAirlineFilters(airlines); // Select Preferred
															// Airline in
															// Airline Filters
				clickOnBookNowInDOM_INTL(1); // select Book Now Airlines
				Log.event("Successfully selected " + airlines + " checkbx in Airlines Filter and clicked Book Now RT ");
			}
			Log.event("Successfully clicked Book Now for Round Trip");
		}
		BrowserActions.nap(3);
		return new ReviewPage(driver).get();
	}

	/**
	 * to click on Book now button in Round Trip for Domestic any flights
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public void clickOnBookNowInRT_DOM(int onwardList, int onwardFlight, int returnList, int returnFlight)
			throws Exception {
		BrowserActions.nap(5);
		WebElement onwardflight = driver.findElement(By.cssSelector(" div[id='resultBoxSlider']>div:nth-child("
				+ onwardList + ")>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child("
				+ onwardFlight
				+ ")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='price']>div[class='full']>div>p[class='new-blue-button fr book-button']:not([class='ng-hide']"));
		WebElement returnflight = driver.findElement(By.cssSelector(" div[id='resultBoxSlider']>div:nth-child("
				+ returnList + ")>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child("
				+ returnFlight
				+ ")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='price']>div[class='full']>div>p[class='new-blue-button fr book-button']:not([class='ng-hide']"));
		BrowserActions.scrollToView(onwardflight, driver);
		BrowserActions.clickOnElement(onwardflight, driver, "To select Flight from one list.");
		BrowserActions.scrollToView(returnflight, driver);
		BrowserActions.clickOnElement(returnflight, driver, "To select Flight from second list.");
		BrowserActions.clickOnElement(btnBookNowRoundTrip, driver,
				"Click on Book Now for RoundTrip for Domestic any flights");

	}

	/**
	 * to click on Book now button in Round Trip for Domestic Preferred flights
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public void clickOnBookNowInRT(int onwardFlight, int returnFlight) throws Exception {
		WebElement onwardflight = driver
				.findElement(By.xpath("//div[@id='resultList_0']//div[@class='js-flightRow js-flightItem']["
						+ onwardFlight + "]//p[@class='new-blue-button fr book-button']"));
		WebElement returnflight = driver
				.findElement(By.xpath("//div[@id='resultList_1']//div[@class='js-flightRow js-flightItem']["
						+ onwardFlight + "]//p[@class='new-blue-button fr book-button']"));
		BrowserActions.scrollToView(onwardflight, driver);
		BrowserActions.clickOnElement(onwardflight, driver, "To select Flight from one list.");
		BrowserActions.scrollToView(returnflight, driver);
		BrowserActions.clickOnElement(returnflight, driver, "To select Flight from second list.");
		BrowserActions.clickOnElement(btnBookNowRoundTrip, driver,
				"Click on Book Now for RoundTrip for Domestic Preferred flights");

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
		WebElement prefferedFlightBookNow = driver
				.findElement(By.xpath("(//div[@id='resultList_0']//div[@class='js-flightRow js-flightItem'][" + index
						+ "])//p[@class='new-blue-button fr book-button js-bookNow relative tc']"));
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
	 * to click on Book Flight Details Link In Round Trip Only 0 or 1 can be
	 * passed in Second argument as o is onward and 1 is return flight
	 * 
	 * @param index
	 *            and i
	 * @return
	 * @throws Exception
	 */
	public void clickOnFlightDetailsInRT(int index, int i) throws Exception {
		BrowserActions.nap(5);
		/*
		 * WebElement onwardflight = driver.findElement(By.cssSelector(
		 * "div[id='resultBoxSlider']>div[id='resultList_" + i +
		 * "']>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child("
		 * + index +
		 * ")>article>footer>ul[class='res-footer-list fl uprcse']>li:not([class='ng-hide'])>a"
		 * ));
		 */
		WebElement onwardflight = driver.findElement(By.cssSelector("div[id='resultBoxSlider']>div[id='resultList_" + i
				+ "']>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child(" + index
				+ ")>article>footer>ul[class='res-footer-list fl uprcse'] a[title='Flight Details']"));
		BrowserActions.scrollToView(onwardflight, driver);
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
	 * to click on Book Now In Flight Details with void
	 * 
	 * @param
	 * 
	 * @throws Exception
	 */
	public void clickOnBookNowInFlightDetail_INTL() throws Exception {
		btnBookNowFlightDeatilPopUp_INTL.click();
	}

	public ArrayList<String> verifyChkBoxAirlineFilter() throws Exception {
		boolean result = false;
		ArrayList<String> airlineName = new ArrayList<String>();
		List<WebElement> Airline = driver
				.findElements(By.cssSelector("span[class='checkbox']>input[analytics*='Filter Option Click|Airline']"));
		List<WebElement> Airline_Nme = driver.findElements(By.cssSelector("span[class='clip-overflow']"));
		for (int i = 0; i < Airline.size(); i++) {
			result = BrowserActions.isRadioOrCheckBoxSelected(Airline.get(i));
			if (result == true) {
				String airline_Name = Airline_Nme.get(i).getText();
				airlineName.add(airline_Name);
				return airlineName;
			}
		}
		return null;
	}

	/**
	 * to Get Text Flight Type(Refundable Or NonRefundable)
	 * 
	 * @return
	 * @param
	 * 
	 * @throws Exception
	 */
	public String getTextFareTypeInFligthDetail_DOM() throws Exception {
		BrowserActions.nap(5);
		String FlightType = txtRefundableAndNonRefundableInFlightDetails.getText();
		return FlightType;
	}

	/*
	 * Getting the text from Prev Day buttons on Onward leg in SRP
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public String getTextOnwardPrevDaySearch() throws Exception {
		Utils.waitForElement(driver, lnkPrevDay_OnwardLeg);
		String prevDayGetTxt = BrowserActions.getTextFromAttribute(driver, lnkPrevDay_OnwardLeg, "title",
				"Onward leg Prev Day title");
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
		String nextDayGetTxt = BrowserActions.getTextFromAttribute(driver, lnkNextDay_OnwardLeg, "title",
				"Onward leg Next Day title");
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
		String prevDayGetTxt = BrowserActions.getTextFromAttribute(driver, lnkPrevDay_ReturnLeg, "title",
				"Return leg Prev Day title");
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
		String nextDayGetTxt = BrowserActions.getTextFromAttribute(driver, lnkNextDay_ReturnLeg, "title",
				"Return leg Next Day title");
		return nextDayGetTxt;
	}

	/**
	 * To click Refundable check box in Fare Type filters
	 * 
	 * @throws Exception
	 */
	public void clickRefundableCheckbox() throws Exception {
		Utils.waitForElement(driver, chkRefundable);
		BrowserActions.clickOnElement(chkRefundable, driver, "Refundable Checkbox");
		Log.event("Clicked Refundable option checkbox");
	}

	/**
	 * To verify the Refundable flights in Flight Grid
	 * 
	 * @throws Exception
	 */
	public boolean verifyRefundableFlight() throws Exception {
		boolean status = false;
		WebElement refundableEle = driver
				.findElement(By.cssSelector("div[class='js-flightRow js-flightItem'] li[ng-if='flt.ft==1']"));
		String flightRow = BrowserActions.getText(driver, refundableEle,
				"Getting txt of the Refundable in flight rows.");
		if (flightRow.equalsIgnoreCase("REFUNDABLE")) {
			status = true;
		} else if (!flightRow.equalsIgnoreCase("REFUNDABLE")) {
			status = false;
		}
		return status;
	}

	/**
	 * To verify the Flight name titles in Result Grid
	 * 
	 * @throws Exception
	 */
	public boolean verifyFlightNameTitlesInResultGrid(String flghtname) throws Exception {
		boolean status = false;
		for (int i = 1; i < lstFlightResultGrid.size(); i++) {
			WebElement Ele = driver.findElement(By.xpath("//div[@class='js-flightRow js-flightItem'][" + i
					+ "]//small[@class='fs-sm gray fl ml5 name carrier-name']"));
			String flightName = BrowserActions.getText(driver, Ele, "Getting txt of the Refundable in flight rows.");
			if (flightName.equalsIgnoreCase(flghtname)) {
				status = true;
			} else if (!flightName.equalsIgnoreCase(flghtname)) {
				status = false;
				break;
			}
		}

		return status;
	}

	/**
	 * To click Reset All in SRP
	 * 
	 * @throws Exception
	 */
	public void clickResetAll() throws Exception {
		Utils.waitForElement(driver, lnkResetAll);
		BrowserActions.clickOnElement(lnkResetAll, driver, "Click Reset All");
		Utils.waitForPageLoad(driver);
		Log.event("Clicked Reset All");
	}

	/**
	 * To verify SRP Menu's
	 * 
	 * @throws Exception
	 */
	public boolean verifySRPMenu(String tripType) throws Exception {
		boolean status = false;
		Utils.waitForElement(driver, lnkHeaders);
		boolean boolHeaders = BrowserActions.isElementPresent(driver, lnkHeaders);
		boolean boolModifySearch = BrowserActions.isElementPresent(driver, btnModifySearchIcon);
		boolean boolFilters = BrowserActions.isElementPresent(driver, lnkFilters);
		boolean boolResultGrid = BrowserActions.isElementPresent(driver, lnkResultGrid);
		// boolean boolSetFareAlerts = BrowserActions.isElementPresent(driver,
		// lnkSetFareAlerts);
		// 02/06/2017 :-Set Fare Alerts(Bell symbol) is Removed in SRP page, so
		// not verified Set Fare alert in SRP
		boolean boolSahreIntinerary = BrowserActions.isElementPresent(driver, lnkShareItinerary);
		boolean boolFooter = BrowserActions.isElementPresent(driver, lnkFooter);

		if (tripType.equals(Constants.C_ONEWAY)) {
			boolean boolAirlineMatrix_OW = BrowserActions.isElementPresent(driver, lnkAirlinematrix_OW);
			if (boolHeaders == true && boolModifySearch == true && boolFilters == true && boolAirlineMatrix_OW == true
					&& boolResultGrid == true && boolSahreIntinerary == true && boolFooter == true) {
				Log.event("Successfully verified SRP Menu for One Way");
				status = true;
			} else {
				status = false;
			}

		} else if (tripType.equals(Constants.C_ROUNDTRIP)) {
			boolean boolAirlineMatrix = BrowserActions.isElementPresent(driver, lnkAirlinematrix);
			boolean boolOLL = BrowserActions.isElementPresent(driver, lnkOnwardLFF);
			boolean boolPrevDay = BrowserActions.isElementPresent(driver, lnkPrevDay_OnwardLeg);
			boolean boolNextDay = BrowserActions.isElementPresent(driver, lnkNextDay_OnwardLeg);
			boolean boolRLL = BrowserActions.isElementPresent(driver, lnkReturnLFF);
			if (boolHeaders == true && boolModifySearch == true && boolFilters == true && boolAirlineMatrix == true
					&& boolPrevDay == true && boolNextDay == true && boolOLL == true && boolRLL == true
					&& boolResultGrid == true && boolSahreIntinerary == true && boolFooter == true) {
				Log.event("Successfully verified SRP Menu for Round Trip");
				status = true;
			} else {
				status = false;
			}

		} else if (tripType.equals(Constants.C_MULTICITY)) {
			boolean boolAirlineMatrix = BrowserActions.isElementPresent(driver, lnkAirlinematrix);
			boolean boolPrevDay = BrowserActions.isElementPresent(driver, lnkPrevDay_OnwardLeg);
			boolean boolNextDay = BrowserActions.isElementPresent(driver, lnkNextDay_OnwardLeg);
			if (boolHeaders == true && boolModifySearch == true && boolFilters == true && boolAirlineMatrix == true
					&& boolPrevDay == true && boolNextDay == true && boolResultGrid == true
					&& boolSahreIntinerary == true && boolFooter == true) {
				Log.event("Successfully verified SRP Menu for Multicity");
				status = true;
			} else {
				status = false;
			}
		}
		Utils.waitForPageLoad(driver);
		return status;
	}

	/**
	 * scrolling the Amount slider
	 * 
	 * @param value
	 */
	public void scrollSliderOfFilterAmount(int value) {
		Actions action = new Actions(driver);
		action.dragAndDropBy(AmountSlider, value, 0).build().perform();
	}

	/*
	 * To verify Result Grid Coulmns
	 * 
	 * @throws Exception
	 */
	public boolean verifyResultGridColumns() throws Exception {
		boolean status = false;
		Utils.waitForElement(driver, lnkAirlineColumn);
		boolean boolAirline = BrowserActions.isElementPresent(driver, lnkAirlineColumn);
		boolean boolDepart = BrowserActions.isElementPresent(driver, lnkDepartColumn);
		boolean boolArrive = BrowserActions.isElementPresent(driver, lnkArriveColumn);
		boolean boolDuration = BrowserActions.isElementPresent(driver, lnkDurationColumn);
		boolean boolPrice = BrowserActions.isElementPresent(driver, lnkPriceColumn);
		if (boolAirline == true && boolDepart == true && boolArrive == true && boolDuration == true
				&& boolPrice == true) {
			Log.event("Successfully verified Result Grid Coulmns");
			status = true;
		} else {
			status = false;
		}
		Utils.waitForPageLoad(driver);
		return status;
	}

	/**
	 * Getting the text from Passenger class Drop down in Modify Search panel
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getResultGridColumns() throws Exception {
		List<String> resultGridCoulmns = new ArrayList<String>();
		String airline = BrowserActions.getText(driver, lnkAirlineColumn, "Airline Column Text");
		String depart = BrowserActions.getText(driver, lnkDepartColumn, "Airline Column Text");
		String arrive = BrowserActions.getText(driver, lnkArriveColumn, "Airline Column Text");
		String duration = BrowserActions.getText(driver, lnkDurationColumn, "Airline Column Text");
		String price = BrowserActions.getText(driver, lnkPriceColumn, "Airline Column Text");
		resultGridCoulmns.add(airline);
		resultGridCoulmns.add(depart);
		resultGridCoulmns.add(arrive);
		resultGridCoulmns.add(duration);
		resultGridCoulmns.add(price);
		Log.event("Result Grid Headers coulmns are : " + resultGridCoulmns);
		return resultGridCoulmns;
	}

	/**
	 * Click On Undo Button
	 *
	 */
	public void ClickOnUndoButton() {
		Utils.waitForPageLoad(driver);
		btnUndoLastFilter.click();
	}

	/**
	 * To verify airline Price is displayed according to the amount Filter
	 * Applied
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyAirlinePriceAccToFilterApplied() throws Exception {
		boolean flag = false;
		List<WebElement> lmtAmt = driver
				.findElements(By.cssSelector("[ng-show='open_price']>div>rzslider>span[class='rz-bubble']"));
		String minLmt = BrowserActions.getText(driver, lmtAmt.get(0), "Next Day title");
		String minLmt1 = minLmt.trim().replace("Rs.", "").replace(",", "").trim().replace(" ", "");
		int min_Limit = Integer.parseInt(minLmt1);
		String maxLmt = BrowserActions.getText(driver, lmtAmt.get(1), "Next Day title").trim().replace("Rs.", "")
				.replace(",", "").trim().replace(" ", "");
		int max_Limit = Integer.parseInt(maxLmt);
		List<WebElement> lstPrice = driver.findElements(By.cssSelector("p[title='Fare Summary']"));
		for (int i = 5; i < lstPrice.size(); i++) {
			String currentPrice = BrowserActions.getText(driver, lstPrice.get(i), "getting Price.").trim()
					.replace("Rs.", "").replace(",", "").trim().replace(" ", "");
			int current_Price = Integer.parseInt(currentPrice);
			if (current_Price >= min_Limit && current_Price <= max_Limit) {
				return !flag;
			}
		}
		return flag;

	}

	/**
	 * To Select Flight Less then 3 Hrs
	 *
	 * @throws Exception
	 */
	public void selectFlightLessThen3Hrs() throws Exception {
		BrowserActions.nap(20);
		int Arrival_Time = 0;
		List<WebElement> arrival_Time1 = driver.findElements(By.cssSelector(
				"#resultBoxSlider>div[id='resultList_0']>div[class='results']>div[class='js-flightRow js-flightItem']>article>div[class='my-res-info full']>ul>li[class='timing']>div[class='end']>span"));
		WebElement deptT = driver.findElement(By.cssSelector(
				"#resultBoxSlider>div[id='resultList_1']>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child(1)>article>div[class='my-res-info full']>ul>li[class='timing']>div[class='start']>span"));
		String deptTime = deptT.getText();

		String deptTime1 = deptTime.replace(":", "").trim();
		int Dept_Time = Integer.parseInt(deptTime1);
		for (int i = 0; i < arrival_Time1.size(); i++) {
			String arrivalTime = BrowserActions.getText(driver, arrival_Time1.get(i), "Arrival Time from the list 1");
			String arrivalTime1 = arrivalTime.trim().replace(":", "");
			Arrival_Time = Integer.parseInt(arrivalTime1);
			int timeDifference = Dept_Time - Arrival_Time;
			if (timeDifference <= 60 && timeDifference >= 0) {
				BrowserActions.scrollToView(arrival_Time1.get(i), driver);
				BrowserActions.clickOnElement(arrival_Time1.get(i), driver, "selected flight from the list1");
				BrowserActions.scrollToView(deptT, driver);
				BrowserActions.clickOnElement(deptT, driver, "selected flight from the list2");
			}
		}
	}

	/**
	 * Getting the text from Book Now If Time between RT flights on the same day
	 * is less the 3 hrs
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextErrorMessageOnBookNowIfTimeIsLessThen3Hrs() throws Exception {
		String ErrorMessage = BrowserActions.getText(driver, txtErrorMessageFlightDifference,
				"Getting the text from Book Now If Time between RT flights on the same day is  less the 3 hrs");
		return ErrorMessage;
	}

	/**
	 * Getting the text from Book Now If Time between RT flights on the same day
	 * is less the 3 hrs
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFreeMeal_INTL() throws Exception {
		BrowserActions.scrollToView(logoFreeMeal, driver);
		BrowserActions.mouseHover(driver, logoFreeMeal);
		String Message = BrowserActions.getText(driver, txtFreeMeal, "Free Meal Message");
		return Message;
	}

	/**
	 * To Verify Free Meal options In SpiceJet,GoAir,Indigo
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyFreeMealOption() throws Exception {
		boolean flag = false;
		List<WebElement> FlightNumber = driver.findElements(By.cssSelector(
				"div[class='result-holder multi-1']>div>div[ng-show='flt.show']>article>div[class='my-res-info full']>ul>li>small[class='fs-10 ltr-gray fl ml5 nowrap']"));
		for (int i = 0; i < FlightNumber.size(); i++) {
			String FlightNumber1 = BrowserActions.getText(driver, FlightNumber.get(i), "Getting Flight Number")
					.substring(0, 2);
			String Number1 = "6E";
			String Number2 = "SG";
			String Number3 = "G8";
			if (FlightNumber1.equals(Number1) || FlightNumber1.equals(Number2) || FlightNumber1.equals(Number3)) {
				txtFreeMeal_DOM.isDisplayed();
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * To verify Price Sort arrows are displayed
	 * 
	 * @param links
	 *            : Price Sort arrows are displayed with Upwards and Downwards
	 * @return
	 * @throws Exception
	 */
	public boolean verifyPriceSorting(String priceSortArrow) throws Exception {
		boolean status = false;
		if (priceSortArrow == "Upwards") {
			Utils.waitForElement(driver, lnkPriceUpwards);
			if (BrowserActions.isElementPresent(driver, lnkPriceUpwards) == true) {
				status = true;
			}
		} else if (priceSortArrow == "Downwards") {
			Utils.waitForElement(driver, lnkPriceDownwards);
			if (BrowserActions.isElementPresent(driver, lnkPriceDownwards) == true) {
				status = true;
			}
		}
		return status;
	}

	/**
	 * To click Price Sort arrow
	 * 
	 * @throws Exception
	 */
	public void clickPriceSortArrow() throws Exception {
		Utils.waitForElement(driver, lnkPriceUpwards);
		BrowserActions.clickOnElement(lnkPriceUpwards, driver, "Click Price Sort Arrow");
		Utils.waitForPageLoad(driver);
		Log.event("Clicked Price Sort Arrow");
	}

	/**
	 * Getting the text from Selected Flight
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSelectedFlight() throws Exception {
		Utils.waitForPageLoad(driver);
		String Message = BrowserActions.getText(driver, selectionDivision, "Selected Flight");
		return Message;
	}

	/**
	 * Clicking On Book Now In RoundTrip
	 * 
	 * @return
	 * @throws Exception
	 */

	public void ClickOnBookNow_DOM_RT() throws Exception {
		Utils.waitForPageLoad(driver);
		btnBookNowRoundTrip.click();
	}

	/**
	 * Getting the text from Error Message After Applying Filter
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextErrorMessageAfterApplyingFilter() throws Exception {
		Utils.waitForPageLoad(driver);
		String errorMessage = BrowserActions.getText(driver, errorMessageAfterApplyingFilter, "Error Message");
		return errorMessage;
	}

	//

	/**
	 * Getting the text from Selection Box RT Domestic
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFareSummarySelectionBox_DOM() throws Exception {
		Utils.waitForPageLoad(driver);
		Thread.sleep(20000);
		BrowserActions.scrollToView(priceSelectionBox, driver);
		BrowserActions.mouseHover(driver, priceSelectionBox);
		String errorMessage = BrowserActions.getText(driver, fareSummaryPopUp, "Fare Summary");
		return errorMessage;
	}

	/**
	 * Selecting the Stops Filter
	 * 
	 * @return
	 * @throws Exception
	 */
	public void selectStopsFilter(int NumberOfStops) throws Exception {
		Utils.waitForPageLoad(driver);
		for (int i = 0; i < stopsFilter.size(); i++) {
			String Filters = BrowserActions.getText(driver, stopsFilter.get(i), "Fare Summary");
			int Filter = Integer.parseInt(Filters);
			if (Filter == (NumberOfStops)) {
				BrowserActions.javascriptClick(stopsFilter.get(i), driver, "Selected Filter");
				break;
			}
		}
	}

	public void ClickOnCrossInRecentSearch() throws Exception {
		BrowserActions.mouseHover(driver, btnRecentSearch);
		BrowserActions.mouseHover(driver, fareRecentSearch);
		BrowserActions.clickOnElement(lnkcloseFlightDetailsPopUp, driver, "Close [X] Button Recent Search");
	}

	/**
	 * Getting Pax Details From Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> getTextPaxDetails() throws Exception {
		ArrayList<String> PaxDetails = new ArrayList<String>();
		for (int i = 0; i < txtPaxDeatilInModify.size(); i++) {
			String PaxDetail = BrowserActions.getText(driver, txtPaxDeatilInModify.get(i),
					"Getting name of Airlines In Pop Up");
			PaxDetails.add(PaxDetail);
		}
		return PaxDetails;
	}

	/**
	 * To verify Price Sort arrows are displayed
	 * 
	 * @param links
	 *            : Price Sort arrows are displayed with Upwards and Downwards
	 * @return
	 * @throws Exception
	 */
	public boolean verifySelectedFlightInCurrentSelectionBox(String selection) throws Exception {
		boolean status = false;
		if (selection == "Onward") {
			Utils.waitForElement(driver, lnkOnwardFlightSelection);
			if (BrowserActions.isElementPresent(driver, lnkOnwardFlightSelection) == true) {
				status = true;
			}
		} else if (selection == "Return") {
			Utils.waitForElement(driver, lnkReturnFlightSelection);
			if (BrowserActions.isElementPresent(driver, lnkReturnFlightSelection) == true) {
				status = true;
			}
		}
		return status;
	}

	public int getSizeofResult() throws Exception {
		Utils.waitForPageLoad(driver);
		List<WebElement> abc = driver.findElements(By.cssSelector(
				"#resultBoxSlider>div[id='resultList_0']>div[class='results']>div[class='js-flightRow js-flightItem']>article>div[class='my-res-info full']>ul>li[class='timing']>div[class='end']>span"));
		return abc.size();
	}

	public String getTextDepatureDate() throws Exception {
		Utils.waitForPageLoad(driver);
		String date = txtDepartureDate.getText();
		return date;
	}

	/**
	 * Getting the text from result grid column headers titles
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getPriceFromSelectedFlightInCurrentSelectionBox() throws Exception {
		List<String> price = new ArrayList<String>();
		String onwardFare = BrowserActions.getText(driver, txtOnwardsSelectedFlightFare,
				"Onward Seleccted Flight Fare");
		String returnFare = BrowserActions.getText(driver, lnkReturnSelectedFlightFare, "Return Seleccted Flight Fare");
		price.add(onwardFare);
		price.add(returnFare);
		Log.event("Price From Selected Flight In Current Selection Box : " + price);
		return price;
	}

	/**
	 * Getting the text from Origin in Modify Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDepartureDate() throws Exception {
		Utils.waitForElement(driver, txtDepartureDate);
		String departureDate = BrowserActions.getText(driver, txtDepartureDate, "Onward Selected Flight Fare");
		return departureDate;
	}

	/**
	 * To click Modify Search link in SRP
	 * 
	 * @throws Exception
	 */
	public void clickOnwardNextDayButton() throws Exception {
		// BrowserActions.nap(10);
		Utils.waitForElement(driver, lnkNextDay_OnwardLeg);
		BrowserActions.clickOnElement(lnkNextDay_OnwardLeg, driver, "Onward leg NextDay Button");
		BrowserActions.nap(3);
		Utils.waitForPageLoad(driver);
		Log.event("Clicked NextDay Button");
	}

	/**
	 * To click Modify Search link in SRP
	 * 
	 * @throws Exception
	 */
	public void clickOnwardPrevDayButton() throws Exception {
		BrowserActions.nap(10);
		Utils.waitForElement(driver, lnkPrevDay_OnwardLeg);
		BrowserActions.clickOnElement(lnkPrevDay_OnwardLeg, driver, "Onward leg Prev Day Button");
		BrowserActions.nap(3);
		Utils.waitForPageLoad(driver);
		Log.event("Clicked Prev Day Button");
	}

	/**
	 * To verify Result Grid Column headers
	 * 
	 * @param headers
	 *            : Headers for Upper and Lower Part
	 * @return
	 * @throws Exception
	 */
	public boolean verifyResultGridColumHeaders(String headers) throws Exception {
		boolean status = false;
		if (headers == "Upper") {
			Utils.waitForElement(driver, upperPartResultGridHeader);
			if (BrowserActions.isElementPresent(driver, upperPartResultGridHeader) == true) {
				status = true;
			}
		} else if (headers == "Lower") {
			Utils.waitForElement(driver, lowerPartResultGridHeader);
			if (BrowserActions.isElementPresent(driver, lowerPartResultGridHeader) == true) {
				status = true;
			}
		}
		return status;
	}

	/**
	 * Clicking Continue In Price Increase Pop Up
	 * 
	 * @return
	 * @throws Exception
	 */

	// TODO : Need to look on - @Narayana
	/*
	 * public void popUpAppear() throws Exception { if
	 * (PricePopUp.isDisplayed()) { if(BrowserActions.isElementVisible(driver,
	 * btnFareChangeContinue)){
	 * BrowserActions.clickOnElement(btnFareChangeContinue, driver,
	 * "Clicked on continue in Popup"); }else
	 * BrowserActions.clickOnElement(ContinueInFarePopUp, driver,
	 * "Clicked on continue in Popup"); }else if (popupFareChange.isDisplayed())
	 * if (ContinueInFareChangeAlertPopUp.isDisplayed()) {
	 * BrowserActions.clickOnElement(ContinueInFareChangeAlertPopUp, driver,
	 * "Clicked on continue in Fare Change Alert Popup"); } else if
	 * (ContinueInpopUpFareSlashed.isDisplayed()) {
	 * BrowserActions.clickOnElement(ContinueInpopUpFareSlashed, driver,
	 * "Clicked on continue in fare slashed popup"); } else Log.event(
	 * "No PopUp appear."); }
	 */

	// TODO : Need to look on - @Narayana
	public void popUpAppear() throws Exception {
		if (PricePopUp.isDisplayed()) {
			if (BrowserActions.isElementVisible(driver, btnFareChangeContinue)) {
				BrowserActions.clickOnElement(btnFareChangeContinue, driver, "Clicked on continue in Popup");
			} else
				BrowserActions.clickOnElement(ContinueInFarePopUp, driver, "Clicked on continue in Popup");
		} else if (popupFareChange.isDisplayed()) {
			if (BrowserActions.isElementVisible(driver, ContinueInFareChangeAlertPopUp)) {
				BrowserActions.clickOnElement(ContinueInFareChangeAlertPopUp, driver,
						"Clicked on continue in Fare Change Alert Popup");
			} else if (BrowserActions.isElementVisible(driver, ContinueInpopUpFareSlashed)) {
				BrowserActions.clickOnElement(ContinueInpopUpFareSlashed, driver,
						"Clicked on continue in fare slashed popup");
			} else
				Log.event("No PopUp appear.");
		}
	}

	// *************************Fresco Related
	// functions*********************************

	/**
	 * Getting the text from Resent Search popup details
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getRecentSearchPopupDetails() throws Exception {
		BrowserActions.mouseHover(driver, btnRecentSearch);
		String departureDate = BrowserActions.getText(driver, txtResentSearchDetails, "Resent Search Popup details");
		return departureDate;
	}

	/**
	 * To click Yatra Logo on Search Result page
	 * 
	 * @throws Exception
	 */
	// TODO: Remove function after changing object fresco to homePage
	public Fresco clickYatraLogo_Fresco() throws Exception {
		BrowserActions.actionClick(lnkYatraLogo, driver, "Yatra Logo");
		Utils.waitForPageLoad(driver);
		Log.event("Successfully clicked Yatra Logo link in SRP");
		// BrowserActions.nap(10);
		return new Fresco(driver).get();

	}

	@FindBy(css = "div[class='recent-iternary-detail']")
	private WebElement cityNameInRecentSearchPopUp;

	/**
	 * getting cities name from recent search Pop up
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCityNameInRecentSearch() throws Exception {
		String name = null;
		if (cityNameInRecentSearchPopUp.isDisplayed()) {
			BrowserActions.mouseHover(driver, cityNameInRecentSearchPopUp);
			name = BrowserActions.getText(driver, cityNameInRecentSearchPopUp,
					"Getting cities name from recent search Pop up");
		}
		return name;
	}

	public String selectAirlineBookNowInOWE2E(String airlines, int index) throws Exception {
		closeINotificationAtTopSRP();
		Thread.sleep(3000);
		String Price = null;
		// click book now based on Any or Preferred airlines
		if (airlines.equalsIgnoreCase("Any")) {
			clickOnBookNowInOW(2); // select Book now
			Log.event("All flights details are visible by default and Clicked BookNow Random flight");
		} else {
			selectAirlineInAirlineFilters(airlines); // Select Preferred Airline
														// in Airline Filters
			WebElement price1 = driver.findElement(By.cssSelector("div[data-gaecposition='" + index + "'] label"));
			if (price1.isDisplayed()) {
				Price = price1.getText();
			}
			driver.findElement(By.cssSelector("div[data-gaecposition='" + index
					+ "'] p[class='new-blue-button fr book-button js-bookNow relative tc']")).click();
			Log.event("Successfully selected " + airlines + " checkbx in Airlines Filter and Clicked BookNow");
		}
		Log.event("Successfully clicked Book Now in SRP");
		return Price;
	}

	/**
	 * to click on Book now button in One Way for INTL flights
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public String clickOnBookNowInOWINTL_E2E(String airlines, int index) throws Exception {
		String Price_final = null;
		if (airlines.equalsIgnoreCase("Any")) {
			BrowserActions.nap(5);
			clickOnBookNowInDOM_INTL(1); // select Book now
			Log.event("All flights details are visible by default and clicked Book Now Random flight -RT");
		} else {
			selectAirlineInAirlineFilters(airlines); // Select Preferred Airline in Airline Filters
			BrowserActions.nap(5);
			WebElement wBookNow = driver
					.findElement(By.xpath("(//div[@ng-controller='scheduleController']//div[@class='js-flightItem'])["+index+"]//p[@class='new-blue-button .js-bookNow book-btn relative tc']"));
			WebElement wflightPrice = driver
					.findElement(By.xpath("(//div[@ng-controller='scheduleController']//div[@class='js-flightItem'])["+index+"]//p[@title='Fare Summary']"));
			String flightPrice = BrowserActions.getText(driver, wflightPrice, "SRP Page Flight fare");
			Price_final = flightPrice.trim().replace(" ", "").trim();
			BrowserActions.nap(2);
			BrowserActions.scrollToView(wBookNow, driver);
			BrowserActions.javascriptClick(wBookNow, driver, "Book Now");
		}
			return Price_final;
		}
	/**
	 * getting cities name from recent search Pop up
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSearchResultPageURL() throws Exception {
		String sURL = driver.getCurrentUrl();
		return sURL;
	}

	/**
	 * @author Narayana
	 * @Description: to verify param in URL
	 * @param sParam:
	 *            the prameter you want to verify
	 * @param sUrl:
	 *            pass current page() url after searching flight
	 */
	public List<String> getParamValueFromCurrnetPageURL() {
		List<String> sPram_Values = new ArrayList<String>();
		String sUrl = driver.getCurrentUrl();
		String sPram_Value = "";

		if (sUrl.contains(Constants.c_Params)) {
			int start = sUrl.indexOf(Constants.c_Params);
			sPram_Value = sUrl.substring(start).split("/")[1];
			sPram_Values.add(sPram_Value);
		}
		if (sUrl.contains(Constants.c_TriggerType)) {
			int start = sUrl.indexOf(Constants.c_TriggerType);
			sPram_Value = sUrl.substring(start).split("&")[0].split("=")[1].trim();
			sPram_Values.add(sPram_Value);
		}
		if (sUrl.contains(Constants.c_Origin)) {
			int start = sUrl.indexOf(Constants.c_Origin);
			sPram_Value = sUrl.substring(start).split("&")[0].split("=")[1].trim();
			sPram_Values.add(sPram_Value);
		}
		if (sUrl.contains(Constants.c_OriginCountry)) {
			int start = sUrl.indexOf(Constants.c_OriginCountry);
			sPram_Value = sUrl.substring(start).split("&")[0].split("=")[1].trim();
			sPram_Values.add(sPram_Value);
		}
		if (sUrl.contains(Constants.c_Destination)) {
			int start = sUrl.indexOf(Constants.c_Destination);
			sPram_Value = sUrl.substring(start).split("&")[0].split("=")[1].trim();
			sPram_Values.add(sPram_Value);
		}
		if (sUrl.contains(Constants.c_DestinationCountry)) {
			int start = sUrl.indexOf(Constants.c_DestinationCountry);
			sPram_Value = sUrl.substring(start).split("&")[0].split("=")[1].trim();
			sPram_Values.add(sPram_Value);
		}
		if (sUrl.contains(Constants.c_NonStop)) {
			int start = sUrl.indexOf(Constants.c_NonStop);
			sPram_Value = sUrl.substring(start).split("&")[0].split("=")[1].trim();
			sPram_Values.add(sPram_Value);
		}
		return sPram_Values;
	}

	// *******************************End of SRP
	// Functions******************************/

} // SearchResult
