package ui.steps;

import com.codeborne.selenide.Selenide;
import dataholder.DashboardData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pojo.api.Content;
import pojo.ui.DashboardPage;

import java.util.List;

public class DashboardSteps {

    DashboardPage dashboardPage = new DashboardPage();
    DashboardData dashboardData = new DashboardData();
    DashboardService service = new DashboardService();

    @Given("User is on Dashboards page")
    public void user_is_on_dashboards_page() {
        Selenide.open("http://localhost:8080/ui/#autoreportportal/dashboard");
    }

    @Then("User clicks on Create new Dashboard button")
    public void user_clicks_on_create_new_dashboard_button() {
        dashboardPage.btnCreateNewDashboard.click();
    }

    @And("User sets dashboard {string}")
    public void user_sets_dashboard_demo1(String name) {
        dashboardPage.newDashboardName.sendKeys(name);
        dashboardData.setName(name);
    }

    @And("User clicks on \"Add\" button")
    public void userClicksOnAddButton() {
        dashboardPage.addButton.click();
    }

    @Then("The dashboard with is created")
    public void verifyDashboardIsCreated() {
        List<Content> dashboardList = service.getDashboardList();
        boolean foundInList = false;
        for (Content content : dashboardList) {
            if (content.getName().equals(dashboardData.getName())) {
                foundInList = true;
                break;
            }
        }
        Assertions.assertTrue(foundInList);
    }


}
