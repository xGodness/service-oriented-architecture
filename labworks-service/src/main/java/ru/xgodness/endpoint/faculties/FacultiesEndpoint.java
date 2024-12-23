package ru.xgodness.endpoint.faculties;

import lombok.extern.java.Log;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.xgodness.ApplicationConfiguration;
import ru.xgodness.endpoint.faculties.model.dto.Faculty;
import ru.xgodness.endpoint.faculties.model.request.AddDisciplineRequest;
import ru.xgodness.endpoint.faculties.model.request.AddFacultyRequest;
import ru.xgodness.endpoint.faculties.model.request.CheckFacultyAndDisciplineExistenceRequest;
import ru.xgodness.endpoint.faculties.model.request.DeleteLabworksByFacultyAndDisciplineRequest;
import ru.xgodness.endpoint.faculties.model.response.*;
import ru.xgodness.exception.NotFoundException;

@Log
@Endpoint
public class FacultiesEndpoint {
    private static final String NAMESPACE_URI = ApplicationConfiguration.NAMESPACE_URI;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteLabworksByFacultyAndDisciplineRequest")
    @ResponsePayload
    public void deleteLabworksByFacultyAndDiscipline(@RequestPayload DeleteLabworksByFacultyAndDisciplineRequest request) {
        FacultyService.deleteLabworksByFacultyAndDiscipline(request.getFaculty(), request.getDisciplineName());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addFacultyRequest")
    @ResponsePayload
    public AddFacultyResponse addFaculty(@RequestPayload AddFacultyRequest request) {
        return new AddFacultyResponse(FacultyService.storeFaculty(new Faculty(request.getFaculty())).getName());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFacultiesRequest")
    @ResponsePayload
    public GetFacultiesResponse getFaculties() {
        return new GetFacultiesResponse(FacultyService.getAllFaculties());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDisciplinesRequest")
    @ResponsePayload
    public GetDisciplinesResponse getDisciplines() {
        return new GetDisciplinesResponse(FacultyService.getAllDisciplines());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addDisciplineRequest")
    @ResponsePayload
    public AddDisciplineResponse addDiscipline(@RequestPayload AddDisciplineRequest request) {
        return new AddDisciplineResponse(FacultyService.storeDiscipline(request.getDiscipline()));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "checkFacultyAndDisciplineExistenceRequest")
    @ResponsePayload
    public CheckFacultyAndDisciplineExistenceResponse checkFacultyAndDisciplineExistence(@RequestPayload CheckFacultyAndDisciplineExistenceRequest request) {
        String faculty = request.getFaculty();
        String disciplineName = request.getDisciplineName();
        if (!FacultyService.disciplineExists(faculty, disciplineName))
            throw new NotFoundException("Discipline %s on faculty %s was not found".formatted(faculty, disciplineName));
        return new CheckFacultyAndDisciplineExistenceResponse();
    }
}
