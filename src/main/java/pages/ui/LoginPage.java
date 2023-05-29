package pages.ui;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.conditions.Visible;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement login = $(By.name("login"));
    private final SelenideElement password = $(By.name("password"));
    private final SelenideElement btnLogin = $(By.xpath(".//button[@class=\"bigButton__big-button--ivY7j bigButton__rounded-corners--3wKBL bigButton__color-organish--4iYXy\"]"));
    public SelenideElement userAvatar = $(By.xpath(".//img[@class=\"userBlock__avatar--Lhnmc\"]"));
    public SelenideElement logout = $(By.xpath(".//div[@class='userBlock__menu-item--3VBsZ']"));

    //   TODO Later change hardcoded credentials and make more secure
    public void loginWithSuperadminCreds() {
        open("http://localhost:8080/ui/#login");
        login.setValue("superadmin");
        password.sendKeys("Docker123");
        btnLogin.click();
    }

    public void logout() {

        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(userAvatar));
        userAvatar.click();
        logout.click();
    }
}
