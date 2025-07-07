package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//cucumber -> TestNG, Junit - we use only for running code
@CucumberOptions(features="src/test/java/cucumber", glue="stepDefinitions", monochrome = true, tags = "@Errorvalidation",  plugin = {"html:target/cucumber.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests {


}
