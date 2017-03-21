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
	
	@FindBy(css= "input#BE_flight_depart_date")
	WebElement dateDeparture;
	
	@FindBy(css= "input#BE_flight_return_date")
    WebElement dateReturn;
	
	WebElement departureDate;	

	@FindBy(id= "BE_flight_return_date")
	WebElement returnDate;

	@FindBy(css ="div[id='PegasusCal-0'] li a[href*='#PegasusCal-0-month-']" )
	List<WebElement> selectMonth;
	
	@FindBy(css= "div[id='BE_flight_paxInfoBox']")
	WebElement passengerInfo;

	String dateLocator="div[class='month-box'] table tbody td[class*='activeTD clsDateCell'] a[id='a_";	
	
	String passengersLocator="span[class='ddSpinnerPlus']";
	
	String passengerClassLocator="div[id='flight_class_select_child'] ul li";
	
	@FindBy(css = "div[class='be-ddn-footer'] span")
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
	
	@FindBy(css= "input#BE_flight_depart_date_1")
	WebElement dateMulticity_Departure1;
	
	@FindBy(css= "input#BE_flight_depart_date_2")
    WebElement dateMulticity_Departure2;
	
	@FindBy(css ="div[id='PegasusCal-7'] li a[href*='#PegasusCal-7-month-']" )
	List<WebElement> selectMonth_MultiDepart1;
	
	@FindBy(css ="div[id='PegasusCal-8'] li a[href*='#PegasusCal-8-month-']" )
	List<WebElement> selectMonth_MultiDepart2;
	
	@FindBy(css ="#userSignInStrip" )
	WebElement lnkMyaccount;
	
	
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
		//final long startTime = StopWatch.startTime();
		BrowserActions.clickOnElement(btnSearch, driver, "Search");
		Utils.waitForPageLoad(driver);
		return new SearchResult(driver).get();
	
	}
	
	public void selectDepartureDate_old(String date) throws Exception{
		int month=Integer.parseInt(date.split("_")[2]);
		BrowserActions.clickOnElement(dateDeparture, driver, "clicking on departure date icon");
		selectMonth.get(month-2).click();
		List<WebElement> datePicker =driver.findElements(By.cssSelector(dateLocator+date+"']"));
		datePicker.get(0).click();
		Log.event("Selected Departure Date: " + date+"(YY/MM/DD)");
	}
	
	
	public void specifyPassengerInfo_old(String passengers) throws Exception{
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
	public void selectReturnDate_old(String date) throws Exception{
		int month=Integer.parseInt(date.split("_")[1]); 
		Utils.waitForElement(driver, dateReturn);
		BrowserActions.clickOnElement(dateReturn, driver, "clicking on return date icon");
		selectMonth.get(month-2).click();
		List<WebElement> datePicker =driver.findElements(By.cssSelector(dateLocator+date+"']"));
		datePicker.get(0).click();
		Log.event("Selected Return Date: " + date+"(YY/MM/DD)");
	}
	
	/**
	 * To navigate to SignIn Page
	 * 
	 * @throws Exception
	 */
	public LoginPage navigateToSignIn() throws Exception {
		// click Login button on signin page
		BrowserActions.clickOnElement(btnSignIn, driver, "Sign In");
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
		}
	}

	/**
	 * To select OneWay Flight search Fields
	 * 
	 * @throws Exception
	 */
	
	public void selectOneWayFlightSearchFields(String origin, String destination, String departureDate,	String passengerInfo,String passengerClass) throws Exception {
		enterOrigin(origin); // enter Origin value
		enterDestination(destination); // enter Destination value
		BrowserActions.nap(3);
		selectDepartureDate(departureDate); // select Departure Date
		specifyPassengerInfo(passengerInfo); // select Passengers details(Adult, Child, Infant)
		selectPassengerClass(passengerClass); // select Passengers class type
		clickDoneButtonInPassengerBox(); // click Done button
        Log.event("Successfully selected OneWay Flight Search fields");

	}

	/**
	 * To select Round Trip Flight search Fields
	 * 
	 * @throws Exception
	 */
	

	public void selectRoundTripFlightSearchFields(String origin, String destination, String departureDate, String returnDate, String passengerInfo,String passengerClass) throws Exception {
		enterOrigin(origin); // enter Origin value
		enterDestination(destination); // enter Destination value
		selectDepartureDate(departureDate); // select Departure Date
		selectReturnDate(returnDate); // select Return Date
		specifyPassengerInfo(passengerInfo); // select Passengers details (Adult, Child, Infant)
		selectPassengerClass(passengerClass); // select Passengers class type
		clickDoneButtonInPassengerBox(); // click Done button	
		Log.event("Successfully selected RoundTrip Flight Search fields");
	}
	
    
      /**
       * To click Done button in Passenger Drop Down Box
     * @return 
       * 
        * @throws Exception
       */


       public void clickDoneButtonInPassengerBox() throws Exception {
     	  BrowserActions.clickOnElement(submitPassengerClassInfo, driver ,"Done Button");
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
		BrowserActions.clickOnElement(dateDeparture, driver, "clicking on departure date icon");
		selectMonth.get(month - 3).click();
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
		BrowserActions.clickOnElement(dateReturn, driver, "clicking on return date icon");
		selectMonth.get(month - 3).click();
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
		BrowserActions.nap(10);
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
	
	/** Enter MultiCity Destination2
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
	
	public void selectMultiCityFlightSearchFields(String origin1, String destination1, String origin2, String destination2, String departureDate1, String departureDate2, String passengerInfo,String passengerClass) throws Exception {
		enterMultiCityOrigin1(origin1); // enter Multicity Origin1 value
		enterMultiCityDestination1( destination1); // enter Multicity Destination2 value
		
		enterMultiCityOrigin2(origin2); // enter Multicity Origin2 value
		enterMultiCityDestination2(destination2); // enter Multicity Destination2 value
		
		selectMultiCityDateDeparture1(departureDate1); // select Multicity Departure1 Date
		selectMultiCityDateDeparture2(departureDate1);  // select Multicity Departure2 Date
		specifyPassengerInfo(passengerInfo); // select Passengers details(Adult, Child, Infant)
		
		selectPassengerClass(passengerClass); // select Passengers class type
		clickDoneButtonInPassengerBox(); // click Done button		
        Log.event("Successfully selected 'Multicity' Flight Search fields");

	}

	/**
	 * To navigate to SignIn Page
	 * 
	 * @throws Exception
	 */
	public LoginPage navigateToSignIn_IE() throws Exception {		
		boolean searchIconPresence = driver.findElement(By.cssSelector("#signInBtn")).isDisplayed();
		boolean searchIconEnabled = driver.findElement(By.cssSelector("#signInBtn")).isEnabled();
		if (searchIconPresence == true && searchIconEnabled == true) {
			System.out.println("True");
			// click on the search button
			BrowserActions.clickOnElement(btnSignIn, driver, "Sign In");
		} else {
			 //BrowserActions.moveToElementJS(driver, lnkMyaccount);
			// BrowserActions.mouseHover(driver, lnkMyaccount);			
			 System.out.println("False");		

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
	

}// HomePage