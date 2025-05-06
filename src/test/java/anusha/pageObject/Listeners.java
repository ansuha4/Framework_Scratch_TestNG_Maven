package anusha.pageObject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
ExtentReports extent;
ExtentTest test;
String path;
    @Override
    public void onTestStart(ITestResult result){
        extent.createTest(path);
    }
    @Override
    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getMethod().getMethodName());
        test.pass("Test Passed");
        test.log(Status.PASS, "Test Passed");

    }
    @Override
    public void onTestFailure(ITestResult result){
//screenshot, attach it to the report
        test.fail(result.getThrowable());
        String filepath = null;
        filepath = getScreenshot(result.getMethod().getMethodName());
        test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
    }

    private String getScreenshot(String methodName) {

        return getScreenshot("Anusha");
    }

    @Override
    public void onTestSkipped(ITestResult result){

    }
}
