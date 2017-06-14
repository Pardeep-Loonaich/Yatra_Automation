package com.Yatra.Pages;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class TravellerPage extends LoadableComponent<TravellerPage> {

	private String appURL;
	Utils utils;
	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();

	/**********************************************************************************************
	 ********************************* WebElements of TravellerPage Page ***********************************
	 **********************************************************************************************/

	@FindBy(css = "div[id='checkoutBase']>div:nth-child(3)>main>div>div[class='col-md-9']>form[name='travellerForm']")
	public WebElement formTraveller;

	@FindBy(xpath = "//*[@ng-repeat='traveller in travellerDetails']")
	List<WebElement> modTravellerDetails;

	@FindBy(css = "[id='redeem-applied-id']>h4")
	WebElement msgEcashRedeemBalance;

	@FindBy(css = "[id='redeem-applied-id']>h3")
	WebElement msgEcashRedeem;

	@FindBy(css = ".box-content.ssr-container.hide-under-overlay.ng-scope>div[class='button-group']>button:nth-child(1)")
	WebElement btnAddMeal;

	@FindBy(css = ".box-content.ssr-container.hide-under-overlay.ng-scope>div[class='button-group']>button:nth-child(2)")
	WebElement btnAddBaggage;
	
	@FindBy(xpath = ".//*[@id='checkoutBase']/div[3]/main/div/div/form/div[3]/button")
	WebElement btnContinueTravellerPage; //TODO 

	@FindBy(css = "button[class='button grey-btn rounded sleek-btn ng-binding']")
	WebElement btnChngeFlight;

	@FindBy(css = "	div[id='traveller-dom']>div[id='ssrContainer']>div[ng-controller='productSSRController']>div:nth-child(3)>div[class='col-xs-8 col-md-4 ssr-trip ng-scope']>div>div>ul>li:nth-child(2)>span>select")
	WebElement drpSelectMeal;

	@FindBy(css = "	div[id='traveller-dom']>div[id='ssrContainer']>div[ng-controller='productSSRController']>div:nth-child(3)>div[class='col-xs-8 col-md-4 ssr-trip ng-scope']>div>div>ul>li:nth-child(2)>span>select>option:nth-child(2)")
	WebElement fldContentselectMeal;
	
	/*@FindBy(xpath = ".//*[@id='ssrContainer']/div[2]/div[2]/div[5]/div/div/ul/li[2]/span/select")
	WebElement drpAddBaggage;*/
	
	/*@FindBy(xpath = ".//*[@id='ssrContainer']/div[2]/div[2]/div[5]/div/div/ul/li[2]/span/select/option[2]")
	WebElement fldContentselectBaggage;*/

	@FindBy(css = "ul[class='list list-border']>li:nth-child(6)>span[class='pull-right tr alignment']>a[class='remove-btn']")
	WebElement btnRemoveBaggage;

	@FindBy(css = "ul[class='list list-border']>li:nth-child(5)")
	WebElement mealDetails;

	@FindBy(css = "ul[class='list list-border']>li:nth-child(6)")
	WebElement BaggageDetails;

	@FindBy(css = "ul[class='list list-border']>li:nth-child(5)>span[class='pull-right tr alignment']>a[class='remove-btn']")
	WebElement btnRemoveMeal;
	
	@FindBy(xpath = ".//*[@id='traveller-dom']/div[3]/div/div/div[2]/p/label/span[1]/input")
	WebElement chkInsurance; //TODO 

	@FindBy(css = "[id='checkoutBase']>div:not([class])>main>div>aside>div[class='box ng-scope']>div[class='box-content hide-under-overlay']>div>ul[class='list list-border']")
	private WebElement contentFareDetails;
	
	@FindBy(css = "#title0")
	WebElement drpTravelTitle;

	/**********************************************************************************************
	 ********************************* WebElements of TravellerPage Page - Ends ****************************
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

	public TravellerPage(WebDriver driver, String url) {
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
	public TravellerPage(WebDriver driver) {
		Utils.waitForPageLoad(driver);
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		elementLayer = new ElementLayer(driver);
	}

	@Override
	protected void isLoaded() {
		timer.end();
		if (!isPageLoaded) {
			Assert.fail();
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isPageLoaded && !(Utils.waitForElement(driver, formTraveller))) {
			Log.fail("TravellerPage didn't open up", driver);
		}
		Log.message("Total time taken by #"+this.getClass().getTypeName()+"to load is:- "+timer.duration()+" "+TimeUnit.MILLISECONDS);
		Constants.performanceData.add(timer.duration());
		// elementLayer = new ElementLayer(driver);

	}

	@Override
	protected void load() {
		timer.start();

		isPageLoaded = true;

		Utils.waitForPageLoad(driver);
	}// load
	/**
	 * To fill Traveller details -- International 
	 * 
	 * @throws Exception
	 */
		public void fillTravellerDetails_INT(String[] Adult, String[] Child, String[] Infant) throws Exception {
		int adult = 1; int child = 1; int infant = 1; int passengerNum = 1;

		String[] adultDOB = selectDOBDate(Adult) ;  // Remove later
		String[] childDOB = selectDOBDate(Child);
		String[] InfantDOB = selectDOBDate(Infant); 

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
		int infant = 1;	int passengerNum = 1;		
		for (int i = 0; i < modTravellerDetails.size(); i++) {
			String formPaxDetail = "//*[@id='paxNum" + i + "']/div[@class='col-md-1 col-xs-3 min-width70']";
			WebElement lblTraveller = driver.findElement(By.xpath(" //*[@id='paxNum" + i + "']/div[@class='col-xs-12 col-md-1 min-wid52 ng-binding']"));
			WebElement Firstname = driver.findElement(By.xpath("//*[@id='paxNum" + i + "']/div[@class='col-md-3 col-xs-offset-3 col-md-offset-0']/div/input"));
			WebElement Lastname = driver.findElement(By.xpath("//*[@id='paxNum" + i + "']/div[@class='col-md-3 col-xs-offset-3 col-md-offset-0']/input"));

			WebElement drptitle = driver.findElement(By.xpath(formPaxDetail)); 
			//BrowserActions.clickOnElement(drptitle, driver, "Title Dropdown Clicked");  // Issues on FF, @Narayana
			String label = BrowserActions.getText(driver, lblTraveller, "Traveller label");

			List<WebElement> titleOptions = driver.findElements(By.xpath("//*[@id='paxNum" + i + "']/div[@class='col-md-1 col-xs-3 min-width70']/span[@class='ui-select']/select/option"));
			if (titleOptions.size() != 0) {
				int rand = Utils.getRandom(1, titleOptions.size());
				Utils.waitForElement(driver, titleOptions.get(rand));
				 //BrowserActions.clickOnElement(titleOptions.get(rand), driver, "title selected");	 // Issues on FF, @Narayana			
				BrowserActions.selectDropdownByIndex(driver, drpTravelTitle, rand, "Title selected");
				Thread.sleep(1000);
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
				String[] InfantDOB = selectDOBDate(Infant);  

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
	/**
	 * Getting the ecash redeem successful messages
	 * 
	 * @return
	 * @throws Exception
	 */

	public String getMsgFromEcashRedeemSuccess() throws Exception {
		String txtRedeemMsg = BrowserActions.getText(driver, msgEcashRedeem, "Getting text for ecash Redeem message.")
				.replace("Cancel Redemption", "");
		return txtRedeemMsg;

	}

	/**
	 * Getting the ecash redeem Balance successful messages
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getMsgFromEcashBalanceDeduction() throws Exception {
		String txtRedeemMsg = BrowserActions.getText(driver, msgEcashRedeemBalance,
				"Getting text for ecash Redeem Balance deduction message.");
		return txtRedeemMsg;

	}

	public PaymentPage clickOnContinue() throws Exception {
		BrowserActions.nap(6);
		Utils.waitForElement(driver, btnContinueTravellerPage);
		BrowserActions.scrollToView(btnContinueTravellerPage, driver);
		BrowserActions.javascriptClick(btnContinueTravellerPage, driver, "Continue Button");
		Utils.waitForPageLoad(driver);
		return new PaymentPage(driver);
	}

	public void clickOnAddMeal() throws Exception {
		Utils.waitForElement(driver, btnAddMeal);
		BrowserActions.scrollToView(btnAddMeal, driver);
		BrowserActions.javascriptClick(btnAddMeal, driver, "Add Meal Button");
		Utils.waitForPageLoad(driver);
	}

	public void clickOnAddBaggage() throws Exception {
		Utils.waitForElement(driver, btnAddBaggage);
		BrowserActions.scrollToView(btnAddBaggage, driver);
		BrowserActions.javascriptClick(btnAddBaggage, driver, "Add Baggage Button");
		Utils.waitForPageLoad(driver);
	}

	public void selectBaggage() throws Exception {
        String css = ".box-content.ssr-container.hide-under-overlay.ng-scope>div[class='row ui-ssr ng-scope']>div[class='col-xs-8 col-md-4 ssr-trip ng-scope']>div>div>ul>li[class='ng-scope']>span";
        WebElement Baggage = driver.findElement(By.cssSelector(css));
        BrowserActions.clickOnElement(Baggage, driver, "Baggage");
        List<WebElement> Baggages = driver.findElements(By.cssSelector(".box-content.ssr-container.hide-under-overlay.ng-scope>div[class='row ui-ssr ng-scope']>div[class='col-xs-8 col-md-4 ssr-trip ng-scope']>div>div>ul>li[class='ng-scope']>span>select>option"));
        if (Baggages.size() != 0) {
               int rand = Utils.getRandom(1, Baggages.size());
               BrowserActions.clickOnElement(Baggages.get(rand), driver, "Baggage Selected");
               Utils.waitForPageLoad(driver);
        }
 }


 public void selectMeal() throws Exception {
        String css = ".box-content.ssr-container.hide-under-overlay.ng-scope>div[class='row ui-ssr ng-scope']>div[class='col-xs-8 col-md-4 ssr-trip ng-scope']>div>div>ul>li:nth-child(2)>span[class='ui-select']";
        WebElement Meal = driver.findElement(By.cssSelector(css));
        BrowserActions.clickOnElement(Meal, driver, "Meal");
        List<WebElement> Meals = driver.findElements(By.cssSelector(".box-content.ssr-container.hide-under-overlay.ng-scope>div[class='row ui-ssr ng-scope']>div[class='col-xs-8 col-md-4 ssr-trip ng-scope']>div>div>ul>li:nth-child(2)>span[class='ui-select']>select>option"));
        if (Meals.size() != 0) {
               int rand = Utils.getRandom(2, Meals.size());
               BrowserActions.clickOnElement(Meals.get(rand), driver, "Meal Selected");
               Utils.waitForPageLoad(driver);
        }
 }
	public void clickOnRemoveBaggageButton() throws Exception {
		BrowserActions.javascriptClick(btnRemoveBaggage, driver, "Remove Baggage Button");
	}

	/**
	 * Getting meal fare details
	 * 
	 * @return
	 * @throws Exception
	 */

	public String getTextMealDetails() throws Exception {
		String txtDetails = BrowserActions.getText(driver, mealDetails, "Getting the Meal fare details.");
		return txtDetails;

	}

	/**
	 * Getting Baggage fare details
	 * 
	 * @return
	 * @throws Exception
	 */

	public String getTextBaggageDetails() throws Exception {
		String txtDetails = BrowserActions.getText(driver, BaggageDetails, "Getting the Baggage fare details.");
		return txtDetails;

	}

	/**
	 * clicking on Remove Meal Button
	 * 
	 * @throws Exception
	 */
	public void clickOnRemoveMealButton() throws Exception {
		BrowserActions.javascriptClick(btnRemoveMeal, driver, "Remove Meal Button");
	}

	/**
	 * verifying insurance checbox is checked on unchecked
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyInsuranceCheckbox() throws Exception {
		boolean status = BrowserActions.isRadioOrCheckBoxSelected(chkInsurance);
		return status;
	}

	/**
	 * clicking on insurance checkbox
	 * 
	 * @throws Exception
	 */
	public void uncheckingInsuranceCheckbox() throws Exception {
		//@Harveer remove this absolute locator and keep all locator at top and it by using @FindByAnnotation
		
		WebElement e = driver.findElement(By.xpath(".//*[@id='traveller-dom']/div[3]/div/div/div[2]/p/label"));
		BrowserActions.clickOnElement(e, driver, "Clicked on Insurance CheckBox.");
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
}