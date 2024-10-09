package model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LabworkPageDTO {
    private List<LabworkDTO> elements;
    private long totalCount;
}
