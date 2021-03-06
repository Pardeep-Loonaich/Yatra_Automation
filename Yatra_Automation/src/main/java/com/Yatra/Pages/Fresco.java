
package com.Yatra.Pages;
import java.awt.Robot;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.BrowserType;
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

/**
 * Fresco page is used to create all page related action functions
 *  
 */

@SuppressWarnings("unused")
public class Fresco extends LoadableComponent<Fresco> {

	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();


	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/

	//@Harveer- change access specifier for all element private

	@FindBy(css = "input#BE_flight_origin_city")
	private WebElement txtOrigin;

	@FindBy(css = "input#BE_flight_arrival_city")
	private WebElement txtDestination;

	@FindBy(css = "input#BE_flight_flsearch_btn")
	private WebElement btnSearch;

	@FindBy(css = "input#BE_flight_depart_date")
	private WebElement dateDeparture;

	@FindBy(css = "input#BE_flight_return_date")
	private WebElement dateReturn;

	String departureDateLocator="(//table[@class='day-container-table']//a[@id='a_";
	

	@FindBy(id = "BE_flight_return_date")
	private WebElement returnDate;

	@FindBy(css = "div[id='PegasusCal-0'] li a[href*='#PegasusCal-0-month-']")
	private List<WebElement> selectMonth;

	@FindBy(css = "div[id='BE_flight_paxInfoBox']")
	private WebElement passengerInfo;

	//String dateLocator = "div[class='month-box'] table tbody td[class*='activeTD clsDateCell'] a[id='a_";
	String returnDateLocator="";
	String passengersLocator = "span[class='ddSpinnerPlus']";
	String passengerClassLocator = "div[id='flight_class_select_child'] ul li";

	@FindBy(css = "div[class='be-ddn-footer']>span[class='done']")
	private WebElement submitPassengerClassInfo;

	@FindBy(css = "a[title='One Way']")
	private WebElement lnkOneWay;

	@FindBy(css = "a[title='Round Trip']")
	private WebElement lnkRoundTrip;

	@FindBy(css = "a[title='Multicity']")
	private WebElement lnkMultiCity;

	@FindBy(css = "#BE_flight_depart_date")
	private WebElement txtDateDepart;

	@FindBy(xpath = "//form[@id='BE_flight_form']//li[3]/i")
	private WebElement txtDeptDatePicker;

	@FindBy(xpath = "//form[@id='BE_flight_form']//li[4]/i")
	private WebElement txtReturnDatePicker;

	@FindBy(css = "a#booking_engine_flights")
	private WebElement lnkFlights;

	@FindBy(css = "a#booking_engine_hotels")
	private WebElement lnkHotels;

	@FindBy(css = "a#booking_engine_homestays")
	private WebElement lnkHomeStays;

	@FindBy(css = "a#booking_engine_holidays")
	private WebElement lnkHolidays;

	@FindBy(css = "a[id='booking_engine_activities']>span:not([class='betaBookingEngine'])")
	private WebElement lnkActivities;

	@FindBy(css = "a#booking_engine_buses")
	private WebElement lnkBuses;

	@FindBy(css = "a#booking_engine_trains")
	private WebElement lnkTrains;

	@FindBy(css = "a#booking_engine_cruise")
	private WebElement lnkCruise;

	@FindBy(css = "#signInBtn")
	private WebElement btnSignIn;

	@FindBy(css = "input#BE_flight_origin_city_1")
	private WebElement txtMulticity_Origin1;

	@FindBy(css = "input#BE_flight_origin_city_2")
	private WebElement txtMulticity_Origin2;

	@FindBy(css = "input#BE_flight_arrival_city_1")
	private WebElement txtMulticity_Destination1;

	@FindBy(css = "input#BE_flight_arrival_city_2")
	private WebElement txtMulticity_Destination2;

	@FindBy(xpath = "//input[@id='BE_flight_depart_date_1']")
	private WebElement dateMulticity_Departure1;

	@FindBy(css = "input#BE_flight_depart_date_2")
	private WebElement dateMulticity_Departure2;

	@FindBy(css = "div[id='PegasusCal-7'] li a[href*='#PegasusCal-7-month-']")
	private List<WebElement> selectMonth_MultiDepart1;

	@FindBy(css = "div[id='PegasusCal-8'] li a[href*='#PegasusCal-8-month-']")
	private List<WebElement> selectMonth_MultiDepart2;

	@FindBy(css = "#userSignInStrip")
	private WebElement lnkMyaccount;

	@FindBy(css = ".be-container-v2")
	private WebElement searchPanel;

	@FindBy(css = "div[id='booking_engine_modues']>form>div>div[id='']>div[id='BE_bus_seats_msdd']>div[class='ddTitle borderRadiusTp']>span[class='ddSpinnerPlus']")
	private WebElement btnIncreseSeat;

	@FindBy(css = "div[class*='selc-more-options mor-option trip-type']>span:nth-child(1)")
	private WebElement lnkOneWayBus;

	@FindBy(css = "div[class*='selc-more-options mor-option trip-type']>span:nth-child(3)")
	private WebElement lnkRoundTripBus;

	@FindBy(css = "#BE_bus_from_station")
	private WebElement txtOriginBus;

	@FindBy(xpath = "//input[@id='BE_train_from_station']")
	private WebElement txtTrainOrigin;

	@FindBy(xpath = "//input[@id='BE_train_to_station']")
	private WebElement txtTrainDestination;

	@FindBy(xpath = "//input[@id='BE_train_depart_date']")
	private WebElement dateTrainDeparture;

	@FindBy(xpath="//input[@id='BE_train_search_btn']")
	private WebElement btnTrainSearch;

	@FindBy(css = "#BE_bus_to_station")
	private WebElement txtDestinationBus;

	@FindBy(css = "#BE_bus_depart_date")
	private WebElement dateDepartureBus;

	@FindBy(css = "#BE_bus_return_date")
	private WebElement dateReturnBus;

	@FindBy(css = "#BE_bus_search_btn")
	private WebElement btnSearchBus;

	@FindBy(css = "div[id='PegasusCal-7'] li a[href*='#PegasusCal-7-month-']")
	private List<WebElement> selectMonth_Bus;

	@FindBy(css = "div[class='toasterHolder']")
	private WebElement txtErrorMsgEmptyCity;

	@FindBy(css = ".ac_over")
	private WebElement txtErrorMsgIncorrectCity;

	@FindBy(css="li[id='userLoginBlock']>a")
	private WebElement drpdwnUserLogin;

	@FindBy(css="li[id='userLoginBlock']>div>div[class='user-drop-ddn header-dropdown']>ul>li[class='simple-dropdown']>a")
	private WebElement lnkMyBooking;

	@FindBy(css = "div[class='header-container']>a")
	private WebElement logoYatra;

	@FindBy(css = "#PegasusCal-7")
	WebElement calenderMultiDept;

	@FindBy(css = "#PegasusCal-0")
	WebElement calenderDeptdate;

	@FindBy(css = "li.ac_even.ac_over")
	private WebElement txtCityOver;

	@FindBy(css = "input[id='BE_activity_destination']")
	private WebElement txtActivitesOrigin;

	@FindBy(css = "div[class='journey-details clearfix']>div>div[class='ripple-parent']>input")
	private WebElement btnSearchActivties;

	@FindBy(css = "div[class='ac_results act-look']>ul[class='mac-scroll scrollable']")
	private WebElement listautoSuggestion;	

	@FindBy(css = "a[for='BE_flight_non_stop']>i")
	private WebElement chkNonStopFlights;
	
	@FindBy(xpath = "//iframe[@id='webklipper-publisher-widget-container-notification-frame']")
	private WebElement IframeNotification;
	
	@FindBy(css = "a[id='webklipper-publisher-widget-container-notification-close-div']")
	private WebElement btnCloseIframeNotification_Double;

	@FindBy(xpath = "/html/body//*[@class='close close-icon']//*[@class='wewidgeticon we_close']")
	private WebElement btnCloseIframeNotification;
	
	@FindBy(css ="div[class='footsec js-footer-new js-footer-slide']>div>div[id='js_yt_footer']>div>ul[class='footer-container']>li[class='parentLI']")
	private List<WebElement> footerOptions;
	
	@FindBy(css ="li[id='MoreAboutUs']>a")
	private WebElement aboutUs;
	
	@FindBy(css ="li[data-href='#team']")
	private WebElement aboutUsTeam;
	
	@FindBy(css ="li[id='Sitemap']>a")
	private WebElement siteMap;
	
	@FindBy(css ="li[id='Terms&Conditions']>a")
	private WebElement termsAndConditions;
	
	@FindBy(css ="li[id='PrivacyPolicy']>a")
	private WebElement privacyPolicy;
	
	@FindBy(css ="li[id='UserAgreement']>a")
	private WebElement userAgreement;
	
	@FindBy(css ="li[id='AwardsWon']>a")
	private WebElement awardsWon;
	
	@FindBy(css ="li[id='PressReleases']>a")
	private WebElement pressReleases;
	
	@FindBy(css ="li[id='YatraHolidayAdvisors']>a")
	private WebElement yatraHolidayAdvisors;
	
	@FindBy(xpath =".//*[@id='ProductOfferings']/ul[1]/li[41]/a")
	private WebElement visaInformation;
	
	@FindBy(css ="li[id='RegisterYourHotel']>a")
	private WebElement registerYourHotel;
	
	@FindBy(css ="li[id='AdvertiseWithUs']>a")
	private WebElement advertiseWithUs;

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

	public Fresco(WebDriver driver, String url) {
		appURL = url;
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		elementLayer = new ElementLayer(driver);
	}// HomePage

