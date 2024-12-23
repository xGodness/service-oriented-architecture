package ru.xgodness.endpoint.labworks;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.xgodness.endpoint.labworks.model.dto.Labwork;
import ru.xgodness.endpoint.labworks.model.dto.LabworkPage;

@Log
@RestController
@RequestMapping("/labworks")
public class LabworksController {
    private final LabworksClient labworksClient;

    @Autowired
    public LabworksController(LabworksClient labworksClient) {
        this.labworksClient = labworksClient;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Labwork> postLabwork(@RequestBody Labwork labwork) {
        var result = labworksClient.storeLabwork(labwork);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Labwork> getLabwork(@PathVariable("id") long id) {
        var result = labworksClient.getLabworkById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<LabworkPage> getAllLabworks(@RequestParam MultiValueMap<String, String> queryParameters) {
        log.info(queryParameters.toString());
        var page = labworksClient.getAllLabworks(queryParameters);
        return ResponseEntity.ok(page);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Labwork> updateLabwork(@PathVariable("id") long id, @RequestBody Labwork labwork) {
        var result = labworksClient.updateLabworkById(id, labwork);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteLabwork(@PathVariable("id") long id) {
        labworksClient.deleteLabwork(id);
        return ResponseEntity.status(204).build();
    }
}