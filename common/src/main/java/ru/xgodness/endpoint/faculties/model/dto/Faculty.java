package ru.xgodness.endpoint.faculties.model.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement
public class Faculty implements Serializable {
    private String name;

    @XmlElement(required = true)
    public String getName() {
        return name;
    }
}
