package ru.xgodness.endpoint.labworks;

import lombok.extern.java.Log;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.xgodness.ApplicationConfiguration;
import ru.xgodness.endpoint.labworks.model.request.*;
import ru.xgodness.endpoint.labworks.model.response.AddLabworkResponse;
import ru.xgodness.endpoint.labworks.model.response.GetAllLabworksResponse;
import ru.xgodness.endpoint.labworks.model.response.GetLabworkByIdResponse;
import ru.xgodness.endpoint.labworks.model.response.UpdateLabworkByIdResponse;

@Log
@Endpoint
public class LabworksEndpoint {
    private static final String NAMESPACE_URI = ApplicationConfiguration.NAMESPACE_URI;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addLabworkRequest")
    @ResponsePayload
    public AddLabworkResponse addLabwork(@RequestPayload AddLabworkRequest request) {
        log.info(request.toString());
        return new AddLabworkResponse(LabworkService.storeLabwork(request.getLabwork()));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLabworkByIdRequest")
    @ResponsePayload
    public GetLabworkByIdResponse getLabwork(@RequestPayload GetLabworkByIdRequest request) {
        return new GetLabworkByIdResponse(LabworkService.getLabworkById(request.getId()));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllLabworksRequest")
    @ResponsePayload
    public GetAllLabworksResponse getAllLabworks(@RequestPayload GetAllLabworksRequest request) {
        MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        if (request.getQueryParamList() != null)
            request.getQueryParamList().forEach(entry -> queryParameters.add(entry.getParam(), entry.getValue()));
        log.info(queryParameters.toString());

        return new GetAllLabworksResponse(LabworkService.getAllLabworks(queryParameters));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateLabworkByIdRequest")
    @ResponsePayload
    public UpdateLabworkByIdResponse updateLabwork(@RequestPayload UpdateLabworkByIdRequest request) {
        return new UpdateLabworkByIdResponse(LabworkService.updateLabworkById(request.getId(), request.getLabwork()));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteLabworkByIdRequest")
    @ResponsePayload
    public void deleteLabwork(@RequestPayload DeleteLabworkByIdRequest request) {
        LabworkService.deleteLabwork(request.getId());
    }
}
