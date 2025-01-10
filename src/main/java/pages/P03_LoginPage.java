package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class P03_LoginPage extends PageBase {
    private WebDriver driver;

    public P03_LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(id = "input-email")
    WebElement emailInputField;
    @FindBy(id = "input-password")
    WebElement passwordInputField;
    @FindBy(xpath = "//input[@value='Login']")
    WebElement login_Btn;
    @FindBy(xpath = "//html/body/div[2]/div/div/div/div[2]/div/form/div[2]/a")
    WebElement forgetpasswordbtn;
    @FindBy(xpath = "//*[@id=\"content\"]/form/div/div[2]/input")
    WebElement continue_forget_btn;

    public void loginProcess(String email, String password) {

        this.emailInputField.sendKeys(email);
        this.passwordInputField.sendKeys(password);
        login_Btn.click();
    }

    public void forgetPassword(String email) {
        forgetpasswordbtn.click();
        emailInputField.sendKeys(email);
        continue_forget_btn.click();


    }


    public String loginSuccessfully() {
        return driver.findElement(By.xpath("(//h2)[1]")).getText();
    }
public String forgetPasswordSuccessfully (){
    return driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]\n")).getText();


}
}
