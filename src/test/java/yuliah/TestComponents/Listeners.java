package yuliah.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import recourses.ExtentReporterNG;

import java.io.IOException;

public class Listeners extends BaseTest  implements ITestListener
{
    ExtentTest test;
    //we create extent object,  for that we  call static method getReportObjects
    ExtentReports extent = ExtentReporterNG.getReportObject();
    //we use result variable to get method name which is Test name

    ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();
    //we use ThreadLocal class to avoid sync issues in Tests () as we run them in parallel






    @Override
    public void onTestStart(ITestResult result) {

      test =   extent.createTest(result.getMethod().getMethodName());
      // with set methods it assigns to this method unique threadid(currentTest)_>creates a map (id, testobjrct)
      extenttest.set(test);


    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extenttest.get().log(Status.PASS, "Test Passed");
//        test.log(Status.PASS, "Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {


//        test.fail(result.getThrowable());
        extenttest.get().fail(result.getThrowable());
        //get method will check which threadid is running and retrieve related testobject

        try
            {

                driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            } catch (Exception e1){
            e1.printStackTrace();
        }
        String filePath = null;
        try {
            filePath = getScreenShot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extenttest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
//        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

//        test.addScreenCaptureFromPath()

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
