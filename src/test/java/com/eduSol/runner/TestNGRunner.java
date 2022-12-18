package com.eduSol.runner;

import java.io.IOException;
import java.util.Map;

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
import com.eduSol.pom.LoginPage;
import com.eduSol.pom.PIM_Login;
import com.eduSol.utilities.ExcelReader;
import com.eduSol.utilities.PropertyReader;

public class TestNGRunner extends Comman_Method {
	LoginPage login; // constructor of the LoginPage class
	PIM_Login pim;


	
	@BeforeMethod
	public void browserSetup() {
		broswerLaunch(PropertyReader.getPropValue(Constants.BROWSER));
		driver.get(PropertyReader.getPropValue(Constants.URL));
	}

	@DataProvider(name = "dp")
	public Object[][] dataSupplier() {
		Object[][] finaldata = ExcelReader.readExcel();
		// return new Object[][]{};
		return finaldata;

	}

	@Test(dataProvider = "dp", priority = 0)
	public void testcase(Map<Object, Object> data) {
		
		
		if (stringValue(data, "Execute").equals("Yes")) {
			login = new LoginPage();// constructor of the LoginPage class
			login.orangeHRMLogin(data,test);
			pim = new PIM_Login();
			pim.addemployee(data,test);
			
		} else {
			throw new SkipException("Test case not run");
		}

	}

	@Test(dataProvider = "dp", priority = 1, enabled = false)
	public void add_employee(Map<Object, Object> data) {
		login = new LoginPage();
		login.orangeHRMLogin(data,test);
		pim = new PIM_Login();
		pim.addemployee(data,test);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@AfterTest
	public void afterTest() {
		
	}

}
