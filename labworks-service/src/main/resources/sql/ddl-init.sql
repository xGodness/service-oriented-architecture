DO $$ BEGIN
    CREATE TYPE difficulty_t AS ENUM ('very_easy', 'easy', 'hard', 'very_hard', 'hopeless');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

CREATE TABLE IF NOT EXISTS faculty (
    name                text            PRIMARY KEY     CHECK (name <> '')
);

CREATE TABLE IF NOT EXISTS discipline (
    name                text            NOT NULL        CHECK (name <> ''),
    faculty             text            NOT NULL        REFERENCES faculty(name),
    self_study_hours    integer         NOT NULL,
    PRIMARY KEY (name, faculty)
);

CREATE TABLE IF NOT EXISTS labwork (
    id                  bigint          PRIMARY KEY     GENERATED ALWAYS AS IDENTITY,
    name                text            NOT NULL        CHECK (name <> ''),
    coordinate_x        bigint          NOT NULL        CHECK (coordinate_x >= -896),
    coordinate_y        integer         NOT NULL        CHECK (coordinate_y >= -528),
    creation_date       date            NOT NULL,
    minimal_point       float           NOT NULL        CHECK (minimal_point > 0),
    difficulty          difficulty_t    NOT NULL,
    faculty             text            NOT NULL,
    discipline          text            NOT NULL,
    FOREIGN KEY (faculty, discipline) REFERENCES discipline(faculty, name)
);

CREATE FUNCTION set_date_on_labwork() RETURNS TRIGGER AS $$
    BEGIN
        NEW.creation_date := now();
        RETURN NEW;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_date_on_labwork_trigger BEFORE INSERT ON labwork
    FOR EACH ROW EXECUTE FUNCTION set_date_on_labwork();