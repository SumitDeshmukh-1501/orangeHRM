package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
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

    By countryOption=By.xpath("//div[contains(@class,'oxd-select-dropdown')]//div//span");

    @FindBy(xpath="(//input[contains(@class,'oxd-input') and @placeholder='yyyy-dd-mm'])[2]")
    WebElement dob;

    @FindBy(xpath="//div[@class='oxd-radio-wrapper']//label")
    List<WebElement> genderslabel;

    @FindBy(xpath="//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button[@type='submit' and normalize-space()='Save']")
    WebElement savePersonal;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text']")
    WebElement success;

    @FindBy(xpath=("//div[@class='oxd-loading-spinner']"))
    WebElement loader;

    @FindBy(xpath="//div[@class='oxd-calendar-selector-year-selected']")
    WebElement calYearDropdown;

    @FindBy(xpath="//li[contains(@class,'oxd-calendar-dropdown--option')]")
    WebElement calYearOptions;

    @FindBy(xpath="//div[@class='oxd-calendar-selector-month-selected']")
    WebElement calMonthDropdown;

    @FindBy(xpath="//li[contains(@class,'oxd-calendar-dropdown--option')]")
    WebElement monthOptions;


    @FindBy(xpath="//div[@class='oxd-calendar-dates-grid']//div//div")
    List<WebElement> calDates;






    @FindBy(xpath="(//div[@class='oxd-loading-spinner-container'])[1]")
    WebElement jobPageLoader;



    @FindBy(xpath="//a[normalize-space()='Job']")
    WebElement jobButton;


    @FindBy(xpath="(//div[contains(@class,'oxd-select-wrapper')])[1]")
    WebElement jobTitleDropDown;

    @FindBy(xpath="//div[contains(@class,'oxd-select-option')]//span")
    List<WebElement>jobTitleOptions;

    @FindBy(xpath="(//div[contains(@class,'oxd-select-wrapper')])[2]")
    WebElement jobCategoryDropDown;

    @FindBy(xpath="//div[contains(@class,'oxd-select-option')]//span")
    List<WebElement>jobCategoryOptions;

    @FindBy(xpath="(//div[contains(@class,'oxd-select-wrapper')])[3]")
    WebElement subUnitDropDown;

    @FindBy(xpath="//div[contains(@class,'oxd-select-option')]//span")
    List<WebElement>subUnitOptions;

    @FindBy(xpath="(//div[contains(@class,'oxd-select-wrapper')])[5]")
    WebElement empStatusDropDown;

    @FindBy(xpath="//div[contains(@class,'oxd-select-option')]//span")
    List<WebElement>empStatusOptions;

    @FindBy (xpath="//button[normalize-space()='Save']")
    WebElement jobSaveBtn;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast-content')]//p[contains(@class,'oxd-text')]")
    WebElement jobSaveMessage;





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

        List<WebElement>options=driver.findElements(countryOption);
        WaitUtils.waitForAllElementsToVisible(options);

        for(WebElement e:options) {

            if (e.getText().equalsIgnoreCase(country)) {
                WaitUtils.waitForElementClickable(e);
                e.click();
                break;
            }
        }




    }

    public void giveDOB(String birthdate){

        dob.sendKeys(birthdate);
    }

    public void selectGender(String gender){
        
        
        for (WebElement e:genderslabel) {
            if(e.getText().equalsIgnoreCase(gender)){
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

    public void goToJob(){
        WaitUtils.waitForPageLoad();
        WaitUtils.waitForElementClickable(jobButton);
        jobButton.click();
        WaitUtils.waitForElementToDisappear(jobPageLoader);


    }

    public void fillJoinedDate(){

    }



    public void selectJobTitle(){
        WaitUtils.waitForElementClickable(jobTitleDropDown);
        jobTitleDropDown.click();


        for (WebElement e:jobTitleOptions){
//            if(e.getText().equalsIgnoreCase("sss")){
//                WaitUtils.waitForElementClickable(e);
//                e.click();
//            }
            System.out.println(e.getText());


        }
        System.out.println("------------------------------------------");
    }

    public void selectJoinCategory(){
        WaitUtils.waitForElementClickable(jobCategoryDropDown);
        jobCategoryDropDown.click();


        for (WebElement e:jobCategoryOptions){
//            if(e.getText().equalsIgnoreCase("sss")){
//                WaitUtils.waitForElementClickable(e);
//                e.click();
//            }
            System.out.println(e.getText());
        }
        System.out.println("------------------------------------------");
    }
    public void selectSubUnit(){
        WaitUtils.waitForElementClickable(subUnitDropDown);
        subUnitDropDown.click();


        for (WebElement e:subUnitOptions){
//            if(e.getText().equalsIgnoreCase("sss")){
//                WaitUtils.waitForElementClickable(e);
//                e.click();
//            }
            System.out.println(e.getText());
        }
        System.out.println("------------------------------------------");
    }
    public void selectEmployementStatus(){

        WaitUtils.waitForElementClickable(empStatusDropDown);
        empStatusDropDown.click();


        for (WebElement e:empStatusOptions){
//            if(e.getText().equalsIgnoreCase("sss")){
//                WaitUtils.waitForElementClickable(e);
//                e.click();
//            }
            System.out.println(e.getText());
        }
        System.out.println("------------------------------------------");
    }

    public void saveJobDetails(){
        WaitUtils.waitForElementClickable(jobSaveBtn);
        jobSaveBtn.click();
    }

    public String getJobSaveMessage(){
        WaitUtils.waitForElementVisibility(jobSaveMessage);
        return jobSaveMessage.getText();
    }

}
