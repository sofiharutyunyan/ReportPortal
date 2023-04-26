package webservice.test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.api.Content;
import pojo.api.Dashboards;
import utils.JsonSchemaValidatorUtils;

import java.util.List;

import static configuration.constants.Endpoints.GET_ALL_SHARED_DASHBOARDS;
import static configuration.constants.paths.JsonSchemeValidatorPaths.*;

public class TestNgDashboardControllerTests extends BaseApiTest{


    @Test(dataProvider = "testData")
    public void verifyCreateDashboardSucceed(String dashboardName) {
        String body = service.generateRequestBodyForCreationOfDashboard(dashboardName);
        Response createDashboardResponse = sendPostRequest(body, "/dashboard");
        createDashboardResponse.then().statusCode(201);
        JsonSchemaValidatorUtils.validateResponseUsingJsonSchema(createDashboardResponse, CREATE_DASHBOARD_RESPONSE_SCHEME_VALIDATOR_PATH);
    }

    @Test
    public void verifyGetAllDashboardsStatusCode() {
        Response allDashboardsResponse = sendGetRequest("/dashboard");
        allDashboardsResponse.then().statusCode(200);
        JsonSchemaValidatorUtils.validateResponseUsingJsonSchema(allDashboardsResponse, GET_ALL_DASHBOARDS_SCHEME_VALIDATOR_PATH);
    }

    @Test
    public void verifyGetSharedDashboardsStatusCode() {
        Response allSharedDashboardsResponse = sendGetRequest("/dashboard/shared");
        allSharedDashboardsResponse.then().statusCode(200);
        JsonSchemaValidatorUtils.validateResponseUsingJsonSchema(allSharedDashboardsResponse, GET_ALL_SHARED_DASHBOARDS_SCHEME_VALIDATOR_PATH);
    }

    @Test
    public void verifyAllReturnedDashboardsAreShared() {
        Response allSharedDashboardsResponse = sendGetRequest(GET_ALL_SHARED_DASHBOARDS);
        Dashboards dashboards = (Dashboards) service.convertResponseToJson(allSharedDashboardsResponse, Dashboards.class);
        List<Content> dashboard = dashboards.getContent();
        for (int i = 0; i < dashboard.size(); i++) {
            if (dashboard.get(i).isShare()) {
                Assert.assertTrue(dashboards.getContent().get(i).isShare());
            }
        }
    }
    @DataProvider(name = "testData")
    private Object[][] testData(){
        return new Object[][] {{"TestNG Dash 1"}, {"TestNG Dash 2"}, {"TestNG Dash 3"}, {"TestNG Dash 4"}, {"TestNG Dash 5"}};
    }
}
