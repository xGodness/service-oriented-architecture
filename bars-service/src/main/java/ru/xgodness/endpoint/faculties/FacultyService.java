package ru.xgodness.endpoint.faculties;

import ru.xgodness.endpoint.labworks.dto.LabworkPage;
import ru.xgodness.http.ExternalApiCaller;
import ru.xgodness.http.LabworkRequestBody;

public class FacultyService {
    public static void checkFacultyAndDisciplineExistence(String faculty, String disciplineName) {
        ExternalApiCaller.checkFacultyAndDisciplineExistence(faculty, disciplineName);
    }

    public static void makeHardcore(String faculty, String disciplineName) {
        LabworkPage labworkPage = ExternalApiCaller.getTenMostDifficultLabworks(faculty, disciplineName);
        labworkPage.getElements().stream()
                .map(labwork -> LabworkRequestBody.mapLabworkAndChangeDiscipline(labwork, faculty, disciplineName))
                .forEach(ExternalApiCaller::postLabwork);
    }
}
