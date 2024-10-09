package endpoint.faculties;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;
import endpoint.faculties.model.dto.DisciplineDTO;
import model.dto.FacultyDTO;

@Log
@Path("/faculties")
public class FacultiesResource {

    @DELETE
    @Path("{faculty}/{discipline-name}/labworks")
    public Response deleteLabworksByFacultyAndDiscipline(@PathParam("faculty") String faculty, @PathParam("discipline-name") String dicsiplineName) {
        FacultyService.deleteLabworksByFacultyAndDiscipline(faculty, dicsiplineName);
        return Response.status(204).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postFaculty(FacultyDTO faculty) {
        var facultyDTO = FacultyService.storeFaculty(faculty);
        return Response.ok().entity(facultyDTO).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("disciplines")
    public Response postDiscipline(DisciplineDTO disciplineDTO) {
        var resultDTO = FacultyService.storeDiscipline(disciplineDTO);
        return Response.ok().entity(resultDTO).build();
    }
}
