package com.test.genericlib;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentTestManager {
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static  ExtentReports extent ;
	static String testName=null;
	static String description=null;
    private static String df = new SimpleDateFormat("yyyyMMddhhss").format(new Date());
    private static String reportFileName = "TestProject-Report"+df+".html";
	
	public static  void setInstance() {
		 extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("ReporterHTML"+System.getProperty("file.separator")+reportFileName);
		//View config
		spark.viewConfigurer()
	    .viewOrder()
	    .as(new ViewName[] { 
		   ViewName.DASHBOARD, 
		   ViewName.TEST, 
		   ViewName.CATEGORY, 
		   ViewName.AUTHOR, 
		   ViewName.DEVICE, 
		   ViewName.EXCEPTION, 
		   ViewName.LOG 
		})
	  .apply();
		
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("TestProject");
		spark.config().setEncoding("utf-8");
		spark.config().setReportName(reportFileName);
		spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		
		//Environament info
		
		
		extent.attachReporter(spark);
		
	}
	public ExtentReports getInstance() {
		return extent;
	}
	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest(){
	
		setEnv(System.getProperty("envName"));
		setEnvURL(System.getProperty("envUrl"));
		setBrowser(BaseClass.cap.getBrowserName());
		setBrowserVersion(BaseClass.cap.getVersion());
		setOsType(BaseClass.cap.getPlatform().toString());
		extent.flush();
	}

	public static synchronized ExtentTest startTest() {
		ExtentTest test = extent.createTest(testName, description);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
	public static String getValue() {
		return description;
	}
	
	public static void codeBlock(String code1, String code2) {
		ExtentTestManager.getTest().info(MarkupHelper.createCodeBlock(code1, code2));
	}
	
	public static Markup lable(String value) {
		return MarkupHelper.createLabel("<a href=\""+value+"\">Test_Case</a>", ExtentColor.BLUE);
	}
	
	public static void childNode(String name,String value) {
		ExtentTestManager.getTest().createNode(name).fail(value);
	}
	
	public static void childNodeSkip(String name,String value) {
		ExtentTestManager.getTest().createNode(name).skip(value);
	}
	
	public static void setValue(String testName, String description) {
		ExtentTestManager.testName=testName;
		ExtentTestManager.description=description;
	}
	public static void setEnv(String env) {
		extent.setSystemInfo("ENVIRONAMENT", env);
	}
	public static void setEnvURL(String url) {
		extent.setSystemInfo("ENVIRONAMENT_URL", url);
	}
	public static void setBrowser(String browser) {
		extent.setSystemInfo("BROWSER_NAME", browser);
	}
	public static void setBrowserVersion(String browserVer) {
		extent.setSystemInfo("BROWSER_VERSION", browserVer);
	}
	public static void setOsType(String os) {
		extent.setSystemInfo("OS", os);
	}
}