package com.Yatra.Utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.SkipException;


/**
 * Util class consists wait for page load,page load with user defined max time
 * and is used globally in all classes and methods
 * 
 */
public class Utils {
	private static EnvironmentPropertiesReader configProperty = EnvironmentPropertiesReader.getInstance();
	public static int maxElementWait = 10;

	/**
	 * waitForPageLoad waits for the page load with default page load wait time
	 * 
	 * @param driver
	 *            : Webdriver
	 */
	public static void waitForPageLoad(final WebDriver driver) {
		waitForPageLoad(driver, WebDriverFactory.maxPageLoadWait);
	}

	/**
	 * waitForPageLoad waits for the page load with custom page load wait time
	 * 
	 * @param driver
	 *            : Webdriver
	 * @param maxWait
	 *            : Max wait duration
	 */
	public static void waitForPageLoad(final WebDriver driver, int maxWait) {
		//long startTime = StopWatch.startTime();
		FluentWait<WebDriver> wait = new WebDriverWait(driver, maxWait).pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(StaleElementReferenceException.class).withMessage("Page Load Timed Out");
		try {

			if (configProperty.getProperty("documentLoad").equalsIgnoreCase("true"))
				wait.until(WebDriverFactory.documentLoad);

			if (configProperty.getProperty("imageLoad").equalsIgnoreCase("true"))
				wait.until(WebDriverFactory.imagesLoad);

			if (configProperty.getProperty("framesLoad").equalsIgnoreCase("true"))
				wait.until(WebDriverFactory.framesLoad);

			String title = driver.getTitle().toLowerCase();
			String url = driver.getCurrentUrl().toLowerCase();
			Log.event("Page URL:: " + url);

			if ("the page cannot be found".equalsIgnoreCase(title) || title.contains("is not available")
					|| url.contains("/error/") || url.toLowerCase().contains("/errorpage/")) {
				Assert.fail("Site is down. [Title: " + title + ", URL:" + url + "]");
			}

		} catch (TimeoutException e) {
			driver.navigate().refresh();
			wait.until(WebDriverFactory.documentLoad);
			wait.until(WebDriverFactory.imagesLoad);
			wait.until(WebDriverFactory.framesLoad);
		}
		//Log.event("Page Load Wait: (Sync)", StopWatch.elapsedTime(startTime));

	} // waitForPageLoad

	/**
	 * To get the test orientation
	 * 
	 * <p>
	 * if test run on sauce lab device return landscape or portrait or valid
	 * message, otherwise check local device execution and return landscape or
	 * portrait or valid message
	 * 
	 * @return dataToBeReturned - portrait or landscape or valid message
	 */
	public static String getTestOrientation() {
		String dataToBeReturned = null;
		boolean checkExecutionOnSauce = false;
		boolean checkDeviceExecution = false;
		checkExecutionOnSauce = (System.getProperty("SELENIUM_DRIVER") != null
				|| System.getenv("SELENIUM_DRIVER") != null) ? true : false;

		if (checkExecutionOnSauce) {
			checkDeviceExecution = ((System.getProperty("runUserAgentDeviceTest") != null)
					&& (System.getProperty("runUserAgentDeviceTest").equalsIgnoreCase("true"))) ? true : false;
			if (checkDeviceExecution) {
				dataToBeReturned = (System.getProperty("deviceOrientation") != null)
						? System.getProperty("deviceOrientation") : "no sauce run system variable: deviceOrientation ";
			} else {
				dataToBeReturned = "sauce browser test: no orientation";
			}
		} else {
			checkDeviceExecution = (configProperty.hasProperty("runUserAgentDeviceTest")
					&& (configProperty.getProperty("runUserAgentDeviceTest").equalsIgnoreCase("true"))) ? true : false;
			if (checkDeviceExecution) {
				dataToBeReturned = configProperty.hasProperty("deviceOrientation")
						? configProperty.getProperty("deviceOrientation")
								: "no local run config variable: deviceOrientation ";
			} else {
				dataToBeReturned = "local browser test: no orientation";
			}
		}
		return dataToBeReturned;
	}

	/**
	 * To wait for the specific element on the page
	 * 
	 * @param driver
	 *            : Webdriver
	 * @param element
	 *            : Webelement to wait for
	 * @return boolean - return true if element is present else return false
	 */
	public static boolean waitForElement(WebDriver driver, WebElement element) {
		return waitForElement(driver, element, maxElementWait);
	}

