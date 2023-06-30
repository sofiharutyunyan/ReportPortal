package configuration.logger;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

public class ScreenshotSaver {

    @RegisterExtension
    public static ScreenShooterExtension makeScreenshotOnFailure = new ScreenShooterExtension(true).to("test-result/reports");

}
