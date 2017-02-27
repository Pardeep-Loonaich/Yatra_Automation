package com.Yatra.TestScript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentTest {

	ExtentReports extent;
	com.relevantcodes.extentreports.ExtentTest test;
	WebDriver driver;

	@BeforeClass
	public void setupTest() {
		extent = ExtentManager.Instance();
		driver = new FirefoxDriver();
	}

	@Test
	public void SampleExtentTest() {
		try {
			driver.get("https://www.yatra.com/");
			test = extent.startTest("ExtentTest", "Verify Yatra HomePage");
			String actualTitle = driver.getTitle();
			System.out.println(actualTitle);

			// 1. verifying Yatra title name
			if (driver.getTitle().contains(actualTitle))
				test.log(LogStatus.PASS, driver.getTitle() + " contain " + "Flights");
			else
				test.log(LogStatus.FAIL, driver.getTitle() + " doesn't contain " + "Flights");

			// 2. verifying Page1
			test.log(LogStatus.PASS, "Page1");

			// 3. verifying Page2
			test.log(LogStatus.PASS, "Page2");

			// 4. verifying Page3
			test.log(LogStatus.PASS, "Page3");

			// 5. verifying Page4
			test.log(LogStatus.PASS, "Page4");

		} catch (Exception e) {
			test.log(LogStatus.ERROR, e.getMessage());
		}
	}

	@AfterClass
	public void teardownTest() {
		extent.endTest(test);
		extent.flush();
		extent.close();
		driver.quit();
	}
}