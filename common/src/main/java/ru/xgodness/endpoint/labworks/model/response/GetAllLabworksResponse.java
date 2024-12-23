package ru.xgodness.endpoint.labworks.model.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.xgodness.endpoint.labworks.model.dto.LabworkPage;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement
public class GetAllLabworksResponse {
    private LabworkPage labworkPage;

    @XmlElement(required = true)
    public LabworkPage getLabworkPage() {
        return labworkPage;
    }
}
