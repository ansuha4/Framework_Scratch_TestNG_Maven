package anusha.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
    WebDriverWait wait;
    public ProductCatalogue(WebDriver driver) {
        super(driver);
        //initializing the code
        this.driver = driver;
        //this method is the first thing to execute so we write the below line in the constructor only
        PageFactory.initElements(driver, this);
    }
    //List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
    @FindBy(xpath="//div[contains(@class,'mb-3')]")
    List<WebElement> items;

   @FindBy(css = ("#toast-container"))
           WebElement toast;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.xpath("//button[@class='btn w-10 rounded']");
    By productAdded =  By.cssSelector(("#toast-container"));


    public List<WebElement> getProductList(){
        waitForElementToBeVisible(productsBy);
        return items;

    }

    public WebElement getProductName(String productName){
        WebElement item1 =  items.stream().filter(item->item.findElement
                        (By.xpath("//div[@class='card-body']/h5/b")).getText()
                .equals(productName)).findFirst().orElse(null);
        System.out.println(item1.getText());
        return item1;

    }

    public void addProductToCart(String productName){
       WebElement item1 = getProductName(productName);
       item1.findElement(addToCart).click();
       waitForElementToBeVisible(productAdded);
       waitForElementToDisappear(toast,wait);
    }
}
