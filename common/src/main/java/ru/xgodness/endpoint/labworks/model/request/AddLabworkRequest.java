package ru.xgodness.endpoint.labworks.model.request;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.xgodness.endpoint.labworks.model.dto.Labwork;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement
public class AddLabworkRequest {
    private Labwork labwork;

    @XmlElement(required = true)
    public Labwork getLabwork() {
        return labwork;
    }
}