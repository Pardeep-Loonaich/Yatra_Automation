package com.Yatra.TestScript.Common;
import org.openqa.selenium.WebDriver;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.Yatra.Utils.Constants;
import com.Yatra.Utils.EmailSender;
import com.Yatra.Utils.EnvironmentPropertiesReader;
import com.Yatra.Utils.ExtentReporter;
import com.Yatra.Utils.Log;
import com.Yatra.Utils.WebDriverFactory;
import com.Yatra.Utils.WriteToExcel;

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
/**
 * @Description: set test Name at run time in case, if we have a test case with multiple data
 * @param anInstanceName
 */
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
	 * @throws Exception 
	 * @throws MessagingException 
	 * @throws AddressException 
	 * @throws IOException 
	 */
	@AfterMethod
	public void  afterTestExecutor(ITestResult result, ITestContext context) throws Exception 

	{
		driver=WebDriverFactory.getCurrentDriverInstance();

		try
		{
			String emailOnFailure=context.getCurrentXmlTest().getParameter("SEND_EMAIL_ON_FAILIURE").trim();
			if(result.getStatus()==ITestResult.FAILURE && emailOnFailure.equalsIgnoreCase("TRUE"))			//if test case fail perform below task
			{
				Log.message("triggering email for failed test case");
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
			WriteToExcel.writePageLoadTimeToExcel(Constants.performanceData);
			Constants.performanceData.clear();
			driver.quit();
		}

	}

}
