package bookPublisherProject.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private int errorCode;
    private boolean status;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String details;

    public ErrorResponse(int errorCode, boolean status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

}