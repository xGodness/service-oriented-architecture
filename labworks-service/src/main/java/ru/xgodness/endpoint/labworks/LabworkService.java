package ru.xgodness.endpoint.labworks;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SortField;
import org.springframework.util.MultiValueMap;
import ru.xgodness.endpoint.faculties.FacultyService;
import ru.xgodness.endpoint.faculties.model.dto.Discipline;
import ru.xgodness.endpoint.labworks.model.dto.Coordinates;
import ru.xgodness.endpoint.labworks.model.dto.Difficulty;
import ru.xgodness.endpoint.labworks.model.dto.Labwork;
import ru.xgodness.endpoint.labworks.model.dto.LabworkPage;
import ru.xgodness.endpoint.labworks.util.QueryContext;
import ru.xgodness.endpoint.labworks.util.SortingQueryToken;
import ru.xgodness.exception.NotFoundException;
import ru.xgodness.exception.ValidationException;
import ru.xgodness.model.dto.util.Validator;
import ru.xgodness.model.generated.enums.DifficultyT;
import ru.xgodness.model.generated.tables.records.LabworkRecord;
import ru.xgodness.orm.DSLContextProvider;

import java.util.List;
import java.util.Optional;

import static ru.xgodness.model.generated.tables.Labwork.LABWORK;

public class LabworkService {
    private static final DSLContext context = DSLContextProvider.getContext();

    public static Labwork storeLabwork(Labwork dto) {
        validateLabworkDTOAndThrow(dto);

        String disciplineName = dto.getDiscipline().getName();
        String faculty = dto.getDiscipline().getFaculty();
        checkDisciplineAndFacultyExistenceAndThrow(disciplineName, faculty);

        LabworkRecord record = context.newRecord(LABWORK);
        record.setName(dto.getName());
        record.setCoordinateX(dto.getCoordinates().getX());
        record.setCoordinateY(dto.getCoordinates().getY());
        record.setMinimalPoint(dto.getMinimalPoint());
        record.setDifficulty(mapDTOToDifficultyT(dto.getDifficulty()));
        record.setDiscipline(disciplineName);
        record.setFaculty(faculty);
        record.store();
        record.refresh(LABWORK.CREATION_DATE);

        dto.setId(record.getId());
        dto.setCreationDate(record.getCreationDate());
        dto.getDiscipline().setSelfStudyHours(FacultyService.getSelfStudyHours(faculty, disciplineName));

        return dto;
    }

    public static LabworkPage getAllLabworks(MultiValueMap<String, String> queryParams) {
        QueryContext queryContext = new QueryContext(queryParams);

        List<Condition> conditions = queryContext.mapFilteringTokensToConditions();
        List<? extends SortField<?>> sortFields = queryContext.getSortingTokens().stream().map(SortingQueryToken::mapToSortField).toList();

        long totalCount = context.fetchCount(LABWORK, conditions);

        var steps = context.select(
                        LABWORK.ID,
                        LABWORK.NAME,
                        LABWORK.COORDINATE_X,
                        LABWORK.COORDINATE_Y,
                        LABWORK.CREATION_DATE,
                        LABWORK.MINIMAL_POINT,
                        LABWORK.DIFFICULTY,
                        LABWORK.FACULTY,
                        LABWORK.DISCIPLINE,
                        LABWORK.discipline().SELF_STUDY_HOURS)
                .from(
                        LABWORK.join(LABWORK.discipline())
                                .on(LABWORK.DISCIPLINE.eq(LABWORK.discipline().NAME)))
                .where(conditions)
                .orderBy(sortFields);

        Long limit = queryContext.getLimit();
        Long offset = queryContext.getOffset();
        Result<?> result;
        if (limit != null) {
            if (offset != null) result = steps.limit(limit).offset(offset).fetch();
            else result = steps.limit(limit).fetch();
        } else if (offset != null) result = steps.offset(offset).fetch();
        else result = steps.fetch();

        List<Labwork> resultList = result.stream()
                .map(record -> Labwork.builder()
                        .id(record.getValue(LABWORK.ID))
                        .name(record.getValue(LABWORK.NAME))
                        .coordinates(new Coordinates(
                                record.getValue(LABWORK.COORDINATE_X),
                                record.getValue(LABWORK.COORDINATE_Y)))
                        .creationDate(record.getValue(LABWORK.CREATION_DATE))
                        .minimalPoint(record.getValue(LABWORK.MINIMAL_POINT))
                        .difficulty(mapDifficultyTToDTO(record.getValue(LABWORK.DIFFICULTY)))
                        .discipline(Discipline.builder()
                                .name(record.getValue(LABWORK.DISCIPLINE))
                                .faculty(record.getValue(LABWORK.FACULTY))
                                .selfStudyHours(FacultyService.getSelfStudyHours(record.getValue(LABWORK.FACULTY), record.getValue(LABWORK.DISCIPLINE))).build())
                        .build())
                .toList();

        return LabworkPage.builder()
                .elements(resultList)
                .totalCount(totalCount)
                .build();

    }

