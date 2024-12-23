package ru.xgodness.endpoint.enums;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.xgodness.ApplicationConfiguration;
import ru.xgodness.endpoint.enums.model.dto.EnumRepresentation;
import ru.xgodness.endpoint.enums.model.request.GetEnumRequest;
import ru.xgodness.endpoint.enums.model.response.GetEnumResponse;

import java.util.List;

@Component
public class EnumsClient extends WebServiceGatewaySupport {
    private static final String WS_URI = ApplicationConfiguration.WS_URI;

    public List<EnumRepresentation> getEnumRepresentation(String enumName) {
        GetEnumRequest request = new GetEnumRequest(enumName);

        GetEnumResponse response = (GetEnumResponse) getWebServiceTemplate()
                .marshalSendAndReceive(WS_URI, request);

        return response.getEnumRepresentationsList();
    }
}
