package exception.handling;

import exception.AlreadyExistsException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;

import java.util.logging.Level;

@Log
@Provider
public class AlreadyExistsExceptionHandler implements ExceptionMapper<AlreadyExistsException> {
    @Override
    public Response toResponse(AlreadyExistsException ex) {
        log.log(Level.INFO, "Caught AlreadyExistsException: " + ex.getErrorMessagesDTO().getMessages());
        return Response
                .status(409)
                .entity(ex.getErrorMessagesDTO())
                .build();
    }
}