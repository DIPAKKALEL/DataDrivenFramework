package com.eduSol.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer{

	int count=0;
	int max_retry=2;//declare variable value how many times you want to retry
	@Override
	public boolean retry(ITestResult result) {
		if(count<max_retry) {
			count++;
			return true;
		}
		return false;
	}

}
