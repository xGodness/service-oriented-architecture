package ru.xgodness.endpoint.labworks;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.xgodness.ApplicationConfiguration;
import ru.xgodness.endpoint.labworks.model.dto.Labwork;
import ru.xgodness.endpoint.labworks.model.dto.LabworkPage;
import ru.xgodness.endpoint.labworks.model.request.*;
import ru.xgodness.endpoint.labworks.model.response.AddLabworkResponse;
import ru.xgodness.endpoint.labworks.model.response.GetAllLabworksResponse;
import ru.xgodness.endpoint.labworks.model.response.GetLabworkByIdResponse;
import ru.xgodness.endpoint.labworks.model.response.UpdateLabworkByIdResponse;

import java.util.ArrayList;
import java.util.List;

@Component
public class LabworksClient extends WebServiceGatewaySupport {
    private static final String WS_URI = ApplicationConfiguration.WS_URI;

    public Labwork storeLabwork(Labwork labwork) {
        AddLabworkRequest request = new AddLabworkRequest(labwork);
        return ((AddLabworkResponse) getWebServiceTemplate().marshalSendAndReceive(WS_URI, request)).getLabwork();
    }

    public Labwork getLabworkById(long id) {
        GetLabworkByIdRequest request = new GetLabworkByIdRequest(id);
        return ((GetLabworkByIdResponse) getWebServiceTemplate().marshalSendAndReceive(WS_URI, request)).getLabwork();
    }

    public LabworkPage getAllLabworks(MultiValueMap<String, String> queryParametersMap) {
        List<QueryParam> queryParamList = new ArrayList<>();
        queryParametersMap.forEach(
                (param, valueList) -> valueList.forEach(
                        value -> queryParamList.add(new QueryParam(param, value))));
        GetAllLabworksRequest request = new GetAllLabworksRequest(queryParamList);
        return ((GetAllLabworksResponse) getWebServiceTemplate().marshalSendAndReceive(WS_URI, request)).getLabworkPage();
    }

    public Labwork updateLabworkById(long id, Labwork labwork) {
        UpdateLabworkByIdRequest request = new UpdateLabworkByIdRequest(id, labwork);
        return ((UpdateLabworkByIdResponse) getWebServiceTemplate().marshalSendAndReceive(WS_URI, request)).getLabwork();
    }

    public void deleteLabwork(long id) {
        DeleteLabworkByIdRequest request = new DeleteLabworkByIdRequest(id);
        getWebServiceTemplate().marshalSendAndReceive(WS_URI, request);
    }
}

