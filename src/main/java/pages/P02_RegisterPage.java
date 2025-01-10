package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class P02_RegisterPage  {
    private  WebDriver driver;
    public P02_RegisterPage(WebDriver driver) {
        this.driver = driver; // Assign the driver instance
        PageFactory.initElements(driver, this);    }
    @FindBy(id = "input-firstname")
    WebElement firstname;
    @FindBy(id = "input-lastname")
    WebElement lastname;
    @FindBy(id = "input-email")
    WebElement email;
    @FindBy(id = "input-telephone")
    WebElement telephone;
    @FindBy(id = "input-password")
    WebElement password;
    @FindBy(id = "input-confirm")
    WebElement passconfirm;
    @FindBy(xpath = "//*[@id=\"content\"]/form/div/div/input[1]")
    WebElement privacycheck;
    @FindBy(xpath = "//*[@id=\"content\"]/form/div/div/input[2]")
    WebElement continueBtn;



    public void register(String firsname,String lastname,String generatedEmail,String telephone,String generatedPassword) {

        this.firstname.sendKeys(firsname);
        this.lastname.sendKeys(lastname);
        this.email.sendKeys(generatedEmail);
        this.telephone.sendKeys(telephone);
        this.password.sendKeys(generatedPassword);
        this.passconfirm.sendKeys(generatedPassword);
        privacycheck.click();
        continueBtn.click();

     }

public   String regeisterSuccessfullyWithValidData(){
return driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();

}

}
