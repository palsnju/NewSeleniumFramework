package com.test.module1;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.apache.log4j.Logger;

import org.testng.annotations.Parameters;

import com.test.genericlib.BaseClass;
import com.test.genericlib.ComMethods;

import com.test.pagefactory.WalletHubReviewPage;
import com.test.pagefactory.WallethubLoginPage;
import com.test.pagefactory.WalletubReviewVerificationPage;

import junit.framework.Assert;

public class Assignment2 extends BaseClass {
	public Logger log = Logger.getLogger(Assignment2.class);
	WallethubLoginPage wPage;
	WalletHubReviewPage rPage;
	WalletubReviewVerificationPage rVerificationPage;
	@BeforeClass
	public void beforeClass() throws Exception {
		log.info("controller Arrived to the Assignmet2 class");
		wPage=new WallethubLoginPage(driver);
		rPage=new WalletHubReviewPage(driver);
		rVerificationPage =new WalletubReviewVerificationPage(driver);
	}
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		String url=wPage.getloginUrl();
		log.info("redirecting to the link "+url);
		//url=envUrl+url; //Contructing the environment url
		log.info("Url constructed for the environment "+url);
		ComMethods.navigateToUrl(url);
		log.info("Openned "+url);
		
	}
	
	  @Parameters({"email", "password", "comment"})  
	  @Test(description = "Login User > Home > create post > submit post")
	  public void oTest1(String email, String password, String comment) throws Exception {
	    wPage.openLoginConsole(); Thread.sleep(2000);
	    log.info("clicked and Opnennd the login console");
	    wPage.setEmail(email); Thread.sleep(2000);
	    log.info("set Email data "+email);
	    wPage.setPassword(password); Thread.sleep(2000);
	    log.info("set password "+password);
	    wPage.clickOnloginButton(); Thread.sleep(2000);
	    log.info("clicked on login button");
	    String url=rPage.getReviewUrl(); 
	    ComMethods.navigateToUrl(url); Thread.sleep(2000);
	    log.info("Moved "+url);
	    rPage.creditScorePopUpHandle(); 
	    log.info("clicked and handlled the credit score report");
	    rPage.moveToStartsAndClickedOnFourth();
	    log.info("moved to rating star and clicked on 4 th star");
	    rPage.clickedOnpolicyList();  Thread.sleep(2000);
	    log.info("clicked on the plicy list");
	    rPage.selectHealthPolicy();
	    log.info("Selected health policy from list");
	    rPage.selectHealthPolicyStar();
	    log.info("submit the health start rating 4");
	    rPage.clearReviewConsole();
	    log.info("Cleard the review console");
		String newComment=comment;
		int len=comment.length()*comment.length();
			 for(int i=0; i<len; i++) {
			    	if(i<len) {newComment=newComment+", ";}
			    	newComment=newComment+comment;
			    }
		log.info("review comment counstructed"+newComment.substring(0, 201));
	    rPage.filledReviewComment(newComment.substring(0, 201));
	    log.info("Review pased to the console");
	    rPage.submitReview();  Thread.sleep(2000);
	    log.info("Review comment submitted ");
	    rPage.reviewPostSuccesfullySubmitCheck();
	    log.info("Review comment submitted acknolegment check ");
	    url=rVerificationPage.getVerifyReviewUrl();
	    ComMethods.navigateToUrl(url);
	    log.info("Moved "+url);
	    String bodyText=rVerificationPage.getBodyText();
	    log.info("Fetched the posted review comment");
	    Assert.assertEquals(bodyText.contains(newComment.substring(0, 201)), true);
	    log.info("Verification done for the review post");
	    
	  }
  
	  @Test(description = "Login User > Home > create post > submit post")
	  public void oTest2() throws Exception {
	    wPage.openLoginConsole(); Thread.sleep(2000);
	    log.info("clicked and Opnennd the login console");
	    String email=prop.getProperty(envName+".wallethub.email");
	    wPage.setEmail(email); Thread.sleep(2000);
	    log.info("set Email data "+email);
	    String password=prop.getProperty(envName+".wallethub.password");
	    wPage.setPassword(password); Thread.sleep(2000);
	    log.info("set password "+password);
	    wPage.clickOnloginButton(); Thread.sleep(2000);
	    log.info("clicked on login button");
	    String url=rPage.getReviewUrl(); 
	    ComMethods.navigateToUrl(url); Thread.sleep(2000);
	    log.info("Moved "+url);
	    rPage.creditScorePopUpHandle(); 
	    log.info("clicked and handlled the credit score report");
	    rPage.moveToStartsAndClickedOnFourth();
	    log.info("moved to rating star and clicked on 4 th star");
	    rPage.clickedOnpolicyList();  Thread.sleep(2000);
	    log.info("clicked on the plicy list");
	    rPage.selectHealthPolicy();
	    log.info("Selected health policy from list");
	    rPage.selectHealthPolicyStar();
	    log.info("submit the health start rating 4");
	    rPage.clearReviewConsole();
	    log.info("Cleard the review console");
	    String comment=prop.getProperty(envName+".wallethub.comment");
		String newComment=comment;
		int len=comment.length()*comment.length();
			 for(int i=0; i<len; i++) {
			    	if(i<len) {newComment=newComment+", ";}
			    	newComment=newComment+comment;
			    }
		log.info("review comment counstructed"+newComment.substring(0, 201));
	    rPage.filledReviewComment(newComment.substring(0, 201));
	    log.info("Review pased to the console");
	    rPage.submitReview();  Thread.sleep(2000);
	    log.info("Review comment submitted ");
	    rPage.reviewPostSuccesfullySubmitCheck();
	    log.info("Review comment submitted acknolegment check ");
	    url=rVerificationPage.getVerifyReviewUrl();
	    ComMethods.navigateToUrl(url);
	    log.info("Moved "+url);
	    String bodyText=rVerificationPage.getBodyText();
	    log.info("Fetched the posted review comment");
	    Assert.assertEquals(bodyText.contains(newComment.substring(0, 201)), true);
	    log.info("Verification done for the review post");
	    
	  }
  
  
	@AfterMethod
	public void afterMethod() throws Exception {
		log.info("Test execution done ");
	}
	
	@AfterClass
	public void afterClass() throws Exception {
		log.info("controller leave the Assignmet2 class");
	}
	

}
