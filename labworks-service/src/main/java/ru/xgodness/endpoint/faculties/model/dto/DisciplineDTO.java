package endpoint.faculties.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DisciplineDTO {
    private String faculty;
    private String name;
    private int selfStudyHours;
}
