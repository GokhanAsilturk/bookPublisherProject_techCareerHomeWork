package bookPublisherProject.exception.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookLogger {
    private static final Logger logger = LogManager.getLogger(BookLogger.class);

    public BookLogger() {
    }

    public static void logError(String errorMessage) {

        logger.error(errorMessage);

    }
}
