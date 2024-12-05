package ru.xgodness.endpoint.faculties.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Faculty implements Serializable {
    private String name;
}
