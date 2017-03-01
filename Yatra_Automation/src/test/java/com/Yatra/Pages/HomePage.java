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
	
	@FindBy(id= "BE_flight_depart_date")
	WebElement departureDate;
	
	@FindBy(id= "BE_flight_return_date")
	WebElement returnDate;
	
	@FindBy(css ="div[id='PegasusCal-0'] li a[href*='#PegasusCal-0-month-']" )
	List<WebElement> selectMonth;
	
	@FindBy(css= "div[id='BE_flight_paxInfoBox']")
	WebElement passengerInfo;

	String dateLocator="div[class='month-box'] table tbody td[class*='activeTD clsDateCell'] a[id='";	
	
	String passengersLocator="span[class='ddSpinnerPlus']";
	
	String passengerClassLocator="div[id='flight_class_select_child'] ul li";
	
	@FindBy(css = "div[class='be-ddn-footer'] span")
	WebElement submitPassengerClassInfo;
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
	
	public void selectDepartureDate(String day) throws Exception{
		int month=Integer.parseInt(day.split("_")[2]);
		BrowserActions.clickOnElement(departureDate, driver, "clicking on departure date icon");
		selectMonth.get(month-2).click();
		List<WebElement> datePicker =driver.findElements(By.cssSelector(dateLocator+day+"']"));
		datePicker.get(0).click();
	}
	
	public void specifyPassengerInfo(String passengers) throws Exception{
		BrowserActions.clickOnElement(passengerInfo, driver, "Passenger Info");
		List<WebElement> updatePassengers =driver.findElements(By.cssSelector(passengersLocator));
		int adult = Integer.parseInt(passengers.split("_")[0]);
		int child = Integer.parseInt(passengers.split("_")[1]);
		int infant = Integer.parseInt(passengers.split("_")[2]);
		int  passengerClass= Integer.parseInt(passengers.split("_")[3]);
		for(int i=1;i<adult;i++){
			updatePassengers.get(0).click();
		}
		
		for(int i=0;i<child ; i++){
			updatePassengers.get(1).click();
		}
		for(int i=0;i<infant ; i++){
			updatePassengers.get(2).click();
		}
		driver.findElements(By.cssSelector(passengerClassLocator)).get(passengerClass).click();
		BrowserActions.clickOnElement(submitPassengerClassInfo, driver, "Done Button");
	}
}// HomePage
