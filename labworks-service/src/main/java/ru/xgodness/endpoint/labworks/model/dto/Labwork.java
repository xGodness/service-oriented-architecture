package ru.xgodness.endpoint.labworks.model.dto;

import lombok.*;
import ru.xgodness.endpoint.faculties.dto.Discipline;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Labwork {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Double minimalPoint;
    private Difficulty difficulty;
    private Discipline discipline;
}
