package com.test.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.genericlib.BaseClass;

public class WalletubReviewVerificationPage {
	
	WebDriver driver;
	public WalletubReviewVerificationPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(tagName="body")
	private WebElement body;
	
	public String getVerifyReviewUrl() throws Exception {
		return BaseClass.prop.getProperty("wallethub.verifyreviewpath");
	}
	
	public String getBodyText() throws Exception {
		return body.getText();
	}

}
