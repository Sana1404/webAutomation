package rahulshettyacademy.test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.pageobjects.loginPage;

public class standAloneTest {

	public static void main(String[] args) {

		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/sanak/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		String pName="ADIDAS ORIGINAL";
		loginPage lp= new loginPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("sana14@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Asdfghjkl:1");
		// login
		driver.findElement(By.id("login")).click();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));

		// img[class='card-img-top']
		// div[class*='col-lg-4']
		// .col-lg-4
		List<WebElement> productList = driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement actualProduct = productList.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(pName)).findFirst().orElse(null);
		actualProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		// .infoWrap
		List<WebElement> cartProduct= driver.findElements(By.cssSelector(".infoWrap"));
		Boolean match =cartProduct.stream().anyMatch(s->s.findElement(By.cssSelector(".cartSection h3")).getText().equals(pName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".subtotal.cf.ng-star-inserted button")).click();
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
		List <WebElement> coutries= driver.findElements(By.cssSelector("button[class*='item']"));
		for(WebElement country: coutries) {			
			if (country.getText().equalsIgnoreCase("India"))
			{
				country.click();
				break;
			}
		}
		driver.findElement(By.cssSelector("a[class*='submit']")).click();
		
		String confirmMsg= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		//driver.close();
		}  
	

}
