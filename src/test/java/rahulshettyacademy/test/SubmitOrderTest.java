package rahulshettyacademy.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkoutPage;
import rahulshettyacademy.pageobjects.confirmationPage;
import rahulshettyacademy.pageobjects.orderPage;
import rahulshettyacademy.pageobjects.productCatalogue;
import rahulshettyacademy.resources.baseTest;

public class SubmitOrderTest extends baseTest {

	String productName="ZARA COAT 3";
	@Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		
		productCatalogue productCatalogue= lp.loginApp(input.get("email"), input.get("password"));
		List<WebElement> productList= productCatalogue.productList();
		productCatalogue.addProductToCart(input.get("product"));
		cartPage cp= productCatalogue.goToCart();
		Boolean match= cp.cartProductList(input.get("product"));
		Assert.assertTrue(match);
		checkoutPage chkpage= cp.checkOutPage();
		chkpage.selectCountry("india");
		confirmationPage confmPage= chkpage.submitOrder();
		String msg= confmPage.getConfirmMsg();
		Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));
		}
	
	@Test(dependsOnMethods="submitOrder")
	public void orderHistoryTest() {
		
		productCatalogue productCatalogue= lp.loginApp("sana14@gmail.com", "Asdfghjkl:1");
		orderPage op= productCatalogue.goToOrders();
		Assert.assertTrue(op.verifyOrderDisplay(productName));
	}
	

	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
		
		/*	HashMap<String, String> map= new HashMap<String, String>();
		map.put("email", "sana14@gmail.com");
		map.put("password", "Asdfghjkl:1");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String, String> map1= new HashMap<String, String>();
		map1.put("email", "khanQK@gmail.com");
		map1.put("password", "Asdfghjkl:1");
		map1.put("product", "ADIDAS ORIGINAL");
		*/
	}
	
}
