package resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.extern.java.Log;
import model.dto.LabworkDTO;
import service.LabworkService;

import java.util.logging.Level;

@Log
@Path("/labworks")
public class LabworksResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLabwork(LabworkDTO labworkDTO) {
        var resultDTO = LabworkService.storeLabwork(labworkDTO);
        return Response.ok().entity(resultDTO).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getLabwork(@PathParam("id") long id) {
        var resultDTO = LabworkService.getLabworkById(id);
        return Response.ok().entity(resultDTO).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLabworks(@Context UriInfo uriInfo) {
        var queryParameters = uriInfo.getQueryParameters();
        log.log(Level.INFO, queryParameters.toString());
        var pageDTO = LabworkService.getAllLabworks(queryParameters);
        return Response.ok().entity(pageDTO).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateLabwork(@PathParam("id") long id, LabworkDTO labworkDTO) {
        var resultDTO = LabworkService.updateLabworkById(id, labworkDTO);
        return Response.ok().entity(resultDTO).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteLabwork(@PathParam("id") long id) {
        LabworkService.deleteLabwork(id);
        return Response.status(204).build();
    }
}
