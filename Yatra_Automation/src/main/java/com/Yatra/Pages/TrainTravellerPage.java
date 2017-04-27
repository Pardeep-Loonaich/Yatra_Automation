package com.Yatra.Pages;


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

public class TrainTravellerPage extends LoadableComponent<TrainTravellerPage> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	private String appURL;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra TrainTravellerPage ***********************************
	 **********************************************************************************************/

	@FindBy(css = "#paxContainer")
	private WebElement divPaxContainer;
	
	@FindBy(css = "i[class='yatraFb']")
	private WebElement lnkFB;

	/**********************************************************************************************
	 ********************************* WebElements of TrainTravellerPage - Ends ****************************
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

	public TrainTravellerPage(WebDriver driver, String url) {
		appURL = url;
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}// 

	/**
	 * 
	 * @param driver
	 *            : webdriver
	 */
	public TrainTravellerPage(WebDriver driver) {
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

		if (isPageLoaded && !(Utils.waitForElement(driver, divPaxContainer))) {
			Log.fail("PaymentPage didn't open up", driver);
		}
		// elementLayer = new ElementLayer(driver);
	}

	@Override
	protected void load() {
		isPageLoaded = true;

		Utils.waitForPageLoad(driver);
	}// load

}//TrainTravellerPageEnd
