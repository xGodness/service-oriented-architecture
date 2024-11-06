package ru.xgodness.endpoint.faculties.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Discipline {
    private String faculty;
    private String name;
    private long selfStudyHours;
}
