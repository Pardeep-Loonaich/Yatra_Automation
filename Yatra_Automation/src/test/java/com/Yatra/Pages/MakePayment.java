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

public class MakePayment extends LoadableComponent<CompleteBooking> {

	private WebDriver driver;
	private boolean isPageLoaded;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/
	
	@FindBy(xpath= "[@id ='cust_email']")
	public WebElement txtCustomerEmail;
	
	@FindBy(xpath="//li[@class='checkAcc']/input[@class='mt3 flL']")
	public WebElement chkYatraAcc;
	
	@FindBy(xpath= "[@id ='super_pnr']")
	public WebElement txtBookingRefNo;
	
	@FindBy(xpath = "[@id ='login-button']")
	public WebElement btnLogin;
	
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
	
	public MakePayment(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}
	
	@Override
	protected void isLoaded() {

		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, txtCustomerEmail))) {
			Log.fail("Complete Booking page didn't open up", driver);
		}
	}

	@Override
	protected void load() {
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
	
	public void enterBookingDetail(String custemail, String bookingrefno) throws Exception {
		Utils.waitForElement(driver, txtCustomerEmail);		
		
		BrowserActions.typeOnTextField(txtCustomerEmail, custemail, driver, "customer email");
		Log.event("Entered the Customer Email: " + custemail);
		
		BrowserActions.getRadioOrCheckboxChecked(chkYatraAcc);
		
		BrowserActions.typeOnTextField(txtBookingRefNo, bookingrefno, driver, "booking reference no");
		Log.event("Entered the Booking reference No: " + bookingrefno);
		
		BrowserActions.clickOnElement(btnLogin, driver, "Login");
		Utils.waitForPageLoad(driver);
		
	}
}
