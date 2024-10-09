package service;

import exception.NotFoundException;
import exception.ValidationException;
import jakarta.ws.rs.core.MultivaluedMap;
import model.dto.*;
import model.dto.util.DTOValidator;
import model.generated.enums.DifficultyT;
import model.generated.tables.Labwork;
import model.generated.tables.records.LabworkRecord;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SortField;
import orm.DSLContextProvider;
import service.util.QueryContext;
import service.util.SortingQueryToken;

import java.util.List;
import java.util.Optional;

public class LabworkService {
    private static final DSLContext context = DSLContextProvider.getContext();

    public static LabworkDTO storeLabwork(LabworkDTO dto) {
        validateLabworkDTOAndThrow(dto);

        String disciplineName = dto.getDiscipline().getName();
        String faculty = dto.getDiscipline().getFaculty();
        checkDisciplineAndFacultyExistenceAndThrow(disciplineName, faculty);

        LabworkRecord record = context.newRecord(Labwork.LABWORK);
        record.setName(dto.getName());
        record.setCoordinateX(dto.getCoordinates().getX());
        record.setCoordinateY(dto.getCoordinates().getY());
        record.setMinimalPoint(dto.getMinimalPoint());
        record.setDifficulty(mapDTOToDifficulty(dto.getDifficulty()));
        record.setDiscipline(disciplineName);
        record.setFaculty(faculty);
        record.store();
        record.refresh(Labwork.LABWORK.CREATION_DATE);

        dto.setId(record.getId());
        dto.setCreationDate(record.getCreationDate());
        dto.getDiscipline().setSelfStudyHours(FacultyService.getSelfStudyHours(disciplineName));

        return dto;
    }

    public static LabworkPageDTO getAllLabworks(MultivaluedMap<String, String> queryParams) {
        QueryContext queryContext = new QueryContext(queryParams);

        List<Condition> conditions = queryContext.mapFilteringTokensToConditions();
        List<? extends SortField<?>> sortFields = queryContext.getSortingTokens().stream().map(SortingQueryToken::mapToSortField).toList();

        long totalCount = context.fetchCount(Labwork.LABWORK, conditions);

        var steps = context.select(
                        Labwork.LABWORK.ID,
                        Labwork.LABWORK.NAME,
                        Labwork.LABWORK.COORDINATE_X,
                        Labwork.LABWORK.COORDINATE_Y,
                        Labwork.LABWORK.CREATION_DATE,
                        Labwork.LABWORK.MINIMAL_POINT,
                        Labwork.LABWORK.DIFFICULTY,
                        Labwork.LABWORK.FACULTY,
                        Labwork.LABWORK.DISCIPLINE)
                .from(Labwork.LABWORK)
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

        List<LabworkDTO> resultList = result.stream()
                .map(record -> LabworkDTO.builder()
                        .id(record.getValue(Labwork.LABWORK.ID))
                        .name(record.getValue(Labwork.LABWORK.NAME))
                        .coordinates(new CoordinatesDTO(
                                record.getValue(Labwork.LABWORK.COORDINATE_X),
                                record.getValue(Labwork.LABWORK.COORDINATE_Y)))
                        .creationDate(record.getValue(Labwork.LABWORK.CREATION_DATE))
                        .minimalPoint(record.getValue(Labwork.LABWORK.MINIMAL_POINT))
                        .difficulty(mapDifficultyToDTO(record.getValue(Labwork.LABWORK.DIFFICULTY)))
                        .discipline(DisciplineDTO.builder()
                                .name(record.getValue(Labwork.LABWORK.DISCIPLINE))
                                .faculty(record.getValue(Labwork.LABWORK.FACULTY))
                                .selfStudyHours(FacultyService.getSelfStudyHours(record.getValue(Labwork.LABWORK.DISCIPLINE))).build())
                        .build())
                .toList();

        return LabworkPageDTO.builder()
                .elements(resultList)
                .totalCount(totalCount)
                .build();

    }

