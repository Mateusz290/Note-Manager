package pl.dabrowski.Backend.Developer.Assignment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.dabrowski.Backend.Developer.Assignment.exception.ErrorDetails;
import pl.dabrowski.Backend.Developer.Assignment.exception.NoteNotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class NoteNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noteNotFoundHandler(NoteNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails customValidationErrorHandling(ConstraintViolationException exc) {
        ErrorDetails errorDetails = new ErrorDetails("Validation Error", exc.getLocalizedMessage(), new Date());
        return errorDetails;
    }
}
