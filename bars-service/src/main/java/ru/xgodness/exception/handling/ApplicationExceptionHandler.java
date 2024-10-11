package ru.xgodness.exception.handling;

import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.xgodness.exception.dto.ErrorMessages;

@Log
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<Object> handle(HttpClientErrorException ex, WebRequest webRequest) {
        log.info("Caught HttpClientErrorException: " + ex.getMessage());
        return super.handleExceptionInternal(
                ex,
                ex.getResponseBodyAs(ErrorMessages.class),
                new HttpHeaders(),
                ex.getStatusCode(),
                webRequest);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Object> handle(MethodArgumentTypeMismatchException ex, WebRequest webRequest) {
        log.info("Caught MethodArgumentTypeMismatchException: " + ex.getMessage());
        return super.handleExceptionInternal(
                ex,
                new ErrorMessages(ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                webRequest);
    }

    @ExceptionHandler(value = {ResourceAccessException.class})
    protected ResponseEntity<Object> handle(ResourceAccessException ex, WebRequest webRequest) {
        log.info("Caught ResourceAccessException: " + ex.getMessage());
        return super.handleExceptionInternal(ex,
                new ErrorMessages("External service is not available"),
                new HttpHeaders(),
                HttpStatus.SERVICE_UNAVAILABLE,
                webRequest);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handle(RuntimeException ex, WebRequest webRequest) {
        log.info("Caught UNHANDLED RuntimeException: " + ex.getMessage());
        return super.handleExceptionInternal(ex,
                new ErrorMessages("Something went wrong"),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest);
    }
}
