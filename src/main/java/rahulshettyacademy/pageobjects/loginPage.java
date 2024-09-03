package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponent.abstractComponent;

public class loginPage extends abstractComponent {
	
	 WebDriver driver;

	public loginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}	
	//Page Factory
	@FindBy(id="userEmail")
	WebElement emailId;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='lyInOut']")
	WebElement errorMsg;
	
	
	public productCatalogue loginApp(String email, String pwd) {
		
		emailId.sendKeys(email);
		password.sendKeys(pwd);
		submit.click();
		productCatalogue productCatalogue=new productCatalogue(driver);
		return productCatalogue;

	}
	
	public String errormsg() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
		
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	
	
	
	
}
