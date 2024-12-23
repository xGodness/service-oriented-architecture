package ru.xgodness.endpoint.faculties.model.dto;

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
public class FacultiesList {
    private List<Faculty> elements;

    @XmlElement(required = true)
    public List<Faculty> getElements() {
        return elements;
    }
}
