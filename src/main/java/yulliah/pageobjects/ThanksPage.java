package yulliah.pageobjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThanksPage extends AbstractComponents {


    WebDriver driver;

    public ThanksPage(WebDriver driver) {
       super (driver);
       this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".hero-primary")
    WebElement message;


        public String getMessage(){
        String confirmMessage = message.getText();
        return confirmMessage;
        }



    }

