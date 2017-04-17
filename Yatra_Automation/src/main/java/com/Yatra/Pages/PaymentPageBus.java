package com.Yatra.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	@FindBy(css = "div[class='cpmt_cardNavigation']>ul>li>a")
	private WebElement fldContentPaymentMethods;
	
	@FindBy(css = "div[class='outer-redeem']>div")
	private WebElement divEwallet;
	
	@FindBy(css = "div[id='redeem-applied-id']>h3>span[class='applied-ecash']>span[class='total-redeemed-ecash']")
	private WebElement redemeedEcash;
	
	@FindBy(css = "#totalAmountSpan")
	private WebElement totalCashPaymentMethod;
	
	@FindBy(css = "span[id='totalAmountSpan']")
	private WebElement totalCashPaymentDeatils;

	@FindBy(css = "#redeem-ecash-button")
	private WebElement btnRedeemNow;

	@FindBy(css=".slider-base")
	private WebElement scrollSlider;

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
     
     /**
      * To Get Text Total Amount in payment Method(Top of the Page) 
 	  * @return 
      * @throws Exception
      */
	public String getTextTotalAmountPaymentMethod() throws Exception{
		String Amount = totalCashPaymentMethod.getText().trim().replace(",", "");
		return Amount; 
	}
	
	 /**
     * To Get Text Ecash Used 
	  * @return 
     * @throws Exception
     */
	public String getTextEcashUsed() throws Exception{
		String Ecash = redemeedEcash.getText();
		return Ecash; 
	}
	
	 /**
     * To Get Text Total Amount in Payment Details
	 * @return 
     * @throws Exception
     */
	public String getTextTotalAmountPaymentDeatils() throws Exception{
		String Amount = totalCashPaymentDeatils.getText().trim().replace(",", "");
		return Amount; 
	}
	/**
     * To Click on Redeem Now Button
	 *  
     * @throws Exception
     */
	public void clickOnRedeemNowButton() throws Exception{
		 btnRedeemNow.click();
	}
	/**
	 * scrolling the ecash slider
	 * @param value
	 */
	public void scrollSliderOfEcashRedeem(int value){
		Actions action = new Actions(driver);
		action.dragAndDropBy(scrollSlider, value, 0).build().perform();
	}
}//PaymentPageBus