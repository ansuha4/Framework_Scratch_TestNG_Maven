import anusha.pageObject.LandingPage;
import anusha.pageObject.ProductCatalogue;
import anusha.pageObject.cartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class LoginClass {
    public static void main(String[] args) throws InterruptedException {
        String productName = "ZARA COAT 3";
        String email = "anushagadapa@gmail.com";
        String password = "Nishwan@001";
       WebDriver driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     //  driver.get("https://rahulshettyacademy.com/client/");

       LandingPage landingPage = new LandingPage(driver);
       landingPage.goT0();
       ProductCatalogue pc = landingPage.loginApplication(email, password);
      // driver.findElement(By.xpath("//button[@routerlink='/dashboard']")).click();

     List<WebElement> items=pc.getProductList();
    cartPage cp = pc.goToCartPage();

     Boolean match = cp.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        cp.checkOut();


        driver.findElement(By.xpath("//ul/li/button[@class ='btn btn-primary']")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        List<WebElement> autoSuggest = driver.findElements(By.xpath("//input[@placeholder='Select Country']"));
        for(int i =0; i<autoSuggest.size(); i++)
        {
            WebElement suggest = autoSuggest.get(i);
            if(suggest.getText().equalsIgnoreCase("India"))
            {
                    suggest.click();
                    System.out.println(suggest.getText());
                    break;
            }
        }
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"ind").build().perform();
        driver.findElement(By.xpath("//div/a[@class = 'btnn action__submit ng-star-inserted']")).click();
        String text = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));
        driver.close();


    }
}
