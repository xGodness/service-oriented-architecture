package ru.xgodness.labworks.dto;

import lombok.Getter;
import ru.xgodness.exception.ValidationException;

import java.io.Serializable;

@Getter
public enum Difficulty implements Serializable {
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

    public static Difficulty increase(Difficulty difficulty, int stepCount) {
        if (stepCount < 0) throw new ValidationException("Step count must be positive");
        return Difficulty.values()[Math.min(difficulty.ordinal() + stepCount, Difficulty.values().length - 1)];
    }
}



