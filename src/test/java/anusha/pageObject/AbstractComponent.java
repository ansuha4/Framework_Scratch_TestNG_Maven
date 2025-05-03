package anusha.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponent {
     WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        //this method is the first thing to execute so we write the below line in the constructor only
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(5));
    }
    //switch into frames
    //switch into windows
    //javascript Executor
    //alert handlings
    //visibility - all the above code can be written in the reusable code in this parent class

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cart;

    public void waitForElementToBeVisible(By findBy) {

        //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mb-3')]")));
        List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
        System.out.println(items.size());
      //  Thread.sleep(1000);
    }
    public void waitForElementToDisappear(WebElement ele, WebDriverWait wait) {

        wait.until(ExpectedConditions.invisibilityOf(ele));

    }

    public cartPage goToCartPage(){
        cart.click();
        cartPage cp = new cartPage(driver);
        return cp;
    }


}
