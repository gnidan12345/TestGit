package yuliah.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import yulliah.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;


public class StandAloneTest {
    public static void main(String[] args) {

//e-commerce  checkout flow
        String productname = "QWERTY";

//      WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();

        // we are creating the object of LandingPage class with driver parameter
        //and driver parameter goes to LandingPage constructor
        // and driver is assigned to this.driver
        LandingPage landingPage = new LandingPage(driver);




        //log in to the account
        driver.findElement(By.id("userEmail")).sendKeys("gnidan12345@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Qwerty12345");
        driver.findElement(By.id("login")).click();



        //create Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //wait until all products are on the page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));


        //create the list of all products on the page
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));


       // selecting the product with specific name
       WebElement prod =  products.stream().filter(product->
       product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);

       //search inside the prod element for Button add to cart
       prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();



       // wait until popup will appear



        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));


        //wait until loading animation dissapear
        // ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        //click Cart button
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();



        //create the list of products added to the cart
        List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));


        //to check if condition matches we create boolean variable and use anyMatch method
        Boolean match = cartproducts.stream().anyMatch(cartProduct-> cartProduct.getText().equals(productname));


        //if match is true > assertion is true and test will pass
        Assert.assertTrue(match);

        //click checkout button
        driver.findElement(By.cssSelector(".totalRow button")).click();

        //input country into Select country field
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

        //wait until list of countries appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        //click on selected country
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

        //click place order
        driver.findElement(By.cssSelector(".action__submit")).click();

        //get text from the thank you page
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

        //check if text is true
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

        driver.close();


















    }
}
