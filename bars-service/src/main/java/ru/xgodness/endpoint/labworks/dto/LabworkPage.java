package ru.xgodness.endpoint.labworks.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LabworkPage {
    private List<Labwork> elements;
    private long totalCount;
}