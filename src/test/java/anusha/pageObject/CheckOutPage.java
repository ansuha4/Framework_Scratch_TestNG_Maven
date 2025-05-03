package anusha.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckOutPage extends AbstractComponent {
 WebDriver driver;
 WebDriverWait wait;
    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    List<WebElement> countries;

    @FindBy(xpath = "//div/a[@class = 'btnn action__submit ng-star-inserted']")
    WebElement submitButton;

    public void dropDown(){
        for(int i =0; i<countries.size(); i++)
        {
            WebElement suggest = countries.get(i);
            if(suggest.getText().equalsIgnoreCase("India"))
            {
                suggest.click();
                System.out.println(suggest.getText());
                break;
            }
    }

        driver.findElement(By.xpath("//div/a[@class = 'btnn action__submit ng-star-inserted']")).click();
        String text = driver.findElement(By.cssSelector(".hero-primary")).getText();

    }

}
