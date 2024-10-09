package service;

import exception.AlreadyExistsException;
import exception.NotFoundException;
import exception.ValidationException;
import model.dto.DisciplineDTO;
import model.dto.FacultyDTO;
import model.generated.tables.Discipline;
import model.generated.tables.Faculty;
import model.generated.tables.Labwork;
import model.generated.tables.records.DisciplineRecord;
import model.generated.tables.records.FacultyRecord;
import org.jooq.DSLContext;
import orm.DSLContextProvider;

public class FacultyService {
    private static final DSLContext context = DSLContextProvider.getContext();

    public static void deleteLabworksByFacultyAndDiscipline(String faculty, String disciplineName) {
        if (!disciplineExists(faculty, disciplineName))
            throw new NotFoundException("Discipline %s on faculty %s was not found".formatted(disciplineName, faculty));

        context.deleteFrom(Labwork.LABWORK)
                .where(Labwork.LABWORK.FACULTY.eq(faculty), Labwork.LABWORK.DISCIPLINE.eq(disciplineName))
                .execute();
    }

    public static FacultyDTO storeFaculty(FacultyDTO dto) {
        String faculty = dto.getName();
        if (facultyExists(faculty))
            throw new AlreadyExistsException("Faculty %s already exists".formatted(faculty));

        FacultyRecord record = context.newRecord(Faculty.FACULTY);
        record.setName(faculty);
        record.store();
        return new FacultyDTO(record.getName());
    }

    public static DisciplineDTO storeDiscipline(DisciplineDTO dto) {
        String faculty = dto.getFaculty();
        String disciplineName = dto.getName();
        int selfStudyHours = dto.getSelfStudyHours();
        if (!facultyExists(faculty))
            throw new NotFoundException("Faculty %s was not found".formatted(faculty));
        if (disciplineExists(faculty, disciplineName))
            throw new AlreadyExistsException("Discipline %s on faculty %s already exists".formatted(faculty, disciplineName));
        if (selfStudyHours <= 0)
            throw new ValidationException("Self study hours must be positive");

        DisciplineRecord record = context.newRecord(Discipline.DISCIPLINE);
        record.setFaculty(faculty);
        record.setName(disciplineName);
        record.setSelfStudyHours(selfStudyHours);
        record.store();

        return DisciplineDTO.builder()
                .faculty(record.getFaculty())
                .name(record.getName())
                .selfStudyHours(record.getSelfStudyHours())
                .build();
    }

    public static boolean facultyExists(String faculty) {
        return context.fetchExists(Faculty.FACULTY, Faculty.FACULTY.NAME.eq(faculty));
    }

    public static boolean disciplineExists(String faculty, String disciplineName) {
        return context.fetchOptional(
                Discipline.DISCIPLINE,
                Discipline.DISCIPLINE.NAME.eq(disciplineName),
                Discipline.DISCIPLINE.FACULTY.eq(faculty)).isPresent();
    }

    public static int getSelfStudyHours(String disciplineName) {
        var recordOpt = context.fetchOptional(Discipline.DISCIPLINE, Discipline.DISCIPLINE.NAME.eq(disciplineName));
        recordOpt.orElseThrow(() -> new NotFoundException("Discipline with name %s does not exist".formatted(disciplineName)));
        return recordOpt.get().getSelfStudyHours();
    }
}
