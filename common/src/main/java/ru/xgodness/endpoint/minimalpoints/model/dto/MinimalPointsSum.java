package ru.xgodness.endpoint.minimalpoints.model.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class MinimalPointsSum {
    private Double value;

    @XmlElement(required = true)
    public Double getValue() {
        return value;
    }
}
