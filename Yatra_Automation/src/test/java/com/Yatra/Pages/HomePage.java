package com.Yatra.Pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

	@FindBy(id="BE_flight_depart_date")
	WebElement txtDepartDate;

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
	
	@FindBy(xpath = "//form[@id='BE_flight_form']//li[1]/a")
	public WebElement lnkOneWay;

	@FindBy(xpath = "//form[@id='BE_flight_form']//li[2]/a")
	WebElement lnkRoundTrip;

	@FindBy(xpath = "//form[@id='BE_flight_form']//li[3]/a")
	WebElement lnkMultiCity;	
	
	@FindBy(id = "BE_flight_depart_date")
	WebElement txtDeptDate;
	
	@FindBy(xpath = "//form[@id='BE_flight_form']//li[3]/i")
	WebElement txtDeptDatePicker;
	
	@FindBy(xpath = "//form[@id='BE_flight_form']//li[4]/i")
	WebElement txtReturnDatePicker;
	
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
	

	/**
	 * To click OneWay link on Home page
	 * 
	 * @throws Exception
	 */
	public void selectOneWayTrip() throws Exception {		
		BrowserActions.clickOnElement(lnkOneWay, driver, "One Way");
		Utils.waitForPageLoad(driver);	
	}
	
	
	/**
	 * To click Round Trip link on Home page
	 * 
	 * @throws Exception
	 */
	public void selectRoundTrip() throws Exception {		
		BrowserActions.clickOnElement(lnkRoundTrip, driver, "Round Trip");
		Utils.waitForPageLoad(driver);		
	}
	
	/**
	 * To click MultiCity link on Home page
	 * 
	 * @throws Exception
	 */
	public void selectMultiCity() throws Exception {	
		BrowserActions.clickOnElement(lnkMultiCity, driver, "Multicity");
		Utils.waitForPageLoad(driver);		
	}
	
	/**
	 * To get System Date
	 * 
	 * @throws Exception
	 */
	public static String getSystemDate(){	
		String currentDate = null;
		DateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd");
		Date date = new Date();
		currentDate = dateFormat.format(date); //2016/11/16 
		return currentDate;
	}
	
	/**
	 * To select Depart date on Home page
	 * 
	 * @throws Exception
	 */
	public void selectDeptCurrentDate() throws Exception {
		//get the dataformat from system
		String dateFormat = getSystemDate();		
		String[] currentDate = dateFormat.split("/");
		String date1 = currentDate[2]; // DD
		String month = currentDate[1]; // MM		
		String year = currentDate[0]; // yyyy		
		String month1 = month.replace("0", ""); 
		String date2 = date1.replace("0", ""); 
		String date = year +"_"+month1+"_"+date2;
		System.out.println("Date after one week : " + date);		
		WebElement linkDateformat =  driver.findElement(By.xpath("//a[@id='a_"+date+"']/span"));
		BrowserActions.clickOnElement(linkDateformat, driver, "select Date");
		Utils.waitForPageLoad(driver);
	}
	
	/**
	 * To select Date after one week in Depart date on Home page
	 * 
	 * @throws Exception
	 */
	public void selectDeptDateAfterOneWeek() throws Exception {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.WEEK_OF_YEAR, 1);
		String date = now.get(Calendar.YEAR) + "_" + (now.get(Calendar.MONTH)+1) + "_" +(now.get(Calendar.DATE));		
		WebElement linkDateformat =  driver.findElement(By.xpath("//a[@id='a_"+date+"']/span"));
		BrowserActions.clickOnElement(linkDateformat, driver, "select Date");
		Utils.waitForPageLoad(driver);
		Log.event("Selected Depart Date after One weeks : " + date);
	}
	
	/**
	 * To select Date after Two week in Return date on Home page
	 * 
	 * @throws Exception
	 */
	public void selectReturnDateAfterTwoWeek() throws Exception {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.WEEK_OF_YEAR, 2);
		String date = now.get(Calendar.YEAR) + "_" + (now.get(Calendar.MONTH)+1) + "_" +(now.get(Calendar.DATE));
		WebElement linkDateformat =  driver.findElement(By.xpath("//a[@id='a_"+date+"']/span"));
		BrowserActions.clickOnElement(linkDateformat, driver, "select Date");
		Utils.waitForPageLoad(driver);
		Log.event("Selected Return Date after Two weeks : " + date);
	}
	
	
	/**
	 * To click Depart Date textbox on Home page
	 * 
	 * @throws Exception
	 */
	public void clickDeptdate() throws Exception {		
		BrowserActions.clickOnElement(txtDeptDate, driver, "Depart Date Textbox");
		Utils.waitForPageLoad(driver);		
	}
	
	
	/**
	 * To click Depart Date picker on Home page
	 * 
	 * @throws Exception
	 */
	public void clickDeptDatePicker() throws Exception {	
		BrowserActions.clickOnElement(txtDeptDatePicker, driver, "Depart Date Picker");
		Utils.waitForPageLoad(driver);		
	}
	
	
	/**
	 * To click Return Date picker on Home page
	 * 
	 * @throws Exception
	 */
	public void clickReturnDatePicker() throws Exception {		
		BrowserActions.clickOnElement(txtReturnDatePicker, driver, "Return Date Picker");
		Utils.waitForPageLoad(driver);		
	}	

	
}// HomePage
