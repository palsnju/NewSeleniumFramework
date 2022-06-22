package com.test.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.genericlib.BaseClass;
import com.test.genericlib.ComMethods;

public class WalletHubReviewPage {
	
	WebDriver driver;
	public WalletHubReviewPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='footer_cta']/span/span/i[2]")
	private WebElement creditScoreReportPopUp;
	
	@FindBy(xpath="//*[contains(@class,'wh-rating rating_5')]")
	private WebElement ratingStar;
	
	@FindBy(xpath="//*[contains(@class,'wh-rating-choices-holder')]/a[4]")
	private WebElement fourthRating;
	
	@FindBy(xpath="//*[@class='dropdown-list-new']")
	private WebElement policyList;
	
	@FindBy(xpath="//a[contains(@data-target,'Health')]")
	private WebElement policyHealth;
	
	@FindBy(xpath="//*[@class='bf-icon-star-empty star bstar bf-icon-star'][4]")
	private WebElement policyHealthStar;
	
	@FindBy(xpath="//textarea[@name='review' and @id='review-content']")
	private WebElement reviewConsole;
	
	@FindBy(xpath="//h1/span[contains(text(),'Your')]/a[contains(text(),'has been posted')]")
	private WebElement reviewPostSuccessMessage;
	

	
	public String getReviewUrl() {
		return BaseClass.prop.getProperty("wallethub.reviewpath");
	}
	
	
	public void creditScorePopUpHandle() throws Exception {
		 ComMethods.explicitWaitToClicked(creditScoreReportPopUp, 60);
		 creditScoreReportPopUp.click();
	}
	
	public void moveToStartsAndClickedOnFourth() throws Exception {
		ComMethods.inputByActions().moveToElement(ratingStar).pause(500).moveToElement(fourthRating).click().pause(500).build().perform();
	}
	
	public void clickedOnpolicyList() throws Exception {
		 ComMethods.explicitWaitToClicked(policyList, 60);
		 policyList.click();
	}
	
	public void selectHealthPolicy() throws Exception {
		policyHealth.click();
	}
	
	public void selectHealthPolicyStar() throws Exception {
		policyHealthStar.click();
	}
	
	public void clearReviewConsole() throws Exception {
		reviewConsole.clear();
	}
	
	public void filledReviewComment(String comment) throws Exception {
		reviewConsole.sendKeys(comment);
	}
	
	public void submitReview()  throws Exception{
		reviewConsole.submit();
	}
	
	public void reviewPostSuccesfullySubmitCheck() throws Exception {
		ComMethods.explicitWaitVisiblity(reviewPostSuccessMessage, 60);
	}
	
	
	

}
