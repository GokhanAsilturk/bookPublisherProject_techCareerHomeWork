package bookPublisherProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception (Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(false,"Error! :)", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<?> authorNotFound(AuthorNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(false, "Author Not Found! :)", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> bookNotFound(BookNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(false, "Book Not Found! :)", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<?> adminNotFound(AdminNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(false, "Admin Not Found! :)", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserListIsEmptyException.class)
    public ResponseEntity<?> userListIsEmpty(UserListIsEmptyException exception) {
        ErrorResponse errorResponse = new ErrorResponse(false, "User List is Empty! :)", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemListIsEmptyException.class)
    public ResponseEntity<?> itemListIsEmpty(ItemListIsEmptyException exception) {
        ErrorResponse errorResponse = new ErrorResponse(false, "Item List is Empty! :)", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> customerNotFound(CustomerNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(false, "Customer Not Found! :)", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}