import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import pojo.ui.LoginPage;

public class Hooks {

    private static LoginPage loginPage = new LoginPage();

    @Before
    public static void setUp() {
        loginPage.loginWithSuperadminCreds();
    }

    @After
    public static void tearDown(Scenario scenario) {
        Configuration.timeout = 10000;
        loginPage.logout();
//        if(scenario.isFailed()) {
//
//        }
    }
}
