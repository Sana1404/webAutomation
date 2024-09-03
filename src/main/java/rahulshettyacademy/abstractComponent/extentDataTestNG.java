package rahulshettyacademy.abstractComponent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentDataTestNG {
	
	public static ExtentReports getReportObject() {
		
		  String path = System.getProperty("user.dir") + "\\reports\\index.html";
	        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	        reporter.config().setReportName("Web Automation");
	        reporter.config().setDocumentTitle("Test Report");

	        ExtentReports extent = new ExtentReports();
	        extent.attachReporter(reporter);
	        extent.setSystemInfo("Tester", "Sana");
	        return extent;
		
	}

}
