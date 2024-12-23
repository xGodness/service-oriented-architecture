package ru.xgodness.endpoint.ping;

import lombok.extern.java.Log;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.xgodness.ApplicationConfiguration;

@Log
@Endpoint
public class PingEndpoint {
    private static final String NAMESPACE_URI = ApplicationConfiguration.NAMESPACE_URI;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "pingRequest")
    @ResponsePayload
    public void ping() {
    }
}
