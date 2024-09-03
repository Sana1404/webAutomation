package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponent.abstractComponent;

public class productCatalogue extends abstractComponent {
	
	 WebDriver driver;

	public productCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}	
	//Page Factory
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productBy= By.cssSelector(".col-lg-4");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	By toastMsg= By.cssSelector("#toast-container");
	
	public List<WebElement> productList() {
		waitForElementToAppear(productBy);
		return products;
		
	}
	
	public WebElement getProductByName(String pName) {
		WebElement actualProduct = productList().stream().filter(s -> s.findElement(By.cssSelector("b")).getText().equals(pName)).findFirst().orElse(null);
		return actualProduct;
	}
	
	public void addProductToCart(String pName) throws InterruptedException {
		
		WebElement prod=getProductByName(pName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);
		waitForElementToDisappear(spinner);

	}
	
	
	
	
	
}
