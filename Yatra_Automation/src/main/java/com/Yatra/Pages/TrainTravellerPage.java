package com.Yatra.Pages;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
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
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExecutionTimer;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

public class TrainTravellerPage extends LoadableComponent<TrainTravellerPage> {

	private String appURL;

	private WebDriver driver;
	private boolean isPageLoaded;
	public ElementLayer elementLayer;
	ExecutionTimer timer=new ExecutionTimer();
	EnvironmentPropertiesReader envPropertiesReader=EnvironmentPropertiesReader.getInstance();


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
	
	@FindBy(css="div[id='paxContainer']>div[class='ytContainerFixed mt20']>form>div[class='ytColm9']>div>div[class='loginBtnArea flL']>div>input")
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
	private WebElement btnResetPwd;
	
	@FindBy(css="#mobileOTPR")
	private WebElement txtMobileIrctcResetPwrd;
	
	@FindBy(css="#resetError")
	private WebElement txtErrorMobileIrctcResetPwrd;
	
	@FindBy(css="#createLink>a")
	private WebElement lnkCreateNewIrctc;
	
	@FindBy(css="#loginwithoutirctc")
	private WebElement irctcLoginSteps;
	
	@FindBy(css="li[class*='irctcID']>ul>li>a[href*='eticketing/userSignUp.jsf']")
	private WebElement lnkAsIndianRes;
	
	@FindBy(css="li[class*='EmailID']>ul>li>a[href*='eticketing/userSignUp.jsf']")
	private WebElement lnkAsNonIndianResStep1;
	
	@FindBy(css="li[class*='EmailID']>ul>li>a[href*='eticketing/contact.jsf']")
	private WebElement lnkAsNonIndianResStep2;
	
	@FindBy(css="li[class*='EmailID']>ul>li>a[href*='eticketing/contact.jsf']")
	private WebElement lnkAsNonIndianResStep3;
	
	@FindBy(css=".toasterHolder")
	private WebElement toaderErrorMsg;
	
	@FindBy(css="#coachId")
	private WebElement txtCoachId;
	
	@FindBy(xpath="//*[@id='summaryContainer']/ul/li/div/label/select[@class='boardingStnListClass']")
	private WebElement drpBoardingPnt;
	
	@FindBy(css="select[class='boardingStnListClass']>option")
	private List<WebElement> lstBoardingPnt;
	
	@FindBy(css = ".boardingMessage")
	private WebElement msgChngeBoardngPoint;
	
