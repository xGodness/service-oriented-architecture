package ru.xgodness.endpoint.minimalpoints;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.xgodness.ApplicationConfiguration;
import ru.xgodness.endpoint.minimalpoints.model.request.SumMinimalPointsRequest;
import ru.xgodness.endpoint.minimalpoints.model.response.SumMinimalPointsResponse;

@Component
public class MinimalPointsClient extends WebServiceGatewaySupport {
    private static final String WS_URI = ApplicationConfiguration.WS_URI;

    public Double sumMinimalPoints() {
        SumMinimalPointsResponse response = ((SumMinimalPointsResponse) getWebServiceTemplate().marshalSendAndReceive(WS_URI, new SumMinimalPointsRequest()));
        return response.getValue();
    }
}

