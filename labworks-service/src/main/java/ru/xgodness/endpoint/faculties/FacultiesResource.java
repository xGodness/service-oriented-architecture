package ru.xgodness.endpoint.faculties;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;
import ru.xgodness.endpoint.faculties.dto.Discipline;
import ru.xgodness.endpoint.faculties.dto.Faculty;
import ru.xgodness.exception.NotFoundException;

@Log
@Path("/faculties")
public class FacultiesResource {

    @DELETE
    @Path("{faculty}/{discipline-name}/labworks")
    public Response deleteLabworksByFacultyAndDiscipline(@PathParam("faculty") String faculty,
                                                         @PathParam("discipline-name") String dicsiplineName) {
        FacultyService.deleteLabworksByFacultyAndDiscipline(faculty, dicsiplineName);
        return Response.status(204).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postFaculty(Faculty faculty) {
        var result = FacultyService.storeFaculty(faculty);
        return Response.ok().entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFaculties() {
        var facultiesList = FacultyService.getAllFaculties();
        return Response.ok().entity(facultiesList).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/disciplines")
    public Response getDisciplines() {
        var disciplineList = FacultyService.getAllDisciplines();
        return Response.ok().entity(disciplineList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/disciplines")
    public Response postDiscipline(Discipline discipline) {
        var result = FacultyService.storeDiscipline(discipline);
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("/check/{faculty}/{discipline-name}")
    public Response checkFacultyAndDiscipline(@PathParam("faculty") String faculty,
                                              @PathParam("discipline-name") String disciplineName) {
        if (FacultyService.disciplineExists(faculty, disciplineName))
            return Response.status(204).build();
        throw new NotFoundException("Discipline %s on faculty %s was not found".formatted(faculty, disciplineName));
    }
}
