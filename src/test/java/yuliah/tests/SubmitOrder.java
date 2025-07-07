package yuliah.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import yulliah.pageobjects.*;

import java.time.Duration;


public class SubmitOrder {
    public static void main(String[] args) {

//e-commerce  checkout flow
        String productname = "QWERTY";

//      WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();

        // we are creating the object of LandingPage class with driver parameter
        //and driver parameter goes to LandingPage constructor
        // and driver is assigned to this.driver
        LandingPage landingPage = new LandingPage(driver);
        //we acesss methods from LandingPage class
        landingPage.goTo();


        // get rid of object creation we use method from landing page class
        ProductCatalogue productCatalogue =  landingPage.loginApplication("gnidan12345@gmail.com", "Qwerty12345");

       //  wait for the list of products and get the list of products
//        ProductCatalogue productCatalogue = new ProductCatalogue(driver);

       productCatalogue.getProductList();

        //we can call this method from this object because of inheritance from abstract class
        productCatalogue.addProductToCart(productname);

        // get rid of object creation, we just catch object here
        CartPage cartPage = productCatalogue.goToCart();
        Boolean match = cartPage.VerifyProductDispaly(productname);
        Assert.assertTrue(match);

       CheckOutPage checkOutPage =  cartPage.goToCheckoutPage();
       checkOutPage.selectCountry("india");


       ThanksPage thanksPage = checkOutPage.goToThanksPage();
       String confirmmessage = thanksPage.getMessage();


        Assert.assertTrue(confirmmessage.equalsIgnoreCase("Thankyou for the order."));

        driver.close();








        //log in to the account
//        driver.findElement(By.id("userEmail")).sendKeys("gnidan12345@gmail.com");
//        driver.findElement(By.id("userPassword")).sendKeys("Qwerty12345");
//        driver.findElement(By.id("login")).click();



        //create Explicit wait
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
////
//        //wait until all products are on the page
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));


        //create the list of all products on the page
//        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));


       // selecting the product with specific name
//       WebElement prod =  products.stream().filter(product->
//       product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);

       //search inside the prod element for Button add to cart
//       prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();



       // wait until popup will appear



//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));


        //wait until loading animation dissapear
        // ng-animating
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));



//        start from here


        //go to  Cart
//        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();



        //create the list of products added to the cart
//        List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));


        //to check if condition matches we create boolean variable and use anyMatch method
//        Boolean match = cartproducts.stream().anyMatch(cartProduct-> cartProduct.getText().equals(productname));


        //if match is true > assertion is true and test will pass
//        Assert.assertTrue(match);

        //click checkout button
//        driver.findElement(By.cssSelector(".totalRow button")).click();

        //input country into Select country field
//        Actions a = new Actions(driver);
//        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
//
//        //wait until list of countries appear
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        //click on selected country
//        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//
//        //click place order
//        driver.findElement(By.cssSelector(".action__submit")).click();
//
//        //get text from the thank you page
//        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//
//        //check if text is true
//        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
//
//        driver.close();


















    }
}
