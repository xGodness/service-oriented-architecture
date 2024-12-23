package ru.xgodness.endpoint.enums.model.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.xgodness.endpoint.enums.model.dto.EnumRepresentation;

import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class GetEnumResponse {
    private List<EnumRepresentation> enumRepresentationsList;

    @XmlElement(required = true)
    public List<EnumRepresentation> getEnumRepresentationsList() {
        return enumRepresentationsList;
    }
}
