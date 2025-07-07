package yulliah.pageobjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {

    WebDriver driver;


    public ProductCatalogue(WebDriver driver) {
        super(driver);
        //every child need to send driver to parent

        //initializing code
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


//    List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));


    //Page Factory is for Webelements
    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;




//    @FindBy(css = ".cartSection h3")
//    List<WebElement>  cartSection;


    //create variable By type
   By productsBY =  By.cssSelector(".mb-3");
   By adddToCart = By.cssSelector(".card-body button:last-of-type");
   By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList(){

        //use method from AbstractComponents class
        waitForElementToAppear(productsBY);
        return products;
    }


    //create method to return product which we need with specific productname
    public WebElement getProductByName(String productName){
        WebElement prod = getProductList().stream().filter(product->
        product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) {
       WebElement prod =  getProductByName(productName);
       prod.findElement(adddToCart).click();
       waitForElementToAppear(toastMessage);
       waitForElementToDissapear(spinner);

    }


    //mine
//    public void goToCart(){
//        cartButton.click();
//    }

//    public List<WebElement> getCartProducts(){
////        List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
//    return cartSection;
//    }


//    public boolean match(String productname){
//        getCartProducts().stream().anyMatch(cartProduct->cartProduct.getText().equals(productname));
//        return true;
//    }

//    Boolean match = cartproducts.stream().anyMatch(cartProduct-> cartProduct.getText().equals(productname));

}

