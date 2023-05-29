package pages.ui;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    public SelenideElement btnCreateNewDashboard = $(By.xpath(".//div[@class=\"addDashboardButton__add-dashboard-btn--_w75i\"]"));
    public SelenideElement newDashboardName = $(By.xpath(".//input[@class=\"input__input--3DC8i type-text variant-standard input__error--3ZvLE\"]"));
    public SelenideElement addOrUpdateButton = $(By.xpath(".//button[@class=\"bigButton__big-button--ivY7j bigButton__color-booger--2IfQT\"]"));

    public ElementsCollection editButtonsOfDashboards = $$(By.xpath(".//i[@class=\"icon__icon--2m6Od icon__icon-pencil--2-R6b\"]"));

    public SelenideElement shareSwitcher = $(By.xpath(".//div[@class=\"inputBigSwitcher__slider--1dQsG\"]"));

    public ElementsCollection deleteIconsOfDashboards = $$(By.xpath(".//i[@class='icon__icon--2m6Od icon__icon-delete--1jIHF']"));

    public SelenideElement deleteButton = $(By.xpath(".//button[@class='bigButton__big-button--ivY7j bigButton__color-tomato--Wvy5L']"));
    public SelenideElement dashboardFirstItem = $(By.xpath(".//div[@class='gridRow__grid-row-wrapper--1dI9K']"));


//    Later will be used with invoke method
//    public SelenideElement getAddOrUpdateButton(){
//        return $(By.xpath(".//button[@class='bigButton__big-button--ivY7j bigButton__color-booger--2IfQT']"));
//    }

}
