package bookPublisherProject.exception;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookException extends ResponseStatusException {

    public BookException(HttpStatus httpStatus,String reason) {
        super(httpStatus, reason);
        logException(reason);
    }

    public void logException(String reason) {

    }

    @Override
    public HttpHeaders getResponseHeaders() {
        return super.getResponseHeaders();
    }

}
