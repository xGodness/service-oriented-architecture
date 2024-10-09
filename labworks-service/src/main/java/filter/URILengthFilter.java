package filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import model.dto.ErrorMessagesDTO;

import java.util.logging.Level;

@Log
@Provider
@PreMatching
public class URILengthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        if (requestContext.getUriInfo().getRequestUri().toString().length() > 1024) {
            log.log(Level.INFO, "Too long URI in request: {}", requestContext.getUriInfo().getRequestUri());
            requestContext.abortWith(
                    Response
                            .status(414)
                            .type(MediaType.APPLICATION_JSON)
                            .entity(new ErrorMessagesDTO("Request URI is too long"))
                            .build()
            );
        }
    }
}
