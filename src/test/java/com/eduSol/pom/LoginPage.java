package com.eduSol.pom;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.eduSol.base.Comman_Method;

public class LoginPage extends Comman_Method {

	@FindBy(name = "username")
	WebElement userid;
	@FindBy(name = "password")
	WebElement password;
	@FindBy(css = "button[type='submit']")
	WebElement login_btn;
	@FindBy(className = "oxd-brand-banner")
	WebElement orangeHRM_logo;
	@FindBy(className = "employee-image")
	WebElement profilePic;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void orangeHRMLogin(Map<Object,Object> data, ExtentTest test) {
		softassert= new SoftAssert();
	//	waitForClick(userid);
		userid.sendKeys(data.get("User id").toString());
		password.sendKeys(data.get("Password").toString());
		login_btn.click();
		test.log(Status.INFO, "Login is sucesfull");
		
		Assert.assertTrue(orangeHRM_logo.isDisplayed());
		hardwait(3000);
		getScreenshot("fail");
	//	softassert.assertTrue(!orangeHRM_logo.isDisplayed());
		waitForDisplay(profilePic);
		hardwait(1000);
	//	getScreenshot("LoginSucess");
	}
	
}
