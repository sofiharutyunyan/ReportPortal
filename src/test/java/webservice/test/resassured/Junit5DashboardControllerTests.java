package webservice.test.resassured;

import configuration.constants.Tags;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.testng.Assert;
import pojo.api.Content;
import pojo.api.Dashboards;
import utils.JsonSchemaValidatorUtils;

import java.util.List;

import static configuration.constants.Endpoints.*;
import static configuration.constants.paths.JsonSchemeValidatorPaths.*;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class Junit5DashboardControllerTests extends BaseApiTest {

    @Test
    @Tag(Tags.POSITIVE)
    @DisplayName("Create new Dashboard")
    @Order(1)
    public void verifyCreateDashboardSucceed() {
        Response createDashboardResponse = getCreateDashboardResponse("New Dashboard", "/dashboard", 201);
        JsonSchemaValidatorUtils.validateResponseUsingJsonSchema(createDashboardResponse, CREATE_DASHBOARD_RESPONSE_SCHEME_VALIDATOR_PATH);
    }

    @Test
    @Tag(Tags.NEGATIVE)
    @DisplayName("Create new Dashboard with incorrect endpoint")
    public void verifyCreateDashboardDoesNotWorkWithIncorrectEndpoint() {
        getCreateDashboardResponse("Incorrect Dashboard", "/dashboards", 404);
    }

    @DisplayName("Verify that Get all dashboard response code is Succeed")
    @Test
    @Tag(Tags.POSITIVE)
    public void verifyGetSharedDashboardsStatusCode() {
        Response allSharedDashboardsResponse = sendGetRequest("/dashboard/shared");
        allSharedDashboardsResponse.then().statusCode(200);
        JsonSchemaValidatorUtils.validateResponseUsingJsonSchema(allSharedDashboardsResponse, GET_ALL_SHARED_DASHBOARDS_SCHEME_VALIDATOR_PATH);
    }

    @DisplayName("Verify that Get all dashboard response code is not Succeed with incorrect endpoint")
    @Test
    @Tag(Tags.NEGATIVE)
    public void verifyGetSharedDashboardsStatusCodeIsNotSuccessfulWithIncorrectEndpoint() {
        Response allSharedDashboardsResponse = sendGetRequest("/dashboard/share");
        allSharedDashboardsResponse.then().statusCode(400);
    }

    @DisplayName("Verify that all returned dashboards are shared")
    @Test
    @Tag(Tags.POSITIVE)
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

    @Test
    @Tag(Tags.POSITIVE)
    public void getDashboardById() {
        Response createDashboardResponse = getCreateDashboardResponse("Dashboard must be returned", "/dashboard", 201);
        Response allSharedDashboardsResponse = sendGetRequest(String.format(GET_DASHBOARD_BY_ID, createDashboardResponse.jsonPath().get("id").toString()));
        allSharedDashboardsResponse.then().statusCode(200);
        JsonSchemaValidatorUtils.validateResponseUsingJsonSchema(allSharedDashboardsResponse, GET_DASHBOARD_BY_ID_RESPONSE_SCHEME_VALIDATOR_PATH);
    }


    @Test
    @Tag(Tags.POSITIVE)
    public void updateExistingDashboard() {
        Response createDashboardResponse = getCreateDashboardResponse("Dashboard must be updated", "/dashboard", 201);
        String body = service.generateRequestBodyForUpdatingTheDashboard();
        Response allSharedDashboardsResponse = sendPutRequest(String.format(CREATE_DASHBOARD, createDashboardResponse.jsonPath().get("id").toString()), body);
        allSharedDashboardsResponse.then().statusCode(200);
        JsonSchemaValidatorUtils.validateResponseUsingJsonSchema(allSharedDashboardsResponse, SUCCESS_MESSAGE_SCHEME_VALIDATOR_PATH);
    }

//    @Test
//    @Tag(Tags.POSITIVE)
//    public void addWidgetToDashboard() {
//        Response createDashboardResponse = getCreateDashboardResponse("Widg in Dash", "/dashboard", 201);
//        Response createWidgetResponse = sendPostRequest(service.generateRequestBodyForCreationOfWidget(), CREATE_WIDGET);
//        createWidgetResponse.then().statusCode(201);
//        Response addNewWidget = sendPutRequest(String.format(ADD_WIDGET_TO_DASHBOARD, createDashboardResponse.jsonPath().get("id")), service.generateRequestBodyForAddingWidgetToDashboard(createWidgetResponse.jsonPath().get("id")));
//        addNewWidget.then().statusCode(200);
//        JsonSchemaValidatorUtils.validateResponseUsingJsonSchema(addNewWidget, SUCCESS_MESSAGE_SCHEME_VALIDATOR_PATH);
//    }

//    @Test
//    @Tag(Tags.POSITIVE)
//    public void deleteWidgetFromDashboard() {
//        Response createDashboardResponse = getCreateDashboardResponse("Delete Widget", "/dashboard", 201);
//        Response createWidgetResponse = sendPostRequest(service.generateRequestBodyForCreationOfWidget(), CREATE_WIDGET);
//        createWidgetResponse.then().statusCode(201);
//        Response addNewWidget = sendPutRequest(String.format(ADD_WIDGET_TO_DASHBOARD, createDashboardResponse.jsonPath().get("id")), service.generateRequestBodyForAddingWidgetToDashboard(createWidgetResponse.jsonPath().get("id")));
//        addNewWidget.then().statusCode(200);
//
//        Response allSharedDashboardsResponse = sendDeleteRequest("/dashboard/" + createWidgetResponse.jsonPath().get("id") + addNewWidget.jsonPath().get(""));
//        allSharedDashboardsResponse.then().statusCode(200);
//        JsonSchemaValidatorUtils.validateResponseUsingJsonSchema(allSharedDashboardsResponse, SUCCESS_MESSAGE_SCHEME_VALIDATOR_PATH);
//    }

    @Test
    @Tag(Tags.POSITIVE)
    public void deleteDashboardById() {
        Response createDashboardResponse = getCreateDashboardResponse("Dashboard deletable", "/dashboard", 201);
        Response allSharedDashboardsResponse = sendDeleteRequest("/dashboard/" + createDashboardResponse.jsonPath().get("id"));
        allSharedDashboardsResponse.then().statusCode(200);
        JsonSchemaValidatorUtils.validateResponseUsingJsonSchema(allSharedDashboardsResponse, SUCCESS_MESSAGE_SCHEME_VALIDATOR_PATH);
    }

    @Test
    @Tag(Tags.NEGATIVE)
    public void deleteDashboardByIncorrectId() {
        Response allSharedDashboardsResponse = sendDeleteRequest("/dashboard/" + "unknownId");
        allSharedDashboardsResponse.then().statusCode(400);
    }

    private Response getCreateDashboardResponse(String Dashboard_deletable, String endpoint, int i) {
        String body = service.generateRequestBodyForCreationOfDashboard(Dashboard_deletable);
        Response createDashboardResponse = sendPostRequest(body, endpoint);
        createDashboardResponse.then().statusCode(i);
        return createDashboardResponse;
    }

}
