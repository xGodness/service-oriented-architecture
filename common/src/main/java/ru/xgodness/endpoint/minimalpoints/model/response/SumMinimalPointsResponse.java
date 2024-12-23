package ru.xgodness.endpoint.minimalpoints.model.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement
public class SumMinimalPointsResponse {
    Double value;

    @XmlElement(required = true)
    public Double getValue() {
        return value;
    }
}
