package com.Yatra.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

public class TrainTravellerPage extends LoadableComponent<TrainTravellerPage> {

	private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;


	/**********************************************************************************************
	 ********************************* WebElements of Yatra TrainTravellerPage ***********************************
	 **********************************************************************************************/

	@FindBy(css = "#paxContainer")
	private WebElement divPaxContainer;

	@FindBy(css = "i[class='yatraFb']")
	private WebElement lnkFB;

	@FindBy(css = "#guest_email")
	private WebElement txtEmail;

	@FindBy(css = "#guest_mobile")
	private WebElement txtMobile;
	
	@FindBy(css = "#login_password")
	private WebElement txtPassword;
	
	@FindBy(css="div[id='guestLoginDiv']>ul>li[class='checkAcc']>label")
	private WebElement chkSignedAsLogin;	
	
	@FindBy(css = "#userContinue")
	private WebElement btnContinueAsGuest;
	
	@FindBy(css = "input[value='Proceed to Book']")
	private WebElement btnProccedAsSignedUser;
	
	@FindBy(css="#paxSubmit")
	private WebElement btnContinue;
	
	@FindBy(css="div[id='insurance']>div[class*='yt-rbtn']>span")
	private WebElement chkInsuranceAccepted;
	
	@FindBy(css="label[for='termsCond']")
	private WebElement chkBookingPolicy;
	
	@FindBy(css="#irctcRUserId")
	private WebElement txtIrctcId;	
	
	@FindBy(css="#irctcLink")
	private WebElement btnSubmit;
	
	@FindBy(css="div[class='alert-irctc-msgs']")
	private WebElement popupInvalidIrctcId;
	
	@FindBy(css="div[class='alert-irctc-msgs']>div")
	private WebElement msgInvalidIrctcId;
	
	@FindBy(css="div[class='alert-irctc-msgs']>span")
	private WebElement closePopupInvalidIrctcId;
	
	@FindBy(css="input[id='irctcIdTxt']")
	private WebElement txtIrctcIDAfterReg;
	
	@FindBy(css=".forgot-holder>a")
	private WebElement btnResetIrctcPsswrd;
	
	@FindBy(css="span[class='edit-det']>a[id='irctcBtnID']")
	private WebElement btnEditIrctcId; 
	
	@FindBy(css=".editmobileid")
	private WebElement txtMobileNoIRCTC;
	
	@FindBy(css="#editType")
	private WebElement btnEditIrctcMobileNo;
	
	@FindBy(css="#popoverWindow")
	private WebElement popUpMobileNoUpdate;
	
	@FindBy(css="#popoverWindow>div[class='popover-content modalBody']")
	private WebElement msgMobileNoUpdate;
	
	@FindBy(css="#popoverWindow>h3>span[title='Close']")
	private WebElement closeMobileNoUpdateMsgPopUp;
	
	@FindBy(css="#sendNewPass")
	private WebElement btnResetIrctcPswrd;
	
	@FindBy(css="#Forgot-pwd-div>label[for='showSecurity']>span")
	private WebElement rdoSendRegMail;
	
	@FindBy(css="#Forgot-pwd-div>label[for='showOTP']>span")
	private WebElement rdoSendRegMobile;
	
	@FindBy(css="#emailSent")
	private WebElement lblEmailResetPwd;
	
	@FindBy(css="#mobileSent")
	private WebElement lblMobileResetPwd;
	
	@FindBy(css=".top-forgot-pwd")
	WebElement btnResetPwd;
	
	
	
	/**********************************************************************************************
	 ********************************* WebElements of TrainTravellerPage - Ends ****************************
	 **********************************************************************************************/

