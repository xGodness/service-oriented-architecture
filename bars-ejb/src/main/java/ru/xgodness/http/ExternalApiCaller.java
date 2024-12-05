package ru.xgodness.http;

import lombok.extern.java.Log;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ru.xgodness.endpoint.labworks.model.dto.Labwork;
import ru.xgodness.endpoint.labworks.model.dto.LabworkPage;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Log
public class ExternalApiCaller {
    private static final RestTemplate restTemplate;
    private static final String LABWORKS_SERVICE_BASE_URL = "https://balancer:4444/labworks-service/api/v1";

    static {
        SSLConfigurationProvider provider = new SSLConfigurationProvider();
        try {
            SSLContext sslContext = provider.getSSLContext();
            PoolingHttpClientConnectionManager connectionManager = provider.getConnectionManager(sslContext);
            CloseableHttpClient httpClient = provider.createHttpClient(connectionManager);
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            restTemplate = new RestTemplate(requestFactory);
        } catch (CertificateException
                 | KeyStoreException
                 | IOException
                 | NoSuchAlgorithmException
                 | KeyManagementException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Labwork getLabworkById(long id) {
        return restTemplate.getForEntity(LABWORKS_SERVICE_BASE_URL + "/labworks/%d".formatted(id), Labwork.class).getBody();
    }

    public static Labwork updateLabworkById(long id, LabworkRequestBody requestBody) {
        HttpEntity<LabworkRequestBody> requestEntity = new HttpEntity<>(requestBody);
        return restTemplate.exchange(
                LABWORKS_SERVICE_BASE_URL + "/labworks/%d".formatted(id),
                HttpMethod.PUT,
                requestEntity,
                Labwork.class
        ).getBody();
    }

    public static void postLabwork(LabworkRequestBody requestBody) {
        HttpEntity<LabworkRequestBody> requestEntity = new HttpEntity<>(requestBody);
        restTemplate.exchange(LABWORKS_SERVICE_BASE_URL + "/labworks", HttpMethod.POST, requestEntity, Object.class);
    }

    public static void checkFacultyAndDisciplineExistence(String faculty, String disciplineName) {
        restTemplate.getForObject(LABWORKS_SERVICE_BASE_URL + "/faculties/check/%s/%s".formatted(faculty, disciplineName), Object.class);
    }

    public static LabworkPage getTenMostDifficultLabworks(String excludeFaculty, String excludeDiscipline) {
        String url = LABWORKS_SERVICE_BASE_URL + "/labworks?filter=faculty[neq]=%s&filter=discipline_name[neq]=%s&limit=10".formatted(excludeFaculty, excludeDiscipline);
        log.info("[getTenMostDifficultLabworks] Sending request: " + url);
        return restTemplate.getForEntity(url, LabworkPage.class).getBody();
    }
}
