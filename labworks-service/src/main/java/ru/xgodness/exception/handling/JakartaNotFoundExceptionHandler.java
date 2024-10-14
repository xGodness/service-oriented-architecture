package ru.xgodness.exception.handling;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import ru.xgodness.exception.dto.ErrorMessages;

@Log
@Provider
public class JakartaNotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException ex) {
        log.info("Caught Jakarta NotFoundException: " + ex.getMessage());
        return Response
                .status(404)
                .header("Content-Type", "application/json;charset=UTF-8")
                .entity(new ErrorMessages("Resource was not found"))
                .build();
    }
}