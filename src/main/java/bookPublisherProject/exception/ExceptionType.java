package bookPublisherProject.exception;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    GENERIC_EXCEPTION(1,"Unkown Error!"),

    DATA_NOT_FOUND(1001,"Data Not Found!");

    private final Integer errorCode;
    private final String message;

}
