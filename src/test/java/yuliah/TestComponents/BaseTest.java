package yuliah.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import yulliah.pageobjects.LandingPage;
import yulliah.pageobjects.Orderpage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    //this test sets all browser configurations


    //global variable driver
    public WebDriver driver;

    // we create public object to give access to it from children classes
    public LandingPage landingPage;


    public WebDriver initializeDriver() throws IOException {

        // we create properties class to get access to global properties saved in .properties file
        Properties prop = new Properties();

        //we use System.getProperty("user.dir")  to dinamicaly get system path , we are not hardcoding loval system path
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main///java/recourses//GlobalData.properties");
        prop.load(fis);
        //get property from Maven
        //this is ternary function
        //result = condition ? trueValue : falseValue
        // we can run test from maven then this code will be run: System.getProperty("browser")
        String browserNane = System.getProperty("browser")!= null ? System.getProperty("browser") :prop.getProperty("browser");

//       String browserNane =  prop.getProperty("browser");
        //      WebDriverManager.chromedriver().setup();

        if(browserNane.contains("chrome")){
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("headless");
            if(browserNane.contains("chrome")) {
                driver = new ChromeDriver();
                //run in full screen
                driver.manage().window().setSize(new Dimension(1440,900));
            }

    } else if (browserNane.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            //Firefox
        } else if (browserNane.equalsIgnoreCase("edge")) {
         driver = new EdgeDriver();

        }
        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        return driver;


    }


    //basic code to convert Json to Sring and then to HasMap
    public List<HashMap<String, String>> getJsondataToMap(String filePath) throws IOException {

        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath));

        //convert String to HashMap (Jackson databind)
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;

        //we create list with hashmaps {map, map1}

    }


    //basic code to create Screenshot
    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        //create source file
        File source = ts.getScreenshotAs(OutputType.FILE);
        //create destination file
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        //copy source file into destination file
        FileUtils.copyFile(source, file );

        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

    }


    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {

        driver =  initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
    }
