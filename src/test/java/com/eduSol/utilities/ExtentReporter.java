package com.eduSol.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.eduSol.base.Comman_Method;

public class ExtentReporter extends Comman_Method {
	

	static ExtentSparkReporter htmlreport;
	static	ExtentReports report;

	
	public static ExtentReports initReport() {
		htmlreport = new ExtentSparkReporter(homepath + "\\src\\test\\resources\\Reports\\ExtentReport_"+getDateTimeMins()+".html");
		htmlreport.config().setDocumentTitle("Execution Report");
		htmlreport.config().setTheme(Theme.DARK);
		report = new ExtentReports();
		report.attachReporter(htmlreport);
		return report;
	}
	

}
