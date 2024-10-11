package ru.xgodness.endpoint.labworks;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.xgodness.endpoint.labworks.dto.Labwork;

@Log
@RestController
@RequestMapping("/labworks")
public class LabworkController {

    @PatchMapping("/{labwork-id}/difficulty/increase/{steps-count}")
    public ResponseEntity<Labwork> increaseDifficulty(@PathVariable("labwork-id") long id, @PathVariable("steps-count") int stepsCount) {
        log.info("increaseDifficulty request with id = %d, stepsCount = %d".formatted(id, stepsCount));
        var dto = LabworkService.increaseDifficultyByStepCount(id, stepsCount);
        return ResponseEntity.ok().body(dto);
    }
}