    public static LabworkDTO getLabworkById(long id) {
        LabworkRecord record = fetchById(id);
        return mapRecordToDTO(record);
    }

    public static LabworkDTO updateLabworkById(long id, LabworkDTO dto) {
        validateIdAndThrow(id);
        validateLabworkDTOAndThrow(dto);
        String disciplineName = dto.getDiscipline().getName();
        String faculty = dto.getDiscipline().getFaculty();
        checkDisciplineAndFacultyExistenceAndThrow(disciplineName, faculty);

        Optional<LabworkRecord> recordOpt = context.update(Labwork.LABWORK)
                .set(Labwork.LABWORK.NAME, dto.getName())
                .set(Labwork.LABWORK.COORDINATE_X, dto.getCoordinates().getX())
                .set(Labwork.LABWORK.COORDINATE_Y, dto.getCoordinates().getY())
                .set(Labwork.LABWORK.MINIMAL_POINT, dto.getMinimalPoint())
                .set(Labwork.LABWORK.DIFFICULTY, mapDTOToDifficulty(dto.getDifficulty()))
                .set(Labwork.LABWORK.FACULTY, faculty)
                .set(Labwork.LABWORK.DISCIPLINE, disciplineName)
                .where(Labwork.LABWORK.ID.eq(id))
                .returning(
                        Labwork.LABWORK.ID,
                        Labwork.LABWORK.NAME,
                        Labwork.LABWORK.COORDINATE_X,
                        Labwork.LABWORK.COORDINATE_Y,
                        Labwork.LABWORK.CREATION_DATE,
                        Labwork.LABWORK.MINIMAL_POINT,
                        Labwork.LABWORK.DIFFICULTY,
                        Labwork.LABWORK.FACULTY,
                        Labwork.LABWORK.DISCIPLINE)
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

        Optional<LabworkRecord> recordOpt = context.fetchOptional(Labwork.LABWORK, Labwork.LABWORK.ID.eq(id));
        recordOpt.orElseThrow(() -> new NotFoundException("Labwork with id=%d was not found".formatted(id)));
        return recordOpt.get();
    }

    private static void validateLabworkDTOAndThrow(LabworkDTO dto) {
        List<String> validationErrors = DTOValidator.validateLabworkDTO(dto);
        if (!validationErrors.isEmpty()) throw new ValidationException(validationErrors);
    }

    private static void validateIdAndThrow(long id) {
        Optional<String> error = DTOValidator.validateId(id);
        error.ifPresent(err -> {
            throw new ValidationException(err);
        });
    }

    private static void checkDisciplineAndFacultyExistenceAndThrow(String disciplineName, String faculty) {
        if (!FacultyService.disciplineExists(faculty, disciplineName))
            throw new NotFoundException("Discipline with name %s and faculty %s does not exist".formatted(disciplineName, faculty));
    }

    private static DifficultyT mapDTOToDifficulty(DifficultyDTO dto) {
        return DifficultyT.lookupLiteral(dto.getLiteral());
    }

    private static DifficultyDTO mapDifficultyToDTO(DifficultyT difficultyT) {
        return DifficultyDTO.fromString(difficultyT.getLiteral());
    }

    private static LabworkDTO mapRecordToDTO(LabworkRecord record) {
        return LabworkDTO.builder()
                .id(record.getId())
                .name(record.getName())
                .coordinates(new CoordinatesDTO(record.getCoordinateX(), record.getCoordinateY()))
                .creationDate(record.getCreationDate())
                .minimalPoint(record.getMinimalPoint())
                .difficulty(mapDifficultyToDTO(record.getDifficulty()))
                .discipline(DisciplineDTO.builder()
                        .name(record.getDiscipline())
                        .faculty(record.getFaculty())
                        .selfStudyHours(FacultyService.getSelfStudyHours(record.getDiscipline())).build())
                .build();
    }
}
