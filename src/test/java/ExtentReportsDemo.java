import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExtentReportsDemo {
    ExtentReports extent;
    //  ExtentReports - Main Class
  //  ExtentSparkReporter - helper class to set the configurations

    @BeforeTest
            public void config(){

            String path = System.getProperty("user.dir")+"\\reports\\index.html";
            ExtentSparkReporter esr = new ExtentSparkReporter(path);
            esr.config().setReportName("Test Report");
            esr.config().setDocumentTitle("");

           extent = new ExtentReports();
            extent.attachReporter(esr);
            extent.setSystemInfo("Tester", "Anusha");
    }
@Test
public  ExtentReports initialDemo(){
    //System.setProperty("webdriver.chrome.driver",path);
    ExtentTest test = extent.createTest("Initial Demo");
    WebDriver driver = new ChromeDriver();
    driver.get("https://rahulshettyacademy.com/");
    driver.manage().window().maximize();
    System.out.println(driver.getTitle());
    test.addScreenCaptureFromBase64String(driver.getPageSource());
    test.fail("Result do not match");
    extent.flush();
    return extent;// it will be notified that the test is done and it will no more monitor
    //at the end of everything - all test cases

}

}
