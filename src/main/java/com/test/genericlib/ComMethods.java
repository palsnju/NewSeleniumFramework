package com.test.genericlib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComMethods {
	
	public static void gotoUrl(String url) throws Exception {
		BaseClass.driver.get(url);
	}
	public static void navigateToUrl(String url) throws Exception {
		BaseClass.driver.navigate().to(url);
	}
	public static void navigateToBack() throws Exception{
		BaseClass.driver.navigate().back();
	}
	public static void navigateToForword() throws Exception{
		BaseClass.driver.navigate().forward();
	}
	public static void navigateToRefresh()throws Exception {
		BaseClass.driver.navigate().refresh();
	}
	public static void implicitWait() throws Exception
	{
		BaseClass.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	public static void waitForPageToLoad()throws Exception 
	{
		BaseClass.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}
	public static void maximize() throws Exception {
		BaseClass.driver.manage().window().maximize();
	}
    public static void alertHandle() throws Exception
    {
    	BaseClass.driver.switchTo().alert().accept();
    }
    public static void alertCancel() throws Exception
    {
    	BaseClass.driver.switchTo().alert().dismiss();
    }
    public static void alertHandleSendKeys(String data) throws Exception
    {
    	Alert a=BaseClass.driver.switchTo().alert();
    			a.sendKeys(data);
    			a.accept();
    }
    public static void alertCancelSendKeys(String data) throws Exception
    {
    	Alert a=BaseClass.driver.switchTo().alert();
		a.sendKeys(data);
		a.dismiss();
    }
    public static Actions inputByActions() throws Exception {
    return new Actions(BaseClass.driver);
    }
    public static void selectText(WebElement swb, String option) throws Exception
    {
    	Select sel = new Select(swb);
    	sel.selectByVisibleText(option);
    }
    public static void selectIndex(WebElement swb, int option) throws Exception
    {
    	Select sel = new Select(swb);
    	sel.selectByIndex(option);
    }
    public static void selectXpath(WebElement swb, String option) throws Exception
    {
    	Select sel = new Select(swb);
    	sel.selectByValue(option);
    }
    
   public static WebElement explicitWait(By ele, long seconds) throws Exception {
    	WebDriverWait wait=new WebDriverWait(BaseClass.driver, seconds);
    	wait.until(ExpectedConditions.presenceOfElementLocated(ele));
    	return BaseClass.driver.findElement(ele);
    }
    
   public static void explicitWaitToClicked(WebElement ele, long seconds) throws Exception {
   	WebDriverWait wait=new WebDriverWait(BaseClass.driver, seconds);
   	wait.until(ExpectedConditions.elementToBeClickable(ele));
   	
   }
   
   public static void explicitWaitVisiblity(WebElement ele, long seconds) throws Exception {
	   	WebDriverWait wait=new WebDriverWait(BaseClass.driver, seconds);
	   	wait.until(ExpectedConditions.visibilityOf(ele));
	   	
	   }

}
