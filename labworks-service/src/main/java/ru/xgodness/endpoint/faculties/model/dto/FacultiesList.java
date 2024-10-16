package ru.xgodness.endpoint.faculties.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.xgodness.endpoint.faculties.dto.Faculty;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultiesList {
    private List<Faculty> elements;
}
