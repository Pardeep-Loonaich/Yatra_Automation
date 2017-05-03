package com.Yatra.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
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

public class TrainReviewPage extends LoadableComponent<TrainReviewPage> {

	private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;


	/**********************************************************************************************
	 ********************************* WebElements of Yatra TrainReviewPage ***********************************
	 **********************************************************************************************/

	@FindBy(css = "#srchModifyBtn")
	private WebElement btnModifyItinerary;

	@FindBy(css="#itineraryCont")
	private WebElement btnContinueIternary;
	
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

		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, btnModifyItinerary))) {
			Log.fail("TrainReviewPage page didn't open up", driver);
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}
	
	
	/**
	 * clicked on continue button 
	 * @return
	 * @throws Exception
	 */
	public PaymentPage continueInReviewIternary() throws Exception{
		Utils.waitForPageLoad(driver);
		BrowserActions.scrollToView(btnContinueIternary, driver);
		BrowserActions.clickOnElement(btnContinueIternary, driver, "Clicked on continue in Review Iternary.");
		return new PaymentPage(driver).get();
	}
}