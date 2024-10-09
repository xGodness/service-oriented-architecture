package exception.handling;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import exception.model.dto.ErrorMessagesDTO;

import java.util.logging.Level;

@Log
@Provider
public class ProcessingExceptionHandler implements ExceptionMapper<ProcessingException> {
    @Override
    public Response toResponse(ProcessingException ex) {
        log.log(Level.INFO, "Caught ProcessingException: " + ex.getCause());
        return Response
                .status(400)
                .entity(new ErrorMessagesDTO("Cannot process request body: invalid JSON format or enum value provided"))
                .build();
    }
}