package testcases;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P03_LoginPage;
import pages.P04_CheckOutPage;

import java.io.FileReader;

import static drivers.DriverHolder.getDriver;

public class TC04_CheckOutTest extends TestBase {

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


    @Test(priority = 1, description = "Create successful Order")
    public void successfullCheckOutProccess() throws InterruptedException {

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
            homePage.moveToCheckOutPage();
            P04_CheckOutPage checkOutPage=new P04_CheckOutPage(getDriver());
            checkOutPage.addBillingDetails("Mohamed","Elsherif","Cairo","Cairo","12512","Egypt","Aswan");
            Thread.sleep(2000);
            Assert.assertTrue(getDriver().getPageSource().contains("Your order has been placed"));
        }
    }
}
