package ru.xgodness.endpoint.enums.model.request;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class GetEnumRequest {
    private String enumName;

    @XmlElement(required = true)
    public String getEnumName() {
        return enumName;
    }
}
