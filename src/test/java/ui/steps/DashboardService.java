package ui.steps;

import configuration.constants.Common;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.api.Content;
import pojo.api.Dashboards;
import utils.JacksonAnnotationUtits;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DashboardService {
    public DashboardService() {
        RestAssured.baseURI = "http://localhost:8080/";
        RestAssured.basePath = "api/v1/" + Common.PROJECT_NAME;
    }

    public List<Content> getDashboardList() {
        Response response = sendGetRequest("/dashboard");
        Dashboards dashboards = (Dashboards) JacksonAnnotationUtits.convertJsonToPojo(response, Dashboards.class);
        return dashboards.getContent();
    }

    public List<Content> getSharedDashboardList() {
        Response response = sendGetRequest("/dashboard/shared");
        Dashboards dashboards = (Dashboards) JacksonAnnotationUtits.convertJsonToPojo(response, Dashboards.class);
        return dashboards.getContent();
    }

    public Response sendGetRequest(String endpoint) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5fd11cb8-a370-4c7c-b8d4-1e9f09f4c0b9")
                .when()
                .get(endpoint);
    }

//    TODO make method calls more generic with this helper methods
//    public SelenideElement getElementByName(String elementName) {
//        if (elementName.startsWith("//") || elementName.startsWith(".//") || elementName.startsWith("/")) {
//            return $x(elementName);
//        }
//        return (SelenideElement) invokeMethod(getMethodName(elementName, "get"));
//    }
//
//    private Object invokeMethod(final String methodName) {
//        return invokeMethod(methodName, DashboardPage.class);
//    }
//
//    private Object invokeMethod(final String methodName, final Class<?> clazz) {
//        if (clazz.isInstance(Object.class)) {
//            throw new RuntimeException(String.format("There is no one method with name %s", methodName));
//        }
//        try {
//            return clazz.getDeclaredMethod(methodName).invoke(DashboardPage.class);
//        } catch (IllegalAccessException | NoSuchMethodException e1) {
//            return invokeMethod(methodName, clazz.getSuperclass());
//        } catch (InvocationTargetException e2) {
//            throw new RuntimeException(methodName + "\n" + e2.getCause().getMessage());
//        }
//    }
//
//    private String getMethodName(final String elementName, final String prefix) {
//        String methodName = prefix + elementName.substring(0, 1).toUpperCase() + elementName.substring(1);
//        return methodName.replaceAll(SPACE, EMPTY);
//    }
}
