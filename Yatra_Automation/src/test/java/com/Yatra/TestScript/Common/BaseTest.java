package com.Yatra.TestScript.Common;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.Yatra.Utils.ExtentReporter;

/**
 * Description: To rename test case name at runtime<br>
 * @author harveer.singh
 *
 */
public class BaseTest implements ITest{
	private String testCaseId = "";
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

		UseAsTestName useAsTestName = method.getAnnotation(UseAsTestName.class);
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
	 */
	@AfterMethod
	public void  afterTestExecutor(ITestResult result)
	
	{
		
		
	}
}
