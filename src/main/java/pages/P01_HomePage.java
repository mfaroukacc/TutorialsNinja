package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class P01_HomePage extends PageBase{


    protected WebDriver driver;

    public P01_HomePage(WebDriver driver)  {
        super(driver);
        this.driver = driver; // Assign the driver instance
        PageFactory.initElements(driver, this);
    }

    // locators
    @FindBy(xpath = "//li/a/span[text()='My Account']") // == driver.findElment(By.xpath)
            WebElement myAccount;

    @FindBy(xpath = "/html/body/nav/div/div[2]/ul/li[2]/ul/li[1]/a")
    WebElement registerBtn;
    @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a")
    WebElement loginBtn;
    @FindBy(xpath = "//*[@id=\"search\"]/input")
    WebElement searchfield;
    @FindBy(xpath = "//*[@id=\"search\"]/span/button")
    WebElement search_btn;
    @FindBy(xpath = "//*[@id=\"form-currency\"]/div/button")
    WebElement currency_btn;
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/p[2]")
    WebElement price_text;
    @FindBy(xpath = "//*[@id='menu']/div[2]/ul/li")
    List<WebElement> categoryList;
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[2]/button[1]")
    WebElement addtocart_btn;
    @FindBy(xpath = " //*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[2]/button[2]")
    WebElement wishlist_btn;
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[2]/button[3]")
    WebElement compare_btn;
    @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[5]")
    WebElement checkout_btn;



    public void moveToRegisterPage() {
        myAccount.click();
        registerBtn.click();
    }

    public void moveToLoginPage() {
        myAccount.click();
        loginBtn.click();
    }

    public void searchProduct(String searchdata) {
        searchfield.sendKeys(searchdata);
        search_btn.click();
    }

    public void changeCurrency() {
        currency_btn.click();
        List<WebElement> toolbarElements = driver.findElements(By.xpath("//*[@id=\"form-currency\"]/div/ul"));
        Random random = new Random();
        int randomIndex = random.nextInt(toolbarElements.size());
        WebElement randomElement = toolbarElements.get(randomIndex);
        Actions actions = new Actions(driver);
        actions.moveToElement(randomElement).click().perform();

    }

    public char getCurrency() {

        return currency_btn.getText().charAt(0);
    }

    public char getPrice() {

        return price_text.getText().charAt(0);
    }


    public void hoverRandmolyOnAnyCategory() {
        List<WebElement> toolbarElements = categoryList;
        hoverWebElement(driver,toolbarElements);

    }


    public void hoverAndSelectRandomFromDropdown() {
        List<WebElement> navbarItems = driver.findElements(By.xpath("//*[@id='menu']/div[2]/ul/li[position() <= 3]"));
        Actions actions = new Actions(driver);
        Random random = new Random();

        int randomNavbarIndex = random.nextInt(navbarItems.size());
        WebElement randomNavbarItem = navbarItems.get(randomNavbarIndex);
        actions.moveToElement(randomNavbarItem).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        List<WebElement> dropdownItems = wait.until(ExpectedConditions.visibilityOfAllElements(
                randomNavbarItem.findElements(By.xpath(".//div[@class='dropdown-menu']/a | .//ul/li/a"))
        ));

        if (!dropdownItems.isEmpty()) {
            int randomDropdownIndex = random.nextInt(dropdownItems.size());
            WebElement randomDropdownItem = dropdownItems.get(randomDropdownIndex);
            actions.moveToElement(randomDropdownItem).perform();

            System.out.println("Hovered over: " + randomDropdownItem.getText());
        } else {
            System.out.println("No dropdown items for: " + randomNavbarItem.getText());
        }
    }

    public void addToCart() {

        addtocart_btn.click();
    }

    public String successMessage() {

        return driver.findElement(By.xpath("//div[contains(@class, 'alert-success')]")).getText();
    }

    public String getProductName() {

        return driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[1]/div/div[2]/div[1]/h4/a")).getText();
    }

    public void addToWishList(){

        wishlist_btn.click();
    }
    public void compareItem(){

        compare_btn.click();
    }
    public void moveToCheckOutPage(){

        checkout_btn.click();
    }

}
