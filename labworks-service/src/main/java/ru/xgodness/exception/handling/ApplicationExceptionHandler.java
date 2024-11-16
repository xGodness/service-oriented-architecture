package ru.xgodness.exception.handling;

import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import ru.xgodness.exception.AlreadyExistsException;
import ru.xgodness.exception.NotFoundException;
import ru.xgodness.exception.UnexpectedInputFormatException;
import ru.xgodness.exception.ValidationException;
import ru.xgodness.exception.dto.ErrorMessages;

import java.util.Arrays;
import java.util.stream.Collectors;

@Log
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handle(RuntimeException ex, WebRequest webRequest) {
        log.warning("Caught UNHANDLED WebApplicationException: [%s] %s".formatted(ex.getClass(), ex.getMessage()));
        log.warning(ex.getMessage());
        log.warning(Arrays.stream(ex.getStackTrace()).map(Object::toString).collect(Collectors.joining("\n")));
        return super.handleExceptionInternal(ex,
                new ErrorMessages("Something went wrong"),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest);
    }

    @ExceptionHandler(value = {AlreadyExistsException.class})
    protected ResponseEntity<Object> handle(AlreadyExistsException ex, WebRequest webRequest) {
        log.info("Caught AlreadyExistsException: " + ex.getErrorMessages().getMessages());
        return super.handleExceptionInternal(ex,
                ex.getErrorMessages(),
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                webRequest);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handle(NotFoundException ex, WebRequest webRequest) {
        log.info("Caught NotFoundException: " + ex.getErrorMessages().getMessages());
        return super.handleExceptionInternal(ex,
                ex.getErrorMessages(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {
        log.info("Caught HttpMessageNotReadableException: " + ex.getMessage());
        return super.handleExceptionInternal(ex,
                new ErrorMessages("Cannot process request body: invalid JSON format or enum value provided"),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {
        log.info("Caught NoResourceFoundException: " + ex.getMessage());
        return super.handleExceptionInternal(ex,
                new ErrorMessages("Resource not found"),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {
        log.info("Caught HttpRequestMethodNotSupportedException: " + ex.getMessage());
        return super.handleExceptionInternal(ex,
                new ErrorMessages("Method not allowed"),
                new HttpHeaders(),
                HttpStatus.METHOD_NOT_ALLOWED,
                webRequest);
    }

    @ExceptionHandler(value = {UnexpectedInputFormatException.class})
    protected ResponseEntity<Object> handle(UnexpectedInputFormatException ex, WebRequest webRequest) {
        log.info("Caught UnexpectedInputFormatException: " + ex.getErrorMessages().getMessages());
        return super.handleExceptionInternal(ex,
                ex.getErrorMessages(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                webRequest);
    }

    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<Object> handle(ValidationException ex, WebRequest webRequest) {
        log.info("Caught ValidationException: " + ex.getErrorMessages().getMessages());
        return super.handleExceptionInternal(ex,
                ex.getErrorMessages(),
                new HttpHeaders(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                webRequest);
    }
}
