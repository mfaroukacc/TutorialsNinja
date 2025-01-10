package testcases;

import drivers.DriverFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

import static drivers.DriverHolder.getDriver;
import static drivers.DriverHolder.setDriver;
import static pages.PageBase.quitBrowser;

public class TestBase {
     SoftAssert softAssert;


    @Parameters ("browsername")
    @BeforeTest

public void setupDriver (@Optional ("chrome") String browsername) {
        setDriver(DriverFactory.getNewInstance(browsername));
        getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        getDriver().get("https://tutorialsninja.com/demo/index.php?route=common/home");

    }
        @AfterTest
        public void teardown() {
            quitBrowser(getDriver());

        }
        }



