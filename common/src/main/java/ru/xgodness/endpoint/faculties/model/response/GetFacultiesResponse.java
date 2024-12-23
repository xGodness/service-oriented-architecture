package ru.xgodness.endpoint.faculties.model.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.xgodness.endpoint.faculties.model.dto.Faculty;

import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class GetFacultiesResponse {
    private List<Faculty> facultyList;

    @XmlElement(required = true)
    public List<Faculty> getFacultyList() {
        return facultyList;
    }
}
