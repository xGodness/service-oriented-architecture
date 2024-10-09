package endpoint.labworks.util;

import lombok.Getter;
import model.generated.enums.DifficultyT;
import model.generated.tables.Labwork;
import model.generated.tables.records.LabworkRecord;
import org.jooq.TableField;

import java.time.LocalDate;

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
    DISCIPLINE_NAME("discipline_name");

    private final String literal;

    LabworkField(String literal) {
        this.literal = literal;
    }

    public static LabworkField fromString(String literal) {
        return literal == null ? null : LabworkField.valueOf(literal.toUpperCase());
    }

    public TableField<LabworkRecord, ?> mapToJooqField() {
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
        };
    }

    public Class<?> mapToClass() {
        return switch (this) {
            case ID, COORDINATE_X -> Long.class;
            case NAME, FACULTY, DISCIPLINE_NAME -> String.class;
            case COORDINATE_Y -> Integer.class;
            case CREATION_DATE -> LocalDate.class;
            case MINIMAL_POINT -> Double.class;
            case DIFFICULTY -> DifficultyT.class;
        };
    }
}
