package ru.xgodness.model.dto.util;

import ru.xgodness.endpoint.labworks.model.dto.Coordinates;
import ru.xgodness.endpoint.labworks.model.dto.Labwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Validator {
    public static List<String> validateLabwork(Labwork labwork) {
        List<String> errors = new ArrayList<>();
        validateLabworkName(labwork.getName()).ifPresent(errors::add);
        errors.addAll(validateCoordinates(labwork.getCoordinates()));
        validateMinimalPoint(labwork.getMinimalPoint()).ifPresent(errors::add);
        validateDisciplineName(labwork.getDiscipline().getName()).ifPresent(errors::add);
        return errors;
    }

    public static List<String> validateCoordinates(Coordinates coordinates) {
        List<String> errors = new ArrayList<>();
        if (coordinates == null) errors.add("Coordinates must not be null");
        else {
            validateCoordinateX(coordinates.getX()).ifPresent(errors::add);
            validateCoordinateY(coordinates.getY()).ifPresent(errors::add);
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
