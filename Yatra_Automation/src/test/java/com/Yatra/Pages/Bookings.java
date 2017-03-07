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
	public WebElement btnSignIn;
	
	@FindBy(id = "signUp")
	public WebElement btnSignUp;

	@FindBy(id = "emailId")
	public WebElement txtEmailId;
	
	@FindBy(id = "password")
	public WebElement txtPassword;
	
	@FindBy(css = "p a[href='javascript:;']")
	public WebElement lnkForgotPassword;
	
	@FindBy(css = "button[id='b2bSignUp']")
	public WebElement btnRegisterHere;
	
	@FindBy(css = "button[class='yt-btn btn-facebook btn-block']")
	public WebElement btnLoginWithFacebook;
	
	@FindBy(id = "emailId_guest")
	public WebElement txtGuestEmail;
	
	@FindBy(id = "book-num")
	public WebElement txtBookingReferenceNum;
	
	@FindBy(css = "button[class='yt-btn btn-blue btn-block']")
	public WebElement btnSubmit;
	
	@FindBy(id = "DYNPMessage")
	public WebElement DYNPMessage;
	
	@FindBy(css = "a[href*='customer-support']")
	public WebElement lnkCustomerCare;
	
	@FindBy(css = "span[class='closeDYNPMessage']")
	public WebElement closeDYNPMessage;
	
	@FindBy(css = "a[class='refNo']")
	public WebElement howDoIFindMyReferenceNumberLinkInitialState;
	
	@FindBy(css = "a[class='refNo rotate']")
	public WebElement howDoIFindMyReferenceNumberLinkRotatedState;
	
	@FindBy(id = "diyLink")
	public WebElement lnkKnowMore;
	
	
	
	/**********************************************************************************************
	 ********************************* WebElements of Home Page - Ends ****************************
	 **********************************************************************************************/
	@Override
	protected void load() {
		if (!isPageLoaded) {
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, btnSubmit))) {
			Log.fail("Agent Login Page did not open up. Site might be down.", driver);
		}	
	}

	@Override
	protected void isLoaded() throws Error { 
		Utils.waitForPageLoad(driver);
		isPageLoaded = true;
	}
	 public void signIn(String loginId , String password) throws Exception{
		    BrowserActions.typeOnTextField(txtEmailId, loginId, driver, "Email Id");
			BrowserActions.typeOnTextField(txtPassword, password, driver, "Password");
			BrowserActions.clickOnElement(btnSignIn, driver, "SignIn buttom");
	 }

	 public void submitBookingDetails(String email , String bookingReferenceNum) throws Exception{
		 BrowserActions.typeOnTextField(txtGuestEmail, email, driver, "Email Address");
		 BrowserActions.typeOnTextField(txtBookingReferenceNum, bookingReferenceNum, driver, "Booking Reference Number");
		 BrowserActions.clickOnElement(btnSignIn, driver, "Submit Button");
	 }
	 
	 public void loginWithFacebook() throws Exception{
		 BrowserActions.clickOnElement(btnLoginWithFacebook, driver, "Login With Facebook Button");
	 }
	 
	 public void signUp() throws Exception{
		 BrowserActions.clickOnElement(btnSignUp, driver, "SignUp Button");
	 }
	 
	 public void navigateToCustomerCarePage() throws Exception{
		 BrowserActions.clickOnElement(lnkCustomerCare, driver, "Customer Care Link");
	 }
	 
	 public void closeDYNPMessage() throws Exception{
		 BrowserActions.clickOnElement(closeDYNPMessage, driver, "close DYNP message");
	 }
}
