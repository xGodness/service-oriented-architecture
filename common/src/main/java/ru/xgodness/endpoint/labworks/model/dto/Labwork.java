package ru.xgodness.endpoint.labworks.model.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;
import ru.xgodness.endpoint.faculties.model.dto.Discipline;
import ru.xgodness.util.LocalDateJAXBAdapter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@XmlRootElement
public class Labwork implements Serializable {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private double minimalPoint;
    private Difficulty difficulty;
    private Discipline discipline;

    @XmlElement
    public Long getId() {
        return id;
    }

    @XmlElement(required = true)
    public String getName() {
        return name;
    }

    @XmlElement(required = true)
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @XmlJavaTypeAdapter(LocalDateJAXBAdapter.class)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @XmlElement(required = true)
    public double getMinimalPoint() {
        return minimalPoint;
    }

    @XmlElement(required = true)
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @XmlElement(required = true)
    public Discipline getDiscipline() {
        return discipline;
    }
}
