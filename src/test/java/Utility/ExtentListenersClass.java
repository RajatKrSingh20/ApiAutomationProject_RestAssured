package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListenersClass implements ITestListener {

    ExtentSparkReporter reporter;
    ExtentReports reports;
    ExtentTest test;

    public void configureExtentReport(){
        reporter = new ExtentSparkReporter("C:\\Users\\rajat\\IdeaProjects\\ApiAutomationProject_RestAssured\\src\\test\\ExtentReport\\ExtentListenerReport.html");
        reports = new ExtentReports();
        reports.attachReporter(reporter);
    }

    @Override
    public void onStart(ITestContext context){

        configureExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result){

    }

   @Override
    public void onTestSuccess(ITestResult result){
        test = reports.createTest(result.getName());
        test.log(Status.PASS,"Test Case : "+result.getName()+" has been successfully passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        test = reports.createTest(result.getName());
        test.log(Status.FAIL,"Test Case : "+result.getName()+" has been failed ");
    }

    @Override
    public void onTestSkipped(ITestResult result){
        test = reports.createTest(result.getName());
        test.log(Status.SKIP,"Test Case : "+result.getName()+" has been skipped ");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result){
        test = reports.createTest(result.getName());
        test.log(Status.FAIL,"Test Case : "+result.getName()+" has been failed but within Success Percentage ");
    }

    @Override
    public void onFinish(ITestContext context){

        test.log(Status.INFO,"All of the Test Cases have been executed successfully");

        reports.flush();
    }


}
