package ru.xgodness.endpoint.labworks.util;

import lombok.Getter;
import org.jooq.Condition;
import ru.xgodness.exception.UnexpectedInputFormatException;
import ru.xgodness.exception.ValidationException;
import ru.xgodness.model.generated.enums.DifficultyT;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static ru.xgodness.model.generated.tables.Labwork.LABWORK;

@Getter
public class FilteringQueryToken extends QueryToken {
    private Operator operator;
    private Object value;

    public FilteringQueryToken(String queryParam) {
        String[] paramTokens = extractTokensFromParam(queryParam);
        super.setField(paramTokens[0]);
        setOperator(paramTokens[1]);
        setValue(paramTokens[2]);
    }

    private String[] extractTokensFromParam(String queryParam) {
        String[] paramTokens = queryParam.split("\\[|]=");
        if (paramTokens.length < 3)
            throw new UnexpectedInputFormatException("Cannot parse filter, format must be filter={parameter_name}[operator]={operand}");
        return paramTokens;
    }

    private void setOperator(String operator) {
        try {
            this.operator = Operator.fromString(operator);
        } catch (IllegalArgumentException ex) {
            throw new UnexpectedInputFormatException("Cannot parse operator name, must be one of: "
                    + Arrays.stream(Operator.values()).map(Operator::getLiteral).collect(Collectors.joining(", ")));
        }

    }

    private void setValue(String value) {
        this.value = switch (super.getField()) {
            case ID -> {
                try {
                    yield Long.parseLong(value);
                } catch (NumberFormatException ex) {
                    throw new UnexpectedInputFormatException("Id must be long type");
                }
            }
            case NAME -> {
                if (value.isEmpty()) throw new ValidationException("Name must not be empty");
                yield value;
            }
            case COORDINATE_X -> {
                try {
                    yield Long.parseLong(value);
                } catch (NumberFormatException ex) {
                    throw new UnexpectedInputFormatException("Coordinate X must be long type");
                }
            }
            case COORDINATE_Y -> {
                try {
                    yield Integer.parseInt(value);
                } catch (NumberFormatException ex) {
                    throw new UnexpectedInputFormatException("Coordinate Y must be integer type");
                }
            }
            case CREATION_DATE -> {
                try {
                    yield LocalDate.parse(value);
                } catch (DateTimeParseException ex) {
                    throw new UnexpectedInputFormatException("Creation date format must be YYYY-MM-DD");
                }
            }
            case MINIMAL_POINT -> {
                try {
                    yield Double.parseDouble(value);
                } catch (NumberFormatException ex) {
                    throw new UnexpectedInputFormatException("Minimal point must be double type");
                }
            }
            case DIFFICULTY -> {
                try {
                    yield DifficultyT.valueOf(value.toLowerCase());
                } catch (IllegalArgumentException ex) {
                    throw new UnexpectedInputFormatException("Difficulty must be one of: "
                            + Arrays.stream(DifficultyT.values()).map(DifficultyT::getLiteral).collect(Collectors.joining(", ")));
                }
            }
            case FACULTY -> {
                if (value.isEmpty()) throw new UnexpectedInputFormatException("Faculty must not be empty");
                yield value;
            }
            case DISCIPLINE_NAME -> {
                if (value.isEmpty()) throw new UnexpectedInputFormatException("Discipline name must not be empty");
                yield value;
            }
            case DISCIPLINE_SELF_STUDY_HOURS -> {
                try {
                    yield Long.parseLong(value);
                } catch (NumberFormatException ex) {
                    throw new UnexpectedInputFormatException("Discipline self study hours must be long type");
                }
            }
        };
    }

