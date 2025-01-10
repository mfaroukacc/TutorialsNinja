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

public class TC03_LoginTest extends TestBase {

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


    @Test(priority = 1, description = "Login With Valid Data TestCase")
    public void loginWithValidData_P() {
        String[] credentials = loadCredentials();
        if (credentials != null) {
            String email = credentials[0];
            String password = credentials[1];
            P01_HomePage homePage = new P01_HomePage(getDriver());
            homePage.moveToLoginPage();
            P03_LoginPage p03LoginPage = new P03_LoginPage(getDriver());
            p03LoginPage.loginProcess(email, password);

            // TODO: add hard assertion
            Assert.assertEquals(p03LoginPage.loginSuccessfully(), "My Account");
            Assert.assertFalse(!getDriver().getPageSource().contains("Logout"));
            Assert.assertEquals(getDriver().getCurrentUrl(), "https://tutorialsninja.com/demo/index.php?route=account/account");

            // TODO: Softassert
            softAssert = new SoftAssert();
            softAssert.assertEquals(p03LoginPage.loginSuccessfully(), "My Account");
            softAssert.assertFalse(!getDriver().getPageSource().contains("Logout"));
            softAssert.assertEquals(getDriver().getCurrentUrl(), "https://tutorialsninja.com/demo/index.php?route=account/account");
            softAssert.assertAll();
        }
    }

    @Test(priority = 2, description = "Check That ForgetPassword Works Successfully")

    public void successForgetPassword() {
        P01_HomePage homePage = new P01_HomePage(getDriver());
        homePage.moveToLoginPage();
        String[] credentials = loadCredentials();
        if (credentials != null) {
            String email = credentials[0];
            P03_LoginPage p03LoginPage = new P03_LoginPage(getDriver());
            p03LoginPage.forgetPassword(email);
            Assert.assertEquals(p03LoginPage.forgetPasswordSuccessfully(), "An email with a confirmation link has been sent your email address.");

        }

    }
}
