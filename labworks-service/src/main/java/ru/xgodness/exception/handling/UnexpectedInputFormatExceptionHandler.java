package ru.xgodness.exception.handling;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import ru.xgodness.exception.UnexpectedInputFormatException;

@Log
@Provider
public class UnexpectedInputFormatExceptionHandler implements ExceptionMapper<UnexpectedInputFormatException> {
    @Override
    public Response toResponse(UnexpectedInputFormatException ex) {
        log.info("Caught UnexpectedInputFormatException: " + ex.getErrorMessages().getMessages());
        return Response
                .status(400)
                .entity(ex.getErrorMessages())
                .build();
    }
}