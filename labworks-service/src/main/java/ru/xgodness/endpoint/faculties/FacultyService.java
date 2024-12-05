package ru.xgodness.endpoint.faculties;

import org.jooq.DSLContext;
import ru.xgodness.endpoint.faculties.model.dto.Discipline;
import ru.xgodness.endpoint.faculties.model.dto.Faculty;
import ru.xgodness.endpoint.faculties.model.dto.DisciplinesList;
import ru.xgodness.endpoint.faculties.model.dto.FacultiesList;
import ru.xgodness.exception.AlreadyExistsException;
import ru.xgodness.exception.NotFoundException;
import ru.xgodness.exception.ValidationException;
import ru.xgodness.model.generated.tables.Labwork;
import ru.xgodness.model.generated.tables.records.DisciplineRecord;
import ru.xgodness.model.generated.tables.records.FacultyRecord;
import ru.xgodness.orm.DSLContextProvider;

import static ru.xgodness.model.generated.tables.Discipline.DISCIPLINE;
import static ru.xgodness.model.generated.tables.Faculty.FACULTY;

public class FacultyService {
    private static final DSLContext context = DSLContextProvider.getContext();

    public static void deleteLabworksByFacultyAndDiscipline(String faculty, String disciplineName) {
        if (!disciplineExists(faculty, disciplineName))
            throw new NotFoundException("Discipline %s on faculty %s was not found".formatted(disciplineName, faculty));

        context.deleteFrom(Labwork.LABWORK)
                .where(Labwork.LABWORK.FACULTY.eq(faculty), Labwork.LABWORK.DISCIPLINE.eq(disciplineName))
                .execute();
    }

    public static DisciplinesList getAllDisciplines() {
        return new DisciplinesList(
                context.select(
                                DISCIPLINE.FACULTY,
                                DISCIPLINE.NAME,
                                DISCIPLINE.SELF_STUDY_HOURS)
                        .from(DISCIPLINE)
                        .orderBy(DISCIPLINE.FACULTY, DISCIPLINE.NAME)
                        .fetch()
                        .stream()
                        .map(record -> Discipline.builder()
                                .faculty(record.getValue(DISCIPLINE.FACULTY))
                                .name(record.getValue(DISCIPLINE.NAME))
                                .selfStudyHours(record.getValue(DISCIPLINE.SELF_STUDY_HOURS))
                                .build())
                        .toList()
        );
    }

    public static FacultiesList getAllFaculties() {
        return new FacultiesList(
                context.select(FACULTY.NAME)
                        .from(FACULTY)
                        .orderBy(FACULTY.NAME)
                        .fetch()
                        .stream()
                        .map(record -> new Faculty(record.getValue(DISCIPLINE.NAME)))
                        .toList()
        );
    }

    public static Faculty storeFaculty(Faculty dto) {
        String faculty = dto.getName();
        if (facultyExists(faculty))
            throw new AlreadyExistsException("Faculty %s already exists".formatted(faculty));

        FacultyRecord record = context.newRecord(FACULTY);
        record.setName(faculty);
        record.store();
        return new Faculty(record.getName());
    }

    public static Discipline storeDiscipline(Discipline dto) {
        String faculty = dto.getFaculty();
        String disciplineName = dto.getName();
        long selfStudyHours = dto.getSelfStudyHours();
        if (!facultyExists(faculty))
            throw new NotFoundException("Faculty %s was not found".formatted(faculty));
        if (disciplineExists(faculty, disciplineName))
            throw new AlreadyExistsException("Discipline %s on faculty %s already exists".formatted(faculty, disciplineName));
        if (selfStudyHours <= 0)
            throw new ValidationException("Self study hours must be positive");

        DisciplineRecord record = context.newRecord(DISCIPLINE);
        record.setFaculty(faculty);
        record.setName(disciplineName);
        record.setSelfStudyHours(selfStudyHours);
        record.store();

        return Discipline.builder()
                .faculty(record.getFaculty())
                .name(record.getName())
                .selfStudyHours(record.getSelfStudyHours())
                .build();
    }

    public static boolean facultyExists(String faculty) {
        return context.fetchExists(FACULTY, FACULTY.NAME.eq(faculty));
    }

    public static boolean disciplineExists(String faculty, String disciplineName) {
        return context.fetchOptional(
                DISCIPLINE,
                DISCIPLINE.NAME.eq(disciplineName),
                DISCIPLINE.FACULTY.eq(faculty)).isPresent();
    }

    public static long getSelfStudyHours(String faculty, String disciplineName) {
        var recordOpt = context.fetchOptional(DISCIPLINE, DISCIPLINE.FACULTY.eq(faculty), DISCIPLINE.NAME.eq(disciplineName));
        recordOpt.orElseThrow(() -> new NotFoundException("Discipline with name %s does not exist".formatted(disciplineName)));
        return recordOpt.get().getSelfStudyHours();
    }
}
