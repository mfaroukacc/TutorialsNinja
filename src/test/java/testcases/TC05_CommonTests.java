package testcases;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P03_LoginPage;

import java.io.FileReader;

import static drivers.DriverHolder.getDriver;

public class TC05_CommonTests extends TestBase{

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


    @Test(priority = 1, description = "Varify that logged user could choose different categories")
    public void hoverRandomlyOnAnyCategory() throws InterruptedException {

        String[] credentials = loadCredentials();
        if (credentials != null) {
            String email = credentials[0];
            String password = credentials[1];
            P01_HomePage homePage = new P01_HomePage(getDriver());
            homePage.moveToLoginPage();
            P03_LoginPage p03LoginPage = new P03_LoginPage(getDriver());
            p03LoginPage.loginProcess(email, password);
            Thread.sleep(2000);
            homePage.hoverRandmolyOnAnyCategory();
        }
    }
    @Test(priority = 2, description = "Choose any Tag from the first three tags in the navbar")
    public void chooseOneOfTheFirstThreeTags() {
        String[] credentials = loadCredentials();
        if (credentials != null) {
            String email = credentials[0];
            String password = credentials[1];
            P01_HomePage homePage = new P01_HomePage(getDriver());
            homePage.moveToLoginPage();
            P03_LoginPage p03LoginPage = new P03_LoginPage(getDriver());
            p03LoginPage.loginProcess(email, password);
            homePage.hoverAndSelectRandomFromDropdown();


        }
    }



}
