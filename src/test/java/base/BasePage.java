package base;

public class BasePage extends baseTest{

    public static void refreshPage(){
        driver.navigate().refresh();
    }

    public static void navigateBack(){
        driver.navigate().back();
    }
    public static String currentUrl(){return driver.getCurrentUrl();}
}












