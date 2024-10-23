package ru.xgodness.exception.handling;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import org.glassfish.jersey.server.ParamException;
import ru.xgodness.exception.dto.ErrorMessages;

@Log
@Provider
public class JerseyPathParamExceptionHandler implements ExceptionMapper<ParamException> {
    @Override
    public Response toResponse(ParamException ex) {
        log.warning("Caught ParamException: " + ex.getMessage());
        return Response
                .status(400)
                .entity(new ErrorMessages("Required path parameter is invalid (e.g. value too large for numeric types)"))
                .build();
    }
}