package bookPublisherProject.exception;

public class ItemListIsEmptyException extends RuntimeException {
    public ItemListIsEmptyException(String message) {
        super(message);
    }
}
