package exception.handling;

import exception.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;

import java.util.logging.Level;

@Log
@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException> {
    @Override
    public Response toResponse(ValidationException ex) {
        log.log(Level.INFO, "Caught ValidationException with errors: " + ex.getErrorMessagesDTO().getMessages());
        return Response
                .status(422)
                .entity(ex.getErrorMessagesDTO())
                .build();
    }
}