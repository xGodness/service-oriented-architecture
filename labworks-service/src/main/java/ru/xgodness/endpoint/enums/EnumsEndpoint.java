package ru.xgodness.endpoint.enums;

import lombok.extern.java.Log;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.xgodness.ApplicationConfiguration;
import ru.xgodness.endpoint.enums.model.request.GetEnumRequest;
import ru.xgodness.endpoint.enums.model.response.GetEnumResponse;

@Log
@Endpoint
public class EnumsEndpoint {
    private static final String NAMESPACE_URI = ApplicationConfiguration.NAMESPACE_URI;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEnumRequest")
    @ResponsePayload
    public GetEnumResponse getEnum(@RequestPayload GetEnumRequest request) {
        var result = EnumService.getEnumRepresentation(request.getEnumName().toLowerCase());
        return new GetEnumResponse(result);
    }
}
