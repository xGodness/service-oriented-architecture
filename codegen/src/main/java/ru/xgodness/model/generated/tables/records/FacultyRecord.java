/*
 * This file is generated by jOOQ.
 */
package ru.xgodness.model.generated.tables.records;


import javax.annotation.processing.Generated;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;

import ru.xgodness.model.generated.tables.Faculty;


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
public class FacultyRecord extends UpdatableRecordImpl<FacultyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.faculty.name</code>.
     */
    public void setName(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.faculty.name</code>.
     */
    public String getName() {
        return (String) get(0);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FacultyRecord
     */
    public FacultyRecord() {
        super(Faculty.FACULTY);
    }

    /**
     * Create a detached, initialised FacultyRecord
     */
    public FacultyRecord(String name) {
        super(Faculty.FACULTY);

        setName(name);
        resetChangedOnNotNull();
    }
}
