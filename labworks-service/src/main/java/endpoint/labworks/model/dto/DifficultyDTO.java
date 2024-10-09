package endpoint.labworks.model.dto;

import jakarta.json.bind.annotation.JsonbTypeDeserializer;
import lombok.Getter;
import model.dto.util.DifficultyDTODeserializer;

@Getter
@JsonbTypeDeserializer(DifficultyDTODeserializer.class)
public enum DifficultyDTO {
    VERY_EASY("very_easy"),
    EASY("easy"),
    HARD("hard"),
    VERY_HARD("very_hard"),
    HOPELESS("hopeless");

    private final String literal;

    DifficultyDTO(String literal) {
        this.literal = literal;
    }

    public static DifficultyDTO fromString(String literal) {
        return literal == null ? null : DifficultyDTO.valueOf(literal.toUpperCase());
    }
}


