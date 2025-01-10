package testcases;

import org.testng.Assert;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P02_RegisterPage;

import static drivers.DriverHolder.getDriver;

public class TC02_RegisterTest extends TestBase{
    Faker faker = new Faker();



    public void saveCredentials(String email, String password) {
        JSONObject credentials = new JSONObject();
        credentials.put("email", email);
        credentials.put("password", password);

        try (FileWriter file = new FileWriter("credentials.json")) {
            file.write(credentials.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test (priority = 1)

    public void registerWithValidData_P (){

        String firsname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String generatedEmail = faker.internet().emailAddress();
        String generatedPassword = faker.internet().password();
        String telephone = faker.phoneNumber().subscriberNumber(11);
        saveCredentials(generatedEmail, generatedPassword);
        P01_HomePage homePage=new P01_HomePage(getDriver());
        homePage.moveToRegisterPage();
        P02_RegisterPage p02RegisterPage=new P02_RegisterPage(getDriver());
        p02RegisterPage.register(firsname,lastname,generatedEmail,telephone,generatedPassword);
        Assert.assertEquals(p02RegisterPage.regeisterSuccessfullyWithValidData(),"Your Account Has Been Created!");
}

}
