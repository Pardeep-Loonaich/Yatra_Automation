package com.Yatra.Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
import com.Yatra.Pages.ElementLayer;
import com.Yatra.Pages.SearchResultActivites;
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class ProductDescriptionActivities  extends LoadableComponent<ProductDescriptionActivities> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	public SearchResultActivites searchResultActivites;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Product Description Activities ***********************************
	 **********************************************************************************************/

	@FindBy(css = "button[data-trackaction='Continue']")
	private WebElement btnContinue;
	
	@FindBy(css = "")
	private WebElement txtDetailActivity;
	
	@FindBy(css = "")
	private WebElement activityPhotos;
	
	@FindBy(css = "")
	private List<WebElement> headerDisplay;
	
	@FindBy(xpath = "")
	private WebElement txtDescrption;
	
	@FindBy(css = "")
	private WebElement txtAdditionalInfo;
	
	@FindBy(css = "")
	private WebElement fldSimilarActivities;
	
	@FindBy(css = "")
	private WebElement dateCalendar;
	
	@FindBy(css = "")
	private WebElement PaxInfo;
	
	@FindBy(css = "")
	private List<WebElement> AvaliableDatesForActivity;
	
	@FindBy(css = "")
	private WebElement AvaliableDate;
	
	@FindBy(css = "")
	private WebElement nextMonth;
	
	@FindBy(css = "")
	private WebElement selectedDate;

	@FindBy(css = "")
	private WebElement noActivity;
	
	@FindBy(css = "")
	private WebElement ErrorMessageNoActivity;

	@FindBy(css = "")
	private WebElement btnCheckAvailability;
	/**********************************************************************************************
	 ********************************* WebElements of Product Description Activities - Ends ****************************
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

	public ProductDescriptionActivities(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		elementLayer = new ElementLayer(driver);
	}// ActivityDetailPage

	@Override
	protected void isLoaded() {
		timer.end();
		if (!isPageLoaded) 
		{
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, btnContinue))) 
		{
		Log.fail("Product Description Page did not open up. Site might be down.", driver);
		}
		Log.message("Total time taken by #"+this.getClass().getTypeName()+" to load is:- "+timer.duration()+" "+TimeUnit.MILLISECONDS);
		Constants.performanceData.put("ProductDescriptionActivies",timer.duration());
	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);

	}  

}
