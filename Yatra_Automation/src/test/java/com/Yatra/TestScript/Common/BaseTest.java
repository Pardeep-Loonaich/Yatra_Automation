package com.Yatra.TestScript.Common;
import org.openqa.selenium.WebDriver;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.Yatra.Utils.EmailSender;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExtentReporter;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.WebDriverFactory;

/**
 * Description: To rename test case name at runtime<br>
 * @author harveer.singh
 *
 */
public class BaseTest implements ITest
{
	EnvironmentPropertiesReader propReader=EnvironmentPropertiesReader.getInstance();
	private String testCaseId = "";
	private static WebDriver driver;
	public  String inputFile;


	static List<String> listOfCompletedTestcase;
	int iCount=1;

	@Override
	public String getTestName() 
	{
		return testCaseId;
	}

	private void setTestName(String anInstanceName)
	{
		testCaseId = anInstanceName;
	} 

	@BeforeMethod(alwaysRun = true)
	public void nameChanger(ITestContext context, Method method, Object[] parameters ){

		//UseAsTestName useAsTestName = method.getAnnotation(UseAsTestName.class);
		@SuppressWarnings("unchecked")
		HashMap<String, String> data = (HashMap<String, String>) parameters[0];
		testCaseId=data.get("TestID");
		if(verifyReportContainsTestcaseId(testCaseId))
		{
			setTestName(testCaseId+"."+iCount);
			iCount++;
		}
		else
		{
			setTestName(testCaseId);
			iCount=1;
		}
	}

	public static boolean verifyReportContainsTestcaseId(String testCaseId)

	{
		boolean dataTobereturn=false;
		listOfCompletedTestcase = new ArrayList<String>(ExtentReporter.tests.keySet());
		for(int i=0; i<listOfCompletedTestcase.size();i++)
		{
			if(listOfCompletedTestcase.get(i).trim().contains(testCaseId.trim()))
			{
				dataTobereturn=true;
				break;
			}

		}

		return dataTobereturn;
	}

	/**
	 * @Description: it will execute after mail after every test case <br>
	 * 
	 * @param result
	 * @throws MessagingException 
	 * @throws AddressException 
	 * @throws IOException 
	 */
	@AfterMethod
	public void  afterTestExecutor(ITestResult result) 

	{
		System.out.println("Executing After Mehtod ..");

		try
		{
			if(result.getStatus()==ITestResult.FAILURE&&propReader.getProperty("SEND_EMAIL_ON_FAILIURE").equalsIgnoreCase("true"))			//if test case fail perform below task
			{
				driver=WebDriverFactory.baseTestDriver;
				inputFile=Log.takeScreenShot(driver);

				testCaseId=result.getName();
				String sCurrentURL=driver.getCurrentUrl();
				EmailSender email=new EmailSender(inputFile, testCaseId, sCurrentURL);
				email.sendHtmlEmail();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			driver.quit();
		}


	}
	/**
	 * to set webdriver for every test case (as of now setting in WebDriverFactory Class)
	 * @param drivers
	 */
	public static void setBaseDriver(WebDriver drivers)
	{
		driver=WebDriverFactory.baseTestDriver;
	}
}
