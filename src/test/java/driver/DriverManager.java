package driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    public static WebDriver driver;

    public static WebDriver getWebDriver(){
        return driver;
    }


    public static void setDriver(WebDriver driverRef) {
        driver=driverRef;
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
        }
    }
}

