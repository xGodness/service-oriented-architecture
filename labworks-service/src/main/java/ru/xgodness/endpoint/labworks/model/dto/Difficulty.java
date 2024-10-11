package ru.xgodness.endpoint.labworks.model.dto;

import jakarta.json.bind.annotation.JsonbTypeDeserializer;
import lombok.Getter;
import ru.xgodness.model.dto.util.DifficultyDeserializer;

@Getter
@JsonbTypeDeserializer(DifficultyDeserializer.class)
public enum Difficulty {
    VERY_EASY("very_easy"),
    EASY("easy"),
    HARD("hard"),
    VERY_HARD("very_hard"),
    HOPELESS("hopeless");

    private final String literal;

    Difficulty(String literal) {
        this.literal = literal;
    }

    public static Difficulty fromString(String literal) {
        return literal == null ? null : Difficulty.valueOf(literal.toUpperCase());
    }
}
