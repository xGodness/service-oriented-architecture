package ru.xgodness.http;

import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ru.xgodness.endpoint.labworks.dto.Labwork;
import ru.xgodness.endpoint.labworks.dto.LabworkPage;

@Log
public class ExternalApiCaller {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String labworksServiceUrl = "https://localhost:9172/labworks-service/api/v1";

    public static Labwork getLabworkById(long id) {
        return restTemplate.getForEntity(labworksServiceUrl + "/labworks/%d".formatted(id), Labwork.class).getBody();
    }

    public static Labwork updateLabworkById(long id, LabworkRequestBody requestBody) {
        HttpEntity<LabworkRequestBody> requestEntity = new HttpEntity<>(requestBody);
        return restTemplate.exchange(
                labworksServiceUrl + "/labworks/%d".formatted(id),
                HttpMethod.PUT,
                requestEntity,
                Labwork.class
        ).getBody();
    }

    public static void postLabwork(LabworkRequestBody requestBody) {
        HttpEntity<LabworkRequestBody> requestEntity = new HttpEntity<>(requestBody);
        restTemplate.exchange(labworksServiceUrl + "/labworks", HttpMethod.POST, requestEntity, Object.class);
    }

    public static void checkFacultyAndDisciplineExistence(String faculty, String disciplineName) {
        restTemplate.getForObject(labworksServiceUrl + "/faculties/check/%s/%s".formatted(faculty, disciplineName), Object.class);
    }

    public static LabworkPage getTenMostDifficultLabworks(String excludeFaculty, String excludeDiscipline) {
        String url = labworksServiceUrl + "/labworks?filter=faculty[neq]=%s&filter=discipline_name[neq]=%s&limit=10".formatted(excludeFaculty, excludeDiscipline);
        log.info("[getTenMostDifficultLabworks] Sending request: " + url);
        return restTemplate.getForEntity(url, LabworkPage.class).getBody();
    }
}
