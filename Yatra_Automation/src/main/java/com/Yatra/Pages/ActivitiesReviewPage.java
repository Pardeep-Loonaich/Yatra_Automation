package com.Yatra.Pages;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class ActivitiesReviewPage  extends LoadableComponent<ActivitiesReviewPage> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	public SearchResultActivites searchResultActivites;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();
	
	/**********************************************************************************************
	 ********************************* WebElements of Yatra ActivitiesReviewPage ***********************************
	 **********************************************************************************************/

	@FindBy(css = "div[id='promocodeContainer']")
	private WebElement divPromoCode;
	
	@FindBy(css = "[data-trackaction='Continue']")
	private WebElement btnContinueInReviewPage;
	
	@FindBy(css = "section[class='login-content']")
	private WebElement divLoginContent;
	
	@FindBy(css = "input[name='email']")
	private WebElement txtEmailId;

	@FindBy(css = "span[class='error-msg']")
	private WebElement msgError;
	
	@FindBy(css="div[class='row']>label>span[class='account-msg my-checkbox']")
	private WebElement chkSignedAsLogin;
	
	@FindBy(css = "button[class*='login-btn ']")
	private WebElement btnLoginInAsGuest;

	@FindBy(css = "button[class*='login-btn']")
	private WebElement btnLogin;

	@FindBy(css = "form[name='userForm']>div[ng-show='!registered']>input[name='mobile']")
	private WebElement txtMobileNum;
	
	@FindBy(css = "input[name='password']")
	private WebElement txtPasswrd;
	
	@FindBy(css = "button[class='paymentSprite yatraFb']")
	private WebElement logoFB;
	
	
	/**********************************************************************************************
	 ********************************* WebElements of ActivitiesReviewPage - Ends ****************************
	 **********************************************************************************************/

	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : Webdriver
	 * 
	 * @param url
	 *            : UAT URL
	 */

	public ActivitiesReviewPage(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		elementLayer = new ElementLayer(driver);
	}// SearchResultActivites

	@Override
	protected void isLoaded() {

		timer.end();
		if (!isPageLoaded) 
		{
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, divPromoCode))) 
		{
		Log.fail("ActivitiesReviewPage did not open up. Site might be down.", driver);
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
	 * to click on continue button on the review page.
	 * @throws Exception
	 */
	public void clickOnContinue() throws Exception{
		BrowserActions.clickOnElement(btnContinueInReviewPage, driver, "Clicked on Continue Button in Review Page.");
	}
	
	/***
	 * entered the email address in the email address field
	 * @param email
	 * @throws Exception
	 */
	public void enterEmailAddress(String email) throws Exception{
		BrowserActions.typeOnTextField(txtEmailId, email, driver, "Enter the email address in the Email Address text field.");
	}
	
	/**
	 * to click on 'I hve yatra account checkbox'
	 * @throws Exception
	 */
	public void clickOnYatraAccountChkbox() throws Exception{
		BrowserActions.clickOnElement(chkSignedAsLogin, driver, "Clicking on having 'Yatra Account' checkbox.");

	}
	/***
	 * entered the email address in the email address field
	 * @param email
	 * @throws Exception
	 */
	public String enterInvlidEmailAddressErrorMsg() throws Exception{
		BrowserActions.clickOnElement(btnLoginInAsGuest, driver, "Clicked on 'Login As Guest' button");
		return BrowserActions.getText(driver, msgError, "Getting error message text.");
	}
	
	

	/**
	 * to verify having Yatra account checkbox
	 * @return
	 * @throws Exception
	 */
	public boolean verifyYatraUserCheckboxInLogin() throws Exception{
		return BrowserActions.isRadioOrCheckBoxSelected(driver.findElement(By.cssSelector("div[class='row']>label>span[class='account-msg my-checkbox']>input")));
	}
	
	/**
	 * to login as a Guest USer
	 * @param email
	 * @param number
	 * @throws Exception
	 */
	public ActivitiesTravellerPage loginAsGuestUser(String email,String number) throws Exception{
		BrowserActions.typeOnTextField(txtEmailId, email, driver, "Enter Email address.");
		BrowserActions.typeOnTextField(txtMobileNum, number, driver, "Enter Phone number.");
		BrowserActions.clickOnElement(btnLoginInAsGuest, driver, "Click on Login as guest");
		return new ActivitiesTravellerPage(driver).get();
	}

}