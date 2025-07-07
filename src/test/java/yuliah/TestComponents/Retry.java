package yuliah.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count = 0;
    int maxTry = 1;


    //if condition is true the method returns true and test will rerun
    // need to add 'retryAnalyzer = Retry.class' to any test  @ Test annotation which should be rerun
    @Override
    public boolean retry(ITestResult result) {
        if (count < maxTry){
            count ++;
            return  true;
        }
        return false;
    }
}
