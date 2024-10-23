package ru.xgodness.exception.handling;

import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import ru.xgodness.exception.dto.ErrorMessages;

@Log
@Provider
public class JakartaNotAllowedExceptionHandler implements ExceptionMapper<NotAllowedException> {
    @Override
    public Response toResponse(NotAllowedException ex) {
        log.warning("Caught NotAllowedException: " + ex.getMessage());
        return Response
                .status(405)
                .header("Content-Type", "application/json;charset=UTF-8")
                .entity(new ErrorMessages("Method not allowed"))
                .build();
    }
}