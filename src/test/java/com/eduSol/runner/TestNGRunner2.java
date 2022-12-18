package com.eduSol.runner;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.eduSol.base.Comman_Method;
import com.eduSol.pom.LoginPage;
import com.eduSol.pom.PIM_Login;
import com.eduSol.utilities.ExcelReader;



public class TestNGRunner2 extends Comman_Method{
	LoginPage login; // constructor of the LoginPage class
	PIM_Login pim;
	
	@BeforeTest
	public void browserSetup() {
		broswerLaunch("chrome");
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}

	@DataProvider(name="dp")
	public Object[][] dataSupplier() {
		Object[][] finaldata = ExcelReader.readExcel();
		//return new Object[][]{};
		return finaldata;
	}
	
	@Test(dataProvider="dp",priority=0)
	public void testcase(Map<Object,Object> data) {
		login =new LoginPage();// constructor of the LoginPage class
		login.orangeHRMLogin(data,test);
	}
	
	@Test(priority=1)
	public void testcase2() {
		System.out.println("testcase-2");
		pim = new PIM_Login();
		//pim.addemployee();
	}
	
	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
}
