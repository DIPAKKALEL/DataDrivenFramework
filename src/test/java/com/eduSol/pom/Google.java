package com.eduSol.pom;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.eduSol.base.Comman_Method;

public class Google extends Comman_Method {
	@FindBy(xpath = "//input[@title='Search']")
	WebElement google_Search_Box;
	@FindBy(css = "input[value='Google Search'][type='submit']")
	List<WebElement> google_searchbtn;
	@FindBy(css = "input[class='RNmpXc'][type='submit']")
	List<WebElement> Feel_luck_btn;
	@FindBy(css = "button[aria-label='No thanks']")
	WebElement noThnkBtn;

	public Google() {
		PageFactory.initElements(driver, this);
	}

	public void Google_web_page_lauch() {
		google_searchbtn.get(1);
		softassert = new SoftAssert();
		softassert.assertTrue(google_Search_Box.isDisplayed());
		hardwait(10000);
		//noThnkBtn.click();
		softassert.assertTrue(google_searchbtn.get(1).isDisplayed());
		// waitforDisplay(Feel_luck_btn);
		System.out.println("FEEL LUCK !!");
		softassert.assertTrue(Feel_luck_btn.get(1).isDisplayed());
		softassert.assertAll();
	}

}
