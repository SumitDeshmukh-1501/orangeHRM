package base;

import driver.DriverManager;
import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.WaitUtils;


// all tart driver before test
//
//close driver after test
public class baseTest {
        public static WebDriver driver;

        String browserName= "chrome";
        String mainURL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";


        @BeforeSuite
        public void setup(){
            DriverFactory.init(browserName);
            driver= DriverManager.getWebDriver();
            driver.manage().window().maximize();
            System.out.println("sdasf");
            driver.get(mainURL);
            WaitUtils.waitForPageLoad();
            Assert.assertEquals(driver.getCurrentUrl(),mainURL);
        }
//        @AfterClass
//        public void tearDown(){
//            DriverManager.quitDriver();
//        }

}
