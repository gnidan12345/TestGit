package yulliah.pageobjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponents {

    WebDriver driver;


    public CheckOutPage(WebDriver driver) {
        super(driver);
        //every child need to send driver to parent
        //initializing code
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    By results = By.cssSelector(".ta-results");

    @FindBy(css = "[placeholder='Select Country']")
    private WebElement country;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    private WebElement selectCountry;

    @FindBy(css = ".action__submit")
    private WebElement submitOrder;


    public void selectCountry(String countryname) {
        Actions a = new Actions(driver);
        a.sendKeys(country, countryname).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();

    }

    public ThanksPage goToThanksPage() {
       submitOrder.click();
//       ThanksPage thanksPage = new ThanksPage(driver);
       return new ThanksPage(driver);
    }




}

