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
    public List<Content> getSharedDashboardList(){
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
}
