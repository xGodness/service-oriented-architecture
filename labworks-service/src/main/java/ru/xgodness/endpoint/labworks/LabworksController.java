package ru.xgodness.endpoint.labworks;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.xgodness.endpoint.labworks.model.dto.Labwork;
import ru.xgodness.endpoint.labworks.model.dto.LabworkPage;

@Log
@RestController
@RequestMapping("/labworks")
public class LabworksController {

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Labwork> postLabwork(@RequestBody Labwork labwork) {
        var result = LabworkService.storeLabwork(labwork);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Labwork> getLabwork(@PathVariable("id") long id) {
        var result = LabworkService.getLabworkById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<LabworkPage> getAllLabworks(@RequestParam MultiValueMap<String, String> queryParameters) {
        log.info(queryParameters.toString());
        var page = LabworkService.getAllLabworks(queryParameters);
        return ResponseEntity.ok(page);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Labwork> updateLabwork(@PathVariable("id") long id, @RequestBody Labwork labwork) {
        var result = LabworkService.updateLabworkById(id, labwork);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteLabwork(@PathVariable("id") long id) {
        LabworkService.deleteLabwork(id);
        return ResponseEntity.status(204).build();
    }
}
