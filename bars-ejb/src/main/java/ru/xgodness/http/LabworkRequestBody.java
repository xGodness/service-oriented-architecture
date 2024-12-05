package ru.xgodness.http;

import lombok.*;
import ru.xgodness.endpoint.faculties.dto.Discipline;
import ru.xgodness.endpoint.labworks.model.dto.Coordinates;
import ru.xgodness.endpoint.labworks.model.dto.Difficulty;
import ru.xgodness.endpoint.labworks.model.dto.Labwork;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LabworkRequestBody {
    private String name;
    private Coordinates coordinates;
    private double minimalPoint;
    private Difficulty difficulty;
    private Discipline discipline;

    public LabworkRequestBody(Labwork labwork) {
        name = labwork.getName();
        coordinates = labwork.getCoordinates();
        minimalPoint = labwork.getMinimalPoint();
        difficulty = labwork.getDifficulty();
        discipline = labwork.getDiscipline();
    }

    public static LabworkRequestBody mapLabworkAndChangeDiscipline(Labwork labwork, String faculty, String disciplineName) {
        return LabworkRequestBody.builder()
                .name(labwork.getName())
                .coordinates(labwork.getCoordinates())
                .minimalPoint(labwork.getMinimalPoint())
                .difficulty(labwork.getDifficulty())
                .discipline(
                        new Discipline(faculty, disciplineName, labwork.getDiscipline().getSelfStudyHours())
                )
                .build();
    }
}
