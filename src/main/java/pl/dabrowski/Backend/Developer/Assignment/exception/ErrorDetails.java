package pl.dabrowski.Backend.Developer.Assignment.exception;

import java.util.Date;

public class ErrorDetails {

    private String message;
    private String details;
    private Date timeError;

    public ErrorDetails() {
    }

    public ErrorDetails(String message, String details, Date timeError) {
        this.message = message;
        this.details = details;
        this.timeError = timeError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getTimeError() {
        return timeError;
    }

    public void setTimeError(Date timeError) {
        this.timeError = timeError;
    }
}
