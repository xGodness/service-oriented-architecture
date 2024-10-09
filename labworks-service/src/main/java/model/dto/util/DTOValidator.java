package model.dto.util;

import model.dto.CoordinatesDTO;
import model.dto.LabworkDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DTOValidator {
    public static List<String> validateLabworkDTO(LabworkDTO dto) {
        List<String> errors = new ArrayList<>();
        validateLabworkName(dto.getName()).ifPresent(errors::add);
        errors.addAll(validateCoordinates(dto.getCoordinates()));
        validateMinimalPoint(dto.getMinimalPoint()).ifPresent(errors::add);
        validateDisciplineName(dto.getDiscipline().getName()).ifPresent(errors::add);
        return errors;
    }

    public static List<String> validateCoordinates(CoordinatesDTO coordinatesDTO) {
        List<String> errors = new ArrayList<>();
        if (coordinatesDTO == null) errors.add("Coordinates must not be null");
        else {
            validateCoordinateX(coordinatesDTO.getX()).ifPresent(errors::add);
            validateCoordinateY(coordinatesDTO.getY()).ifPresent(errors::add);
        }
        return errors;
    }

    public static Optional<String> validateId(long id) {
        if (id <= 0) return Optional.of("Id must be positive");
        return Optional.empty();
    }

    private static Optional<String> validateLabworkName(String name) {
        if (name == null || name.isEmpty()) return Optional.of("Labwork name must not be null or empty");
        return Optional.empty();
    }

    private static Optional<String> validateCoordinateX(long x) {
        if (x <= -895) return Optional.of("Coordinate X must be greater than -895");
        return Optional.empty();
    }

    private static Optional<String> validateCoordinateY(Integer y) {
        if (y == null) return Optional.of("Coordinate Y must not be null");
        if (y <= -529) return Optional.of("Coordinate Y must be greater than -529");
        return Optional.empty();
    }

    private static Optional<String> validateMinimalPoint(double minimalPoint) {
        if (minimalPoint <= 0) return Optional.of("Minimal point must be greater than 0");
        return Optional.empty();
    }

    private static Optional<String> validateDisciplineName(String name) {
        if (name == null || name.isEmpty()) return Optional.of("Discipline name must not be null or empty");
        return Optional.empty();
    }
}
