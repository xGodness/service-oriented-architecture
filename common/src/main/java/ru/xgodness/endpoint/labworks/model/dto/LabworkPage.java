package ru.xgodness.endpoint.labworks.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LabworkPage implements Serializable {
    private List<Labwork> elements;
    private long totalCount;
}
