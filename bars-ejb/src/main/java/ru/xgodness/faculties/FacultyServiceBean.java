package ru.xgodness.faculties;

import jakarta.ejb.Stateless;
import org.jboss.ejb3.annotation.Pool;
import ru.xgodness.endpoint.labworks.model.dto.LabworkPage;
import ru.xgodness.http.ExternalApiCaller;
import ru.xgodness.http.LabworkRequestBody;

@Stateless(name = "FacultyService")
@Pool("bars-ejb-slsb-pool")
public class FacultyServiceBean implements FacultyService {

    @Override
    public void checkFacultyAndDisciplineExistence(String faculty, String disciplineName) {
        ExternalApiCaller.checkFacultyAndDisciplineExistence(faculty, disciplineName);
    }

    @Override
    public void makeHardcore(String faculty, String disciplineName) {
        LabworkPage labworkPage = ExternalApiCaller.getTenMostDifficultLabworks(faculty, disciplineName);
        labworkPage.getElements().stream()
                .map(labwork -> LabworkRequestBody.mapLabworkAndChangeDiscipline(labwork, faculty, disciplineName))
                .forEach(ExternalApiCaller::postLabwork);
    }
}
