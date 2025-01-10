package testcases;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.P01_HomePage;
import pages.P03_LoginPage;
import java.io.FileReader;
import static drivers.DriverHolder.getDriver;

public class TC01_HomePageTest extends TestBase {

    public String[] loadCredentials() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("credentials.json")) {
            JSONObject credentials = (JSONObject) parser.parse(reader);
            String email = (String) credentials.get("email");
            String password = (String) credentials.get("password");
            return new String[]{email, password};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test(priority = 1)
    public void navigateToRegistration() {
        P01_HomePage homePage = new P01_HomePage(getDriver());
        homePage.moveToRegisterPage();

    }

    @Test(priority = 2, description = "Check that logged User Could Search For any product")
    public void searchForProduct() {

        String[] credentials = loadCredentials();
        if (credentials != null) {
            String email = credentials[0];
            String password = credentials[1];
            P01_HomePage homePage = new P01_HomePage(getDriver());
            homePage.moveToLoginPage();
            P03_LoginPage p03LoginPage = new P03_LoginPage(getDriver());
            p03LoginPage.loginProcess(email, password);
            homePage.searchProduct("mac");
            softAssert = new SoftAssert();
            softAssert.assertTrue(getDriver().getPageSource().contains("Add to Cart"));
            softAssert.assertAll();
        }
    }

    @Test(priority = 3, description = "Check that Currency changed successfully")
    public void changeCurrency() {
        String[] credentials = loadCredentials();
        if (credentials != null) {
            String email = credentials[0];
            String password = credentials[1];
            P01_HomePage homePage = new P01_HomePage(getDriver());
            homePage.moveToLoginPage();
            P03_LoginPage p03LoginPage = new P03_LoginPage(getDriver());
            p03LoginPage.loginProcess(email, password);
            homePage.searchProduct("mac");
            homePage.changeCurrency();
            Assert.assertEquals(homePage.getCurrency(), homePage.getPrice());

        }
    }


    @Test(priority = 4, description = "Check Add To Cart button ")
    public void addProductToCart() {
        String[] credentials = loadCredentials();
        if (credentials != null) {
            String email = credentials[0];
            String password = credentials[1];
            P01_HomePage homePage = new P01_HomePage(getDriver());
            homePage.moveToLoginPage();
            P03_LoginPage p03LoginPage = new P03_LoginPage(getDriver());
            p03LoginPage.loginProcess(email, password);
            homePage.searchProduct("iPod Touch");
            homePage.addToCart();
            softAssert = new SoftAssert();
            softAssert.assertEquals(homePage.successMessage(), "Success: You have added " + homePage.getProductName() + " to your shopping cart!\n" +
                    "×");
            softAssert.assertAll();

        }
    }


    @Test(priority = 5, description = "Check Add To Cart button ")
    public void addProductToWishList() {
        String[] credentials = loadCredentials();
        if (credentials != null) {
            String email = credentials[0];
            String password = credentials[1];
            P01_HomePage homePage = new P01_HomePage(getDriver());
            homePage.moveToLoginPage();
            P03_LoginPage p03LoginPage = new P03_LoginPage(getDriver());
            p03LoginPage.loginProcess(email, password);
            homePage.searchProduct("mac");
            homePage.addToWishList();
            softAssert = new SoftAssert();
            softAssert.assertEquals(homePage.successMessage(), "Success: You have added " + homePage.getProductName() + " to your wish list!\n" +
                    "×");
            softAssert.assertAll();

        }
    }

    @Test(priority = 6, description = "Check Add To Cart button ")
    public void compareItem() {
        String[] credentials = loadCredentials();
        if (credentials != null) {
            String email = credentials[0];
            String password = credentials[1];
            P01_HomePage homePage = new P01_HomePage(getDriver());
            homePage.moveToLoginPage();
            P03_LoginPage p03LoginPage = new P03_LoginPage(getDriver());
            p03LoginPage.loginProcess(email, password);
            homePage.searchProduct("mac");
            homePage.compareItem();
            softAssert = new SoftAssert();
            softAssert.assertEquals(homePage.successMessage(), "Success: You have added " + homePage.getProductName() + " to your product comparison!\n" +
                    "×");
            softAssert.assertAll();

        }
    }


}



