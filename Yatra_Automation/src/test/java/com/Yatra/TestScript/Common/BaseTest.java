package com.Yatra.TestScript.Common;
import org.testng.ITest;
import org.testng.Reporter;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.annotations.BeforeMethod;

import com.Yatra.Utils.ExtentReporter;

/**
 * Description: To rename test case name at runtime<br>
 * @author harveer.singh
 *
 */
public class BaseTest implements ITest{
	private String testCaseId = "";
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
	public void nameChanger(Method method, Object[] parameters){

		UseAsTestName useAsTestName = method.getAnnotation(UseAsTestName.class);
		HashMap<String, String> data = (HashMap<String, String>) parameters[0];
		String testName =Reporter.getCurrentTestResult().getName();
		//testCaseId=ExtentReporter.getTestName();
		//if(ExtentReporter.tests.containsKey(""))
		setTestName("");
		
		
		
	}
}
