package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class P04_CheckOutPage {
    private WebDriver driver;

    public P04_CheckOutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);


    }

    @FindBy(id = "input-payment-firstname")
    WebElement firstname;
    @FindBy(id = "input-payment-lastname")
    WebElement lastname;
    @FindBy(id = "input-payment-address-1")
    WebElement address;
    @FindBy(id = "input-payment-city")
    WebElement city;
    @FindBy(id = "input-payment-postcode")
    WebElement postcode;
    @FindBy(xpath = "//*[@id=\"input-payment-country\"]")
    WebElement country_dropdown;
    @FindBy(id = "input-payment-zone")
    WebElement state_dropdown;
    @FindBy(id = "button-payment-address")
    WebElement continue_billing_btn;
    @FindBy(id = "button-shipping-address")
    WebElement continue_shipping_address_btn;
    @FindBy(id = "button-shipping-method")
    WebElement continue_shipping_method_btn;
    @FindBy(xpath = "//*[@id=\"collapse-payment-method\"]/div/div[2]/div/input[1]")
    WebElement terms_checkbox;
    @FindBy(id = "button-payment-method")
    WebElement continue_paymentr_method_btn;
    @FindBy(id = "button-confirm")
    WebElement confirm_btn;





    public void addBillingDetails(String firstname, String lastname, String address, String city, String postcode, String country, String state) throws InterruptedException {
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
        this.address.sendKeys(address);
        this.city.sendKeys(city);
        this.postcode.sendKeys(postcode);
        Thread.sleep(2000);
        Select selectCountry = new Select(country_dropdown);
        selectCountry.selectByVisibleText(country);
        Select selectState = new Select(state_dropdown);
        selectState.selectByVisibleText(state);
        Thread.sleep(2000);
        continue_billing_btn.click();
        Thread.sleep(2000);
        continue_shipping_address_btn.click();
        Thread.sleep(2000);
        continue_shipping_method_btn.click();
        Thread.sleep(2000);
        terms_checkbox.click();
        Thread.sleep(2000);
        continue_paymentr_method_btn.click();
        Thread.sleep(2000);
        confirm_btn.click();
        Thread.sleep(3000);

    }


}
