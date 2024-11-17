package ru.xgodness.endpoint.labworks.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Coordinates implements Serializable {
    private Long x;
    private Integer y;
}
