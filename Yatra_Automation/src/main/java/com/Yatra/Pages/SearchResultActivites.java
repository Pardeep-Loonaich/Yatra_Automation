package com.Yatra.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
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
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class SearchResultActivites  extends LoadableComponent<SearchResultActivites> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	public SearchResultActivites searchResultActivites;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();
	
	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/

	@FindBy(css = "p[class='new-gray-button fl small link-button hidden-md modify-btn eventTrackable']")
	private WebElement btnModifySearch;

	@FindBy(css = "div[class='noResultsTxt']")
	private WebElement txtNoResultFound;
	
	//no-activity-tip
	@FindBy(css = "div[class='my-price tr']>ul>li:not([class='left'])>h3")
	private List<WebElement> txtPriceValue;
	
	@FindBy(css = "span[class='ico-rating rating-4']")
	private List<WebElement> txtPopularActivites;
	
	@FindBy(css = "input[id='modifyCityInput_value']")
	private WebElement txtFldModifySearch;
	
	@FindBy(xpath = ".//*[@id='modifySearch']/input")
	private WebElement btnSearch;

	@FindBy(css = "div[class='my-desc']>h3")
	private WebElement details;

	@FindBy(css = "article[class='my-res fr eventTrackable ng-scope']:nth-child(3)>div[class='my-res-info']>div[class='my-price tr']>ul>li:not([class='left'])>h3")
	private WebElement price;
	
	/**********************************************************************************************
	 ********************************* WebElements of Home Page - Ends ****************************
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

	public SearchResultActivites(WebDriver driver) {
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
		if (isPageLoaded && !(Utils.waitForElement(driver, btnModifySearch))) 
		{
		Log.fail("SearchResult Page did not open up. Site might be down.", driver);
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
	 * Click On Modify Search Button
	 * 
	 * @return
	 * @throws Exception
	 */
	public void clickOnModifySearchButton() throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.clickOnElement(btnModifySearch, driver, "Click On Modify Search button");
	}
	
	
	/**
	 * Getting the text from the SRP 'No Activity found'
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextErrorMessage() throws Exception {
		Utils.waitForPageLoad(driver);
		String txtDetails = BrowserActions.getText(driver, txtNoResultFound,
				"Getting text from the SRP 'No Activity found'");
		return txtDetails;
	}
	
	/**
	 * To check Price In Sorted Form
	 * 
	 * @throws Exception
	 */
	public boolean getSortedPrice() throws Exception {
		boolean Flag = true;
		ArrayList<String> price = new ArrayList<String>();
		for (int j = 6; j < txtPriceValue.size(); j++) {
			price.add(driver
					.findElement(By.cssSelector("div[class='my-price tr']>ul>li:not([class='left'])>h3")).getText());
		}
		Collections.sort(price);
		System.out.println(price);
		for (int i = 1; i < price.size(); i++) {
			if (price.get(i - 1).compareTo(price.get(i)) > 0)
				Flag = false;
		}
		return Flag;
	}
	/**
	 * To check Price In Sorted Form
	 * 
	 * @throws Exception
	 */
	public boolean getSortedPopluarActivity() throws Exception {
		
		boolean Flag = true;
		ArrayList<String> popular = new ArrayList<String>();
		for (int j = 1; j < 5; j++) {
			popular.add(driver
					.findElement(By.cssSelector("span[class='ico-rating rating-"+ j + "']")).getText());
		}
		Collections.sort(popular);
		System.out.println(popular);
		for (int i = 1; i < popular.size(); i++) {
			if (popular.get(i - 1).compareTo(popular.get(i)) > 0)
				Flag = false;
		}
		return Flag;
	}
	
	/**
	 * to Enter Destination in Modify Search Field and click Search Button
	 * 
	 * @return
	 * @throws Exception
	 */
	public void enterDestinationInModifySearch(String City) throws Exception {
		Utils.waitForPageLoad(driver);
		BrowserActions.typeOnTextField(txtFldModifySearch, City, driver, "Modify Search Text Field");
		btnSearch.click();
	}
	
	/**
	 * to get the text of the activities detail by giving tile index as parameter and starts the index from '2'
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public String getTextActivityDetailsByTileIndex(int index) throws Exception {
		Utils.waitForPageLoad(driver);
		String txtDetails = BrowserActions.getText(driver, driver.findElement(By.cssSelector("article[class='my-res fr eventTrackable ng-scope']:nth-child("+index+")>div[class='my-res-info']>div[class='my-desc']>h3")), "Getting text of the Activity.");
		return txtDetails;
	}
	
	/**
	 * to Click on Book Now By Index as parameter and starts the index from '2'
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public ActivityDetailPage ClickBookNowByIndex(int index) throws Exception {
	Utils.waitForPageLoad(driver);
	driver.findElement(By.cssSelector("article[class='my-res fr eventTrackable ng-scope']:nth-child("+index+")>div[class='my-res-info']>div[class='my-price tr']>a")).click();
	return new ActivityDetailPage(driver).get();
	}
	
	
	
	
	
	
	
	
	
}//SearchResultActivitesPageEnd