package com.Yatra.Pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
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
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class HomePage extends LoadableComponent<HomePage> {

	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/

	@FindBy(css = "input#BE_flight_origin_city")
	public WebElement txtOrigin;

	@FindBy(css = "input#BE_flight_arrival_city")
	WebElement txtDestination;

	@FindBy(css = "input#BE_flight_flsearch_btn")
	WebElement btnSearch;

	@FindBy(css = "input#BE_flight_depart_date")
	WebElement dateDeparture;

	@FindBy(css = "input#BE_flight_return_date")
	WebElement dateReturn;

	WebElement departureDate;

	@FindBy(id = "BE_flight_return_date")
	WebElement returnDate;

	@FindBy(css = "div[id='PegasusCal-0'] li a[href*='#PegasusCal-0-month-']")
	List<WebElement> selectMonth;

	@FindBy(css = "div[id='BE_flight_paxInfoBox']")
	WebElement passengerInfo;

	String dateLocator = "div[class='month-box'] table tbody td[class*='activeTD clsDateCell'] a[id='a_";

	String passengersLocator = "span[class='ddSpinnerPlus']";

	String passengerClassLocator = "div[id='flight_class_select_child'] ul li";

	@FindBy(css = "div[class='be-ddn-footer']>span[class='done']")
	WebElement submitPassengerClassInfo;

	@FindBy(css = "a[title='One Way']")
	public WebElement lnkOneWay;

	@FindBy(css = "a[title='Round Trip']")
	WebElement lnkRoundTrip;

	@FindBy(css = "a[title='Multicity']")
	WebElement lnkMultiCity;

	@FindBy(css = "#BE_flight_depart_date")
	WebElement txtDateDepart;

	@FindBy(xpath = "//form[@id='BE_flight_form']//li[3]/i")
	WebElement txtDeptDatePicker;

	@FindBy(xpath = "//form[@id='BE_flight_form']//li[4]/i")
	WebElement txtReturnDatePicker;

	@FindBy(css = "a#booking_engine_flights")
	WebElement lnkFlights;

	@FindBy(css = "a#booking_engine_hotels")
	WebElement lnkHotels;

	@FindBy(css = "a#booking_engine_homestays")
	WebElement lnkHomeStays;

	@FindBy(css = "a#booking_engine_holidays")
	WebElement lnkHolidays;

	@FindBy(css = "a#booking_engine_activities")
	WebElement lnkActivities;

	@FindBy(css = "a#booking_engine_buses")
	WebElement lnkBuses;

	@FindBy(css = "a#booking_engine_trains")
	WebElement lnkTrains;

	@FindBy(css = "a#booking_engine_cruise")
	WebElement lnkCruise;

	@FindBy(css = "#signInBtn")
	WebElement btnSignIn;

	@FindBy(css = "input#BE_flight_origin_city_1")
	public WebElement txtMulticity_Origin1;

	@FindBy(css = "input#BE_flight_origin_city_2")
	public WebElement txtMulticity_Origin2;

	@FindBy(css = "input#BE_flight_arrival_city_1")
	WebElement txtMulticity_Destination1;

	@FindBy(css = "input#BE_flight_arrival_city_2")
	WebElement txtMulticity_Destination2;

	@FindBy(css = "input#BE_flight_depart_date_1")
	WebElement dateMulticity_Departure1;

	@FindBy(css = "input#BE_flight_depart_date_2")
	WebElement dateMulticity_Departure2;

	@FindBy(css = "div[id='PegasusCal-7'] li a[href*='#PegasusCal-7-month-']")
	List<WebElement> selectMonth_MultiDepart1;

	@FindBy(css = "div[id='PegasusCal-8'] li a[href*='#PegasusCal-8-month-']")
	List<WebElement> selectMonth_MultiDepart2;

	@FindBy(css = "#userSignInStrip")
	WebElement lnkMyaccount;
	
	@FindBy(css = ".be-container-v2")
	WebElement searchPanel;

	@FindBy(css = "div[id='booking_engine_modues']>form>div>div[id='']>div[id='BE_bus_seats_msdd']>div[class='ddTitle borderRadiusTp']>span[class='ddSpinnerPlus']")
	WebElement btnIncreseSeat;
	
	@FindBy(css = "div[class*='selc-more-options mor-option trip-type']>span:nth-child(1)")
	WebElement lnkOneWayBus;
	
	@FindBy(css = "div[class*='selc-more-options mor-option trip-type']>span:nth-child(3)")
	WebElement lnkRoundTripBus;
	
	@FindBy(css = "#BE_bus_from_station")
	 WebElement txtOriginBus;
	
	@FindBy(xpath = "//input[@id='BE_train_from_station']")
	WebElement txtTrainOrigin;
	
	@FindBy(xpath = "//input[@id='BE_train_to_station']")
	WebElement txtTrainDestination;
	
	@FindBy(xpath = "//input[@id='BE_train_depart_date']")
	WebElement dateTrainDeparture;
	
	@FindBy(xpath="//input[@id='BE_train_search_btn']")
	WebElement btnTrainSearch;
	
	@FindBy(xpath= "//li[@class = 'ac_over']")
	WebElement txtErrorOriginTrain;

	@FindBy(css = "#BE_bus_to_station")
	WebElement txtDestinationBus;

	@FindBy(css = "#BE_bus_depart_date")
	WebElement dateDepartureBus;

	@FindBy(css = "#BE_bus_return_date")
	WebElement dateReturnBus;
	
	@FindBy(css = "#BE_bus_search_btn")
	WebElement btnSearchBus;
	
	@FindBy(css = "div[id='PegasusCal-7'] li a[href*='#PegasusCal-7-month-']")
	List<WebElement> selectMonth_Bus;
	
	@FindBy(css = "#toater_21")
	WebElement txtErrorMsgEmptyCity;
	
	@FindBy(css = "#toater_23")
	WebElement txtErrorMsgSameCity;
	
	
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
		appURL = "https://www.yatra.com/";
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);		
		elementLayer = new ElementLayer(driver);
	}// HomePage

	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, btnSearch))) {
			Log.fail("Home Page did not open up. Site might be down.", driver);

		}

	}// isLoaded

	@Override
	protected void load() {
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
		Log.event("Entered the Origin: " + origin);
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

	public SearchResult clickBtnSearch() throws Exception {
		// final long startTime = StopWatch.startTime();
		BrowserActions.clickOnElement(btnSearch, driver, "Search");
		Utils.waitForPageLoad(driver);
		return new SearchResult(driver).get();

	}

	public void selectDepartureDate_old(String date) throws Exception {
		int month = Integer.parseInt(date.split("_")[2]);
		BrowserActions.clickOnElement(dateDeparture, driver, "clicking on departure date icon");
		selectMonth.get(month - 2).click();
		List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		datePicker.get(0).click();
		Log.event("Selected Departure Date: " + date + "(YY/MM/DD)");
	}

	public void specifyPassengerInfo_old(String passengers) throws Exception {
		BrowserActions.clickOnElement(passengerInfo, driver, "Passenger Info");
		List<WebElement> updatePassengers = driver.findElements(By.cssSelector(passengersLocator));
		int adult = Integer.parseInt(passengers.split("_")[0]);
		int child = Integer.parseInt(passengers.split("_")[1]);
		int infant = Integer.parseInt(passengers.split("_")[2]);
		int passengerClass = Integer.parseInt(passengers.split("_")[3]);
		for (int i = 1; i < adult; i++) {
			updatePassengers.get(0).click();
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
		List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		datePicker.get(0).click();
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
		BrowserActions.mouseHover(driver, lnkMyaccount);*/
		//BrowserActions.moveToElementJS(driver, lnkMyaccount);
		//BrowserActions.actionClick(btnSignIn, driver, "Sign In");
		Utils.waitForElement(driver, btnSignIn);
		BrowserActions.javascriptClick(btnSignIn, driver, "Sign In");
		//Utils.waitForPageLoad(driver);
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
		BrowserActions.nap(3);
		selectDepartureDate(departureDate); // select Departure Date
		
		specifyPassengerInfo(passengerInfo); // select Passengers details(Adult,
												// Child, Infant)
		selectPassengerClass(passengerClass); // select Passengers class type
		clickDoneButtonInPassengerBox(); // click Done button

		Log.event("Successfully selected OneWay Flight Search fields");

	}

	/**
	 * To select Round Trip Flight search Fields
	 * 
	 * @throws Exception
	 */

	public void selectRoundTripFlightSearchFields(String origin, String destination, String departureDate,	String returnDate, String passengerInfo, String passengerClass) throws Exception {
		// selectRoundTrip();
		BrowserActions.nap(2);
		enterOrigin(origin); // enter Origin value
		enterDestination(destination); // enter Destination value
		selectDepartureDate(departureDate); // select Departure Date
		selectReturnDate(returnDate); // select Return Date
		specifyPassengerInfo(passengerInfo); // select Passengers details
												// (Adult, Child, Infant)
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
		BrowserActions.scrollToViewElement(dateDeparture, driver);
		Utils.waitForElement(driver, dateDeparture);
		BrowserActions.clickOnElement(dateDeparture, driver, "clicking on departure date icon");
		
		selectMonth.get(month - 2).click();
		List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		datePicker.get(0).click();
		Log.event("Selected Departure Date: " + date + "(YY/MM/DD)");
		return date;
	}

	/**
	 * To select Return Date
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public String selectReturnDate(String returnDate) throws Exception {
		int iDay = Integer.parseInt(returnDate);
		String date = utils.dateGenerator("yyyy_M_d", iDay);
		int month = Integer.parseInt(date.split("_")[1]);
		BrowserActions.nap(2);
		BrowserActions.scrollToViewElement(dateReturn, driver);
		BrowserActions.clickOnElement(dateReturn, driver, "clicking on return date icon");
		selectMonth.get(month - 2).click();
		List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		datePicker.get(0).click();
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
		Log.event("Entered the Destination: " + destination);
	}

	/**
	 * To select Multicity Date Departure1
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
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(dateMulticity_Departure1, driver, "clicking on MultiCity departure1 date icon");
		selectMonth_MultiDepart1.get(month - 3).click();
		List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		datePicker.get(7).click();
		Log.event("Selected MultiCity Departure1 Date: " + date + "(YY/MM/DD)");
		return date;
	}

	/**
	 * To select Multicity Date Departure2
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
		selectMonth_MultiDepart2.get(month - 3).click();
		List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		datePicker.get(8).click();
		Log.event("Selected MultiCity Departure2 Date: " + date + "(YY/MM/DD)");
		return date;
	}

	/**
	 * To select Multicity Flight search Fields
	 * 
	 * @throws Exception
	 */

	public void selectMultiCityFlightSearchFields(String origin1, String destination1, String origin2,
			String destination2, String departureDate1, String departureDate2, String passengerInfo,
			String passengerClass) throws Exception {
		enterMultiCityOrigin1(origin1); // enter Multicity Origin1 value
		enterMultiCityDestination1(destination1); // enter Multicity
													// Destination2 value

		enterMultiCityOrigin2(origin2); // enter Multicity Origin2 value
		enterMultiCityDestination2(destination2); // enter Multicity
													// Destination2 value

		selectMultiCityDateDeparture1(departureDate1); // select Multicity
														// Departure1 Date
		selectMultiCityDateDeparture2(departureDate1); // select Multicity
														// Departure2 Date
		specifyPassengerInfo(passengerInfo); // select Passengers details(Adult,
												// Child, Infant)

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
		int z = Integer.parseInt(passengers);
		for(int i=1 ; i<z ; i++){
			BrowserActions.clickOnElement(btnIncreseSeat, driver, "Passenger Info");
		}
			}
	
	
	/**
	 * To Select Bus Trip Type
	 * 
	 * @throws Exception
	 */
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
		selectMonth_Bus.get(month - 3).click();
		BrowserActions.nap(2);
		List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		BrowserActions.nap(2);
		datePicker.get(7).click();
		Log.event("Selected Bus Return Date: " + date + "(YY/MM/DD)");
		return date;
	}
	
	/**
	 * To Select Bus Depature Date
	 * 
	 * @throws Exception
	 */
	public String selectDepartureDateBus(String departureDate) throws Exception {
		int iDay = Integer.parseInt(departureDate);
		String date = utils.dateGenerator("yyyy_M_d", iDay);
		int month = Integer.parseInt(date.split("_")[1]);
		BrowserActions.nap(2);
		BrowserActions.clickOnElement(dateDepartureBus, driver, "clicking on Bus Depart date icon");
		selectMonth_Bus.get(month - 3).click();
		BrowserActions.nap(2);
		List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
		BrowserActions.nap(2);
		datePicker.get(7).click();
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
		Utils.waitForPageLoad(driver);
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
		selectReturnDateBus(returnDate); // select Return Date
		BrowserActions.nap(2);
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
		selectDepartureDateBus(departureDate); // select Departure Date
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
           BrowserActions.nap(2);
           List<WebElement> datePicker = driver.findElements(By.cssSelector(dateLocator + date + "']"));
           BrowserActions.nap(2);
           datePicker.get(7).click();
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
	
	
}// HomePage
