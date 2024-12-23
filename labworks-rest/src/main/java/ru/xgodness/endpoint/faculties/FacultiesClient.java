package ru.xgodness.endpoint.faculties;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.xgodness.ApplicationConfiguration;
import ru.xgodness.endpoint.faculties.model.dto.Discipline;
import ru.xgodness.endpoint.faculties.model.dto.DisciplinesList;
import ru.xgodness.endpoint.faculties.model.dto.FacultiesList;
import ru.xgodness.endpoint.faculties.model.dto.Faculty;
import ru.xgodness.endpoint.faculties.model.request.*;
import ru.xgodness.endpoint.faculties.model.response.AddDisciplineResponse;
import ru.xgodness.endpoint.faculties.model.response.AddFacultyResponse;
import ru.xgodness.endpoint.faculties.model.response.GetDisciplinesResponse;
import ru.xgodness.endpoint.faculties.model.response.GetFacultiesResponse;

@Component
public class FacultiesClient extends WebServiceGatewaySupport {
    private static final String WS_URI = ApplicationConfiguration.WS_URI;

    public void deleteLabworksByFacultyAndDiscipline(String faculty, String disciplineName) {
        DeleteLabworksByFacultyAndDisciplineRequest request = new DeleteLabworksByFacultyAndDisciplineRequest(faculty, disciplineName);
        getWebServiceTemplate().marshalSendAndReceive(WS_URI, request);
    }

    public Faculty storeFaculty(Faculty faculty) {
        AddFacultyRequest request = new AddFacultyRequest(faculty.getName());
        return new Faculty(((AddFacultyResponse) getWebServiceTemplate().marshalSendAndReceive(WS_URI, request)).getFaculty());
    }

    public FacultiesList getAllFaculties() {
        return new FacultiesList(((GetFacultiesResponse) getWebServiceTemplate().marshalSendAndReceive(WS_URI, new GetFacultiesRequest())).getFacultyList());
    }

    public DisciplinesList getAllDisciplines() {
        return new DisciplinesList(((GetDisciplinesResponse) getWebServiceTemplate().marshalSendAndReceive(WS_URI, new GetDisciplinesRequest())).getDisciplineList());
    }

    public Discipline storeDiscipline(Discipline discipline) {
        AddDisciplineRequest request = new AddDisciplineRequest(discipline);
        return ((AddDisciplineResponse) getWebServiceTemplate().marshalSendAndReceive(WS_URI, request)).getDiscipline();
    }

    public void disciplineExists(String faculty, String disciplineName) {
        CheckFacultyAndDisciplineExistenceRequest request = new CheckFacultyAndDisciplineExistenceRequest(faculty, disciplineName);
        getWebServiceTemplate().marshalSendAndReceive(WS_URI, request);
    }
}

