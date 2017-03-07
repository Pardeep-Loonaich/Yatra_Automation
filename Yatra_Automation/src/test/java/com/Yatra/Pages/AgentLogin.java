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

public class AgentLogin extends LoadableComponent<AgentLogin>{
	
	private boolean isPageLoaded;
	private WebDriver driver;
	
	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/
	
	@FindBy(id = "signInBtn")
	public WebElement btnSignIn;

	@FindBy(id = "emailId")
	public WebElement txtMailId;
	
	@FindBy(id = "password")
	public WebElement txtPassword;
	
	@FindBy(css = "p a[href*='forgotpassword']")
	public WebElement lnkForgotPassword;
	
	@FindBy(css = "button[id='b2bSignUp']")
	public WebElement btnRegisterHere;
	
	/**********************************************************************************************
	 ********************************* WebElements of Home Page - Ends ****************************
	 **********************************************************************************************/

	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, btnSignIn))) {
			Log.fail("Agent Login Page did not open up. Site might be down.", driver);
		}	
	}

	@Override
	protected void load() {
		Utils.waitForPageLoad(driver);
		isPageLoaded = true;		
	}
	
	public AgentLogin(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
				Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}

	public void signIn(String loginId , String password) 
			throws Exception{
		BrowserActions.typeOnTextField(
				txtMailId, loginId, driver,
						"Email Id");
		BrowserActions.typeOnTextField(
				txtPassword, password, driver,
						"Password");
		BrowserActions.clickOnElement(
				btnSignIn, driver, 
						"SignIn buttom");
	}// the return type needs to be chnaged because the sinin action returns the homepage of the user
	
	public AgentRegister clickOnAgentRegsiter() throws Exception{
		BrowserActions.clickOnElement(
				btnRegisterHere, driver,
						"Agent Register Btn");
		return new AgentRegister(driver).get();
	}
	
}
