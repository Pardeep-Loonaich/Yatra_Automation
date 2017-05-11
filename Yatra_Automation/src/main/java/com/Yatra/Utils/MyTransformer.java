package com.Yatra.Utils;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.xml.Parser;
import org.xml.sax.SAXException;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

public class MyTransformer implements IAnnotationTransformer

{
	
	EnvironmentPropertiesReader prop=EnvironmentPropertiesReader.getInstance();
	private String workBookName=prop.getProperty("workBookName");
	private String workSheet=prop.getProperty("workSheet"); 
	@Override
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		
		HashMap<String, String> testData = TestDataExtractor.initTestData(workBookName, workSheet, testMethod.getName());
		String invoke = testData.get("RunMode");

		if (invoke.equalsIgnoreCase("No")) {
			annotation.setEnabled(false);
			System.out.println("disabled test case: \"" + testMethod.getName() + "\", Now it will not be executed  !!");
		}

	}

} //MyTransformer

