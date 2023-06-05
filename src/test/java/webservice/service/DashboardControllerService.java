package webservice.service;

import io.restassured.response.Response;
import utils.CommonUtils;
import utils.JacksonAnnotationUtits;
import utils.RequestBodyUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static configuration.constants.paths.RequestBodyPaths.*;
import static configuration.constants.paths.RequestBodyPaths.CREATE_WIDGET_FILE_PATH;

public class DashboardControllerService {

//    Parameter is added just to have TDD testcase, Later will be removed and uncommented below line with random name generator.
    public String generateRequestBodyForCreationOfDashboard(String dashboardName) {

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("description", "New Description");
        jsonAsMap.put("name", dashboardName);
//        jsonAsMap.put("name", CommonUtils.generateRandomStringWithPrefix("Dashboard"));
        jsonAsMap.put("share", true);

        try {
            return RequestBodyUtils.editJson(CREATE_DASHBOARD_FILE_PATH, jsonAsMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateRequestBodyForUpdatingTheDashboard() {

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("description", "Dashboard Description");
        jsonAsMap.put("name", "Updated Name");
        jsonAsMap.put("share", false);

        try {
            return RequestBodyUtils.editJson(UPDATE_DASHBOARD_FILE_PATH, jsonAsMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateRequestBodyForAddingWidgetToDashboard(int widgetId) {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("widgetName", CommonUtils.generateRandomStringWithPrefix("Widget"));
        jsonAsMap.put("widgetId", widgetId);
        jsonAsMap.put("share", false);

        try {
            return RequestBodyUtils.editJson(ADD_WIDGET_TO_DASHBOARD_FILE_PATH, jsonAsMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateRequestBodyForCreationOfWidget() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", CommonUtils.generateRandomStringWithPrefix("Widget"));
        jsonAsMap.put("share", true);

        try {
            return RequestBodyUtils.editJson(CREATE_WIDGET_FILE_PATH, jsonAsMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object convertResponseToJson(Response response, Object object) {
        return JacksonAnnotationUtits.convertJsonToPojo(response, object);
    }

}
