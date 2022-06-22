package com.test.genericlib;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;


public class TestListener implements ITestListener {

	public static Logger log = Logger.getLogger(TestListener.class);
	String testName="Test Name";
	String testObj="Test Objective";
	String exptd="Expected Results";
	
	public void onStart(ITestContext context) {
		log.info("*** Test Suite "  + " started ***");
		ExtentTestManager.setInstance();
	}

	public void onFinish(ITestContext context) {
		log.info(("*** Test Suite ending ***"));
		ExtentTestManager.endTest();
	}
	/* If you want method name provided as an Description replace with line 36
	 * ExtentTestManager.setValue(result.getName(), result.getMethod().getMethodName());
	 * Descriptions are mandatory to provide with @Test annotation as per below line -36
	 */
	public void onTestStart(ITestResult result) {
			ExtentTestManager.setValue(result.getMethod().getDescription(), result.getMethod().getMethodName());
			log.info(("*** Running test method "+result.getInstanceName()+result.getName()+" ***"));
			ExtentTestManager.startTest();
	}

	public void onTestSuccess(ITestResult result) {
			ExtentTestManager.getTest().log(Status.PASS, result.getMethod().getMethodName());
			log.info("*** "+result.getInstanceName()+result.getName()+"***Pass***");
	}

	public void onTestFailure(ITestResult result)  {
		
		String tName = result.getMethod().getMethodName()+new SimpleDateFormat("yyyyMMddhhss").format(new Date());
		EventFiringWebDriver edriver = new EventFiringWebDriver(BaseClass.driver);
		File srcFile = edriver.getScreenshotAs(OutputType.FILE);
		File dstFile = new File("./Screenshot/" +tName+ ".jpeg");
		try
		{
			log.info("ScreenShot avaialbe for the failure cause with name :"+tName);
			FileUtils.copyFile(srcFile, dstFile);
		}
		catch(IOException e)
		{
			log.info("Unnable to take screenShot"+e.getMessage());
			e.printStackTrace();
		}
			ExtentTestManager.getTest().log(Status.FAIL, result.getName());
			ExtentTestManager.childNode(result.getName(),result.getThrowable().toString());
			log.error("***"+result.getInstanceName()+result.getName()+"***Fail***", result.getThrowable());
			ExtentTestManager.getTest().addScreenCaptureFromPath(System.getProperty("user.dir")+"\\Screenshot\\"+tName+".jpeg", "Please find the failure cause in the attached media");
	}
	
	public void onTestSkipped(ITestResult result) {
			ExtentTestManager.getTest().log(Status.SKIP,result.getMethod().getMethodName());
			ExtentTestManager.childNodeSkip(result.getName(),result.getThrowable().toString());
			log.trace("***"+result.getInstanceName()+result.getName()+"***Skipped***", result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			ExtentTestManager.getTest().log(Status.WARNING, result.getMethod().getMethodName());
			ExtentTestManager.childNode(result.getName() ,result.getThrowable().toString());
			log.warn("***"+result.getInstanceName()+result.getName()+"***FailedButWithinSuccessPercentage**", result.getThrowable());
	}
	
	

}
