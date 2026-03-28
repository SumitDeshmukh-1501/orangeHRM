package tests;

import base.BasePage;
import base.baseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.DashboardPage;

public class DashboardTests extends baseTest {

    DashboardPage dp;

    @Test(priority = 2, dependsOnMethods = "login")
    public void goToPim(){
        dp=new DashboardPage(driver);
        dp.clickPIM();

        Assert.assertTrue(BasePage.currentUrl().contains("pim"));
    }

}
