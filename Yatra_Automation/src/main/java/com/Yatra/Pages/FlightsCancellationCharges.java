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

public class FlightsCancellationCharges extends LoadableComponent<FlightsCancellationCharges> {

	private WebDriver driver;
	private boolean isPageLoaded;
	
	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/

	@FindBy(xpath = "//input[@id ='bookingNumber']")
    private WebElement txtBookingNo;
	
	@FindBy(xpath = "//input[@id ='mobileNumber']")
	private WebElement txtMobileNo;
	
	@FindBy(xpath =  "//input[@type='submit']")
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
	
	
	public FlightsCancellationCharges(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}
	
	@Override
	protected void isLoaded() {

		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, txtBookingNo))) {
			Log.fail("Flight Cancellation page didn't open up", driver);
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}

	/**
	 * Enter booking no
	 * 
	 * @param booking no
	 *            as string
	 * Enter mobile no
	 *            
	 * @param mobile no
	 *          as string
	 *          
	 * To click Submit button on Flight cancellation page 
	 *            
	 * @throws Exception
	 */
	
	public void enterCancellationDetails(String bookingno, String mobileno) throws Exception {
		Utils.waitForElement(driver, txtBookingNo);		
		
		BrowserActions.typeOnTextField(txtBookingNo, bookingno, driver, "booking no");
		Log.event("Entered the Booking No: " + bookingno);
		
		BrowserActions.typeOnTextField(txtMobileNo, mobileno, driver, "mobile no");
		Log.event("Entered the Mobile no: " + mobileno);
		
		BrowserActions.clickOnElement(btnSubmit, driver, "Submit");
		Utils.waitForPageLoad(driver);
	}
		
}
