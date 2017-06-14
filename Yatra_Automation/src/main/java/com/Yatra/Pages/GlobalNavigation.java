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

import com.Yatra.Utils.Constants;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;


public class GlobalNavigation extends LoadableComponent<GlobalNavigation> {

	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	String sMian_Nav_Option="";
	String sSub_Nav_Option="";
	final String  sMAIN_NAV_OPTION_OBJECT="//div[@class='menu']//a[contains(.,'"+sMian_Nav_Option+"')]";
	final String  sMAIN_NAV_SUBMENU_OPTION_OBJECT="//div[@class='menu']//a[contains(.,'"+sSub_Nav_Option+"')]";
	ExecutionTimer timer=new ExecutionTimer();


	/**********************************************************************************************
	 this class contains all re-usable method webElement for global navigation panel
	 **********************************************************************************************/

	@FindBy(id = "BE_flight_arrival_city")
	private WebElement txtDestination;

	@FindBy(id = "BE_flight_flsearch_btn")
	private WebElement btnSearch;




	/**********************************************************************************************
	 ********************************* WebElements of Home Page - Ends ****************************
	 **********************************************************************************************/

	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : Webdriver
	 * 
	 * @param url: URL
	 */
	public GlobalNavigation(WebDriver driver, String url) {
		appURL = url;
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}// Globalnavigation constructor

	/**
	 * 
	 * @param driver
	 *            : webdriver
	 */
	public GlobalNavigation(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);


	}//Globalnavigation constructor 

	@Override
	protected void isLoaded() {
		timer.end();
		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, btnSearch))) {
			Log.fail("Home Page did not open up. Site might be down.", driver);
		}
		Log.message("Total time taken by #"+this.getClass().getTypeName()+" to load is:- "+timer.duration()+" "+TimeUnit.MILLISECONDS);
		Constants.performanceData.add(timer.duration());
	}

	@Override
	protected void load() {
		timer.start();
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForPageLoad(driver);
	}// load

/**
  @Method Name: clickMainMenu(String sMainMenu)
  @Description: use this method to click on main navigation panel, based on option available
 * @param sMainMenu- have to pass option as string
 * 
 */
	public void clickMainMenu(String sMainMenu)
	{
		sMian_Nav_Option=sMainMenu;
		
		driver.findElement(By.xpath(sMAIN_NAV_OPTION_OBJECT)).click();
	}
	
	/**
	  @Method Name: clickSubMenu(String sSubMenu)
	  @Description: use this method to click on sub menu option, based on option available(it will not woerk for recent search)
	 * @param sMainMenu- have to pass sub option as string
	 * 
	 */
		public void clickSubMenu(String sSubMenu)
		{
			sSub_Nav_Option=sSubMenu;
			
			driver.findElement(By.xpath(sMAIN_NAV_SUBMENU_OPTION_OBJECT)).click();
		}

		
		
}// GlobalNavigation
