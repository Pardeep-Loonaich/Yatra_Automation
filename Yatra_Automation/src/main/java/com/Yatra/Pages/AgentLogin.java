
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
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class AgentLogin extends LoadableComponent<AgentLogin>{
	
	private boolean isPageLoaded;
	private WebDriver driver;
	ExecutionTimer timer=new ExecutionTimer();
	
	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/
	
	@FindBy(xpath = "//*[@id='signInBtn']")
	private WebElement btnSignIn;

	@FindBy(xpath = "//*[@id='emailId']")
	private WebElement txtMailId;
	
	@FindBy(xpath = "//*[@id='password']")
	private WebElement txtPassword;
	
	@FindBy(css = "p a[href*='forgotpassword']")
	private WebElement lnkForgotPassword;
	
	@FindBy(css = "button[id='b2bSignUp']")
	private WebElement btnRegisterHere;
	
	@FindBy(css = ".login-form ")
	private WebElement loginForm;

	
	/**********************************************************************************************
	 ********************************* WebElements of Home Page - Ends ****************************
	 **********************************************************************************************/


	@Override
	protected void isLoaded() throws Error {
		timer.end();
		if (!isPageLoaded) {
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, loginForm))) {
			Log.fail("Agent Login Page did not open up. Site might be down.", driver);
		}	
		Log.message("Total time taken by #"+this.getClass().getTypeName()+" to load is:- "+timer.duration()+" "+TimeUnit.MILLISECONDS);
		Constants.performanceData.put("AgentLoginPage",timer.duration());
	}

	@Override
	protected void load() {
		timer.start();
		Utils.waitForPageLoad(driver);
		isPageLoaded = true;		
	}
	
	public AgentLogin(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}

	public void signIn(String loginId , String password) 
			throws Exception{
		BrowserActions.typeOnTextField(	txtMailId, loginId, driver,"Email Id");
		BrowserActions.typeOnTextField(txtPassword, password, driver,"Password");
		BrowserActions.clickOnElement(btnSignIn, driver, "SignIn buttom");
	}// the return type needs to be chnaged because the sinin action returns the homepage of the user
	
	public AgentRegister clickOnAgentRegsiter() throws Exception{
		
		BrowserActions.clickOnElement(btnRegisterHere, driver,"Agent Register Btn");
		return new AgentRegister(driver).get();
	}
	
}
