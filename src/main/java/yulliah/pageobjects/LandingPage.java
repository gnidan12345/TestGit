package yulliah.pageobjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {

    //local variable driver
    WebDriver driver;

    //constructor - 1st method to execute .'
    // driver inside the constructor is visible only in scope of this method and it came from object parameter
    public LandingPage(WebDriver driver){

        super(driver);
        //initializing code
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
//    WebElement userEmail = driver.findElement(By.id("userEmail"));


    //PageFactory design pattern
    //another way how to find element

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement passwordEle;

    @FindBy(id="login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String email, String password){
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;


    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage(){
        waitForWebElementToAppear(errorMessage);
       return errorMessage.getText();

    }

}
