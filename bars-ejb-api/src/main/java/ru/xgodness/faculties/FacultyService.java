package ru.xgodness.faculties;

import jakarta.ejb.Remote;

@Remote
public interface FacultyService {
    void checkFacultyAndDisciplineExistence(String faculty, String disciplineName);

    void makeHardcore(String faculty, String disciplineName);
}
