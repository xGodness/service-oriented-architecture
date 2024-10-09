package endpoint.minimalpoints;

import endpoint.minimalpoints.model.dto.MinimalPointsSumDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;

@Log
@Path("/minimal-points")
public class MinimalPointsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sum")
    public Response sumMinimalPoints() {
        var sum = MinimalPointService.sumMinimalPoints();
        return Response.ok().entity(new MinimalPointsSumDTO(sum)).build();
    }
}
