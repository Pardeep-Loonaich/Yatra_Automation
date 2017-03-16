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
	public WebElement BtnModifySearchIcon;

	@FindBy(css = "div[class='full']>div[class='matrix-wrapper day-matrix new-theme day-matrix-responsive']")
	WebElement weeklyStrip;

	@FindBy(css = "[class='js-flightRow js-flightItem']")
	List<WebElement> btnBookNow;

	@FindBy(css = "p[class='new-blue-button .js-bookNow book-btn']")
    WebElement btnBookNowINT;
 
	@FindBy(css = "div[ng-controller='productFareDetailsController']")
	WebElement moduleFareDetails;

	@FindBy(css = "div[class='show-result multi-1']>div>div[class='results']>div:nth-child(1)>article>div[class='my-res-info full']>ul>li>small:nth-child(2)")
	WebElement firstAirlineName_OW_DOM;
//	
//	@FindBy(css = "div[class='js-flightItem']:nth-child(2)>article>div[class='full airlines-deals-holder bxs hidden-sm']>div[class='ib airlines-info hidden-sm']>p")
//	WebElement firstAirlineName_RT_INTL;
//
//	@FindBy(css = "div[class='js-flightItem']:nth-child(2)>article>div[class='full result-card-content']>ul>li:nth-child(1)>div>p[class='full airline-name']")
//	WebElement firstAirlineName_OW_INTL;
//	
//	@FindBy(css = "#resultBoxSlider>div[id='resultList_0']>div[class='results']>div:nth-child(1)>article>div[class='my-res-info full']>ul>li:nth-child(1)")
//	WebElement firstAirlineName_RT_DOM_Left;
//	
//	@FindBy(css = "#resultBoxSlider>div[id='resultList_0']>div[class='results']>div:nth-child(1)>article>div[class='my-res-info full']>ul>li:nth-child(1)")
//	WebElement firstAirlineName_RT_DOM_Right;
	
	@FindBy(css = "div[class='js-flightItem']:nth-child(2)>article>div[class='full lob-inclusions bxs hidden-md']>div[class='inc-rgt']>ul>li>a[title='Flight Details']")
	WebElement lnkFlightDetails_INTL;
	
	@FindBy(css = "#resultBoxSlider>div[id='resultList_0']>div[class='results']>div:nth-child(1)>article>footer>ul[class='res-footer-list fl uprcse']")
	WebElement lnkFlightDetails_DOM;
	
	@FindBy(css ="div[class='new-green-button fr relative tc']")
	WebElement btnBookNowRoundTrip;
	
	
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


		if (isPageLoaded && !(Utils.waitForElement(driver, BtnModifySearchIcon))) {
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
      * @param index
      * @return
      * @throws Exception
      */
	public ReviewPage clickOnBookNowInRound(int list1,int index1,int list2,int index2) throws Exception {
		WebElement e1=  driver.findElement(By.cssSelector(" div[id='resultBoxSlider']>div:nth-child("+list1+")>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child("+index1+")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='price']>div[class='full']>div>p[class='new-blue-button fr book-button']:not([class='ng-hide']"));
		WebElement e2=  driver.findElement(By.cssSelector(" div[id='resultBoxSlider']>div:nth-child("+list2+")>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child("+index2+")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='price']>div[class='full']>div>p[class='new-blue-button fr book-button']:not([class='ng-hide']"));

		BrowserActions.scrollToView(e1, driver);
		BrowserActions.clickOnElement(e1,driver,"To select Flight from one list.");
		BrowserActions.scrollToView(e2, driver);
		BrowserActions.clickOnElement(e2,driver,"To select Flight from second list.");
		
		BrowserActions.clickOnElement(btnBookNowRoundTrip, driver, "Click on Book Now for RoundTrip.");
        return new ReviewPage(driver).get();
	}

	 /**
     * to click on Book now button in OneWay Trip for International flights
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
     * @param index
     * @return
     * @throws Exception
     */
	public ReviewPage clickOnBookNowInOneWay(int index) throws Exception {
		WebElement e= driver.findElement(By.cssSelector("div[id='resultBoxSlider']>div>div[class='results']>div[class='js-flightRow js-flightItem']:nth-child("+index+")>article[class*='my-res new-theme my-result-list animation']>div[class='my-res-info full']>ul>li[class='book-now']>div>p[class='new-blue-button fr book-button js-bookNow relative tc']"));
		BrowserActions.scrollToView(e, driver);
		BrowserActions.clickOnElement(e,driver,"To click on Book now button.");
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
	}
