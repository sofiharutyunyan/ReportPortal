package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.api.Dashboards;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class JacksonAnnotationUtits {

    public Object convertJsonToPojo(Response response, Object object) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(response.asString(), Dashboards.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convertPojoToJsonToPojo(Object object) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

