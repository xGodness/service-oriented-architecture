package exception.handling;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;

import java.util.logging.Level;

@Log
@Provider
public class JakartaNotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException ex) {
        log.log(Level.INFO, "Caught Jakarta NotFoundException: " + ex.getMessage());
        return Response
                .status(404)
                .entity("Resource was not found")
                .build();
    }
}