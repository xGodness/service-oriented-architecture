package ru.xgodness.exception.handling;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import ru.xgodness.exception.*;

import javax.xml.namespace.QName;
import java.util.stream.Collectors;

@Log
public class SoapFaultHandler extends SoapFaultMappingExceptionResolver {
    private static final QName STATUS_CODE = new QName("statusCode");
    private static final QName DESCRIPTION = new QName("description");

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
        log.info("Got SOAP exception: %s".formatted(ex.getMessage()));
        if (ex instanceof ApplicationException appEx) {
            SoapFaultDetail detail = fault.addFaultDetail();
            detail.addFaultDetailElement(STATUS_CODE).addText(String.valueOf(getStatusCode(appEx).value()));
            detail.addFaultDetailElement(DESCRIPTION).addText(
                    "[%s]".formatted(
                            appEx.getErrorMessages().getMessages()
                                    .stream()
                                    .map("\"%s\""::formatted)
                                    .collect(Collectors.joining(",")))
            );
        }
    }

    private HttpStatus getStatusCode(Exception ex) {
        if (ex instanceof AlreadyExistsException) return HttpStatus.CONFLICT;
        if (ex instanceof NotFoundException) return HttpStatus.NOT_FOUND;
        if (ex instanceof UnexpectedInputFormatException) return HttpStatus.BAD_REQUEST;
        if (ex instanceof ValidationException) return HttpStatus.UNPROCESSABLE_ENTITY;

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
