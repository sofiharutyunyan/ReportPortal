package ui.steps;

import com.codeborne.selenide.Selenide;
import dataholder.DashboardData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pojo.api.Content;
import pages.ui.DashboardPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.back;

public class DashboardSteps {

    private final DashboardPage dashboardPage = new DashboardPage();
    private final DashboardData dashboardData = new DashboardData();
    private final DashboardService service = new DashboardService();
    private List<Content> dashboardList = new ArrayList<>();

    @Given("User is on Dashboards page")
    public void user_is_on_dashboards_page() {
        Selenide.open("http://localhost:8080/ui/#autoreportportal/dashboard");
    }

    @When("User clicks on Create new Dashboard button")
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
        dashboardPage.addOrUpdateButton.click();
    }

    @And("Go back")
    public void goBack() {
        back();
    }

    @Then("The dashboard is created")
    public void verifyDashboardIsCreated() {
        dashboardList = service.getDashboardList();
        boolean foundInList = false;
        for (Content content : dashboardList) {
            if (content.getName().equals(dashboardData.getName())) {
                foundInList = true;
                break;
            }
        }
        Assertions.assertTrue(foundInList);
    }

    @When("User clicks on Edit Dashboard button")
    public void userEditDashboard() {
        dashboardPage.dashboardFirstItem.should(visible, Duration.ofSeconds(10));
        for (int i = 0; i < dashboardPage.editButtonsOfDashboards.size(); i++) {
            dashboardPage.editButtonsOfDashboards.get(i).click();
            dashboardPage.shareSwitcher.click();
            dashboardPage.addOrUpdateButton.click();
        }
    }

    @Then("The dashboards become shared")
    public void the_dashboard_become_shared() {
        dashboardList = service.getDashboardList();
        Assertions.assertTrue(dashboardList.get(0).isShare());
    }

    @When("User clicks on delete dashboard Icon")
    public void user_clicks_on_delete_dashboard_icon() {
        dashboardPage.dashboardFirstItem.should(visible, Duration.ofSeconds(10));
        dashboardPage.deleteIconsOfDashboards.get(dashboardPage.deleteIconsOfDashboards.size() - 1).click();
        dashboardPage.deleteButton.click();
    }

    @Then("Dashboard is deleted from dashboard list")
    public void dashboard_list_becomes_empty() {
        dashboardList = service.getDashboardList();
        boolean foundInList = true;
        for (Content content : dashboardList) {
            if (!content.getName().equals(dashboardData.getName())) {
                foundInList = false;
                break;
            }
        }
        Assertions.assertFalse(foundInList);
    }
}