	/**
	 * 
	 * @param driver
	 *            : webdriver
	 */
	public TrainTravellerPage(WebDriver driver) {
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

		if (isPageLoaded && !(Utils.waitForElement(driver, divPaxContainer))) {
			Log.fail("Train Search Result page didn't open up", driver);
		}
	}

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForPageLoad(driver);
	}



    /**
     * filling details as 'Guest' user and clicked on Book as Guest
     * @param userName
     * @param mobileNumber
     * @throws Exception
     */

	public void loginAsGuestUser(String userName,String mobileNumber) throws Exception{
		BrowserActions.typeOnTextField(txtEmail, userName, driver, "Entered Email Id.");
		BrowserActions.typeOnTextField(txtMobile,mobileNumber, driver, "Entered Mobile Number.");
		BrowserActions.clickOnElement(btnContinueAsGuest, driver, "Clicked on 'Book as Guest' button.");
	}
	
	/**'
	 * to logIn as Signed In user
	 * @param userName
	 * @param password
	 * @throws Exception
	 */
	public void loginAsSignedUser(String userName,String password) throws Exception{
		BrowserActions.typeOnTextField(txtEmail, userName, driver, "Entered Email Id.");
		
		BrowserActions.clickOnElement(chkSignedAsLogin, driver, "Clicked on 'Have Yatra' account checkbox.");
		BrowserActions.typeOnTextField(txtPassword,password, driver, "Entered Password.");
		BrowserActions.clickOnElement(btnProccedAsSignedUser, driver, "Clicked on 'Proceed as signed In users' button.");
	}
	
	
	/**
	 * filling traveller details in Pax Page
	 * @throws Exception
	 */
	public void fillTravellerDetails()  throws Exception{
		List<WebElement> lstPax = driver.findElements(By.cssSelector("ul[id='paxStripContainer']>li[class='horzSeprB pb5']"));

		for(int i=0;i<lstPax.size();i++){
			BrowserActions.clickOnElement(lstPax.get(i).findElement(By.cssSelector("div[class='flL ml10 select-input relative']>label")), driver, "Clicked on title dropdown.");
			List<WebElement> lstTitle = lstPax.get(i).findElements(By.cssSelector("div[class='flL ml10 select-input relative']>label>select>option"));
             if(lstTitle.size()!=0){
 				int rand = Utils.getRandom(2, lstTitle.size());
                Utils.waitForElement(driver, lstTitle.get(rand));
 				BrowserActions.clickOnElement(lstTitle.get(rand), driver, "title selected");
 				Utils.waitForPageLoad(driver);
 			  }
		
        
		String randomName = RandomStringUtils.randomAlphabetic(5).toLowerCase();
		String age = RandomStringUtils.randomNumeric(2);
		int age1 = Integer.parseInt(age);
		BrowserActions.typeOnTextField(lstPax.get(i).findElement(By.cssSelector("input")), randomName, driver, "First Name");
		Log.event("Successfully entered Passenger name:"+randomName);
		BrowserActions.typeOnTextField(lstPax.get(i).findElement(By.cssSelector("div[class*='ageSel ']>input")), age, driver, "Age");
		Log.event("Successfully entered Passenger name:"+age);
		if(age1>=60){
			BrowserActions.clickOnElement(lstPax.get(i).findElement(By.cssSelector("div[id*='SnrCtzn']>label")), driver, "Checked 'Senior Citizen' checkbox in case of 60 and above.");
			
		}
		
		BrowserActions.clickOnElement(lstPax.get(i).findElement(By.cssSelector("div[class*='berthPref']>label")), driver, "Clicked on Berth dropdown.");
		List<WebElement> lstBerth = lstPax.get(i).findElements(By.cssSelector("div[class*='berthPref']>label>select>option"));
         if(lstBerth.size()!=0){
				int rand = Utils.getRandom(2, lstBerth.size());
                Utils.waitForElement(driver, lstBerth.get(rand));
				BrowserActions.clickOnElement(lstBerth.get(rand), driver, "Berth selected");
				Utils.waitForPageLoad(driver);
			  }

		
		}
	}
	
	
	/**
	 * clicked on Continue In paxPage
	 * @throws Exception
	 */
	public TrainReviewPage clickOnContinueInPaxPage() throws Exception{
		BrowserActions.scrollToView(btnContinue, driver);
		BrowserActions.clickOnElement(btnContinue, driver, "Clicked on Continue in Pax Page.");
		return new TrainReviewPage(driver).get();
	}
	
	
	/**
	 * Checked travel insurance checkbox
	 * @throws Exception
	 */
	public void clickToAcceptInsurance() throws Exception{
		BrowserActions.clickOnElement(chkInsuranceAccepted, driver, "Checking Insurance checkbox.");
	}
	
	
	/**
	 * checked Booking Policy
	 * @throws Exception
	 */
	public void checkBookingPolicy() throws Exception{
		BrowserActions.clickOnElement(chkBookingPolicy, driver, "Checked Booking Policys.");
	}
	
	/**
	 * verify irctc division first and clicked on Submit button
	 * @param id
	 * @throws Exception
	 */
	public void enterIrctcId(String id) throws Exception{
		if(btnSubmit.isDisplayed()){
    	BrowserActions.typeOnTextField(txtIrctcId, id, driver, "Enter IRCTC Id.");
		BrowserActions.clickOnElement(btnSubmit, driver, "Clicked on Submit button.");

		}
	}
	
	/**
	 * getting error message from the Popup appears for the inavlid IRCTC id and then close it.
	 * @return
	 * @throws Exception
	 */
	public String getTextFromInvalidIrctcIdPopUp() throws Exception{
		 String errorMsg = BrowserActions.getText(driver, msgInvalidIrctcId, "Getting Text from the Invalid IRCTC Id Popup.");
		 BrowserActions.clickOnElement(closePopupInvalidIrctcId, driver, "Close Invalid IRCTC Id Popup.");
		 return errorMsg;
	}
	
	/**
	 * click on edit link to edit IRCTC id and then save it
	 * @throws Exception
	 */
	public void clickOnEditIrctcIDLink(String irctcId) throws Exception{
		BrowserActions.javascriptClick(btnEditIrctcId, driver, "Clicked on 'Edit' IRCTC Id link.");
		BrowserActions.typeOnTextField(txtIrctcIDAfterReg, irctcId, driver, "Ënter the new IRCTC Id.");
		BrowserActions.javascriptClick(btnEditIrctcId, driver, "Clicked on 'Edit' IRCTC Id link.");

		
	}
	
	
	/**
	 * getting IRCTC Mobile number
	 * @return
	 * @throws Exception
	 */
	public String getIRCTCMobileNo() throws Exception{
		return BrowserActions.getText(driver, txtMobileNoIRCTC, "Getting Text from the Mobile IRCTC");
	}
	
	
	/**
	 * click on edit link to edit IRCTC Mobile No. and then save it
	 * @throws Exception
	 */
	public void editIrctcMobileNumber(String mobile) throws Exception{
		BrowserActions.clickOnElement(btnEditIrctcMobileNo, driver, "Clicked on 'Edit' IRCTC Mobile link.");
		BrowserActions.typeOnTextField(txtMobileNoIRCTC, mobile, driver, "Entering IRCTC Mobile Number to Edit.");
		BrowserActions.clickOnElement(btnEditIrctcMobileNo, driver, "Clicked on 'Save' IRCTC Mobile link.");	
	}
	
	
	/**
	 * getting text from the Popup Message that appears after the successfully updating mobile no.
	 * @return
	 * @throws Exception
	 */
	public String getSuccessMsgFromUpdateMobileNoPopUp() throws Exception{
		String successMsg = BrowserActions.getText(driver, msgMobileNoUpdate, "Getting Success Message after Updating MobileNo.");
		BrowserActions.moveToElementJS(driver, closeMobileNoUpdateMsgPopUp);
		BrowserActions.clickOnElement(closeMobileNoUpdateMsgPopUp, driver, "Close the Success Message after Updating Mobile no Popup.");
		return successMsg;
	}
	
	
	/**
	 * clicked on 'Reset Irctc Password' button.
	 * @throws Exception
	 */
	public void clickOnIRCTCResetPassword() throws Exception{
		BrowserActions.clickOnElement(btnResetPwd, driver, "Clicked on 'Reset IRCTC Password' button.");
	}
	
	public void selectResetPasswordOption(String ResetOption) throws Exception{
		switch(ResetOption){
		
		case "Mobile":
		BrowserActions.clickOnElement(rdoSendRegMobile, driver, "Selected 'Send Password on Mobile.'");
        break;
        
		case "Email":
			BrowserActions.clickOnElement(rdoSendRegMail, driver, "Selected 'Send Password on Email.'");
	        break;
         
         
		}
	}
	
}//TrainTravellerPage


