package exception.handling;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import model.dto.ErrorMessagesDTO;

import java.util.logging.Level;

@Log
@Provider
public class DefaultExceptionHandler implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException ex) {
        log.log(Level.WARNING, "Caught UNHANDLED WebApplicationException: " + ex.getMessage());
        return Response
                .status(500)
                .entity(new ErrorMessagesDTO("Something went wrong"))
                .build();
    }
}