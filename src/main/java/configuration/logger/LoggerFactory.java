package configuration.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoggerFactory {
    private static final Logger logger = LogManager.getLogger("Dashboard logger");

    public static Logger getLogger() {
        return logger;
    }
}
