
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
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class MakePayment extends LoadableComponent<MakePayment> {

	private WebDriver driver;
	private boolean isPageLoaded;
	ExecutionTimer timer = new ExecutionTimer();

	/**********************************************************************************************
	 ********************************* WebElements of Make Payment ***********************************
	 **********************************************************************************************/
	
	@FindBy(xpath= "//input[@id ='cust_email']")
	private WebElement txtCustomerEmail;
	
	@FindBy(xpath="//li[@class='checkAcc']/input[@class='mt3 flL']")
	private WebElement chkYatraAcc;
	
	@FindBy(xpath= "//input[@id ='super_pnr']")
	private WebElement txtBookingRefNo;
	
	@FindBy(xpath =  "//input[@id ='login-button']")
	private WebElement btnLogin;
	
	/**********************************************************************************************
	 ********************************* WebElements of Make Payment - Ends ****************************
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
		timer.end();
		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, txtCustomerEmail))) {
			Log.fail("MakePayment page didn't open up", driver);
		}
		Log.message("Total time taken by #" + this.getClass().getTypeName() + "to load is:- " + timer.duration() + " "
				+ TimeUnit.MILLISECONDS);
		Constants.performanceData.put("MakePaymentPage",timer.duration());
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
