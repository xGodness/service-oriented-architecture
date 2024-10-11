package ru.xgodness.exception.handling;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import ru.xgodness.exception.dto.ErrorMessages;

@Log
@Provider
public class ProcessingExceptionHandler implements ExceptionMapper<ProcessingException> {
    @Override
    public Response toResponse(ProcessingException ex) {
        log.info("Caught ProcessingException: " + ex.getCause());
        return Response
                .status(400)
                .entity(new ErrorMessages("Cannot process request body: invalid JSON format or enum value provided"))
                .build();
    }
}