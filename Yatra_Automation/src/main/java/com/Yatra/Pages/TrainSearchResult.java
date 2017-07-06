
package com.Yatra.Pages;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;

@SuppressWarnings("unused")
public class TrainSearchResult extends LoadableComponent<TrainSearchResult> {

	//private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Train Search Result Page ***********************************
	 **********************************************************************************************/

	@FindBy(css= "input[title='Find Trains']")
	private WebElement btnFindTrain;

	@FindBy(css = "button[title='Find Buses']")
	private WebElement btnFindBuses;

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

	@FindBy(css="i[class='disp-table-cell ico-alt-opts ico-opt-bus txt-10']")
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

	@FindBy(css= "span[class='srcStations']")
	private WebElement txtSourceStation;

	@FindBy(css="[id='trainSrp']>ul[class*='train-info-block true']>li[class='trainDepart']>p")
	private List<WebElement> txtDepartTime;

	@FindBy(css="div[id='sidebarFilter']")
	private WebElement lftNavSection;
	
	@FindBy(css = "div[class='contBttn']>input")
	private WebElement btnContinueToPaxPage;

	@FindBy(css = "ul[class='train-info']>li[class='train_head trainDuration']")
	private WebElement lstHeadDuration;
	
	@FindBy(css="li[class='trainDuration']")
	private List<WebElement> trainDuration;
	
	@FindBy(css="ul[id='dstations']>li[class='all-label']")
	private WebElement chkDestStatn;

