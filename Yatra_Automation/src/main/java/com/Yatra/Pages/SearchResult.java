
package com.Yatra.Pages;

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

public class SearchResult extends LoadableComponent<SearchResult> {

	private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Search Result Page ***********************************
	 **********************************************************************************************/

	@FindBy(css = ".ico-newHeaderLogo")
	public WebElement headerLogo;

	@FindBy(css = ".filter-list.filter-list-with-clear:nth-child(1)>label>div>span")
	WebElement chkChooseFlightFirst;

	@FindBy(css = ".filter-list.filter-list-with-clear:nth-child(1)>label>span:nth-child(2)")
	WebElement preferredFlightName;

	@FindBy(css = "[class ='ico-newHeaderLogo']")
	public WebElement logoYatra;

	@FindBy(xpath = "//ul[@class='matrix-slide-list tabs matrix-ul']/li[2]")
	public WebElement matrixStrip;

	@FindBy(css = "[class='ico fl ico-gray-modify-search']")
	public WebElement btnModifySearchIcon;

	@FindBy(css = "div[class='full']>div[class='matrix-wrapper day-matrix new-theme day-matrix-responsive']")
	WebElement weeklyStrip;

	@FindBy(css = "[class='js-flightRow js-flightItem']")
	List<WebElement> btnBookNow;

	@FindBy(css = "p[class='new-blue-button .js-bookNow book-btn relative tc']")
	WebElement btnBookNowINT;

	@FindBy(css = "div[ng-controller='productFareDetailsController']")
	WebElement moduleFareDetails;

	@FindBy(css = "div[class='show-result multi-1']>div>div[class='results']>div:nth-child(1)>article>div[class='my-res-info full']>ul>li>small:nth-child(2)")
	WebElement firstAirlineName_OW_DOM;
	//
	// @FindBy(css =
	// "div[class='js-flightItem']:nth-child(2)>article>div[class='full
	// airlines-deals-holder bxs hidden-sm']>div[class='ib airlines-info
	// hidden-sm']>p")
	// WebElement firstAirlineName_RT_INTL;
	//
	// @FindBy(css =
	// "div[class='js-flightItem']:nth-child(2)>article>div[class='full
	// result-card-content']>ul>li:nth-child(1)>div>p[class='full
	// airline-name']")
	// WebElement firstAirlineName_OW_INTL;
	//
	// @FindBy(css =
	// "#resultBoxSlider>div[id='resultList_0']>div[class='results']>div:nth-child(1)>article>div[class='my-res-info
	// full']>ul>li:nth-child(1)")
	// WebElement firstAirlineName_RT_DOM_Left;
	//
	// @FindBy(css =
	// "#resultBoxSlider>div[id='resultList_0']>div[class='results']>div:nth-child(1)>article>div[class='my-res-info
	// full']>ul>li:nth-child(1)")
	// WebElement firstAirlineName_RT_DOM_Right;

	@FindBy(css = "div[class='js-flightItem']:nth-child(2)>article>div[class='full lob-inclusions bxs hidden-md']>div[class='inc-rgt']>ul>li>a[title='Flight Details']")
	WebElement lnkFlightDetails_INTL;

	@FindBy(css = "#resultBoxSlider>div[id='resultList_0']>div[class='results']>div:nth-child(1)>article>footer>ul[class='res-footer-list fl uprcse']")
	WebElement lnkFlightDetails_DOM;

	@FindBy(css = "div[class='new-green-button fr relative tc']")
	WebElement btnBookNowRoundTrip;

	@FindBy(css = "div[class='show-result multi-1']>div>div[class='results']>div:nth-child(1)>article>footer>ul[class='res-footer-list fl uprcse']>li:nth-child(1)")
	WebElement lnkFlightDetails;

	@FindBy(css = "div[class='short-details']>h3>p")
	WebElement btnBookNowFlightDeatilPopUp;

	@FindBy(css = "div[class='short-details']>div")
	WebElement fldContentFlightDetails;

	@FindBy(css = "div[class='my-fare-grid']>ul[class='tab fs-md']>li[ng-hide='isBYOPPage']")
	WebElement lnkFareSummaryandRules;

	@FindBy(css = ".one-fourth")
	WebElement fldContentFareDeatilAndRulesDetails;

	@FindBy(css = "div[class*='disclaimer']>span")
	WebElement txtDisclaimer;

	@FindBy(css = "div[class='my-fare-grid']>ul[class='tab fs-md']>li:nth-child(3)")
	WebElement lnkBaggage;

	@FindBy(css = "div[class='row baggage-summary']")
	WebElement fldContentBaggageDetail;
	
	@FindBy(css = "#userSignInStrip a.dropdown-toggle")
	WebElement txtMyAccount;
	
	@FindBy(css = "#cutomerSupportNav a.dropdown-toggle.eventTrackable")
	WebElement txtSupport;
	
	@FindBy(css = "#discountHdrLink")
	WebElement txtSplDeals;
	
	@FindBy(css = "#recentSearch-wrapper a.dropdown-toggle")
	WebElement txtRecentSearch;
	
	@FindBy(css = "a.logo")
	WebElement lnkYatraLogo;
	
	@FindBy(css = "#userShowName")
	WebElement txtUserAcctName;
	
	@FindBy(css = ".simple-dropdown")
	WebElement txtMyBookings;
	
	@FindBy(css = "#signInBtn")
	WebElement txtLogin;
	
	@FindBy(css = ".simple-dropdown.login-li.clearfix>span>a")
	WebElement txtSignUp;
	
	@FindBy(css = "a.simple-tab.eventTrackable.uniqueDate")
	WebElement txtCorporateLogin;
	
