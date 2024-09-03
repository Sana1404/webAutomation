package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponent.abstractComponent;

public class cartPage extends abstractComponent {

	WebDriver driver;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".infoWrap")
	List<WebElement> cartProducts;

	@FindBy(css = ".subtotal.cf.ng-star-inserted button")
	WebElement checkout;

	By productName = By.cssSelector(".cartSection h3");

	public Boolean cartProductList(String pName) {

		Boolean match = cartProducts.stream()
				.anyMatch(s -> s.findElement(productName).getText().equalsIgnoreCase(pName));
		return match;

	}

	public checkoutPage checkOutPage() {
		checkout.click();
		return new checkoutPage(driver);
			

	}
}
