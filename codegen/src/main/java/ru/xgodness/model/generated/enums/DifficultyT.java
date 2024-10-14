/*
 * This file is generated by jOOQ.
 */
package ru.xgodness.model.generated.enums;


import javax.annotation.processing.Generated;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;

import ru.xgodness.model.generated.Public;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.19.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum DifficultyT implements EnumType {

    very_easy("very_easy"),

    easy("easy"),

    hard("hard"),

    very_hard("very_hard"),

    hopeless("hopeless");

    private final String literal;

    private DifficultyT(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public String getName() {
        return "difficulty_t";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Lookup a value of this EnumType by its literal. Returns
     * <code>null</code>, if no such value could be found, see {@link
     * EnumType#lookupLiteral(Class, String)}.
     */
    public static DifficultyT lookupLiteral(String literal) {
        return EnumType.lookupLiteral(DifficultyT.class, literal);
    }
}