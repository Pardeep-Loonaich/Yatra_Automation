package com.Yatra.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class TrainReviewPage extends LoadableComponent<TrainReviewPage> {

	//private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();


	/**********************************************************************************************
	 ********************************* WebElements of Yatra TrainReviewPage ***********************************
	 **********************************************************************************************/

	@FindBy(css = "#srchModifyBtn")
	private WebElement btnModifyItinerary;

	@FindBy(xpath ="(//input[@id='itineraryCont'])[1]")
	private WebElement btnContinueIternary;
	
	@FindBy(css="#modifyPaxBtn")
	private WebElement btnModifyTraveller;
	
	@FindBy(css=".tripInfo")
	private WebElement tripInfo;
	
	@FindBy(css="#paxTable")
	private WebElement paxInfo;
	
	@FindBy(css="div[id='time-label']>span")
	private WebElement timeOnStrip;
	
	@FindBy(css="div[id='time-label']")
	private WebElement timeStrip;
	
	@FindBy(css="i[class='ico-newHeaderLogo']")
	private WebElement yatraLogo;
	
	/**********************************************************************************************
	 ********************************* WebElements of TrainReviewPage - Ends ****************************
	 **********************************************************************************************/

	/**
	 * 
	 * @param driver
	 *            : webdriver
	 */
	public TrainReviewPage(WebDriver driver) {
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

		if (isPageLoaded && !(Utils.waitForElement(driver, yatraLogo))) {
			Log.fail("TrainReviewPage page didn't open up", driver);
		}
		Log.message("Total time taken by #"+this.getClass().getTypeName()+"to load is:- "+timer.duration()+" "+TimeUnit.MILLISECONDS);
		Constants.performanceData.put("TrainReviewPage",timer.duration());
	}

	@Override
	protected void load() {
		timer.start();

		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}
	
	
	/**
	 * clicked on continue button 
	 * @return
	 * @throws Exception
	 */
	public PaymentPage continueInReviewIternary() throws Exception{
		Thread.sleep(2000);
		BrowserActions.setAttribute(driver, timeStrip, "style", "display:none;");
		Thread.sleep(2000);
		BrowserActions.clickOnElement(btnContinueIternary, driver, "Clicked on continue in Review Iternary.");
		return new PaymentPage(driver).get();
	}
		
	/**
	 * to click on Modify Traveller Button in the Review Page
	 * @return
	 * @throws Exception
	 */
	public TrainTravellerPage clickOnModifyTraveller() throws Exception{
		BrowserActions.scrollToView(btnModifyTraveller, driver);
		BrowserActions.clickOnElement(btnModifyTraveller, driver, "Clicked on Modify Traveller Button in Review Page.");
		return new TrainTravellerPage(driver).get();
	}
	
	/**
	 * to click on Modify Iternary Button in the Review Page
	 * @return
	 * @throws Exception
	 */
	public TrainSearchResult clickOnModifyIternary() throws Exception{
		BrowserActions.scrollToView(btnModifyItinerary, driver);
		BrowserActions.clickOnElement(btnModifyItinerary, driver, "Clicked on Modify Iternary Button in Review Page.");
		return new TrainSearchResult(driver).get();
	}
	
	
	
	/**
	 * getting text of the train info
	 * @return
	 * @throws Exception
	 */
	public String getTripInfo() throws Exception{
		return BrowserActions.getText(driver, tripInfo, "Getting text from the Trip Info");
	}
	
	/**
	 * getting text from the Pax info
	 * @return
	 * @throws Exception
	 */
	public String getPaxInfo() throws Exception{
		return BrowserActions.getText(driver, paxInfo, "Getting text from the Pax Info");
	}
	
	/**
	 * to get the time from the time strip
	 * @return
	 * @throws Exception
	 */
	public String getTimeFromStrip() throws Exception{
		return BrowserActions.getText(driver, timeOnStrip, "Getting time from the Time Strip.");
	}
	
	
}