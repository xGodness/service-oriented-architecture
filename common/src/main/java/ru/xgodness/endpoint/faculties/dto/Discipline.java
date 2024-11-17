package ru.xgodness.endpoint.faculties.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Discipline implements Serializable {
    private String faculty;
    private String name;
    private long selfStudyHours;
}
