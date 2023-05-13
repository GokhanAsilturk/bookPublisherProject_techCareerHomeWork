package bookPublisherProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookRuntimeException extends RuntimeException{


    public BookRuntimeException(String message) {
        super(message);
    }
}
