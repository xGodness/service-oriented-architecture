package ru.xgodness.endpoint.minimalpoints;

import lombok.extern.java.Log;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.xgodness.ApplicationConfiguration;
import ru.xgodness.endpoint.minimalpoints.model.response.SumMinimalPointsResponse;

@Log
@Endpoint
public class MinimalPointsEndpoint {
    private static final String NAMESPACE_URI = ApplicationConfiguration.NAMESPACE_URI;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sumMinimalPointsRequest")
    @ResponsePayload
    public SumMinimalPointsResponse sumMinimalPoints() {
        return new SumMinimalPointsResponse(MinimalPointService.sumMinimalPoints());
    }
}
