package ru.xgodness.endpoint.labworks.model.request;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class QueryParam {
    private String param;
    private String value;

    @XmlElement(required = true)
    public String getParam() {
        return param;
    }

    @XmlElement(required = true)
    public String getValue() {
        return value;
    }
}
