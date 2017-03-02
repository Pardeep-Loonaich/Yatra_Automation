package com.Yatra.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class Bookings extends LoadableComponent<Bookings>  {
	
	private boolean isPageLoaded;
	private WebDriver driver;
	
	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/
	
	@FindBy(id = "signInBtn")
	public WebElement signInBtn;
	
	@FindBy(id = "signUp")
	public WebElement signUpBtn;

	@FindBy(id = "emailId")
	public WebElement emailIdTxtBox;
	
	@FindBy(id = "password")
	public WebElement passwordTxtBox;
	
	@FindBy(css = "p a[href='javascript:;']")
	public WebElement forgotPasswordLink;
	
	@FindBy(css = "button[id='b2bSignUp']")
	public WebElement registerHereBtn;
	
	@FindBy(css = "button[class='yt-btn btn-facebook btn-block']")
	public WebElement loginWithFacebookBtn;
	
	@FindBy(id = "emailId_guest")
	public WebElement guestEmailtxtBox;
	
	@FindBy(id = "book-num")
	public WebElement bookingReferenceNumTxtBox;
	
	@FindBy(css = "button[class='yt-btn btn-blue btn-block']")
	public WebElement submitBtn;
	
	@FindBy(id = "DYNPMessage")
	public WebElement DYNPMessage;
	
	@FindBy(css = "a[href*='customer-support']")
	public WebElement customerCareLink;
	
	@FindBy(css = "span[class='closeDYNPMessage']")
	public WebElement closeDYNPMessage;
	
	@FindBy(css = "a[class='refNo']")
	public WebElement howDoIFindMyReferenceNumberLinkInitialState;
	
	@FindBy(css = "a[class='refNo rotate']")
	public WebElement howDoIFindMyReferenceNumberLinkRotatedState;
	
	@FindBy(id = "diyLink")
	public WebElement knowMoreLink;
	
	
	
	/**********************************************************************************************
	 ********************************* WebElements of Home Page - Ends ****************************
	 **********************************************************************************************/
	@Override
	protected void load() {
		if (!isPageLoaded) {
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, submitBtn))) {
			Log.fail("Agent Login Page did not open up. Site might be down.", driver);
		}	
	}

	@Override
	protected void isLoaded() throws Error { 
		Utils.waitForPageLoad(driver);
		isPageLoaded = true;
	}
	 public void signIn(String loginId , String password) throws Exception{
		    BrowserActions.typeOnTextField(emailIdTxtBox, loginId, driver, "Email Id");
			BrowserActions.typeOnTextField(passwordTxtBox, password, driver, "Password");
			BrowserActions.clickOnElement(signInBtn, driver, "SignIn buttom");
	 }

	 public void submitBookingDetails(String email , String bookingReferenceNum) throws Exception{
		 BrowserActions.typeOnTextField(guestEmailtxtBox, email, driver, "Email Address");
		 BrowserActions.typeOnTextField(bookingReferenceNumTxtBox, bookingReferenceNum, driver, "Booking Reference Number");
		 BrowserActions.clickOnElement(submitBtn, driver, "Submit Button");
	 }
	 
	 public void loginWithFacebook() throws Exception{
		 BrowserActions.clickOnElement(loginWithFacebookBtn, driver, "Login With Facebook Button");
	 }
	 
	 public void signUp() throws Exception{
		 BrowserActions.clickOnElement(signUpBtn, driver, "SignUp Button");
	 }
	 
	 public void navigateToCustomerCarePage() throws Exception{
		 BrowserActions.clickOnElement(customerCareLink, driver, "Customer Care Link");
	 }
	 
	 public void closeDYNPMessage() throws Exception{
		 BrowserActions.clickOnElement(closeDYNPMessage, driver, "close DYNP message");
	 }
}
