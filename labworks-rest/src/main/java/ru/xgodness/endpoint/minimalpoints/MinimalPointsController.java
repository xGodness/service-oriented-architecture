package ru.xgodness.endpoint.minimalpoints;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.xgodness.endpoint.minimalpoints.model.dto.MinimalPointsSum;

@Log
@RestController
@RequestMapping("/minimal-points")
public class MinimalPointsController {
    private final MinimalPointsClient minimalPointsClient;

    @Autowired
    public MinimalPointsController(MinimalPointsClient minimalPointsClient) {
        this.minimalPointsClient = minimalPointsClient;
    }

    @GetMapping(value = "/sum", produces = "application/json")
    public ResponseEntity<MinimalPointsSum> sumMinimalPoints() {
        var sum = minimalPointsClient.sumMinimalPoints();
        return ResponseEntity.ok(new MinimalPointsSum(sum));
    }
}