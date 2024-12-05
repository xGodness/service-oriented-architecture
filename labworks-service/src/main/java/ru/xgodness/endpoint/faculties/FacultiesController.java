package ru.xgodness.endpoint.faculties;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.xgodness.endpoint.faculties.model.dto.Discipline;
import ru.xgodness.endpoint.faculties.model.dto.Faculty;
import ru.xgodness.endpoint.faculties.model.dto.DisciplinesList;
import ru.xgodness.endpoint.faculties.model.dto.FacultiesList;
import ru.xgodness.exception.NotFoundException;

@Log
@RestController
@RequestMapping("/faculties")
public class FacultiesController {

    @DeleteMapping(value = "/{faculty}/{discipline-name}/labworks")
    public ResponseEntity<Void> deleteLabworksByFacultyAndDiscipline(@PathVariable("faculty") String faculty,
                                                                     @PathVariable("discipline-name") String disciplineName) {
        FacultyService.deleteLabworksByFacultyAndDiscipline(faculty, disciplineName);
        return ResponseEntity.status(204).build();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Faculty> postFaculty(@RequestBody Faculty faculty) {
        var result = FacultyService.storeFaculty(faculty);
        return ResponseEntity.ok(result);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<FacultiesList> getFaculties() {
        var facultiesList = FacultyService.getAllFaculties();
        return ResponseEntity.ok(facultiesList);
    }

    @GetMapping(value = "/disciplines", produces = "application/json")
    public ResponseEntity<DisciplinesList> getDisciplines() {
        var disciplineList = FacultyService.getAllDisciplines();
        return ResponseEntity.ok(disciplineList);
    }

    @PostMapping(value = "/disciplines", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Discipline> postDiscipline(@RequestBody Discipline discipline) {
        var result = FacultyService.storeDiscipline(discipline);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/check/{faculty}/{discipline-name}")
    public ResponseEntity<Void> checkFacultyAndDiscipline(@PathVariable("faculty") String faculty,
                                                          @PathVariable("discipline-name") String disciplineName) {
        if (FacultyService.disciplineExists(faculty, disciplineName))
            return ResponseEntity.status(204).build();
        throw new NotFoundException("Discipline %s on faculty %s was not found".formatted(faculty, disciplineName));
    }
}
