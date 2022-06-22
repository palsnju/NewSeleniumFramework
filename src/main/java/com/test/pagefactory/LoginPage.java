package com.test.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.genericlib.BaseClass;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage (WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='email']")
	private WebElement emailBox;
	
	@FindBy(xpath="//*[@id='pass']")
	private WebElement passwordBox;
	
	@FindBy(xpath="//button[@name='login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//*[@id=\"u_0_a_Lt\"]/div[3]/a")
	private WebElement forgotButton;
	
	public String getUrl() throws Exception {
		return BaseClass.prop.getProperty("facebook.path");
	}
	
	public String getUserName() throws Exception {
		return BaseClass.prop.getProperty(BaseClass.envName+".facebook.user");
	}
	public String getPassword() throws Exception {
		return BaseClass.prop.getProperty(BaseClass.envName+".facebook.password");
	}
	
	public void setEmail(String email) throws Exception {
		emailBox.clear();
		emailBox.sendKeys(email);
	}
	
	public void setPassword(String password) throws Exception {
		passwordBox.clear();
		passwordBox.sendKeys(password);
	}
	
	public void clickOnLogin() throws Exception {
		loginButton.click();
	}
	
	public void clickOnForgotPassword() throws Exception {
		forgotButton.click();
	}
	
}
