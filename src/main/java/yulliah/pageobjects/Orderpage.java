package yulliah.pageobjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Orderpage extends AbstractComponents {

    WebDriver driver;




    public Orderpage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);


    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productNames;

    public boolean VerifyOrderDispaly(String productname){
        Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
        return match;
    }
}
