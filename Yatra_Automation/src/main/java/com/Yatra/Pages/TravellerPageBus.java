package com.Yatra.Pages;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.Yatra.Utils.BrowserActions;
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class TravellerPageBus extends LoadableComponent<TravellerPageBus> {

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;
	SearchResultBus searchResult;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Home Page ***********************************
	 **********************************************************************************************/
	@FindBy(css = "div[id='paxListBox']")
	private WebElement fldContentPaxDetails;

	@FindBy(css = "input[id='paxname1']")
	private WebElement txtBoxName;

	@FindBy(css = "input[class='ytBtn-chek ytBtnOrange-chek eventTrackable']")
	private WebElement btnContinue;

	@FindBy(css = "div[class='toasterHolder']")
	private WebElement txtErrorMsg;

	@FindBy(css = "span[id='editMobileNo']>a")
	private WebElement lnkEditButton;

	@FindBy(css = "input[id='userMobile']")
	private WebElement fldContentUserMobileNo;

	@FindBy(css = "#userMobile")
	private WebElement fldUserMobileNo;

	@FindBy(css = "span[class='custom-checkbox']>input")
	private WebElement chkBoxAddonsFirst;

	@FindBy(css = "div[class='wfull']>span>input")
	private WebElement chkBoxAddonsSecond;

	@FindBy(css = "div[class='agreeToContNxt']>a")
	private WebElement lnkTermAndCond;
	
	@FindBy(css = "div[class='yatra-promo']>input")
	private WebElement txtFldPromoCode;
	
	@FindBy(css = "span[class='promo-heading ml10']")
	private WebElement txtPromoMessage;
	
	@FindBy(css = "input[id='promoValidate']")
	private WebElement btnApply;
	
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
	}// TravellerPageBus

	@Override
	protected void isLoaded() {

		timer.end();
		if (!isPageLoaded) 
		{
			Assert.fail();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, fldContentPaxDetails))) 
		{
		Log.fail("Travellers Page did not open up. Site might be down.", driver);
		}
		Log.message("Total time taken by #"+this.getClass().getTypeName()+"to load is:- "+timer.duration()+" "+TimeUnit.MILLISECONDS);
		Constants.performanceData.add(timer.duration());
	}// isLoaded

	@Override
	protected void load() 
	{
		timer.start();
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}// load

	/**
	 * To Fill traveller details
	 * 
	 * @throws Exception
	 * @param: String 
	 */


	public void TravellerDetails(String name) throws Exception {
		Utils.waitForPageLoad(driver);
		WebElement Title = driver.findElement(By.cssSelector("select[id='paxtitle1']"));
		BrowserActions.clickOnElement(Title, driver, "Title");
		List<WebElement> Titles = driver.findElements(By.cssSelector("#paxtitle1>option"));
		if (Titles.size() != 0) {
			int rand = Utils.getRandom(2, Titles.size());
			BrowserActions.clickOnElement(Titles.get(rand), driver, "Title Selected");
		} // Random Title Selection
		Thread.sleep(5000);
		BrowserActions.typeOnTextField(txtBoxName, name, driver, "Guest Name");// Name
																				// From
																				// Regression
		Thread.sleep(5000);
		WebElement Age = driver.findElement(By.cssSelector("#paxage1"));
		Select age = new Select(Age);
		List<WebElement> Ages = age.getOptions();
		if (Ages.size() != 0) {
			int rand1 = Utils.getRandom(2, Ages.size());
			BrowserActions.nap(2);
			age.selectByIndex(rand1);
		}
	}

	/**
	 * To click On Continue
	 * 
	 * @return PaymentPageBus
	 * @throws Exception
	 */

	public PaymentPageBus clickOnContinueInTravellerPage() throws Exception {
		Utils.waitForElement(driver, btnContinue);
		BrowserActions.javascriptClick(btnContinue, driver, "Continue Button");
		return new PaymentPageBus(driver).get();
	}

	/**
	 * Getting the Error Text
	 *  
	 *  @throws Exception
	 * @return
	 *
	 */
	public String getTextErrorMsg() throws Exception {
		Utils.waitForElement(driver, txtErrorMsg);
		String txtDetails = txtErrorMsg.getText();
		return txtDetails;
	}

	/**
	 * Getting the Error Text From promo section
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTextErrorMsgPromo() throws Exception {
		String txtDetails = txtPromoMessage.getText();
		return txtDetails;
	}

	/**
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
	}

	/**
	 * To Get Mobile Number
	 * 
	 * @throws Exception
	 */
	public String getTextMobileNo() throws Exception {
		Utils.waitForElement(driver, fldUserMobileNo);
		String number = fldUserMobileNo.getAttribute("value");
		return number;
	}

	/**
	 * To verify checkBox in Traveller page
	 * 
	 * @throws Exception
	 */
	public boolean checkBoxFirst() throws Exception {
		boolean flag = false;
		String result = chkBoxAddonsFirst.getAttribute("type");
		if (result.equals("checkbox")) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;

	}
	/**
	 * To verify checkBox in Traveller page
	 * 
	 * @throws Exception
	 */

	public boolean checkBoxSecond() throws Exception {
		boolean flag = false;
		String result = chkBoxAddonsSecond.getAttribute("type");
		if (result.equals("checkbox")) {
			flag = true;
		} else {
			flag = false;
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
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyTnCPage() throws Exception {
		return BrowserActions.getText(driver, driver.findElement(By.cssSelector("div[class='ytAboutHanding txtDrkGreyI']")),
				"Getting Text from span").contains("Terms and Conditions");
	}/**
	 * To click Edit Mobile Link
	 * 
	 * @throws Exception
	 */
	public void enterPromoCode(String Promo) throws Exception {
		Utils.waitForElement(driver, txtFldPromoCode);
		BrowserActions.typeOnTextField(txtFldPromoCode, Promo, driver, "Yatra Promo Code");
		BrowserActions.clickOnElement(btnApply, driver, "Apply Button");
		Utils.waitForPageLoad(driver);
	}
	/**
	 * To click On Continue
	 * 
	 * @throws Exception
	 */

	public void clickOnContinue() throws Exception {
		Utils.waitForElement(driver, lnkTermAndCond);
		BrowserActions.clickOnElement(btnContinue, driver, "Continue Button");
	}

}// TravellerPage