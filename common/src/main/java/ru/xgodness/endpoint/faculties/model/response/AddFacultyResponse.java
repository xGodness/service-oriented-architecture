package ru.xgodness.endpoint.faculties.model.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class AddFacultyResponse {
    private String faculty;

    @XmlElement(required = true)
    public String getFaculty() {
        return faculty;
    }
}
