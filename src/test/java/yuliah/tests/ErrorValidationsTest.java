package yuliah.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import yuliah.TestComponents.BaseTest;
import yuliah.TestComponents.Retry;
import yulliah.pageobjects.CartPage;
import yulliah.pageobjects.ProductCatalogue;

import java.io.IOException;


public class ErrorValidationsTest extends BaseTest {

// we use TESTNG here to avoid using static methods here

        @Test(groups = "ErrorHandling", retryAnalyzer = Retry.class)

        public void LoginErrorValidation() throws IOException {

            String productname = "QWERTY";
            landingPage.loginApplication("gnidan123456@gmail.com", "Qwerty123453");

            Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

        }


        //this test is checking whether some random product does not exist
        @Test
    public void ProductErrorValidation() throws IOException {

            String productname = "ZARA COAT 3";
            ProductCatalogue productCatalogue = landingPage.loginApplication("gnidan12345@gmail.com", "Qwerty12345");
            productCatalogue.getProductList();
            productCatalogue.addProductToCart(productname);
            CartPage cartPage = productCatalogue.goToCart();
            Boolean match = cartPage.VerifyProductDispaly("ZARA COAT 33");
            System.out.println(match);
            Assert.assertFalse(match);


    }





    }

