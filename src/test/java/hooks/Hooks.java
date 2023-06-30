package hooks;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.ui.LoginPage;

public class Hooks {

    private static LoginPage loginPage;

    @Before
    public static void setUp() {
        Configuration.timeout = 10000;
        loginPage = new LoginPage();
        loginPage.loginWithSuperadminCreds();
    }

    @After
    public static void tearDown(Scenario scenario) {
        if (scenario.isFailed()){
            throw new AssertionError("Scenario \""+scenario.getName()+"\"" + "failed: condition not met");
        }
        loginPage.logout();
    }
}
