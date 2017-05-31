package com.Yatra.Pages;



import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
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

public class ActivitiesTravellerPage extends LoadableComponent<ActivitiesTravellerPage> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	public SearchResultActivites searchResultActivites;
	ExecutionTimer timer = new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader = EnvironmentPropertiesReader.getInstance();

	/**********************************************************************************************
	 ********************************* WebElements of Yatra ActivitiesTravellerPage
	 * ***********************************
	 **********************************************************************************************/

	@FindBy(css = "form[id='traveller-dom']")
	private WebElement formPaxDetails;

	@FindBy(css = "select[name='title0']")
	private WebElement drpTitle;

	@FindBy(css = "select[name='title0']>option")
	private List<WebElement> lstTitle;

	@FindBy(css = "input[name='fname0']")
	private WebElement txtFirstNme;

	@FindBy(css = "input[name='lname0']")
	private WebElement txtLastNme;

	@FindBy(css = "div[class='wrapper_div']>div[class='margintop12']>label")
	private WebElement lblHotelPickup;

	@FindBy(css = "button[data-trackcategory='Activities Traveler Details']")
	private WebElement btnContinueInTravellr;

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

	public ActivitiesTravellerPage(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		elementLayer = new ElementLayer(driver);
	}// SearchResultActivites

	@Override
	protected void isLoaded() {

		timer.end();
		if (!isPageLoaded) {
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, formPaxDetails))) {
			Log.fail("ActivitiesTravellerPage did not open up. Site might be down.", driver);
		}
		Log.message("Total time taken by #" + this.getClass().getTypeName() + "to load is:- " + timer.duration() + " "
				+ TimeUnit.SECONDS);

	}// isLoaded

	@Override
	protected void load() {
		timer.start();
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}// load

	/**
	 * filling traveller details in Pax Page
	 * 
	 * @throws Exception
	 */
	public void fillTravellerDetails() throws Exception {
		BrowserActions.clickOnElement(drpTitle, driver, "Clicked on 'Title' dropdown.");
		if (lstTitle.size() != 0) {
			int rand = Utils.getRandom(1, lstTitle.size());
			Utils.waitForElement(driver, lstTitle.get(rand));
			BrowserActions.clickOnElement(lstTitle.get(rand), driver, "title selected");
			Utils.waitForPageLoad(driver);
		}

		String randomFirstName = RandomStringUtils.randomAlphabetic(5).toLowerCase();
		String randomLastName = RandomStringUtils.randomAlphabetic(5).toLowerCase();

		// enter First Name with random string
		BrowserActions.typeOnTextField(txtFirstNme, randomFirstName, driver, "First Name");
		Log.event("Successfully entered Passenger FirstName: " + randomFirstName);

		// enter Last Name with random string
		BrowserActions.typeOnTextField(txtLastNme, randomLastName, driver, "Last Name");
		Log.event("Successfully entered Passenger Last Name: " + randomLastName);

	}

	/***
	 * to select the 'Hotels Not Required' checkbox
	 * 
	 * @throws Exception
	 */

	public void clickOnHotelNotRequiredChkbox() throws Exception {
		if(lblHotelPickup.isDisplayed()){
		BrowserActions.clickOnElement(lblHotelPickup, driver, "Clicked on 'checkbox' on Traveller's Page.");
		}
	}

	/**
	 * to click on Continue button and navigate to Payment Page.
	 * 
	 * @return
	 * @throws Exception
	 */
	public PaymentPage clickOnContinueInTravellerPage() throws Exception {
		BrowserActions.clickOnElement(btnContinueInTravellr, driver,
				"Clicked on 'Continue' button on Traveller's Page.");
		return new PaymentPage(driver).get();
	}

}// ActivitiesTravellerPageEnd
