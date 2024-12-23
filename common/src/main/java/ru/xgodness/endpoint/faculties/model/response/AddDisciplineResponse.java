package ru.xgodness.endpoint.faculties.model.response;

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
public class AddDisciplineResponse {
    private Discipline discipline;

    @XmlElement(required = true)
    public Discipline getDiscipline() {
        return discipline;
    }
}
