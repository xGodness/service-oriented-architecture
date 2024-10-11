package ru.xgodness.endpoint.faculties;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("/faculties")
public class FacultyController {

    @PostMapping("/{faculty}/{discipline-name}/make-hardcore")
    public ResponseEntity<?> makeHardcore(@PathVariable("faculty") String faculty, @PathVariable("discipline-name") String disciplineName) {
        log.info("makeHardcore request with faculy = %s, disciplineName = %s".formatted(faculty, disciplineName));
        FacultyService.checkFacultyAndDisciplineExistence(faculty, disciplineName);
        log.info("Checked existence; faculty and discipline do exist");
        FacultyService.makeHardcore(faculty, disciplineName);
        return ResponseEntity.status(204).build();
    }
}
