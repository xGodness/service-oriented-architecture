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
public class AddFacultyRequest {
    private String faculty;

    @XmlElement(required = true)
    public String getFaculty() {
        return faculty;
    }
}
