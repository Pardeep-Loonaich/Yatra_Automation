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

import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class AgentRegister extends LoadableComponent<AgentRegister> {

	private boolean isPageLoaded;
	private WebDriver driver;
	ExecutionTimer timer=new ExecutionTimer();
	
	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/
	//@Harveer- make all element private
	@FindBy(xpath = "//*[@id='submitbutton']")	
	private WebElement btnSubmit;
	
	@Override
	protected void isLoaded() throws Error {
		timer.end();
		if (!isPageLoaded) {
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, btnSubmit))) {
			Log.fail("Agent Login Page did not open up. Site might be down.", driver);
		}	
		Log.message("Total time taken by #"+this.getClass().getTypeName()+" to load is:- "+timer.duration()+" "+TimeUnit.SECONDS);
		
	}

	@Override
	protected void load() {
		timer.start();
		Utils.waitForPageLoad(driver);
		isPageLoaded = true;	
	}
	
	public AgentRegister(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
				Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}
}
