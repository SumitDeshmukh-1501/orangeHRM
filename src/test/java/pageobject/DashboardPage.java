package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class DashboardPage {

    WebDriver driver;
    public DashboardPage(WebDriver refdriver){
        this.driver=refdriver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']")
    WebElement pim;



    public void clickPIM(){
        WaitUtils.waitForElementVisibility(pim);
        WaitUtils.waitForElementClickable(pim);
        pim.click();
    }
}
