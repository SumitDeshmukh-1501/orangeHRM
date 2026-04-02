package pageobject;

import org.openqa.selenium.*;
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

    @FindBy(xpath=("//div[@class='oxd-loading-spinner']"))
    WebElement jobPageLoader;

    @FindBy(xpath="//i[@class='oxd-icon bi-calendar oxd-date-input-icon']")
    WebElement calDropdown;


    @FindBy(xpath="//div[@class='oxd-calendar-selector-year-selected']")
    WebElement calYearDropdown;

    @FindBy(xpath="//li[contains(@class,'oxd-calendar-dropdown--option')]")
    List<WebElement> calYearOptions;

    @FindBy(xpath="//div[@class='oxd-calendar-selector-month-selected']")
    WebElement calMonthDropdown;

    @FindBy(xpath="//li[contains(@class,'oxd-calendar-dropdown--option')]")
    List<WebElement> calmonthOptions;


    @FindBy(xpath="//div[@class='oxd-calendar-dates-grid']//div//div")
    List<WebElement> calDates;


    @FindBy(xpath="//a[normalize-space()='Job']")
    WebElement jobButton;


    @FindBy(xpath="//div[normalize-space()='Job Title']//following-sibling::div")
    WebElement jobTitleDropDown;

    @FindBy(xpath="//div[contains(@class,'oxd-select-option')]//span")
    List<WebElement>jobTitleOptions;

    By jobCategoryDropDown=By.xpath("//div[normalize-space()='Job Category']//following-sibling::div");


    @FindBy(xpath="//div[contains(@class,'oxd-select-option')]//span")
    List<WebElement>jobCategoryOptions;


    By subUnitDropDown= By.xpath("//div[normalize-space()='Sub Unit']//following-sibling::div");

    @FindBy(xpath="//div[contains(@class,'oxd-select-option')]//span")
    List<WebElement>subUnitOptions;


    By empStatusDropDown =By.xpath("//div[normalize-space()='Employment Status']//following-sibling::div");

    @FindBy(xpath="//div[contains(@class,'oxd-select-option')]//span")
    List<WebElement>empStatusOptions;

    @FindBy (xpath="//button[normalize-space()='Save']")
    WebElement jobSaveBtn;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast-content')]//p[contains(@class,'oxd-text')]")
    WebElement jobSaveMessage;

    By reportTo= By.xpath("//a[normalize-space()='Report-to']");
    By addbtn=By.xpath("(//button[contains(@class,'oxd-button')])[1]");
    By superwiserInput=By.xpath("//input[@placeholder='Type for hints...']");
    By superwiserOptions=By.xpath("//div[@class='oxd-autocomplete-option']");
    By reportingMethodDropdown=By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");
    By reportMethodOptions=By.xpath("(//div[@class='oxd-select-option'])");
    By saveSuperwiser=By.xpath("//button[@type='submit']");




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

    public void fillJoinedDate(String joinedDate){
        WaitUtils.waitForElementClickable(calDropdown);
        calDropdown.click();


        String [] date=joinedDate.split("-");

        System.out.println(date[1]);
        WaitUtils.waitForAllElementsToVisible(Arrays.asList(calYearDropdown,calMonthDropdown));
        calYearDropdown.click();
        for (WebElement e : calYearOptions){
            if(e.getText().equalsIgnoreCase(date[0])){
                WaitUtils.waitForElementClickable(e);
                e.click();
                break;
            }
        }

        WaitUtils.waitTill10sec();
        calMonthDropdown.click();

        for(int i=0; i<=11; i++){
           if(i== Integer.parseInt(date[1])){
               WaitUtils.waitForElementClickable(calmonthOptions.get(i));
               calmonthOptions.get(i).click();
               break;

            }
        }

        WaitUtils.waitTill10sec();

        for (WebElement e : calDates){
            if(e.getText().equalsIgnoreCase(date[2])){
                WaitUtils.waitForElementClickable(e);
                e.click();
                break;
            }
        }



    }



    public void selectJobTitle(String jobTitle){
        WaitUtils.waitTill10sec();
       JavaScriptUtils.scrollToTop();
        WaitUtils.waitTill10sec();

        WaitUtils.waitForElementClickable(jobTitleDropDown);
        jobTitleDropDown.click();


        for (WebElement e:jobTitleOptions){
            if(e.getText().equalsIgnoreCase(jobTitle)){
                WaitUtils.waitForElementClickable(e);
                e.click();
                break;
            }

        }
        WaitUtils.waitForElementToDisappear(loader);
    }

    public void selectJobCategory(String jobCategory) {
        WaitUtils.waitTill10sec();
        WebElement CategoryDropDown= driver.findElement(jobCategoryDropDown);
        WaitUtils.waitForElementClickable(CategoryDropDown);

        try {
            CategoryDropDown.click();
        }
        catch(StaleElementReferenceException e){
            WaitUtils.waitFortheElementRefresh(jobCategoryDropDown).click();
        }


        for (WebElement e : jobCategoryOptions) {
            if (e.getText().equalsIgnoreCase(jobCategory)) {
                WaitUtils.waitForElementClickable(e);
                e.click();
                break;
            }

        }
        WaitUtils.waitForElementToDisappear(loader);
    }
    public void selectSubUnit(String subUnit){
        WebElement UnitDropDown= driver.findElement(subUnitDropDown);
        WaitUtils.waitTill10sec();
        WaitUtils.waitForElementClickable(UnitDropDown);


        try {
            UnitDropDown.click();
        }
        catch(StaleElementReferenceException e){
            WaitUtils.waitFortheElementRefresh(subUnitDropDown).click();
        }


        for (WebElement e:subUnitOptions){

            if(e.getText().equalsIgnoreCase(subUnit)){
                WaitUtils.waitForElementClickable(e);
                e.click();
                break;
            }

        }
        WaitUtils.waitForElementToDisappear(loader);

    }
    public void selectEmployementStatus(String empStatus){


        WebElement StatusDropDown= driver.findElement(empStatusDropDown);
        WaitUtils.waitTill10sec();
        WaitUtils.waitForElementClickable(StatusDropDown);
        try {
            StatusDropDown.click();
        }
        catch(StaleElementReferenceException e){
            WaitUtils.waitFortheElementRefresh(empStatusDropDown).click();
        }

        for (WebElement e:empStatusOptions){
            if(e.getText().equalsIgnoreCase(empStatus)){
                WaitUtils.waitForElementClickable(e);
                e.click();
                break;
            }

        }
        WaitUtils.waitForElementToDisappear(loader);

    }

    public void saveJobDetails(){
        WaitUtils.waitForElementClickable(jobSaveBtn);
        jobSaveBtn.click();
    }

    public String getJobSaveMessage(){
        WaitUtils.waitForElementVisibility(jobSaveMessage);
        return jobSaveMessage.getText();
    }


    public void goToreportTo(){
       WebElement reportsTo= driver.findElement(reportTo);
       WaitUtils.waitForElementClickable(reportsTo);
       reportsTo.click();
    }

    public void clickAdd(){
        WaitUtils.waitForPageLoad();
        WaitUtils.waitForElementToDisappear(loader);
        WebElement add= driver.findElement(addbtn);
        WaitUtils.waitForElementClickable(add);
        add.click();
    }
    public void selectSuperWiser(String superwiserName){
        driver.findElement(superwiserInput).sendKeys("a");
        List<WebElement> superwisers=driver.findElements(superwiserOptions);
        for( WebElement e: superwisers){
            if(e.findElement(By.xpath("./span")).getText().equalsIgnoreCase(superwiserName)){
                WaitUtils.waitForElementClickable(e);
                e.click();
                break;
            }
        }

    }

    public void selectReportingMethod(String reportingMethodName){
        WebElement reporting=driver.findElement(reportingMethodDropdown);
        WaitUtils.waitForElementClickable(reporting);
        reporting.click();

        List<WebElement> reportinMethods=driver.findElements(reportMethodOptions);
        for( WebElement e: reportinMethods){
            if(e.findElement(By.xpath("./span")).getText().equalsIgnoreCase(reportingMethodName)){
                WaitUtils.waitForElementClickable(e);
                e.click();
                break;
            }
        }

    }

    public void saveSuperwiserDetails(){
           WebElement save= driver.findElement(saveSuperwiser);
            WaitUtils.waitForElementClickable(save);
            save.click();
    }

    public String getSaveSuperwiserMessage(){
        WaitUtils.waitForElementToDisappear(loader);
        WaitUtils.waitForElementVisibility(successMsg);
        return successMsg.getText();
    }

}
