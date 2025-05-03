package anusha.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent{
    WebDriver driver;
    public LandingPage(WebDriver driver) {
        super(driver);
        //initializing the code
        this.driver = driver;
        //this method is the first thing to execute so we write the below line in the constructor only
        PageFactory.initElements(driver, this);
    }
  //  WebElement userEmail = driver.findElement(By.xpath("//input[@formcontrolname='userEmail']"));
    //PageFactory

    @FindBy(xpath="//input[@formcontrolname='userEmail'")
    WebElement userEmail;

    @FindBy(css="input[id='userPassword']")
    WebElement password1;

    @FindBy(css="#login")
    WebElement login;

    public ProductCatalogue loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        password1.sendKeys(password);
        login.click();
        ProductCatalogue pc =  new ProductCatalogue(driver);
        return pc;
    }
    public void goT0(){
        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().window().maximize();
    }

}
