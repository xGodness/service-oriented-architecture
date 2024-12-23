package ru.xgodness.endpoint.faculties.model.request;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.xgodness.endpoint.faculties.model.dto.Discipline;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class AddDisciplineRequest {
    private Discipline discipline;

    @XmlElement(required = true)
    public Discipline getDiscipline() {
        return discipline;
    }
}
