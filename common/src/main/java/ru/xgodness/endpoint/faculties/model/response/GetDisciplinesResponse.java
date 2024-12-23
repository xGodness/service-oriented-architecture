package ru.xgodness.endpoint.faculties.model.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.xgodness.endpoint.faculties.model.dto.Discipline;

import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class GetDisciplinesResponse {
    private List<Discipline> disciplineList;

    @XmlElement(required = true)
    public List<Discipline> getDisciplineList() {
        return disciplineList;
    }
}
