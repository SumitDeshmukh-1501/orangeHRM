package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.atp.Switch;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ExtentReport {

    public static ExtentSparkReporter spark;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static Status s=null;


    public static void configSparkReport(){

        DateTimeFormatter date_formatter= DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter dateTime_formatter= DateTimeFormatter.ofPattern("MM-dd-YYYY_HH-mm-ss");
        LocalDateTime today=LocalDateTime.now();

        Path path = Paths.get(System.getProperty("user.dir") + "/Reports/ExtentReport",today.toLocalDate().format(date_formatter));
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        spark= new ExtentSparkReporter(path + "/ExtentReport" + "_" + today.format(dateTime_formatter) + ".html");
        ExtentSparkReporterConfig setProp=spark.config();
        setProp.setDocumentTitle("OrangeHRM automation report");
        setProp.setReportName( "ExtentReport" + "_" + today.format(dateTime_formatter));
        setProp.setTheme(Theme.STANDARD);
    }

    public static void configExtendReport(ITestContext context){
        extent=new ExtentReports();
        extent.attachReporter(spark);
        List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()){
            extent.setSystemInfo("Groups",includedGroups.toString());
        }

    }


    public static void setExtendTest(ITestResult r){
        String methodname= r.getMethod().getMethodName();
        String methodDescription= r.getMethod().getDescription();
        test=extent.createTest(methodname + "\n"  + methodDescription);

        switch (r.getStatus()){
            case ITestResult.SUCCESS :
                test.log(Status.PASS, r.getName() + "Test case is Passed");
                break;
            case ITestResult.FAILURE:
                test.log(Status.FAIL, r.getName() + "Test case is FAILED");
                break;
            case ITestResult.SKIP:
                test.log(Status.SKIP, r.getName() + "Test case is Skipped");
                break;
        }
        Thread.sleep(5000);
        if(r.getStatus()==ITestResult.FAILURE) test.addScreenCaptureFromPath(ScreenshotUtil.takeScreenShot(methodname));




    }

}
