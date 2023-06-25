package bookPublisherProject.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private int errorCode;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String details;

    public ErrorResponse(ExceptionType exceptionType, String details) {
        this.errorCode = exceptionType.getErrorCode();
        this.message = exceptionType.getMessage();
        this.details = details;
    }

}