	/**
	 * 
	 * @param driver
	 *            : webdriver
	 */
	public Fresco(WebDriver driver) {
		appURL = "https://www.yatra.com/";
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);		
		elementLayer = new ElementLayer(driver);
	}// HomePage

	@Override
	protected void isLoaded() {
		timer.end();
		if (!isPageLoaded) 
		{
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, btnSearch))) 
		{
		Log.fail("Home Page did not open up. Site might be down.", driver);
		}
		Log.message("Total time taken by #"+this.getClass().getTypeName()+" to load is:- "+timer.duration()+" "+TimeUnit.MILLISECONDS);
		Constants.performanceData.put("FrescoPage",timer.duration());
	}// isLoaded

	@Override
	protected void load(){
		timer.start();
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);

	}// load


	/**
	 * Enter Origin
	 * 
	 * @param origin
	 *            as string
	 * @throws Exception
	 */
	public void enterOrigin(String origin) throws Exception {
		Utils.waitForElement(driver, txtOrigin);		
		BrowserActions.typeOnTextField(txtOrigin, origin, driver, "Select Origin");
		BrowserActions.nap(3);
		Utils.waitForElement(driver, txtCityOver);			
		Log.event("Entered the Origin: "+origin);		
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
		BrowserActions.nap(3);
		Utils.waitForElement(driver, txtCityOver);		
		Log.event("Entered the Destination: " + destination);
	}

	/**
	 * To click search button on Home page
	 * 
	 * @throws Exception
	 */

	public SearchResult clickBtnSearch() throws Exception {		
		BrowserActions.clickOnElement(btnSearch, driver, "Search");
		Utils.waitForPageLoad(driver);
		BrowserActions.nap(5);
		return new SearchResult(driver).get();

	}

	public void selectDepartureDate_old(String date) throws Exception {
		int month = Integer.parseInt(date.split("_")[2]);
		BrowserActions.clickOnElement(dateDeparture, driver, "clicking on departure date icon");
		selectMonth.get(month - 2).click();

		Utils.waitForElement(driver, driver.findElement(By.xpath(departureDateLocator+date+"']//span[@class='day-num'])[1]")));
		driver.findElement(By.xpath(departureDateLocator+date+"'])[1]")).click();
		/*List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		datePicker.get(0).click();
		Log.event("Selected Departure Date: " + date + "(YY/MM/DD)");*/
	}

	public void specifyPassengerInfo_old(String passengers) throws Exception {
		BrowserActions.clickOnElement(passengerInfo, driver, "Passenger Info");
		List<WebElement> updatePassengers = driver.findElements(By.cssSelector(passengersLocator));
		int adult = Integer.parseInt(passengers.split("_")[0]);
		int child = Integer.parseInt(passengers.split("_")[1]);
		int infant = Integer.parseInt(passengers.split("_")[2]);
		int passengerClass = Integer.parseInt(passengers.split("_")[3]);
		for (int i = 1; i < adult; i++) {
		}

		for (int i = 0; i < child; i++) {
			updatePassengers.get(1).click();
		}
		for (int i = 0; i < infant; i++) {
			updatePassengers.get(2).click();
		}
		driver.findElements(By.cssSelector(passengerClassLocator)).get(passengerClass).click();
		BrowserActions.clickOnElement(submitPassengerClassInfo, driver, "Done Button");
	}

	/**
	 * To select Passenger Class Type (Economy, Premium Economy, Business, First
	 * Class )
	 * 
	 * @throws Exception
	 */
	public void selectPassengerClass(String passengerClass) throws Exception {
		if (passengerClass.equals(Constants.C_ECONOMY)) {
			driver.findElements(By.cssSelector(passengerClassLocator)).get(0).click();
			Log.event("Selected 'Economy' Class ");
		} else if (passengerClass.equals(Constants.C_PREMIUM_ECONOMY)) {
			driver.findElements(By.cssSelector(passengerClassLocator)).get(1).click();
			Log.event("Selected 'Premium Economy' Class ");
		} else if (passengerClass.equals(Constants.C_BUSINESS)) {
			driver.findElements(By.cssSelector(passengerClassLocator)).get(2).click();
			Log.event("Selected 'Business' Class ");
		} else if (passengerClass.equals(Constants.C_FIRST_CLASS)) {
			driver.findElements(By.cssSelector(passengerClassLocator)).get(3).click();
			Log.event("Selected 'First Class'");
		}
	}

	/**
	 * To click Done button in Passenger Drop Down Box
	 * 
	 * @throws Exception
	 */

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
	public static String getSystemDate() {
		String currentDate = null;
		DateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd");
		Date date = new Date();
		currentDate = dateFormat.format(date); // 2016/11/16
		return currentDate;
	}

	/**
	 * To select Depart date on Home page
	 * 
	 * @throws Exception
	 */
	public void selectDeptCurrentDate() throws Exception {
		// get the dataformat from system
		String dateFormat = getSystemDate();
		String[] currentDate = dateFormat.split("/");
		String date1 = currentDate[2]; // DD
		String month = currentDate[1]; // MM
		String year = currentDate[0]; // yyyy
		String month1 = month.replace("0", "");
		String date2 = date1.replace("0", "");
		String date = year + "_" + month1 + "_" + date2;
		System.out.println("Date after one week : " + date);
		WebElement linkDateformat = driver.findElement(By.xpath("//a[@id='a_" + date + "']/span"));
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
		String date = now.get(Calendar.YEAR) + "_" + (now.get(Calendar.MONTH) + 1) + "_" + (now.get(Calendar.DATE));
		WebElement linkDateformat = driver.findElement(By.xpath("//a[@id='a_" + date + "']/span"));
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
		String date = now.get(Calendar.YEAR) + "_" + (now.get(Calendar.MONTH) + 1) + "_" + (now.get(Calendar.DATE));
		WebElement linkDateformat = driver.findElement(By.xpath("//a[@id='a_" + date + "']/span"));
		BrowserActions.clickOnElement(linkDateformat, driver, "select Date");
		Utils.waitForPageLoad(driver);
		Log.event("Selected Return Date after Two weeks : " + date);
	}

	/**
	 * To click Depart Date textbox on Home page
	 * 
	 * @throws Exception
	 */
	public void clickDateDepart() throws Exception {
		BrowserActions.clickOnElement(txtDateDepart, driver, "Depart Textbox");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To click Depart Date picker on Home page
	 * 
	 * @throws Exception
	 */
	public void clickDeptDatePicker() throws Exception {
		BrowserActions.clickOnElement(txtDeptDatePicker, driver, "Depart DatePicker");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To click Return Date picker on Home page
	 * 
	 * @throws Exception
	 */
	public void clickReturnDatePicker() throws Exception {
		BrowserActions.clickOnElement(txtReturnDatePicker, driver, "Return DatePicker");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To click Flights on Home page
	 * 
	 * @throws Exception
	 */
	public void clickFlights() throws Exception {
		BrowserActions.clickOnElement(lnkFlights, driver, "Flights");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To click Hotels on Home page
	 * 
	 * @throws Exception
	 */
	public void clickHotels() throws Exception {
		BrowserActions.clickOnElement(lnkHotels, driver, "Hotels");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To click HomeStays on Home page
	 * 
	 * @throws Exception
	 */
	public void clickHomeStays() throws Exception {
		BrowserActions.clickOnElement(lnkHomeStays, driver, "HomeStays");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To click Holidays on Home page
	 * 
	 * @throws Exception
	 */
	public void clickHolidays() throws Exception {
		BrowserActions.clickOnElement(lnkHolidays, driver, "Holidays");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To click Buses on Home page
	 * 
	 * @throws Exception
	 */
	public void clickBuses() throws Exception {
		BrowserActions.clickOnElement(lnkBuses, driver, "Buses");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To click Trains on Home page
	 * 
	 * @throws Exception
	 */
	public void clickTrains() throws Exception {
		BrowserActions.clickOnElement(lnkTrains, driver, "Trains");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To click Cruise on Home page
	 * 
	 * @throws Exception
	 */
	public void clickCruise() throws Exception {
		BrowserActions.clickOnElement(lnkCruise, driver, "Cruise");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To click Activities on Home page
	 * 
	 * @throws Exception
	 */
	public void clickActivities() throws Exception {
		BrowserActions.clickOnElement(lnkActivities, driver, "Activities");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To select Return Date
	 * 
	 * @throws Exception
	 */
	public void selectReturnDate_old(String date) throws Exception {
		int month = Integer.parseInt(date.split("_")[1]);
		Utils.waitForElement(driver, dateReturn);
		BrowserActions.clickOnElement(dateReturn, driver, "clicking on return date icon");
		selectMonth.get(month - 2).click();

		Utils.waitForElement(driver, driver.findElement(By.xpath(departureDateLocator+date+"'])[1]")));
		driver.findElement(By.xpath(departureDateLocator+date+"'])[1]")).click();
		/*List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		datePicker.get(0).click();*/
		Log.event("Selected Return Date: " + date + "(YY/MM/DD)");
	}

	/**
	 * To navigate to SignIn Page
	 * 
	 * @throws Exception
	 */
	public LoginPage navigateToSignIn() throws Exception {
		// click Login button on signin page

		/*Utils.waitForElement(driver, lnkMyaccount);		
		BrowserActions.mouseHover(driver, lnkMyaccount);
		BrowserActions.moveToElementJS(driver, lnkMyaccount);
		BrowserActions.actionClick(btnSignIn, driver, "Sign In");

	    Utils.waitForPageLoad(driver);		

		BrowserActions.moveToElementJS(driver, driver.findElement(By.cssSelector("li[id='userSignInStrip']>a")));
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.cssSelector("li[id='userSignInStrip']"))).perform();
		actions.click(driver.findElement(By.cssSelector("li[id='userSignInStrip']"))).click().perform();

		actions.moveToElement(driver.findElement(By.xpath("//a[@class='dropdown-toggle' and contains(text(), 'My Account')]"))).perform();
		actions.click(driver.findElement(By.xpath("//a[@class='dropdown-toggle' and contains(text(), 'My Account')]"))).perform();

		 */

		WebElement lnkMyAccount = driver.findElement(By.cssSelector("li[id='userSignInStrip']>a"));
		Point point = lnkMyAccount.getLocation();
		int xCord = point.getX();
		int yCord = point.getY();

		if(BrowserType.fromConfiguration("ie") == BrowserType.IE){
			Robot robot= new Robot();
			robot.mouseMove(xCord, yCord);		
		}

		Utils.waitForElement(driver, btnSignIn);
		BrowserActions.javascriptClick(btnSignIn, driver, "Sign In");
		Utils.waitForPageLoad(driver);
		return new LoginPage(driver).get();
	}

	/**
	 * To select Trip Type
	 * 
	 * @throws Exception
	 */
	public void selectTripType(String tripType) throws Exception {
		if (tripType.equals(Constants.C_ONEWAY)) {
			BrowserActions.clickOnElement(lnkOneWay, driver, "One Way");
			Utils.waitForPageLoad(driver);
			Log.event("Successfully selected OneWay option in Search Fields");
		} else if (tripType.equals(Constants.C_ROUNDTRIP)) {
			BrowserActions.clickOnElement(lnkRoundTrip, driver, "Round Trip");
			Utils.waitForPageLoad(driver);
			Log.event("Successfully selected RoundTrip option in Search Fields");
		} else if (tripType.equals(Constants.C_MULTICITY)) {
			BrowserActions.clickOnElement(lnkMultiCity, driver, "Multicity");
			Utils.waitForPageLoad(driver);
			Log.event("Successfully selected Multicity option in Search Fields");
		} else if (tripType.equals(Constants.C_EXPLORE)) {
			BrowserActions.clickOnElement(lnkExplore, driver, "Explore");
			Utils.waitForPageLoad(driver);
			Log.event("Successfully selected Explore option in Search Fields");
		}
	}

	/**
	 * To select OneWay Flight search Fields
	 * 
	 * @throws Exception
	 */

	public void selectOneWayFlightSearchFields(String origin, String destination, String departureDate,
			String passengerInfo, String passengerClass) throws Exception {
		BrowserActions.nap(2);
		enterOrigin(origin); // enter Origin value
		enterDestination(destination); // enter Destination value
		BrowserActions.nap(2);
		selectDepartureDate(departureDate); // select Departure Date

		specifyPassengerInfo(passengerInfo); // select Passengers details(Adult,
		// Child, Infant)
		selectPassengerClass(passengerClass); // select Passengers class type
		//clickDoneButtonInPassengerBox(); // click Done button
		Log.event("Successfully selected OneWay Flight Search fields");

	}

	/**
	 * To select Round Trip Flight search Fields
	 * 
	 * @throws Exception
	 */

	public void selectRoundTripFlightSearchFields(String origin, String destination, String departureDate,	String returnDate, String passengerInfo, String passengerClass) throws Exception {
		BrowserActions.nap(2);
		enterOrigin(origin); // enter Origin value
		enterDestination(destination); // enter Destination value
		selectDepartureDate(departureDate); // select Departure Date
		selectReturnDate(returnDate); // select Return Date
		specifyPassengerInfo(passengerInfo); // select Passengers details (Adult, Child, Infant)
		selectPassengerClass(passengerClass); // select Passengers class type
		BrowserActions.nap(3);
		clickDoneButtonInPassengerBox(); // click Done button
		Log.event("Successfully selected RoundTrip Flight Search fields");
	}

	/**
	 * To click Done button in Passenger Drop Down Box
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */

	public void clickDoneButtonInPassengerBox() throws Exception {
		Utils.waitForElement(driver, submitPassengerClassInfo);
		BrowserActions.clickOnElement(submitPassengerClassInfo, driver, "Done Button");
		Log.event("Successfully clicked Done button in Passenger DropDown box");

	}

	/**
	 * To select Departure Date
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public String selectDepartureDate(String departureDate) throws Exception {
		int iDay = Integer.parseInt(departureDate);
		String date = utils.dateGenerator("yyyy_M_d", iDay);
		int month = Integer.parseInt(date.split("_")[1]);
		BrowserActions.nap(2);
		Utils.waitForElement(driver, dateDeparture);		
		BrowserActions.clickOnElement(dateDeparture, driver, "clicking on departure date icon..");
		selectMonth.get(month - 2).click();
		Utils.waitForElement(driver, driver.findElement(By.xpath(departureDateLocator+date+"']//span[@class='day-num'])[1]")));
		Utils.waitForPageLoad(driver, 7);
		driver.findElement(By.xpath(departureDateLocator+date+"']//span[@class='day-num'])[1]")).click();		
		Log.event("Selected Departure Date: " + date + "(YYYY/MM/DD)");
		return date;
	}

	/**
	 * To select Return Date
	 * 
	 * @throws Exception
	 */
	public String selectReturnDate(String returnDate) throws Exception {
		int iDay = Integer.parseInt(returnDate);
		String date = Utils.dateGenerator("yyyy_M_d", iDay);
		int month = Integer.parseInt(date.split("_")[1]);
		BrowserActions.nap(2);
		Utils.waitForElement(driver, dateReturn);
		BrowserActions.scrollToViewElement(dateReturn, driver);
		BrowserActions.clickOnElement(dateReturn, driver, "clicking on return date icon");
		selectMonth.get(month - 2).click();
		Utils.waitForPageLoad(driver, 15); 
		BrowserActions.nap(2);
		driver.findElement(By.xpath(departureDateLocator+date+"']//span[@class='day-num'])[1]")).click();
		Log.event("Selected Return Date: " + date + "(YY/MM/DD)");
		return date;
	}

	/**
	 * To select Passenger Information (Adults, Child, Infants)
	 * 
	 * @throws Exception
	 */
	public void specifyPassengerInfo(String passengers) throws Exception {
		BrowserActions.nap(2);
		BrowserActions.scrollToViewElement(passengerInfo, driver);
		BrowserActions.nap(3);
		BrowserActions.clickOnElement(passengerInfo, driver, "Passenger Info");
		List<WebElement> updatePassengers = driver.findElements(By.cssSelector(passengersLocator));
		int adult = Integer.parseInt(passengers.split("_")[0]);
		int child = Integer.parseInt(passengers.split("_")[1]);
		int infant = Integer.parseInt(passengers.split("_")[2]);
		for (int i = 1; i < adult; i++) {
			updatePassengers.get(0).click();
		}
		for (int i = 0; i < child; i++) {
			updatePassengers.get(1).click();
		}
		for (int i = 0; i < infant; i++) {
			updatePassengers.get(2).click();
		}
	}

	/**
	 * Enter MultiCity Origin1
	 * 
	 * @param origin
	 *            as string
	 * @throws Exception
	 */
	public void enterMultiCityOrigin1(String origin) throws Exception {
		Utils.waitForElement(driver, txtMulticity_Origin1);
		BrowserActions.typeOnTextField(txtMulticity_Origin1, origin, driver, "Select MultiCity Origin1");
		Utils.waitForElement(driver, txtCityOver);		
		Log.event("Entered the Origin: " + origin);
	}

	/**
	 * Enter MultiCity Origin2
	 * 
	 * @param origin
	 *            as string
	 * @throws Exception
	 */
	public void enterMultiCityOrigin2(String origin) throws Exception {
		Utils.waitForElement(driver, txtMulticity_Origin2);
		BrowserActions.typeOnTextField(txtMulticity_Origin2, origin, driver, "Select MultiCity Origin2");
		Utils.waitForElement(driver, txtCityOver);		
		Log.event("Entered the Origin: " + origin);
	}

	/**
	 * Enter MultiCity Destination1
	 * 
	 * @param destination
	 *            as string
	 * @throws Exception
	 */
	public void enterMultiCityDestination1(String destination) throws Exception {
		Utils.waitForElement(driver, txtMulticity_Destination1);
		BrowserActions.typeOnTextField(txtMulticity_Destination1, destination, driver, "Select MultiCity Destination1");
		Utils.waitForElement(driver, txtCityOver);		
		Log.event("Entered the Destination: " + destination);
	}

	/**
	 * Enter MultiCity Destination2
	 * 
	 * @param destination
	 *            as string
	 * @throws Exception
	 */
	public void enterMultiCityDestination2(String destination) throws Exception {
		Utils.waitForElement(driver, txtMulticity_Destination2);
		BrowserActions.typeOnTextField(txtMulticity_Destination2, destination, driver, "Select MultiCity Destination2");
		Utils.waitForElement(driver, txtCityOver);		
		Log.event("Entered the Destination: " + destination);
	}

	/**
	 * To select MultiCity Date Departure1
	 * 
	 * @param departureDate
	 *            as string
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public String selectMultiCityDateDeparture1(String departureDate) throws Exception {
		int iDay = Integer.parseInt(departureDate);
		String date = utils.dateGenerator("yyyy_M_d", iDay);
		int month = Integer.parseInt(date.split("_")[1]);
		Utils.waitForElement(driver, calenderMultiDept);
		BrowserActions.clickOnElement(dateMulticity_Departure1, driver, "clicking on MultiCity departure1 date icon");
		selectMonth_MultiDepart1.get(month - 3).click();
		Utils.waitForElement(driver, driver.findElement(By.xpath(departureDateLocator+date+"'])[8]")));
		driver.findElement(By.xpath(departureDateLocator+date+"'])[8]")).click();
		/*List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		datePicker.get(7).click();*/
		Log.event("Selected MultiCity Departure1 Date: " + date + "(YY/MM/DD)");
		return date;
	}

	/**
	 * To select MultiCity Date Departure2
	 * 
	 * @param departureDate
	 *            as string
	 * @throws Exception
	 */

	@SuppressWarnings("static-access")
	public String selectMultiCityDateDeparture2(String departureDate) throws Exception {
		int iDay = Integer.parseInt(departureDate);
		String date = utils.dateGenerator("yyyy_M_d", iDay);
		int month = Integer.parseInt(date.split("_")[1]);
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(dateMulticity_Departure2, driver, "clicking on MultiCity Departure2 date icon");
		//selectMonth_MultiDepart2.get(0).click();
		//Utils.waitForElement(driver, driver.findElement(By.xpath(departureDateLocator+date+"'])[9]")));
		driver.findElement(By.xpath(departureDateLocator+date+"'])[9]")).click();
		/*List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		datePicker.get(8).click();*/
		Log.event("Selected MultiCity Departure2 Date: " + date + "(YY/MM/DD)");
		return date;
	}

	/**
	 * To select MultiCity Flight search Fields
	 * 
	 * @throws Exception
	 */

	public void selectMultiCityFlightSearchFields(String origin1, String destination1, String departureDate1, String origin2,
			String destination2, String departureDate2, String passengerInfo, String passengerClass) throws Exception {

		enterMultiCityOrigin1(origin1); // enter MultiCity Origin1 value
		enterMultiCityDestination1(destination1); // enter MultiCity Destination1 value	
		selectMultiCityDateDeparture1(departureDate1); // select MultiCity Departure1 Date

		enterMultiCityOrigin2(origin2); // enter MultiCity Origin2 value
		enterMultiCityDestination2(destination2); // enter MultiCity Destination2 value		
		selectMultiCityDateDeparture2(departureDate1); // select MultiCity Departure2 Date

		specifyPassengerInfo(passengerInfo); // select Passengers details(Adult, Child, Infant)
		selectPassengerClass(passengerClass); // select Passengers class type
		clickDoneButtonInPassengerBox(); // click Done button
		Log.event("Successfully selected 'Multicity' Flight Search fields");

	}

	/**
	 * To navigate to SignIn Page -- In Progress
	 * 
	 * @throws Exception
	 */
	public LoginPage navigateToSignIn_IE() throws Exception {
		boolean searchIconPresence = btnSignIn.isDisplayed();
		boolean searchIconEnabled = btnSignIn.isEnabled();
		if (searchIconPresence == true && searchIconEnabled == true) {
			BrowserActions.moveToElementJS(driver, lnkMyaccount);
			BrowserActions.mouseHover(driver, lnkMyaccount);
			// click on the search button
			BrowserActions.clickOnElement(btnSignIn, driver, "Sign In");
		} else {
			BrowserActions.moveToElementJS(driver, lnkMyaccount);
			BrowserActions.mouseHover(driver, lnkMyaccount);

			// click Login button on signin page
			Actions action = new Actions(driver);
			action.contextClick(lnkMyaccount).build().perform();
			BrowserActions.clickOnElement(btnSignIn, driver, "Sign In");
			Log.event("Successfully selected RoundTrip option in Search Fields");
			System.out.println("False");
		}
		Utils.waitForPageLoad(driver);
		return new LoginPage(driver).get();

	}

	/**
	 * Enter Train Origin
	 * 
	 * @param trainOrigin
	 *            as string
	 * @throws Exception
	 */
	public void enterTrainOrigin(String trainOrigin) throws Exception {
		Utils.waitForElement(driver, txtTrainOrigin);
		BrowserActions.typeOnTextField(txtTrainOrigin, trainOrigin, driver, "Select Origin");
		Log.event("Entered the Origin: " + trainOrigin);
	}


	/**
	 * To select Passenger Information 
	 * 
	 * @throws Exception
	 */
	public void PassengerInfoBus(String passengers) throws Exception {
		BrowserActions.nap(2);
		Utils.waitForElement(driver, btnIncreseSeat);
		int z = Integer.parseInt(passengers);
		for(int i=1 ; i<z ; i++){
			BrowserActions.clickOnElement(btnIncreseSeat, driver, "Passenger Info");
		}
	}

	public void selectTripTypeBus(String tripType) throws Exception {
		if (tripType.equals(Constants.C_ONEWAY)) {
			BrowserActions.javascriptClick(lnkOneWayBus, driver, "One Way");
			Utils.waitForPageLoad(driver);
			Log.event("Successfully selected OneWay option in Search Fields");
		} else if (tripType.equals(Constants.C_ROUNDTRIP)) {
			BrowserActions.javascriptClick(lnkRoundTripBus, driver, "Round Trip");
			Utils.waitForPageLoad(driver);
			Log.event("Successfully selected RoundTrip option in Search Fields");
		}
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
	 * To Select Bus Destination
	 * 
	 * @throws Exception
	 */

	public void enterDestinationBus(String destination) throws Exception {
		Utils.waitForElement(driver, txtDestinationBus);
		BrowserActions.typeOnTextField(txtDestinationBus, destination, driver, "Select Destination");
		Log.event("Entered the Destination: " + destination);
	}
	/**
	 * To select Return Date
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public String selectReturnDateBus(String returnDate) throws Exception {
		int iDay = Integer.parseInt(returnDate);
		String date = utils.dateGenerator("yyyy_M_d", iDay);
		int month = Integer.parseInt(date.split("_")[1]);
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(dateReturnBus, driver, "clicking on Bus Return date icon");
		BrowserActions.scrollToViewElement(dateDeparture, driver);
		selectMonth_Bus.get(month - 3).click();
		BrowserActions.nap(2);
		Utils.waitForElement(driver, driver.findElement(By.xpath(departureDateLocator+date+"'])[8]")));
		driver.findElement(By.xpath(departureDateLocator+date+"'])[8]")).click();

		/*List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		BrowserActions.nap(2);
		datePicker.get(7).click();*/
		Log.event("Selected Bus Return Date: " + date + "(YY/MM/DD)");
		return date;
	}

	/**
	 * To Select Bus Departure Date
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public String selectDepartureDateBus(String departureDate) throws Exception {
		int iDay = Integer.parseInt(departureDate);
		String date = utils.dateGenerator("yyyy_M_d", iDay);
		int month = Integer.parseInt(date.split("_")[1]);
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(dateDepartureBus, driver, "clicking on Bus Depart date icon");
		selectMonth_Bus.get(month).click();
		BrowserActions.nap(2);

		Utils.waitForElement(driver, driver.findElement(By.xpath(departureDateLocator+date+"'])[8]")));
		driver.findElement(By.xpath(departureDateLocator+date+"'])[8]")).click();
		/*List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		BrowserActions.nap(2);
		datePicker.get(7).click();*/
		Log.event("Selected Bus Departure Date: " + date + "(YY/MM/DD)");
		return date;
	}
	/**
	 * To click search button on Home page
	 * 
	 * @throws Exception
	 */
	public SearchResultBus clickBtnSearchBus() throws Exception {
		BrowserActions.clickOnElement(btnSearchBus, driver, "Search Button");
		return new SearchResultBus(driver).get();
	}
	/**
	 * To select RoundTrip Bus search Fields
	 * 
	 * @throws Exception
	 */

	public void selectRoundTripBusSearchFields(String origin, String destination, String departureDate,
			String returnDate, String passengerInfo) throws Exception {

		enterOriginBus(origin); // enter Origin value
		BrowserActions.nap(2);
		enterDestinationBus(destination); // enter Destination value
		BrowserActions.nap(2);
		selectDepartureDateBus(departureDate); // select Departure Date
		BrowserActions.nap(2);
		BrowserActions.scrollToView(logoYatra, driver);
		selectReturnDateBus(returnDate); // select Return Date
		BrowserActions.nap(2);
		BrowserActions.scrollToView(logoYatra, driver);
		PassengerInfoBus(passengerInfo); // select Passengers
		BrowserActions.nap(2);
		Log.event("Successfully Filled RoundTrip Bus Search fields");
	}

	/**
	 * To select OneWay Bus search Fields
	 * 
	 * @throws Exception
	 */

	public void selectOneWayBusSearchFields(String origin, String destination, String departureDate,
			String passengerInfo) throws Exception {
		BrowserActions.nap(2);
		enterOriginBus(origin); // enter Origin value
		BrowserActions.nap(2);
		enterDestinationBus(destination); // enter Destination value
		BrowserActions.nap(2);
		Utils.setMousePositionOffPage(driver);
		Utils.scrollPage(driver, Constants.C_Page_Top);	
		selectDepartureDateBus(departureDate); // select Departure Date
		Utils.setMousePositionOffPage(driver);
		Utils.scrollPage(driver, Constants.C_Page_Top);
		BrowserActions.nap(2);
		PassengerInfoBus(passengerInfo); // select Passengers 
		Log.event("Successfully Filled OneWay Bus Search fields");

	}

	/**
	 * Getting the text from the Bus Info
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextErrorMsgEmptyCity() throws Exception {
		Utils.waitForElement(driver, txtErrorMsgEmptyCity);
		String txtDetails = BrowserActions.getText(driver, txtErrorMsgEmptyCity,
				"Getting text from the Bus Dropping Point");
		return txtDetails;
	}



	public Boolean incorrectCity() throws Exception {
		BrowserActions.typeOnTextField(txtTrainOrigin, "xyz", driver, "Invalid Origin city");
		Utils.waitForPageLoad(driver);
		String cityError = BrowserActions.getText(driver, txtTrainOrigin, "Incorrect Orgin City");
		if (cityError.startsWith("No match"))
		{
			return true;
		}

		return false;

	}
	/**
	 * Getting the text from the Bus Info
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextErrorSameCity() throws Exception {
		Utils.waitForElement(driver, txtErrorMsgEmptyCity);
		String txtDetails = BrowserActions.getText(driver, txtErrorMsgEmptyCity,
				"Getting text from the Bus Dropping Point");
		return txtDetails;
	}


	/**
	 * Enter Train Destination
	 * 
	 * @param trainDestination
	 *            as string
	 * @throws Exception
	 */
	public void enterTrainDestination(String trainDestination) throws Exception {
		Utils.waitForElement(driver, txtTrainDestination);
		BrowserActions.typeOnTextField(txtTrainDestination, trainDestination, driver, "Select Destination");
		Log.event("Entered the Destination: " + trainDestination);
	}

	/**
	 * To click search button on Home page for Trains
	 * 
	 * @throws Exception
	 */

	public TrainSearchResult clickTrainBtnSearch() throws Exception {
		// final long startTime = StopWatch.startTime();
		BrowserActions.clickOnElement(btnTrainSearch, driver, "Train Search");
		Utils.waitForPageLoad(driver);
		return new TrainSearchResult(driver).get();

	}


	public void EnterOriginCity(String trainorigin) throws Exception {
		BrowserActions.typeOnTextField(txtTrainOrigin,trainorigin, driver, "Invalid Origin city");	
		Log.event("Entered the Origin: " + trainorigin);

	}


	/**
	 * To select Departure Date
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public String selectTrainDepartureDate(String trainDepartureDate) throws Exception {
		int iDay = Integer.parseInt(trainDepartureDate);
		String date = utils.dateGenerator("yyyy_M_d", iDay);
		int month = Integer.parseInt(date.split("_")[1]);
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(dateTrainDeparture, driver, "clicking on Bus Return date icon");
		selectMonth_Bus.get(month - 3).click();
		Utils.waitForElement(driver, driver.findElement(By.xpath(departureDateLocator+date+"'])[8]")));
		driver.findElement(By.xpath(departureDateLocator+date+"'])[8]")).click();
		/*BrowserActions.nap(2);
           List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
           BrowserActions.nap(2);
           datePicker.get(7).click();*/
		Log.event("Selected Bus Return Date: " + date + "(YY/MM/DD)");
		return date;
	}


	/**
	 * To select Train search Fields
	 * 
	 * @throws Exception
	 */

	public void selectTrainSearchFields(String trainOrigin, String trainDestination, String trainDepartureDate) throws Exception {
		BrowserActions.nap(2);
		enterTrainOrigin(trainOrigin); // enter Origin value
		enterTrainDestination(trainDestination); // enter Destination value
		BrowserActions.nap(3);
		selectTrainDepartureDate(trainDepartureDate); // select Departure Date
		Log.event("Successfully selected OneWay Flight Search fields");

	}

	public void clickTrainTab() throws Exception {
		
		
		// final long startTime = StopWatch.startTime();
		BrowserActions.clickOnElement(lnkTrains, driver, "Train Search");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To click search button on Home page
	 * 
	 * @throws Exception
	 */
	public void clickOnSearchBus() throws Exception {
		BrowserActions.clickOnElement(btnSearchBus, driver, "Search Button");
		Utils.waitForPageLoad(driver);
	}
	/**
	 * Getting the text from the Bus Info
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextErrorMsg() throws Exception {
		Utils.waitForElement(driver, txtErrorMsgEmptyCity);
		String txtDetails = BrowserActions.getText(driver, txtErrorMsgEmptyCity,
				"Getting text from the Bus Dropping Point");
		return txtDetails;
	}
	/**
	 * Getting the text from the Bus Info
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextErrorIncorrectCity() throws Exception {
		Utils.waitForElement(driver, txtErrorMsgIncorrectCity);
		String txtDetails = BrowserActions.getText(driver, txtErrorMsgIncorrectCity,
				"Getting text from the Bus Dropping Point");
		return txtDetails;
	}



	public Bookings navigateToBooking() throws Exception{
		BrowserActions.moveToElementJS(driver, drpdwnUserLogin);
		BrowserActions.clickOnElement(lnkMyBooking, driver, "Clicked on Login user dropdown");	
		return new Bookings(driver).get();
	}




	/**
	 * Description:to Click on Main Menu and sub menu
	 * @param driver
	 * @param sMainMenu
	 * @param sSubMenu
	 * @throws Exception 
	 */
	public LoginPage clickOnMainMenu(WebDriver driver, String sMainMenu, String sSubMenu) throws Exception

	{/*

		WebElement wMainMenu=driver.findElement(By.xpath("//div[@class='menu']//a[contains(.,'"+sMainMenu+"')]"));

		Actions action=new Actions(driver);
		Utils.waitForElement(driver, wMainMenu, 5);
		Log.message("clicking on "+sMainMenu+" in main Navigation");
		Point location=wMainMenu.getLocation();
		int xCoordinate=location.getX();
		int yCoordinate=location.getY();
		System.out.println();
		action.moveToElement(wMainMenu, xCoordinate, yCoordinate).clickAndHold().build().perform();
		BrowserActions.clickOnElement(wMainMenu, driver, "clicking on "+sMainMenu+" in main Navigation");

		if("My Account".equalsIgnoreCase(sMainMenu.trim()))
		{
			Log.message("clicking on "+sSubMenu+" under "+sMainMenu+"");
			WebElement wSubMenu=driver.findElement(By.xpath("//div[@class='user-drop-ddn-out header-dropdown']//a[text()='"+sSubMenu+"']"));
			BrowserActions.clickOnElement(wSubMenu, driver, sSubMenu+" under "+sMainMenu+"");

		}

	 */
		//this is just patch as of now will work on it
		if(sSubMenu.contains("Agent"))
		{
			driver.navigate().to("https://www.yatra.com/agents");
		}
		else if(sSubMenu.contains("Corporate"))

		{
			driver.navigate().to("https://www.yatra.com/corporatetravel");
		}
		else if(sSubMenu.equalsIgnoreCase("Login"))

		{
			driver.navigate().to("https://secure.yatra.com/social/common/yatra/signin.htm");
		}
		return new LoginPage(driver).get();
	}
	/**
	 * Description: to select date from  calendar (it will work for depart date and return date)
	 * @author harveer.singh
	 * @param: String date in (yyyy_mm_dd) 
	 */
	@SuppressWarnings("static-access")
	public void datePicker(WebDriver driver,String departureDate)

	{
		int iDay = Integer.parseInt(departureDate);
		String date = utils.dateGenerator("yyyy_M_d", iDay);
		int year=Integer.parseInt(date.split("_")[0]);
		int month = Integer.parseInt(date.split("_")[1]);
		int day=Integer.parseInt(date.split("_")[2]);


		WebElement element=driver.findElement(By.xpath("/*[@class='month-list'])[1]//*[@href='#PegasusCal-0-month-"+month+"-"+year+"']"));
		element.click();
		WebElement calndr=driver.findElement(By.xpath("//*[@id='PegasusCal-0-month-"+month+"-"+year+"']//a[@id='a_"+year+"_"+month+"_"+day+"']"));


	}

	/***
	 * in this we are getting error text from the suggestion drpdwn after entering invalid code in train
	 * @return
	 * @throws Exception
	 */
	public String getErrorTextAfterInvalidTxt() throws Exception{
		return BrowserActions.getText(driver, txtErrorMsgIncorrectCity, "Getting error message after entering invalid city");
	}


	@FindBy(css="div[class='overview']>li[class*='ac']>strong")
	private WebElement lstOriginSuggestion;
	/**
	 * in this we are getting text from the suggestion dropdown after entering the origincity name of train
	 * @return
	 * @throws Exception
	 */
	public String getTextFrmSuggestionInOriginTrain() throws Exception{
		return BrowserActions.getText(driver, lstOriginSuggestion, "Getting text from the list of suggestion");
	}
	/**
	 * Entert Activites Origin
	 * 
	 * @param origin
	 *            as string
	 * @throws Exception
	 */
	public void enterActivitiesOrigin(String origin) throws Exception {
		Utils.waitForElement(driver, txtActivitesOrigin);		
		BrowserActions.typeOnTextField(txtActivitesOrigin, origin, driver, "Select Origin");			
		Log.event("Entered the Origin: " + origin);		
	}
	/**
	 * To click search button on Home page
	 * 
	 * @throws Exception
	 */
	public SearchResultActivites clickOnSearchActivites() throws Exception {
		BrowserActions.clickOnElement(btnSearchActivties, driver, "Search Button");
		Utils.waitForPageLoad(driver);
		return new SearchResultActivites(driver).get();
	}
	/**
	 * Getting the text from the Auto Suggestion DropDown
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextAutoSuggestionCityName() throws Exception {
		String txtDetails = BrowserActions.getText(driver, listautoSuggestion,
				"Getting text from the Auto Suggestion City Name");
		return txtDetails;
	}


	/**
	 * To click Non Stop Flights Checkbox
	 * 
	 * @throws Exception
	 */
	public void clickNonStopFlightsCheckbox() throws Exception {
		Utils.waitForElement(driver, chkNonStopFlights);	
		BrowserActions.clickOnElement(chkNonStopFlights, driver, "Click Non Stop Flights Checkbox");
		Log.event("Clicked Non Stop Flights in HomePage");
	}
	
		
	
	/**
	 * Description: to close notification which display randomly on SRP page
	 * 
	 * @throws Exception
	 */

	public void closeINotificationAtTopSRP() throws Exception {	
		BrowserActions.nap(4);		
		if (BrowserActions.isElementPresent(driver, IframeNotification) == true) {
		//if(IframeNotification.isDisplayed()){
			BrowserActions.switchToIframe(driver, IframeNotification);			
			if(BrowserActions.isElementPresent(driver, btnCloseIframeNotification) == true){ 
				BrowserActions.clickOnElement(btnCloseIframeNotification, driver, "Button to close Iframe Notification at top on SRP");
			}else if(BrowserActions.isElementPresent(driver, btnCloseIframeNotification_Double) == true){
				BrowserActions.clickOnElement(btnCloseIframeNotification_Double, driver, "Button to close Iframe Notification at Left side bottom on SRP");
			}		
			BrowserActions.switchToDefault(driver);
			BrowserActions.nap(2);	
		} else {
			Log.event("Not displayed Iframe Notification at Top or Bottom on SRP ");
		}
	}		



	
//*************************************************************************************************************************

    //*******************Fresco**********************//

//*************************************************************************************************************************

	@FindBy(css = "ul[class='mac-scroll scrollable'] li")
	private List<WebElement> lnkCitiesList;

	@FindBy(css = "ul[class='mac-scroll scrollable']")
	WebElement lnkAutoSuggestionsGrid;

	@FindBy(css = "ul[class='mac-scroll scrollable'] li[class='ac_even ac_over']")
	private WebElement lnkFlightAutoSuggestionName;

	@FindBy(css = "input#BE_hotel_destination_city")
	private WebElement txtHotelCity;

	@FindBy(css = "ul[class='mac-scroll scrollable'] li[class='ac_odd ac_over']")
	private WebElement lnkHotelCityAutoSuggestionName;

	
	@FindBy(css = "a[id='booking_engine_flight_hotels']")
	private WebElement lnkFlightsAndHotels;

	@FindBy(css = "form[id='BE_byop_form'] a[title='One Way']")
	private WebElement lnkOneWay_FlightsAndHotels;

	@FindBy(css = "form[id='BE_byop_form'] a[title='Round Trip']")
	private WebElement lnkRoundTrip_FlightsAndHotels;

	@FindBy(css = "#BE_byop_origin_city")
	private WebElement txtOrigin_FlightsAndHotels;

	@FindBy(css = "#BE_homestay_destination_city")
	private WebElement txtCity_Homestays;

	@FindBy(css = "#BE_holiday_leaving_city")
	private WebElement txtCity_Holidays;

	@FindBy(css = "ul[class='mac-scroll scrollable'] li[class='ac_odd']")
	private WebElement lnkActivitiesAutoSuggestionName;

	@FindBy(css = "ul[class='mac-scroll scrollable'] p[class='ac_cityname']")
	private List<WebElement> txtCityNames;

	@FindBy(css = "ul[class='mac-scroll scrollable'] li[class='ac_even ac_over'] p[class='ac_cityname']")
	private WebElement lnkFlightAirportCityName;

	@FindBy(css = "ul[class='mac-scroll scrollable'] li[class='ac_even ac_over'] div[class='ac_country']")
	private WebElement lnkFlightAirportCountryName;

	@FindBy(css = "div[class='toasterHolder']")
	private WebElement txtErrorMessageInFlightDestination;
		
	@FindBy(css = "a[title='Explore']")
	private WebElement lnkExplore;
	
	@FindBy(css = "#graphicalViewBtn")
	private WebElement lnkFindNowInLowestFareFinder;
	
	@FindBy(css = "a[class='orange-btn eventTrackable js-prodSpecEvtCat']")
	private WebElement lnkFindNowInDestinationFinder;
	
	@FindBy(css = "#BE_flight_origin_city_gp")
	private WebElement txtOrigin_LowestFareFinder;

	@FindBy(css = "#BE_flight_arrival_city_gp")
	private WebElement txtDestination_LowestFareFinder;
	
	@FindBy(css = "input[id='oneway']")
	private WebElement btnOneWay_DestinationFinder;
	
	@FindBy(css = "input[id='round']")
	private WebElement btnRoundTrip_DestinationFinder;
	
	@FindBy(css = "input[placeholder='Origin']")
	private WebElement txtOrigin_DestinationFinder;

	@FindBy(css = "input[placeholder='Destination']")
	private WebElement txtDestination_DestinationFinder;
	
	@FindBy(css = "body[id='extreme-search'] li[class='ac_even ac_over']")
	private WebElement lnkFlightAutoSuggestion_DestinationFinder;
	
	@FindBy(css = "#extreme_origin")
	private WebElement txtOrgin_TravelBudget;
	
	@FindBy(css = "div[id='extremePadCont'] h2[class='main-title']")
	private WebElement txtTravelBudgetTitle;
	
	@FindBy(css = "#BE_byop_arrival_city")
	private WebElement txtDestination_FlightsAndHotels;
	
	@FindBy(css="li[id='userSignInStrip'] a[title='My Bookings']")
	private WebElement lnkMyBookings;
	
	@FindBy(css="li[id='userSignInStrip'] a[title='Yatra For Corporates']")
	private WebElement lnkCorporateLogin;
	
	@FindBy(css="li[id='userSignInStrip'] a[title='Yatra for Travel Agents']")
	private WebElement lnkTravelAgent;
	
	@FindBy(css="li[id='userSignInStrip'] a[title='My eCash']")
	private WebElement lnkMyECash;
		
	@FindBy(css = "#cutomerSupportNav")
	private WebElement lnkSupport;
	
	@FindBy(css="li[id='cutomerSupportNav'] a[title='Make a Payment']")
	private WebElement lnkMakePayment;
	
	@FindBy(css="li[id='cutomerSupportNav'] a[title='Contact Us']")
	private WebElement lnkContactUS;

	@FindBy(css="li[id='cutomerSupportNav'] a[title='Flights Cancellation Charges']")
	private WebElement lnkFlightsCancellationCharges;
	
	@FindBy(css="li[id='cutomerSupportNav'] a[title='Complete Booking']")
	private WebElement lnkCompleteBooking;
	
	@FindBy(css="div[class='row boxSlider boxSliderYtSpcl'] a:nth-child(2)")
	private WebElement lnkYatraSpecials;

	@FindBy(css="div[id='galleryGrid'] article:nth-child(1) a[class='catg-content']")
	private WebElement lnkPerfectHolidays;
	
	@FindBy(css="table[class='resultsScroll sorterBody'] tr:nth-child(1) td[class='price-right']")
	private WebElement lnkTravelBudget;
		
	@FindBy(css="#discountHdrLink")
	private WebElement lnkSpecialDeals;
	
	@FindBy(css="ul[id='saveBigDD'] a[title='Offers']")
	private WebElement lnkOffer;	
	
	@FindBy(css="#offer_sub_cat_21")
	private WebElement lnkDomesticFlights_Offer;
	
	@FindBy(css="#offer_sub_cat_22")
	private WebElement lnkIntlFlights_Offer;
	
	@FindBy(css="#offer_sub_cat_23")
	private WebElement lnkHotels_Offer;
	
	@FindBy(css="#offer_sub_cat_128")
	private WebElement lnkHomeStayss_Offer;
	
	@FindBy(css="#offer_sub_cat_30")
	private WebElement lnkOthers_Offer;
	
	@FindBy(css="#offer_sub_cat_33")
	private WebElement lnkMobile_Offer;
	
	@FindBy(css="#offer_sub_cat_31")
	private WebElement lnkHolidays_Offer;
	
	@FindBy(css="#offer_sub_cat_34")
	private WebElement lnkAdventureHolidays_Offer;
	
	@FindBy(css="#offer_sub_cat_56")
	private WebElement lnkBus_Offer;
	
	@FindBy(css="#offer_sub_cat_123")
	private WebElement lnkActivity_Offer;
	
	
	// **********************Fresco Functions*******************************************
	/**
	 * Getting the text from the Auto Suggestion Source city name
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFlightAutoSuggestionSourceCityName() throws Exception {
		String txtCityName = BrowserActions.getText(driver, lnkFlightAutoSuggestionName, "Flights Auto Suggestion City Name");
		return txtCityName;
	}

	/**
	 * Getting the text from the Auto Suggestion DropDown
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean getCitiesListCount() throws Exception {
		boolean status = false;
		int citiesCount = lnkCitiesList.size();
		if (citiesCount == 10) {
			Log.event("Ten cities list displayed when clicked on booking engine");
			status = true;
		} else {
			Log.event("Ten cities list not displayed when clicked on booking engine");
			status = false;
		}
		return status;
	}

	/**
	 * verify the Cities grid
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean getAutoSuggestionGrid() throws Exception {
		boolean status = false;
		if (BrowserActions.isElementPresent(driver, lnkAutoSuggestionsGrid) == true) {
			status = true;
		}
		return status;
	}

	/**
	 * Enter Hotel city name
	 * 
	 * @param origin
	 *            as string
	 * @throws Exception
	 */
	public void enterHotelCity(String origin) throws Exception {
		Utils.waitForElement(driver, txtHotelCity);
		BrowserActions.typeOnTextField(txtHotelCity, origin, driver, "Hotel city text field");
		BrowserActions.nap(3);		
		Log.event("Entered the Hotel City: " + origin);
	}

	/**
	 * Getting the text from the Auto Suggestion DropDown
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextHotelAutoSuggestionCityName() throws Exception {
		String txtCityName = BrowserActions.getText(driver, lnkHotelCityAutoSuggestionName,	" Hotel Auto Suggestion City Name ");
		return txtCityName;
	}

	/**
	 * To click Flights + Hotels link in HomePage
	 * 
	 * @throws Exception
	 */
	public void clickFlightsAndHotels() throws Exception {
		BrowserActions.clickOnElement(lnkFlightsAndHotels, driver, "Flights+Hotels link");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To select Trip Type in Flights+Hotels
	 * 
	 * @param tripType
	 *            as string
	 * @throws Exception
	 */
	public void selectTripType_FlightsAndHotels(String tripType) throws Exception {
		if (tripType.equals(Constants.C_ONEWAY)) {
			BrowserActions.clickOnElement(lnkOneWay_FlightsAndHotels, driver, "One Way");
			Utils.waitForPageLoad(driver);
			Log.event("Successfully selected OneWay option in Search Fields");
		} else if (tripType.equals(Constants.C_ROUNDTRIP)) {
			BrowserActions.clickOnElement(lnkRoundTrip_FlightsAndHotels, driver, "Round Trip");
			Utils.waitForPageLoad(driver);
			Log.event("Successfully selected RoundTrip option in Search Fields");
		}
	}

	/**
	 * Enter Origin for Flights+Hotels
	 * 
	 * @param origin
	 *            as string
	 * @throws Exception
	 */
	public void enterOriginInFlightsAndHotels(String origin) throws Exception {
		Utils.waitForElement(driver, txtOrigin_FlightsAndHotels);
		BrowserActions.typeOnTextField(txtOrigin_FlightsAndHotels, origin, driver, "Select Origin");
		BrowserActions.nap(3);		
		Log.event("Entered the Origin: " + origin);
	}

	/**
	 * Enter City for Homestays
	 * 
	 * @param city
	 *            as string
	 * @throws Exception
	 */
	public void enterHomestaysCity(String city) throws Exception {
		Utils.waitForElement(driver, txtCity_Homestays);
		BrowserActions.typeOnTextField(txtCity_Homestays, city, driver, "Select Homestays City");
		BrowserActions.nap(2);		
		Log.event("Entered the Homestays City: " + city);
	}

	/**
	 * Enter City for Holidays
	 * 
	 * @param city
	 *            as string
	 * @throws Exception
	 */
	public void enterHolidaysCity(String city) throws Exception {
		Utils.waitForElement(driver, txtCity_Holidays);
		BrowserActions.typeOnTextField(txtCity_Holidays, city, driver, "Select Holidays City");
		BrowserActions.nap(3);		
		Log.event("Entered the Holidays City: " + city);
	}

	/**
	 * Getting the text from the Auto Suggestion DropDown
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextActivitiesAutoSuggestionCityName() throws Exception {
		String txtCityName = BrowserActions.getText(driver, lnkActivitiesAutoSuggestionName, "Activities Auto Suggestion City Name ");
		return txtCityName;
	}

	/**
	 * Getting the text form Airline Names
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getSourceCitiesNamesInFlight() throws Exception {
		List<String> cityNames = new ArrayList<String>();
		for (int i = 1; i <= txtCityNames.size(); i++) {
			WebElement cityNameEle = driver.findElement(By.cssSelector("ul[class='mac-scroll scrollable'] li:nth-child(" + i + ") p[class='ac_cityname']"));
			BrowserActions.scrollToView(cityNameEle, driver);
			String cityName = cityNameEle.getText().toString().trim();
			cityNames.add(cityName);
		}
		Log.event("City Names : " + cityNames);		
		return cityNames;
	}

	/**
	 * Getting the text from the Flights Airport City Name
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFlightAirportCityName() throws Exception {
		String txtCityName = BrowserActions.getText(driver, lnkFlightAirportCityName, "Flights Airport City Name");
		return txtCityName;
	}

	/**
	 * Getting the text from the Flights Airport Country Name
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFlightAirportCountryName() throws Exception {
		String txtCityName = BrowserActions.getText(driver, lnkFlightAirportCountryName, "Flights Airport Country Name");
		return txtCityName;
	}

	/**
	 * Getting the text from the get error message in Flight destination
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getErroeMessageInFlightDestination() throws Exception {
		String txtCityName = BrowserActions.getText(driver, txtErrorMessageInFlightDestination, "Flight destination Error Message");
		return txtCityName;
	}

	/**
	 * To click search button on Home page
	 * 
	 * @throws Exception
	 */

	public void clickSearchButton() throws Exception {
		BrowserActions.clickOnElement(btnSearch, driver, "Search");
	}

	/**
	 * Getting the Source city name in Booking engine
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSourceCityName() throws Exception {
		String txtCityName = BrowserActions.getText(driver, txtOrigin, "Flight Soure City name");
		return txtCityName;
	}
	
	/**
	 * Getting the Destination city name in Booking engine
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDestinationCityName() throws Exception {
		String txtCityName = BrowserActions.getText(driver, txtDestination, "Flight Destination City name");
		return txtCityName;
	}
	
	/**
	 * To click Find Now button in Lowest Fare Finder Popup
	 * 
	 * @throws Exception
	 */
	public void clickFindNowInLowestFareFinder() throws Exception {
		BrowserActions.clickOnElement(lnkFindNowInLowestFareFinder, driver, "LowestFareFinder Find Now button");
		//Utils.waitForPageLoad(driver);
		BrowserActions.nap(5);
	}
	
	/**
	 * To click Find Now button in Destination Finder Popup
	 * 
	 * @throws Exception
	 */
	public void clickFindNowInDestinationFinder() throws Exception {
		BrowserActions.clickOnElement(lnkFindNowInDestinationFinder, driver, "DestinationFinder Find Now button");
		//Utils.waitForPageLoad(driver);
		BrowserActions.nap(5);
	}
	
	
	/**
	 * Enter Origin
	 * 
	 * @param origin
	 *            as string
	 * @throws Exception
	 */
	public void enterOrigin_LowestFareFinder(String origin) throws Exception {
		Utils.waitForElement(driver, txtOrigin_LowestFareFinder);		
		BrowserActions.typeOnTextField(txtOrigin_LowestFareFinder, origin, driver, "Select Origin");
		BrowserActions.nap(3);
		Utils.waitForElement(driver, txtCityOver);			
		Log.event("Entered the Origin: "+origin);		
	}

	/**
	 * Enter Destination
	 * 
	 * @param destination
	 *            as string
	 * @throws Exception
	 */
	public void enterDestination_LowestFareFinder(String destination) throws Exception {		
		Utils.waitForElement(driver, txtDestination_LowestFareFinder);
		BrowserActions.typeOnTextField(txtDestination_LowestFareFinder, destination, driver, "Select Destination");		
		BrowserActions.nap(3);
		Utils.waitForElement(driver, txtCityOver);		
		Log.event("Entered the Destination: " + destination);
	}

	/**
	 * Enter Origin
	 * 
	 * @param origin
	 *            as string
	 * @throws Exception
	 */
	public void enterOrigin_DestinationFinder(String origin) throws Exception {
		Utils.waitForElement(driver, txtOrigin_DestinationFinder);		
		txtOrigin_DestinationFinder.clear();
		BrowserActions.typeOnTextField(txtOrigin_DestinationFinder, origin, driver, "Select Origin");
		BrowserActions.nap(3);
		Utils.waitForElement(driver, txtCityOver);			
		Log.event("Entered the Origin: "+origin);		
	}

	/**
	 * Enter Destination
	 * 
	 * @param destination
	 *            as string
	 * @throws Exception
	 */
	public void enterDestination_DestinationFinder(String destination) throws Exception {		
		Utils.waitForElement(driver, txtDestination_DestinationFinder);
		txtOrigin_DestinationFinder.clear();
		BrowserActions.typeOnTextField(txtDestination_DestinationFinder, destination, driver, "Select Destination");		
		BrowserActions.nap(3);
		Utils.waitForElement(driver, txtCityOver);		
		Log.event("Entered the Destination: " + destination);
	}

	/**
	 * To click Find Now button in Destination Finder Popup
	 * 
	 * @throws Exception
	 */
	public void clickOneWayInDestinationFinder() throws Exception {
		BrowserActions.clickOnElement(btnOneWay_DestinationFinder, driver, "DestinationFinder OneWay radio button");
		BrowserActions.nap(5);
	}
	
	
	public void HandleWindowTabs() throws Exception {
		BrowserActions.nap(2);
		String winHandleBefore = driver.getWindowHandle();
		//JavascriptExecutor js = (JavascriptExecutor) driver; js.executeScript("window.history.go(-1)");
		Set<String> handles = driver.getWindowHandles();
		for (String winHandle : handles) {
			if (!winHandle.equals(winHandleBefore)) {
				driver.switchTo().window(winHandle);
				break;
			}
		}
		BrowserActions.nap(3);
	}
	
	/**
	 * Getting the text from the Auto Suggestion Source city name
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextFlightAutoSuggestionCityName_Destinationfinder() throws Exception {
		String txtCityName = BrowserActions.getText(driver, lnkFlightAutoSuggestion_DestinationFinder, "Flights Destination Finder Auto Suggestion City Name");
		return txtCityName;
	}
	
	/**
	 * Getting the text form Airline Names
	 * 
	 * @return
	 * @throws Exception
	 *//*
	public List<String> getSourceCitiesFullDetailsInFlight() throws Exception {
		List<String> cityNames = new ArrayList<String>();
		for (int i = 1; i <= txtCityNames.size(); i++) {
			WebElement cityNameEle = driver.findElement(By.cssSelector("ul[class='mac-scroll scrollable'] li:nth-child(" + i + ")"));
			BrowserActions.scrollToView(cityNameEle, driver);
			String cityName = cityNameEle.getText().toString().trim();
			cityNames.add(cityName);
		}
		Log.event("Cities Full Details : " + cityNames);		
		return cityNames;
	}*/
	
	
	/**
	 * Enter Origin for Travel Budget
	 * 
	 * @param origin
	 *            as string
	 * @throws Exception
	 */
	public void enterOriginInTravelBudget(String origin) throws Exception {
		Utils.waitForElement(driver, txtOrgin_TravelBudget);
		BrowserActions.scrollToView(txtTravelBudgetTitle, driver);
		//BrowserActions.scrollToView(txtOrgin_TravelBudget, driver);
		txtOrgin_TravelBudget.clear();
		BrowserActions.typeOnTextField(txtOrgin_TravelBudget, origin, driver, "Select Travel Budget Origin");
		BrowserActions.nap(3);		
		Log.event("Entered the Travel Budget Origin: " + origin);
	}
	
	 /* Getting the City Name from the TravelBudget 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextTravelBudgetCityName() throws Exception {
		String txtCityName = BrowserActions.getText(driver, lnkFlightAutoSuggestionName, "TravelBudget City Name");
		return txtCityName;
	}

	/**
	 * Enter destination for Flights+Hotels
	 * 
	 * @param origin
	 *            as string
	 * @throws Exception
	 */
	public void enterDestinationInFlightsAndHotels(String destination) throws Exception {
		Utils.waitForElement(driver, txtDestination_FlightsAndHotels);
		BrowserActions.typeOnTextField(txtDestination_FlightsAndHotels, destination, driver, "Select Destination");
		BrowserActions.nap(3);		
		Log.event("Entered the Destination: " + destination);
	}	
	
	/**
	 * Getting the Cities details from Flight booking engine
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getCitiesFullDetailsInFlight() throws Exception {
		List<String> cityNames = new ArrayList<String>();
		for (int i = 1; i <= txtCityNames.size(); i++) {
			WebElement cityNameEle = driver.findElement(By.cssSelector("ul[class='mac-scroll scrollable'] li:nth-child(" + i + ")"));
			BrowserActions.scrollToView(cityNameEle, driver);
			String cityName = cityNameEle.getText().toString().trim();
			cityNames.add(cityName);
		}
		Log.event("Cities Full Details : " + cityNames);		
		return cityNames;
	}
	
	/**
	 * Navigate to My Booking Page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToMyBooking() throws Exception{
		BrowserActions.mouseHover(driver, lnkMyaccount); 
		BrowserActions.clickOnElement(lnkMyBookings, driver, "My Bookings ");			
	}
	
	/**
	 * Navigate to Yatra For Corporates page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToCorporateLogin() throws Exception{
		BrowserActions.mouseHover(driver, lnkMyaccount); 
		BrowserActions.clickOnElement(lnkCorporateLogin, driver, "Yatra For Corporates");		
	}
	
	/**
	 * Navigate to Yatra For Travel Agents page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToTravelAgent() throws Exception{
		BrowserActions.mouseHover(driver, lnkMyaccount); 
		BrowserActions.clickOnElement(lnkTravelAgent, driver, "Yatra for Travel Agents");			
	}

	/**
	 * Navigate to eCash page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToeCash() throws Exception{
		BrowserActions.mouseHover(driver, lnkMyaccount); 
		BrowserActions.clickOnElement(lnkMyECash, driver, "My eCash");			
	}
	

	/**
	 * Navigate to Contact US page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToContactUS() throws Exception{
		BrowserActions.mouseHover(driver, lnkSupport); 
		BrowserActions.clickOnElement(lnkContactUS, driver, "ContactUS");		
	}
	
	/**
	 * Navigate to Make a payment page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToMakePayment() throws Exception{
		BrowserActions.mouseHover(driver, lnkSupport); 
		BrowserActions.clickOnElement(lnkMakePayment, driver, "Make a payment");		
	}	
	
	/**
	 * Navigate to Flights Cancellation Charges page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToFlightsCancellationCharges() throws Exception{
		BrowserActions.mouseHover(driver, lnkSupport); 
		BrowserActions.clickOnElement(lnkFlightsCancellationCharges, driver, "Flights Cancellation Charges");
	}	
	
	/**
	 * Navigate to Complete Bookings page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToCompleteBookings() throws Exception{
		BrowserActions.mouseHover(driver, lnkSupport); 
		BrowserActions.clickOnElement(lnkFlightsCancellationCharges, driver, "Complete Bookings");		
	}	

	/**
	 * Navigate to Yatra specials page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToYatraSpecials() throws Exception{
		BrowserActions.scrollToView(lnkYatraSpecials, driver);
		BrowserActions.clickOnElement(lnkYatraSpecials, driver, "Yatra specials");			
	}	

	/**
	 * Navigate to Perfect holidays page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToPerfectHolidays() throws Exception{
		BrowserActions.scrollToView(lnkPerfectHolidays, driver);
		BrowserActions.clickOnElement(lnkPerfectHolidays, driver, "Perfect holidays");		
	}	

	
	/**
	 * Navigate to Travel with in budget page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToTravelBudget() throws Exception{
		BrowserActions.scrollToView(lnkTravelBudget, driver);
		BrowserActions.clickOnElement(lnkTravelBudget, driver, "Travel with in budget");		
	}	


	/**
	 * Navigate to Special Deals -->Offers  page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToOffers() throws Exception{
		Utils.waitForElement(driver, lnkSpecialDeals);
		BrowserActions.scrollToView(lnkSpecialDeals,driver);	
		BrowserActions.clickOnElement(lnkOffer, driver, "SpecialDeals-- Offers");
		Utils.waitForElement(driver, lnkDomesticFlights_Offer);
	}	
	
	
	/**
	 * Navigate to Special Deals/Offers Domestic Flights page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToDomFligts() throws Exception{
		Utils.waitForElement(driver, lnkDomesticFlights_Offer);
		BrowserActions.clickOnElement(lnkDomesticFlights_Offer, driver, "Domestic Flights");		
	}	
	

	/**
	 * Navigate to Special Deals/Offers International Flights page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToIntlFlights() throws Exception{
		Utils.waitForElement(driver, lnkIntlFlights_Offer);
		BrowserActions.clickOnElement(lnkIntlFlights_Offer, driver, "International flights");		
	}	
	

	/**
	 * Navigate to Special Deals/Offers Hotels page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToHotels() throws Exception{
		Utils.waitForElement(driver, lnkHotels_Offer);
		BrowserActions.clickOnElement(lnkHotels_Offer, driver, "Hotels");		
	}	
	

	/**
	 * Navigate to Special Deals/Offers HomeStays page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToHomeStays() throws Exception{
		Utils.waitForElement(driver, lnkHomeStayss_Offer);
		BrowserActions.clickOnElement(lnkHomeStayss_Offer, driver, "HomeStays");		
	}	
	

	/**
	 * Navigate to Special Deals/Offers Others page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToOthers() throws Exception{
		Utils.waitForElement(driver, lnkOthers_Offer);
		BrowserActions.clickOnElement(lnkOthers_Offer, driver, "Others");		
	}	
	
	/**
	 * Navigate to Special Deals/Offers Mobile page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToMobile() throws Exception{
		Utils.waitForElement(driver, lnkMobile_Offer);
		BrowserActions.clickOnElement(lnkMobile_Offer, driver, "Mobile");		
	}	
	/**
	 * Navigate to Special Deals/Offers Holidays page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToHolidays() throws Exception{
		Utils.waitForElement(driver, lnkHolidays_Offer);
		BrowserActions.clickOnElement(lnkHolidays_Offer, driver, "Holidays");		
	}	
	

	/**
	 * Navigate to Special Deals/Offers Adventure Holidays page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToAdventureHolidays() throws Exception{
		Utils.waitForElement(driver, lnkAdventureHolidays_Offer);
		BrowserActions.clickOnElement(lnkAdventureHolidays_Offer, driver, "Adventure Holidays");		
	}	
	

	/**
	 * Navigate to Special Deals/Offers Bus page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToBus() throws Exception{
		Utils.waitForElement(driver, lnkBus_Offer);
		BrowserActions.clickOnElement(lnkBus_Offer, driver, "Bus");		
	}	
	

	/**
	 * Navigate to Special Deals/Offers Activity page
	 * 
	 * @return
	 * @throws Exception
	 */
	public void navigateToActivity() throws Exception{
		Utils.waitForElement(driver, lnkActivity_Offer);
		BrowserActions.clickOnElement(lnkActivity_Offer, driver, "Activity");		
	}	
	
	
	/**
	 * Navigate to Footer option
	 * 
	 * @return
	 * @throws Exception
	 */
	public void selectFooterOption(String footerOption) throws Exception {
		BrowserActions.nap(5);
		List<WebElement> lstElement = footerOptions;
			for (WebElement e : lstElement) {
				if (e.findElement(By.cssSelector("a")).getText().equals(footerOption)) {
					BrowserActions.scrollToViewElement(e.findElement(By.cssSelector("a")), driver);
					BrowserActions.clickOnElement(e.findElement(By.cssSelector("a")), driver, "list elements");
					break;
			}
		}
	}
	/**
	 * Navigate to About Us
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnAboutUs() throws Exception{
		Utils.waitForElement(driver, aboutUs);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(aboutUs, driver, "About Us");		
	}	
	/**
	 * Navigate to About Us Team
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnAboutUsTeam() throws Exception{
		Utils.waitForElement(driver, aboutUsTeam);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(aboutUsTeam, driver, "About Us Team");		
	}	
	/**
	 * 
	 * Navigate to SiteMap
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnSiteMap() throws Exception{
		Utils.waitForElement(driver, siteMap);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(siteMap, driver, "Site Map");		
	}
	/**
	 * 
	 * Navigate to Terms And Conditions
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnTermsAndConditions() throws Exception{
		Utils.waitForElement(driver, termsAndConditions);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(termsAndConditions, driver, "Terms And Conditions");	
	}	
	/**
	 * 
	 * Navigate to Terms And Conditions
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnPrivacyPolicy() throws Exception{
		Utils.waitForElement(driver, privacyPolicy);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(privacyPolicy, driver, "Terms And Conditions");	
	}
	/**
	 * 
	 * Navigate to User Agreement
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnUserAgreement() throws Exception{
		Utils.waitForElement(driver, userAgreement);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(userAgreement, driver, "User Agreement");
	}
	/**
	 * 
	 * Navigate to Awards Won
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnAwardsWon() throws Exception{
		Utils.waitForElement(driver, awardsWon);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(awardsWon, driver, "Awards Won");
	}
	/**
	 * 
	 * Navigate to Yatra in the news
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnYatrainthenews() throws Exception{
		Utils.waitForElement(driver, awardsWon);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(awardsWon, driver, "Yatra in the news");
	}
	/**
	 * 
	 * Navigate to Press Release
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnPressRelease() throws Exception{
		Utils.waitForElement(driver, pressReleases);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(pressReleases, driver, "Press Release");
	}
	/**
	 * 
	 * Navigate to Yatra Holiday Advisors
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnYatraHolidayAdvisors() throws Exception{
		Utils.waitForElement(driver, yatraHolidayAdvisors);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(yatraHolidayAdvisors, driver, "Yatra Holiday Advisors");
	}
	/**
	 * 
	 * Navigate to Yatra Holiday Advisors
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnVisaInformation() throws Exception{
		Utils.waitForElement(driver, yatraHolidayAdvisors);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(visaInformation, driver, "VISA Information");
	}
	/**
	 * 
	 * Navigate to Register Your Hotel
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnRegisterYourHotel() throws Exception{
		Utils.waitForElement(driver, registerYourHotel);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(registerYourHotel, driver, "Register Your Hotel");
	}
	/**
	 * 
	 * Navigate to Advertise With Us
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ClickOnAdvertiseWithUs() throws Exception{
		Utils.waitForElement(driver, advertiseWithUs);
		Thread.sleep(3000);
		BrowserActions.clickOnElement(advertiseWithUs, driver, "Advertise With Us");
	}
	
	
}// Fresco