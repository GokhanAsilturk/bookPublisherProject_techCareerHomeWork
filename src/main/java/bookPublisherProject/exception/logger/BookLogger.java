package bookPublisherProject.exception.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookLogger {

    private static Logger logger = LogManager.getLogger(BookLogger.class);


    public static void logError(String errorMessage){
        logger.error(errorMessage);
    }
}