	/**
	 * To wait for the specific element on the page
	 * 
	 * @param driver
	 *            : Webdriver
	 * @param element
	 *            : Webelement to wait for
	 * @param maxWait
	 *            : Max wait duration
	 * @return boolean - return true if element is present else return false
	 */
	public static boolean waitForElement(WebDriver driver, WebElement element, int maxWait) {
		boolean statusOfElementToBeReturned = false;
		//long startTime = StopWatch.startTime();
		WebDriverWait wait = new WebDriverWait(driver, maxWait);
		try {
			WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(element));
			if (waitElement.isDisplayed() && waitElement.isEnabled()) {
				statusOfElementToBeReturned = true;
				Log.event("Element is displayed:: " + element.toString());
			}
		} catch (Exception e) {
			statusOfElementToBeReturned = false;
			/*Log.event("Unable to find a element after " + StopWatch.elapsedTime(startTime) + " sec ==> "
					+ element.toString());*/
		}
		return statusOfElementToBeReturned;
	}

	public static WebDriver switchWindows(WebDriver driver, String windowToSwitch, String opt,
			String closeCurrentDriver) throws Exception {

		WebDriver currentWebDriver = driver;
		WebDriver assingedWebDriver = driver;
		boolean windowFound = false;
		ArrayList<String> multipleWindows = new ArrayList<String>(assingedWebDriver.getWindowHandles());

		for (int i = 0; i < multipleWindows.size(); i++) {

			assingedWebDriver.switchTo().window(multipleWindows.get(i));

			if (opt.equals("title")) {
				if (assingedWebDriver.getTitle().trim().equals(windowToSwitch)) {
					windowFound = true;
					break;
				}
			} else if (opt.equals("url")) {
				if (assingedWebDriver.getCurrentUrl().contains(windowToSwitch)) {
					windowFound = true;
					break;
				}
			} // if

		} // for

		if (!windowFound)
			throw new Exception("Window: " + windowToSwitch + ", not found!!");
		else {
			if (closeCurrentDriver.equals("true"))
				currentWebDriver.close();
		}

		return assingedWebDriver;

	}// switchWindows

	/**
	 * Switching between tabs or windows in a browser
	 * 
	 * @param driver
	 *            -
	 */
	public static void switchToNewWindow(WebDriver driver) {
		String winHandle = driver.getWindowHandle();
		for (String index : driver.getWindowHandles()) {
			if (!index.equals(winHandle)) {
				driver.switchTo().window(index);
				break;
			}
		}
		if (!((RemoteWebDriver) driver).getCapabilities().getBrowserName().matches(".*safari.*")) {
			((JavascriptExecutor) driver).executeScript(
					"if(window.screen){window.moveTo(0, 0); window.resizeTo(window.screen.availWidth, window.screen.availHeight);};");
		}
	}

	/**
	 * To compare two HashMap values,then print unique list value and print
	 * missed list value
	 * 
	 * @param expectedList
	 *            - expected element list
	 * @param actualList
	 *            - actual element list
	 * @return statusToBeReturned - returns true if both the lists are equal,
	 *         else returns false
	 */
	public static boolean compareTwoHashMap(Map<String, String> expectedList, Map<String, String> actualList) {
		List<String> missedkey = new ArrayList<String>();
		HashMap<String, String> missedvalue = new HashMap<String, String>();
		try {
			for (String k : expectedList.keySet()) {
				if (!(actualList.get(k).equalsIgnoreCase(expectedList.get(k)))) {
					missedvalue.put(k, actualList.get(k));
					Log.failsoft("Missed Values:: " + missedvalue);
					return false;
				}
			}
			for (String y : actualList.keySet()) {
				if (!expectedList.containsKey(y)) {
					missedkey.add(y);
					Log.failsoft("Missed keys:: " + missedkey);
					return false;
				}
			}
		} catch (NullPointerException np) {
			return false;
		}
		return true;
	}

	/**
	 * To compare two array list values,then print unique list value and print
	 * missed list value
	 * 
	 * @param expectedElements
	 *            - expected element list
	 * @param actualElements
	 *            - actual element list
	 * @return statusToBeReturned - returns true if both the lists are equal,
	 *         else returns false
	 */
	public static boolean compareTwoList(List<String> expectedElements, List<String> actualElements) {
		boolean statusToBeReturned = false;
		List<String> uniqueList = new ArrayList<String>();
		List<String> missedList = new ArrayList<String>();
		for (String item : expectedElements) {
			if (actualElements.contains(item)) {
				uniqueList.add(item);
			} else {
				missedList.add(item);
			}
		}
		Collections.sort(expectedElements);
		Collections.sort(actualElements);
		if (expectedElements.equals(actualElements)) {
			Log.event("All elements checked on this page:: " + uniqueList);
			statusToBeReturned = true;
		} else {
			Log.failsoft("Missing element on this page:: " + missedList);
			statusToBeReturned = false;
		}
		return statusToBeReturned;
	}

	/**
	 * Verify the css property for an element
	 * 
	 * @param element
	 *            - WebElement for which to verify the css property
	 * @param cssProperty
	 *            - the css property name to verify
	 * @param actualValue
	 *            - the actual css value of the element
	 * @return boolean
	 */
	public static boolean verifyCssPropertyForElement(WebElement element, String cssProperty, String actualValue) {
		boolean result = false;

		String actualClassProperty = element.getCssValue(cssProperty);

		if (actualClassProperty.contains(actualValue)) {
			result = true;
		}
		return result;
	}

	/**
	 * To get the value of an input field.
	 * 
	 * @param element
	 *            - the input field you need the value/text of
	 * @param driver
	 *            -
	 * @return text of the input's value
	 */
	public static String getValueOfInputField(WebElement element, WebDriver driver) {
		String sDataToBeReturned = null;
		if (Utils.waitForElement(driver, element)) {
			sDataToBeReturned = element.getAttribute("value");
		}
		return sDataToBeReturned;
	}

	/**
	 * To wait for the specific element which is in disabled state on the page
	 * 
	 * @param driver
	 *            - current driver object
	 * @param element
	 *            - disabled webelement
	 * @param maxWait
	 *            - duration of wait in seconds
	 * @return boolean - return true if disabled element is present else return
	 *         false
	 */
	public static boolean waitForDisabledElement(WebDriver driver, WebElement element, int maxWait) {
		boolean statusOfElementToBeReturned = false;
		//long startTime = StopWatch.startTime();
		WebDriverWait wait = new WebDriverWait(driver, maxWait);
		try {
			WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(element));
			if (!waitElement.isEnabled()) {
				statusOfElementToBeReturned = true;
				Log.event("Element is displayed and disabled:: " + element.toString());
			}
		} catch (Exception ex) {
			statusOfElementToBeReturned = false;
			/*Log.event("Unable to find disabled element after " + StopWatch.elapsedTime(startTime) + " sec ==> "
					+ element.toString());*/
		}
		return statusOfElementToBeReturned;
	}

	/**
	 * Wait until element disappears in the page
	 * 
	 * @param driver
	 *            - driver instance
	 * @param element
	 *            - webelement to wait to have disaapear
	 * @return true if element is not appearing in the page
	 */
	public static boolean waitUntilElementDisappear(WebDriver driver, final WebElement element) {
		final boolean isNotDisplayed;

		WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, WebDriverFactory.maxPageLoadWait);
		isNotDisplayed = wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				boolean isPresent = false;
				try {
					if (element.isDisplayed()) {
						isPresent = false;
						Log.event("Element " + element.toString() + ", is still visible in page");
					}
				} catch (Exception ex) {
					isPresent = true;
					Log.event("Element " + element.toString() + ", is not displayed in page ");
					return isPresent;
				}
				return isPresent;
			}
		});
		return isNotDisplayed;
	}

	/**
	 * Wait until element disappears in the page
	 * 
	 * @param driver
	 *            - driver instance
	 * @param element
	 *            - webelement to wait to have disaapear
	 * @return true if element is not appearing in the page
	 */
	public static boolean waitUntilElementDisappear(WebDriver driver, final WebElement element, int maxWait) {
		final boolean isNotDisplayed;

		WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, maxWait);
		isNotDisplayed = wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				boolean isPresent = false;
				try {
					if (element.isDisplayed()) {
						isPresent = false;
						Log.event("Element " + element.toString() + ", is still visible in page");
					}
				} catch (Exception ex) {
					isPresent = true;
					Log.event("Element " + element.toString() + ", is not displayed in page ");
					return isPresent;
				}
				return isPresent;
			}
		});
		return isNotDisplayed;
	}

	/**
	 * To get run platform from the config.Property files
	 * 
	 * @return String - return mobile/desktop value
	 */
	public static String getRunPlatForm() {
		String dataToBeReturned = null;
		if ((configProperty.hasProperty("runMobile")
				&& configProperty.getProperty("runMobile").equalsIgnoreCase("true"))
				|| (configProperty.hasProperty("runUserAgentDeviceTest")
						&& configProperty.getProperty("runUserAgentDeviceTest").equalsIgnoreCase("true"))) {
			dataToBeReturned = "mobile";
		} else {
			dataToBeReturned = "desktop";
		}
		Log.event("Running platform type:: " + dataToBeReturned);
		return dataToBeReturned;
	}// getRunPlatForm

	/**
	 * To generate random number from '0 to Maximum Value' or 'Minimum Value ---- Maximum Value'
	 * @param max - maximum bound
	 * @param min - origin bound
	 * @return - random number between 'min to max' or '0 to max'
	 * @throws Exception
	 */
	public static int getRandom(int min, int max)throws Exception{
		Random random = new Random();
		int rand;
		if(min == 0)
			rand = random.nextInt(max);
		else
			rand = ThreadLocalRandom.current().nextInt(min, max);

		return rand;
	}

	/**
	 * To verify the page url contains the given word
	 * @param driver
	 * @param stringContains
	 * @return boolean
	 */
	public static boolean verifyPageURLContains(final WebDriver driver, String hostURL, String stringContains) {
		boolean status = false;
		String url = null;
		try {
			url = driver.getCurrentUrl();
			if(url==null) {
				url = ((JavascriptExecutor) driver).executeScript("return document.URL;").toString();
			}
		} catch (Exception e) {
			url = ((JavascriptExecutor) driver).executeScript("return document.URL;").toString();
			// TODO: handle exception
		}
		if(url.contains(hostURL.split("https://")[1]) && url.contains(stringContains)) {
			status = true;
		}

		return status;
	}

	/**
	 * Round to certain number of decimals
	 * 
	 * @param d
	 * @param decimalPlace the numbers of decimals
	 * @return
	 */
	public static float round(double d, int decimalPlace)

	{
		return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).floatValue();
	}


	/**
	 * @author harveer.singh
	 * @param iDay: provide No of which you want to decrease/increase
	 * 
	 *              for current date: iDay should be 0 (Zero ) 
	 *              for future date: iDay should be +ve (1,2,3,4 ..etc.)
	 *              for past date: iDay should be -ve (-1,-2,-3,-4 ..etc.)
	 * @return : it will return date in as string
	 */
	public static String dateGenerator(String sDateFormat,int iDay)

	{
		String dataToBeReturn="";
		if(sDateFormat.equalsIgnoreCase("") ||sDateFormat.equalsIgnoreCase(null))
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		}

		Calendar cal=Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sDateFormat);

		if(!(iDay==0))

		{
			cal.add(Calendar.DATE, iDay);
			dataToBeReturn=simpleDateFormat.format(cal.getTime()).toString();
			//System.out.println(simpleDateFormat.format(cal.getTime()));
		}
		else 
		{
			dataToBeReturn=simpleDateFormat.format(new Date());
			//System.out.println(simpleDateFormat.format(cal.getTime()));
		}
		return dataToBeReturn;

	}
	/** 
	 * 
	 * @param sExecute: a string flag YES/No
	 * @param testCaseId: test case ID
	 * @return: it will return boolean
	 */

	public static boolean testCaseConditionalSkip(String sExecute,String testCaseId)
	{
		boolean dataToBeReturn=false;

		if("yes".equalsIgnoreCase(sExecute.toLowerCase().trim()))
		{
			dataToBeReturn=true;
			Log.message("\""+testCaseId+"\"has marked Run as \"NO\" in Excel data !!");
			Log.message("\""+testCaseId+"\" has been skipped !!");
			throw new SkipException("\""+testCaseId+"\" has been skipped !!");
		
		}
		return dataToBeReturn;
	}







}
