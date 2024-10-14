/*
 * This file is generated by jOOQ.
 */
package ru.xgodness.model.generated;


import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import ru.xgodness.model.generated.tables.Discipline;
import ru.xgodness.model.generated.tables.Faculty;
import ru.xgodness.model.generated.tables.Labwork;


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
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.discipline</code>.
     */
    public final Discipline DISCIPLINE = Discipline.DISCIPLINE;

    /**
     * The table <code>public.faculty</code>.
     */
    public final Faculty FACULTY = Faculty.FACULTY;

    /**
     * The table <code>public.labwork</code>.
     */
    public final Labwork LABWORK = Labwork.LABWORK;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Discipline.DISCIPLINE,
            Faculty.FACULTY,
            Labwork.LABWORK
        );
    }
}