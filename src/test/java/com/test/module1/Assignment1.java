package com.test.module1;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.genericlib.BaseClass;
import com.test.genericlib.ComMethods;
import com.test.genericlib.ExtentTestManager;
import com.test.genericlib.FileUtils;
import com.test.genericlib.GetData;
import com.test.pagefactory.HomePage;
import com.test.pagefactory.LoginPage;
import com.test.pagefactory.WelComePage;

public class Assignment1 extends BaseClass {
	public Logger log = Logger.getLogger(Assignment1.class);
	LoginPage login;
	HomePage home;
	WelComePage welcom;

	@BeforeClass
	public void beforeClass() throws Exception {
		log.info("controller Arrived to the Assignmet1 class");
		login=new LoginPage(driver);
		home=new HomePage(driver);
		welcom=new WelComePage(driver);
	}
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		String url=login.getUrl();
		log.info("redirecting to the link "+url);
		//url=envUrl+url; //Contructing the environment url
		log.info("Url constructed for the environment "+url);
		ComMethods.navigateToUrl(url);
		
	}
	 //Test execution with parameter data provided via testNg file
	@Parameters({"data"})  
	@Test(description = "Login User > Home > create post > submit post") 
	  public void facebookTest1(String data) throws Exception {
		ExtentTestManager.getTest().assignCategory("Assignment1_FacebookPost");
		String user=login.getUserName();
		 login.setEmail(user);
		 log.info("Email box filled with "+user);
		String password=login.getPassword();
		 login.setPassword(password);
		 log.info("Password box filled with "+password);
		 login.clickOnLogin();
		 log.info("clicked on login");
		 welcom.clickOnHomeIcon();
		 log.info("click on home icon");
		 home.clickOnNewPostBox(60);
		 log.info("click on home postInputBox");
		 home.postStatus(data);
		 log.info("send the message to the postInputBox");
		 home.clickOnPostButton();
		 log.info("Clicked on post submit button");
		 Thread.sleep(6000);
		 Assert.assertEquals(driver.getPageSource().contains(data), true, data+" feed post failure");
		 log.info("Verfication of post content done...");
	  }
	
	//below test case is disable as it is same as above
	//Test execution with data provider
	@Test(description = "Login User > Home > create post > submit post", dataProvider = "data-provider", enabled = false)
	public void facebookTest2(String user, String password, String data) throws Exception {
	  log.info("Test data feched from dataProvider : "+user+"   "+password+"  "+data);
	  ExtentTestManager.getTest().assignCategory("Assignment1_FacebookPost");
		 login.setEmail(user);
		 log.info("Email box filled with "+user);
		 login.setPassword(password);
		 log.info("Password box filled with "+password);
		 login.clickOnLogin();
		 log.info("clicked on login");
		 welcom.clickOnHomeIcon();
		 log.info("click on home icon");
		 home.clickOnNewPostBox(60);
		 log.info("click on home postInputBox");
		 home.postStatus(data);
		 log.info("send the message to the postInputBox");
		 home.clickOnPostButton();
		 log.info("Clicked on post submit button");
		 Thread.sleep(6000);
		 Assert.assertEquals(driver.getPageSource().contains(data), true, data+" feed post failure");
		 log.info("Verfication of post content done...");
	}
	
	@AfterMethod
	public void afterMethod() throws Exception {
		log.info("Test execution done and loggedout");
	}
	
	@AfterClass
	public void afterClass() throws Exception {
		log.info("controller leave the Assignmet1 class");
	}
	
	//Data provider fetching data from xlsx file
	@DataProvider(name = "data-provider")
	  public Object [][] getData() throws Exception {
		String fileName=GetData.TestDataRepo.data();
		Object [][]obj = null;
		LinkedHashMap<String, String> createUserData=new LinkedHashMap<String, String>();
		FileUtils fLib=new FileUtils();
		try {
			int row=fLib.getExcelRows(fileName, 0);
			int col=0;
			obj=new Object [row-1][];
			for(int i=1; i<row; i++ )
			{
				col=fLib.getExcelCell(fileName, 0);
				obj[i-1]=new Object[col];
				for(int j=0; j<col; j++) {
				try {
					createUserData.put(fLib.getExcelData(fileName, 0, 0, j), fLib.getExcelData(fileName, 0, i, j));
					
				} catch (Throwable e) {
					}
				
				}
				obj[i-1][0]=createUserData.get("Email");
				obj[i-1][1]=createUserData.get("Password");
				obj[i-1][2]=createUserData.get("FeedPost");
				
			}
		}catch(Throwable e) {
				
	}
		return obj;
	}
	
	
	
}
