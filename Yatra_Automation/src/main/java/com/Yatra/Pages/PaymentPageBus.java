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

public class PaymentPageBus extends LoadableComponent<PaymentPageBus> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/
	@FindBy(css = "div[class='cpmt_detailBox cpmt_clearfix']>h2")
	private WebElement headerPayemntMethod;
	
	@FindBy(css = "div[class='col-lg-3 col-md-3']>div[id='itineraryDetailHeader']>div>aside>div>h2>i")
	private WebElement headerBookingSummary;
	
	@FindBy(css = "div[class*='bus-booking-summary-onward']>p")
	private WebElement BookingSummaryDetail;
	
	/**********************************************************************************************
	 ********************************* WebElements of Home Page - Ends ****************************
	 **********************************************************************************************/

	/**
		 * constructor of the class
		 * 
		 * @param driver
		 *            : Webdriver
		 * 
		 			**/

		public PaymentPageBus(WebDriver driver) {
			this.driver = driver;
			ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
			PageFactory.initElements(finder, this);
			elementLayer = new ElementLayer(driver);
		}// PaymentPageBus

	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, headerPayemntMethod))) {
			Log.fail("Payment Page did not open up. Site might be down.", driver);
		}
	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}
	
	/**
     * To Click on Booking Summary
	 * @return 
     * @throws Exception
     */
     public String clickOnBookingSummary() throws Exception{
    	 String details = null ;
    	 Thread.sleep(3000);
    	 if(BookingSummaryDetail.isDisplayed()){
    		 details = BookingSummaryDetail.getText();
    	 }else if(!(BookingSummaryDetail.isDisplayed()))
    	 {
           BrowserActions.clickOnElement(headerBookingSummary, driver, "clicked on Booking Summary Drop Down");
            details = BookingSummaryDetail.getText();
    	 }
    	 return details;
    	 }
	
	
	
	
	
	
	
	
	
}//PaymentPageBus