package ru.xgodness.endpoint.faculties;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.xgodness.JNDIServiceProvider;
import ru.xgodness.faculties.FacultyService;

@Log
@RestController
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService = JNDIServiceProvider.getFacultyService();

    @PostMapping("/{faculty}/{discipline-name}/make-hardcore")
    public ResponseEntity<?> makeHardcore(@PathVariable("faculty") String faculty, @PathVariable("discipline-name") String disciplineName) {
        log.info("makeHardcore request with faculty = %s, disciplineName = %s".formatted(faculty, disciplineName));
        facultyService.checkFacultyAndDisciplineExistence(faculty, disciplineName);
        facultyService.makeHardcore(faculty, disciplineName);
        return ResponseEntity.status(204).build();
    }
}
