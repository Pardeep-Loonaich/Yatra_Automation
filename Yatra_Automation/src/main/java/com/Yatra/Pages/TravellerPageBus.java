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

public class TravellerPageBus extends LoadableComponent<TravellerPageBus> {

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
	
	@FindBy(css = "input[id='paxname1']")
	WebElement txtBoxName;

	@FindBy(css = "input[class='ytBtn-chek ytBtnOrange-chek eventTrackable']")
	WebElement btnContinue;
	
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

		public TravellerPageBus(WebDriver driver) {
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
	
	
	public void TravellerDetails() throws Exception {
		Utils.waitForPageLoad(driver);
        WebElement Title = driver.findElement(By.cssSelector("select[id='paxtitle1']"));
        BrowserActions.clickOnElement(Title, driver, "Title");
        List<WebElement> Titles = driver.findElements(By.cssSelector("#paxtitle1>option"));
        if (Titles.size() != 0) {
               int rand = Utils.getRandom(2, Titles.size());
               BrowserActions.clickOnElement(Titles.get(rand), driver, "Title Selected");
        }//Random Title Selection
        Thread.sleep(5000);
        String rand =  RandomStringUtils.randomAlphabetic(10);
 		BrowserActions.typeOnTextField(txtBoxName, rand , driver, "Guest Name");//Random Name
 		Thread.sleep(5000);
 		WebElement Age = driver.findElement(By.cssSelector("#paxage1"));
         BrowserActions.clickOnElement(Age, driver, "Title");
         List<WebElement> Ages = driver.findElements(By.cssSelector("#paxage1>option"));
         if (Ages.size() != 0) {
                int rand1 = Utils.getRandom(2, Ages.size());
                BrowserActions.nap(2);
                BrowserActions.clickOnElement(Ages.get(rand1), driver, "Age Selected");//Random Age Selection
         }
         BrowserActions.clickOnElement(btnContinue, driver, "Continue Button");
	}
		
}//TravellerPage