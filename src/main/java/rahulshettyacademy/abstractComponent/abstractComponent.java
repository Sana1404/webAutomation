package rahulshettyacademy.abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.orderPage;

public class abstractComponent {

	
	 WebDriver driver;
	public abstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);		

	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orders;
	

	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
}
	
	public void waitForWebElementToAppear(WebElement by) {
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(by));
}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(2000);
	}
	
	public cartPage goToCart() {
		cart.click();
		cartPage cp=new cartPage(driver);
		return cp;
	}
	
	public orderPage goToOrders() {
		orders.click();
		orderPage op=new orderPage(driver);
		return op;
	}
	
	
	
}
