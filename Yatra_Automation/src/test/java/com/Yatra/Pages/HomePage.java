package com.Yatra.Pages;

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


public class HomePage extends LoadableComponent<HomePage> {

	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	


	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/

	@FindBy(id = "BE_flight_origin_city")
	public WebElement txtOrgion;

	@FindBy(id = "BE_flight_arrival_city")
	WebElement txtDestination;

	@FindBy(id = "BE_flight_flsearch_btn")
	WebElement btnSearch;
	
	
	

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
	public HomePage(WebDriver driver, String url) {
		appURL = url;
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}// HomePage

	/**
	 * 
	 * @param driver
	 *            : webdriver
	 */
	public HomePage(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);

		/*headers = new Headers(driver).get();
		footers = new Footers(driver).get();
		minicart = new MiniCartPage(driver).get();
		elementLayer = new ElementLayer(driver);*/
	}// HomePage

	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, btnSearch))) {
			Log.fail("Home Page did not open up. Site might be down.", driver);
		}
		/*headers = new Headers(driver).get();
		footers = new Footers(driver).get();
		elementLayer = new ElementLayer(driver);*/

	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}// load


	/**
	 * Enter Orgion
	 * 
	 * @param orgion
	 *            as string
	 * @throws Exception
	 */
	public void enterOrgion(String orgion) throws Exception {
		Utils.waitForElement(driver, txtOrgion);
		BrowserActions.typeOnTextField(txtOrgion, orgion, driver, "Select Orgion");
		Log.event("Entered the Orgion: " + orgion);
	}
	
	
	/**
	 * Enter Destination
	 * 
	 * @param destination
	 *            as string
	 * @throws Exception
	 */
	public void enterDestination(String destination) throws Exception {
		Utils.waitForElement(driver, txtDestination);
		BrowserActions.typeOnTextField(txtDestination, destination, driver, "Select Destination");
		Log.event("Entered the Destination: " + destination);
	}
	
	
	/**
	 * To click search button on Home page
	 * 
	 * @throws Exception
	 */
	public void clickBtnSearch() throws Exception {
		//final long startTime = StopWatch.startTime();
		BrowserActions.clickOnElement(btnSearch, driver, "Search");
		Utils.waitForPageLoad(driver);
		//Log.event("Clicked 'Login' button on SignIn page",	StopWatch.elapsedTime(startTime));
	}
	
	
	
}// HomePage
