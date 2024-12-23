package ru.xgodness.endpoint.labworks.model.request;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class GetAllLabworksRequest {
    private List<QueryParam> queryParamList;

    @XmlElement
    public List<QueryParam> getQueryParamList() {
        return queryParamList;
    }
}
