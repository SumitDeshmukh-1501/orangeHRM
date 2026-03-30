package tests;

import base.BasePage;
import base.baseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.PIMPage;
import utils.DataProviderUtil;
import utils.WaitUtils;

import java.util.Map;

public class PIMTests extends baseTest {

    PIMPage pi;
    Map<String,String> newEmployee= DataProviderUtil.employeeDetails();
    String newEmployeeID;
    String newID;

    @Test(priority = 3)
    public void goToAddEmployee(){
        pi=new PIMPage(driver);
        pi.clickAddEmployee();
        Assert.assertTrue(BasePage.currentUrl().contains("addEmployee"));
    }
    @Test(priority = 4)
    public void addNewEmployee() throws InterruptedException {
        pi.fillEmployeeDetails(newEmployee.get("firstName"),newEmployee.get("middleName"),newEmployee.get("lastName"), newEmployee.get("empID"));
        pi.clickOnSave();


        Assert.assertEquals(pi.getMessage(),"Success");


    }
    @Test(priority = 4,dependsOnMethods = "addNewEmployee")
    public void verifyID(){
        WaitUtils.waitTill10sec();
        Assert.assertEquals(pi.getIDFromEmpList(),newEmployee.get("empID"));
    }

    @Test(priority = 5, dependsOnMethods = "verifyID")
    public void fillPersonalDetails(){
      //pi.selectNationality(newEmployee.get("country"));
       pi.giveDOB(newEmployee.get("dob"));
       pi.selectGender(newEmployee.get("gender"));
       pi.savePersonalDetails();

        Assert.assertEquals(pi.getSuccessMsg(),"Success");
    }
}
