package pageobject;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver refdriver){
        this.driver=refdriver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(name = "username")
    WebElement username;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginBtn;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
    WebElement errormsg;

    @FindBy(xpath = "//span[contains(@class,'oxd-text oxd-text--span')]")
    WebElement fieldError;

//    @FindBy(xpath = "//div[@class='orangehrm-login-form']//div[2]//div[1]//span[1]")
//    WebElement passError;




    public void fillDetails(String user, String pass){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        username.sendKeys(user);
        password.sendKeys(pass);

    }

    public void clickOnLogin(){
        loginBtn.click();

    }

    public String getError(){
        WaitUtils.waitForElementVisibility(errormsg);
        return errormsg.getText();
    }

    public String getFieldError(){

        WaitUtils.waitForElementVisibility(fieldError);
        return fieldError.getText();
    }

//    public String getPassError(){
//        WaitUtils.waitForElementVisibility(passError);
//        return passError.getText();
//    }






}
