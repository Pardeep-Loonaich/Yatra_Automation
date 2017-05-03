package com.Yatra.Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
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
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.gargoylesoftware.htmlunit.AlertHandler;

public class PaymentPageBus extends LoadableComponent<PaymentPageBus> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();

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
	
	@FindBy(css ="div[id='cpmt_tabContainer']>ul>li")
	private List<WebElement> paymentType;

	@FindBy(css ="li[id='cpmt_otherPayOp']>ul>li")
	private List<WebElement> otherPaymentType;
	
	@FindBy(css="li[id='cpmt_others']>a")
	private WebElement lnkOtherPayment;
	
	@FindBy(css = "#cc_cno_id")
	private WebElement creditCardNumber;

	@FindBy(css = "#cc_cardholder_name_id")
	private WebElement creditCardName;

	@FindBy(css = "#cc_cvv_id")
	private WebElement creditCardCvv;
	
	@FindBy(css="#cc_expm_id>option")
	private List <WebElement> lstMonthsCC;

	@FindBy(css="#cc_expy_id")
	private WebElement yearCC;

	@FindBy(css="#cc_expy_id>option")
	private List <WebElement> lstYearsCC;
	
	@FindBy(css="#cc_expm_id")
	private WebElement monthCC;
	
	@FindBy(css = "#payNow")
	private WebElement lblPayNow;
	
	@FindBy(css="input[class='BtnAlign cancelBtn Mbtn-cancel']")
	private WebElement btnCancelInHdfc;
	
	@FindBy(css="#promoEcashEarned")
	private WebElement PromoRewardPaymentPage;
	
	@FindBy(css = "span[id='total-redeemable-ecash']")
	private WebElement maxEcash;

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

			timer.end();
			if (!isPageLoaded) 
			{
				Assert.fail();
			}
			if (isPageLoaded && !(Utils.waitForElement(driver, headerPayemntMethod))) 
			{
			Log.fail("Payment Page did not open up. Site might be down.", driver);
			}
			Log.message("Total time taken by #"+this.getClass().getTypeName()+"to load is:- "+timer.duration()+" "+TimeUnit.SECONDS);
		}// isLoaded

		@Override
		protected void load() 
		{
			timer.start();
			isPageLoaded = true;
			Utils.waitForPageLoad(driver);
		}// load

	
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
     * To Get Text Maxium Ecash that can be redmeed
	 * @return 
     * @throws Exception
     */
	public String getTextMaxiumEcash() throws Exception{
		String Amount = maxEcash.getText();
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
	/**
	 * to select the payment time from left panel
	 * @param PaymentType
	 * @throws Exception
	 */

	public void selectPaymentType(String PaymentType) throws Exception {
		List<WebElement> lstElement = paymentType;
		if(PaymentType.equals("ezeClick")||PaymentType.equalsIgnoreCase("Reward Points")){
			BrowserActions.scrollToViewElement(lnkOtherPayment, driver);
			BrowserActions.clickOnElement(lnkOtherPayment, driver, "list elements");

			for (WebElement ele : otherPaymentType) {
				if (ele.findElement(By.cssSelector("a")).getText().equalsIgnoreCase(PaymentType)) 
				{
					BrowserActions.scrollToViewElement(ele.findElement(By.cssSelector("a")), driver);
					BrowserActions.clickOnElement(ele.findElement(By.cssSelector("a")), driver, "list elements in others");
					break;
				}
			}
		}
		else{
			for (WebElement e : lstElement) {

				if (e.findElement(By.cssSelector("a")).getText().equalsIgnoreCase(PaymentType)) {
			          //findElement is required here
					BrowserActions.scrollToViewElement(e.findElement(By.cssSelector("a")), driver);
					BrowserActions.clickOnElement(e.findElement(By.cssSelector("a")), driver, "list elements");
					break;

				}
			}
		}
	}
	/**
	 * Filling Credit Card Details
	 * 
	 * @return
	 * @throws Exception
	 */
	public void enterCreditCardDetails(String cardNumber) throws Exception {
		String randomName = RandomStringUtils.randomAlphabetic(7).toLowerCase();
		String randomCvv = RandomStringUtils.randomNumeric(3);

		BrowserActions.typeOnTextField(creditCardNumber,cardNumber, driver, "Credit card Number");
		BrowserActions.typeOnTextField(creditCardName, randomName, driver, "Credit card Name");
		BrowserActions.clickOnElement(monthCC, driver, "Date");
		if (lstMonthsCC.size() != 0) {
			int rand = Utils.getRandom(1, lstMonthsCC.size());
			BrowserActions.clickOnElement(lstMonthsCC.get(rand), driver, "Month Selected");
		}
		Thread.sleep(2000);
		BrowserActions.clickOnElement(yearCC, driver, "Year");
		if (lstYearsCC.size() != 0) {
			int rand = Utils.getRandom(1, lstYearsCC.size());
			BrowserActions.clickOnElement(lstYearsCC.get(rand), driver, "Year Selected");
		}
		Thread.sleep(2000);
		BrowserActions.typeOnTextField(creditCardCvv, randomCvv, driver, "Credit card Cvv");
		BrowserActions.clickOnElement(lblPayNow,driver ,"Pay Now");
	}
	
	/**
	 * to click on Cancel in HDFC payment page
	 * @throws Exception
	 */
	public void cancelHdfcPayment(String browser) throws Exception{
		Utils.waitForPageLoad(driver);
		Utils.waitForElement(driver, btnCancelInHdfc);
		BrowserActions.javascriptClick(btnCancelInHdfc, driver, "Clicked on cancel button");
		Thread.sleep(1000);
		if(browser.equalsIgnoreCase("chrome_windows")){
		BrowserActions.javaScriptAlertPopUpHandler(driver, "cancel");
		}
		else if(browser.equalsIgnoreCase("iexplorer_windows")){
			BrowserActions.javaScriptAlertPopUpHandler(driver, "ok");
		}
		else if(browser.equalsIgnoreCase("FireFox_windows")){
			BrowserActions.javaScriptAlertPopUpHandler(driver, "ok");
		}
	}
	/**
	 * to navigate back to Payment Page From Payment GateWay
	 * @param browser
	 * @throws Exception
	 */

	public void returnFromCreditCardPage(String browser,int ran) throws Exception{
		if(browser.equalsIgnoreCase("firefox_windows")){
			for(int i=0;i<ran;i++){
				driver.navigate().back();
			}
		}
		else if(browser.equalsIgnoreCase("iexplorer_windows")){
		}
		else if(browser.equalsIgnoreCase("Chrome_windows")){
		driver.navigate().back();
		}
	}
	
	 /**
     * To Get Text Promotional Reward on Payment Details
	 * @return 
     * @throws Exception
     */
	public String getTextPromotionalRewards() throws Exception{
		Utils.waitForPageLoad(driver);
		String Amount = PromoRewardPaymentPage.getText();
		return Amount; 
	}
	public static double round1(double value, int scale) {
	    return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
	}
	
	
}//PaymentPageBus