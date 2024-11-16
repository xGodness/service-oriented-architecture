package ru.xgodness.endpoint.minimalpoints;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.xgodness.endpoint.minimalpoints.model.dto.MinimalPointsSum;

@Log
@RestController
@RequestMapping("/minimal-points")
public class MinimalPointsController {

    @GetMapping(value = "/sum", produces = "application/json")
    public ResponseEntity<MinimalPointsSum> sumMinimalPoints() {
        var sum = MinimalPointService.sumMinimalPoints();
        return ResponseEntity.ok(new MinimalPointsSum(sum));
    }
}
