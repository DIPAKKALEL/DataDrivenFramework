package com.eduSol.base;


import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestNG_Listeners extends Comman_Method implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("=======On Test Start========");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("=======On Test Success========");
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("=======On Test Skipped========");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("=======On Test Failure========");
		
		// getScreenshot( "FailedSS_"+sent_unique_number());
		test.log(Status.FAIL, "test is failed");
		hardwait(3000);
		test.addScreenCaptureFromPath(getScreenshot("FailedSS_" + sent_unique_number()));
	}
}
