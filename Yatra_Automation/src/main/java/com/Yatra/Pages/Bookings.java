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

public class Bookings extends LoadableComponent<Bookings>  {
	
	private boolean isPageLoaded;
	private WebDriver driver;
	
	/**********************************************************************************************
	 ********************************* WebElements of Yatra Bookings ***********************************
	 **********************************************************************************************/
	
	@FindBy(xpath = "//*[@id='signInBtn']")
	public WebElement btnSignIn;
	
	@FindBy(xpath = "//*[@id='signUp']")
	public WebElement btnSignUp;

	@FindBy(xpath = "//*[@id='emailId']")
	public WebElement txtEmailId;
	
	@FindBy(xpath = "//*[@id='password']")
	public WebElement txtPassword;
	
	@FindBy(css = "p a[href='javascript:;']")
	public WebElement lnkForgotPassword;
	
	@FindBy(css = "button[id='b2bSignUp']")
	public WebElement btnRegisterHere;
	
	@FindBy(css = "button[class='yt-btn btn-facebook btn-block']")
	public WebElement btnLoginWithFacebook;
	
	@FindBy(xpath = "//*[@id='emailId_guest']")
	public WebElement txtGuestEmail;
	
	@FindBy(xpath = "//*[@id='book-num']")
	public WebElement txtBookingReferenceNum;
	
	@FindBy(css = "button[class='yt-btn btn-blue btn-block']")
	public WebElement btnSubmit;
	
	@FindBy(xpath = "//*[@id='DYNPMessage']")
	public WebElement DYNPMessage;
	
	@FindBy(css = "a[href*='customer-support']")
	public WebElement lnkCustomerCare;
	
	@FindBy(css = "span[class='closeDYNPMessage']")
	public WebElement closeDYNPMessage;
	
	@FindBy(css = "a[class='refNo']")
	public WebElement howDoIFindMyReferenceNumberLinkInitialState;
	
	@FindBy(css = "a[class='refNo rotate']")
	public WebElement howDoIFindMyReferenceNumberLinkRotatedState;
	
	@FindBy(xpath = "//*[@id='diyLink']")
	public WebElement lnkKnowMore;
	
	 @FindBy(css="#qbLink")
	 WebElement lnkQuickBook;
	 
	 @FindBy(css="#view-card-btn")
	 WebElement btnViewCard;
	 
	 @FindBy(css="a[title='Delete']")
	 WebElement deleteCreditCard;
	 
	 @FindBy(css="a[class='logo']>i")
	 WebElement logoYatra;
	 
	 @FindBy(css=" button[confirm='yes']")
	 WebElement btnYesInPopUp;
	
	
	/**********************************************************************************************
	 ********************************* WebElements of Bookings - Ends ****************************
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

	public Bookings(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}

	
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
	 
	 
	
	 /**
	  * To Click on QuickBook Link 
	  * @throws Exception
	  */
	 public void clickOnQuickBook() throws Exception{
		 BrowserActions.clickOnElement(lnkQuickBook, driver, "Navigated to Quick Book Link.");
		 Utils.waitForPageLoad(driver);
		 BrowserActions.clickOnElement(lnkQuickBook, driver, "Navigated to Quick Book Link.");

	 }
	 
	/**
	 * to click on View Saved Cards Button
	 * @throws Exception
	 */
	 	 public void clickOnViewSavedCard() throws Exception{
		 BrowserActions.clickOnElement(btnViewCard, driver, "Clicked on button View Saved Cards.");
	 }
	 
	/**
	 * to click on Delete CreditCard icon
	 * @throws Exception
	 */
	 
	 public void clickOnDeleteCreditCard() throws Exception{
		 BrowserActions.clickOnElement(deleteCreditCard, driver, "Clicked on delete Saved Cards.");
	 } 
	 
	 /**
	  * to navigate to home page by clicking on Yatra Logo
	  * @return
	  * @throws Exception
	  */
	
	 public HomePage navigateToHomePage() throws Exception{
		 
		 BrowserActions.scrollToViewElement(logoYatra, driver);
		 BrowserActions.clickOnElement(logoYatra, driver, "Clicked on 'Yatra' logo.");
		 return new HomePage(driver).get();
	 }
	
	
	 /**
	  * to click on confirmation popup to delete saved cards
	  * @throws Exception
	  */
	 public void clickOnYesInPopUp() throws Exception{
		 BrowserActions.clickOnElement(btnYesInPopUp, driver, "Clicked On 'Yes' button in Pop-up.");
	 } 
}