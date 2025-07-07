package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import yuliah.TestComponents.BaseTest;
import yulliah.pageobjects.*;

import java.io.IOException;
import java.util.List;

// Git2
//Git1
//Git2

//this is from develop
public class StepDefinitionImpl extends BaseTest {

    //creating an object of landingpage
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ThanksPage thanksPage;
    public CheckOutPage checkOutPage;

    //method will be excecuted which matching the given pattern
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();

    }

    //we put regular expression
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_password(String username, String password) {
        productCatalogue = landingPage.loginApplication(username, password);

    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
       productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName)
    {
        CartPage cartPage = productCatalogue.goToCart();
        Boolean match = cartPage.VerifyProductDispaly(productName);
        Assert.assertTrue(match);

        CheckOutPage checkOutPage = cartPage.goToCheckoutPage();
        checkOutPage.selectCountry("india");


        thanksPage = checkOutPage.goToThanksPage();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
        String confirmmessage = thanksPage.getMessage();
        driver.close();

    }


    @Then("{string} message is displayed")
    public void error_message_displayed(String string)
    {
        landingPage.loginApplication("gnidan123456@gmail.com", "Qwerty123453");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
        driver.close();

    }



}