	@FindBy(xpath = "//a[contains(text(),'Agent Login')]")
	WebElement txtAgentLogin;
	
	@FindBy(css = "div[class='left fl']>p[class='fs-12']")  
	WebElement txtTotalFlightSearch;

	@FindBy(css = ".left.fl p.fs-10.ltr-gray.uprcse.mt2")
	WebElement txtFlightSearchDuration;
	
	@FindBy(css = "div[class='fl center']>ul[class='full city-details tripO']>li:nth-child(1)>p[class='city-name']")   
	WebElement txtSourceCity_OW;
	
	@FindBy(css = "div[class='fl center']>ul[class='full city-details tripO']>li:nth-child(1)>p[class*='fs-10']")   
	WebElement txtSourceDate_OW;
	
	@FindBy(css = "div[class='fl center']>ul[class='full city-details tripO']>li:nth-child(2)>p[class='city-name']")  
	WebElement txtDestCity_OW;
	
	@FindBy(css = "ul[class='full city-details tripR']>li:nth-child(1)>p[class='city-name']")   
	WebElement txtSourceCity;
	
	@FindBy(css = "ul[class='full city-details tripR']>li:nth-child(1)>p[class*='fs-10']")   
	WebElement txtSourceDate;
	
	@FindBy(css = "ul[class='full city-details tripR']>li:nth-child(2)>p[class='city-name']")  
	WebElement txtDestCity;
	
	@FindBy(css = "ul[class='full city-details tripR']>li:nth-child(2)>p[class*='fs-10']")   
	WebElement txtDestDate;	
	
	@FindBy(css = "div[class*='center']>ul:nth-child(2)>li:nth-child(1)>p[class='city-name tl']")   
	WebElement txtStartSourceCity;
	
	@FindBy(css = "div[class*='center']>ul:nth-child(2)>li:nth-child(1)>p[class*='fs-10']")   
	WebElement txtStartSourceDate;
	
	@FindBy(css = "div[class*='center']>ul:nth-child(2)>li:nth-child(2)>p[class='fl city-name tl']")  
	WebElement txtstartDestCity;
	
	@FindBy(css = "div[class*='center']>ul:nth-child(3)>li:nth-child(1)>p[class='city-name tl']")   
	WebElement txtEndSourceCity;
	
	@FindBy(css = "div[class*='center']>ul:nth-child(3)>li:nth-child(1)>p[class*='fs-10']")   
	WebElement txtEndSourceDate;
	
	@FindBy(css = "div[class*='center']>ul:nth-child(3)>li:nth-child(2)>p[class='fl city-name tl']")  
	WebElement txtEndDestCity;
	
	@FindBy(css = "div[id='resultList_0']>div:nth-child(3)>div:nth-child(1) div[class='time']>span")   
	WebElement txtFlightDuration;
	
	@FindBy(css = "div[id='resultList_0']>div:nth-child(3)>div:nth-child(1) a[class='under-link']")  
	WebElement lnkFlightDetail;
	
	@FindBy(css = "div[class='row itinerary-details'] li[class='trip-type'] time")  
	WebElement txtFlightDetailsPopupDuration;
	
	@FindBy(css = "span[class='ico ico-close overlay-close']")  
	WebElement lnkcloseFlightDetailsPopUp;
	
	
	@FindBy(css = "div[id='resultList_0']>div:nth-child(3)>div:nth-child(1) span[class='ml5 hidden-sm']")  
	WebElement txtResultStripView;
	
	
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
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public ReviewPage clickOnBookNowInOneWay(int index) throws Exception {
		WebElement e = driver.findElement(By.cssSelector(
				"div[id='resultBoxSlider']>div>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child("
						+ index
						+ ")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='book-now']>div>p[class='new-blue-button fr book-button js-bookNow relative tc']"));
		BrowserActions.scrollToView(e, driver);
		BrowserActions.clickOnElement(e, driver, "To click on Book now button.");
		return new ReviewPage(driver).get();
	}

	public String preferredFlightFirst() throws Exception {
		Utils.waitForElement(driver, chkChooseFlightFirst);
		BrowserActions.scrollToView(chkChooseFlightFirst, driver);
		BrowserActions.javascriptClick(chkChooseFlightFirst, driver, "Choosed Preferred Flight");
		Utils.waitForPageLoad(driver);
		String name = BrowserActions.getText(driver, preferredFlightName, "Flight Name");
		return name;
	}

	public void clickAirlineMatrix() throws Exception {
		BrowserActions.clickOnElement(matrixStrip, driver, "Airline Matrix Strip");
		Utils.waitForPageLoad(driver);

	}

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
	 * Getting the text from the flight Deatil Pop Up
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
	 * to click on Book now button in OneWay Trip for International flights
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public void clickOnlnkFareandRule() throws Exception {
		BrowserActions.scrollToView(lnkFareSummaryandRules, driver);
		BrowserActions.clickOnElement(lnkFareSummaryandRules, driver, "Link Fare And Rule In Flight Detail Pop Up");

	}

	/**
	 * Getting the text from the flight fare and rules Deatil Pop Up
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
	 * Getting the text from the flight fare and rules Deatil Pop Up
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
		BrowserActions.scrollToView(lnkFlightDetails_INTL, driver);
		BrowserActions.clickOnElement(lnkFlightDetails_INTL, driver, "Link Flight Details For International One Way");

	}
	//

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

	//
	/**
	 * Getting the text from the flight fare and rules Deatil Pop Up
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
	 * Getting the text from My Acccount in SRP page 
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
	public String getTextFromAYatraLogo() throws Exception {
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
	 * Getting the text from Recent Search in SRP  
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
	
	
  //*******************************End of SRP Functions********************************************************************************************

} // SearchResult