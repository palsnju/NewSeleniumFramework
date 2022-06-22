package com.test.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.genericlib.ComMethods;

public class HomePage {

	WebDriver driver;
	ComMethods com=new ComMethods();
	public HomePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//html/body/div[1]/div/div[1]/div/div[4]/div/div/div[1]/div/div[2]/div/div/div/form/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div[1]/p")
	private WebElement newPostBox;
	//div[@contenteditable='true' and @role='textbox']
	
	@FindBy(xpath="//div[@aria-label='Post' and @role='button']/div")
	private WebElement postButton;
	
	@FindBy(xpath="//html/body/div[1]/div/div[1]/div/div[3]/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[3]/div/div[4]/div/div[1]/div/div/div/div/div/div/div/div/div/div/div[2]/div/div[3]/div/div/div/div")
	private WebElement newPost;
	
	public void clickOnNewPostBox(int seconds) throws Exception {
		By ele=By.xpath("//div[@aria-label='Create a post']/div[1]/div[@role='button'][1]/div[@data-visualcompletion='ignore']/../div/span");
		ComMethods.explicitWait(ele, seconds).click();
	}
	
	public void postStatus(String data) throws Exception {
		Assert.assertEquals(true, newPostBox.isEnabled());
		newPostBox.sendKeys(data);
	}
	
	public void clickOnPostButton() throws Exception {
		ComMethods.inputByActions().moveToElement(postButton).pause(5000).click().build().perform();
	}
	
	public String getNewPost() throws Exception {
		return newPost.getText();
	}
	
	
	
}
