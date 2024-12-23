package ru.xgodness.endpoint.labworks.model.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import ru.xgodness.exception.ValidationException;

import java.io.Serializable;

@XmlRootElement
public enum Difficulty implements Serializable {
    VERY_EASY("very_easy"),
    EASY("easy"),
    HARD("hard"),
    VERY_HARD("very_hard"),
    HOPELESS("hopeless");

    @XmlElement(required = true)
    public String getLiteral() {
        return literal;
    }

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
