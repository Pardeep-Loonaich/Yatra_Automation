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
	private WebDriver driver;
	private boolean isPageLoaded;

	public ElementLayer elementLayer;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/

	@FindBy(xpath= "//input[@title='Find Trains']")
	public WebElement btnFindTrain;

	@FindBy(xpath = "//*[@id='trainSrp']/div/p")
	public WebElement fldContentTrainMsgBox; 
	
	@FindBy (xpath ="//li[@class='trainDepart']")
	public List<WebElement> timeDepart;
	
	//ul[class ='train-info-block true']>li[class='trainDepart']>p

	/**********************************************************************************************
	 ********************************* WebElements of Home Page - Ends ****************************
	 **********************************************************************************************/

	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : WebDriver
	 * 
	 * @param url
	 *            : UAT URL
	 */

	public TrainSearchResult(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}

	@Override
	protected void isLoaded() {

		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, btnFindTrain))) {
			Log.fail("Complete Booking page didn't open up", driver);
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
	
	
}