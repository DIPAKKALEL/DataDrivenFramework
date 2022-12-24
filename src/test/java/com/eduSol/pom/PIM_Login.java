package com.eduSol.pom;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.eduSol.base.Comman_Method;

public class PIM_Login extends Comman_Method {
	@FindBy(className = "oxd-brand-banner")
	WebElement orangeHRM_logo;
	@FindBy(css = "span[class='oxd-text oxd-text--span oxd-main-menu-item--name']")
	List<WebElement> PIM_btn;
	@FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--secondary']")
	WebElement add_btn;
	@FindBy(name = "firstName")
	WebElement firstname;
	@FindBy(name = "middleName")
	WebElement middlename;
	@FindBy(name = "lastName")
	WebElement lastname;
	@FindBy(css = "input[class='oxd-input oxd-input--active']")
//	List<WebElement> emp_id;
//	@FindBy(css = "button[class='oxd-icon-button employee-image-action']")//use specific locator
	List<WebElement> emp_id;
	@FindBy(css = "i[class='oxd-icon bi-plus']")
	WebElement pic_upload;
	@FindBy(css = "button[type='submit']")
	WebElement submit_btn;
	@FindBy(xpath = "//div[contains(@class,'orangehrm-horizontal-padding')]/h6")
	WebElement employee_title;
	public PIM_Login() {
		PageFactory.initElements(driver, this);
		
	}
	public void addemployee(Map<Object, Object>data, ExtentTest test) {
		softassert=new SoftAssert();
		if(stringValue(data,"AddEmployee").equals("Yes")){
			PIM_btn.get(1).click();
		hardwait(2000);
		getScreenshot("pim");
		add_btn.click();
		firstname.sendKeys(stringValue(data,"FirstName"));
		middlename.sendKeys(stringValue(data,"MiddleName"));
		lastname.sendKeys(stringValue(data,"LastName"));
		emp_id.get(1).clear();
		emp_id.get(1).sendKeys(stringValue(data,"EmpiID"));
		javascript_Click(pic_upload);
	//	sikuli("C:\\Users\\Deep\\New folder\\IMG_20220321_172048-min.jpg","filename","upload");
		robotClass("C:\\Users\\Deep\\New_folder\\","Capture");
		submit_btn.click();
		hardwait(3000);
	//	Assert.assertTrue(employee_title.getText().equalsIgnoreCase("Persona Details"));
		softassert.assertTrue(employee_title.getText().equalsIgnoreCase("Personal Details"));
		
	//	System.out.println("ran after soft assertion");
	test.log(Status.PASS, "Employee has been added sucesfully");
	softassert.assertAll();
		}
	}
}
