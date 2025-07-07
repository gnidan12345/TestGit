package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import yulliah.pageobjects.CartPage;
import yulliah.pageobjects.Orderpage;

import java.time.Duration;

public class AbstractComponents {
    // in this class we create  all reusable methods


    //local object driver
    WebDriver driver;

    //driver comes to constructor from child classs with super keyword
public AbstractComponents(WebDriver driver){
    this.driver = driver;
    PageFactory.initElements(driver,this);
}

    @FindBy(css = "[routerlink*='cart']" )
    WebElement cartButton;
    @FindBy(css = "[routerlink*='/dashboard/myorders']" )
    WebElement orderHeader;



    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement elem){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(elem));

    }


    public void waitForElementToDissapear(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }


    // we declare it in abstract class as Cart icon is in the Header which is common on all pages
    public CartPage goToCart(){
        cartButton.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public Orderpage goToOrdersPage(){
        orderHeader.click();
        Orderpage orderpage  = new Orderpage(driver);
        return orderpage;
    }

}