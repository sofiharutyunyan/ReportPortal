package webservice.test.httpClient;

import configuration.constants.Common;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DashboardTest {

    @Test
    public void verifyGetAllDashboardsStatusCodeIsOk() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/api/v1/" + Common.PROJECT_NAME + "/dashboard"))
                .setHeader("Authorization", "Bearer 5fd11cb8-a370-4c7c-b8d4-1e9f09f4c0b9")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(response.statusCode(), HttpStatus.SC_OK);

    }

}

