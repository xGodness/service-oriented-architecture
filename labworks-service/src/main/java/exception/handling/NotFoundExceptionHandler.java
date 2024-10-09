package exception.handling;

import exception.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;

import java.util.logging.Level;

@Log
@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException ex) {
        log.log(Level.INFO, "Caught NotFoundException: " + ex.getErrorMessagesDTO().getMessages());
        return Response
                .status(400)
                .entity(ex.getErrorMessagesDTO())
                .build();
    }
}