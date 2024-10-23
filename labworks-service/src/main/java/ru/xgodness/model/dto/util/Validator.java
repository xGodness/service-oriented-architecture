package ru.xgodness.model.dto.util;

import ru.xgodness.endpoint.faculties.dto.Discipline;
import ru.xgodness.endpoint.labworks.model.dto.Coordinates;
import ru.xgodness.endpoint.labworks.model.dto.Labwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Validator {
    public static List<String> validateLabwork(Labwork labwork) {
        List<String> errors = new ArrayList<>();

        if (labwork == null) {
            errors.add("Labwork must not be null");
            return errors;
        }

        validateStringField(labwork.getName(), "Labwork name").ifPresent(errors::add);
        errors.addAll(validateCoordinates(labwork.getCoordinates()));
        validateMinimalPoint(labwork.getMinimalPoint()).ifPresent(errors::add);
        if (labwork.getDifficulty() == null) errors.add("Difficulty must not be null");
        errors.addAll(validateDiscipline(labwork.getDiscipline()));
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

    private static Optional<String> validateCoordinateX(Long x) {
        if (x == null) return Optional.of("Coordinate X must not be null");
        if (x <= -895) return Optional.of("Coordinate X must be greater than -895");
        return Optional.empty();
    }

    private static Optional<String> validateCoordinateY(Integer y) {
        if (y == null) return Optional.of("Coordinate Y must not be null");
        if (y <= -529) return Optional.of("Coordinate Y must be greater than -529");
        return Optional.empty();
    }

    private static Optional<String> validateMinimalPoint(Double minimalPoint) {
        if (minimalPoint == null) return Optional.of("Minimal point must not be null");
        if (Double.isInfinite(minimalPoint) || Double.isNaN(minimalPoint))
            return Optional.of("Minimal point must be finite number");
        if (minimalPoint < 0) return Optional.of("Minimal point must be greater than 0");
        return Optional.empty();
    }

    private static List<String> validateDiscipline(Discipline discipline) {
        List<String> errors = new ArrayList<>();
        if (discipline == null) errors.add("Discipline must not be null");
        else {
            validateStringField(discipline.getName(), "Discipline name").ifPresent(errors::add);
            validateStringField(discipline.getFaculty(), "Faculty").ifPresent(errors::add);
        }
        return errors;
    }

    private static Optional<String> validateStringField(String value, String fieldName) {
        if (value == null || value.isEmpty()) return Optional.of(fieldName + " must not be null or empty");
        if (value.length() > 255) return Optional.of(fieldName + " length must not be greater than 255 characters");
        return Optional.empty();
    }
}
