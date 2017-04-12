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
import org.openqa.selenium.support.ui.Select;
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
	//@Harveer- make all element private under findby annotation

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/
	@FindBy(css = "a[title='Change Your Selected Bus']")
	WebElement BtnChangeBus;
	
	@FindBy(css = "input[id='paxname1']")
	WebElement txtBoxName;

	@FindBy(css = "input[class='ytBtn-chek ytBtnOrange-chek eventTrackable']")
	WebElement btnContinue;
	
	@FindBy(css = "div[class='toasterHolder']")
	WebElement txtErrorMsg;
	
	@FindBy(css = "span[id='editMobileNo']>a")
	WebElement lnkEditButton;
	
	@FindBy(css = "input[id='userMobile']")
	WebElement fldContentUserMobileNo;
	
	@FindBy(css = "#userMobile")
	WebElement fldUserMobileNo;

	@FindBy(css = "span[class='custom-checkbox']>input")
	WebElement chkBoxAddonsFirst;
	
	@FindBy(css = "div[class='wfull']>span>input")
	WebElement chkBoxAddonsSecond;
	
	@FindBy(css = "div[class='agreeToContNxt']>a")
	WebElement lnkTermAndCond;
	
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
	
	
	public PaymentPageBus TravellerDetails(String name) throws Exception {
		Utils.waitForPageLoad(driver);
        WebElement Title = driver.findElement(By.cssSelector("select[id='paxtitle1']"));
        BrowserActions.clickOnElement(Title, driver, "Title");
        List<WebElement> Titles = driver.findElements(By.cssSelector("#paxtitle1>option"));
        if (Titles.size() != 0) {
               int rand = Utils.getRandom(2, Titles.size());
               BrowserActions.clickOnElement(Titles.get(rand), driver, "Title Selected");
        }//Random Title Selection
        Thread.sleep(5000);
 		BrowserActions.typeOnTextField(txtBoxName, name , driver, "Guest Name");//Name From Regression
 		Thread.sleep(5000);
 		WebElement Age = driver.findElement(By.cssSelector("#paxage1"));
 		Select age = new Select(Age);
 		List<WebElement> Ages = age.getOptions();
         if (Ages.size() != 0) {
           int rand1 = Utils.getRandom(2, Ages.size());               
           BrowserActions.nap(2);
           age.selectByIndex(rand1);
         }
         BrowserActions.clickOnElement(btnContinue, driver, "Continue Button");
		return new PaymentPageBus(driver).get();
	}
	/**
	 * Getting the Error Text
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextErrorMsg() throws Exception {
		Utils.waitForElement(driver, txtErrorMsg);
		String txtDetails = BrowserActions.getText(driver, txtErrorMsg,
				"Getting Error Text");
		return txtDetails;
	}/**
	 * To click Edit Mobile Link
	 * 
	 * @throws Exception
	 */

	public void clickEditMobileLink() throws Exception {
		Utils.waitForElement(driver, lnkEditButton);
		BrowserActions.clickOnElement(lnkEditButton, driver, "Click Edit Button");
		Utils.waitForPageLoad(driver);
	}

	/**
	 * To Enter Mobile Number
	 * 
	 * @throws Exception
	 */
	public void enterMobileNumber(String Number) throws Exception {
		Utils.waitForElement(driver, fldContentUserMobileNo);
		BrowserActions.typeOnTextField(fldContentUserMobileNo, Number, driver, "User Phone Number");
	}/**
	 * To Get Mobile Number
	 * 
	 * @throws Exception
	 */
	public String getTextMobileNo() throws Exception {
		Utils.waitForElement(driver, fldUserMobileNo);
		String number =	fldUserMobileNo.getAttribute("value");
		return number;	
	}
	public boolean checkBoxFirst() throws Exception {
		boolean flag = false;
		String result = chkBoxAddonsFirst.getAttribute("type");
		if(result.equals("checkbox")){
			 flag= true;
		}else{
			flag= false;
		}
		return flag;
		
	}
	public boolean checkBoxSecond() throws Exception {
		boolean flag = false;
		String result = chkBoxAddonsSecond.getAttribute("type");
		if(result.equals("checkbox")){
			 flag= true;
		}else{
			flag= false;
		}
		return flag;
	}
 /**
 * To click On Term and Conditions
 * 
 * @throws Exception
 */

public void clickOnTermAndCondition() throws Exception {
	Utils.waitForElement(driver, lnkTermAndCond);
	BrowserActions.clickOnElement(lnkTermAndCond, driver, "Click Term And Condition Link");
	Utils.waitForPageLoad(driver);
}
/**
 * to verify Tnc page
 * @return
 * @throws Exception
 */
public boolean verifyTnCPage() throws Exception{
	return BrowserActions.getText(driver, driver.findElement(By.cssSelector(".ytAboutHanding.txtDrkGreyI")), "Getting Text from span").contains("Terms and Conditions");
}	
}//TravellerPage