    public static Labwork getLabworkById(long id) {
        LabworkRecord record = fetchById(id);
        return mapRecordToDTO(record);
    }

    public static Labwork updateLabworkById(long id, Labwork dto) {
        validateIdAndThrow(id);
        validateLabworkDTOAndThrow(dto);
        String disciplineName = dto.getDiscipline().getName();
        String faculty = dto.getDiscipline().getFaculty();
        checkDisciplineAndFacultyExistenceAndThrow(disciplineName, faculty);

        Optional<LabworkRecord> recordOpt = context.update(LABWORK)
                .set(LABWORK.NAME, dto.getName())
                .set(LABWORK.COORDINATE_X, dto.getCoordinates().getX())
                .set(LABWORK.COORDINATE_Y, dto.getCoordinates().getY())
                .set(LABWORK.MINIMAL_POINT, dto.getMinimalPoint())
                .set(LABWORK.DIFFICULTY, mapDTOToDifficultyT(dto.getDifficulty()))
                .set(LABWORK.FACULTY, faculty)
                .set(LABWORK.DISCIPLINE, disciplineName)
                .where(LABWORK.ID.eq(id))
                .returning(
                        LABWORK.ID,
                        LABWORK.NAME,
                        LABWORK.COORDINATE_X,
                        LABWORK.COORDINATE_Y,
                        LABWORK.CREATION_DATE,
                        LABWORK.MINIMAL_POINT,
                        LABWORK.DIFFICULTY,
                        LABWORK.FACULTY,
                        LABWORK.DISCIPLINE)
                .fetchOptional();
        recordOpt.orElseThrow(() -> new NotFoundException("Labwork with id=%d was not found".formatted(id)));
        return mapRecordToDTO(recordOpt.get());
    }

    public static void deleteLabwork(long id) {
        LabworkRecord record = fetchById(id);
        record.delete();
    }

    private static LabworkRecord fetchById(long id) {
        validateIdAndThrow(id);

        Optional<LabworkRecord> recordOpt = context.fetchOptional(LABWORK, LABWORK.ID.eq(id));
        recordOpt.orElseThrow(() -> new NotFoundException("Labwork with id=%d was not found".formatted(id)));
        return recordOpt.get();
    }

    private static void validateLabworkDTOAndThrow(Labwork dto) {
        List<String> validationErrors = Validator.validateLabwork(dto);
        if (!validationErrors.isEmpty()) throw new ValidationException(validationErrors);
    }

    private static void validateIdAndThrow(long id) {
        Optional<String> error = Validator.validateId(id);
        error.ifPresent(err -> {
            throw new ValidationException(err);
        });
    }

    private static void checkDisciplineAndFacultyExistenceAndThrow(String disciplineName, String faculty) {
        if (!FacultyService.disciplineExists(faculty, disciplineName))
            throw new NotFoundException("Discipline with name %s and faculty %s does not exist".formatted(disciplineName, faculty));
    }

    private static DifficultyT mapDTOToDifficultyT(Difficulty dto) {
        return DifficultyT.lookupLiteral(dto.getLiteral());
    }

    private static Difficulty mapDifficultyTToDTO(DifficultyT difficultyT) {
        return Difficulty.fromString(difficultyT.getLiteral());
    }

    private static Labwork mapRecordToDTO(LabworkRecord record) {
        return Labwork.builder()
                .id(record.getId())
                .name(record.getName())
                .coordinates(new Coordinates(record.getCoordinateX(), record.getCoordinateY()))
                .creationDate(record.getCreationDate())
                .minimalPoint(record.getMinimalPoint())
                .difficulty(mapDifficultyTToDTO(record.getDifficulty()))
                .discipline(Discipline.builder()
                        .name(record.getDiscipline())
                        .faculty(record.getFaculty())
                        .selfStudyHours(FacultyService.getSelfStudyHours(record.getFaculty(), record.getDiscipline())).build())
                .build();
    }
}
