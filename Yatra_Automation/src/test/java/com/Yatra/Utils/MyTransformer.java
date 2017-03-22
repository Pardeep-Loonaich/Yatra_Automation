package com.Yatra.Utils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;


public class MyTransformer implements IAnnotationTransformer

{
	EnvironmentPropertiesReader prop=EnvironmentPropertiesReader.getInstance();
	private String workBookName=prop.getProperty("workBookName");
	private String workSheet=prop.getProperty("workSheet");
	
	
	@Override
	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) 

	{
		
	/*Utils.annotation=annotation;
		 Utils.testMethod=testMethod;
		//Utils.testCaseId=testMethod.getName();
		System.out.println(testMethod.getName());*/
		
		HashMap<String, String> testData=TestDataExtractor.initTestData(workBookName, workSheet,testMethod.getName());	
		String invoke=testData.get("RunMode");
		
		if(invoke.equalsIgnoreCase("No"))
		{
			annotation.setEnabled(false);
			System.out.println("disabled test case: \""+testMethod.getName()+"\", Now it will not be executed  !!");
		}
		
		
		
	}

	

}
