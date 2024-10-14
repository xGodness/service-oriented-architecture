package ru.xgodness.exception.handling;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import ru.xgodness.exception.dto.ErrorMessages;

import java.util.Arrays;
import java.util.stream.Collectors;

@Log
@Provider
public class DefaultExceptionHandler implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException ex) {
        log.warning("Caught UNHANDLED WebApplicationException: " + ex.getMessage());
        log.warning(ex.getMessage());
        log.warning(Arrays.stream(ex.getStackTrace()).map(Object::toString).collect(Collectors.joining("\n")));
        return Response
                .status(500)
                .header("Content-Type", "application/json;charset=UTF-8")
                .entity(new ErrorMessages("Something went wrong"))
                .build();
    }
}