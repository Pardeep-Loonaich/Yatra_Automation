package com.Yatra.Pages;

import java.util.ArrayList;
import java.util.Collections;
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

public class TrainSearchResult extends LoadableComponent<TrainSearchResult> {

	private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Train Search Result Page ***********************************
	 **********************************************************************************************/

	@FindBy(css= "input[title='Find Trains']")
	private WebElement btnFindTrain;

	@FindBy(xpath = "//*[@id='trainSrp']/div/p")
	private WebElement fldContentTrainMsgBox; 

	@FindBy (xpath ="//li[@class='trainDepart']")
	private List<WebElement> timeDepart;

	@FindBy (xpath ="//li[@class='trainArrive']")
	private List<WebElement> trainArrive;

	@FindBy(css="button[class*='train-prevDay']")
	private WebElement tabPrevDay;

	@FindBy(css="button[class*='train-nextDay']")
	private WebElement tabNextDay;

	@FindBy(css="[class='find-bus-btn']")
	private WebElement btnFindBus;

	@FindBy(css="select[id='quotaSelectdd']")
	private WebElement drpQuota;

	@FindBy(css="div[class='fareSection']>button[id='bookBtn1']")
	private WebElement btnBookNow;

	@FindBy(css="[id='travelAlterOptionClose']")
	private WebElement popupOtherRouteInSideNav;

	@FindBy(css="[id='alternettRoutCont']")
	private WebElement popupOtherRoute;

	@FindBy(css="[id='arriveInfo']")
	private WebElement modalArriveInfo;

	@FindBy(css="[id='alternettRoutCont']>i[class='close-ato']")
	private WebElement clickCloseOtherRoutePopUp;

	@FindBy(css="[id='alternettRoutDiv']>li")
	private List<WebElement> txtOtherTravelOption;
	
	@FindBy(css="ul[id='srcContainer']>li")
	private List<WebElement> lstBordingPoint;	
	
	@FindBy(css="li[class='hide detailBox trainDetailContainer']>article:not([class='trainNo bookNow'])")
	private List<WebElement> trainDetail; 

	@FindBy(css="ul[id='srcContainer']>li>label>span[class='ico-radio']")
	private WebElement inactiveBoardingPoint;
	
	@FindBy(css="ul[id='srcContainer']>li>label>span[class='ico-radio ico-active']")
	private WebElement activeBoardingPoint;
	
	@FindBy(css="ul[id='srcContainer']>li>label>span[class='ico-radio ico-active']>input")
	private WebElement getTxtActBoardingPoint;
	
	@FindBy(css="ul[id='destContainer']>li>label>span[class='ico-radio ico-active']>input")
	private WebElement getTxtActDestinationPoint;
	
	@FindBy(css="div[id='sidebarFilter']>section[class*='result-module']")
	private List<WebElement> lstLeftNavSection;
	
	
	@FindBy(css="div[id='sidebarFilter']")
	private WebElement lftNavSection;
	
	/**********************************************************************************************
	 ********************************* WebElements of Train Search Result Page - Ends ****************************
	 **********************************************************************************************/




