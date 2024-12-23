package ru.xgodness.exception.handling;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.client.SoapFaultClientException;
import ru.xgodness.exception.dto.ErrorMessages;

import java.util.Arrays;
import java.util.stream.Collectors;

@Log
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handle(RuntimeException ex, WebRequest webRequest) {
        log.warning("Caught UNHANDLED RuntimeException: [%s] %s".formatted(ex.getClass(), ex.getMessage()));
        log.warning(ex.getMessage());
        log.warning(Arrays.stream(ex.getStackTrace()).map(Object::toString).collect(Collectors.joining("\n")));
        return super.handleExceptionInternal(ex,
                new ErrorMessages("Something went wrong"),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest);
    }

    @ExceptionHandler(value = {SoapFaultClientException.class})
    protected ResponseEntity<Object> handle(SoapFaultClientException ex, WebRequest webRequest) throws JAXBException {
        log.info("Caught SoapFaultClientException: [%s] %s".formatted(ex.getClass(), ex.getMessage()));

        SoapFault fault = ex.getSoapFault();
        if (fault != null && fault.getFaultDetail() != null) {
            Unmarshaller unmarshaller = JAXBContext.newInstance(FaultDetails.class).createUnmarshaller();
            FaultDetails faultDetails = unmarshaller.unmarshal(fault.getFaultDetail().getSource(), FaultDetails.class).getValue();
            return super.handleExceptionInternal(ex,
                    faultDetails.descriptionToErrorMessages(),
                    new HttpHeaders(),
                    HttpStatus.valueOf(faultDetails.getStatusCode()),
                    webRequest);
        }

        return handle(new RuntimeException(ex), webRequest);
    }

    @ExceptionHandler(value = {WebServiceIOException.class})
    protected ResponseEntity<Object> handle(WebServiceIOException ex, WebRequest webRequest) {
        log.info("Caught WebServiceIOException: " + ex.getMessage());
        return super.handleExceptionInternal(ex,
                new ErrorMessages("External service is not available"),
                new HttpHeaders(),
                HttpStatus.SERVICE_UNAVAILABLE,
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
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class FaultDetails {
    private String description;
    private int statusCode;

    public ErrorMessages descriptionToErrorMessages() {
        return new ErrorMessages(
                Arrays.stream(description.replace("[", "")
                                .replace("]", "")
                                .split("\", "))
                        .map(msg -> msg.replace("\"", ""))
                        .toList());
    }
}