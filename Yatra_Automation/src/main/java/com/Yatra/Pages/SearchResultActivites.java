package com.Yatra.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

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

	@FindBy(css = "div[class='noMatchRes']>p")
	private WebElement txtNoResultFound;
	
	//no-activity-tip
	@FindBy(css = "article[class*='eventTrackable']>div[class='my-res-info']>div[class='my-price tr']>ul>li:not([class='left'])>h3:not(i)")
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
	
	@FindBy(css="a[title='Price']")
	private WebElement arrwPrice;

	@FindBy(css="li[ng-repeat*='categoryFilter']>label")
	private List<WebElement> lstCategory;
	
	@FindBy(css="div[class='left fl']>p[class*='fs-12']")
	private WebElement txtResultStrip;
	
	@FindBy(css="a[title='Recommended']")
	private WebElement txtRecommended;
	
	@FindBy(css="a[title='Popular']")
	private WebElement txtPopular;
	
	
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
		Log.message("Total time taken by #"+this.getClass().getTypeName()+"to load is:- "+timer.duration()+" "+TimeUnit.MILLISECONDS);
		Constants.performanceData.put("SearchresultActivities",timer.duration());
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
		boolean Flag = false;
		ArrayList<String> price = new ArrayList<String>();
		for(int i=0;i<2;i++){
		BrowserActions.clickOnElement(arrwPrice, driver, "Arranging Price in ascending order.");
		}
		Utils.waitForPageLoad(driver);
		for (int j = 1; j < txtPriceValue.size(); j++) {
			String price_Txt = BrowserActions.getText(driver, txtPriceValue.get(j), "Getting text of the price.").trim().replace("Rs.", "").replace(",", "");
			price.add(price_Txt);
		}
		System.out.println(price);
		for (int i = 1; i < price.size(); i++) {
			if (price.get(i - 1).compareTo(price.get(i)) <= 0)
			Flag=true;			
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
			if (popular.get(i - 1).compareTo(popular.get(i)) > 0){
				Flag = false;
			}
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
	Thread.sleep(3000);
	driver.findElement(By.cssSelector("article[class='my-res fr eventTrackable ng-scope']:nth-child("+index+")>div[class='my-res-info']>div[class='my-price tr']>a")).click();
	return new ActivityDetailPage(driver).get();
	}
	
	/**
	 * Select the category from the left navigation and return the number of that category
	 * @param categoryName
	 * @return
	 * @throws Exception
	 */
	public String selectCategory(String categoryName) throws Exception{
		for(WebElement category:lstCategory){
			if(category.findElement(By.cssSelector("span[class*='clip-overflow']")).getAttribute("title").contains(categoryName)){
			BrowserActions.clickOnElement(category.findElement(By.cssSelector("span[class*='clip-overflow']")), driver, "Selected the Category.");	
			String catgryNum = BrowserActions.getText(driver, category.findElement(By.cssSelector("span[class*='ltr-gray pl5 ng-binding']")), "Getting the Category Number available.");
			return catgryNum;
			}
		}
		return null;
	}
	
	
	
	/**
	 * getting the result number from the result Strip
	 * @return
	 * @throws Exception
	 */
	public String gettingTxtFrmResultFoundStrip() throws Exception{
		String resultValue[] = BrowserActions.getText(driver, txtResultStrip, "Getting the number of result found for the category.").split(" ");
		return resultValue[1];
	}
	
	
	/**
	 * Verify Color Of Sort By Activity
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public boolean verifySortByGivenCategory(String categoryNme) throws Exception {
	boolean status3 = false;
	String rgbvalue = "243, 71, 71";
	switch (categoryNme){
	case "Recommended":
		status3= Utils.verifyCssPropertyForElement(txtRecommended,"color",rgbvalue);
		break;
	case "Popular":
		BrowserActions.clickOnElement(txtPopular, driver, "Selected Popular as SortBy option.");
		status3= Utils.verifyCssPropertyForElement(txtPopular,"color",rgbvalue);
		break;
	}
	return status3;
	}
	
}//SearchResultActivitesPageEnd