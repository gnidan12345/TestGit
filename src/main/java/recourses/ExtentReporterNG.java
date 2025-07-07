package recourses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    //static method we can access from any class without creating an object
    public static  ExtentReports getReportObject(){
        String path =   System.getProperty("user.dir") + "//reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        // we create the object of ExtentReports class
        ExtentReports  extent = new ExtentReports();

        //we attach our report config
        extent.attachReporter(reporter);
        //we set info
        extent.setSystemInfo("Tester", "Yuliia");
        return extent;
    }

}

