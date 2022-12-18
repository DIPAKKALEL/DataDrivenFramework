package com.eduSol.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Comman_Method {
	public static WebDriver driver;
	public static String homepath = System.getProperty("user.dir");
	public static ExtentTest test;
	public static SoftAssert softassert;

	public static void broswerLaunch(String browserName) {

		switch (browserName) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// return driver;
	}

	public static void waitForClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void javascript_Click(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}

	/*
	 * //failed public static void javascript_Send(WebElement element,String val) {
	 * JavascriptExecutor js = (JavascriptExecutor) driver;
	 * js.executeScript("arguments[0].value='"+val+"';",element);
	 * 
	 * }
	 */
	public static void waitForDisplay(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void hardwait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getScreenshot(String screenshotName) {
		TakesScreenshot ts = (TakesScreenshot) driver;// downcasting
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(
				homepath + "\\src\\test\\resources\\Screenshot\\" + getDateTime() + "\\" + screenshotName + ".png");
		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destination =des.getAbsolutePath();
		return destination;
		
	}

	public static String getScreenshot(WebElement element, String screenshotName) {
		TakesScreenshot ts = (TakesScreenshot) element;// downcasting
		hardwait(3000);
		File src = ts.getScreenshotAs(OutputType.FILE);

		File des = new File(
				homepath + "\\src\\test\\resources\\Screenshot\\" + getDateTime() + "\\" + screenshotName + ".png");
		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destination =des.getAbsolutePath();
		return destination;

	}

	public static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh");
		String datetime = format.format(date);
		System.out.println(datetime);
		return datetime;
	}

	public static String getDateTimeMins() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh_mm");
		String datetime = format.format(date);
		System.out.println(datetime);
		return datetime;
	}

	static int num = 0;

	public static int sent_unique_number() {
		num++;
		return num;
	}

	public static void robotClass(String path,String image) {
		 // copy path to clipboard
		StringSelection strSelection = new StringSelection(path+image);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(strSelection, null); // keyboard button clicks
		try {
			Robot robot = new Robot();
			robot.delay(3000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			robot.delay(3000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void sikuli(String path, String image, String image1) {
		Screen sc = new Screen();
		Pattern pattern = new Pattern(homepath + "\\src\\test\\resources\\Screenshot\\Sikuli\\" + image + "");
		try {
			sc.type(pattern, path);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		Pattern pattern2 = new Pattern(homepath + "\\src\\test\\resources\\Screenshot\\Sikuli\\" + image1 + "");
		try {
			sc.click(pattern2);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public static ThreadLocal<WebDriver> driver1 = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return driver1.get();
	}

	public static void broswerLaunchThreadLocal(String browserName) {
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver1.set(new ChromeDriver());
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver1.set(new EdgeDriver());
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver1.set(new FirefoxDriver());
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver1.set(new ChromeDriver());
		}

		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	public String stringValue(Map<Object, Object> data, String key) {
		return data.get(key).toString();
	}
}
