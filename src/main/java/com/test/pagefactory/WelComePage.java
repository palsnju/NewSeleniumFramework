package com.test.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelComePage {

	WebDriver driver;
	public WelComePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@aria-label='Facebook' and @role='navigation']/ul/li[1]")
	private WebElement homeIcon;
	
	
	public void clickOnHomeIcon() throws Exception {
		homeIcon.click();
	}
	

}
