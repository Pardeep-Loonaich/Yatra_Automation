package com.Yatra.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class AgentRegister extends LoadableComponent<AgentRegister> {

	private boolean isPageLoaded;
	private WebDriver driver;
	
	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/
	//@Harveer- make all element private
	@FindBy(xpath = "//*[@id='submitbutton']")	
	public WebElement btnSubmit;
	
	@Override
	protected void isLoaded() throws Error {
		if (!isPageLoaded) {
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, btnSubmit))) {
			Log.fail("Agent Login Page did not open up. Site might be down.", driver);
		}	
	}

	@Override
	protected void load() {
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
