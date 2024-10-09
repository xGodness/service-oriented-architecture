package exception.handling;

import exception.UnexpectedInputFormatException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;

import java.util.logging.Level;

@Log
@Provider
public class UnexpectedInputFormatExceptionHandler implements ExceptionMapper<UnexpectedInputFormatException> {
    @Override
    public Response toResponse(UnexpectedInputFormatException ex) {
        log.log(Level.INFO, "Caught UnexpectedInputFormatException: " + ex.getErrorMessagesDTO().getMessages());
        return Response
                .status(400)
                .entity(ex.getErrorMessagesDTO())
                .build();
    }
}