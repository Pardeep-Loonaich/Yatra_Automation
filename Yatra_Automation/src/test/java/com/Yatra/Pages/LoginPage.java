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

public class LoginPage extends LoadableComponent<LoginPage> {

	private final WebDriver driver;
	private boolean isPageLoaded;

	/**********************************************************************************************
	 ********************************* WebElements of Login Page ***********************************
	 **********************************************************************************************/

	@FindBy(xpath = "//div[@id='main-container']/div/div[1]")
	WebElement divLoginBox;

	@FindBy(id = "emailId")
	WebElement txtUserName;

	@FindBy(id = "password")
	WebElement txtPassWord;

	@FindBy(id = "signInBtn")
	WebElement btnSignIn;

	/**********************************************************************************************
	 ********************************* WebElements of Login Page - Ends ****************************
	 **********************************************************************************************/

	/**
	 * 
	 * Constructor class for Login page Here we initializing the driver for page
	 * factory objects. For ajax element waiting time has added while
	 * initialization
	 * 
	 * @param driver
	 * @param url
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}

	@Override
	protected void isLoaded() {

		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, divLoginBox))) {
			Log.fail("SignIn page didn't open up", driver);
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}

	/**
	 * Enter email id
	 * 
	 * @param emailid
	 *            as string
	 * @throws Exception
	 */
	public void enterEmailID(String emailid) throws Exception {
		Utils.waitForElement(driver, txtUserName);
		BrowserActions.typeOnTextField(txtUserName, emailid, driver, "Email id");
		Log.event("Entered the Email Id: " + emailid);
	}

	/**
	 * Enter password
	 * 
	 * @param pwd
	 *            as string
	 * @throws Exception
	 */
	public void enterPassword(String pwd) throws Exception {
		Utils.waitForElement(driver, txtPassWord);
		BrowserActions.typeOnTextField(txtPassWord, pwd, driver, "Password");
		Log.event("Entered the Password: " + pwd);
	}

	/**
	 * To click LogIn button on signin page
	 * 
	 * @throws Exception
	 */
	public void clickBtnSignIn() throws Exception {
		// final long startTime = StopWatch.startTime();
		BrowserActions.clickOnElement(btnSignIn, driver, "Sign In");
		Utils.waitForPageLoad(driver);
		// Log.event("Clicked 'Login' button on SignIn page", StopWatch.elapsedTime(startTime));
	}

}