package rahulshettyacademy.test;

import rahulshettyacademy.resources.Retry;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.productCatalogue;
import rahulshettyacademy.resources.baseTest;

public class ErrorValidations extends baseTest {

	@Test(groups= {"errorHandling"}, retryAnalyzer= Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {

		lp.loginApp("sana14@gmail.com", "sdfghjkl:1");
		Assert.assertEquals("Incorrect email password.", lp.errormsg());
	}
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String pName = "ADIDAS ORIGINAL";
		productCatalogue productCatalogue = lp.loginApp("sana14@gmail.com", "Asdfghjkl:1");
		List<WebElement> productList = productCatalogue.productList();
		productCatalogue.addProductToCart(pName);
		cartPage cp = productCatalogue.goToCart();
		Boolean match = cp.cartProductList("ADIDAS44ORIGINAL");
		Assert.assertFalse(match);
	}
}