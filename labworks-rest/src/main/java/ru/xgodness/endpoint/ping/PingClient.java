package ru.xgodness.endpoint.ping;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.xgodness.ApplicationConfiguration;
import ru.xgodness.endpoint.ping.model.request.PingRequest;

@Component
public class PingClient extends WebServiceGatewaySupport {
    private static final String WS_URI = ApplicationConfiguration.WS_URI;

    public void ping() {
        getWebServiceTemplate().marshalSendAndReceive(WS_URI, new PingRequest());
    }
}

