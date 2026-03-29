package utils;

import base.baseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil extends baseTest {

    //For test listeners
    public static String takeScreenShot(String methodName){
        DateTimeFormatter date_formatter= DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter dateTime_formatter= DateTimeFormatter.ofPattern("MM-dd-YYYY_HH-mm-ss");
        LocalDateTime today=LocalDateTime.now();

        //create a folder
        Path path = Paths.get(System.getProperty("user.dir") + "/Screenshots/",today.toLocalDate().format(date_formatter));
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //create a screenshotfilele
        File screenshotfile= new File(path + "/" + methodName + "_screenshot_" +today.format(dateTime_formatter) + ".png");

        //take a screenshot and store in above file
        try {
            FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),screenshotfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "./Screenshots/"+today.toLocalDate().format(date_formatter)+ "/" + methodName + "_screenshot_" +today.format(dateTime_formatter) + ".png";
    }





}
