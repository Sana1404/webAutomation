package rahulshettyacademy.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobjects.loginPage;

public class baseTest {

	public WebDriver driver;
	public loginPage lp;

	public WebDriver initialiseDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\globalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:/Users/sanak/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			// firefox
		}

		else if (browserName.equalsIgnoreCase("edge")) {

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// JSON to STring
		String jsonContant = FileUtils.readFileToString(new File(
				filePath),
				StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContant,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}
	
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot ts  =(TakesScreenshot)driver;
		File src= ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	

	@BeforeMethod(alwaysRun=true)
	public loginPage launchApplication() throws IOException {
		driver = initialiseDriver();
		lp= new loginPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
		
	}
	
}
