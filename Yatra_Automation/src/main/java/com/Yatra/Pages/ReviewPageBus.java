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
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class ReviewPageBus extends LoadableComponent<ReviewPageBus> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	SearchResultBus searchResult;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/
	@FindBy(css = "a[title='Change Your Selected Bus']")
	private WebElement BtnChangeBus;
	
	@FindBy(css = "p[data-seat]")
	private WebElement txtSeatDetails;
	
	@FindBy(css = "#guest_email")
	private WebElement txtBoxEmailGuest;
	
	@FindBy(css = "#guest_mobile")
	private WebElement txtBoxPhoneNumberGuest;
	
	@FindBy(css = "#userContinue")
	WebElement btnContinueGuest;
	
	@FindBy(css = "label[for='showLoginForm']>span[class='custom-checkbox']>span")
	private WebElement chkBoxYatraUser;
	
	@FindBy(css = "#login_email")
	private WebElement txtBoxEmailLogin;
	
	@FindBy(css = "#login_password")
	private WebElement txtBoxPasswordLogin;
	
	@FindBy(css = "div[class='loginInfo']>div[class='loginBtnCont']>input")
	private WebElement btnContinueLogin;
	
	@FindBy(css = ".yatraFb")
	private WebElement btnFaceBook;
	
	@FindBy(css = "div[class='toasterHolder']")
	private WebElement txtErrorMsg;
	
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

		public ReviewPageBus(WebDriver driver) {
			this.driver = driver;
			ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
			PageFactory.initElements(finder, this);
			elementLayer = new ElementLayer(driver);
		}// ReviewPageBus

		@Override
		protected void isLoaded() {

			timer.end();
			if (!isPageLoaded) 
			{
				Assert.fail();
			}
			if (isPageLoaded && !(Utils.waitForElement(driver, BtnChangeBus))) 
			{
			Log.fail("Review Page did not open up. Site might be down.", driver);
			}
			Log.message("Total time taken by #"+this.getClass().getTypeName()+"to load is:- "+timer.duration()+" "+TimeUnit.MILLISECONDS);
			Constants.performanceData.put("reviewPageBus",timer.duration());
		}// isLoaded

		@Override
		protected void load() 
		{
			timer.start();
			isPageLoaded = true;
			Utils.waitForPageLoad(driver);
		}// load

	/**
	 * Getting the text from the Bus Info
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextSeatDeatilReview() throws Exception {
		Utils.waitForElement(driver, txtSeatDetails);
		String txtDetails = txtSeatDetails.getText();
		return txtDetails;	
	}
	/**
	 * To click Select Seat
	 * 
	 * @throws Exception
	 */

	public void clickBtnSelectSeat() throws Exception {
		BrowserActions.clickOnElement(txtSeatDetails, driver, "Select Seat");
		Utils.waitForPageLoad(driver);
	}
	/**
     * to click on change bus button
     * @throws Exception
     */
     public SearchResultBus clickOnChangeBus() throws Exception{
    	 	Utils.waitForPageLoad(driver);
            BrowserActions.clickOnElement(BtnChangeBus, driver, "clicked on change bus button.");
            return new SearchResultBus(driver).get();
     }
     
     /**
 	 * To Fill Traveller Details
 	 * 
 	 * @throws Exception
 	 */

 	public void fillUserDetailsAsGuest(String email,String PhoneNumber) throws Exception {
 		Utils.waitForElement(driver, txtBoxEmailGuest);
 		BrowserActions.typeOnTextField(txtBoxEmailGuest, email, driver, "Enter User Email");
 		BrowserActions.typeOnTextField(txtBoxPhoneNumberGuest, PhoneNumber, driver, "Enter Phone Number");
 		
 	}
 	/**
 	 * To Click On Book Now Button 
 	 * @returns TravellerPageBus
 	 * @throws Exception
 	 */
 	
 	public TravellerPageBus clickOnBookNow() throws Exception{
 		BrowserActions.clickOnElement(btnContinueGuest, driver, "Book Now");
 		return new TravellerPageBus(driver).get();
 	}
 
 	/**
 	 * To Click On Book Now Button 
 	 * 
 	 * @throws Exception
 	 */
 	
 	public void clickOnbtnBookNow() throws Exception{
 		BrowserActions.clickOnElement(btnContinueGuest, driver, "Book Now");
 	}
 	
 	  /**
 	 	 * To Fill Traveller Details As Login User
 	 	 * 
 	 	 * @throws Exception
 	 	 */

 	 	public TravellerPageBus fillUserDetailsAsLogin(String email, String Password) throws Exception {
 	 		BrowserActions.nap(2);
 	 		Utils.waitForPageLoad(driver);
 	 		Utils.waitForElement(driver, chkBoxYatraUser);
 	 		BrowserActions.clickOnElement(chkBoxYatraUser, driver, "Existing Yatra User Check Box");
 	 		BrowserActions.typeOnTextField(txtBoxEmailLogin, email, driver, "Enter User Email");
 	 		BrowserActions.typeOnTextField(txtBoxPasswordLogin, Password, driver, "Enter User Password");
 	 		BrowserActions.clickOnElement(btnContinueLogin, driver, "Book Now");
 	 		return new TravellerPageBus(driver).get();
 	 	}
 	 	
 	 	/**
 		 * Getting the text from the Error
 		 * 
 		 * @return
 		 * @throws Exception
 		 */
 		public String getTextErrorMsg() throws Exception {
 			Utils.waitForElement(driver, txtErrorMsg);
 			String txtDetails = BrowserActions.getText(driver, txtErrorMsg,
 					"Getting text from the Incoorect Element");
 			return txtDetails;
 		}
}//ReviewPageBus