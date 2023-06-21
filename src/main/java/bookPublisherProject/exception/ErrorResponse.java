package bookPublisherProject.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private boolean status;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String details;

    public ErrorResponse(boolean status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}