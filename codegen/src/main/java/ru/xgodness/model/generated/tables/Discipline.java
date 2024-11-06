/*
 * This file is generated by jOOQ.
 */
package ru.xgodness.model.generated.tables;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Check;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import ru.xgodness.model.generated.Keys;
import ru.xgodness.model.generated.Public;
import ru.xgodness.model.generated.tables.Faculty.FacultyPath;
import ru.xgodness.model.generated.tables.Labwork.LabworkPath;
import ru.xgodness.model.generated.tables.records.DisciplineRecord;


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
public class Discipline extends TableImpl<DisciplineRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.discipline</code>
     */
    public static final Discipline DISCIPLINE = new Discipline();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DisciplineRecord> getRecordType() {
        return DisciplineRecord.class;
    }

    /**
     * The column <code>public.discipline.name</code>.
     */
    public final TableField<DisciplineRecord, String> NAME = createField(DSL.name("name"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.discipline.faculty</code>.
     */
    public final TableField<DisciplineRecord, String> FACULTY = createField(DSL.name("faculty"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.discipline.self_study_hours</code>.
     */
    public final TableField<DisciplineRecord, Long> SELF_STUDY_HOURS = createField(DSL.name("self_study_hours"), SQLDataType.BIGINT.nullable(false), this, "");

    private Discipline(Name alias, Table<DisciplineRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Discipline(Name alias, Table<DisciplineRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.discipline</code> table reference
     */
    public Discipline(String alias) {
        this(DSL.name(alias), DISCIPLINE);
    }

    /**
     * Create an aliased <code>public.discipline</code> table reference
     */
    public Discipline(Name alias) {
        this(alias, DISCIPLINE);
    }

    /**
     * Create a <code>public.discipline</code> table reference
     */
    public Discipline() {
        this(DSL.name("discipline"), null);
    }

    public <O extends Record> Discipline(Table<O> path, ForeignKey<O, DisciplineRecord> childPath, InverseForeignKey<O, DisciplineRecord> parentPath) {
        super(path, childPath, parentPath, DISCIPLINE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class DisciplinePath extends Discipline implements Path<DisciplineRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> DisciplinePath(Table<O> path, ForeignKey<O, DisciplineRecord> childPath, InverseForeignKey<O, DisciplineRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private DisciplinePath(Name alias, Table<DisciplineRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public DisciplinePath as(String alias) {
            return new DisciplinePath(DSL.name(alias), this);
        }

        @Override
        public DisciplinePath as(Name alias) {
            return new DisciplinePath(alias, this);
        }

        @Override
        public DisciplinePath as(Table<?> alias) {
            return new DisciplinePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<DisciplineRecord> getPrimaryKey() {
        return Keys.DISCIPLINE_PKEY;
    }

    @Override
    public List<ForeignKey<DisciplineRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DISCIPLINE__DISCIPLINE_FACULTY_FKEY);
    }

    private transient FacultyPath _faculty;

    /**
     * Get the implicit join path to the <code>public.faculty</code> table.
     */
    public FacultyPath faculty() {
        if (_faculty == null)
            _faculty = new FacultyPath(this, Keys.DISCIPLINE__DISCIPLINE_FACULTY_FKEY, null);

        return _faculty;
    }

    private transient LabworkPath _labwork;

    /**
     * Get the implicit to-many join path to the <code>public.labwork</code>
     * table
     */
    public LabworkPath labwork() {
        if (_labwork == null)
            _labwork = new LabworkPath(this, null, Keys.LABWORK__LABWORK_FACULTY_DISCIPLINE_FKEY.getInverseKey());

        return _labwork;
    }

    @Override
    public List<Check<DisciplineRecord>> getChecks() {
        return Arrays.asList(
            Internal.createCheck(this, DSL.name("discipline_name_check"), "((name <> ''::text))", true)
        );
    }

    @Override
    public Discipline as(String alias) {
        return new Discipline(DSL.name(alias), this);
    }

    @Override
    public Discipline as(Name alias) {
        return new Discipline(alias, this);
    }

    @Override
    public Discipline as(Table<?> alias) {
        return new Discipline(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Discipline rename(String name) {
        return new Discipline(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Discipline rename(Name name) {
        return new Discipline(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Discipline rename(Table<?> name) {
        return new Discipline(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Discipline where(Condition condition) {
        return new Discipline(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Discipline where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Discipline where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Discipline where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Discipline where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Discipline where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Discipline where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Discipline where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Discipline whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Discipline whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
