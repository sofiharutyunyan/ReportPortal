package hooks;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pojo.ui.LoginPage;

public class Hooks {

    private static final LoginPage loginPage = new LoginPage();

    @Before
    public static void setUp() {
        Configuration.timeout = 10000;
        loginPage.loginWithSuperadminCreds();
    }

    @After
    public static void tearDown(Scenario scenario) {
        loginPage.logout();
    }
}
