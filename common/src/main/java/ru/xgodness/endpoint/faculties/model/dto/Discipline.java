package ru.xgodness.endpoint.faculties.model.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@XmlRootElement
public class Discipline implements Serializable {
    private String faculty;
    private String name;
    private long selfStudyHours;

    @XmlElement(required = true)
    public String getFaculty() {
        return faculty;
    }

    @XmlElement(required = true)
    public String getName() {
        return name;
    }

    @XmlElement
    public long getSelfStudyHours() {
        return selfStudyHours;
    }
}
