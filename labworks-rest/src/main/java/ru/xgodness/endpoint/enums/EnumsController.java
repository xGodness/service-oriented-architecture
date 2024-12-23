package ru.xgodness.endpoint.enums;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.xgodness.endpoint.enums.model.dto.EnumRepresentation;

import java.util.List;

@Log
@RestController
@RequestMapping("/enums")
public class EnumsController {
    EnumsClient enumsClient;

    @Autowired
    public EnumsController(EnumsClient enumsClient) {
        this.enumsClient = enumsClient;
    }

    @GetMapping(value = "/{enum-name}", produces = "application/json")
    public ResponseEntity<List<EnumRepresentation>> getEnum(@PathVariable("enum-name") String enumName) {
        var result = enumsClient.getEnumRepresentation(enumName);
        return ResponseEntity.ok(result);
    }
}