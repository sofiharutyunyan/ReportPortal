package webservice.test;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testng.Assert;
import pojo.api.Content;
import pojo.api.Dashboards;
import utils.JsonSchemaValidatorUtils;

import static configuration.constants.paths.JsonSchemeValidatorPaths.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;

import static configuration.constants.Endpoints.*;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class Junit5DashboardControllerTests extends BaseApiTest{

    @ParameterizedTest
    @DisplayName("Create new Dashboard")
    @MethodSource("testData")
    public void verifyCreateDashboardSucceed(String dashboardName) {
        String body = service.generateRequestBodyForCreationOfDashboard(dashboardName);
        Response createDashboardResponse = sendPostRequest(body, "/dashboard");
        createDashboardResponse.then().statusCode(201);
        JsonSchemaValidatorUtils.validateResponseUsingJsonSchema(createDashboardResponse, CREATE_DASHBOARD_RESPONSE_SCHEME_VALIDATOR_PATH);
    }

    @DisplayName("Verify that Get all dashboard response code is Succeed")
    @Test
    public void verifyGetSharedDashboardsStatusCode() {
        Response allSharedDashboardsResponse = sendGetRequest("/dashboard/shared");
        allSharedDashboardsResponse.then().statusCode(200);
        JsonSchemaValidatorUtils.validateResponseUsingJsonSchema(allSharedDashboardsResponse, GET_ALL_SHARED_DASHBOARDS_SCHEME_VALIDATOR_PATH);
    }

    @DisplayName("Verify that all returned dashboards are shared")
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

    private static Stream<Arguments> testData(){
        return Stream.of(
                arguments("Junit5 Demo1"),
                arguments("Junit5 Demo2"),
                arguments("Junit5 Demo3"),
                arguments("Junit5 Demo4"),
                arguments("Junit5 Demo5")
        );
    }
}
