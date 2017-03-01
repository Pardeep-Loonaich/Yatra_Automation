package com.Yatra.TestScript;

import org.testng.annotations.Test;
import com.Yatra.Pages.HomePage;
import com.Yatra.Utils.*;
import java.net.MalformedURLException;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TC_1 {
	EnvironmentPropertiesReader environmentPropertiesReader;
	String webSite;
	private String workbookName = "testdata\\data\\Regression.xls";
	private String sheetName = "SampleTest";
	
  @BeforeTest(alwaysRun = true)
  public void beforeTest(ITestContext context) {
		  webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite")
					: context.getCurrentXmlTest().getParameter("webSite"));
	  }
  @Test(groups = { "desktop" },dataProviderClass = DataProviderUtils.class, dataProvider = "parallelTestDataProvider")
  public void TC_Yatra_001(String browser) throws MalformedURLException {
	  HashMap<String, String> testData = TestDataExtractor.initTestData(workbookName, sheetName);
	  final WebDriver driver = WebDriverFactory.get(browser);
		Log.testCaseInfo(testData);
		
		try{
			HomePage homePage = new HomePage(driver , webSite).get();
			Log.message("1. Successfully navigate to Yatra HomePage");
			
			homePage.enterOrgion("New Delhi");
			Log.message("2. Successfully Entered DEL in the origin field");
		}
		catch(Exception e){
			
		}finally {
			Log.endTestCase();
			driver.quit();
		} // finally
  }

  @AfterTest
  public void afterTest() {
	  
  }

}
