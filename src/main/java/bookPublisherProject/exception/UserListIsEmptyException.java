package bookPublisherProject.exception;

public class UserListIsEmptyException extends RuntimeException {

    public UserListIsEmptyException(String message) {
        super(message);
    }
}