	@FindBy(css="ul[id='paxStripContainer']>li[class='horzSeprB pb5']")
	private List<WebElement> lstPax;
	
	
	
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
		timer.end();

		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !(Utils.waitForElement(driver, divPaxContainer))) {
			Log.fail("Train Traveller page didn't open up", driver);
		}
		Log.message("Total time taken by #"+this.getClass().getTypeName()+"to load is:- "+timer.duration()+" "+TimeUnit.MILLISECONDS);
		Constants.performanceData.add(timer.duration());
	}

	@Override
	protected void load() {
		timer.start();

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
			//BrowserActions.scrollToView(lstPax.get(i).findElement(By.cssSelector("div[class='flL ml10 select-input relative']>label")), driver);
			BrowserActions.clickOnElement(lstPax.get(i).findElement(By.cssSelector("div[class='flL ml10 select-input relative']>label")), driver, "Clicked on title dropdown.");
			List<WebElement> lstTitle = lstPax.get(i).findElements(By.cssSelector("div[class='flL ml10 select-input relative']>label>select>option"));
             if(lstTitle.size()!=0){
 				int rand = Utils.getRandom(2, lstTitle.size());
                Utils.waitForElement(driver, lstTitle.get(rand));
 				BrowserActions.clickOnElement(lstTitle.get(rand), driver, "title selected");
 				Utils.waitForPageLoad(driver);
 			  }
		
        int range[] = {12,13,14,15,16,17,34,45,48,49,50,54,35,23};
		String randomName = RandomStringUtils.randomAlphabetic(5).toLowerCase();
	/*	String age = RandomStringUtils.randomNumeric(2);
		int age1 = Integer.parseInt(age);*/
		Random rand1 = new Random();
		int index = rand1.nextInt(range.length);
		int age = range[index];
		String Age = String.valueOf(age);
		BrowserActions.typeOnTextField(lstPax.get(i).findElement(By.cssSelector("input")), randomName, driver, "First Name");
		Log.event("Successfully entered Passenger name:"+randomName);
		
			
		BrowserActions.typeOnTextField(lstPax.get(i).findElement(By.cssSelector("div[class*='ageSel ']>input")),Age, driver, "Age");
		Log.event("Successfully entered Passenger name:"+rand1);
	/*	if(age1>=60){
			BrowserActions.clickOnElement(lstPax.get(i).findElement(By.cssSelector("div[id*='SnrCtzn']>label")), driver, "Checked 'Senior Citizen' checkbox in case of 60 and above.");
		
		*/
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
	 * clicked on Continue In paxPage in case we fill invalid details and we are not navigated to review page.
	 * @throws Exception
	 */
	public void clickOnContinueBtnInPaxPage() throws Exception{
		BrowserActions.scrollToView(btnContinue, driver);
		BrowserActions.clickOnElement(btnContinue, driver, "Clicked on Continue in Pax Page.");
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
	
	/**
	 * to enter the Mobile Number to reset Password 
	 * @param number
	 * @throws Exception
	 */
	
	public void enterMobileNmberToRestIrctcPswd(String number) throws Exception{
		BrowserActions.typeOnTextField(txtMobileIrctcResetPwrd, number, driver, "Entered the mobile number to reset Password.");
	}
	
	/**
	 * to get error message after adding the invalid number 
	 * @return
	 * @throws Exception
	 */
	public String getErrorMsgForInvalidMbleNmbr() throws Exception{
		BrowserActions.clickOnElement(btnResetIrctcPswrd, driver, "Clicked on 'Reset Password' button");
		return BrowserActions.getText(driver,txtErrorMobileIrctcResetPwrd , "To get the text from the error msg on invalid mobile number.");

	}
	
	/**
	 * to click on 'Create New' Irctc account link
	 * @throws Exception
	 */
	public void clickOnCreateNewIrctcAccLnk() throws Exception{
		BrowserActions.scrollToView(lnkCreateNewIrctc, driver);
		BrowserActions.clickOnElement(lnkCreateNewIrctc, driver, "Clicked on 'Create New Account' link.");
	}
	
	/**
	 * to get the steps for the new Account Creation 
	 * @return
	 * @throws Exception
	 */
	public String getCreateNewIrctcAccSteps() throws Exception{
		return BrowserActions.getText(driver,irctcLoginSteps, "Getting text from the Login Steps.");
	}
	
	/**
	 * to get text from the invalid toader messages that appear after invalid data enteries
	 * @return
	 * @throws Exception
	 */
	public String getErrorTxtFromTheToader() throws Exception{
		return BrowserActions.getText(driver, toaderErrorMsg, "Getting Text from the Toader's invalid error messages.");
	}
	
	/**
	 * to enter the Coach Id in coach id text field
	 * @param coachId
	 * @throws Exception
	 */
	public void fillCoachId(String coachId) throws Exception{
		BrowserActions.typeOnTextField(txtCoachId, coachId, driver, "Entered CoachId.");
	}
	
	/**
	 * to enter the Coach Id in coach id text field
	 * @param coachId
	 * @throws Exception
	 */
	public void deleteCoachId() throws Exception{
		txtCoachId.clear();
	}
	
	
	
	/**
	 * selecting boarding point from the boarding point dropdown
	 * @throws Exception
	 */
	public void selectBoardingPointFrmDrpdwn() throws Exception{
		BrowserActions.clickOnElement(drpBoardingPnt, driver, "Clicked on Boarding dropdown.");
		if(lstBoardingPnt.size()>0){
			int rand = Utils.getRandom(2, lstBoardingPnt.size());
            Utils.waitForElement(driver, lstBoardingPnt.get(rand));
			BrowserActions.clickOnElement(lstBoardingPnt.get(rand), driver, "Boarding Point selected");
			Utils.waitForPageLoad(driver);
		}
	}
	
	/**
	 * getting message text after changing boarding point in left nav panel
	 * @return
	 * @throws Exception
	 */
	public String getSuccessMsgAfterChngBoardPnt() throws Exception{
		return BrowserActions.getText(driver, msgChngeBoardngPoint, "Getting message after changing Boarding Point.");
	}
	
	/**
	 * to verify the pax detail section 
	 * @throws Exception
	 */
	public void verifyPaxDetails() throws Exception{
	String randomName = RandomStringUtils.randomAlphabetic(5).toLowerCase();
	BrowserActions.scrollToView(lstPax.get(0).findElement(By.cssSelector("input")), driver);
	BrowserActions.typeOnTextField(lstPax.get(0).findElement(By.cssSelector("input")), randomName, driver, "First Name");
	Log.event("Successfully entered Passenger name:"+randomName);
	clickOnContinueBtnInPaxPage();
	}
	
	
	public void verifyInfantDetails() throws Exception{
		String randomInfntName = RandomStringUtils.randomAlphabetic(5).toLowerCase();
		BrowserActions.scrollToView(driver.findElement(By.cssSelector("#childName1")), driver);
		BrowserActions.typeOnTextField(driver.findElement(By.cssSelector("#childName1")), randomInfntName, driver, "First Infant Name");
		Log.event("Successfully entered Infant name:"+randomInfntName);
		clickOnContinueBtnInPaxPage();

	}
	@FindBy(css = "div[class='recent-iternary-detail']")
	private WebElement cityNameInRecentSearchPopUp;
	
	/**
	 * getting cities name from recent search Pop up
	 * @return
	 * @throws Exception
	 */
	public String getCityNameInRecentSearch() throws Exception{
		String name = null;
		if(cityNameInRecentSearchPopUp.isDisplayed()){
		BrowserActions.mouseHover(driver, cityNameInRecentSearchPopUp);
		name = BrowserActions.getText(driver, cityNameInRecentSearchPopUp, "Getting cities name from recent search Pop up");
		}
		return name;
	}
	
}//TrainTravellerPage


