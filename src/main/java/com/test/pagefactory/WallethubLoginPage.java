package com.test.pagefactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.test.genericlib.BaseClass;

public class WallethubLoginPage {

	WebDriver driver;
	public WallethubLoginPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//li/a[contains(text(),'Login')]")
	private WebElement loginButton;
	
	@FindBy(xpath="//input[@type='text' and contains(@placeholder,'Email')]")
	private WebElement emailBox;
	
	@FindBy(xpath="//input[@type='password' and contains(@placeholder,'Password')]")
	private WebElement passwordBox;
	
	@FindBy(xpath="//button//span[contains(text(),'Login')]")
	private WebElement loginSubmitButton;
	
	
	
	public String getloginUrl() throws Exception {
		return BaseClass.prop.getProperty("wallethub.path");
	}
	
	public void openLoginConsole()throws Exception {
		loginButton.click();
	}
	
	public void setEmail(String email) throws Exception {
		emailBox.sendKeys(email);
	}
	
	public void setPassword(String password) throws Exception {
		passwordBox.sendKeys(password);
	}
	
	public void clickOnloginButton() throws Exception {
		loginSubmitButton.click();
	}
	
	
	
	
	
}
