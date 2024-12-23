package ru.xgodness.endpoint.labworks.model.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@XmlRootElement
public class LabworkPage implements Serializable {
    private List<Labwork> elements;
    private long totalCount;

    @XmlElement(required = true)
    public List<Labwork> getElements() {
        return elements;
    }

    @XmlElement(required = true)
    public long getTotalCount() {
        return totalCount;
    }
}
