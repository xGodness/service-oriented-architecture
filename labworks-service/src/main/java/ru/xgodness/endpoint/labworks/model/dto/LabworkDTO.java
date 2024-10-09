package endpoint.labworks.model.dto;

import endpoint.faculties.model.dto.DisciplineDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LabworkDTO {
    private Long id;
    private String name;
    private CoordinatesDTO coordinates;
    private java.time.LocalDate creationDate;
    private double minimalPoint;
    private DifficultyDTO difficulty;
    private DisciplineDTO discipline;
}
