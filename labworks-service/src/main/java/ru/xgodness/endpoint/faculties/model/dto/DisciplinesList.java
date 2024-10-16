package ru.xgodness.endpoint.faculties.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.xgodness.endpoint.faculties.dto.Discipline;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinesList {
    private List<Discipline> elements;
}