	/**
	 * 
	 * @param driver
	 *            : webdriver
	 */
	public TrainSearchResult(WebDriver driver) {
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

		if (isPageLoaded && !(Utils.waitForElement(driver, btnFindTrain))) {
			Log.fail("Train Search Result page didn't open up", driver);
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}


	public boolean trainMessageBox() throws Exception {
		String messagetext = BrowserActions.getText(driver,fldContentTrainMsgBox, "Train Search Message ");
		messagetext.startsWith("Sorry");
		return true;
	}


	/**
	 * To check Depart Time in Sorted Form
	 * 
	 * @throws Exception
	 */
	public boolean sortDepartDate() throws Exception {
		boolean Flag = true;
		ArrayList<String> time = new ArrayList<String>();
		for (int j = 1; j < timeDepart.size(); j++) {
			time.add(driver
					.findElement(By.cssSelector("section[class='results col-3 anim']>div>section>div[class='row res-schedule-details']>div>ul:nth-child("+j+")>li[class='trainDepart']"))
					.getText());
		}
		Collections.sort(time);
		for (int i = 1; i < time.size(); i++) {
			if (time.get(i - 1).compareTo(time.get(i)) > 0)
				Flag = false;
		}
		return Flag;
	}

	/**
	 * To check Arrival Time in Sorted Form
	 * 
	 * @throws Exception
	 */
	public boolean sortArrivalDate() throws Exception {
		boolean Flag = true;
		ArrayList<String> time = new ArrayList<String>();

		for (int j = 1; j < trainArrive.size(); j++) {
			time.add(driver.findElement(By.cssSelector("section[class='results col-3 anim']>div>section>div[class='row res-schedule-details']>div>ul:nth-child("+j+")>li[class='trainArrive']")).getText());
		}

		Collections.sort(time);
		for (int i = 1; i < time.size(); i++) {
			if (time.get(i - 1).compareTo(time.get(i)) > 0)
				Flag = false;
		}
		return Flag;
	}

	/**
	 * this method will return the available classes of the selected train
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String>  checkAvailableClassBySelectingTrainByRow(int index) throws Exception{
		ArrayList<String> lstClass = new ArrayList<String>();
		List<WebElement> lst = driver.findElements(By.cssSelector("ul[class='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p"));
		for(int i=0;i<lst.size();i++){
			String classAvail = BrowserActions.getText(driver, lst.get(i), "Getting available seats by selecting trains by row.");
			lstClass.add(classAvail);
		}       
		return lstClass;
	}



	/**
	 * this method clicks on the quota dropdown first and then return the list options
	 * @return
	 * @throws Exception
	 */

	public ArrayList<String> selectQuota() throws Exception{
		BrowserActions.clickOnElement(drpQuota, driver, "Clicked on Select Quota dropdown.");
		ArrayList<String> lstQuota = new ArrayList<String>();
		List<WebElement> lst = drpQuota.findElements(By.cssSelector("option"));
		for(int i=0;i<lst.size();i++){
			String quotaAvail = BrowserActions.getText(driver, lst.get(i), "Getting available Quota for the Selected seat.");
			lstQuota.add(quotaAvail);
		}       
		return lstQuota;

	}

	/**
	 * to Close the Other Travel OptionPopup
	 * @throws Exception
	 */
	public void closeOtherTravelOptionPopup() throws Exception{
		BrowserActions.clickOnElement(clickCloseOtherRoutePopUp, driver, "Close Other Travel option popup.");
	}


	/**
	 * to get the text from all the Other Travel Option
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> getTxtFromOtherTravelOption() throws Exception{
		ArrayList<String> otherOptions = new ArrayList<String>();

		for(int i=0;i<txtOtherTravelOption.size();i++){
			String otherOption= BrowserActions.getText(driver, txtOtherTravelOption.get(i), "Getting Text from the Other Travel Option PopUp.");
			otherOptions.add(otherOption);
		}
		return  otherOptions;

	}


	/**
	 * this method will return the available seats of the selected train in all class 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String>  checkAvailableSeatsBySelectingTrainNClassByRow(int index) throws Exception{
		ArrayList<String> lstClass = new ArrayList<String>();
		List<WebElement> lstClss = driver.findElements(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p"));

		for(int i=0;i<lstClss.size();i++){
			Thread.sleep(1000);
			BrowserActions.clickOnElement(lstClss.get(i),driver, "Clicked on available class of the train one by one.");
			String classAvail = BrowserActions.getText(driver, lstClss.get(i), "Selected class");
			for(int j=0;j<trainDetail.size();j++){
			String seatAvail = BrowserActions.getText(driver, trainDetail.get(j), "Selected class").concat(classAvail);
			if(seatAvail.contains("AVAILABLE")){
				lstClass.add(seatAvail);

			}
			}

		}       
		return lstClass;
	}
	
	/**
	 * this method selects the train by index and Book the default seat of the first available class 
	 * @param index
	 * @throws Exception
	 */
	public void selectTrainByIndexAndBook(int index) throws Exception{
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p")),driver, "Clicked on first class of the train.");
		BrowserActions.clickOnElement(btnBookNow, driver, "Clicked on 'Book Now' button.");

	}
	
	/**
	 * to get the list of Boarding Points 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> gettingBoardingPoints() throws Exception{
		ArrayList<String> lstBordingPoints = new ArrayList<String>();
		for(int i=0;i<lstBordingPoint.size();i++){
			String borPoints = BrowserActions.getText(driver,lstBordingPoint.get(i) ,"Getting Bording Points.");
			lstBordingPoints.add(borPoints);
		}
       return lstBordingPoints;
	}
	
	
	/**
	 * In this method getting the station name of the selected Boarding/Departure Point with the help of its attribute
	 * @return
	 * @throws Exception
	 */
	
	public String verifyBoardingPointChange() throws Exception{
		if(activeBoardingPoint.isDisplayed()){
			String bordPoint = BrowserActions.getTextFromAttribute(driver, getTxtActBoardingPoint, "data-stnname", "Getting the text of the active Boarding Point.");
        	return bordPoint;
		}
		
		else
			return null;
	}
	
	
	/**
	 * selecting the other option for boarding point
	 * @throws Exception
	 */
	public void changeBoardingPoint() throws Exception{
		BrowserActions.clickOnElement(inactiveBoardingPoint, driver, "Selecting the inactive Boarding Point.");
	}
	
	/**
	 * in the method we are verifying that the Active Boarding Point should be the entered origin
	 * @param origin
	 * @return
	 * @throws Exception
	 */
	public boolean verifySelectedBoardingPoint(String origin) throws Exception{
    	String boardPoint = BrowserActions.getTextFromAttribute(driver, getTxtActBoardingPoint, "data-stnname", "Getting the text of the active Boarding Point.");
    	Log.event(" Boarding Point:"+boardPoint);
			if(origin.contains(boardPoint)){
				return true;		
			}
			else
				return false;
	}
	
	
	/**
	 * to get the text from the heading in left navigation filter panel 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String> getLeftNavHeading() throws Exception{	
		ArrayList<String> lstleftNavHeadings = new ArrayList<String>();

			String departHeading = BrowserActions.getText(driver, lstLeftNavSection.get(0).findElement(By.cssSelector("div>h3>span")), "Getting text of the Source station.");
			lstleftNavHeadings.add(departHeading);
			String arrivalHeading = BrowserActions.getText(driver, lstLeftNavSection.get(1).findElement(By.cssSelector("div>h3>span")), "Getting text of the destination station.");
			lstleftNavHeadings.add(arrivalHeading);
        	String departureStation =BrowserActions.getText(driver, lstLeftNavSection.get(2).findElement(By.cssSelector("form>h3>span>span")), "Getting text of the Departure time.");
			lstleftNavHeadings.add(departureStation);
        	String destinationStation =BrowserActions.getText(driver, lstLeftNavSection.get(3).findElement(By.cssSelector("form>h3>span>span")), "Getting text of the Arrival time.");
			lstleftNavHeadings.add(destinationStation);

			return lstleftNavHeadings;
		}
	
	
}