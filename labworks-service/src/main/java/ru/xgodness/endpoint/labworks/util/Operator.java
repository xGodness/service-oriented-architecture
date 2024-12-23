package ru.xgodness.endpoint.labworks.util;

import lombok.Getter;

@Getter
public enum Operator {
    LT("lt"),
    LTE("lte"),
    GT("gt"),
    GTE("gte"),
    EQ("eq"),
    NEQ("neq"),
    LIKE("~");

    private final String literal;

    Operator(String literal) {
        this.literal = literal;
    }

    public static Operator fromString(String literal) {
        if (literal == null) throw new IllegalArgumentException("Operator must not be null");
        if (literal.equals("~")) return LIKE;

        return Operator.valueOf(literal.toUpperCase());
    }

}
