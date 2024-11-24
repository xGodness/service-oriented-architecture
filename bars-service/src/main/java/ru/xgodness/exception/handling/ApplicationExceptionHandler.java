package ru.xgodness.exception.handling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ejb.EJBException;
import lombok.extern.java.Log;
import org.jboss.ejb.client.RequestSendFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.xgodness.exception.ValidationException;
import ru.xgodness.exception.dto.ErrorMessages;

@Log
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /* When exception is being thrown in the `bars-ejb` context, it's being wrapped in RuntimeException and returned to
     * the `bars-service` as is.
     * Thus, to handle any possible exception from the `bars-ejb` context we need to "unwrap" received RuntimeException. */
    @ExceptionHandler(value = {EJBException.class})
    protected ResponseEntity<Object> handleEJBException(EJBException ex, WebRequest webRequest) {
        log.info("Caught EJBException: " + ex.getMessage());
        if (ex.getCause() instanceof HttpClientErrorException cause)
            return handleHttpClientErrorException(cause, webRequest);
        if (ex.getCause() instanceof ValidationException cause)
            return handleValidationException(cause, webRequest);
        if (ex.getCause() instanceof ResourceAccessException cause)
            return handleResourceAccessException(cause, webRequest);
        if (ex.getCause() instanceof RequestSendFailedException cause)
            return handleRequestSendFailedException(cause, webRequest);
        if (ex.getCause() instanceof HttpServerErrorException.ServiceUnavailable cause)
            return handleHttpServerErrorExceptionServiceUnavailable(cause, webRequest);
        return handleRuntimeException(new RuntimeException(ex), webRequest);
    }

    @ExceptionHandler(value = {HttpServerErrorException.ServiceUnavailable.class})
    protected ResponseEntity<Object> handleHttpServerErrorExceptionServiceUnavailable(HttpServerErrorException.ServiceUnavailable ex, WebRequest webRequest) {
        log.info("Caught HttpServerErrorException.ServiceUnavailable: " + ex.getMessage());

        return super.handleExceptionInternal(
                ex,
                new ErrorMessages("External service is not available"),
                new HttpHeaders(),
                HttpStatus.SERVICE_UNAVAILABLE,
                webRequest);
    }

    @ExceptionHandler(value = {RequestSendFailedException.class})
    protected ResponseEntity<Object> handleRequestSendFailedException(RequestSendFailedException ex, WebRequest webRequest) {
        log.info("Caught RequestSendFailedException: " + ex.getMessage());

        return super.handleExceptionInternal(
                ex,
                new ErrorMessages("External service is not available"),
                new HttpHeaders(),
                HttpStatus.SERVICE_UNAVAILABLE,
                webRequest);
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest webRequest) {
        log.info("Caught HttpClientErrorException: " + ex.getMessage());

        ErrorMessages errorMessages;
        try {
            errorMessages = objectMapper.readValue(ex.getResponseBodyAsString(), ErrorMessages.class);
        } catch (JsonProcessingException jsonEx) {
            return handleRuntimeException(new RuntimeException(jsonEx), webRequest);
        }

        return super.handleExceptionInternal(
                ex,
                errorMessages,
                new HttpHeaders(),
                ex.getStatusCode(),
                webRequest);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest webRequest) {
        log.info("Caught MethodArgumentTypeMismatchException: " + ex.getMessage());
        return super.handleExceptionInternal(
                ex,
                new ErrorMessages("Required path parameter is invalid (e.g. too large for numeric types)"),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                webRequest);
    }

    @ExceptionHandler(value = {ResourceAccessException.class})
    protected ResponseEntity<Object> handleResourceAccessException(ResourceAccessException ex, WebRequest webRequest) {
        log.info("Caught ResourceAccessException: " + ex.getMessage());
        return super.handleExceptionInternal(ex,
                new ErrorMessages("External service is not available"),
                new HttpHeaders(),
                HttpStatus.SERVICE_UNAVAILABLE,
                webRequest);
    }

    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest webRequest) {
        log.info("Caught ValidationException: " + ex.getErrorMessages().getMessages());
        return super.handleExceptionInternal(ex,
                ex.getErrorMessages(),
                new HttpHeaders(),
                HttpStatus.UNPROCESSABLE_ENTITY,
                webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {
        log.info("Caught NoHandlerFoundException: " + ex.getMessage());
        return super.handleExceptionInternal(ex,
                new ErrorMessages("Resource not found"),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                webRequest);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest webRequest) {
        log.info("Caught UNHANDLED RuntimeException: " + ex.getMessage());
        return super.handleExceptionInternal(ex,
                new ErrorMessages("Something went wrong"),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest);
    }
}
