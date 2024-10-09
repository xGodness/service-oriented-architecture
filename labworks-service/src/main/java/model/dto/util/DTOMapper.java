package model.dto.util;

import exception.ValidationException;
import model.dto.DifficultyDTO;
import model.dto.LabworkDTO;
import model.generated.enums.DifficultyT;
import model.generated.tables.Labwork;
import model.generated.tables.records.LabworkRecord;
import org.jooq.DSLContext;
import orm.DSLContextProvider;

import java.util.List;

public class DTOMapper {
    private static final DSLContext context = DSLContextProvider.getContext();

    public static DifficultyT mapDifficulty(DifficultyDTO dto) {
        return DifficultyT.lookupLiteral(dto.getLiteral());
    }

    public static LabworkRecord validateAndMapLabwork(LabworkDTO dto) {
        List<String> validationErrors = DTOValidator.validateLabworkDTO(dto);
        if (!validationErrors.isEmpty()) throw new ValidationException(validationErrors);

        LabworkRecord labwork = context.newRecord(Labwork.LABWORK);
        labwork.setName(dto.getName());
        labwork.setCoordinateX(dto.getCoordinates().getX());
        labwork.setCoordinateY(dto.getCoordinates().getY());
        labwork.setMinimalPoint(dto.getMinimalPoint());
        labwork.setDifficulty(mapDifficulty(dto.getDifficulty()));
        labwork.setDiscipline(dto.getDiscipline().getName());
        labwork.store();

        return labwork;
    }
}
