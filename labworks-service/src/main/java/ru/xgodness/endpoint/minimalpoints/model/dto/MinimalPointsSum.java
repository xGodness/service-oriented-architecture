package ru.xgodness.endpoint.minimalpoints.model.dto;

import jakarta.json.bind.annotation.JsonbTypeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.xgodness.model.dto.util.MinimalPointsSumSerializer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonbTypeSerializer(MinimalPointsSumSerializer.class)
public class MinimalPointsSum {
    private Double value;
}
