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


	//ul[class ='train-info-block true']>li[class='trainDepart']>p

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

}