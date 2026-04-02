package utils;

import base.baseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import java.time.Duration;
import java.util.List;

public class WaitUtils extends baseTest {

    public static WebDriverWait wait;

    public static void waitForPageLoad(){
        wait=new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(webDriver -> ((JavascriptExecutor)webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }


    public static void waitForElementVisibility(WebElement ele){
        wait=new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public static void waitForElementClickable(WebElement ele){
        wait=new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public static void waitForAllElementsToVisible(List<WebElement> elements){
        wait=new WebDriverWait(driver,Duration.ofSeconds(30));
        for (WebElement ele:elements) wait.until(ExpectedConditions.visibilityOfAllElements(ele));

    }

    public static void waitForElementToDisappear(WebElement ele){
        wait=new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public static void waitTill10sec(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public static WebElement waitFortheElementRefresh(By loactor){
        WebElement element = wait.until(
                ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(loactor)
                )
        );

        return element;
    }


}
