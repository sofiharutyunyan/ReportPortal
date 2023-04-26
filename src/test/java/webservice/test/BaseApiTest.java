package webservice.test;

import configuration.constants.Common;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import webservice.service.DashboardControllerService;

import static io.restassured.RestAssured.given;

public class BaseApiTest {

    protected final DashboardControllerService service = new DashboardControllerService();
    
    protected BaseApiTest(){
        RestAssured.baseURI = "http://localhost:8080/";
        RestAssured.basePath = "api/v1/" + Common.PROJECT_NAME;
    }

    public Response sendPostRequest(String body, String endpoint){
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5fd11cb8-a370-4c7c-b8d4-1e9f09f4c0b9")
                .when()
                .body(body)
                .post(endpoint);
    }

    public Response sendGetRequest(String endpoint){
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5fd11cb8-a370-4c7c-b8d4-1e9f09f4c0b9")
                .when()
                .get(endpoint);
    }
    public Response sendPutRequest(String endpoint, String body){
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5fd11cb8-a370-4c7c-b8d4-1e9f09f4c0b9")
                .body(body)
                .when()
                .put(endpoint);
    }

    public Response sendDeleteRequest(String endpoint){
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5fd11cb8-a370-4c7c-b8d4-1e9f09f4c0b9")
                .when()
                .delete(endpoint);
    }
}
