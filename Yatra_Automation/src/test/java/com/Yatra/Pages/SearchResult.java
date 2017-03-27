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
	 ********************************* WebElements of Yatra Search Page ***********************************
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
	
	@FindBy(css = ".ico-newHeaderLogo")
	WebElement lnkYatraLogo1;
	
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
		String myAccountGetTxt = BrowserActions.getText(driver,	txtSupport, "Support Text Should be displayed in SRP Page");
		return myAccountGetTxt;
	}
	
	/**
	 * Getting the text from Special Deals in SRP page 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSplDeals() throws Exception {
		String myAccountGetTxt = BrowserActions.getText(driver,	txtSplDeals, "Special Deals Text Should be displayed in SRP Page");
		return myAccountGetTxt;
	}
	
	/**
	 * Getting the text from Recent Search in SRP page 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextRecentSearch() throws Exception {
		String myAccountGetTxt = BrowserActions.getText(driver,	txtRecentSearch, "Recent Search Text Should be displayed in SRP Page");
		return myAccountGetTxt;
	}
	
	
	public String getTextFromAYatraLogo() throws Exception {
		return (BrowserActions.getTextFromAttribute(driver, lnkYatraLogo, "title", "Yatra Logo title"));
	}

	/**
	 * To click search button on Home page
	 * 
	 * @throws Exception
	 */

	public HomePage clickYatraLogo() throws Exception {		
		BrowserActions.clickOnElement(lnkYatraLogo, driver, "Search");
		Utils.waitForPageLoad(driver);
		Log.event("Successfully clicked Yatra Logo link in SRP");
		return new HomePage(driver).get();

	}
	
	
} // SearchResult