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
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class CompleteBooking extends LoadableComponent<CompleteBooking> {

	private WebDriver driver;
	private boolean isPageLoaded;
	ExecutionTimer timer=new ExecutionTimer();
	


	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/
	
	@FindBy(css = "td [id='ref']")
	private WebElement txtBookingRefNo;
	
	@FindBy(css =  "td [id='submitBtn']")
	private WebElement btnSubmit;
	
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
	
	public CompleteBooking(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}
	
	@Override
	protected void isLoaded() {
		timer.end();
		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, txtBookingRefNo))) {
			Log.fail("Complete Booking page didn't open up", driver);
		}
		Log.message("Total time taken by #"+this.getClass().getTypeName()+" to load is:- "+timer.duration()+" "+TimeUnit.SECONDS);
		
	}

	@Override
	protected void load() {
		timer.start();
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}
	
	/**
	 * Enter booking reference no
	 * 
	 * @param booking reference no
	 *            as string
	 *                   
	 * To click Submit button on complete booking page 
	 *            
	 * @throws Exception
	 */
	
	public void enterBookingDetail(String bookingrefno) throws Exception {
		Utils.waitForElement(driver, txtBookingRefNo);		
		
		BrowserActions.typeOnTextField(txtBookingRefNo, bookingrefno, driver, "booking ref no");
		Log.event("Entered the Booking No: " + bookingrefno);
		
		BrowserActions.clickOnElement(btnSubmit, driver, "Submit");
		Utils.waitForPageLoad(driver);
	}
}
