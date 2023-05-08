package pojo.ui;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    public SelenideElement btnCreateNewDashboard = $(By.xpath(".//div[@class=\"addDashboardButton__add-dashboard-btn--_w75i\"]"));
    public SelenideElement newDashboardName = $(By.xpath(".//input[@class=\"input__input--3DC8i type-text variant-standard input__error--3ZvLE\"]"));
    public SelenideElement addButton = $(By.xpath(".//button[@class=\"bigButton__big-button--ivY7j bigButton__color-booger--2IfQT\"]"));

}
