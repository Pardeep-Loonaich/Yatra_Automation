package com.Yatra.dbinsert;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ReportUtil  {
	public static String separator = System.getProperty("file.separator");
	public static LinkedHashMap<String, String> failed = null;
	public static LinkedHashMap<String, String> broken = null;
	public static LinkedHashMap<String, String> skipped = null;
	public static LinkedHashMap<String, String> passed = null;
	public static HSSFWorkbook workBook = null;
	public static HSSFSheet sheet = null;
	public static String className=null;

	public static void fetchTheResultsFromTestNG(String buildNumber) throws Exception {
		String class_of_exception = "";
		File f;
		failed = new LinkedHashMap<String, String>();
		broken = new LinkedHashMap<String, String>();
		skipped = new LinkedHashMap<String, String>();
		passed = new LinkedHashMap<String, String>();

		f = new File(System.getProperty("user.dir")
				+ "/test-output/testng-results.xml");

		DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		DocumentBuilder build = fact.newDocumentBuilder();
		Document doc = build.parse(f);
		doc.getDocumentElement().normalize();

		NodeList test_list = doc.getElementsByTagName("test");
		Node test_node = test_list.item(0);
		NodeList class_list = ((Element) test_node)
				.getElementsByTagName("class");
		for (int i = 0; i < class_list.getLength(); i++) {
			Node class_node = class_list.item(i);
		//	String class_name = ((Element) class_node).getAttribute("name").substring(16);
			String class_name = ((Element) class_node).getAttribute("name").split(Pattern.quote("."))[5];
			
			className=class_name;
		//	String[] arr = new String[3];
		//	arr = class_name.substring(16).split(".");
			System.out.println("className:- " + class_name);
			NodeList test_method_list = ((Element) class_node)
					.getElementsByTagName("test-method");
			System.out.println("test-methods length:"+test_method_list.getLength());
			for (int j = 0; j < test_method_list.getLength(); j++) {
				Node test_method_node = test_method_list.item(j);
				String test_method_name = class_name + ":"
						+ ((Element) test_method_node).getAttribute("name");
				
				String test_method_status = ((Element) test_method_node)
						.getAttribute("status");
				if (!(test_method_name.contains("setUp")
						|| test_method_name.contains("beforeMethod")
						|| test_method_name.contains("afterMethod")
						|| test_method_name.contains("beforeClass")
						|| test_method_name.contains("afterClass")
						|| test_method_name.contains("beforeTest")
						|| test_method_name.contains("afterTest")
						|| test_method_name.contains("beforeSuite")
						|| test_method_name.contains("afterSuite") || test_method_name
							.contains("tearDown"))){
				
					System.out.println("testMethodName:"+test_method_name);
					System.out.println("test method status:"+test_method_status);
					if (test_method_status.contains("FAIL")) {
						System.out.println("Failed Test:"+test_method_name);
						NodeList test_method_exception = ((Element) test_method_node)
								.getElementsByTagName("exception");
						if (test_method_exception.getLength() > 0) {
							Node exception_node = ((Element) test_method_node)
									.getElementsByTagName("exception").item(0);
							String test_method_exception_message = ((Element) exception_node)
									.getElementsByTagName("message").toString();
							// String test_method_exception_fullStackTrace =
							// ((Element)
							// exception_node).getElementsByTagName("full-stacktrace").toString();
							class_of_exception = ((Element) exception_node)
									.getAttribute("class");
							if (class_of_exception
									.contains("WebDriverException")) {
								// broken.put(test_method_name,
								// class_of_exception+" \n   "+test_method_exception_message);
								broken.put(test_method_name, class_of_exception
										+ " \n   " + "");
							} else {
								// failed.put(test_method_name,
								// class_of_exception+" \n   "+test_method_exception_message);
								failed.put(test_method_name, class_of_exception
										+ " \n   " + "");
							}
						}
					} else if (test_method_status.contains("PASS")) {
						System.out.println("Passed Test:"+test_method_name);
						passed.put(test_method_name, class_of_exception);
					} else if (test_method_status.contains("SKIP")) {
						skipped.put(test_method_name, class_of_exception);
					}
				class_of_exception = null;
				}
			}
		}

		System.out.println("Failed: " + failed.size() + "Passed: "
				+ passed.size() + "Skipped: " + skipped.size() + "Broken: "
				+ broken.size());
		

		DBInserter.insertIntoDB(failed, broken, skipped, passed, buildNumber);
		
		
	}
	
	public static void main(String[] args){
		//int buildNumber=commonOps.generateRandomNumber(0, 999999);
		DateFormat df = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");

		// Get the date today using Calendar object.
      	Date today = Calendar.getInstance().getTime();        
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String reportDate = df.format(today);
		System.out.println(reportDate);
		try {
			
			ReportUtil.fetchTheResultsFromTestNG(reportDate);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		
	}

}
