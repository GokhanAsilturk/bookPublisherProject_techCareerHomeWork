package bookPublisherProject.exception;

import bookPublisherProject.exception.logger.BookLogger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookException extends ResponseStatusException {


    public BookException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
        logException(reason);
    }

    public void logException(String reason){
        BookLogger.logError(reason);
    }


    @Override
    public HttpHeaders getResponseHeaders() {
        return super.getResponseHeaders();
    }
}
