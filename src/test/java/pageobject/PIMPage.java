package pageobject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.JavaScriptUtils;
import utils.WaitUtils;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class PIMPage {
    WebDriver driver;
    public PIMPage(WebDriver refDriver) {
        this.driver=refDriver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a[normalize-space()='Add Employee']")
    WebElement addEmpbtn;

    @FindBy(xpath = "//input[@name='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//input[@name='middleName']")
    WebElement middleName;

    @FindBy(xpath = "//input[@name='lastName']")
    WebElement lastName;

    @FindBy(xpath="(//input[contains(@class,'oxd-input')])[5]")
    WebElement id;

    @FindBy(xpath="//button[@type='submit']")
    WebElement save;

    @FindBy(xpath="//p[normalize-space()= 'Success' ]")
    WebElement successMsg;

    @FindBy(xpath="(//input[contains(@class,'oxd-input')])[5]")
    WebElement idFromEmpList;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div/div[2]/i")
    WebElement nationality;

    @FindBy(xpath="//div[contains(@class,'oxd-select-dropdown')]//div")
    List<WebElement> countryOption;

    @FindBy(xpath="(//input[contains(@class,'oxd-input') and @placeholder='yyyy-dd-mm'])[2]")
    WebElement dob;

    @FindBy(xpath="//input[@type='radio']")
    List<WebElement> genders;

    @FindBy(xpath="//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button[@type='submit' and normalize-space()='Save']")
    WebElement savePersonal;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text']")
    WebElement success;

    @FindBy(xpath=("//div[@class='oxd-loading-spinner']"))
    WebElement loader;



    public void clickAddEmployee(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WaitUtils.waitForElementClickable(addEmpbtn);
        addEmpbtn.click();
    }

    public void fillEmployeeDetails(String first, String middle, String last, String empID) throws InterruptedException {
        WaitUtils.waitForPageLoad();
        WaitUtils.waitForAllElementsToVisible(Arrays.asList(firstName,middleName,lastName,id));
        firstName.sendKeys(first);
        middleName.sendKeys(middle);
        lastName.sendKeys(last);

        Thread.sleep(5000);
        Actions actions = new Actions(driver);
        actions.click(id)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(empID)
                .perform();
    }

    public void clickOnSave(){
        WaitUtils.waitForElementClickable(save);
        save.click();
    }

    public String gtID(){
        return id.getAttribute("value");
    }

    public String getMessage(){
        WaitUtils.waitForElementVisibility(successMsg);
        return successMsg.getText();
    }

    public String getIDFromEmpList(){
        WaitUtils.waitForPageLoad();
        WaitUtils.waitForElementToDisappear(loader);
        return idFromEmpList.getAttribute("value");
    }

    public void selectNationality(String country){
        WaitUtils.waitForPageLoad();
        nationality.click();

        for(WebElement e:countryOption){
            WaitUtils.waitForElementClickable(e);
            if(e.getText().equalsIgnoreCase(country)) e.click();
        }

    }

    public void giveDOB(String birthdate){

        dob.sendKeys(birthdate);
    }

    public void selectGender(String gender){
        for (WebElement e:genders) {
            if(e.getText().equalsIgnoreCase(gender)){
                WaitUtils.waitForElementClickable(e);
                e.click();
            }
        }
    }

    public void savePersonalDetails(){
        WaitUtils.waitForElementClickable(savePersonal);
        savePersonal.click();
    }

    public String getSuccessMsg(){
        WaitUtils.waitForElementVisibility(success);
        return success.getText();
    }

}
