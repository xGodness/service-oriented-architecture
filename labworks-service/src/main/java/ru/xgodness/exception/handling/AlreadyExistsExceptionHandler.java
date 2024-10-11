package ru.xgodness.exception.handling;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import ru.xgodness.exception.AlreadyExistsException;

@Log
@Provider
public class AlreadyExistsExceptionHandler implements ExceptionMapper<AlreadyExistsException> {
    @Override
    public Response toResponse(AlreadyExistsException ex) {
        log.info("Caught AlreadyExistsException: " + ex.getErrorMessages().getMessages());
        return Response
                .status(409)
                .entity(ex.getErrorMessages())
                .build();
    }
}