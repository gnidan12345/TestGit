package yuliah.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import yuliah.TestComponents.BaseTest;
import yulliah.pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder_refactorTest extends BaseTest{



    String productName = "ZARA COAT 3";

// we use TESTNG here to avoid using static methods here

        @Test(dataProvider = "getData", groups = {"Purchase"})

        public void submitOrder(HashMap<String,String> input) throws IOException {
//e-commerce  checkout flow

            //we return LandingPage in BaseTeast launchApplication and create landingPage variable in this class
//            LandingPage landingPage =  launchApplication();  - we used @BeforeTest in BaseTest to automate running this method before each method




            // get rid of object creation we use method from landing page class
            ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

            //  wait for the list of products and get the list of products
//        ProductCatalogue productCatalogue = new ProductCatalogue(driver);

            productCatalogue.getProductList();

            //we can call this method from this object because of inheritance from abstract class
            productCatalogue.addProductToCart(input.get("product"));

            // get rid of object creation, we just catch object here
            CartPage cartPage = productCatalogue.goToCart();
            Boolean match = cartPage.VerifyProductDispaly(input.get("product"));
            Assert.assertTrue(match);

            CheckOutPage checkOutPage = cartPage.goToCheckoutPage();
            checkOutPage.selectCountry("india");


            ThanksPage thanksPage = checkOutPage.goToThanksPage();
            String confirmmessage = thanksPage.getMessage();


            Assert.assertTrue(confirmmessage.equalsIgnoreCase("Thankyou for the order."));


        }



    //to verify QWERTY is displayed in orders page
    //will be run after method is completed

        @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTEst(){

            ProductCatalogue productCatalogue = landingPage.loginApplication("gnidan12345@gmail.com", "Qwerty12345");
            Orderpage orderpage = productCatalogue.goToOrdersPage();

            Assert.assertTrue(orderpage.VerifyOrderDispaly(productName));


        }




        //Extent Reports


        //reading from json file

    @DataProvider
    public Object[][] getData() throws IOException {

    List<HashMap<String, String>>  data = getJsondataToMap(System.getProperty("user.dir") + "//src//test//java//yuliah//data//PurchaseOrder.json");


        return new  Object[][] {{data.get(0)}, {data.get(1)}};
    }

//
//        @DataProvider
//    public Object[][] getData(){
//
//
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("email", "gnidan12345@gmail.com");
//            map.put("password", "Qwerty12345");
//            map.put("product", "QWERTY");
//
//            HashMap<String, String> map1 = new HashMap<String, String>();
//            map1.put("email", "test1407@gmail.com");
//            map1.put("password", "Qwerty54321");
//            map1.put("product", "IPHONE 13 PRO");
//
//
//           return new  Object[][] {{map}, {map1}};
//        }




//    @DataProvider
//    public Object[][] getData(){
//            return new Object[][]{{"gnidan12345@gmail.com", "Qwerty12345","QWERTY" }, {"test1407@gmail.com", "Qwerty54321", "IPHONE 13 PRO"}};
//
//    }



    }



