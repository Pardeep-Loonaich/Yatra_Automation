package com.Yatra.Pages;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.select.Evaluator.ContainsOwnText;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class ReviewPage extends LoadableComponent<ReviewPage> {

	private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	Utils utils;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra ReviewPage ***********************************
	 **********************************************************************************************/

	@FindBy(css = "button[class='button grey-btn rounded sleek-btn ng-binding']")
	public WebElement btnChngeFlight;

	@FindBy(css = "[id='checkoutBase']>div:nth-child(3)>main>div>aside>div[ng-controller='productFareDetailsController']")
	WebElement moduleFareDetails;

	@FindBy(xpath = "//i[@class = 'arrow-down']")
	WebElement drpPromoCode;

	@FindBy(xpath = "//ul[@class = 'promo-options']/li/label/span/span[@class='promo-key ng-binding']")
	WebElement fldContentpromo;

	@FindBy(xpath = "//ul[@class = 'promo-options']/li[@class='ng-scope']")
	WebElement lnkPromoCoupon;

	@FindBy(xpath = ".//*[@id='checkoutBase']/div[3]/main/div/div/form/div[3]/button")
	WebElement btnContinueReviewPage;

	@FindBy(xpath = ".//*[@id='travellerf0']")
	WebElement userFirstName;

	@FindBy(xpath = ".//*[@id='travellerl0']")
	WebElement userSecondName;

	@FindBy(css = ".col-md-1.col-xs-3.min-width70>span[class='ui-select']>select>option:nth-child(2)")
	WebElement userTitle;

	@FindBy(xpath = ".//*[@id='paxNum0']/div[2]/span[1]")
	WebElement drpuserTitle;

	@FindBy(xpath = ".//*[@id='traveller-dom']/div[1]/div[1]/div/article[2]/div[2]/input")
	WebElement userEmail;

	@FindBy(css = "span[class='pull-left cursor-pointer ng-binding under-link']>a")
	WebElement lnkFeeSurchrge;

	@FindBy(css = "span[class='pull-left w85perc ng-binding']")
	WebElement msgPromoApplied;

	@FindBy(css = "span[ng-show='selectPromo && promoListEnable']")
	WebElement lnkHavePromoCode;

	@FindBy(xpath = "//input[@id='promoListInput']")
	WebElement txtPromoCode;

	@FindBy(css = "button[ng-show='!selectPromo']")
	WebElement btnApply;

	@FindBy(css = "[ng-click='showFareRulesPopup()']")
	WebElement lnkFareRules;

	@FindBy(css = "div[class='fareBox']>ul[class='list review-title']")
	WebElement moduleFeeSurchrge;

	@FindBy(xpath = "//div[@ng-show='showLoginRegister']/form/div[@class='row']/button")
	WebElement btnBookAsGuest;

	@FindBy(xpath = "//div[@ng-show='showLoginRegister']/form/div[@class='row']/input[@name = 'email']")
	WebElement txtGuestEmail;

	@FindBy(xpath = "//div[@id='regUserDiv']/input[@type='password']")
	WebElement txtGuestPassword;

	@FindBy(css = ".update-fare.pt10.ico-right")
	WebElement PricePopUp;

	@FindBy(css = ".overlay-content>p[class='text-center']>button[class='button primary rounded']")
	WebElement ContinueInPopUp;

	@FindBy(css="div[ng-show='priceChangeDiv']>div[class='overlay modal-new']>div[class='overlay-content ']")
	WebElement popupFareChange;
	
	@FindBy(css="	button[class='button primary rounded pull-right']")
	WebElement ContinueInFareChangeAlertPopUp;

	@FindBy(css = " div[class='center-block text-center mt-1 mb-1 sticky-sm-bottom hide-under-overlay']>button[ng-disabled='isContinueBtnDisabled']")
	WebElement ContinueInTravellerPage;

	@FindBy(css = "div[class='align-width']>input[name='mobile']")
	WebElement txtGuestMobile;

	@FindBy(xpath = "//span[@class='account-msg ui-checkbox']")
	WebElement chkExistingUser;

	@FindBy(css = "[id='checkoutBase']>div:not([class])>main>div>aside>div[class='box ng-scope']>div[class='box-content hide-under-overlay']>div>ul[class='list list-border']")
	WebElement contentFareDetails;

	@FindBy(xpath= "//*[@ng-repeat='traveller in travellerDetails']")
	List<WebElement> modTravellerDetails ;
	
	
    @FindBy(css = "div[class='fareruleContainer overlay-holder']>div>div[class='overlay-content']")
	WebElement moduleFareRules;

	/**********************************************************************************************
	 ********************************* WebElements of Yatra ReviewPage - Ends ****************************
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

	public ReviewPage(WebDriver driver, String url) {
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
	public ReviewPage(WebDriver driver) {
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

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, btnChngeFlight))) {
			Log.fail("ReviewPage didn't open up", driver);
		}
		// elementLayer = new ElementLayer(driver);
	}

	@Override
	protected void load() {
		isPageLoaded = true;

		Utils.waitForPageLoad(driver);
	}// load

	/**
	 * To click Book as Guest button on Review Page
	 * 
	 * @throws Exception
	 */

	public void clickOnBookAsGuestBtn() throws Exception {
		BrowserActions.scrollToView(btnBookAsGuest, driver);
		BrowserActions.clickOnElement(btnBookAsGuest, driver, "To click on Book as Guest button.");
	}

	/**
	 * Enter email id
	 * 
	 * @param emailid
	 *            as string
	 * @throws Exception
	 */
	public void enterEmailID(String emailid) throws Exception {
		Utils.waitForElement(driver, txtGuestEmail);
		BrowserActions.typeOnTextField(txtGuestEmail, emailid, driver, "Email id");
		Log.event("Entered the Email Id: " + emailid);
	}

	/**
	 * Enter mobile
	 * 
	 * @param mobile
	 *            as string
	 * @throws Exception
	 */
	public void enterMobile(String mobile) throws Exception {
		Utils.waitForElement(driver, txtGuestMobile);
		BrowserActions.typeOnTextField(txtGuestMobile, mobile, driver, "Mobile");
		Log.event("Entered the Mobile no: " + mobile);
	}

	/**
	 * Enter mobile
	 * 
	 * @param mobile
	 *            as string
	 * @throws Exception
	 */
	public void enterPassword(String password) throws Exception {
		Utils.waitForElement(driver, txtGuestPassword);
		BrowserActions.typeOnTextField(txtGuestPassword, password, driver, "Password");
		Log.event("Entered the Password : " + password);
	}

	public TravellerPage loginYatraGuestAccount(String emailId, String mobile) throws Exception {
		enterEmailID(emailId); // enter email address
		enterMobile(mobile); // enter mobile no
		clickOnBookAsGuestBtn(); // click Login button
		return new TravellerPage(driver).get();

	}

	public TravellerPage loginYatraGuestAccountExisting(String emailId, String password) throws Exception {
		enterEmailID(emailId); // enter email address
		enterPassword(password); // enter password
		clickOnBookAsGuestBtn(); // click Login button
		return new TravellerPage(driver).get();
	}

	/**
	 * verifying ExistingUse checkbox is checked on unchecked
	 * 
	 * @return
	 * @throws Exception
	 */

	public boolean verifyExistingUser() throws Exception {
		boolean status = BrowserActions.isRadioOrCheckBoxSelected(chkExistingUser);
		return status;
	}

	/**
	 * clicking on ExistingUser checkbox
	 * 
	 * @throws Exception
	 */
	public void clickOnExistingUser() throws Exception {
		BrowserActions.clickOnElement(chkExistingUser, driver, "Clicked on Existing User CheckBox.");
	}

	/**
	 * To click promo drop down on Review page
	 * 
	 * @throws Exception
	 */

	public void clickOnPromoDrpDwn() throws Exception {
		BrowserActions.scrollToView(drpPromoCode, driver);
		BrowserActions.clickOnElement(drpPromoCode, driver, "To click on Promo code Dropdown.");
	}

	/**
	 * To click promo drop down on Review page fetch coupon text from the
	 * dropdown submit the received coupon and click on Apply button on Review
	 * page
	 * 
	 * @throws Exception
	 */

	public boolean getPromoCode() throws Exception {
		BrowserActions.scrollToView(drpPromoCode, driver);
		BrowserActions.clickOnElement(drpPromoCode, driver, "To click on Promo code Dropdown.");
		String promoCode = BrowserActions.getText(driver, fldContentpromo, "Promo Code Content");
		BrowserActions.clickOnElement(lnkHavePromoCode, driver, "To click on Hava a Promo code link.");
		BrowserActions.typeOnTextField(txtPromoCode, promoCode, driver, "Entered Applicable Promo Code");
		BrowserActions.clickOnElement(btnApply, driver, "To click on Apply button.");
		String promoAppliedMessage = BrowserActions.getText(driver, msgPromoApplied,
				"Successful Promo Code applied message");
		promoAppliedMessage.startsWith("Congrats");
		return true;
	}

	/**
	 * To click promo drop down on Review page click on a coupon from the
	 * dropdown on Review page
	 * 
	 * @throws Exception
	 */

	public boolean clickOnPromoCoupon() throws Exception {
		BrowserActions.scrollToView(drpPromoCode, driver);
		BrowserActions.clickOnElement(drpPromoCode, driver, "To click on Promo coupon link.");
		BrowserActions.clickOnElement(lnkPromoCoupon, driver, "To click on Promo Coupon");
		String promoAppliedMessage = BrowserActions.getText(driver, msgPromoApplied,
				"Successful Promo Code applied message");
		promoAppliedMessage.startsWith("Congrats");
		return true;
	}

	/**
	 * To click Have a promo code link on Review page
	 * 
	 * @throws Exception
	 */

	public void clickOnHavePromoCode() throws Exception {
		BrowserActions.scrollToView(lnkHavePromoCode, driver);
		BrowserActions.clickOnElement(lnkHavePromoCode, driver, "To click on Hava a Promo code link.");

	}

	public TravellerPage clickOnContinue() throws Exception {
		BrowserActions.nap(6);
		Utils.waitForElement(driver, btnContinueReviewPage);
		BrowserActions.scrollToView(btnContinueReviewPage, driver);
		BrowserActions.javascriptClick(btnContinueReviewPage, driver, "Continue Button");
		Utils.waitForPageLoad(driver);
		return new TravellerPage(driver).get();
	}

	/**
	 * to click on View fare rules link in fare details module
	 * 
	 * @throws Exception
	 */
	public void clickOnFareRulesLink() throws Exception {
		BrowserActions.javascriptClick(lnkFareRules, driver, "Clicked on View Fare Rules link.");
	}

	/**
	 * to click on Fee & surcharge link in fare detail module
	 * 
	 * @throws Exception
	 */

	public void clickOnFeeSurchrgeLink() throws Exception {
		BrowserActions.javascriptClick(lnkFeeSurchrge, driver, "Clicked on Fees & Surcharge link.");
	}

	/**
	 * Clicking Continue In Price Increase Pop Up
	 * 
	 * @return
	 * @throws Exception
	 */

	public void popUpAppear() throws Exception {
		if (PricePopUp.isDisplayed()) {
				BrowserActions.clickOnElement(ContinueInPopUp, driver, "Clicked on continue in Popup");
			}
		
		else if (popupFareChange.isDisplayed()) {
				BrowserActions.clickOnElement(ContinueInFareChangeAlertPopUp, driver, "Clicked on continue in Fare Change Alert Popup");
			}
		
	}

		
		/**
		 * Getting the text from the fare details panel
		 * 
		 * @return
		 * @throws Exception
		 */

		public String getTextFromFareDetails() throws Exception {
			String txtDetails = BrowserActions.getText(driver, contentFareDetails, "Getting text from the fare details.");
			return txtDetails;

		}

		/**
		 * To fill Traveller details -- International 
		 * 
		 * @throws Exception
		 */
		// public void fillTravellerDetails_INT(String[] adultDOB, String[], childDOB, String[] InfantDOB ) throws Exception {
		public void fillTravellerDetails_INT() throws Exception {
			int adult = 1; int child = 1; int infant = 1; int passengerNum = 1;

			String[] adultDOB = { "02 Apr 2011", "02 Apr 2012" };   // Remove later
			String[] childDOB = { "02 Apr 2013" };
			String[] InfantDOB = { "02 Apr 2014" };

			for (int i = 0; i < modTravellerDetails.size(); i++) {
				String formPaxDetail = "//*[@id='paxNum" + i + "']/div[@class='col-md-1 col-xs-3 min-width70']";
				WebElement lblTraveller = driver.findElement(By.xpath(" //*[@id='paxNum" + i + "']/div[@class='col-xs-12 col-md-1 min-wid52 ng-binding']"));
				WebElement Firstname = driver.findElement(By.xpath("//*[@id='paxNum" + i + "']/div[@class='col-md-3 col-xs-offset-3 col-md-offset-0']/div/input"));
				WebElement Lastname = driver.findElement(By.xpath("//*[@id='paxNum" + i + "']/div[@class='col-md-3 col-xs-offset-3 col-md-offset-0']/input"));

				WebElement drptitle = driver.findElement(By.xpath(formPaxDetail));
				BrowserActions.clickOnElement(drptitle, driver, "Title Dropdown Clicked");
				String label = BrowserActions.getText(driver, lblTraveller, "Traveller label");

				List<WebElement> titleOptions = driver.findElements(By.xpath("//*[@id='paxNum" + i + "']/div[@class='col-md-1 col-xs-3 min-width70']/span[@class='ui-select']/select/option"));
				if (titleOptions.size() != 0) {
					int rand = Utils.getRandom(1, titleOptions.size());
					Utils.waitForElement(driver, titleOptions.get(rand));
					BrowserActions.clickOnElement(titleOptions.get(rand), driver, "title selected");
					Utils.waitForPageLoad(driver);
				}

				String randomFirstName = RandomStringUtils.randomAlphabetic(5).toLowerCase();
				String randomLastName = RandomStringUtils.randomAlphabetic(5).toLowerCase();

				// enter First Name with random string
				BrowserActions.typeOnTextField(Firstname, randomFirstName, driver, "First Name");			
				Log.event("Successfully entered Passenger" + passengerNum + " FirstName: " + randomFirstName);

				// enter Last Name with random string
				BrowserActions.typeOnTextField(Lastname, randomLastName, driver, "Last Name");
				Log.event("Successfully entered Passenger" + passengerNum + " Last Name: " + randomLastName);

				// select the Passenger DOB's
				if (label.startsWith("Adult")) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					String adultDOBDate = "document.querySelector(\"input#Adult_" + adult + "_dob\").value='"+ adultDOB[adult - 1] + "'";
					js.executeScript(adultDOBDate);
					Thread.sleep(1000);
					Log.event("Successfully selected Adult" + adult + " DOB: " + adultDOB[adult - 1]);
					adult++;
				} else if (label.startsWith("Child")) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					String childDOBDate = "document.querySelector(\"input#Child_" + child + "_dob\").value='" + childDOB[child - 1] + "'";
					js.executeScript(childDOBDate);
					Thread.sleep(1000);
					Log.event("Successfully selected Child" + child + " DOB: " + childDOB[child - 1]);
					child++;
				} else if (label.startsWith("Infant")) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					String infantDOBDate = "document.querySelector(\"input#Infant_" + infant + "_dob\").value='" + InfantDOB[infant - 1] + "'";
					js.executeScript(infantDOBDate);
					Thread.sleep(1000);
					Log.event("Successfully selected Infant" + infant + " DOB: " + InfantDOB[infant - 1]);
					infant++;
				}
				Thread.sleep(1000);
				passengerNum++;
			}
		}

	/**
	 * To fill Traveller details -- Domestic 
	 * 
	 * @throws Exception
	 */
    public void fillTravellerDetails_DOM(String[] Infant) throws Exception {	
    	// Infant DOB dates 
    	String[] InfantDOB = selectDOBDate(Infant);  
    	
		int infant = 1;	int passengerNum = 1;		
		for (int i = 0; i < modTravellerDetails.size(); i++) {
			String formPaxDetail = "//*[@id='paxNum" + i + "']/div[@class='col-md-1 col-xs-3 min-width70']";
			WebElement lblTraveller = driver.findElement(By.xpath(" //*[@id='paxNum" + i + "']/div[@class='col-xs-12 col-md-1 min-wid52 ng-binding']"));
			WebElement Firstname = driver.findElement(By.xpath("//*[@id='paxNum" + i + "']/div[@class='col-md-3 col-xs-offset-3 col-md-offset-0']/div/input"));
			WebElement Lastname = driver.findElement(By.xpath("//*[@id='paxNum" + i + "']/div[@class='col-md-3 col-xs-offset-3 col-md-offset-0']/input"));

			WebElement drptitle = driver.findElement(By.xpath(formPaxDetail));
			BrowserActions.clickOnElement(drptitle, driver, "Title Dropdown Clicked");
			String label = BrowserActions.getText(driver, lblTraveller, "Traveller label");

			List<WebElement> titleOptions = driver.findElements(By.xpath("//*[@id='paxNum" + i + "']/div[@class='col-md-1 col-xs-3 min-width70']/span[@class='ui-select']/select/option"));
			if (titleOptions.size() != 0) {
				int rand = Utils.getRandom(1, titleOptions.size());
				Utils.waitForElement(driver, titleOptions.get(rand));
				BrowserActions.clickOnElement(titleOptions.get(rand), driver, "title selected");
				Utils.waitForPageLoad(driver);
			}
				String randomFirstName = RandomStringUtils.randomAlphabetic(5).toLowerCase();
				String randomLastName = RandomStringUtils.randomAlphabetic(5).toLowerCase();

				// enter First Name with random string
				BrowserActions.typeOnTextField(Firstname, randomFirstName, driver, "First Name");
				Log.event("Successfully entered Passenger" + passengerNum + " FirstName: " + randomFirstName);

				// enter Last Name with random string
				BrowserActions.typeOnTextField(Lastname, randomLastName, driver, "Last Name");
				Log.event("Successfully entered Passenger" + passengerNum + " Last Name: " + randomLastName);

				// select the Passenger DOB's
				if (label.startsWith("Infant")) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					String infantDOBDate = "document.querySelector(\"input#Infant_" + infant + "_dob\").value='" + InfantDOB[infant - 1] + "'";
					js.executeScript(infantDOBDate);
					Thread.sleep(1000);
					Log.event("Successfully selected Infant" + infant + " DOB: " + InfantDOB[infant - 1]);
					infant++;
				}
				Thread.sleep(1000);
				passengerNum++;
			}
		}
    
    
    /**
	 * To select DOB date
	 * 
	 * @throws Exception
	 */
	
	public String[] selectDOBDate(String[] DOB) throws Exception {
		String[] DOBDates = new String[DOB.length] ;
		for (int i = 0; i < DOB.length; i++) {
			int iDay = Integer.parseInt(DOB[i]);
			String date = utils.dateGenerator_DOB("dd MMM yyyy", iDay);
			Log.event("DOB Date: " + date); 
			DOBDates[i] = date;						
		}
		return DOBDates;
	}

} //ReviewPage
