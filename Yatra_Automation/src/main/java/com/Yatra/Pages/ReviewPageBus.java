package com.Yatra.Pages;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class ReviewPageBus extends LoadableComponent<ReviewPageBus> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	SearchResultBus searchResult;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/
	@FindBy(css = "a[title='Change Your Selected Bus']")
	WebElement BtnChangeBus;
	
	@FindBy(css = "p[data-seat]")
	WebElement txtSeatDetails;
	
	@FindBy(css = "#guest_email")
	WebElement txtBoxEmailGuest;
	
	@FindBy(css = "#guest_mobile")
	WebElement txtBoxPhoneNumberGuest;
	
	@FindBy(css = "#userContinue")
	WebElement btnContinueGuest;
	
	@FindBy(css = "label[for='showLoginForm']>span[class='custom-checkbox']>span")
	WebElement chkBoxYatraUser;
	
	@FindBy(css = "#login_email")
	WebElement txtBoxEmailLogin;
	
	@FindBy(css = "#login_password")
	WebElement txtBoxPasswordLogin;
	
	@FindBy(css = "div[class='loginInfo']>div[class='loginBtnCont']>input")
	WebElement btnContinueLogin;
	
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
		if (!isPageLoaded) {
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, BtnChangeBus))) {
			Log.fail("Review Page did not open up. Site might be down.", driver);
		}
	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);

	}
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
            BrowserActions.clickOnElement(BtnChangeBus, driver, "clicked on change bus button.");
            return new SearchResultBus(driver);
     }
     
     /**
 	 * To Fill Traveller Details
 	 * 
 	 * @throws Exception
 	 */

 	public TravellerPageBus fillUserDetailsAsGuest(String email) throws Exception {
 		BrowserActions.typeOnTextField(txtBoxEmailGuest, email, driver, "Enter User Email");
 		String rand =  RandomStringUtils.randomNumeric(10);
 		BrowserActions.typeOnTextField(txtBoxPhoneNumberGuest,rand , driver, "Guest Phone Number");
 		BrowserActions.clickOnElement(btnContinueGuest, driver, "Book Now");
		return new TravellerPageBus(driver).get();
 	}
 	
 	  /**
 	 	 * To Fill Traveller Details As Login User
 	 	 * 
 	 	 * @throws Exception
 	 	 */

 	 	public TravellerPageBus fillUserDetailsAsLogin(String email, String Password) throws Exception {
 	 		BrowserActions.clickOnElement(chkBoxYatraUser, driver, "Existing Yatra User Check Box");
 	 		BrowserActions.typeOnTextField(txtBoxEmailLogin, email, driver, "Enter User Email");
 	 		BrowserActions.typeOnTextField(txtBoxPasswordLogin, Password, driver, "Enter User Password");
 	 		BrowserActions.clickOnElement(btnContinueLogin, driver, "Book Now");
 	 		return new TravellerPageBus(driver).get();
 	 	}
}//ReviewPageBus