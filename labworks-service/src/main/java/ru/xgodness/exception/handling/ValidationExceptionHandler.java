package ru.xgodness.exception.handling;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import ru.xgodness.exception.ValidationException;

@Log
@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException> {
    @Override
    public Response toResponse(ValidationException ex) {
        log.info("Caught ValidationException with errors: " + ex.getErrorMessages().getMessages());
        return Response
                .status(422)
                .entity(ex.getErrorMessages())
                .build();
    }
}