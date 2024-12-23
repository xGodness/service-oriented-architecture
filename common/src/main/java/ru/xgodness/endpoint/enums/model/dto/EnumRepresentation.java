package ru.xgodness.endpoint.enums.model.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement
public class EnumRepresentation {

    private String value;
    private String displayValue;
    private int numericValue;

    @XmlElement(required = true)
    public String getValue() {
        return value;
    }

    @XmlElement(required = true)
    public String getDisplayValue() {
        return displayValue;
    }

    @XmlElement(required = true)
    public int getNumericValue() {
        return numericValue;
    }
}
