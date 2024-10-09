package endpoint.enums;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;

@Log
@Path("/enums")
public class EnumsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{enum-name}")
    public Response getEnum(@PathParam("enum-name") String enumName) {
        var result = EnumService.getEnumRepresentation(enumName);
        return Response.ok().entity(result).build();
    }
}
