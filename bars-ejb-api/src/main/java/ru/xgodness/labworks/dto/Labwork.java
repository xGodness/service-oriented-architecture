package ru.xgodness.labworks.dto;

import lombok.*;
import ru.xgodness.endpoint.faculties.dto.Discipline;
import ru.xgodness.endpoint.labworks.model.dto.Coordinates;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Labwork implements Serializable {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private double minimalPoint;
    private Difficulty difficulty;
    private Discipline discipline;
}