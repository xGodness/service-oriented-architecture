package ru.xgodness.endpoint.enums.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnumRepresentation {
    private String value;
    private String displayValue;
    private int numericValue;
}
