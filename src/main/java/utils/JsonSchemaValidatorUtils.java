package utils;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.core.report.ListReportProvider;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.experimental.UtilityClass;
import io.restassured.response.Response;

@UtilityClass
public class JsonSchemaValidatorUtils {

    public void validateResponseUsingJsonSchema(Response response, String schemaPath) {
        final JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory
                .newBuilder()
                .setValidationConfiguration(ValidationConfiguration
                        .newBuilder()
                        .setDefaultVersion(SchemaVersion.DRAFTV4)
                        .freeze()
                )
                .setReportProvider(new ListReportProvider(LogLevel.ERROR, LogLevel.ERROR))
                .freeze();

        response.then()
                .assertThat()
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(schemaPath)
                        .using(jsonSchemaFactory)
                );
        //@TODO maybe better to return boolean and made assertion in test?
    }
}
