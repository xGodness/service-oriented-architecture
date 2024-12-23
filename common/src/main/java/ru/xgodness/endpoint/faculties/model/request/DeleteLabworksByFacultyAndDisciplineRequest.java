package ru.xgodness.endpoint.faculties.model.request;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class DeleteLabworksByFacultyAndDisciplineRequest {
    private String faculty;
    private String disciplineName;

    @XmlElement(required = true)
    public String getFaculty() {
        return faculty;
    }

    @XmlElement(required = true)
    public String getDisciplineName() {
        return disciplineName;
    }
}
