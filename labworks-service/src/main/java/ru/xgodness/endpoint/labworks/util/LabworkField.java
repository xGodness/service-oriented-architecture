package ru.xgodness.endpoint.labworks.util;

import lombok.Getter;
import org.jooq.TableField;
import ru.xgodness.model.generated.tables.Labwork;

@Getter
public enum LabworkField {
    ID("id"),
    NAME("name"),
    COORDINATE_X("coordinate_x"),
    COORDINATE_Y("coordinate_y"),
    CREATION_DATE("creation_date"),
    MINIMAL_POINT("minimal_point"),
    DIFFICULTY("difficulty"),
    FACULTY("faculty"),
    DISCIPLINE_NAME("discipline_name"),
    DISCIPLINE_SELF_STUDY_HOURS("discipline_self_study_hours");

    private final String literal;

    LabworkField(String literal) {
        this.literal = literal;
    }

    public static LabworkField fromString(String literal) {
        return literal == null ? null : LabworkField.valueOf(literal.toUpperCase());
    }

    public TableField<?, ?> mapToJooqField() {
        return switch (this) {
            case ID -> Labwork.LABWORK.ID;
            case NAME -> Labwork.LABWORK.NAME;
            case COORDINATE_X -> Labwork.LABWORK.COORDINATE_X;
            case COORDINATE_Y -> Labwork.LABWORK.COORDINATE_Y;
            case CREATION_DATE -> Labwork.LABWORK.CREATION_DATE;
            case MINIMAL_POINT -> Labwork.LABWORK.MINIMAL_POINT;
            case DIFFICULTY -> Labwork.LABWORK.DIFFICULTY;
            case FACULTY -> Labwork.LABWORK.FACULTY;
            case DISCIPLINE_NAME -> Labwork.LABWORK.DISCIPLINE;
            case DISCIPLINE_SELF_STUDY_HOURS -> Labwork.LABWORK.discipline().SELF_STUDY_HOURS;
        };
    }
}
