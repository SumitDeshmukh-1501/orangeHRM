package tests;

import base.BasePage;
import base.baseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.LoginPage;
import utils.DataProviderUtil;
import utils.WaitUtils;

import java.time.Duration;

public class LoginTests extends baseTest {



    LoginPage lp;



    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUtil.class, priority = 1)
    public void login (String testDataType, String expectedError , String user, String pass) throws InterruptedException {

        lp= new LoginPage(driver);
        BasePage.refreshPage();
        WaitUtils.waitForPageLoad();

        lp.fillDetails(user,pass);
        lp.clickOnLogin();

        switch(testDataType){
            case "Valid":
                Thread.sleep(3000);

                    Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));

                    break;
            case "Invalid UserName":
                Assert.assertEquals(lp.getError(),expectedError);


                break;
            case  "Invalid Password":
                Assert.assertEquals(lp.getError(),expectedError);


                break;
            case  "Null Password":
                Assert.assertEquals(lp.getFieldError(),expectedError);


                break;

            case  "Null Username":
                Assert.assertEquals(lp.getFieldError(),expectedError);

                break;
        }

        Thread.sleep(10000);
    }
}
