package pojo.ui;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    public SelenideElement btnCreateNewDashboard = $(By.xpath(".//div[@class=\"addDashboardButton__add-dashboard-btn--_w75i\"]"));
    public SelenideElement newDashboardName = $(By.xpath(".//input[@class=\"input__input--3DC8i type-text variant-standard input__error--3ZvLE\"]"));
    public SelenideElement addButton = $(By.xpath(".//button[@class=\"bigButton__big-button--ivY7j bigButton__color-booger--2IfQT\"]"));

    public ElementsCollection editButtonsOfDashboards = $$(By.xpath(".//i[@class=\"icon__icon--2m6Od icon__icon-pencil--2-R6b\"]"));

    public SelenideElement shareSwitcher = $(By.xpath(".//div[@class=\"inputBigSwitcher__slider--1dQsG\"]"));

    public SelenideElement updateButton = $(By.xpath(".//button[@class='bigButton__big-button--ivY7j bigButton__color-booger--2IfQT']"));
}
