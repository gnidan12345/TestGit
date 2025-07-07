package yulliah.pageobjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {

    WebDriver driver;


    public CartPage(WebDriver driver) {
        super(driver);
        //every child need to send driver to parent
        //initializing code
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


//    List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));


    //Page Factory is for Webelements

    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProducts;


    @FindBy(css = ".totalRow button")
    private WebElement checkOutEle;


    public boolean VerifyProductDispaly(String productname){
        Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(productname));
        return match;
    }

    public CheckOutPage goToCheckoutPage(){
        checkOutEle.click();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;
    }

//    public List<WebElement> getProductList(){
//
//        //use method from AbstractComponents class
//        waitForElementToAppear(productsBY);
//        return products;
//    }




}

