package com.eduSol.runner;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.eduSol.base.Comman_Method;
import com.eduSol.base.Constants;
import com.eduSol.pom.Google;
import com.eduSol.pom.LoginPage;
import com.eduSol.pom.PIM_Login;
import com.eduSol.utilities.ExcelReader;
import com.eduSol.utilities.ExtentReporter;
import com.eduSol.utilities.PropertyReader;

public class TestNGRunner_extent_reporeter extends Comman_Method {
	LoginPage login; // constructor of the LoginPage class
	PIM_Login pim;
	static Logger log=LogManager.getLogger(TestNGRunner_extent_reporeter.class);
	ExtentReports report;
	
	@BeforeTest
	public void beforeTest() {
		report = ExtentReporter.initReport();
		log.info("Execution has started");
	}

	@BeforeMethod
	public void browserSetup() {
		broswerLaunch(PropertyReader.getPropValue(Constants.BROWSER));
		driver.get(PropertyReader.getPropValue(Constants.URL));
		log.debug(PropertyReader.getPropValue(Constants.BROWSER)+" has been launched");
	}

	@DataProvider(name = "dp")
	public Object[][] dataSupplier() {  
		Object[][] finaldata = ExcelReader.readExcel();
		// return new Object[][]{};
		return finaldata;

	}
@Test(enabled=false)
public void t2() {
	test = report.createTest("GP launching started");//ExtentTest will be initializes by the report.createTest
//	log.info("test has started");
	Google gg= new Google();
	gg.Google_web_page_lauch();
//	log.info("Soft assertion execution started");
	softassert.assertAll();
}
	@Test(dataProvider = "dp", priority = 0,enabled=true)
	public void testcase(Map<Object, Object> data) {
		test = report.createTest(stringValue(data, "TestCaseObjective"));
		log.info("test has started");
		if (stringValue(data, "Execute").equals("Yes")) {
			login = new LoginPage();// constructor of the LoginPage class
			login.orangeHRMLogin(data,test);
			pim = new PIM_Login();
			pim.addemployee(data,test);
			log.info("Soft assertion execution staterd");
			softassert.assertAll();

		} else {
			test.log(Status.SKIP, "test has been skipped");
			log.error("test has been skipped");
			throw new SkipException("Test case not run");
		}

	}

	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@AfterTest
	public void afterTest() {
		report.flush();
		
	}

}
