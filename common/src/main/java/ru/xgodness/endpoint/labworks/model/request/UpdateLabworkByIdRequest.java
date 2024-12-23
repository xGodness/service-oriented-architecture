package ru.xgodness.endpoint.labworks.model.request;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.xgodness.endpoint.labworks.model.dto.Labwork;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class UpdateLabworkByIdRequest {
    private long id;
    private Labwork labwork;

    @XmlElement(required = true)
    public long getId() {
        return id;
    }

    @XmlElement(required = true)
    public Labwork getLabwork() {
        return labwork;
    }
}
