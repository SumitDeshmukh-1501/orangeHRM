package Listeners;

import Reports.ExtentReport;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListeners implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentReport.configSparkReport();
        ExtentReport.configExtendReport(context);





        System.out.println("onstart");
    }

//    @Override
//    public void onTestStart(ITestResult result) {
//        ExtentReport.setExtendTest(result);
//        System.out.println("ontest start");
//
//    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReport.setExtendTest(result);
        System.out.println("on success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReport.setExtendTest(result);

        System.out.println("on failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReport.setExtendTest(result);
        System.out.println("on skipped");
    }



    @Override
    public void onFinish(ITestContext context) {
        ExtentReport.extent.flush();
        System.out.println("on finish");
    }
}