	@FindBy(css="#reset-filter")
	private WebElement btnResetFltr;
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
		timer.end();

		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, btnFindTrain))) {
			Log.fail("Train Search Result page didn't open up", driver);
		}
		Log.message("Total time taken by #"+this.getClass().getTypeName()+"to load is:- "+timer.duration()+" "+TimeUnit.MILLISECONDS);
		Constants.performanceData.put("TrainSearchResultPage",timer.duration());
	}

	@Override
	protected void load() {
		timer.start();

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
	 * To check Duration in Sorted Form
	 * 
	 * @throws Exception
	 */
	public boolean sortByDuration() throws Exception {
		boolean Flag = false;
		ArrayList<String> time = new ArrayList<String>();
		for(int i=0;i<=1;i++){
		BrowserActions.clickOnElement(lstHeadDuration, driver, "Clicked on Duration List tab.");
		}

		for (int j = 1; j < trainDuration.size(); j++) {
			time.add(trainDuration.get(j).getText());
		}

		Collections.sort(time);
		for (int i = 1; i < time.size(); i++) {
			if (time.get(i - 1).compareTo(time.get(i)) <= 0)
				Flag = true;
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
	public ArrayList<String>  checkDetailsBySelectingTrainNClassByRow(int index,String typeToVerify) throws Exception{
		ArrayList<String> lstOfSeat = new ArrayList<String>();

		List<WebElement> lstClss = driver.findElements(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p"));

		for(int i=0;i<lstClss.size();i++){
			Thread.sleep(1000);
			BrowserActions.clickOnElement(lstClss.get(i),driver, "Clicked on available class of the train one by one.");
			String classAvail = BrowserActions.getText(driver, lstClss.get(i), "Selected class");
			for(int j=0;j<trainDetail.size();j++){
				ArrayList<String> lstSeat = new ArrayList<String>();

				String seatAvail = BrowserActions.getText(driver, trainDetail.get(j), "Selected class");
				lstSeat.add(seatAvail);
				String strofSeat = lstSeat.get(0).concat("---"+classAvail);
				String[] splitterofSeat = strofSeat.split("\n");
				String date = splitterofSeat[0];
				String day = splitterofSeat[1];
				String quota = splitterofSeat[2];
				String availability = splitterofSeat[3];

				switch (typeToVerify){				

				case "AVAILABLE":
					if(availability.startsWith("AVAILABLE"))
						lstOfSeat.add(strofSeat);
					break;

				case "General":
					if(quota.equals("General"))
						lstOfSeat.add(strofSeat);
					break;

				case "Ladies Quota":
					if(quota.equals("Ladies Quota"))
						lstOfSeat.add(strofSeat);
					break;

				case "Tatkal":
					if(quota.equals("Tatkal"))
						lstOfSeat.add(strofSeat);
					break;

				}
			}
		}
		return lstOfSeat;
	}

	/**
	 * this method selects the train by index and Book the default seat of the first available class 
	 * @param index
	 * @throws Exception
	 */
	public void selectTrainByIndexAndBook(int index,String broswer) throws Exception{
		Utils.waitForPageLoad(driver);
		if(broswer.equalsIgnoreCase("firefox_windows")){
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p")),driver, "Clicked on first class of the train.");
		Thread.sleep(2000);		
		BrowserActions.clickOnElement(btnBookNow, driver, "Clicked on 'Book Now' button.");
		/*driver.navigate().refresh();
		Thread.sleep(2000);
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("ul[class*='train-info-block true']:nth-child(2)>li[class*='trainClass']>p")),driver, "Clicked on first class of the train.");
		Thread.sleep(2000);		
		BrowserActions.clickOnElement(btnBookNow2, driver, "Clicked on 'Book Now' button.");
		//btnBookNow.sendKeys(Keys.ENTER);
		driver.findElement(By.id("div[class='fareSection']>button[id='bookBtn1']")).click();
		driver.navigate().refresh();
		Thread.sleep(3000);
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p")),driver, "Clicked on first class of the train.");		
		BrowserActions.javascriptClick(btnBookNow, driver, "Clicked on 'Book Now' button.");*/
		}
		else if(broswer.equalsIgnoreCase("Chrome_windows")){
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p")),driver, "Clicked on first class of the train.");
		Thread.sleep(1000);
		//BrowserActions.clickOnElement(driver.findElement(By.cssSelector("input[id='trainquota10']")), driver, "");
		BrowserActions.clickOnElement(btnBookNow, driver, "Book Now");
		//BrowserActions.javascriptClick(btnBookNow, driver, "Clicked on 'Book Now' button.");
		Thread.sleep(3000);
	}else{
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p")),driver, "Clicked on first class of the train.");
		Thread.sleep(3000);
		BrowserActions.clickOnElement(btnBookNow, driver, "Clicked on 'Book Now' button.");
	}
	}
	/**
	 * this method selects the train by index the default seat of the first available class 
	 * @param index
	 * @throws Exception
	 */
	public void selectTrainByIndex(int index) throws Exception{
		BrowserActions.clickOnElement(driver.findElement(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p")),driver, "Clicked on first class of the train.");
	}

	
	/**
	 * this method selects the train by index 
	 * @param index
	 * @throws Exception
	 */
	public boolean verifyBookNowByselectingTrainByIndex(int index) throws Exception{
		List<WebElement> lstClss = driver.findElements(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p"));
		boolean flag= false;
		for(int i=0;i<lstClss.size();i++){
			Thread.sleep(1000);
			BrowserActions.clickOnElement(lstClss.get(i),driver, "Clicked on available class of the train one by one.");
			String classAvail = BrowserActions.getText(driver, lstClss.get(i), "Selected class");
			for(int j=0;j<trainDetail.size();j++){
				String seatAvail = BrowserActions.getText(driver, trainDetail.get(j), "Selected class").concat(classAvail);
				if(seatAvail.contains("NOT AVAILABLE")){
					// flag = BrowserActions.isElementVisible(driver, btnBookNow);
					if (!btnBookNow.isDisplayed()){
						flag=true;
					}
				}	
			}

		}
		return flag;       
	}


	/**
	 * this method verify FindBus By index
	 * @param index
	 * @throws Exception
	 */
	public boolean verifyFindBusByselectingTrainByIndex() throws Exception{
		List<WebElement> lstClss = driver.findElements(By.cssSelector("ul[class*='train-info-block true']"));
		boolean flag= false;
		for(int i=0;i<lstClss.size();i++){
			Thread.sleep(1000);
			BrowserActions.clickOnElement(lstClss.get(i).findElement(By.cssSelector("li[class*='trainClass']>p")),driver, "Clicked on available class of the train one by one.");
			if (btnFindBus.isDisplayed()){
						flag=true;
						break;
			}							
		}
		return flag;       
	}
	/**
	 * to verify that the selected train contains seat of the selected Quota only.
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public boolean verifyQuotaByselectingTrainByIndex(int index,String quota) throws Exception{
		List<WebElement> lstClss = driver.findElements(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p"));
		boolean flag= false;
		for(int i=0;i<lstClss.size();i++){
			Thread.sleep(1000);
			BrowserActions.clickOnElement(lstClss.get(i),driver, "Clicked on available class of the train one by one.");
			String classAvail = BrowserActions.getText(driver, lstClss.get(i), "Selected class");
			for(int j=0;j<trainDetail.size();j++){
				String seatAvail = BrowserActions.getText(driver, trainDetail.get(j), "Selected class").concat(classAvail);
				if(seatAvail.contains(quota)){
					flag=true;

				}	
			}

		}
		return flag;       
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
		String[] boardP = boardPoint.split(" ");
		Log.event(" Boarding Point:"+boardP[0]);
		if(origin.contains(boardP[0])){
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


	/**
	 * clicked on FindBus Button by selecting train by index and then checking in every class for FindBus option
	 * @param index
	 * @throws Exception
	 */
	public void clickingOnFindBusButton(int index) throws Exception{	
		List<WebElement> lstClss = driver.findElements(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p"));

		for(int i=0;i<lstClss.size();i++){
			Thread.sleep(2000);
			BrowserActions.clickOnElement(lstClss.get(i),driver, "Clicked on available class of the train one by one.");
			String classAvail = BrowserActions.getText(driver, lstClss.get(i), "Selected class");
			WebElement sec_FindBus = driver.findElement(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainBusDivert']"));
			Thread.sleep(2000);

			if(sec_FindBus.isDisplayed()){
				//BrowserActions.clickOnElement(sec_FindBus.findElement(By.cssSelector("div>div>div[class='bus-deatail']>div[class='find-bus-btn']")), driver, "Clicked on 'Find Bus' button.");
				BrowserActions.clickOnElement(btnFindBus, driver, "Clicked on 'Find Bus' button.");

				Log.event("Bus option is available in '"+classAvail+"' class.");

				break;
			}
			else
				Log.event("No Bus option is available in '"+classAvail+"' class.");
		}
	}

	
	

	/**
	 * to select the Quota from the Quota dropdown
	 * @param QuotaType
	 * @throws Exception
	 */
	public void selectQuotaFrmDrpDown(String QuotaType) throws Exception{
		BrowserActions.clickOnElement(drpQuota, driver, "Clicked on Select Quota dropdown.");
		List<WebElement> lstQuota = drpQuota.findElements(By.cssSelector("option"));	

		for (WebElement e : lstQuota) {
			String quota = BrowserActions.getText(driver, e, "Getting text from attribute.");
			if (quota.equalsIgnoreCase(QuotaType)) {
				BrowserActions.scrollToViewElement(e, driver);
				BrowserActions.clickOnElement(e, driver, "Selected Quota Type");
				break;

			}
		}
	}


	/**
	 * verifying Confirmation Popup details
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public boolean verifyConfirmationPopUpDetails(int index) throws Exception{
		List<WebElement> lstClss = driver.findElements(By.cssSelector("ul[class*='train-info-block true']:nth-child("+index+")>li[class*='trainClass']>p"));

		String sourceStation = BrowserActions.getText(driver, txtSourceStation, "Getting text of the Source station.");
		String departureTime =BrowserActions.getText(driver, txtDepartTime.get(index-1), "Getting text of the Departure time.");

		BrowserActions.clickOnElement(lstClss.get(0),driver, "Clicked on available class of the train one by one.");
		BrowserActions.clickOnElement(btnBookNow, driver, "Clicked on 'Book Now' button.");
		Thread.sleep(1000);
		String boardPoint = BrowserActions.getTextFromAttribute(driver, getTxtActBoardingPoint, "data-stnname", "Getting the text of the active Boarding Point.");
		String boardTime = BrowserActions.getTextFromAttribute(driver, getTxtActBoardingPoint, "data-dt", "Getting the text of the active Boarding Point dept time.");

		if((boardPoint.contains(sourceStation))&&(boardTime.equalsIgnoreCase(departureTime))){
			return true;
		}

		return false;
	}
	

	/**
	 * To click on continue button to navigate on Review page 
	 * @return
	 * @throws Exception
	 */
	public TrainTravellerPage clickOnContinue()throws Exception{
		BrowserActions.clickOnElement(btnContinueToPaxPage, driver, "Click on Continue in Confirmation Popup to book Train.");
		return new TrainTravellerPage(driver).get();
	}
	
	/**
	 * to click on reset filter button
	 * @throws Exception
	 */
	public void clickOnReset() throws Exception{
		BrowserActions.clickOnElement(btnResetFltr, driver, "Clicked on 'Reset Filter' button.");
	}
	

	/**
	 * clicked on FindBus Button by selecting train by index and then checking in every class for FindBus option
	 * @param index
	 * @throws Exception
	 */
	public void clickingOnFindBusButton() throws Exception{	
		List<WebElement> lstClss = driver.findElements(By.cssSelector("ul[class*='train-info-block true']"));
		//boolean flag= false;
		for(int i=0;i<lstClss.size();i++){
			Thread.sleep(1000);
			BrowserActions.clickOnElement(lstClss.get(i).findElement(By.cssSelector("li[class*='trainClass']>p")),driver, "Clicked on available class of the train one by one.");
			Thread.sleep(1000);

			if (btnFindBus.isDisplayed()){
				
				BrowserActions.clickOnElement(btnFindBus, driver, "Clicked on 'Find Bus' button.");
						break;
			}							
		}
	}
}//TrainSearchEnds