    /*
     * Every project has to have its own filthy side, right?
     * Unfortunately I didn't manage to find a great solution for mapping tokens to something JOOQ can work with,
     * and since I don't have much time for proper brainstorming there are two options left:
     * nasty SQL query builder or filthy 58-branched switch.
     * I chose the latter.
     * */
    public Condition mapToCondition() {
        return switch (getOperator()) {
            case LT -> switch (getField()) {
                case ID -> LABWORK.ID.lessThan((Long) getValue());
                case NAME -> LABWORK.NAME.lessThan((String) getValue());
                case COORDINATE_X -> LABWORK.COORDINATE_X.lessThan((Long) getValue());
                case COORDINATE_Y -> LABWORK.COORDINATE_Y.lessThan((Integer) getValue());
                case CREATION_DATE -> LABWORK.CREATION_DATE.lessThan((LocalDate) getValue());
                case MINIMAL_POINT -> LABWORK.MINIMAL_POINT.lessThan((Double) getValue());
                case DIFFICULTY -> LABWORK.DIFFICULTY.lessThan((DifficultyT) getValue());
                case FACULTY -> LABWORK.FACULTY.lessThan((String) getValue());
                case DISCIPLINE_NAME -> LABWORK.DISCIPLINE.lessThan((String) getValue());
                case DISCIPLINE_SELF_STUDY_HOURS -> LABWORK.discipline().SELF_STUDY_HOURS.lessThan((Long) getValue());
            };
            case LTE -> switch (getField()) {
                case ID -> LABWORK.ID.lessOrEqual((Long) getValue());
                case NAME -> LABWORK.NAME.lessOrEqual((String) getValue());
                case COORDINATE_X -> LABWORK.COORDINATE_X.lessOrEqual((Long) getValue());
                case COORDINATE_Y -> LABWORK.COORDINATE_Y.lessOrEqual((Integer) getValue());
                case CREATION_DATE -> LABWORK.CREATION_DATE.lessOrEqual((LocalDate) getValue());
                case MINIMAL_POINT -> LABWORK.MINIMAL_POINT.lessOrEqual((Double) getValue());
                case DIFFICULTY -> LABWORK.DIFFICULTY.lessOrEqual((DifficultyT) getValue());
                case FACULTY -> LABWORK.FACULTY.lessOrEqual((String) getValue());
                case DISCIPLINE_NAME -> LABWORK.DISCIPLINE.lessOrEqual((String) getValue());
                case DISCIPLINE_SELF_STUDY_HOURS -> LABWORK.discipline().SELF_STUDY_HOURS.lessOrEqual((Long) getValue());
            };
            case GT -> switch (getField()) {
                case ID -> LABWORK.ID.greaterThan((Long) getValue());
                case NAME -> LABWORK.NAME.greaterThan((String) getValue());
                case COORDINATE_X -> LABWORK.COORDINATE_X.greaterThan((Long) getValue());
                case COORDINATE_Y -> LABWORK.COORDINATE_Y.greaterThan((Integer) getValue());
                case CREATION_DATE -> LABWORK.CREATION_DATE.greaterThan((LocalDate) getValue());
                case MINIMAL_POINT -> LABWORK.MINIMAL_POINT.greaterThan((Double) getValue());
                case DIFFICULTY -> LABWORK.DIFFICULTY.greaterThan((DifficultyT) getValue());
                case FACULTY -> LABWORK.FACULTY.greaterThan((String) getValue());
                case DISCIPLINE_NAME -> LABWORK.DISCIPLINE.greaterThan((String) getValue());
                case DISCIPLINE_SELF_STUDY_HOURS -> LABWORK.discipline().SELF_STUDY_HOURS.greaterThan((Long) getValue());
            };
            case GTE -> switch (getField()) {
                case ID -> LABWORK.ID.greaterOrEqual((Long) getValue());
                case NAME -> LABWORK.NAME.greaterOrEqual((String) getValue());
                case COORDINATE_X -> LABWORK.COORDINATE_X.greaterOrEqual((Long) getValue());
                case COORDINATE_Y -> LABWORK.COORDINATE_Y.greaterOrEqual((Integer) getValue());
                case CREATION_DATE -> LABWORK.CREATION_DATE.greaterOrEqual((LocalDate) getValue());
                case MINIMAL_POINT -> LABWORK.MINIMAL_POINT.greaterOrEqual((Double) getValue());
                case DIFFICULTY -> LABWORK.DIFFICULTY.greaterOrEqual((DifficultyT) getValue());
                case FACULTY -> LABWORK.FACULTY.greaterOrEqual((String) getValue());
                case DISCIPLINE_NAME -> LABWORK.DISCIPLINE.greaterOrEqual((String) getValue());
                case DISCIPLINE_SELF_STUDY_HOURS -> LABWORK.discipline().SELF_STUDY_HOURS.greaterOrEqual((Long) getValue());
            };
            case EQ -> switch (getField()) {
                case ID -> LABWORK.ID.equal((Long) getValue());
                case NAME -> LABWORK.NAME.equal((String) getValue());
                case COORDINATE_X -> LABWORK.COORDINATE_X.equal((Long) getValue());
                case COORDINATE_Y -> LABWORK.COORDINATE_Y.equal((Integer) getValue());
                case CREATION_DATE -> LABWORK.CREATION_DATE.equal((LocalDate) getValue());
                case MINIMAL_POINT -> LABWORK.MINIMAL_POINT.equal((Double) getValue());
                case DIFFICULTY -> LABWORK.DIFFICULTY.equal((DifficultyT) getValue());
                case FACULTY -> LABWORK.FACULTY.equal((String) getValue());
                case DISCIPLINE_NAME -> LABWORK.DISCIPLINE.equal((String) getValue());
                case DISCIPLINE_SELF_STUDY_HOURS -> LABWORK.discipline().SELF_STUDY_HOURS.equal((Long) getValue());
            };
            case NEQ -> switch (getField()) {
                case ID -> LABWORK.ID.notEqual((Long) getValue());
                case NAME -> LABWORK.NAME.notEqual((String) getValue());
                case COORDINATE_X -> LABWORK.COORDINATE_X.notEqual((Long) getValue());
                case COORDINATE_Y -> LABWORK.COORDINATE_Y.notEqual((Integer) getValue());
                case CREATION_DATE -> LABWORK.CREATION_DATE.notEqual((LocalDate) getValue());
                case MINIMAL_POINT -> LABWORK.MINIMAL_POINT.notEqual((Double) getValue());
                case DIFFICULTY -> LABWORK.DIFFICULTY.notEqual((DifficultyT) getValue());
                case FACULTY -> LABWORK.FACULTY.notEqual((String) getValue());
                case DISCIPLINE_NAME -> LABWORK.DISCIPLINE.notEqual((String) getValue());
                case DISCIPLINE_SELF_STUDY_HOURS -> LABWORK.discipline().SELF_STUDY_HOURS.notEqual((Long) getValue());
            };
            case LIKE -> switch (getField()) {
                case ID, COORDINATE_X, COORDINATE_Y, CREATION_DATE, MINIMAL_POINT, DIFFICULTY, DISCIPLINE_SELF_STUDY_HOURS ->
                        throw new UnexpectedInputFormatException("Operator '~' (like) is supported only for string fields");
                case NAME -> LABWORK.NAME.contains((String) getValue());
                case FACULTY -> LABWORK.FACULTY.contains((String) getValue());
                case DISCIPLINE_NAME -> LABWORK.DISCIPLINE.contains((String) getValue());
            };
        };
    }
}