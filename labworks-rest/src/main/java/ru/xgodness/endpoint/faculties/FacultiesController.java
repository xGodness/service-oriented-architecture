package ru.xgodness.endpoint.faculties;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.xgodness.endpoint.faculties.model.dto.Discipline;
import ru.xgodness.endpoint.faculties.model.dto.DisciplinesList;
import ru.xgodness.endpoint.faculties.model.dto.FacultiesList;
import ru.xgodness.endpoint.faculties.model.dto.Faculty;

@Log
@RestController
@RequestMapping("/faculties")
public class FacultiesController {
    private final FacultiesClient facultiesClient;

    @Autowired
    public FacultiesController(FacultiesClient facultiesClient) {
        this.facultiesClient = facultiesClient;
    }

    @DeleteMapping(value = "/{faculty}/{discipline-name}/labworks")
    public ResponseEntity<Void> deleteLabworksByFacultyAndDiscipline(@PathVariable("faculty") String faculty,
                                                                     @PathVariable("discipline-name") String disciplineName) {
        facultiesClient.deleteLabworksByFacultyAndDiscipline(faculty, disciplineName);
        return ResponseEntity.status(204).build();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Faculty> postFaculty(@RequestBody Faculty faculty) {
        var result = facultiesClient.storeFaculty(faculty);
        return ResponseEntity.ok(result);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<FacultiesList> getFaculties() {
        var facultiesList = facultiesClient.getAllFaculties();
        return ResponseEntity.ok(facultiesList);
    }

    @GetMapping(value = "/disciplines", produces = "application/json")
    public ResponseEntity<DisciplinesList> getDisciplines() {
        var disciplineList = facultiesClient.getAllDisciplines();
        return ResponseEntity.ok(disciplineList);
    }

    @PostMapping(value = "/disciplines", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Discipline> postDiscipline(@RequestBody Discipline discipline) {
        var result = facultiesClient.storeDiscipline(discipline);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/check/{faculty}/{discipline-name}")
    public ResponseEntity<Void> checkFacultyAndDiscipline(@PathVariable("faculty") String faculty,
                                                          @PathVariable("discipline-name") String disciplineName) {
        facultiesClient.disciplineExists(faculty, disciplineName);
        return ResponseEntity.status(204).build();
    }
}