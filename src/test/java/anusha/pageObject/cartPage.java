package anusha.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class cartPage extends AbstractComponent{
    WebDriver driver;
    WebDriverWait wait;
    public cartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        //this method is the first thing to execute so we write the below line in the constructor only
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='cartSection']/h3")
    List<WebElement> cartProducts;

    @FindBy(xpath = "//ul/li/button[@class ='btn btn-primary']" )
    WebElement cartButton;

    public Boolean verifyProductDisplay(String productName){
        Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
        return match;
    }

    public CheckOutPage checkOut(){
        cartButton.click();
       CheckOutPage cop =  new CheckOutPage();
       return cop;
    }
}
