package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponent.abstractComponent;

public class checkoutPage extends abstractComponent{
	
	 WebDriver driver;

	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}

	@FindBy(css="input[placeholder='Select Country']")
	WebElement enterCountry;
	
	@FindBy(css="a[class*='submit']")
	WebElement submit;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	
	public void selectCountry(String cName) {
		Actions a = new Actions(driver);
		a.sendKeys(enterCountry, cName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
		
		}
		
	public confirmationPage submitOrder() {
		submit.click();
		return new confirmationPage(driver);
	}
	
}
