package utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@UtilityClass
public class RequestBodyUtils {
    // @TODO SneakyThrows

    public static String editJson(String pathOfFileToManipulate, Map<String, Object> valuesToReplaceWith) throws IOException {

        String editedJson;
        String projectDirectory = new File("").getAbsolutePath();
        Configuration freeMarkerConfig = new Configuration(Configuration.VERSION_2_3_29);
        freeMarkerConfig.setDirectoryForTemplateLoading(new File(projectDirectory+"/src/test/resources/"));

        Template freeMarkerTemplate = freeMarkerConfig.getTemplate(pathOfFileToManipulate);

        try (StringWriter out = new StringWriter()) {
            freeMarkerTemplate.process(valuesToReplaceWith, out);
            editedJson = out.getBuffer().toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return editedJson;
    }

}
