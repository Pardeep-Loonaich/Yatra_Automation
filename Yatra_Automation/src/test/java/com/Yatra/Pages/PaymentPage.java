package com.Yatra.Pages;

import java.sql.Driver;
import java.util.List;

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
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class PaymentPage extends LoadableComponent<PaymentPage> {

	private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Search Page ***********************************
	 **********************************************************************************************/
	@FindBy(xpath = "//input[@id= 'payNow']")
	public WebElement btnPayNow;
	
	
	@FindBy(css = "#cc_cno_id")
	 WebElement creditCardNumber;
	
	@FindBy(css = "#cc_cardholder_name_id")
	 WebElement creditCardName;
	
	@FindBy(css = "#cc_cvv_id")
	 WebElement creditCardCvv;
	
//	@FindBy(css = "#cc_expm_id")
//	 WebElement drpcreditCardExpiredate;
//	
//	@FindBy(css = "#cc_expy_id")
//	 WebElement drpcreditCardExpireYear;
//	
//	@FindBy(css = "#cc_expm_id>option")
//	List <WebElement> lstcreditCardExpireMonth;
//	
//	@FindBy(css = "#cc_expy_id>option")
//	List <WebElement> lstcreditCardExpireYear;
	
	@FindBy(css = "#dc")
	WebElement lnkDebitCard;
	
	@FindBy(css = "#dc_cno_id")
	 WebElement debitCardNumber;
	
	@FindBy(css = "#dc_cardholder_name_id")
	 WebElement debitCardName;
	
	@FindBy(css = "#dc_cvv_id")
	 WebElement debitCardCvv;
	
	@FindBy(css = "#toater_20")
	 WebElement popUpInvalidCardNumber;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra Search Page - Ends ****************************
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

	public PaymentPage(WebDriver driver, String url) {
		appURL = url;
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}// SearchPage

	/**
	 * 
	 * @param driver
	 *            : webdriver
	 */
	public PaymentPage(WebDriver driver) {
		Utils.waitForPageLoad(driver);
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		elementLayer = new ElementLayer(driver);
	}

	@Override
	protected void isLoaded() {
		if (!isPageLoaded) {
			Assert.fail();
		}


		if (isPageLoaded && !(Utils.waitForElement(driver, btnPayNow))) {
			Log.fail("Search Result page didn't open up", driver);
		}
		// elementLayer = new ElementLayer(driver);
	}

	@Override
	protected void load() {
		isPageLoaded = true;

		Utils.waitForPageLoad(driver);
	}// load
	
	
	
	/**
     * Filling Credit Card Details
     * @return
     * @throws Exception
     */
	public void enterCreditCardDetails() throws Exception {
		String css = "#cc_expm_id";
		WebElement month = driver.findElement(By.cssSelector(css));
		BrowserActions.clickOnElement(month, driver, "Date");
		List<WebElement> months = driver.findElements(By.cssSelector("#cc_expm_id>option"));
		if (months.size() != 0) {
			int rand = Utils.getRandom(1, months.size());
			BrowserActions.clickOnElement(months.get(rand), driver, "Month Selected");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);
		String css1 = "#cc_expy_id";
		WebElement year = driver.findElement(By.cssSelector(css1));
		BrowserActions.clickOnElement(year, driver, "Year");
		List<WebElement> year1 = driver.findElements(By.cssSelector("#cc_expy_id>option"));
		if (year1.size() != 0) {
			int rand = Utils.getRandom(1, year1.size());
			BrowserActions.clickOnElement(year1.get(rand), driver, "Year Selected");
			Utils.waitForPageLoad(driver);
		}
		Thread.sleep(2000);
		String randomNumber = RandomStringUtils.randomNumeric(10);
		String randomName = RandomStringUtils.randomAlphabetic(7).toLowerCase();
		String randomCvv = RandomStringUtils.randomNumeric(3);
		BrowserActions.typeOnTextField(creditCardNumber, randomNumber, driver, "Credit card Number");
		BrowserActions.typeOnTextField(creditCardName, randomName, driver, "Credit card Name");
		BrowserActions.typeOnTextField(creditCardCvv, randomCvv, driver, "Credit card Cvv");

	}
	/**
     * Clicking on Pay Now In Payment Page
     * 
     * @throws Exception
     */
	
	public void clickOnPayNow() throws Exception {
		BrowserActions.scrollToView(btnPayNow, driver);
		BrowserActions.javascriptClick(btnPayNow, driver, "Pay Now");
		Utils.waitForPageLoad(driver);
	}
	/**
     * Getting the text from the Error Pop Up In Credit card
     * @return
     * @throws Exception
     */
    public String getTextErrorMessage() throws Exception{
    	String txtDetails = BrowserActions.getText(driver, popUpInvalidCardNumber, "Getting text from the Credit card Error message");
    	return txtDetails;
    	
    }

/**
 * Clicking on Debit Card Link
 * 
 * @throws Exception
 */

public void clickOnDebitCardLink() throws Exception {
	BrowserActions.scrollToView(lnkDebitCard, driver);
	BrowserActions.javascriptClick(lnkDebitCard, driver, "Debit Card Link");
	Utils.waitForPageLoad(driver);
}
/**
 * Filling Debit Card Details
 * @return
 * @throws Exception
 */
public void enterDebitCardDetails() throws Exception {
	clickOnDebitCardLink();
	String css = "#dc_expm_id";
	WebElement month = driver.findElement(By.cssSelector(css));
	BrowserActions.clickOnElement(month, driver, "Month");
	List<WebElement> months = driver.findElements(By.cssSelector("#dc_expm_id>option"));
	if (months.size() != 0) {
		int rand = Utils.getRandom(1, months.size());
		BrowserActions.clickOnElement(months.get(rand), driver, "Month Selected");
		Utils.waitForPageLoad(driver);
	}
	Thread.sleep(2000);
	String css1 = "#dc_expy_id";
	WebElement Year = driver.findElement(By.cssSelector(css1));
	BrowserActions.clickOnElement(Year, driver, "Year");
	List<WebElement> Year1 = driver.findElements(By.cssSelector("#dc_expy_id>option"));
	if (Year1.size() != 0) {
		int rand = Utils.getRandom(1, Year1.size());
		BrowserActions.clickOnElement(Year1.get(rand), driver, "Year Selected");
		Utils.waitForPageLoad(driver);
	}
	Thread.sleep(2000);
	String randomNumber = RandomStringUtils.randomNumeric(10);
	String randomName = RandomStringUtils.randomAlphabetic(7).toLowerCase();
	String randomCvv = RandomStringUtils.randomNumeric(3);
	BrowserActions.typeOnTextField(debitCardNumber, randomNumber, driver, "Debit card Number");
	BrowserActions.typeOnTextField(debitCardName, randomName, driver, "Debit card Name");
	BrowserActions.typeOnTextField(debitCardCvv, randomCvv, driver, "Debit card Cvv");

}
	
	

}
				
