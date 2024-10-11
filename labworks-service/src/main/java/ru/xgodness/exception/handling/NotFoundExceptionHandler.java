package ru.xgodness.exception.handling;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import ru.xgodness.exception.NotFoundException;

@Log
@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException ex) {
        log.info("Caught NotFoundException: " + ex.getErrorMessages().getMessages());
        return Response
                .status(404)
                .entity(ex.getErrorMessages())
                .build();
    }
}